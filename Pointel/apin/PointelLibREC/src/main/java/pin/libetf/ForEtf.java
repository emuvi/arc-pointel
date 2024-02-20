package pin.libetf;

import pin.libamg.EdtFor;
import pin.libbas.Dados;
import pin.libvlr.VFor;

public class ForEtf extends EdtIntf {

    public ForEtf() {
        super(Dados.Tp.For);
    }

    public ForEtf(String comDescricao) {
        super(Dados.Tp.For, comDescricao);
    }

    @Override
    public ForEtf mInicial(Object oValor) {
        cmps().pega("edt").mVlrInicial(oValor);
        return this;
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Forma Etf");
    }

    @Override
    public ForEtf mostra() throws Exception {
        super.mostra();
        return this;
    }

    @Override
    public ForEtf mostra(String comTitulo) throws Exception {
        super.mostra(comTitulo);
        return this;
    }

    @Override
    public EdtFor edt() {
        return (EdtFor) super.edt();
    }

    @Override
    public ForEtf abre(Object oValor) throws Exception {
        super.abre(oValor);
        return this;
    }

    @Override
    public synchronized VFor pgVlr() throws Exception {
        return cmps().pega("edt").pEdt().pgVlrFor();
    }

}
