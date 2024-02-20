package pin.libvlr;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import pin.libutl.Utl;
import pin.libutl.UtlArq;
import pin.libutl.UtlBin;
import pin.libutl.UtlCor;
import pin.libutl.UtlCr;
import pin.libutl.UtlCrs;
import pin.libutl.UtlDat;
import pin.libutl.UtlDatHor;
import pin.libutl.UtlHor;
import pin.libutl.UtlIma;
import pin.libutl.UtlInt;
import pin.libutl.UtlIntLon;
import pin.libutl.UtlLis;
import pin.libutl.UtlLog;
import pin.libutl.UtlMom;
import pin.libutl.UtlNum;
import pin.libutl.UtlNumLon;

public class Vlr {

    private volatile Object valor;

    public Vlr() {
        valor = null;
    }

    public Vlr(Object doValor) {
        valor = doValor;
    }

    public static Vlr descrito(String nosCaracteres) throws Exception {
        return new Vlr(UtlBin.descrito(nosCaracteres));
    }

    public Object pg() {
        return pg((Object) null);
    }

    public Object pg(Object comPadrao) {
        if (valor != null) {
            return valor;
        } else {
            return comPadrao;
        }
    }

    public <T> T pg(Class<? extends T> daClasse) throws Exception {
        Method[] metodos = this.getClass().getMethods();
        Class dpPar = UtlBin.pegaDaPrimitiva(daClasse);
        for (Method metodo : metodos) {
            if (metodo.getName().startsWith("pg")) {
                if (metodo.getParameterCount() == 0) {
                    if (Objects.equals(dpPar, UtlBin.pegaDaPrimitiva(metodo.getReturnType()))) {
                        return (T) metodo.invoke(this);
                    }
                }
            }
        }
        return UtlBin.pega(daClasse, pg());
    }

    public Vlr md(Object para) throws Exception {
        valor = para;
        return this;
    }

    public String paraConsSQL() {
        return Utl.pegaConsSQL(valor);
    }

    public Object paraParamSQL() {
        return Utl.pegaParamSQL(valor);
    }

    public Boolean vazio() {
        return pg() == null;
    }

    public Boolean tem() {
        return pg() != null;
    }

    public void imprimi() {
        Utl.imp(toString());
    }

    public String descreve() {
        return UtlBin.descreve(valor);
    }

    @Override
    public String toString() {
        return UtlCrs.pega(valor);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vlr) {
            obj = ((Vlr) obj).pg();
        }
        return Objects.equals(pg(), obj);
    }

    @Override
    public int hashCode() {
        int hash = 9;
        hash = 84 * hash + Objects.hashCode(pg());
        return hash;
    }

    public VLog pgVlrLog() throws Exception {
        if (this instanceof VLog) {
            return (VLog) this;
        } else {
            return new VLog(this);
        }
    }

    public VLog pgVlrLog(Object comPadrao) throws Exception {
        if (vazio()) {
            return new VLog(comPadrao);
        } else {
            return pgVlrLog();
        }
    }

    public VCr pgVlrCr() throws Exception {
        if (this instanceof VCr) {
            return (VCr) this;
        } else {
            return new VCr(this);
        }
    }

    public VCr pgVlrCr(Object comPadrao) throws Exception {
        if (vazio()) {
            return new VCr(comPadrao);
        } else {
            return pgVlrCr();
        }
    }

    public VCrs pgVlrCrs() throws Exception {
        if (this instanceof VCrs) {
            return (VCrs) this;
        } else {
            return new VCrs(this);
        }
    }

    public VCrs pgVlrCrs(Object comPadrao) throws Exception {
        if (vazio()) {
            return new VCrs(comPadrao);
        } else {
            return pgVlrCrs();
        }
    }

    public VCor pgVlrCor() throws Exception {
        if (this instanceof VCor) {
            return (VCor) this;
        } else {
            return new VCor(this);
        }
    }

    public VCor pgVlrCor(Object comPadrao) throws Exception {
        if (vazio()) {
            return new VCor(comPadrao);
        } else {
            return pgVlrCor();
        }
    }

    public VInt pgVlrInt() throws Exception {
        if (this instanceof VInt) {
            return (VInt) this;
        } else {
            return new VInt(this);
        }
    }

    public VInt pgVlrInt(Object comPadrao) throws Exception {
        if (vazio()) {
            return new VInt(comPadrao);
        } else {
            return pgVlrInt();
        }
    }

    public VIntLon pgVlrIntLon() throws Exception {
        if (this instanceof VIntLon) {
            return (VIntLon) this;
        } else {
            return new VIntLon(this);
        }
    }

    public VIntLon pgVlrIntLon(Object comPadrao) throws Exception {
        if (vazio()) {
            return new VIntLon(comPadrao);
        } else {
            return pgVlrIntLon();
        }
    }

    public VNum pgVlrNum() throws Exception {
        if (this instanceof VNum) {
            return (VNum) this;
        } else {
            return new VNum(this);
        }
    }

    public VNum pgVlrNum(Object comPadrao) throws Exception {
        if (vazio()) {
            return new VNum(comPadrao);
        } else {
            return pgVlrNum();
        }
    }

    public VNumLon pgVlrNumLon() throws Exception {
        if (this instanceof VNumLon) {
            return (VNumLon) this;
        } else {
            return new VNumLon(this);
        }
    }

    public VNumLon pgVlrNumLon(Object comPadrao) throws Exception {
        if (vazio()) {
            return new VNumLon(comPadrao);
        } else {
            return pgVlrNumLon();
        }
    }

    public VDat pgVlrDat() throws Exception {
        if (this instanceof VDat) {
            return (VDat) this;
        } else {
            return new VDat(this);
        }
    }

    public VDat pgVlrDat(Object comPadrao) throws Exception {
        if (vazio()) {
            return new VDat(comPadrao);
        } else {
            return pgVlrDat();
        }
    }

    public VHor pgVlrHor() throws Exception {
        if (this instanceof VHor) {
            return (VHor) this;
        } else {
            return new VHor(this);
        }
    }

    public VHor pgVlrHor(Object comPadrao) throws Exception {
        if (vazio()) {
            return new VHor(comPadrao);
        } else {
            return pgVlrHor();
        }
    }

    public VDatHor pgVlrDatHor() throws Exception {
        if (this instanceof VDatHor) {
            return (VDatHor) this;
        } else {
            return new VDatHor(this);
        }
    }

    public VDatHor pgVlrDatHor(Object comPadrao) throws Exception {
        if (vazio()) {
            return new VDatHor(comPadrao);
        } else {
            return pgVlrDatHor();
        }
    }

    public VMom pgVlrMom() throws Exception {
        if (this instanceof VMom) {
            return (VMom) this;
        } else {
            return new VMom(this);
        }
    }

    public VMom pgVlrMom(Object comPadrao) throws Exception {
        if (vazio()) {
            return new VMom(comPadrao);
        } else {
            return pgVlrMom();
        }
    }

    public VEsc pgVlrEsc() throws Exception {
        if (this instanceof VEsc) {
            return (VEsc) this;
        } else {
            return new VEsc(this);
        }
    }

    public VEsc pgVlrEsc(Object comPadrao) throws Exception {
        if (vazio()) {
            return new VEsc(comPadrao);
        } else {
            return pgVlrEsc();
        }
    }

    public VLis pgVlrLis() throws Exception {
        if (this instanceof VLis) {
            return (VLis) this;
        } else {
            return new VLis(this);
        }
    }

    public VLis pgVlrLis(Object comPadrao) throws Exception {
        if (vazio()) {
            return new VLis(comPadrao);
        } else {
            return pgVlrLis();
        }
    }

    public VTex pgVlrTex() throws Exception {
        if (this instanceof VTex) {
            return (VTex) this;
        } else {
            return new VTex(this);
        }
    }

    public VTex pgVlrTex(Object comPadrao) throws Exception {
        if (vazio()) {
            return new VTex(comPadrao);
        } else {
            return pgVlrTex();
        }
    }

    public VIma pgVlrIma() throws Exception {
        if (this instanceof VIma) {
            return (VIma) this;
        } else {
            return new VIma(this);
        }
    }

    public VIma pgVlrIma(Object comPadrao) throws Exception {
        if (vazio()) {
            return new VIma(comPadrao);
        } else {
            return pgVlrIma();
        }
    }

    public VDoc pgVlrDoc() throws Exception {
        if (this instanceof VDoc) {
            return (VDoc) this;
        } else {
            return new VDoc(this);
        }
    }

    public VDoc pgVlrDoc(Object comPadrao) throws Exception {
        if (vazio()) {
            return new VDoc(comPadrao);
        } else {
            return pgVlrDoc();
        }
    }

    public VPla pgVlrPla() throws Exception {
        if (this instanceof VPla) {
            return (VPla) this;
        } else {
            return new VPla(this);
        }
    }

    public VPla pgVlrPla(Object comPadrao) throws Exception {
        if (vazio()) {
            return new VPla(comPadrao);
        } else {
            return pgVlrPla();
        }
    }

    public VAud pgVlrAud() throws Exception {
        if (this instanceof VAud) {
            return (VAud) this;
        } else {
            return new VAud(this);
        }
    }

    public VAud pgVlrAud(Object comPadrao) throws Exception {
        if (vazio()) {
            return new VAud(comPadrao);
        } else {
            return pgVlrAud();
        }
    }

    public VVid pgVlrVid() throws Exception {
        if (this instanceof VVid) {
            return (VVid) this;
        } else {
            return new VVid(this);
        }
    }

    public VVid pgVlrVid(Object comPadrao) throws Exception {
        if (vazio()) {
            return new VVid(comPadrao);
        } else {
            return pgVlrVid();
        }
    }

    public VFor pgVlrFor() throws Exception {
        if (this instanceof VFor) {
            return (VFor) this;
        } else {
            return new VFor(this);
        }
    }

    public VFor pgVlrFor(Object comPadrao) throws Exception {
        if (vazio()) {
            return new VFor(comPadrao);
        } else {
            return pgVlrFor();
        }
    }

    public VFrm pgVlrFrm() throws Exception {
        if (this instanceof VFrm) {
            return (VFrm) this;
        } else {
            return new VFrm(this);
        }
    }

    public VFrm pgVlrFrm(Object comPadrao) throws Exception {
        if (vazio()) {
            return new VFrm(comPadrao);
        } else {
            return pgVlrFrm();
        }
    }

    public VApr pgVlrApr() throws Exception {
        if (this instanceof VApr) {
            return (VApr) this;
        } else {
            return new VApr(this);
        }
    }

    public VApr pgVlrApr(Object comPadrao) throws Exception {
        if (vazio()) {
            return new VApr(comPadrao);
        } else {
            return pgVlrApr();
        }
    }

    public VArq pgVlrArq() throws Exception {
        if (this instanceof VArq) {
            return (VArq) this;
        } else {
            return new VArq(this);
        }
    }

    public VArq pgVlrArq(Object comPadrao) throws Exception {
        if (vazio()) {
            return new VArq(comPadrao);
        } else {
            return pgVlrArq();
        }
    }

    public Boolean pgLog() {
        return UtlLog.pega(this);
    }

    public Boolean pgLog(Boolean comPadrao) {
        return UtlLog.pega(this, comPadrao);
    }

    public Character pgCr() {
        return UtlCr.pega(this);
    }

    public Character pgCr(Character comPadrao) {
        return UtlCr.pega(this, comPadrao);
    }

    public String pgCrs() {
        return UtlCrs.pega(this);
    }

    public String pgCrs(String comPadrao) {
        return UtlCrs.pega(this, comPadrao);
    }

    public Color pgCor() {
        return UtlCor.pega(this);
    }

    public Color pgCor(Color comPadrao) {
        return UtlCor.pega(this, comPadrao);
    }

    public Integer pgInt() {
        return UtlInt.pega(this);
    }

    public Integer pgInt(Integer comPadrao) {
        return UtlInt.pega(this, comPadrao);
    }

    public Long pgIntLon() {
        return UtlIntLon.pega(this);
    }

    public Long pgIntLon(Long comPadrao) {
        return UtlIntLon.pega(this, comPadrao);
    }

    public Float pgNum() {
        return UtlNum.pega(this);
    }

    public Float pgNum(Float comPadrao) {
        return UtlNum.pega(this, comPadrao);
    }

    public Double pgNumLon() {
        return UtlNumLon.pega(this);
    }

    public Double pgNumLon(Double comPadrao) {
        return UtlNumLon.pega(this, comPadrao);
    }

    public java.util.Date pgDat() {
        return UtlDat.pega(this);
    }

    public java.util.Date pgDat(java.util.Date comPadrao) {
        return UtlDat.pega(this, comPadrao);
    }

    public java.util.Date pgHor() {
        return UtlHor.pega(this);
    }

    public java.util.Date pgHor(java.util.Date comPadrao) {
        return UtlHor.pega(this, comPadrao);
    }

    public java.util.Date pgDatHor() {
        return UtlDatHor.pega(this);
    }

    public java.util.Date pgDatHor(java.util.Date comPadrao) {
        return UtlDatHor.pega(this, comPadrao);
    }

    public java.util.Date pgMom() {
        return UtlMom.pega(this);
    }

    public java.util.Date pgMom(java.util.Date comPadrao) {
        return UtlMom.pega(this, comPadrao);
    }

    public List<Object> pgLis() {
        return UtlLis.pega(this);
    }

    public List<Object> pgLis(List<Object> comPadrao) {
        return UtlLis.pega(this, comPadrao);
    }

    public <T> List<T> pgLis(Class<? extends T> naClasse) {
        return UtlLis.copia(pgLis(), naClasse);
    }

    public <T> List<T> pgLis(Class<? extends T> naClasse, List<T> comPadrao) {
        return UtlLis.copia(pgLis(), naClasse, comPadrao);
    }

    public Object[] pgMat() {
        return UtlLis.pegaMat(pgLis());
    }

    public Object[] pgMat(Object[] comPadrao) {
        return UtlLis.pegaMat(pgLis(), comPadrao);
    }

    public <T> T[] pgMat(Class<? extends T> naClasse) throws Exception {
        return UtlLis.pegaMat(pgLis(), naClasse);
    }

    public <T> T[] pgMat(Class<? extends T> naClasse, T[] comPadrao) throws Exception {
        return UtlLis.pegaMat(pgLis(), naClasse, comPadrao);
    }

    public String[] pgMatCrs() {
        return UtlLis.pegaMatCrs(pgLis());
    }

    public String[] pgMatCrs(String[] comPadrao) {
        return UtlLis.pegaMatCrs(pgLis(), comPadrao);
    }

    public BufferedImage pgIma() {
        return UtlIma.pega(this);
    }

    public BufferedImage pgIma(BufferedImage comPadrao) {
        return UtlIma.pega(this, comPadrao);
    }

    public byte[] pgArq() {
        return UtlArq.pega(this);
    }

    public byte[] pgArq(byte[] comPadrao) {
        return UtlArq.pega(this, comPadrao);
    }

    public File pgArqCam() throws Exception {
        return pgArqCam(null);
    }

    public File pgArqCam(File comPadrao) throws Exception {
        File retorno = comPadrao;
        Vlr vlr = this;
        if (vlr != null) {
            if (vlr instanceof VArq) {
                retorno = ((VArq) vlr).pgArqCam(comPadrao);
            } else {
                retorno = UtlArq.pegaCam(this, comPadrao);
            }
        }
        return retorno;
    }

    public static Vlr novo(Object doValor) {
        if (doValor != null) {
            if (doValor instanceof Vlr) {
                return (Vlr) doValor;
            } else {
                if (UtlLog.eh(doValor)) {
                    return new VLog(doValor);
                } else if (UtlCr.eh(doValor)) {
                    return new VCr(doValor);
                } else if (UtlCrs.eh(doValor)) {
                    return new VCrs(doValor);
                } else if (UtlInt.eh(doValor)) {
                    return new VInt(doValor);
                } else if (UtlIntLon.eh(doValor)) {
                    return new VIntLon(doValor);
                } else if (UtlNum.eh(doValor)) {
                    return new VNum(doValor);
                } else if (UtlNumLon.eh(doValor)) {
                    return new VNumLon(doValor);
                } else if (UtlMom.eh(doValor)) {
                    return new VMom(doValor);
                } else if (UtlDatHor.eh(doValor)) {
                    return new VDatHor(doValor);
                } else if (UtlDat.eh(doValor)) {
                    return new VDat(doValor);
                } else if (UtlHor.eh(doValor)) {
                    return new VHor(doValor);
                } else if (UtlArq.eh(doValor)) {
                    return new VArq(doValor);
                } else if (UtlLis.eh(doValor)) {
                    return new VLis(doValor);
                } else {
                    return new Vlr(doValor);
                }
            }
        } else {
            return new Vlr(null);
        }
    }

    public static Object pNativo(Object doValor) {
        if (doValor instanceof Vlr) {
            return ((Vlr) doValor).pg();
        } else {
            return doValor;
        }
    }

}
