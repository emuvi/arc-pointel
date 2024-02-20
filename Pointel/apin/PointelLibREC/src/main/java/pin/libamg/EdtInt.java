package pin.libamg;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import pin.libjan.TrataIntf;
import pin.libutl.Utl;
import pin.libutl.UtlCrs;
import pin.libutl.UtlInt;
import pin.libvlr.VInt;
import pin.libvlr.Vlr;

public class EdtInt extends Edt {

    public static enum Params {
        Progressivo
    };

    public static class ParamMinimo {

        public Integer valor;

        public ParamMinimo(Integer valor) {
            this.valor = valor;
        }
    }

    public static class ParamMaximo {

        public Integer valor;

        public ParamMaximo(Integer valor) {
            this.valor = valor;
        }
    }

    private final JTextField editor;
    private Integer casas;
    private JSlider progressivo;
    private Boolean progredindo;

    public EdtInt() {
        this(null);
    }

    public EdtInt(Integer comCasas) {
        super();
        editor = new JTextField();
        progressivo = null;
        add(editor, BorderLayout.CENTER);
        casas = comCasas;
        inicia();
    }

    private void ajustaValor() {
        String digitado = editor.getText().replaceAll(" ", "");
        digitado = UtlCrs.pegaSomenteInteirosComNegativos(digitado);
        Integer valor = UtlInt.pega(digitado);
        if (digitado.isEmpty()) {
            editor.setText("");
        } else {
            ParamMinimo minimo = (ParamMinimo) pegaParam(ParamMinimo.class);
            if (minimo != null) {
                valor = UtlInt.pegaMaior(valor, minimo.valor);
            }
            ParamMaximo maximo = (ParamMaximo) pegaParam(ParamMaximo.class);
            if (maximo != null) {
                valor = UtlInt.pegaMenor(valor, maximo.valor);
            }

        }
        if (valor == null) {
            editor.setText("");
        } else {
            editor.setText(UtlInt.formata(valor, casas, ""));
        }
        if (progressivo != null) {
            try {
                progredindo = true;
                if (valor == null) {
                    progressivo.setValue(progressivo.getMinimum() + ((progressivo.getMaximum() - progressivo.getMinimum()) / 2));
                } else {
                    if (valor > progressivo.getMaximum()) {
                        progressivo.setMaximum(valor);
                    }
                    if (valor < progressivo.getMinimum()) {
                        progressivo.setMinimum(valor);
                    }
                    progressivo.setValue(valor);
                }
            } catch (Exception ex) {
                Utl.registra(ex);
            } finally {
                progredindo = false;
            }
        }
    }

    @Override
    public void inicia() {
        progredindo = false;
        casas = null;
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
        mLargura(90);
        super.inicia();
    }

    @Override
    public void atualizaOpcoes() {
    }

    @Override
    public void atualizaParams() {
        if (temParam(Params.Progressivo)) {
            botaProgressivo();
        } else {
            tiraProgressivo();
        }
        if (progressivo != null) {
            ParamMinimo minimo = (ParamMinimo) pegaParam(ParamMinimo.class);
            if (minimo != null) {
                progressivo.setMinimum(minimo.valor);
            }
            ParamMaximo maximo = (ParamMaximo) pegaParam(ParamMaximo.class);
            if (maximo != null) {
                progressivo.setMaximum(maximo.valor);
            }
        }
    }

    public EdtInt botaProgressivo() {
        if (progressivo == null) {
            progressivo = new JSlider();
            ajustaValor();
            Integer atual = UtlInt.pega(editor.getText(), 0);
            progressivo.setMinimum(atual - 100);
            progressivo.setMaximum(atual + 100);
            ParamMinimo minimo = (ParamMinimo) pegaParam(ParamMinimo.class);
            if (minimo != null) {
                progressivo.setMinimum(minimo.valor);
                if (atual > minimo.valor) {
                    atual = minimo.valor;
                }
            }
            ParamMaximo maximo = (ParamMaximo) pegaParam(ParamMaximo.class);
            if (maximo != null) {
                progressivo.setMaximum(maximo.valor);
                if (atual > minimo.valor) {
                    atual = minimo.valor;
                }
            }
            progressivo.setValue(atual);
            progressivo.addChangeListener(new MudProgressivo());
            if (!ehDimensionavel()) {
                TrataIntf.mudaLargura(progressivo, 150);
            }
            add(progressivo, BorderLayout.WEST);
            revalidate();
        }
        return this;
    }

    public EdtInt tiraProgressivo() {
        if (progressivo != null) {
            remove(progressivo);
            revalidate();
            progressivo = null;
        }
        return this;
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
            return new VInt(UtlInt.pega(editor.getText()));
        }
    }

    @Override
    public synchronized EdtInt mdVlr(Object para) throws Exception {
        editor.setText(UtlInt.formata(UtlInt.pega(para)));
        return this;
    }

    @Override
    public EdtInt botaAoModificar(AbstractAction aAcao) {
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
    public EdtInt auxiliar() throws Exception {

        return this;
    }

    @Override
    public EdtInt aleatorio() {

        return this;
    }

    @Override
    public Boolean vazio() {
        ajustaValor();
        return editor.getText().isEmpty();
    }

    @Override
    public EdtInt mEditavel(Boolean para) {
        editor.setEditable(para);
        if (progressivo != null) {
            progressivo.setEnabled(para);
        }
        return this;
    }

    @Override
    public Boolean editavel() {
        return editor.isEditable();
    }

    private class MudProgressivo implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            if (!progredindo) {
                progredindo = true;
                try {
                    editor.setText(UtlInt.formata(progressivo.getValue()));
                    progressivo.setValue(pgVlr().pgInt());
                } catch (Exception ex) {
                    Utl.registra(ex);
                } finally {
                    progredindo = false;
                }
            }
        }

    }

}
