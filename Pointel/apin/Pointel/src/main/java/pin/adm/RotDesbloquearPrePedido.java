package pin.adm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pin.libamk.Cmp;
import pin.libbas.Dados;
import pin.libutl.Utl;
import pin.libvlr.Vlrs;
import pin.modamk.Rotina;

public class RotDesbloquearPrePedido extends Rotina {

    private Boolean especifico;

    public RotDesbloquearPrePedido() {
        this(null);
    }

    public RotDesbloquearPrePedido(String comPrePedido) {
        super("Desbloquear Pré-Pedido", true, new Cmp[]{
            new Cmp(1, 1, "prepedido", "Pré-Pedido", Dados.Tp.Crs).mTamanho(10).botaObrigatorio().mVlrInicial(comPrePedido)
        });

        especifico = comPrePedido != null;

        aoConfirmar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    String prepedido = pegaCrs("prepedido");
                    conexao.opera("UPDATE prepedidos SET enviado = 'N' WHERE codigo = ?",
                            new Vlrs(prepedido));
                    Utl.alerta("Pré-Pedido Desbloqueado");
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
