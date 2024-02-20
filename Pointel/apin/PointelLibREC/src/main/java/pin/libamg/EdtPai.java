package pin.libamg;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import pin.libout.GBCWrap;
import pin.libvlr.Vlr;

public class EdtPai extends Edt {

    private final JPanel editor;
    private final EdtPaiLins linhas;

    public EdtPai() {
        super();
        editor = new JPanel(new GridBagLayout());
        linhas = new EdtPaiLins();
        add(editor, BorderLayout.CENTER);
        inicia();
    }

    @Override
    public void inicia() {
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
    public synchronized EdtPai mdVlr(Object para) throws Exception {
        return this;
    }

    @Override
    public EdtPai botaAoModificar(AbstractAction aAcao) {

        return this;
    }

    @Override
    public EdtPai auxiliar() throws Exception {

        return this;
    }

    @Override
    public EdtPai aleatorio() {

        return this;
    }

    @Override
    public Boolean vazio() {
        return false;
    }

    @Override
    public EdtPai mEditavel(Boolean para) {
        editor.setEnabled(para);
        return this;
    }

    @Override
    public Boolean editavel() {
        return editor.isEnabled();
    }

    public EdtPai botaEditor(Edt oEditor, EdtPaiLis comPais) {
        if (oEditor instanceof EdtAbs || oEditor instanceof EdtCts || oEditor instanceof EdtPai) {
            comPais.add(oEditor);
        }
        EdtPaiLin lin = null;
        if (oEditor.temPai()) {
            Edt pai = comPais.pega(oEditor.pPai());
            if (pai instanceof EdtPai) {
                lin = ((EdtPai) pai).linhas.pegaLinha(oEditor.pPaiLinha());
            } else if (pai instanceof EdtAbs) {
                if (oEditor instanceof EdtPai) {
                    ((EdtAbs) pai).botaAba((EdtPai) oEditor);
                    oEditor.botaTituloInvisivel();
                    return this;
                }
            } else if (pai instanceof EdtCts) {
                if (oEditor instanceof EdtPai) {
                    ((EdtCts) pai).botaCartao((EdtPai) oEditor);
                    return this;
                }
            }
        }
        if (lin == null) {
            lin = linhas.pegaLinha(oEditor.pPaiLinha());
        }
        lin.botaColuna(oEditor);
        return this;
    }

    public EdtPai constroi() {
        int indice = 0;
        for (EdtPaiLin lin : linhas) {
            editor.add(lin.controi(), new GBCWrap(0, indice).setAnchor(GridBagConstraints.NORTHWEST).setFill(GridBagConstraints.HORIZONTAL).setWeight(1, 0));
            indice++;
        }
        editor.add(new JPanel(), new GBCWrap(0, indice).setAnchor(GridBagConstraints.NORTHWEST).setFill(GridBagConstraints.BOTH).setWeight(1, 1));
        editor.revalidate();
        return this;
    }

}
