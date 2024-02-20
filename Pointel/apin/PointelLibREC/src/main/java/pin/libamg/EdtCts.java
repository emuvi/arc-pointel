package pin.libamg;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import pin.libvlr.Vlr;

public class EdtCts extends Edt {

    private JPanel editor;
    private CardLayout layout;
    private EdtPaiCols cartoes;

    public EdtCts() {
        super();
        editor = new JPanel();
        add(editor, BorderLayout.CENTER);
        inicia();
    }

    @Override
    public void inicia() {
        layout = new CardLayout();
        editor.setLayout(layout);
        cartoes = new EdtPaiCols();
        editor.setFocusable(false);
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
    public JPanel controle() {
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
    public synchronized EdtCts mdVlr(Object para) throws Exception {
        return this;
    }

    @Override
    public EdtCts botaAoModificar(AbstractAction aAcao) {

        return this;
    }

    @Override
    public EdtCts auxiliar() throws Exception {

        return this;
    }

    @Override
    public EdtCts aleatorio() {

        return this;
    }

    @Override
    public Boolean vazio() {
        return false;
    }

    @Override
    public EdtCts mEditavel(Boolean para) {
        editor.setEnabled(para);
        return this;
    }

    @Override
    public Boolean editavel() {
        return editor.isEnabled();
    }

    public EdtCts botaCartao(EdtPai oCartao) {
        cartoes.add(oCartao);
        return this;
    }

    public EdtCts mostra(String comNome) {
        layout.show(editor, comNome);
        return this;
    }

    public EdtCts mostra(Integer comIndice) {
        layout.show(editor, comIndice.toString());
        return this;
    }

    public EdtCts controi() {
        Integer idx = 0;
        for (EdtPai col : cartoes) {
            if (col.pNome() != null) {
                editor.add(col.constroi(), col.pNome());
            } else {
                editor.add(col.constroi(), idx.toString());
            }
            idx++;
        }
        editor.revalidate();
        return this;
    }

}
