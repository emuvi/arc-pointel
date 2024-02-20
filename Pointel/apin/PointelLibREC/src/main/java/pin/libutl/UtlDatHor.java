package pin.libutl;

import java.util.Objects;
import pin.libvlr.VDatHor;

public class UtlDatHor {

    public static String formato = "dd/MM/yyyy HH:mm:ss";
    public static String formatoSQL = "yyyy-MM-dd HH:mm:ss.SSS";
    public static String formatoMaquina = "yyyy-MM-dd-HH-mm-ss";

    public static Boolean eh(Object oValor) {
        Boolean retorno = false;
        if (oValor != null) {
            if (oValor instanceof java.util.Date) {
                retorno = true;
            } else if (oValor instanceof java.sql.Timestamp) {
                retorno = true;
            } else if (oValor instanceof VDatHor) {
                retorno = true;
            }
        }
        return retorno;
    }

    public static Boolean eh(Class daClasse) {
        Boolean retorno = false;
        if (daClasse != null) {
            if (java.util.Date.class.isAssignableFrom(UtlBin.pegaDaPrimitiva(daClasse))) {
                retorno = true;
            } else if (java.sql.Timestamp.class.isAssignableFrom(UtlBin.pegaDaPrimitiva(daClasse))) {
                retorno = true;
            } else if (VDatHor.class.isAssignableFrom(daClasse)) {
                retorno = true;
            }
        }
        return retorno;
    }

    public static java.util.Date pega(Object doValor) {
        return UtlTem.pega(doValor, formato, null);
    }

    public static java.util.Date pega(Object doValor, java.util.Date comPadrao) {
        return UtlTem.pega(doValor, formato, comPadrao);
    }

    public static java.util.Date pega(String dosCaracteres) {
        return UtlTem.pega(dosCaracteres, formato, null);
    }

    public static java.util.Date pega(String dosCaracteres, java.util.Date comPadrao) {
        return UtlTem.pega(dosCaracteres, formato, comPadrao);
    }

    public static Boolean ehIgual(Object oValor, Object eValor) {
        return Objects.equals(pega(oValor), pega(eValor));
    }

    public static boolean ehMaior(Object oValor, Object queValor) {
        if (oValor != null && queValor != null) {
            return pega(oValor).getTime() > pega(queValor).getTime();
        } else if (oValor == null & queValor == null) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean ehMenor(Object oValor, Object queValor) {
        if (oValor != null && queValor != null) {
            return pega(oValor).getTime() < pega(queValor).getTime();
        } else if (oValor == null & queValor == null) {
            return true;
        } else {
            return false;
        }
    }

    public static Boolean ehMaiorOuIgual(Object oValor, Object queValor) {
        return ehMaior(oValor, queValor) || ehIgual(oValor, queValor);
    }

    public static Boolean ehMenorOuIgual(Object oValor, Object queValor) {
        return ehMenor(oValor, queValor) || ehIgual(oValor, queValor);
    }

    public static java.util.Date soma(Object oValor, Object comValor) {
        return new java.util.Date(pega(oValor, new java.util.Date()).getTime() + pega(comValor, new java.util.Date()).getTime());
    }

    public static java.sql.Timestamp pegaSQL(Object doValor) {
        return pegaSQL(UtlTem.pega(doValor, formatoSQL, null));
    }

    public static java.sql.Timestamp pegaSQL(Object doValor, java.util.Date comPadrao) {
        return pegaSQL(UtlTem.pega(doValor, formatoSQL, comPadrao));
    }

    public static java.sql.Timestamp pegaSQL(java.util.Date doTempo) {
        return pegaSQL(doTempo, null);
    }

    public static java.sql.Timestamp pegaSQL(java.util.Date doTempo, java.sql.Timestamp comPadrao) {
        java.sql.Timestamp retorno = comPadrao;
        try {
            retorno = new java.sql.Timestamp(doTempo.getTime());
        } catch (Exception ex) {
        }
        return retorno;
    }

    public static java.util.Date pegaMaquina(Object doValor) {
        return UtlTem.pega(doValor, formatoMaquina, null);
    }

    public static java.util.Date pegaMaquina(Object doValor, java.util.Date comPadrao) {
        return UtlTem.pega(doValor, formatoMaquina, comPadrao);
    }

    public static java.util.Date pegaMaquina(String dosCaracteres) {
        return UtlTem.pega(dosCaracteres, formatoMaquina, null);
    }

    public static java.util.Date pegaMaquina(String dosCaracteres, java.util.Date comPadrao) {
        return UtlTem.pega(dosCaracteres, formatoMaquina, comPadrao);
    }

    public static String formata(java.util.Date oTempo) {
        return formata(oTempo, null);
    }

    public static String formata(java.util.Date oTempo, String comPadrao) {
        return UtlTem.formata(oTempo, formato, comPadrao);
    }

    public static String formataSQL(java.util.Date oTempo) {
        return formataSQL(oTempo, null);
    }

    public static String formataSQL(java.util.Date oTempo, String comPadrao) {
        return "'" + UtlTem.formata(oTempo, formatoSQL, comPadrao) + "'";
    }

    public static String formataMaquina(java.util.Date oTempo) {
        return formataMaquina(oTempo, null);
    }

    public static String formataMaquina(java.util.Date oTempo, String comPadrao) {
        return UtlTem.formata(oTempo, formatoMaquina, comPadrao);
    }

    public static String pegaAtual() {
        return formata(new java.util.Date());
    }

    public static String pegaAtualParaMaquina() {
        return UtlTem.formata(new java.util.Date(), formatoMaquina);
    }

    public static String pegaParaSQL(java.util.Date daData) {
        return pegaParaSQL(daData, null);
    }

    public static String pegaParaSQL(java.util.Date daData, String comPadrao) {
        return UtlTem.formata(daData, formatoSQL, comPadrao);
    }

    public static String pegaParaSQL(java.sql.Timestamp daData) {
        return pegaParaSQL(daData, null);
    }

    public static String pegaParaSQL(java.sql.Timestamp daData, String comPadrao) {
        return UtlTem.formata(UtlTem.pega(daData, formatoSQL), formatoSQL, comPadrao);
    }

    public static java.util.Date pergunta() {
        return pergunta(null, null);
    }

    public static java.util.Date pergunta(String comMensagem) {
        return pergunta(comMensagem, null);
    }

    public static java.util.Date pergunta(String comMensagem, java.util.Date ePadrao) {
        return pega(UtlCrs.pergunta(comMensagem, formata(ePadrao)));
    }

    public static String ajustaDigitado(String osCaracteres) {
        return UtlTem.ajustaDigitado(osCaracteres, formato);
    }
}
