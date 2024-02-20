package pin.libitr;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import pin.libbas.Dados;
import pin.libjan.Janelas;
import pin.libjan.TrataIntf;
import pin.libutl.Utl;
import pin.libutl.UtlCrs;

public class QuestaoTp {

    public Dados.Tp tipo;
    public String mensagem;
    public Object padrao;
    public AbstractAction aoConfirmar;
    public AbstractAction aoCancelar;
    public Boolean executado;
    public Object retorno;

    public QuestaoTp(Dados.Tp doTipo) {
        this(doTipo, null, null);
    }

    public QuestaoTp(Dados.Tp doTipo, String eMensagem) {
        this(doTipo, eMensagem, null);
    }

    public QuestaoTp(Dados.Tp doTipo, String eMensagem, Object ePadrao) {
        tipo = doTipo;
        mensagem = eMensagem;
        padrao = ePadrao;
        aoConfirmar = null;
        aoCancelar = null;
        executado = false;
    }

    public Boolean aoExecutar(Object comRetorno) throws Exception {
        return true;
    }

    public AbstractAction aoConfirmar() {
        return aoConfirmar;
    }

    public QuestaoTp botaAoConfirmar(AbstractAction aAcao) {
        aoConfirmar = aAcao;
        return this;
    }

    public AbstractAction aoCancelar() {
        return aoCancelar;
    }

    public QuestaoTp botaAoCancelar(AbstractAction aAcao) {
        aoCancelar = aAcao;
        return this;
    }

    public void mostra() {
        mostra("Entrada");
    }

    public void mostra(String comTitulo) {
        JFrame janela = new JFrame(comTitulo);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela.setLayout(new BorderLayout(4, 4));
        janela.setResizable(false);
        janela.getRootPane().setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        JPanel jpnEditor = new JPanel();
        jpnEditor.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        JTextField editor = new JTextField(UtlCrs.pega(padrao));
        TrataIntf.mudaLargura(editor, 180);
        jpnEditor.add(editor);
        janela.add(jpnEditor, BorderLayout.CENTER);
        if (mensagem != null) {
            JTextArea jtaMensagem = new JTextArea();
            jtaMensagem.setLineWrap(true);
            jtaMensagem.setWrapStyleWord(true);
            jtaMensagem.setEditable(false);
            jtaMensagem.setOpaque(false);
            jtaMensagem.setBackground(new Color(0, 0, 0, 0));
            jtaMensagem.setText(mensagem);
            Dimension dim = new Dimension(editor.getPreferredSize().width, 30);
            jtaMensagem.setSize(dim);
            janela.add(jtaMensagem, BorderLayout.NORTH);
        }
        JPanel jpnBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton jbtConfirmar = new JButton("Confirmar");
        jbtConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    retorno = Dados.pegaVlr(tipo, editor.getText());
                    executado = true;
                    if (aoExecutar(retorno)) {
                        if (aoConfirmar != null) {
                            aoConfirmar.actionPerformed(e);
                        }
                    } else {
                        if (aoCancelar != null) {
                            aoCancelar.actionPerformed(e);
                        }
                    }
                    Janelas.fecha(janela);
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        });
        jpnBotoes.add(jbtConfirmar);
        JButton jbtCancelar = new JButton("Cancelar");
        jbtCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Janelas.fecha(janela);
            }
        });
        jpnBotoes.add(jbtCancelar);
        janela.add(jpnBotoes, BorderLayout.SOUTH);
        janela.getRootPane().setDefaultButton(jbtConfirmar);
        Janelas.inicia(janela, this);
        Janelas.mostra(janela);
    }

    public Boolean executado() {
        return executado;
    }

    public Object retorno() {
        return retorno;
    }

}
