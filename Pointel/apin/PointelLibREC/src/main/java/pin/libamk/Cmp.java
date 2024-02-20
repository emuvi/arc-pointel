package pin.libamk;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.swing.AbstractAction;
import pin.libamg.Edt;
import pin.libbas.Conferencia;
import pin.libbas.Dados;
import pin.libjan.TrataIntf;
import pin.libutl.Utl;

public class Cmp {

    private String pai;
    private Integer linha;
    private Integer coluna;
    private String nome;
    private String titulo;
    private Dados.Tp tipo;
    private Integer tamanho;
    private Integer precisao;
    private List<Object> opcoes;
    private List<Object> params;
    private List<Object> vlrsRestritos;
    private String vlrPadrao;
    private Object vlrInicial;
    private Object vlrFixo;
    private Object vlrSalvo;
    private Boolean obrigatorio;
    private String dica;
    private Integer largura;
    private Integer altura;
    private volatile Edt editor;
    private AbstractAction acao;
    private List<AcaoSe> acoesSe;
    private List<Botao> botoes;
    private List<Conferencia> conferencias;
    private HashMap<String, Object> detalhes;
    private Double ordem;

    public Cmp(Integer coluna, String nome, Dados.Tp tipo) {
        this(null, null, coluna, nome, null, tipo);
    }

    public Cmp(String pai, Integer coluna, String nome, Dados.Tp tipo) {
        this(pai, null, coluna, nome, null, tipo);
    }

    public Cmp(Integer coluna, String nome, String titulo, Dados.Tp tipo) {
        this(null, null, coluna, nome, titulo, tipo);
    }

    public Cmp(String pai, Integer coluna, String nome, String titulo, Dados.Tp tipo) {
        this(pai, null, coluna, nome, titulo, tipo);
    }

    public Cmp(Integer linha, Integer coluna, String nome, Dados.Tp tipo) {
        this(null, linha, coluna, nome, null, tipo);
    }

    public Cmp(String pai, Integer linha, Integer coluna, String nome, Dados.Tp tipo) {
        this(pai, linha, coluna, nome, null, tipo);
    }

    public Cmp(Integer linha, Integer coluna, String nome, String titulo, Dados.Tp tipo) {
        this(null, linha, coluna, nome, titulo, tipo);
    }

    public Cmp(String pai, Integer linha, Integer coluna, String nome, String titulo, Dados.Tp tipo) {
        this.pai = pai;
        this.linha = linha;
        this.coluna = coluna;
        this.nome = nome;
        this.titulo = titulo;
        this.tipo = tipo;
        tamanho = null;
        precisao = null;
        opcoes = null;
        params = null;
        vlrsRestritos = null;
        vlrPadrao = null;
        vlrInicial = null;
        vlrFixo = null;
        vlrSalvo = null;
        obrigatorio = false;
        dica = null;
        largura = null;
        altura = null;
        editor = null;
        botoes = null;
        detalhes = null;
        ordem = null;
    }

    public Boolean temPai() {
        return pai != null;
    }

    public String pPai() {
        return pai;
    }

    public Cmp mPai(String para) {
        pai = para;
        return this;
    }

    public Integer pLinha() {
        return linha;
    }

    public Cmp mLinha(Integer para) {
        linha = para;
        return this;
    }

    public Integer pColuna() {
        return coluna;
    }

    public Cmp mColuna(Integer para) {
        coluna = para;
        return this;
    }

    public String pNome() {
        return nome;
    }

    public Cmp mNome(String para) {
        nome = para;
        return this;
    }

    public String pTitulo() {
        return titulo;
    }

    public Cmp mTitulo(String para) {
        titulo = para;
        return this;
    }

    public Dados.Tp pTipo() {
        return tipo;
    }

    public Cmp mTipo(Dados.Tp para) {
        tipo = para;
        return this;
    }

    public Integer pTamanho() {
        return tamanho;
    }

    public Cmp mTamanho(Integer para) {
        tamanho = para;
        return this;
    }

    public Integer pPrecisao() {
        return precisao;
    }

    public Cmp mPrecisao(Integer para) {
        precisao = para;
        return this;
    }

    public Cmp mTamanhoPrecisao(Integer oTamanho, Integer ePrecisao) {
        tamanho = oTamanho;
        precisao = ePrecisao;
        return this;
    }

    public List<Object> pOpcoes() {
        return opcoes;
    }

    public Cmp botaOpcoes(Object... asOpcoes) {
        if (opcoes == null) {
            opcoes = new ArrayList<>();
        }
        opcoes.addAll(Arrays.asList(asOpcoes));
        return this;
    }

    public Cmp tiraOpcoes(Object... asOpcoes) {
        if (opcoes == null) {
            opcoes = new ArrayList<>();
        }
        opcoes.removeAll(Arrays.asList(asOpcoes));
        return this;
    }

    public Cmp botaOpcoes(Class daClasse) {
        return botaOpcoes(TrataIntf.pegaOpcoes(daClasse));
    }

    public Cmp limpaOpcoes() {
        if (opcoes != null) {
            opcoes.clear();
        }
        opcoes = null;
        return this;
    }

    public List<Object> pParams() {
        return params;
    }

    public Cmp botaParams(Object... osParametros) {
        if (params == null) {
            params = new ArrayList<>();
        }
        params.addAll(Arrays.asList(osParametros));
        return this;
    }

    public Cmp tiraParams(Object... osParametros) {
        if (params == null) {
            params = new ArrayList<>();
        }
        params.removeAll(Arrays.asList(osParametros));
        return this;
    }

    public Cmp limpaParams() {
        if (params != null) {
            params.clear();
        }
        params = null;
        return this;
    }

    public List<Object> pRestritos() {
        return vlrsRestritos;
    }

    public Cmp botaRestritos(Object... aosValores) {
        if (vlrsRestritos == null) {
            vlrsRestritos = new ArrayList<>();
        }
        vlrsRestritos.addAll(Arrays.asList(aosValores));
        return this;
    }

    public Cmp tiraRestritos(Object... aosValores) {
        if (vlrsRestritos == null) {
            vlrsRestritos = new ArrayList<>();
        }
        vlrsRestritos.removeAll(Arrays.asList(aosValores));
        return this;
    }

    public Cmp limpaRestritos() {
        if (vlrsRestritos != null) {
            vlrsRestritos.clear();
        }
        vlrsRestritos = null;
        return this;
    }

    public String pVlrPadrao() {
        return vlrPadrao;
    }

    public Cmp mVlrPadrao(String oPadrao) {
        vlrPadrao = oPadrao;
        return this;
    }

    public Object pVlrInicial() {
        return vlrInicial;
    }

    public Cmp mVlrInicial(Object oValor) {
        vlrInicial = oValor;
        return this;
    }

    public Object pVlrFixo() {
        return vlrFixo;
    }

    public Cmp mVlrFixo(Object oValor) {
        vlrFixo = oValor;
        return this;
    }

    public Object pVlrSalvo() {
        return vlrSalvo;
    }

    public Cmp mVlrSalvo(Object oValor) {
        vlrSalvo = oValor;
        return this;
    }

    public Boolean pObrigatorio() {
        return obrigatorio;
    }

    public Cmp mObrigatorio(Boolean para) {
        obrigatorio = para;
        return this;
    }

    public Cmp botaObrigatorio() {
        obrigatorio = true;
        return this;
    }

    public Cmp tiraObrigatorio() {
        obrigatorio = false;
        return this;
    }

    public String pDica() {
        return dica;
    }

    public Cmp mDica(String aDica) {
        dica = aDica;
        return this;
    }

    public Integer pLargura() {
        return largura;
    }

    public Cmp mLargura(Integer aLargura) {
        largura = aLargura;
        return this;
    }

    public Integer pAltura() {
        return altura;
    }

    public Cmp mAltura(Integer aAltura) {
        altura = aAltura;
        return this;
    }

    public Dimension pDimensao() {
        return new Dimension(largura, altura);
    }

    public Cmp mDimensao(Integer aLargura, Integer eAltura) {
        largura = aLargura;
        altura = eAltura;
        return this;
    }

    public synchronized Edt pEdt() {
        return editor;
    }

    public Cmp mEdt(Edt oEditor) {
        editor = oEditor;
        return this;
    }

    public AbstractAction pAcao() {
        return acao;
    }

    public Cmp mAcao(AbstractAction aAcao) {
        acao = aAcao;
        return this;
    }

    public List<AcaoSe> pAcoesSe() {
        return acoesSe;
    }

    public Cmp botaAcaoSe(AcaoSe aAcaoSe) {
        if (acoesSe == null) {
            acoesSe = new ArrayList<>();
        }
        acoesSe.add(aAcaoSe);
        return this;
    }

    public List<Botao> botoes() {
        return botoes;
    }

    public Cmp botaBotao(Botao oBotao) {
        if (botoes == null) {
            botoes = new ArrayList<>();
        }
        botoes.add(oBotao);
        return this;
    }

    public List<Conferencia> conferencia() {
        return conferencias;
    }

    public Cmp botaConferencia(Conferencia aConferencia) {
        if (conferencias == null) {
            conferencias = new ArrayList<>();
        }
        conferencias.add(aConferencia);
        return this;
    }

    public Cmp botaDetalhe(Object oDetalhe) {
        return botaDetalhe(null, oDetalhe);
    }

    public Cmp botaDetalhe(String comNome, Object oDetalhe) {
        if (detalhes == null) {
            detalhes = new HashMap<>();
        }
        if (comNome == null) {
            comNome = "Sem Nome " + (detalhes.size() + 1);
        }
        detalhes.put(comNome, oDetalhe);
        return this;
    }

    public Boolean temDetalhe(Class daClasse) {
        Boolean retorno = false;
        if (detalhes != null) {
            for (Map.Entry<String, Object> ent : detalhes.entrySet()) {
                if (daClasse.isInstance(ent.getValue())) {
                    retorno = true;
                    break;
                }
            }
        }
        return retorno;
    }

    public Boolean temDetalhe(String comNome) {
        Boolean retorno = false;
        if (detalhes != null) {
            retorno = detalhes.keySet().contains(comNome);
        }
        return retorno;
    }

    public <T> T pDetalhe(Class<? extends T> daClasse) {
        Object retorno = null;
        if (detalhes != null) {
            for (Map.Entry<String, Object> ent : detalhes.entrySet()) {
                if (daClasse.isInstance(ent.getValue())) {
                    retorno = ent.getValue();
                    break;
                }
            }
        }
        return (T) retorno;
    }

    public Object pDetalhe(String comNome) {
        Object retorno = null;
        if (detalhes != null) {
            retorno = detalhes.get(comNome);
        }
        return retorno;
    }

    public <T> T pDetalhe(String comNome, T ePadrao) {
        T retorno = ePadrao;
        if (detalhes != null) {
            retorno = (T) detalhes.getOrDefault(comNome, ePadrao);
        }
        return retorno;
    }

    public Double pOrdem() {
        return ordem;
    }

    public Cmp mOrdem(Double naOrdem) {
        ordem = naOrdem;
        return this;
    }

    public Edt criaEdt(Object comOrigem) throws Exception {
        Edt retorno = Edt.novo(titulo, obrigatorio, tipo, tamanho, precisao, opcoes, params, dica, acao, vlrInicial);
        retorno.mPai(pai);
        retorno.mPaiLocal(linha, coluna);
        retorno.mNome(nome);
        if (vlrFixo != null) {
            retorno.mFixo(vlrFixo);
            retorno.limpa();
        }
        if (largura != null) {
            retorno.mLargura(largura);
        }
        if (altura != null) {
            retorno.mAltura(altura);
        }
        if (botoes != null) {
            for (Botao botao : botoes) {
                retorno.botaBotao(botao, comOrigem);
            }
        }
        if (conferencias != null) {
            for (Conferencia cnf : conferencias) {
                retorno.botaConferencia(cnf);
            }
        }
        editor = retorno;
        return retorno;
    }

    public Boolean ehVlrIgual(Object aoValor) throws Exception {
        return ehVlrIgual(aoValor, null);
    }

    public Boolean ehVlrIgual(Object aoValor, Object comPadrao) throws Exception {
        Boolean retorno = false;
        if (editor != null) {
            Object atual = editor.pgVlr(comPadrao);
            retorno = Objects.equals(atual, aoValor);
        }
        return retorno;
    }

    public Boolean ehVlrMaior(Object aoValor) throws Exception {
        return ehVlrMaior(aoValor, null);
    }

    public Boolean ehVlrMaior(Object aoValor, Object comPadrao) throws Exception {
        Boolean retorno = false;
        if (editor != null) {
            Object atual = editor.pgVlr(comPadrao);
            retorno = Utl.ehMaior(atual, aoValor, false);
        }
        return retorno;
    }

    public Boolean ehVlrMenor(Object aoValor) throws Exception {
        return ehVlrMenor(aoValor, null);
    }

    public Boolean ehVlrMenor(Object aoValor, Object comPadrao) throws Exception {
        Boolean retorno = false;
        if (editor != null) {
            Object atual = editor.pgVlr(comPadrao);
            retorno = Utl.ehMenor(atual, aoValor, false);
        }
        return retorno;
    }

    public Boolean ehVlrMaiorOuIgual(Object aoValor) throws Exception {
        return ehVlrMaiorOuIgual(aoValor, null);
    }

    public Boolean ehVlrMaiorOuIgual(Object aoValor, Object comPadrao) throws Exception {
        return ehVlrMaior(aoValor, comPadrao) || ehVlrIgual(aoValor, comPadrao);
    }

    public Boolean ehVlrMenorOuIgual(Object aoValor) throws Exception {
        return ehVlrMaiorOuIgual(aoValor, null);
    }

    public Boolean ehVlrMenorOuIgual(Object aoValor, Object comPadrao) throws Exception {
        return ehVlrMenor(aoValor, comPadrao) || ehVlrIgual(aoValor, comPadrao);
    }

}
