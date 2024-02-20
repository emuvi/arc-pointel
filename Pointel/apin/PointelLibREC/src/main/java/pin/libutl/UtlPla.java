package pin.libutl;

import pin.libbas.Conjunto;
import pin.libvlr.Vlr;
import pin.libvlr.Vlrs;
import java.util.List;

public class UtlPla {

    public static Boolean ehExtensao(String aExtensao) {
        if (!aExtensao.startsWith(".")) {
            aExtensao = "." + aExtensao;
        }
        aExtensao = aExtensao.toLowerCase();
        return UtlLis.tem(aExtensao, pegaExtensoes());
    }

    public static String[] pegaExtensoes() {
        return new String[]{"cnj", "ods", "odc", "xls", "xls", "csv"};
    }

    public static String pegaExtensoesDescricao() {
        return "Planilhas (*.cnj, *.ods, *.odc, *.xls, *.xls, *.csv)";
    }

    public static Conjunto pega(Object doValor) {
        return pega(doValor, null);
    }

    public static Conjunto pega(Object doValor, Conjunto comPadrao) {
        Conjunto retorno = comPadrao;
        if (doValor != null) {
            if (doValor instanceof Vlr) {
                doValor = ((Vlr) doValor).pg();
            }
            if (doValor instanceof Conjunto) {
                retorno = (Conjunto) doValor;
            } else {
                retorno = new Conjunto();
                if (doValor instanceof Vlrs) {
                    retorno.bota((Vlrs) doValor);
                } else if (UtlArq.eh(doValor)) {
                    Vlrs vlrs = new Vlrs(doValor);
                    retorno.bota(vlrs);
                } else if (UtlLis.eh(doValor)) {
                    List<Object> lst = UtlLis.pega(doValor);
                    Vlrs vlrs = new Vlrs(lst.toArray());
                    retorno.bota(vlrs);
                } else {
                    Vlrs vlrs = new Vlrs(doValor);
                    retorno.bota(vlrs);
                }
            }
        }
        return retorno;
    }

    public static String formata(Conjunto aPlanilha) {
        if (aPlanilha != null) {
            return aPlanilha.toString();
        } else {
            return "";
        }
    }

    public static String descreve(Conjunto aPlanilha) {
        if (aPlanilha != null) {
            return aPlanilha.descreve();
        } else {
            return "";
        }
    }

    public static Conjunto descrito(String nosCaracteres) throws Exception {
        return Conjunto.descrito(nosCaracteres);
    }

}
