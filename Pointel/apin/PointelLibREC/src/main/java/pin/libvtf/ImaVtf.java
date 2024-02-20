package pin.libvtf;

import java.awt.image.BufferedImage;
import pin.libamg.EdtIma;
import pin.libbas.Dados;
import pin.libvlr.VIma;

public class ImaVtf extends ValIntf {

    public ImaVtf() {
        super("Imagem Vtf", Dados.Tp.Ima);
    }

    public ImaVtf(String comDescricao) {
        super("Imagem Vtf", Dados.Tp.Ima, comDescricao);
    }

    @Override
    public ImaVtf mInicial(Object oValor) {
        cmps().pega("edt").mVlrInicial(oValor);
        return this;
    }

    @Override
    public BufferedImage pgVal() throws Exception {
        return pgVlr().pgIma();
    }

    @Override
    public ImaVtf mostra() throws Exception {
        super.mostra();
        return this;
    }

    @Override
    public ImaVtf mostra(String comTitulo) throws Exception {
        super.mostra(comTitulo);
        return this;
    }

    @Override
    public EdtIma edt() {
        return (EdtIma) super.edt();
    }

    @Override
    public ImaVtf abre(Object oValor) throws Exception {
        super.abre(oValor);
        return this;
    }

    @Override
    public synchronized VIma pgVlr() throws Exception {
        return edt().pgVlrIma();
    }

}
