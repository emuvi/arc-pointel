package pin.libutl;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtlTex {

    public static Boolean ehExtensao(String aExtensao) {
        aExtensao = aExtensao.toLowerCase();
        return UtlLis.tem(aExtensao, pegaExtensoes());
    }

    public static String[] pegaExtensoes() {
        return new String[]{"txt", "log", "java", "sql", "xml", "xhtm", "xhtml", "htm", "html", "php", "asp", "css", "js", "jsp", "map", "json"};
    }

    public static String pegaExtensoesDescricao() {
        return "Textos (*.txt, *.log, *.java, *.sql, *.xml, *.xhtm, *.xhtml, *.htm, *.html, *.php, *.asp, *.css, *.js, *.jsp, *.map, *.json)";
    }

    public static String pMaior(String[] dasLinhas) {
        if (dasLinhas == null) {
            return null;
        }
        String retorno = null;
        for (String linha : dasLinhas) {
            if (retorno == null) {
                retorno = linha;
            } else if (linha.length() > retorno.length()) {
                retorno = linha;
            }
        }
        return retorno;
    }

    public static String[] pLinhas(String doTexto) {
        return pLinhas(doTexto, "\\r\\n|\\n|\\r", false);
    }

    public static String[] pLinhas(String doTexto, String comQuebra) {
        return pLinhas(doTexto, comQuebra, false);
    }

    public static String[] pLinhas(String doTexto, Boolean limpando) {
        return pLinhas(doTexto, "\\r\\n|\\n|\\r", limpando);
    }

    public static String[] pLinhas(String doTexto, String comQuebra, Boolean limpando) {
        if (doTexto == null) {
            return null;
        }
        String[] retorno = doTexto.split(comQuebra);
        if (limpando) {
            for (int i = 0; i < retorno.length; i++) {
                retorno[i] = retorno[i].trim();
            }
        }
        return retorno;
    }

    public static List<String> pegaLinhas(File doArquivo) throws Exception {
        return pegaLinhas(doArquivo, "UTF8", false);
    }

    public static List<String> pegaLinhas(File doArquivo, String naCodificacao) throws Exception {
        return pegaLinhas(doArquivo, naCodificacao, false);
    }

    public static List<String> pegaLinhas(File doArquivo, String naCodificacao, Boolean limpando) throws Exception {
        ArrayList<String> linhas = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(doArquivo), naCodificacao));
        String linha;
        while ((linha = br.readLine()) != null) {
            if (limpando) {
                linha = linha.trim();
            }
            linhas.add(linha);
        }
        br.close();
        return linhas;
    }

    public static List<String> pegaLinhas(URL doEndereco) throws Exception {
        return pegaLinhas(doEndereco, "UTF8", false);
    }

    public static List<String> pegaLinhas(URL doEndereco, String naCodificacao) throws Exception {
        return pegaLinhas(doEndereco, naCodificacao, false);
    }

    public static List<String> pegaLinhas(URL doEndereco, String naCodificacao, Boolean limpando) throws Exception {
        ArrayList<String> linhas = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(doEndereco.openStream(), naCodificacao));
        String linha;
        while ((linha = br.readLine()) != null) {
            if (limpando) {
                linha = linha.trim();
            }
            linhas.add(linha);
        }
        br.close();
        return linhas;
    }

    public static String pegaLinhaComecando(String com, String[] noTexto) {
        String retorno = null;
        if (noTexto != null) {
            for (String lin : noTexto) {
                if (lin.startsWith(com)) {
                    retorno = lin;
                    break;
                }
            }
        }
        return retorno;
    }

    public static Boolean pegaLinhasComecando(String com, File doArquivo, File salvandoEm) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(doArquivo), "UTF8"));
        String linha;
        String novo = "";
        while ((linha = br.readLine()) != null) {
            if (linha.startsWith(com)) {
                novo = novo + linha + System.lineSeparator();
            }
        }
        br.close();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(salvandoEm), "UTF8"));
        bw.write(novo);
        bw.close();
        return true;
    }

    public static String pegaLinhaAleatoria(File doArquivo) throws Exception {
        String retorno = null;
        List<String> linhas = pegaLinhas(doArquivo);
        if (linhas.size() > 0) {
            retorno = linhas.get(UtlAle.pegaInt(0, linhas.size()));
        }
        return retorno;
    }

    public static List<Point> procura(String noTexto, Pattern comVerificador) {
        List<Point> encontrados = new ArrayList<>();
        Matcher m = comVerificador.matcher(noTexto);
        while (m.find()) {
            encontrados.add(new Point(m.start(), m.end()));
        }
        return encontrados;
    }

    public static String soma(String noTexto, String... asLinhas) {
        String retorno = noTexto;
        if (asLinhas != null) {
            for (String linha : asLinhas) {
                if (linha != null) {
                    if (retorno == null) {
                        retorno = "";
                    }
                    retorno += (retorno.isEmpty() ? "" : UtlTex.quebra()) + linha;
                }
            }
        }
        return retorno;
    }

    public static String botaParte(String noTexto, Integer comInicio, Integer eFim, String aParte) {
        String retorno = noTexto;
        String inicio = pegaParte(noTexto, 0, comInicio, "");
        String fim = pegaParte(noTexto, eFim, noTexto.length(), "");
        retorno = inicio + aParte + fim;
        return retorno;
    }

    public static String pegaParte(String doTexto, Integer comInicio, Integer eFim) {
        return pegaParte(doTexto, comInicio, eFim, null);
    }

    public static String pegaParte(String doTexto, Integer comInicio, Integer eFim, String comPadrao) {
        String retorno = comPadrao;
        if (doTexto != null) {
            if (eFim - comInicio > 0) {
                int ini = comInicio > doTexto.length() ? doTexto.length() : comInicio;
                int fim = eFim > doTexto.length() ? doTexto.length() : eFim;
                retorno = doTexto.substring(ini, fim);
            }
        }
        return retorno;
    }

    public static String pegaEntre(String oComeco, String eFim, File doArquivo) throws Exception {
        String retorno = null;
        ArrayList<String> linhas = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(doArquivo), "UTF8"));
        String todo = "";
        String linha;
        while ((linha = br.readLine()) != null) {
            todo = todo + linha + System.lineSeparator();
        }
        br.close();
        int in = todo.indexOf(oComeco);
        int fi = todo.indexOf(eFim, in);
        retorno = todo.substring(in + oComeco.length(), fi);
        return retorno;
    }

    public static String junta(String... asLinhas) {
        return soma("", asLinhas);
    }

    public static String junta(List<String> asLinhas) {
        if (asLinhas != null) {
            return soma("", asLinhas.toArray(new String[0]));
        } else {
            return null;
        }
    }

    public static String juntaVlrs(List<Object> osValores) {
        String retorno = null;
        if (osValores != null) {
            for (Object vlr : osValores) {
                retorno = soma(retorno, UtlCrs.pega(vlr));
            }
        }
        return retorno;
    }

    public static Boolean contem(String aLinha, String[] noTexto) {
        Boolean retorno = false;
        if (noTexto != null) {
            for (String lin : noTexto) {
                if (lin.equals(aLinha)) {
                    retorno = true;
                    break;
                }
            }
        }
        return retorno;
    }

    public static Integer contaLinhas(File doArquivo) throws Exception {
        Integer retorno = 0;
        if (doArquivo.exists()) {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(doArquivo)));
            String linha;
            while ((linha = br.readLine()) != null) {
                retorno++;
            }
            br.close();
        }
        return retorno;
    }

    public static String[] limpa(String[] asLinhas) {
        if (asLinhas != null) {
            for (int i1 = 0; i1 < asLinhas.length; i1++) {
                asLinhas[i1] = asLinhas[i1].trim();
            }
        }
        return asLinhas;
    }

    public static List<String> limpa(List<String> asLinhas) {
        if (asLinhas != null) {
            for (int i1 = 0; i1 < asLinhas.size(); i1++) {
                asLinhas.set(i1, asLinhas.get(i1).trim());
            }
        }
        return asLinhas;
    }

    public static String quebraLinha() {
        return quebraLinha(1);
    }

    public static String quebraLinha(Integer quantidade) {
        String retorno = "";
        for (int i1 = 0; i1 < quantidade; i1++) {
            retorno += System.lineSeparator();
        }
        return retorno;
    }

    public static Boolean adiciona(String osCaracteres, File noArquivo) throws Exception {
        Boolean retorno = false;
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(noArquivo, true), "UTF8"));
        out.write(osCaracteres);
        out.flush();
        out.close();
        retorno = true;
        return retorno;
    }

    public static String adicionaNasLinhas(String oComeco, String doTexto) {
        String[] linhas = pLinhas(doTexto, true);
        String retorno = "";
        for (String ln : linhas) {
            retorno = retorno + oComeco + ln + System.lineSeparator();
        }
        return retorno;
    }

    public static Boolean adicionaNasLinhas(String oComeco, File doArquivo, File salvandoEm) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(doArquivo), "UTF8"));
        String linha;
        String novo = "";
        while ((linha = br.readLine()) != null) {
            novo = novo + oComeco + linha + System.lineSeparator();
        }
        br.close();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(salvandoEm), "UTF8"));
        bw.write(novo);
        bw.close();
        return true;
    }

    public static String removeQuebraLinhas(String doTexto) {
        String retorno = doTexto;
        if (retorno != null) {
            retorno = retorno.replaceAll("\\r\\n|\\n|\\r", "");
        }
        return retorno;
    }

    public static void removeLinha(String aLinha, File doArquivo) throws Exception {
        String novo = "";
        if (doArquivo.exists()) {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(doArquivo), "UTF8"));
            String linha;
            while ((linha = br.readLine()) != null) {
                if (!linha.equals(aLinha)) {
                    novo = novo + linha + System.lineSeparator();
                }
            }
            br.close();
            salva(novo, doArquivo);
        }
    }

    public static void removeLinhasRepetidasEmSequencia(File doArquivo, File salvandoEm) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(doArquivo), "UTF8"));
        String linha;
        String ultima = null;
        String novo = "";
        while ((linha = br.readLine()) != null) {
            if (!linha.equals(ultima)) {
                novo = novo + linha + System.lineSeparator();
            }
            ultima = linha;
        }
        br.close();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(salvandoEm), "UTF8"));
        bw.write(novo);
        bw.close();
    }

    public static String abre(File doArquivo) throws Exception {
        return abre(doArquivo, "UTF8");
    }

    public static String abre(File doArquivo, String comCodificacao) throws Exception {
        String retorno;
        BufferedReader br;
        if (comCodificacao == null) {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(doArquivo)));
        } else {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(doArquivo), comCodificacao));
        }
        retorno = "";
        String linha;
        Boolean primeiro = true;
        while ((linha = br.readLine()) != null) {
            if (primeiro) {
                retorno = linha;
                primeiro = false;
            } else {
                retorno = retorno + System.lineSeparator() + linha;
            }
        }
        br.close();
        return retorno;
    }

    public static String abre(File doArquivo, Integer quantidade) throws Exception {
        return abre(doArquivo, "UTF8", quantidade);
    }

    public static String abre(File doArquivo, String comCodificacao, Integer quantidade) throws Exception {
        String retorno;
        BufferedReader br;
        if (comCodificacao == null) {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(doArquivo)));
        } else {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(doArquivo), comCodificacao));
        }
        retorno = "";
        String linha;
        Boolean primeiro = true;
        int qt = 0;
        while ((linha = br.readLine()) != null) {
            if (primeiro) {
                retorno = linha;
                primeiro = false;
            } else {
                retorno = retorno + System.lineSeparator() + linha;
            }
            qt++;
            if (qt >= quantidade) {
                break;
            }
        }
        br.close();
        return retorno;
    }

    public static List<String> abreLinhas(File doArquivo) throws Exception {
        return abreLinhas(doArquivo, "UTF8");
    }

    public static List<String> abreLinhas(File doArquivo, String comCodificacao) throws Exception {
        List<String> retorno = new ArrayList<>();
        BufferedReader br;
        if (comCodificacao == null) {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(doArquivo)));
        } else {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(doArquivo), comCodificacao));
        }
        String linha;
        while ((linha = br.readLine()) != null) {
            retorno.add(linha);
        }
        br.close();
        return retorno;
    }

    public static Boolean salva(String[] asLinhas, File noArquivo) throws Exception {
        String texto = "";
        if (asLinhas != null) {
            for (String lin : asLinhas) {
                texto += (texto.isEmpty() ? "" : System.lineSeparator()) + lin;
            }
        }
        return salva(texto, noArquivo);
    }

    public static Boolean salva(List<String> asLinhas, File noArquivo) throws Exception {
        String texto = "";
        if (asLinhas != null) {
            for (String lin : asLinhas) {
                texto += (texto.isEmpty() ? "" : System.lineSeparator()) + lin;
            }
        }
        return salva(texto, noArquivo);
    }

    public static Boolean salva(String oTexto, File noArquivo) throws Exception {
        return salva(oTexto, noArquivo, "UTF8");
    }

    public static Boolean salva(String oTexto, File noArquivo, String comCodificacao) throws Exception {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(noArquivo, false), comCodificacao));
        if (oTexto != null) {
            out.write(oTexto);
        }
        out.flush();
        out.close();
        return true;
    }
    
    public static String quebraDeLinha = "\r\n";

    public static String quebra(String maisCaracteres) {
        return quebra(1) + maisCaracteres;
    }

    public static String quebra() {
        return quebra(1);
    }

    public static String quebra(Integer quantidade, String maisCaracteres) {
        return quebra(quantidade) + maisCaracteres;
    }

    public static String quebra(Integer quantidade) {
        String retorno = "";
        for (int iq = 0; iq < quantidade; iq++) {
            retorno += quebraDeLinha;
        }
        return retorno;
    }

    public static void tiraECopiaPrimeiraLinha(File doArquivo) throws Exception {
        List<String> linhas = abreLinhas(doArquivo);
        UtlCrs.copiaParaTransferencia(linhas.get(0));
        linhas.remove(0);
        salva(linhas, doArquivo);
    }

}
