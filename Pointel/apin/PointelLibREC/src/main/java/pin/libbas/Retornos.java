package pin.libbas;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import pin.libutl.UtlArq;
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

public class Retornos extends ParsVals<Retorno> {

    private Progresso mostrador;

    public Retornos() {
        super();
        mostrador = null;
    }

    public Retornos(Retorno... aRetornos) {
        super(Arrays.asList(aRetornos));
    }

    public static Retornos abre(Object doValor) throws Exception {
        if (doValor instanceof Retornos) {
            return (Retornos) doValor;
        } else if (UtlCrs.eh(doValor)) {
            return descrito(UtlCrs.pega(doValor));
        } else if (UtlArq.eh(doValor)) {
            return descrito(UtlArq.formata(UtlArq.pega(doValor)));
        } else {
            throw new Exception("Valor Não é Reconhecido");
        }
    }

    public static Retornos descrito(String dosCaracteres) throws Exception {
        Retornos retorno = new Retornos();
        String linhas[] = UtlTex.pLinhas(dosCaracteres);
        for (int il = 1; il < linhas.length - 1; il++) {
            retorno.add(Retorno.descrito(linhas[il]));
        }
        return retorno;
    }

    public Progresso pMostrador() {
        return mostrador;
    }

    public void mostrador(String comTitulo) {
        mostrador = new Progresso(comTitulo);
        mostrador.abre();
    }

    public void mostrador(String comTitulo, String eDescricao) {
        mostrador = new Progresso(comTitulo);
        mostrador.mDescricao(eDescricao);
        mostrador.abre();
    }

    public void mostrador(String comTitulo, Boolean pausavel) {
        mostrador = new Progresso(comTitulo, pausavel);
        mostrador.abre();
    }

    public void mostrador(String comTitulo, String eDescricao, Boolean pausavel) {
        mostrador = new Progresso(comTitulo, pausavel);
        mostrador.mDescricao(eDescricao);
        mostrador.abre();
    }

    public void mostrador(String comTitulo, Boolean pausavel, Boolean paravel) {
        mostrador = new Progresso(comTitulo, pausavel, paravel);
        mostrador.abre();
    }

    public void mostrador(String comTitulo, String eDescricao, Boolean pausavel, Boolean paravel) {
        mostrador = new Progresso(comTitulo, pausavel, paravel);
        mostrador.mDescricao(eDescricao);
        mostrador.abre();
    }

    public void abre() {
        if (mostrador != null) {
            mostrador.abre();
        }
    }

    public void abre(Integer eMaximo) {
        if (mostrador != null) {
            mostrador.abre(eMaximo);
        }
    }

    public void abre(Integer comMinimo, Integer eMaximo) {
        if (mostrador != null) {
            mostrador.abre(comMinimo, eMaximo);
        }
    }

    public void aumenta() {
        if (mostrador != null) {
            mostrador.aumenta();
        }
    }

    public void aumenta(Integer posicoes) {
        if (mostrador != null) {
            mostrador.aumenta(posicoes);
        }
    }

    public void mudaProgresso(Long oTamanho) {
        if (mostrador != null) {
            mostrador.mudaProgresso(oTamanho);
        }
    }

    public void mudaProgresso(Integer oTamanho) {
        if (mostrador != null) {
            mostrador.mProgresso(oTamanho);
        }
    }

    public void mudaTamanho(Long oTamanho) {
        if (mostrador != null) {
            mostrador.mudaTamanho(oTamanho);
        }
    }

    public void mudaTamanho(Integer oTamanho) {
        if (mostrador != null) {
            mostrador.mTamanho(oTamanho);
        }
    }

    public void avanca() {
        if (mostrador != null) {
            mostrador.avanca();
        }
    }

    public void avanca(String comMensagem) {
        if (mostrador != null) {
            mostrador.avanca(comMensagem);
        }
    }

    public void posicao(Integer naPosicao) {
        if (mostrador != null) {
            mostrador.mudaPosicao(naPosicao);
        }
    }

    public void criaAutoCorrer() {
        if (mostrador != null) {
            mostrador.criaAutoCorrer();
        }
    }

    public void pausar() {
        if (mostrador != null) {
            mostrador.pausar();
        }
    }

    public Boolean devePausar() {
        Boolean retorno = false;
        if (mostrador != null) {
            retorno = mostrador.devePausar();
        }
        return retorno;
    }

    public Boolean deveParar() {
        Boolean retorno = false;
        if (mostrador != null) {
            retorno = mostrador.deveParar();
        }
        return retorno;
    }

    public Boolean pausarEdeveParar() {
        Boolean retorno = false;
        if (mostrador != null) {
            retorno = mostrador.pausarEdeveParar();
        }
        return retorno;
    }

    public Boolean avancaPausarEdeveParar() {
        Boolean retorno = false;
        if (mostrador != null) {
            retorno = mostrador.avancaPausarEdeveParar();
        }
        return retorno;
    }

    public Boolean avancaPausarEdeveParar(String comMensagem) {
        Boolean retorno = false;
        if (mostrador != null) {
            retorno = mostrador.avancaPausarEdeveParar(comMensagem);
        }
        return retorno;
    }

    public void termina() {
        bota("Terminado", true);
        if (mostrador != null) {
            mostrador.termina();
        }
    }

    public void fechar() {
        if (mostrador != null) {
            mostrador.fechar();
        }
    }

    public void bota(Retorno oRetorno) {
        botaUltimo(oRetorno);
        if (mostrador != null) {
            mostrador.bota(oRetorno.pegaDescricao(), oRetorno.pegaValor());
        }
    }

    public void bota(Object oValor) {
        botaUltimo(new Retorno(oValor));
        if (mostrador != null) {
            mostrador.bota(oValor);
        }
    }

    public void bota(String comDescricao, Object eValor) {
        bota(comDescricao, eValor, true);
    }

    public void bota(String comDescricao, Object eValor, Boolean mostra) {
        botaUltimo(new Retorno(comDescricao, eValor));
        if (mostra) {
            if (mostrador != null) {
                mostrador.bota(comDescricao, eValor);
            }
        }
    }

    public void bota(Throwable erro) {
        botaUltimo(new Retorno(erro));
        if (mostrador != null) {
            mostrador.bota(erro);
        }
    }

    public void bota(String comDescricao, Throwable oErro) {
        botaUltimo(new Retorno(comDescricao, oErro));
        if (mostrador != null) {
            mostrador.bota(comDescricao, oErro);
        }
    }

    public Object pega(String comDescricao) {
        return pega(comDescricao, (Object) null);
    }

    public Object pega(String comDescricao, Object ePadrao) {
        Object retorno = ePadrao;
        for (Retorno ret : this) {
            if (Objects.equals(ret.pegaDescricao(), comDescricao)) {
                retorno = ret.pegaValor();
                break;
            }
        }
        return retorno;
    }

    public Boolean pegaLog(String comDescricao) {
        return UtlLog.pega(pega(comDescricao));
    }

    public Boolean pegaLog(String comDescricao, Boolean ePadrao) {
        return UtlLog.pega(pega(comDescricao), ePadrao);
    }

    public Character pegaCr(String comDescricao) {
        return UtlCr.pega(pega(comDescricao));
    }

    public Character pegaCr(String comDescricao, Character ePadrao) {
        return UtlCr.pega(pega(comDescricao), ePadrao);
    }

    public String pegaCrs(String comDescricao) {
        return UtlCrs.pega(pega(comDescricao));
    }

    public String pegaCrs(String comDescricao, String ePadrao) {
        return UtlCrs.pega(pega(comDescricao), ePadrao);
    }

    public Integer pegaInt(String comDescricao) {
        return UtlInt.pega(pega(comDescricao));
    }

    public Integer pegaInt(String comDescricao, Integer ePadrao) {
        return UtlInt.pega(pega(comDescricao), ePadrao);
    }

    public Long pegaIntLon(String comDescricao) {
        return UtlIntLon.pega(pega(comDescricao));
    }

    public Long pegaIntLon(String comDescricao, Long ePadrao) {
        return UtlIntLon.pega(pega(comDescricao), ePadrao);
    }

    public Float pegaNum(String comDescricao) {
        return UtlNum.pega(pega(comDescricao));
    }

    public Float pegaNum(String comDescricao, Float ePadrao) {
        return UtlNum.pega(pega(comDescricao), ePadrao);
    }

    public Double pegaNumLon(String comDescricao) {
        return UtlNumLon.pega(pega(comDescricao));
    }

    public Double pegaNumLon(String comDescricao, Double ePadrao) {
        return UtlNumLon.pega(pega(comDescricao), ePadrao);
    }

    public java.util.Date pegaDat(String comDescricao) {
        return UtlDat.pega(pega(comDescricao));
    }

    public java.util.Date pegaDat(String comDescricao, java.util.Date ePadrao) {
        return UtlDat.pega(pega(comDescricao), ePadrao);
    }

    public java.util.Date pegaHor(String comDescricao) {
        return UtlHor.pega(pega(comDescricao));
    }

    public java.util.Date pegaHor(String comDescricao, java.util.Date ePadrao) {
        return UtlHor.pega(pega(comDescricao), ePadrao);
    }

    public java.util.Date pegaDatHor(String comDescricao) {
        return UtlDatHor.pega(pega(comDescricao));
    }

    public java.util.Date pegaDatHor(String comDescricao, java.util.Date ePadrao) {
        return UtlDatHor.pega(pega(comDescricao), ePadrao);
    }

    public java.util.Date pegaMom(String comDescricao) {
        return UtlMom.pega(pega(comDescricao));
    }

    public java.util.Date pegaMom(String comDescricao, java.util.Date ePadrao) {
        return UtlMom.pega(pega(comDescricao), ePadrao);
    }

    public List<Object> pegaTodos(String comDescricao) {
        List<Object> retorno = new ArrayList<>();
        for (Retorno ret : this) {
            if (Objects.equals(ret.pegaDescricao(), comDescricao)) {
                retorno.add(ret.pegaValor());
            }
        }
        return retorno;
    }

    public Boolean temRetorno(String comDescricao) {
        for (Retorno ret : this) {
            if (Objects.equals(ret.pegaDescricao(), comDescricao)) {
                return true;
            }
        }
        return false;
    }

    public String somaCrs(String comDescricao) {
        String retorno = "";
        for (Retorno ret : this) {
            if (Objects.equals(comDescricao, ret.pegaDescricao())) {
                retorno = retorno + (retorno.isEmpty() ? "" : System.lineSeparator()) + UtlCrs.pega(ret.pegaValor(), "");
            }
        }
        return retorno;
    }

    public Integer somaInt(String comDescricao) {
        Integer retorno = 0;
        for (Retorno ret : this) {
            if (Objects.equals(comDescricao, ret.pegaDescricao())) {
                retorno = retorno + UtlInt.pega(ret.pegaValor(), 0);
            }
        }
        return retorno;
    }

    public Long somaIntLon(String comDescricao) {
        Long retorno = 0l;
        for (Retorno ret : this) {
            if (Objects.equals(comDescricao, ret.pegaDescricao())) {
                retorno = retorno + UtlIntLon.pega(ret.pegaValor(), 0l);
            }
        }
        return retorno;
    }

    public Float somaNum(String comDescricao) {
        Float retorno = 0.0f;
        for (Retorno ret : this) {
            if (Objects.equals(comDescricao, ret.pegaDescricao())) {
                retorno = retorno + UtlNum.pega(ret.pegaValor(), 0.0f);
            }
        }
        return retorno;
    }

    public Double somaNumLon(String comDescricao) {
        Double retorno = 0.0;
        for (Retorno ret : this) {
            if (Objects.equals(comDescricao, ret.pegaDescricao())) {
                retorno = retorno + UtlNumLon.pega(ret.pegaValor(), 0.0);
            }
        }
        return retorno;
    }

    public List<Object[]> pegaTab() throws Exception {
        List<Object[]> retorno = new ArrayList<>();
        iterage(new Analisado<Retorno, Boolean>() {
            @Override
            public Boolean analisa(Retorno oValor) {
                retorno.add(new Object[]{oValor.pegaMomento(), oValor.pegaDescricao(), oValor.pegaValor()});
                return true;
            }
        });
        return retorno;
    }

    @Override
    public String toString() {
        String retorno = "Retornos:";
        for (Retorno ret : this) {
            retorno += UtlTex.quebra() + ret.toString();
        }
        return retorno;
    }

    public String descreve() {
        String retorno = "<retornos>";
        for (Retorno ret : this) {
            retorno = retorno + UtlTex.quebra() + ret.descreve();
        }
        return retorno + UtlTex.quebra() + "</retornos>";
    }

    public Boolean salva() throws Exception {
        return UtlTex.salva(descreve(), UtlArq.salvaArq("Retornos (*.rts)", "rts"));
    }

    public Boolean salva(File noArquivo) throws Exception {
        return UtlTex.salva(descreve(), noArquivo);
    }

    public String salvaEmRetsDir(String comNome) throws Exception {
        String descrito = descreve();
        File arq = UtlArq.naoSobrescrever(UtlArq.pegaPointelArq("rets", UtlMom.pegaAtualParaMaquina() + " - " + comNome + ".rts"));
        UtlTex.salva(descrito, arq);
        return arq.getAbsolutePath();
    }

    public static void bota(Retornos nosRetornos, Object oValor) {
        if (nosRetornos != null) {
            nosRetornos.bota(oValor);
        }
    }

    public static void bota(Retornos nosRetornos, String comDescricao, Object eValor) {
        if (nosRetornos != null) {
            nosRetornos.bota(comDescricao, eValor);
        }
    }

    public static void bota(Retornos nosRetornos, Throwable oErro) {
        if (nosRetornos != null) {
            nosRetornos.bota("Erro", oErro);
        }
    }

    public static void bota(Retornos nosRetornos, String comDescricao, Throwable oErro) {
        if (nosRetornos != null) {
            nosRetornos.bota(comDescricao, oErro);
        }
    }

    public static Object pega(Retornos dosRetornos, String comDescricao) {
        return pega(dosRetornos, comDescricao, null);
    }

    public static Object pega(Retornos dosRetornos, String comDescricao, Object ePadrao) {
        if (dosRetornos != null) {
            return dosRetornos.pega(comDescricao, ePadrao);
        } else {
            return ePadrao;
        }
    }

    public static void mostrador(Retornos dosRetornos, Progresso oMostrador) {
        if (dosRetornos != null) {
            dosRetornos.mostrador = oMostrador;
            dosRetornos.mostrador.abre();
        }
    }

    public static void mostrador(Retornos dosRetornos, String comTitulo) {
        if (dosRetornos != null) {
            dosRetornos.mostrador = new Progresso(comTitulo);
            dosRetornos.mostrador.abre();
        }
    }

    public static void mostrador(Retornos dosRetornos, String comTitulo, Boolean pausavel) {
        if (dosRetornos != null) {
            dosRetornos.mostrador = new Progresso(comTitulo, pausavel);
            dosRetornos.mostrador.abre();
        }
    }

    public static void mostrador(Retornos dosRetornos, String comTitulo, Boolean pausavel, Boolean paravel) {
        if (dosRetornos != null) {
            dosRetornos.mostrador = new Progresso(comTitulo, pausavel, paravel);
            dosRetornos.mostrador.abre();
        }
    }

    public static void abre(Retornos dosRetornos) {
        if (dosRetornos != null) {
            if (dosRetornos.mostrador != null) {
                dosRetornos.mostrador.abre();
            }
        }
    }

    public static void abre(Retornos dosRetornos, Integer eMaximo) {
        if (dosRetornos != null) {
            if (dosRetornos.mostrador != null) {
                dosRetornos.mostrador.abre(eMaximo);
            }
        }
    }

    public static void abre(Retornos dosRetornos, Integer comMinimo, Integer eMaximo) {
        if (dosRetornos != null) {
            if (dosRetornos.mostrador != null) {
                dosRetornos.mostrador.abre(comMinimo, eMaximo);
            }
        }
    }

    public static void aumenta(Retornos dosRetornos) {
        if (dosRetornos != null) {
            if (dosRetornos.mostrador != null) {
                dosRetornos.mostrador.aumenta();
            }
        }
    }

    public static void aumenta(Retornos dosRetornos, Integer posicoes) {
        if (dosRetornos != null) {
            if (dosRetornos.mostrador != null) {
                dosRetornos.mostrador.aumenta(posicoes);
            }
        }
    }

    public static void mudaProgresso(Retornos dosRetornos, Long oTamanho) {
        if (dosRetornos != null) {
            if (dosRetornos.mostrador != null) {
                dosRetornos.mostrador.mudaProgresso(oTamanho);
            }
        }
    }

    public static void mudaProgresso(Retornos dosRetornos, Integer oTamanho) {
        if (dosRetornos != null) {
            if (dosRetornos.mostrador != null) {
                dosRetornos.mostrador.mProgresso(oTamanho);
            }
        }
    }

    public static void mudaTamanho(Retornos dosRetornos, Long oTamanho) {
        if (dosRetornos != null) {
            if (dosRetornos.mostrador != null) {
                dosRetornos.mostrador.mudaTamanho(oTamanho);
            }
        }
    }

    public static void mudaTamanho(Retornos dosRetornos, Integer oTamanho) {
        if (dosRetornos != null) {
            if (dosRetornos.mostrador != null) {
                dosRetornos.mostrador.mTamanho(oTamanho);
            }
        }
    }

    public static void avanca(Retornos dosRetornos) {
        if (dosRetornos != null) {
            if (dosRetornos.mostrador != null) {
                dosRetornos.mostrador.avanca();
            }
        }
    }

    public static void avanca(Retornos dosRetornos, String comMensagem) {
        if (dosRetornos != null) {
            if (dosRetornos.mostrador != null) {
                dosRetornos.mostrador.avanca(comMensagem);
            }
        }
    }

    public static void mudaPosicao(Retornos dosRetornos, Integer naPosicao) {
        if (dosRetornos != null) {
            if (dosRetornos.mostrador != null) {
                dosRetornos.mostrador.mudaPosicao(naPosicao);
            }
        }
    }

    public static void criaAutoCorrer(Retornos dosRetornos) {
        if (dosRetornos != null) {
            if (dosRetornos.mostrador != null) {
                dosRetornos.mostrador.criaAutoCorrer();
            }
        }
    }

    public static void pausar(Retornos dosRetornos) {
        if (dosRetornos != null) {
            if (dosRetornos.mostrador != null) {
                dosRetornos.mostrador.pausar();
            }
        }
    }

    public static Boolean devePausar(Retornos dosRetornos) {
        Boolean retorno = false;
        if (dosRetornos != null) {
            if (dosRetornos.mostrador != null) {
                retorno = dosRetornos.mostrador.devePausar();
            }
        }
        return retorno;
    }

    public static Boolean deveParar(Retornos dosRetornos) {
        Boolean retorno = false;
        if (dosRetornos != null) {
            if (dosRetornos.mostrador != null) {
                retorno = dosRetornos.mostrador.deveParar();
            }
        }
        return retorno;
    }

    public static Boolean pausarEdeveParar(Retornos dosRetornos) {
        Boolean retorno = false;
        if (dosRetornos != null) {
            if (dosRetornos.mostrador != null) {
                retorno = dosRetornos.mostrador.pausarEdeveParar();
            }
        }
        return retorno;
    }

    public static Boolean avancaPausarEdeveParar(Retornos dosRetornos) {
        Boolean retorno = false;
        if (dosRetornos != null) {
            if (dosRetornos.mostrador != null) {
                retorno = dosRetornos.mostrador.avancaPausarEdeveParar();
            }
        }
        return retorno;
    }

    public static Boolean avancaPausarEdeveParar(Retornos dosRetornos, String comMensagem) {
        Boolean retorno = false;
        if (dosRetornos != null) {
            if (dosRetornos.mostrador != null) {
                retorno = dosRetornos.mostrador.avancaPausarEdeveParar(comMensagem);
            }
        }
        return retorno;
    }

    public static void termina(Retornos dosRetornos) {
        if (dosRetornos != null) {
            dosRetornos.bota("Terminado", true, false);
            if (dosRetornos.mostrador != null) {
                dosRetornos.mostrador.termina();
            }
        }
    }

    public static void fechar(Retornos dosRetornos) {
        if (dosRetornos != null) {
            if (dosRetornos.mostrador != null) {
                dosRetornos.mostrador.fechar();
            }
        }
    }

    public static Boolean salva(Retornos osRetornos) throws Exception {
        if (osRetornos != null) {
            return osRetornos.salva();
        } else {
            return false;
        }
    }

    public static Boolean salva(Retornos osRetornos, File noArquivo) throws Exception {
        if (osRetornos != null) {
            return osRetornos.salva(noArquivo);
        } else {
            return false;
        }
    }

    public static String salvaEmRetsDir(Retornos osRetornos, String comNome) throws Exception {
        if (osRetornos != null) {
            return osRetornos.salvaEmRetsDir(comNome);
        } else {
            return "Retornos Nulo";
        }
    }

    public static Boolean ehExtensao(String aExtensao) {
        aExtensao = aExtensao.toLowerCase();
        return UtlLis.tem(aExtensao, pegaExtensoes());
    }

    public static String[] pegaExtensoes() {
        return new String[]{"rts", "rets"};
    }

    public static String pegaExtensoesDescricao() {
        return "Imagens (*.rts, *.rets)";
    }
}
