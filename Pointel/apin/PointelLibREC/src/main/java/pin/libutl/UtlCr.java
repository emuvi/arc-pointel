package pin.libutl;

import java.util.Objects;
import pin.libvlr.VCr;
import pin.libvlr.Vlr;

public class UtlCr {

    public static Boolean eh(Object oValor) {
        Boolean retorno = false;
        if (oValor != null) {
            if (oValor instanceof Character) {
                retorno = true;
            } else if (oValor instanceof VCr) {
                retorno = true;
            }
        }
        return retorno;
    }

    public static Boolean eh(Class daClasse) {
        Boolean retorno = false;
        if (daClasse != null) {
            if (Character.class.isAssignableFrom(UtlBin.pegaDaPrimitiva(daClasse))) {
                retorno = true;
            } else if (VCr.class.isAssignableFrom(daClasse)) {
                retorno = true;
            }
        }
        return retorno;
    }

    public static Character pega(Object doValor) {
        return pega(doValor, null);
    }

    public static Character pega(Object doValor, Character comPadrao) {
        if (doValor instanceof Vlr) {
            doValor = ((Vlr) doValor).pg();
        }
        Character chret = comPadrao;
        if (doValor != null) {
            if (doValor instanceof Character) {
                return (Character) doValor;
            } else {
                String retorno = UtlCrs.pega(doValor);
                if (retorno == null) {
                    return comPadrao;
                }
                if (retorno.isEmpty()) {
                    return comPadrao;
                }
                if (retorno.equals("null")) {
                    return comPadrao;
                }
                chret = retorno.charAt(0);
            }
        }
        return chret;
    }

    public static Character pegaSQL(Object doValor) {
        return pegaSQL(doValor, null);
    }

    public static Character pegaSQL(Object doValor, Character comPadrao) {
        return pega(doValor, comPadrao);
    }

    public static String formata(Character oValor) {
        return formata(oValor, null);
    }

    public static String formata(Character oValor, String comPadrao) {
        String retorno = comPadrao;
        if (oValor != null) {
            retorno = "" + oValor;
        }
        return retorno;
    }

    public static String formataSQL(Character oValor) {
        return formataSQL(oValor, null);
    }

    public static String formataSQL(Character oValor, String comPadrao) {
        String retorno = comPadrao;
        if (oValor != null) {
            retorno = "\"" + oValor + "\"";
        }
        return retorno;
    }

    public static Boolean ehIgual(Object oValor, Object eValor) {
        return Objects.equals(pega(oValor, Character.MIN_VALUE), pega(eValor, Character.MIN_VALUE));
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

    public static char[] crsNormais = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ', '\\', '|', ',', '<', '.', '>', ';', ':', '/', '?', ']', '}', '[', '{', '\'', '"', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_', '=', '+', '!', '@', '#', '$', '%', '&', '*', '(', ')'};
    public static char[] crsInteiros = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    public static char[] crsInteirosComNegativos = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-'};
    public static char[] crsNumericos = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.', ','};
    public static char[] crsNumericosComNegativos = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.', ',', '-'};
    public static char[] crsSimples = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '.', '_'};
    public static char[] crsMaiusculos = new char[]{'A', 'B', 'C', 'Ç', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    public static char[] crsMaiusculosSimples = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    public static char[] crsMaiusculosSimplesInteiros = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    public static char[] crsExpEspeciais = new char[]{'\\', '/', '|', '^', '~', '\'', '"', '!', '@', '#', '$', '%', '¨', '&', '.', ':', ',', ';', '?', '*', '+', '-', '=', '_', '<', '>', '{', '}', '[', ']', '(', ')', 'ª', 'º'};

    public static Boolean ehNormal(char oCaracter) {
        return UtlLis.tem(oCaracter, crsNormais);
    }

    public static Boolean ehInteiro(char oCaracter) {
        return UtlLis.tem(oCaracter, crsInteiros);
    }

    public static Boolean ehInteiroComNegativos(char oCaracter) {
        return UtlLis.tem(oCaracter, crsInteirosComNegativos);
    }

    public static Boolean ehNumerico(char oCaracter) {
        return UtlLis.tem(oCaracter, crsNumericos);
    }

    public static Boolean ehNumericoComNegativos(char oCaracter) {
        return UtlLis.tem(oCaracter, crsNumericosComNegativos);
    }

    public static Boolean ehSimples(char oCaracter) {
        return UtlLis.tem(oCaracter, crsSimples);
    }

    public static Boolean ehMaiusculo(char oCaracter) {
        return UtlLis.tem(oCaracter, crsMaiusculos);
    }

    public static Boolean ehMaiusculoSimples(char oCaracter) {
        return UtlLis.tem(oCaracter, crsMaiusculosSimples);
    }

    public static Boolean ehMaiusculoSimplesInteiros(char oCaracter) {
        return UtlLis.tem(oCaracter, crsMaiusculosSimplesInteiros);
    }

    public static Boolean ehExpEspeciais(char oCaracter) {
        return UtlLis.tem(oCaracter, crsExpEspeciais);
    }
}
