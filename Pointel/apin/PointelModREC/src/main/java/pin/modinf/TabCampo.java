package pin.modinf;

import pin.libbas.Dados;

public class TabCampo {

    private Integer ordem;
    private String nome;
    private String titulo;
    private Dados.Tp tipo;
    private Integer tamanho;
    private Integer precisao;
    private String padrao;
    private Boolean obrigatorio;
    private Boolean chave;

    public TabCampo(String comNome) {
        this(null, comNome, null, null);
    }

    public TabCampo(Integer naOrdem, String comNome) {
        this(naOrdem, comNome, null, null);
    }

    public TabCampo(String comNome, Dados.Tp eTipo) {
        this(null, comNome, null, eTipo);
    }

    public TabCampo(String comNome, String eTitulo, Dados.Tp eTipo) {
        this(null, comNome, eTitulo, eTipo);
    }

    public TabCampo(Integer naOrdem, String comNome, Dados.Tp eTipo) {
        this(naOrdem, comNome, null, eTipo);
    }

    public TabCampo(Integer naOrdem, String comNome, String eTitulo, Dados.Tp eTipo) {
        ordem = naOrdem;
        nome = comNome;
        titulo = eTitulo;
        tipo = eTipo;

        tamanho = 0;
        precisao = 0;

        padrao = null;
        obrigatorio = false;
        chave = false;
    }

    public Integer pOrdem() {
        return ordem;
    }

    public TabCampo mOrdem(Integer ordem) {
        this.ordem = ordem;
        return this;
    }

    public String pNome() {
        return nome;
    }

    public TabCampo mNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String pTitulo() {
        return titulo;
    }

    public TabCampo mTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public Dados.Tp pTipo() {
        return tipo;
    }

    public TabCampo mTipo(Dados.Tp tipo) {
        this.tipo = tipo;
        return this;
    }

    public Integer pTamanho() {
        return tamanho;
    }

    public TabCampo mTamanho(Integer oTamanho) {
        tamanho = oTamanho;
        return this;
    }

    public TabCampo mTamanho(Integer oTamanho, Integer ePrecisao) {
        tamanho = oTamanho;
        precisao = ePrecisao;
        return this;
    }

    public Integer pPrecisao() {
        return precisao;
    }

    public TabCampo bmPrecisao(Integer precisao) {
        this.precisao = precisao;
        return this;
    }

    public String pPadrao() {
        return padrao;
    }

    public TabCampo mPadrao(String oPadrao) {
        padrao = oPadrao;
        return this;
    }

    public Boolean ehObrigatorio() {
        return obrigatorio;
    }

    public TabCampo mObrigatorio(Boolean para) {
        obrigatorio = para;
        return this;
    }

    public TabCampo botaObrigatorio() {
        obrigatorio = true;
        return this;
    }

    public TabCampo tiraObrigatorio() {
        obrigatorio = false;
        return this;
    }

    public TabCampo trocaObrigatorio() {
        obrigatorio = !obrigatorio;
        return this;
    }

    public Boolean ehChave() {
        return chave;
    }

    public TabCampo mChave(Boolean aChave) {
        chave = aChave;
        return this;
    }

    public TabCampo botaChave() {
        chave = true;
        return this;
    }

    public TabCampo tiraChave() {
        chave = false;
        return this;
    }

    public TabCampo trocaChave() {
        chave = !chave;
        return this;
    }

    public String pDescricao() {
        String retorno = "Campo " + nome;
        if (titulo != null) {
            if (!titulo.isEmpty()) {
                retorno += " [ " + titulo + " ]";
            }
        }
        if (tipo != null) {
            retorno += " " + tipo.toString();
        }
        if (tamanho != null) {
            if (tamanho > 0) {
                retorno += " (" + tamanho;
                if (precisao != null) {
                    if (precisao > 0) {
                        retorno += "," + precisao;
                    }
                }
                retorno += ")";
            }
        }
        if (obrigatorio != null) {
            if (obrigatorio) {
                retorno += " {Obrigatório}";
            }
        }
        if (chave != null) {
            if (chave) {
                retorno += " {Chave}";
            }
        }
        return retorno;
    }
}
