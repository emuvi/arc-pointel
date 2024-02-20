package pin.modamk;

import pin.libbas.Configs;
import pin.libbas.Globais;
import pin.libutl.UtlDat;
import pin.libutl.UtlHor;
import pin.modinf.Conexao;
import pin.libbas.Conjunto;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.Arrays;
import javax.print.PrintService;
import javax.swing.JComponent;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.datasource.DRDataSource;

public abstract class Relatorio {

    public Configs cfgs;
    public Conexao conexao;
    public String titulo;
    public Tipo tipo;
    public ArrayList<RelatorioPagina> paginas;

    public RelatorioPagina.Tamanho pagina;
    public RelatorioPagina.Orientacao orientacao;

    public PageType paginaGrafico;
    public PageOrientation orientacaoGrafica;

    public Integer paginaLargura;
    public Integer paginaAltura;

    public ArrayList<RelatorioCampo> campos;
    public ArrayList<RelatorioParametro> parametros;
    public ArrayList<RelatorioGrupo> grupos;

    public Conjunto resultados;
    public DRDataSource fonteDados;
    public String saida;
    public Integer copias;

    public Boolean condensado;

    public enum Tipo {
        Gra, Mat
    };

    public Relatorio(String aTitulo, Tipo aTipo, RelatorioPagina[] aPaginas, RelatorioCampo[] aCampos) {
        cfgs = ((Configs) Globais.pega("mainConf"));
        conexao = ((Conexao) Globais.pega("mainConc"));
        titulo = aTitulo;
        tipo = aTipo;
        paginas = new ArrayList<>(Arrays.asList(aPaginas));

        campos = new ArrayList<>(Arrays.asList(aCampos));
        parametros = new ArrayList<>();
        parametros.add(new RelatorioParametro("titulo", titulo));
        grupos = null;

        resultados = null;
        fonteDados = null;
        saida = "Na Tela";
        copias = 1;

        condensado = false;
    }

    public void botaRodapePadraoData() {
        botaRodapeData(1);
    }

    public void botaRodapeData(Integer naLinha) {
        campos.add(new RelatorioCampo(naLinha, 1, "Relatório de " + UtlDat.pegaAtual() + " às " + UtlHor.pegaAtual(), RelatorioCampo.Area.FIM_PAGINA, RelatorioCampo.Tp.TEXTO).botaAoCentro());
    }

    public void botaPagina(String aNome) {
        for (RelatorioPagina pag : paginas) {
            if (pag.nome.equals(aNome)) {
                pagina = pag.tamanho;
                orientacao = pag.orientacao;

                paginaGrafico = PageType.valueOf(pagina.toString());
                orientacaoGrafica = PageOrientation.valueOf(orientacao.toString());

                fazPaginaTamanho();
                break;
            }
        }
    }

    public String[] pegaPaginas() {
        String[] retorno = null;
        retorno = new String[paginas.size()];
        for (int i1 = 0; i1 < retorno.length; i1++) {
            retorno[i1] = paginas.get(i1).nome;
        }
        return retorno;
    }

    public String[] pegaSaidas() {
        String[] retorno = null;

        PrintService[] prtSrs = PrinterJob.lookupPrintServices();
        retorno = new String[prtSrs.length + 2];

        retorno[0] = "Na Tela";
        retorno[1] = "No Arquivo";

        int i1 = 2;
        for (PrintService prtSr : prtSrs) {
            retorno[i1] = prtSr.getName();
            i1++;
        }

        return retorno;
    }

    public void botaCondensado() {
        condensado = true;
    }

    private void fazPaginaTamanho() {
        Integer pagLettLargura = 612;
        Integer pagLettAltura = 792;

        Integer pagLettLarguraCrs = (condensado ? 140 : 80);
        Integer pagLettAlturaCrs = 66;

        Integer pagGrafLargura = paginaGrafico.getWidth();
        Integer pagGrafAltura = paginaGrafico.getHeight();

        if (tipo == Tipo.Gra) {
            paginaLargura = paginaGrafico.getWidth();
            paginaAltura = paginaGrafico.getHeight();
        } else if (tipo == Tipo.Mat) {
            switch (pagina) {
                case LETTER:
                    paginaLargura = pagLettLarguraCrs;
                    paginaAltura = pagLettAlturaCrs;
                    break;
                case HALFLETTER:
                    paginaLargura = pagLettLarguraCrs;
                    paginaAltura = pagLettAlturaCrs / 2;
                    break;
                case NOTE:
                case LEGAL:
                case A0:
                case A1:
                case A2:
                case A3:
                case A4:
                case A5:
                case A6:
                case A7:
                case A8:
                case A9:
                case A10:
                case B0:
                case B1:
                case B2:
                case B3:
                case B4:
                case B5:
                case B6:
                case B7:
                case B8:
                case B9:
                case B10:
                case C0:
                case C1:
                case C2:
                case C3:
                case C4:
                case C5:
                case C6:
                case C7:
                case C8:
                case C9:
                case C10:
                case ARCH_E:
                case ARCH_D:
                case ARCH_C:
                case ARCH_B:
                case ARCH_A:
                case FLSA:
                case FLSE:
                case LEDGER:
                case _11X17:
                    paginaLargura = pagGrafLargura * pagLettLarguraCrs / pagLettLargura;
                    paginaAltura = pagGrafAltura * pagLettAlturaCrs / pagLettAltura;
                    break;
            }
        }
    }

    private Boolean ehMenor(RelatorioCampo aoCampo, RelatorioCampo oCampo) {
        Boolean retorno = false;
        if (pegaGrupoIndice(oCampo.grupo) < pegaGrupoIndice(aoCampo.grupo)) {
            retorno = true;
        } else if (oCampo.linha < aoCampo.linha) {
            retorno = true;
        } else if (oCampo.linha == aoCampo.linha) {
            if (oCampo.coluna < aoCampo.coluna) {
                retorno = true;
            }
        }
        return retorno;
    }

    public ArrayList<RelatorioCampo> ordenar(ArrayList<RelatorioCampo> osCampos) {
        ArrayList<RelatorioCampo> retorno = new ArrayList<>();

        for (RelatorioCampo cmp : osCampos) {
            boolean ins = false;
            for (int iret = 0; iret < retorno.size(); iret++) {
                if (ehMenor(retorno.get(iret), cmp)) {
                    retorno.add(iret, cmp);
                    ins = true;
                    break;
                }
            }
            if (!ins) {
                retorno.add(cmp);
            }
        }

        return retorno;
    }

    private Boolean ehMenor(RelatorioGrupo aoGrupo, RelatorioGrupo oGrupo) {
        Boolean retorno = false;
        if (oGrupo.indice < aoGrupo.indice) {
            retorno = true;
        }
        return retorno;
    }

    public ArrayList<RelatorioGrupo> ordenarGrupos(ArrayList<RelatorioGrupo> osGrupos) {
        if (osGrupos == null) {
            return null;
        }

        ArrayList<RelatorioGrupo> retorno = new ArrayList<>();

        for (RelatorioGrupo cmp : osGrupos) {
            boolean ins = false;
            for (int iret = 0; iret < retorno.size(); iret++) {
                if (ehMenor(retorno.get(iret), cmp)) {
                    retorno.add(iret, cmp);
                    ins = true;
                    break;
                }
            }
            if (!ins) {
                retorno.add(cmp);
            }
        }

        return retorno;
    }

    public RelatorioCampo pegaCampo(String aReferencia) {
        RelatorioCampo retorno = null;
        for (RelatorioCampo rcmp : campos) {
            if (rcmp.referencia.equals(aReferencia)) {
                retorno = rcmp;
                break;
            }
        }
        return retorno;
    }

    public void limparParametros() {
        parametros.clear();
        botaParametro(new RelatorioParametro("titulo", titulo));
    }

    public Relatorio botaParametro(RelatorioParametro aParametro) {
        parametros.add(aParametro);
        return this;
    }

    public Relatorio botaParametro(String comNome, Object eValor) {
        parametros.add(new RelatorioParametro(comNome, eValor));
        return this;
    }

    public Relatorio botaParametro(String comNome, JComponent doEditor) {
        parametros.add(new RelatorioParametro(comNome, doEditor));
        return this;
    }

    public Relatorio mudaParametro(String comNome, Object paraValor) {
        for (RelatorioParametro par : parametros) {
            if (par.nome.equals(comNome)) {
                par.valor = paraValor;
            }
        }
        return this;
    }

    public Relatorio botaParametros(RelatorioParametro[] aParametros) {
        parametros.addAll(Arrays.asList(aParametros));
        return this;
    }

    public Object pegaParametroValor(String aNome) {
        return pegaParametroValor(aNome, null);
    }

    public Object pegaParametroValor(String aNome, Object aPadrao) {
        Object retorno = aPadrao;
        for (RelatorioParametro rpa : parametros) {
            if (rpa.nome.equals(aNome)) {
                retorno = rpa.valor;
                break;
            }
        }
        return retorno;
    }

    public void botaGrupo(RelatorioGrupo oGrupo) {
        if (grupos == null) {
            grupos = new ArrayList<>();
        }

        grupos.add(oGrupo);
    }

    public RelatorioGrupo pegaGrupo(String doCampo) {
        RelatorioGrupo retorno = null;
        if (grupos != null) {
            for (RelatorioGrupo rlg : grupos) {
                if (rlg.campo.equals(doCampo)) {
                    retorno = rlg;
                    break;
                }
            }
        }
        return retorno;
    }

    public Integer pegaGrupoIndice(String doCampo) {
        Integer retorno = -1;
        if (grupos != null) {
            for (RelatorioGrupo rlg : grupos) {
                if (rlg.campo.equals(doCampo)) {
                    retorno = rlg.indice;
                    break;
                }
            }
        }
        return retorno;
    }

    public abstract void produzir() throws Exception;

    public abstract void finalizar() throws Exception;

    public void imprimir() {
        if (tipo == Tipo.Mat) {
            RelatorioMatricial matricial = new RelatorioMatricial(this);
            matricial.produzir();
            matricial.imprimir();
        } else if (tipo == Tipo.Gra) {
            RelatorioGrafico grafio = new RelatorioGrafico(this);
            grafio.produzir();
            grafio.imprimir();
        }
    }

}
