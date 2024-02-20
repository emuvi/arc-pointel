package pin.libutl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import pin.libvlr.VInt;
import pin.libvlr.Vlr;

public class UtlInt {

    public static Boolean eh(Object oValor) {
        Boolean retorno = false;
        if (oValor != null) {
            if (oValor instanceof Integer) {
                retorno = true;
            } else if (oValor instanceof VInt) {
                retorno = true;
            }
        }
        return retorno;
    }

    public static Boolean eh(Class daClasse) {
        Boolean retorno = false;
        if (daClasse != null) {
            if (Integer.class.isAssignableFrom(UtlBin.pegaDaPrimitiva(daClasse))) {
                retorno = true;
            } else if (VInt.class.isAssignableFrom(daClasse)) {
                retorno = true;
            }
        }
        return retorno;
    }

    public static Integer pega(Object doValor) {
        return pega(doValor, null);
    }

    public static Integer pega(Object doValor, Integer comPadrao) {
        if (doValor instanceof Vlr) {
            doValor = ((Vlr) doValor).pg();
        }
        Integer retorno = comPadrao;
        if (doValor != null) {
            if (doValor instanceof BigDecimal) {
                retorno = ((BigDecimal) doValor).intValue();
            } else if (doValor instanceof Double) {
                retorno = ((Double) doValor).intValue();
            } else if (doValor instanceof Float) {
                retorno = ((Float) doValor).intValue();
            } else if (doValor instanceof Long) {
                retorno = ((Long) doValor).intValue();
            } else if (doValor instanceof Integer) {
                retorno = ((Integer) doValor);
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

    public static Integer pega(String dosCaracteres) {
        return pega(dosCaracteres, null);
    }

    public static Integer pega(String dosCaracteres, Integer comPadrao) {
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
            return Integer.valueOf(dosCaracteres);
        } catch (Exception ex) {
            try {
                return Integer.valueOf(dosCaracteres.replaceAll("\\.", "").replaceAll(",", ""));
            } catch (Exception ex2) {
                return comPadrao;
            }
        }
    }

    public static Integer pegaSQL(Object doValor) {
        return pegaSQL(doValor, null);
    }

    public static Integer pegaSQL(Object doValor, Integer comPadrao) {
        return pega(doValor, comPadrao);
    }

    public static String formata(Integer oInteiro) {
        return formata(oInteiro, null, "");
    }

    public static String formata(Integer oInteiro, String comPadrao) {
        return formata(oInteiro, null, comPadrao);
    }

    public static String formata(Integer oInteiro, Integer comCasas) {
        return formata(oInteiro, comCasas, null);
    }

    public static String formata(Integer oInteiro, Integer comCasas, String ePadrao) {
        String retorno = ePadrao;
        if (oInteiro != null) {
            retorno = oInteiro.toString();
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

    public static String formataSQL(Integer oInteiro) {
        return formata(oInteiro, null, "");
    }

    public static String formataSQL(Integer oInteiro, String comPadrao) {
        return formata(oInteiro, null, comPadrao);
    }

    public static Integer pegaMaior(Integer inteiro, Integer eInteiro) {
        Integer retorno = null;
        if ((inteiro != null) && (eInteiro == null)) {
            retorno = inteiro;
        } else if ((inteiro == null) && (eInteiro != null)) {
            retorno = eInteiro;
        } else if ((inteiro != null) && (eInteiro != null)) {
            if (inteiro > eInteiro) {
                retorno = inteiro;
            } else {
                retorno = eInteiro;
            }
        }
        return retorno;
    }

    public static Integer pegaMenor(Integer inteiro, Integer eInteiro) {
        Integer retorno = null;
        if ((inteiro != null) && (eInteiro == null)) {
            retorno = inteiro;
        } else if ((inteiro == null) && (eInteiro != null)) {
            retorno = eInteiro;
        } else if ((inteiro != null) && (eInteiro != null)) {
            if (inteiro < eInteiro) {
                retorno = inteiro;
            } else {
                retorno = eInteiro;
            }
        }
        return retorno;
    }

    public static Integer pegaMenor(List<Integer> daLista) {
        Integer retorno = -1;
        Integer valor = -1;
        for (int i1 = 0; i1 < daLista.size(); i1++) {
            if (retorno == -1) {
                retorno = i1;
                valor = daLista.get(i1);
            } else if (daLista.get(i1) < valor) {
                retorno = i1;
                valor = daLista.get(i1);
            }
        }
        return retorno;
    }

    public static Integer distancia(Integer inteiro, Integer eInteiro) {
        return pegaMaior(inteiro, eInteiro) - pegaMenor(inteiro, eInteiro);
    }

    public static Integer casas(Integer doValor) {
        Integer casas = 1;
        Integer total = doValor;
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

    public static Integer pergunta() {
        return pergunta(null, null);
    }

    public static Integer pergunta(String comMensagem) {
        return pergunta(comMensagem, null);
    }

    public static Integer pergunta(String comMensagem, Integer ePadrao) {
        return pega(UtlCrs.pergunta(comMensagem, formata(ePadrao)));
    }

}
