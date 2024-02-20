package pin.libamg;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import pin.libamk.Botao;
import pin.libamk.RoteirosIntf;
import pin.libbas.Retornos;
import pin.libbas.Roteiro;
import pin.libjan.Janelas;
import pin.libjan.PaiTabela;
import pin.libjan.PopMenu;
import pin.libpic.Pics;
import pin.libutl.Utl;
import pin.libvlr.VBot;
import pin.libvlr.Vlr;

public class EdtBot extends Edt {

    private final JButton editor;
    private final Botao botao;
    private PopMenu menu;
    private Integer prioridade;
    private String codigo;
    private Object origem;
    private Retornos retornos;
    private Object retorno;
    private AbstractAction aoRetornar;
    private AbstractAction aoConfirmar;

    public EdtBot() {
        this("Botão", null, null);
    }

    public EdtBot(String comTitulo) {
        this(comTitulo, null, null);
    }

    public EdtBot(ImageIcon comIcone) {
        this(null, comIcone, null);
    }

    public EdtBot(AbstractAction comAcao) {
        this(null, Pics.pegaProcessar(), comAcao);
    }

    public EdtBot(String comTitulo, AbstractAction eAcao) {
        this(comTitulo, null, eAcao);
    }

    public EdtBot(String comTitulo, ImageIcon eIcone) {
        this(comTitulo, eIcone, null);
    }

    public EdtBot(ImageIcon comIcone, AbstractAction eAcao) {
        this(null, comIcone, eAcao);
    }

    public EdtBot(String comTitulo, ImageIcon eIcone, AbstractAction eAcao) {
        super();
        botao = null;
        editor = new JButton();
        add(editor, BorderLayout.CENTER);
        if (comTitulo != null) {
            editor.setText(comTitulo);
        }
        if (eIcone != null) {
            editor.setIcon(eIcone);
        }
        if (eAcao != null) {
            editor.addActionListener(eAcao);
        }
        inicia();
    }

    public EdtBot(Botao doBotao) {
        this(doBotao, null);
    }

    public EdtBot(Botao doBotao, Object comOrigem) {
        super();
        botao = doBotao;
        if (comOrigem == null) {
            comOrigem = this;
        }
        origem = comOrigem;
        editor = botao.cria(origem);
        add(editor, BorderLayout.CENTER);
        inicia();
    }

    @Override
    public void inicia() {
        menu = null;
        prioridade = Thread.NORM_PRIORITY;
        codigo = null;
        retornos = null;
        origem = this;
        pPopMenu().bota("Código", new ActMenCodigo());
        editor.addActionListener(new ActExcuta());
        super.inicia();
    }

    @Override
    public void atualizaOpcoes() {
    }

    @Override
    public void atualizaParams() {
    }

    @Override
    public JButton controle() {
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
            return new VBot(codigo, retornos, origem);
        }
    }

    @Override
    public synchronized EdtBot mdVlr(Object para) throws Exception {
        return this;
    }

    @Override
    public EdtBot botaAoModificar(AbstractAction aAcao) {

        return this;
    }

    @Override
    public EdtBot auxiliar() throws Exception {

        return this;
    }

    @Override
    public EdtBot aleatorio() {

        return this;
    }

    @Override
    public Boolean vazio() {
        if (codigo != null) {
            if (!codigo.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public EdtBot mEditavel(Boolean para) {
        editor.setEnabled(para);
        return this;
    }

    @Override
    public Boolean editavel() {
        return editor.isEnabled();
    }

    @Override
    public EdtBot botaTitulo(String oTitulo) {
        return botaTitulo(oTitulo, false, null);
    }

    @Override
    public EdtBot botaTitulo(String oTitulo, Boolean ehObrigatorio) {
        return botaTitulo(oTitulo, ehObrigatorio, null);
    }

    @Override
    public EdtBot botaTitulo(String oTitulo, Boolean ehObrigatorio, String comDica) {
        mTitulo(oTitulo);
        mObrigatorio(ehObrigatorio);
        mDica(comDica);
        editor.setText(oTitulo);
        if (ehObrigatorio) {
            Font fnt = editor.getFont();
            Font bldFnt = new Font(fnt.getFontName(), Font.BOLD, fnt.getSize());
            editor.setFont(bldFnt);
        } else {
            Font fnt = editor.getFont();
            Font bldFnt = new Font(fnt.getFontName(), Font.PLAIN, fnt.getSize());
            editor.setFont(bldFnt);
        }
        editor.setToolTipText(comDica);
        editor.revalidate();
        return this;
    }

    public Botao pBotao() {
        return botao;
    }

    public String pCodigo() {
        return codigo;
    }

    public EdtBot mCodigo(String para) {
        this.codigo = para;
        return this;
    }

    public Retornos pRetornos() {
        return retornos;
    }

    public EdtBot mRetornos(Retornos retornos) {
        this.retornos = retornos;
        return this;
    }

    public EdtBot botaRetornos() {
        this.retornos = new Retornos();
        return this;
    }

    public Object pOrigem() {
        return origem;
    }

    public EdtBot mOrigem(Object origem) {
        this.origem = origem;
        return this;
    }

    public Object pRetorno() {
        return retorno;
    }

    public AbstractAction pAoRetornar() {
        return aoRetornar;
    }

    public EdtBot mAoRetornar(AbstractAction aAcao) {
        aoRetornar = aAcao;
        return this;
    }

    public AbstractAction pAoConfirmar() {
        return aoConfirmar;
    }

    public EdtBot mAoConfirmar(AbstractAction aoConfirmar) {
        this.aoConfirmar = aoConfirmar;
        return this;
    }

    public PopMenu pMenu() {
        if (menu == null) {
            menu = new PopMenu();
            editor.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    menu.mostra(editor);
                }
            });
        }
        return menu;
    }

    private PaiTabela panPreenche;

    private void atualizaAltura() {
        Integer largura = editor.getPreferredSize().width;
        Integer altura = getParent().getPreferredSize().height - 2;
        panPreenche.setPreferredSize(new Dimension(largura, altura));
        panPreenche.revalidate();
        revalidate();
    }

    public EdtBot botaAPreencher() {
        remove(editor);
        panPreenche = new PaiTabela()
                .bota()
                .preencheAmbos()
                .insere()
                .bota(editor)
                .preencheNao()
                .insere();
        add(panPreenche, BorderLayout.CENTER);
        botaDimensionavel();
        getParent().addContainerListener(new ContainerListener() {
            @Override
            public void componentAdded(ContainerEvent e) {
                atualizaAltura();
            }

            @Override
            public void componentRemoved(ContainerEvent e) {
                atualizaAltura();
            }
        });
        atualizaAltura();
        return this;
    }

    private class ActMenCodigo extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                RoteirosIntf roe = new RoteirosIntf();
                roe.mNome(pNome());
                roe.mPrioridade(prioridade);
                roe.edtCodigo().mdVlr(codigo);
                roe.mAoSalvar(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            mNome(roe.pNome());
                            prioridade = roe.pPrioridade();
                            codigo = roe.edtCodigo().pgVlr().pgCrs();
                            Janelas.fecha(roe);
                        } catch (Exception ex) {
                            Utl.registra(ex);
                        }
                    }
                });
                roe.mostra();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    private class ActExcuta extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (aoConfirmar != null) {
                    aoConfirmar.actionPerformed(e);
                }
                if (!vazio()) {
                    Roteiro rot = new Roteiro(pNome(), prioridade, codigo);
                    rot.mudaRetornos(retornos);
                    rot.mudaOrigem(origem);
                    retorno = rot.inicia()
                            .espera()
                            .pegaRetorno();
                    if (aoRetornar != null) {
                        aoRetornar.actionPerformed(new ActionEvent(EdtBot.this, 0, "AoRetornar"));
                    }
                }
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

}
