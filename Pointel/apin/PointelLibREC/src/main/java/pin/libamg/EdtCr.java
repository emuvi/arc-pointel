package pin.libamg;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import pin.libutl.UtlCr;
import pin.libvlr.VCr;
import pin.libvlr.Vlr;

public class EdtCr extends Edt {

    public final JTextField editor;

    public EdtCr() {
        super();
        editor = new JTextField();
        add(editor, BorderLayout.CENTER);
        inicia();
    }

    private void mantemTamanho() {
        if (editor.getText().length() > 1) {
            editor.setText(editor.getText().substring(0, 1));
        }
    }

    @Override
    public void inicia() {
        editor.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                editor.selectAll();
            }
        });
        editor.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                mantemTamanho();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                mantemTamanho();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                mantemTamanho();
            }
        });
        editor.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        editor.setAlignmentY(JComponent.TOP_ALIGNMENT);
        editor.setDisabledTextColor(UIManager.getColor("TextField.foreground"));
        mLargura(60);
        super.inicia();
    }

    @Override
    public void atualizaOpcoes() {
    }

    @Override
    public void atualizaParams() {
    }

    @Override
    public JTextField controle() {
        return editor;
    }

    @Override
    public JScrollPane rolagem() {
        return null;
    }

    @Override
    public synchronized Vlr pgVlr(Object comPadrao) throws Exception {
        if (vazio()) {
            return Vlr.novo(comPadrao);
        } else {
            return new VCr(editor.getText());
        }
    }

    @Override
    public synchronized EdtCr mdVlr(Object para) throws Exception {
        if (para == null) {
            editor.setText("");
        } else {
            editor.setText(UtlCr.pega(para) + "");
        }
        return this;
    }

    @Override
    public EdtCr botaAoModificar(AbstractAction aAcao) {
        editor.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                aAcao.actionPerformed(new ActionEvent(editor, 0, e.getLength() + "," + e.getOffset()));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                aAcao.actionPerformed(new ActionEvent(editor, 1, e.getLength() + "," + e.getOffset()));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                aAcao.actionPerformed(new ActionEvent(editor, 2, e.getLength() + "," + e.getOffset()));
            }
        });
        return this;
    }

    @Override
    public EdtCr auxiliar() throws Exception {

        return this;
    }

    @Override
    public EdtCr aleatorio() {

        return this;
    }

    @Override
    public Boolean vazio() {
        return editor.getText().isEmpty();
    }

    @Override
    public EdtCr mEditavel(Boolean para) {
        editor.setEditable(para);
        return this;
    }

    @Override
    public Boolean editavel() {
        return editor.isEditable();
    }

}
