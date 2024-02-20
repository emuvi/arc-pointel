package pin.modinf;

import pin.libout.Base36;
import pin.libutl.Utl;
import pin.libutl.UtlCrs;
import pin.libutl.UtlInt;
import pin.libutl.UtlIntLon;
import pin.libvlr.Vlrs;

public class CodigosMod {

    public static String pegaFormato(Banco noBanco, String daTabela) throws Exception {
        return noBanco.busca("SELECT formato FROM codigos WHERE tabela = ?",
                new Vlrs(daTabela))
                .pgCol("").pgCrs();
    }

    public static void botaFormato(Banco noBanco, String daTabela, String oFormato) throws Exception {
        if (noBanco.busca("SELECT tabela FROM codigos WHERE tabela = ?",
                new Vlrs(daTabela))
                .vazio()) {
            noBanco.opera("INSERT INTO codigos (tabela, formato) VALUES (?, ?)",
                    new Vlrs(daTabela, oFormato));
        } else {
            noBanco.opera("UPDATE codigos SET formato = ? WHERE tabela = ?",
                    new Vlrs(oFormato, daTabela));
        }
    }

    public static String pegaSequencia(Banco noBanco, String daTabela) throws Exception {
        return noBanco.busca("SELECT sequencia FROM codigos WHERE tabela = ?",
                new Vlrs(daTabela))
                .pgCol("").pgCrs();
    }

    public static void botaSequencia(Banco noBanco, String daTabela, String aSequencia) throws Exception {
        noBanco.opera("CREATE SEQUENCE IF NOT EXISTS " + aSequencia);
        if (noBanco.busca("SELECT tabela FROM codigos WHERE tabela = ?",
                new Vlrs(daTabela))
                .vazio()) {
            noBanco.opera("INSERT INTO codigos (tabela, sequencia) VALUES (?, ?)",
                    new Vlrs(daTabela, aSequencia));
        } else {
            noBanco.opera("UPDATE codigos SET sequencia = ? WHERE tabela = ?",
                    new Vlrs(aSequencia, daTabela));
        }
    }

    public static Long pegaUltimo(Banco noBanco, String daSequencia) throws Exception {
        return noBanco.busca("SELECT currval('" + daSequencia + "')")
                .pgCol(0).pgIntLon();
    }

    public static void botaUltimo(Banco noBanco, String daSequencia, Long oUltimo) throws Exception {
        noBanco.opera("DROP SEQUENCE " + daSequencia);
        oUltimo = oUltimo + 1;
        noBanco.opera("CREATE SEQUENCE " + daSequencia + " START " + oUltimo);
    }

    public static String pegaProximoCrs(Banco noBanco, String daTabela) throws Exception {
        return UtlCrs.pega(pegaProximo(noBanco, daTabela, "", ""));
    }

    public static String pegaProximoCrs(Banco noBanco, String daTabela, String comCampo, String eCondicao) throws Exception {
        return UtlCrs.pega(pegaProximo(noBanco, daTabela, comCampo, eCondicao));
    }

    public static Object pegaProximo(Banco noBanco, String daTabela) throws Exception {
        return pegaProximo(noBanco, daTabela, "", "");
    }

    public static Object pegaProximo(Banco noBanco, String daTabela, String comCampo, String eCondicao) throws Exception {
        Object retorno = null;
        String formato = pegaFormato(noBanco, daTabela);
        if (!formato.isEmpty()) {
            retorno = pegaProximo(noBanco, formato, daTabela, comCampo, eCondicao);
        } else {
            Utl.registra(new Exception("Não foi possível encontrar o formato para a tabela: " + daTabela + "\n\nContate o administrador do sistema."));
        }
        return retorno;
    }

    public static Object pegaProximo(Banco noBanco, String noFormato, String daTabela, String comCampo, String eCondicao) throws Exception {
        Object retorno = null;
        String[] formatos = noFormato.split(";");
        if (formatos[0].equals("MX")) {
            int casas = UtlInt.pega(formatos[1]);
            String ultimo = noBanco.busca("SELECT MAX(" + comCampo + ") FROM " + daTabela + " WHERE " + eCondicao)
                    .pgCol("").pgCrs();
            if (ultimo.isEmpty()) {
                ultimo = UtlCrs.repete("0", casas);
            }
            String proximo = UtlCrs.pegaProxima(ultimo, true);
            retorno = proximo;
        } else if (formatos[0].equals("CX")) {
            int casas = UtlInt.pega(formatos[1]);
            String ultimo = noBanco.busca("SELECT MAX(" + comCampo + ") FROM " + daTabela + " WHERE " + eCondicao)
                    .pgCol("").pgCrs();
            if (ultimo.isEmpty()) {
                ultimo = UtlCrs.repete("0", casas);
            }
            String proximo = UtlCrs.pegaProxima(ultimo, false);
            retorno = proximo;
        } else {
            String sequencia = pegaSequencia(noBanco, daTabela);
            if (sequencia.isEmpty()) {
                sequencia = "seq" + daTabela.replaceAll("\\.", "");
                botaSequencia(noBanco, daTabela, sequencia);
            }
            Long nextval = noBanco.busca("SELECT nextval('" + sequencia + "')")
                    .pgCol(1).pgIntLon();
            if (formatos[0].equals("NS")) {
                int casas = UtlInt.pega(formatos[1]);
                String proximo = UtlIntLon.formata(nextval);
                proximo = UtlCrs.repete("0", casas - proximo.length()) + proximo;
                retorno = proximo;
            } else if (formatos[0].equals("CS")) {
                int casas = UtlInt.pega(formatos[1]);
                String proximo = Base36.fromBase10(nextval);
                proximo = UtlCrs.repete("0", casas - proximo.length()) + proximo;
                retorno = proximo;
            }
        }
        return retorno;
    }

}
