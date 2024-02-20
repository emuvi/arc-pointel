package pin.modamk;

import pin.libjan.Janelas;
import pin.libutl.Utl;
import java.awt.Font;
import java.util.ArrayList;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.field;
import static net.sf.dynamicreports.report.builder.DynamicReports.grp;
import static net.sf.dynamicreports.report.builder.DynamicReports.margin;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;
import static net.sf.dynamicreports.report.builder.DynamicReports.variable;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.component.LineBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;
import net.sf.dynamicreports.report.builder.group.ColumnGroupBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.Calculation;
import net.sf.dynamicreports.report.constant.GroupHeaderLayout;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class RelatorioGrafico {

    public Relatorio relatorio;
    public JasperReportBuilder report;

    public RelatorioGrafico(Relatorio aRelatorio) {
        relatorio = aRelatorio;
    }

    private TextFieldBuilder fazCampo(RelatorioCampo aCampo) {
        TextFieldBuilder retorno = null;
        if (aCampo.tipo == RelatorioCampo.Tp.TEXTO) {
            retorno = cmp.text(aCampo.formata(aCampo.referencia));
        } else if (aCampo.tipo == RelatorioCampo.Tp.PARAMETRO) {
            retorno = cmp.text(aCampo.formata(relatorio.pegaParametroValor(aCampo.referencia)));
        } else if (aCampo.tipo == RelatorioCampo.Tp.DADO) {
            if (aCampo.formato == RelatorioCampo.Formatos.NENHUM) {
                retorno = cmp.text(field(aCampo.referencia, type.stringType())).setPattern(aCampo.formatar);
            } else if (aCampo.formato == RelatorioCampo.Formatos.CARACTERES) {
                retorno = cmp.text(field(aCampo.referencia, type.stringType())).setPattern(aCampo.formatar);
            } else if (aCampo.formato == RelatorioCampo.Formatos.DATA) {
                retorno = cmp.text(field(aCampo.referencia, type.dateType())).setPattern(aCampo.formatar);
            } else if (aCampo.formato == RelatorioCampo.Formatos.HORA) {
                retorno = cmp.text(field(aCampo.referencia, type.dateType())).setPattern(aCampo.formatar);
            } else if (aCampo.formato == RelatorioCampo.Formatos.INTEIRO) {
                retorno = cmp.text(field(aCampo.referencia, type.integerType())).setPattern(aCampo.formatar);
            } else if (aCampo.formato == RelatorioCampo.Formatos.NUMERO_LON) {
                retorno = cmp.text(field(aCampo.referencia, type.doubleType())).setPattern(aCampo.formatar);
            }
        } else if (aCampo.tipo == RelatorioCampo.Tp.FUNCAO) {
            if (aCampo.funcao.equals("somar")) {
                if (aCampo.formato == RelatorioCampo.Formatos.NENHUM) {
                    retorno = cmp.text(variable(aCampo.funcaoParametros[0], Double.class, Calculation.SUM));
                } else if (aCampo.formato == RelatorioCampo.Formatos.CARACTERES) {
                    retorno = cmp.text(variable(aCampo.funcaoParametros[0], String.class, Calculation.SUM)).setPattern(aCampo.formatar);
                } else if (aCampo.formato == RelatorioCampo.Formatos.DATA) {
                    retorno = cmp.text(variable(aCampo.funcaoParametros[0], java.sql.Date.class, Calculation.SUM)).setPattern(aCampo.formatar);
                } else if (aCampo.formato == RelatorioCampo.Formatos.HORA) {
                    retorno = cmp.text(variable(aCampo.funcaoParametros[0], java.sql.Time.class, Calculation.SUM)).setPattern(aCampo.formatar);
                } else if (aCampo.formato == RelatorioCampo.Formatos.INTEIRO) {
                    retorno = cmp.text(variable(aCampo.funcaoParametros[0], Integer.class, Calculation.SUM)).setPattern(aCampo.formatar);
                } else if (aCampo.formato == RelatorioCampo.Formatos.NUMERO_LON) {
                    retorno = cmp.text(variable(aCampo.funcaoParametros[0], Double.class, Calculation.SUM)).setPattern(aCampo.formatar);
                }
            } else if (aCampo.funcao.equals("contar")) {
                if (aCampo.formato == RelatorioCampo.Formatos.NENHUM) {
                    retorno = cmp.text(variable(aCampo.funcaoParametros[0], Double.class, Calculation.COUNT));
                } else if (aCampo.formato == RelatorioCampo.Formatos.CARACTERES) {
                    retorno = cmp.text(variable(aCampo.funcaoParametros[0], String.class, Calculation.COUNT)).setPattern(aCampo.formatar);
                } else if (aCampo.formato == RelatorioCampo.Formatos.DATA) {
                    retorno = cmp.text(variable(aCampo.funcaoParametros[0], java.sql.Date.class, Calculation.COUNT)).setPattern(aCampo.formatar);
                } else if (aCampo.formato == RelatorioCampo.Formatos.HORA) {
                    retorno = cmp.text(variable(aCampo.funcaoParametros[0], java.sql.Time.class, Calculation.COUNT)).setPattern(aCampo.formatar);
                } else if (aCampo.formato == RelatorioCampo.Formatos.INTEIRO) {
                    retorno = cmp.text(variable(aCampo.funcaoParametros[0], Integer.class, Calculation.COUNT)).setPattern(aCampo.formatar);
                } else if (aCampo.formato == RelatorioCampo.Formatos.NUMERO_LON) {
                    retorno = cmp.text(variable(aCampo.funcaoParametros[0], Double.class, Calculation.COUNT)).setPattern(aCampo.formatar);
                }
            }
        }

        StyleBuilder estilo = stl.style();

        estilo.setFontSize(9);
        estilo.setPadding(2);

        HorizontalTextAlignment hta = HorizontalTextAlignment.LEFT;
        if (aCampo.justificado) {
            hta = HorizontalTextAlignment.JUSTIFIED;
        } else if (aCampo.aoCentro) {
            hta = HorizontalTextAlignment.CENTER;
        } else if (aCampo.aDireita) {
            hta = HorizontalTextAlignment.RIGHT;
        }

        VerticalTextAlignment vta = VerticalTextAlignment.TOP;
        if (aCampo.aoMeio) {
            vta = VerticalTextAlignment.MIDDLE;
        } else if (aCampo.aDireita) {
            vta = VerticalTextAlignment.BOTTOM;
        }

        estilo.setTextAlignment(hta, vta);

        if (!aCampo.fonte.isEmpty()) {
            if (aCampo.fonte.equals("<com-serifa>")) {
                estilo.setFontName(Font.SERIF);
            } else if (aCampo.fonte.equals("<sem-serifa>")) {
                estilo.setFontName(Font.SANS_SERIF);
            } else if (aCampo.fonte.equals("<quadrada>")) {
                estilo.setFontName(Font.MONOSPACED);
            } else {
                estilo.setFontName(aCampo.fonte);
            }
        }

        if (aCampo.tamanho > 0) {
            estilo.setFontSize(aCampo.tamanho);
        }

        estilo.setBold(aCampo.negrito);
        estilo.setItalic(aCampo.italico);
        estilo.setUnderline(aCampo.sublinhado);
        estilo.setStrikeThrough(aCampo.riscado);

        estilo.setLeftPadding(aCampo.margemEsquerda);
        estilo.setTopPadding(aCampo.margemEmCima);
        estilo.setRightPadding(aCampo.margemDireita);
        estilo.setBottomPadding(aCampo.margemEmBaixo);

        retorno.setStyle(estilo);

        if (aCampo.largura > 0) {
            retorno.setFixedWidth(aCampo.largura);
        }
        if (aCampo.altura > 0) {
            retorno.setFixedHeight(aCampo.altura);
        }

        return retorno;
    }

    private void addCampoALinha(RelatorioCampo oCampo, ArrayList<HorizontalListBuilder> aLinhas) {
        while (aLinhas.size() - 1 < oCampo.linha - 1) {
            aLinhas.add(cmp.horizontalList());
        }

        if ((oCampo.tipo == RelatorioCampo.Tp.TEXTO)
                || (oCampo.tipo == RelatorioCampo.Tp.DADO)
                || (oCampo.tipo == RelatorioCampo.Tp.PARAMETRO)
                || (oCampo.tipo == RelatorioCampo.Tp.CLASSE)
                || (oCampo.tipo == RelatorioCampo.Tp.FUNCAO)) {
            aLinhas.get(oCampo.linha - 1).add(fazCampo(oCampo));
        } else if (oCampo.tipo == RelatorioCampo.Tp.FORMA) {
            if (oCampo.referencia.equals("linha")) {
                LineBuilder ln = cmp.line();
                if (oCampo.largura > 0) {
                    ln.setWidth(oCampo.largura);
                }
                if (oCampo.altura > 0) {
                    ln.setHeight(oCampo.altura);
                }
                aLinhas.get(oCampo.linha - 1).add(ln);
            }
        }
    }

    private void addCampoAoGrupoLinhas(RelatorioCampo oCampo, ArrayList<GrupoLinhas> aoGrupoLinhas) {
        RelatorioGrupo rgr = relatorio.pegaGrupo(oCampo.grupo);

        if (rgr == null) {
            Utl.alerta("Não foi possível encontrar o grupo : '" + oCampo.grupo + "' do campo: '" + oCampo.referencia + "'");
            return;
        }

        Boolean achou = false;
        for (GrupoLinhas glins : aoGrupoLinhas) {
            if (glins.campo.equals(rgr.campo)) {
                achou = true;
                addCampoALinha(oCampo, glins.linhas);
                break;
            }
        }

        if (!achou) {
            GrupoLinhas glin = new GrupoLinhas(rgr.indice, rgr.campo);
            aoGrupoLinhas.add(glin);
            addCampoALinha(oCampo, glin.linhas);
        }
    }

    private class GrupoLinhas {

        Integer indice;
        String campo;

        ArrayList<HorizontalListBuilder> linhas = new ArrayList<>();

        public GrupoLinhas(Integer noIndice, String doCampo) {
            indice = noIndice;
            campo = doCampo;

            linhas = new ArrayList<>();
        }
    }

    public void produzir() {
        report = report();
        report.setTemplate(RelatorioModelos.reportTemplate);
        report.setPageFormat(relatorio.paginaGrafico, relatorio.orientacaoGrafica);

        report.setPageMargin(margin(25));

        StyleBuilder textStyle = stl.style();
        textStyle.setFontSize(9);
        textStyle.setPadding(2);
        report.setTextStyle(textStyle);

        ArrayList<RelatorioCampo> cmpsIniRelatorio = new ArrayList<>();
        ArrayList<RelatorioCampo> cmpsIniPagina = new ArrayList<>();
        ArrayList<RelatorioCampo> cmpsIniGrupo = new ArrayList<>();
        ArrayList<RelatorioCampo> cmpsDetalhes = new ArrayList<>();
        ArrayList<RelatorioCampo> cmpsFimGrupo = new ArrayList<>();
        ArrayList<RelatorioCampo> cmpsFimPagina = new ArrayList<>();
        ArrayList<RelatorioCampo> cmpsFimRelatorio = new ArrayList<>();

        for (RelatorioCampo cmp : relatorio.campos) {
            if (cmp.area == RelatorioCampo.Area.INICIO_RELATORIO) {
                cmpsIniRelatorio.add(cmp);
            } else if (cmp.area == RelatorioCampo.Area.INICIO_PAGINA) {
                cmpsIniPagina.add(cmp);
            } else if (cmp.area == RelatorioCampo.Area.INICIO_GRUPO) {
                cmpsIniGrupo.add(cmp);
            } else if (cmp.area == RelatorioCampo.Area.DADOS_DETALHES) {
                cmpsDetalhes.add(cmp);
            } else if (cmp.area == RelatorioCampo.Area.FIM_GRUPO) {
                cmpsFimGrupo.add(cmp);
            } else if (cmp.area == RelatorioCampo.Area.FIM_PAGINA) {
                cmpsFimPagina.add(cmp);
            } else if (cmp.area == RelatorioCampo.Area.FIM_RELATORIO) {
                cmpsFimRelatorio.add(cmp);
            }
        }

        cmpsIniRelatorio = relatorio.ordenar(cmpsIniRelatorio);
        cmpsIniPagina = relatorio.ordenar(cmpsIniPagina);
        cmpsIniGrupo = relatorio.ordenar(cmpsIniGrupo);
        cmpsDetalhes = relatorio.ordenar(cmpsDetalhes);
        cmpsFimGrupo = relatorio.ordenar(cmpsFimGrupo);
        cmpsFimPagina = relatorio.ordenar(cmpsFimPagina);
        cmpsFimRelatorio = relatorio.ordenar(cmpsFimRelatorio);

        ArrayList<HorizontalListBuilder> linsIniRelatorio = new ArrayList<>();
        ArrayList<HorizontalListBuilder> linsIniPagina = new ArrayList<>();
        ArrayList<GrupoLinhas> grulinsIniGrupo = new ArrayList<>();
        ArrayList<HorizontalListBuilder> linsDetalhes = new ArrayList<>();
        ArrayList<GrupoLinhas> grulinsFimGrupo = new ArrayList<>();
        ArrayList<HorizontalListBuilder> grulinsFimPagina = new ArrayList<>();
        ArrayList<HorizontalListBuilder> linsFimRelatorio = new ArrayList<>();

        for (RelatorioCampo cmp : cmpsIniRelatorio) {
            addCampoALinha(cmp, linsIniRelatorio);
        }
        for (RelatorioCampo cmp : cmpsIniPagina) {
            addCampoALinha(cmp, linsIniPagina);
        }
        for (RelatorioCampo cmp : cmpsIniGrupo) {
            addCampoAoGrupoLinhas(cmp, grulinsIniGrupo);
        }
        for (RelatorioCampo cmp : cmpsDetalhes) {
            addCampoALinha(cmp, linsDetalhes);
        }
        for (RelatorioCampo cmp : cmpsFimGrupo) {
            addCampoAoGrupoLinhas(cmp, grulinsFimGrupo);
        }
        for (RelatorioCampo cmp : cmpsFimPagina) {
            addCampoALinha(cmp, grulinsFimPagina);
        }
        for (RelatorioCampo cmp : cmpsFimRelatorio) {
            addCampoALinha(cmp, linsFimRelatorio);
        }

        ArrayList<RelatorioGrupo> ordGrupos = relatorio.ordenarGrupos(relatorio.grupos);
        ArrayList<ColumnGroupBuilder> grupos = new ArrayList<>();

        Boolean prm = true;
        if (ordGrupos != null) {
            for (RelatorioGrupo rgr : ordGrupos) {
                ColumnGroupBuilder gr = null;
                switch (rgr.formato) {
                    case DATA:
                    case HORA:
                    case DATAHORA:
                        gr = grp.group(col.column(rgr.campo, type.dateType()));
                        break;
                    case INTEIRO:
                        gr = grp.group(col.column(rgr.campo, type.integerType()));
                        break;
                    case NUMERO_LON:
                        gr = grp.group(col.column(rgr.campo, type.floatType()));
                        break;
                    case NENHUM:
                    case CARACTERES:
                    default:
                        gr = grp.group(col.column(rgr.campo, type.stringType()));
                        break;
                }

                gr.setHeaderLayout(GroupHeaderLayout.EMPTY);
                gr.setPadding(0);
                rgr.grupoGrafico = gr;
                grupos.add(gr);
                if (prm) {
                    report.groupBy(gr);
                    prm = false;
                } else {
                    report.addGroup(gr);
                }
            }
        }

        if (linsIniRelatorio.size() > 0) {
            VerticalListBuilder linhas = cmp.verticalList();
            for (HorizontalListBuilder lin : linsIniRelatorio) {
                linhas.add(lin);
            }

            report.title(linhas);
        }

        if (linsIniPagina.size() > 0) {
            VerticalListBuilder linhas = cmp.verticalList();
            for (HorizontalListBuilder lin : linsIniPagina) {
                linhas.add(lin);
            }

            report.pageHeader(linhas);
        }

        if (grulinsIniGrupo.size() > 0) {
            prm = true;

            for (GrupoLinhas glin : grulinsIniGrupo) {
                ColumnGroupBuilder cgb = null;

                for (RelatorioGrupo rgr : relatorio.grupos) {
                    if (rgr.campo.equals(glin.campo)) {
                        cgb = rgr.grupoGrafico;
                    }
                }

                VerticalListBuilder linhas = cmp.verticalList();
                for (HorizontalListBuilder lin : glin.linhas) {
                    linhas.add(lin);
                }

                if (prm) {
                    report.groupHeader(cgb, linhas);
                    prm = false;
                } else {
                    report.addGroupHeader(cgb, linhas);
                }
            }
        }

        if (linsDetalhes.size() > 0) {
            VerticalListBuilder linhas = cmp.verticalList();
            for (HorizontalListBuilder lin : linsDetalhes) {
                linhas.add(lin);
            }

            report.detail(linhas);
        }

        if (grulinsFimGrupo.size() > 0) {
            prm = true;

            for (GrupoLinhas glin : grulinsFimGrupo) {
                ColumnGroupBuilder cgb = null;

                for (RelatorioGrupo rgr : relatorio.grupos) {
                    if (rgr.campo.equals(glin.campo)) {
                        cgb = rgr.grupoGrafico;
                    }
                }

                VerticalListBuilder linhas = cmp.verticalList();
                for (HorizontalListBuilder lin : glin.linhas) {
                    linhas.add(lin);
                }

                if (prm) {
                    report.groupHeader(cgb, linhas);
                    prm = false;
                } else {
                    report.addGroupHeader(cgb, linhas);
                }
            }
        }

        if (grulinsFimPagina.size() > 0) {
            VerticalListBuilder linhas = cmp.verticalList();
            for (HorizontalListBuilder lin : grulinsFimPagina) {
                linhas.add(lin);
            }

            report.pageFooter(linhas);
        }

        if (linsFimRelatorio.size() > 0) {
            VerticalListBuilder linhas = cmp.verticalList();
            for (HorizontalListBuilder lin : linsFimRelatorio) {
                linhas.add(lin);
            }

            report.summary(linhas);
        }
    }

    public void imprimir() {
        try {
            if (relatorio.resultados != null) {
                relatorio.fonteDados = new DRDataSource(relatorio.resultados.pegaNomes());
                relatorio.resultados.reinicia();
                while (relatorio.resultados.proximo()) {
                    relatorio.fonteDados.add(relatorio.resultados.pegaLinha().paraPrimitivos());
                }
            }
            report.setDataSource(relatorio.fonteDados);

            JasperViewer jasperViewer = new JasperViewer(report.toJasperPrint(), false);
            jasperViewer.setTitle("Relatório " + relatorio.titulo);
            jasperViewer.setBounds(45, 45, 600, 400);

            Janelas.inicia(jasperViewer, relatorio.getClass());
            jasperViewer.setVisible(true);
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }
}
