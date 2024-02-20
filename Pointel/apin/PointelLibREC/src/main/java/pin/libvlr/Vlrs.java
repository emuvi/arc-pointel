package pin.libvlr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import pin.libbas.Partes;
import pin.libtex.Marcados;
import pin.libutl.Utl;
import pin.libutl.UtlCrs;
import pin.libutl.UtlLis;
import pin.libutl.UtlTex;

public class Vlrs extends ArrayList<Vlr> {

    private String[] nomes;

    public Vlrs() {
        super();
        nomes = null;
    }

    public Vlrs(Vlr... comValores) {
        super(Arrays.asList(comValores));
        nomes = null;
    }

    public Vlrs(Object... comValores) {
        super();
        for (Object vlr : comValores) {
            bota(Vlr.novo(vlr));
        }
        nomes = null;
    }

    public static Vlrs descrito(String nosCaracteres) throws Exception {
        Vlrs retorno = new Vlrs();
        String linha = Marcados.preparado(nosCaracteres, "linha");
        retorno.nomes = UtlLis.pMatCrs(Partes.juntados(Marcados.preparado(linha, "nomes")));
        Object[] vlrs = Partes.juntados(Marcados.preparado(linha, "vlrs"));
        for (Object vlr : vlrs) {
            retorno.bota(Vlr.novo(vlr));
        }
        return retorno;
    }

    public String[] pegaNomes() {
        return nomes;
    }

    public Vlrs botaNomes(String... osNomes) {
        nomes = osNomes;
        return this;
    }

    public Vlrs botaNome(String oNome) {
        nomes = UtlLis.bota(oNome, nomes);
        return this;
    }

    public Vlrs botaNome(String oNome, Integer noIndice) {
        nomes = UtlLis.bota(oNome, nomes, noIndice);
        return this;
    }

    public Integer pegaColunaInd(String doNome) {
        Integer retorno = -1;
        if (nomes != null) {
            for (int in = 0; in < nomes.length; in++) {
                if (Objects.equals(doNome, nomes[in])) {
                    retorno = in;
                    break;
                }
            }
        }
        return retorno;
    }

    public Vlrs novo(Object oValor) {
        bota(Vlr.novo(oValor));
        return this;
    }

    public Vlrs novo(String comNome, Object oValor) {
        bota(comNome, Vlr.novo(oValor));
        return this;
    }

    public Vlrs bota(Vlr oValor) {
        add(oValor);
        return this;
    }

    public Vlrs bota(String comNome, Vlr oValor) {
        botaNome(comNome);
        add(oValor);
        return this;
    }

    public Vlrs troca(Integer noIndice, Object paraValor) {
        muda(noIndice, Vlr.novo(paraValor));
        return this;
    }

    public Vlrs troca(String doNome, Object paraValor) {
        muda(doNome, Vlr.novo(paraValor));
        return this;
    }

    public Vlrs muda(Integer noIndice, Vlr paraValor) {
        set(noIndice, paraValor);
        return this;
    }

    public Vlrs muda(String doNome, Vlr paraValor) {
        Integer ind = pegaColunaInd(doNome);
        if (ind > -1) {
            set(ind, paraValor);
        } else {
            bota(doNome, paraValor);
        }
        return this;
    }

    public Integer tamanho() {
        return size();
    }

    public Boolean vazio() {
        return isEmpty();
    }

    public Boolean tem() {
        return !isEmpty();
    }

    public Boolean tem(Vlr oValor) {
        Boolean retorno = false;
        for (Vlr vlr : this) {
            if (Objects.equals(vlr, oValor)) {
                retorno = true;
                break;
            }
        }
        return retorno;
    }

    public Vlr pegaCol() {
        return pegaCol(null);
    }

    public Vlr pegaCol(Object comPadrao) {
        if (!vazio()) {
            return pgVlr(0, comPadrao);
        } else {
            return Vlr.novo(comPadrao);
        }
    }

    public String paraConsSQL(Integer doIndice) {
        return pgVlr(doIndice).paraConsSQL();
    }

    public String paraConsSQL(String doNome) {
        return pgVlr(doNome).paraConsSQL();
    }

    public Object paraParamSQL(Integer doIndice) {
        return pgVlr(doIndice).paraParamSQL();
    }

    public Object paraParamSQL(String doNome) {
        return pgVlr(doNome).paraParamSQL();
    }

    public Vlr pgVlr(Integer doIndice) {
        return pgVlr(doIndice, null);
    }

    public Vlr pgVlr(Integer doIndice, Object comPadrao) {
        Vlr retorno = null;
        if (doIndice > -1 && doIndice < size()) {
            retorno = get(doIndice);
        }
        if (retorno == null) {
            retorno = Vlr.novo(comPadrao);
        } else if (retorno.vazio()) {
            retorno = Vlr.novo(comPadrao);
        }
        return retorno;
    }

    public VLog pgVlrLog(Integer doIndice) throws Exception {
        return pgVlr(doIndice).pgVlrLog();
    }

    public VLog pgVlrLog(Integer doIndice, Object comPadrao) throws Exception {
        return pgVlr(doIndice).pgVlrLog(comPadrao);
    }

    public VCr pgVlrCr(Integer doIndice) throws Exception {
        return pgVlr(doIndice).pgVlrCr();
    }

    public VCr pgVlrCr(Integer doIndice, Object comPadrao) throws Exception {
        return pgVlr(doIndice).pgVlrCr(comPadrao);
    }

    public VCrs pgVlrCrs(Integer doIndice) throws Exception {
        return pgVlr(doIndice).pgVlrCrs();
    }

    public VCrs pgVlrCrs(Integer doIndice, Object comPadrao) throws Exception {
        return pgVlr(doIndice).pgVlrCrs(comPadrao);
    }

    public VCor pgVlrCor(Integer doIndice) throws Exception {
        return pgVlr(doIndice).pgVlrCor();
    }

    public VCor pgVlrCor(Integer doIndice, Object comPadrao) throws Exception {
        return pgVlr(doIndice).pgVlrCor(comPadrao);
    }

    public VInt pgVlrInt(Integer doIndice) throws Exception {
        return pgVlr(doIndice).pgVlrInt();
    }

    public VInt pgVlrInt(Integer doIndice, Object comPadrao) throws Exception {
        return pgVlr(doIndice).pgVlrInt(comPadrao);
    }

    public VIntLon pgVlrIntLon(Integer doIndice) throws Exception {
        return pgVlr(doIndice).pgVlrIntLon();
    }

    public VIntLon pgVlrIntLon(Integer doIndice, Object comPadrao) throws Exception {
        return pgVlr(doIndice).pgVlrIntLon(comPadrao);
    }

    public VNum pgVlrNum(Integer doIndice) throws Exception {
        return pgVlr(doIndice).pgVlrNum();
    }

    public VNum pgVlrNum(Integer doIndice, Object comPadrao) throws Exception {
        return pgVlr(doIndice).pgVlrNum(comPadrao);
    }

    public VNumLon pgVlrNumLon(Integer doIndice) throws Exception {
        return pgVlr(doIndice).pgVlrNumLon();
    }

    public VNumLon pgVlrNumLon(Integer doIndice, Object comPadrao) throws Exception {
        return pgVlr(doIndice).pgVlrNumLon(comPadrao);
    }

    public VDat pgVlrDat(Integer doIndice) throws Exception {
        return pgVlr(doIndice).pgVlrDat();
    }

    public VDat pgVlrDat(Integer doIndice, Object comPadrao) throws Exception {
        return pgVlr(doIndice).pgVlrDat(comPadrao);
    }

    public VHor pgVlrHor(Integer doIndice) throws Exception {
        return pgVlr(doIndice).pgVlrHor();
    }

    public VHor pgVlrHor(Integer doIndice, Object comPadrao) throws Exception {
        return pgVlr(doIndice).pgVlrHor(comPadrao);
    }

    public VDatHor pgVlrDatHor(Integer doIndice) throws Exception {
        return pgVlr(doIndice).pgVlrDatHor();
    }

    public VDatHor pgVlrDatHor(Integer doIndice, Object comPadrao) throws Exception {
        return pgVlr(doIndice).pgVlrDatHor(comPadrao);
    }

    public VMom pgVlrMom(Integer doIndice) throws Exception {
        return pgVlr(doIndice).pgVlrMom();
    }

    public VMom pgVlrMom(Integer doIndice, Object comPadrao) throws Exception {
        return pgVlr(doIndice).pgVlrMom(comPadrao);
    }

    public VEsc pgVlrEsc(Integer doIndice) throws Exception {
        return pgVlr(doIndice).pgVlrEsc();
    }

    public VEsc pgVlrEsc(Integer doIndice, Object comPadrao) throws Exception {
        return pgVlr(doIndice).pgVlrEsc(comPadrao);
    }

    public VLis pgVlrLis(Integer doIndice) throws Exception {
        return pgVlr(doIndice).pgVlrLis();
    }

    public VLis pgVlrLis(Integer doIndice, Object comPadrao) throws Exception {
        return pgVlr(doIndice).pgVlrLis(comPadrao);
    }

    public VTex pgVlrTex(Integer doIndice) throws Exception {
        return pgVlr(doIndice).pgVlrTex();
    }

    public VTex pgVlrTex(Integer doIndice, Object comPadrao) throws Exception {
        return pgVlr(doIndice).pgVlrTex(comPadrao);
    }

    public VIma pgVlrIma(Integer doIndice) throws Exception {
        return pgVlr(doIndice).pgVlrIma();
    }

    public VIma pgVlrIma(Integer doIndice, Object comPadrao) throws Exception {
        return pgVlr(doIndice).pgVlrIma(comPadrao);
    }

    public VDoc pgVlrDoc(Integer doIndice) throws Exception {
        return pgVlr(doIndice).pgVlrDoc();
    }

    public VDoc pgVlrDoc(Integer doIndice, Object comPadrao) throws Exception {
        return pgVlr(doIndice).pgVlrDoc(comPadrao);
    }

    public VPla pgVlrPla(Integer doIndice) throws Exception {
        return pgVlr(doIndice).pgVlrPla();
    }

    public VPla pgVlrPla(Integer doIndice, Object comPadrao) throws Exception {
        return pgVlr(doIndice).pgVlrPla(comPadrao);
    }

    public VAud pgVlrAud(Integer doIndice) throws Exception {
        return pgVlr(doIndice).pgVlrAud();
    }

    public VAud pgVlrAud(Integer doIndice, Object comPadrao) throws Exception {
        return pgVlr(doIndice).pgVlrAud(comPadrao);
    }

    public VVid pgVlrVid(Integer doIndice) throws Exception {
        return pgVlr(doIndice).pgVlrVid();
    }

    public VVid pgVlrVid(Integer doIndice, Object comPadrao) throws Exception {
        return pgVlr(doIndice).pgVlrVid(comPadrao);
    }

    public VFor pgVlrFor(Integer doIndice) throws Exception {
        return pgVlr(doIndice).pgVlrFor();
    }

    public VFor pgVlrFor(Integer doIndice, Object comPadrao) throws Exception {
        return pgVlr(doIndice).pgVlrFor(comPadrao);
    }

    public VFrm pgVlrFrm(Integer doIndice) throws Exception {
        return pgVlr(doIndice).pgVlrFrm();
    }

    public VFrm pgVlrFrm(Integer doIndice, Object comPadrao) throws Exception {
        return pgVlr(doIndice).pgVlrFrm(comPadrao);
    }

    public VApr pgVlrApr(Integer doIndice) throws Exception {
        return pgVlr(doIndice).pgVlrApr();
    }

    public VApr pgVlrApr(Integer doIndice, Object comPadrao) throws Exception {
        return pgVlr(doIndice).pgVlrApr(comPadrao);
    }

    public VArq pgVlrArq(Integer doIndice) throws Exception {
        return pgVlr(doIndice).pgVlrArq();
    }

    public VArq pgVlrArq(Integer doIndice, Object comPadrao) throws Exception {
        return pgVlr(doIndice).pgVlrArq(comPadrao);
    }

    public Vlr pgVlr(String doNome) {
        return pgVlr(pegaColunaInd(doNome));
    }

    public Vlr pgVlr(String doNome, Object comPadrao) {
        return pgVlr(pegaColunaInd(doNome), comPadrao);
    }

    public VLog pgVlrLog(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrLog();
    }

    public VLog pgVlrLog(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrLog(comPadrao);
    }

    public VCr pgVlrCr(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrCr();
    }

    public VCr pgVlrCr(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrCr(comPadrao);
    }

    public VCrs pgVlrCrs(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrCrs();
    }

    public VCrs pgVlrCrs(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrCrs(comPadrao);
    }

    public VCor pgVlrCor(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrCor();
    }

    public VCor pgVlrCor(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrCor(comPadrao);
    }

    public VInt pgVlrInt(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrInt();
    }

    public VInt pgVlrInt(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrInt(comPadrao);
    }

    public VIntLon pgVlrIntLon(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrIntLon();
    }

    public VIntLon pgVlrIntLon(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrIntLon(comPadrao);
    }

    public VNum pgVlrNum(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrNum();
    }

    public VNum pgVlrNum(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrNum(comPadrao);
    }

    public VNumLon pgVlrNumLon(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrNumLon();
    }

    public VNumLon pgVlrNumLon(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrNumLon(comPadrao);
    }

    public VDat pgVlrDat(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrDat();
    }

    public VDat pgVlrDat(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrDat(comPadrao);
    }

    public VHor pgVlrHor(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrHor();
    }

    public VHor pgVlrHor(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrHor(comPadrao);
    }

    public VDatHor pgVlrDatHor(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrDatHor();
    }

    public VDatHor pgVlrDatHor(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrDatHor(comPadrao);
    }

    public VMom pgVlrMom(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrMom();
    }

    public VMom pgVlrMom(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrMom(comPadrao);
    }

    public VEsc pgVlrEsc(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrEsc();
    }

    public VEsc pgVlrEsc(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrEsc(comPadrao);
    }

    public VLis pgVlrLis(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrLis();
    }

    public VLis pgVlrLis(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrLis(comPadrao);
    }

    public VTex pgVlrTex(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrTex();
    }

    public VTex pgVlrTex(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrTex(comPadrao);
    }

    public VIma pgVlrIma(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrIma();
    }

    public VIma pgVlrIma(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrIma(comPadrao);
    }

    public VDoc pgVlrDoc(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrDoc();
    }

    public VDoc pgVlrDoc(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrDoc(comPadrao);
    }

    public VPla pgVlrPla(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrPla();
    }

    public VPla pgVlrPla(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrPla(comPadrao);
    }

    public VAud pgVlrAud(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrAud();
    }

    public VAud pgVlrAud(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrAud(comPadrao);
    }

    public VVid pgVlrVid(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrVid();
    }

    public VVid pgVlrVid(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrVid(comPadrao);
    }

    public VFor pgVlrFor(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrFor();
    }

    public VFor pgVlrFor(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrFor(comPadrao);
    }

    public VFrm pgVlrFrm(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrFrm();
    }

    public VFrm pgVlrFrm(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrFrm(comPadrao);
    }

    public VApr pgVlrApr(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrApr();
    }

    public VApr pgVlrApr(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrApr(comPadrao);
    }

    public VArq pgVlrArq(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrArq();
    }

    public VArq pgVlrArq(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrArq(comPadrao);
    }

    public String[] analisaTipos() {
        String[] retorno = new String[size()];
        for (int it = 0; it < size(); it++) {
            Vlr vlr = get(it);
            if (vlr == null) {
                retorno[it] = "java.lang.null";
            } else {
                retorno[it] = vlr.getClass().getCanonicalName();
            }
        }
        return retorno;
    }

    public void imprimi() {
        Utl.imp(toString());
    }

    @Override
    public String toString() {
        String retorno = "Vlrs: ";
        int idx = 0;
        for (Vlr vlr : this) {
            String nome = "";
            if (nomes != null) {
                if (idx < nomes.length) {
                    nome = nomes[idx] + " = ";
                }
            }
            retorno += UtlTex.quebra() + "  += " + nome + UtlCrs.pega(vlr);
            idx++;
        }
        return retorno;
    }

    public String descreve() {
        String linha = Marcados.prepara(Partes.junta(nomes), "nomes");
        linha += UtlTex.quebra() + Marcados.prepara(Partes.juntaVals(toArray()), "vlrs");
        return Marcados.prepara(linha, "linha");
    }

    public Object[] paraPrimitivos() {
        Object[] retorno = new Object[size()];
        for (int i = 0; i < size(); i++) {
            retorno[i] = get(i).pg();
        }
        return retorno;
    }

    public Vlr[] paraArray() {
        Vlr[] retorno = new Vlr[size()];
        for (int i = 0; i < size(); i++) {
            retorno[i] = get(i);
        }
        return retorno;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Vlrs) {
            Vlrs ob = (Vlrs) o;
            if (Objects.equals(tamanho(), ob.tamanho())) {
                for (int iv = 0; iv < tamanho(); iv++) {
                    if (!Objects.equals(pgVlr(iv), ob.pgVlr(iv))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public String pegaPrimeirosCrs(Integer dosPrimeiros) {
        String[] retorno = new String[dosPrimeiros];
        for (int iv = 0; iv < dosPrimeiros; iv++) {
            retorno[iv] = pgVlr(iv, "").pgCrs();
        }
        return Partes.junta(retorno);
    }
}
