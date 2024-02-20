package pin.libetf;

import pin.libamg.EdtPla;
import pin.libbas.Dados;
import pin.libvlr.VPla;

public class PlaEtf extends EdtIntf {

    public PlaEtf() {
        super(Dados.Tp.Pla);
    }

    public PlaEtf(String comDescricao) {
        super(Dados.Tp.Pla, comDescricao);
    }

    @Override
    public PlaEtf mInicial(Object oValor) {
        cmps().pega("edt").mVlrInicial(oValor);
        return this;
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Planilha Etf");
    }

    private void inicia() {
        edt().controlador().botaColunas();
        edt().controlador().botaLinhas();
    }

    @Override
    public PlaEtf mostra() throws Exception {
        super.mostra();
        inicia();
        return this;
    }

    @Override
    public PlaEtf mostra(String comTitulo) throws Exception {
        super.mostra(comTitulo);
        inicia();
        return this;
    }

    @Override
    public EdtPla edt() {
        return (EdtPla) super.edt();
    }

    @Override
    public PlaEtf abre(Object oValor) throws Exception {
        super.abre(oValor);
        return this;
    }

    @Override
    public synchronized VPla pgVlr() throws Exception {
        return cmps().pega("edt").pEdt().pgVlrPla();
    }

}
