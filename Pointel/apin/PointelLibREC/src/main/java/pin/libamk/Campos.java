package pin.libamk;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import pin.libamg.Edt;
import pin.libamg.EdtPai;
import pin.libbas.ParsLista;
import pin.libvlr.VApr;
import pin.libvlr.VArq;
import pin.libvlr.VAud;
import pin.libvlr.VCor;
import pin.libvlr.VCr;
import pin.libvlr.VCrs;
import pin.libvlr.VDat;
import pin.libvlr.VDatHor;
import pin.libvlr.VDoc;
import pin.libvlr.VEsc;
import pin.libvlr.VFor;
import pin.libvlr.VFrm;
import pin.libvlr.VHor;
import pin.libvlr.VIma;
import pin.libvlr.VInt;
import pin.libvlr.VIntLon;
import pin.libvlr.VLis;
import pin.libvlr.VLog;
import pin.libvlr.VMom;
import pin.libvlr.VNum;
import pin.libvlr.VNumLon;
import pin.libvlr.VPla;
import pin.libvlr.VTex;
import pin.libvlr.VVid;
import pin.libvlr.Vlr;

public class Campos extends ArrayList<Cmp> {

    public JTabbedPane jtbAbas;

    public Campos() {
        super();
    }

    public Campos(Cmp... dosCampos) {
        super(Arrays.asList(dosCampos));
    }

    public Campos(List<Cmp> dosCampos) {
        super(dosCampos);
    }

    public EdtPai constroi(Object comOrigem) throws Exception {
        return new Construtor(this).constroi(comOrigem);
    }

    private void normilizaLocais() {
        Integer ord = 1;
        for (Cmp cmp : this) {
            if (cmp.pOrdem() == null) {
                cmp.mOrdem(new Double(ord));
            }
            ord++;
        }
    }

    public Campos orderna() {
        normilizaLocais();
        Collections.sort(this, new Comparator<Cmp>() {
            @Override
            public int compare(Cmp cmp1, Cmp cmp2) {
                return cmp1.pOrdem().compareTo(cmp2.pOrdem());
            }
        });
        return this;
    }

    public Cmp pAncestral(Cmp doCampo) {
        Cmp retorno = doCampo;
        if (retorno.temPai()) {
            retorno = pAncestral(pega(retorno.pPai()));
        }
        return retorno;
    }

    public Cmp pega(String comNome) {
        Cmp retorno = null;
        for (Cmp cmp : this) {
            if (comNome.equals(cmp.pNome())) {
                retorno = cmp;
                break;
            }
        }
        return retorno;
    }

    public Cmp pega(Integer doIndice) {
        return get(doIndice);
    }

    public Cmp pega(Edt doEditor) {
        for (Cmp cmp : this) {
            if (Objects.equals(doEditor, cmp.pEdt())) {
                return cmp;
            }
        }
        return null;
    }

    public Cmp pega(JComponent doComponente) {
        for (Cmp cmp : this) {
            if (Objects.equals(doComponente, cmp.pEdt().controle())) {
                return cmp;
            }
        }
        return null;
    }

    public Cmp pega(Component doEditor) {
        Cmp retorno = null;
        for (Cmp cmp : this) {
            if (doEditor.equals(cmp.pEdt().controle())) {
                retorno = cmp;
                break;
            }
        }
        return retorno;
    }

    public Edt pEdt(String doNome) {
        return pega(doNome).pEdt();
    }

    public String pAbaSelecionada(String dasAbas) {
        for (Cmp cmp : this) {
            if (Objects.equals(dasAbas, cmp.pNome())) {
                JTabbedPane abs = (JTabbedPane) cmp.pEdt().controle();
                return abs.getTitleAt(abs.getSelectedIndex());
            }
        }
        return null;
    }

    public Integer pAbaSelecionadaIdx(String dasAbas) {
        for (Cmp cmp : this) {
            if (Objects.equals(dasAbas, cmp.pNome())) {
                JTabbedPane abs = (JTabbedPane) cmp.pEdt().controle();
                return abs.getSelectedIndex();
            }
        }
        return null;
    }

    public Campos limpa() throws Exception {
        for (Cmp campo : this) {
            campo.pEdt().limpa();
        }
        return this;
    }

    public String paraConsSQL(String doCampo) throws Exception {
        return pgVlr(doCampo).paraConsSQL();
    }

    public Object paraParamSQL(String doCampo) throws Exception {
        return pgVlr(doCampo).paraParamSQL();
    }

    public Campos mdVlr(String doCampo, Object para) throws Exception {
        pega(doCampo).pEdt().mdVlr(para);
        return this;
    }

    public synchronized Vlr pgVlr(String doCampo) throws Exception {
        return pega(doCampo).pEdt().pgVlr();
    }

    public synchronized Vlr pgVlr(String doCampo, Object comPadrao) throws Exception {
        return pega(doCampo).pEdt().pgVlr(comPadrao);
    }

    public VLog pgVlrLog(String doCampo) throws Exception {
        return pgVlr(doCampo).pgVlrLog();
    }

    public VLog pgVlrLog(String doCampo, Object comPadrao) throws Exception {
        return pgVlr(doCampo).pgVlrLog(comPadrao);
    }

    public VCr pgVlrCr(String doCampo) throws Exception {
        return pgVlr(doCampo).pgVlrCr();
    }

    public VCr pgVlrCr(String doCampo, Object comPadrao) throws Exception {
        return pgVlr(doCampo).pgVlrCr(comPadrao);
    }

    public VCrs pgVlrCrs(String doCampo) throws Exception {
        return pgVlr(doCampo).pgVlrCrs();
    }

    public VCrs pgVlrCrs(String doCampo, Object comPadrao) throws Exception {
        return pgVlr(doCampo).pgVlrCrs(comPadrao);
    }

    public VCor pgVlrCor(String doCampo) throws Exception {
        return pgVlr(doCampo).pgVlrCor();
    }

    public VCor pgVlrCor(String doCampo, Object comPadrao) throws Exception {
        return pgVlr(doCampo).pgVlrCor(comPadrao);
    }

    public VInt pgVlrInt(String doCampo) throws Exception {
        return pgVlr(doCampo).pgVlrInt();
    }

    public VInt pgVlrInt(String doCampo, Object comPadrao) throws Exception {
        return pgVlr(doCampo).pgVlrInt(comPadrao);
    }

    public VIntLon pgVlrIntLon(String doCampo) throws Exception {
        return pgVlr(doCampo).pgVlrIntLon();
    }

    public VIntLon pgVlrIntLon(String doCampo, Object comPadrao) throws Exception {
        return pgVlr(doCampo).pgVlrIntLon(comPadrao);
    }

    public VNum pgVlrNum(String doCampo) throws Exception {
        return pgVlr(doCampo).pgVlrNum();
    }

    public VNum pgVlrNum(String doCampo, Object comPadrao) throws Exception {
        return pgVlr(doCampo).pgVlrNum(comPadrao);
    }

    public VNumLon pgVlrNumLon(String doCampo) throws Exception {
        return pgVlr(doCampo).pgVlrNumLon();
    }

    public VNumLon pgVlrNumLon(String doCampo, Object comPadrao) throws Exception {
        return pgVlr(doCampo).pgVlrNumLon(comPadrao);
    }

    public VDat pgVlrDat(String doCampo) throws Exception {
        return pgVlr(doCampo).pgVlrDat();
    }

    public VDat pgVlrDat(String doCampo, Object comPadrao) throws Exception {
        return pgVlr(doCampo).pgVlrDat(comPadrao);
    }

    public VHor pgVlrHor(String doCampo) throws Exception {
        return pgVlr(doCampo).pgVlrHor();
    }

    public VHor pgVlrHor(String doCampo, Object comPadrao) throws Exception {
        return pgVlr(doCampo).pgVlrHor(comPadrao);
    }

    public VDatHor pgVlrDatHor(String doCampo) throws Exception {
        return pgVlr(doCampo).pgVlrDatHor();
    }

    public VDatHor pgVlrDatHor(String doCampo, Object comPadrao) throws Exception {
        return pgVlr(doCampo).pgVlrDatHor(comPadrao);
    }

    public VMom pgVlrMom(String doCampo) throws Exception {
        return pgVlr(doCampo).pgVlrMom();
    }

    public VMom pgVlrMom(String doCampo, Object comPadrao) throws Exception {
        return pgVlr(doCampo).pgVlrMom(comPadrao);
    }

    public VEsc pgVlrEsc(String doCampo) throws Exception {
        return pgVlr(doCampo).pgVlrEsc();
    }

    public VEsc pgVlrEsc(String doCampo, Object comPadrao) throws Exception {
        return pgVlr(doCampo).pgVlrEsc(comPadrao);
    }

    public VLis pgVlrLis(String doCampo) throws Exception {
        return pgVlr(doCampo).pgVlrLis();
    }

    public VLis pgVlrLis(String doCampo, Object comPadrao) throws Exception {
        return pgVlr(doCampo).pgVlrLis(comPadrao);
    }

    public VTex pgVlrTex(String doCampo) throws Exception {
        return pgVlr(doCampo).pgVlrTex();
    }

    public VTex pgVlrTex(String doCampo, Object comPadrao) throws Exception {
        return pgVlr(doCampo).pgVlrTex(comPadrao);
    }

    public VIma pgVlrIma(String doCampo) throws Exception {
        return pgVlr(doCampo).pgVlrIma();
    }

    public VIma pgVlrIma(String doCampo, Object comPadrao) throws Exception {
        return pgVlr(doCampo).pgVlrIma(comPadrao);
    }

    public VDoc pgVlrDoc(String doCampo) throws Exception {
        return pgVlr(doCampo).pgVlrDoc();
    }

    public VDoc pgVlrDoc(String doCampo, Object comPadrao) throws Exception {
        return pgVlr(doCampo).pgVlrDoc(comPadrao);
    }

    public VPla pgVlrPla(String doCampo) throws Exception {
        return pgVlr(doCampo).pgVlrPla();
    }

    public VPla pgVlrPla(String doCampo, Object comPadrao) throws Exception {
        return pgVlr(doCampo).pgVlrPla(comPadrao);
    }

    public VAud pgVlrAud(String doCampo) throws Exception {
        return pgVlr(doCampo).pgVlrAud();
    }

    public VAud pgVlrAud(String doCampo, Object comPadrao) throws Exception {
        return pgVlr(doCampo).pgVlrAud(comPadrao);
    }

    public VVid pgVlrVid(String doCampo) throws Exception {
        return pgVlr(doCampo).pgVlrVid();
    }

    public VVid pgVlrVid(String doCampo, Object comPadrao) throws Exception {
        return pgVlr(doCampo).pgVlrVid(comPadrao);
    }

    public VFor pgVlrFor(String doCampo) throws Exception {
        return pgVlr(doCampo).pgVlrFor();
    }

    public VFor pgVlrFor(String doCampo, Object comPadrao) throws Exception {
        return pgVlr(doCampo).pgVlrFor(comPadrao);
    }

    public VFrm pgVlrFrm(String doCampo) throws Exception {
        return pgVlr(doCampo).pgVlrFrm();
    }

    public VFrm pgVlrFrm(String doCampo, Object comPadrao) throws Exception {
        return pgVlr(doCampo).pgVlrFrm(comPadrao);
    }

    public VApr pgVlrApr(String doCampo) throws Exception {
        return pgVlr(doCampo).pgVlrApr();
    }

    public VApr pgVlrApr(String doCampo, Object comPadrao) throws Exception {
        return pgVlr(doCampo).pgVlrApr(comPadrao);
    }

    public VArq pgVlrArq(String doCampo) throws Exception {
        return pgVlr(doCampo).pgVlrArq();
    }

    public VArq pgVlrArq(String doCampo, Object comPadrao) throws Exception {
        return pgVlr(doCampo).pgVlrArq(comPadrao);
    }
}
