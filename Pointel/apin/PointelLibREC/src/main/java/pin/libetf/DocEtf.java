package pin.libetf;

import pin.libamg.EdtDoc;
import pin.libbas.Dados;
import pin.libvlr.VDoc;

public class DocEtf extends EdtIntf {

    public DocEtf() {
        super(Dados.Tp.Doc);
    }

    public DocEtf(String comDescricao) {
        super(Dados.Tp.Doc, comDescricao);
    }

    @Override
    public DocEtf mInicial(Object oValor) {
        cmps().pega("edt").mVlrInicial(oValor);
        return this;
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Documento Etf");
    }

    @Override
    public DocEtf mostra() throws Exception {
        super.mostra();
        return this;
    }

    @Override
    public DocEtf mostra(String comTitulo) throws Exception {
        super.mostra(comTitulo);
        return this;
    }

    @Override
    public EdtDoc edt() {
        return (EdtDoc) super.edt();
    }

    @Override
    public DocEtf abre(Object oValor) throws Exception {
        super.abre(oValor);
        return this;
    }

    @Override
    public synchronized VDoc pgVlr() throws Exception {
        return cmps().pega("edt").pEdt().pgVlrDoc();
    }

}
