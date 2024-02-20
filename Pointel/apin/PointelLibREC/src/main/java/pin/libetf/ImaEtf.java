package pin.libetf;

import pin.libamg.EdtIma;
import pin.libbas.Dados;
import pin.libvlr.VIma;

public class ImaEtf extends EdtIntf {

    public ImaEtf() {
        super(Dados.Tp.Ima);
    }

    public ImaEtf(String comDescricao) {
        super(Dados.Tp.Ima, comDescricao);
    }

    @Override
    public ImaEtf mInicial(Object oValor) {
        cmps().pega("edt").mVlrInicial(oValor);
        return this;
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Imagem Etf");
    }

    @Override
    public ImaEtf mostra() throws Exception {
        super.mostra();
        return this;
    }

    @Override
    public ImaEtf mostra(String comTitulo) throws Exception {
        super.mostra(comTitulo);
        return this;
    }

    @Override
    public EdtIma edt() {
        return (EdtIma) super.edt();
    }

    @Override
    public ImaEtf abre(Object oValor) throws Exception {
        super.abre(oValor);
        return this;
    }

    @Override
    public synchronized VIma pgVlr() throws Exception {
        return edt().pgVlrIma();
    }

}
