package pin.modamk;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import pin.libamg.Edt;
import pin.libamk.Botao;
import pin.libbas.Conferencia;
import pin.libbas.Dados;
import pin.libutl.Utl;

public class CadCmp {

    public Integer abaIndice;
    public String abaNome;
    public Integer linha;
    public Integer coluna;
    public String nome;
    public String titulo;
    public Dados.Tp tipo;
    public Integer tamanho;
    public Integer precisao;
    public Integer largura;
    public Integer altura;

    public Boolean obrigatorio;
    public Object[] opcoes;
    public Object[] parametros;
    public Object[] restritos;
    public String dica;

    public String valorPadrao;
    public Object valorInicial;
    public Object valorFixo;
    public Object valorSalvo;

    public Edt editor;

    public Boolean chave;
    public Boolean estrangeiro;

    public List<Conferencia> conferencias;
    public List<Botao> botoes;

    public Boolean podeInserir;
    public Boolean podeEditar;

    public Boolean podeFiltrar;
    public Boolean podeOrdenar;

    public CadCmp(Integer naLinha, Integer naColuna, String comNome, String eTitulo, Dados.Tp eTipo) {
        abaIndice = 0;
        abaNome = "";
        linha = naLinha;
        coluna = naColuna;
        nome = comNome;
        titulo = eTitulo;
        tipo = eTipo;

        tamanho = 0;
        precisao = 0;
        largura = 0;
        altura = 0;

        obrigatorio = false;
        opcoes = null;
        parametros = null;
        restritos = null;
        dica = null;
        valorPadrao = "";
        valorInicial = null;
        valorFixo = null;
        valorSalvo = null;
        chave = false;
        estrangeiro = false;

        conferencias = null;
        botoes = null;

        podeInserir = true;
        podeEditar = true;

        podeFiltrar = true;
        podeOrdenar = true;
    }

    public CadCmp(String comAbaNome, Integer naLinha, Integer naColuna, String comNome, String eTitulo, Dados.Tp eTipo) {
        abaIndice = 0;
        abaNome = comAbaNome;
        linha = naLinha;
        coluna = naColuna;
        nome = comNome;
        titulo = eTitulo;
        tipo = eTipo;

        tamanho = 0;
        precisao = 0;
        largura = 0;
        altura = 0;

        obrigatorio = false;
        opcoes = null;
        parametros = null;
        restritos = null;
        dica = null;
        valorPadrao = "";
        valorInicial = null;
        valorFixo = null;
        valorSalvo = null;
        chave = false;
        estrangeiro = false;

        conferencias = null;
        botoes = null;

        podeInserir = true;
        podeEditar = true;

        podeFiltrar = true;
        podeOrdenar = true;
    }

    public CadCmp mTitulo(String oTitulo) {
        titulo = oTitulo;
        return this;
    }

    public CadCmp mTamanho(Integer oTamanho) {
        tamanho = oTamanho;
        return this;
    }

    public CadCmp mPrecisao(Integer aPrecisao) {
        precisao = aPrecisao;
        return this;
    }

    public CadCmp mTamanhoPrecisao(Integer oTamanho, Integer ePrecisao) {
        tamanho = oTamanho;
        precisao = ePrecisao;
        return this;
    }

    public CadCmp mDimensao(Integer aLargura, Integer eAltura) {
        largura = aLargura;
        altura = eAltura;
        return this;
    }

    public CadCmp mLargura(Integer aLargura) {
        largura = aLargura;
        return this;
    }

    public CadCmp mAltura(Integer aAltura) {
        altura = aAltura;
        return this;
    }

    public CadCmp botaObrigatorio() {
        obrigatorio = true;
        return this;
    }

    public CadCmp mObrigatorio(Boolean para) {
        obrigatorio = para;
        return this;
    }

    public CadCmp botaOpcoes(Object... asOpcoes) {
        opcoes = asOpcoes;
        return this;
    }

    public CadCmp botaParametros(Object... osParametros) {
        parametros = osParametros;
        return this;
    }

    public CadCmp botaRestritos(Object... osRestritos) {
        restritos = osRestritos;
        return this;
    }

    public CadCmp mDica(String aDica) {
        dica = aDica;
        return this;
    }

    public CadCmp mVlrPadrao(String oPadrao) {
        valorPadrao = oPadrao;
        return this;
    }

    public CadCmp mVlrInicial(Object oInicial) {
        valorInicial = oInicial;
        return this;
    }

    public CadCmp mVlrFixo(Object oFixo) {
        valorFixo = oFixo;
        return this;
    }

    public CadCmp botaChave() {
        chave = true;
        return this;
    }

    public CadCmp mChave(Boolean para) {
        chave = para;
        return this;
    }

    public CadCmp botaEstrangeiro() {
        estrangeiro = true;
        return this;
    }

    public CadCmp mEstrangeiro(Boolean para) {
        estrangeiro = para;
        return this;
    }

    public CadCmp botaConferencia(Conferencia aConferencia) {
        if (conferencias == null) {
            conferencias = new ArrayList<>();
        }
        conferencias.add(aConferencia);
        return this;
    }

    public CadCmp botaBotao(Botao oBotao) {
        if (botoes == null) {
            botoes = new ArrayList<>();
        }
        botoes.add(oBotao);
        return this;
    }

    public CadCmp podeInserir(Boolean condicao) {
        podeInserir = condicao;
        return this;
    }

    public CadCmp podeEditar(Boolean condicao) {
        podeEditar = condicao;
        return this;
    }

    public CadCmp tiraInserir() {
        podeInserir = false;
        return this;
    }

    public CadCmp tiraEditar() {
        podeEditar = false;
        return this;
    }

    public CadCmp tiraInserirEditar() {
        podeInserir = false;
        podeEditar = false;
        return this;
    }

    public CadCmp tiraFiltrar() {
        podeFiltrar = false;
        return this;
    }

    public CadCmp tiraOrdenar() {
        podeOrdenar = false;
        return this;
    }

    public CadCmp tiraFiltrarOrdenar() {
        podeFiltrar = false;
        podeOrdenar = false;
        return this;
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
