package pin.adm;

import pin.libamk.Botao;

public class CadMeusProdutos extends CadProdutos {

    public CadMeusProdutos() throws Exception {
        super();
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        pegaCampo("ativo").mVlrFixo('S');
    }

    @Override
    public void preparaIntf() throws Exception {
        botaBotao(new BotPrecos());
    }

    private class BotPrecos extends Botao {

        public BotPrecos() {
            super("Preços", 'P');
        }

        @Override
        public void aoExecutar(Object comOrigem) throws Exception {
            if (confirmar(true)) {
                CadMeusPrecos filho = new CadMeusPrecos();
                filho.pegaCampo("produto").mVlrFixo(pegaCampo("codigo").pEdt().pgVlr());
                filho.pegaCampo("produtos.nome").mVlrFixo(pegaCampo("nome").pEdt().pgVlr());
                filho.mostra();
            }
        }
    }
}
