package pin.modinf;

import pin.libamk.Campos;
import pin.libamk.Cmp;
import pin.libamk.Intf;
import pin.libbas.Dados;

public class ConAuxImpOpePegaParteFixadaIntf extends Intf {

    private ConAuxImpOpePegaParteFixada operacao;

    public ConAuxImpOpePegaParteFixadaIntf(ConAuxImpOpePegaParteFixada daOperacao) {
        super(new Campos(
                new Cmp(1, 1, "nome", "Nome", Dados.Tp.Crs).mVlrInicial(daOperacao.pNome()),
                new Cmp(2, 1, "inicio", "Início", Dados.Tp.Int).mVlrInicial(daOperacao.pInicio()),
                new Cmp(2, 2, "tamanho", "Tamanho", Dados.Tp.Int).mVlrInicial(daOperacao.pTamanho())
        ));
        operacao = daOperacao;
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Operação Pega Parte Fixada");
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
        operacao.mInicio(cmps().pEdt("inicio").pgVlr().pgInt());
        operacao.mTamanho(cmps().pEdt("tamanho").pgVlr().pgInt());
    }

}
