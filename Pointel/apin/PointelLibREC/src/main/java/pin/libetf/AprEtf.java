package pin.libetf;

import pin.libamg.EdtApr;
import pin.libbas.Dados;
import pin.libvlr.VApr;

public class AprEtf extends EdtIntf {

    public AprEtf() {
        super(Dados.Tp.Apr);
    }

    public AprEtf(String comDescricao) {
        super(Dados.Tp.Apr, comDescricao);
    }

    @Override
    public AprEtf mInicial(Object oValor) {
        cmps().pega("edt").mVlrInicial(oValor);
        return this;
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Apresentação Etf");
    }

    @Override
    public AprEtf mostra() throws Exception {
        super.mostra();
        return this;
    }

    @Override
    public AprEtf mostra(String comTitulo) throws Exception {
        super.mostra(comTitulo);
        return this;
    }

    @Override
    public EdtApr edt() {
        return (EdtApr) super.edt();
    }

    @Override
    public AprEtf abre(Object oValor) throws Exception {
        super.abre(oValor);
        return this;
    }

    @Override
    public synchronized VApr pgVlr() throws Exception {
        return cmps().pega("edt").pEdt().pgVlrApr();
    }

}
