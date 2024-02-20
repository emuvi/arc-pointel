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
import pin.libutl.UtlNumLon;
import pin.libvlr.VNumLon;
import pin.libvlr.Vlr;

public class EdtNumLon extends Edt {

    public static enum Params {
        Progressivo
    };

    public static class ParamMinimo {

        public Double valor;

        public ParamMinimo(Double valor) {
            this.valor = valor;
        }
    }

    public static class ParamMaximo {

        public Double valor;

        public ParamMaximo(Double valor) {
            this.valor = valor;
        }
    }

    public static class ParamPasso {

        public Double valor;

        public ParamPasso(Double valor) {
            this.valor = valor;
        }
    }

    public JTextField editor;
    private Integer casas;
    private Integer precisao;
    private JSlider progressivo;
    private Boolean progredindo;
    private Double passo;

    public EdtNumLon() {
        this(null, null);
    }

    public EdtNumLon(Integer comCasas, Integer ePrecisao) {
        super();
        editor = new JTextField();
        progressivo = null;
        add(editor, BorderLayout.CENTER);
        casas = comCasas;
        precisao = ePrecisao;
        inicia();
    }

    private Integer transforma(Double doValor) {
        return new Long(Math.round(doValor / passo)).intValue();
    }

    private Double transforma(Integer doValor) {
        return doValor * passo;
    }

    private void ajustaValor() {
        String digitado = editor.getText().replaceAll(" ", "");
        digitado = UtlCrs.pegaSomenteNumericosComNegativos(digitado);
        Double valor = UtlNumLon.pega(digitado);
        if (digitado.isEmpty()) {
            editor.setText("");
        } else {
            ParamMinimo minimo = (ParamMinimo) pegaParam(ParamMinimo.class);
            if (minimo != null) {
                valor = UtlNumLon.pegaMaior(valor, minimo.valor);
            }
            ParamMaximo maximo = (ParamMaximo) pegaParam(ParamMaximo.class);
            if (maximo != null) {
                valor = UtlNumLon.pegaMenor(valor, maximo.valor);
            }
        }
        if (valor == null) {
            editor.setText("");
        } else {
            editor.setText(UtlNumLon.formata(valor));
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
        passo = 0.1;
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
        ParamPasso parPasso = (ParamPasso) pegaParam(ParamPasso.class);
        if (parPasso != null) {
            passo = parPasso.valor;
        } else {
            passo = 0.1;
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

    public EdtNumLon botaProgressivo() {
        if (progressivo == null) {
            progressivo = new JSlider();
            ajustaValor();
            Integer atual = transforma(UtlNumLon.pega(editor.getText(), 0.0));
            progressivo.setMinimum(atual - new Long(Math.round(100 / passo)).intValue());
            progressivo.setMaximum(atual + new Long(Math.round(100 / passo)).intValue());
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

    public EdtNumLon tiraProgressivo() {
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
            return new VNumLon(UtlNumLon.pega(editor.getText()));
        }
    }

    @Override
    public synchronized EdtNumLon mdVlr(Object para) throws Exception {
        editor.setText(UtlNumLon.formata(UtlNumLon.pega(para)));
        return this;
    }

    @Override
    public EdtNumLon botaAoModificar(AbstractAction aAcao) {
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
    public EdtNumLon auxiliar() throws Exception {

        return this;
    }

    @Override
    public EdtNumLon aleatorio() {

        return this;
    }

    @Override
    public Boolean vazio() {
        ajustaValor();
        return editor.getText().isEmpty();
    }

    @Override
    public EdtNumLon mEditavel(Boolean para) {
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
                    editor.setText(UtlNumLon.formata(transforma(progressivo.getValue())));
                    progressivo.setValue(transforma(pgVlr().pgNumLon()));
                } catch (Exception ex) {
                    Utl.registra(ex);
                } finally {
                    progredindo = false;
                }
            }
        }

    }

}
