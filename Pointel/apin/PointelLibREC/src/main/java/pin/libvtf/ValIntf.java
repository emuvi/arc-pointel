package pin.libvtf;

import pin.libamg.Edt;
import pin.libamk.Campos;
import pin.libamk.Cmp;
import pin.libamk.Intf;
import pin.libbas.Dados;
import pin.libvlr.Vlr;

public abstract class ValIntf extends Intf {

    private final String titPad;

    public ValIntf(String titulo, Dados.Tp tipo) {
        super(new Campos(new Cmp(1, 1, "edt", tipo)));
        titPad = titulo;
    }

    public ValIntf(String titulo, Dados.Tp tipo, String comDescricao) {
        super(comDescricao, new Campos(new Cmp(1, 1, "edt", tipo)));
        titPad = titulo;
    }

    public ValIntf(String titulo) {
        super();
        titPad = titulo;
    }

    public ValIntf(String titulo, Campos comCampos) {
        super(comCampos);
        titPad = titulo;
    }

    public ValIntf(String titulo, String comDescricao) {
        super(comDescricao);
        titPad = titulo;
    }

    public ValIntf(String titulo, String comDescricao, Campos eCampos) {
        super(comDescricao, eCampos);
        titPad = titulo;
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo(titPad);
    }

    @Override
    public void preparaIntf() throws Exception {
        super.preparaIntf();
        botaConfirmarECancelar();
    }

    public ValIntf mInicial(Object oValor) {
        cmps().pega("edt").mVlrInicial(oValor);
        return this;
    }

    @Override
    public ValIntf mostra() throws Exception {
        super.mostra();
        try {
            cmps().pega("edt").pEdt().botaFoco();
        } catch (Exception ex) {
        }
        return this;
    }

    @Override
    public ValIntf mostra(String comTitulo) throws Exception {
        super.mostra(comTitulo);
        try {
            cmps().pega("edt").pEdt().botaFoco();
        } catch (Exception ex) {
        }
        return this;
    }

    public ValIntf abre(Object oValor) throws Exception {
        cmps().pega("edt").pEdt().mdVlr(oValor);
        return this;
    }

    public Cmp campo() {
        return cmps().pega("edt");
    }

    public Edt edt() {
        return cmps().pega("edt").pEdt();
    }

    public synchronized Vlr pgVlr() throws Exception {
        return cmps().pega("edt").pEdt().pgVlr();
    }

    public synchronized Vlr pgVlr(Object comPadrao) throws Exception {
        return cmps().pega("edt").pEdt().pgVlr(comPadrao);
    }

    public abstract Object pgVal() throws Exception;

}
