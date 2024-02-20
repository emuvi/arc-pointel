package pin.libvtf;

import pin.libamk.Campos;
import pin.libamk.Cmp;
import pin.libamk.Intf;
import pin.libbas.Dados;

public class TituloVtf extends Intf {

    public TituloVtf(String comTitulo, Boolean eObrigatorio, String eDica) {
        super(new Campos(
                new Cmp(1, 1, "titulo", "Título", Dados.Tp.Crs).mLargura(350).mVlrInicial(comTitulo),
                new Cmp(1, 2, "obrigatorio", "Obrigatório", Dados.Tp.Log).mVlrInicial(eObrigatorio),
                new Cmp(2, 1, "dica", "Dica", Dados.Tp.Crs).mLargura(500).mVlrInicial(eDica)
        ));
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Título Vtf");
    }

    @Override
    public void preparaIntf() throws Exception {
        super.preparaIntf();
        botaConfirmarECancelar();
    }

    public String pegaAuxTitulo() throws Exception {
        return cmps().pgVlr("titulo").pgCrs();
    }

    public Boolean pegaAuxObrigatorio() throws Exception {
        return cmps().pgVlr("obrigatorio").pgLog();
    }

    public String pegaAuxDica() throws Exception {
        return cmps().pgVlr("dica").pgCrs();
    }

    public void aoConfirmar(String comTitulo, Boolean eObrigatorio, String eDica) {

    }

    @Override
    public void aoConfirmar(Object comOrigem) throws Exception {
        aoConfirmar(pegaAuxTitulo(), pegaAuxObrigatorio(), pegaAuxDica());
        fecha();
    }

}
