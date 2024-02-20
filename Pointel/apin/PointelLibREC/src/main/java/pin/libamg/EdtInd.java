package pin.libamg;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import pin.libbas.Dados;
import pin.libjan.TrataIntf;
import pin.libutl.Utl;
import pin.libvlr.Vlr;

public class EdtInd extends Edt {

    private JPanel jpnEditor;
    private JComboBox jcbTipo;
    private DefaultComboBoxModel dcmTipos;
    private Edt editor;

    public EdtInd() {
        super();
        jpnEditor = new JPanel(new BorderLayout(4, 4));
        dcmTipos = new DefaultComboBoxModel<>();
        jcbTipo = new JComboBox(dcmTipos);
        jpnEditor.add(jcbTipo, BorderLayout.NORTH);
        editor = new EdtCrs();
        jpnEditor.add(editor, BorderLayout.CENTER);
        add(jpnEditor, BorderLayout.CENTER);
        inicia();
    }

    @Override
    public void inicia() {
        for (Object tp : Dados.pTiposValorEnu()) {
            dcmTipos.addElement(tp);
        }
        TrataIntf.mudaValorDeCombo(jcbTipo, "CS");
        jcbTipo.addActionListener(new ActTipo());
        super.inicia();
    }

    @Override
    public void atualizaOpcoes() {
        editor.atualizaOpcoes();
    }

    @Override
    public void atualizaParams() {
        editor.atualizaParams();
    }

    @Override
    public JComponent controle() {
        return editor.controle();
    }

    @Override
    public JScrollPane rolagem() {
        return editor.rolagem();
    }

    @Override
    public synchronized Vlr pgVlr(Object comPadrao) throws Exception {
        return editor.pgVlr(comPadrao);
    }

    @Override
    public synchronized EdtInd mdVlr(Object para) throws Exception {
        editor.mdVlr(para);
        return this;
    }

    @Override
    public EdtInd botaAoModificar(AbstractAction aAcao) {

        return this;
    }

    @Override
    public EdtInd auxiliar() throws Exception {

        return this;
    }

    @Override
    public EdtInd aleatorio() {
        editor.aleatorio();
        return this;
    }

    @Override
    public EdtInd mEditavel(Boolean para) {
        editor.mEditavel(para);
        return this;
    }

    @Override
    public Boolean vazio() {
        return editor.vazio();
    }

    @Override
    public Boolean editavel() {
        return editor.editavel();
    }

    @Override
    public void abreEdt() {
        editor.abreEdt();
    }

    @Override
    public void fechaEdt() {
        editor.fechaEdt();
    }

    private class ActTipo extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Vlr anterior = editor.pgVlr();
                jpnEditor.remove(editor);
                Dados.Tp tipo = Dados.pTipoEnu(jcbTipo.getSelectedItem());
                editor = Edt.novo(tipo);
                jpnEditor.add(editor, BorderLayout.CENTER);
                EdtInd.this.revalidate();
                SwingUtilities.getWindowAncestor(EdtInd.this).pack();
                editor.mdVlr(anterior);
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }
}
