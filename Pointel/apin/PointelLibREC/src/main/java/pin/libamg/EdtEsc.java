package pin.libamg;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import pin.libout.JCheckList;
import pin.libutl.UtlLis;
import pin.libvlr.Vlr;
import pin.libvlr.VEsc;
import pin.libitr.FormulaCrs;

public class EdtEsc extends Edt {

    public static enum Params {
        MULTIPLOS
    };

    public JScrollPane scroll;
    public JCheckList editor;

    public EdtEsc() {
        super();
        editor = new JCheckList();
        scroll = new JScrollPane(editor);
        add(scroll, BorderLayout.CENTER);
        inicia();
    }

    @Override
    public void inicia() {
        scroll.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        scroll.setAlignmentY(JComponent.TOP_ALIGNMENT);
        mDimensao(180, 180);
        pPopMenu().bota("Procurar", new ActProcurar());
        super.inicia();
    }

    @Override
    public void atualizaOpcoes() {
        editor.clear();
        if (opcoes() != null) {
            for (Object opc : opcoes()) {
                editor.addItem(opc);
            }
        }
    }

    @Override
    public void atualizaParams() {
        if (temParam(Params.MULTIPLOS)) {
            botaSelecaoMultipa();
        } else {
            botaSelecaoSimples();
        }
    }

    public void botaSelecaoSimples() {
        editor.setSelectionSimple();
    }

    public void botaSelecaoMultipa() {
        editor.setSelectionMultiple();
    }

    @Override
    public JCheckList controle() {
        return editor;
    }

    @Override
    public JScrollPane rolagem() {
        return scroll;
    }

    @Override
    public synchronized Vlr pgVlr(Object comPadrao) throws Exception {
        if (vazio()) {
            return Vlr.novo(comPadrao);
        } else if (editor.isSelectionSimple()) {
            return new VEsc(pegaSelecionado());
        } else {
            return new VEsc(pegaSelecionados());
        }
    }

    @Override
    public synchronized EdtEsc mdVlr(Object para) throws Exception {
        editor.unselectAll();
        if (para != null) {
            if (editor.isSelectionSimple()) {
                botaEscolha(para);
            } else {
                List<Object> vlrs = UtlLis.pega(para);
                if (vlrs != null) {
                    for (Object obj : vlrs) {
                        botaEscolha(obj);
                    }
                }
            }
        }
        return this;
    }

    @Override
    public EdtEsc botaAoModificar(AbstractAction aAcao) {

        return this;
    }

    @Override
    public EdtEsc auxiliar() throws Exception {

        return this;
    }

    @Override
    public EdtEsc aleatorio() {

        return this;
    }

    public void botaEscolha(Object oValor) {
        editor.select(oValor);
    }

    public void tiraEscolha(Object oValor) {
        editor.unselect(oValor);
    }

    @Override
    public Boolean vazio() {
        return editor.isEmpty();
    }

    @Override
    public EdtEsc mEditavel(Boolean para) {
        editor.setEnabled(para);
        return this;
    }

    @Override
    public Boolean editavel() {
        return editor.isEnabled();
    }

    public Integer tamanho() {
        int[] sels = pegaSelecionadosIndices();
        if (sels == null) {
            return 0;
        } else {
            return sels.length;
        }
    }

    public Object pegaSelecionado() {
        return editor.getSelectedValue();
    }

    public List pegaSelecionados() {
        return editor.getSelectedValuesList();
    }

    public Integer pegaSelecionadoIndice() {
        return editor.getSelectedIndex();
    }

    public int[] pegaSelecionadosIndices() {
        return editor.getSelectedIndices();
    }

    public void botaAoDuploClique(ActionListener aAcao) {
        editor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (e.getClickCount() >= 2) {
                        aAcao.actionPerformed(new ActionEvent(e.getSource(), e.getID(), "Duplo Clique"));
                    }
                }
            }
        });
    }

    public void botaAoSelecionar(ActionListener aAcao) {
        editor.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                aAcao.actionPerformed(new ActionEvent(e.getSource(), 1, "Ao Selecionar"));
            }
        });
        editor.clearSelection();
    }

    public EdtEsc procurar() {
        new FormulaCrs() {
            @Override
            public Boolean executa(FormulaCrs comFormula) {
                for (Object val : editor.getValues()) {
                    if (comFormula.verifica(val.toString())) {
                        botaEscolha(val);
                    }
                }
                return true;
            }
        }.mostra("Procurar na Escolha");
        return this;
    }

    private class ActProcurar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            procurar();
        }
    }

}
