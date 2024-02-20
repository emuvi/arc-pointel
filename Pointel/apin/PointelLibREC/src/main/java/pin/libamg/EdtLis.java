package pin.libamg;

import java.awt.BorderLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import pin.libamk.Botao;
import pin.libbas.Dados;
import pin.libitr.QuestaoTp;
import pin.libitr.QuestaoTpVr;
import pin.libjan.TrataIntf;
import pin.libpic.Pics;
import pin.libutl.Utl;
import pin.libutl.UtlCrs;
import pin.libutl.UtlLis;
import pin.libvlr.VLis;
import pin.libvlr.Vlr;

public class EdtLis extends Edt {

    private JPanel jpnEditor;
    private JScrollPane rolagem;
    private JList editor;
    private DefaultListModel dlmEditor;

    private AbstractAction aoAdicionar;
    private AbstractAction aoInserir;
    private AbstractAction aoAlterar;
    private AbstractAction aoRemover;

    private JPanel jpnBotoesLis;
    private final Boolean botHorizontal;
    private JButton jbtAdicionar;
    private JButton jbtInserir;
    private JButton jbtAlterar;
    private JButton jbtRemover;
    private JButton jbtSubir;
    private JButton jbtDescer;

    private Boolean estaEditavel;

    public EdtLis() {
        this(BorderLayout.EAST);
    }

    public EdtLis(String localBotoes) {
        super();
        jpnEditor = new JPanel(new BorderLayout(0, 0));
        dlmEditor = new DefaultListModel();
        editor = new JList(dlmEditor);
        rolagem = new JScrollPane(editor);
        jpnEditor.add(rolagem, BorderLayout.CENTER);
        jpnBotoesLis = new JPanel();
        jbtAdicionar = new JButton();
        jbtInserir = new JButton();
        jbtAlterar = new JButton();
        jbtRemover = new JButton();
        jbtSubir = new JButton();
        jbtDescer = new JButton();
        if (UtlLis.tem(localBotoes, new String[]{BorderLayout.EAST, BorderLayout.WEST})) {
            jpnBotoesLis.setLayout(new BoxLayout(jpnBotoesLis, BoxLayout.PAGE_AXIS));
            botHorizontal = false;
        } else {
            jpnBotoesLis.setLayout(new BoxLayout(jpnBotoesLis, BoxLayout.LINE_AXIS));
            botHorizontal = true;
        }
        jpnBotoesLis.add(jbtAdicionar);
        jpnBotoesLis.add(jbtInserir);
        jpnBotoesLis.add(jbtAlterar);
        jpnBotoesLis.add(jbtRemover);
        jpnBotoesLis.add(jbtSubir);
        jpnBotoesLis.add(jbtDescer);
        jpnEditor.add(jpnBotoesLis, localBotoes);
        add(jpnEditor, BorderLayout.CENTER);
        inicia();
    }

    @Override
    public void atualizaOpcoes() {
        dlmEditor.removeAllElements();
        if (opcoes() != null) {
            for (Object opc : opcoes()) {
                dlmEditor.addElement(opc);
            }
        }
    }

    @Override
    public void atualizaParams() {
    }

    @Override
    public void inicia() {
        aoAdicionar = null;
        aoInserir = null;
        aoAlterar = null;
        aoRemover = null;
        estaEditavel = true;
        jbtAdicionar.setIcon(Pics.pega("add.png"));
        jbtAdicionar.setToolTipText("Adicionar   [alt INSERT]");
        TrataIntf.mDimensao(jbtAdicionar, 33, 33);
        jbtInserir.setIcon(Pics.pega("insert.png"));
        jbtInserir.setToolTipText("Inserir   [shift alt INSERT]");
        TrataIntf.mDimensao(jbtInserir, 33, 33);
        jbtAlterar.setIcon(Pics.pega("pencil.png"));
        jbtAlterar.setToolTipText("Alterar   [alt A]");
        TrataIntf.mDimensao(jbtAlterar, 33, 33);
        jbtRemover.setIcon(Pics.pega("delete.png"));
        jbtRemover.setToolTipText("Remover   [alt DELETE]");
        TrataIntf.mDimensao(jbtRemover, 33, 33);
        jbtSubir.setIcon(Pics.pega("arrow_up.png"));
        jbtSubir.setToolTipText("Subir   [alt UP]");
        TrataIntf.mDimensao(jbtSubir, 33, 33);
        jbtDescer.setIcon(Pics.pega("arrow_down.png"));
        jbtDescer.setToolTipText("Descer   [alt DOWN]");
        TrataIntf.mDimensao(jbtDescer, 33, 33);
        pPopMenu().bota("Adicionar", new ActAdicionar()).botaAcao(jbtAdicionar).botaAtalho(controle(), "alt INSERT");
        pPopMenu().bota("Inserir", new ActInserir()).botaAcao(jbtInserir).botaAtalho(controle(), "shift alt INSERT");
        pPopMenu().bota("Alterar", new ActAlterar()).botaAcao(jbtAlterar).botaAtalho(controle(), "alt A");
        pPopMenu().bota("Remover", new ActRemover()).botaAcao(jbtRemover).botaAtalho(controle(), "alt DELETE");
        pPopMenu().bota("Subir", new ActSubir()).botaAcao(jbtSubir).botaAtalho(controle(), "alt UP");
        pPopMenu().bota("Descer", new ActDescer()).botaAcao(jbtDescer).botaAtalho(controle(), "alt DOWN");
        jpnEditor.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        jpnEditor.setAlignmentY(JComponent.TOP_ALIGNMENT);
        TrataIntf.mDimensao(rolagem, 180, 180);
        editor.setDropTarget(new EvtTransferir());
        super.inicia();
    }

    @Override
    public JList controle() {
        return editor;
    }

    @Override
    public JScrollPane rolagem() {
        return rolagem;
    }

    @Override
    public synchronized Vlr pgVlr(Object comPadrao) throws Exception {
        if (vazio()) {
            return Vlr.novo(comPadrao);
        } else {
            return new VLis(dlmEditor.toArray());
        }
    }

    @Override
    public synchronized EdtLis mdVlr(Object para) throws Exception {
        List<Object> lst = UtlLis.pega(para);
        dlmEditor.clear();
        Dados.Tp tipo = null;
        if (temParam(Dados.Tp.class)) {
            tipo = pegaParam(Dados.Tp.class);
        }
        if (lst != null) {
            for (Object obj : lst) {
                if (tipo != null) {
                    dlmEditor.addElement(Dados.pegaVlr(tipo, obj));
                } else {
                    dlmEditor.addElement(obj);
                }
            }
        }
        return this;
    }

    @Override
    public EdtLis botaAoModificar(AbstractAction aAcao) {

        return this;
    }

    @Override
    public EdtLis auxiliar() throws Exception {

        return this;
    }

    @Override
    public EdtLis aleatorio() {

        return this;
    }

    public Boolean temSelecionado() {
        return editor.getSelectedIndex() > -1;
    }

    public int pSelecionado() {
        return editor.getSelectedIndex();
    }

    public int[] pSelecionados() {
        return editor.getSelectedIndices();
    }

    public Object pItem() {
        return pItem(pSelecionado());
    }

    public Object pItem(int doIndice) {
        return dlmEditor.get(doIndice);
    }

    public List<Object> pItens() {
        return pItens(pSelecionados());
    }

    public List<Object> pItens(int[] dosIndices) {
        List<Object> retorno = new ArrayList<>();
        int[] sels = dosIndices;
        for (int sel : sels) {
            retorno.add(pItem(sel));
        }
        return retorno;
    }

    public EdtLis adicionaItem(Object oItem) {
        dlmEditor.add(0, oItem);
        editor.setSelectedIndex(0);
        TrataIntf.posicionaNaSelecao(editor, rolagem);
        return this;
    }

    public EdtLis insereItem(Object oItem) {
        int nidx = editor.getSelectedIndex();
        if (nidx == -1) {
            nidx = 0;
        }
        dlmEditor.add(nidx, oItem);
        editor.setSelectedIndex(nidx);
        TrataIntf.posicionaNaSelecao(editor, rolagem);
        return this;
    }

    public EdtLis botaItem(Object oItem) {
        dlmEditor.addElement(oItem);
        editor.setSelectedIndex(dlmEditor.getSize() - 1);
        TrataIntf.posicionaNaSelecao(editor, rolagem);
        return this;
    }

    public EdtLis mItem(Object para) {
        dlmEditor.setElementAt(para, pSelecionado());
        TrataIntf.posicionaNaSelecao(editor, rolagem);
        return this;
    }

    public EdtLis mItem(Object para, Integer doIndice) {
        dlmEditor.setElementAt(para, doIndice);
        editor.setSelectedIndex(doIndice);
        TrataIntf.posicionaNaSelecao(editor, rolagem);
        return this;
    }

    public EdtLis tiraItem() {
        int sel = pSelecionado();
        dlmEditor.removeElementAt(sel);
        sel--;
        editor.setSelectedIndex(sel);
        TrataIntf.posicionaNaSelecao(editor, rolagem);
        return this;
    }

    public EdtLis tiraItem(Object oItem) {
        return tiraItem(dlmEditor.indexOf(oItem));
    }

    public EdtLis tiraItem(int doIndice) {
        dlmEditor.removeElementAt(doIndice);
        doIndice--;
        editor.setSelectedIndex(doIndice);
        TrataIntf.posicionaNaSelecao(editor, rolagem);
        return this;
    }

    public EdtLis tiraItens() {
        tiraItens(pSelecionados());
        return this;
    }

    public EdtLis tiraItens(int[] dosIndices) {
        int[] sels = dosIndices;
        if (sels != null) {
            Integer ultInd = null;
            for (int is = sels.length - 1; is >= 0; is--) {
                dlmEditor.remove(sels[is]);
                ultInd = sels[is];
            }
            if (ultInd != null) {
                if (ultInd > dlmEditor.getSize() - 1) {
                    ultInd--;
                }
                editor.setSelectedIndex(ultInd);
                TrataIntf.posicionaNaSelecao(editor, rolagem);
            }
        }
        return this;
    }

    public EdtLis tiraItens(List<Object> osItens) {
        int[] sels = new int[osItens.size()];
        for (int is = 0; is < sels.length; is++) {
            sels[is] = dlmEditor.indexOf(osItens.get(is));
        }
        if (sels != null) {
            Integer ultInd = null;
            for (int is = sels.length - 1; is >= 0; is--) {
                dlmEditor.remove(sels[is]);
                ultInd = sels[is];
            }
            if (ultInd != null) {
                if (ultInd > dlmEditor.getSize() - 1) {
                    ultInd--;
                }
                editor.setSelectedIndex(ultInd);
                TrataIntf.posicionaNaSelecao(editor, rolagem);
            }
        }
        return this;
    }

    public Boolean contem(Object oValor) {
        return dlmEditor.contains(oValor);
    }

    public String[] pgMatCrs() {
        String[] retorno = new String[dlmEditor.getSize()];
        for (int i = 0; i < dlmEditor.getSize(); i++) {
            retorno[i] = UtlCrs.pega(dlmEditor.getElementAt(i));
        }
        return retorno;
    }

    @Override
    public Boolean vazio() {
        return dlmEditor.isEmpty();
    }

    @Override
    public EdtLis mEditavel(Boolean para) {
        estaEditavel = para;
        return this;
    }

    @Override
    public Boolean editavel() {
        return estaEditavel;
    }

    public Integer tamanho() {
        return dlmEditor.getSize();
    }

    public void mAoAdicionar(AbstractAction aAcao) {
        mAoAdicionar(null, null, aAcao);
    }

    public void mAoAdicionar(Icon comIcone, AbstractAction aAcao) {
        mAoAdicionar(comIcone, null, aAcao);
    }

    public void mAoAdicionar(Icon comIcone, String eDica, AbstractAction aAcao) {
        if (comIcone != null) {
            jbtAdicionar.setIcon(comIcone);
        }
        if (eDica != null) {
            jbtAdicionar.setToolTipText(eDica);
        }
        aoAdicionar = aAcao;
    }

    public AbstractAction pAoAdicionar() {
        return aoAdicionar;
    }

    public void mAoInserir(AbstractAction aAcao) {
        mAoInserir(null, null, aAcao);
    }

    public void mAoInserir(Icon comIcone, AbstractAction aAcao) {
        mAoInserir(comIcone, null, aAcao);
    }

    public void mAoInserir(Icon comIcone, String eDica, AbstractAction aAcao) {
        if (comIcone != null) {
            jbtAdicionar.setIcon(comIcone);
        }
        if (eDica != null) {
            jbtAdicionar.setToolTipText(eDica);
        }
        aoInserir = aAcao;
    }

    public AbstractAction pAoInserir() {
        return aoInserir;
    }

    public void mAoAlterar(AbstractAction aAcao) {
        mAoAlterar(null, null, aAcao);
    }

    public void mAoAlterar(Icon comIcone, AbstractAction aAcao) {
        mAoAlterar(comIcone, null, aAcao);
    }

    public void mAoAlterar(Icon comIcone, String eDica, AbstractAction aAcao) {
        if (comIcone != null) {
            jbtAlterar.setIcon(comIcone);
        }
        if (eDica != null) {
            jbtAlterar.setToolTipText(eDica);
        }
        aoAlterar = aAcao;
    }

    public AbstractAction pAoAlterar() {
        return aoAlterar;
    }

    public void mAoRemover(AbstractAction aAcao) {
        mAoRemover(null, null, aAcao);
    }

    public void mAoRemover(Icon comIcone, AbstractAction aAcao) {
        mAoRemover(comIcone, null, aAcao);
    }

    public void mAoRemover(Icon comIcone, String eDica, AbstractAction aAcao) {
        if (comIcone != null) {
            jbtRemover.setIcon(comIcone);
        }
        if (eDica != null) {
            jbtRemover.setToolTipText(eDica);
        }
        aoRemover = aAcao;
    }

    public AbstractAction pAoRemover() {
        return aoRemover;
    }

    public EdtLis botaAoDuploClique(AbstractAction aAcao) {
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
        return this;
    }

    public EdtLis botaAoSelecionar(AbstractAction aAcao) {
        editor.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                aAcao.actionPerformed(null);
            }
        });
        return this;
    }

    public EdtLis tiraAdicionar() {
        jpnBotoesLis.remove(jbtAdicionar);
        return atualizaTamanho();
    }

    public EdtLis tiraInserir() {
        jpnBotoesLis.remove(jbtInserir);
        return atualizaTamanho();
    }

    public EdtLis tiraAlterar() {
        jpnBotoesLis.remove(jbtAlterar);
        return atualizaTamanho();
    }

    public EdtLis tiraRemover() {
        jpnBotoesLis.remove(jbtRemover);
        return atualizaTamanho();
    }

    public EdtLis tiraSubir() {
        jpnBotoesLis.remove(jbtSubir);
        return atualizaTamanho();
    }

    public EdtLis tiraDescer() {
        jpnBotoesLis.remove(jbtDescer);
        return atualizaTamanho();
    }

    public EdtLis botaBotaoLis(Botao oBotao) {
        return botaBotaoLis(oBotao, this);
    }

    public EdtLis botaBotaoLis(Botao oBotao, Object comOrigem) {
        JButton jbt = oBotao.cria(comOrigem);
        TrataIntf.mDimensao(jbt, 33, 33);
        jpnBotoesLis.add(jbt);
        return atualizaTamanho();
    }

    public EdtLis tiraBotaoLis(Botao oBotao) {
        jpnBotoesLis.remove(oBotao.pControle());
        return atualizaTamanho();
    }

    public EdtLis atualizaTamanho() {
        if (!ehDimensionavel()) {
            int alt = rolagem.getMinimumSize().height;
            int lar = rolagem.getMinimumSize().width;
            if (botHorizontal) {
                if (jpnBotoesLis.getPreferredSize().width > lar) {
                    TrataIntf.mDimensao(rolagem, jpnBotoesLis.getPreferredSize().width, alt);
                }
            } else {
                if (jpnBotoesLis.getPreferredSize().height > alt) {
                    TrataIntf.mDimensao(rolagem, lar, jpnBotoesLis.getPreferredSize().height);
                }
            }

        }
        return this;
    }

    private class ActAdicionar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (aoAdicionar != null) {
                aoAdicionar.actionPerformed(e);
            } else {
                if (estaEditavel) {
                    if (temParam(Dados.Tp.class)) {
                        Dados.Tp tipo = pegaParam(Dados.Tp.class);
                        new QuestaoTp(tipo) {
                            @Override
                            public Boolean aoExecutar(Object comRetorno) {
                                if (estaEditavel) {
                                    botaItem(comRetorno);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        }.mostra();
                    } else {
                        new QuestaoTpVr() {
                            @Override
                            public Boolean aoExecutar(Object comRetorno) {
                                if (estaEditavel) {
                                    botaItem(comRetorno);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        }.mostra();
                    }
                }
            }
        }
    }

    private class ActInserir extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (aoInserir != null) {
                aoInserir.actionPerformed(e);
            } else {
                if (estaEditavel) {
                    if (temParam(Dados.Tp.class)) {
                        Dados.Tp tipo = pegaParam(Dados.Tp.class);
                        new QuestaoTp(tipo) {
                            @Override
                            public Boolean aoExecutar(Object comRetorno) {
                                if (estaEditavel) {
                                    insereItem(comRetorno);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        }.mostra();
                    } else {
                        new QuestaoTpVr() {
                            @Override
                            public Boolean aoExecutar(Object comRetorno) {
                                if (estaEditavel) {
                                    insereItem(comRetorno);
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        }.mostra();
                    }
                }
            }
        }
    }

    private class ActAlterar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (aoAlterar != null) {
                aoAlterar.actionPerformed(e);
            } else {
                if (estaEditavel) {
                    int sel = editor.getSelectedIndex();
                    if (sel > -1) {
                        Object val = editor.getSelectedValue();
                        if (temParam(Dados.Tp.class)) {
                            Dados.Tp tipo = pegaParam(Dados.Tp.class);
                            new QuestaoTp(tipo, null, val) {
                                @Override
                                public Boolean aoExecutar(Object comRetorno) {
                                    if (estaEditavel) {
                                        mItem(comRetorno, sel);
                                        return true;
                                    } else {
                                        return false;
                                    }
                                }
                            }.mostra();
                        } else {
                            new QuestaoTpVr(null, val) {
                                @Override
                                public Boolean aoExecutar(Object comRetorno) {
                                    if (estaEditavel) {
                                        mItem(comRetorno, sel);
                                        return true;
                                    } else {
                                        return false;
                                    }
                                }
                            }.mostra();
                        }
                    }
                }
            }
        }
    }

    private class ActRemover extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (aoRemover != null) {
                aoRemover.actionPerformed(e);
            } else {
                if (estaEditavel) {
                    tiraItens();
                }
            }
        }
    }

    private class ActSubir extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!estaEditavel) {
                return;
            }
            int sel = editor.getSelectedIndex();
            if (sel > 0) {
                Object ax = dlmEditor.get(sel - 1);
                dlmEditor.set(sel - 1, dlmEditor.get(sel));
                dlmEditor.set(sel, ax);
                editor.setSelectedIndex(sel - 1);
                TrataIntf.posicionaNaSelecao(editor, rolagem);
            }
        }
    }

    private class ActDescer extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!estaEditavel) {
                return;
            }
            int sel = editor.getSelectedIndex();
            if (sel > -1) {
                if (sel < dlmEditor.getSize() - 1) {
                    Object ax = dlmEditor.get(sel + 1);
                    dlmEditor.set(sel + 1, dlmEditor.get(sel));
                    dlmEditor.set(sel, ax);
                    editor.setSelectedIndex(sel + 1);
                    TrataIntf.posicionaNaSelecao(editor, rolagem);
                }
            }
        }
    }

    private class EvtTransferir extends DropTarget {

        protected void processDrag(DropTargetDragEvent dtde) {
            if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                dtde.acceptDrag(DnDConstants.ACTION_COPY);
            } else {
                dtde.rejectDrag();
            }
        }

        @Override
        public void dragEnter(DropTargetDragEvent dtde) {
            processDrag(dtde);
        }

        @Override
        public void dragOver(DropTargetDragEvent dtde) {
            processDrag(dtde);
        }

        @Override
        public void drop(DropTargetDropEvent dtde) {
            Transferable transferable = dtde.getTransferable();
            if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                dtde.acceptDrop(dtde.getDropAction());
                try {
                    List transferData = (List) transferable.getTransferData(DataFlavor.javaFileListFlavor);
                    if (transferData != null && transferData.size() > 0) {
                        for (int ia = 0; ia < transferData.size(); ia++) {
                            botaItem(transferData.get(ia));
                        }
                        dtde.dropComplete(true);
                    }
                } catch (Exception ex) {
                    Utl.registra(ex, false);
                }
            } else {
                dtde.rejectDrop();
            }
        }

    }

}
