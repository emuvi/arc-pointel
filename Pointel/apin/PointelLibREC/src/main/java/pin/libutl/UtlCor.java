package pin.libutl;

import pin.libout.HSLColor;
import pin.libvlr.Vlr;
import pin.libvlr.VCor;
import java.awt.Color;

public class UtlCor {

    public static Boolean eh(Object oValor) {
        Boolean retorno = false;
        if (oValor != null) {
            if (oValor instanceof Color) {
                retorno = true;
            } else if (oValor instanceof VCor) {
                retorno = true;
            }
        }
        return retorno;
    }

    public static Boolean eh(Class daClasse) {
        Boolean retorno = false;
        if (daClasse != null) {
            if (Color.class.isAssignableFrom(UtlBin.pegaDaPrimitiva(daClasse))) {
                retorno = true;
            } else if (VCor.class.isAssignableFrom(daClasse)) {
                retorno = true;
            }
        }
        return retorno;
    }

    public static Color pega(Object doValor) {
        return pega(doValor, null);
    }

    public static Color pega(Object doValor, Color comPadrao) {
        Color retorno = comPadrao;
        if (doValor instanceof Vlr) {
            doValor = ((Vlr) doValor).pg();
        }
        if (doValor instanceof Color) {
            retorno = (Color) doValor;
        } else if (doValor instanceof String) {
            retorno = pega((String) doValor);
        }
        return retorno;
    }

    public static Color pega(String dosCaracteres) {
        Color retorno = null;
        if (dosCaracteres != null) {
            if (dosCaracteres.length() >= 7) {
                Integer r = 0;
                Integer g = 0;
                Integer b = 0;
                Integer a = 255;
                if (dosCaracteres.length() >= 3) {
                    r = Integer.valueOf(dosCaracteres.substring(1, 3), 16);
                }
                if (dosCaracteres.length() >= 5) {
                    g = Integer.valueOf(dosCaracteres.substring(3, 5), 16);
                }
                if (dosCaracteres.length() >= 7) {
                    b = Integer.valueOf(dosCaracteres.substring(5, 7), 16);
                }
                if (dosCaracteres.length() >= 9) {
                    a = Integer.valueOf(dosCaracteres.substring(7, 9), 16);
                }
                retorno = new Color(r, g, b, a);
            }
        }
        return retorno;
    }

    public static final String formata(Color aCor) {
        if (aCor == null) {
            return null;
        }
        String r = (aCor.getRed() < 16) ? "0" + Integer.toHexString(aCor.getRed()) : Integer.toHexString(aCor.getRed());
        String g = (aCor.getGreen() < 16) ? "0" + Integer.toHexString(aCor.getGreen()) : Integer.toHexString(aCor.getGreen());
        String b = (aCor.getBlue() < 16) ? "0" + Integer.toHexString(aCor.getBlue()) : Integer.toHexString(aCor.getBlue());
        String a = (aCor.getAlpha() < 16) ? "0" + Integer.toHexString(aCor.getAlpha()) : Integer.toHexString(aCor.getAlpha());
        return "#" + r + g + b + a;
    }

    public static Integer diferenca(Color daCor, Color comCor) {
        Integer da = Math.max(daCor.getAlpha(), comCor.getAlpha()) - Math.min(daCor.getAlpha(), comCor.getAlpha());
        Integer dr = Math.max(daCor.getRed(), comCor.getRed()) - Math.min(daCor.getRed(), comCor.getRed());
        Integer dg = Math.max(daCor.getGreen(), comCor.getGreen()) - Math.min(daCor.getGreen(), comCor.getGreen());
        Integer db = Math.max(daCor.getBlue(), comCor.getBlue()) - Math.min(daCor.getBlue(), comCor.getBlue());
        return da + dr + dg + db;
    }

    public static Float diferencaPer(Color daCor, Color comCor) {
        return diferenca(daCor, comCor) / 1020.0f * 100.0f;
    }

    public static Boolean iguais(Color aCor, Color eCor) {
        return iguais(aCor, eCor, 0f);
    }

    public static Boolean iguais(Color aCor, Color eCor, Float toleranciaPer) {
        return diferencaPer(aCor, eCor) <= toleranciaPer;
    }

    public static Boolean ultrapassou(Integer corParte) {
        return ((corParte < 0) || (corParte > 255));
    }

    public static int[] desbasta(int parteVermelha, int parteVerde, int parteAzul) {
        int[] retorno = new int[3];
        retorno[0] = parteVermelha;
        retorno[1] = parteVerde;
        retorno[2] = parteAzul;
        while (ultrapassou(retorno[0]) || ultrapassou(retorno[1]) || ultrapassou(retorno[2])) {
            if (retorno[0] > 255) {
                int df = retorno[0] - 255;
                retorno[0] -= df;
                retorno[1] -= (df / 2);
                retorno[2] -= (df / 2);
            }
            if (retorno[1] > 255) {
                int df = retorno[1] - 255;
                retorno[0] -= (df / 2);
                retorno[1] -= df;
                retorno[2] -= (df / 2);
            }
            if (retorno[2] > 255) {
                int df = retorno[2] - 255;
                retorno[0] -= (df / 2);
                retorno[1] -= (df / 2);
                retorno[2] -= df;
            }
            if (retorno[0] < 0) {
                int df = retorno[0];
                retorno[0] -= df;
                retorno[1] -= (df / 2);
                retorno[2] -= (df / 2);
            }
            if (retorno[1] < 0) {
                int df = retorno[1];
                retorno[0] -= (df / 2);
                retorno[1] -= df;
                retorno[2] -= (df / 2);
            }
            if (retorno[2] < 0) {
                int df = retorno[2];
                retorno[0] -= (df / 2);
                retorno[1] -= (df / 2);
                retorno[2] -= df;
            }
        }
        return retorno;
    }

    public static Color tonDeCinza(Color daCor) {
        int cA = daCor.getAlpha();
        int cR = daCor.getRed();
        int cG = daCor.getGreen();
        int cB = daCor.getBlue();
        int med = (cR + cG + cB) / 3;
        return new Color(med, med, med, cA);
    }

    public static Color simplifica(Color aCor) {
        return centroDeFaixa(aCor, 5);
    }

    public static Color centroDeFaixa(Color daCor, Integer tamanhoDaFaixa) {
        if (daCor != null) {
            int r = Math.min(((daCor.getRed() / tamanhoDaFaixa) * tamanhoDaFaixa) + (tamanhoDaFaixa / 2), 254);
            int g = Math.min(((daCor.getGreen() / tamanhoDaFaixa) * tamanhoDaFaixa) + (tamanhoDaFaixa / 2), 254);
            int b = Math.min(((daCor.getBlue() / tamanhoDaFaixa) * tamanhoDaFaixa) + (tamanhoDaFaixa / 2), 254);
            int a = Math.min(((daCor.getAlpha() / tamanhoDaFaixa) * tamanhoDaFaixa) + (tamanhoDaFaixa / 2), 254);
            return new Color(r, g, b, a);
        }
        return null;
    }

    public static Color escurece(Color aCor, Integer naIntensidade) {
        return clareia(aCor, -1 * naIntensidade);
    }

    public static Color clareia(Color aCor, Integer naIntensidade) {
        int cA = aCor.getAlpha();
        int cR = aCor.getRed() + naIntensidade;
        int cG = aCor.getGreen() + naIntensidade;
        int cB = aCor.getBlue() + naIntensidade;
        int[] desb = desbasta(cR, cG, cB);
        return new Color(desb[0], desb[1], desb[2], cA);
    }

    public static Boolean ehClara(Color aCor) {
        return (tonDeCinza(aCor).getRed() > 128);
    }

    public static Color diferencia(Color aCor, Integer naQuantidade) {
        if (naQuantidade < 0) {
            naQuantidade = naQuantidade * -1;
        }
        if (ehClara(aCor)) {
            return clareia(aCor, naQuantidade * -1);
        } else {
            return clareia(aCor, naQuantidade);
        }
    }

    public static int[] pegaDiferencas(Color daCor, Color comCor) {
        if (daCor != null && comCor != null) {
            int[] retorno = new int[4];
            retorno[0] = comCor.getRed() - daCor.getRed();
            retorno[1] = comCor.getGreen() - daCor.getGreen();
            retorno[2] = comCor.getBlue() - daCor.getBlue();
            retorno[3] = comCor.getAlpha() - daCor.getAlpha();
            return retorno;
        }
        return null;
    }

    public static Color media(Color aCor, Color comCor) {
        return mistura(aCor, 50.0f, comCor);
    }

    public static Color mistura(Color aCor, float naPorcentagem, Color comCor) {
        HSLColor c1 = new HSLColor(aCor);
        HSLColor c2 = new HSLColor(comCor);
        float[] ac1 = c1.getHSL();
        float[] ac2 = c2.getHSL();
        float[] acm = new float[3];
        acm[0] = (ac1[0] * naPorcentagem / 100.0f) + (ac2[0] * (100.0f - naPorcentagem) / 100.0f);
        acm[1] = (ac1[1] * naPorcentagem / 100.0f) + (ac2[1] * (100.0f - naPorcentagem) / 100.0f);
        acm[2] = (ac1[2] * naPorcentagem / 100.0f) + (ac2[2] * (100.0f - naPorcentagem) / 100.0f);
        float alpha = ((aCor.getAlpha() + comCor.getAlpha()) / 2.0f) / 255.0f;
        return HSLColor.toRGB(acm[0], acm[1], acm[2], alpha);
    }

    public static Color gravita(Color aCor, int[] comDiferencas) {
        return gravita(aCor, comDiferencas, 50);
    }

    public static Color gravita(Color aCor, int[] comDiferencas, float ePercentual) {
        int cR = aCor.getRed() + Math.round(comDiferencas[0] * ePercentual / 100.0f);
        int cG = aCor.getGreen() + Math.round(comDiferencas[1] * ePercentual / 100.0f);
        int cB = aCor.getBlue() + Math.round(comDiferencas[2] * ePercentual / 100.0f);
        int cA = aCor.getAlpha() + Math.round(comDiferencas[3] * ePercentual / 100.0f);
        int[] desb = desbasta(cR, cG, cB);
        cR = desb[0];
        cG = desb[1];
        cB = desb[2];
        if (cA < 0) {
            cA = 0;
        }
        if (cA > 254) {
            cA = 254;
        }
        return new Color(cR, cG, cB, cA);
    }

}
