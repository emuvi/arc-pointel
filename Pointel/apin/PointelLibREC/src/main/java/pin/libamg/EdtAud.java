package pin.libamg;

import java.awt.BorderLayout;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import pin.libvlr.Vlr;

public class EdtAud extends Edt {

    public JScrollPane scroll;

    public EdtAud() {
        super();
        scroll = new JScrollPane();
        add(scroll, BorderLayout.CENTER);
        inicia();
    }

    @Override
    public void inicia() {
        mDimensao(360, 120);
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
        if (vazio()) {
            return Vlr.novo(comPadrao);
        } else {
            return null;
        }
    }

    @Override
    public synchronized EdtAud mdVlr(Object para) throws Exception {
        return this;
    }

    @Override
    public EdtAud botaAoModificar(AbstractAction aAcao) {

        return this;
    }

    @Override
    public EdtAud auxiliar() throws Exception {

        return this;
    }

    @Override
    public EdtAud aleatorio() {

        return this;
    }

    @Override
    public Boolean vazio() {
        return null;
    }

    @Override
    public EdtAud mEditavel(Boolean para) {

        return this;
    }

    @Override
    public Boolean editavel() {
        return null;
    }

}
