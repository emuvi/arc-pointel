package pin.adm;

import pin.libbas.Dados;
import pin.modamk.CadRelacao;
import pin.modamk.CadReferenciar;
import pin.modamk.Relatorio;
import pin.modamk.RelatorioCampo;
import pin.modamk.RelatorioFiltro;
import pin.modamk.RelatorioFiltroCampo;
import pin.modamk.RelatorioPagina;
import pin.modamk.RelatorioParametro;

public class RelComissoes extends Relatorio {

    public RelatorioFiltro filtro;

    public RelComissoes() {
        super("Comissões", Tipo.Gra, RelatorioPagina.padraoGrafico(), new RelatorioCampo[]{
            new RelatorioCampo(1, 1, "titulo", RelatorioCampo.Area.INICIO_RELATORIO, RelatorioCampo.Tp.PARAMETRO).botaFonteComSerifa().botaTamanho(30).botaNegrito().botaAoCentro().botaMargens(2, 2, 2, 10),
            new RelatorioCampo(2, 1, "subtitulo", RelatorioCampo.Area.INICIO_RELATORIO, RelatorioCampo.Tp.PARAMETRO).botaFonteComSerifa().botaTamanho(18).botaNegrito().botaAoCentro().botaMargens(2, 2, 2, 30),
            new RelatorioCampo(1, 1, "Data", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaNegrito().botaLargura(56),
            new RelatorioCampo(1, 2, "Vencimento", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaNegrito().botaLargura(64),
            new RelatorioCampo(1, 3, "Pedido", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaNegrito().botaLargura(60),
            new RelatorioCampo(1, 4, "Histórico", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaNegrito(),
            new RelatorioCampo(1, 5, "Pessoa", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaNegrito(),
            new RelatorioCampo(1, 6, "Valor", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaNegrito().botaLargura(65),
            new RelatorioCampo(1, 7, "Comissão", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaNegrito().botaLargura(65),
            new RelatorioCampo(1, 7, "%", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaNegrito().botaLargura(35),
            new RelatorioCampo(2, 1, "linha", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.FORMA),
            new RelatorioCampo(1, 1, "data", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaFormato(RelatorioCampo.Formatos.DATA, "dd/MM/yyyy").botaLargura(56),
            new RelatorioCampo(1, 2, "vencimento", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaFormato(RelatorioCampo.Formatos.DATA, "dd/MM/yyyy").botaLargura(64),
            new RelatorioCampo(1, 3, "ped_vendedor", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaLargura(60),
            new RelatorioCampo(1, 4, "hist_nome", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO),
            new RelatorioCampo(1, 5, "pes_nome", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO),
            new RelatorioCampo(1, 6, "valor", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaFormato(RelatorioCampo.Formatos.NUMERO_LON, "R$ #,##0.00").botaLargura(65),
            new RelatorioCampo(1, 7, "comissao", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaFormato(RelatorioCampo.Formatos.NUMERO_LON, "R$ #,##0.000").botaLargura(65),
            new RelatorioCampo(1, 8, "com_per", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaFormato(RelatorioCampo.Formatos.NUMERO_LON, "#,##0.000").botaLargura(35),
            new RelatorioCampo(2, 1, "linha", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.FORMA),
            new RelatorioCampo(1, 1, "Total:", RelatorioCampo.Area.FIM_RELATORIO, RelatorioCampo.Tp.TEXTO).botaNegrito().botaADireita(),
            new RelatorioCampo(1, 2, "tot_val", RelatorioCampo.Area.FIM_RELATORIO, RelatorioCampo.Tp.FUNCAO).botaFuncao("somar", "valor").botaFormato(RelatorioCampo.Formatos.NUMERO_LON, "R$ #,##0.00").botaLargura(100),
            new RelatorioCampo(2, 1, "Comissão:", RelatorioCampo.Area.FIM_RELATORIO, RelatorioCampo.Tp.TEXTO).botaNegrito().botaADireita(),
            new RelatorioCampo(2, 2, "tot_com", RelatorioCampo.Area.FIM_RELATORIO, RelatorioCampo.Tp.FUNCAO).botaFuncao("somar", "comissao").botaFormato(RelatorioCampo.Formatos.NUMERO_LON, "R$ #,##0.000").botaLargura(100),});

        botaRodapePadraoData();
    }

    @Override
    public void produzir() throws Exception {
        filtro = new RelatorioFiltro(this, "lancamentos", new RelatorioFiltroCampo[]{
            new RelatorioFiltroCampo(1, 1, "data", "Data De", Dados.Tp.Dat).botaObrigatorio().botaOperador(">="),
            new RelatorioFiltroCampo(1, 2, "data", "Data Até", Dados.Tp.Dat).botaObrigatorio().botaOperador("<="),
            new RelatorioFiltroCampo(2, 1, "representante", "Representante - Cód.", Dados.Tp.Crs).botaObrigatorio().botaTamanho(8),
            new RelatorioFiltroCampo(2, 2, "representantes.nome", "Representante - Nome", Dados.Tp.Crs).botaTamanho(80).botaEstrangeiro(),
            new RelatorioFiltroCampo(3, 1, "vencimento", "Vencimento", Dados.Tp.Dat),
            new RelatorioFiltroCampo(3, 2, "ped_vendedor", "Ped. Vendedor", Dados.Tp.Crs).botaTamanho(15),
            new RelatorioFiltroCampo(3, 3, "codigo", "Lançamento", Dados.Tp.Crs).botaTamanho(10),});

        filtro.botaEstrangeiro(new CadRelacao("representantes", "pessoas").botaRelacao("representante", "codigo"));

        filtro.botaReferencia(new CadReferenciar(filtro, "representante", CadPessoas.class).botaChave("representante", "codigo").botaEstrangeiro("representantes.nome", "nome"));

        filtro.botaOrdemTitulos("Data De;Vencimento;Ped. Vendedor");
        filtro.abrir();
    }

    @Override
    public void finalizar() throws Exception {
        resultados = conexao.busca("SELECT DISTINCT ON (" + filtro.pegaDistincao("", "lancamentos.codigo") + ") "
                + "   lancamentos.data, lancamentos.vencimento, lancamentos.ped_vendedor, "
                + "   historicos.nome AS hist_nome, pessoas.nome AS pes_nome, lancamentos.valor, "
                + "   lancamentos.comissao, (lancamentos.comissao / lancamentos.valor * 100) AS com_per "
                + " FROM lancamentos "
                + "   LEFT JOIN pessoas ON pessoas.codigo = lancamentos.pessoa "
                + "   LEFT JOIN historicos ON historicos.codigo = lancamentos.historico "
                + filtro.pegaCondicao("WHERE", "(lancamentos.tipo = 'E' OR lancamentos.tipo = 'S') AND (lancamentos.situacao = 'Q' OR lancamentos.situacao = 'D')")
                + filtro.pegaOrdem("ORDER BY", "lancamentos.codigo"),
                filtro.pegaParametros());

        limparParametros();

        mudaParametro("titulo", filtro.pegaCampo("representantes.nome").editor.pgVlr() + " Comissões");

        String subtitulo = "De " + filtro.pegaCampoPeloTitulo("Data De").editor.pgVlrDat().pgCrs()
                + " Até " + filtro.pegaCampoPeloTitulo("Data Até").editor.pgVlrDat().pgCrs();
        botaParametro(new RelatorioParametro("subtitulo", subtitulo));

        imprimir();
    }

}
