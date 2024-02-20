package pin.libamg;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import pin.libjan.TrataIntf;
import pin.libutl.UtlCrs;
import pin.libvlr.Vlr;

public class EdtSug extends Edt {

    public final JComboBox editor;
    public final DefaultComboBoxModel dcmEditor;

    public EdtSug(Object... comOpcoes) {
        super();
        dcmEditor = new DefaultComboBoxModel<>(comOpcoes);
        editor = new JComboBox(dcmEditor);
        editor.setEditable(true);
        add(editor, BorderLayout.CENTER);
        inicia();
    }

    @Override
    public void inicia() {
        ((JTextField) editor.getEditor().getEditorComponent()).setDisabledTextColor(Color.BLACK);
        mLargura(120);
        super.inicia();
    }

    @Override
    public void atualizaOpcoes() {
        dcmEditor.removeAllElements();
        if (opcoes() != null) {
            for (Object opc : opcoes()) {
                dcmEditor.addElement(opc);
            }
        }
    }

    @Override
    public void atualizaParams() {
    }

    public void mudaOpcoes(Object... para) {
        DefaultComboBoxModel dcm = (DefaultComboBoxModel) editor.getModel();
        dcm.removeAllElements();
        for (Object elm : para) {
            dcm.addElement(elm);
        }
    }

    @Override
    public JComboBox controle() {
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
            Object retorno = TrataIntf.pegaValorDeCombo(editor);
            if (!(retorno instanceof Vlr)) {
                retorno = Vlr.novo(retorno);;
            }
            return (Vlr) retorno;
        }
    }

    @Override
    public synchronized EdtSug mdVlr(Object para) throws Exception {
        TrataIntf.mudaValorDeCombo(editor, para);
        return this;
    }

    @Override
    public EdtSug botaAoModificar(AbstractAction aAcao) {
        editor.getEditor().addActionListener(aAcao);
        return this;
    }

    @Override
    public Edt botaFoco() {
        TrataIntf.garanteFoco((JComponent) editor.getEditor().getEditorComponent());
        return this;
    }

    @Override
    public EdtSug auxiliar() throws Exception {

        return this;
    }

    @Override
    public EdtSug aleatorio() {

        return this;
    }

    @Override
    public Boolean vazio() {
        return UtlCrs.pega(editor.getSelectedItem(), "").isEmpty();
    }

    @Override
    public EdtSug mEditavel(Boolean para) {
        editor.setEnabled(para);
        return this;
    }

    @Override
    public Boolean editavel() {
        return editor.isEnabled();
    }

    public void botaAoSelecionar(ActionListener aAcao) {
        editor.addActionListener(aAcao);
    }

}
