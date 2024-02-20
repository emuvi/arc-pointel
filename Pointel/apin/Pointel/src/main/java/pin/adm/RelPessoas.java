package pin.adm;

import pin.libbas.Dados;
import pin.libutl.UtlLog;
import pin.modamk.CadReferenciar;
import pin.modamk.CadRelacao;
import pin.modamk.Relatorio;
import pin.modamk.RelatorioCampo;
import pin.modamk.RelatorioFiltro;
import pin.modamk.RelatorioFiltroCampo;
import pin.modamk.RelatorioPagina;

public class RelPessoas extends Relatorio {

    public RelatorioFiltro filtro;

    public RelPessoas() {
        super("Pessoas", Tipo.Gra, RelatorioPagina.padraoGrafico(), new RelatorioCampo[]{
            new RelatorioCampo(1, 1, "titulo", RelatorioCampo.Area.INICIO_RELATORIO, RelatorioCampo.Tp.PARAMETRO).botaFonteComSerifa().botaTamanho(30).botaNegrito().botaAoCentro().botaMargens(2, 2, 2, 30),
            new RelatorioCampo(1, 1, "Código", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaNegrito().botaLargura(45),
            new RelatorioCampo(1, 2, "Nome", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaNegrito(),
            new RelatorioCampo(1, 3, "Fantasia", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaNegrito(),
            new RelatorioCampo(1, 4, "Contato", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaNegrito().botaLargura(65),
            new RelatorioCampo(1, 5, "Fone 1", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaNegrito().botaLargura(65),
            new RelatorioCampo(1, 6, "Fone 2", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaNegrito().botaLargura(65),
            new RelatorioCampo(1, 7, "Fone 3", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaNegrito().botaLargura(65),
            new RelatorioCampo(2, 1, "CEP", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaNegrito().botaLargura(50),
            new RelatorioCampo(2, 2, "Endereço", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaNegrito(),
            new RelatorioCampo(2, 3, "UF", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaNegrito().botaLargura(40),
            new RelatorioCampo(2, 4, "Cidade", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaNegrito().botaLargura(130),
            new RelatorioCampo(2, 5, "Bairro", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaNegrito().botaLargura(130),
            new RelatorioCampo(3, 1, "CNPJ/CPNF", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaNegrito().botaLargura(80),
            new RelatorioCampo(3, 2, "Ins. Estadual", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaNegrito().botaLargura(65),
            new RelatorioCampo(3, 3, "Cadastro", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaNegrito().botaLargura(56),
            new RelatorioCampo(3, 4, "Atualização", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaNegrito().botaLargura(56),
            new RelatorioCampo(3, 5, "Região", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaNegrito().botaLargura(90),
            new RelatorioCampo(3, 6, "Representante", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaNegrito(),
            new RelatorioCampo(3, 7, "Grupo", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaNegrito().botaLargura(40),
            new RelatorioCampo(3, 8, "SubGrupo", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaNegrito().botaLargura(50),
            new RelatorioCampo(4, 1, "linha", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.FORMA),
            new RelatorioCampo(1, 1, "codigo", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaLargura(45),
            new RelatorioCampo(1, 2, "nome", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO),
            new RelatorioCampo(1, 3, "fantasia", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO),
            new RelatorioCampo(1, 4, "contato", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaLargura(65),
            new RelatorioCampo(1, 5, "fone1", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaLargura(65),
            new RelatorioCampo(1, 6, "fone2", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaLargura(65),
            new RelatorioCampo(1, 7, "fone3", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaLargura(65),
            new RelatorioCampo(2, 1, "cep", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaLargura(50),
            new RelatorioCampo(2, 2, "endereco", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO),
            new RelatorioCampo(2, 3, "estado", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaLargura(40),
            new RelatorioCampo(2, 4, "cidade_nome", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaLargura(130),
            new RelatorioCampo(2, 5, "bairro_nome", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaLargura(130),
            new RelatorioCampo(3, 1, "cnpjcpf", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaLargura(80),
            new RelatorioCampo(3, 2, "insestadual", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaLargura(65),
            new RelatorioCampo(3, 3, "cadastro", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaFormato(RelatorioCampo.Formatos.DATA, "dd/MM/yyyy").botaLargura(56),
            new RelatorioCampo(3, 4, "atualizacao", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaFormato(RelatorioCampo.Formatos.DATA, "dd/MM/yyyy").botaLargura(56),
            new RelatorioCampo(3, 5, "regiao_nome", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaLargura(90),
            new RelatorioCampo(3, 6, "representante_nome", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO),
            new RelatorioCampo(3, 7, "grupo", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaLargura(40),
            new RelatorioCampo(3, 8, "subgrupo", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaLargura(50),
            new RelatorioCampo(4, 1, "linha", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.FORMA)
        });

        botaRodapePadraoData();
    }

    @Override
    public void produzir() throws Exception {
        filtro = new RelatorioFiltro(this, "pessoas", new RelatorioFiltroCampo[]{
            new RelatorioFiltroCampo(1, 1, "nome", "Nome", Dados.Tp.Crs).botaTamanho(80).botaOperador("LIKE '%' || ? || '%'"),
            new RelatorioFiltroCampo(1, 1, "fantasia", "Fantasia", Dados.Tp.Crs).botaTamanho(60).botaOperador("LIKE '%' || ? || '%'"),
            new RelatorioFiltroCampo(2, 1, "grupo", "Grupo - Cód.", Dados.Tp.Crs).botaTamanho(4),
            new RelatorioFiltroCampo(2, 2, "grupos_pessoas.nome", "Grupo - Nome", Dados.Tp.Crs).botaTamanho(60).botaEstrangeiro().tiraOrdenar(),
            new RelatorioFiltroCampo(2, 1, "subgrupo", "SubGrupo - Cód.", Dados.Tp.Crs).botaTamanho(4),
            new RelatorioFiltroCampo(2, 2, "subgrupos_pessoas.nome", "SubGrupo - Nome", Dados.Tp.Crs).botaTamanho(60).botaEstrangeiro().tiraOrdenar(),
            new RelatorioFiltroCampo(3, 1, "cidade", "Cidade - Cód.", Dados.Tp.Crs).botaTamanho(6),
            new RelatorioFiltroCampo(3, 2, "cidades.pais", "Cidade - País", Dados.Tp.Crs).botaTamanho(4).botaEstrangeiro(),
            new RelatorioFiltroCampo(3, 3, "cidades.estado", "Cidade - Estado", Dados.Tp.Crs).botaTamanho(4).botaEstrangeiro(),
            new RelatorioFiltroCampo(3, 4, "cidades.nome", "Cidade - Nome", Dados.Tp.Crs).botaTamanho(60).botaEstrangeiro(),
            new RelatorioFiltroCampo(4, 1, "bairro", "Bairro - Cód.", Dados.Tp.Crs).botaTamanho(4),
            new RelatorioFiltroCampo(4, 2, "bairros.nome", "Bairro - Nome", Dados.Tp.Crs).botaTamanho(60).botaEstrangeiro(),
            new RelatorioFiltroCampo(4, 3, "regiao", "Região - Cód.", Dados.Tp.Crs).botaTamanho(4),
            new RelatorioFiltroCampo(4, 4, "regioes.nome", "Região - Nome", Dados.Tp.Crs).botaTamanho(60).botaEstrangeiro(),
            new RelatorioFiltroCampo(5, 1, "representante", "Representante - Cód.", Dados.Tp.Crs).botaTamanho(8),
            new RelatorioFiltroCampo(5, 2, "representantes.nome", "Representante - Nome", Dados.Tp.Crs).botaTamanho(80).botaEstrangeiro(),});

        filtro.botaEstrangeiro(new CadRelacao("grupos_pessoas").botaRelacao("grupo", "codigo"));
        filtro.botaEstrangeiro(new CadRelacao("subgrupos_pessoas").botaRelacao("grupo", "grupo").botaRelacao("subgrupo", "codigo"));
        filtro.botaEstrangeiro(new CadRelacao("cidades").botaRelacao("cidade", "codigo"));
        filtro.botaEstrangeiro(new CadRelacao("bairros").botaRelacao("cidade", "cidade").botaRelacao("bairro", "codigo"));
        filtro.botaEstrangeiro(new CadRelacao("regioes").botaRelacao("regiao", "codigo"));
        filtro.botaEstrangeiro(new CadRelacao("representantes", "pessoas").botaRelacao("representante", "codigo"));

        filtro.botaReferencia(new CadReferenciar(filtro, "grupo", CadGruposPessoas.class).botaChave("grupo", "codigo").botaEstrangeiro("grupos_pessoas.nome", "nome"));
        filtro.botaReferencia(new CadReferenciar(filtro, "subgrupo", CadSubGruposPessoas.class).botaChaveFixo("grupo", "grupo").botaEstranFixo("grupos_pessoas.nome", "grupos_pessoas.nome").botaChave("subgrupo", "codigo").botaEstrangeiro("subgrupos_pessoas.nome", "nome"));
        filtro.botaReferencia(new CadReferenciar(filtro, "cidade", CadCidades.class).botaChave("cidade", "codigo").botaEstrangeiro("cidades.pais", "pais").botaEstrangeiro("cidades.estado", "estado").botaEstrangeiro("cidades.nome", "nome"));
        filtro.botaReferencia(new CadReferenciar(filtro, "bairro", CadBairros.class).botaChaveFixo("cidade", "cidade").botaEstranFixo("cidades.pais", "cidades.pais").botaEstranFixo("cidades.estado", "cidades.estado").botaEstranFixo("cidades.nome", "cidades.nome").botaChave("bairro", "codigo").botaEstrangeiro("bairros.nome", "nome"));
        filtro.botaReferencia(new CadReferenciar(filtro, "regiao", CadRegioes.class).botaChave("regiao", "codigo").botaEstrangeiro("regioes.nome", "nome"));
        filtro.botaReferencia(new CadReferenciar(filtro, "representante", CadPessoas.class).botaChave("representante", "codigo").botaEstrangeiro("representantes.nome", "nome"));

        filtro.abrir();
    }

    @Override
    public void finalizar() throws Exception {
        String cnd = filtro.pegaCondicao("WHERE");
        if (cnd.trim().isEmpty()) {
            cnd = " WHERE ";
        } else {
            cnd += " AND ";
        }
        cnd += " pessoas.ativo = " + UtlLog.formataSQL(true) + " ";
        resultados = conexao.busca("SELECT DISTINCT ON (" + filtro.pegaOrdem("", "pessoas.codigo").replaceAll("DESC", "") + ") "
                + "   pessoas.codigo, pessoas.nome, pessoas.fantasia, pessoas.contato, pessoas.fone1, pessoas.fone2, pessoas.fone3, "
                + "   pessoas.cep, CONCAT(pessoas.logradouro, ' ', pessoas.endereco, ', ', pessoas.numero, ' ', pessoas.complemento) AS endereco, "
                + "   cidades.estado, cidades.nome AS cidade_nome, bairros.nome AS bairro_nome, pessoas.cnpjcpf, pessoas.insestadual, "
                + "   pessoas.cadastro, pessoas.atualizacao, regioes.nome AS regiao_nome, representantes.nome AS representante_nome, "
                + "   pessoas.grupo, pessoas.subgrupo "
                + " FROM pessoas "
                + " LEFT JOIN cidades ON cidades.codigo = pessoas.cidade "
                + " LEFT JOIN bairros ON bairros.cidade = pessoas.cidade AND bairros.codigo = pessoas.bairro "
                + " LEFT JOIN regioes ON regioes.codigo = pessoas.regiao "
                + " LEFT JOIN pessoas AS representantes ON representantes.codigo = pessoas.representante "
                + cnd + filtro.pegaOrdem("ORDER BY", "pessoas.codigo"),
                filtro.pegaParametros());

        imprimir();
    }
}
