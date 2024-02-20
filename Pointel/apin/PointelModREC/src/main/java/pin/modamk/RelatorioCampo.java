package pin.modamk;

import pin.libutl.UtlCrs;
import pin.libutl.UtlDat;
import pin.libutl.UtlDatHor;
import pin.libutl.UtlHor;
import pin.libutl.UtlInt;
import pin.libutl.UtlNumLon;
import pin.libutl.UtlTem;

public class RelatorioCampo {

    public Integer linha;
    public Integer coluna;
    public Integer inicio;
    public Integer tamanho;
    public String referencia;
    public Tp tipo;
    public Area area;
    public String grupo;

    public Formatos formato;
    public String formatar;

    public String classe;
    public String classeFuncao;

    public String funcao;
    public String[] funcaoParametros;

    public String fonte;

    public boolean justificado = false;
    public boolean aDireita = false;
    public boolean aoCentro = false;
    public boolean aoMeio = false;
    public boolean emBaixo = false;

    public boolean negrito = false;
    public boolean italico = false;
    public boolean sublinhado = false;
    public boolean riscado = false;

    public Integer margemEsquerda;
    public Integer margemEmCima;
    public Integer margemDireita;
    public Integer margemEmBaixo;

    public Integer largura;
    public Integer altura;

    public enum Tp {
        TEXTO, DADO, PARAMETRO, CLASSE, FUNCAO, FORMA
    };

    public enum Area {
        INICIO_RELATORIO, INICIO_PAGINA, INICIO_GRUPO, DADOS_DETALHES, FIM_GRUPO, FIM_PAGINA, FIM_RELATORIO
    };

    public enum Formatos {
        NENHUM, DATA, HORA, DATAHORA, INTEIRO, NUMERO_LON, CARACTERES
    };

    public RelatorioCampo(Integer naLinha, Integer naColuna, String comReferencia, Area naArea, Tp doTipo) {
        linha = naLinha;
        coluna = naColuna;
        inicio = 0;
        tamanho = 0;
        referencia = comReferencia;
        area = naArea;
        grupo = "";
        tipo = doTipo;

        formato = Formatos.NENHUM;
        formatar = "";

        classe = "";
        classeFuncao = "";

        funcao = "";
        funcaoParametros = null;

        justificado = false;
        aDireita = false;
        aoCentro = false;
        aoMeio = false;
        emBaixo = false;

        fonte = "";

        negrito = false;
        italico = false;
        sublinhado = false;
        riscado = false;

        margemEsquerda = 2;
        margemEmCima = 2;
        margemDireita = 2;
        margemEmBaixo = 2;

        largura = 0;
        altura = 0;
    }

    public RelatorioCampo(Integer naLinha, Integer naColuna, String comReferencia, Area naArea, String noGrupo, Tp doTipo) {
        linha = naLinha;
        coluna = naColuna;
        inicio = 0;
        tamanho = 0;
        referencia = comReferencia;
        area = naArea;
        grupo = noGrupo;
        tipo = doTipo;

        formato = Formatos.NENHUM;
        formatar = "";

        classe = "";
        classeFuncao = "";

        funcao = "";
        funcaoParametros = null;

        justificado = false;
        aDireita = false;
        aoCentro = false;
        aoMeio = false;
        emBaixo = false;

        fonte = "";

        negrito = false;
        italico = false;
        sublinhado = false;
        riscado = false;

        margemEsquerda = 2;
        margemEmCima = 2;
        margemDireita = 2;
        margemEmBaixo = 2;

        largura = 0;
        altura = 0;
    }

    public RelatorioCampo botaTamanho(Integer oTamanho) {
        tamanho = oTamanho;
        return this;
    }

    public RelatorioCampo botaInicio(Integer oInicio) {
        inicio = oInicio;
        return this;
    }

    public RelatorioCampo botaFormato(Formatos doTipo, String comoFormatar) {
        formato = doTipo;
        formatar = comoFormatar;
        return this;
    }

    public RelatorioCampo botaClasse(String doPacoteENome, String paraFuncao) {
        classe = doPacoteENome;
        classeFuncao = paraFuncao;
        return this;
    }

    public RelatorioCampo botaFuncao(String doNome, String comParametro) {
        funcao = doNome;
        funcaoParametros = new String[]{comParametro};
        return this;
    }

    public RelatorioCampo botaFuncao(String doNome, String[] comParametros) {
        funcao = doNome;
        funcaoParametros = comParametros;
        return this;
    }

    public RelatorioCampo botaJustificado() {
        justificado = true;
        return this;
    }

    public RelatorioCampo botaADireita() {
        aDireita = true;
        return this;
    }

    public RelatorioCampo botaAoCentro() {
        aoCentro = true;
        return this;
    }

    public RelatorioCampo botaAoMeio() {
        aoMeio = true;
        return this;
    }

    public RelatorioCampo botaEmBaixo() {
        emBaixo = true;
        return this;
    }

    public RelatorioCampo botaFonte(String aFonte) {
        fonte = aFonte;
        return this;
    }

    public RelatorioCampo botaFonteComSerifa() {
        fonte = "<com-serifa>";
        return this;
    }

    public RelatorioCampo botaFonteSemSerifa() {
        fonte = "<sem-serifa>";
        return this;
    }

    public RelatorioCampo botaFonteQuadrada() {
        fonte = "<quadrada>";
        return this;
    }

    public RelatorioCampo botaNegrito() {
        negrito = true;
        return this;
    }

    public RelatorioCampo botaItalico() {
        italico = true;
        return this;
    }

    public RelatorioCampo botaSublinhado() {
        sublinhado = true;
        return this;
    }

    public RelatorioCampo botaRiscado() {
        riscado = true;
        return this;
    }

    public RelatorioCampo botaMargens(Integer naEsquerda, Integer emCima, Integer naDireita, Integer emBaixo) {
        margemEsquerda = naEsquerda;
        margemEmCima = emCima;
        margemDireita = naDireita;
        margemEmBaixo = emBaixo;
        return this;
    }

    public RelatorioCampo botaLargura(Integer aLargura) {
        largura = aLargura;
        return this;
    }

    public RelatorioCampo botaAltura(Integer aAltura) {
        altura = aAltura;
        return this;
    }

    public String formata(Object oValor) {
        String retorno = "";
        if (oValor != null) {
            if (null != formato) {
                switch (formato) {
                    case NENHUM:
                        retorno = oValor.toString();
                        break;
                    case HORA:
                        retorno = UtlTem.formata(UtlHor.pega(oValor), formatar, "");
                        break;
                    case DATA:
                        retorno = UtlTem.formata(UtlDat.pega(oValor), formatar, "");
                        break;
                    case DATAHORA:
                        retorno = UtlTem.formata(UtlDatHor.pega(oValor), formatar, "");
                        break;
                    case INTEIRO:
                        Integer casas = null;
                        if (formatar != null) {
                            if (!formatar.isEmpty()) {
                                casas = formatar.length();
                            }
                        }
                        retorno = UtlInt.formata(UtlInt.pega(oValor), casas, "");
                        break;
                    case NUMERO_LON:
                        retorno = UtlNumLon.formata(UtlNumLon.pega(oValor), formatar, "");
                        break;
                    case CARACTERES:
                        retorno = UtlCrs.formata(oValor.toString(), formatar, "");
                        break;
                    default:
                        break;
                }
            }
        }
        return retorno;
    }

    public String pegaEstilo() {
        String retorno = "";
        if (negrito) {
            if (!retorno.isEmpty()) {
                retorno += " , ";
            }
            retorno += "Negrito";
        }
        if (italico) {
            if (!retorno.isEmpty()) {
                retorno += " , ";
            }
            retorno += "Itálico";
        }
        if (sublinhado) {
            if (!retorno.isEmpty()) {
                retorno += " , ";
            }
            retorno += "Sublinhado";
        }
        if (riscado) {
            if (!retorno.isEmpty()) {
                retorno += " , ";
            }
            retorno += "Riscado";
        }
        return retorno;
    }

    public String pegaAlinhamento() {
        String retorno = "aEsquerda";
        if (aoCentro) {
            retorno = "aoCentro";
        } else if (aDireita) {
            retorno = "aDireita";
        } else if (justificado) {
            retorno = "justificado";
        }
        return retorno;
    }

    public String pegaVertical() {
        String retorno = "emCima";
        if (emBaixo) {
            retorno = "emBaixo";
        } else if (aoMeio) {
            retorno = "aoCentro";
        }
        return retorno;
    }

    public String pegaMargem() {
        return UtlCrs.pega(margemEmCima, "") + " , " + UtlCrs.pega(margemDireita, "") + " , " + UtlCrs.pega(margemEmBaixo, "") + " , " + UtlCrs.pega(margemEsquerda, "");
    }

}
