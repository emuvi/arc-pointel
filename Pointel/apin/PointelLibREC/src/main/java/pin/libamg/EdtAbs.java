package pin.libamg;

import java.awt.BorderLayout;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import pin.libvlr.Vlr;

public class EdtAbs extends Edt {

    public JTabbedPane editor;
    public EdtPaiCols abas;

    public EdtAbs() {
        super();
        editor = new JTabbedPane();
        add(editor, BorderLayout.CENTER);
        inicia();
    }

    @Override
    public void inicia() {
        abas = new EdtPaiCols();
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
    public JTabbedPane controle() {
        return editor;
    }

    @Override
    public JScrollPane rolagem() {
        return null;
    }

    @Override
    public synchronized Vlr pgVlr(Object comPadrao) throws Exception {
        return null;
    }

    @Override
    public synchronized EdtAbs mdVlr(Object para) throws Exception {
        return this;
    }

    @Override
    public EdtAbs botaAoModificar(AbstractAction aAcao) {

        return this;
    }

    @Override
    public EdtAbs auxiliar() throws Exception {

        return this;
    }

    @Override
    public EdtAbs aleatorio() {

        return this;
    }

    @Override
    public Boolean vazio() {
        return false;
    }

    @Override
    public EdtAbs mEditavel(Boolean para) {
        editor.setEnabled(para);
        return this;
    }

    @Override
    public Boolean editavel() {
        return editor.isEnabled();
    }

    public EdtAbs botaAba(EdtPai aAba) {
        abas.add(aAba);
        return this;
    }

    public EdtAbs controi() {
        for (EdtPai col : abas) {
            if (col.pTitulo() != null) {
                if (col.pTitulo().isEmpty()) {
                    col.botaTitulo(null);
                }
            }
            if (col.pTitulo() != null) {
                editor.add(col.pTitulo(), col.constroi());
                editor.setMnemonicAt(editor.getTabCount() - 1, col.pTitulo().charAt(0));
            } else {
                editor.add(col.constroi());
            }
        }
        editor.revalidate();
        return this;
    }

}
