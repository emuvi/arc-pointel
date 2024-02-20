package pin.adm;

public class ConPedidosItens extends CadPedidosItens {

    public ConPedidosItens() throws Exception {
        super();
    }

    public ConPedidosItens(String doPedido) throws Exception {
        super(doPedido);
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        botaSoConsulta();
    }
}
