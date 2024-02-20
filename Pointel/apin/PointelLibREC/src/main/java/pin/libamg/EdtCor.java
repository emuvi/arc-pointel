package pin.libamg;

import java.awt.BorderLayout;
import java.awt.Color;
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
import pin.libamk.Botao;
import pin.libpic.Pics;
import pin.libitr.QuestaoCor;
import pin.libutl.UtlCor;
import pin.libvlr.VCor;
import pin.libvlr.Vlr;

public class EdtCor extends Edt {

    private final JTextField editor;
    private Botao botao;

    public EdtCor() {
        super();
        editor = new JTextField();
        add(editor, BorderLayout.CENTER);
        inicia();
    }

    @Override
    public void inicia() {
        botao = new Botao(Pics.pega("color_wheel.png"), 0) {
            @Override
            public void aoExecutar(Object comOrigem) throws Exception {
                new QuestaoCor(UtlCor.pega(editor.getText())) {
                    @Override
                    public Boolean aoEscolher(Color comEscolhida) {
                        editor.setText(UtlCor.formata(comEscolhida));
                        return true;
                    }
                }.mostra();
            }
        };
        botaBotao(botao);
        editor.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                editor.selectAll();
            }
        });
        editor.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        editor.setAlignmentY(JComponent.TOP_ALIGNMENT);
        editor.setDisabledTextColor(UIManager.getColor("TextField.foreground"));
        mLargura(100);
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
            return new VCor(UtlCor.pega(editor.getText()));
        }
    }

    @Override
    public synchronized EdtCor mdVlr(Object para) throws Exception {
        editor.setText(UtlCor.formata(UtlCor.pega(para)));
        return this;
    }

    @Override
    public EdtCor botaAoModificar(AbstractAction aAcao) {
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
    public EdtCor auxiliar() throws Exception {

        return this;
    }

    @Override
    public EdtCor aleatorio() {

        return this;
    }

    @Override
    public Boolean vazio() {
        return editor.getText().isEmpty();
    }

    @Override
    public EdtCor mEditavel(Boolean para) {
        editor.setEditable(para);
        return this;
    }

    @Override
    public Boolean editavel() {
        return editor.isEditable();
    }

}
