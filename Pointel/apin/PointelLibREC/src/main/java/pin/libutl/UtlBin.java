package pin.libutl;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import pin.libbas.Erro;
import pin.libbas.Par;
import pin.libtex.Marcados;
import pin.libvlr.Vlr;

public class UtlBin {

    public static Boolean eh(Object objeto, Class daClasse) {
        Boolean retorno = false;
        if (objeto != null) {
            Class clObj = UtlBin.pegaDaPrimitiva(objeto.getClass());
            return daClasse.isAssignableFrom(clObj);
        }
        return retorno;
    }

    public static void abrePointel(String... comArgumentos) throws Exception {
        File raiz = UtlArq.pegaPointelDir();
        File execBin = new File(raiz, "Pointel");
        if (!execBin.exists()) {
            execBin = new File(raiz, "Pointel.exe");
        }
        List<String> execBld = new ArrayList<>();
        execBld.add(execBin.getAbsolutePath());
        if (comArgumentos != null) {
            for (String arg : comArgumentos) {
                execBld.add(arg);
            }
        }
        new ProcessBuilder(execBld).start();
    }

    public static File pegaPointelJRE() throws Exception {
        File raiz = UtlArq.pegaPointelDir();
        File execBin = UtlArq.soma(raiz, "java", "bin", "java.exe"); //Win
        if (!execBin.exists()) {
            execBin = UtlArq.soma(raiz, "java", "bin", "java"); //Nix
        }
        if (!execBin.exists()) {
            execBin = UtlArq.soma(raiz, "java", "Contents", "Home", "bin", "java"); //Mac
        }
        return execBin;
    }

    public static String abreModulo(String daLegenda, String... comArgumentos) throws Exception {
        File raiz = UtlArq.pegaPointelDir();
        File execBin = pegaPointelJRE();
        File jarBin = UtlArq.soma(raiz, "apinSrvs", daLegenda, "dist", daLegenda + ".jar");
        if (!jarBin.exists()) {
            throw new Erro("Módulo Não Existe");
        }
        List<String> execBld = new ArrayList<>();
        execBld.add(execBin.getAbsolutePath());
        execBld.add("-jar");
        execBld.add(jarBin.getAbsolutePath());
        if (comArgumentos != null) {
            for (String arg : comArgumentos) {
                execBld.add(arg);
            }
        }
        Process pr = new ProcessBuilder(execBld).start();
        InputStreamReader isr = new InputStreamReader(pr.getInputStream());
        BufferedReader br = new BufferedReader(isr);
        String registro = "";
        String linha;
        while ((linha = br.readLine()) != null) {
            registro = UtlTex.soma(registro, linha);
        }
        if (Marcados.tem(registro, "erro")) {
            String erro = Marcados.preparado(registro, "erro");
            if (Marcados.tem(registro, "origem")) {
                String origem = Marcados.preparado(registro, "origem");
                throw new Erro(erro).mOrigem(origem);
            } else {
                throw new Erro(erro);
            }
        }
        return registro;
    }

    public static Boolean ehPrimitiva(Class classe) {
        if (classe == null) {
            return false;
        }
        if (classe.equals(boolean.class)) {
            return true;
        } else if (classe.equals(byte.class)) {
            return true;
        } else if (classe.equals(char.class)) {
            return true;
        } else if (classe.equals(double.class)) {
            return true;
        } else if (classe.equals(float.class)) {
            return true;
        } else if (classe.equals(int.class)) {
            return true;
        } else if (classe.equals(long.class)) {
            return true;
        } else if (classe.equals(short.class)) {
            return true;
        } else if (classe.equals(void.class)) {
            return true;
        } else {
            return false;
        }
    }

    public static Class pegaDaPrimitiva(Class classe) {
        if (classe == null) {
            return null;
        }
        if (classe.equals(boolean.class)) {
            return Boolean.class;
        } else if (classe.equals(byte.class)) {
            return Byte.class;
        } else if (classe.equals(char.class)) {
            return Character.class;
        } else if (classe.equals(double.class)) {
            return Double.class;
        } else if (classe.equals(float.class)) {
            return Float.class;
        } else if (classe.equals(int.class)) {
            return Integer.class;
        } else if (classe.equals(long.class)) {
            return Long.class;
        } else if (classe.equals(short.class)) {
            return Short.class;
        } else if (classe.equals(void.class)) {
            return Void.class;
        } else {
            return classe;
        }
    }

    public static Class pegaPrimitiva(Class classe) {
        if (classe == null) {
            return null;
        }
        if (classe.equals(Boolean.class)) {
            return boolean.class;
        } else if (classe.equals(Byte.class)) {
            return byte.class;
        } else if (classe.equals(Character.class)) {
            return char.class;
        } else if (classe.equals(Double.class)) {
            return double.class;
        } else if (classe.equals(Float.class)) {
            return float.class;
        } else if (classe.equals(Integer.class)) {
            return int.class;
        } else if (classe.equals(Long.class)) {
            return long.class;
        } else if (classe.equals(Short.class)) {
            return short.class;
        } else if (classe.equals(Void.class)) {
            return void.class;
        } else {
            return classe;
        }
    }

    public static Boolean compara(Method oMetodo, String comNome, List<Object> eParametros) {
        Boolean retorno = true;
        if (comNome.equals(oMetodo.getName())) {
            if (oMetodo.getParameterCount() == eParametros.size()) {
                Class[] pars = oMetodo.getParameterTypes();
                for (int ip = 0; ip < pars.length; ip++) {
                    Class cls1 = pegaDaPrimitiva(eParametros.get(ip).getClass());
                    Class cls2 = pegaDaPrimitiva(pars[ip]);
                    if (!(cls1.equals(cls2))) {
                        retorno = false;
                        break;
                    }
                }
            } else {
                retorno = false;
            }
        } else {
            retorno = false;
        }
        return retorno;
    }

    public static <T> T novo(Class<? extends T> daClasse, Object[] comParametros) throws Exception {
        T retorno = null;
        if (daClasse != null) {
            if (comParametros != null) {
                Class[] parsClass = new Class[comParametros.length];
                for (int ip = 0; ip < comParametros.length; ip++) {
                    if (comParametros[ip] == null) {
                        parsClass[ip] = null;
                    } else {
                        parsClass[ip] = comParametros[ip].getClass();
                    }
                }
                retorno = daClasse.getConstructor(parsClass).newInstance(comParametros);
            } else {
                retorno = daClasse.newInstance();
            }
        }
        return retorno;
    }

    public static void chama(JDialog naJanela, String oMetodo, Object... comParametros) {
        chama(naJanela.getComponents(), oMetodo, comParametros);
        chama((Object) naJanela, oMetodo, comParametros);
    }

    public static void chama(JFrame naJanela, String oMetodo, Object... comParametros) {
        chama(naJanela.getComponents(), oMetodo, comParametros);
        chama((Object) naJanela, oMetodo, comParametros);
    }

    public static void chama(Component[] nosComponentes, String oMetodo, Object... comParametros) {
        if (nosComponentes != null) {
            for (Component cmp : nosComponentes) {
                chama(cmp, oMetodo, comParametros);
                if (cmp instanceof JComponent) {
                    chama(((JComponent) cmp).getComponents(), oMetodo, comParametros);
                }
            }
        }
    }

    public static Object chama(Object doObjeto, String oMetodo, Object... comParametros) {
        Class[] parsClass = null;
        if (comParametros != null) {
            parsClass = new Class[comParametros.length];
            for (int ip = 0; ip < comParametros.length; ip++) {
                if (comParametros[ip] == null) {
                    parsClass[ip] = null;
                } else {
                    parsClass[ip] = comParametros[ip].getClass();
                }
            }
        }
        Class daClasse = doObjeto.getClass();
        try {
            Method metodo = daClasse.getMethod(oMetodo, parsClass);
            return metodo.invoke(doObjeto, comParametros);
        } catch (Exception ex) {
            return null;
        }
    }

    public static Object chama(Class daClasse, String oMetodo, Object... comParametros) {
        Class[] parsClass = null;
        if (comParametros != null) {
            parsClass = new Class[comParametros.length];
            for (int ip = 0; ip < comParametros.length; ip++) {
                if (comParametros[ip] == null) {
                    parsClass[ip] = null;
                } else {
                    parsClass[ip] = comParametros[ip].getClass();
                }
            }
        }
        try {
            Method metodo = daClasse.getMethod(oMetodo, parsClass);
            return metodo.invoke(null, comParametros);
        } catch (Exception ex) {
            return null;
        }
    }

    public static Object manda(Object doObjeto, String oMetodo, Object... comParametros) throws Exception {
        Class[] parsClass = null;
        if (comParametros != null) {
            parsClass = new Class[comParametros.length];
            for (int ip = 0; ip < comParametros.length; ip++) {
                if (comParametros[ip] == null) {
                    parsClass[ip] = null;
                } else {
                    parsClass[ip] = comParametros[ip].getClass();
                }
            }
        }
        Class daClasse = doObjeto.getClass();
        Method metodo = daClasse.getMethod(oMetodo, parsClass);
        return metodo.invoke(doObjeto, comParametros);
    }

    public static Object manda(Class daClasse, String oMetodo, Object... comParametros) throws Exception {
        Class[] parsClass = null;
        if (comParametros != null) {
            parsClass = new Class[comParametros.length];
            for (int ip = 0; ip < comParametros.length; ip++) {
                if (comParametros[ip] == null) {
                    parsClass[ip] = null;
                } else {
                    parsClass[ip] = comParametros[ip].getClass();
                }
            }
        }
        Method metodo = daClasse.getMethod(oMetodo, parsClass);
        return metodo.invoke(null, comParametros);
    }

    public static <T> T pega(Class<T> daClasse, Object doValor, T comPadrao) {
        try {
            return pega(daClasse, doValor);
        } catch (Exception ex) {
            return comPadrao;
        }
    }

    public static <T> T pega(Class<T> daClasse, Object doValor) throws Exception {
        if (daClasse.isInstance(doValor)) {
            return (T) doValor;
        } else {
            if (doValor instanceof Vlr) {
                return ((Vlr) doValor).pg(daClasse);
            } else {
                try {
                    return daClasse.getConstructor(doValor.getClass()).newInstance(doValor);
                } catch (Exception ex1) {
                    try {
                        if (ehPrimitiva(daClasse)) {
                            return daClasse.getConstructor(UtlBin.pegaDaPrimitiva(doValor.getClass())).newInstance(doValor);
                        } else {
                            return daClasse.getConstructor(UtlBin.pegaPrimitiva(doValor.getClass())).newInstance(doValor);
                        }
                    } catch (Exception ex2) {
                        return daClasse.getConstructor(Object.class).newInstance(doValor);
                    }
                }
            }
        }
    }

    public static String descreveClasse(Object doObjeto) {
        if (doObjeto == null) {
            return Marcados.marca("java.lang.Null", "classe");
        } else {
            return Marcados.marca(doObjeto.getClass().getCanonicalName(), "classe");
        }
    }

    public static String descreveFonte(Object doObjeto) {
        if (doObjeto == null) {
            return Marcados.marca("null", "fonte");
        } else {
            String fonte;
            Class classe = doObjeto.getClass();
            try {
                Method metodo = classe.getMethod("descreve");
                fonte = (String) metodo.invoke(doObjeto);
            } catch (Exception ex) {
                fonte = doObjeto.toString();
            }
            return Marcados.marca(fonte, "fonte");
        }
    }

    public static String descreve(Object oObjeto) {
        if (UtlLis.eh(oObjeto)) {
            return UtlLis.descreve(UtlLis.pega(oObjeto));
        } else {
            return descreveClasse(oObjeto) + descreveFonte(oObjeto);
        }
    }

    public static Boolean ehDescritoClasse(String osCaracteres) {
        return Marcados.tem(osCaracteres, "classe");
    }

    public static Boolean ehDescritoFonte(String osCaracteres) {
        return Marcados.tem(osCaracteres, "fonte");
    }

    public static Boolean ehDescrito(String osCaracteres) {
        return Marcados.tem(osCaracteres, "classe") && Marcados.tem(osCaracteres, "fonte");
    }

    public static Object descrito(String nosCaracteres) throws Exception {
        if (nosCaracteres == null) {
            return null;
        }
        nosCaracteres = nosCaracteres.trim();
        if (!nosCaracteres.equals("<classe>java.lang.Null</classe><fonte>null</fonte>")) {
            if (nosCaracteres.startsWith("<lista>") && nosCaracteres.endsWith("</lista>")) {
                return UtlLis.descrito(nosCaracteres);
            } else {
                String classeNome = Marcados.marcado(nosCaracteres, "classe").trim();
                String classeFonte = Marcados.marcado(nosCaracteres, "fonte");
                Class classe = Class.forName(classeNome);
                if (classeFonte.startsWith("<membros>") && classeFonte.endsWith("</membros>")) {
                    return mudaMembros(classe.newInstance(), classeFonte);
                } else {
                    Method metodo;
                    try {
                        metodo = classe.getMethod("descrito", String.class);
                    } catch (Exception ex) {
                        Constructor construtor = classe.getConstructor(new Class[]{String.class});
                        return construtor.newInstance(classeFonte);
                    }
                    return metodo.invoke(null, classeFonte);
                }
            }
        }
        return null;
    }

    public static String descreveMembros(Object doValor) {
        if (doValor == null) {
            return null;
        }
        StringBuilder retorno = new StringBuilder();
        retorno.append("<membros>");
        retorno.append(UtlTex.quebra());
        Class cls = doValor.getClass();
        for (Field fld : cls.getDeclaredFields()) {
            boolean trc = false;
            if (!fld.isAccessible()) {
                fld.setAccessible(true);
                trc = true;
            }
            StringBuilder membro = null;
            try {
                membro = new StringBuilder();
                membro.append("<membro><nome>");
                membro.append(fld.getName());
                membro.append("</nome><valor>");
                membro.append(descreve(fld.get(doValor)));
                membro.append("</valor></membro>");
                membro.append(UtlTex.quebra());
            } catch (Exception ex) {
                membro = null;
            } finally {
                if (trc) {
                    fld.setAccessible(false);
                }
            }
            if (membro != null) {
                retorno.append(membro.toString());
            }
        }
        retorno.append("</membros>");
        return retorno.toString();
    }

    public static <T> T mudaMembros(T noValor, String descritos) throws Exception {
        if (noValor == null) {
            return null;
        }
        if (descritos == null) {
            return noValor;
        }
        Class cls = noValor.getClass();
        String membros = Marcados.marcado(descritos, "membros");
        for (String membro : Marcados.marcados(membros, "membro")) {
            String nome = Marcados.marcado(membro, "nome");
            String valor = Marcados.marcado(membro, "valor");
            Field fld = cls.getDeclaredField(nome);
            boolean trc = false;
            if (!fld.isAccessible()) {
                fld.setAccessible(true);
                trc = true;
            }
            try {
                fld.set(noValor, descrito(valor));
            } finally {
                if (trc) {
                    fld.setAccessible(false);
                }
            }

        }
        return noValor;
    }

    public static String mostraMembros(Object doValor) {
        if (doValor == null) {
            return null;
        }
        StringBuilder retorno = new StringBuilder();
        boolean primeiro = true;
        for (Field fld : doValor.getClass().getDeclaredFields()) {
            if (!primeiro) {
                retorno.append("; ");
            }
            retorno.append(fld.getName());
            retorno.append(" = ");
            boolean trc = false;
            if (!fld.isAccessible()) {
                fld.setAccessible(true);
                trc = true;
            }
            try {
                Object val = fld.get(doValor);
                if (val == null) {
                    retorno.append("null");
                } else {
                    retorno.append("'");
                    retorno.append(UtlCrs.pega(val));
                    retorno.append("'");
                }
                primeiro = false;
            } catch (Exception ex) {
            } finally {
                if (trc) {
                    fld.setAccessible(false);
                }
            }
        }
        return retorno.toString();
    }

    public static String pegaHierarquia(Class daClasse) {
        return pegaHierarquia(daClasse, "");
    }

    public static String pegaHierarquia(Class daClasse, String comComeco) {
        String retorno = comComeco;
        if (retorno == null) {
            retorno = "";
        }
        String nome = daClasse.getCanonicalName();
        retorno = retorno + nome + "\n";
        if (daClasse.getSuperclass() != null) {
            retorno = pegaHierarquia(daClasse.getSuperclass(), retorno);
        }
        return retorno;
    }

    public static String pegaOrigem() {
        String retorno = "Processo: " + Thread.currentThread().getName() + UtlTex.quebra();
        retorno += "Origem:";
        StackTraceElement[] lst = Thread.currentThread().getStackTrace();
        for (int ist = lst.length - 1; ist >= 0; ist--) {
            StackTraceElement ste = lst[ist];
            if (ste.getClassName().startsWith("pin.")) {
                retorno = retorno + UtlTex.quebra() + ste.getClassName() + "." + ste.getMethodName() + " - " + ste.getFileName() + " (" + ste.getLineNumber() + ")";
            }
        }
        return retorno;
    }

    public static String pegaOrigem(Throwable doErro) {
        String retorno = "Processo: " + Thread.currentThread().getName() + UtlTex.quebra();
        retorno += "Origem:";
        StackTraceElement[] lst = doErro.getStackTrace();
        for (int ist = lst.length - 1; ist >= 0; ist--) {
            StackTraceElement ste = lst[ist];
            if (ste.getClassName().startsWith("pin.")) {
                retorno = retorno + UtlTex.quebra() + ste.getClassName() + "." + ste.getMethodName() + " - " + ste.getFileName() + " (" + ste.getLineNumber() + ")";
            }
        }
        return retorno;
    }

    public static void esperaSegundo() {
        esperaSegundos(1);
    }

    public static void esperaSegundos(Integer segundos) {
        try {
            Thread.currentThread().sleep(segundos * 1000);
        } catch (InterruptedException ex) {
        }
    }

    public static void esperaMilis(Integer miliSegundos) {
        try {
            Thread.currentThread().sleep(miliSegundos);
        } catch (InterruptedException ex) {
        }
    }

    public static String pPlataformaOS() {
        if (ehWin()) {
            return "win";
        } else if (ehNix()) {
            return "nix";
        } else if (ehMac()) {
            return "max";
        } else if (ehSol()) {
            return "sol";
        } else {
            return null;
        }
    }

    public static File pPlataformaOSDir(Class daClasse) throws Exception {
        File retorno = UtlArq.pEndereco(daClasse);
        if (retorno.isDirectory()) {
            retorno = retorno.getParentFile();
            retorno = retorno.getParentFile();
            retorno = new File(retorno, "rlib");
        } else {
            retorno = retorno.getParentFile();
            if (!retorno.getName().equals("lib")) {
                retorno = new File(retorno, "lib");
            }
        }
        return retorno;
    }

    public static String pJavaVersao() {
        return System.getProperty("java.version");
    }

    public static Boolean ehWin() {
        String OS = System.getProperty("os.name").toLowerCase();
        return (OS.contains("win"));
    }

    public static Boolean ehNix() {
        String OS = System.getProperty("os.name").toLowerCase();
        return (OS.contains("nix") || OS.contains("nux") || OS.indexOf("aix") > 0);
    }

    public static Boolean ehMac() {
        String OS = System.getProperty("os.name").toLowerCase();
        return (OS.contains("mac"));
    }

    public static Boolean ehSol() {
        String OS = System.getProperty("os.name").toLowerCase();
        return (OS.contains("sunos"));
    }

    public static Boolean compara(Parameter[] osParametros, Object[] comValores) {
        Boolean retorno = true;
        try {
            for (int i1 = 0; i1 < osParametros.length; i1++) {
                Class parType = osParametros[i1].getType();
                if (!parType.isInstance(comValores[i1])) {
                    retorno = false;
                    break;
                }
            }
        } catch (Exception ex) {
            retorno = false;
        }
        return retorno;
    }

    public static void botaPacotes(List<String> naLista) throws Exception {
        String adicionaisCrs = System.getProperty("java.class.path");
        String separador = System.getProperty("path.separator");
        String[] adicionaisMat = adicionaisCrs.split("\\" + separador);
        if (adicionaisMat != null) {
            for (String adicional : adicionaisMat) {
                botaPacotes(adicional, naLista);
            }
        }
    }

    public static void botaPacotes(String daOrigem, List<String> naLista) throws Exception {
        if (daOrigem != null) {
            if (!daOrigem.contains("://")) {
                File caminho = new File(daOrigem);
                if (caminho.exists()) {
                    if (daOrigem.toLowerCase().endsWith(".jar")) {
                        botaPacotes(new JarFile(caminho), naLista);
                    } else {
                        botaPacotes(caminho, naLista);
                    }
                }
            } else {
                //Exemplo de origem: "jar:http://www.caminho.com/examples/meujar.jar"
                URL url = new URL(daOrigem);
                JarURLConnection conn = (JarURLConnection) url.openConnection();
                botaPacotes(conn.getJarFile(), naLista);
            }
        }
    }

    public static void botaPacotes(JarFile doArquivo, List<String> naLista) throws Exception {
        Manifest mnf = doArquivo.getManifest();
        if (mnf != null) {
            Attributes mainAttributes = mnf.getMainAttributes();
            if (mainAttributes != null) {
                String clpth = mainAttributes.getValue("Class-Path");
                if (clpth != null) {
                    String[] clps = clpth.split(" ");
                    if (clps.length > 0) {
                        String pai = UtlArq.pegaPai(doArquivo.getName());
                        for (String inc : clps) {
                            if (inc.endsWith(".jar")) {
                                inc = UtlArq.garanteSeparador(inc);
                                File incArq = new File(UtlArq.soma(pai, inc));
                                if (incArq.exists()) {
                                    JarFile incJar = new JarFile(incArq);
                                    botaPacotes(incJar, naLista);
                                }
                            }
                        }
                    }
                }
            }
        }
        Enumeration<JarEntry> ents = doArquivo.entries();
        while (ents.hasMoreElements()) {
            JarEntry ent = ents.nextElement();
            if (ent.isDirectory()) {
                if (!ent.getName().contains("META-INF")) {
                    String nome = ent.getName();
                    nome = nome.substring(0, nome.length() - 1);
                    nome = UtlCrs.troca(nome, "/", ".");
                    if (!naLista.contains(nome)) {
                        naLista.add(nome);
                    }
                }
            }
        }
    }

    public static void botaPacotes(File doCaminho, List<String> naLista) throws Exception {
        botaPacotes(doCaminho, "", naLista);
    }

    public static void botaPacotes(File doCaminho, String comRaiz, List<String> naLista) throws Exception {
        for (File itn : doCaminho.listFiles()) {
            if (itn.isDirectory()) {
                String pct = UtlCrs.soma(comRaiz, ".", itn.getName());
                if (!naLista.contains(pct)) {
                    naLista.add(pct);
                }
                botaPacotes(itn, pct, naLista);
            }
        }
    }

    public static void botaClasses(List<String> naLista) throws Exception {
        String adicionaisCrs = System.getProperty("java.class.path");
        String separador = System.getProperty("path.separator");
        String[] adicionaisMat = adicionaisCrs.split("\\" + separador);
        if (adicionaisMat != null) {
            for (String adicional : adicionaisMat) {
                botaClasses(adicional, naLista);
            }
        }
    }

    public static void botaClasses(String daOrigem, List<String> naLista) throws Exception {
        if (daOrigem != null) {
            if (!daOrigem.contains("://")) {
                File caminho = new File(daOrigem);
                if (caminho.exists()) {
                    if (daOrigem.toLowerCase().endsWith(".jar")) {
                        botaClasses(new JarFile(caminho), naLista);
                    } else {
                        botaClasses(caminho, naLista);
                    }
                }
            } else {
                //Exemplo de origem: "jar:http://www.caminho.com/examples/meujar.jar"
                URL url = new URL(daOrigem);
                JarURLConnection conn = (JarURLConnection) url.openConnection();
                botaClasses(conn.getJarFile(), naLista);
            }
        }
    }

    public static void botaClasses(JarFile doArquivo, List<String> naLista) throws Exception {
        Manifest mnf = doArquivo.getManifest();
        if (mnf != null) {
            Attributes mainAttributes = mnf.getMainAttributes();
            if (mainAttributes != null) {
                String clpth = mainAttributes.getValue("Class-Path");
                if (clpth != null) {
                    String[] clps = clpth.split(" ");
                    if (clps.length > 0) {
                        String pai = UtlArq.pegaPai(doArquivo.getName());
                        for (String inc : clps) {
                            if (inc.endsWith(".jar")) {
                                inc = UtlArq.garanteSeparador(inc);
                                File incArq = new File(UtlArq.soma(pai, inc));
                                if (incArq.exists()) {
                                    JarFile incJar = new JarFile(incArq);
                                    botaClasses(incJar, naLista);
                                }
                            }
                        }
                    }
                }
            }
        }
        Enumeration<JarEntry> ents = doArquivo.entries();
        while (ents.hasMoreElements()) {
            JarEntry ent = ents.nextElement();
            if (!ent.isDirectory()) {
                if (!ent.getName().contains("META-INF") && ent.getName().endsWith(".class")) {
                    String nome = ent.getName();
                    nome = nome.substring(0, nome.length() - 6);
                    nome = UtlCrs.troca(nome, "/", ".");
                    if (!naLista.contains(nome)) {
                        naLista.add(nome);
                    }
                }
            }
        }
    }

    public static void botaClasses(File doCaminho, List<String> naLista) throws Exception {
        botaClasses(doCaminho, "", naLista);
    }

    public static void botaClasses(File doCaminho, String comRaiz, List<String> naLista) throws Exception {
        for (File itn : doCaminho.listFiles()) {
            if (itn.isDirectory()) {
                String pct = UtlCrs.soma(comRaiz, ".", itn.getName());
                botaClasses(itn, pct, naLista);
            } else {
                if (itn.getName().endsWith(".class")) {
                    String nome = itn.getName();
                    nome = nome.substring(0, nome.length() - 6);
                    nome = UtlCrs.soma(comRaiz, ".", nome);
                    if (!naLista.contains(nome)) {
                        naLista.add(nome);
                    }
                }
            }
        }
    }

    public static Class<?> pClasseGenerica(Class daClasse) {
        try {
            return (Class<?>) ((ParameterizedType) daClasse.getGenericSuperclass()).getActualTypeArguments()[0];
        } catch (Exception ex) {
            return daClasse;
        }
    }

    private static final List<Par<Integer, Object>> idsBin = new ArrayList<>();

    public static Boolean temId(Integer doId) {
        if (doId == null) {
            return false;
        }
        synchronized (idsBin) {
            for (Par<Integer, Object> ent : idsBin) {
                if (Objects.equals(doId, ent.pLado1())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Boolean temVal(Object oValor) {
        if (oValor == null) {
            return false;
        }
        synchronized (idsBin) {
            for (Par<Integer, Object> ent : idsBin) {
                if (Objects.equals(oValor, ent.pLado2())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Integer pegaId(Object doValor) {
        if (doValor == null) {
            return null;
        }
        synchronized (idsBin) {
            for (Par<Integer, Object> ent : idsBin) {
                if (Objects.equals(doValor, ent.pLado2())) {
                    return ent.pLado1();
                }
            }
        }
        return null;
    }

    public static Object pegaVal(Integer doId) {
        if (doId == null) {
            return null;
        }
        synchronized (idsBin) {
            for (Par<Integer, Object> ent : idsBin) {
                if (Objects.equals(doId, ent.pLado1())) {
                    return ent.pLado2();
                }
            }
        }
        return null;
    }

    public static Integer novoId(Object paraValor) {
        return novoId(paraValor, null);
    }

    public static Integer novoId(Object paraValor, Integer comSugestao) {
        if (paraValor == null) {
            return null;
        }
        synchronized (idsBin) {
            Integer novo = comSugestao;
            if (novo == null) {
                novo = UtlAle.pegaInt();
            }
            while (true) {
                boolean achou = false;
                for (Par<Integer, Object> ent : idsBin) {
                    if (Objects.equals(novo, ent.pLado1())) {
                        achou = true;
                    }
                    if (Objects.equals(paraValor, ent.pLado2())) {
                        return ent.pLado1();
                    }
                }
                if (achou) {
                    novo = UtlAle.pegaInt();
                } else {
                    break;
                }

            }
            idsBin.add(new Par(novo, paraValor));
            return novo;
        }
    }

    public static void fechaId(Integer oId) {
        if (oId == null) {
            return;
        }
        synchronized (idsBin) {
            for (Par<Integer, Object> ent : idsBin) {
                if (Objects.equals(oId, ent.pLado1())) {
                    idsBin.remove(ent);
                    return;
                }
            }
        }
    }

    public static Integer mudaId(Object doValor, Integer de, Integer para) {
        fechaId(de);
        return novoId(doValor, para);
    }
}
