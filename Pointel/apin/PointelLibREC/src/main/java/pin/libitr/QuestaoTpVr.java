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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import pin.libbas.Dados;
import pin.libjan.Janelas;
import pin.libjan.TrataIntf;
import pin.libutl.Utl;
import pin.libutl.UtlCrs;

public class QuestaoTpVr {

    private String mensagem;
    private Object padrao;
    private AbstractAction aoConfirmar;
    private AbstractAction aoCancelar;
    private Boolean executado;
    private Object retorno;

    public QuestaoTpVr() {
        this(null, null);
    }

    public QuestaoTpVr(String comMensagen) {
        this(comMensagen, null);
    }

    public QuestaoTpVr(String comMensagem, Object ePadrao) {
        mensagem = comMensagem;
        padrao = ePadrao;
        aoConfirmar = null;
        aoCancelar = null;
        executado = false;
        retorno = null;
    }

    public Boolean aoExecutar(Object comRetorno) {
        return true;
    }

    public AbstractAction aoConfirmar() {
        return aoConfirmar;
    }

    public QuestaoTpVr botaAoConfirmar(AbstractAction aAcao) {
        aoConfirmar = aAcao;
        return this;
    }

    public AbstractAction aoCancelar() {
        return aoCancelar;
    }

    public QuestaoTpVr botaAoCancelar(AbstractAction aAcao) {
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
        jpnEditor.setLayout(new BorderLayout(4, 4));
        JComboBox jcbTipo = new JComboBox(Dados.pTiposBasicosEnu());
        if (padrao != null) {
            jcbTipo.setSelectedItem(Dados.pNomeCompletoEnu(Dados.pega(padrao)));
        } else {
            jcbTipo.setSelectedItem(Dados.pNomeCompletoEnu(Dados.Tp.Crs));
        }
        jpnEditor.add(jcbTipo, BorderLayout.WEST);
        JTextField jtfEditor = new JTextField(UtlCrs.pega(padrao));
        TrataIntf.mudaLargura(jtfEditor, 180);
        jpnEditor.add(jtfEditor, BorderLayout.CENTER);
        if (mensagem != null) {
            JTextArea jtaMensagem = new JTextArea();
            jtaMensagem.setLineWrap(true);
            jtaMensagem.setWrapStyleWord(true);
            jtaMensagem.setEditable(false);
            jtaMensagem.setOpaque(false);
            jtaMensagem.setBackground(new Color(0, 0, 0, 0));

            jtaMensagem.setText(mensagem);
            Dimension dim = new Dimension(jpnEditor.getPreferredSize().width, 30);
            jtaMensagem.setSize(dim);

            janela.add(jtaMensagem, BorderLayout.NORTH);
        }
        janela.add(jpnEditor, BorderLayout.CENTER);
        JPanel jpnBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton jbtConfirmar = new JButton("Confirmar");
        jbtConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    retorno = Dados.pegaVlr(Dados.pTipoEnu(TrataIntf.pegaValorDeCombo(jcbTipo)), jtfEditor.getText());
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
                executado = false;
                retorno = null;
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
