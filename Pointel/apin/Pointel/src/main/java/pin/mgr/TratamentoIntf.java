package pin.mgr;

import pin.libamk.Campos;
import pin.libamk.Cmp;
import pin.libamk.Intf;
import pin.libbas.Dados;

public class TratamentoIntf extends Intf {

    public TratamentoIntf() {
        this(new Tratamento());
    }

    public TratamentoIntf(Tratamento comInicial) {
        super(new Campos(
                        new Cmp(1, 1, "classe", "Classe", Dados.Tp.Crs).mVlrInicial(comInicial.pegaClasse()),
                        new Cmp(2, 1, "metodo", "Método", Dados.Tp.Crs).mVlrInicial(comInicial.pegaMetodo()),
                        new Cmp(3, 1, "casos", "Casos", Dados.Tp.Lis).mVlrInicial(comInicial.pegaCasos())
                )
        );
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Tratamento");
    }

    @Override
    public void preparaIntf() throws Exception {
        super.preparaIntf();
        botaConfirmarECancelar();
    }

    public Tratamento pegaTratamento() throws Exception {
        return new Tratamento()
                .botaClasse(cmps().pgVlr("classe").pgCrs())
                .botaMetodo(cmps().pgVlr("metodo").pgCrs())
                .botaCasos(cmps().pgVlr("casos").pgMatCrs());
    }
}
