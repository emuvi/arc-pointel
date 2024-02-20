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

public class RelTabelasPrecos extends Relatorio {

    public RelatorioFiltro filtro;

    public RelTabelasPrecos() {
        super("Tabelas de Preços", Tipo.Gra, RelatorioPagina.padraoGrafico(), new RelatorioCampo[]{
            new RelatorioCampo(1, 1, "titulo", RelatorioCampo.Area.INICIO_RELATORIO, RelatorioCampo.Tp.PARAMETRO).botaFonteComSerifa().botaTamanho(30).botaNegrito().botaAoCentro().botaMargens(2, 2, 2, 30),
            new RelatorioCampo(1, 1, "grupo_nome", RelatorioCampo.Area.INICIO_GRUPO, "grupo_nome", RelatorioCampo.Tp.DADO).botaFonteComSerifa().botaTamanho(23).botaNegrito().botaMargens(0, 10, 0, 5),
            new RelatorioCampo(1, 1, "subgrupo_nome", RelatorioCampo.Area.INICIO_GRUPO, "subgrupo_nome", RelatorioCampo.Tp.DADO).botaFonteComSerifa().botaTamanho(18).botaNegrito().botaItalico().botaMargens(0, 10, 0, 5),
            new RelatorioCampo(2, 1, "Código", RelatorioCampo.Area.INICIO_GRUPO, "subgrupo_nome", RelatorioCampo.Tp.TEXTO).botaNegrito().botaLargura(40),
            new RelatorioCampo(2, 2, "Nome", RelatorioCampo.Area.INICIO_GRUPO, "subgrupo_nome", RelatorioCampo.Tp.TEXTO).botaNegrito(),
            new RelatorioCampo(2, 3, "Unid", RelatorioCampo.Area.INICIO_GRUPO, "subgrupo_nome", RelatorioCampo.Tp.TEXTO).botaNegrito().botaLargura(30),
            new RelatorioCampo(2, 4, "principal", RelatorioCampo.Area.INICIO_GRUPO, "subgrupo_nome", RelatorioCampo.Tp.PARAMETRO).botaNegrito().botaLargura(60),
            new RelatorioCampo(2, 5, "secundario", RelatorioCampo.Area.INICIO_GRUPO, "subgrupo_nome", RelatorioCampo.Tp.PARAMETRO).botaNegrito().botaLargura(60),
            new RelatorioCampo(3, 1, "linha", RelatorioCampo.Area.INICIO_GRUPO, "subgrupo_nome", RelatorioCampo.Tp.FORMA),
            new RelatorioCampo(1, 1, "codigo", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaLargura(40),
            new RelatorioCampo(1, 2, "nome", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO),
            new RelatorioCampo(1, 3, "unidade", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaLargura(30),
            new RelatorioCampo(1, 4, "principal", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaLargura(60).botaFormato(RelatorioCampo.Formatos.NUMERO_LON, "R$ #,##0.00"),
            new RelatorioCampo(1, 5, "secundario", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaLargura(60).botaFormato(RelatorioCampo.Formatos.NUMERO_LON, "R$ #,##0.00"),
            new RelatorioCampo(2, 1, "linha", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.FORMA)
        });

        botaRodapePadraoData();

        botaGrupo(new RelatorioGrupo("grupo_nome", RelatorioCampo.Formatos.CARACTERES, 1));
        botaGrupo(new RelatorioGrupo("subgrupo_nome", RelatorioCampo.Formatos.CARACTERES, 2));
    }

    @Override
    public void produzir() throws Exception {
        filtro = new RelatorioFiltro(this, "produtos", new RelatorioFiltroCampo[]{
            new RelatorioFiltroCampo(1, 1, "tabelas_precos.tabela", "Tab. de Preço - Cód.", Dados.Tp.Crs).botaTamanho(6).botaObrigatorio().tiraAutomatico(),
            new RelatorioFiltroCampo(1, 2, "tabelas_precos.nome", "Tab. de Preço - Nome", Dados.Tp.Crs).botaTamanho(60).botaEstrangeiro(),
            new RelatorioFiltroCampo(1, 3, "tabelas_secundarias.tabela", "Tab. Secundária - Cód.", Dados.Tp.Crs).botaTamanho(6).tiraAutomatico(),
            new RelatorioFiltroCampo(1, 4, "tabelas_secundarias.nome", "Tab. Secundária - Nome", Dados.Tp.Crs).botaTamanho(60).botaEstrangeiro(),
            new RelatorioFiltroCampo(2, 1, "grupo", "Grupo - Cód.", Dados.Tp.Crs).botaTamanho(4),
            new RelatorioFiltroCampo(2, 2, "grupos_produtos.nome", "Grupo - Nome", Dados.Tp.Crs).botaTamanho(60).botaEstrangeiro(),
            new RelatorioFiltroCampo(2, 3, "subgrupo", "SubGrupo - Cód.", Dados.Tp.Crs).botaTamanho(4),
            new RelatorioFiltroCampo(2, 4, "subgrupos_produtos.nome", "SubGrupo - Nome", Dados.Tp.Crs).botaTamanho(60).botaEstrangeiro(),});

        filtro.botaEstrangeiro(new CadRelacao("tabelas_precos").botaRelacao("tabelas_precos.tabela", "codigo"));
        filtro.botaEstrangeiro(new CadRelacao("tabelas_secundarias", "tabelas_precos").botaRelacao("tabelas_secundarias.tabela", "codigo"));
        filtro.botaEstrangeiro(new CadRelacao("grupos_produtos").botaRelacao("grupo", "codigo"));
        filtro.botaEstrangeiro(new CadRelacao("subgrupos_produtos").botaRelacao("grupo", "grupo").botaRelacao("subgrupo", "codigo"));

        filtro.botaReferencia(new CadReferenciar(filtro, "tabelas_precos.codigo", CadTabelasPrecos.class).botaChave("tabelas_precos.codigo", "codigo").botaEstrangeiro("tabelas_precos.nome", "nome"));
        filtro.botaReferencia(new CadReferenciar(filtro, "tabelas_secundarias.codigo", CadTabelasPrecos.class).botaChave("tabelas_secundarias.codigo", "codigo").botaEstrangeiro("tabelas_secundarias.nome", "nome"));
        filtro.botaReferencia(new CadReferenciar(filtro, "grupo", CadGruposProdutos.class).botaChave("grupo", "codigo").botaEstrangeiro("grupos_produtos.nome", "nome"));
        filtro.botaReferencia(new CadReferenciar(filtro, "subgrupo", CadSubGruposProdutos.class).botaChaveFixo("grupo", "grupo").botaEstranFixo("grupos_produtos.nome", "grupos_produtos.nome").botaChave("subgrupo", "codigo").botaEstrangeiro("subgrupos_produtos.nome", "nome"));

        filtro.abrir();
    }

    @Override
    public void finalizar() throws Exception {
        limparParametros();

        RelatorioFiltroCampo cpri = filtro.pegaCampo("tabelas_precos.tabela");
        String spri = cpri.editor.pgVlr().pgCrs();
        botaParametro(new RelatorioParametro("principal", spri));

        RelatorioFiltroCampo csec = filtro.pegaCampo("tabelas_secundarias.tabela");
        String ssec = csec.editor.pgVlr().pgCrs();
        botaParametro(new RelatorioParametro("secundario", ssec));

        resultados = conexao.busca("SELECT DISTINCT ON (" + filtro.pegaDistincao("", "grupos_produtos.codigo, subgrupos_produtos.nome, produtos.ordem, produtos.codigo") + ") "
                + "   grupos_produtos.nome AS grupo_nome, subgrupos_produtos.nome AS subgrupo_nome, produtos.codigo, produtos.nome, produtos.unidade, "
                + "   (SELECT valor FROM precos WHERE tabela = '" + spri + "' AND produto = produtos.codigo) AS principal, "
                + "   (SELECT valor FROM precos WHERE tabela = '" + ssec + "' AND produto = produtos.codigo) AS secundario "
                + " FROM produtos "
                + "   LEFT JOIN grupos_produtos ON grupos_produtos.codigo = produtos.grupo "
                + "   LEFT JOIN subgrupos_produtos ON subgrupos_produtos.grupo = produtos.grupo AND subgrupos_produtos.codigo = produtos.subgrupo "
                + " WHERE produtos.ativo = 'S' "
                + filtro.pegaCondicao("AND")
                + filtro.pegaOrdem("ORDER BY", "grupos_produtos.codigo, subgrupos_produtos.nome, produtos.ordem, produtos.codigo"),
                filtro.pegaParametros());

        imprimir();
    }
}
