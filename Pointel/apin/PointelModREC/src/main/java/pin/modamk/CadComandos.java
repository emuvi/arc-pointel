package pin.modamk;

import java.util.ArrayList;
import java.util.List;
import pin.libamk.Cmp;
import pin.libbas.Conjunto;
import pin.libbas.Dados;
import pin.libutl.Utl;
import pin.libutl.UtlCrs;
import pin.libvlr.Vlrs;
import pin.modinf.CodigosMod;

public class CadComandos {

    public Boolean ordernarDecrescente;
    public Cadastro cadastro;

    public Boolean selectFiltro;
    public Integer selectLimite;
    public Integer selectPular;

    public CadComandos(Cadastro aCadastro) {
        cadastro = aCadastro;
        selectFiltro = false;
        selectPular = -1;
        ordernarDecrescente = true;
        selectLimite = 10;
        try {
            if (cadastro.formatoCodigo == null) {
                cadastro.formatoCodigo = CodigosMod.pegaFormato(cadastro.conexao, cadastro.tabela);
            }
            if (aCadastro.formatoCodigo != null) {
                if (aCadastro.formatoCodigo.contains("MX")) {
                    ordernarDecrescente = false;
                } else if (aCadastro.formatoCodigo.contains("CX")) {
                    ordernarDecrescente = false;
                }
            }
            selectLimite = cadastro.conexao.conCfgs().pegaInt("PointelAllMake - Cadastro - Pegar Seleção Por Grupos De", 10);
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }

    public Conjunto selecionar() throws Exception {
        Conjunto retorno = null;
        Vlrs parametros = new Vlrs();
        String sqlChaves = "";
        String sqlChavesOrd = "";
        String ordPadrao = (ordernarDecrescente ? " DESC" : "");
        for (Cmp cmp : cadastro.camposDeValor) {
            if (cmp.pDetalhe("chave", false)) {
                sqlChaves += (cmp.pNome().contains(".") ? "" : cadastro.tabela + ".") + cmp.pNome() + ", ";
                sqlChavesOrd += (cmp.pNome().contains(".") ? "" : cadastro.tabela + ".") + cmp.pNome() + ordPadrao + ", ";
            }
        }

        sqlChaves = UtlCrs.corta(sqlChaves, 2);
        sqlChavesOrd = UtlCrs.corta(sqlChavesOrd, 2);

        String dtcOrdem = "";
        if (cadastro.cadProcurar != null) {
            dtcOrdem = cadastro.cadProcurar.ordem.trim().replaceAll("DESC", "");
        }
        if (!dtcOrdem.isEmpty()) {
            dtcOrdem += " , ";
        }

        String sqlSelect = "SELECT DISTINCT ON ( " + dtcOrdem + sqlChaves + " ) ";

        List<Dados.Tp> tipos = new ArrayList<>();
        for (Cmp cmp : cadastro.camposDeValor) {
            sqlSelect += (cmp.pNome().contains(".") ? "" : cadastro.tabela + ".") + cmp.pNome() + ", ";
            tipos.add(cmp.pTipo());
        }

        sqlSelect = UtlCrs.corta(sqlSelect, 2);

        String sqlFrom = " FROM " + cadastro.tabela;
        if (cadastro.cadRelacoes != null) {
            for (CadRelacao est : cadastro.cadRelacoes) {
                sqlFrom += (est.ehObrigatorio() ? "" : " LEFT") + " JOIN " + est.pegaEsquemaETabela() + (est.pegaAlias().isEmpty() ? "" : " AS " + est.pegaAlias()) + " ON ";
                for (CmpRelacao crl : est.relacoes()) {
                    sqlFrom += (crl.pegaReferencia().contains(".") ? "" : est.pegaNome() + ".") + crl.pegaReferencia() + " = "
                            + (crl.pegaReferenciado().contains(".") ? "" : cadastro.tabela + ".") + crl.pegaReferenciado() + " AND ";
                }
                sqlFrom = UtlCrs.corta(sqlFrom, 5);
            }
        }

        sqlSelect += sqlFrom;

        if (cadastro.cadProcurar != null) {
            String SqlWhere = cadastro.cadProcurar.pegaCondicao(parametros);
            if (!SqlWhere.isEmpty()) {
                sqlSelect += SqlWhere;
            }
        }

        for (Cmp cmp : cadastro.camposDeValor) {
            if (!cmp.pDetalhe("estrangeiro", false)) {
                if (cmp.pVlrFixo() != null) {
                    sqlSelect += (sqlSelect.contains("WHERE") ? " AND " : " WHERE ") + (cmp.pNome().contains(".") ? "" : cadastro.tabela + ".") + cmp.pNome() + " = ? ";
                    parametros.novo(cmp.pVlrFixo());
                }
                if (cmp.pRestritos() != null) {
                    if (cmp.pRestritos().size() > 0) {
                        sqlSelect += (sqlSelect.contains("WHERE") ? " AND (" : " WHERE (") + (cmp.pNome().contains(".") ? "" : cadastro.tabela + ".") + cmp.pNome() + " IN (";
                        for (Object ort : cmp.pRestritos()) {
                            sqlSelect += "? , ";
                            parametros.novo(ort);
                        }
                        sqlSelect = UtlCrs.corta(sqlSelect, 2) + ") ";
                        sqlSelect += " OR " + (cmp.pNome().contains(".") ? "" : cadastro.tabela + ".") + cmp.pNome() + " IS NULL)";
                    }
                }
            }
        }

        String sqlOrd = "";
        if (cadastro.cadProcurar != null) {
            sqlOrd = cadastro.cadProcurar.ordem.trim();
        }
        if (!sqlOrd.isEmpty()) {
            sqlOrd += " , ";
        }

        sqlSelect += " ORDER BY " + sqlOrd + sqlChavesOrd;

        if (selectLimite > 0) {
            sqlSelect += " LIMIT " + selectLimite;
        }
        if (selectPular > 0) {
            sqlSelect += " OFFSET " + selectPular;
        }

        retorno = cadastro.conexao.busca(sqlSelect, parametros, tipos);
        return retorno;
    }

    public Boolean inserir() throws Exception {
        Boolean retorno = false;
        Cadastro.botaProximo(cadastro);
        String sql = "INSERT INTO " + cadastro.tabela + " (";
        Vlrs valores = new Vlrs();
        Object[] chavesParaLista = new Object[cadastro.pegaChavesQuantidade()];
        Object[] valoresParaLista = new Object[cadastro.camposDeValor.size()];
        int iChave = 0;
        int iQuant = 0;
        for (int i1 = 0; i1 < cadastro.camposDeValor.size(); i1++) {
            Cmp cmp = cadastro.camposDeValor.get(i1);
            if (cmp.pDetalhe("chave", false)) {
                chavesParaLista[iChave] = cmp.pEdt().pgVlr();
                iChave++;
            }
            valoresParaLista[i1] = cmp.pEdt().pgVlr();
            if ((!cmp.pDetalhe("estrangeiro", false)) && (!cmp.pEdt().vazio())) {
                sql += cmp.pNome() + ", ";
                valores.add(cmp.pEdt().pgVlr());
                iQuant++;
            }
        }
        sql = sql.substring(0, sql.length() - 2);
        sql += ") VALUES (";
        for (int ic = 1; ic <= iQuant; ic++) {
            sql += "? , ";
        }
        sql = sql.substring(0, sql.length() - 2);
        sql += ")";
        retorno = cadastro.conexao.opera(sql, valores) > 0;
        if (retorno) {
            int nidx = 0;
            if (!ordernarDecrescente) {
                nidx = cadastro.dtmLista.getRowCount();
            }
            cadastro.dtmLista.insertRow(nidx, valoresParaLista);
            cadastro.cadCarregar.ultimoSelecionadoChaves = chavesParaLista;
            cadastro.cadCarregar.ultimoSelecionadoValores = valoresParaLista;
            cadastro.cadCarregar.ultimoSelecionadoIndex = nidx;
            cadastro.jtbLista.setRowSelectionInterval(nidx, nidx);
        }
        return retorno;
    }

    public Boolean modificar() throws Exception {
        Boolean retorno = false;
        Vlrs valores = new Vlrs();
        String sql = "UPDATE " + cadastro.tabela + " SET ";
        String where = " WHERE ";

        Boolean achou = false;
        for (int i1 = 0; i1 < cadastro.camposDeValor.size(); i1++) {
            Cmp cmp = cadastro.camposDeValor.get(i1);
            if ((!cmp.pDetalhe("chave", false)) && (!cmp.pDetalhe("estrangeiro", false))) {
                if (cadastro.verificaMudanca(cadastro.cadCarregar.ultimoSelecionadoValores[i1], cmp.pEdt().pgVlr())) {
                    sql += cmp.pNome() + " = ? , ";
                    valores.add(cmp.pEdt().pgVlr());
                    achou = true;
                }
            }
        }

        if (achou) {
            for (int i1 = 0; i1 < cadastro.camposDeValor.size(); i1++) {
                Cmp cmp = cadastro.camposDeValor.get(i1);
                if (cmp.pDetalhe("chave", false)) {
                    where += cmp.pNome() + " = ? AND ";
                    valores.add(cmp.pEdt().pgVlr());
                }
            }
            sql = UtlCrs.corta(sql, 3);
            where = UtlCrs.corta(where, 5);
            sql += where;

            retorno = cadastro.conexao.opera(sql, valores) > 0;
        } else {
            retorno = true;
        }

        if (retorno) {
            cadastro.cadCarregar.botaListaNoUltimoSelecionado();
            int sel = cadastro.jtbLista.getSelectedRow();
            if (sel > -1) {
                for (int i1 = 0; i1 < cadastro.camposDeValor.size(); i1++) {
                    Cmp cmp = cadastro.camposDeValor.get(i1);
                    if (!cmp.pDetalhe("chave", false)) {
                        cadastro.cadCarregar.ultimoSelecionadoValores[i1] = cmp.pEdt().pgVlr();
                        cadastro.dtmLista.setValueAt(cmp.pEdt().pgVlr(), sel, i1);
                    }
                }
            }
        }
        return retorno;
    }

    public Boolean excluir() throws Exception {
        Boolean retorno = false;
        Vlrs valores = new Vlrs();
        String sql = "DELETE FROM " + cadastro.tabela + " WHERE ";
        for (Cmp cmp : cadastro.camposDeValor) {
            if (cmp.pDetalhe("chave", false)) {
                sql += cmp.pNome() + " = ? AND ";
                valores.add(cmp.pEdt().pgVlr());
            }
        }
        sql = UtlCrs.corta(sql, 5);

        retorno = cadastro.conexao.opera(sql, valores) > 0;
        return retorno;
    }
}
