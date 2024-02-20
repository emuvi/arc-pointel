package pin.adm;

import pin.libbas.Dados;
import pin.libvlr.Vlrs;
import pin.modamk.CadReferenciar;
import pin.modamk.CadRelacao;
import pin.modamk.Relatorio;
import pin.modamk.RelatorioCampo;
import pin.modamk.RelatorioFiltro;
import pin.modamk.RelatorioFiltroCampo;
import pin.modamk.RelatorioGrupo;
import pin.modamk.RelatorioPagina;
import pin.modamk.RelatorioParametro;

public class RelSaidasProdutos extends Relatorio {

    public RelatorioFiltro filtro;

    public RelSaidasProdutos() {
        super("Saídas dos Produtos", Tipo.Gra, RelatorioPagina.padraoGrafico(), new RelatorioCampo[]{
            new RelatorioCampo(1, 1, "titulo", RelatorioCampo.Area.INICIO_RELATORIO, RelatorioCampo.Tp.PARAMETRO).botaFonteComSerifa().botaTamanho(30).botaNegrito().botaAoCentro().botaMargens(2, 2, 2, 10),
            new RelatorioCampo(2, 1, "subtitulo", RelatorioCampo.Area.INICIO_RELATORIO, RelatorioCampo.Tp.PARAMETRO).botaFonteComSerifa().botaTamanho(18).botaNegrito().botaAoCentro().botaMargens(2, 2, 2, 30),
            new RelatorioCampo(1, 1, "grupo_nome", RelatorioCampo.Area.INICIO_GRUPO, "grupo_nome", RelatorioCampo.Tp.DADO).botaFonteComSerifa().botaTamanho(23).botaNegrito().botaMargens(0, 10, 0, 5),
            new RelatorioCampo(1, 1, "subgrupo_nome", RelatorioCampo.Area.INICIO_GRUPO, "subgrupo_nome", RelatorioCampo.Tp.DADO).botaFonteComSerifa().botaTamanho(18).botaNegrito().botaItalico().botaMargens(0, 10, 0, 5),
            new RelatorioCampo(2, 1, "Código", RelatorioCampo.Area.INICIO_GRUPO, "subgrupo_nome", RelatorioCampo.Tp.TEXTO).botaNegrito().botaLargura(90),
            new RelatorioCampo(2, 2, "Produto", RelatorioCampo.Area.INICIO_GRUPO, "subgrupo_nome", RelatorioCampo.Tp.TEXTO).botaNegrito(),
            new RelatorioCampo(2, 3, "Quantidade", RelatorioCampo.Area.INICIO_GRUPO, "subgrupo_nome", RelatorioCampo.Tp.TEXTO).botaNegrito().botaLargura(90),
            new RelatorioCampo(3, 1, "linha", RelatorioCampo.Area.INICIO_GRUPO, "subgrupo_nome", RelatorioCampo.Tp.FORMA),
            new RelatorioCampo(1, 1, "codigo", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaLargura(90),
            new RelatorioCampo(1, 2, "nome", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO),
            new RelatorioCampo(1, 3, "quantidade", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaFormato(RelatorioCampo.Formatos.NUMERO_LON, "#,##0.000").botaLargura(90),});

        botaRodapePadraoData();

        botaGrupo(new RelatorioGrupo("grupo_nome", RelatorioCampo.Formatos.CARACTERES, 1));
        botaGrupo(new RelatorioGrupo("subgrupo_nome", RelatorioCampo.Formatos.CARACTERES, 2));
    }

    @Override
    public void produzir() throws Exception {
        filtro = new RelatorioFiltro(this, "produtos", new RelatorioFiltroCampo[]{
            new RelatorioFiltroCampo(1, 1, "data_de", "Data De", Dados.Tp.Dat).botaObrigatorio().tiraAutomatico(),
            new RelatorioFiltroCampo(1, 2, "data_ate", "Data Até", Dados.Tp.Dat).botaObrigatorio().tiraAutomatico(),
            new RelatorioFiltroCampo(1, 2, "serie", "Série", Dados.Tp.Crs).botaTamanho(4).tiraAutomatico(),
            new RelatorioFiltroCampo(2, 1, "grupo", "Grupo - Cód.", Dados.Tp.Crs).botaTamanho(4),
            new RelatorioFiltroCampo(2, 2, "grupos_produtos.nome", "Grupo - Nome", Dados.Tp.Crs).botaTamanho(60).botaEstrangeiro(),
            new RelatorioFiltroCampo(2, 3, "subgrupo", "SubGrupo - Cód.", Dados.Tp.Crs).botaTamanho(4),
            new RelatorioFiltroCampo(2, 4, "subgrupos_produtos.nome", "SubGrupo - Nome", Dados.Tp.Crs).botaTamanho(60).botaEstrangeiro(),
            new RelatorioFiltroCampo(3, 1, "subgrupo_lista", "SubGrupo - Lista", Dados.Tp.Crs).tiraAutomatico().botaTamanho(240).botaLargura(480)});

        filtro.botaEstrangeiro(new CadRelacao("grupos_produtos").botaRelacao("grupo", "codigo"));
        filtro.botaEstrangeiro(new CadRelacao("subgrupos_produtos").botaRelacao("grupo", "grupo").botaRelacao("subgrupo", "codigo"));

        filtro.botaReferencia(new CadReferenciar(filtro, "grupo", CadGruposProdutos.class).botaChave("grupo", "codigo").botaEstrangeiro("grupos_produtos.nome", "nome"));
        filtro.botaReferencia(new CadReferenciar(filtro, "subgrupo", CadSubGruposProdutos.class).botaChaveFixo("grupo", "grupo").botaEstranFixo("grupos_produtos.nome", "grupos_produtos.nome").botaChave("subgrupo", "codigo").botaEstrangeiro("subgrupos_produtos.nome", "nome"));

        filtro.botaOrdemTitulos("Grupo;SubGrupo - Nome");
        filtro.abrir();
    }

    @Override
    public void finalizar() throws Exception {
        limparParametros();

        String subtitulo = "De " + filtro.pegaCampoPeloTitulo("Data De").editor.pgVlrDat().pgCrs()
                + " Até " + filtro.pegaCampoPeloTitulo("Data Até").editor.pgVlrDat().pgCrs();
        botaParametro(new RelatorioParametro("subtitulo", subtitulo));

        Vlrs innPars = new Vlrs();
        innPars.bota(filtro.pegaCampo("data_de").editor.pgVlr());
        innPars.bota(filtro.pegaCampo("data_ate").editor.pgVlr());

        RelatorioFiltroCampo cmpSerie = filtro.pegaCampo("serie");
        if (!cmpSerie.editor.vazio()) {
            innPars.add(cmpSerie.editor.pgVlr());
            cmpSerie.indiceFiltro = innPars.size();
        }

        String distPrincipal = filtro.pegaDistincao("", "grupos_produtos.codigo, subgrupos_produtos.nome, produtos.ordem, produtos.codigo");
        String condPrincipal = filtro.pegaCondicao("AND", innPars.size() + 1);

        String subGruposListados = filtro.pegaCampo("subgrupo_lista").editor.pgVlr().pgCrs("");
        if (subGruposListados != null && !subGruposListados.isEmpty()) {
            subGruposListados = subGruposListados.replaceAll("\\s", "");
            subGruposListados = subGruposListados.replaceAll("\\,", "', '");
            subGruposListados = "('" + subGruposListados + "') ";
            condPrincipal += " AND produtos.subgrupo IN " + subGruposListados;
        }

        String ordmPrincipal = filtro.pegaOrdem("ORDER BY", "grupos_produtos.codigo, subgrupos_produtos.nome, produtos.ordem, produtos.codigo");
        Vlrs parsPrincipal = filtro.pegaParametros(innPars);

        resultados = conexao.busca("SELECT DISTINCT ON (" + distPrincipal + ") "
                + "   grupos_produtos.nome AS grupo_nome, subgrupos_produtos.nome AS subgrupo_nome, produtos.codigo, produtos.nome, "
                + " ( SELECT SUM (itens_faturas.quantidade) "
                + "     FROM itens_faturas "
                + "     JOIN faturas "
                + "       ON faturas.serie = itens_faturas.serie "
                + "       AND faturas.codigo = itens_faturas.nf "
                + "       AND faturas.emitido_data >= ? "
                + "       AND faturas.emitido_data <= ? "
                + (cmpSerie.editor.vazio() ? "" : " AND faturas.serie = ?")
                + "     WHERE itens_faturas.produto = produtos.codigo ) AS quantidade "
                + " FROM produtos "
                + "   LEFT JOIN grupos_produtos ON grupos_produtos.codigo = produtos.grupo "
                + "   LEFT JOIN subgrupos_produtos ON subgrupos_produtos.grupo = produtos.grupo AND subgrupos_produtos.codigo = produtos.subgrupo "
                + " WHERE produtos.ativo = 'S' "
                + condPrincipal + ordmPrincipal,
                parsPrincipal);

        imprimir();
    }
}
