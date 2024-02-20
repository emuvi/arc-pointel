package pin.libutl;

import java.awt.Desktop;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import pin.libbas.Retornos;

public class UtlRed {

    public static String pergunta() throws Exception {
        String caminho = UtlCrs.pergunta("Qual o Caminho?");
        if (caminho != null) {
            caminho = normaliza(caminho);
        }
        return caminho;
    }

    public static void abre(String oCaminho) throws Exception {
        Desktop.getDesktop().browse(new URI(normaliza(oCaminho)));
    }

    public static String normaliza(String oCaminho) throws Exception {
        if (oCaminho == null) {
            return null;
        } else {
            String retorno = decodifica(oCaminho);
            oCaminho = oCaminho.toLowerCase();
            if (!oCaminho.contains("://")) {
                oCaminho = "http://" + oCaminho;
            }
            return retorno;
        }
    }

    public static String codifica(String oCaminho) throws Exception {
        if (oCaminho == null) {
            return null;
        } else {
            return URLEncoder.encode(oCaminho, "UTF8");
        }
    }

    public static String decodifica(String oCaminho) throws Exception {
        if (oCaminho == null) {
            return null;
        } else {
            return URLDecoder.decode(oCaminho, "UTF8");
        }
    }

    public static String pegaHex(int i) {
        return Integer.toHexString(i);
    }

    public static String limpaCaracteres(String osCaracteres, boolean limpaAspasSimples, boolean limpaContraBarra) {
        StringBuilder out = new StringBuilder("");
        if (osCaracteres == null) {
            return null;
        }
        int sz;
        sz = osCaracteres.length();
        for (int i = 0; i < sz; i++) {
            char ch = osCaracteres.charAt(i);

            // handle unicode
            if (ch > 0xfff) {
                out.append("\\u").append(pegaHex(ch));
            } else if (ch > 0xff) {
                out.append("\\u0").append(pegaHex(ch));
            } else if (ch > 0x7f) {
                out.append("\\u00").append(pegaHex(ch));
            } else if (ch < 32) {
                switch (ch) {
                    case '\b':
                        out.append('\\');
                        out.append('b');
                        break;
                    case '\n':
                        out.append('\\');
                        out.append('n');
                        break;
                    case '\t':
                        out.append('\\');
                        out.append('t');
                        break;
                    case '\f':
                        out.append('\\');
                        out.append('f');
                        break;
                    case '\r':
                        out.append('\\');
                        out.append('r');
                        break;
                    default:
                        if (ch > 0xf) {
                            out.append("\\u00").append(pegaHex(ch));
                        } else {
                            out.append("\\u000").append(pegaHex(ch));
                        }
                        break;
                }
            } else {
                switch (ch) {
                    case '\'':
                        if (limpaAspasSimples) {
                            out.append('\\');
                        }
                        out.append('\'');
                        break;
                    case '"':
                        out.append('\\');
                        out.append('"');
                        break;
                    case '\\':
                        out.append('\\');
                        out.append('\\');
                        break;
                    case '/':
                        if (limpaContraBarra) {
                            out.append('\\');
                        }
                        out.append('/');
                        break;
                    default:
                        out.append(ch);
                        break;
                }
            }
        }
        return out.toString();
    }

    public static String pegaServidor(String doCaminho) {
        String retorno = null;
        if (doCaminho != null) {
            if (!doCaminho.isEmpty()) {
                String cam = doCaminho;
                if (!cam.contains("://")) {
                    cam = "http://" + cam;
                }
                int ii = cam.indexOf("//");
                int ib = cam.indexOf("/", ii + 2);
                if (ib == -1) {
                    ib = cam.length();
                }
                retorno = cam.substring(0, ib);
            }
        }
        return retorno;
    }

    public static String pegaRaiz(String doCaminho) {
        String retorno = null;
        if (doCaminho != null) {
            if (!doCaminho.isEmpty()) {
                String cam = doCaminho;
                if (!cam.contains("/")) {
                    cam = "/" + cam;
                }
                Integer ib = cam.lastIndexOf("/");
                String fim = cam.substring(ib);
                if (fim.contains(".")) {
                    retorno = cam.substring(0, ib);
                } else {
                    retorno = cam;
                }
                if (!retorno.endsWith("/")) {
                    retorno = retorno + "/";
                }
            }
        }
        return retorno;
    }

    public static String pegaParente(String doCaminho) {
        String retorno = null;
        if (doCaminho != null) {
            if (!doCaminho.isEmpty()) {
                String cam = doCaminho.trim();
                if (!cam.contains("/")) {
                    cam = "/" + cam;
                }
                Integer ib = cam.lastIndexOf("/");
                if (ib == cam.length() - 1) {
                    ib = cam.lastIndexOf("/", ib - 1);
                }
                retorno = cam.substring(0, ib + 1);
                if (retorno.equals("/")) {
                    retorno = doCaminho.trim();
                }
            }
        }
        return retorno;
    }

    public static String pegaNome(String doCaminho) {
        String retorno = null;
        if (doCaminho != null) {
            if (!doCaminho.isEmpty()) {
                String cam = doCaminho;
                if (!cam.contains("/")) {
                    cam = "/" + cam;
                }
                Integer ib = cam.lastIndexOf("/");
                retorno = cam.substring(ib + 1);
            }
        }
        return retorno;
    }

    public static Boolean existe(String oCaminho) throws Exception {
        Boolean retorno = false;
        if (oCaminho != null) {
            URL url = new URL(oCaminho);
            if (oCaminho.startsWith("http://")) {
                HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                urlc.setAllowUserInteraction(false);
                urlc.setDoInput(true);
                urlc.setDoOutput(false);
                urlc.setUseCaches(true);
                urlc.setRequestMethod("HEAD");
                urlc.connect();
                retorno = urlc.getResponseCode() == 200;
            } else if (oCaminho.startsWith("file://")) {
                File arq = new File(url.toURI());
                return arq.exists();
            }
        }
        return retorno;
    }

    public static ByteArrayOutputStream baixa() throws Exception {
        String caminho = pergunta();
        if (caminho != null) {
            return UtlRed.baixa(caminho);
        }
        return null;
    }

    public static ByteArrayOutputStream baixa(String oCaminho) throws Exception {
        if (oCaminho == null) {
            return null;
        }
        if (!oCaminho.contains("://")) {
            oCaminho = "http://" + oCaminho;
        }
        ByteArrayOutputStream retorno = null;
        URL url = new URL(oCaminho);
        InputStream is = url.openStream();
        retorno = new ByteArrayOutputStream();
        byte[] byteChunk = new byte[1024];
        int n;
        while ((n = is.read(byteChunk)) > 0) {
            retorno.write(byteChunk, 0, n);
        }
        is.close();
        return retorno;
    }

    public static void baixa(String oCaminho, File noArquivo) throws Exception {
        if (!UtlArq.criaHierarquia(noArquivo.getParentFile())) {
            throw new Exception("Não Foi Possível Estabelecer O Diretório: " + noArquivo.getParent());
        }
        ByteArrayOutputStream baos = UtlRed.baixa(oCaminho);
        FileOutputStream fou = new FileOutputStream(noArquivo, false);
        fou.write(baos.toByteArray());
        fou.close();
    }

    public static Boolean baixa(String oCaminho, File noArquivo, Retornos retornos) {
        try {
            Date tmpIni = new Date();
            UtlArq.criaHierarquia(noArquivo.getParentFile());
            ByteArrayOutputStream baos = UtlRed.baixa(oCaminho);
            FileOutputStream fou = new FileOutputStream(noArquivo, false);
            fou.write(baos.toByteArray());
            fou.close();
            Date tmpFim = new Date();
            Retornos.bota(retornos, "Baixar", "Baixado em " + UtlTem.diferencaFormatada(tmpFim, tmpIni));
            return true;
        } catch (Exception ex) {
            Utl.registra(ex, true, retornos == null, retornos);
            return false;
        }
    }

    public static String baixaTex() throws Exception {
        String retorno = null;
        ByteArrayOutputStream baos = baixa();
        if (baos != null) {
            retorno = UtlArq.formata(baos.toByteArray());
        }
        return retorno;
    }

    public static String baixaTex(String doCaminho) throws Exception {
        String retorno = null;
        ByteArrayOutputStream baos = UtlRed.baixa(doCaminho);
        if (baos != null) {
            retorno = UtlArq.formata(baos.toByteArray());
        }
        return retorno;
    }

    public static byte[] baixaArq() throws Exception {
        byte[] retorno = null;
        ByteArrayOutputStream baos = baixa();
        if (baos != null) {
            retorno = baos.toByteArray();
        }
        return retorno;
    }

    public static byte[] baixaArq(String doCaminho) throws Exception {
        byte[] retorno = null;
        ByteArrayOutputStream baos = UtlRed.baixa(doCaminho);
        if (baos != null) {
            retorno = baos.toByteArray();
        }
        return retorno;
    }

    public static Boolean ehFonte(String oCaminho) {
        Boolean retorno = false;
        if (oCaminho != null) {
            try {
                URL url = new URL(oCaminho);
                HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                urlc.setAllowUserInteraction(false);
                urlc.setDoInput(true);
                urlc.setDoOutput(false);
                urlc.setUseCaches(true);
                urlc.setRequestMethod("HEAD");
                urlc.connect();
                String mime = urlc.getContentType().toLowerCase();
                retorno = mime.contains("text/html");
            } catch (Exception ex) {
            }
        }
        return retorno;
    }

    public static List<String> pegaVinculos(String doCaminho) {
        return pegaVinculos(doCaminho, (Retornos) null);
    }

    public static Boolean pegaVinculos(String doCaminho, File salvaEm) throws Exception {
        List<String> vinculos = pegaVinculos(doCaminho);
        return UtlTex.salva(vinculos, salvaEm);
    }

    public static List<String> pegaVinculos(String doCaminho, Retornos retornos) {
        List<String> retorno = new ArrayList<>();
        try {
            Date tmpIni = new Date();
            ByteArrayOutputStream baos = UtlRed.baixa(doCaminho);
            String fonte = baos.toString();
            int iFim = -1;
            int iPos = fonte.indexOf("href=\"", 0);
            while (iPos > -1) {
                iPos = iPos + 6;
                iFim = fonte.indexOf("\"", iPos);
                if (iFim > -1) {
                    String vinculo = fonte.substring(iPos, iFim);
                    retorno.add(vinculo.replaceAll("\\s", ""));
                    iPos = fonte.indexOf("href=\"", iFim);
                } else {
                    iPos = -1;
                }
            }
            Date tmpFim = new Date();
            Retornos.bota(retornos, "PegaVínculos", "Carregado em " + UtlTem.diferencaFormatada(tmpFim, tmpIni));
        } catch (Exception ex) {
            Utl.registra(ex, true, retornos == null, retornos);
        }
        return retorno;
    }

    public static String soma(String naRaiz, String oComplemento) {
        String retorno = naRaiz;
        if (retorno != null) {
            if (oComplemento != null) {
                if (!oComplemento.isEmpty()) {
                    if (!retorno.endsWith("/")) {
                        retorno += "/";
                    }
                    if (oComplemento.startsWith("/")) {
                        oComplemento = oComplemento.substring(1);
                    }
                }
            }
            retorno += oComplemento;
        } else {
            retorno = oComplemento;
        }
        return retorno;
    }

    public static String soma(String... asPartes) {
        String retorno = null;
        if (asPartes != null) {
            for (String parte : asPartes) {
                retorno = UtlRed.soma(retorno, parte);
            }
        }
        return retorno;
    }

}
