package pin.libetf;

import pin.libamg.EdtVid;
import pin.libbas.Dados;
import pin.libvlr.VVid;

public class VidEtf extends EdtIntf {

    public VidEtf() {
        super(Dados.Tp.Vid);
    }

    public VidEtf(String comDescricao) {
        super(Dados.Tp.Vid, comDescricao);
    }

    @Override
    public VidEtf mInicial(Object oValor) {
        cmps().pega("edt").mVlrInicial(oValor);
        return this;
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Vídeo Etf");
    }

    @Override
    public VidEtf mostra() throws Exception {
        super.mostra();
        return this;
    }

    @Override
    public VidEtf mostra(String comTitulo) throws Exception {
        super.mostra(comTitulo);
        return this;
    }

    @Override
    public EdtVid edt() {
        return (EdtVid) super.edt();
    }

    @Override
    public VidEtf abre(Object oValor) throws Exception {
        super.abre(oValor);
        return this;
    }

    @Override
    public synchronized VVid pgVlr() throws Exception {
        return cmps().pega("edt").pEdt().pgVlrVid();
    }

}
