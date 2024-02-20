package pin.libetf;

import pin.libamg.EdtCor;
import pin.libbas.Dados;
import pin.libvlr.VCor;

public class CorEtf extends EdtIntf {

    public CorEtf() {
        super(Dados.Tp.Cor);
    }

    public CorEtf(String comDescricao) {
        super(Dados.Tp.Cor, comDescricao);
    }

    @Override
    public CorEtf mInicial(Object oValor) {
        cmps().pega("edt").mVlrInicial(oValor);
        return this;
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Cor Etf");
    }

    @Override
    public CorEtf mostra() throws Exception {
        super.mostra();
        return this;
    }

    @Override
    public CorEtf mostra(String comTitulo) throws Exception {
        super.mostra(comTitulo);
        return this;
    }

    @Override
    public EdtCor edt() {
        return (EdtCor) super.edt();
    }

    @Override
    public CorEtf abre(Object oValor) throws Exception {
        super.abre(oValor);
        return this;
    }

    @Override
    public synchronized VCor pgVlr() throws Exception {
        return cmps().pega("edt").pEdt().pgVlrCor();
    }

}
