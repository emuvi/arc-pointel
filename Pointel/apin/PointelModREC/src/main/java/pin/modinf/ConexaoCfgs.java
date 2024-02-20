package pin.modinf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import pin.libbas.Partes;
import pin.libutl.UtlArq;
import pin.libutl.UtlCr;
import pin.libutl.UtlCrs;
import pin.libutl.UtlDat;
import pin.libutl.UtlDatHor;
import pin.libutl.UtlHor;
import pin.libutl.UtlInt;
import pin.libutl.UtlIntLon;
import pin.libutl.UtlLog;
import pin.libutl.UtlMom;
import pin.libutl.UtlNum;
import pin.libutl.UtlNumLon;
import pin.libbas.Conjunto;
import pin.libvlr.VCrs;
import pin.libvlr.Vlrs;

public class ConexaoCfgs {

    public Conexao conexao;

    public ConexaoCfgs(Conexao aConexao) {
        conexao = aConexao;
    }

    public ConexaoCfgs carrega(File oArquivo) throws Exception {
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
        return this;
    }

    public ConexaoCfgs carrega(String aLinha) throws Exception {
        String[] partes = Partes.pega(aLinha);
        if (partes != null) {
            if (partes.length == 2) {
                String chave = partes[0];
                String valor = partes[1];
                botaCrs(chave, valor);
            }
        }
        return this;
    }

    public Boolean tem(String aChave) throws Exception {
        return conexao.busca("SELECT chave FROM cfgs WHERE chave = ?",
                new Vlrs(aChave))
                .tem();
    }

    public Conjunto pegaIniciando(String chaveCom) throws Exception {
        return conexao.busca("SELECT chave, valor FROM cfgs WHERE chave LIKE ?",
                new Vlrs(chaveCom + "%"));
    }

    public Conjunto pegaTodas(Object... asChaves) throws Exception {
        return conexao.busca("SELECT chave, valor FROM cfgs WHERE "
                + new VCrs().repete("chave = ? OR ", asChaves.length).corta(4),
                new Vlrs(asChaves));
    }

    public void tira(String aChave) throws Exception {
        conexao.recorrentes().tira("SELECT valor FROM cfgs WHERE chave = ?",
                new Vlrs(aChave));
        conexao.opera("DELETE FROM cfgs WHERE chave = ?",
                new Vlrs(aChave));
    }

    public void tiraIniciando(String chaveCom) throws Exception {
        conexao.opera("DELETE FROM cfgs WHERE chave LIKE ? || %",
                new Vlrs(chaveCom));
    }

    public void botaLog(String naChave, Boolean oValor) throws Exception {
        botaCrs(naChave, UtlLog.formata(oValor));
    }

    public void botaCr(String naChave, Character oValor) throws Exception {
        botaCrs(naChave, UtlCr.formata(oValor));
    }

    public void botaCrs(String naChave, String osCaracteres) throws Exception {
        tira(naChave);
        conexao.opera("INSERT INTO cfgs (chave, valor) VALUES (?, ?)",
                new Vlrs(naChave, osCaracteres));
    }

    private static String senhaPadrao = "Ts%$!Q){WEG&fSn?H:&zWff${/'fF>b!";

    public void botaCrsCrpt(String naChave, String oValor) throws Exception {
        botaCrsCrpt(naChave, oValor, senhaPadrao);
    }

    public void botaCrsCrpt(String naChave, String oValor, String eSenha) throws Exception {
        botaCrs(naChave, UtlCrs.encripta(oValor, eSenha));
    }

    public void botaInt(String naChave, Integer oValor) throws Exception {
        botaCrs(naChave, UtlInt.formata(oValor));
    }

    public void botaIntLon(String naChave, Long oValor) throws Exception {
        botaCrs(naChave, UtlIntLon.formata(oValor));
    }

    public void botaNum(String naChave, Float oValor) throws Exception {
        botaCrs(naChave, UtlNum.formata(oValor));
    }

    public void botaNumLon(String naChave, Double oValor) throws Exception {
        botaCrs(naChave, UtlNumLon.formata(oValor));
    }

    public void botaDat(String naChave, java.util.Date oValor) throws Exception {
        botaCrs(naChave, UtlDat.formata(oValor));
    }

    public void botaHor(String naChave, java.util.Date oValor) throws Exception {
        botaCrs(naChave, UtlHor.formata(oValor));
    }

    public void botaDatHor(String naChave, java.util.Date oValor) throws Exception {
        botaCrs(naChave, UtlDatHor.formata(oValor));
    }

    public void botaMom(String naChave, java.util.Date oValor) throws Exception {
        botaCrs(naChave, UtlMom.formata(oValor));
    }

    public void botaArq(String naChave, byte[] oValor) throws Exception {
        botaCrs(naChave, UtlArq.formataB64(oValor));
    }

    public Boolean pegaLog(String daChave) throws Exception {
        return pegaLog(daChave, null);
    }

    public Boolean pegaLog(String daChave, Boolean comPadrao) throws Exception {
        return UtlLog.pega(pegaCrs(daChave), comPadrao);
    }

    public Character pegaCr(String daChave) throws Exception {
        return pegaCr(daChave, null);
    }

    public Character pegaCr(String daChave, Character comPadrao) throws Exception {
        return UtlCr.pega(pegaCrs(daChave), comPadrao);
    }

    public String pegaCrs(String daChave) throws Exception {
        return pegaCrs(daChave, null);
    }

    public String pegaCrs(String daChave, String comPadrao) throws Exception {
        return conexao.recorrentes().busca("SELECT valor FROM cfgs WHERE chave = ?",
                new Vlrs(daChave))
                .pgCol(comPadrao).pgCrs();
    }

    public String pegaCrsCrpt(String daChave) throws Exception {
        return pegaCrsCrpt(daChave, null, senhaPadrao);
    }

    public String pegaCrsCrpt(String daChave, String comPadrao) throws Exception {
        return pegaCrsCrpt(daChave, comPadrao, senhaPadrao);
    }

    public String pegaCrsCrpt(String daChave, String comPadrao, String eSenha) throws Exception {
        String origem = pegaCrs(daChave);
        if (origem == null) {
            return comPadrao;
        } else {
            return UtlCrs.desencripta(origem, eSenha);
        }
    }

    public Integer pegaInt(String daChave) throws Exception {
        return pegaInt(daChave, null);
    }

    public Integer pegaInt(String daChave, Integer comPadrao) throws Exception {
        return UtlInt.pega(pegaCrs(daChave), comPadrao);
    }

    public Long pegaIntLon(String daChave) throws Exception {
        return pegaIntLon(daChave, null);
    }

    public Long pegaIntLon(String daChave, Long comPadrao) throws Exception {
        return UtlIntLon.pega(pegaCrs(daChave), comPadrao);
    }

    public Float pegaNum(String daChave) throws Exception {
        return pegaNum(daChave, null);
    }

    public Float pegaNum(String daChave, Float comPadrao) throws Exception {
        return UtlNum.pega(pegaCrs(daChave), comPadrao);
    }

    public Double pegaNumLon(String daChave) throws Exception {
        return pegaNumLon(daChave, null);
    }

    public Double pegaNumLon(String daChave, Double comPadrao) throws Exception {
        return UtlNumLon.pega(pegaCrs(daChave), comPadrao);
    }

    public java.util.Date pegaDat(String daChave) throws Exception {
        return pegaDat(daChave, null);
    }

    public java.util.Date pegaDat(String daChave, java.util.Date comPadrao) throws Exception {
        return UtlDat.pega(pegaCrs(daChave), comPadrao);
    }

    public java.util.Date pegaHor(String daChave) throws Exception {
        return pegaHor(daChave, null);
    }

    public java.util.Date pegaHor(String daChave, java.util.Date comPadrao) throws Exception {
        return UtlHor.pega(pegaCrs(daChave), comPadrao);
    }

    public java.util.Date pegaDatHor(String daChave) throws Exception {
        return pegaDatHor(daChave, null);
    }

    public java.util.Date pegaDatHor(String daChave, java.util.Date comPadrao) throws Exception {
        return UtlDatHor.pega(pegaCrs(daChave), comPadrao);
    }

    public java.util.Date pegaMom(String daChave) throws Exception {
        return pegaMom(daChave, null);
    }

    public java.util.Date pegaMom(String daChave, java.util.Date comPadrao) throws Exception {
        return UtlMom.pega(pegaCrs(daChave), comPadrao);
    }

    public byte[] pegaArq(String daChave) throws Exception {
        return pegaArq(daChave, null);
    }

    public byte[] pegaArq(String daChave, byte[] comPadrao) throws Exception {
        return UtlArq.pegaB64(pegaCrs(daChave), comPadrao);
    }

    public String toString(String daChave) throws Exception {
        return Partes.junta(daChave, pegaCrs(daChave, ""));
    }
}
