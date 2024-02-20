package pin.libvtf;

import pin.libamg.EdtNum;
import pin.libbas.Dados;

public class NumVtf extends ValIntf {

    public NumVtf() {
        super("Número Vtf", Dados.Tp.Num);
    }

    public NumVtf(String comDescricao) {
        super("Número Vtf", Dados.Tp.Num, comDescricao);
    }

    @Override
    public NumVtf mInicial(Object oValor) {
        cmps().pega("edt").mVlrInicial(oValor);
        return this;
    }

    @Override
    public Float pgVal() throws Exception {
        return pgVlr().pgNum();
    }

    @Override
    public NumVtf mostra() throws Exception {
        super.mostra();
        return this;
    }

    @Override
    public NumVtf mostra(String comTitulo) throws Exception {
        super.mostra(comTitulo);
        return this;
    }

    @Override
    public EdtNum edt() {
        return (EdtNum) super.edt();
    }

    @Override
    public NumVtf abre(Object oValor) throws Exception {
        super.abre(oValor);
        return this;
    }

}
