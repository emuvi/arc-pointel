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
import pin.libutl.UtlNum;
import pin.libvlr.VNum;
import pin.libvlr.Vlr;

public class EdtNum extends Edt {

    public static enum Params {
        Progressivo
    };

    public static class ParamMinimo {

        public Float valor;

        public ParamMinimo(Float valor) {
            this.valor = valor;
        }
    }

    public static class ParamMaximo {

        public Float valor;

        public ParamMaximo(Float valor) {
            this.valor = valor;
        }
    }

    public static class ParamPasso {

        public Float valor;

        public ParamPasso(Float valor) {
            this.valor = valor;
        }
    }

    public final JTextField editor;
    private Integer casas;
    private Integer precisao;
    private JSlider progressivo;
    private Boolean progredindo;
    private Float passo;

    public EdtNum() {
        this(null, null);
    }

    public EdtNum(Integer comCasas, Integer ePrecisao) {
        super();
        editor = new JTextField();
        progressivo = null;
        add(editor, BorderLayout.CENTER);
        casas = comCasas;
        precisao = ePrecisao;
        inicia();
    }

    private Integer transforma(Float doValor) {
        return Math.round(doValor / passo);
    }

    private Float transforma(Integer doValor) {
        return doValor * passo;
    }

    private void ajustaValor() {
        String digitado = editor.getText().replaceAll(" ", "");
        digitado = UtlCrs.pegaSomenteNumericosComNegativos(digitado);
        Float valor = UtlNum.pega(digitado);
        if (digitado.isEmpty()) {
            editor.setText("");
        } else {
            ParamMinimo minimo = (ParamMinimo) pegaParam(ParamMinimo.class);
            if (minimo != null) {
                valor = UtlNum.pegaMaior(valor, minimo.valor);
            }
            ParamMaximo maximo = (ParamMaximo) pegaParam(ParamMaximo.class);
            if (maximo != null) {
                valor = UtlNum.pegaMenor(valor, maximo.valor);
            }
        }
        if (valor == null) {
            editor.setText("");
        } else {
            editor.setText(UtlNum.formata(valor));
        }
        if (progressivo != null) {
            try {
                progredindo = true;
                if (valor == null) {
                    progressivo.setValue(progressivo.getMinimum() + ((progressivo.getMaximum() - progressivo.getMinimum()) / 2));
                } else {
                    Integer valRel = transforma(valor);
                    if (valor > progressivo.getMaximum()) {
                        progressivo.setMaximum(valRel);
                    }
                    if (valor < progressivo.getMinimum()) {
                        progressivo.setMinimum(valRel);
                    }
                    progressivo.setValue(valRel);
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
        passo = 0.1f;
        editor.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                editor.selectAll();
            }

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
        ParamPasso parPasso = (ParamPasso) pegaParam(ParamPasso.class);
        if (parPasso != null) {
            passo = parPasso.valor;
        } else {
            passo = 0.1f;
        }
        if (temParam(Params.Progressivo)) {
            botaProgressivo();
        } else {
            tiraProgressivo();
        }
        if (progressivo != null) {
            ParamMinimo minimo = (ParamMinimo) pegaParam(ParamMinimo.class);
            if (minimo != null) {
                progressivo.setMinimum(transforma(minimo.valor));
            }
            ParamMaximo maximo = (ParamMaximo) pegaParam(ParamMaximo.class);
            if (maximo != null) {
                progressivo.setMaximum(transforma(maximo.valor));
            }
        }
    }

    public EdtNum botaProgressivo() {
        if (progressivo == null) {
            progressivo = new JSlider();
            ajustaValor();
            Integer atual = transforma(UtlNum.pega(editor.getText(), 0.0f));
            progressivo.setMinimum(atual - Math.round(100 / passo));
            progressivo.setMaximum(atual + Math.round(100 / passo));
            ParamMinimo minimo = (ParamMinimo) pegaParam(ParamMinimo.class);
            if (minimo != null) {
                progressivo.setMinimum(transforma(minimo.valor));
            }
            ParamMaximo maximo = (ParamMaximo) pegaParam(ParamMaximo.class);
            if (maximo != null) {
                progressivo.setMaximum(transforma(maximo.valor));
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

    public EdtNum tiraProgressivo() {
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
            return new VNum(UtlNum.pega(editor.getText()));
        }
    }

    @Override
    public synchronized EdtNum mdVlr(Object para) throws Exception {
        editor.setText(UtlNum.formata(UtlNum.pega(para)));
        return this;
    }

    @Override
    public EdtNum botaAoModificar(AbstractAction aAcao) {
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
    public EdtNum auxiliar() throws Exception {

        return this;
    }

    @Override
    public EdtNum aleatorio() {

        return this;
    }

    @Override
    public Boolean vazio() {
        ajustaValor();
        return editor.getText().isEmpty();
    }

    @Override
    public EdtNum mEditavel(Boolean para) {
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
                    editor.setText(UtlNum.formata(transforma(progressivo.getValue())));
                    progressivo.setValue(transforma(pgVlr().pgNum()));
                } catch (Exception ex) {
                    Utl.registra(ex);
                } finally {
                    progredindo = false;
                }
            }
        }

    }

}
