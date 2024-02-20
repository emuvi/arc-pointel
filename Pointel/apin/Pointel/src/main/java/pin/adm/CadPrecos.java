package pin.adm;

import pin.libbas.Dados;
import pin.modamk.CadRelacao;
import pin.modamk.Cadastro;
import pin.libamk.Cmp;
import pin.modamk.CadReferenciar;

public class CadPrecos extends Cadastro {

    public CadPrecos() throws Exception {
        super("precos", "Preços", new Cmp[]{
            new Cmp(1, 1, "produto", "Produto - Cód.", Dados.Tp.Crs).mTamanho(6).botaDetalhe("chave", true),
            new Cmp(1, 2, "produtos.nome", "Produto - Nome", Dados.Tp.Crs).mTamanho(80).botaDetalhe("estrangeiro", true),
            new Cmp(2, 1, "tabela", "Tabela - Cód.", Dados.Tp.Crs).mTamanho(6).botaDetalhe("chave", true),
            new Cmp(2, 2, "tabelas_precos.nome", "Tabela - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true),
            new Cmp(2, 3, "valor", "Valor", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3),
            new Cmp(3, 1, "negocio", "Negócio - Cód.", Dados.Tp.Crs).mTamanho(6),
            new Cmp(3, 2, "negocios.nome", "Negócio - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true)
        });
    }

    @Override
    public void preparaEstr() throws Exception {
        botaRelacao(new CadRelacao("produtos").botaRelacao("produto", "codigo"));
        botaRelacao(new CadRelacao("tabelas_precos").botaRelacao("tabela", "codigo"));
        botaReferencia(new CadReferenciar(this, "produto", CadProdutos.class).botaChave("produto", "codigo").botaEstrangeiro("produtos.nome", "nome"));
        botaReferencia(new CadReferenciar(this, "tabela", CadTabelasPrecos.class).botaChave("tabela", "codigo").botaEstrangeiro("tabelas_precos.nome", "nome"));
        botaRelacao(new CadRelacao("negocios").botaRelacao("negocio", "codigo"));
        botaReferencia(new CadReferenciar(this, "negocio", CadNegocios.class).botaChave("negocio", "codigo").botaEstrangeiro("negocios.nome", "nome"));
    }
}
