package pin.libutl;

import pin.libutl.UtlCrs;
import pin.libutl.UtlLis;
import java.util.ArrayList;
import java.util.List;

public class UtlEma {

    public static char[] crsEMails = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.', '-', '_', '@', '\r', '\n'};

    public static List<String> pegaEMails(String dosCaracteres) {
        List<String> retorno = new ArrayList<>();
        int tam = dosCaracteres.length();
        for (int ic = 0; ic < tam; ic++) {
            if (dosCaracteres.charAt(ic) == '@') {
                int inicio = 0;
                for (int iin = ic; iin >= 0; iin--) {
                    if (!UtlLis.tem(dosCaracteres.charAt(iin), crsEMails)) {
                        inicio = iin + 1;
                        break;
                    }
                }
                int fim = tam - 1;
                for (int ifm = ic; ifm < tam; ifm++) {
                    if (!UtlLis.tem(dosCaracteres.charAt(ifm), crsEMails)) {
                        fim = ifm - 1;
                        break;
                    }
                }
                String email = dosCaracteres.substring(inicio, fim + 1);
                email = email.trim();
                email = UtlTex.removeQuebraLinhas(email);
                Boolean valido = true;
                if (UtlCrs.quantosTem(email, '@') > 1) {
                    valido = false;
                }
                int iat = email.indexOf("@");
                if (iat == 0) {
                    valido = false;
                }
                int idt = email.lastIndexOf(".");
                if (idt < iat) {
                    valido = false;
                }
                if (valido) {
                    retorno.add(email);
                }
            }
        }
        return retorno;
    }

}
