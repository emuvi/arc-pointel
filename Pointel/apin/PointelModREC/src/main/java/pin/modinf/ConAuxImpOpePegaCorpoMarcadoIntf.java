package pin.modinf;

import pin.libamk.Campos;
import pin.libamk.Cmp;
import pin.libamk.Intf;
import pin.libbas.Dados;

public class ConAuxImpOpePegaCorpoMarcadoIntf extends Intf {

    private ConAuxImpOpePegaCorpoMarcado operacao;

    public ConAuxImpOpePegaCorpoMarcadoIntf(ConAuxImpOpePegaCorpoMarcado daOperacao) {
        super(new Campos(
                new Cmp(1, 1, "inicio", "Início", Dados.Tp.Crs).mVlrInicial(daOperacao.pInicio()),
                new Cmp(1, 2, "fim", "Fim", Dados.Tp.Crs).mVlrInicial(daOperacao.pFim())
        ));
        operacao = daOperacao;
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Operação Pega Corpo Marcado");
    }

    @Override
    public void preparaIntf() throws Exception {
        super.preparaIntf();
        botaConfirmarECancelar();
    }

    @Override
    public void aoConfirmar(Object comOrigem) throws Exception {
        super.aoConfirmar(comOrigem);
        operacao.mInicio(cmps().pEdt("inicio").pgVlr().pgCrs());
        operacao.mFim(cmps().pEdt("fim").pgVlr().pgCrs());
    }

}
