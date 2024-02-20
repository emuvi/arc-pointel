package pin.mgr;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import pin.libbas.Erro;
import pin.libutl.UtlCrs;
import pin.modinf.TabCampo;
import pin.modinf.TabRelacao;
import pin.modinf.Tabela;

public class MagoImpCst {

    public Integer indice;
    public String conexaoDe;
    public String conexaoPara;
    public Tabela tabelaDe;
    public Tabela tabelaPara;
    public List<TabRelacao> relacoes;
    public Boolean excluir;
    public Boolean inserir;
    public Boolean atualizar;
    public Boolean pausarSeErrar;

    public MagoImpCst() {
        this.indice = null;
        this.conexaoDe = null;
        this.conexaoPara = null;
        this.tabelaDe = null;
        this.tabelaPara = null;
        this.relacoes = null;
        this.excluir = null;
        this.inserir = null;
        this.atualizar = null;
        this.pausarSeErrar = null;
    }

    public MagoImpCst(String conexaoDe, String conexaoPara, Tabela tabelaDe, Tabela tabelaPara) {
        this.indice = null;
        this.conexaoDe = conexaoDe;
        this.conexaoPara = conexaoPara;
        this.tabelaDe = tabelaDe;
        this.tabelaPara = tabelaPara;
        this.relacoes = new ArrayList<>();
        this.excluir = null;
        this.inserir = null;
        this.atualizar = null;
        this.pausarSeErrar = null;
    }

    private TabCampo pegaReferencia(TabCampo doReferenciado) throws Exception {
        for (TabRelacao rel : relacoes) {
            if (Objects.equals(rel.referenciado, doReferenciado.pNome())) {
                return tabelaDe.pCampo(rel.referencia);
            }
        }
        return null;
    }

    public String pegaOperacao() throws Exception {
        String selecao = "SELECT ";
        for (TabCampo cmp : tabelaDe.pCampos()) {
            selecao += cmp.pNome() + ", ";
        }
        selecao = UtlCrs.corta(selecao, 2);
        selecao += " FROM " + tabelaDe;
        String comandos = "";
        if (excluir) {
            if (!comandos.isEmpty()) {
                comandos += "-prox-\n";
            }
            comandos += "DELETE FROM " + tabelaPara + " WHERE ";
            String pars = "";
            for (TabCampo cmp : tabelaPara.pCampos()) {
                if (cmp.ehChave()) {
                    comandos += cmp.pNome() + " = ? AND ";
                    TabCampo ref = pegaReferencia(cmp);
                    if (ref == null) {
                        throw new Erro("Não há referencia para a chave de destino " + cmp.pNome());
                    }
                    pars += (tabelaDe.pCampos().indexOf(ref) + 1) + ",";
                }
            }
            pars = UtlCrs.corta(pars, 1);
            comandos = UtlCrs.corta(comandos, 5);
            comandos += "\n-pars-\n" + pars + "\n";
        }
        if (inserir) {
            if (!comandos.isEmpty()) {
                comandos += "-prox-\n";
            }
            comandos += "INSERT INTO " + tabelaPara + " (";
            int quantC = 0;
            String pars = "";
            for (TabCampo cmp : tabelaPara.pCampos()) {
                TabCampo ref = pegaReferencia(cmp);
                if (ref != null) {
                    comandos += cmp.pNome() + ", ";
                    pars += (tabelaDe.pCampos().indexOf(ref) + 1) + ",";
                    quantC++;
                }
            }
            pars = UtlCrs.corta(pars, 1);
            comandos = UtlCrs.corta(comandos, 2) + ")\n";
            comandos += "  VALUES (";
            for (int ic = 1; ic <= quantC; ic++) {
                comandos += "?, ";
            }
            comandos = UtlCrs.corta(comandos, 2) + ")";
            comandos += "\n-pars-\n" + pars + "\n";
        }
        if (atualizar) {
            if (!comandos.isEmpty()) {
                comandos += "-prox-\n";
            }
            comandos += "UPDATE " + tabelaPara + " SET ";
            String where = " WHERE ";
            String pars = "";
            String parsWhere = "";
            for (TabCampo cmp : tabelaPara.pCampos()) {
                if (cmp.ehChave()) {
                    TabCampo ref = pegaReferencia(cmp);
                    if (ref != null) {
                        where += cmp.pNome() + " = ? AND ";
                        parsWhere += (tabelaDe.pCampos().indexOf(ref) + 1) + ",";
                    } else {
                        throw new Erro("Não há Referencia para a Chave " + cmp.pNome());
                    }
                } else {
                    TabCampo ref = tabelaDe.pCampo(cmp.pNome());
                    if (ref != null) {
                        comandos += cmp.pNome() + " = ? , ";
                        pars += (tabelaDe.pCampos().indexOf(ref) + 1) + ",";
                    }
                }
            }
            comandos = UtlCrs.corta(comandos, 3);
            where = UtlCrs.corta(where, 5);
            pars += parsWhere;
            pars = UtlCrs.corta(pars, 1);
            comandos += where;
            comandos += "\n-pars-\n" + pars + "\n";
        }
        comandos = comandos.trim();
        String construcao = "<pin.mgr.ExecutarParaCada>\n"
                + "  <conexaoSelecao>" + conexaoDe + "</conexaoSelecao>\n"
                + "  <conexaoComandos>" + conexaoPara + "</conexaoComandos>\n"
                + "  <pausarSeErrar>" + (pausarSeErrar ? "true" : "false") + "</pausarSeErrar>\n"
                + "  <selecao>" + selecao + "</selecao>\n"
                + "  <comandos>" + comandos + "</comandos>\n"
                + "</pin.mgr.ExecutarParaCada>";
        return construcao;
    }

    @Override
    public String toString() {
        return conexaoDe + " - " + tabelaDe + " > " + conexaoPara + " - " + tabelaPara;
    }

}
