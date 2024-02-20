package pin.modamk;

import java.util.ArrayList;
import java.util.List;
import pin.libamg.Edt;
import pin.libamk.Botao;
import pin.libbas.Conferencia;
import pin.libbas.Dados;
import pin.libjan.TrataIntf;

public class RelatorioFiltroCampo {

    public Integer abaIndice;
    public String abaNome;
    public Integer linha;
    public Integer coluna;
    public String tabela;
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
    public String dica;

    public String valorPadrao;
    public Object valorInicial;
    public Object valorFixo;
    public Object valorSalvo;

    public Boolean editavel;
    public Edt editor;

    public Boolean chave;
    public Boolean estrangeiro;

    public String operador;
    public Boolean automatico;

    public List<Conferencia> conferencias;
    public List<Botao> botoes;

    public Boolean podeFiltrar;
    public Boolean podeOrdenar;

    public Integer indiceFiltro;

    public RelatorioFiltroCampo(Integer naLinha, Integer naColuna, String comNome, String eTitulo, Dados.Tp eTipo) {
        this(naLinha, naColuna, "", comNome, eTitulo, eTipo);
    }

    public RelatorioFiltroCampo(Integer naLinha, Integer naColuna, String daTabela, String comNome, String eTitulo, Dados.Tp eTipo) {
        abaIndice = 0;
        abaNome = "";
        linha = naLinha;
        coluna = naColuna;
        tabela = daTabela;
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
        dica = null;
        valorPadrao = "";
        valorInicial = null;
        valorFixo = null;
        valorSalvo = null;
        editavel = true;
        chave = false;
        estrangeiro = false;

        operador = null;
        automatico = true;

        conferencias = null;
        conferencias = null;

        podeFiltrar = true;
        podeOrdenar = true;
    }

    public RelatorioFiltroCampo(Integer naAbaIndice, String comAbaNome, Integer naLinha, Integer naColuna, String comNome, String eTitulo, Dados.Tp eTipo) {
        this(naAbaIndice, comAbaNome, naLinha, naColuna, "", comNome, eTitulo, eTipo);
    }

    public RelatorioFiltroCampo(Integer naAbaIndice, String comAbaNome, Integer naLinha, Integer naColuna, String daTabela, String comNome, String eTitulo, Dados.Tp eTipo) {
        abaIndice = naAbaIndice;
        abaNome = comAbaNome;
        linha = naLinha;
        coluna = naColuna;
        tabela = daTabela;
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
        dica = null;
        valorPadrao = "";
        valorInicial = null;
        valorFixo = null;
        valorSalvo = null;
        editavel = true;
        chave = false;
        estrangeiro = false;
        operador = null;
        conferencias = null;
        conferencias = null;

        podeFiltrar = true;
        podeOrdenar = true;
    }

    public RelatorioFiltroCampo botaTitulo(String oTitulo) {
        titulo = oTitulo;
        return this;
    }

    public RelatorioFiltroCampo botaTamanho(Integer oTamanho) {
        tamanho = oTamanho;
        return this;
    }

    public RelatorioFiltroCampo botaPrecisao(Integer aPrecisao) {
        precisao = aPrecisao;
        return this;
    }

    public RelatorioFiltroCampo botaLargura(Integer aLargura) {
        largura = aLargura;
        return this;
    }

    public RelatorioFiltroCampo botaAltura(Integer aAltura) {
        altura = aAltura;
        return this;
    }

    public RelatorioFiltroCampo botaObrigatorio() {
        obrigatorio = true;
        return this;
    }

    public RelatorioFiltroCampo mudaObrigatorio(Boolean para) {
        obrigatorio = para;
        return this;
    }

    public RelatorioFiltroCampo botaOpcoes(Object... asOpcoes) {
        opcoes = asOpcoes;
        return this;
    }

    public RelatorioFiltroCampo botaOpcoes(Class daClasse) {
        return botaOpcoes(TrataIntf.pegaOpcoes(daClasse));
    }

    public RelatorioFiltroCampo botaParametros(Object... osParametros) {
        parametros = osParametros;
        return this;
    }

    public RelatorioFiltroCampo botaDica(String aDica) {
        dica = aDica;
        return this;
    }

    public RelatorioFiltroCampo botaPadrao(String oPadrao) {
        valorPadrao = oPadrao;
        return this;
    }

    public RelatorioFiltroCampo botaInicial(Object oInicial) {
        valorInicial = oInicial;
        return this;
    }

    public RelatorioFiltroCampo botaFixo(Object oFixo) {
        valorFixo = oFixo;
        return this;
    }

    public RelatorioFiltroCampo mudaEditavel(Boolean para) {
        editavel = para;
        return this;
    }

    public RelatorioFiltroCampo botaChave() {
        chave = true;
        return this;
    }

    public RelatorioFiltroCampo mudaChave(Boolean para) {
        chave = para;
        return this;
    }

    public RelatorioFiltroCampo botaEstrangeiro() {
        estrangeiro = true;
        return this;
    }

    public RelatorioFiltroCampo mudaEstrangeiro(Boolean para) {
        estrangeiro = para;
        return this;
    }

    public RelatorioFiltroCampo botaConferencia(Conferencia aConferencia) {
        if (conferencias == null) {
            conferencias = new ArrayList<>();
        }

        conferencias.add(aConferencia);
        return this;
    }

    public RelatorioFiltroCampo botaBotao(Botao oBotao) {
        if (botoes == null) {
            botoes = new ArrayList<>();
        }

        botoes.add(oBotao);
        return this;
    }

    public RelatorioFiltroCampo botaOperador(String oOperador) {
        operador = oOperador;
        return this;
    }

    public RelatorioFiltroCampo tiraAutomatico() {
        automatico = false;
        return this;
    }

    public RelatorioFiltroCampo tiraFiltrar() {
        podeFiltrar = false;
        return this;
    }

    public RelatorioFiltroCampo tiraOrdenar() {
        podeOrdenar = false;
        return this;
    }

    public String pegaCriacao() {
        String retorno = nome;
        switch (tipo) {
            case Log:
            case Cr:
                retorno += " VARCHAR(1)";
                break;
            case Crs:
            case Sen:
                if (tamanho == 0) {
                    tamanho = 45;
                }
                retorno += " VARCHAR(" + tamanho + ")";
                break;
            case Enu:
                if (tamanho == 0) {
                    tamanho = 3;
                }
                retorno += " VARCHAR(" + tamanho + ")";
                break;
            case Sug:
                if (tamanho == 0) {
                    tamanho = 45;
                }
                retorno += " VARCHAR(" + tamanho + ")";
                break;
            case Int:
                retorno += " INTEGER";
                break;
            case IntLon:
                retorno += " BIGINT";
                break;
            case Num:
            case NumLon:
                if (tamanho == 0) {
                    tamanho = 12;
                }
                if (precisao == 0) {
                    tamanho = 3;
                }
                retorno += " NUMERIC(" + tamanho + "," + precisao + ")";
                break;
            case Dat:
                retorno += " DATE";
                break;
            case Hor:
                retorno += " TIME";
                break;
            case DatHor:
                retorno += " TIMESTAMP";
                break;
            case Lis:
            case Tex:
            case Ima:
            case Doc:
            case Pla:
            case Aud:
            case Vid:
            case Frm:
            case Apr:
            case Arq:
                retorno += " BYTEA";
                break;
        }

        if (!valorPadrao.isEmpty()) {
            retorno += " DEFAULT " + valorPadrao;
        }

        return retorno;
    }
}
