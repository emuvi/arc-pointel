package pin.libetf;

import pin.libamg.EdtTex;
import pin.libbas.Dados;
import pin.libvlr.VTex;

public class TexEtf extends EdtIntf {

    public TexEtf() {
        super(Dados.Tp.Tex);
    }

    public TexEtf(String comDescricao) {
        super(Dados.Tp.Tex, comDescricao);
    }

    @Override
    public TexEtf mInicial(Object oValor) {
        cmps().pega("edt").mVlrInicial(oValor);
        return this;
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Texto Etf");
    }

    @Override
    public TexEtf mostra() throws Exception {
        super.mostra();
        return this;
    }

    @Override
    public TexEtf mostra(String comTitulo) throws Exception {
        super.mostra(comTitulo);
        return this;
    }

    @Override
    public EdtTex edt() {
        return (EdtTex) super.edt();
    }

    @Override
    public TexEtf abre(Object oValor) throws Exception {
        super.abre(oValor);
        return this;
    }

    @Override
    public synchronized VTex pgVlr() throws Exception {
        return cmps().pega("edt").pEdt().pgVlrTex();
    }

}
