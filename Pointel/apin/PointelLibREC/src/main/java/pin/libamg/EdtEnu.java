package pin.libamg;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import pin.libjan.TrataIntf;
import pin.libutl.UtlCrs;
import pin.libvlr.Vlr;

public class EdtEnu extends Edt {

    public final JComboBox editor;
    public final DefaultComboBoxModel dcmEditor;

    public EdtEnu() {
        super();
        dcmEditor = new DefaultComboBoxModel<>();
        editor = new JComboBox(dcmEditor);
        add(editor, BorderLayout.CENTER);
        inicia();
    }

    @Override
    public void inicia() {
        editor.setEditable(false);
        editor.addKeyListener(new EvtTeclado());
        editor.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        editor.setAlignmentY(JComponent.TOP_ALIGNMENT);
        ((JTextField) editor.getEditor().getEditorComponent()).setDisabledTextColor(Color.BLACK);
        mLargura(120);
        super.inicia();
    }

    @Override
    public void atualizaOpcoes() {
        int tamax = 0;
        dcmEditor.removeAllElements();
        if (opcoes() != null) {
            for (Object opc : opcoes()) {
                dcmEditor.addElement(opc);
                String opcStr = opc.toString();
                if (opcStr.length() > tamax) {
                    tamax = opcStr.length();
                }
            }
        }
        if (tamax > 0) {
            tamax = 90 + tamax * 3;
            if (tamax > 210) {
                tamax = 210;
            }
        } else {
            tamax = 120;
        }
        mLargura(tamax);
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
    public synchronized EdtEnu mdVlr(Object para) throws Exception {
        TrataIntf.mudaValorDeCombo(editor, para);
        TrataIntf.aciona(editor, null);
        return this;
    }

    @Override
    public EdtEnu botaAoModificar(AbstractAction aAcao) {
        editor.getEditor().addActionListener(aAcao);
        return this;
    }

    @Override
    public EdtEnu auxiliar() throws Exception {

        return this;
    }

    @Override
    public EdtEnu aleatorio() {

        return this;
    }

    @Override
    public Boolean vazio() {
        return UtlCrs.pega(editor.getSelectedItem(), "").isEmpty();
    }

    @Override
    public EdtEnu mEditavel(Boolean para) {
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

    private class EvtTeclado extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            String pr = e.getKeyChar() + "";
            pr = pr.toUpperCase();
            int sel = editor.getSelectedIndex();
            boolean achou = false;
            for (int is = sel + 1; is < dcmEditor.getSize(); is++) {
                String isVal = UtlCrs.pega(dcmEditor.getElementAt(is), "");
                if (isVal.startsWith("(") && isVal.length() > 1) {
                    isVal = isVal.substring(1);
                }
                if (isVal.startsWith(pr)) {
                    editor.setSelectedIndex(is);
                    achou = true;
                    break;
                }
            }
            if (!achou) {
                for (int is = 0; is < sel; is++) {
                    String isVal = UtlCrs.pega(dcmEditor.getElementAt(is), "");
                    if (isVal.startsWith("(") && isVal.length() > 1) {
                        isVal = isVal.substring(1);
                    }
                    if (isVal.startsWith(pr)) {
                        editor.setSelectedIndex(is);
                        break;
                    }
                }
            }
        }
    }

}
