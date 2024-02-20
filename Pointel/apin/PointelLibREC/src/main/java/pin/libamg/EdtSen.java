package pin.libamg;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import pin.libutl.UtlCrs;
import pin.libvlr.VCrs;
import pin.libvlr.Vlr;

public class EdtSen extends Edt {

    public static enum Params {
        CONFIRMAR
    };

    public final JPasswordField editor;
    public Integer tamanho;

    public EdtSen() {
        this(null);
    }

    public EdtSen(Integer comTamanho) {
        super();
        editor = new JPasswordField();
        add(editor, BorderLayout.CENTER);
        tamanho = comTamanho;
        inicia();
    }

    private void mantemTamanho() {
        if (tamanho != null) {
            if (tamanho > 0) {
                if (new String(editor.getPassword()).length() > tamanho) {
                    editor.setText(new String(editor.getPassword()).substring(0, tamanho));
                }
            }
        }
    }

    @Override
    public void inicia() {
        editor.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                editor.selectAll();
            }
        });
        editor.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                mantemTamanho();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                mantemTamanho();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                mantemTamanho();
            }
        });
        editor.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        editor.setAlignmentY(JComponent.TOP_ALIGNMENT);
        editor.setDisabledTextColor(UIManager.getColor("TextField.foreground"));
        Integer larg = 180;
        if (tamanho != null) {
            if (tamanho > 0) {
                larg = 60 + tamanho * 3;
            }
        }
        if (larg > 360) {
            larg = 360;
        }
        mLargura(larg);
        super.inicia();
    }

    @Override
    public void atualizaOpcoes() {
    }

    @Override
    public void atualizaParams() {
    }

    @Override
    public JPasswordField controle() {
        return editor;
    }

    @Override
    public JScrollPane rolagem() {
        return null;
    }

    public String pegaSenha() throws Exception {
        String retorno = new String(editor.getPassword());
        if (temParam(Params.CONFIRMAR)) {
            String titulo = "";
            if (pTitulo() != null) {
                titulo = " do Campo " + pTitulo();
            }
            String conf = UtlCrs.perguntaSenha("Confirme a Senha" + titulo);
            if (conf == null || !Objects.equals(retorno, conf)) {
                throw new Exception("Senha" + titulo + " Não Confirmou");
            }
        }

        return retorno;
    }

    @Override
    public synchronized Vlr pgVlr(Object comPadrao) throws Exception {
        return new VCrs(pegaSenha());
    }

    @Override
    public synchronized EdtSen mdVlr(Object para) throws Exception {
        editor.setText(UtlCrs.pega(para));
        return this;
    }

    @Override
    public EdtSen botaAoModificar(AbstractAction aAcao) {
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
    public EdtSen auxiliar() throws Exception {

        return this;
    }

    @Override
    public EdtSen aleatorio() {

        return this;
    }

    @Override
    public Boolean vazio() {
        return new String(editor.getPassword()).isEmpty();
    }

    @Override
    public EdtSen mEditavel(Boolean para) {
        editor.setEditable(para);
        return this;
    }

    @Override
    public Boolean editavel() {
        return editor.isEditable();
    }

}
