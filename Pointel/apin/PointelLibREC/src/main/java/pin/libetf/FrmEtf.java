package pin.libetf;

import pin.libamg.EdtFrm;
import pin.libbas.Dados;
import pin.libvlr.VFrm;

public class FrmEtf extends EdtIntf {

    public FrmEtf() {
        super(Dados.Tp.Frm);
    }

    public FrmEtf(String comDescricao) {
        super(Dados.Tp.Frm, comDescricao);
    }

    @Override
    public FrmEtf mInicial(Object oValor) {
        cmps().pega("edt").mVlrInicial(oValor);
        return this;
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Formulário Etf");
    }

    @Override
    public FrmEtf mostra() throws Exception {
        super.mostra();
        return this;
    }

    @Override
    public FrmEtf mostra(String comTitulo) throws Exception {
        super.mostra(comTitulo);
        return this;
    }

    @Override
    public EdtFrm edt() {
        return (EdtFrm) super.edt();
    }

    @Override
    public FrmEtf abre(Object oValor) throws Exception {
        super.abre(oValor);
        return this;
    }

    @Override
    public synchronized VFrm pgVlr() throws Exception {
        return cmps().pega("edt").pEdt().pgVlrFrm();
    }

}
