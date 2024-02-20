package pin.libutl;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Objects;
import pin.libvlr.VIntLon;
import pin.libvlr.Vlr;

public class UtlIntLon {

    public static Boolean eh(Object oValor) {
        Boolean retorno = false;
        if (oValor != null) {
            if (oValor instanceof Long) {
                retorno = true;
            } else if (oValor instanceof VIntLon) {
                retorno = true;
            }
        }
        return retorno;
    }

    public static Boolean eh(Class daClasse) {
        Boolean retorno = false;
        if (daClasse != null) {
            if (Long.class.isAssignableFrom(UtlBin.pegaDaPrimitiva(daClasse))) {
                retorno = true;
            } else if (VIntLon.class.isAssignableFrom(daClasse)) {
                retorno = true;
            }
        }
        return retorno;
    }

    public static Long pega(Object doValor) {
        return pega(doValor, null);
    }

    public static Long pega(Object doValor, Long comPadrao) {
        if (doValor instanceof Vlr) {
            doValor = ((Vlr) doValor).pg();
        }
        Long retorno = comPadrao;
        if (doValor != null) {
            if (doValor instanceof BigDecimal) {
                retorno = ((BigDecimal) doValor).longValue();
            } else if (doValor instanceof Double) {
                retorno = ((Double) doValor).longValue();
            } else if (doValor instanceof Float) {
                retorno = ((Float) doValor).longValue();
            } else if (doValor instanceof Long) {
                retorno = ((Long) doValor);
            } else if (doValor instanceof Integer) {
                retorno = ((Integer) doValor).longValue();
            } else if (doValor instanceof String) {
                retorno = pega((String) doValor, comPadrao);
            }
        }
        if (retorno == null) {
            return comPadrao;
        } else {
            return retorno;
        }
    }

    public static Long pega(String dosCaracteres) {
        return pega(dosCaracteres, null);
    }

    public static Long pega(String dosCaracteres, Long comPadrao) {
        if (dosCaracteres == null) {
            return comPadrao;
        }
        if (dosCaracteres.isEmpty()) {
            return comPadrao;
        }
        if (dosCaracteres.equals("null")) {
            return comPadrao;
        }
        try {
            return Long.valueOf(dosCaracteres);
        } catch (Exception ex) {
            try {
                return Long.valueOf(dosCaracteres.replaceAll("\\.", "").replaceAll(",", ""));
            } catch (Exception ex2) {
                return comPadrao;
            }
        }
    }

    public static Long pegaSQL(Object doValor) {
        return pegaSQL(doValor, null);
    }

    public static Long pegaSQL(Object doValor, Long comPadrao) {
        return pega(doValor, comPadrao);
    }

    public static String formata(Long oInteiroLon) {
        return formata(oInteiroLon, null, "");
    }

    public static String formata(Long oInteiroLon, String comPadrao) {
        return formata(oInteiroLon, null, comPadrao);
    }

    public static String formata(Long oInteiroLon, Integer comCasas, String ePadrao) {
        String retorno = ePadrao;
        if (oInteiroLon != null) {
            retorno = oInteiroLon.toString();
            if (comCasas != null) {
                if (comCasas > 0) {
                    while (retorno.length() < comCasas) {
                        retorno = "0" + retorno;
                    }
                    while (retorno.length() > comCasas) {
                        retorno = retorno.substring(1);
                    }
                }
            }
        }
        return retorno;
    }

    public static String formataSQL(Long oInteiroLon) {
        return formata(oInteiroLon, null, "");
    }

    public static String formataSQL(Long oInteiroLon, String comPadrao) {
        return formata(oInteiroLon, null, comPadrao);
    }

    public static String formataComPontos(Long oLongo) {
        return NumberFormat.getIntegerInstance().format(oLongo);
    }

    public static Long pegaMaior(Long longo, Long eLongo) {
        Long retorno = null;
        if ((longo != null) && (eLongo == null)) {
            retorno = longo;
        } else if ((longo == null) && (eLongo != null)) {
            retorno = eLongo;
        } else if ((longo != null) && (eLongo != null)) {
            if (longo > eLongo) {
                retorno = longo;
            } else {
                retorno = eLongo;
            }
        }
        return retorno;
    }

    public static Long pegaMenor(Long longo, Long eLongo) {
        Long retorno = null;
        if ((longo != null) && (eLongo == null)) {
            retorno = longo;
        } else if ((longo == null) && (eLongo != null)) {
            retorno = eLongo;
        } else if ((longo != null) && (eLongo != null)) {
            if (longo < eLongo) {
                retorno = longo;
            } else {
                retorno = eLongo;
            }
        }
        return retorno;
    }

    public static Long distancia(Long inteiro, Long eInteiro) {
        return pegaMaior(inteiro, eInteiro) - pegaMenor(inteiro, eInteiro);
    }

    public static Integer casas(Long doValor) {
        Integer casas = 1;
        Long total = doValor;
        while ((total = total / 10) > 1) {
            casas++;
        }
        return casas;
    }

    public static Boolean ehIgual(Object oValor, Object eValor) {
        return Objects.equals(pega(oValor), pega(eValor));
    }

    public static Boolean ehMaior(Object oValor, Object queValor) {
        Boolean retorno = false;
        if (oValor == null && queValor == null) {
            retorno = true;
        } else if (eh(oValor) && eh(queValor)) {
            retorno = pega(oValor) > pega(queValor);
        }
        return retorno;
    }

    public static Boolean ehMenor(Object oValor, Object queValor) {
        Boolean retorno = false;
        if (oValor == null && queValor == null) {
            retorno = true;
        } else if (eh(oValor) && eh(queValor)) {
            retorno = pega(oValor) < pega(queValor);
        }
        return retorno;
    }

    public static Boolean ehMaiorOuIgual(Object oValor, Object queValor) {
        return ehMaior(oValor, queValor) || ehIgual(oValor, queValor);
    }

    public static Boolean ehMenorOuIgual(Object oValor, Object queValor) {
        return ehMenor(oValor, queValor) || ehIgual(oValor, queValor);
    }

    public static Long pergunta() {
        return pergunta(null, null);
    }

    public static Long pergunta(String comMensagem) {
        return pergunta(comMensagem, null);
    }

    public static Long pergunta(String comMensagem, Long ePadrao) {
        return pega(UtlCrs.pergunta(comMensagem, formata(ePadrao)));
    }
}
