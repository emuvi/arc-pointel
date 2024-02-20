package pin.libitr;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import pin.libbas.Dados;
import pin.libjan.Janelas;
import pin.libjan.TrataIntf;
import pin.libutl.Utl;
import pin.libutl.UtlCrs;

public class PerguntaTp {

    private Boolean confirmado;
    private Object retorno;

    public PerguntaTp() {
        this("Entrada", null, Dados.Tp.Crs, null);
    }

    public PerguntaTp(String comTitulo) {
        this(comTitulo, null, Dados.Tp.Crs, null);
    }

    public PerguntaTp(String comTitulo, String eMensagem) {
        this(comTitulo, eMensagem, Dados.Tp.Crs, null);
    }

    public PerguntaTp(String comMensagem, Dados.Tp eTipo) {
        this("Entrada", comMensagem, eTipo, null);
    }

    public PerguntaTp(String comTitulo, String eMensagem, Dados.Tp eTipo) {
        this(comTitulo, eMensagem, eTipo, null);
    }

    public PerguntaTp(String comMensagem, Dados.Tp eTipo, Object ePadrao) {
        this("Entrada", comMensagem, eTipo, ePadrao);
    }

    public PerguntaTp(String comTitulo, String eMensagem, Dados.Tp eTipo, Object ePadrao) {
        confirmado = false;
        JDialog janela = new JDialog((JFrame) null, true);
        janela.setLocationRelativeTo(null);
        janela.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        janela.setTitle(comTitulo);
        janela.setLayout(new BorderLayout(4, 4));
        janela.setResizable(false);
        janela.getRootPane().setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        JPanel jpnEditor = new JPanel();
        jpnEditor.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        JTextField editor = new JTextField(UtlCrs.pega(ePadrao));
        TrataIntf.mudaLargura(editor, 180);
        jpnEditor.add(editor);
        janela.add(jpnEditor, BorderLayout.CENTER);
        if (eMensagem != null) {
            JTextArea jtaMensagem = new JTextArea();
            jtaMensagem.setLineWrap(true);
            jtaMensagem.setWrapStyleWord(true);
            jtaMensagem.setEditable(false);
            jtaMensagem.setOpaque(false);
            jtaMensagem.setBackground(new Color(0, 0, 0, 0));
            jtaMensagem.setText(eMensagem);
            Dimension dim = new Dimension(jpnEditor.getPreferredSize().width, 90);
            jtaMensagem.setSize(dim);
            janela.add(jtaMensagem, BorderLayout.NORTH);
        }
        JPanel jpnBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton jbtConfirmar = new JButton("Confirmar");
        jbtConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    retorno = Dados.pegaVlr(eTipo, editor.getText());
                    confirmado = true;
                    Janelas.fecha(janela);
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        });
        JButton jbtCancelar = new JButton("Cancelar");
        jbtCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmado = false;
                retorno = null;
                Janelas.fecha(janela);
            }
        });
        jpnBotoes.add(jbtConfirmar);
        jpnBotoes.add(jbtCancelar);
        janela.add(jpnBotoes, BorderLayout.SOUTH);
        janela.getRootPane().setDefaultButton(jbtConfirmar);
        Janelas.inicia(janela);
        Janelas.mostra(janela);
    }

    public Boolean confirmado() {
        return confirmado;
    }

    public Object retorno() {
        return retorno;
    }
}
