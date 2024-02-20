package pin.adm;

import pin.libamk.Botao;
import pin.libamk.Cmp;
import pin.libbas.Dados;
import pin.modamk.CadRelacao;
import pin.modamk.CadReferenciar;
import pin.modamk.Cadastro;

public class CadProdutos extends Cadastro {

    public CadProdutos() throws Exception {
        super("produtos", "Produtos", new Cmp[]{
            new Cmp(1, 1, "codigo", "Código", Dados.Tp.Crs).mTamanho(6).botaDetalhe("chave", true),
            new Cmp(1, 2, "ativo", "Ativo", Dados.Tp.Log),
            new Cmp(1, 3, "nome", "Nome", Dados.Tp.Crs).mTamanho(80),
            new Cmp(2, 1, "unidade", "Unidade - Cód.", Dados.Tp.Crs).mTamanho(4),
            new Cmp(2, 2, "unidades.nome", "Unidade - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true),
            new Cmp(2, 3, "origem", "Origem", Dados.Tp.Enu).botaOpcoes(new Object[]{"(R) Revenda", "(F) Fabricação"}),
            new Cmp(2, 4, "ordem", "Ordem", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3),
            new Cmp(3, 1, "grupo", "Grupo - Cód.", Dados.Tp.Crs).mTamanho(4),
            new Cmp(3, 2, "grupos_produtos.nome", "Grupo - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true),
            new Cmp(3, 3, "subgrupo", "SubGrupo - Cód.", Dados.Tp.Crs).mTamanho(4),
            new Cmp(3, 4, "subgrupos_produtos.nome", "SubGrupo - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true),
            new Cmp(4, 1, "negocio", "Negócio - Cód.", Dados.Tp.Crs).mTamanho(6),
            new Cmp(4, 2, "negocios.nome", "Negócio - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true)
        });
    }

    @Override
    public void preparaEstr() throws Exception {
        botaRelacao(new CadRelacao("unidades").botaRelacao("unidade", "codigo"));
        botaRelacao(new CadRelacao("grupos_produtos").botaRelacao("grupo", "codigo"));
        botaRelacao(new CadRelacao("subgrupos_produtos").botaRelacao("grupo", "grupo").botaRelacao("subgrupo", "codigo"));
        botaReferencia(new CadReferenciar(this, "unidade", CadUnidades.class).botaChave("unidade", "codigo").botaEstrangeiro("unidades.nome", "nome"));
        botaReferencia(new CadReferenciar(this, "grupo", CadGruposProdutos.class).botaChave("grupo", "codigo").botaEstrangeiro("grupos_produtos.nome", "nome"));
        botaReferencia(new CadReferenciar(this, "subgrupo", CadSubGruposProdutos.class).botaChaveFixo("grupo", "grupo").botaEstranFixo("grupos_produtos.nome", "grupos_produtos.nome").botaChave("subgrupo", "codigo").botaEstrangeiro("subgrupos_produtos.nome", "nome"));
        botaRelacao(new CadRelacao("negocios").botaRelacao("negocio", "codigo"));
        botaReferencia(new CadReferenciar(this, "negocio", CadNegocios.class).botaChave("negocio", "codigo").botaEstrangeiro("negocios.nome", "nome"));
    }

    @Override
    public void preparaIntf() throws Exception {
        botaBotao(new BotPrecos());
    }

    private class BotPrecos extends Botao {

        public BotPrecos() {
            super("Preços", 'P');
        }

        @Override
        public void aoExecutar(Object comOrigem) throws Exception {
            if (confirmar(true)) {
                CadPrecos filho = new CadPrecos();
                filho.pegaCampo("produto").mVlrFixo(pegaCampo("codigo").pEdt().pgVlr());
                filho.pegaCampo("produtos.nome").mVlrFixo(pegaCampo("nome").pEdt().pgVlr());
                filho.mostra();
            }
        }
    }
}
