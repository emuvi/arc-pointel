package pin.modamk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import pin.libamg.EdtCrs;
import pin.libamg.EdtPai;
import pin.libamk.Botao;
import pin.libamk.Campos;
import pin.libamk.Cmp;
import pin.libbas.Conferencia;
import pin.libbas.Configs;
import pin.libbas.Conjunto;
import pin.libbas.Dados;
import pin.libbas.Erro;
import pin.libbas.Globais;
import pin.libjan.Janelas;
import pin.libjan.PopMenu;
import pin.libjan.TrataIntf;
import pin.libout.GBCWrap;
import pin.libpic.Pics;
import pin.libutl.Utl;
import pin.libutl.UtlAle;
import pin.libutl.UtlCrs;
import pin.libutl.UtlIma;
import pin.libutl.UtlNumLon;
import pin.libutl.UtlTab;
import pin.libutl.UtlTex;
import pin.libvlr.Vlr;
import pin.libvlr.Vlrs;
import pin.modinf.CodigosMod;
import pin.modinf.Conexao;
import pin.modpim.Pims;

public abstract class Cadastro {

    public Configs cfgs;
    public Conexao conexao;
    public String nome;
    public String titulo;
    public String icone;
    public String esquema;
    public String tabela;
    public String formatoCodigo;

    public List<Cmp> campos;
    public List<Cmp> camposDeValor;

    public Boolean janelaDeEdicao;
    private Boolean soConsulta;
    private Modo modo;

    public static enum Modo {
        NOVO, PROCURAR, PROCURANDO, EDITAR;
    }

    public CadComandos cadSQL;
    public CadProcurar cadProcurar;
    public CadCarregar cadCarregar;

    public ArrayList<CadRelacao> cadRelacoes;
    public ArrayList<CadReferenciar> cadReferenciar;
    public ArrayList<Botao> cadBotoes;

    public CadReferenciar cadReferenciado;
    public Boolean ehReferencia;

    public ArrayList<Conferencia> antesDeAbrir;
    public ArrayList<Conferencia> depoisDeAbrir;
    public ArrayList<Conferencia> antesDeInserir;
    public ArrayList<Conferencia> antesDeProcurar;
    public ArrayList<Conferencia> antesDeEditar;
    public ArrayList<Conferencia> antesDeExcluir;
    public ArrayList<Conferencia> depoisDeExcluir;
    public ArrayList<Conferencia> antesDeSalvar;
    public ArrayList<Conferencia> depoisDeSalvar;
    public ArrayList<Conferencia> antesDeFechar;
    public ArrayList<Conferencia> depoisDeFechar;

    public ArrayList<String> deveAtualizar;

    public Object[] chavesUltimoSalvo;

    public JFrame janela;
    public JPanel jpnJanela;
    public CadBarra cadBarra;
    public CadBarraRef cadBarraRef;
    public EdtPai jpnCampos;
    public JPanel jpnLista;
    public JPanel jpnStatus;
    public JPanel jpnAcoes;
    public JButton jbtConfirmarDoStatus;
    public String status;
    public JLabel jlbStatus;
    public DefaultTableModel dtmLista;
    public JTable jtbLista;
    public JScrollPane jspLista;
    public PopMenu menuLista;

    public Cadastro(String aNome, String aTitulo, Cmp[] aCampos) throws Exception {
        this(aNome, aTitulo, aNome, aCampos);
    }

    public Cadastro(Conexao naConexao, String aNome, String aTitulo, Cmp[] aCampos) throws Exception {
        this(naConexao, aNome, aTitulo, aNome, aCampos);
    }

    public Cadastro(String aNome, String aTitulo, String aTabela, Cmp[] aCampos) throws Exception {
        this(((Conexao) Globais.pega("mainConc")), aNome, aTitulo, aTabela, aCampos);
    }

    public Cadastro(Conexao naConexao, String aNome, String aTitulo, String aTabela, Cmp[] aCampos) throws Exception {
        cfgs = ((Configs) Globais.pega("mainConf"));
        conexao = naConexao;
        nome = aNome;
        titulo = aTitulo;
        icone = null;
        tabela = aTabela;
        formatoCodigo = null;
        janelaDeEdicao = false;
        cadBarra = null;
        cadBarraRef = null;
        jpnCampos = null;
        jpnLista = null;
        jpnStatus = null;
        jpnAcoes = null;
        jlbStatus = null;
        status = "";
        cadSQL = null;
        cadProcurar = null;
        cadCarregar = null;
        cadRelacoes = null;
        cadReferenciar = null;
        cadBotoes = null;
        cadReferenciado = null;
        ehReferencia = false;
        antesDeAbrir = null;
        depoisDeAbrir = null;
        antesDeExcluir = null;
        depoisDeExcluir = null;
        antesDeSalvar = null;
        depoisDeSalvar = null;
        antesDeFechar = null;
        deveAtualizar = null;
        campos = new ArrayList<>(Arrays.asList(aCampos));
        soConsulta = false;
        preparaEstr();
        especializaEstr();
        camposDeValor = new ArrayList<>();
        for (Cmp cmp : campos) {
            if (cmp.pTipo().pValor()) {
                camposDeValor.add(cmp);
            }
        }
    }

    public Cadastro mudaIcone(String para) {
        icone = para;
        return this;
    }

    public void removeCampo(String comNome) {
        if (comNome != null) {
            for (int ic = campos.size() - 1; ic >= 0; ic--) {
                if (comNome.equals(campos.get(ic).pNome())) {
                    campos.remove(ic);
                }
            }
        }

    }

    public void removeCampos(String daAba) {
        if (daAba != null) {
            for (int ic = campos.size() - 1; ic >= 0; ic--) {
                if (daAba.equals(campos.get(ic).pPai())) {
                    campos.remove(ic);
                }
            }
        }

    }

    public String[] pCamposValorTitulos() {
        List<String> titulos = new ArrayList<>();
        for (int i1 = 0; i1 < campos.size(); i1++) {
            if (campos.get(i1).pTipo().pValor()) {
                titulos.add(campos.get(i1).pTitulo());
            }
        }
        return titulos.toArray(new String[0]);
    }

    public Cmp pegaCampo(String aNome) {
        Cmp retorno = null;
        for (int i1 = 0; i1 < campos.size(); i1++) {
            if (campos.get(i1).pNome().equals(aNome)) {
                retorno = campos.get(i1);
                break;
            }
        }
        return retorno;
    }

    public Cmp pegaCampo(Component aEditor) {
        Cmp retorno = null;
        for (Cmp cmp : campos) {
            if (cmp.pEdt().controle().equals(aEditor)) {
                retorno = cmp;
                break;
            }
        }
        return retorno;
    }

    public Cmp[] pegaChaves() {
        Cmp[] retorno = null;
        ArrayList<Cmp> chaves = new ArrayList<>();
        for (int i1 = 0; i1 < campos.size(); i1++) {
            if (campos.get(i1).pDetalhe("chave", false)) {
                chaves.add(campos.get(i1));
            }
        }
        retorno = new Cmp[chaves.size()];
        for (int i1 = 0; i1 < chaves.size(); i1++) {
            retorno[i1] = chaves.get(i1);
        }
        return retorno;
    }

    public List<Integer> pegaChavesIndices() {
        ArrayList<Integer> chaves = new ArrayList<>();
        for (int ic = 0; ic < camposDeValor.size(); ic++) {
            if (camposDeValor.get(ic).pDetalhe("chave", false)) {
                chaves.add(ic);
            }
        }
        return chaves;
    }

    public Object[] pegaChavesValores() throws Exception {
        Object[] retorno = null;
        ArrayList<Object> chaves = new ArrayList<>();
        for (int i1 = 0; i1 < camposDeValor.size(); i1++) {
            if (camposDeValor.get(i1).pDetalhe("chave", false)) {
                chaves.add(camposDeValor.get(i1).pEdt().pgVlr());
            }
        }
        retorno = new Object[chaves.size()];
        for (int i1 = 0; i1 < chaves.size(); i1++) {
            retorno[i1] = chaves.get(i1);
        }
        return retorno;
    }

    public Object[] pegaCamposValores() throws Exception {
        Object[] retorno = new Object[camposDeValor.size()];
        for (int i1 = 0; i1 < camposDeValor.size(); i1++) {
            retorno[i1] = camposDeValor.get(i1).pEdt().pgVlr();
        }
        return retorno;
    }

    public String pegaCampoNome(String comTitulo) {
        String retorno = "";
        for (int i1 = 0; i1 < campos.size(); i1++) {
            if (Objects.equals(campos.get(i1).pTitulo(), comTitulo)) {
                retorno = campos.get(i1).pNome();
                break;
            }
        }
        return retorno;
    }

    public Integer pegaCampoIndice(String aNome) {
        Integer retorno = -1;
        for (int i1 = 0; i1 < campos.size(); i1++) {
            if (Objects.equals(campos.get(i1).pNome(), aNome)) {
                retorno = i1;
            }
        }
        return retorno;
    }

    public Integer pegaCampoValorIndice(String aNome) {
        Integer retorno = -1;
        for (int i1 = 0; i1 < camposDeValor.size(); i1++) {
            if (Objects.equals(camposDeValor.get(i1).pNome(), aNome)) {
                retorno = i1;
            }
        }
        return retorno;
    }

    public Cmp pegaCampoTitulo(String aTitulo) {
        Cmp retorno = null;
        for (int i1 = 0; i1 < campos.size(); i1++) {
            if (Objects.equals(campos.get(i1).pTitulo(), aTitulo)) {
                retorno = campos.get(i1);
                break;
            }
        }
        return retorno;
    }

    public String pegaCampoNomeCompostoTitulo(String aTitulo) {
        String retorno = null;
        for (int i1 = 0; i1 < campos.size(); i1++) {
            if (Objects.equals(campos.get(i1).pTitulo(), aTitulo)) {
                retorno = (campos.get(i1).pNome().contains(".") ? "" : tabela + ".") + campos.get(i1).pNome();
                break;
            }
        }
        return retorno;
    }

    public Integer pegaChavesQuantidade() {
        Integer retorno = 0;
        for (int i1 = 0; i1 < campos.size(); i1++) {
            if (campos.get(i1).pDetalhe("chave", false)) {
                retorno++;
            }
        }
        return retorno;
    }

    public void limparCampos() throws Exception {
        if (!ehReferencia) {
            for (Cmp cmp : camposDeValor) {
                cmp.mVlrSalvo(null);
                if (cmp.pVlrFixo() != null) {
                    cmp.pEdt().mdVlr(cmp.pVlrFixo());
                } else {
                    cmp.pEdt().mdVlr(cmp.pVlrInicial());
                }
            }
            botaFocoNoPrimeiroEditavel();
        }
    }

    public void botaFocoNoPrimeiroEditavel() {
        for (Cmp cmp : camposDeValor) {
            if (cmp.pEdt().editavel() && !cmp.pDetalhe("chave", false)) {
                TrataIntf.garanteFoco(cmp.pEdt().controle());
                break;
            }
        }
    }

    public void mudaCampoTituloValor(String aTitulo, Object aValor) throws Exception {
        Cmp cmp = pegaCampoTitulo(aTitulo);
        cmp.mVlrSalvo(aValor);
        cmp.pEdt().mdVlr(aValor);
    }

    public void mudaChavesEditavel(Boolean para) {
        for (Cmp cmp : camposDeValor) {
            if (cmp.pDetalhe("chave", false)) {
                cmp.pEdt().mEditavel(false);
                cmp.pEdt().mFocavel(false);
                if ((!cmp.pDetalhe("estrangeiro", false)) && (cmp.pVlrFixo() == null)) {
                    if (ehModoNovo()) {
                        if (cmp.pDetalhe("podeInserir", true)) {
                            cmp.pEdt().mEditavel(para);
                            cmp.pEdt().mFocavel(para);
                        }
                    } else if (ehModoEditar()) {
                        if (cmp.pDetalhe("podeEditar", true)) {
                            cmp.pEdt().mEditavel(para);
                            cmp.pEdt().mFocavel(para);
                        }
                    }
                }
            }
        }
    }

    public void mudaCamposEditavel(Boolean para) {
        for (Cmp cmp : camposDeValor) {
            cmp.pEdt().mEditavel(false);
            cmp.pEdt().mFocavel(false);
            if ((!cmp.pDetalhe("estrangeiro", false)) && (cmp.pVlrFixo() == null)) {
                if (ehModoNovo()) {
                    if (cmp.pDetalhe("podeInserir", true)) {
                        cmp.pEdt().mEditavel(para);
                        cmp.pEdt().mFocavel(para);
                    }
                } else if (ehModoEditar()) {
                    if (cmp.pDetalhe("podeEditar", true)) {
                        cmp.pEdt().mEditavel(para);
                        cmp.pEdt().mFocavel(para);
                    }
                }
            }
        }
    }

    public void mudaValor(Object[] comChavesVlrs, String doCampo, Object paraValor) throws Exception {
        List<Integer> icvs = pegaChavesIndices();
        Integer icmp = pegaCampoValorIndice(doCampo);
        boolean estaExibido = true;
        for (int ie = 0; ie < comChavesVlrs.length; ie++) {
            Object esperado = Vlr.pNativo(comChavesVlrs[ie]);
            Object encontrado = Vlr.pNativo(camposDeValor.get(icvs.get(ie)).pEdt().pgVlr());
            if (!Objects.equals(esperado, encontrado)) {
                estaExibido = false;
                break;
            }
        }
        if (estaExibido) {
            camposDeValor.get(icmp).pEdt().mdVlr(paraValor);
            cadCarregar.ultimoSelecionadoValores[icmp] = paraValor;
        }
        for (int il = 0; il < dtmLista.getRowCount(); il++) {
            boolean ehLinha = true;
            for (int ie = 0; ie < comChavesVlrs.length; ie++) {
                Object esperado = Vlr.pNativo(comChavesVlrs[ie]);
                Object encontrado = Vlr.pNativo(dtmLista.getValueAt(il, icvs.get(ie)));
                if (!Objects.equals(encontrado, esperado)) {
                    ehLinha = false;
                    break;
                }
            }
            if (ehLinha) {
                dtmLista.setValueAt(paraValor, il, icmp);
                break;
            }
        }

    }

    public Boolean ehCampoTituloChave(String aTitulo) {
        Cmp cmp = pegaCampoTitulo(aTitulo);
        return cmp.pDetalhe("chave", false);
    }

    public void botaBotao(Botao aAcao) {
        if (cadBotoes == null) {
            cadBotoes = new ArrayList<>();
        }
        cadBotoes.add(aAcao);
    }

    public Botao pegaBotao(String comTitulo) {
        if (cadBotoes == null) {
            for (Botao bot : cadBotoes) {
                if (Objects.equals(bot.pTitulo(), comTitulo)) {
                    return bot;
                }
            }
        }
        return null;
    }

    public void removeBotao(String comTitulo) {
        if (cadBotoes != null) {
            for (Botao bot : cadBotoes) {
                if (Objects.equals(bot.pTitulo(), comTitulo)) {
                    cadBotoes.remove(bot);
                }
            }
            if (cadBotoes.isEmpty()) {
                cadBotoes = null;
            }
        }
    }

    public Cadastro limpaBotoes() {
        if (cadBotoes != null) {
            cadBotoes.clear();
        }
        cadBotoes = null;
        return this;
    }

    public CadReferenciar pegaReferencia(String aReferenciado) {
        CadReferenciar retorno = null;
        for (CadReferenciar ref : cadReferenciar) {
            if (ref.selecionado.equals(aReferenciado)) {
                retorno = ref;
                break;
            }
        }
        return retorno;
    }

    public Cadastro botaRelacao(CadRelacao aEstrangeiro) {
        if (cadRelacoes == null) {
            cadRelacoes = new ArrayList<>();
        }
        cadRelacoes.add(aEstrangeiro);
        return this;
    }

    public Cadastro botaReferencia(CadReferenciar aReferenciar) {
        if (cadReferenciar == null) {
            cadReferenciar = new ArrayList<>();
        }
        cadReferenciar.add(aReferenciar);
        return this;
    }

    public void trocaReferencia(Class daClasse, Class paraClasse) {
        if (cadReferenciar != null) {
            for (CadReferenciar cadRef : cadReferenciar) {
                if (daClasse.equals(cadRef.buscar)) {
                    cadRef.buscar = paraClasse;
                }
            }
        }
    }

    public Cadastro botaAntesDeAbrir(Conferencia aAcao) {
        if (antesDeAbrir == null) {
            antesDeAbrir = new ArrayList<>();
        }
        antesDeAbrir.add(aAcao);
        return this;
    }

    public Cadastro botaDepoisDeAbrir(Conferencia aAcao) {
        if (depoisDeAbrir == null) {
            depoisDeAbrir = new ArrayList<>();
        }
        depoisDeAbrir.add(aAcao);
        return this;
    }

    public Cadastro botaAntesDeInserir(Conferencia aAcao) {
        if (antesDeInserir == null) {
            antesDeInserir = new ArrayList<>();
        }
        antesDeInserir.add(aAcao);
        return this;
    }

    public Cadastro botaAntesDeProcurar(Conferencia aAcao) {
        if (antesDeProcurar == null) {
            antesDeProcurar = new ArrayList<>();
        }
        antesDeProcurar.add(aAcao);
        return this;
    }

    public Cadastro botaAntesDeEditar(Conferencia aAcao) {
        if (antesDeEditar == null) {
            antesDeEditar = new ArrayList<>();
        }
        antesDeEditar.add(aAcao);
        return this;
    }

    public Cadastro botaAntesDeExcluir(Conferencia aAcao) {
        if (antesDeExcluir == null) {
            antesDeExcluir = new ArrayList<>();
        }
        antesDeExcluir.add(aAcao);
        return this;
    }

    public Cadastro botaDepoisDeExcluir(Conferencia aAcao) {
        if (depoisDeExcluir == null) {
            depoisDeExcluir = new ArrayList<>();
        }
        depoisDeExcluir.add(aAcao);
        return this;
    }

    public Cadastro botaAntesDeSalvar(Conferencia aAcao) {
        if (antesDeSalvar == null) {
            antesDeSalvar = new ArrayList<>();
        }
        antesDeSalvar.add(aAcao);
        return this;
    }

    public Cadastro botaDepoisDeSalvar(Conferencia aAcao) {
        if (depoisDeSalvar == null) {
            depoisDeSalvar = new ArrayList<>();
        }
        depoisDeSalvar.add(aAcao);
        return this;
    }

    public Cadastro botaAntesDeFechar(Conferencia aAcao) {
        if (antesDeFechar == null) {
            antesDeFechar = new ArrayList<>();
        }
        antesDeFechar.add(aAcao);
        return this;
    }

    public Cadastro botaDepoisDeFechar(Conferencia aAcao) {
        if (depoisDeFechar == null) {
            depoisDeFechar = new ArrayList<>();
        }
        depoisDeFechar.add(aAcao);
        return this;
    }

    public Boolean estaParaAtualizar(String oCampo) {
        Boolean retorno = false;
        if (deveAtualizar != null) {
            if (deveAtualizar.indexOf(oCampo) > -1) {
                retorno = true;
            }
        }
        return retorno;
    }

    public void botaParaAtualizar(String oCampo) {
        if (deveAtualizar == null) {
            deveAtualizar = new ArrayList<>();
        }

        deveAtualizar.add(oCampo);
    }

    public Boolean ehSoConsulta() {
        return soConsulta;
    }

    public Cadastro botaSoConsulta() {
        soConsulta = true;
        return this;
    }

    public Cadastro tiraSoConsulta() {
        soConsulta = false;
        return this;
    }

    public String pegaCriacaoTabela() throws Exception {
        String sqlCria = "CREATE TABLE IF NOT EXISTS " + tabela + " (";

        for (Cmp cmp : campos) {
            if (!cmp.pDetalhe("estrangeiro", false)) {
                sqlCria += "\n  ";
                sqlCria += conexao.pCriacao(cmp);
                sqlCria += ", ";
            }
        }

        sqlCria += " \n  PRIMARY KEY (";
        for (Cmp cmp : campos) {
            if (cmp.pDetalhe("chave", false)) {
                sqlCria += cmp.pNome() + ", ";
            }
        }

        sqlCria = UtlCrs.corta(sqlCria, 2);
        sqlCria += ")";

        if (cadRelacoes != null) {
            for (CadRelacao ces : cadRelacoes) {
                sqlCria += " , \n  FOREIGN KEY (";
                for (CmpRelacao rel : ces.relacoes()) {
                    sqlCria += rel.pegaReferenciado() + ", ";
                }
                sqlCria = UtlCrs.corta(sqlCria, 2) + ") REFERENCES " + ces.pegaEsquemaETabela() + " (";
                for (CmpRelacao rel : ces.relacoes()) {
                    sqlCria += rel.pegaReferencia() + ", ";
                }
                sqlCria = UtlCrs.corta(sqlCria, 2) + ")";
            }
        }

        sqlCria += " \n)";

        return sqlCria;
    }

    public Boolean criaTabela() throws Exception {
        return criaTabela(false);
    }

    public Boolean criaTabela(Boolean forcar) throws Exception {
        if (forcar) {
            try {
                if (!conexao.operaLog("DROP TABLE " + tabela)) {
                    return false;
                }
            } catch (Exception ex) {
                throw ex;
            }
        }
        String sqlCria = pegaCriacaoTabela();
        return conexao.operaLog(sqlCria);
    }

    public void preparaEstr() throws Exception {
    }

    public void especializaEstr() throws Exception {
    }

    public void preparaIntf() throws Exception {
    }

    public void especializaIntf() throws Exception {
    }

    public void mostra() throws Exception {
        mostra(false, false);
    }

    public void mostra(Boolean seleciona) throws Exception {
        mostra(seleciona, false);
    }

    public void mostra(Boolean seleciona, Boolean edita) throws Exception {
        preparaIntf();
        if (antesDeAbrir != null) {
            for (Conferencia cnf : antesDeAbrir) {
                cnf.confere(this);
            }
        }
        janelaDeEdicao = edita;
        janela = new JFrame(titulo + (janelaDeEdicao ? " - Edição" : ""));
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        if (icone != null) {
            try {
                janela.setIconImage(Pims.pega(icone).getImage());
            } catch (Exception ex) {
                Utl.registra(ex, false);
            }
        }
        janela.setResizable(true);
        jpnJanela = new JPanel();
        jpnJanela.setLayout(new GridBagLayout());
        jpnJanela.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        janela.setContentPane(jpnJanela);
        criaBarra();
        criaCampos();
        criaAcoes();
        criaStatus();
        criaLista();
        if (!janelaDeEdicao) {
            jpnJanela.add(cadBarra, new GBCWrap(0, 0).setAnchor(GridBagConstraints.NORTHWEST).setFill(GridBagConstraints.HORIZONTAL).setWeight(1, 0));
            jpnJanela.add(jpnCampos, new GBCWrap(0, 1).setAnchor(GridBagConstraints.NORTHWEST).setFill(GridBagConstraints.HORIZONTAL).setWeight(1, 0));
            jpnJanela.add(jpnStatus, new GBCWrap(0, 2).setAnchor(GridBagConstraints.NORTHWEST).setFill(GridBagConstraints.HORIZONTAL).setWeight(1, 0));
            jpnJanela.add(jpnLista, new GBCWrap(0, 3).setAnchor(GridBagConstraints.NORTHWEST).setFill(GridBagConstraints.BOTH).setWeight(1, 1));
        } else {
            jpnJanela.add(jpnCampos, new GBCWrap(0, 0).setAnchor(GridBagConstraints.NORTHWEST).setFill(GridBagConstraints.HORIZONTAL).setWeight(1, 0));
            jpnJanela.add(jpnStatus, new GBCWrap(0, 1).setAnchor(GridBagConstraints.NORTHWEST).setFill(GridBagConstraints.HORIZONTAL).setWeight(1, 0));
        }
        janela.setResizable(!janelaDeEdicao);
        especializaIntf();
        cadSQL = new CadComandos(this);
        cadCarregar = new CadCarregar(this);
        if (seleciona || janelaDeEdicao) {
            cadCarregar.selecionaPrimeiro();
        }
        cadCarregar.start();
        janela.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                verificaAtualizacoes();
            }

            @Override
            public void windowClosing(WindowEvent e) {
                Boolean tudoCerto = true;
                if (antesDeFechar != null) {
                    for (Conferencia cnf : antesDeFechar) {
                        try {
                            cnf.confere(this);
                        } catch (Exception ex) {
                            Utl.registra(ex);
                            tudoCerto = false;
                            break;
                        }
                    }
                }
                if (!tudoCerto) {
                    janela.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                } else {
                    janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    cadCarregar.parar();
                    cadCarregar.terminar();
                }
            }
        });
        criaAtalhos();
        if (janelaDeEdicao) {
            botaModoProcurando(false, false);
        } else {
            botaModoNovo(false, true);
        }
        mStatus("");
        janela.getRootPane().setDefaultButton(jbtConfirmarDoStatus);
        if (depoisDeAbrir != null) {
            for (Conferencia act : depoisDeAbrir) {
                act.confere(this);
            }
        }
        Janelas.inicia(janela, Cadastro.this);
        Janelas.mostra(janela);
    }

    public Cadastro mostraReferenciador(CadReferenciar daReferencia) throws Exception {
        return mostraReferenciador(daReferencia, null);
    }

    public Cadastro mostraReferenciador(CadReferenciar daReferencia, Vlr contendo) throws Exception {
        for (Cmp cmp : campos) {
            if (Dados.Tp.Log.equals(cmp.pTipo()) && "ativo".equals(cmp.pNome())) {
                cmp.mVlrFixo("S");
            }
        }

        boolean realizaProcura = false;
        if (contendo != null) {
            if (cadProcurar == null) {
                cadProcurar = new CadProcurar(this);
            }
            Dados.Tp tp = Dados.pega(contendo);
            if (tp != null) {
                String val = UtlCrs.pega(contendo, "");
                if (!val.isEmpty()) {
                    cadProcurar.limpaCompletamente();
                    for (Cmp cmp : campos) {
                        if (Objects.equals(tp, cmp.pTipo())) {
                            CadProcurarCmp pcmp = new CadProcurarCmp(cadProcurar, true);
                            pcmp.mCampo(cmp.pTitulo());
                            if (Dados.Tp.Crs.equals(tp)) {
                                pcmp.mCondicao("Contendo");
                            } else {
                                pcmp.mCondicao("Igual");
                            }
                            pcmp.mValor(val);
                            pcmp.mMais("Ou");
                            cadProcurar.camposAdicionar(null, pcmp);
                            realizaProcura = true;
                        }
                    }
                    if (!realizaProcura) {
                        cadProcurar.limpa();
                    }
                }
            }
        }

        cadReferenciado = daReferencia;
        ehReferencia = true;

        janela = new JFrame(titulo + " - Procurando Referência");
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela.setResizable(true);

        jpnJanela = new JPanel();
        jpnJanela.setLayout(new GridBagLayout());
        jpnJanela.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        janela.setContentPane(jpnJanela);

        for (CmpRelacao crl : cadReferenciado.relacoes) {
            if (crl.ehFixo()) {
                if (cadReferenciado.cadastro != null) {
                    pegaCampo(crl.pegaReferencia()).mVlrFixo(cadReferenciado.cadastro.pegaCampo(crl.pegaReferenciado()).pEdt().pgVlr());
                } else if (cadReferenciado.relatorio != null) {
                    pegaCampo(crl.pegaReferencia()).mVlrFixo(cadReferenciado.relatorio.pegaCampo(crl.pegaReferenciado()).editor.pgVlr());
                }
            }
        }
        criaLista();
        jpnLista.setPreferredSize(new Dimension(600, 400));
        jpnJanela.add(jpnLista, new GBCWrap(0, 0).setAnchor(GridBagConstraints.NORTHWEST).setFill(GridBagConstraints.BOTH).setWeight(1, 1));
        criaBarraRef();
        jpnJanela.add(cadBarraRef, new GBCWrap(0, 1).setAnchor(GridBagConstraints.NORTHWEST).setFill(GridBagConstraints.HORIZONTAL).setWeight(1, 0));
        janela.setResizable(true);
        cadSQL = new CadComandos(this);
        cadCarregar = new CadCarregar(this);
        cadCarregar.start();
        if (realizaProcura) {
            cadProcurar.realiza();
        }
        janela.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cadCarregar.parar();
                cadCarregar.terminar();
            }
        });
        criarAtalhosReferencia();
        janela.getRootPane().setDefaultButton(cadBarraRef.pJbtConfirmar());
        Janelas.inicia(janela, Cadastro.this);
        Janelas.mostra(janela);
        return this;
    }

    private void criaBarra() throws Exception {
        cadBarra = new CadBarra(this);
    }

    private void criaBarraRef() throws Exception {
        cadBarraRef = new CadBarraRef(this);
    }

    private void criaCampos() throws Exception {
        jpnCampos = new Campos(campos).constroi(this);
        for (Cmp cmp : camposDeValor) {
            if (cmp.pDetalhe("chave", false)) {
                cmp.pEdt().mObrigatorio(true);
            }
        }
        criaEstrangeiros();
    }

    private void criaEstrangeiros() throws Exception {
        if (cadRelacoes != null) {
            for (Cmp cmp : camposDeValor) {
                if (cmp.pDetalhe("estrangeiro", false)) {
                    String tabNome = cmp.pNome().split("\\.")[0];
                    botaEstrangeiroParaAtualizar(tabNome, cmp);
                }
            }
        }
    }

    private void criaAcoes() throws Exception {
        if (cadBotoes == null) {
            cadBotoes = new ArrayList<>();
        }
        cadBotoes.add(0, new Botao("Confirmar", 'C', Pics.pegaConfirma()) {
            @Override
            public void aoExecutar(Object comOrigem) {
                if (estaHabilitado()) {
                    try {
                        confirmar();
                    } catch (Exception ex) {
                        Utl.registra(ex);
                    }
                }
            }
        });
        if (jpnAcoes == null) {
            jpnAcoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 4));
        }
        for (Botao botao : cadBotoes) {
            if (botao.temOrdem()) {
                jpnAcoes.add(botao.cria(this), botao.pOrdem().intValue());
            } else {
                jpnAcoes.add(botao.cria(this));
            }
        }
        jbtConfirmarDoStatus = cadBotoes.get(0).pControle();
    }

    private String pStatusModo() {
        String retorno = " | ";
        if (modo == Modo.NOVO) {
            retorno = retorno + "Inserindo";
        } else if (modo == Modo.EDITAR) {
            retorno = retorno + "Editando";
        } else {
            retorno = retorno + "Procurando";
        }
        return retorno;
    }

    private void mStatus(String para) {
        status = para;
        jlbStatus.setText(status + " " + pStatusModo() + "...");
    }

    private void criaStatus() {
        jpnStatus = new JPanel(new BorderLayout(4, 4));
        jpnStatus.add(jpnAcoes, BorderLayout.CENTER);
        jlbStatus = new JLabel("Status...");
        jlbStatus.setForeground(Color.RED);
        jlbStatus.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 11));
        jpnStatus.add(jlbStatus, BorderLayout.EAST);
    }

    private class ListaRolamento implements AdjustmentListener {

        public void adjustmentValueChanged(AdjustmentEvent evt) {
            if (evt.getValueIsAdjusting()) {
                return;
            }
            if (dtmLista.getRowCount() > 0 && dtmLista.getRowCount() >= cadSQL.selectLimite) {
                int extent = jspLista.getVerticalScrollBar().getModel().getExtent();
                if (jspLista.getVerticalScrollBar().getValue() + extent >= jspLista.getVerticalScrollBar().getMaximum()) {
                    cadCarregar.continuar();
                }
            }
        }

    }

    private void criaLista() {
        jpnLista = new JPanel();
        jpnLista.setLayout(new BorderLayout());
        jpnLista.setPreferredSize(new Dimension(400, 40));
        jtbLista = new JTable();
        dtmLista = UtlTab.fazTabela(jtbLista, janela, pCamposValorTitulos());
        jspLista = new JScrollPane(jtbLista);
        jpnLista.add(jspLista, BorderLayout.CENTER);
        AdjustmentListener listaRol = new ListaRolamento();
        jspLista.getVerticalScrollBar().addAdjustmentListener(listaRol);
        jtbLista.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (e.getClickCount() > 1) {
                        try {
                            mudaSelecao();
                        } catch (Exception ex) {
                            Utl.registra(ex);
                        }
                    }
                }
            }
        });
        jtbLista.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getExtendedKeyCode() == KeyEvent.VK_SPACE) {
                    try {
                        mudaSelecao();
                    } catch (Exception ex) {
                        Utl.registra(ex);
                    }
                }
            }
        });
        menuLista = new PopMenu();
        menuLista.bota("Aleatório", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int sel = UtlAle.pegaInt(0, dtmLista.getRowCount());
                    jtbLista.setRowSelectionInterval(sel, sel);
                    TrataIntf.posicionaNaSelecao(jtbLista, jspLista);
                    mudaSelecao();
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        });
        menuLista.bota("Copiar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ir = jtbLista.getSelectedRow();
                int ic = jtbLista.getSelectedColumn();
                if (ir > -1 && ic > -1) {
                    UtlCrs.copiaParaTransferencia(UtlCrs.pega(dtmLista.getValueAt(ir, ic)));
                }
            }
        });
        menuLista.bota("Somar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ic = jtbLista.getSelectedColumn();
                Double soma = 0.0;
                for (int il = 0; il < dtmLista.getRowCount(); il++) {
                    soma = soma + UtlNumLon.pega(dtmLista.getValueAt(il, ic), 0.0);
                }
                Utl.alerta("Soma de " + campos.get(ic).pTitulo() + " = " + UtlNumLon.formata(soma));
            }
        });
        menuLista.bota("Parar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadCarregar.parar();
            }
        });
        menuLista.bota("Carregar");
        menuLista.bota("Carregar", "Tudo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadCarregar.carregaTudo();
            }
        });
        menuLista.bota("Carregar", "Partes", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadCarregar.carregaPartes();
            }
        });
        menuLista.coloca(jtbLista, false);
        menuLista.coloca(jspLista, false);
    }

    private void criaAtalhos() {
        if (ehReferencia) {
            return;
        }
        Janelas.botaAtalho(janela, "Cadastro Modo Inserir", "alt INSERT", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cadBarra.pJbtNovo().isEnabled()) {
                    TrataIntf.aciona(cadBarra.pJbtNovo(), e);
                }
            }
        });

        Janelas.botaAtalho(janela, "Cadastro Modo Procurar", "F3", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cadBarra.pJbtProcurar().isEnabled()) {
                    TrataIntf.aciona(cadBarra.pJbtProcurar(), e);
                }
            }
        });

        Janelas.botaAtalho(janela, "Cadastro Modo Editar", "F4", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cadBarra.pJbtEditar().isEnabled()) {
                    TrataIntf.aciona(cadBarra.pJbtEditar(), e);
                }
            }
        });

        Janelas.botaAtalho(janela, "Cadastro Buscar Referencia", "F5", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ehModoNovoOuEditar()) {
                    buscarReferencia();
                }
            }
        });

        Janelas.botaAtalho(janela, "Cadastro Buscar Referencia Contendo", "F6", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ehModoNovoOuEditar()) {
                    buscarReferencia(true);
                }
            }
        });

        Janelas.botaAtalho(janela, "Cadastro Criar Referencia Edição", "F7", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarReferenciaEdicao();
            }
        });

        Janelas.botaAtalho(janela, "Cadastro Criar Referencia", "F8", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarReferencia();
            }
        });

        Janelas.botaAtalho(janela, "Cadastro Excluir", "alt DELETE", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cadBarra.pJbtExcluir().isEnabled()) {
                    TrataIntf.aciona(cadBarra.pJbtExcluir(), e);
                }
            }
        });

        Janelas.botaAtalho(janela, "Cadastro Atualizar", "alt F5", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cadBarra.pJbtAtualizar().isEnabled()) {
                    TrataIntf.aciona(cadBarra.pJbtAtualizar(), e);
                }
            }
        });

        Janelas.botaAtalho(janela, "Cadastro Primeiro", "alt HOME", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cadBarra.pJbtPrimeiro().isEnabled()) {
                    TrataIntf.aciona(cadBarra.pJbtPrimeiro(), e);
                }
            }
        });

        Janelas.botaAtalho(janela, "Cadastro Anterior", "alt PAGE_UP", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cadBarra.pJbtAnterior().isEnabled()) {
                    TrataIntf.aciona(cadBarra.pJbtAnterior(), e);
                }
            }
        });

        Janelas.botaAtalho(janela, "Cadastro Próximo", "alt PAGE_DOWN", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cadBarra.pJbtProximo().isEnabled()) {
                    TrataIntf.aciona(cadBarra.pJbtProximo(), e);
                }
            }
        });

        Janelas.botaAtalho(janela, "Cadastro Último", "alt END", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cadBarra.pJbtUltimo().isEnabled()) {
                    TrataIntf.aciona(cadBarra.pJbtUltimo(), e);
                }
            }
        });

        Janelas.botaAtalho(janela, "Cadastro Confirmar", "alt ENTER", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cadBarra.pJbtConfirmar().isEnabled()) {
                    TrataIntf.aciona(cadBarra.pJbtConfirmar(), e);
                }
            }
        });

        Janelas.botaAtalho(janela, "Cadastro Cancelar", "alt F1", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cadBarra.pJbtCancelar().isEnabled()) {
                    TrataIntf.aciona(cadBarra.pJbtCancelar(), e);
                }
            }
        });

        Janelas.botaAtalho(janela, "Cadastro Tamanho", "shift control alt T", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Utl.alerta(
                        "Janela Tamanhos" + UtlTex.quebra()
                        + "Largura: " + janela.getWidth() + UtlTex.quebra()
                        + "Altura: " + janela.getHeight()
                );
            }
        });

    }

    private void criarAtalhosReferencia() {
        if (ehReferencia) {
            return;
        }
        Janelas.botaAtalho(janela, "Cadastro Modo Procurar", "F4", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cadBarraRef.pJbtProcurar().isEnabled()) {
                    TrataIntf.aciona(cadBarraRef.pJbtProcurar(), e);
                }
            }
        });

        Janelas.botaAtalho(janela, "Cadastro Confirmar", "alt ENTER", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cadBarraRef.pJbtConfirmar().isEnabled()) {
                    TrataIntf.aciona(cadBarraRef.pJbtConfirmar(), e);
                }
            }
        });

        Janelas.botaAtalho(janela, "Cadastro Cancelar", "alt F1", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cadBarraRef.pJbtCancelar().isEnabled()) {
                    TrataIntf.aciona(cadBarraRef.pJbtCancelar(), e);
                }
            }
        });

    }

    private void botaEstrangeiroParaAtualizar(String daTabela, Cmp oCampo) throws Exception {
        for (CadRelacao est : cadRelacoes) {
            if (est.pegaNome().equals(daTabela)) {
                for (CmpRelacao crl : est.relacoes()) {
                    if (!crl.pegaReferenciado().contains(".")) {
                        Cmp cmpChave = pegaCampo(crl.pegaReferenciado());
                        if (cmpChave != null) {
                            cmpChave.pEdt().controle().addFocusListener(new FocusAdapter() {
                                @Override
                                public void focusLost(FocusEvent e) {
                                    atualizarEstrangeiro(oCampo);
                                }
                            });
                        }
                    } else {
                        String tabOrigem = crl.pegaReferenciado().split("\\.")[0];
                        botaEstrangeiroParaAtualizar(tabOrigem, oCampo);
                    }
                }
            }
        }
    }

    private String botaTabelaAssociada(String aTabela, CadRelacao doEstrangeiro, CmpRelacao daRelacao, ArrayList<Object> comParametros) throws Exception {
        String retorno = "";

        for (CadRelacao cadEst : cadRelacoes) {
            if (cadEst.pegaNome().equals(aTabela)) {
                retorno += " JOIN " + cadEst.pegaEsquemaETabela() + (cadEst.pegaAlias().isEmpty() ? "" : " AS " + cadEst.pegaAlias()) + " ON ";
                retorno += daRelacao.pegaReferenciado() + " = " + doEstrangeiro.pegaNome() + "." + daRelacao.pegaReferencia() + " AND ";

                ArrayList<String> sqlAssoc = new ArrayList<>();
                ArrayList<Object> obsAssoc = new ArrayList<>();
                for (CmpRelacao crl : cadEst.relacoes()) {
                    if (!crl.pegaReferenciado().contains(".")) {
                        retorno += cadEst.pegaNome() + "." + crl.pegaReferencia() + " = ? AND ";
                        comParametros.add(pegaCampo(crl.pegaReferenciado()).pEdt().pgVlr());
                    } else {
                        String tabAss = crl.pegaReferenciado().split("\\.")[0];
                        sqlAssoc.add(botaTabelaAssociada(tabAss, cadEst, crl, obsAssoc));
                    }
                }

                retorno = UtlCrs.corta(retorno, 5);

                for (String sAs : sqlAssoc) {
                    retorno += " " + sAs;
                }

                for (Object oAs : obsAssoc) {
                    comParametros.add(oAs);
                }

                break;
            }

        }
        return retorno;
    }

    public void atualizarEstrangeiro(Cmp doCampo) {
        new Thread("Cadastro Atualizar Estrangeiro") {
            @Override
            public void run() {
                if (cadRelacoes != null) {
                    try {
                        doCampo.pEdt().botaAtualizando();
                        String sqlSelect = "SELECT ";

                        String[] parsNm = doCampo.pNome().split("\\.");

                        String tabOrigem = parsNm[0];
                        String cmpNome = parsNm[1];

                        String sqlWhere = " WHERE ";

                        ArrayList<Object> parsAssoc = new ArrayList<>();
                        ArrayList<Object> parsWhere = new ArrayList<>();

                        for (CadRelacao cadEst : cadRelacoes) {
                            if (cadEst.pegaNome().equals(tabOrigem)) {
                                sqlSelect += cadEst.pegaNome() + "." + cmpNome + " FROM " + cadEst.pegaEsquemaETabela() + (cadEst.pegaAlias().isEmpty() ? "" : " AS " + cadEst.pegaAlias());
                                for (CmpRelacao crl : cadEst.relacoes()) {
                                    if (!crl.pegaReferenciado().contains(".")) {
                                        sqlWhere += cadEst.pegaNome() + "." + crl.pegaReferencia() + " = ? AND ";
                                        parsWhere.add(pegaCampo(crl.pegaReferenciado()).pEdt().pgVlr());
                                    } else {
                                        String tabAss = crl.pegaReferenciado().split("\\.")[0];
                                        if (!sqlSelect.contains(tabAss)) {
                                            sqlSelect += botaTabelaAssociada(tabAss, cadEst, crl, parsAssoc);
                                        } else {
                                            sqlSelect += " AND " + crl.pegaReferenciado() + " = ";
                                            if (crl.pegaReferencia().contains(".")) {
                                                sqlSelect += crl.pegaReferencia();
                                            } else {
                                                sqlSelect += tabOrigem + "." + crl.pegaReferencia();
                                            }
                                        }
                                    }
                                }

                                break;
                            }
                        }

                        if (!sqlWhere.equals(" WHERE ")) {
                            sqlSelect += UtlCrs.corta(sqlWhere, 5);
                        }

                        Vlrs todosPars = new Vlrs();
                        for (Object par : parsAssoc) {
                            todosPars.novo(par);
                        }
                        for (Object par : parsWhere) {
                            todosPars.novo(par);
                        }

                        boolean fazer = false;
                        for (Vlr vlr : todosPars) {
                            if (vlr.pg() != null) {
                                fazer = true;
                                break;
                            }
                        }

                        if (fazer) {
                            Conjunto rst = conexao.busca(sqlSelect, todosPars);
                            if (rst.tem()) {
                                doCampo.pEdt().mdVlr(rst.pgCol());
                            } else {
                                Utl.alertaPop("Problema no Campo " + doCampo.pTitulo());
                                doCampo.pEdt().limpa();
                            }
                        } else {
                            doCampo.pEdt().limpa();
                        }
                    } catch (Exception ex) {
                        Utl.registra(ex);
                    } finally {
                        doCampo.pEdt().tiraAtualizando();
                    }
                }
            }
        }.start();
    }

    public void buscarReferencia() {
        buscarReferencia(false);
    }

    public void buscarReferencia(Boolean contendo) {
        Component cfo = janela.getFocusOwner();
        Cmp cmp = pegaCampo(cfo);
        if (cmp != null) {
            CadReferenciar ref = pegaReferencia(cmp.pNome());
            if (ref != null) {
                try {
                    Vlr cnt = null;
                    if (contendo) {
                        cnt = cmp.pEdt().pgVlr();
                        cmp.pEdt().limpa();
                    }
                    Object cad = ref.buscar.getDeclaredConstructor().newInstance();
                    ((Cadastro) cad).mostraReferenciador(ref, cnt);
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        }
    }

    public void mostraReferenciado() {
        Component cfo = janela.getFocusOwner();
        Cmp cmp = pegaCampo(cfo);
        if (cmp != null) {
            CadReferenciar ref = pegaReferencia(cmp.pNome());
            if (ref != null) {
                try {
                    Object cad = ref.buscar.getDeclaredConstructor().newInstance();

                    for (CmpRelacao crf : ref.relacoes) {
                        try {
                            if (!crf.pegaReferenciado().contains(".")) {
                                ((Cadastro) cad).pegaCampo(crf.pegaReferencia()).mVlrFixo(pegaCampo(crf.pegaReferenciado()).pEdt().pgVlr());
                            }
                        } catch (Exception ex) {
                        }
                    }

                    ((Cadastro) cad).mostra(true, true);
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        }
    }

    public void criarReferencia() {
        Component cfo = janela.getFocusOwner();
        Cmp cmp = pegaCampo(cfo);
        if (cmp != null) {
            CadReferenciar ref = pegaReferencia(cmp.pNome());
            if (ref != null) {
                try {
                    Object cad = ref.buscar.getDeclaredConstructor().newInstance();
                    ((Cadastro) cad).mostra();
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        }
    }

    public void criarReferenciaEdicao() {
        Component cfo = janela.getFocusOwner();
        Cmp cmp = pegaCampo(cfo);
        if (cmp != null) {
            CadReferenciar ref = pegaReferencia(cmp.pNome());
            if (ref != null) {
                try {
                    String relativo = null;
                    for (CmpRelacao relacao : ref.relacoes) {
                        if (Objects.equals(relacao.pegaReferenciado(), cmp.pNome())) {
                            relativo = relacao.pegaReferencia();
                            break;
                        }
                    }
                    Cadastro cad = (Cadastro) ref.buscar.getDeclaredConstructor().newInstance();
                    if (relativo != null) {
                        cad.pegaCampo(relativo).mVlrFixo(cmp.pEdt().pgVlr());
                    }
                    cad.mostra(true, true);
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        }
    }

    public void mudaSelecao() throws Exception {
        if (ehReferencia) {
            confirmarReferencia();
        } else {
            if (modo == Modo.NOVO) {
                if (verificaMudancas()) {
                    cadCarregar.carregarSelecao();
                    botaModoProcurando(false, true);
                }
            } else if (modo == Modo.PROCURANDO) {
                cadCarregar.carregarSelecao();
            } else if (modo == Modo.EDITAR) {
                if (verificaMudancas()) {
                    cadCarregar.carregarSelecao();
                } else {
                    cadCarregar.botaListaNoUltimoSelecionado();
                }
            }
        }
    }

    public Cadastro mudaModo(Modo paraModo) throws Exception {
        if (paraModo == Modo.NOVO) {
            botaModoNovo();
        } else if (paraModo == Modo.PROCURAR) {
            botaModoProcurar();
        } else if (paraModo == Modo.PROCURANDO) {
            botaModoProcurando();
        } else if (paraModo == Modo.EDITAR) {
            botaModoEditar();
        }
        return this;
    }

    public Cadastro botaModoNovo() throws Exception {
        return botaModoNovo(true, true);
    }

    public Cadastro botaModoNovo(Boolean verificarMudancas, Boolean limparCampos) throws Exception {
        if (ehReferencia) {
            throw new Erro("Este Cadastro É de Referência");
        }
        if (antesDeInserir != null) {
            for (Conferencia conf : antesDeInserir) {
                conf.confere(this);
            }
        }
        if (verificarMudancas) {
            if (!verificaMudancas()) {
                return this;
            }
        }
        cadBarra.pJbtNovo().setEnabled(true);
        cadBarra.pJbtProcurar().setEnabled(true);
        cadBarra.pJbtEditar().setEnabled(false);
        cadBarra.pJbtExcluir().setEnabled(false);
        cadBarra.pJbtAtualizar().setEnabled(true);
        cadBarra.pJbtPrimeiro().setEnabled(true);
        cadBarra.pJbtAnterior().setEnabled(true);
        cadBarra.pJbtProximo().setEnabled(true);
        cadBarra.pJbtUltimo().setEnabled(true);
        cadBarra.pJbtConfirmar().setEnabled(true);
        cadBarra.pJbtCancelar().setEnabled(true);
        if (limparCampos) {
            limparCampos();
        }
        modo = Modo.NOVO;
        UtlIma.botaPic(cadBarra.pJlbModo(), "ModoNovo.png");
        mudaCamposEditavel(true);
        if (cadBotoes != null) {
            for (Botao botao : cadBotoes) {
                botao.habilitado(botao.temPermissao("PodeAoInserir", true));
            }
        }
        jlbStatus.setText(status + " " + pStatusModo() + "...");
        cadBarra.pJlbModo().setToolTipText("Modo Inserindo");
        botaFocoNoPrimeiroEditavel();
        return this;
    }

    public Cadastro botaModoProcurar() throws Exception {
        if (cadProcurar == null) {
            cadProcurar = new CadProcurar(this);
        }
        cadProcurar.setVisible(true);
        return this;
    }

    public void pegaProcura(CadProcurar aProcurar) throws Exception {
        if (verificaMudancas()) {
            cadCarregar.recarregarUltimoSelecionado = true;
            cadCarregar.atualizar(aProcurar);
            if (!ehReferencia) {
                botaModoProcurando();
            }
        }
    }

    public Cadastro botaModoProcurando() throws Exception {
        return botaModoProcurando(true, true);
    }

    public Cadastro botaModoProcurando(Boolean verificarMudancas, Boolean carregar) throws Exception {
        if (ehReferencia) {
            throw new Erro("Este Cadastro É de Referência");
        }
        if (antesDeProcurar != null) {
            for (Conferencia conf : antesDeProcurar) {
                conf.confere(this);
            }
        }
        if (verificarMudancas) {
            if (!verificaMudancas()) {
                return this;
            }
        }
        cadBarra.pJbtNovo().setEnabled(true);
        cadBarra.pJbtProcurar().setEnabled(true);
        cadBarra.pJbtEditar().setEnabled(true);
        cadBarra.pJbtExcluir().setEnabled(true);
        cadBarra.pJbtAtualizar().setEnabled(true);
        cadBarra.pJbtPrimeiro().setEnabled(true);
        cadBarra.pJbtAnterior().setEnabled(true);
        cadBarra.pJbtProximo().setEnabled(true);
        cadBarra.pJbtUltimo().setEnabled(true);
        cadBarra.pJbtConfirmar().setEnabled(true);
        cadBarra.pJbtCancelar().setEnabled(false);
        modo = Modo.PROCURANDO;
        UtlIma.botaPic(cadBarra.pJlbModo(), "ModoProcurar.png");
        mudaCamposEditavel(false);
        if (carregar) {
            cadCarregar.carregarSelecao();
        }
        if (cadBotoes != null) {
            for (Botao botao : cadBotoes) {
                botao.habilitado(botao.temPermissao("PodeAoProcurar", true));
            }
        }
        jlbStatus.setText(status + " " + pStatusModo() + "...");
        cadBarra.pJlbModo().setToolTipText("Modo Procurando");
        return this;
    }

    public Cadastro botaModoEditar() throws Exception {
        return botaModoEditar(true);
    }

    public Cadastro botaModoEditar(Boolean verificarMudancas) throws Exception {
        if (ehReferencia) {
            throw new Erro("Este Cadastro É de Referência");
        }
        if (soConsulta) {
            throw new Erro("Este Cadastro É Somente para Consulta");
        }
        if (antesDeEditar != null) {
            for (Conferencia conf : antesDeEditar) {
                conf.confere(this);
            }
        }
        if (verificarMudancas) {
            if (!verificaMudancas()) {
                return this;
            }
        }
        if (ehModoEditar() && !janelaDeEdicao) {
            cadCarregar.recarregarUltimoSelecionado();
            botaModoProcurando(false, false);
        } else {
            cadBarra.pJbtNovo().setEnabled(true);
            cadBarra.pJbtProcurar().setEnabled(true);
            cadBarra.pJbtEditar().setEnabled(true);
            cadBarra.pJbtExcluir().setEnabled(true);
            cadBarra.pJbtAtualizar().setEnabled(true);
            cadBarra.pJbtPrimeiro().setEnabled(true);
            cadBarra.pJbtAnterior().setEnabled(true);
            cadBarra.pJbtProximo().setEnabled(true);
            cadBarra.pJbtUltimo().setEnabled(true);
            cadBarra.pJbtConfirmar().setEnabled(true);
            cadBarra.pJbtCancelar().setEnabled(true);
            modo = Modo.EDITAR;
            UtlIma.botaPic(cadBarra.pJlbModo(), "ModoEditar.png");
            mudaCamposEditavel(true);
            mudaChavesEditavel(false);
            if (cadBotoes != null) {
                for (Botao botao : cadBotoes) {
                    botao.habilitado(botao.temPermissao("PodeAoEditar", true));
                }
            }
        }
        jlbStatus.setText(status + " " + pStatusModo() + "...");
        cadBarra.pJlbModo().setToolTipText("Modo Editando");
        botaFocoNoPrimeiroEditavel();
        return this;
    }

    public Boolean ehModoNovoOuEditar() {
        return ((modo == Modo.NOVO) || (modo == Modo.EDITAR));
    }

    public Boolean ehModoEditarOuProcurar() {
        return ((modo == Modo.EDITAR) || (modo == Modo.PROCURAR) || (modo == Modo.PROCURANDO));
    }

    public Boolean ehModoNovo() {
        return ((modo == Modo.NOVO));
    }

    public Boolean ehModoEditar() {
        return ((modo == Modo.EDITAR));
    }

    public Boolean ehModoProcurar() {
        return ((modo == Modo.PROCURAR) || (modo == Modo.PROCURANDO));
    }

    public boolean verificaMudanca(Cmp oCampo) throws Exception {
        Boolean retorno = false;
        if (oCampo != null) {
            Object antes = oCampo.pVlrSalvo();
            Object depois = oCampo.pEdt().pgVlr();
            retorno = verificaMudanca(antes, depois);
        }
        return retorno;
    }

    public boolean verificaMudanca(Object antes, Object eDepois) throws Exception {
        String ant = UtlCrs.pega(antes, "");
        String dep = UtlCrs.pega(eDepois, "");
        return !Objects.equals(ant, dep);
    }

    public Boolean verificaMudancas() throws Exception {
        Boolean retorno = true;
        String mudados = "";
        for (int i1 = 0; i1 < camposDeValor.size(); i1++) {
            Cmp cmp = camposDeValor.get(i1);
            if (cmp.pVlrFixo() != null) {
                if (cmp.pEdt() != null) {
                    if (!cmp.pVlrFixo().equals(cmp.pEdt().pgVlr())) {
                        cmp.pEdt().mdVlr(cmp.pVlrFixo());
                    }
                }
            } else if (cmp.pVlrInicial() != null) {
                if (cmp.pEdt() != null) {
                    Object anterior = cmp.pVlrInicial();
                    Object posterior = cmp.pEdt().pgVlr();
                    if (verificaMudanca(anterior, posterior)) {
                        retorno = false;
                        mudados = mudados + (mudados.isEmpty() ? "" : ", ") + cmp.pTitulo();
                    }
                }
            } else {
                if (modo == Modo.NOVO) {
                    if ((!cmp.pEdt().vazio()) && (!(cmp.pTipo() == Dados.Tp.Log))) {
                        retorno = false;
                        mudados = mudados + (mudados.isEmpty() ? "" : ", ") + cmp.pTitulo();
                    }
                } else if (modo == Modo.EDITAR) {
                    Object anterior = cadCarregar.ultimoSelecionadoValores[i1];
                    Object posterior = cmp.pEdt().pgVlr();
                    if (verificaMudanca(anterior, posterior)) {
                        retorno = false;
                        mudados = mudados + (mudados.isEmpty() ? "" : ", ") + cmp.pTitulo();
                    }
                }
            }
        }
        if (!retorno) {
            retorno = Utl.continua("Campo(s) do cadastro foram alterados.\nSão ele(s): " + mudados + "\n\nSe continuar esses dados serão perdidos.\nDeseja Continuar?");
        }
        return retorno;
    }

    public Cadastro excluir() throws Exception {
        if (ehReferencia) {
            return this;
        }
        if (Utl.continua("Essa ação irá excluir esse registro.")) {
            if (antesDeExcluir != null) {
                for (Conferencia act : antesDeExcluir) {
                    act.confere(this);
                }
            }
            if (ehModoNovo()) {
                limparCampos();
            } else if (cadSQL.excluir()) {
                mStatus("Excluído: " + UtlCrs.junta(pegaChavesValores()));
                int sel = cadCarregar.ultimoSelecionadoIndex;
                if (sel > -1) {
                    dtmLista.removeRow(sel);
                }
                if (sel > dtmLista.getRowCount() - 1) {
                    sel--;
                }
                if (sel >= 0) {
                    jtbLista.setRowSelectionInterval(sel, sel);
                    cadCarregar.carregarSelecao();
                } else {
                    botaModoNovo();
                }
                if (depoisDeExcluir != null) {
                    for (Conferencia act : depoisDeExcluir) {
                        act.confere(this);
                    }
                }
            }
        }
        return this;
    }

    public Date ultimaVerificacao = null;

    public Boolean verificaAtualizacoes() {
        if (!conexao.estaConectado()) {
            return true;
        }

        Boolean retorno = true;

        if (deveAtualizar != null) {
            if (deveAtualizar.size() > 0) {
                if (ehModoEditarOuProcurar() && !janelaDeEdicao) {
                    try {
                        List<Dados.Tp> tipos = new ArrayList<>();
                        String sqlSelect = "SELECT ";
                        for (Cmp cmp : campos) {
                            if (cmp.pTipo().pValor()) {
                                sqlSelect += (cmp.pNome().contains(".") ? "" : tabela + ".") + cmp.pNome() + ", ";
                                tipos.add(cmp.pTipo());
                            }
                        }
                        sqlSelect = UtlCrs.corta(sqlSelect, 2);

                        String sqlFrom = " FROM " + tabela;
                        if (cadRelacoes != null) {
                            for (CadRelacao est : cadRelacoes) {
                                sqlFrom += (est.ehObrigatorio() ? "" : " LEFT ") + " JOIN " + est.pegaEsquemaETabela() + (est.pegaAlias().isEmpty() ? "" : " AS " + est.pegaAlias()) + " ON ";
                                for (CmpRelacao crl : est.relacoes()) {
                                    sqlFrom += (crl.pegaReferencia().contains(".") ? "" : est.pegaNome() + ".") + crl.pegaReferencia() + " = "
                                            + (crl.pegaReferenciado().contains(".") ? "" : tabela + ".") + crl.pegaReferenciado() + " AND ";
                                }
                                sqlFrom = UtlCrs.corta(sqlFrom, 5);
                            }
                        }

                        sqlSelect += sqlFrom + " WHERE ";

                        Vlrs parChaves = new Vlrs();
                        for (Cmp cmp : campos) {
                            if (cmp.pDetalhe("chave", false)) {
                                sqlSelect += tabela + "." + cmp.pNome() + " = ? AND ";
                                parChaves.bota(cmp.pEdt().pgVlr());
                            }
                        }

                        sqlSelect = UtlCrs.corta(sqlSelect, 5);
                        Conjunto rst = conexao.busca(sqlSelect, parChaves, tipos);

                        if (rst.proximo()) {
                            int iCol = 1;
                            for (Cmp cmp : campos) {
                                if (cmp.pTipo().pValor()) {
                                    Object atuVal = rst.pgVlr(iCol);
                                    Object antVal = cadCarregar.ultimoSelecionadoValores[iCol - 1];

                                    if (verificaMudanca(antVal, atuVal)) {
                                        Boolean modificar = false;
                                        if (ehModoProcurar()) {
                                            modificar = true;
                                        } else if (estaParaAtualizar(cmp.pNome())) {
                                            modificar = true;
                                        }

                                        if (modificar) {
                                            cmp.pEdt().mdVlr(atuVal);
                                            cmp.mVlrSalvo(atuVal);
                                            if (cadCarregar.ultimoSelecionadoIndex > -1) {
                                                dtmLista.setValueAt(atuVal, cadCarregar.ultimoSelecionadoIndex, iCol - 1);
                                            }
                                            cadCarregar.ultimoSelecionadoValores[iCol - 1] = atuVal;
                                        }
                                    }

                                    iCol++;
                                }
                            }
                        }
                    } catch (Exception ex) {
                        Utl.registra(ex);
                    }
                }
            }
        }

        return retorno;
    }

    public void atualizar() throws Exception {
        if (!verificaMudancas()) {
            return;
        }

        cadCarregar.recarregarUltimoSelecionado = true;
        cadCarregar.atualizar();
        botaModoProcurando();
    }

    public Boolean irAoPrimeiro() throws Exception {
        if (!verificaMudancas()) {
            return false;
        }
        if (dtmLista.getRowCount() > 0) {
            jtbLista.setRowSelectionInterval(0, 0);
            cadCarregar.carregarSelecao();
            if (ehModoEditar()) {
                Boolean pode = true;
                if (antesDeEditar != null) {
                    for (Conferencia cnf : antesDeEditar) {
                        try {
                            cnf.confere(this);
                        } catch (Exception ex) {
                            Utl.registra(ex);
                            pode = false;
                        }
                    }
                }
                if (!pode) {
                    botaModoProcurando(false, false);
                }
            } else if (ehModoNovo()) {
                botaModoProcurando(false, false);
            }

            return true;
        } else {
            return false;
        }
    }

    public void irAoAnterior() throws Exception {
        if (!verificaMudancas()) {
            return;
        }
        int sel = cadCarregar.ultimoSelecionadoIndex;
        if (sel == -1) {
            irAoUltimo();
        } else if (sel > 0) {
            jtbLista.setRowSelectionInterval(sel - 1, sel - 1);
            cadCarregar.carregarSelecao();
            if (ehModoEditar()) {
                Boolean pode = true;
                if (antesDeEditar != null) {
                    for (Conferencia cnf : antesDeEditar) {
                        try {
                            cnf.confere(this);
                        } catch (Exception ex) {
                            Utl.registra(ex);
                            pode = false;
                        }
                    }
                }
                if (!pode) {
                    botaModoProcurando(false, false);
                }
            } else if (ehModoNovo()) {
                botaModoProcurando(false, false);
            }
        }
    }

    public Boolean irAoProximo() throws Exception {
        return irAoProximo(true);
    }

    public Boolean irAoProximo(Boolean verificarMudancas) throws Exception {
        if (verificarMudancas) {
            if (!verificaMudancas()) {
                return false;
            }
        }
        int sel = cadCarregar.ultimoSelecionadoIndex;
        if (sel == -1) {
            return irAoPrimeiro();
        } else if (sel < dtmLista.getRowCount() - 1) {
            jtbLista.setRowSelectionInterval(sel + 1, sel + 1);
            cadCarregar.carregarSelecao();

            if (ehModoEditar()) {
                Boolean pode = true;
                if (antesDeEditar != null) {
                    for (Conferencia cnf : antesDeEditar) {
                        try {
                            cnf.confere(this);
                        } catch (Exception ex) {
                            Utl.registra(ex);
                            pode = false;
                        }
                    }
                }
                if (!pode) {
                    botaModoProcurando(false, false);
                }
            } else if (ehModoNovo()) {
                botaModoProcurando(false, false);
            }
            return true;
        } else {
            return false;
        }
    }

    public void irAoUltimo() throws Exception {
        if (!verificaMudancas()) {
            return;
        }
        if (dtmLista.getRowCount() > 0) {
            jtbLista.setRowSelectionInterval(dtmLista.getRowCount() - 1, dtmLista.getRowCount() - 1);
            cadCarregar.carregarSelecao();
            if (ehModoEditar()) {
                Boolean pode = true;
                if (antesDeEditar != null) {
                    for (Conferencia cnf : antesDeEditar) {
                        try {
                            cnf.confere(this);
                        } catch (Exception ex) {
                            Utl.registra(ex);
                            pode = false;
                        }
                    }
                }
                if (!pode) {
                    botaModoProcurando(false, false);
                }
            } else if (ehModoNovo()) {
                botaModoProcurando(false, false);
            }
        }
    }

    public Boolean confirmar() {
        return confirmar(false);
    }

    public Boolean confirmar(Boolean mantemPosicao) {
        Boolean retorno = true;
        try {
            if ((modo == Modo.NOVO) || (modo == Modo.EDITAR)) {
                if (soConsulta) {
                    throw new Erro("Este Cadastro É Somente para Consulta");
                }
                String faltou = "";
                for (Cmp cmp : campos) {
                    if (cmp.pObrigatorio()) {
                        if (cmp.pEdt().vazio()) {
                            faltou = faltou + (faltou.isEmpty() ? "" : " , ") + cmp.pTitulo();
                        }
                    }
                }
                if (!faltou.isEmpty()) {
                    Utl.alerta("Faltou Preencher Os Campos Obrigatórios:\n\n" + faltou);
                    return false;
                }
                if (antesDeSalvar != null) {
                    for (Conferencia cnf : antesDeSalvar) {
                        cnf.confere(this);
                    }
                }
                if (modo == Modo.NOVO) {
                    if (cadSQL.inserir()) {
                        chavesUltimoSalvo = pegaChavesValores();
                        mStatus("Inserido: " + UtlCrs.junta(chavesUltimoSalvo));
                        if (!mantemPosicao) {
                            limparCampos();
                        } else {
                            botaModoEditar(false);

                        }
                    } else {
                        retorno = false;
                    }
                } else if (modo == Modo.EDITAR) {
                    if (cadSQL.modificar()) {
                        chavesUltimoSalvo = pegaChavesValores();
                        mStatus("Editado: " + UtlCrs.junta(chavesUltimoSalvo));
                        if (!mantemPosicao) {
                            if (!irAoProximo(false)) {
                                botaModoNovo(false, true);
                            } else {
                                botaFocoNoPrimeiroEditavel();
                            }
                        } else {
                            cadCarregar.ultimoSelecionadoChaves = pegaChavesValores();
                            cadCarregar.ultimoSelecionadoValores = pegaCamposValores();
                        }
                    } else {
                        retorno = false;
                    }
                }
                if (retorno) {
                    if (depoisDeSalvar != null) {
                        if ((modo == Modo.NOVO) || (modo == Modo.EDITAR)) {
                            if (depoisDeSalvar != null) {
                                for (Conferencia act : depoisDeSalvar) {
                                    act.confere(this);
                                }
                            }
                        }
                    }
                }
            } else if (!mantemPosicao) {
                irAoProximo();
            }
        } catch (Exception ex) {
            Utl.registra(ex, true);
            retorno = false;
        }
        return retorno;
    }

    public void cancelar() throws Exception {
        if (!Utl.continua("Essa ação irá cancelar as modificações não salvas.")) {
            return;
        }

        if (modo == Modo.NOVO) {
            limparCampos();
        } else if (modo == Modo.EDITAR) {
            cadCarregar.recarregarUltimoSelecionado();
        }
    }

    public void confirmarReferencia() throws Exception {
        int sel = jtbLista.getSelectedRow();
        if (sel > -1) {
            if (cadReferenciado.cadastro != null) {
                int maxCadInd = -1;

                for (CmpRelacao cmr : cadReferenciado.relacoes) {
                    Object valor = dtmLista.getValueAt(sel, pegaCampoValorIndice(cmr.pegaReferencia()));
                    cadReferenciado.cadastro.pegaCampo(cmr.pegaReferenciado()).pEdt().mdVlr(valor);
                    int iCadInd = cadReferenciado.cadastro.pegaCampoIndice(cmr.pegaReferenciado());
                    if (iCadInd > maxCadInd) {
                        maxCadInd = iCadInd;
                    }
                }

                cadReferenciado.cadastro.janela.requestFocus();
                if (maxCadInd > -1) {
                    if (maxCadInd < cadReferenciado.cadastro.campos.size() - 1) {
                        maxCadInd++;
                    }
                    while ((cadReferenciado.cadastro.campos.get(maxCadInd).pDetalhe("estrangeiro", false)) && (maxCadInd < cadReferenciado.cadastro.campos.size() - 1)) {
                        maxCadInd++;
                    }
                    cadReferenciado.cadastro.campos.get(maxCadInd).pEdt().controle().requestFocus();
                }

                cadCarregar.parar();
                cadCarregar.terminar();

                Janelas.fecha(janela);
            } else if (cadReferenciado.rotina != null) {
                int maxCadInd = -1;

                for (CmpRelacao cmr : cadReferenciado.relacoes) {
                    Object valor = dtmLista.getValueAt(sel, pegaCampoValorIndice(cmr.pegaReferencia()));
                    cadReferenciado.rotina.pegaCampo(cmr.pegaReferenciado()).pEdt().mdVlr(valor);
                    int iCadInd = cadReferenciado.rotina.pegaCampoIndice(cmr.pegaReferenciado());
                    if (iCadInd > maxCadInd) {
                        maxCadInd = iCadInd;
                    }
                }

                cadReferenciado.rotina.intf.janela().requestFocus();
                if (maxCadInd > -1) {
                    if (maxCadInd < cadReferenciado.rotina.campos.size() - 1) {
                        maxCadInd++;
                    }
                    while ((cadReferenciado.rotina.campos.get(maxCadInd).pDetalhe("estrangeiro", false)) && (maxCadInd < cadReferenciado.rotina.campos.size() - 1)) {
                        maxCadInd++;
                    }
                    cadReferenciado.rotina.campos.get(maxCadInd).pEdt().controle().requestFocus();
                }

                cadCarregar.parar();
                cadCarregar.terminar();

                Janelas.fecha(janela);
            } else if (cadReferenciado.relatorio != null) {
                int maxCadInd = -1;

                for (CmpRelacao cmr : cadReferenciado.relacoes) {
                    Object valor = dtmLista.getValueAt(sel, pegaCampoValorIndice(cmr.pegaReferencia()));
                    cadReferenciado.relatorio.pegaCampo(cmr.pegaReferenciado()).editor.mdVlr(valor);
                    int iCadInd = cadReferenciado.relatorio.pegaCampoIndice(cmr.pegaReferenciado());
                    if (iCadInd > maxCadInd) {
                        maxCadInd = iCadInd;
                    }
                }

                cadReferenciado.relatorio.jfrJanela.requestFocus();
                if (maxCadInd > -1) {
                    if (maxCadInd < cadReferenciado.relatorio.campos.size() - 1) {
                        maxCadInd++;
                    }
                    while ((cadReferenciado.relatorio.campos.get(maxCadInd).estrangeiro) && (maxCadInd < cadReferenciado.relatorio.campos.size() - 1)) {
                        maxCadInd++;
                    }
                    cadReferenciado.relatorio.campos.get(maxCadInd).editor.controle().requestFocus();
                }

                cadCarregar.parar();
                cadCarregar.terminar();
                Janelas.fecha(janela);
            }
        }
    }

    public void cancelarReferencia() {
        cadCarregar.parar();
        cadCarregar.terminar();
        Janelas.fecha(janela);
    }

    public static Boolean botaProximo(Cadastro noCadastro) throws Exception {
        if (noCadastro.formatoCodigo == null) {
            noCadastro.formatoCodigo = CodigosMod.pegaFormato(noCadastro.conexao, noCadastro.tabela);
        }
        String formato = noCadastro.formatoCodigo;
        Cmp[] nosCampos = noCadastro.pegaChaves();
        String daTabela = noCadastro.tabela;
        if (formato.isEmpty()) {
            if (nosCampos.length > 1) {
                if (nosCampos[nosCampos.length - 1].pEdt() instanceof EdtCrs) {
                    formato = "MX;" + ((EdtCrs) (nosCampos[nosCampos.length - 1].pEdt())).tamanho;
                } else {
                    formato = "MX;4";
                }
            } else if (nosCampos.length == 1) {
                if (nosCampos[0].pEdt() instanceof EdtCrs) {
                    formato = "NS;" + ((EdtCrs) (nosCampos[0].pEdt())).tamanho;
                } else {
                    formato = "NS;6";
                }
            }
            CodigosMod.botaFormato(noCadastro.conexao, daTabela, formato);
            noCadastro.formatoCodigo = formato;
        }
        String condicao = "";
        for (Cmp cmp : nosCampos) {
            if (cmp.pDetalhe("chave", false)) {
                if (cmp.pEdt().vazio()) {
                    EdtMod.botaProximo(noCadastro.conexao, cmp.pEdt(), daTabela, formato, cmp.pNome(), condicao);
                } else {
                    condicao = condicao + (condicao.isEmpty() ? "" : " AND ") + cmp.pNome() + " = " + cmp.pEdt().paraConsSQL();
                }
            }
        }
        return true;
    }

    public static Boolean botaProximo(Conexao naConexao, Cmp[] nosCampos, String daTabela) throws Exception {
        String formato = CodigosMod.pegaFormato(naConexao, daTabela);
        if (formato.isEmpty()) {
            if (nosCampos.length > 1) {
                if (nosCampos[nosCampos.length - 1].pEdt() instanceof EdtCrs) {
                    formato = "MX;" + ((EdtCrs) (nosCampos[nosCampos.length - 1].pEdt())).tamanho;
                } else {
                    formato = "MX;4";
                }
            } else if (nosCampos.length == 1) {
                if (nosCampos[0].pEdt() instanceof EdtCrs) {
                    formato = "NS;" + ((EdtCrs) (nosCampos[0].pEdt())).tamanho;
                } else {
                    formato = "NS;6";
                }
            }
            CodigosMod.botaFormato(naConexao, daTabela, formato);
        }
        String condicao = "";
        for (Cmp cmp : nosCampos) {
            if (cmp.pDetalhe("chave", false)) {
                if (cmp.pEdt().vazio()) {
                    EdtMod.botaProximo(naConexao, cmp.pEdt(), daTabela, formato, cmp.pNome(), condicao);
                } else {
                    condicao = condicao + (condicao.isEmpty() ? "" : " AND ") + cmp.pNome() + " = " + cmp.pEdt().paraConsSQL();
                }
            }
        }
        return true;
    }
}
