package pin.modamk;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import pin.libjan.JanAtalhos;
import pin.libutl.Utl;
import pin.libutl.UtlIma;

public class CadBarra extends JPanel {

    private final Cadastro cadastro;
    private final JLabel jlbModo;
    private final JButton jbtNovo;
    private final JButton jbtProcurar;
    private final JButton jbtEditar;
    private final JButton jbtAtualizar;
    private final JButton jbtPrimeiro;
    private final JButton jbtAnterior;
    private final JButton jbtProximo;
    private final JButton jbtUltimo;
    private final JButton jbtConfirmar;
    private final JButton jbtCancelar;
    private final JButton jbtExcluir;

    public CadBarra(Cadastro doCadastro) {
        super(new FlowLayout(FlowLayout.LEFT, 2, 2));
        this.cadastro = doCadastro;
        Dimension dimBt = new Dimension(33, 33);
        Border borBt = BorderFactory.createEmptyBorder(2, 2, 2, 2);
        jlbModo = new JLabel();
        jlbModo.setFocusable(false);
        jlbModo.setHorizontalAlignment(SwingConstants.CENTER);
        jlbModo.setVerticalAlignment(SwingConstants.CENTER);
        UtlIma.botaPic(jlbModo, "ModoNovo.png");
        jlbModo.setPreferredSize(dimBt);
        jlbModo.setBorder(borBt);
        add(jlbModo);
        jbtNovo = new JButton();
        jbtNovo.setFocusable(false);
        UtlIma.botaPic(jbtNovo, "Novo.png");
        jbtNovo.setPreferredSize(dimBt);
        jbtNovo.setBorder(borBt);
        jbtNovo.setToolTipText("Novo (" + JanAtalhos.pega("Cadastro Modo Inserir", "alt INSERT") + ")");
        add(jbtNovo);
        jbtNovo.addActionListener(new ActNovo());
        jbtProcurar = new JButton();
        jbtProcurar.setFocusable(false);
        UtlIma.botaPic(jbtProcurar, "Procurar.png");
        jbtProcurar.setPreferredSize(dimBt);
        jbtProcurar.setBorder(borBt);
        jbtProcurar.setToolTipText("Procurar (" + JanAtalhos.pega("Cadastro Modo Procurar", "F3") + ")");
        add(jbtProcurar);
        jbtProcurar.addActionListener(new ActProcurar());
        jbtEditar = new JButton();
        jbtEditar.setFocusable(false);
        UtlIma.botaPic(jbtEditar, "Editar.png");
        jbtEditar.setPreferredSize(dimBt);
        jbtEditar.setBorder(borBt);
        jbtEditar.setToolTipText("Editar (" + JanAtalhos.pega("Cadastro Modo Editar", "F4") + ")");
        add(jbtEditar);
        jbtEditar.addActionListener(new ActEditar());
        jbtAtualizar = new JButton();
        jbtAtualizar.setFocusable(false);
        UtlIma.botaPic(jbtAtualizar, "Atualizar.png");
        jbtAtualizar.setPreferredSize(dimBt);
        jbtAtualizar.setBorder(borBt);
        jbtAtualizar.setToolTipText("Atualizar (" + JanAtalhos.pega("Cadastro Atualizar", "alt F5") + ")");
        add(jbtAtualizar);
        jbtAtualizar.addActionListener(new ActAtualizar());
        jbtPrimeiro = new JButton();
        jbtPrimeiro.setFocusable(false);
        UtlIma.botaPic(jbtPrimeiro, "Primeiro.png");
        jbtPrimeiro.setPreferredSize(dimBt);
        jbtPrimeiro.setBorder(borBt);
        jbtPrimeiro.setToolTipText("Ir ao Primeiro (" + JanAtalhos.pega("Cadastro Primeiro", "alt HOME") + ")");
        add(jbtPrimeiro);
        jbtPrimeiro.addActionListener(new ActPrimeiro());
        jbtAnterior = new JButton();
        jbtAnterior.setFocusable(false);
        UtlIma.botaPic(jbtAnterior, "Anterior.png");
        jbtAnterior.setPreferredSize(dimBt);
        jbtAnterior.setBorder(borBt);
        jbtAnterior.setToolTipText("Ir ao Anterior (" + JanAtalhos.pega("Cadastro Anterior", "alt PAGE_UP") + ")");
        add(jbtAnterior);
        jbtAnterior.addActionListener(new ActAnterior());
        jbtProximo = new JButton();
        jbtProximo.setFocusable(false);
        UtlIma.botaPic(jbtProximo, "Proximo.png");
        jbtProximo.setPreferredSize(dimBt);
        jbtProximo.setBorder(borBt);
        jbtProximo.setToolTipText("Ir ao Próximo (" + JanAtalhos.pega("Cadastro Próximo", "alt PAGE_DOWN") + ")");
        add(jbtProximo);
        jbtProximo.addActionListener(new ActProximo());
        jbtUltimo = new JButton();
        jbtUltimo.setFocusable(false);
        UtlIma.botaPic(jbtUltimo, "Ultimo.png");
        jbtUltimo.setPreferredSize(dimBt);
        jbtUltimo.setBorder(borBt);
        jbtUltimo.setToolTipText("Ir ao Último (" + JanAtalhos.pega("Cadastro Último", "alt END") + ")");
        add(jbtUltimo);
        jbtUltimo.addActionListener(new ActUltimo());
        jbtConfirmar = new JButton();
        jbtConfirmar.setFocusable(false);
        UtlIma.botaPic(jbtConfirmar, "Confirmar.png");
        jbtConfirmar.setPreferredSize(dimBt);
        jbtConfirmar.setBorder(borBt);
        jbtConfirmar.setToolTipText("Confimar (" + JanAtalhos.pega("Cadastro Confirmar", "alt ENTER") + ")");
        add(jbtConfirmar);
        jbtConfirmar.addActionListener(new ActConfirmar());
        jbtCancelar = new JButton();
        jbtCancelar.setFocusable(false);
        UtlIma.botaPic(jbtCancelar, "Cancelar.png");
        jbtCancelar.setPreferredSize(dimBt);
        jbtCancelar.setBorder(borBt);
        jbtCancelar.setToolTipText("Cancelar (" + JanAtalhos.pega("Cadastro Cancelar", "alt F1") + ")");
        add(jbtCancelar);
        jbtCancelar.addActionListener(new ActCancelar());
        jbtExcluir = new JButton();
        jbtExcluir.setFocusable(false);
        UtlIma.botaPic(jbtExcluir, "Excluir.png");
        jbtExcluir.setPreferredSize(dimBt);
        jbtExcluir.setBorder(borBt);
        jbtExcluir.setToolTipText("Excluir (" + JanAtalhos.pega("Cadastro Excluir", "alt DELETE") + ")");
        add(jbtExcluir);
        jbtExcluir.addActionListener(new ActExcluir());
    }

    public JLabel pJlbModo() {
        return jlbModo;
    }

    public JButton pJbtNovo() {
        return jbtNovo;
    }

    public JButton pJbtProcurar() {
        return jbtProcurar;
    }

    public JButton pJbtEditar() {
        return jbtEditar;
    }

    public JButton pJbtAtualizar() {
        return jbtAtualizar;
    }

    public JButton pJbtPrimeiro() {
        return jbtPrimeiro;
    }

    public JButton pJbtAnterior() {
        return jbtAnterior;
    }

    public JButton pJbtProximo() {
        return jbtProximo;
    }

    public JButton pJbtUltimo() {
        return jbtUltimo;
    }

    public JButton pJbtConfirmar() {
        return jbtConfirmar;
    }

    public JButton pJbtCancelar() {
        return jbtCancelar;
    }

    public JButton pJbtExcluir() {
        return jbtExcluir;
    }

    private class ActNovo extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                cadastro.mudaModo(Cadastro.Modo.NOVO);
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
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

    private class ActEditar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                cadastro.mudaModo(Cadastro.Modo.EDITAR);
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    private class ActAtualizar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                cadastro.atualizar();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    private class ActPrimeiro extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                cadastro.irAoPrimeiro();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    private class ActAnterior extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                cadastro.irAoAnterior();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    private class ActProximo extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                cadastro.irAoProximo();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    private class ActUltimo extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                cadastro.irAoUltimo();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    private class ActConfirmar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                cadastro.confirmar();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    private class ActCancelar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                cadastro.cancelar();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    private class ActExcluir extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                cadastro.excluir();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

}
