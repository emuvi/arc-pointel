package pin.adm;

import pin.libamk.Botao;

public class ConPedidos extends CadPedidos {

    public ConPedidos() throws Exception {
        super();
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        botaSoConsulta();
    }

    @Override
    public void preparaIntf() throws Exception {
        super.preparaIntf();
        limpaBotoes();
        botaBotao(new BotItens());
    }

    private class BotItens extends Botao {

        public BotItens() {
            super("Itens", 'I');
        }

        @Override
        public void aoExecutar(Object comOrigem) throws Exception {
            if (confirmar(true)) {
                String pedido = pegaCampo("codigo").pEdt().pgVlr().pgCrs();
                ConPedidosItens filho = new ConPedidosItens(pedido);
                filho.mostra();
            }
        }
    }

}
