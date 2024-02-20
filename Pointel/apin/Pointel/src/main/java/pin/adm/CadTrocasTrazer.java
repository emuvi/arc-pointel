package pin.adm;

import pin.libamk.Cmp;
import pin.libbas.Conferencia;
import pin.libbas.Dados;
import pin.libvlr.Vlrs;
import pin.modamk.CadRelacao;
import pin.modamk.CadReferenciar;
import pin.modamk.Cadastro;

public class CadTrocasTrazer extends Cadastro {

    public CadTrocasTrazer() throws Exception {
        super("trocas_trazer", "Itens a Trazer das Trocas", new Cmp[]{
            new Cmp(1, 1, "troca", "Troca - Cód.", Dados.Tp.Crs).mTamanho(8).botaDetalhe("chave", true),
            new Cmp(1, 2, "codigo", "Código", Dados.Tp.Crs).mTamanho(4).botaDetalhe("chave", true),
            new Cmp(1, 3, "produto", "Produto - Cód.", Dados.Tp.Crs).mTamanho(6),
            new Cmp(1, 4, "produtos.nome", "Produto - Nome", Dados.Tp.Crs).mTamanho(80).botaDetalhe("estrangeiro", true),
            new Cmp(1, 5, "defeito", "Com Defeito", Dados.Tp.Log),
            new Cmp(2, 1, "unidade", "Unidade - Cód.", Dados.Tp.Crs).mTamanho(4),
            new Cmp(2, 2, "unidades.nome", "Unidade - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true),
            new Cmp(2, 3, "quantidade", "Quantidade", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3),
            new Cmp(3, 1, "obs", "Obs", Dados.Tp.Crs).mTamanho(200),
            new Cmp(4, 1, "negocio", "Negócio - Cód.", Dados.Tp.Crs).mTamanho(6),
            new Cmp(4, 2, "negocios.nome", "Negócio - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true)
        });
    }

    @Override
    public void preparaEstr() throws Exception {
        botaRelacao(new CadRelacao("trocas").botaRelacao("troca", "codigo"));
        botaRelacao(new CadRelacao("produtos").botaRelacao("produto", "codigo"));
        botaRelacao(new CadRelacao("unidades").botaRelacao("unidade", "codigo"));
        botaReferencia(new CadReferenciar(this, "troca", CadTrocas.class).botaChave("troca", "codigo"));
        botaReferencia(new CadReferenciar(this, "produto", CadProdutos.class).botaChave("produto", "codigo").botaEstrangeiro("produtos.nome", "nome"));
        botaReferencia(new CadReferenciar(this, "unidade", CadUnidades.class).botaChave("unidade", "codigo").botaEstrangeiro("unidades.nome", "nome"));
        botaRelacao(new CadRelacao("negocios").botaRelacao("negocio", "codigo"));
        botaReferencia(new CadReferenciar(this, "negocio", CadNegocios.class).botaChave("negocio", "codigo").botaEstrangeiro("negocios.nome", "nome"));
    }

    @Override
    public void preparaIntf() throws Exception {
        super.preparaIntf();
        pegaCampo("produto").botaConferencia(new CnfUnidade());
    }

    private class CnfUnidade extends Conferencia {

        @Override
        public void confere(Object comOrigem) throws Exception {
            if (ehModoNovoOuEditar()) {
                String produto = pegaCampo("produto").pEdt().pgVlr().pgCrs("");
                String unidade = pegaCampo("unidade").pEdt().pgVlr().pgCrs("");
                if (!produto.isEmpty() && unidade.isEmpty()) {
                    String nova = conexao.busca("SELECT unidade FROM produtos WHERE codigo = ?",
                            new Vlrs(produto))
                            .pgCol().pgCrs();
                    pegaCampo("unidade").pEdt().mdVlr(nova);
                }
            }
        }
    }
}
