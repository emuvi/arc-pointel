package pin.modamk;

import java.awt.print.PrinterJob;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Locale;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.JobName;
import javax.swing.JFileChooser;
import org.apache.commons.lang3.StringUtils;
import pin.libetf.TexEtf;
import pin.libutl.Utl;
import pin.libutl.UtlBin;
import pin.libutl.UtlCrs;
import pin.libutl.UtlLis;
import pin.libvlr.Vlr;
import pin.libvlr.Vlrs;

public class RelatorioMatricial {

    public Relatorio relatorio;
    public ArrayList<String> linhas;
    public Vlrs dadosCorrente;
    public Vlrs dadosFuncoes;

    public RelatorioMatricial(Relatorio aRelatorio) {
        relatorio = aRelatorio;
        linhas = new ArrayList<>();
        dadosCorrente = null;
        dadosFuncoes = null;
    }

    public void botaValor(Integer aLinha, Integer aColuna, String aValor) {
        while (aLinha > linhas.size() - 1) {
            linhas.add("");
        }

        String linha = linhas.get(aLinha);

        while (linha.length() < aColuna + aValor.length()) {
            linha = linha + " ";
        }

        StringBuilder strMk = new StringBuilder(linha);
        for (int i1 = 0; i1 < aValor.length(); i1++) {
            strMk.setCharAt(aColuna + i1, aValor.charAt(i1));
        }

        linha = strMk.toString();

        if (relatorio.paginaLargura > 0) {
            if (linha.length() > relatorio.paginaLargura) {
                linha = linha.substring(0, relatorio.paginaLargura);
            }
        }

        linhas.set(aLinha, linha);
    }

    private Object pegaValorDado(String aReferencia) {
        Object retorno = null;
        String referencia = aReferencia.trim();
        if (referencia.startsWith("'")) {
            retorno = UtlCrs.pEntreAspasSimples(referencia);
        } else {
            retorno = dadosCorrente.pgVlr(referencia);
        }
        return retorno;
    }

    private Object[] pegaValoresDados(String aReferencia) {
        Object[] retorno = new Object[0];
        if (aReferencia != null) {
            String referencia = aReferencia.trim();
            if (!referencia.isEmpty()) {
                if (referencia.startsWith("'")) {
                    retorno = UtlCrs.pEntreAspasSimples(referencia).split(";");
                } else {
                    String proc[] = referencia.split(";");
                    if (proc != null) {
                        retorno = new Object[proc.length];
                        for (int i1 = 0; i1 < proc.length; i1++) {
                            retorno[i1] = dadosCorrente.pgVlr(proc[i1]);
                        }
                    }
                }
            }
        }
        return retorno;
    }

    private Object pegaValorBruto(RelatorioCampo aCampo) {
        Object retorno = null;
        try {
            if (aCampo.tipo == RelatorioCampo.Tp.TEXTO) {
                retorno = aCampo.referencia;
            } else if (aCampo.tipo == RelatorioCampo.Tp.PARAMETRO) {
                retorno = relatorio.pegaParametroValor(aCampo.referencia);
            } else if (aCampo.tipo == RelatorioCampo.Tp.FUNCAO) {
                retorno = dadosFuncoes.pgVlr(aCampo.referencia);
            } else if (aCampo.tipo == RelatorioCampo.Tp.DADO) {
                retorno = pegaValorDado(aCampo.referencia);
            } else if (aCampo.tipo == RelatorioCampo.Tp.CLASSE) {
                Class cls = Class.forName(aCampo.classe);
                if (cls != null) {
                    Object[] valDads = pegaValoresDados(aCampo.referencia);

                    Object[] valDadsOp1 = new Object[valDads.length + 1];
                    UtlLis.copia(valDads, valDadsOp1);
                    valDadsOp1[valDadsOp1.length - 1] = relatorio;

                    Object[] valDadsOp2 = new Object[valDads.length + 1];
                    UtlLis.copia(valDads, valDadsOp2);
                    valDadsOp2[valDadsOp2.length - 1] = dadosCorrente;

                    Object[] valDadsOp3 = new Object[valDads.length + 2];
                    UtlLis.copia(valDads, valDadsOp3);
                    valDadsOp3[valDadsOp3.length - 2] = relatorio;
                    valDadsOp3[valDadsOp3.length - 1] = dadosCorrente;

                    Method mtd = null;
                    Object[] pars = null;

                    for (Method mtp : cls.getMethods()) {
                        if (mtp.getName().equals(aCampo.classeFuncao)) {
                            if (UtlBin.compara(mtp.getParameters(), valDads)) {
                                mtd = mtp;
                                pars = valDads;
                                break;
                            } else if (UtlBin.compara(mtp.getParameters(), valDadsOp1)) {
                                mtd = mtp;
                                pars = valDadsOp1;
                                break;
                            } else if (UtlBin.compara(mtp.getParameters(), valDadsOp2)) {
                                mtd = mtp;
                                pars = valDadsOp2;
                                break;
                            } else if (UtlBin.compara(mtp.getParameters(), valDadsOp3)) {
                                mtd = mtp;
                                pars = valDadsOp3;
                                break;
                            }
                        }
                    }

                    if (mtd != null) {
                        if (pars != null) {
                            retorno = mtd.invoke(cls, pars);
                        } else {
                            retorno = mtd.invoke(cls);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            Utl.registra(ex, "campo referencia = " + aCampo.referencia);
        }
        return retorno;
    }

    private String pegaValor(RelatorioCampo aCampo) {
        String retorno = null;
        try {
            Object valor = pegaValorBruto(aCampo);
            retorno = aCampo.formata(valor);
            retorno = StringUtils.stripAccents(retorno);
            retorno = UtlCrs.pegaSomenteNormais(retorno);

            if (aCampo.inicio > 0) {
                if (aCampo.inicio <= retorno.length()) {
                    retorno = retorno.substring(aCampo.inicio);
                } else {
                    retorno = "";
                }
            }

            if (aCampo.tamanho > 0) {
                if (retorno.length() > aCampo.tamanho) {
                    retorno = retorno.substring(0, aCampo.tamanho);
                }
            }

            if (aCampo.aDireita) {
                while (retorno.length() < aCampo.tamanho) {
                    retorno = " " + retorno;
                }
            } else if (aCampo.aoCentro) {
                int achegar = (aCampo.tamanho - retorno.length()) / 2;
                retorno = UtlCrs.fazCaracteres(' ', achegar) + retorno;
            }
        } catch (Exception ex) {
            Utl.registra(ex);
        }
        return retorno;
    }

    public Object getValorFuncaoParametro(String aParametro) {
        Object retorno = null;
        if (aParametro.startsWith("<")) {
            aParametro = UtlCrs.cortaAFrente(aParametro, 1);
            aParametro = UtlCrs.corta(aParametro, 1);
            retorno = pegaValorBruto(relatorio.pegaCampo(aParametro));
        } else {
            retorno = pegaValorDado(aParametro);
        }
        return retorno;
    }

    private void procFuncao(String aReferencia, String aFuncao, String[] aParametros) {
        if (aFuncao.equals("somar")) {
            Object anterior = dadosFuncoes.pgVlr(aReferencia);
            Object atual = getValorFuncaoParametro(aParametros[0]);
            Object atualizado = Utl.soma(anterior, atual);
            dadosFuncoes.muda(aReferencia, Vlr.novo(atualizado));
        }
    }

    public void produzir() {
        try {
            if (relatorio.campos == null) {
                return;
            }

            int tamInicio = 0;
            int tamCabecalho = 0;
            int tamDetalhes = 0;
            int tamRodape = 0;
            int tamFim = 0;

            ArrayList<RelatorioCampo> cmpsInicio = new ArrayList<>();
            ArrayList<RelatorioCampo> cmpsCabecalho = new ArrayList<>();
            ArrayList<RelatorioCampo> cmpsDetalhes = new ArrayList<>();
            ArrayList<RelatorioCampo> cmpsRodape = new ArrayList<>();
            ArrayList<RelatorioCampo> cmpsFim = new ArrayList<>();

            for (RelatorioCampo cmp : relatorio.campos) {
                switch (cmp.area) {
                    case INICIO_RELATORIO:
                        if (cmp.linha > tamInicio) {
                            tamInicio = cmp.linha;
                        }
                        cmpsInicio.add(cmp);
                        break;
                    case INICIO_PAGINA:
                        if (cmp.linha > tamCabecalho) {
                            tamCabecalho = cmp.linha;
                        }
                        cmpsCabecalho.add(cmp);
                        break;
                    case DADOS_DETALHES:
                        if (cmp.linha > tamDetalhes) {
                            tamDetalhes = cmp.linha;
                        }
                        cmpsDetalhes.add(cmp);
                        break;
                    case FIM_PAGINA:
                        if (cmp.linha > tamRodape) {
                            tamRodape = cmp.linha;
                        }
                        cmpsRodape.add(cmp);
                        break;
                    case FIM_RELATORIO:
                        if (cmp.linha > tamFim) {
                            tamFim = cmp.linha;
                        }
                        cmpsFim.add(cmp);
                        break;
                }
            }

            boolean impInicio = true;
            boolean impCabecalho = true;

            int linTot = 0;
            int linPag = 0;

            dadosFuncoes = new Vlrs();

            while (relatorio.resultados.proximo()) {
                dadosCorrente = relatorio.resultados.pegaLinha();

                for (RelatorioCampo cmp : relatorio.campos) {
                    if (cmp.tipo == RelatorioCampo.Tp.FUNCAO) {
                        procFuncao(cmp.referencia, cmp.funcao, cmp.funcaoParametros);
                    }
                }

                if (impInicio) {
                    for (RelatorioCampo cmp : cmpsInicio) {
                        botaValor(cmp.linha - 1 + linTot, cmp.coluna - 1, pegaValor(cmp));
                    }

                    linTot = linTot + tamInicio;
                    linPag = linPag + tamInicio;
                    impInicio = false;
                }

                if (impCabecalho) {
                    for (RelatorioCampo cmp : cmpsCabecalho) {
                        botaValor(cmp.linha - 1 + linTot, cmp.coluna - 1, pegaValor(cmp));
                    }

                    linTot = linTot + tamCabecalho;
                    linPag = linPag + tamCabecalho;
                    impCabecalho = false;
                }

                for (RelatorioCampo cmp : cmpsDetalhes) {
                    botaValor(cmp.linha - 1 + linTot, cmp.coluna - 1, pegaValor(cmp));
                }

                linTot = linTot + tamDetalhes;
                linPag = linPag + tamDetalhes;

                if (linPag + tamRodape > relatorio.paginaAltura) {
                    for (RelatorioCampo cmp : cmpsRodape) {
                        botaValor(cmp.linha - 1 + linTot, cmp.coluna - 1, pegaValor(cmp));
                    }

                    linTot = linTot + tamRodape;
                    linPag = 0;
                    impCabecalho = true;
                }
            }

            if (!impInicio) {
                if (impCabecalho) {
                    if (tamCabecalho > 0) {
                        int tamMove = tamDetalhes + tamRodape;

                        String[] mvDet = new String[tamMove];
                        for (int i1 = 0; i1 < tamMove; i1++) {
                            int i2 = linhas.size() - (1 + (tamMove - i1));
                            mvDet[i1] = linhas.get(i2);
                            linhas.set(i2, "");
                        }

                        linTot = (linTot - linPag) + (relatorio.paginaAltura); // pular página
                        linPag = 0;

                        for (RelatorioCampo cmp : cmpsCabecalho) {
                            botaValor(cmp.linha - 1 + linTot, cmp.coluna - 1, pegaValor(cmp));
                        }

                        linTot = linTot + tamCabecalho;
                        linPag = linPag + tamCabecalho;
                        impCabecalho = false;

                        for (int i1 = 0; i1 < tamMove - tamRodape; i1++) {
                            botaValor(i1 + linTot, 0, mvDet[i1]);
                        }

                        linTot = linTot + tamMove - tamRodape;
                        linPag = linPag + tamMove - tamRodape;
                    }
                } else if (linPag + tamRodape + tamFim > relatorio.paginaAltura) {
                    String[] mvDet = new String[tamDetalhes];
                    for (int i1 = 0; i1 < tamDetalhes; i1++) {
                        int i2 = linhas.size() - (1 + (tamDetalhes - i1));
                        mvDet[i1] = linhas.get(i2);
                        linhas.set(i2, "");
                    }

                    linTot = (linTot - linPag) + (relatorio.paginaAltura); // pular página
                    linPag = 0;

                    for (RelatorioCampo cmp : cmpsCabecalho) {
                        botaValor(cmp.linha - 1 + linTot, cmp.coluna - 1, pegaValor(cmp));
                    }

                    linTot = linTot + tamCabecalho;
                    linPag = linPag + tamCabecalho;
                    impCabecalho = false;

                    for (int i1 = 0; i1 < tamDetalhes; i1++) {
                        botaValor(i1 + linTot, 0, mvDet[i1]);
                    }

                    linTot = linTot + tamDetalhes;
                    linPag = linPag + tamDetalhes;
                }

                int iniRod = (linTot - linPag) + (relatorio.paginaAltura - (tamRodape + tamFim));
                int iniFim = (linTot - linPag) + (relatorio.paginaAltura - (tamFim));

                for (RelatorioCampo cmp : cmpsRodape) {
                    botaValor(cmp.linha - 1 + iniRod, cmp.coluna - 1, pegaValor(cmp));
                }

                for (RelatorioCampo cmp : cmpsFim) {
                    botaValor(cmp.linha - 1 + iniFim, cmp.coluna - 1, pegaValor(cmp));
                }
            }
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }

    public void imprimir() {
        try {
            String relatorioFinal = "";

            if (relatorio.condensado) {
                relatorioFinal = CMD_CONDENSADO;
            } else {
                relatorioFinal = CMD_NORMAL;
            }

            for (String linha : linhas) {
                relatorioFinal = relatorioFinal + UtlCrs.corta(linha, relatorio.paginaLargura) + CMD_RETURN + CMD_NEWLINE;
            }

            String relCops = relatorioFinal;

            relatorioFinal = "";

            for (int i1 = 0; i1 < relatorio.copias; i1++) {
                relatorioFinal = relatorioFinal + relCops;
            }

            if (relatorio.saida.equals("Na Tela")) {
                new TexEtf().mostra("Relatório " + relatorio.titulo).abre(relatorioFinal);
            } else if (relatorio.saida.equals("No Arquivo")) {
                JFileChooser chooser = new JFileChooser();
                if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    String flRel = chooser.getSelectedFile().getPath();
                    if (flRel.lastIndexOf('.') == -1) {
                        flRel = flRel + ".txt";
                    } else if (!flRel.substring(flRel.lastIndexOf('.')).equalsIgnoreCase(".txt")) {
                        flRel = flRel + ".txt";
                    }
                    File flSave = new File(flRel);
                    flSave.delete();
                    Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(flSave), "UTF-8"));
                    try {
                        out.write(relatorioFinal);
                    } finally {
                        out.close();
                    }
                }
            } else {
                DocPrintJob docPrinter = null;
                PrintService[] prSers = PrinterJob.lookupPrintServices();
                for (PrintService prSer : prSers) {
                    if (prSer.getName().equalsIgnoreCase(relatorio.saida)) {
                        docPrinter = prSer.createPrintJob();
                        break;
                    }
                }

                PrintRequestAttributeSet attr = new HashPrintRequestAttributeSet();
                attr.add(new JobName(relatorio.titulo, Locale.getDefault()));
                DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
                byte[] baDoc = relatorioFinal.getBytes("UTF-8");
                Doc doc = new SimpleDoc(baDoc, flavor, null);

                docPrinter.print(doc, attr);
            }
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }

    private static final String CMD_ESCAPE = "\u001B";      //escape
    private static final String CMD_AT = (char) 64 + "";  //@
    private static final String CMD_NEWLINE = (char) 10 + "";  //line feed/new line
    private static final String CMD_LEFTPARENTS = (char) 40 + "";  // Parenthesis Left
    private static final String CMD_BACKSLASH = (char) 92 + "";  // Backslash
    private static final String CMD_RETURN = (char) 13 + "";  //carriage return
    private static final String CMD_HORIZONTALTAB = (char) 9 + "";   //horizontal tab
    private static final String CMD_ADVANCEVERTICAL = (char) 74 + "";  //used for advancing paper vertically
    private static final String CMD_FORMFEED = (char) 12 + "";  //form feed  // avança uma página
    private static final String CMD_CONDENSADO = (char) 15 + "";  //condense
    private static final String CMD_NORMAL = (char) 18 + "";  //normal
    private static final String CMD_PITCH10 = (char) 80 + "";  //10cpi pitch
    private static final String CMD_PITCH15 = (char) 103 + ""; //15cpi pitch
    private static final String CMD_PITCHFIEXED = (char) 112 + ""; //used for choosing proportional mode or fixed-pitch
    private static final String CMD_STRINGACTER = (char) 116 + ""; //used for Stringacter set assignment/selection
    private static final String CMD_FONT = (char) 107 + ""; //used for font
    private static final String CMD_MARGINLEFT = (char) 108 + ""; //used for setting left margin
    private static final String CMD_MARGINRIGHT = (char) 81 + "";  //used for setting right margin
    private static final String CMD_RIGHTMARGIN = (char) 0 + "";  //used for setting right margin
    private static final String CMD_DRAFT = (char) 120 + ""; //used for setting draft or letter quality (LQ) printing
    private static final String CMD_PAGELENGTH = (char) 67 + "";  //used for page lenght
    private static final String CMD_BOLDON = (char) 69 + "";  //bold font on
    private static final String CMD_BOLDOFF = (char) 70 + "";  //bold font off
}
