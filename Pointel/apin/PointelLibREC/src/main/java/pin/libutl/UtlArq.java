package pin.libutl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import pin.libbas.Globais;
import pin.libbas.Retornos;
import pin.libvlr.VArq;
import pin.libvlr.Vlr;

public class UtlArq {

    public static Boolean eh(Object oValor) {
        Boolean retorno = false;
        if (oValor instanceof byte[]) {
            retorno = true;
        } else if (oValor instanceof Byte[]) {
            retorno = true;
        } else if (oValor instanceof File) {
            retorno = true;
        } else if (oValor instanceof URL) {
            retorno = true;
        } else if (oValor instanceof VArq) {
            retorno = true;
        }
        return retorno;
    }

    public static Boolean eh(Class daClasse) {
        Boolean retorno = false;
        if (daClasse != null) {
            if (daClasse.isArray()) {
                if (Byte.class.isAssignableFrom(UtlBin.pegaDaPrimitiva(daClasse.getComponentType()))) {
                    retorno = true;
                }
            } else if (File.class.isAssignableFrom(UtlBin.pegaDaPrimitiva(daClasse))) {
                retorno = true;
            } else if (URL.class.isAssignableFrom(UtlBin.pegaDaPrimitiva(daClasse))) {
                retorno = true;
            } else if (VArq.class.isAssignableFrom(daClasse)) {
                retorno = true;
            }
        }
        return retorno;
    }

    public static byte[] pega(Object doValor) {
        return pega(doValor, null);
    }

    public static byte[] pega(Object doValor, byte[] comPadrao) {
        byte[] retorno = comPadrao;
        if (doValor instanceof VArq) {
            doValor = ((VArq) doValor).pgArq();
        } else if (doValor instanceof Vlr) {
            doValor = ((Vlr) doValor).pg();
        }
        if (doValor != null) {
            if (doValor instanceof byte[]) {
                retorno = (byte[]) doValor;
            } else if (doValor instanceof File) {
                try {
                    File doArquivo = (File) doValor;
                    if (!doArquivo.isDirectory() && doArquivo.exists()) {
                        retorno = Files.readAllBytes(doArquivo.toPath());
                    }
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            } else if (doValor instanceof URL) {
                try {
                    retorno = Files.readAllBytes(Paths.get(((URL) doValor).toURI()));
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            } else if (doValor instanceof String) {
                retorno = pega((String) doValor, comPadrao);
            }
        }
        return retorno;
    }

    public static byte[] pega(String dosCaracteres) {
        return pega(dosCaracteres, null);
    }

    public static byte[] pega(String dosCaracteres, byte[] comPadrao) {
        byte[] retorno = comPadrao;
        if (dosCaracteres != null) {
            try {
                retorno = dosCaracteres.getBytes("UTF8");
            } catch (Exception ex) {
                retorno = dosCaracteres.getBytes();
            }
        }
        return retorno;
    }

    public static byte[] pega(InputStream daCorrente) throws Exception {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[0xFFFF];
        for (int len = daCorrente.read(buffer); len != -1; len = daCorrente.read(buffer)) {
            os.write(buffer, 0, len);
        }
        return os.toByteArray();
    }

    public static byte[] pegaSQL(Object doValor) {
        return pegaSQL(doValor, null);
    }

    public static byte[] pegaSQL(Object doValor, byte[] comPadrao) {
        return pega(doValor, comPadrao);
    }

    public static byte[] pegaB64(String dosCaracteres) {
        return pegaB64(dosCaracteres, null);
    }

    public static byte[] pegaB64(String dosCaracteres, byte[] comPadrao) {
        byte[] retorno = comPadrao;
        if (dosCaracteres != null) {
            retorno = Base64.getDecoder().decode(dosCaracteres);
        }
        return retorno;
    }

    public static File pegaCam(Object doValor) {
        return pegaCam(doValor, null);
    }

    public static File pegaCam(Object doValor, File comPadrao) {
        File retorno = comPadrao;
        if (doValor != null) {
            if (doValor instanceof File) {
                retorno = (File) doValor;
            } else if (doValor instanceof VArq) {
                retorno = ((VArq) doValor).pgArqCam(comPadrao);
            } else {
                try {
                    retorno = new File(UtlCrs.pega(doValor));
                } catch (Exception ex) {
                    Utl.registra(ex, false);
                    return comPadrao;
                }
            }
        }
        return retorno;
    }

    public static String pegaMD5(byte[] doValor) throws Exception {
        if (doValor == null) {
            return null;
        }
        if (doValor.length == 0) {
            return "";
        }
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(doValor);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }

    public static Boolean ehIgual(Object oValor, Object eValor) {
        return Arrays.equals(pega(oValor), pega(eValor));
    }

    public static boolean ehMaior(Object oValor, Object queValor) {
        if (oValor != null && queValor != null) {
            return pega(oValor).length > pega(queValor).length;
        } else if (oValor == null & queValor == null) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean ehMenor(Object oValor, Object queValor) {
        if (oValor != null && queValor != null) {
            return pega(oValor).length < pega(queValor).length;
        } else if (oValor == null & queValor == null) {
            return true;
        } else {
            return false;
        }
    }

    public static Boolean ehMaiorOuIgual(Object oValor, Object queValor) {
        return ehMaior(oValor, queValor) || ehIgual(oValor, queValor);
    }

    public static Boolean ehMenorOuIgual(Object oValor, Object queValor) {
        return ehMenor(oValor, queValor) || ehIgual(oValor, queValor);
    }

    public static String formata(byte[] oValor) {
        return formata(oValor, null);
    }

    public static String formata(byte[] oValor, String comPadrao) {
        String retorno = comPadrao;
        if (oValor != null) {
            try {
                retorno = new String(oValor, "UTF8");
            } catch (Exception ex) {
                retorno = new String(oValor);
            }
        }
        return retorno;
    }

    public static String formata(File oEndereco) {
        return formata(oEndereco, null);
    }

    public static String formata(File oEndereco, String comPadrao) {
        String retorno = comPadrao;
        if (oEndereco != null) {
            retorno = oEndereco.getAbsolutePath();
        }
        return retorno;
    }

    public static String formataB64(byte[] oValor) {
        return formataB64(oValor, null);
    }

    public static String formataB64(byte[] oValor, String comPadrao) {
        String retorno = comPadrao;
        if (oValor != null) {
            retorno = Base64.getEncoder().encodeToString(oValor);
        }
        return retorno;
    }

    public static String formataSQL(byte[] oValor) {
        return formataSQL(oValor, null);
    }

    public static String formataSQL(byte[] oValor, String comPadrao) {
        String retorno = comPadrao;
        if (oValor != null) {
            retorno = "decode('" + UtlArq.formataB64(oValor) + "', 'base64')";
        }
        return retorno;
    }

    public static String formataTamanho(byte[] doValor) {
        return formataTamanho(doValor, null);
    }

    public static String formataTamanho(byte[] doValor, String comPadrao) {
        String retorno = comPadrao;
        if (doValor != null) {
            retorno = formataTamanho(new Long(doValor.length), comPadrao);
        }
        return retorno;
    }

    public static String formataTamanho(Long doTamanho) {
        return formataTamanho(doTamanho, null);
    }

    public static String formataTamanho(Long doTamanho, String comPadrao) {
        String retorno = comPadrao;
        if (doTamanho != null) {
            long tam = doTamanho;
            retorno = "b";
            if (tam > 1000000000) {
                retorno = "gb";
                tam = tam / 1000000000;
            } else if (tam > 1000000) {
                retorno = "mb";
                tam = tam / 1000000;
            } else if (tam > 1000) {
                retorno = "kb";
                tam = tam / 1000;
            }
            retorno = tam + " " + retorno;
        }
        return retorno;
    }

    public static File pEndereco() throws Exception {
        return pEndereco(UtlArq.class);
    }

    public static File pEndereco(Class daClasse) throws Exception {
        return new File(daClasse.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
    }

    public static String soma(String noEndereco, String oFilho) {
        String retorno = oFilho;
        if (noEndereco != null) {
            if (noEndereco.endsWith(File.separator) || oFilho.startsWith(File.separator)) {
                retorno = noEndereco + oFilho;
            } else {
                retorno = noEndereco + File.separator + oFilho;
            }
        }
        return retorno;
    }

    public static String soma(String noEndereco, String... osFilhos) {
        String retorno = noEndereco;
        if (osFilhos != null) {
            for (String filho : osFilhos) {
                retorno = soma(retorno, filho);
            }
        }
        return retorno;
    }

    public static File soma(File noCaminho, String... osFilhos) {
        File retorno = noCaminho;
        if (retorno != null) {
            if (osFilhos != null) {
                for (String filho : osFilhos) {
                    retorno = new File(retorno, filho);
                }
            }
        }
        return retorno;
    }

    public static Boolean criaHierarquia(File doCaminho) {
        return criaHierarquia(doCaminho, true);
    }

    public static Boolean criaHierarquia(File doCaminho, Boolean inclusiveUltimo) {
        List<File> hierarquia = pegaHierarquia(doCaminho);
        if (hierarquia != null) {
            for (int ih = 0; ih < hierarquia.size() - (inclusiveUltimo ? 0 : 1); ih++) {
                File arq = hierarquia.get(ih);
                arq.mkdir();
            }
        }
        return doCaminho.exists();
    }

    public static List<File> pegaHierarquia(File doDiretorio) {
        List<File> hierarquia = new ArrayList<>();
        pegaHierarquia(doDiretorio, hierarquia);
        return hierarquia;
    }

    public static void pegaHierarquia(File doDiretorio, List<File> naLista) {
        if (doDiretorio != null) {
            if (naLista != null) {
                naLista.add(0, doDiretorio);
                pegaHierarquia(doDiretorio.getParentFile(), naLista);
            }
        }
    }

    public static File pegaPai(File doEndereco, String comNome) {
        File retorno = null;
        if (doEndereco != null) {
            File atual = doEndereco.getParentFile();
            while (!comNome.equals(atual.getName())) {
                atual = atual.getParentFile();
                if (atual == null) {
                    break;
                }
            }
            retorno = atual;
        }
        return retorno;
    }

    public static String pegaPai(String doEndereco) {
        if (doEndereco.contains(File.separator)) {
            return doEndereco.substring(0, doEndereco.lastIndexOf(File.separator));
        } else {
            return doEndereco;
        }
    }

    public static String pegaNome(String doEndereco) {
        if (doEndereco.contains(File.separator)) {
            return doEndereco.substring(doEndereco.lastIndexOf(File.separator) + 1);
        } else {
            return doEndereco;
        }
    }

    public static String pNomeBasico(File doEndereco) {
        return UtlArq.pNomeBasico(doEndereco.getName());
    }

    public static String pNomeBasico(String doEndereco) {
        String nome = pegaNome(doEndereco);
        if (nome.contains(".")) {
            return nome.substring(0, nome.lastIndexOf("."));
        } else {
            return nome;
        }
    }

    public static String incluiAoNomeBasico(String doEndereco, String osCaracteres) {
        String retorno = osCaracteres;
        if (doEndereco != null) {
            if (doEndereco.contains(".")) {
                retorno = doEndereco.substring(0, doEndereco.lastIndexOf(".")) + osCaracteres + doEndereco.substring(doEndereco.lastIndexOf("."));
            } else {
                retorno = doEndereco + osCaracteres;
            }
        }
        return retorno;
    }

    public static String pExtensao(String doEndereco) {
        String nome = pegaNome(doEndereco);
        if (nome.contains(".")) {
            return nome.substring(nome.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }

    public static String garanteSeparador(String doEndereco) {
        if ("\\".equals(File.separator)) {
            return UtlCrs.troca(doEndereco, "/", "\\");
        } else if ("/".equals(File.separator)) {
            return UtlCrs.troca(doEndereco, "\\", "/");
        } else {
            return doEndereco;
        }
    }

    public static Boolean ehArquivoDaExtencao(File oArquivo, String daExtensao) {
        Boolean retorno = true;
        if (daExtensao != null) {
            retorno = false;
            if (oArquivo.getName().toLowerCase().endsWith(daExtensao.toLowerCase())) {
                return true;
            }
        }
        return retorno;
    }

    public static Boolean ehArquivoDasExtencoes(File oArquivo, String[] dasExtensoes) {
        Boolean retorno = true;
        if (dasExtensoes != null) {
            retorno = false;
            for (String ext : dasExtensoes) {
                if (oArquivo.getName().toLowerCase().endsWith(ext.toLowerCase())) {
                    return true;
                }
            }
        }
        return retorno;
    }

    public static void procurarArquivos(File aRaiz, Boolean aNasSubPastas, Pattern aPadrao, ArrayList<File> aEncontrados) {
        if (aRaiz != null) {
            if (aRaiz.exists()) {
                if (aRaiz.isDirectory()) {
                    if (aNasSubPastas) {
                        for (File fl : aRaiz.listFiles()) {
                            procurarArquivos(fl, aNasSubPastas, aPadrao, aEncontrados);
                        }
                    }
                } else {
                    if (aPadrao.matcher(aRaiz.getName()).find()) {
                        aEncontrados.add(aRaiz);
                    }
                }
            }
        }
    }

    public static void procurarPastas(File aRaiz, Boolean aNasSubPastas, Pattern aPadrao, ArrayList<File> aEncontrados) {
        if (aRaiz != null) {
            if (aRaiz.exists()) {
                if (aRaiz.isDirectory()) {
                    if (aPadrao.matcher(aRaiz.getName()).find()) {
                        aEncontrados.add(aRaiz);
                    }
                    if (aNasSubPastas) {
                        for (File fl : aRaiz.listFiles()) {
                            procurarPastas(fl, aNasSubPastas, aPadrao, aEncontrados);
                        }
                    }
                }
            }
        }
    }

    public static void procurarAmbos(File aRaiz, Boolean aNasSubPastas, Pattern aPadrao, ArrayList<File> aEncontrados) {
        if (aRaiz != null) {
            if (aRaiz.exists()) {
                if (aPadrao.matcher(aRaiz.getName()).find()) {
                    aEncontrados.add(aRaiz);
                }
                if (aNasSubPastas) {
                    for (File fl : aRaiz.listFiles()) {
                        procurarAmbos(fl, aNasSubPastas, aPadrao, aEncontrados);
                    }
                }
            }
        }
    }

    public static List<File> pegaTudo(File daOrigem) {
        return pegaTudo(daOrigem, true, true);
    }

    public static List<File> pegaTudo(File daOrigem, Boolean incluirPastas) {
        return pegaTudo(daOrigem, incluirPastas, true);
    }

    public static List<File> pegaTudo(File daOrigem, Boolean incluirPastas, Boolean incluirEscondidos) {
        List<File> lista = new ArrayList<>();
        pegaTudo(daOrigem, lista, incluirPastas, incluirEscondidos);
        return lista;
    }

    public static void pegaTudo(File daOrigem, List<File> naLista, Boolean incluirPastas, Boolean incluirEscondidos) {
        if (daOrigem.isDirectory()) {
            if (incluirPastas) {
                if (!daOrigem.isHidden()) {
                    naLista.add(daOrigem);
                } else if (incluirEscondidos) {
                    naLista.add(daOrigem);
                }
            }
            if (!daOrigem.isHidden()) {
                for (File fl : daOrigem.listFiles()) {
                    pegaTudo(fl, naLista, incluirPastas, incluirEscondidos);
                }
            } else if (incluirEscondidos) {
                for (File fl : daOrigem.listFiles()) {
                    pegaTudo(fl, naLista, incluirPastas, incluirEscondidos);
                }
            }
        } else if (!daOrigem.isHidden()) {
            naLista.add(daOrigem);
        } else if (incluirEscondidos) {
            naLista.add(daOrigem);
        }
    }

    public static String listar(File aPastaOuArquivo) {
        String retorno = "";
        if (aPastaOuArquivo != null) {
            if (aPastaOuArquivo.exists()) {
                if (aPastaOuArquivo.isDirectory()) {
                    File[] fls = aPastaOuArquivo.listFiles();
                    if (fls != null) {
                        if (fls.length > 0) {
                            for (File fl : fls) {
                                if (fl.isDirectory()) {
                                    retorno += "D" + (fl.isHidden() ? "E" : "M") + " - " + fl.getAbsolutePath() + System.lineSeparator();
                                } else if (fl.isFile()) {
                                    retorno += "A" + (fl.isHidden() ? "E" : "M") + " - " + fl.getAbsolutePath() + System.lineSeparator();
                                }
                            }
                        }
                    }
                } else {
                    retorno = "A" + (aPastaOuArquivo.isHidden() ? "E" : "M") + " - " + aPastaOuArquivo.getAbsolutePath() + System.lineSeparator();
                }
            } else {
                retorno = "<inexistente>";
            }
        } else {
            retorno = "<null>";
        }
        return retorno;
    }

    public static int compara(File oArquivo, File queArquivo) {
        Integer retorno = 1;
        if (oArquivo != null && queArquivo != null) {
            Integer ps1 = UtlCrs.quantosTem(oArquivo.getAbsolutePath(), File.separator);
            Integer ps2 = UtlCrs.quantosTem(queArquivo.getAbsolutePath(), File.separator);
            if (ps1 < ps2) {
                retorno = -1;
            } else if (ps1 > ps2) {
                retorno = +1;
            } else {
                retorno = oArquivo.getAbsolutePath().compareTo(queArquivo.getAbsolutePath());
            }
        }
        return retorno;
    }

    public static List<File> ordena(List<File> aLista) {
        if (aLista != null) {
            Collections.sort(aLista, new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    return compara(o1, o2);
                }
            });
        } else {
            return null;
        }
        return aLista;
    }

    public static JFileChooser abridor(String comDescricao, String... eExtensao) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Selecionar");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setAcceptAllFileFilterUsed(eExtensao == null);
        FileFilter filter = new FileNameExtensionFilter(comDescricao, eExtensao);
        chooser.setFileFilter(filter);
        return chooser;
    }

    public static File abreArq() {
        return abreArq(null);
    }

    public static File abreArq(File selecionado) {
        return abreArq(selecionado, null);
    }

    public static File abreArq(String comDescricao, String... eExtensao) {
        return abreArq(null, comDescricao, eExtensao);
    }

    public static File abreArq(File selecionado, String comDescricao, String... eExtensao) {
        File retorno = null;
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Selecionar Arquivo");
        chooser.setMultiSelectionEnabled(false);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setAcceptAllFileFilterUsed(eExtensao == null);
        if (selecionado != null) {
            chooser.setSelectedFile(selecionado);
        }
        if (comDescricao != null & eExtensao != null) {
            FileFilter filter = new FileNameExtensionFilter(comDescricao, eExtensao);
            chooser.setFileFilter(filter);
        }
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            retorno = chooser.getSelectedFile();
        }
        return retorno;
    }

    public static File abreDir() {
        return abreDir(null);
    }

    public static File abreDir(File selecionado) {
        return abreDir(selecionado, null);
    }

    public static File abreDir(String comDescricao, String... eExtensao) {
        return abreDir(null, comDescricao, eExtensao);
    }

    public static File abreDir(File selecionado, String comDescricao, String... eExtensao) {
        File retorno = null;
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Selecionar Diretório");
        chooser.setMultiSelectionEnabled(false);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(eExtensao == null);
        if (selecionado != null) {
            chooser.setSelectedFile(selecionado);
        }
        if (comDescricao != null & eExtensao != null) {
            FileFilter filter = new FileNameExtensionFilter(comDescricao, eExtensao);
            chooser.setFileFilter(filter);
        }
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            retorno = chooser.getSelectedFile();
        }
        return retorno;
    }

    public static File abreArqOuDir() {
        return abreArqOuDir(null);
    }

    public static File abreArqOuDir(File selecionado) {
        return abreArqOuDir(selecionado, null);
    }

    public static File abreArqOuDir(String comDescricao, String... eExtensao) {
        return abreArqOuDir(null, comDescricao, eExtensao);
    }

    public static File abreArqOuDir(File selecionado, String comDescricao, String... eExtensao) {
        File retorno = null;
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Selecionar Arquivo Ou Diretório");
        chooser.setMultiSelectionEnabled(false);
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setAcceptAllFileFilterUsed(eExtensao == null);
        if (selecionado != null) {
            chooser.setSelectedFile(selecionado);
        }
        if (comDescricao != null & eExtensao != null) {
            FileFilter filter = new FileNameExtensionFilter(comDescricao, eExtensao);
            chooser.setFileFilter(filter);
        }
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            retorno = chooser.getSelectedFile();
        }
        return retorno;
    }

    public static File[] abreArqMult() {
        return abreArqMult(null);
    }

    public static File[] abreArqMult(File[] selecionados) {
        return abreArqMult(selecionados, null);
    }

    public static File[] abreArqMult(String comDescricao, String... eExtensao) {
        return abreArqMult(null, comDescricao, eExtensao);
    }

    public static File[] abreArqMult(File[] selecionados, String comDescricao, String... eExtensao) {
        File[] retorno = null;
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Selecionar Múltiplos Arquivos");
        chooser.setMultiSelectionEnabled(true);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setAcceptAllFileFilterUsed(eExtensao == null);
        if (selecionados != null) {
            chooser.setSelectedFiles(selecionados);
        }
        if (comDescricao != null & eExtensao != null) {
            FileFilter filter = new FileNameExtensionFilter(comDescricao, eExtensao);
            chooser.setFileFilter(filter);
        }
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            retorno = chooser.getSelectedFiles();
        }
        return retorno;
    }

    public static File[] abreDirMult() {
        return abreDirMult(null);
    }

    public static File[] abreDirMult(File[] selecionados) {
        return abreDirMult(selecionados, null);
    }

    public static File[] abreDirMult(String comDescricao, String... eExtensao) {
        return abreDirMult(null, comDescricao, eExtensao);
    }

    public static File[] abreDirMult(File[] selecionados, String comDescricao, String... eExtensao) {
        File[] retorno = null;
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Selecionar Múltiplos Diretórios");
        chooser.setMultiSelectionEnabled(true);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(eExtensao == null);
        if (selecionados != null) {
            chooser.setSelectedFiles(selecionados);
        }
        if (comDescricao != null & eExtensao != null) {
            FileFilter filter = new FileNameExtensionFilter(comDescricao, eExtensao);
            chooser.setFileFilter(filter);
        }
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            retorno = chooser.getSelectedFiles();
        }
        return retorno;
    }

    public static File[] abreArqOuDirMult() {
        return abreArqOuDirMult(null);
    }

    public static File[] abreArqOuDirMult(File[] selecionados) {
        return abreArqOuDirMult(selecionados, null);
    }

    public static File[] abreArqOuDirMult(String comDescricao, String... eExtensao) {
        return abreArqOuDirMult(null, comDescricao, eExtensao);
    }

    public static File[] abreArqOuDirMult(File[] selecionados, String comDescricao, String... eExtensao) {
        File[] retorno = null;
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Selecionar Múltiplos Arquivos ou Diretórios");
        chooser.setMultiSelectionEnabled(true);
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setAcceptAllFileFilterUsed(eExtensao == null);
        if (selecionados != null) {
            chooser.setSelectedFiles(selecionados);
        }
        if (comDescricao != null & eExtensao != null) {
            FileFilter filter = new FileNameExtensionFilter(comDescricao, eExtensao);
            chooser.setFileFilter(filter);
        }
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            retorno = chooser.getSelectedFiles();
        }
        return retorno;
    }

    public static File salvaArq() {
        return salvaArq(null);
    }

    public static File salvaArq(File selecionado) {
        return salvaArq(selecionado, null);
    }

    public static File salvaArq(String comDescricao, String... eExtensao) {
        return salvaArq(null, comDescricao, eExtensao);
    }

    public static File salvaArq(File selecionado, String comDescricao, String... eExtensao) {
        File retorno = null;
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Salvar Arquivo");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setAcceptAllFileFilterUsed(eExtensao == null);
        if (selecionado != null) {
            chooser.setSelectedFile(selecionado);
        }
        if (comDescricao != null & eExtensao != null) {
            FileFilter filter = new FileNameExtensionFilter(comDescricao, eExtensao);
            chooser.setFileFilter(filter);
        }
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            retorno = chooser.getSelectedFile();
            if (eExtensao != null) {
                if (eExtensao.length > 0) {
                    boolean deveIncluir = true;
                    if (retorno.getName().contains(".")) {
                        String ext = UtlArq.pExtensao(retorno.getName());
                        if (UtlLis.tem(ext, eExtensao)) {
                            deveIncluir = false;
                        }
                    }
                    if (deveIncluir) {
                        retorno = new File(retorno.getAbsolutePath() + "." + eExtensao[0]);
                    }
                }
            }
        }
        return retorno;
    }

    public static File salvaDir() {
        return salvaDir(null);
    }

    public static File salvaDir(File selecionado) {
        return salvaDir(selecionado, null);
    }

    public static File salvaDir(String comDescricao, String... eExtensao) {
        return salvaDir(null, comDescricao, eExtensao);
    }

    public static File salvaDir(File selecionado, String comDescricao, String... eExtensao) {
        File retorno = null;
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Salvar Diretório");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(eExtensao == null);
        if (selecionado != null) {
            chooser.setSelectedFile(selecionado);
        }
        if (comDescricao != null & eExtensao != null) {
            FileFilter filter = new FileNameExtensionFilter(comDescricao, eExtensao);
            chooser.setFileFilter(filter);
        }
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            retorno = chooser.getSelectedFile();
            String ext = pExtensao(retorno.getAbsolutePath());
            if (eExtensao != null) {
                if (!UtlLis.tem(ext, eExtensao)) {
                    retorno = new File(retorno.getAbsolutePath() + "." + eExtensao[0]);
                }
            }
        }
        return retorno;
    }

    public static File salvaArqOuDir() {
        return salvaArqOuDir(null);
    }

    public static File salvaArqOuDir(File selecionado) {
        return salvaArqOuDir(selecionado, null);
    }

    public static File salvaArqOuDir(String comDescricao, String... eExtensao) {
        return salvaArqOuDir(null, comDescricao, eExtensao);
    }

    public static File salvaArqOuDir(File selecionado, String comDescricao, String... eExtensao) {
        File retorno = null;
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Salvar Arquivo ou Diretório");
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setAcceptAllFileFilterUsed(eExtensao == null);
        if (selecionado != null) {
            chooser.setSelectedFile(selecionado);
        }
        if (comDescricao != null & eExtensao != null) {
            FileFilter filter = new FileNameExtensionFilter(comDescricao, eExtensao);
            chooser.setFileFilter(filter);
        }
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            retorno = chooser.getSelectedFile();
            String ext = pExtensao(retorno.getAbsolutePath());
            if (eExtensao != null) {
                if (!UtlLis.tem(ext, eExtensao)) {
                    retorno = new File(retorno.getAbsolutePath() + "." + eExtensao[0]);
                }
            }
        }
        return retorno;
    }

    public static File naoSobrescrever(File oDestino) {
        if (!oDestino.exists()) {
            return oDestino;
        } else {
            File retorno = null;
            int tent = 2;
            do {
                retorno = new File(incluiAoNomeBasico(oDestino.getAbsolutePath(), " (" + tent + ")"));
                tent++;
            } while (retorno.exists());
            return retorno;
        }
    }

    public static File pegaPointelJarDir() throws Exception {
        return pegaPointelJarDir(UtlArq.class, "apin");
    }

    public static File pegaPointelJarDir(Class daClasse) throws Exception {
        return pegaPointelJarDir(daClasse, "apin");
    }

    public static File pegaPointelJarDir(Class daClasse, String ePlataforma) throws Exception {
        File endClass = UtlArq.pEndereco(daClasse);
        File apinEnd = UtlArq.pegaPai(endClass, ePlataforma);
        return apinEnd.getParentFile();
    }

    public static File pegaPointelJarDir(String... maisFilhos) throws Exception {
        return pegaPointelJarDir(true, maisFilhos);
    }

    public static File pegaPointelJarDir(Boolean criaHierarquia, String... maisFilhos) throws Exception {
        File retorno = pegaPointelJarDir();
        if (maisFilhos != null) {
            for (String filho : maisFilhos) {
                retorno = new File(retorno, filho);
            }
        }
        if (criaHierarquia) {
            UtlArq.criaHierarquia(retorno);
        }
        return retorno;
    }

    public static File pegaPointelDir() throws Exception {
        String cam = Globais.pegaCrs("raizInsta");
        if (cam == null) {
            return pegaPointelJarDir();
        } else {
            return new File(cam);
        }
    }

    public static File pegaPointelDir(String... maisFilhos) throws Exception {
        return pegaPointelDir(true, maisFilhos);
    }

    public static File pegaPointelDir(Boolean criaHierarquia, String... maisFilhos) throws Exception {
        File retorno = pegaPointelDir();
        if (maisFilhos != null) {
            for (String filho : maisFilhos) {
                retorno = new File(retorno, filho);
            }
        }
        if (criaHierarquia) {
            UtlArq.criaHierarquia(retorno, true);
        }
        return retorno;
    }

    public static File pegaPointelArq(String... maisFilhos) throws Exception {
        return pegaPointelArq(true, maisFilhos);
    }

    public static File pegaPointelArq(Boolean criaHierarquia, String... maisFilhos) throws Exception {
        File retorno = pegaPointelDir();
        if (maisFilhos != null) {
            for (String filho : maisFilhos) {
                retorno = new File(retorno, filho);
            }
        }
        if (criaHierarquia) {
            UtlArq.criaHierarquia(retorno, false);
        }
        return retorno;
    }

    public static File pegaUsuarioDir(String... maisFilhos) {
        return pegaUsuarioDir(true, maisFilhos);
    }

    public static File pegaUsuarioDir(Boolean criaHierarquia, String... maisFilhos) {
        File retorno = new File(System.getProperty("user.home"));
        if (maisFilhos != null) {
            for (String filho : maisFilhos) {
                retorno = new File(retorno, filho);
            }
        }
        if (criaHierarquia) {
            UtlArq.criaHierarquia(retorno, true);
        }
        return retorno;
    }

    public static File pegaUsuarioArq(String... maisFilhos) {
        return pegaUsuarioArq(true, maisFilhos);
    }

    public static File pegaUsuarioArq(Boolean criaHierarquia, String... maisFilhos) {
        File retorno = new File(System.getProperty("user.home"));
        if (maisFilhos != null) {
            for (String filho : maisFilhos) {
                retorno = new File(retorno, filho);
            }
        }
        if (criaHierarquia) {
            UtlArq.criaHierarquia(retorno, false);
        }
        return retorno;
    }

    public static void procura(File naRaiz, String contendoNoNome, Retornos nosRetornos) {
        if (naRaiz != null) {
            if (naRaiz.getName().contains(contendoNoNome)) {
                Retornos.bota(nosRetornos, "Encontrados", (naRaiz.isDirectory() ? "D" : "F") + (naRaiz.isHidden() ? "H" : "S") + " - " + naRaiz.getAbsolutePath());
            }
            if (naRaiz.isDirectory()) {
                File[] fls = naRaiz.listFiles();
                if (fls != null) {
                    if (fls.length > 0) {
                        for (File fl : fls) {
                            procura(fl, contendoNoNome, nosRetornos);
                        }
                    }
                }
            }
        }
    }

    public static Retornos procura(File naRaiz, String contendoNoNome) {
        Retornos retornos = new Retornos();
        procura(naRaiz, contendoNoNome, retornos);
        return retornos;
    }

    public static InputStream pega(File doArquivoZip, String comNome) throws Exception {
        InputStream retorno = null;
        ZipFile zipFile = new ZipFile(doArquivoZip);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            String thsNome = entry.getName();
            if (thsNome.contains("/")) {
                thsNome = thsNome.substring(thsNome.lastIndexOf("/"));
            }
            if (thsNome.contains("\\")) {
                thsNome = thsNome.substring(thsNome.lastIndexOf("\\"));
            }
            if (thsNome.equals(comNome)) {
                retorno = zipFile.getInputStream(entry);
                break;
            }
        }
        return retorno;
    }

    public static Boolean pega(File doArquivoZip, String comNome, File noArquivo) throws Exception {
        OutputStream output = null;
        InputStream input = UtlArq.pega(doArquivoZip, comNome);
        output = new FileOutputStream(noArquivo);
        int read = 0;
        byte[] bytes = new byte[1024];
        while ((read = input.read(bytes)) != -1) {
            output.write(bytes, 0, read);
        }
        output.close();
        return true;
    }

    public static Boolean copia(File de, File para) throws Exception {
        Boolean retorno = false;
        if (de.exists()) {
            if (!de.isDirectory()) {
                if (para.exists()) {
                    if (!para.isDirectory()) {
                        para.delete();
                        Files.copy(de.toPath(), para.toPath(), StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING);
                        retorno = true;
                    } else {
                        Files.copy(de.toPath(), new File(para, de.getName()).toPath(), StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING);
                        retorno = true;
                    }
                } else {
                    UtlArq.criaHierarquia(para, false);
                    Files.copy(de.toPath(), para.toPath(), StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING);
                    retorno = true;
                }
            } else {
                File nvPara = new File(para, de.getName());
                UtlArq.criaHierarquia(nvPara);
                File[] arqs = de.listFiles();
                for (File arq : arqs) {
                    copia(arq, nvPara);
                }
            }
        }
        return retorno;
    }

    public static void subPastasDesredundar(File aPasta) throws Exception {
        if (aPasta != null) {
            if (aPasta.exists() && aPasta.isDirectory()) {
                for (File fl : aPasta.listFiles()) {
                    subPastasDesredundar(fl);
                }
                File pai = aPasta.getParentFile();
                if (pai != null) {
                    if (pai.getName().equals(aPasta.getName())) {
                        for (File fl : aPasta.listFiles()) {
                            if (fl.isDirectory()) {
                                copia(fl, pai);
                                exclui(fl);
                            } else {
                                copia(fl, pai);
                                fl.delete();
                            }
                        }
                        exclui(aPasta);
                    }
                }
            }
        }
    }

    public static void subPastasLimparArvore(File aPasta) throws Exception {
        if (aPasta != null) {
            if (aPasta.exists() && aPasta.isDirectory()) {
                File[] arqs = aPasta.listFiles();
                if (arqs != null) {
                    if (arqs.length == 0) {
                        aPasta.delete();
                    } else if (arqs.length == 1) {
                        File arq = arqs[0];
                        if (arq.isDirectory()) {
                            String nnome;
                            do {
                                nnome = UtlAle.pegaCrsSimples(5);
                            } while (new File(arq.getParent(), nnome).exists() || new File(aPasta.getParent(), nnome).exists());
                            File temp = new File(arq.getParent(), nnome);
                            arq.renameTo(temp);
                            copia(temp, aPasta.getParentFile());
                            exclui(temp);
                            File ntemp = new File(aPasta.getParent(), temp.getName());
                            File ntept = new File(aPasta.getParent(), aPasta.getName());
                            aPasta.delete();
                            ntemp.renameTo(ntept);
                        }
                    } else {
                        for (File fl : arqs) {
                            subPastasLimparArvore(fl);
                        }
                    }
                }
            }
        }
    }

    public static Boolean exclui(File oArquivoOuPasta) {
        if (oArquivoOuPasta.exists() && oArquivoOuPasta.isDirectory()) {
            for (File fl : oArquivoOuPasta.listFiles()) {
                exclui(fl);
            }
        }
        return oArquivoOuPasta.delete();
    }

    public static void excluiSobrePastasVazias(File aPasta) {
        if (aPasta != null) {
            if (aPasta.exists() && aPasta.isDirectory()) {
                File[] arqs = aPasta.listFiles();
                if (arqs != null) {
                    if (arqs.length == 0) {
                        aPasta.delete();
                        excluiSobrePastasVazias(aPasta.getParentFile());
                    }
                } else {
                    aPasta.delete();
                    excluiSobrePastasVazias(aPasta.getParentFile());
                }
            }
        }
    }

    public static long copia(final InputStream daCorrente, final OutputStream paraCorrente) throws Exception {
        return copia(daCorrente, paraCorrente, 8024);
    }

    public static long copia(final InputStream daCorrente, final OutputStream paraCorrente, int comPartes) throws Exception {
        final byte[] buffer = new byte[comPartes];
        int n = 0;
        long count = 0;
        while (-1 != (n = daCorrente.read(buffer))) {
            paraCorrente.write(buffer, 0, n);
            count += n;
        }
        return count;
    }

    public static void botaArquivoNoZip(File aArquivo, File aZip) throws Exception {
        ZipOutputStream zipFile = new ZipOutputStream(new FileOutputStream(aZip));
        ZipEntry entry = new ZipEntry(aArquivo.getName());
        zipFile.putNextEntry(entry);
        FileInputStream in = new FileInputStream(aArquivo);
        copia(in, zipFile);
        in.close();
        zipFile.close();
    }

    public static void botaDiretorioNoZip(File aDiretorio, File aZip) throws Exception {
        ZipOutputStream zipFile = new ZipOutputStream(new FileOutputStream(aZip));
        botaDiretorioNoZip(aDiretorio.getAbsolutePath() + File.separator, aDiretorio.getAbsolutePath() + File.separator, zipFile);
        zipFile.close();
    }

    public static void botaDiretorioNoZip(String rootDir, String sourceDir, ZipOutputStream out) throws Exception {
        for (File file : new File(sourceDir).listFiles()) {
            if (file.isDirectory()) {
                botaDiretorioNoZip(rootDir, sourceDir + file.getName() + File.separator, out);
            } else {
                ZipEntry entry = new ZipEntry(sourceDir.replace(rootDir, "") + file.getName());
                out.putNextEntry(entry);
                FileInputStream in = new FileInputStream(sourceDir + file.getName());
                copia(in, out);
                in.close();
            }
        }
    }

    public static void copia(String aDe, String aPara, Boolean aSubPastas, Boolean aSoSeAtualizado, Boolean aManterAntigo, Retornos retornos) {
        File de = new File(aDe);
        try {
            Files.createDirectories(new File(aPara).toPath());
        } catch (Exception ex) {
            Utl.registra(ex, retornos == null, retornos);
        }
        if (de.exists()) {
            if (de.isDirectory()) {
                File[] arqs = de.listFiles();
                for (File arq : arqs) {
                    if (!arq.isDirectory()) {
                        copia(arq.getAbsolutePath(), aPara, aSubPastas, aSoSeAtualizado, aManterAntigo, retornos);
                    } else if (aSubPastas) {
                        copia(arq.getAbsolutePath(), UtlArq.soma(aPara, arq.getName()), aSubPastas, aSoSeAtualizado, aManterAntigo, retornos);
                    }
                }
            } else {
                File para = new File(UtlArq.soma(aPara, de.getName()));
                File flcop = null;
                if (!para.exists()) {
                    flcop = para;
                } else {
                    boolean tocop = true;
                    if (aSoSeAtualizado) {
                        try {
                            String md5Para = pegaMD5(Files.readAllBytes(para.toPath()));
                            String md5De = pegaMD5(Files.readAllBytes(de.toPath()));
                            if (md5De.equals(md5Para)) {
                                tocop = false;
                            }
                        } catch (Exception ex) {
                            Utl.registra(ex);
                            Retornos.bota(retornos, "Erro", "Erro");
                            Retornos.bota(retornos, "Erro", ex.getMessage());
                            Retornos.bota(retornos, "Erro", UtlBin.pegaOrigem(ex));
                        }
                    }
                    if (tocop) {
                        if (!aManterAntigo) {
                            para.delete();
                            flcop = para;
                        } else {
                            String nm = UtlArq.pNomeBasico(de.getName());
                            String ex = UtlArq.pExtensao(de.getName());
                            flcop = new File(UtlArq.soma(aPara, nm + " (" + UtlDatHor.pegaAtualParaMaquina() + ")." + ex));
                        }
                    }
                }
                if (flcop != null) {
                    try {
                        Files.copy(de.toPath(), flcop.toPath(), StandardCopyOption.COPY_ATTRIBUTES);
                        Retornos.bota(retornos, "Copiados", 1);
                    } catch (Exception ex) {
                        Utl.registra(ex);
                        Retornos.bota(retornos, "Erro", "Erro");
                        Retornos.bota(retornos, "Erro", ex.getMessage());
                        Retornos.bota(retornos, "Erro", UtlBin.pegaOrigem(ex));
                    }
                }
            }
        }
    }

    public static File mExtensao(File doArquivo, String para) {
        if (doArquivo == null) {
            return null;
        }
        return new File(doArquivo.getParentFile(), pNomeBasico(doArquivo.getName()) + "." + para);
    }

}
