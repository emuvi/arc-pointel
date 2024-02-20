package pin.libutl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Objects;
import pin.libvlr.VNum;
import pin.libvlr.Vlr;

public class UtlNum {

    public static String formatoPadrao = "#,##0.000;-#,##0.000";
    public static String formatoSQL = "0.000;-0.000";

    public static Boolean eh(Object oValor) {
        Boolean retorno = false;
        if (oValor != null) {
            if (oValor instanceof Float) {
                retorno = true;
            } else if (oValor instanceof VNum) {
                retorno = true;
            }
        }
        return retorno;
    }

    public static Boolean eh(Class daClasse) {
        Boolean retorno = false;
        if (daClasse != null) {
            if (Float.class.isAssignableFrom(UtlBin.pegaDaPrimitiva(daClasse))) {
                retorno = true;
            } else if (VNum.class.isAssignableFrom(daClasse)) {
                retorno = true;
            }
        }
        return retorno;
    }

    public static Float pega(Object doValor) {
        return pega(doValor, null);
    }

    public static Float pega(Object doValor, Float comPadrao) {
        return pega(doValor, comPadrao, null);
    }

    public static Float pega(Object doValor, Float comPadrao, String eFormato) {
        if (doValor instanceof Vlr) {
            doValor = ((Vlr) doValor).pg();
        }

        Float retorno = comPadrao;
        if (doValor != null) {
            if (doValor instanceof BigDecimal) {
                retorno = ((BigDecimal) doValor).floatValue();
            } else if (doValor instanceof Double) {
                retorno = ((Double) doValor).floatValue();
            } else if (doValor instanceof Float) {
                retorno = ((Float) doValor);
            } else if (doValor instanceof Long) {
                retorno = ((Long) doValor).floatValue();
            } else if (doValor instanceof Integer) {
                retorno = ((Integer) doValor).floatValue();
            } else if (doValor instanceof String) {
                retorno = pega((String) doValor, comPadrao, eFormato);
            }
        }
        return retorno;
    }

    public static Float pega(String dosCaracteres) {
        return pega(dosCaracteres, null, null);
    }

    public static Float pega(String dosCaracteres, Float comPadrao) {
        return pega(dosCaracteres, comPadrao, null);
    }

    public static Float pega(String dosCaracteres, String comFormato) {
        return pega(dosCaracteres, null, comFormato);
    }

    public static Float pega(String dosCaracteres, Float comPadrao, String eFormato) {
        if (dosCaracteres == null) {
            return null;
        }
        if (dosCaracteres.isEmpty()) {
            return null;
        }
        if (dosCaracteres.equals("null")) {
            return null;
        }
        try {
            if (eFormato == null) {
                eFormato = formatoPadrao;
            }
            return new DecimalFormat(eFormato).parse(dosCaracteres).floatValue();
        } catch (Exception ex) {
            return comPadrao;
        }

    }

    public static Float pegaSQL(Object doValor) {
        return pegaSQL(doValor, null);
    }

    public static Float pegaSQL(Object doValor, Float comPadrao) {
        return pega(doValor, comPadrao);
    }

    public static String formata(Float oNumero) {
        return formata(oNumero, formatoPadrao, null);
    }

    public static String formata(Float oNumero, String comPadrao) {
        return formata(oNumero, formatoPadrao, comPadrao);
    }

    public static String formata(Float oNumero, String noFormato, String comPadrao) {
        String retorno = comPadrao;
        try {
            DecimalFormat formatter = new DecimalFormat(noFormato);
            retorno = formatter.format(oNumero);
        } catch (Exception ex) {
        }
        return retorno;
    }

    public static String formataSQL(Float oNumero) {
        return UtlCrs.troca(formata(oNumero, formatoSQL, null), ",", ".");
    }

    public static String formataSQL(Float oNumero, String comPadrao) {
        return UtlCrs.troca(formata(oNumero, formatoSQL, comPadrao), ",", ".");
    }

    public static String pegaParaSQL(Float oNumero) {
        return formata(oNumero, formatoSQL);
    }

    public static Float pegaMaior(Float numero, Float eNumero) {
        Float retorno = null;
        if ((numero != null) && (eNumero == null)) {
            retorno = numero;
        } else if ((numero == null) && (eNumero != null)) {
            retorno = eNumero;
        } else if ((numero != null) && (eNumero != null)) {
            if (numero > eNumero) {
                retorno = numero;
            } else {
                retorno = eNumero;
            }
        }
        return retorno;
    }

    public static Float pegaMenor(Float numero, Float eNumero) {
        Float retorno = null;
        if ((numero != null) && (eNumero == null)) {
            retorno = numero;
        } else if ((numero == null) && (eNumero != null)) {
            retorno = eNumero;
        } else if ((numero != null) && (eNumero != null)) {
            if (numero < eNumero) {
                retorno = numero;
            } else {
                retorno = eNumero;
            }
        }
        return retorno;
    }

    public static Float distancia(Float numero, Float eNumero) {
        return pegaMaior(numero, eNumero) - pegaMenor(numero, eNumero);
    }

    public static Integer casas(Float doValor) {
        Integer casas = 1;
        Float total = doValor;
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

    public static String porExtenso(Float aValor) {
        if (aValor == 0) {
            return "zero";
        }
        long inteiro = (long) Math.abs(aValor); // parte inteira do valor
        double resto = aValor - inteiro; // parte fracionária do valor
        String vlrS = String.valueOf(inteiro);
        if (vlrS.length() > 15) {
            return "Erro: valor superior a 999 trilh\u00f5es.";
        }
        String s = "";
        String saux;
        String vlrP;
        String centavos = String.valueOf((int) Math.round(resto * 100));
        String[] unidade = {"", "um", "dois", "tr\u00eas", "quatro", "cinco", "seis", "sete", "oito", "nove", "dez", "onze", "doze", "treze", "quatorze", "quinze", "dezesseis", "dezessete", "dezoito", "dezenove"};
        String[] centena = {"", "cento", "duzentos", "trezentos", "quatrocentos", "quinhentos", "seiscentos", "setecentos", "oitocentos", "novecentos"};
        String[] dezena = {"", "", "vinte", "trinta", "quarenta", "cinquenta", "sessenta", "setenta", "oitenta", "noventa"};
        String[] qualificaS = {"", "mil", "milh\u00e3o", "bilh\u00e3o", "trilh\u00e3o"};
        String[] qualificaP = {"", "mil", "milh\u00f5es", "bilh\u00f5es", "trilh\u00f5es"};
        // definindo o extenso da parte inteira do valor
        int n;
        int unid;
        int dez;
        int cent;
        int tam;
        int i = 0;
        boolean umReal = false;
        boolean tem = false;
        while (!vlrS.equals("0")) {
            tam = vlrS.length();
            // retira do valor a 1a. parte, 2a. parte, por exemplo, para 123456789:
            // 1a. parte = 789 (centena)
            // 2a. parte = 456 (mil)
            // 3a. parte = 123 (milhões)
            if (tam > 3) {
                vlrP = vlrS.substring(tam - 3, tam);
                vlrS = vlrS.substring(0, tam - 3);
            } else {
                // última parte do valor
                vlrP = vlrS;
                vlrS = "0";
            }
            if (!vlrP.equals("000")) {
                saux = "";
                if (vlrP.equals("100")) {
                    saux = "cem";
                } else {
                    n = Integer.parseInt(vlrP, 10); // para n = 371, tem-se:
                    cent = n / 100; // cent = 3 (centena trezentos)
                    dez = (n % 100) / 10; // dez  = 7 (dezena setenta)
                    unid = (n % 100) % 10; // unid = 1 (unidade um)
                    if (cent != 0) {
                        saux = centena[cent];
                    }
                    if ((n % 100) <= 19) {
                        if (saux.length() != 0) {
                            saux = saux + " e " + unidade[n % 100];
                        } else {
                            saux = unidade[n % 100];
                        }
                    } else {
                        if (saux.length() != 0) {
                            saux = saux + " e " + dezena[dez];
                        } else {
                            saux = dezena[dez];
                        }
                        if (unid != 0) {
                            if (saux.length() != 0) {
                                saux = saux + " e " + unidade[unid];
                            } else {
                                saux = unidade[unid];
                            }
                        }
                    }
                }
                if (vlrP.equals("1") || vlrP.equals("001")) {
                    if (i == 0) {
                        umReal = true;
                    } else {
                        saux = saux + " " + qualificaS[i];
                    }
                } else if (i != 0) {
                    saux = saux + " " + qualificaP[i];
                }
                if (s.length() != 0) {
                    s = saux + ", " + s;
                } else {
                    s = saux;
                }
            }
            if (((i == 0) || (i == 1)) && s.length() != 0) {
                tem = true; // tem centena ou mil no valor
            }
            i = i + 1; // próximo qualificador: 1- mil, 2- milhão, 3- bilhão, ...
        }
        if (s.length() != 0) {
            if (umReal) {
                s = s + " real";
            } else if (tem) {
                s = s + " reais";
            } else {
                s = s + " de reais";
            }
        }
        // definindo o extenso dos centavos do valor
        if (!centavos.equals("0")) {
            // valor com centavos
            if (s.length() != 0) {
                s = s + " e ";
            }
            if (centavos.equals("1")) {
                s = s + "um centavo";
            } else {
                n = Integer.parseInt(centavos, 10);
                if (n <= 19) {
                    s = s + unidade[n];
                } else {
                    // para n = 37, tem-se:
                    unid = n % 10; // unid = 37 % 10 = 7 (unidade sete)
                    dez = n / 10; // dez  = 37 / 10 = 3 (dezena trinta)
                    s = s + dezena[dez];
                    if (unid != 0) {
                        s = s + " e " + unidade[unid];
                    }
                }
                s = s + " centavos";
            }
        }
        return s;
    }

    public static String porExtensoMatricial(Object oValor) {
        return porExtensoMatricial(pega(oValor));
    }

    public static String porExtensoMatricial(Float aValor) {
        String retorno = porExtenso(aValor);
        retorno = retorno.toUpperCase();
        while (retorno.length() < 400) {
            retorno = retorno + "**********";
        }
        return retorno;
    }

    public static Float pergunta() {
        return pergunta(null, null);
    }

    public static Float pergunta(String comMensagem) {
        return pergunta(comMensagem, null);
    }

    public static Float pergunta(String comMensagem, Float ePadrao) {
        return pega(UtlCrs.pergunta(comMensagem, formata(ePadrao)));
    }

    public static Float pegaFracao(Float doNumero) {
        if (doNumero == null) {
            return null;
        } else {
            return doNumero % 1;
        }
    }

    public static Float racionaliza(Float oNumero) {
        return racionaliza(oNumero, 0.003f);
    }

    public static Float racionaliza(Float oNumero, Float racionalizador) {
        if (oNumero == null) {
            return null;
        } else {
            Float fracao = pegaFracao(oNumero);
            if (fracao < racionalizador) {
                return oNumero - fracao;
            } else if (fracao > 1 - racionalizador) {
                return oNumero - fracao + 1;
            } else {
                return oNumero;
            }

        }
    }

    public static Float mantem(Float doNumero, Integer casas) throws Exception {
        DecimalFormat df = new DecimalFormat("#,##0." + UtlCrs.repete("0", casas));
        return df.parse(df.format(doNumero)).floatValue();
    }

}
