package pin.libamk;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import pin.libamg.Edt;
import pin.libamg.EdtCts;
import pin.libbas.Conferencia;
import pin.libjan.Janelas;
import pin.libjan.TrataIntf;
import pin.libout.GBCWrap;
import pin.libutl.Utl;

public class Intf {

    public static enum AlinhamentoTp {
        Esquerda, Direita, Centro;

        public Integer pegaCFG() {
            switch (this) {
                case Esquerda:
                    return FlowLayout.LEFT;
                case Centro:
                    return FlowLayout.CENTER;
                default:
                    return FlowLayout.RIGHT;
            }
        }
    }

    public static enum PreencherTp {
        Horizontal, Vertical, Ambos, Nenhum;

        public Integer pegaCFG() {
            switch (this) {
                case Horizontal:
                    return GridBagConstraints.HORIZONTAL;
                case Vertical:
                    return GridBagConstraints.VERTICAL;
                case Ambos:
                    return GridBagConstraints.BOTH;
                default:
                    return GridBagConstraints.NONE;
            }
        }
    };

    private String titulo;
    private String descricao;
    private Integer linhaDescricao;
    private Campos campos;
    private Boolean mostraCampos;
    private Integer linhaCampos;
    private List<VinculoCts> vinculosCts;
    private Botoes botoes;
    private AlinhamentoTp botoesAlinhamento;
    private Integer linhaBotoes;
    private Dimension dimensao;
    private BufferedImage icone;
    private Boolean dimensionavel;
    private Boolean fecharAoConfirmar;
    private Boolean sairAoFechar;
    private JFrame janela;
    private JTextArea jtaDescricao;
    private JPanel jpnCampos;
    private JPanel jpnBotoes;
    private Boolean construido;
    private AbstractAction acaoConfirmar;
    private AbstractAction acaoCancelar;
    private List<Conferencia> antesDeAbrir;
    private List<Conferencia> depoisDeAbrir;
    private List<Conferencia> antesDeFechar;
    private List<Conferencia> depoisDeFechar;

    public Intf() {
        this(null, null);
    }

    public Intf(Campos comCampos) {
        this(null, comCampos);
    }

    public Intf(String comDescricao) {
        this(comDescricao, null);
    }

    public Intf(String comDescricao, Campos eCampos) {
        titulo = null;
        descricao = comDescricao;
        linhaDescricao = 30;
        campos = eCampos;
        mostraCampos = true;
        linhaCampos = 60;
        vinculosCts = null;
        botoes = null;
        botoesAlinhamento = AlinhamentoTp.Esquerda;
        linhaBotoes = 90;
        dimensao = null;
        icone = null;
        dimensionavel = false;
        fecharAoConfirmar = true;
        sairAoFechar = false;
        janela = null;
        jtaDescricao = null;
        jpnCampos = null;
        jpnBotoes = null;
        construido = false;
        acaoConfirmar = null;
        acaoCancelar = null;
        antesDeAbrir = null;
        depoisDeAbrir = null;
        antesDeFechar = null;
        depoisDeFechar = null;
    }

    public String pTitulo() {
        return titulo;
    }

    public Intf mTitulo(String para) {
        titulo = para;
        if (janela != null) {
            janela.setTitle(titulo);
        }
        return this;
    }

    public String pDescricao() {
        return descricao;
    }

    public Intf mDescricao(String para) {
        descricao = para;
        if (jtaDescricao != null) {
            jtaDescricao.setText(para);
            janela.pack();
        }
        return this;
    }

    public Campos cmps() {
        return campos;
    }

    public Intf vinculaCts(String nomeEnu, Object valorEnu, String nomePai, String nomeCts) {
        if (vinculosCts == null) {
            vinculosCts = new ArrayList<>();
        }
        vinculosCts.add(new VinculoCts(nomeEnu, valorEnu, nomePai, nomeCts));
        return this;
    }

    public Intf botaAcao(String noCampo, AbstractAction aAcao) {
        Cmp cmp = campos.pega(noCampo);
        if (cmp.pEdt() != null) {
            cmp.pEdt().botaAcao(aAcao);
        } else {
            cmp.mAcao(aAcao);
        }
        return this;
    }

    public List<Botao> botoes() {
        return botoes;
    }

    public Intf botaBotao(Botao oBotao) {
        if (botoes == null) {
            botoes = new Botoes();
        }
        botoes.bota(oBotao);
        return this;
    }

    public Intf botaConfirmar() {
        return botaBotao(new BotConfirmar());
    }

    public Intf botaCancelar() {
        return botaBotao(new BotCancelar());
    }

    public Intf botaConfirmarECancelar() {
        botaBotao(new BotConfirmar());
        return botaBotao(new BotCancelar());
    }

    public AlinhamentoTp pBotoesAlinhamento() {
        return botoesAlinhamento;
    }

    public Intf mBotoesAlinhamento(AlinhamentoTp para) {
        this.botoesAlinhamento = para;
        return this;
    }

    public Intf botaLinhaCampos() {
        mostraCampos = true;
        return this;
    }

    public Intf tiraLinhaCampos() {
        mostraCampos = false;
        return this;
    }

    public Integer pLinhaDescricao() {
        return linhaDescricao;
    }

    public void mLinhaDescricao(Integer para) {
        this.linhaDescricao = para;
    }

    public Integer pLinhaCampos() {
        return linhaCampos;
    }

    public void mLinhaCampos(Integer para) {
        this.linhaCampos = para;
    }

    public Integer pLinhaBotoes() {
        return linhaBotoes;
    }

    public void mLinhaBotoes(Integer para) {
        this.linhaBotoes = para;
    }

    public Intf botaLinha(Integer naLinha, JComponent aLinha) {
        return botaLinha(naLinha, aLinha, PreencherTp.Nenhum, 0, 0);
    }

    public Intf botaLinha(Integer naLinha, JComponent aLinha, PreencherTp preencher, Integer aumentarX, Integer aumentarY) {
        janela.add(aLinha, new GBCWrap(0, naLinha).setAnchor(GridBagConstraints.NORTHWEST).setFill(preencher.pegaCFG()).setWeight(aumentarX, aumentarY));
        return this;
    }

    public Intf tiraLinha(JComponent aLinha) {
        janela.remove(aLinha);
        return this;
    }

    public Intf mDimensao(Integer comLargura, Integer eAltura) {
        return mDimensao(new Dimension(comLargura, eAltura));
    }

    public Intf mDimensao(Dimension dimensao) {
        this.dimensao = dimensao;
        return this;
    }

    public BufferedImage pIcone() {
        return icone;
    }

    public Intf mIcone(BufferedImage icone) {
        this.icone = icone;
        return this;
    }

    public Boolean pDimensionavel() {
        return this.dimensionavel;
    }

    public Intf mDimensionavel(Boolean para) {
        this.dimensionavel = para;
        return this;
    }

    public Intf botaDimensionavel() {
        this.dimensionavel = true;
        return this;
    }

    public Intf tiraDimensionavel() {
        this.dimensionavel = false;
        return this;
    }

    public Boolean pFecharAoConfirmar() {
        return this.fecharAoConfirmar;
    }

    public Intf mFecharAoConfirmar(Boolean para) {
        this.fecharAoConfirmar = para;
        return this;
    }

    public Intf botaFecharAoConfirmar() {
        this.fecharAoConfirmar = true;
        return this;
    }

    public Intf tiraFecharAoConfirmar() {
        this.fecharAoConfirmar = false;
        return this;
    }

    public Boolean pSairAoFechar() {
        return this.sairAoFechar;
    }

    public Intf mSairAoFechar(Boolean para) {
        this.sairAoFechar = para;
        return this;
    }

    public Intf botaSairAoFechar() {
        this.sairAoFechar = true;
        return this;
    }

    public Intf tiraSairAoFechar() {
        this.sairAoFechar = false;
        return this;
    }

    public AbstractAction pAcaoConfirmar() {
        return acaoConfirmar;
    }

    public Intf mAcaoConfirmar(AbstractAction para) {
        this.acaoConfirmar = para;
        return this;
    }

    public AbstractAction pAcaoCancelar() {
        return acaoCancelar;
    }

    public Intf mAcaoCancelar(AbstractAction para) {
        this.acaoCancelar = para;
        return this;
    }

    public Intf botaAntesDeAbrir(Conferencia aConferencia) {
        if (antesDeAbrir == null) {
            antesDeAbrir = new ArrayList<>();
        }
        antesDeAbrir.add(aConferencia);
        return this;
    }

    public Intf tiraAntesDeAbrir(Conferencia aConferencia) {
        if (antesDeAbrir != null) {
            antesDeAbrir.remove(aConferencia);
        }
        return this;
    }

    private void confAntesDeAbrir() throws Exception {
        if (antesDeAbrir != null) {
            for (Conferencia conf : antesDeAbrir) {
                conf.confere(this);
            }
        }
    }

    public Intf botaDepoisDeAbrir(Conferencia aConferencia) {
        if (depoisDeAbrir == null) {
            depoisDeAbrir = new ArrayList<>();
        }
        depoisDeAbrir.add(aConferencia);
        return this;
    }

    public Intf tiraDepoisDeAbrir(Conferencia aConferencia) {
        if (depoisDeAbrir != null) {
            depoisDeAbrir.remove(aConferencia);
        }
        return this;
    }

    private void confDepoisDeAbrir() throws Exception {
        if (depoisDeAbrir != null) {
            for (Conferencia conf : depoisDeAbrir) {
                conf.confere(this);
            }
        }
    }

    public Intf botaAntesDeFechar(Conferencia aConferencia) {
        if (antesDeFechar == null) {
            antesDeFechar = new ArrayList<>();
        }
        antesDeFechar.add(aConferencia);
        return this;
    }

    public Intf tiraAntesDeFechar(Conferencia aConferencia) {
        if (antesDeFechar != null) {
            antesDeFechar.remove(aConferencia);
        }
        return this;
    }

    private void confAntesDeFechar() throws Exception {
        if (antesDeFechar != null) {
            for (Conferencia conf : antesDeFechar) {
                conf.confere(this);
            }
        }
    }

    public Intf botaDepoisDeFechar(Conferencia aConferencia) {
        if (depoisDeFechar == null) {
            depoisDeFechar = new ArrayList<>();
        }
        depoisDeFechar.add(aConferencia);
        return this;
    }

    public Intf tiraDepoisDeFechar(Conferencia aConferencia) {
        if (depoisDeFechar != null) {
            depoisDeFechar.remove(aConferencia);
        }
        return this;
    }

    private void confDepoisDeFechar() throws Exception {
        if (depoisDeFechar != null) {
            for (Conferencia conf : depoisDeFechar) {
                conf.confere(this);
            }
        }
    }

    public void preparaEstr() throws Exception {
    }

    private void constroiEstr() throws Exception {
        if (titulo == null) {
            titulo = "Interface";
        }
    }

    public void especializaEstr() throws Exception {
    }

    public void preparaIntf() throws Exception {
    }

    private void constroiVinculosCts() {
        List<List<VinculoCts>> gruposCts = new ArrayList<>();
        for (VinculoCts vinculo : vinculosCts) {
            boolean achou = false;
            for (List<VinculoCts> grupo : gruposCts) {
                if (Objects.equals(grupo.get(0).nomeEnu, vinculo.nomeEnu)) {
                    grupo.add(vinculo);
                    achou = true;
                    break;
                }
            }
            if (!achou) {
                List<VinculoCts> grupo = new ArrayList<>();
                grupo.add(vinculo);
                gruposCts.add(grupo);
            }
        }
        for (List<VinculoCts> grupo : gruposCts) {
            Edt edtOgn = cmps().pega(grupo.get(0).nomeEnu).pEdt();
            edtOgn.botaConferencia(new Conferencia() {
                @Override
                public void confere(Object comOrigem) throws Exception {
                    Object valor = edtOgn.pgVlr().pg();
                    for (VinculoCts vnc : grupo) {
                        if (Objects.equals(vnc.valorEnu, valor)) {
                            EdtCts edtCts = (EdtCts) cmps().pega(vnc.nomeCts).pEdt();
                            edtCts.mostra(vnc.nomePai);
                        }
                    }
                }
            });
        }
    }

    private void constroiIntf() throws Exception {
        janela = new JFrame(titulo);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel jpnConteudo = new JPanel();
        jpnConteudo.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        janela.setContentPane(jpnConteudo);
        janela.setLayout(new GridBagLayout());
        if (campos.size() > 0) {
            jpnCampos = new Construtor(campos).constroi(this);
            jpnCampos.setMinimumSize(jpnCampos.getPreferredSize());
            jpnCampos.setMaximumSize(jpnCampos.getPreferredSize());
            jpnCampos.setSize(jpnCampos.getPreferredSize());
        }
        if (descricao != null) {
            jtaDescricao = new JTextArea();
            jtaDescricao.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
            jtaDescricao.setBackground(new Color(0, 0, 0, 0));
            jtaDescricao.setOpaque(false);
            jtaDescricao.setFont(UIManager.getFont("Label.font"));
            jtaDescricao.setText(descricao);
            jtaDescricao.setLineWrap(true);
            jtaDescricao.setWrapStyleWord(true);
            jtaDescricao.setEditable(false);
            int larg = 300;
            if (jpnCampos != null) {
                larg = jpnCampos.getPreferredSize().width;
            }
            jtaDescricao.setSize(larg, 30);
            botaLinha(linhaDescricao, jtaDescricao, PreencherTp.Nenhum, 0, 0);
        }
        if (mostraCampos) {
            botaLinha(linhaCampos, jpnCampos, PreencherTp.Horizontal, 1, 0);
        }
        if (vinculosCts != null) {
            constroiVinculosCts();
        }
        boolean temBotao = false;
        if (botoes != null) {
            if (botoes.size() > 0) {
                jpnBotoes = botoes.constroi(botoesAlinhamento, this);
                temBotao = true;
            }
        }
        if (temBotao) {
            botaLinha(linhaBotoes, jpnBotoes, PreencherTp.Horizontal, 1, 0);
            janela.getRootPane().setDefaultButton(botoes.pegaPadrao());
        }
        if (dimensao != null) {
            janela.setPreferredSize(dimensao);
            janela.setSize(dimensao);
        }
        janela.setResizable(dimensionavel);
    }

    public void especializaIntf() throws Exception {
    }

    public JFrame janela() {
        return janela;
    }

    public JTextArea jlbDescricao() {
        return jtaDescricao;
    }

    public JPanel jpnCampos() {
        return jpnCampos;
    }

    public JPanel jpnBotoes() {
        return jpnBotoes;
    }

    public Intf constroi() throws Exception {
        preparaEstr();
        constroiEstr();
        especializaEstr();
        preparaIntf();
        constroiIntf();
        especializaIntf();
        construido = true;
        return this;
    }

    public Intf mostra() throws Exception {
        return mostra(null);
    }

    public Intf mostra(String comTitulo) throws Exception {
        if (!construido) {
            constroi();
        }
        if (comTitulo != null) {
            titulo = comTitulo;
            janela.setTitle(titulo);
        }
        if (icone != null) {
            janela.setIconImage(icone);
        }
        if (sairAoFechar) {
            janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        janela.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    if (antesDeFechar != null) {
                        for (Conferencia adf : antesDeFechar) {
                            adf.confere(this);
                        }
                    }
                    if (sairAoFechar) {
                        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    } else {
                        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    }
                } catch (Exception ex) {
                    Utl.registra(ex);
                    janela.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    if (depoisDeFechar != null) {
                        for (Conferencia adf : depoisDeFechar) {
                            adf.confere(this);
                        }
                    }
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }

        });
        Janelas.inicia(janela);
        confAntesDeAbrir();
        Janelas.mostra(janela, false);
        if (campos.size() > 0) {
            campos.pega(0).pEdt().botaFoco();
        }
        confDepoisDeAbrir();
        return this;
    }

    public void fecha() {
        janela.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        try {
            confAntesDeFechar();
            if (sairAoFechar) {
                janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            } else {
                janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
            Janelas.fecha(janela);
            confDepoisDeFechar();
        } catch (Exception ex) {
            Utl.registra(ex);
        }

    }

    public void aoConfirmar(Object comOrigem) throws Exception {
    }

    public void confirma(Object comOrigem) throws Exception {
        aoConfirmar(comOrigem);
        if (acaoConfirmar != null) {
            acaoConfirmar.actionPerformed(new ActionEvent(comOrigem, 1, "confirma"));
        }
        if (fecharAoConfirmar) {
            fecha();
        }
    }

    public void cancela() throws Exception {
        if (acaoCancelar != null) {
            acaoCancelar.actionPerformed(new ActionEvent(this, -1, "cancela"));
        } else {
            fecha();
        }
    }

    public Intf pedeFoco() {
        TrataIntf.garanteFoco(janela);
        return this;
    }

    private class BotConfirmar extends Botao {

        public BotConfirmar() {
            super("Confirmar", 'C');
            botaPadrao();
        }

        @Override
        public void aoExecutar(Object comOrigem) throws Exception {
            confirma(comOrigem);
        }
    }

    private class BotCancelar extends Botao {

        public BotCancelar() {
            super("Cancelar", 'A');
        }

        @Override
        public void aoExecutar(Object comOrigem) throws Exception {
            cancela();
        }
    }

}
