package pin.libetf;

import pin.libamg.EdtNum;
import pin.libbas.Dados;
import pin.libvlr.VNum;

public class NumEtf extends EdtIntf {

    public NumEtf() {
        super(Dados.Tp.Num);
    }

    public NumEtf(String comDescricao) {
        super(Dados.Tp.Num, comDescricao);
    }

    @Override
    public NumEtf mInicial(Object oValor) {
        cmps().pega("edt").mVlrInicial(oValor);
        return this;
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Número Etf");
    }

    @Override
    public NumEtf mostra() throws Exception {
        super.mostra();
        return this;
    }

    @Override
    public NumEtf mostra(String comTitulo) throws Exception {
        super.mostra(comTitulo);
        return this;
    }

    @Override
    public EdtNum edt() {
        return (EdtNum) super.edt();
    }

    @Override
    public NumEtf abre(Object oValor) throws Exception {
        super.abre(oValor);
        return this;
    }

    @Override
    public synchronized VNum pgVlr() throws Exception {
        return cmps().pega("edt").pEdt().pgVlrNum();
    }

}
