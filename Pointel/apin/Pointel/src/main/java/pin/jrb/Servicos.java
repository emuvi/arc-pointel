package pin.jrb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import pin.libamg.EdtPla;
import pin.libbas.Conjunto;
import pin.libbas.ParsLista;
import pin.libutl.Utl;
import pin.libutl.UtlHor;
import pin.libutl.UtlMom;
import pin.libutl.UtlTem;
import pin.libvlr.Vlrs;
import pin.modinf.Carregador;
import pin.modinf.SQLite;

public class Servicos {

    private ParsLista<Servico> lista;
    private SQLite banco;

    public Servicos(String noArquivo) throws Exception {
        super();
        lista = new ParsLista<>();
        banco = new SQLite("Serviços", noArquivo + ".srs");
        banco.conecta();
        banco.opera("CREATE TABLE IF NOT EXISTS servicos (executar, tipo, fonte, acada, vezes, de, ate, ultimo)");
        banco.opera("CREATE TABLE IF NOT EXISTS executados (servico, momento, registro, retornos)");
        Conjunto rst = banco.busca("SELECT rowid, executar, tipo, fonte, acada, vezes, de, ate, ultimo FROM servicos ORDER BY rowid");
        while (rst.proximo()) {
            Integer codigo = rst.pgVlr(1).pgInt();
            Boolean executar = rst.pgVlr(2).pgLog();
            Servico.Tp tipo = Servico.Tp.valueOf(rst.pgVlr(3).pgCrs());
            String fonte = rst.pgVlr(4).pgCrs();
            UtlTem.Temporidade acada = UtlTem.Temporidade.valueOf(rst.pgVlr(5).pgCrs());
            Integer vezes = rst.pgVlr(6).pgInt();
            Date de = UtlHor.pega(rst.pgVlr(7).pgCrs());
            Date ate = UtlHor.pega(rst.pgVlr(8).pgCrs());
            Date ultimo = UtlMom.pegaMaquina(rst.pgVlr(9).pgCrs());
            Servico serv = new Servico(codigo, executar, tipo, fonte, acada, vezes, de, ate, ultimo);
            lista.add(serv);
        }
    }

    public Integer bota(Servico oServico) throws Exception {
        Integer retorno = 0;
        retorno = banco.opera("INSERT INTO servicos (executar, tipo, fonte, acada, vezes, de, ate, ultimo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                new Vlrs(oServico.executar.toString(), oServico.tipo.toString(), oServico.fonte, oServico.aCada.toString(), oServico.vezes, UtlHor.formata(oServico.de), UtlHor.formata(oServico.ate), UtlMom.formataMaquina(oServico.ultimo)));
        if (retorno > 0) {
            Conjunto rst = banco.busca("select last_insert_rowid();");
            if (rst.proximo()) {
                retorno = rst.pgVlr(1).pgInt();
            }
        }
        oServico.codigo = retorno;
        lista.add(oServico);
        return retorno;
    }

    public Integer muda(Servico oServico) throws Exception {
        for (Servico proc : lista) {
            if (proc.codigo.equals(oServico.codigo)) {
                proc.executar = oServico.executar;
                proc.tipo = oServico.tipo;
                proc.fonte = oServico.fonte;
                proc.aCada = oServico.aCada;
                proc.vezes = oServico.vezes;
                proc.de = oServico.de;
                proc.ate = oServico.ate;
                proc.ultimo = oServico.ultimo;
            }
        }
        return banco.opera("UPDATE servicos SET executar = ?, tipo = ?, fonte = ?, acada = ?, vezes = ?, de = ?, ate = ?, ultimo = ? WHERE rowid = ?",
                new Vlrs(oServico.executar.toString(), oServico.tipo.toString(), oServico.fonte, oServico.aCada.toString(), oServico.vezes, UtlHor.formata(oServico.de), UtlHor.formata(oServico.ate), UtlMom.formataMaquina(oServico.ultimo), oServico.codigo));
    }

    public Servico pega(Integer comCodigo) throws Exception {
        Servico retorno = null;
        for (Servico proc : lista) {
            if (proc.codigo.equals(comCodigo)) {
                retorno = proc;
                break;
            }
        }
        if (retorno == null) {
            Conjunto rst = banco.busca("SELECT executar, tipo, fonte, acada, vezes, de, ate, ultimo FROM servicos WHERE rowid = ?",
                    new Vlrs(comCodigo));
            if (rst.proximo()) {
                Integer codigo = rst.pgVlr(1).pgInt();
                Boolean executar = rst.pgVlr(2).pgLog();
                Servico.Tp tipo = Servico.Tp.valueOf(rst.pgVlr(3).pgCrs());
                String fonte = rst.pgVlr(4).pgCrs();
                UtlTem.Temporidade acada = UtlTem.Temporidade.valueOf(rst.pgVlr(5).pgCrs());
                Integer vezes = rst.pgVlr(6).pgInt();
                Date de = UtlHor.pega(rst.pgVlr(7).pgCrs());
                Date ate = UtlHor.pega(rst.pgVlr(8).pgCrs());
                Date ultimo = UtlMom.pegaMaquina(rst.pgVlr(9).pgCrs());
                retorno = new Servico(codigo, executar, tipo, fonte, acada, vezes, de, ate, ultimo);
                lista.add(retorno);
            }
        }
        return retorno;
    }

    public Integer tira(Integer comCodigo) throws Exception {
        for (Servico proc : lista) {
            if (proc.codigo.equals(comCodigo)) {
                lista.remove(proc);
                break;
            }
        }
        Vlrs params = new Vlrs(comCodigo);
        banco.opera("DELETE FROM executados WHERE servico = ?",
                params);
        return banco.opera("DELETE FROM servicos WHERE rowid = ?",
                params);
    }

    public void mudaUltimo(Servico doServico) throws Exception {
        doServico.ultimo = new Date();
        muda(doServico);
    }

    public Integer botaExecutado(Integer doServico, String comRegistro, String eRetornosCam) throws Exception {
        return banco.opera("INSERT INTO executados (servico, momento, registro, retornos) VALUES (?, ?, ?, ?)",
                new Vlrs(doServico, UtlMom.pegaAtualParaMaquina(), comRegistro, eRetornosCam));
    }

    public Carregador carregaExecutados(Integer doServico, EdtPla noEditor) throws Exception {
        noEditor.controlador().botaColunas("Momento", "Registro", "Retornos");
        return new Carregador(banco, "SELECT momento, registro, retornos FROM executados WHERE servico = ? ORDER BY momento DESC",
                new Vlrs(doServico))
                .mudaEditor(noEditor)
                .inicia();
    }

    public Servicos limpaExecutados(Integer doServico) throws Exception {
        banco.opera("DELETE FROM executados WHERE servico = ?", new Vlrs(doServico));
        return this;
    }

    public void executa() {
        for (Servico serv : lista) {
            try {
                if (serv.executar) {
                    boolean confUlt = true;
                    if (serv.ultimo != null) {
                        confUlt = UtlTem.periodico(serv.ultimo, serv.vezes, serv.aCada);
                    }
                    if (confUlt) {
                        if (serv.de != null && serv.ate != null) {
                            long nw = UtlHor.pega(UtlHor.formata(new Date())).getTime();
                            long de = serv.de.getTime();
                            long ate = serv.ate.getTime();
                            if (!(nw >= de && nw <= ate)) {
                                confUlt = false;
                            }
                        }
                    }
                    if (confUlt) {
                        mudaUltimo(serv);
                        Serv servExc = Serv.descrito(serv.fonte);
                        servExc.mCodigo(serv.codigo);
                        servExc.botaRegistra();
                        Serv.inicia(servExc);
                    }
                }
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    public void fecha() {
        banco.desconecta();
    }

    public List<Object[]> pegaTab() {
        List<Object[]> retorno = new ArrayList<>();
        for (Servico srv : lista) {
            retorno.add(new Object[]{srv.codigo, srv.executar.toString(), srv.tipo.toString(), srv.fonte, srv.aCada.toString(), srv.vezes, UtlHor.formata(srv.de), UtlHor.formata(srv.ate), UtlMom.formataMaquina(srv.ultimo)});
        }
        return retorno;
    }
}
