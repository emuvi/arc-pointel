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
import pin.libpic.Pics;
import pin.libutl.UtlDat;
import pin.libvlr.VDat;
import pin.libvlr.Vlr;

public class EdtDat extends Edt {

    public static enum Params {
        BOTA_ESCOLHA, TIRA_ESCOLHA, BOTA_EDITOR, TIRA_EDITOR
    };

    private final JTextField editor;
    private EdtBot botEscolha;

    public EdtDat() {
        super();
        editor = new JTextField();
        add(editor, BorderLayout.CENTER);
        inicia();
    }

    private void ajustaValor() {
        editor.setText(UtlDat.ajustaDigitado(editor.getText()));
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
        botEscolha = null;
        mLargura(100);
        super.inicia();
    }

    @Override
    public void atualizaOpcoes() {
    }

    @Override
    public void atualizaParams() {
        if (temParam(Params.BOTA_ESCOLHA)) {
            if (botEscolha == null) {
                botEscolha = new BotAuxiliar();
                botaBotao(botEscolha);
            }
        }
        if (temParam(Params.TIRA_ESCOLHA)) {
            if (botEscolha != null) {
                tiraBotao(botEscolha);
                botEscolha = null;
            }
        }
        if (temParam(Params.BOTA_EDITOR)) {
            editor.setVisible(true);
        }
        if (temParam(Params.TIRA_EDITOR)) {
            editor.setVisible(false);
        }
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
            return new VDat(editor.getText());
        }
    }

    @Override
    public synchronized EdtDat mdVlr(Object para) throws Exception {
        editor.setText(UtlDat.formata(UtlDat.pega(para)));
        return this;
    }

    @Override
    public EdtDat botaAoModificar(AbstractAction aAcao) {
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
    public EdtDat auxiliar() throws Exception {
        new EdtDatAux(EdtDat.this).mostra();
        return this;
    }

    @Override
    public EdtDat aleatorio() {

        return this;
    }

    @Override
    public Boolean vazio() {
        ajustaValor();
        return editor.getText().isEmpty();
    }

    @Override
    public EdtDat mEditavel(Boolean para) {
        editor.setEditable(para);
        return this;
    }

    @Override
    public Boolean editavel() {
        return editor.isEditable();
    }

    private class BotAuxiliar extends EdtBot {

        public BotAuxiliar() {
            super(Pics.pega("calendar.png"));
            mAoConfirmar(new ActValAuxiliar());
        }
    }

}
