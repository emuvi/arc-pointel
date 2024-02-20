package pin.adm;

import pin.libbas.Dados;
import pin.modamk.CadRelacao;
import pin.modamk.CadReferenciar;
import pin.modamk.Relatorio;
import pin.modamk.RelatorioCampo;
import pin.modamk.RelatorioFiltro;
import pin.modamk.RelatorioFiltroCampo;
import pin.modamk.RelatorioGrupo;
import pin.modamk.RelatorioPagina;
import pin.modamk.RelatorioParametro;

public class RelContasReceber extends Relatorio {

    public RelatorioFiltro filtro;

    public RelContasReceber() {
        super("Contas a Receber", Tipo.Gra, RelatorioPagina.padraoGrafico(), new RelatorioCampo[]{
            new RelatorioCampo(1, 1, "titulo", RelatorioCampo.Area.INICIO_RELATORIO, RelatorioCampo.Tp.PARAMETRO).botaFonteComSerifa().botaTamanho(30).botaNegrito().botaAoCentro().botaMargens(2, 2, 2, 10),
            new RelatorioCampo(2, 1, "subtitulo", RelatorioCampo.Area.INICIO_RELATORIO, RelatorioCampo.Tp.PARAMETRO).botaFonteComSerifa().botaTamanho(18).botaNegrito().botaAoCentro().botaMargens(2, 2, 2, 30),
            new RelatorioCampo(1, 1, "Vencimento:", RelatorioCampo.Area.INICIO_GRUPO, "vencimento", RelatorioCampo.Tp.TEXTO).botaTamanho(14).botaNegrito().botaLargura(100).botaMargens(2, 15, 2, 12),
            new RelatorioCampo(1, 2, "vencimento", RelatorioCampo.Area.INICIO_GRUPO, "vencimento", RelatorioCampo.Tp.DADO).botaTamanho(14).botaNegrito().botaFormato(RelatorioCampo.Formatos.DATA, "dd/MM/yyyy").botaLargura(100).botaMargens(2, 15, 2, 12),
            new RelatorioCampo(2, 1, "Data", RelatorioCampo.Area.INICIO_GRUPO, "vencimento", RelatorioCampo.Tp.TEXTO).botaNegrito().botaLargura(56),
            new RelatorioCampo(2, 2, "Histórico", RelatorioCampo.Area.INICIO_GRUPO, "vencimento", RelatorioCampo.Tp.TEXTO).botaNegrito(),
            new RelatorioCampo(2, 3, "Pessoa", RelatorioCampo.Area.INICIO_GRUPO, "vencimento", RelatorioCampo.Tp.TEXTO).botaNegrito(),
            new RelatorioCampo(2, 4, "Fatura", RelatorioCampo.Area.INICIO_GRUPO, "vencimento", RelatorioCampo.Tp.TEXTO).botaNegrito().botaLargura(60),
            new RelatorioCampo(2, 5, "Lançamento", RelatorioCampo.Area.INICIO_GRUPO, "vencimento", RelatorioCampo.Tp.TEXTO).botaNegrito().botaLargura(64),
            new RelatorioCampo(2, 6, "Valor", RelatorioCampo.Area.INICIO_GRUPO, "vencimento", RelatorioCampo.Tp.TEXTO).botaNegrito().botaLargura(65),
            new RelatorioCampo(3, 1, "linha", RelatorioCampo.Area.INICIO_GRUPO, "vencimento", RelatorioCampo.Tp.FORMA),
            new RelatorioCampo(1, 1, "data", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaFormato(RelatorioCampo.Formatos.DATA, "dd/MM/yyyy").botaLargura(56),
            new RelatorioCampo(1, 2, "hist_nome", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO),
            new RelatorioCampo(1, 3, "pessoa", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO),
            new RelatorioCampo(1, 4, "fatura", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaLargura(60),
            new RelatorioCampo(1, 5, "codigo", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaLargura(64),
            new RelatorioCampo(1, 6, "valor", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaFormato(RelatorioCampo.Formatos.NUMERO_LON, "R$ #,##0.00").botaLargura(65),
            new RelatorioCampo(2, 1, "linha", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.FORMA),
            new RelatorioCampo(1, 1, "Total:", RelatorioCampo.Area.FIM_RELATORIO, RelatorioCampo.Tp.TEXTO).botaNegrito().botaADireita(),
            new RelatorioCampo(1, 2, "tot_val", RelatorioCampo.Area.FIM_RELATORIO, RelatorioCampo.Tp.FUNCAO).botaFuncao("somar", "valor").botaFormato(RelatorioCampo.Formatos.NUMERO_LON, "R$ #,##0.00").botaLargura(100),});

        botaRodapePadraoData();

        botaGrupo(new RelatorioGrupo("vencimento", RelatorioCampo.Formatos.DATA, 1));
    }

    @Override
    public void produzir() throws Exception {
        filtro = new RelatorioFiltro(this, "lancamentos", new RelatorioFiltroCampo[]{
            new RelatorioFiltroCampo(1, 1, "vencimento", "Vencimento De", Dados.Tp.Dat).botaObrigatorio().botaOperador(">="),
            new RelatorioFiltroCampo(1, 2, "vencimento", "Vencimento Até", Dados.Tp.Dat).botaObrigatorio().botaOperador("<="),
            new RelatorioFiltroCampo(2, 1, "pessoa", "Pessoa - Cód.", Dados.Tp.Crs).botaTamanho(8),
            new RelatorioFiltroCampo(2, 2, "pessoas.nome", "Pessoa - Nome", Dados.Tp.Crs).botaTamanho(80).botaEstrangeiro(),
            new RelatorioFiltroCampo(3, 1, "serie", "Serie", Dados.Tp.Crs).botaTamanho(4),
            new RelatorioFiltroCampo(3, 2, "fatura", "Fatura", Dados.Tp.Crs).botaTamanho(10),
            new RelatorioFiltroCampo(3, 3, "codigo", "Lançamento", Dados.Tp.Crs).botaTamanho(10),});

        filtro.botaEstrangeiro(new CadRelacao("pessoas").botaRelacao("pessoa", "codigo"));

        filtro.botaReferencia(new CadReferenciar(filtro, "pessoa", CadPessoas.class).botaChave("pessoa", "codigo").botaEstrangeiro("pessoas.nome", "nome"));

        filtro.botaOrdemTitulos("Vencimento De;Serie;Fatura");
        filtro.abrir();
    }

    @Override
    public void finalizar() throws Exception {
        resultados = conexao.busca("SELECT DISTINCT ON (" + filtro.pegaOrdem("", "lancamentos.codigo").replaceAll("DESC", "") + ") "
                + "   lancamentos.vencimento, lancamentos.data, historicos.nome AS hist_nome, pessoas.nome AS pessoa, "
                + "   CONCAT(lancamentos.serie, ' ', lancamentos.fatura) AS fatura, lancamentos.codigo, lancamentos.valor "
                + " FROM lancamentos "
                + "   LEFT JOIN pessoas ON pessoas.codigo = lancamentos.pessoa "
                + "   LEFT JOIN pessoas AS representantes ON representantes.codigo = lancamentos.representante "
                + "   LEFT JOIN historicos ON historicos.codigo = lancamentos.historico "
                + filtro.pegaCondicao("WHERE", "lancamentos.tipo = 'R'")
                + filtro.pegaOrdem("ORDER BY", "lancamentos.codigo"),
                filtro.pegaParametros());

        limparParametros();
        botaParametro(new RelatorioParametro("subtitulo", "De " + filtro.pegaCampoPeloTitulo("Vencimento De").editor.pgVlrDat().pgCrs() + " Até " + filtro.pegaCampoPeloTitulo("Vencimento Até").editor.pgVlrDat().pgCrs()));

        imprimir();
    }

}
