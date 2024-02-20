package pin.libutl;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.security.Key;
import java.security.MessageDigest;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Formatter;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import pin.libbas.Partes;
import pin.libitr.Pergunta;
import pin.libitr.PerguntaSenha;
import pin.libvlr.VCrs;
import pin.libvlr.VTex;
import pin.libvlr.Vlr;

public class UtlCrs {

    public static Boolean eh(Object oValor) {
        Boolean retorno = false;
        if (oValor != null) {
            if (oValor instanceof String) {
                retorno = true;
            } else if (oValor instanceof VCrs) {
                retorno = true;
            } else if (oValor instanceof VTex) {
                retorno = true;
            }
        }
        return retorno;
    }

    public static Boolean eh(Class daClasse) {
        Boolean retorno = false;
        if (daClasse != null) {
            if (String.class.isAssignableFrom(UtlBin.pegaDaPrimitiva(daClasse))) {
                retorno = true;
            } else if (VCrs.class.isAssignableFrom(daClasse)) {
                retorno = true;
            }
        }
        return retorno;
    }

    public static String pega(Object doValor) {
        return pega(doValor, null);
    }

    public static String pega(Object doValor, String comPadrao) {
        String retorno = comPadrao;
        if (doValor != null) {
            if (doValor instanceof Vlr) {
                retorno = ((Vlr) doValor).toString();
            } else if (doValor instanceof String) {
                retorno = (String) doValor;
            } else if (UtlCr.eh(doValor)) {
                retorno = UtlCr.formata(UtlCr.pega(doValor), comPadrao);
            } else if (UtlLog.eh(doValor)) {
                retorno = UtlLog.formata(UtlLog.pega(doValor), comPadrao);
            } else if (UtlInt.eh(doValor)) {
                retorno = UtlInt.formata(UtlInt.pega(doValor), comPadrao);
            } else if (UtlIntLon.eh(doValor)) {
                retorno = UtlIntLon.formata(UtlIntLon.pega(doValor), comPadrao);
            } else if (UtlNum.eh(doValor)) {
                retorno = UtlNum.formata(UtlNum.pega(doValor), comPadrao);
            } else if (UtlNumLon.eh(doValor)) {
                retorno = UtlNumLon.formata(UtlNumLon.pega(doValor), comPadrao);
            } else if (UtlMom.eh(doValor)) {
                retorno = UtlMom.formata(UtlDatHor.pega(doValor), comPadrao);
            } else if (UtlDatHor.eh(doValor)) {
                retorno = UtlDatHor.formata(UtlDatHor.pega(doValor), comPadrao);
            } else if (UtlHor.eh(doValor)) {
                retorno = UtlHor.formata(UtlHor.pega(doValor), comPadrao);
            } else if (UtlDat.eh(doValor)) {
                retorno = UtlDat.formata(UtlDat.pega(doValor), comPadrao);
            } else if (UtlLis.eh(doValor)) {
                retorno = UtlLis.formataLinha(UtlLis.pega(doValor));
            } else if (UtlArq.eh(doValor)) {
                if (doValor instanceof File) {
                    if (((File) doValor).isDirectory()) {
                        retorno = UtlArq.formata((File) doValor, comPadrao);
                    } else {
                        retorno = UtlArq.formata(UtlArq.pega(doValor), comPadrao);
                    }
                } else {
                    retorno = UtlArq.formata(UtlArq.pega(doValor), comPadrao);
                }
            } else {
                retorno = doValor.toString();
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

    public static String pegaSQL(Object doValor, String comPadrao) {
        return pega(doValor, comPadrao);
    }

    public static String pegaB64(String daBase64) {
        return new String(Base64.getDecoder().decode(daBase64));
    }

    public static String botaB64(String osCaracteres) {
        return Base64.getEncoder().encodeToString(osCaracteres.getBytes());
    }

    public static Boolean ehIgual(Object oValor, Object eValor) {
        return Objects.equals(pega(oValor, ""), pega(eValor, ""));
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

    public static Boolean vazio(String osCaracteres) {
        Boolean retorno = true;
        if (osCaracteres != null) {
            if (!osCaracteres.isEmpty()) {
                retorno = false;
            }
        }
        return retorno;
    }

    public static Boolean ehSomenteNormais(String osCaracteres) {
        Boolean retorno = true;
        for (int i1 = 0; i1 < osCaracteres.length(); i1++) {
            if (!UtlLis.tem(osCaracteres.charAt(i1), UtlCr.crsNormais)) {
                retorno = false;
                break;
            }
        }
        return retorno;
    }

    public static String pegaSomenteNormais(String dosCaracteres) {
        String retorno = "";
        if (dosCaracteres != null) {
            for (char ch : dosCaracteres.toCharArray()) {
                if (UtlLis.tem(ch, UtlCr.crsNormais)) {
                    retorno = retorno + ch;
                }
            }
        }
        return retorno;
    }

    public static Boolean temInteiros(String nosCaracteres) {
        for (int i1 = 0; i1 < nosCaracteres.length(); i1++) {
            if (UtlLis.tem(nosCaracteres.charAt(i1), UtlCr.crsInteiros)) {
                return true;
            }
        }
        return false;
    }

    public static Boolean ehSomenteInteiros(String osCaracteres) {
        Boolean retorno = true;
        if (osCaracteres != null) {
            for (int i1 = 0; i1 < osCaracteres.length(); i1++) {
                if (!UtlLis.tem(osCaracteres.charAt(i1), UtlCr.crsInteiros)) {
                    retorno = false;
                    break;
                }
            }
        }
        return retorno;
    }

    public static String pegaSomenteInteiros(String dosCaracteres) {
        String retorno = "";
        if (dosCaracteres != null) {
            for (char ch : dosCaracteres.toCharArray()) {
                if (UtlLis.tem(ch, UtlCr.crsInteiros)) {
                    retorno = retorno + ch;
                }
            }
        }
        return retorno;
    }

    public static Boolean temNumericos(String nosCaracteres) {
        if (nosCaracteres != null) {
            for (int i1 = 0; i1 < nosCaracteres.length(); i1++) {
                if (UtlLis.tem(nosCaracteres.charAt(i1), UtlCr.crsNumericos)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Boolean ehSomenteNumericos(String osCaracteres) {
        Boolean retorno = true;
        if (osCaracteres != null) {
            for (int i1 = 0; i1 < osCaracteres.length(); i1++) {
                if (!UtlLis.tem(osCaracteres.charAt(i1), UtlCr.crsNumericos)) {
                    retorno = false;
                    break;
                }
            }
        }
        return retorno;
    }

    public static String pegaSomenteNumericos(String dosCaracteres) {
        String retorno = "";
        if (dosCaracteres != null) {
            for (char ch : dosCaracteres.toCharArray()) {
                if (UtlLis.tem(ch, UtlCr.crsNumericos)) {
                    retorno = retorno + ch;
                }
            }
        }
        return retorno;
    }

    public static Boolean ehSomenteInteirosComNegativos(String osCaracteres) {
        Boolean retorno = true;
        if (osCaracteres != null) {
            for (int i1 = 0; i1 < osCaracteres.length(); i1++) {
                if (!UtlLis.tem(osCaracteres.charAt(i1), UtlCr.crsInteirosComNegativos)) {
                    retorno = false;
                    break;
                }
            }
        }
        return retorno;
    }

    public static String pegaSomenteInteirosComNegativos(String dosCaracteres) {
        String retorno = "";
        if (dosCaracteres != null) {
            for (char ch : dosCaracteres.toCharArray()) {
                if (UtlLis.tem(ch, UtlCr.crsInteirosComNegativos)) {
                    retorno = retorno + ch;
                }
            }
        }
        return retorno;
    }

    public static Boolean ehSomenteNumericosComNegativos(String osCaracteres) {
        Boolean retorno = true;
        if (osCaracteres != null) {
            for (int i1 = 0; i1 < osCaracteres.length(); i1++) {
                if (!UtlLis.tem(osCaracteres.charAt(i1), UtlCr.crsNumericosComNegativos)) {
                    retorno = false;
                    break;
                }
            }
        }
        return retorno;
    }

    public static String pegaSomenteNumericosComNegativos(String dosCaracteres) {
        String retorno = "";
        if (dosCaracteres != null) {
            for (char ch : dosCaracteres.toCharArray()) {
                if (UtlLis.tem(ch, UtlCr.crsNumericosComNegativos)) {
                    retorno = retorno + ch;
                }
            }
        }
        return retorno;
    }

    public static Boolean ehSomenteSimples(String osCaracteres) {
        Boolean retorno = true;
        if (osCaracteres != null) {
            for (int i1 = 0; i1 < osCaracteres.length(); i1++) {
                if (!UtlLis.tem(osCaracteres.charAt(i1), UtlCr.crsSimples)) {
                    retorno = false;
                    break;
                }
            }
        }
        return retorno;
    }

    public static String pegaSomenteSimples(String dosCaracteres) {
        String retorno = "";
        if (dosCaracteres != null) {
            for (char ch : dosCaracteres.toCharArray()) {
                if (UtlLis.tem(ch, UtlCr.crsSimples)) {
                    retorno = retorno + ch;
                }
            }
        }
        return retorno;
    }

    public static String simplifica(String osCaracteres) {
        String retorno = "";
        for (char ch : osCaracteres.toLowerCase().toCharArray()) {
            if (UtlLis.tem(ch, UtlCr.crsSimples)) {
                retorno += ch;
            } else {
                switch (ch) {
                    case 'á':
                    case 'à':
                    case 'â':
                    case 'ã':
                        retorno += 'a';
                        break;
                    case 'é':
                    case 'è':
                    case 'ê':
                        retorno += 'e';
                        break;
                    case 'í':
                    case 'ì':
                    case 'î':
                        retorno += 'i';
                        break;
                    case 'ó':
                    case 'ò':
                    case 'ô':
                    case 'õ':
                        retorno += 'o';
                        break;
                    case 'ú':
                    case 'ù':
                    case 'û':
                        retorno += 'u';
                        break;
                    case 'ç':
                        retorno += 'c';
                        break;

                }
            }
        }
        return retorno;
    }

    public static Boolean ehSomenteMaiusculos(String osCaracteres) {
        Boolean retorno = true;
        for (int i1 = 0; i1 < osCaracteres.length(); i1++) {
            if (!UtlLis.tem(osCaracteres.charAt(i1), UtlCr.crsMaiusculos)) {
                retorno = false;
                break;
            }
        }
        return retorno;
    }

    public static String pegaSomenteMaiusculos(String dosCaracteres) {
        String retorno = "";
        for (char ch : dosCaracteres.toCharArray()) {
            if (UtlLis.tem(ch, UtlCr.crsMaiusculos)) {
                retorno = retorno + ch;
            }
        }
        return retorno;
    }

    public static String pEspacadosMaiusculos(String nosCaracteres) {
        return UtlCrs.pEspacadosMaiusculos(nosCaracteres, 2);
    }

    public static String pEspacadosMaiusculos(String nosCaracteres, Integer maioresQue) {
        if (nosCaracteres == null) {
            return null;
        }
        String retorno = nosCaracteres;
        String[] partes = retorno.split("\\s");
        retorno = "";
        for (String prt : partes) {
            if (!prt.isEmpty()) {
                if (prt.length() > maioresQue) {
                    retorno = UtlCrs.soma(retorno, " ", pSoPrimeiroMaiusculo(prt));
                } else {
                    retorno = UtlCrs.soma(retorno, " ", prt.toLowerCase());
                }
            }
        }
        return retorno;
    }

    public static String mSoPrimeirosMaiusculos(String nosCaracteres) {
        if (nosCaracteres == null) {
            return null;
        }
        String retorno = pSoPrimeiroMaiusculo(nosCaracteres);
        if (retorno.contains(".")) {
            String[] prts1 = Partes.pega(retorno, ".");
            retorno = "";
            for (String prt1 : prts1) {
                retorno = UtlCrs.soma(retorno, ".", pSoPrimeiroMaiusculo(prt1));
            }
        }
        if (retorno.contains(":")) {
            String[] prts2 = Partes.pega(retorno, ":");
            retorno = "";
            for (String prt2 : prts2) {
                retorno = UtlCrs.soma(retorno, ":", pSoPrimeiroMaiusculo(prt2));
            }
        }
        if (retorno.contains(";")) {
            String[] prts2 = Partes.pega(retorno, ";");
            retorno = "";
            for (String prt2 : prts2) {
                retorno = UtlCrs.soma(retorno, ";", pSoPrimeiroMaiusculo(prt2));
            }
        }
        if (retorno.contains("?")) {
            String[] prts3 = Partes.pega(nosCaracteres, "?");
            retorno = "";
            for (String prt3 : prts3) {
                retorno = UtlCrs.soma(retorno, "?", pSoPrimeiroMaiusculo(prt3));
            }
        }
        if (retorno.contains("!")) {
            String[] prts4 = Partes.pega(nosCaracteres, "!");
            retorno = "";
            for (String prt4 : prts4) {
                retorno = UtlCrs.soma(retorno, "!", pSoPrimeiroMaiusculo(prt4));
            }
        }
        if (retorno.contains("§")) {
            String[] prts5 = Partes.pega(nosCaracteres, "§");
            retorno = "";
            for (String prt5 : prts5) {
                retorno = UtlCrs.soma(retorno, "§", pSoPrimeiroMaiusculo(prt5));
            }
        }
        return retorno;
    }

    public static String pSoPrimeiroMaiusculo(String nosCaracteres) {
        if (nosCaracteres == null) {
            return null;
        }
        nosCaracteres = nosCaracteres.trim();
        String retorno = "";
        if (nosCaracteres.length() > 0) {
            retorno = nosCaracteres.substring(0, 1).toUpperCase();
        }
        if (nosCaracteres.length() > 1) {
            retorno += nosCaracteres.substring(1).toLowerCase();
        }
        return retorno;
    }

    public static String junta(Object... osValores) {
        return UtlCrs.junta(osValores, " , ");
    }

    public static String junta(Object[] osValores, String comJuntor) {
        String retorno = "";
        if (osValores != null) {
            boolean primeiro = true;
            for (Object vlr : osValores) {
                retorno = retorno + (primeiro ? "" : comJuntor) + pega(vlr, "");
                primeiro = false;
            }
        }
        return retorno;
    }

    public static <T> String junta(List<T> osValores) {
        return UtlCrs.junta(osValores, " , ");
    }

    public static <T> String junta(List<T> osValores, String comJuntor) {
        String retorno = "";
        if (osValores != null) {
            boolean primeiro = true;
            for (Object vlr : osValores) {
                retorno = retorno + (primeiro ? "" : comJuntor) + pega(vlr, "");
                primeiro = false;
            }
        }
        return retorno;
    }

    public static String lista(Object... objetos) {
        String retorno = null;
        if (objetos != null) {
            for (int io = 0; io < objetos.length; io++) {
                retorno += (retorno == null ? "" : UtlTex.quebra()) + pega(objetos[io]);
            }
        }
        return retorno;
    }

    public static String pEntreAspas(String dosCaracteres) {
        return pEntre(dosCaracteres, '"', '"', '\\');
    }

    public static String pEntreAspasSimples(String dosCaracteres) {
        return pEntre(dosCaracteres, '\'', '\'', '\\');
    }

    public static String botaEntreAspas(String osCaracteres) {
        return "\"" + troca(osCaracteres, "\"", "\\\"") + "\"";
    }

    public static String botaEntreAspasSimples(String osCaracteres) {
        return "'" + troca(osCaracteres, "'", "\\'") + "'";
    }

    public static Boolean compara(String osCaracteres, String comCaracteres) {
        Boolean retorno = false;
        String cmp1 = osCaracteres;
        String cmp2 = comCaracteres;
        if (cmp1 == null) {
            cmp1 = "";
        }
        if (cmp2 == null) {
            cmp2 = "";
        }
        if (cmp1.equals(cmp2)) {
            retorno = true;
        }
        return retorno;
    }

    public static Object[] pegaMaisParecida(String palavra, List<String> naLista) {
        Object[] retorno = null;
        Integer diferenca = null;
        if (naLista != null) {
            for (int il = 0; il < naLista.size(); il++) {
                int dif = diferenciaPalavras(palavra, naLista.get(il));
                if (diferenca == null) {
                    retorno = new Object[3];
                    retorno[0] = il;
                    retorno[1] = dif;
                    diferenca = dif;
                } else if (dif < diferenca) {
                    retorno[0] = il;
                    retorno[1] = dif;
                    diferenca = dif;
                }
            }
        }
        return retorno;
    }

    public static Integer diferenciaPalavras(String de, String com) {
        return diferenciaPalavras(de, com, false);
    }

    public static Integer diferenciaPalavras(String de, String com, Boolean maiusculas) {
        Integer retorno = 0;
        String cmp1 = de;
        String cmp2 = com;
        if (cmp1 == null) {
            cmp1 = "";
        }
        if (cmp2 == null) {
            cmp2 = "";
        }
        if (!maiusculas) {
            cmp1 = cmp1.toLowerCase();
            cmp2 = cmp2.toLowerCase();
        }
        int dm1 = cmp1.length();
        int dm2 = cmp2.length();
        int dmd = UtlInt.distancia(dm1, dm2);
        for (int id = 0; id < dm1; id++) {
            if (id < dm2) {
                if (cmp1.charAt(id) != cmp2.charAt(id)) {
                    int da = dm2;
                    for (int ia = id - 1; ia >= 0; ia--) {
                        if (cmp1.charAt(id) == cmp2.charAt(ia)) {
                            da = id - ia;
                            break;
                        }
                    }
                    int dr = dm2;
                    for (int ir = id + 1; ir < dm2; ir++) {
                        if (cmp1.charAt(id) == cmp2.charAt(ir)) {
                            dr = ir - id;
                            break;
                        }
                    }
                    retorno += UtlInt.pegaMenor(da, dr);
                }
            } else {
                retorno += dmd;
            }
        }
        for (int id = 0; id < dm2; id++) {
            if (id < dm1) {
                if (cmp2.charAt(id) != cmp1.charAt(id)) {
                    int da = dm1;
                    for (int ia = id - 1; ia >= 0; ia--) {
                        if (cmp2.charAt(id) == cmp1.charAt(ia)) {
                            da = id - ia;
                            break;
                        }
                    }
                    int dr = dm1;
                    for (int ir = id + 1; ir < dm1; ir++) {
                        if (cmp2.charAt(id) == cmp1.charAt(ir)) {
                            dr = ir - id;
                            break;
                        }
                    }
                    retorno += UtlInt.pegaMenor(da, dr);
                }
            } else {
                retorno += dmd;
            }
        }
        return retorno;
    }

    public static Integer diferenciaFrases(String de, String com) {
        return diferenciaFrases(de, com, false);
    }

    public static Integer diferenciaFrases(String de, String com, Boolean maiusculas) {
        Integer retorno = 0;
        String cmp1 = de;
        String cmp2 = com;
        if (cmp1 == null) {
            cmp1 = "";
        }
        if (cmp2 == null) {
            cmp2 = "";
        }
        if (!maiusculas) {
            cmp1 = cmp1.toLowerCase();
            cmp2 = cmp2.toLowerCase();
        }
        List<String> pals1 = new ArrayList<>(Arrays.asList(cmp1.split("\\s")));
        List<String> pals2 = new ArrayList<>(Arrays.asList(cmp2.split("\\s")));
        while (pals1.size() < pals2.size()) {
            pals1.add("");
        }
        while (pals2.size() < pals1.size()) {
            pals2.add("");
        }
        for (int ip = 0; ip < pals1.size(); ip++) {
            Object[] msp = pegaMaisParecida(pals1.get(ip), pals2);
            if (msp != null) {
                int ix = (int) msp[0];
                int id = (UtlInt.pegaMaior(ix, ip) - UtlInt.pegaMenor(ix, ip)) + 1;
                int dif = (int) msp[1] + 1;
                retorno = retorno + (id * dif);
            }
        }
        for (int ip = 0; ip < pals2.size(); ip++) {
            Object[] msp = pegaMaisParecida(pals2.get(ip), pals1);
            if (msp != null) {
                int ix = (int) msp[0];
                int id = (UtlInt.pegaMaior(ix, ip) - UtlInt.pegaMenor(ix, ip)) + 1;
                int dif = (int) msp[1] + 1;
                retorno = retorno + (id * dif);
            }
        }
        return retorno - 4;
    }

    public static void copiaParaTransferencia(String osCaracteres) {
        String myString = osCaracteres;
        StringSelection stringSelection = new StringSelection(myString);
        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
        clpbrd.setContents(stringSelection, null);
    }

    public static String colaDaTransferencia() {
        String retorno = "";
        try {
            Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
            retorno = (String) clpbrd.getContents(retorno).getTransferData(DataFlavor.stringFlavor);
        } catch (Exception ex) {
            Utl.registra(ex);
        }
        return retorno;
    }

    public static boolean ehUltimoDaOrdem(char oCaracter, boolean somenteNumeros) {
        boolean retorno = false;
        if ((oCaracter == '9') && (somenteNumeros)) {
            retorno = true;
        }
        if ((oCaracter == 'Z') && (!somenteNumeros)) {
            retorno = true;
        }
        return retorno;
    }

    public static char pegaProximo(char ultimo, boolean somenteNumeros) {
        char retorno = ' ';
        if (ultimo == ' ') {
            retorno = '0';
        } else if (ultimo == '0') {
            retorno = '1';
        } else if (ultimo == '1') {
            retorno = '2';
        } else if (ultimo == '2') {
            retorno = '3';
        } else if (ultimo == '3') {
            retorno = '4';
        } else if (ultimo == '4') {
            retorno = '5';
        } else if (ultimo == '5') {
            retorno = '6';
        } else if (ultimo == '6') {
            retorno = '7';
        } else if (ultimo == '7') {
            retorno = '8';
        } else if (ultimo == '8') {
            retorno = '9';
        } else if (ultimo == '9') {
            if (somenteNumeros) {
                retorno = '0';
            } else {
                retorno = 'A';
            }
        } else if (!somenteNumeros) {
            if (ultimo == 'A') {
                retorno = 'B';
            } else if (ultimo == 'B') {
                retorno = 'C';
            } else if (ultimo == 'C') {
                retorno = 'D';
            } else if (ultimo == 'D') {
                retorno = 'E';
            } else if (ultimo == 'E') {
                retorno = 'F';
            } else if (ultimo == 'F') {
                retorno = 'G';
            } else if (ultimo == 'G') {
                retorno = 'H';
            } else if (ultimo == 'H') {
                retorno = 'I';
            } else if (ultimo == 'I') {
                retorno = 'J';
            } else if (ultimo == 'J') {
                retorno = 'K';
            } else if (ultimo == 'K') {
                retorno = 'L';
            } else if (ultimo == 'L') {
                retorno = 'M';
            } else if (ultimo == 'M') {
                retorno = 'N';
            } else if (ultimo == 'N') {
                retorno = 'O';
            } else if (ultimo == 'O') {
                retorno = 'P';
            } else if (ultimo == 'P') {
                retorno = 'Q';
            } else if (ultimo == 'Q') {
                retorno = 'R';
            } else if (ultimo == 'R') {
                retorno = 'S';
            } else if (ultimo == 'S') {
                retorno = 'T';
            } else if (ultimo == 'T') {
                retorno = 'U';
            } else if (ultimo == 'U') {
                retorno = 'V';
            } else if (ultimo == 'V') {
                retorno = 'W';
            } else if (ultimo == 'W') {
                retorno = 'X';
            } else if (ultimo == 'X') {
                retorno = 'Y';
            } else if (ultimo == 'Y') {
                retorno = 'Z';
            } else if (ultimo == 'Z') {
                retorno = '0';
            }
        }
        return retorno;
    }

    public static String pegaProxima(String ultima, Boolean somenteNumeros) {
        String retorno = "";
        boolean terminou = false;
        for (int i1 = ultima.length() - 1; i1 > -1; i1--) {
            char fazendo = ultima.charAt(i1);
            if (!terminou) {
                retorno = pegaProximo(fazendo, somenteNumeros) + retorno;
                if (!ehUltimoDaOrdem(fazendo, somenteNumeros)) {
                    terminou = true;
                }
            } else {
                retorno = fazendo + retorno;
            }
        }
        return retorno;
    }

    public static String bota(String osCaracteres, Integer naPosicao, String nosCaracteres) {
        if (nosCaracteres == null) {
            return null;
        } else {
            return pParte(nosCaracteres, 0, naPosicao) + osCaracteres + pParte(nosCaracteres, naPosicao, nosCaracteres.length());
        }
    }

    public static String corta(String dosCaracteres, String osCaracteres) {
        return troca(dosCaracteres, osCaracteres, "");
    }

    public static String corta(String dosCaracteres, Integer aQuantidade) {
        if (aQuantidade > 0) {
            if (dosCaracteres.length() > aQuantidade) {
                return dosCaracteres.substring(0, dosCaracteres.length() - aQuantidade);
            } else {
                return dosCaracteres;
            }
        } else {
            return dosCaracteres;
        }
    }

    public static String cortaAFrente(String dosCaracteres, Integer aQuantidade) {
        if (aQuantidade > dosCaracteres.length()) {
            return "";
        } else {
            return dosCaracteres.substring(aQuantidade);
        }
    }

    public static String cortaAFrente(String dosCaracteres, char oCaracter) {
        String retorno = "";
        Boolean cortando = true;
        for (int i1 = 0; i1 < dosCaracteres.length(); i1++) {
            if (dosCaracteres.charAt(i1) != oCaracter) {
                cortando = false;
            }
            if (!cortando) {
                retorno = retorno + dosCaracteres.charAt(i1);
            }
        }
        return retorno;
    }

    public static String cortaMaior(String dosCaracteres, Integer queQuantidade) {
        if (dosCaracteres != null) {
            if (dosCaracteres.length() > queQuantidade) {
                return dosCaracteres.substring(0, queQuantidade);
            } else {
                return dosCaracteres;
            }
        } else {
            return null;
        }
    }

    public static String formata(String osCaracteres, String noFormato) {
        return formata(osCaracteres, noFormato, null);
    }

    public static String formata(String osCaracteres, String noFormato, String comPadrao) {
        String retorno = comPadrao;
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatter.format(noFormato, osCaracteres);
        retorno = sb.toString();
        return retorno;
    }

    public static String formataSQL(String dosCaracteres) {
        return formataSQL(dosCaracteres, null);
    }

    public static String formataSQL(String dosCaracteres, String comPadrao) {
        String retorno = "'";
        if (dosCaracteres != null) {
            retorno += dosCaracteres;
        } else {
            if (comPadrao != null) {
                retorno += comPadrao;
            }
        }
        return retorno + "'";
    }

    public static String fazEspeciais(String dosCaracteres) {
        String retorno = dosCaracteres;
        if (retorno != null) {
            retorno = troca(retorno, "\\r", "\r");
            retorno = troca(retorno, "\\n", "\n");
            retorno = troca(retorno, "\\t", "\t");
        }
        return retorno;
    }

    public static String fazSigla(String dosCaracteres) {
        String retorno = "";
        if (dosCaracteres != null) {
            String[] palavras = dosCaracteres.trim().split(" ");
            for (String palavra : palavras) {
                if (palavra != null) {
                    String procPal = palavra.trim().toUpperCase();
                    if (!procPal.isEmpty()) {
                        retorno = retorno + procPal.charAt(0);
                    }
                }
            }
        }
        return retorno;
    }

    public static String fazCaracteres(char doCaracter, Integer naQuantidade) {
        String retorno = "";
        while (retorno.length() < naQuantidade) {
            retorno = retorno + doCaracter;
        }
        return retorno;
    }

    public static String preencheAntes(String osCaracteres, Integer ateTamanho, char comCaracter) {
        String retorno = osCaracteres;
        while (retorno.length() < ateTamanho) {
            retorno = comCaracter + retorno;
        }
        return retorno;
    }

    public static String pEntre(String dosCaracteres, Character abridor, Character fechador, Character auxiliar) {
        String retorno = null;
        int abriu = 0;
        boolean anteriorAux = false;
        if (dosCaracteres != null) {
            retorno = "";
            for (int i1 = 0; i1 < dosCaracteres.length(); i1++) {
                if (anteriorAux) {
                    retorno = retorno + dosCaracteres.charAt(i1);
                    anteriorAux = false;
                } else if (dosCaracteres.charAt(i1) == auxiliar) {
                    anteriorAux = true;
                } else if (dosCaracteres.charAt(i1) == abridor) {
                    abriu++;
                } else if (dosCaracteres.charAt(i1) == fechador) {
                    abriu--;
                    if (abriu == 0) {
                        break;
                    }
                } else if (abriu > 0) {
                    retorno = retorno + dosCaracteres.charAt(i1);
                }
            }
        }
        return retorno;
    }

    public static String pegaEntreParenteses(String dosCaracteres) {
        return pEntre(dosCaracteres, '(', ')', '\\');
    }

    public static String pegaEntre(String nosCaracteres, String comAbridor, String eFechador) {
        String retorno = null;

        if (nosCaracteres != null) {
            int imi = nosCaracteres.indexOf(comAbridor);
            if (imi > -1) {
                retorno = "";
                int reabertos = 0;
                int idx = imi + comAbridor.length();
                while (idx < nosCaracteres.length()) {
                    Character chAt = nosCaracteres.charAt(idx);
                    if (idx + comAbridor.length() < nosCaracteres.length()) {
                        String verAbre = nosCaracteres.substring(idx, idx + comAbridor.length());
                        if (comAbridor.equals(verAbre)) {
                            reabertos++;
                        }

                        String verFecha = nosCaracteres.substring(idx, idx + eFechador.length());
                        if (eFechador.equals(verFecha)) {
                            if (reabertos > 0) {
                                reabertos--;
                            } else {
                                break;
                            }
                        }
                    }
                    retorno += chAt;
                    idx++;
                }
            }
        }

        return retorno;
    }

    public static String removeDoInicio(String dosCaracteres, String osCaracteres) {
        String retorno = dosCaracteres;
        if (dosCaracteres != null && osCaracteres != null) {
            if (dosCaracteres.indexOf(osCaracteres) == 0) {
                retorno = dosCaracteres.substring(osCaracteres.length());
            }
        }
        return retorno;
    }

    public static String junta(String aosCaracteres, String osCaracteres, String comJuntador) {
        String retorno = aosCaracteres;
        if (osCaracteres != null) {
            if (retorno != null) {
                if (!retorno.isEmpty()) {
                    retorno += comJuntador + osCaracteres;
                } else {
                    retorno = osCaracteres;
                }
            } else {
                retorno = osCaracteres;
            }
        }
        return retorno;
    }

    public static String preenche(String osCaracteres, Integer ateTamanho, char comCaracter) {
        String retorno = osCaracteres;
        while (retorno.length() < ateTamanho) {
            retorno = retorno + comCaracter;
        }
        return retorno;
    }

    public static String repete(String osCaracteres, Integer naQuantidade) {
        String retorno = "";
        for (int i1 = 0; i1 < naQuantidade; i1++) {
            retorno += osCaracteres;
        }
        return retorno;
    }

    public static String pParte(String dosCaracteres, Integer iniciandoEm, Integer terminandoEm) {
        String retorno = "";
        if (dosCaracteres != null) {
            int ini = iniciandoEm;
            int ter = terminandoEm;
            if (ini > -1 && ter > 0 && ter > ini && ini < dosCaracteres.length()) {
                if (ter <= dosCaracteres.length()) {
                    retorno = dosCaracteres.substring(ini, ter);
                } else {
                    retorno = dosCaracteres.substring(ini);
                }
            }

        } else {
            return null;
        }
        return retorno;
    }

    public static Integer quantosTem(String nosCaracteres, Character oCaracter) {
        Integer retorno = 0;
        if (nosCaracteres != null && oCaracter != null) {
            for (int i1 = 0; i1 < nosCaracteres.length(); i1++) {
                if (nosCaracteres.charAt(i1) == oCaracter) {
                    retorno++;
                }
            }
        }
        return retorno;
    }

    public static Integer quantosTem(String nosCaracteres, String osCaracteres) {
        Integer retorno = 0;
        if (nosCaracteres != null && osCaracteres != null) {
            int ind = -1;
            do {
                ind = nosCaracteres.indexOf(osCaracteres, ind + 1);
                if (ind > -1) {
                    retorno++;
                }
            } while (ind > -1);
        }
        return retorno;
    }

    public static List<Integer> posicoes(String nosCaracteres, String dosCaracteres) {
        List<Integer> retorno = new ArrayList<>();
        if (nosCaracteres != null && dosCaracteres != null) {
            int ind = -1;
            do {
                ind = nosCaracteres.indexOf(dosCaracteres, ind + 1);
                if (ind > -1) {
                    retorno.add(ind);
                }
            } while (ind > -1);
        }
        return retorno;
    }

    public static String troca(String dosCaracteres, String de, String para) {
        String retorno = dosCaracteres;
        if (dosCaracteres != null) {
            if (dosCaracteres.contains(de)) {
                retorno = "";
                int ant = 0;
                int ind = -1;
                do {
                    ind = dosCaracteres.indexOf(de, ind + 1);
                    if (ind > -1) {
                        retorno += pParte(dosCaracteres, ant, ind) + para;
                        ant = ind + de.length();
                    }
                } while (ind > -1);
                retorno += pParte(dosCaracteres, ant, dosCaracteres.length());
            }
        }
        return retorno;
    }

    public static String pergunta(String comMensagem) {
        return pergunta(comMensagem, null, "Pointel Pergunta");
    }

    public static String pergunta(String comMensagem, String ePadrao) {
        return pergunta(comMensagem, ePadrao, "Pointel Pergunta");
    }

    public static String pergunta(String comMensagem, String ePadrao, String eTitulo) {
        if (eTitulo == null) {
            eTitulo = "Pointel Pergunta";
        }
        return new Pergunta(comMensagem, eTitulo, ePadrao).mostra().pegaResposta();
    }

    public static String perguntaSenha() {
        return new PerguntaSenha().mostra().pegaResposta();
    }

    public static String perguntaSenha(String comMsg) {
        return new PerguntaSenha(comMsg).mostra().pegaResposta();
    }

    public static String perguntaSenha(String comMsg, String eTitulo) {
        return new PerguntaSenha(comMsg, eTitulo).mostra().pegaResposta();
    }

    public static Boolean confereSenha(String comCrs) {
        return comCrs.equals(perguntaSenha());
    }

    public static Boolean confereSenha(String comCrs, String comMsg) {
        return comCrs.equals(perguntaSenha(comMsg));
    }

    public static Boolean confereSenha(String comCrs, String comMsg, String eTitulo) {
        return comCrs.equals(perguntaSenha(comMsg, eTitulo));
    }

    public static Boolean confereSenhaRepete(String comCrs, String comMsg) {
        return confereSenhaRepete(comCrs, comMsg, "Pointel Senha");
    }

    public static Boolean confereSenhaRepete(String comCrs, String comMsg, String eTitulo) {
        return new PerguntaSenha(comMsg, eTitulo) {
            @Override
            public void aoConfirmar(String comResposta) throws Exception {
                if (comCrs.equals(comResposta)) {
                    botaConfirmado();
                } else {
                    throw new Exception("Senha Não Conferiu");
                }
            }
        }.mostra().pegaConfirmado();
    }

    private static MessageDigest mapeador = null;
    private static Cipher cifrador = null;

    public static String encripta(String osCaracteres, String comSenha) throws Exception {
        if (mapeador == null) {
            mapeador = MessageDigest.getInstance("MD5");
        }
        byte[] passHash = mapeador.digest(comSenha.getBytes("UTF8"));
        Key aesKey = new SecretKeySpec(passHash, "AES");
        if (cifrador == null) {
            cifrador = Cipher.getInstance("AES");
        }
        cifrador.init(Cipher.ENCRYPT_MODE, aesKey);
        byte[] encrypted = cifrador.doFinal(osCaracteres.getBytes("UTF8"));
        return DatatypeConverter.printBase64Binary(encrypted);
    }

    public static String desencripta(String osCaracteres, String comSenha) throws Exception {
        if (mapeador == null) {
            mapeador = MessageDigest.getInstance("MD5");
        }
        byte[] passHash = mapeador.digest(comSenha.getBytes("UTF8"));
        Key aesKey = new SecretKeySpec(passHash, "AES");
        if (cifrador == null) {
            cifrador = Cipher.getInstance("AES");
        }
        cifrador.init(Cipher.DECRYPT_MODE, aesKey);
        byte[] orign = DatatypeConverter.parseBase64Binary(osCaracteres);
        byte[] decrypted = cifrador.doFinal(orign);
        return new String(decrypted, "UTF8");
    }

    private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

    public static String pegaCNPJCPFFormatado(VCrs dosCaracteres) {
        return pegaCNPJCPFFormatado(dosCaracteres.toString());
    }

    public static String pegaCNPJCPFFormatado(String dosCaracteres) {
        String retorno = dosCaracteres;
        if (dosCaracteres.length() == 14) {
            retorno = dosCaracteres.substring(0, 2) + "." + dosCaracteres.substring(2, 5) + "." + dosCaracteres.substring(5, 8) + "/" + dosCaracteres.substring(8, 12) + "." + dosCaracteres.substring(12, 14);
        }
        if (dosCaracteres.length() == 11) {
            retorno = dosCaracteres.substring(0, 3) + "." + dosCaracteres.substring(3, 6) + "." + dosCaracteres.substring(6, 9) + "-" + dosCaracteres.substring(9, 11);
        }
        return retorno;
    }

    public static boolean ehCNPJValido(String osCaracteres) {
        if ((osCaracteres == null) || (osCaracteres.length() != 14)) {
            return false;
        }
        Integer digito1 = calculaDigito(osCaracteres.substring(0, 12), pesoCNPJ);
        Integer digito2 = calculaDigito(osCaracteres.substring(0, 12) + digito1, pesoCNPJ);
        return osCaracteres.equals(osCaracteres.substring(0, 12) + digito1.toString() + digito2.toString());
    }

    public static int calculaDigito(String str, int[] peso) {
        int soma = 0;
        for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
            digito = Integer.parseInt(str.substring(indice, indice + 1));
            soma += digito * peso[peso.length - str.length() + indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }

    public static boolean ehCPFValido(String osCaracteres) {
        if ((osCaracteres == null) || (osCaracteres.length() != 11)) {
            return false;
        }
        Integer digito1 = calculaDigito(osCaracteres.substring(0, 9), pesoCPF);
        Integer digito2 = calculaDigito(osCaracteres.substring(0, 9) + digito1, pesoCPF);
        return osCaracteres.equals(osCaracteres.substring(0, 9) + digito1.toString() + digito2.toString());
    }

    public static Boolean ehCNPJCPFValido(String osCaracteres) {
        Boolean retorno = false;
        if (osCaracteres.length() == 14) {
            retorno = ehCNPJValido(osCaracteres);
        }
        if (osCaracteres.length() == 11) {
            retorno = ehCPFValido(osCaracteres);
        }
        return retorno;
    }

    public static String soma(String[] todosCaracteres) {
        return soma(todosCaracteres, null);
    }

    public static String soma(String[] todosCaracteres, String usaUniao) {
        if (todosCaracteres == null) {
            return null;
        }
        String retorno = "";
        for (String osCaracteres : todosCaracteres) {
            retorno = soma(retorno, usaUniao, osCaracteres);
        }
        return retorno;
    }

    public static String soma(String osCaracteres, String comCaracteres) {
        return soma(osCaracteres, null, comCaracteres);
    }

    public static String soma(String osCaracteres, String usaUniao, String comCaracteres) {
        String retorno = osCaracteres;
        if (comCaracteres != null) {
            if (!comCaracteres.isEmpty()) {
                if (retorno == null) {
                    retorno = comCaracteres;
                } else if (retorno.isEmpty()) {
                    retorno = comCaracteres;
                } else {
                    retorno += (usaUniao != null ? usaUniao : "") + comCaracteres;
                }
            }
        }
        return retorno;
    }

    public static String somaCrs(String comUniao, Object... dosObjetos) {
        String retorno = "";
        if (dosObjetos != null) {
            for (Object obj : dosObjetos) {
                retorno = soma(retorno, comUniao, pega(obj));
            }
        }
        return retorno;
    }

    public static String somaAFrente(String osCaracteres, String comCaracteres) {
        return soma(comCaracteres, null, osCaracteres);
    }

    public static String somaAFrente(String osCaracteres, String usaUniao, String comCaracteres) {
        return soma(comCaracteres, usaUniao, osCaracteres);
    }

    public static String tiraAcentos(String dosCaracteres) {
        if (dosCaracteres == null) {
            return null;
        }
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");//$NON-NLS-1$
        StringBuilder decomposed = new StringBuilder(Normalizer.normalize(dosCaracteres, Normalizer.Form.NFD));
        converteRestanteAcentuados(decomposed);
        return pattern.matcher(decomposed).replaceAll("");
    }

    private static void converteRestanteAcentuados(StringBuilder decomposed) {
        for (int i = 0; i < decomposed.length(); i++) {
            if (decomposed.charAt(i) == '\u0141') {
                decomposed.deleteCharAt(i);
                decomposed.insert(i, 'L');
            } else if (decomposed.charAt(i) == '\u0142') {
                decomposed.deleteCharAt(i);
                decomposed.insert(i, 'l');
            }
        }
    }

    public static String pWebNome(String dosCaracteres) {
        if (dosCaracteres == null) {
            return null;
        }
        String retorno = dosCaracteres.toLowerCase();
        retorno = troca(retorno, "\t", " ");
        retorno = troca(retorno, "\f", " ");
        retorno = troca(retorno, "\n", " ");
        retorno = troca(retorno, "\r", " ");
        while (retorno.contains("  ")) {
            retorno = troca(retorno, "  ", " ");
        }
        retorno = retorno.trim();
        retorno = troca(retorno, " ", "_");
        retorno = tiraAcentos(retorno);
        return retorno;
    }
}
