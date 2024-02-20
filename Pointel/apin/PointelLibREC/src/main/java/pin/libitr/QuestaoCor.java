package pin.libitr;

import pin.libjan.Janelas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class QuestaoCor {

    private Color escolhida;
    private AbstractAction aoConfirmar;
    private AbstractAction aoCancelar;

    public QuestaoCor() {
        this(null);
    }

    public QuestaoCor(Color comPadrao) {
        this.escolhida = comPadrao;
        this.aoConfirmar = null;
        this.aoCancelar = null;
    }

    public QuestaoCor(Color comPadrao, AbstractAction aoEscolher) {
        this.escolhida = comPadrao;
        this.aoConfirmar = aoEscolher;
    }

    public Boolean aoEscolher(Color comEscolhida) {
        return true;
    }

    public Color pegaEscolhida() {
        return escolhida;
    }

    public QuestaoCor botaAoConfirmar(AbstractAction aAcao) {
        aoConfirmar = aAcao;
        return this;
    }

    public QuestaoCor botaAoCancelar(AbstractAction aAcao) {
        aoCancelar = aAcao;
        return this;
    }

    public QuestaoCor mostra() {
        return mostra("Seleção de Cor");
    }

    public QuestaoCor mostra(String comTitulo) {
        JFrame escolhedor = new JFrame(comTitulo);
        escolhedor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        escolhedor.setLayout(new BorderLayout(4, 4));
        escolhedor.setResizable(false);
        JColorChooser escolhendo = null;
        if (escolhida != null) {
            escolhendo = new JColorChooser(escolhida);
        } else {
            escolhendo = new JColorChooser();
        }
        escolhendo.setPreviewPanel(new JPanel());
        final JColorChooser esc = escolhendo;
        escolhedor.add(escolhendo, BorderLayout.CENTER);
        JButton jbtConfirmar = new JButton("Confirmar");
        jbtConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                escolhida = esc.getColor();
                if (aoEscolher(escolhida)) {
                    if (aoConfirmar != null) {
                        aoConfirmar.actionPerformed(e);
                    }
                } else {
                    if (aoCancelar != null) {
                        aoCancelar.actionPerformed(e);
                    }
                }
                Janelas.fecha(escolhedor);
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
        Janelas.inicia(escolhedor);
        Janelas.mostra(escolhedor);
        return this;
    }
}
