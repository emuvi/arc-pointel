package pin.libbas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import pin.libutl.UtlBin;
import static pin.libutl.UtlCrs.troca;

public class Partes {

    public static Boolean eh(String osCaracteres) {
        Boolean retorno = false;
        if (osCaracteres != null) {
            if (osCaracteres.contains(separador)) {
                retorno = true;
            }
        }
        return retorno;
    }

    public static String separador = "‗";

    public static String prepara(String osCaracteres) {
        return prepara(osCaracteres, separador);
    }

    public static String prepara(String osCaracteres, String paraSeparador) {
        String retorno = osCaracteres;
        retorno = troca(retorno, "&", "&amp;");
        retorno = troca(retorno, separador, "&sep;");
        return retorno;
    }

    public static String preparado(String dosCaracteres) {
        return preparado(dosCaracteres, separador);
    }

    public static String preparado(String dosCaracteres, String paraSeparador) {
        String retorno = dosCaracteres;
        retorno = troca(retorno, "&sep;", separador);
        retorno = troca(retorno, "&amp;", "&");
        return retorno;
    }

    public static String bota(String nosCaracteres, String aParte, String comSeparador) {
        String retorno = nosCaracteres == null ? "" : nosCaracteres;
        retorno += (retorno.isEmpty() ? "" : comSeparador) + prepara((aParte == null ? "" : aParte), comSeparador);
        return retorno;
    }

    public static String bota(String nosCaracteres, String aParte) {
        return bota(nosCaracteres, aParte, separador);
    }

    public static String[] pega(String dosCaracteres) {
        return pega(dosCaracteres, separador);
    }

    public static String[] pega(String dosCaracteres, String comSeparador) {
        if (dosCaracteres == null) {
            return null;
        } else {
            if (dosCaracteres.contains(comSeparador)) {
                String[] retorno = dosCaracteres.split("\\" + comSeparador);
                for (int ir = 0; ir < retorno.length; ir++) {
                    retorno[ir] = preparado(retorno[ir], comSeparador);
                }
                return retorno;
            } else {
                return new String[]{preparado(dosCaracteres, comSeparador)};
            }
        }
    }

    public static String pega(String dosCaracteres, Integer comIndice) {
        return pega(dosCaracteres, comIndice, (String) null);
    }

    public static String pega(String dosCaracteres, Integer comIndice, String ePadrao) {
        String retorno = ePadrao;
        String[] partes = pega(dosCaracteres);
        if (partes != null) {
            if (partes.length > comIndice) {
                retorno = partes[comIndice];
            }
        }
        return retorno;
    }

    public static List<String> pegaLis(String dosCaracteres) {
        List<String> retorno = null;
        String[] itns = pega(dosCaracteres);
        if (itns != null) {
            retorno = new ArrayList<>(Arrays.asList(itns));
        }
        return retorno;
    }

    public static String junta(List<String> aLista) {
        StringBuilder retorno = new StringBuilder();
        if (aLista != null) {
            boolean primeiro = true;
            for (String vlr : aLista) {
                if (!primeiro) {
                    retorno.append(separador);
                }
                retorno.append(prepara((vlr == null ? "" : vlr)));
                primeiro = false;
            }
        }
        return retorno.toString();
    }

    public static String junta(String... osCaracteres) {
        StringBuilder retorno = new StringBuilder();
        if (osCaracteres != null) {
            boolean primeiro = true;
            for (String vlr : osCaracteres) {
                if (!primeiro) {
                    retorno.append(separador);
                }
                retorno.append(prepara((vlr == null ? "" : vlr)));
                primeiro = false;
            }
        }
        return retorno.toString();
    }

    public static String juntaVals(Object... osValores) {
        StringBuilder retorno = new StringBuilder();
        if (osValores != null) {
            boolean primeiro = true;
            for (Object vlr : osValores) {
                if (!primeiro) {
                    retorno.append(separador);
                }
                retorno.append(prepara(UtlBin.descreve(vlr)));
                primeiro = false;
            }
        }
        return retorno.toString();
    }

    public static Object[] juntados(String nosCarecteres) throws Exception {
        String[] partes = pega(nosCarecteres);
        Object[] retorno = null;
        if (partes != null) {
            retorno = new Object[partes.length];
            for (int ip = 0; ip < partes.length; ip++) {
                retorno[ip] = UtlBin.descrito(partes[ip]);
            }
        }
        return retorno;
    }

    public static Boolean contem(String nosCaracteres, String aParte) {
        return contem(nosCaracteres, aParte, true);
    }

    public static Boolean contem(String nosCaracteres, String aParte, Boolean diferenciaMaiusculas) {
        Boolean retorno = false;
        if (nosCaracteres != null) {
            String[] partes = pega(nosCaracteres);
            if (partes != null) {
                for (String part : partes) {
                    if (diferenciaMaiusculas) {
                        if (part.equals(aParte)) {
                            retorno = true;
                            break;
                        }
                    } else {
                        if (part.toLowerCase().equals(aParte.toLowerCase())) {
                            retorno = true;
                            break;
                        }
                    }
                }
            }
        }
        return retorno;
    }
}
