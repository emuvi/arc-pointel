package pin.libitr;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import pin.libjan.TrataIntf;
import pin.libutl.UtlCrs;
import pin.libutl.UtlTex;

public class Mostrador extends JPanel {

    private JTextArea mostrador;
    private JScrollPane rolagem;
    private JPanel jpnBotoes;

    public Mostrador() {
        super(new BorderLayout(4, 4));
        mostrador = new JTextArea();
        mostrador.setEditable(false);
        rolagem = new JScrollPane(mostrador);
        add(rolagem, BorderLayout.CENTER);
        jpnBotoes = null;
    }

    public Mostrador muda(Object para) {
        mostrador.setText(UtlCrs.pega(para));
        return this;
    }

    public Mostrador bota(Object oValor) {
        mostrador.append(UtlCrs.pega(oValor) + UtlTex.quebra());
        TrataIntf.posicionaNoFimVertical(rolagem);
        return this;
    }

    private void iniciaBotoes() {
        if (jpnBotoes == null) {
            jpnBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 4));
        }
    }

    public Mostrador botaBotao(JButton oBotao) {
        iniciaBotoes();
        jpnBotoes.add(oBotao);
        return this;
    }

    public Mostrador botaBotao(AbstractAction comAcao) {
        JButton botao = new JButton(comAcao);
        botao.setText("*");
        botaBotao(botao);
        return this;
    }

    public Mostrador botaBotao(String comTitulo, AbstractAction eAcao) {
        JButton botao = new JButton(eAcao);
        botao.setText(comTitulo);
        botaBotao(botao);
        return this;
    }

    public Mostrador botaBotao(Icon comIcone, AbstractAction eAcao) {
        JButton botao = new JButton(eAcao);
        botao.setIcon(comIcone);
        botaBotao(botao);
        return this;
    }

    public Mostrador botaBotao(String comTitulo, Icon eIcone, AbstractAction eAcao) {
        JButton botao = new JButton(eAcao);
        botao.setText(comTitulo);
        botao.setIcon(eIcone);
        botaBotao(botao);
        return this;
    }
}
