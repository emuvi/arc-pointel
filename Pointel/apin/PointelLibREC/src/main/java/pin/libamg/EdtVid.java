package pin.libamg;

import java.awt.BorderLayout;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import pin.libvlr.Vlr;

public class EdtVid extends Edt {

    public JScrollPane scroll;

    public EdtVid() {
        super();
        scroll = new JScrollPane();
        add(scroll, BorderLayout.CENTER);
        inicia();
    }

    @Override
    public void inicia() {
        scroll.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        scroll.setAlignmentY(JComponent.TOP_ALIGNMENT);
        mDimensao(360, 270);
        super.inicia();
    }

    @Override
    public void atualizaOpcoes() {
    }

    @Override
    public void atualizaParams() {
    }

    @Override
    public JComponent controle() {
        return null;
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
    public synchronized EdtVid mdVlr(Object para) throws Exception {
        return this;
    }

    @Override
    public EdtVid botaAoModificar(AbstractAction aAcao) {

        return this;
    }

    @Override
    public EdtVid auxiliar() throws Exception {

        return this;
    }

    @Override
    public EdtVid aleatorio() {

        return this;
    }

    @Override
    public Boolean vazio() {
        return null;
    }

    @Override
    public EdtVid mEditavel(Boolean para) {

        return this;
    }

    @Override
    public Boolean editavel() {
        return null;
    }

}
