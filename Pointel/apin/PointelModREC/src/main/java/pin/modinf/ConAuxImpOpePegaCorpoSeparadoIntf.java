package pin.modinf;

import pin.libamk.Campos;
import pin.libamk.Cmp;
import pin.libamk.Intf;
import pin.libbas.Dados;

public class ConAuxImpOpePegaCorpoSeparadoIntf extends Intf {

    private ConAuxImpOpePegaCorpoSeparado operacao;

    public ConAuxImpOpePegaCorpoSeparadoIntf(ConAuxImpOpePegaCorpoSeparado daOperacao) {
        super(new Campos(
                new Cmp(1, 1, "separador", "Separador", Dados.Tp.Crs).mVlrInicial(daOperacao.pSeparador())
        ));
        operacao = daOperacao;
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Operação Pega Corpo Separado");
    }

    @Override
    public void preparaIntf() throws Exception {
        super.preparaIntf();
        botaConfirmarECancelar();
    }

    @Override
    public void aoConfirmar(Object comOrigem) throws Exception {
        super.aoConfirmar(comOrigem);
        operacao.mSeparador(cmps().pEdt("separador").pgVlr().pgCrs());
    }

}
