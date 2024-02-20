package pin.modinf;

import pin.libamk.Campos;
import pin.libamk.Cmp;
import pin.libamk.Intf;
import pin.libbas.Dados;

public class ConAuxImpOpePegaParteMarcadaIntf extends Intf {

    private ConAuxImpOpePegaParteMarcada operacao;

    public ConAuxImpOpePegaParteMarcadaIntf(ConAuxImpOpePegaParteMarcada daOperacao) {
        super(new Campos(
                new Cmp(1, 1, "nome", "Nome", Dados.Tp.Crs).mVlrInicial(daOperacao.pNome()),
                new Cmp(2, 1, "inicio", "Início", Dados.Tp.Crs).mVlrInicial(daOperacao.pInicio()),
                new Cmp(2, 2, "fim", "Fim", Dados.Tp.Crs).mVlrInicial(daOperacao.pFim())
        ));
        operacao = daOperacao;
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Operação Pega Parte Marcada");
    }

    @Override
    public void preparaIntf() throws Exception {
        super.preparaIntf();
        botaConfirmarECancelar();
    }

    @Override
    public void aoConfirmar(Object comOrigem) throws Exception {
        super.aoConfirmar(comOrigem);
        operacao.mNome(cmps().pEdt("nome").pgVlr().pgCrs());
        operacao.mInicio(cmps().pEdt("inicio").pgVlr().pgCrs());
        operacao.mFim(cmps().pEdt("fim").pgVlr().pgCrs());
    }

}
