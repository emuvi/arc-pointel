package pin.libamg;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.AbstractAction;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import pin.libutl.UtlLog;
import pin.libvlr.VLog;
import pin.libvlr.Vlr;

public class EdtLog extends Edt {

    public final JCheckBox editor;

    public EdtLog() {
        super();
        editor = new JCheckBox();
        add(editor, BorderLayout.CENTER);
        inicia();
    }

    @Override
    public void inicia() {
        editor.setOpaque(false);
        editor.setBackground(new Color(0, 0, 0, 0));
        editor.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        editor.setAlignmentY(JComponent.TOP_ALIGNMENT);
        super.inicia();
    }

    @Override
    public void atualizaOpcoes() {
    }

    @Override
    public void atualizaParams() {
    }

    @Override
    public JCheckBox controle() {
        return editor;
    }

    @Override
    public JScrollPane rolagem() {
        return null;
    }

    @Override
    public synchronized Vlr pgVlr(Object comPadrao) throws Exception {
        return new VLog(editor.isSelected());
    }

    @Override
    public synchronized EdtLog mdVlr(Object para) throws Exception {
        editor.setSelected(UtlLog.pega(para, false));
        return this;
    }

    @Override
    public EdtLog botaAoModificar(AbstractAction aAcao) {
        editor.addActionListener(aAcao);
        return this;
    }

    @Override
    public EdtLog auxiliar() throws Exception {

        return this;
    }

    @Override
    public EdtLog aleatorio() {

        return this;
    }

    @Override
    public Boolean vazio() {
        return false;
    }

    @Override
    public EdtLog mEditavel(Boolean para) {
        editor.setEnabled(para);
        return this;
    }

    @Override
    public Boolean editavel() {
        return editor.isEnabled();
    }

}
