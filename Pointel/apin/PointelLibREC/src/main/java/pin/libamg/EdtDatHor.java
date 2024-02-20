package pin.libamg;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import pin.libutl.UtlDatHor;
import pin.libvlr.VDatHor;
import pin.libvlr.Vlr;

public class EdtDatHor extends Edt {

    public final JTextField editor;

    public EdtDatHor() {
        super();
        editor = new JTextField();
        add(editor, BorderLayout.CENTER);
        inicia();
    }

    private void ajustaValor() {
        editor.setText(UtlDatHor.ajustaDigitado(editor.getText()));
    }

    @Override
    public void inicia() {
        editor.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                editor.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
                ajustaValor();
            }
        });
        editor.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        editor.setAlignmentY(JComponent.TOP_ALIGNMENT);
        editor.setDisabledTextColor(UIManager.getColor("TextField.foreground"));
        mLargura(140);
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
        ajustaValor();
        if (vazio()) {
            return Vlr.novo(comPadrao);
        } else {
            return new VDatHor(editor.getText());
        }
    }

    @Override
    public synchronized EdtDatHor mdVlr(Object para) throws Exception {
        if (para instanceof Vlr) {
            para = ((Vlr) para).pg();
        }
        editor.setText(UtlDatHor.formata(UtlDatHor.pega(para)));
        return this;
    }

    @Override
    public EdtDatHor botaAoModificar(AbstractAction aAcao) {
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
    public EdtDatHor auxiliar() throws Exception {

        return this;
    }

    @Override
    public EdtDatHor aleatorio() {

        return this;
    }

    @Override
    public Boolean vazio() {
        ajustaValor();
        return editor.getText().isEmpty();
    }

    @Override
    public EdtDatHor mEditavel(Boolean para) {
        editor.setEditable(para);
        return this;
    }

    @Override
    public Boolean editavel() {
        return editor.isEditable();
    }

}
