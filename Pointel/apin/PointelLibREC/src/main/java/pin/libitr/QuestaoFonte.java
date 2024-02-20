package pin.libitr;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import pin.libout.JFontChooser;
import pin.libjan.Janelas;

public class QuestaoFonte {

    private Font escolhida;
    private AbstractAction aoConfirmar;
    private AbstractAction aoCancelar;

    public QuestaoFonte() {
        this(null);
    }

    public QuestaoFonte(Font comPadrao) {
        this.escolhida = comPadrao;
        this.aoConfirmar = null;
        this.aoCancelar = null;
    }

    public QuestaoFonte(Font comPadrao, AbstractAction aoEscolher) {
        this.escolhida = comPadrao;
        this.aoConfirmar = aoEscolher;
    }

    public Boolean aoEscolher(Font comEscolhida) {
        return true;
    }

    public Font pegaEscolhida() {
        return escolhida;
    }

    public QuestaoFonte botaAoConfirmar(AbstractAction aAcao) {
        aoConfirmar = aAcao;
        return this;
    }

    public QuestaoFonte botaAoCancelar(AbstractAction aAcao) {
        aoCancelar = aAcao;
        return this;
    }

    public QuestaoFonte mostra() {
        return mostra("Escolha a Fonte");
    }

    public QuestaoFonte mostra(String comTitulo) {
        return mostra(comTitulo, true, true, true);
    }

    public QuestaoFonte mostra(String comTitulo, boolean comFonteNome, boolean eFonteEstilo, boolean eFonteTamanho) {
        JFrame escolhedor = new JFrame(comTitulo);
        escolhedor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        escolhedor.setLayout(new BorderLayout(4, 4));
        escolhedor.setResizable(false);
        JFontChooser escolhendo = new JFontChooser(comFonteNome, eFonteEstilo, eFonteTamanho);
        if (escolhida != null) {
            escolhendo.setSelectedFont(escolhida);
        }
        escolhedor.add(escolhendo, BorderLayout.CENTER);
        JButton jbtConfirmar = new JButton("Confirmar");
        jbtConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Janelas.fecha(escolhedor);
                escolhida = escolhendo.getSelectedFont();
                if (aoEscolher(escolhida)) {
                    if (aoConfirmar != null) {
                        aoConfirmar.actionPerformed(e);
                    }
                } else {
                    if (aoCancelar != null) {
                        aoCancelar.actionPerformed(e);
                    }
                }
            }
        });
        JButton jbtCancelar = new JButton("Cancelar");
        jbtCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Janelas.fecha(escolhedor);
            }
        });
        JPanel jpnBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        jpnBotoes.add(jbtConfirmar);
        jpnBotoes.add(jbtCancelar);
        escolhedor.add(jpnBotoes, BorderLayout.SOUTH);
        escolhedor.getRootPane().setDefaultButton(jbtConfirmar);
        Janelas.inicia(escolhedor);
        Janelas.mostra(escolhedor);
        return this;
    }
}
