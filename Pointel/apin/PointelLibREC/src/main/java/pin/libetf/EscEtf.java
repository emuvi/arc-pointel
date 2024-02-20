package pin.libetf;

import java.util.List;
import pin.libamg.EdtEsc;
import pin.libbas.Dados;
import pin.libvlr.VEsc;

public class EscEtf extends EdtIntf {

    public EscEtf() {
        super(Dados.Tp.Esc);
    }

    public EscEtf(String comDescricao) {
        super(Dados.Tp.Esc, comDescricao);
    }

    @Override
    public EscEtf mInicial(Object oValor) {
        cmps().pega("edt").mVlrInicial(oValor);
        return this;
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Escolha Etf");
    }

    @Override
    public EscEtf mostra() throws Exception {
        super.mostra();
        return this;
    }

    @Override
    public EscEtf mostra(String comTitulo) throws Exception {
        super.mostra(comTitulo);
        return this;
    }

    @Override
    public EdtEsc edt() {
        return (EdtEsc) super.edt();
    }

    public EscEtf botaOpcoes(List<Object> asOpcoes) throws Exception {
        edt().botaOpcoes(asOpcoes);
        return this;
    }

    public EscEtf botaOpcoes(Object... asOpcoes) throws Exception {
        edt().botaOpcoes(asOpcoes);
        return this;
    }

    @Override
    public EscEtf abre(Object oValor) throws Exception {
        super.abre(oValor);
        return this;
    }

    @Override
    public synchronized VEsc pgVlr() throws Exception {
        return cmps().pega("edt").pEdt().pgVlrEsc();
    }

}
