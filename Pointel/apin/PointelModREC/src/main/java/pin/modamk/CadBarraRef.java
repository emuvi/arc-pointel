package pin.modamk;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import pin.libpic.Pics;
import pin.libutl.Utl;
import pin.libutl.UtlIma;

public class CadBarraRef extends JPanel {

    private final Cadastro cadastro;
    private final JButton jbtProcurar;
    private final JButton jbtConfirmar;
    private final JButton jbtCancelar;

    public CadBarraRef(Cadastro doCadastro) {
        super(new FlowLayout(FlowLayout.LEFT, 2, 2));
        this.cadastro = doCadastro;
        Dimension dimBt = new Dimension(33, 33);
        Border borBt = BorderFactory.createEmptyBorder(2, 2, 2, 2);
        jbtProcurar = new JButton();
        jbtProcurar.setFocusable(false);
        UtlIma.botaPic(jbtProcurar, "Procurar.png");
        jbtProcurar.setPreferredSize(dimBt);
        jbtProcurar.setBorder(borBt);
        jbtProcurar.setToolTipText("Procurar (F4)");
        add(jbtProcurar);
        jbtProcurar.addActionListener(new ActProcurar());
        jbtConfirmar = new JButton();
        jbtConfirmar.setFocusable(false);
        UtlIma.botaPic(jbtConfirmar, "Confirmar.png");
        jbtConfirmar.setPreferredSize(dimBt);
        jbtConfirmar.setBorder(borBt);
        jbtConfirmar.setToolTipText("Confimar (alt ENTER)");
        add(jbtConfirmar);
        jbtConfirmar.addActionListener(new ActConfirmar());
        jbtCancelar = new JButton();
        jbtCancelar.setFocusable(false);
        UtlIma.botaPic(jbtCancelar, "Cancelar.png");
        jbtCancelar.setPreferredSize(dimBt);
        jbtCancelar.setBorder(borBt);
        jbtCancelar.setToolTipText("Cancelar (alt F1)");
        add(jbtCancelar);
        jbtCancelar.addActionListener(new ActCancelar());
    }

    public JButton pJbtProcurar() {
        return jbtProcurar;
    }

    public JButton pJbtConfirmar() {
        return jbtConfirmar;
    }

    public JButton pJbtCancelar() {
        return jbtCancelar;
    }

    private class ActProcurar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                cadastro.mudaModo(Cadastro.Modo.PROCURAR);
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    private class ActConfirmar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                cadastro.confirmarReferencia();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    private class ActCancelar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                cadastro.cancelarReferencia();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

}
