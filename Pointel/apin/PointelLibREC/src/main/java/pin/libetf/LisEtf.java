package pin.libetf;

import java.util.List;
import javax.swing.AbstractAction;
import pin.libamg.EdtLis;
import pin.libbas.Dados;
import pin.libvlr.VLis;

public class LisEtf extends EdtIntf {

    public LisEtf() {
        super(Dados.Tp.Lis);
    }

    public LisEtf(String comDescricao) {
        super(Dados.Tp.Lis, comDescricao);
    }

    @Override
    public LisEtf mInicial(Object oValor) {
        cmps().pega("edt").mVlrInicial(oValor);
        return this;
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Lista Etf");
    }

    @Override
    public LisEtf mostra() throws Exception {
        super.mostra();
        return this;
    }

    @Override
    public LisEtf mostra(String comTitulo) throws Exception {
        super.mostra(comTitulo);
        return this;
    }

    @Override
    public EdtLis edt() {
        return (EdtLis) super.edt();
    }

    @Override
    public LisEtf abre(Object oValor) throws Exception {
        super.abre(oValor);
        return this;
    }

    @Override
    public synchronized VLis pgVlr() throws Exception {
        return cmps().pega("edt").pEdt().pgVlrLis();
    }

    public Boolean temSelecionado() {
        return ((EdtLis) cmps().pega("edt").pEdt()).temSelecionado();
    }

    public int pSelecionado() {
        return ((EdtLis) cmps().pega("edt").pEdt()).pSelecionado();
    }

    public int[] pSelecionados() {
        return ((EdtLis) cmps().pega("edt").pEdt()).pSelecionados();
    }

    public Object pItem() {
        return ((EdtLis) cmps().pega("edt").pEdt()).pItem();
    }

    public Object pItem(int doIndice) {
        return ((EdtLis) cmps().pega("edt").pEdt()).pItem(doIndice);
    }

    public List<Object> pItens() {
        return ((EdtLis) cmps().pega("edt").pEdt()).pItens();
    }

    public List<Object> pItens(int[] dosIndices) {
        return ((EdtLis) cmps().pega("edt").pEdt()).pItens(dosIndices);
    }

    public LisEtf botaItem(Object oItem) {
        ((EdtLis) cmps().pega("edt").pEdt()).botaItem(oItem);
        return this;
    }

    public LisEtf mItem(Object para) {
        ((EdtLis) cmps().pega("edt").pEdt()).mItem(para);
        return this;
    }

    public LisEtf mItem(Object para, Integer doIndice) {
        ((EdtLis) cmps().pega("edt").pEdt()).mItem(para, doIndice);
        return this;
    }

    public LisEtf tiraItem() {
        ((EdtLis) cmps().pega("edt").pEdt()).tiraItem();
        return this;
    }

    public LisEtf tiraItem(int doIndice) {
        ((EdtLis) cmps().pega("edt").pEdt()).tiraItem(doIndice);
        return this;
    }

    public LisEtf tiraItem(Object oItem) {
        ((EdtLis) cmps().pega("edt").pEdt()).tiraItem(oItem);
        return this;
    }

    public LisEtf tiraItens() {
        ((EdtLis) cmps().pega("edt").pEdt()).tiraItens();
        return this;
    }

    public LisEtf tiraItens(int[] dosIndices) {
        ((EdtLis) cmps().pega("edt").pEdt()).tiraItens(dosIndices);
        return this;
    }

    public LisEtf tiraItens(List<Object> osItens) {
        ((EdtLis) cmps().pega("edt").pEdt()).tiraItens(osItens);
        return this;
    }

    public Boolean contem(Object oValor) {
        return ((EdtLis) cmps().pega("edt").pEdt()).contem(oValor);
    }

    public String[] pgMatCrs() {
        return ((EdtLis) cmps().pega("edt").pEdt()).pgMatCrs();
    }

    public VLis pgVlrLis() throws Exception {
        return ((EdtLis) cmps().pega("edt").pEdt()).pgVlrLis();
    }

    public List<Object> pgLis() throws Exception {
        return ((EdtLis) cmps().pega("edt").pEdt()).pgVlrLis().pgLis();
    }

    public <T> List<T> pgLis(Class<? extends T> naClasse) throws Exception {
        return ((EdtLis) cmps().pega("edt").pEdt()).pgVlrLis().pgLis(naClasse);
    }

    public Object[] pgMat() throws Exception {
        return ((EdtLis) cmps().pega("edt").pEdt()).pgVlrLis().pgMat();
    }

    public <T> T[] pgMat(Class<? extends T> naClasse) throws Exception {
        return ((EdtLis) cmps().pega("edt").pEdt()).pgVlrLis().pgMat(naClasse);
    }

    public LisEtf mAoAdicionar(AbstractAction aAcao) {
        ((EdtLis) cmps().pega("edt").pEdt()).mAoAdicionar(aAcao);
        return this;
    }

    public LisEtf mAoAlterar(AbstractAction aAcao) {
        ((EdtLis) cmps().pega("edt").pEdt()).mAoAlterar(aAcao);
        return this;
    }

    public LisEtf mAoRemover(AbstractAction aAcao) {
        ((EdtLis) cmps().pega("edt").pEdt()).mAoRemover(aAcao);
        return this;
    }

}
