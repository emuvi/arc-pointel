package pin.adm;

import pin.libbas.Dados;
import pin.libutl.UtlDat;
import pin.libutl.UtlInt;
import pin.libutl.UtlTem;
import pin.libvlr.Vlrs;
import pin.modamk.CadRelacao;
import pin.modamk.CadReferenciar;
import pin.modamk.Relatorio;
import pin.modamk.RelatorioCampo;
import pin.modamk.RelatorioFiltro;
import pin.modamk.RelatorioFiltroCampo;
import pin.modamk.RelatorioPagina;
import pin.modamk.RelatorioParametro;

public class RelCaixa extends Relatorio {

    public RelatorioFiltro filtro;

    public RelCaixa() {
        super("Caixa", Tipo.Gra, RelatorioPagina.padraoGrafico(), new RelatorioCampo[]{
            new RelatorioCampo(1, 1, "titulo", RelatorioCampo.Area.INICIO_RELATORIO, RelatorioCampo.Tp.PARAMETRO).botaFonteComSerifa().botaTamanho(30).botaNegrito().botaAoCentro().botaMargens(2, 2, 2, 10),
            new RelatorioCampo(2, 1, "subtitulo", RelatorioCampo.Area.INICIO_RELATORIO, RelatorioCampo.Tp.PARAMETRO).botaFonteComSerifa().botaTamanho(18).botaNegrito().botaAoCentro().botaMargens(2, 2, 2, 30),
            new RelatorioCampo(1, 1, "Data", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaNegrito().botaLargura(56),
            new RelatorioCampo(1, 2, "Histórico", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaNegrito(),
            new RelatorioCampo(1, 3, "Pessoa", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaNegrito(),
            new RelatorioCampo(1, 4, "Fatura", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaNegrito().botaLargura(60),
            new RelatorioCampo(1, 5, "Lançamento", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaNegrito().botaLargura(64),
            new RelatorioCampo(1, 6, "Entradas", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaNegrito().botaLargura(65),
            new RelatorioCampo(1, 7, "Saídas", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaNegrito().botaLargura(65),
            new RelatorioCampo(2, 1, "linha", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.FORMA),
            new RelatorioCampo(1, 1, "data", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaFormato(RelatorioCampo.Formatos.DATA, "dd/MM/yyyy").botaLargura(56),
            new RelatorioCampo(1, 2, "hist_nome", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO),
            new RelatorioCampo(1, 3, "pessoa", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO),
            new RelatorioCampo(1, 4, "ser_fatura", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaLargura(60),
            new RelatorioCampo(1, 5, "codigo", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaLargura(64),
            new RelatorioCampo(1, 5, "entrada", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaFormato(RelatorioCampo.Formatos.NUMERO_LON, "R$ #,##0.00").botaLargura(65),
            new RelatorioCampo(1, 7, "saida", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaFormato(RelatorioCampo.Formatos.NUMERO_LON, "R$ #,##0.00").botaLargura(65),
            new RelatorioCampo(2, 1, "linha", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.FORMA),
            new RelatorioCampo(1, 1, "Saldo Anterior:", RelatorioCampo.Area.FIM_RELATORIO, RelatorioCampo.Tp.TEXTO).botaNegrito().botaADireita(),
            new RelatorioCampo(1, 2, "saldo_anterior", RelatorioCampo.Area.FIM_RELATORIO, RelatorioCampo.Tp.PARAMETRO).botaFormato(RelatorioCampo.Formatos.NUMERO_LON, "R$ #,##0.00").botaLargura(100),
            new RelatorioCampo(2, 1, "Saldo Caixa:", RelatorioCampo.Area.FIM_RELATORIO, RelatorioCampo.Tp.TEXTO).botaNegrito().botaADireita(),
            new RelatorioCampo(2, 2, "saldo_caixa", RelatorioCampo.Area.FIM_RELATORIO, RelatorioCampo.Tp.FUNCAO).botaFuncao("somar", "valor").botaFormato(RelatorioCampo.Formatos.NUMERO_LON, "R$ #,##0.00").botaLargura(100),
            new RelatorioCampo(3, 1, "Saldo Atual:", RelatorioCampo.Area.FIM_RELATORIO, RelatorioCampo.Tp.TEXTO).botaNegrito().botaADireita(),
            new RelatorioCampo(3, 2, "saldo_atual", RelatorioCampo.Area.FIM_RELATORIO, RelatorioCampo.Tp.PARAMETRO).botaFormato(RelatorioCampo.Formatos.NUMERO_LON, "R$ #,##0.00").botaLargura(100),});

        botaRodapePadraoData();
    }

    @Override
    public void produzir() throws Exception {
        filtro = new RelatorioFiltro(this, "lancamentos", new RelatorioFiltroCampo[]{
            new RelatorioFiltroCampo(1, 1, "data", "Data De", Dados.Tp.Dat).botaObrigatorio().botaOperador(">="),
            new RelatorioFiltroCampo(1, 2, "data", "Data Até", Dados.Tp.Dat).botaObrigatorio().botaOperador("<="),
            new RelatorioFiltroCampo(2, 1, "pessoa", "Pessoa - Cód.", Dados.Tp.Crs).botaTamanho(8),
            new RelatorioFiltroCampo(2, 2, "pessoas.nome", "Pessoa - Nome", Dados.Tp.Crs).botaTamanho(80).botaEstrangeiro(),
            new RelatorioFiltroCampo(3, 1, "serie", "Serie", Dados.Tp.Crs).botaTamanho(4),
            new RelatorioFiltroCampo(3, 2, "fatura", "Fatura", Dados.Tp.Crs).botaTamanho(10),
            new RelatorioFiltroCampo(3, 3, "codigo", "Lançamento", Dados.Tp.Crs).botaTamanho(10),});

        filtro.botaEstrangeiro(new CadRelacao("pessoas").botaRelacao("pessoa", "codigo"));

        filtro.botaReferencia(new CadReferenciar(filtro, "pessoa", CadPessoas.class).botaChave("pessoa", "codigo").botaEstrangeiro("pessoas.nome", "nome"));

        filtro.botaOrdemTitulos("Data De;Serie;Fatura");
        filtro.abrir();
    }

    @Override
    public void finalizar() throws Exception {
        resultados = conexao.busca("SELECT DISTINCT ON (" + filtro.pegaOrdem("", "lancamentos.codigo").replaceAll("DESC", "") + ") "
                + "   lancamentos.data, historicos.nome AS hist_nome, pessoas.nome AS pessoa, "
                + "   CONCAT(lancamentos.serie, ' ', lancamentos.fatura) AS ser_fatura, lancamentos.codigo, lancamentos.valor, "
                + "   (CASE WHEN lancamentos.valor < 0 THEN NULL ELSE lancamentos.valor END) AS entrada, "
                + "   (CASE WHEN lancamentos.valor > 0 THEN NULL ELSE lancamentos.valor END) AS saida "
                + " FROM lancamentos "
                + "   LEFT JOIN pessoas ON pessoas.codigo = lancamentos.pessoa "
                + "   LEFT JOIN pessoas AS representantes ON representantes.codigo = lancamentos.representante "
                + "   LEFT JOIN historicos ON historicos.codigo = lancamentos.historico "
                + filtro.pegaCondicao("WHERE", "(lancamentos.tipo = 'E' OR lancamentos.tipo = 'S')")
                + filtro.pegaOrdem("ORDER BY", "lancamentos.codigo"),
                filtro.pegaParametros());

        Double saldoAnterior = 0.0;

        Integer dia = UtlTem.pegaDia(UtlDat.pega(filtro.pegaCampoPeloTitulo("Data De").editor.pgVlr()));
        Integer mes = UtlTem.pegaMes(UtlDat.pega(filtro.pegaCampoPeloTitulo("Data De").editor.pgVlr()));
        Integer ano = UtlTem.pegaAno(UtlDat.pega(filtro.pegaCampoPeloTitulo("Data De").editor.pgVlr()));

        if ((mes != null) && (ano != null)) {
            saldoAnterior = conexao.busca("SELECT saldo FROM saldos WHERE mes = ? AND ano = ?",
                    new Vlrs(mes, ano))
                    .pgCol().pgNumLon(0.0);

            if (dia - 1 > 0) {
                String dsDe = ano + "-" + UtlInt.formata(mes, 2) + "-" + "01";
                String dsAte = ano + "-" + UtlInt.formata(mes, 2) + "-" + UtlInt.formata(dia - 1, 2);
                java.sql.Date tsDe = UtlDat.pegaSQL(dsDe);
                java.sql.Date tsAte = UtlDat.pegaSQL(dsAte);
                if (tsAte != null) {
                    saldoAnterior += conexao.busca("SELECT SUM(valor) "
                            + " FROM lancamentos "
                            + " WHERE data >= ? AND data <= ? "
                            + "   AND ((tipo = 'E') OR (tipo = 'S'))",
                            new Vlrs(tsDe, tsAte))
                            .pgCol().pgNumLon(0.0);
                }
            }
        }

        Double saldoCaixa = conexao.busca("SELECT SUM(valor) "
                + " FROM lancamentos "
                + " WHERE data >= ? AND data <= ? "
                + "   AND ((tipo = 'E') OR (tipo = 'S'))",
                new Vlrs(filtro.pegaCampoPeloTitulo("Data De").editor.pgVlr(),
                        filtro.pegaCampoPeloTitulo("Data Até").editor.pgVlr()))
                .pgCol().pgNumLon(0.0);

        Double saldoAtual = saldoAnterior + saldoCaixa;

        limparParametros();
        botaParametro(new RelatorioParametro("subtitulo", "De " + filtro.pegaCampoPeloTitulo("Data De").editor.pgVlrDat().pgCrs() + " Até " + filtro.pegaCampoPeloTitulo("Data Até").editor.pgVlrDat().pgCrs()));
        botaParametro(new RelatorioParametro("saldo_anterior", saldoAnterior));
        botaParametro(new RelatorioParametro("saldo_atual", saldoAtual));

        imprimir();
    }

}
