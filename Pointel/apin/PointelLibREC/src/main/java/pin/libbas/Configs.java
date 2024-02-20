package pin.libbas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import pin.libutl.Utl;
import pin.libutl.UtlArq;
import pin.libutl.UtlBin;
import pin.libutl.UtlCr;
import pin.libutl.UtlCrs;
import pin.libutl.UtlDat;
import pin.libutl.UtlDatHor;
import pin.libutl.UtlHor;
import pin.libutl.UtlInt;
import pin.libutl.UtlIntLon;
import pin.libutl.UtlLis;
import pin.libutl.UtlLog;
import pin.libutl.UtlMom;
import pin.libutl.UtlNum;
import pin.libutl.UtlNumLon;
import pin.libutl.UtlTex;

public class Configs extends ConcurrentHashMap<String, String> {

    private volatile AtomicBoolean salvar;
    private volatile AtomicBoolean fechar;
    private volatile AtomicBoolean fechado;

    private File arquivo;

    public Configs() {
        super();
        salvar = new AtomicBoolean(false);
        fechar = new AtomicBoolean(false);
        fechado = new AtomicBoolean(false);
        new Salvador().start();
    }

    public Configs(File doArquivo) throws Exception {
        this();
        abre(doArquivo);
    }

    public Configs(String dosCaracteres) throws Exception {
        this();
        abre(dosCaracteres);
    }

    public Configs abre(File doArquivo) throws Exception {
        clear();
        arquivo = doArquivo;
        if (!arquivo.exists()) {
            UtlArq.criaHierarquia(arquivo, false);
            arquivo.createNewFile();
        }
        carrega(arquivo);
        return salva();
    }

    public Configs abre(String dosCaracteres) throws Exception {
        clear();
        if (dosCaracteres != null) {
            String[] linhas = UtlTex.pLinhas(dosCaracteres);
            for (String linha : linhas) {
                carrega(linha);
            }
        }
        return salva();
    }

    public Configs abre(Object doValor) throws Exception {
        return abre(UtlCrs.pega(doValor));
    }

    public Configs carrega(File oArquivo) throws Exception {
        if (oArquivo != null) {
            if (oArquivo.exists()) {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(oArquivo), "UTF8"));
                String linha;
                while ((linha = br.readLine()) != null) {
                    carrega(linha);
                }
                br.close();
            }
        }
        return salva();
    }

    public Configs carrega(String[] asLinhas) {
        if (asLinhas != null) {
            for (String linha : asLinhas) {
                carrega(linha);
            }
        }
        return salva();
    }

    public Configs carrega(String aLinha) {
        String[] partes = Partes.pega(aLinha);
        if (partes != null) {
            if (partes.length >= 2) {
                String chave = partes[0];
                String valor = partes[1];
                put(chave, valor);
            }
        }
        return salva();
    }

    public Configs salva(File doArquivo) {
        arquivo = doArquivo;
        return salva();
    }

    public Configs salva() {
        salvar.set(true);
        return this;
    }

    public void limpa() {
        clear();
    }

    public void fecha() {
        fechar.set(true);
        while (!fechado.get()) {
            UtlBin.esperaMilis(100);
        }
    }

    public Boolean tem(String aChave) {
        return containsKey(aChave);
    }

    public HashMap<String, String> pegaIniciando(String chaveCom) throws Exception {
        HashMap<String, String> retorno = new HashMap<>();
        for (Map.Entry<String, String> ent : entrySet()) {
            if (ent.getKey() != null) {
                if (ent.getKey().startsWith(chaveCom)) {
                    retorno.put(ent.getKey(), ent.getValue());
                }
            }
        }
        return retorno;
    }

    public HashMap<String, String> pegaTodas(Object... asChaves) throws Exception {
        HashMap<String, String> retorno = new HashMap<>();
        for (Map.Entry<String, String> ent : entrySet()) {
            if (ent.getKey() != null) {
                if (UtlLis.tem(ent.getKey(), asChaves)) {
                    retorno.put(ent.getKey(), ent.getValue());
                }
            }
        }
        return retorno;
    }

    public void tira(String aChave) {
        remove(aChave);
    }

    public void botaLog(String naChave, Boolean oValor) {
        put(naChave, UtlLog.formata(oValor));
        salva();
    }

    public void botaCr(String naChave, Character oValor) {
        put(naChave, UtlCr.formata(oValor));
        salva();
    }

    public void botaCrs(String naChave, String oValor) {
        put(naChave, oValor);
        salva();
    }

    private static String senhaPadrao = "Lg$4?K){UOP*fRn:V`azYfw${/'yW>b!";

    public void botaCrsCrpt(String naChave, String oValor) throws Exception {
        botaCrsCrpt(naChave, oValor, senhaPadrao);
    }

    public void botaCrsCrpt(String naChave, String oValor, String eSenha) throws Exception {
        put(naChave, UtlCrs.encripta(oValor, eSenha));
        salva();
    }

    public void botaInt(String naChave, Integer oValor) {
        put(naChave, UtlInt.formata(oValor));
        salva();
    }

    public void botaIntLon(String naChave, Long oValor) {
        put(naChave, UtlIntLon.formata(oValor));
        salva();
    }

    public void botaNum(String naChave, Float oValor) {
        put(naChave, UtlNum.formata(oValor));
        salva();
    }

    public void botaNumLon(String naChave, Double oValor) {
        put(naChave, UtlNumLon.formata(oValor));
        salva();
    }

    public void botaDat(String naChave, java.util.Date oValor) {
        put(naChave, UtlDat.formata(oValor));
        salva();
    }

    public void botaHor(String naChave, java.util.Date oValor) {
        put(naChave, UtlHor.formata(oValor));
        salva();
    }

    public void botaDatHor(String naChave, java.util.Date oValor) {
        put(naChave, UtlDatHor.formata(oValor));
        salva();
    }

    public void botaMom(String naChave, java.util.Date oValor) {
        put(naChave, UtlMom.formata(oValor));
        salva();
    }

    public void botaArq(String naChave, byte[] oValor) {
        put(naChave, UtlArq.formataB64(oValor));
        salva();
    }

    public Boolean pegaLog(String daChave) {
        return pegaLog(daChave, null);
    }

    public Boolean pegaLog(String daChave, Boolean comPadrao) {
        return UtlLog.pega(get(daChave), comPadrao);
    }

    public Character pegaCr(String daChave) {
        return pegaCr(daChave, null);
    }

    public Character pegaCr(String daChave, Character comPadrao) {
        return UtlCr.pega(get(daChave), comPadrao);
    }

    public String pegaCrs(String daChave) {
        return pegaCrs(daChave, null);
    }

    public String pegaCrs(String daChave, String comPadrao) {
        return UtlCrs.pega(get(daChave), comPadrao);
    }

    public String pegaCrsCrpt(String daChave) throws Exception {
        return pegaCrsCrpt(daChave, null, senhaPadrao);
    }

    public String pegaCrsCrpt(String daChave, String comPadrao) throws Exception {
        return pegaCrsCrpt(daChave, comPadrao, senhaPadrao);
    }

    public String pegaCrsCrpt(String daChave, String comPadrao, String eSenha) throws Exception {
        String origem = get(daChave);
        if (origem == null) {
            return comPadrao;
        } else {
            return UtlCrs.desencripta(origem, eSenha);
        }
    }

    public Integer pegaInt(String daChave) {
        return pegaInt(daChave, null);
    }

    public Integer pegaInt(String daChave, Integer comPadrao) {
        return UtlInt.pega(get(daChave), comPadrao);
    }

    public Long pegaIntLon(String daChave) {
        return pegaIntLon(daChave, null);
    }

    public Long pegaIntLon(String daChave, Long comPadrao) {
        return UtlIntLon.pega(get(daChave), comPadrao);
    }

    public Float pegaNum(String daChave) {
        return pegaNum(daChave, null);
    }

    public Float pegaNum(String daChave, Float comPadrao) {
        return UtlNum.pega(get(daChave), comPadrao);
    }

    public Double pegaNumLon(String daChave) {
        return pegaNumLon(daChave, null);
    }

    public Double pegaNumLon(String daChave, Double comPadrao) {
        return UtlNumLon.pega(get(daChave), comPadrao);
    }

    public java.util.Date pegaDat(String daChave) {
        return pegaDat(daChave, null);
    }

    public java.util.Date pegaDat(String daChave, java.util.Date comPadrao) {
        return UtlDat.pega(get(daChave), comPadrao);
    }

    public java.util.Date pegaHor(String daChave) {
        return pegaHor(daChave, null);
    }

    public java.util.Date pegaHor(String daChave, java.util.Date comPadrao) {
        return UtlHor.pega(get(daChave), comPadrao);
    }

    public java.util.Date pegaDatHor(String daChave) {
        return pegaDatHor(daChave, null);
    }

    public java.util.Date pegaDatHor(String daChave, java.util.Date comPadrao) {
        return UtlDatHor.pega(get(daChave), comPadrao);
    }

    public java.util.Date pegaMom(String daChave) {
        return pegaMom(daChave, null);
    }

    public java.util.Date pegaMom(String daChave, java.util.Date comPadrao) {
        return UtlMom.pega(get(daChave), comPadrao);
    }

    public byte[] pegaArq(String daChave) {
        return pegaArq(daChave, null);
    }

    public byte[] pegaArq(String daChave, byte[] comPadrao) {
        return UtlArq.pegaB64(get(daChave), comPadrao);
    }

    public String toString(String daChave) {
        return Partes.junta(daChave, pegaCrs(daChave, ""));
    }

    @Override
    public String toString() {
        String retorno = "";
        for (Map.Entry<String, String> entry : entrySet()) {
            String chave = entry.getKey();
            String valor = entry.getValue();
            String linha = Partes.junta(chave, valor);
            retorno = UtlTex.soma(retorno, linha);
        }
        return retorno;
    }

    private class Salvador extends Thread {

        public Salvador() {
            super("Configs Salvador");
        }

        @Override
        public void run() {
            try {
                while (!fechar.get() || salvar.get()) {
                    if (salvar.getAndSet(false)) {
                        try {
                            if (arquivo != null) {
                                if (!arquivo.exists()) {
                                    UtlArq.criaHierarquia(arquivo, false);
                                    arquivo.createNewFile();
                                }
                                File novo = UtlArq.mExtensao(arquivo, "cge");
                                Boolean interrompido = false;
                                try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(novo), "UTF8"))) {
                                    for (Map.Entry<String, String> entry : entrySet()) {
                                        if (salvar.get()) {
                                            interrompido = true;
                                            break;
                                        }
                                        String chave = entry.getKey();
                                        String valor = entry.getValue();
                                        String linha = Partes.junta(chave, valor);
                                        bw.write(linha + UtlTex.quebra());
                                        bw.flush();
                                    }
                                    bw.flush();
                                } catch (Exception ex) {
                                    Utl.registra(ex, false);
                                    interrompido = true;
                                }
                                if (interrompido) {
                                    novo.delete();
                                } else {
                                    boolean sucesso = false;
                                    File temp = UtlArq.mExtensao(arquivo, "cgt");
                                    temp.delete();
                                    Files.move(arquivo.toPath(), temp.toPath());
                                    if (novo.renameTo(arquivo)) {
                                        sucesso = true;
                                        temp.delete();
                                    }
                                    if (!sucesso) {
                                        salvar.set(true);
                                    }
                                }
                            }
                        } catch (Exception ex) {
                            Utl.registra(ex, false);
                            salvar.set(true);
                        }
                    }
                    UtlBin.esperaMilis(100);
                }
            } catch (Exception ex) {
                Utl.registra(ex);
            } finally {
                fechado.set(true);
            }
        }

    }

}
