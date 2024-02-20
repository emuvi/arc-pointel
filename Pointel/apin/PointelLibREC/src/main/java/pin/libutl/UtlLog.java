package pin.libutl;

import java.math.BigDecimal;
import java.util.Objects;
import pin.libitr.Alerta;
import pin.libvlr.VLog;
import pin.libvlr.Vlr;

public class UtlLog {

    public static Boolean eh(Object oValor) {
        Boolean retorno = false;
        if (oValor != null) {
            if (oValor instanceof Boolean) {
                retorno = true;
            } else if (oValor instanceof VLog) {
                retorno = true;
            }
        }
        return retorno;
    }

    public static Boolean eh(Class daClasse) {
        Boolean retorno = false;
        if (daClasse != null) {
            if (Boolean.class.isAssignableFrom(UtlBin.pegaDaPrimitiva(daClasse))) {
                retorno = true;
            } else if (VLog.class.isAssignableFrom(daClasse)) {
                retorno = true;
            }
        }
        return retorno;
    }

    public static Boolean pega(Object doValor) {
        return pega(doValor, null);
    }

    public static Boolean pega(Object doValor, Boolean comPadrao) {
        if (doValor instanceof Vlr) {
            doValor = ((Vlr) doValor).pg();
        }
        Boolean retorno = comPadrao;
        if (doValor != null) {
            if (doValor instanceof Boolean) {
                retorno = (Boolean) doValor;
            } else if (doValor instanceof Integer) {
                retorno = ((Integer) doValor) > 0;
            } else if (doValor instanceof Long) {
                retorno = ((Long) doValor) > 0l;
            } else if (doValor instanceof Float) {
                retorno = ((Float) doValor) > 0.0f;
            } else if (doValor instanceof Double) {
                retorno = ((Double) doValor) > 0.0;
            } else if (doValor instanceof BigDecimal) {
                retorno = ((BigDecimal) doValor).doubleValue() > 0.0;
            } else if (doValor instanceof String) {
                String fonte = ((String) doValor).toUpperCase();
                if (fonte.isEmpty()) {
                    return comPadrao;
                }
                if (fonte.equals("null")) {
                    return comPadrao;
                }
                retorno = (("S".equals(fonte) || "T".equals(fonte)) || ("SIM".equals(fonte) || "TRUE".equals(fonte)));
            }
        }
        if (retorno == null) {
            return comPadrao;
        } else {
            return retorno;
        }
    }

    public static String pegaSQL(Object doValor) {
        return pegaSQL(doValor, null);
    }

    public static String pegaSQL(Object doValor, Boolean comPadrao) {
        return pegaSQL(pega(doValor, false), "N");
    }

    public static String pegaSQL(Boolean doValor, String comPadrao) {
        if (doValor == null) {
            return comPadrao;
        } else {
            return (doValor ? "S" : "N");
        }
    }

    public static String formata(Boolean oValor) {
        return formata(oValor, null);
    }

    public static String formata(Boolean oValor, String comPadrao) {
        String retorno = comPadrao;
        if (oValor != null) {
            retorno = oValor ? "Sim" : "Não";
        }
        return retorno;
    }

    public static String formataSQL(Boolean oValor) {
        return formataSQL(oValor, null);
    }

    public static String formataSQL(Boolean oValor, String comPadrao) {
        String retorno = comPadrao;
        if (oValor != null) {
            retorno = oValor ? "'S'" : "'N'";
        }
        return retorno;
    }

    public static Boolean ehIgual(Object oValor, Object eValor) {
        return Objects.equals(pega(oValor), pega(eValor));
    }

    public static Boolean ehMaior(Object oValor, Object queValor) {
        Boolean retorno = false;
        if (oValor == null) {
            oValor = "";
        }
        if (queValor == null) {
            queValor = "";
        }
        if (eh(oValor) && eh(queValor)) {
            retorno = pega(oValor).compareTo(pega(queValor)) > 0;
        }
        return retorno;
    }

    public static Boolean ehMenor(Object oValor, Object queValor) {
        Boolean retorno = false;
        if (oValor == null) {
            oValor = "";
        }
        if (queValor == null) {
            queValor = "";
        }
        if (eh(oValor) && eh(queValor)) {
            retorno = pega(oValor).compareTo(pega(queValor)) < 0;
        }
        return retorno;
    }

    public static Boolean ehMaiorOuIgual(Object oValor, Object queValor) {
        return ehMaior(oValor, queValor) || ehIgual(oValor, queValor);
    }

    public static Boolean ehMenorOuIgual(Object oValor, Object queValor) {
        return ehMenor(oValor, queValor) || ehIgual(oValor, queValor);
    }

    public static Boolean pergunta(String comMsg) {
        return new Alerta(comMsg).botaSimNao().confirmado();
    }

    public static Boolean pergunta(String comMsg, Boolean ePadrao) {
        return new Alerta(comMsg).botaSimNao(ePadrao).confirmado();
    }

}
