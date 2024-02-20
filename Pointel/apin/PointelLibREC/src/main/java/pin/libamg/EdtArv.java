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
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import pin.libamk.Botao;
import pin.libbas.Dados;
import pin.libitr.QuestaoTp;
import pin.libitr.QuestaoTpVr;
import pin.libjan.TrataIntf;
import pin.libpic.Pics;
import pin.libutl.Utl;
import pin.libutl.UtlLis;
import pin.libvlr.VArv;
import pin.libvlr.Vlr;

public class EdtArv extends Edt {

    private JPanel jpnEditor;
    private JScrollPane rolagem;
    private JTree editor;
    private DefaultTreeModel dtmEditor;
    private Item itnRaiz;

    private AbstractAction aoAdicionar;
    private AbstractAction aoInserir;
    private AbstractAction aoEscalar;
    private AbstractAction aoNomear;
    private AbstractAction aoValorar;
    private AbstractAction aoRemover;

    private JPanel jpnBotoesLis;
    private final Boolean botHorizontal;
    private JButton jbtAdicionar;
    private JButton jbtInserir;
    private JButton jbtEscalar;
    private JButton jbtNomear;
    private JButton jbtValorar;
    private JButton jbtRemover;
    private JButton jbtSubir;
    private JButton jbtDescer;
    private JButton jbtAscender;
    private JButton jbtDescender;

    private Boolean estaEditavel;

    public EdtArv() {
        this(BorderLayout.EAST);
    }

    public EdtArv(String localBotoes) {
        super();
        jpnEditor = new JPanel(new BorderLayout(0, 0));
        itnRaiz = new Item();
        dtmEditor = new DefaultTreeModel(itnRaiz);
        editor = new JTree(dtmEditor);
        rolagem = new JScrollPane(editor);
        jpnEditor.add(rolagem, BorderLayout.CENTER);
        jpnBotoesLis = new JPanel();
        jbtAdicionar = new JButton();
        jbtInserir = new JButton();
        jbtEscalar = new JButton();
        jbtNomear = new JButton();
        jbtValorar = new JButton();
        jbtRemover = new JButton();
        jbtSubir = new JButton();
        jbtDescer = new JButton();
        jbtAscender = new JButton();
        jbtDescender = new JButton();
        if (UtlLis.tem(localBotoes, new String[]{BorderLayout.EAST, BorderLayout.WEST})) {
            jpnBotoesLis.setLayout(new BoxLayout(jpnBotoesLis, BoxLayout.PAGE_AXIS));
            botHorizontal = false;
        } else {
            jpnBotoesLis.setLayout(new BoxLayout(jpnBotoesLis, BoxLayout.LINE_AXIS));
            botHorizontal = true;
        }
        jpnBotoesLis.add(jbtAdicionar);
        jpnBotoesLis.add(jbtInserir);
        jpnBotoesLis.add(jbtEscalar);
        jpnBotoesLis.add(jbtNomear);
        jpnBotoesLis.add(jbtValorar);
        jpnBotoesLis.add(jbtRemover);
        jpnBotoesLis.add(jbtSubir);
        jpnBotoesLis.add(jbtDescer);
        jpnBotoesLis.add(jbtAscender);
        jpnBotoesLis.add(jbtDescender);
        jpnEditor.add(jpnBotoesLis, localBotoes);
        add(jpnEditor, BorderLayout.CENTER);
        inicia();
    }

    @Override
    public void atualizaOpcoes() {

    }

    @Override
    public void atualizaParams() {
    }

    @Override
    public void inicia() {
        aoAdicionar = null;
        aoInserir = null;
        aoEscalar = null;
        aoNomear = null;
        aoValorar = null;
        aoRemover = null;
        estaEditavel = true;
        editor.getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
        jbtAdicionar.setIcon(Pics.pega("add.png"));
        jbtAdicionar.setToolTipText("Adicionar   [alt INSERT]");
        TrataIntf.mDimensao(jbtAdicionar, 33, 33);
        jbtInserir.setIcon(Pics.pega("insert.png"));
        jbtInserir.setToolTipText("Inserir   [shift alt INSERT]");
        TrataIntf.mDimensao(jbtInserir, 33, 33);
        jbtEscalar.setIcon(Pics.pega("hierarchy.png"));
        jbtEscalar.setToolTipText("Escalar   [shift control alt INSERT]");
        TrataIntf.mDimensao(jbtEscalar, 33, 33);
        jbtNomear.setIcon(Pics.pega("pencil_name.png"));
        jbtNomear.setToolTipText("Nomear   [alt N]");
        TrataIntf.mDimensao(jbtNomear, 33, 33);
        jbtValorar.setIcon(Pics.pega("pencil_value.png"));
        jbtValorar.setToolTipText("Valorar   [alt V]");
        TrataIntf.mDimensao(jbtValorar, 33, 33);
        jbtRemover.setIcon(Pics.pega("delete.png"));
        jbtRemover.setToolTipText("Remover   [alt DELETE]");
        TrataIntf.mDimensao(jbtRemover, 33, 33);
        jbtSubir.setIcon(Pics.pega("arrow_up.png"));
        jbtSubir.setToolTipText("Subir   [alt UP]");
        TrataIntf.mDimensao(jbtSubir, 33, 33);
        jbtDescer.setIcon(Pics.pega("arrow_down.png"));
        jbtDescer.setToolTipText("Descer   [alt DOWN]");
        TrataIntf.mDimensao(jbtDescer, 33, 33);
        jbtAscender.setIcon(Pics.pega("arrow_left.png"));
        jbtAscender.setToolTipText("Ascender   [alt LEFT]");
        TrataIntf.mDimensao(jbtAscender, 33, 33);
        jbtDescender.setIcon(Pics.pega("arrow_right.png"));
        jbtDescender.setToolTipText("Descender   [alt RIGHT]");
        TrataIntf.mDimensao(jbtDescender, 33, 33);
        pPopMenu().bota("Adicionar", new ActAdicionar()).botaAcao(jbtAdicionar).botaAtalho(controle(), "alt INSERT");
        pPopMenu().bota("Inserir", new ActInserir()).botaAcao(jbtInserir).botaAtalho(controle(), "shift alt INSERT");
        pPopMenu().bota("Escalar", new ActEscalar()).botaAcao(jbtEscalar).botaAtalho(controle(), "shift control alt INSERT");
        pPopMenu().bota("Nomear", new ActNomear()).botaAcao(jbtNomear).botaAtalho(controle(), "alt N");
        pPopMenu().bota("Valorar", new ActValorar()).botaAcao(jbtValorar).botaAtalho(controle(), "alt V");
        pPopMenu().bota("Remover", new ActRemover()).botaAcao(jbtRemover).botaAtalho(controle(), "alt DELETE");
        pPopMenu().bota("Subir", new ActSubir()).botaAcao(jbtSubir).botaAtalho(controle(), "alt UP");
        pPopMenu().bota("Descer", new ActDescer()).botaAcao(jbtDescer).botaAtalho(controle(), "alt DOWN");
        pPopMenu().bota("Ascender", new ActAscender()).botaAcao(jbtAscender).botaAtalho(controle(), "alt LEFT");
        pPopMenu().bota("Descender", new ActDescender()).botaAcao(jbtDescender).botaAtalho(controle(), "alt RIGHT");
        jpnEditor.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        jpnEditor.setAlignmentY(JComponent.TOP_ALIGNMENT);
        TrataIntf.mDimensao(rolagem, 180, 180);
        editor.setDropTarget(new EvtTransferir());
        super.inicia();
    }

    @Override
    public JTree controle() {
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
            return new VArv(itnRaiz);
        }
    }

    @Override
    public synchronized EdtArv mdVlr(Object para) throws Exception {
        itnRaiz.removeAllChildren();
        itnRaiz.setUserObject(null);
        return this;
    }

    @Override
    public EdtArv botaAoModificar(AbstractAction aAcao) {

        return this;
    }

    @Override
    public EdtArv auxiliar() throws Exception {

        return this;
    }

    @Override
    public EdtArv aleatorio() {

        return this;
    }

    private Boolean estGrv = false;
    private List<TreePath> estExps = new ArrayList<>();
    private List<TreePath> estSels = new ArrayList<>();

    private void gravaEstado() {
        estExps.clear();
        for (int ir = 0; ir < editor.getRowCount(); ir++) {
            if (editor.isExpanded(ir)) {
                estExps.add(editor.getPathForRow(ir));
            }
        }
        estSels.clear();
        TreePath[] pths = editor.getSelectionPaths();
        if (pths != null) {
            for (TreePath sel : pths) {
                estSels.add(sel);
            }
        }
        estGrv = true;
    }

    public EdtArv atualiza() {
        if (!estGrv) {
            gravaEstado();
        }
        dtmEditor.reload();
        for (TreePath rw : estExps) {
            try {
                editor.expandPath(rw);
            } catch (Exception ex) {
            }
        }
        List<Integer> rows = new ArrayList<>();
        for (TreePath sl : estSels) {
            int idx = editor.getRowForPath(sl);
            if (idx > -1) {
                rows.add(idx);
            }
        }
        if (!rows.isEmpty()) {
            int[] selrows = new int[rows.size()];
            for (int ir = 0; ir < rows.size(); ir++) {
                selrows[ir] = rows.get(ir);
            }
            try {
                editor.setSelectionRows(selrows);
            } catch (Exception ex) {
            }
        }
        return this;
    }

    public Boolean temSelecionado() {
        return editor.getSelectionCount() > 0;
    }

    public TreePath pSelecionado() {
        return editor.getSelectionPath();
    }

    public TreePath[] pSelecionados() {
        return editor.getSelectionPaths();
    }

    public Item pItem() {
        TreePath pth = editor.getSelectionPath();
        if (pth == null) {
            return null;
        }
        return (Item) pth.getLastPathComponent();
    }

    public Item[] pItens() {
        TreePath[] pths = editor.getSelectionPaths();
        if (pths == null) {
            return null;
        }
        Item[] retorno = new Item[pths.length];
        for (int ip = 0; ip < pths.length; ip++) {
            retorno[ip] = (Item) pths[ip].getLastPathComponent();
        }
        return retorno;
    }

    public EdtArv adicionaItem(String comNome) {
        return adicionaItem(comNome, null);
    }

    public EdtArv adicionaItem(String comNome, Object eValor) {
        return adicionaItem(new Item(comNome, eValor));
    }

    public EdtArv adicionaItem(Item oItem) {
        gravaEstado();
        TreePath pth = editor.getSelectionPath();
        Item pai = itnRaiz;
        if (pth != null) {
            if (pth.getPathCount() > 1) {
                pai = (Item) pth.getPathComponent(pth.getPathCount() - 2);
            }
        }
        pai.insert(oItem, 0);
        return atualiza();
    }

    public EdtArv insereItem(String comNome) {
        return insereItem(comNome, null);
    }

    public EdtArv insereItem(String comNome, Object eValor) {
        return insereItem(new Item(comNome, eValor));
    }

    public EdtArv insereItem(Item oItem) {
        gravaEstado();
        TreePath pth = editor.getSelectionPath();
        Item pai = itnRaiz;
        int idx = 0;
        if (pth != null) {
            if (pth.getPathCount() > 1) {
                pai = (Item) pth.getPathComponent(pth.getPathCount() - 2);
            }
            idx = pai.getIndex((TreeNode) pth.getLastPathComponent());
            if (idx < 0) {
                idx = 0;
            }
        }
        pai.insert(oItem, idx);
        return atualiza();
    }

    public EdtArv botaItem(String comNome) {
        return botaItem(comNome, null);
    }

    public EdtArv botaItem(String comNome, Object eValor) {
        return botaItem(new Item(comNome, eValor));
    }

    public EdtArv botaItem(Item oItem) {
        gravaEstado();
        TreePath pth = editor.getSelectionPath();
        Item pai = itnRaiz;
        if (pth != null) {
            if (pth.getPathCount() > 1) {
                pai = (Item) pth.getPathComponent(pth.getPathCount() - 2);
            }
        }
        pai.add(oItem);
        return atualiza();
    }

    public EdtArv botaFilho(String comNome) {
        return botaFilho(comNome, null);
    }

    public EdtArv botaFilho(String comNome, Object eValor) {
        return botaFilho(comNome, eValor);
    }

    public EdtArv botaFilho(Item oFilho) {
        gravaEstado();
        TreePath pth = editor.getSelectionPath();
        Item pai = itnRaiz;
        if (pth != null) {
            if (pth.getPathCount() > 0) {
                pai = (Item) pth.getPathComponent(pth.getPathCount() - 1);
            }
        }
        pai.add(oFilho);
        return atualiza();
    }

    public EdtArv mNomeItem(String para) {
        gravaEstado();
        Item itn = (Item) editor.getLastSelectedPathComponent();
        if (itn != null) {
            itn.mNome(para);
        }
        return atualiza();
    }

    public EdtArv mValorItem(Object para) {
        gravaEstado();
        Item itn = (Item) editor.getLastSelectedPathComponent();
        if (itn != null) {
            itn.mValor(para);
        }
        return atualiza();
    }

    public EdtArv tiraItem() {
        gravaEstado();
        TreePath pth = editor.getSelectionPath();
        if (pth != null) {
            if (pth.getPathCount() > 0) {
                dtmEditor.removeNodeFromParent((MutableTreeNode) pth.getPathComponent(pth.getPathCount() - 1));
            }
        }
        return atualiza();
    }

    public EdtArv tiraItens() {
        gravaEstado();
        TreePath[] pths = editor.getSelectionPaths();
        if (pths != null) {
            for (TreePath pth : pths) {
                if (pth.getPathCount() > 0) {
                    dtmEditor.removeNodeFromParent((MutableTreeNode) pth.getPathComponent(pth.getPathCount() - 1));
                }
            }
        }
        return atualiza();
    }

    public EdtArv sobeItem() {
        gravaEstado();
        TreePath pth = editor.getSelectionPath();
        if (pth != null) {
            if (pth.getPathCount() > 1) {
                MutableTreeNode itn = (MutableTreeNode) pth.getPathComponent(pth.getPathCount() - 1);
                MutableTreeNode pai = (MutableTreeNode) itn.getParent();
                int idx = pai.getIndex(itn);
                if (idx > 0) {
                    dtmEditor.removeNodeFromParent(itn);
                    dtmEditor.insertNodeInto(itn, pai, idx - 1);
                }
            }
        }
        return atualiza();
    }

    public EdtArv desceItem() {
        gravaEstado();
        TreePath pth = editor.getSelectionPath();
        if (pth != null) {
            if (pth.getPathCount() > 1) {
                MutableTreeNode itn = (MutableTreeNode) pth.getPathComponent(pth.getPathCount() - 1);
                MutableTreeNode pai = (MutableTreeNode) itn.getParent();
                int idx = pai.getIndex(itn);
                if (idx < pai.getChildCount() - 1) {
                    dtmEditor.removeNodeFromParent(itn);
                    dtmEditor.insertNodeInto(itn, pai, idx + 1);
                }
            }
        }
        return atualiza();
    }

    public EdtArv ascendeItem() {
        gravaEstado();
        TreePath pth = editor.getSelectionPath();
        if (pth != null) {
            if (pth.getPathCount() > 2) {
                MutableTreeNode itn = (MutableTreeNode) pth.getPathComponent(pth.getPathCount() - 1);
                MutableTreeNode pai = (MutableTreeNode) itn.getParent();
                MutableTreeNode ppai = (MutableTreeNode) pai.getParent();
                int nidx = ppai.getIndex(pai) + 1;
                dtmEditor.removeNodeFromParent(itn);
                dtmEditor.insertNodeInto(itn, ppai, nidx);
            }
        }
        return atualiza();
    }

    public EdtArv descendeItem() {
        gravaEstado();
        TreePath pth = editor.getSelectionPath();
        if (pth != null) {
            if (pth.getPathCount() > 1) {
                MutableTreeNode itn = (MutableTreeNode) pth.getPathComponent(pth.getPathCount() - 1);
                MutableTreeNode pai = (MutableTreeNode) itn.getParent();
                int idx = pai.getIndex(itn);
                if (idx > 0) {
                    MutableTreeNode npai = (MutableTreeNode) pai.getChildAt(idx - 1);
                    int nidx = npai.getChildCount();
                    dtmEditor.removeNodeFromParent(itn);
                    dtmEditor.insertNodeInto(itn, npai, nidx);
                }
            }
        }
        return atualiza();
    }

    public Boolean contem(TreeNode oValor) {
        return itnRaiz.getIndex(itnRaiz) > -1;
    }

    @Override
    public Boolean vazio() {
        return tamanho() <= 0;
    }

    @Override
    public EdtArv mEditavel(Boolean para) {
        estaEditavel = para;
        return this;
    }

    @Override
    public Boolean editavel() {
        return estaEditavel;
    }

    public Integer tamanho() {
        return dtmEditor.getChildCount(itnRaiz);
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
            jbtInserir.setIcon(comIcone);
        }
        if (eDica != null) {
            jbtInserir.setToolTipText(eDica);
        }
        aoInserir = aAcao;
    }

    public AbstractAction pAoInserir() {
        return aoInserir;
    }

    public void mAoEscalar(AbstractAction aAcao) {
        mAoEscalar(null, null, aAcao);
    }

    public void mAoEscalar(Icon comIcone, AbstractAction aAcao) {
        mAoEscalar(comIcone, null, aAcao);
    }

    public void mAoEscalar(Icon comIcone, String eDica, AbstractAction aAcao) {
        if (comIcone != null) {
            jbtEscalar.setIcon(comIcone);
        }
        if (eDica != null) {
            jbtEscalar.setToolTipText(eDica);
        }
        aoEscalar = aAcao;
    }

    public AbstractAction pAoEscalar() {
        return aoEscalar;
    }

    public void mAoNomear(AbstractAction aAcao) {
        mAoNomear(null, null, aAcao);
    }

    public void mAoNomear(Icon comIcone, AbstractAction aAcao) {
        mAoNomear(comIcone, null, aAcao);
    }

    public void mAoNomear(Icon comIcone, String eDica, AbstractAction aAcao) {
        if (comIcone != null) {
            jbtNomear.setIcon(comIcone);
        }
        if (eDica != null) {
            jbtNomear.setToolTipText(eDica);
        }
        aoNomear = aAcao;
    }

    public AbstractAction pAoNomear() {
        return aoNomear;
    }

    public void mAoValorar(AbstractAction aAcao) {
        mAoValorar(null, null, aAcao);
    }

    public void mAoValorar(Icon comIcone, AbstractAction aAcao) {
        mAoValorar(comIcone, null, aAcao);
    }

    public void mAoValorar(Icon comIcone, String eDica, AbstractAction aAcao) {
        if (comIcone != null) {
            jbtNomear.setIcon(comIcone);
        }
        if (eDica != null) {
            jbtNomear.setToolTipText(eDica);
        }
        aoValorar = aAcao;
    }

    public AbstractAction pAoValorar() {
        return aoValorar;
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

    public EdtArv botaAoDuploClique(AbstractAction aAcao) {
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

    public EdtArv botaAoSelecionar(AbstractAction aAcao) {
        editor.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                aAcao.actionPerformed(null);
            }
        });
        return this;
    }

    public EdtArv tiraAdicionar() {
        jpnBotoesLis.remove(jbtAdicionar);
        return atualizaTamanho();
    }

    public EdtArv tiraInserir() {
        jpnBotoesLis.remove(jbtInserir);
        return atualizaTamanho();
    }

    public EdtArv tiraEscalar() {
        jpnBotoesLis.remove(jbtEscalar);
        return atualizaTamanho();
    }

    public EdtArv tiraNomear() {
        jpnBotoesLis.remove(jbtNomear);
        return atualizaTamanho();
    }

    public EdtArv tiraValoara() {
        jpnBotoesLis.remove(jbtValorar);
        return atualizaTamanho();
    }

    public EdtArv tiraRemover() {
        jpnBotoesLis.remove(jbtRemover);
        return atualizaTamanho();
    }

    public EdtArv tiraSubir() {
        jpnBotoesLis.remove(jbtSubir);
        return atualizaTamanho();
    }

    public EdtArv tiraDescer() {
        jpnBotoesLis.remove(jbtDescer);
        return atualizaTamanho();
    }

    public EdtArv botaBotaoLis(Botao oBotao) {
        return botaBotaoLis(oBotao, this);
    }

    public EdtArv botaBotaoLis(Botao oBotao, Object comOrigem) {
        JButton jbt = oBotao.cria(comOrigem);
        TrataIntf.mDimensao(jbt, 33, 33);
        jpnBotoesLis.add(jbt);
        return atualizaTamanho();
    }

    public EdtArv tiraBotaoLis(Botao oBotao) {
        jpnBotoesLis.remove(oBotao.pControle());
        return atualizaTamanho();
    }

    public EdtArv atualizaTamanho() {
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
                    new QuestaoTp(Dados.Tp.Crs) {
                        @Override
                        public Boolean aoExecutar(Object comRetorno) {
                            if (estaEditavel) {
                                botaItem(comRetorno.toString());
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

    private class ActInserir extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (aoInserir != null) {
                aoInserir.actionPerformed(e);
            } else {
                if (estaEditavel) {
                    new QuestaoTp(Dados.Tp.Crs) {
                        @Override
                        public Boolean aoExecutar(Object comRetorno) {
                            if (estaEditavel) {
                                insereItem(comRetorno.toString());
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

    private class ActEscalar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (aoEscalar != null) {
                aoEscalar.actionPerformed(e);
            } else {
                if (estaEditavel) {
                    new QuestaoTp(Dados.Tp.Crs) {
                        @Override
                        public Boolean aoExecutar(Object comRetorno) {
                            if (estaEditavel) {
                                botaFilho(comRetorno.toString());
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

    private class ActNomear extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (aoNomear != null) {
                aoNomear.actionPerformed(e);
            } else {
                if (estaEditavel) {
                    Item sel = pItem();
                    if (sel != null) {
                        new QuestaoTp(Dados.Tp.Crs, "Nomear Item", sel.pNome()) {
                            @Override
                            public Boolean aoExecutar(Object comRetorno) {
                                if (estaEditavel) {
                                    sel.mNome(comRetorno.toString());
                                    atualiza();
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

    private class ActValorar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (aoValorar != null) {
                aoValorar.actionPerformed(e);
            } else {
                if (estaEditavel) {
                    Item sel = pItem();
                    if (sel != null) {
                        new QuestaoTpVr("Valorar Item", sel.pValor()) {
                            @Override
                            public Boolean aoExecutar(Object comRetorno) {
                                if (estaEditavel) {
                                    sel.mValor(comRetorno);
                                    atualiza();
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
            if (estaEditavel) {
                sobeItem();
            }

        }
    }

    private class ActDescer extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (estaEditavel) {
                desceItem();
            }

        }
    }

    private class ActAscender extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (estaEditavel) {
                ascendeItem();
            }

        }
    }

    private class ActDescender extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (estaEditavel) {
                descendeItem();
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
                            File fl = (File) transferData.get(ia);
                            botaItem(fl.getName(), fl);
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

    public static class Item extends DefaultMutableTreeNode {

        private String nome;
        private Object valor;

        public Item() {
            this(null, null);
        }

        public Item(String nome) {
            this(nome, null);
        }

        public Item(String nome, Object valor) {
            this.nome = nome;
            this.valor = valor;
        }

        public String pNome() {
            return nome;
        }

        public Item mNome(String nome) {
            this.nome = nome;
            return this;
        }

        public Object pValor() {
            return valor;
        }

        public Item mValor(Object valor) {
            this.valor = valor;
            return this;
        }

        @Override
        public String toString() {
            return nome;
        }

    }

}
