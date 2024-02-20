package pin.adm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pin.libamk.Cmp;
import pin.libbas.Dados;
import pin.libutl.Utl;
import pin.libvlr.Vlrs;
import pin.modamk.Rotina;

public class RotDesbloquearPedido extends Rotina {

    private Boolean especifico;

    public RotDesbloquearPedido() {
        this(null);
    }

    public RotDesbloquearPedido(String comPedido) {
        super("Desbloquear Pré-Pedido", true, new Cmp[]{
            new Cmp(1, 1, "pedido", "Pedido", Dados.Tp.Crs).mTamanho(10).botaObrigatorio().mVlrInicial(comPedido)
        });

        especifico = comPedido != null;

        aoConfirmar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    String prepedido = pegaCrs("pedido");
                    conexao.opera("UPDATE pedidos SET situacao = 'P' WHERE codigo = ?",
                            new Vlrs(prepedido));
                    Utl.alerta("Pedido Desbloqueado");
                    if (especifico) {
                        fechar();
                    }
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        };
    }

}
