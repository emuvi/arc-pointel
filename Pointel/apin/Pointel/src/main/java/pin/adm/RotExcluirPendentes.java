package pin.adm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pin.libamk.Cmp;
import pin.libbas.Dados;
import pin.libutl.Utl;
import pin.libvlr.Vlrs;
import pin.modamk.Rotina;

public class RotExcluirPendentes extends Rotina {

  public RotExcluirPendentes() {
    super("Excluir Pendentes", new Cmp[]{
      new Cmp(1, 1, "cliente", "Cliente", Dados.Tp.Crs).mTamanho(8).botaObrigatorio()
    });

    aoConfirmar = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent ae) {
        try {
          Object cliente = pegaVlr("cliente");
          String cliente_nome = conexao.busca("SELECT nome FROM pessoas WHERE codigo = ?", new Vlrs(cliente)).pgCol().pgCrs();
          if (Utl.continua("Deseja excluir os pedidos pendentes do cliente: " + cliente + " - " + cliente_nome)) {
            String selecao = "(SELECT codigo FROM pedidos WHERE cliente = ? AND situacao = 'P')";
            conexao.opera("DELETE FROM itens_pedido WHERE pedido IN " + selecao,
                    new Vlrs(cliente));
            int total = conexao.opera("DELETE FROM pedidos WHERE codigo IN " + selecao,
                    new Vlrs(cliente));
            Utl.alerta("Foram excluídos " + total + " pedidos pendentes.");
          }
        } catch (Exception ex) {
          Utl.registra(ex);
        }
      }
    };
  }
}
