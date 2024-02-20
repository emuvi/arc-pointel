package pin.modinf;

import pin.libamk.Campos;
import pin.libamk.Cmp;
import pin.libamk.Intf;
import pin.libbas.Dados;

public class ConAuxImpOpePegaParteSeparadaIntf extends Intf {

    private ConAuxImpOpePegaParteSeparada operacao;

    public ConAuxImpOpePegaParteSeparadaIntf(ConAuxImpOpePegaParteSeparada daOperacao) {
        super(new Campos(
                new Cmp(1, 1, "nome", "Nome", Dados.Tp.Crs).mVlrInicial(daOperacao.pNome()),
                new Cmp(2, 1, "separador", "Separador", Dados.Tp.Crs).mVlrInicial(daOperacao.pSeparador())
        ));
        operacao = daOperacao;
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Operação Pega Parte Separada");
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
        operacao.mSeparador(cmps().pEdt("separador").pgVlr().pgCrs());
    }

}
