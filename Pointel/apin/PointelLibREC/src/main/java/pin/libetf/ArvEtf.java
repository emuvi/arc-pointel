package pin.libetf;

import pin.libamg.EdtArv;
import pin.libbas.Dados;
import pin.libvlr.VApr;

public class ArvEtf extends EdtIntf {

    public ArvEtf() {
        super(Dados.Tp.Arv);
    }

    public ArvEtf(String comDescricao) {
        super(Dados.Tp.Arv, comDescricao);
    }

    @Override
    public ArvEtf mInicial(Object oValor) {
        cmps().pega("edt").mVlrInicial(oValor);
        return this;
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Árvore Etf");
    }

    @Override
    public ArvEtf mostra() throws Exception {
        super.mostra();
        return this;
    }

    @Override
    public ArvEtf mostra(String comTitulo) throws Exception {
        super.mostra(comTitulo);
        return this;
    }

    @Override
    public EdtArv edt() {
        return (EdtArv) super.edt();
    }

    @Override
    public ArvEtf abre(Object oValor) throws Exception {
        super.abre(oValor);
        return this;
    }

    @Override
    public synchronized VApr pgVlr() throws Exception {
        return cmps().pega("edt").pEdt().pgVlrApr();
    }

}
