package pin.modinf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import pin.libutl.UtlCrs;
import pin.libutl.UtlTex;
import pin.libvlr.VCrs;

public class Tabela {

    private volatile Banco banco;
    private volatile String esquema;
    private volatile String nome;
    private volatile String titulo;
    private volatile List<TabCampo> campos;
    private volatile List<TabCampo> chaves;
    private volatile List<TabEstrangeiro> estrangeiros;
    private volatile List<TabIndice> indices;
    private volatile List<TabVerificador> verificadores;
    private volatile Tabelas dasTabelas;
    private volatile List<Tabela> filhas;

    public Tabela(String comNome) {
        this(null, null, comNome, null, null, null, null, null);
    }

    public Tabela(Banco doBanco, String comNome) {
        this(doBanco, null, comNome, null, null, null, null, null);
    }

    public Tabela(String noEsquema, String comNome) {
        this(null, noEsquema, comNome, null, null, null, null, null);
    }

    public Tabela(Banco doBanco, String noEsquema, String comNome) {
        this(doBanco, noEsquema, comNome, null, null, null, null, null);
    }

    public Tabela(String noEsquema, String comNome, String eTitulo) {
        this(null, noEsquema, comNome, eTitulo, null, null, null, null);
    }

    public Tabela(Banco doBanco, String noEsquema, String comNome, String eTitulo) {
        this(doBanco, noEsquema, comNome, eTitulo, null, null, null, null);
    }

    public Tabela(String noEsquema, String comNome, String eTitulo, TabCampo[] eCampos) {
        this(null, noEsquema, comNome, eTitulo, eCampos, null, null, null);
    }

    public Tabela(Banco doBanco, String noEsquema, String comNome, String eTitulo, TabCampo[] eCampos) {
        this(doBanco, noEsquema, comNome, eTitulo, eCampos, null, null, null);
    }

    public Tabela(String noEsquema, String comNome, String eTitulo, TabCampo[] eCampos, TabEstrangeiro[] eEstrangeiros) {
        this(null, noEsquema, comNome, eTitulo, eCampos, eEstrangeiros, null, null);
    }

    public Tabela(Banco doBanco, String noEsquema, String comNome, String eTitulo, TabCampo[] eCampos, TabEstrangeiro[] eEstrangeiros) {
        this(doBanco, noEsquema, comNome, eTitulo, eCampos, eEstrangeiros, null, null);
    }

    public Tabela(String noEsquema, String comNome, String eTitulo, TabCampo[] eCampos, TabEstrangeiro[] eEstrangeiros, TabIndice[] eUnicos) {
        this(null, noEsquema, comNome, eTitulo, eCampos, eEstrangeiros, eUnicos, null);
    }

    public Tabela(Banco doBanco, String noEsquema, String comNome, String eTitulo, TabCampo[] eCampos, TabEstrangeiro[] eEstrangeiros, TabIndice[] eUnicos) {
        this(doBanco, noEsquema, comNome, eTitulo, eCampos, eEstrangeiros, eUnicos, null);
    }

    public Tabela(String noEsquema, String comNome, String eTitulo, TabCampo[] eCampos, TabEstrangeiro[] eEstrangeiros, TabIndice[] eUnicos, TabVerificador[] eVerificadores) {
        this(null, noEsquema, comNome, eTitulo, eCampos, eEstrangeiros, eUnicos, eVerificadores);
    }

    public Tabela(Banco doBanco, String noEsquema, String comNome, String eTitulo, TabCampo[] eCampos, TabEstrangeiro[] eEstrangeiros, TabIndice[] eUnicos, TabVerificador[] eVerificadores) {
        if (noEsquema == null) {
            noEsquema = "public";
        }
        banco = doBanco;
        esquema = noEsquema;
        nome = comNome;
        titulo = eTitulo;
        campos = null;
        chaves = null;
        if (eCampos != null) {
            campos = new ArrayList<>();
            campos.addAll(Arrays.asList(eCampos));
        }
        estrangeiros = null;
        if (eEstrangeiros != null) {
            estrangeiros = new ArrayList<>();
            estrangeiros.addAll(Arrays.asList(eEstrangeiros));
        }
        indices = null;
        if (eUnicos != null) {
            indices = new ArrayList<>();
            indices.addAll(Arrays.asList(eUnicos));
        }
        verificadores = null;
        if (eVerificadores != null) {
            verificadores = new ArrayList<>();
            verificadores.addAll(Arrays.asList(eVerificadores));
        }
        filhas = null;
    }

    public Banco pBanco() {
        return banco;
    }

    public Tabela mBanco(Banco banco) {
        this.banco = banco;
        return this;
    }

    public String pEsquema() {
        return esquema;
    }

    public Tabela mEsquema(String esquema) {
        this.esquema = esquema;
        return this;
    }

    public String pNome() {
        return nome;
    }

    public Tabela mNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String pTitulo() {
        return titulo;
    }

    public Tabela mTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public String pEsquemaENome() {
        return UtlCrs.pega(esquema, "") + "." + UtlCrs.pega(nome, "");
    }

    public synchronized List<TabCampo> pCampos() throws Exception {
        if (campos == null) {
            if (banco != null) {
                banco.carregaCampos(this);
            }
        }
        return campos;
    }

    public Tabela mCampos(List<TabCampo> osCampos) throws Exception {
        campos = osCampos;
        return this;
    }

    public synchronized TabCampo pCampo(String aNome) throws Exception {
        if (campos == null) {
            banco.carregaCampos(this);
        }
        TabCampo retorno = null;
        for (TabCampo cmp : campos) {
            if (cmp.pNome().equals(aNome)) {
                retorno = cmp;
                break;
            }
        }
        return retorno;
    }

    public List<TabCampo> pChaves() throws Exception {
        if (chaves != null) {
            return chaves;
        }
        chaves = new ArrayList<>();
        for (TabCampo cmp : pCampos()) {
            if (cmp.ehChave()) {
                chaves.add(cmp);
            }
        }

        return chaves;
    }

    public synchronized List<TabEstrangeiro> pEstrangeiros() throws Exception {
        if (estrangeiros == null) {
            if (banco != null) {
                banco.carregaEstrangeiros(this);
            }
        }
        return estrangeiros;
    }

    public Tabela mEstrangeiros(List<TabEstrangeiro> osEstrangeiros) throws Exception {
        estrangeiros = osEstrangeiros;
        return this;
    }

    public TabEstrangeiro pEstrangeiro(String doEsquema, String eTabela) throws Exception {
        return Tabela.this.pEstrangeiro(doEsquema + "." + eTabela);
    }

    public synchronized TabEstrangeiro pEstrangeiro(String doEsquemaETabela) throws Exception {
        if (estrangeiros == null) {
            banco.carregaEstrangeiros(this);
        }

        TabEstrangeiro retorno = null;
        for (TabEstrangeiro est : estrangeiros) {
            if (est.pEsquemaETabela().equals(doEsquemaETabela)) {
                retorno = est;
                break;
            }
        }
        return retorno;
    }

    public synchronized List<TabIndice> pIndices() throws Exception {
        if (indices == null) {
            if (banco != null) {
                banco.carregaIndices(this);
            }
        }
        return indices;
    }

    public Tabela mIndices(List<TabIndice> osIndices) throws Exception {
        indices = osIndices;
        return this;
    }

    public synchronized List<TabVerificador> pVerificadores() throws Exception {
        if (verificadores == null) {
            if (banco != null) {
                banco.carregaVerificadores(this);
            }
        }
        return verificadores;
    }

    public Tabela mVerificadores(List<TabVerificador> osVerificadores) throws Exception {
        verificadores = osVerificadores;
        return this;
    }

    public Tabelas pDasTabelas() {
        return dasTabelas;
    }

    public Tabela mDasTabelas(Tabelas dasTabelas) {
        this.dasTabelas = dasTabelas;
        return this;
    }

    public Boolean ehFilha() throws Exception {
        for (TabEstrangeiro est : pEstrangeiros()) {
            for (TabRelacao rel : est.pRelacoes()) {
                for (TabCampo chv : pChaves()) {
                    if (Objects.equals(rel.referenciado, chv.pNome())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public synchronized List<Tabela> pFilhas() throws Exception {
        if (filhas != null) {
            return filhas;
        }
        filhas = new ArrayList<>();
        for (int it = 0; it < dasTabelas.tamanho(); it++) {
            Tabela tab = dasTabelas.pega(it);
            for (TabEstrangeiro est : tab.pEstrangeiros()) {
                if (Objects.equals(est.pEsquemaETabela(), pEsquemaENome())) {
                    int qr = 0;
                    for (TabRelacao rel : est.pRelacoes()) {
                        for (TabCampo chv : tab.pChaves()) {
                            if (Objects.equals(rel.referenciado, chv.pNome())) {
                                qr++;
                            }
                        }
                    }
                    if (qr == est.pRelacoes().size()) {
                        filhas.add(tab);
                    }
                }
            }
        }
        return filhas;
    }

    public TabEstrangeiro pEstrangeiro(Tabela relacionadoComPai) throws Exception {
        for (TabEstrangeiro est : pEstrangeiros()) {
            if (Objects.equals(est.pEsquemaETabela(), relacionadoComPai.pEsquemaENome())) {
                int enc = 0;
                for (TabRelacao rel : est.pRelacoes()) {
                    for (TabCampo chv : pChaves()) {
                        if (Objects.equals(rel.referenciado, chv.pNome())) {
                            enc++;
                        }
                    }
                }
                if (enc == est.pRelacoes().size()) {
                    return est;
                }
            }
        }
        return null;
    }

    public String fazJuncao(Tabela comFilha) throws Exception {
        TabEstrangeiro est = comFilha.pEstrangeiro(this);
        if (est == null) {
            return "";
        }
        String retorno = "";
        for (TabRelacao rel : est.pRelacoes()) {
            if (!retorno.isEmpty()) {
                retorno += " AND ";
            }
            retorno += pEsquemaENome() + "." + rel.referencia + " = " + comFilha.pEsquemaENome() + "." + rel.referenciado;
        }
        retorno = " JOIN " + pEsquemaENome() + " ON " + retorno;
        return retorno;
    }

    public String pDescricao() throws Exception {
        VCrs retorno = new VCrs("Tabela " + pEsquemaENome());
        if (titulo != null) {
            if (!titulo.isEmpty()) {
                retorno.soma(" [ " + titulo + " ]");
            }
        }
        retorno.soma(" (");
        List<TabCampo> cmps = pCampos();
        for (TabCampo cmp : cmps) {
            retorno.soma(UtlTex.quebra() + "  " + cmp.pDescricao() + ",");
        }
        if (!cmps.isEmpty()) {
            retorno.corta(1);
        }
        retorno.soma(UtlTex.quebra() + ")");

        retorno.soma(UtlTex.quebra() + "Estrangeiros (");
        List<TabEstrangeiro> ests = pEstrangeiros();
        for (TabEstrangeiro est : ests) {
            retorno.soma(UtlTex.quebra() + "  " + est.pDescricao() + ",");
        }
        if (!ests.isEmpty()) {
            retorno.corta(1);
        }
        retorno.soma(UtlTex.quebra() + ")");
        retorno.soma(UtlTex.quebra() + "Índices (");
        List<TabIndice> inds = pIndices();
        for (TabIndice ind : inds) {
            retorno.soma(UtlTex.quebra() + "  " + ind.pDescricao() + ",");
        }
        if (!inds.isEmpty()) {
            retorno.corta(1);
        }
        retorno.soma(UtlTex.quebra() + ")");
        retorno.soma(UtlTex.quebra() + "Verificadores (");
        List<TabVerificador> vers = pVerificadores();
        for (TabVerificador ver : vers) {
            retorno.soma(UtlTex.quebra() + "  " + ver.pDescricao() + ",");
        }
        if (!vers.isEmpty()) {
            retorno.corta(1);
        }
        retorno.soma(UtlTex.quebra() + ")");
        return retorno.toString();
    }

    @Override
    public String toString() {
        return pEsquemaENome();
    }
}
