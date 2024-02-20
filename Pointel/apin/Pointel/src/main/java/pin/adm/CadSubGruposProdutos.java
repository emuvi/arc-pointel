package pin.adm;

import pin.libbas.Dados;
import pin.modamk.CadRelacao;
import pin.modamk.Cadastro;
import pin.libamk.Cmp;
import pin.modamk.CadReferenciar;

public class CadSubGruposProdutos extends Cadastro {

    public CadSubGruposProdutos() throws Exception {
        super("subgrupos_produtos", "SubGrupos de Produtos", new Cmp[]{
            new Cmp(1, 1, "grupo", "Grupo - Cód.", Dados.Tp.Crs).mTamanho(4).botaDetalhe("chave", true),
            new Cmp(1, 2, "grupos_produtos.nome", "Grupo - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true),
            new Cmp(2, 1, "codigo", "Código", Dados.Tp.Crs).mTamanho(4).botaDetalhe("chave", true),
            new Cmp(2, 2, "ativo", "Ativo", Dados.Tp.Log),
            new Cmp(2, 3, "nome", "Nome", Dados.Tp.Crs).mTamanho(60),
            new Cmp(3, 1, "negocio", "Negócio - Cód.", Dados.Tp.Crs).mTamanho(6),
            new Cmp(3, 2, "negocios.nome", "Negócio - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true)
        });
    }

    @Override
    public void preparaEstr() throws Exception {
        botaRelacao(new CadRelacao("grupos_produtos").botaRelacao("grupo", "codigo"));
        botaReferencia(new CadReferenciar(this, "grupo", CadGruposProdutos.class).botaChave("grupo", "codigo").botaEstrangeiro("grupos_produtos.nome", "nome"));
        botaRelacao(new CadRelacao("negocios").botaRelacao("negocio", "codigo"));
        botaReferencia(new CadReferenciar(this, "negocio", CadNegocios.class).botaChave("negocio", "codigo").botaEstrangeiro("negocios.nome", "nome"));
    }
}
