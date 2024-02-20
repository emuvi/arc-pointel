package pin.libvtf;

import java.awt.Color;
import pin.libamg.EdtCor;
import pin.libbas.Dados;
import pin.libvlr.VCor;

public class CorVtf extends ValIntf {

    public CorVtf() {
        super("Cor Vtf", Dados.Tp.Cor);
    }

    public CorVtf(String comDescricao) {
        super("Cor Vtf", Dados.Tp.Cor, comDescricao);
    }

    @Override
    public CorVtf mInicial(Object oValor) {
        cmps().pega("edt").mVlrInicial(oValor);
        return this;
    }

    @Override
    public Color pgVal() throws Exception {
        return pgVlr().pgCor();
    }

    @Override
    public CorVtf mostra() throws Exception {
        super.mostra();
        return this;
    }

    @Override
    public CorVtf mostra(String comTitulo) throws Exception {
        super.mostra(comTitulo);
        return this;
    }

    @Override
    public EdtCor edt() {
        return (EdtCor) super.edt();
    }

    @Override
    public CorVtf abre(Object oValor) throws Exception {
        super.abre(oValor);
        return this;
    }

    @Override
    public synchronized VCor pgVlr() throws Exception {
        return edt().pgVlrCor();
    }

}
