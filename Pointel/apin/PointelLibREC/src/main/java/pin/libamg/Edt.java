package pin.libamg;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import pin.libamk.Botao;
import pin.libbas.Conferencia;
import pin.libbas.Configs;
import pin.libbas.Dados;
import pin.libbas.Globais;
import pin.libfor.Fonte;
import pin.libitr.QuestaoCor;
import pin.libitr.QuestaoFonte;
import pin.libjan.Borda;
import pin.libjan.Janelas;
import pin.libjan.PopMenu;
import pin.libjan.TrataIntf;
import pin.libout.ComponentMover;
import pin.libout.ComponentResizer;
import pin.libpic.Pics;
import pin.libtex.Marcados;
import pin.libtex.Marcas;
import pin.libutl.Utl;
import pin.libutl.UtlBin;
import pin.libutl.UtlCor;
import pin.libutl.UtlCrs;
import pin.libutl.UtlInt;
import pin.libutl.UtlLis;
import pin.libutl.UtlLog;
import pin.libutl.UtlTex;
import pin.libvlr.VApr;
import pin.libvlr.VArq;
import pin.libvlr.VAud;
import pin.libvlr.VCor;
import pin.libvlr.VCr;
import pin.libvlr.VCrs;
import pin.libvlr.VDat;
import pin.libvlr.VDatHor;
import pin.libvlr.VDoc;
import pin.libvlr.VEsc;
import pin.libvlr.VFor;
import pin.libvlr.VFrm;
import pin.libvlr.VHor;
import pin.libvlr.VIma;
import pin.libvlr.VInt;
import pin.libvlr.VIntLon;
import pin.libvlr.VLis;
import pin.libvlr.VLog;
import pin.libvlr.VMom;
import pin.libvlr.VNum;
import pin.libvlr.VNumLon;
import pin.libvlr.VPla;
import pin.libvlr.VTex;
import pin.libvlr.VVid;
import pin.libvlr.Vlr;
import pin.libvtf.BordaVtf;
import pin.libvtf.DimensaoVtf;
import pin.libvtf.PosicaoVtf;
import pin.libvtf.TituloVtf;

public abstract class Edt extends JPanel {

    private final PopMenu popMenu;
    private final ActApaCor actApaCor;
    private final ActApaFundo actApaFundo;
    private final ActApaBorda actApaBorda;
    private final ActApaFonte actApaFonte;
    private final ActApaTitulo actApaTitulo;
    private final ActApaPosPuxa actApaPosPuxa;
    private final ActApaPosEspecifica actApaPosEspecifica;
    private final ActApaLimPuxa actApaLimPuxa;
    private final ActApaLimEspecifica actApaLimEspecifica;
    private final ActApaLimAutoDimensao actApaLimAutoDimensao;
    private final ActValRecorta actValRecorta;
    private final ActValCopia actValCopia;
    private final ActValCola actValCola;
    private final ActValLimpa actValLimpa;
    private final ActValAuxiliar actValAuxiliar;
    private final ActValAleatorio actValAleatorio;
    private final ActValDescreve actValDescreve;
    private volatile Integer id;
    private volatile String pai;
    private volatile Integer paiLinha;
    private volatile Integer paiColuna;
    private volatile String nome;
    private volatile String titulo;
    private volatile Boolean obrigatorio;
    private volatile String dica;
    private volatile Boolean autoDimensao;
    private volatile Object inicial;
    private volatile Object fixo;
    private volatile List<Object> opcoes;
    private volatile List<Object> params;
    private volatile Integer botoesLayout;
    private volatile String botoesLocal;
    private volatile List<EdtBot> botoes;
    private volatile JLabel jlbTitulo;
    private volatile JPanel jpnBotoes;
    private volatile JLabel jlbAtualizando;

    public Edt() {
        super(new BorderLayout(4, 4));
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setOpaque(false);
        setFocusable(false);
        setAlignmentY(Component.TOP_ALIGNMENT);
        setAlignmentX(Component.LEFT_ALIGNMENT);
        popMenu = new PopMenu();
        actApaCor = new ActApaCor();
        actApaFundo = new ActApaFundo();
        actApaBorda = new ActApaBorda();
        actApaFonte = new ActApaFonte();
        actApaTitulo = new ActApaTitulo();
        actApaPosPuxa = new ActApaPosPuxa();
        actApaPosEspecifica = new ActApaPosEspecifica();
        actApaLimPuxa = new ActApaLimPuxa();
        actApaLimEspecifica = new ActApaLimEspecifica();
        actApaLimAutoDimensao = new ActApaLimAutoDimensao();
        actValRecorta = new ActValRecorta();
        actValCopia = new ActValCopia();
        actValCola = new ActValCola();
        actValLimpa = new ActValLimpa();
        actValAuxiliar = new ActValAuxiliar();
        actValAleatorio = new ActValAleatorio();
        actValDescreve = new ActValDescreve();
        pai = null;
        paiLinha = null;
        paiColuna = null;
        id = UtlBin.novoId(this);
        nome = null;
        titulo = null;
        obrigatorio = null;
        dica = null;
        autoDimensao = false;
        opcoes = null;
        params = null;
        botoesLayout = BoxLayout.LINE_AXIS;
        botoesLocal = BorderLayout.EAST;
        botoes = null;
        jlbTitulo = null;
        jpnBotoes = null;
        jlbAtualizando = null;
    }

    public static Edt descrito(String nosCaracteres) throws Exception {
        if (nosCaracteres == null) {
            return null;
        }
        nosCaracteres = nosCaracteres.trim();
        if (nosCaracteres.isEmpty()) {
            return null;
        }
        if (nosCaracteres.equals("null")) {
            return null;
        }
        Marcas marcas = Marcados.pega(nosCaracteres);
        String tp = marcas.marcada("tp").valor;
        Edt retorno = (Edt) Class.forName(tp).newInstance();
        retorno.mId(UtlInt.pega(marcas.valor("id")));
        retorno.mPai(marcas.valor("pai"));
        retorno.mPaiLinha(UtlInt.pega(marcas.valor("paiLinha")));
        retorno.mPaiLinha(UtlInt.pega(marcas.valor("paiColuna")));
        retorno.mNome(marcas.valor("nome"));
        retorno.mTitulo(marcas.valor("titulo"));
        retorno.mObrigatorio(UtlLog.pega(marcas.valor("obrigatorio")));
        retorno.mDica(marcas.valor("dica"));
        retorno.criaTitulo();
        String borda = marcas.valor("borda");
        if (borda != null) {
            retorno.mBorda(Borda.descrito(borda));
        }
        retorno.mCor(UtlCor.pega(marcas.valor("cor"), TrataIntf.padraoCor(retorno.controle())));
        retorno.mFundo(UtlCor.pega(marcas.valor("fundo"), TrataIntf.padraoFundo(retorno.controle())));
        String fonte = marcas.valor("fonte");
        if (fonte != null) {
            retorno.mFonte(Fonte.descrito(fonte));
        }
        retorno.mPosicao(UtlInt.pega(marcas.valor("posx"), 0), UtlInt.pega(marcas.valor("posy"), 0));
        if (UtlLog.pega(marcas.valor("dimensionavel"), false)) {
            retorno.botaDimensionavel();
        } else {
            retorno.mLargura(UtlInt.pega(marcas.valor("largura")));
            retorno.mAltura(UtlInt.pega(marcas.valor("altura")));
        }
        retorno.mAutoDimensao(UtlLog.pega(marcas.valor("autoDimensao"), false));
        retorno.botaOpcoes(UtlLis.descrito(marcas.valor("opcoes")));
        retorno.botaParams(UtlLis.descrito(marcas.valor("params")));
        retorno.mBotoesLayout(UtlInt.pega(marcas.valor("botoesLayout"), BoxLayout.LINE_AXIS));
        retorno.mBotoesLocal(UtlCrs.pega(marcas.valor("botoesLocal"), BorderLayout.EAST));
        retorno.botaBotoes(UtlLis.descrito(marcas.valor("botoes"), EdtBot.class));
        retorno.mdVlr(UtlBin.descrito(marcas.valor("vlr")));
        return retorno;
    }

    public abstract JComponent controle();

    public abstract JScrollPane rolagem();

    public abstract Edt mEditavel(Boolean para);

    public abstract Boolean editavel();

    public abstract Boolean vazio();

    public abstract Vlr pgVlr(Object comPadrao) throws Exception;

    public abstract Edt mdVlr(Object para) throws Exception;

    public abstract Edt botaAoModificar(AbstractAction aAcao);

    public abstract void atualizaOpcoes();

    public abstract void atualizaParams();

    public abstract Edt auxiliar() throws Exception;

    public abstract Edt aleatorio();

    public PopMenu pPopMenu() {
        return popMenu;
    }

    public ActApaCor pActApaCor() {
        return actApaCor;
    }

    public ActApaFundo pActApaFundo() {
        return actApaFundo;
    }

    public ActApaBorda pActApaBorda() {
        return actApaBorda;
    }

    public ActApaFonte pActApaFonte() {
        return actApaFonte;
    }

    public ActApaTitulo pActApaTitulo() {
        return actApaTitulo;
    }

    public ActApaPosPuxa pActApaPosPuxa() {
        return actApaPosPuxa;
    }

    public ActApaPosEspecifica pActApaPosEspecifica() {
        return actApaPosEspecifica;
    }

    public ActApaLimPuxa pActApaLimPuxa() {
        return actApaLimPuxa;
    }

    public ActApaLimEspecifica pActApaLimEspecifica() {
        return actApaLimEspecifica;
    }

    public ActApaLimAutoDimensao pActApaLimAutoDimensao() {
        return actApaLimAutoDimensao;
    }

    public ActValAuxiliar pActValAuxiliar() {
        return actValAuxiliar;
    }

    public ActValAleatorio pActValAleatorio() {
        return actValAleatorio;
    }

    public ActValLimpa pActValLimpa() {
        return actValLimpa;
    }

    public ActValRecorta pActValRecorta() {
        return actValRecorta;
    }

    public ActValCopia pActValCopia() {
        return actValCopia;
    }

    public ActValCola pActValCola() {
        return actValCola;
    }

    public void inicia() {
        popMenu.botaSeparador();
        popMenu.bota("Aparência");
        popMenu.bota("Aparência", "Cor", actApaCor);
        popMenu.bota("Aparência", "Fundo", actApaFundo);
        popMenu.bota("Aparência", "Borda", actApaBorda);
        popMenu.bota("Aparência", "Fonte", actApaFonte);
        popMenu.bota("Aparência", "Título", actApaTitulo);
        popMenu.bota("Aparência", "Posição");
        popMenu.bota("Aparência.Posição", "Puxa", actApaPosPuxa);
        popMenu.bota("Aparência.Posição", "Especifica", actApaPosEspecifica);
        popMenu.bota("Aparência", "Limites");
        popMenu.bota("Aparência.Limites", "Puxa", actApaLimPuxa);
        popMenu.bota("Aparência.Limites", "Especifica", actApaLimEspecifica);
        popMenu.bota("Aparência.Limites", "AutoDimensão", actApaLimAutoDimensao);
        actApaLimAutoDimensao.inicia();
        popMenu.bota("Valor");
        popMenu.bota("Valor", "Recorta", actValRecorta).botaAtalho(controle(), "shift control X");
        popMenu.bota("Valor", "Copia", actValCopia).botaAtalho(controle(), "shift control C");
        popMenu.bota("Valor", "Cola", actValCola).botaAtalho(controle(), "shift control V");
        popMenu.botaSeparador("Valor");
        popMenu.bota("Valor", "Limpa", actValLimpa).botaAtalho(controle(), "shift control alt X");
        popMenu.bota("Valor", "Auxiliar", actValAuxiliar).botaAtalho(controle(), "F9");
        popMenu.bota("Valor", "Aleatório", actValAleatorio);
        popMenu.botaSeparador("Valor");
        popMenu.bota("Valor", "Descreve", actValDescreve);
        popMenu.coloca(controle(), false, true);
        popMenu.coloca(rolagem(), false);
        Janelas.botaAtalho(controle(), "Edt - Abrir Pop Menu", "control shift M", new ActMenu());
        setMinimumSize(getPreferredSize());
        setMaximumSize(getPreferredSize());
        setSize(getPreferredSize());
    }

    public Configs cfgs() {
        return (Configs) Globais.pega("mainConf");
    }

    public JComponent recipiente() {
        if (rolagem() != null) {
            return rolagem();
        } else {
            return controle();
        }
    }

    public void abreEdt() {
    }

    public void fechaEdt() {
        try {
            mdVlr(null);
            UtlBin.fechaId(id);
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }

    public void limpa() throws Exception {
        if (fixo != null) {
            mdVlr(fixo);
        } else if (inicial != null) {
            mdVlr(inicial);
        } else {
            mdVlr(null);
        }
    }

    public Integer pId() {
        return id;
    }

    public Edt mId(Integer para) {
        this.id = UtlBin.mudaId(this, this.id, para);
        return this;
    }

    @Override
    public int hashCode() {
        return pId();
    }

    public Boolean temPai() {
        return pai != null;
    }

    public String pPai() {
        return pai;
    }

    public Edt mPai(String oPai) {
        pai = oPai;
        return this;
    }

    public Integer pPaiLinha() {
        return paiLinha;
    }

    public Edt mPaiLinha(Integer para) {
        paiLinha = para;
        return this;
    }

    public Integer pPaiColuna() {
        return paiColuna;
    }

    public Edt mPaiColuna(Integer para) {
        paiLinha = para;
        return this;
    }

    public Edt mPaiLocal(Integer naLinha, Integer eColuna) {
        paiLinha = naLinha;
        paiColuna = eColuna;
        return this;
    }

    public String pNome() {
        return nome;
    }

    public Edt mNome(String oNome) {
        nome = oNome;
        return this;
    }

    public Edt botaTitulo(String oTitulo) {
        return botaTitulo(oTitulo, false, null);
    }

    public Edt botaTitulo(String oTitulo, Boolean ehObrigatorio) {
        return botaTitulo(oTitulo, ehObrigatorio, null);
    }

    public Edt botaTitulo(String oTitulo, Boolean ehObrigatorio, String comDica) {
        if (jlbTitulo != null) {
            tiraTitulo();
        }
        titulo = oTitulo;
        obrigatorio = ehObrigatorio;
        dica = comDica;
        jlbTitulo = new JLabel(oTitulo);
        jlbTitulo.setFocusable(false);
        jlbTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        jlbTitulo.setAlignmentY(Component.TOP_ALIGNMENT);
        if (ehObrigatorio) {
            Font fnt = jlbTitulo.getFont();
            Font bldFnt = new Font(fnt.getFontName(), Font.BOLD, fnt.getSize());
            jlbTitulo.setFont(bldFnt);
        }
        if (comDica != null) {
            jlbTitulo.setToolTipText(comDica);
            controle().setToolTipText(comDica);
        }
        jlbTitulo.setMinimumSize(jlbTitulo.getPreferredSize());
        jlbTitulo.setMaximumSize(jlbTitulo.getPreferredSize());
        jlbTitulo.setSize(jlbTitulo.getPreferredSize());
        add(jlbTitulo, BorderLayout.NORTH);
        setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        validate();
        setMinimumSize(getPreferredSize());
        setMaximumSize(getPreferredSize());
        setSize(getPreferredSize());
        popMenu.coloca(jlbTitulo, false);
        return this;
    }

    public Edt tiraTitulo() {
        titulo = null;
        if (jlbTitulo != null) {
            remove(jlbTitulo);
            setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            validate();
            setMinimumSize(getPreferredSize());
            setMaximumSize(getPreferredSize());
            setSize(getPreferredSize());
        }
        return this;
    }

    public String pTitulo() {
        return titulo;
    }

    public Edt mTitulo(String para) {
        titulo = para;
        if (jlbTitulo != null) {
            jlbTitulo.setText(para);
        }
        return this;
    }

    public Boolean pObrigatorio() {
        return obrigatorio;
    }

    public Edt mObrigatorio(Boolean para) {
        obrigatorio = para;
        if (jlbTitulo != null) {
            if (para) {
                Font fnt = jlbTitulo.getFont();
                jlbTitulo.setFont(new Font(fnt.getFontName(), Font.BOLD, fnt.getSize()));
            } else {
                Font fnt = jlbTitulo.getFont();
                jlbTitulo.setFont(new Font(fnt.getFontName(), Font.PLAIN, fnt.getSize()));
            }
        }
        return this;
    }

    public Edt criaTitulo() {
        if (titulo != null) {
            botaTitulo(titulo, obrigatorio, dica);
        } else {
            tiraTitulo();
        }
        return this;
    }

    public String pDica() {
        return dica;
    }

    public Edt mDica(String para) {
        dica = para;
        if (jlbTitulo != null) {
            jlbTitulo.setToolTipText(para);
        }
        controle().setToolTipText(para);
        return this;
    }

    public Edt botaTituloVisivel() {
        if (jlbTitulo != null) {
            jlbTitulo.setVisible(true);
        }
        return this;
    }

    public Edt botaTituloInvisivel() {
        if (jlbTitulo != null) {
            jlbTitulo.setVisible(false);
        }
        return this;
    }

    public Edt botaVisivel() {
        setVisible(true);
        validate();
        return this;
    }

    public Edt botaInvisivel() {
        setVisible(false);
        validate();
        return this;
    }

    public Edt habilita() {
        controle().setEnabled(true);
        return this;
    }

    public Edt deshabilita() {
        controle().setEnabled(false);
        return this;
    }

    public Edt mFocavel(Boolean para) {
        controle().setFocusable(para);
        if (botoes != null) {
            for (EdtBot bot : botoes) {
                bot.controle().setFocusable(para);
            }
        }
        return this;
    }

    public Edt botaFoco() {
        TrataIntf.garanteFoco(controle());
        return this;
    }

    public Object pInicial() {
        return inicial;
    }

    public Edt mInicial(Object inicial) {
        this.inicial = inicial;
        return this;
    }

    public Object pFixo() {
        return fixo;
    }

    public Edt mFixo(Object fixo) {
        this.fixo = fixo;
        return this;
    }

    public List<Object> opcoes() {
        return opcoes;
    }

    public Edt botaOpcoes(Object... asOpcoes) {
        if (asOpcoes != null) {
            if (opcoes == null) {
                opcoes = new ArrayList<>();
            }
            opcoes.addAll(Arrays.asList(asOpcoes));
            atualizaOpcoes();
        }
        return this;
    }

    public Edt botaOpcoes(List<Object> asOpcoes) {
        if (asOpcoes != null) {
            if (opcoes == null) {
                opcoes = new ArrayList<>();
            }
            opcoes.addAll(asOpcoes);
            atualizaOpcoes();
        }
        return this;
    }

    public Edt botaOpcoes(Class daClasse) {
        return botaOpcoes(TrataIntf.pegaOpcoes(daClasse));
    }

    public Edt limpaOpcoes() {
        if (opcoes != null) {
            opcoes.clear();
        }
        opcoes = null;
        return this;
    }

    public List<Object> params() {
        return params;
    }

    public Boolean temParam(Object oParam) {
        Boolean retorno = false;
        if (params != null) {
            for (Object obj : params) {
                if (oParam.equals(obj)) {
                    retorno = true;
                    break;
                }
            }
        }
        return retorno;
    }

    public Boolean temParam(Class daClasse) {
        Boolean retorno = false;
        if (params != null) {
            for (Object obj : params) {
                if (daClasse.isInstance(obj)) {
                    retorno = true;
                    break;
                }
            }
        }
        return retorno;
    }

    public <T> T pegaParam(Class<? extends T> daClasse) {
        return pegaParam(daClasse, null);
    }

    public <T> T pegaParam(Class<? extends T> daClasse, T comPadrao) {
        T retorno = comPadrao;
        if (params != null) {
            for (Object obj : params) {
                if (daClasse.isInstance(obj)) {
                    retorno = (T) obj;
                    break;
                }
            }
        }
        return retorno;
    }

    public Edt botaParams(List<Object> osParametros) {
        if (osParametros != null) {
            if (params == null) {
                params = new ArrayList<>();
            }
            params.addAll(osParametros);
            atualizaParams();
        }
        return this;
    }

    public Edt botaParams(Object... osParametros) {
        if (osParametros != null) {
            if (params == null) {
                params = new ArrayList<>();
            }
            params.addAll(Arrays.asList(osParametros));
            atualizaParams();
        }
        return this;
    }

    public Edt limpaParams() {
        if (params != null) {
            params.clear();
        }
        params = null;
        return this;
    }

    public void botaAcao(AbstractAction aAcao) {
        TrataIntf.botaAcao(controle(), aAcao);
    }

    public Edt botaAoEntrar(AbstractAction comAcao) {
        controle().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                comAcao.actionPerformed(new ActionEvent(controle(), 0, "AoEntrar"));
            }
        });

        return this;
    }

    public Edt botaAoSair(AbstractAction comAcao) {
        controle().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                comAcao.actionPerformed(new ActionEvent(controle(), 0, "AoSair"));
            }
        });

        return this;
    }

    public Edt botaConferencia(Conferencia aConferencia) {
        controle().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                Boolean conferido = false;
                try {
                    aConferencia.confere(this);
                    conferido = true;
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
                if (!conferido) {
                    controle().requestFocus();
                }
            }
        });
        TrataIntf.botaAcao(controle(), new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean conferido = false;
                try {
                    aConferencia.confere(this);
                    conferido = true;
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
                if (!conferido) {
                    controle().requestFocus();
                }
            }
        });
        return this;
    }

    public Integer pPosX() {
        return pPosicao().x;
    }

    public Integer pPosY() {
        return pPosicao().y;
    }

    public Point pPosicao() {
        int xb = getBounds().x;
        int yb = getBounds().y;
        int xl = getLocation().x;
        int yl = getLocation().y;
        return new Point(Math.max(xb, xl), Math.max(yb, yl));
    }

    public Edt mPosicao(Point paraPosicao) {
        return mPosicao(paraPosicao.x, paraPosicao.y);
    }

    public Edt mPosicao(Integer comPosX, Integer ePosY) {
        Rectangle rct = getBounds();
        rct.x = comPosX;
        rct.y = ePosY;
        setBounds(rct);
        setLocation(comPosX, ePosY);
        validate();
        return this;
    }

    public Integer pLargura() {
        if (rolagem() != null) {
            return rolagem().getBounds().width;
        } else {
            return controle().getBounds().width;
        }
    }

    public Integer pAltura() {
        if (rolagem() != null) {
            return rolagem().getBounds().height;
        } else {
            return controle().getBounds().height;
        }
    }

    public Edt mLargura(Integer para) {
        JComponent alt = rolagem();
        if (alt == null) {
            alt = controle();
        }
        TrataIntf.mudaLargura(alt, para);
        return forcaDimensao();
    }

    public Edt mAltura(Integer para) {
        JComponent alt = rolagem();
        if (alt == null) {
            alt = controle();
        }
        TrataIntf.mudaAltura(alt, para);
        return forcaDimensao();
    }

    public Edt mDimensao(Integer comLargura, Integer eAltura) {
        JComponent alt = rolagem();
        if (alt == null) {
            alt = controle();
        }
        TrataIntf.mDimensao(alt, comLargura, eAltura);
        return forcaDimensao();
    }

    public Edt forcaDimensao() {
        if (TrataIntf.ehPaiSemLayout(this)) {
            Dimension dim = new Dimension(getBounds().width, getBounds().height);
            setMinimumSize(dim);
            setMaximumSize(dim);
            setSize(dim);
        } else {
            setMinimumSize(getPreferredSize());
            setMaximumSize(getPreferredSize());
            setSize(getPreferredSize());
        }
        validate();
        return this;
    }

    public Edt desforcaDimensao() {
        setMinimumSize(null);
        setMaximumSize(null);
        setPreferredSize(null);
        if (TrataIntf.ehPaiSemLayout(this)) {
            setSize(new Dimension(getBounds().width, getBounds().height));
        } else {
            setSize(getPreferredSize());
        }
        validate();
        return this;
    }

    public Edt botaDimensionavel() {
        JComponent alt = rolagem();
        if (alt == null) {
            alt = controle();
        }
        alt.setMinimumSize(null);
        alt.setMaximumSize(null);
        alt.setPreferredSize(null);
        if (TrataIntf.ehPaiSemLayout(this)) {
            alt.setSize(new Dimension(alt.getBounds().width, alt.getBounds().height));
        } else {
            alt.setSize(alt.getPreferredSize());
        }
        alt.validate();
        return desforcaDimensao();
    }

    public Boolean ehDimensionavel() {
        JComponent alt = rolagem();
        if (alt == null) {
            alt = controle();
        }
        return alt.getMinimumSize() == null;
    }

    public Boolean pAutoDimensao() {
        return autoDimensao;
    }

    public Edt mAutoDimensao(Boolean para) {
        autoDimensao = para;
        actApaLimAutoDimensao.atualiza();
        return this;
    }

    public Edt botaAutoDimensao() {
        autoDimensao = true;
        actApaLimAutoDimensao.atualiza();
        return this;
    }

    public Edt tiraAutoDimensao() {
        autoDimensao = false;
        actApaLimAutoDimensao.atualiza();
        return this;
    }

    public Edt trocaAutoDimensao() {
        autoDimensao = !autoDimensao;
        actApaLimAutoDimensao.atualiza();
        return this;
    }

    public Color pCor() {
        return controle().getForeground();
    }

    public Edt mCor(Color aCor) {
        controle().setForeground(aCor);
        return this;
    }

    public Edt mCor(VCor aCor) {
        controle().setForeground(aCor.pgCor());
        return this;
    }

    public Color pFundo() {
        return controle().getBackground();
    }

    public Edt mFundo(Color aCor) {
        controle().setBackground(aCor);
        return this;
    }

    public Edt mFundo(VCor aCor) {
        controle().setBackground(aCor.pgCor());
        return this;
    }

    public Font pFonte() {
        return controle().getFont();
    }

    public Edt mFonte(Fonte aFonte) {
        controle().setFont(aFonte.pNativa());
        actApaLimAutoDimensao.atualiza();
        return this;
    }

    public Edt mFonte(Font aFonte) {
        controle().setFont(aFonte);
        actApaLimAutoDimensao.atualiza();
        return this;
    }

    public Border pBorda() {
        if (rolagem() != null) {
            return rolagem().getBorder();
        } else {
            return controle().getBorder();
        }
    }

    public Edt mBorda(Border aBorda) {
        if (rolagem() != null) {
            rolagem().setBorder(aBorda == null ? TrataIntf.padraoBorda(rolagem()) : aBorda);
        } else {
            controle().setBorder(aBorda == null ? TrataIntf.padraoBorda(controle()) : aBorda);
        }
        return this;
    }

    public Edt mBorda(Borda aBorda) {
        if (rolagem() != null) {
            rolagem().setBorder(aBorda == null ? TrataIntf.padraoBorda(rolagem()) : aBorda.pNativa());
        } else {
            controle().setBorder(aBorda == null ? TrataIntf.padraoBorda(controle()) : aBorda.pNativa());
        }
        return this;
    }

    private void recriaJpnBotoes() {
        if (jpnBotoes != null) {
            JPanel antigo = jpnBotoes;
            jpnBotoes = new JPanel();
            jpnBotoes.setLayout(new BoxLayout(jpnBotoes, botoesLayout));
            jpnBotoes.setFocusable(false);
            for (Component cmp : antigo.getComponents()) {
                jpnBotoes.add(cmp);
            }
            remove(antigo);
            add(jpnBotoes, botoesLocal);
        }
    }

    public Integer pBotoesLayout() {
        return botoesLayout;
    }

    public Edt mBotoesLayout(Integer botoesLayout) {
        this.botoesLayout = botoesLayout;
        recriaJpnBotoes();
        return this;
    }

    public String pBotoesLocal() {
        return botoesLocal;
    }

    public Edt mBotoesLocal(String botoesLocal) {
        this.botoesLocal = botoesLocal;
        recriaJpnBotoes();
        return this;
    }

    public Edt botaBotao(Botao oBotao) {
        return botaBotao(oBotao, Edt.this);
    }

    public Edt botaBotao(Botao oBotao, Object comOrigem) {
        return botaBotao(new EdtBot(oBotao, comOrigem));
    }

    public Edt botaBotoes(List<EdtBot> osBotoes) {
        return botaBotoes(osBotoes, this);
    }

    public Edt botaBotoes(List<EdtBot> osBotoes, Object comOrigem) {
        if (osBotoes != null) {
            for (EdtBot bot : osBotoes) {
                botaBotao(bot);
            }
        }
        return this;
    }

    public Edt botaBotao(EdtBot oEdtBot) {
        if (jpnBotoes == null) {
            jpnBotoes = new JPanel();
            jpnBotoes.setLayout(new BoxLayout(jpnBotoes, botoesLayout));
            jpnBotoes.setFocusable(false);
            add(jpnBotoes, botoesLocal);
        }
        if (botoes == null) {
            botoes = new ArrayList<>();
        }
        Integer ordem = null;
        if (oEdtBot.pBotao() != null) {
            if (oEdtBot.pBotao().temOrdem()) {
                ordem = oEdtBot.pBotao().pOrdem();
            }
        }
        if (ordem != null) {
            jpnBotoes.add(oEdtBot.controle(), ordem.intValue());
            botoes.add(ordem.intValue(), oEdtBot);
        } else {
            jpnBotoes.add(oEdtBot.controle());
            botoes.add(oEdtBot);
        }
        return this;
    }

    public Edt tiraBotao(EdtBot oBotao) {
        jpnBotoes.remove(oBotao.controle());
        botoes.remove(oBotao);
        return this;
    }

    public Edt botaAtualizando() {
        JPanel gls = (JPanel) SwingUtilities.getRootPane(this).getGlassPane();
        if (gls == null) {
            gls = new JPanel();
            gls.setLayout(null);
            gls.setOpaque(false);
            gls.setBackground(new Color(0, 0, 0, 0));
            SwingUtilities.getRootPane(this).setGlassPane(gls);
            gls.setVisible(true);
        }
        if (gls.getLayout() != null) {
            gls.setLayout(null);
            gls.setOpaque(false);
            gls.setBackground(new Color(0, 0, 0, 0));
            gls.setVisible(true);
        }
        if (jlbAtualizando != null) {
            gls.remove(jlbAtualizando);
        } else {
            jlbAtualizando = new JLabel(Pics.pega("arrow_rotate_clockwise.png"));
        }
        Point pt = SwingUtilities.convertPoint(getParent(), getLocation(), gls);
        pt.x += getSize().width - jlbAtualizando.getPreferredSize().width;
        pt.y += getSize().height - jlbAtualizando.getPreferredSize().height;
        jlbAtualizando.setBounds(pt.x, pt.y, jlbAtualizando.getPreferredSize().width, jlbAtualizando.getPreferredSize().height);
        gls.add(jlbAtualizando);
        gls.validate();
        gls.repaint();
        return this;
    }

    public Edt tiraAtualizando() {
        JPanel gls = (JPanel) SwingUtilities.getRootPane(this).getGlassPane();
        if (gls == null) {
            gls = new JPanel();
            gls.setLayout(null);
            gls.setOpaque(false);
            gls.setBackground(new Color(0, 0, 0, 0));
            SwingUtilities.getRootPane(this).setGlassPane(gls);
        }
        if (jlbAtualizando != null) {
            gls.remove(jlbAtualizando);
            gls.validate();
            gls.repaint();
        }
        return this;
    }

    public String paraConsSQL() throws Exception {
        return pgVlr().paraConsSQL();
    }

    public Object paraParamSQL() throws Exception {
        return pgVlr().paraParamSQL();
    }

    public synchronized Vlr pgVlr() throws Exception {
        return pgVlr(null);
    }

    public VLog pgVlrLog() throws Exception {
        return pgVlr().pgVlrLog();
    }

    public VLog pgVlrLog(Object comPadrao) throws Exception {
        return pgVlr().pgVlrLog(comPadrao);
    }

    public VCr pgVlrCr() throws Exception {
        return pgVlr().pgVlrCr();
    }

    public VCr pgVlrCr(Object comPadrao) throws Exception {
        return pgVlr().pgVlrCr(comPadrao);
    }

    public VCrs pgVlrCrs() throws Exception {
        return pgVlr().pgVlrCrs();
    }

    public VCrs pgVlrCrs(Object comPadrao) throws Exception {
        return pgVlr().pgVlrCrs(comPadrao);
    }

    public VCor pgVlrCor() throws Exception {
        return pgVlr().pgVlrCor();
    }

    public VCor pgVlrCor(Object comPadrao) throws Exception {
        return pgVlr().pgVlrCor(comPadrao);
    }

    public VInt pgVlrInt() throws Exception {
        return pgVlr().pgVlrInt();
    }

    public VInt pgVlrInt(Object comPadrao) throws Exception {
        return pgVlr().pgVlrInt(comPadrao);
    }

    public VIntLon pgVlrIntLon() throws Exception {
        return pgVlr().pgVlrIntLon();
    }

    public VIntLon pgVlrIntLon(Object comPadrao) throws Exception {
        return pgVlr().pgVlrIntLon(comPadrao);
    }

    public VNum pgVlrNum() throws Exception {
        return pgVlr().pgVlrNum();
    }

    public VNum pgVlrNum(Object comPadrao) throws Exception {
        return pgVlr().pgVlrNum(comPadrao);
    }

    public VNumLon pgVlrNumLon() throws Exception {
        return pgVlr().pgVlrNumLon();
    }

    public VNumLon pgVlrNumLon(Object comPadrao) throws Exception {
        return pgVlr().pgVlrNumLon(comPadrao);
    }

    public VDat pgVlrDat() throws Exception {
        return pgVlr().pgVlrDat();
    }

    public VDat pgVlrDat(Object comPadrao) throws Exception {
        return pgVlr().pgVlrDat(comPadrao);
    }

    public VHor pgVlrHor() throws Exception {
        return pgVlr().pgVlrHor();
    }

    public VHor pgVlrHor(Object comPadrao) throws Exception {
        return pgVlr().pgVlrHor(comPadrao);
    }

    public VDatHor pgVlrDatHor() throws Exception {
        return pgVlr().pgVlrDatHor();
    }

    public VDatHor pgVlrDatHor(Object comPadrao) throws Exception {
        return pgVlr().pgVlrDatHor(comPadrao);
    }

    public VMom pgVlrMom() throws Exception {
        return pgVlr().pgVlrMom();
    }

    public VMom pgVlrMom(Object comPadrao) throws Exception {
        return pgVlr().pgVlrMom(comPadrao);
    }

    public VEsc pgVlrEsc() throws Exception {
        return pgVlr().pgVlrEsc();
    }

    public VEsc pgVlrEsc(Object comPadrao) throws Exception {
        return pgVlr().pgVlrEsc(comPadrao);
    }

    public VLis pgVlrLis() throws Exception {
        return pgVlr().pgVlrLis();
    }

    public VLis pgVlrLis(Object comPadrao) throws Exception {
        return pgVlr().pgVlrLis(comPadrao);
    }

    public VTex pgVlrTex() throws Exception {
        return pgVlr().pgVlrTex();
    }

    public VTex pgVlrTex(Object comPadrao) throws Exception {
        return pgVlr().pgVlrTex(comPadrao);
    }

    public VIma pgVlrIma() throws Exception {
        return pgVlr().pgVlrIma();
    }

    public VIma pgVlrIma(Object comPadrao) throws Exception {
        return pgVlr().pgVlrIma(comPadrao);
    }

    public VDoc pgVlrDoc() throws Exception {
        return pgVlr().pgVlrDoc();
    }

    public VDoc pgVlrDoc(Object comPadrao) throws Exception {
        return pgVlr().pgVlrDoc(comPadrao);
    }

    public VPla pgVlrPla() throws Exception {
        return pgVlr().pgVlrPla();
    }

    public VPla pgVlrPla(Object comPadrao) throws Exception {
        return pgVlr().pgVlrPla(comPadrao);
    }

    public VAud pgVlrAud() throws Exception {
        return pgVlr().pgVlrAud();
    }

    public VAud pgVlrAud(Object comPadrao) throws Exception {
        return pgVlr().pgVlrAud(comPadrao);
    }

    public VVid pgVlrVid() throws Exception {
        return pgVlr().pgVlrVid();
    }

    public VVid pgVlrVid(Object comPadrao) throws Exception {
        return pgVlr().pgVlrVid(comPadrao);
    }

    public VFor pgVlrFor() throws Exception {
        return pgVlr().pgVlrFor();
    }

    public VFor pgVlrFor(Object comPadrao) throws Exception {
        return pgVlr().pgVlrFor(comPadrao);
    }

    public VFrm pgVlrFrm() throws Exception {
        return pgVlr().pgVlrFrm();
    }

    public VFrm pgVlrFrm(Object comPadrao) throws Exception {
        return pgVlr().pgVlrFrm(comPadrao);
    }

    public VApr pgVlrApr() throws Exception {
        return pgVlr().pgVlrApr();
    }

    public VApr pgVlrApr(Object comPadrao) throws Exception {
        return pgVlr().pgVlrApr(comPadrao);
    }

    public VArq pgVlrArq() throws Exception {
        return pgVlr().pgVlrArq();
    }

    public VArq pgVlrArq(Object comPadrao) throws Exception {
        return pgVlr().pgVlrArq(comPadrao);
    }

    public static Vlr[] pegaVlrs(Edt... dosEditores) throws Exception {
        if (dosEditores != null) {
            Vlr[] retorno = new Vlr[dosEditores.length];
            for (int i1 = 0; i1 < dosEditores.length; i1++) {
                retorno[i1] = dosEditores[i1].pgVlr();
            }
            return retorno;
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        try {
            return pgVlr().toString();
        } catch (Exception ex) {
            Utl.registra(ex);
            return null;
        }
    }

    public String descreve() throws Exception {
        String retorno = Marcados.marca(getClass().getName(), "tp");
        if (id != null) {
            retorno += UtlTex.quebra() + Marcados.marca(UtlInt.formata(id), "id");
        }
        if (pai != null) {
            retorno += UtlTex.quebra() + Marcados.marca(pai, "pai");
        }
        if (paiLinha != null) {
            retorno += UtlTex.quebra() + Marcados.marca(UtlInt.formata(paiLinha), "paiLinha");
        }
        if (paiColuna != null) {
            retorno += UtlTex.quebra() + Marcados.marca(UtlInt.formata(paiColuna), "paiColuna");
        }
        if (nome != null) {
            retorno += UtlTex.quebra() + Marcados.marca(nome, "nome");
        }
        if (titulo != null) {
            retorno += UtlTex.quebra() + Marcados.marca(titulo, "titulo");
        }
        if (obrigatorio != null) {
            retorno += UtlTex.quebra() + Marcados.marca(UtlLog.formata(obrigatorio), "obrigatorio");
        }
        if (dica != null) {
            retorno += UtlTex.quebra() + Marcados.marca(dica, "dica");
        }
        if (!Objects.equals(pBorda(), TrataIntf.padraoBorda(recipiente()))) {
            retorno += UtlTex.quebra() + Marcados.marca(new Borda(pBorda()).descreve(), "borda");
        }
        if (!Objects.equals(pCor(), TrataIntf.padraoCor(controle()))) {
            retorno += UtlTex.quebra() + Marcados.marca(UtlCor.formata(pCor()), "cor");
        }
        if (!Objects.equals(pFundo(), TrataIntf.padraoFundo(controle()))) {
            retorno += UtlTex.quebra() + Marcados.marca(UtlCor.formata(pFundo()), "fundo");
        }
        if (!Objects.equals(pFonte(), TrataIntf.padraoFonte(controle()))) {
            retorno += UtlTex.quebra() + Marcados.marca(new Fonte(pFonte()).descreve(), "fonte");
        }
        retorno += UtlTex.quebra() + Marcados.marca(UtlInt.formata(pPosX()), "posx");
        retorno += UtlTex.quebra() + Marcados.marca(UtlInt.formata(pPosY()), "posy");
        if (ehDimensionavel()) {
            retorno += UtlTex.quebra() + Marcados.marca(UtlLog.formata(true), "dimensionavel");
        } else {
            retorno += UtlTex.quebra() + Marcados.marca(UtlInt.formata(pLargura()), "largura");
            retorno += UtlTex.quebra() + Marcados.marca(UtlInt.formata(pAltura()), "altura");
        }
        if (autoDimensao) {
            retorno += UtlTex.quebra() + Marcados.marca(UtlLog.formata(autoDimensao), "autoDimensao");
        }
        if (opcoes != null) {
            if (!opcoes.isEmpty()) {
                retorno += UtlTex.quebra() + Marcados.marca(UtlLis.descreve(opcoes), "opcoes");
            }
        }
        if (params != null) {
            if (!params.isEmpty()) {
                retorno += UtlTex.quebra() + Marcados.marca(UtlLis.descreve(params), "params");
            }
        }
        if (!Objects.equals(botoesLayout, BoxLayout.LINE_AXIS)) {
            retorno += UtlTex.quebra() + Marcados.marca(UtlInt.formata(botoesLayout), "botoesLayout");
        }
        if (!Objects.equals(botoesLocal, BorderLayout.EAST)) {
            retorno += UtlTex.quebra() + Marcados.marca(botoesLocal, "botoesLocal");
        }
        if (botoes != null) {
            if (!botoes.isEmpty()) {
                retorno += UtlTex.quebra() + Marcados.marca(new VLis(botoes).descreve(), "botoes");
            }
        }
        retorno += UtlTex.quebra() + Marcados.marca(UtlBin.descreve(pgVlr()), "vlr");
        return retorno;
    }

    public class ActApaCor extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                try {
                    new QuestaoCor(controle().getForeground()) {
                        @Override
                        public Boolean aoEscolher(Color comEscolhida) {
                            mCor(comEscolhida);
                            return true;
                        }
                    }.mostra();
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }

    }

    public class ActApaFundo extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                try {
                    new QuestaoCor(controle().getBackground()) {
                        @Override
                        public Boolean aoEscolher(Color comEscolhida) {
                            mFundo(comEscolhida);
                            return true;
                        }
                    }.mostra();
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }

    }

    public class ActApaBorda extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                try {
                    new BordaVtf() {
                        @Override
                        public void aoConfirmar(Object comOrigem) throws Exception {
                            mBorda(pgVal().pNativa());
                            fecha();
                        }
                    }.mostra();
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }

    }

    public class ActApaFonte extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                try {
                    new QuestaoFonte(controle().getFont()) {
                        @Override
                        public Boolean aoEscolher(Font comEscolhida) {
                            mFonte(comEscolhida);
                            return true;
                        }
                    }.mostra();
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }

    }

    public class ActApaTitulo extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                try {
                    new TituloVtf(titulo, obrigatorio, dica) {
                        @Override
                        public void aoConfirmar(String comTitulo, Boolean eObrigatorio, String eDica) {
                            Edt.this.botaTitulo(comTitulo, eObrigatorio, eDica);
                        }
                    }.mostra();
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }

    }

    public class ActApaPosPuxa extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                new ComponentMover().init(Edt.this);
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }

    }

    public class ActApaPosEspecifica extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                try {
                    new PosicaoVtf(getLocation()) {
                        @Override
                        public void aoConfirmar(Object comOrigem) throws Exception {
                            mPosicao(pgVal());
                        }
                    }.mostra();
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }

    }

    public class ActApaLimPuxa extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                new ComponentResizer().init(Edt.this);
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }

    }

    public class ActApaLimEspecifica extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                try {
                    new DimensaoVtf(getPreferredSize()) {
                        @Override
                        public void aoConfirmar(Object comOrigem) throws Exception {
                            mDimensao(pgVal());
                        }
                    }.mostra();
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActApaLimAutoDimensao extends AbstractAction {

        public void inicia() {
            atualiza();
            botaAoModificar(new AoModificar());
        }

        public void atualiza() {
            atualizaEscolhido();
            atualizaDimensao();
        }

        private void atualizaEscolhido() {
            pPopMenu().mudaSelecionado("Aparência.Limites", "AutoDimensão", autoDimensao);
        }

        private void atualizaDimensao() {
            if (autoDimensao) {
                Dimension dimCon = TrataIntf.pAutoDimensao(controle());
                mDimensao(dimCon.width, dimCon.height);
                Rectangle rect = getBounds();
                Dimension dimAct = getLayout().preferredLayoutSize(Edt.this);
                rect.width = dimAct.width;
                rect.height = dimAct.height;
                setBounds(rect);
                validate();
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            autoDimensao = !autoDimensao;
            atualiza();
        }

        private class AoModificar extends AbstractAction {

            @Override
            public void actionPerformed(ActionEvent e) {
                atualizaDimensao();
            }
        }
    }

    public class ActValLimpa extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                try {
                    limpa();
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }

    }

    public class ActValAuxiliar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                try {
                    auxiliar();
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }

    }

    public class ActValAleatorio extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                aleatorio();
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }

    }

    public class ActValDescreve extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Utl.alerta(descreve());
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }

    }

    public class ActValRecorta extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                try {
                    UtlCrs.copiaParaTransferencia(pgVlr().pgCrs());
                    limpa();
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }

    }

    public class ActValCopia extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                UtlCrs.copiaParaTransferencia(pgVlr().pgCrs());
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }

    }

    public class ActValCola extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                try {
                    mdVlr(UtlCrs.colaDaTransferencia());
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }

    }

    public class ActMenu extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            popMenu.troca(controle(), false);
        }
    }

    public static Edt novo(Dados.Tp oTipo) {
        Edt retorno = null;
        switch (oTipo) {
            case Abs:
                retorno = new EdtAbs();
                break;
            case Cts:
                retorno = new EdtCts();
                break;
            case Pai:
                retorno = new EdtPai();
                break;
            case Bot:
                retorno = new EdtBot();
                break;
            case Log:
                retorno = new EdtLog();
                break;
            case Cr:
                retorno = new EdtCr();
                break;
            case Crs:
                retorno = new EdtCrs();
                break;
            case Cor:
                retorno = new EdtCor();
                break;
            case Sen:
                retorno = new EdtSen();
                break;
            case Enu:
                retorno = new EdtEnu();
                break;
            case Sug:
                retorno = new EdtSug();
                break;
            case Int:
                retorno = new EdtInt();
                break;
            case IntLon:
                retorno = new EdtIntLon();
                break;
            case Num:
                retorno = new EdtNum();
                break;
            case NumLon:
                retorno = new EdtNumLon();
                break;
            case Dat:
                retorno = new EdtDat();
                break;
            case Hor:
                retorno = new EdtHor();
                break;
            case DatHor:
                retorno = new EdtDatHor();
                break;
            case Mom:
                retorno = new EdtMom();
                break;
            case Esc:
                retorno = new EdtEsc();
                break;
            case Lis:
                retorno = new EdtLis();
                break;
            case Arv:
                retorno = new EdtArv();
                break;
            case Tex:
                retorno = new EdtTex();
                break;
            case Ima:
                retorno = new EdtIma();
                break;
            case Aud:
                retorno = new EdtAud();
                break;
            case Vid:
                retorno = new EdtVid();
                break;
            case For:
                retorno = new EdtFor();
                break;
            case Pla:
                retorno = new EdtPla();
                break;
            case Doc:
                retorno = new EdtDoc();
                break;
            case Frm:
                retorno = new EdtFrm();
                break;
            case Apr:
                retorno = new EdtApr();
                break;
            case Arq:
                retorno = new EdtArq();
                break;
            case Ind:
                retorno = new EdtInd();
                break;
        }
        return retorno;
    }

    public static Edt novo(String comTitulo, Boolean obrigatorio, Dados.Tp oTipo, Integer comTamanho, Integer ePrecisao, Object eOpcoes, Object eParametros, String eDica, AbstractAction eAcao, Object vlrInicial) throws Exception {
        Edt retorno = null;
        switch (oTipo) {
            case Abs:
                retorno = new EdtAbs();
                break;
            case Cts:
                retorno = new EdtCts();
                break;
            case Pai:
                retorno = new EdtPai();
                break;
            case Bot:
                retorno = new EdtBot(comTitulo, eAcao);
                break;
            case Log:
                retorno = new EdtLog();
                break;
            case Cr:
                retorno = new EdtCr();
                break;
            case Crs:
                retorno = new EdtCrs(comTamanho);
                break;
            case Cor:
                retorno = new EdtCor();
                break;
            case Sen:
                retorno = new EdtSen(comTamanho);
                break;
            case Enu:
                retorno = new EdtEnu();
                break;
            case Sug:
                retorno = new EdtSug();
                break;
            case Int:
                retorno = new EdtInt(comTamanho);
                break;
            case IntLon:
                retorno = new EdtIntLon(comTamanho);
                break;
            case Num:
                retorno = new EdtNum(comTamanho, ePrecisao);
                break;
            case NumLon:
                retorno = new EdtNumLon(comTamanho, ePrecisao);
                break;
            case Dat:
                retorno = new EdtDat();
                break;
            case Hor:
                retorno = new EdtHor();
                break;
            case DatHor:
                retorno = new EdtDatHor();
                break;
            case Mom:
                retorno = new EdtMom();
                break;
            case Esc:
                retorno = new EdtEsc();
                break;
            case Lis:
                retorno = new EdtLis();
                break;
            case Arv:
                retorno = new EdtArv();
                break;
            case Tex:
                retorno = new EdtTex();
                break;
            case Ima:
                retorno = new EdtIma();
                break;
            case Aud:
                retorno = new EdtAud();
                break;
            case Vid:
                retorno = new EdtVid();
                break;
            case For:
                retorno = new EdtFor();
                break;
            case Pla:
                retorno = new EdtPla();
                break;
            case Doc:
                retorno = new EdtDoc();
                break;
            case Frm:
                retorno = new EdtFrm();
                break;
            case Apr:
                retorno = new EdtApr();
                break;
            case Arq:
                retorno = new EdtArq(comTamanho);
                break;
            case Ind:
                retorno = new EdtInd();
                break;
        }
        if (retorno != null) {
            if (eOpcoes != null) {
                if (eOpcoes instanceof List) {
                    retorno.botaOpcoes((List) eOpcoes);
                } else if (eOpcoes instanceof Object[]) {
                    retorno.botaOpcoes((Object[]) eOpcoes);
                } else {
                    retorno.botaOpcoes(eOpcoes);
                }
            }
            if (eParametros != null) {
                if (eParametros instanceof List) {
                    retorno.botaParams((List) eParametros);
                } else if (eParametros instanceof Object[]) {
                    retorno.botaParams((Object[]) eParametros);
                } else {
                    retorno.botaParams(eParametros);
                }
            }
            retorno.controle().setToolTipText(eDica);
            if (comTitulo != null) {
                retorno.botaTitulo(comTitulo, obrigatorio, eDica);
            }
            if (oTipo != Dados.Tp.Bot) {
                if (eAcao != null) {
                    retorno.botaAcao(eAcao);
                }
            }
            retorno.mInicial(vlrInicial);
            retorno.limpa();
        }
        return retorno;
    }

    public static Boolean ehSimples(Edt oEditor) {
        Boolean retorno = false;
        if (oEditor == null) {
            retorno = false;
        } else if (EdtLog.class.isInstance(oEditor)
                || EdtCr.class.isInstance(oEditor)
                || EdtCrs.class.isInstance(oEditor)
                || EdtCor.class.isInstance(oEditor)
                || EdtSen.class.isInstance(oEditor)
                || EdtEnu.class.isInstance(oEditor)
                || EdtSug.class.isInstance(oEditor)
                || EdtInt.class.isInstance(oEditor)
                || EdtIntLon.class.isInstance(oEditor)
                || EdtNum.class.isInstance(oEditor)
                || EdtNumLon.class.isInstance(oEditor)
                || EdtDat.class.isInstance(oEditor)
                || EdtHor.class.isInstance(oEditor)
                || EdtDatHor.class.isInstance(oEditor)
                || EdtMom.class.isInstance(oEditor)) {
            retorno = true;
        }
        return retorno;
    }

}
