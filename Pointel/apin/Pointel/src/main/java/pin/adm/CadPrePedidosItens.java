package pin.adm;

import java.util.ArrayList;
import java.util.List;
import pin.libamk.Cmp;
import pin.libbas.Conferencia;
import pin.libbas.Conjunto;
import pin.libbas.Dados;
import pin.libbas.Erro;
import pin.libutl.UtlNumLon;
import pin.libvlr.Vlrs;
import pin.modamk.CadReferenciar;
import pin.modamk.CadRelacao;
import pin.modamk.Cadastro;

public class CadPrePedidosItens extends Cadastro {

    public CadPrePedidosItens() throws Exception {
        this(null);
    }

    public CadPrePedidosItens(String doPrePedido) throws Exception {
        super("itens_prepedidos", "Itens dos Pré-Pedidos", new Cmp[]{
            new Cmp(1, 1, "prepedido", "Pré-Pedido - Cód.", Dados.Tp.Crs).mTamanho(10).botaDetalhe("chave", true).botaObrigatorio().mVlrFixo(doPrePedido),
            new Cmp(1, 2, "codigo", "Código", Dados.Tp.Crs).mTamanho(4).botaDetalhe("chave", true),
            new Cmp(1, 3, "produto", "Produto - Cód.", Dados.Tp.Crs).mTamanho(6).botaObrigatorio(),
            new Cmp(1, 4, "produtos.nome", "Produto - Nome", Dados.Tp.Crs).mTamanho(80).botaDetalhe("estrangeiro", true),
            new Cmp(1, 5, "quantidade", "Quantidade", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3).botaObrigatorio(),
            new Cmp(2, 1, "tabela", "Tabela", Dados.Tp.Crs).mTamanho(6),
            new Cmp(2, 2, "preco", "Preço", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3).botaObrigatorio(),
            new Cmp(2, 3, "subtotal", "SubTotal", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3).botaDetalhe("podeInserir", false).botaDetalhe("podeEditar", false),
            new Cmp(2, 4, "desconto_per", "Desc. %", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3),
            new Cmp(2, 5, "desconto", "Desconto", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3),
            new Cmp(2, 6, "acrescimo_per", "Acre. %", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3),
            new Cmp(2, 7, "acrescimo", "Acréscimo", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3),
            new Cmp(3, 1, "total", "Total", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3).botaDetalhe("podeInserir", false).botaDetalhe("podeEditar", false),
            new Cmp(3, 2, "obs", "Obs", Dados.Tp.Crs).mTamanho(100),
            new Cmp(3, 3, "negocio", "Negócio - Cód.", Dados.Tp.Crs).mTamanho(6),
            new Cmp(3, 4, "negocios.nome", "Negócio - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true)
        });
    }

    @Override
    public void preparaEstr() throws Exception {
        if (pegaCampo("prepedido").pVlrFixo() != null) {
            String tabela = conexao.busca("SELECT pessoas.tabela_preco "
                    + " FROM prepedidos "
                    + " JOIN pessoas ON pessoas.codigo = prepedidos.cliente"
                    + " WHERE prepedidos.codigo = ?",
                    new Vlrs(pegaCampo("prepedido").pVlrFixo()))
                    .pgCol().pgCrs();
            pegaCampo("tabela").mVlrInicial(tabela);
        }
        botaRelacao(new CadRelacao("prepedidos").botaRelacao("prepedido", "codigo"));
        botaRelacao(new CadRelacao("produtos").botaRelacao("produto", "codigo"));
        botaRelacao(new CadRelacao("tabelas_precos").botaRelacao("tabela", "codigo"));
        botaReferencia(new CadReferenciar(this, "prepedido", CadPrePedidos.class).botaChave("prepedido", "codigo"));
        botaReferencia(new CadReferenciar(this, "produto", CadProdutos.class).botaChave("produto", "codigo").botaEstrangeiro("produtos.nome", "nome"));
        botaReferencia(new CadReferenciar(this, "tabela", CadMinhasTabelasPrecos.class).botaChave("tabela", "codigo"));
        botaReferencia(new CadReferenciar(this, "preco", CadMeusPrecos.class).botaChaveFixo("produto", "produto").botaEstranFixo("produtos.nome", "produtos.nome").botaChave("tabela", "tabela").botaEstrangeiro("preco", "valor"));
        botaRelacao(new CadRelacao("negocios").botaRelacao("negocio", "codigo"));
        botaReferencia(new CadReferenciar(this, "negocio", CadNegocios.class).botaChave("negocio", "codigo").botaEstrangeiro("negocios.nome", "nome"));
    }

    private CnfProduto cnfProduto;
    private CnfPreco cnfPreco;
    private CnfCalculosPer cnfCalculosPer;
    private CnfCalculos cnfCalculos;
    private CnfTotais cnfTotais;
    private CnfEnviado cnfEnviado;

    @Override
    public void preparaIntf() throws Exception {
        List<Object> tabsRests = new ArrayList<>();
        Conjunto res = conexao.recorrentes().busca("SELECT tabela FROM pessoas_comissoes WHERE pessoa = ?",
                new Vlrs(conexao.pegaBaseUsuarioPessoa()));
        while (res.proximo()) {
            tabsRests.add(res.pgVlr(1));
        }
        pegaCampo("tabela").botaRestritos(tabsRests.toArray());
        cnfProduto = new CnfProduto();
        cnfPreco = new CnfPreco();
        cnfCalculosPer = new CnfCalculosPer();
        cnfCalculos = new CnfCalculos();
        cnfTotais = new CnfTotais();
        cnfEnviado = new CnfEnviado();
        pegaCampo("produto").botaConferencia(cnfProduto);
        pegaCampo("quantidade").botaConferencia(cnfCalculos);
        pegaCampo("tabela").botaConferencia(cnfPreco);
        pegaCampo("tabela").botaConferencia(cnfCalculos);
        pegaCampo("preco").botaConferencia(cnfCalculos);
        pegaCampo("desconto_per").botaConferencia(cnfCalculos);
        pegaCampo("acrescimo_per").botaConferencia(cnfCalculos);
        pegaCampo("desconto").botaConferencia(cnfCalculosPer);
        pegaCampo("desconto").botaConferencia(cnfCalculos);
        pegaCampo("acrescimo").botaConferencia(cnfCalculosPer);
        pegaCampo("acrescimo").botaConferencia(cnfCalculos);
        botaAntesDeEditar(cnfEnviado);
        botaAntesDeExcluir(cnfEnviado);
        botaAntesDeSalvar(cnfEnviado);
        botaAntesDeSalvar(cnfCalculosPer);
        botaAntesDeSalvar(cnfCalculos);
        botaDepoisDeSalvar(cnfTotais);
        botaDepoisDeExcluir(cnfTotais);
    }

    private class CnfProduto extends Conferencia {

        @Override
        public void confere(Object comOrigem) throws Exception {
            if (ehModoNovoOuEditar()) {
                if (verificaMudanca(pegaCampo("produto"))) {
                    if (!pegaCampo("produto").pEdt().vazio()) {
                        if (!conexao.busca("SELECT ativo FROM produtos WHERE codigo = ?",
                                new Vlrs(pegaCampo("produto").pEdt().pgVlr()))
                                .pgCol().pgLog(false)) {
                            pegaCampo("produto").pEdt().limpa();
                            throw new Erro("Este Produto Não Está Ativo");
                        }
                    }
                    pegaCampo("preco").pEdt().mdVlr(0.0);
                    cnfPreco.confere(comOrigem);
                    cnfCalculos.confere(comOrigem);
                }
            }
        }
    }

    private class CnfPreco extends Conferencia {

        @Override
        public void confere(Object comOrigem) throws Exception {
            if (ehModoNovoOuEditar()) {
                String produto = pegaCampo("produto").pEdt().pgVlr().pgCrs("");
                String tabela = pegaCampo("tabela").pEdt().pgVlr().pgCrs("");
                if (!tabela.isEmpty() && !produto.isEmpty()) {
                    Double sugpreco = conexao.busca("SELECT valor FROM precos WHERE produto = ? AND tabela = ?",
                            new Vlrs(produto, tabela))
                            .pgCol().pgNumLon(0.0);
                    pegaCampo("preco").pEdt().mdVlr(sugpreco);
                }
            }
        }
    }

    private class CnfCalculosPer extends Conferencia {

        @Override
        public void confere(Object comOrigem) throws Exception {
            if (ehModoNovoOuEditar()) {
                Double quantidade = pegaCampo("quantidade").pEdt().pgVlr().pgNumLon();
                Double preco = pegaCampo("preco").pEdt().pgVlr().pgNumLon();
                if ((quantidade != null) && (preco != null)) {
                    Double subtotal = quantidade * preco;
                    pegaCampo("subtotal").pEdt().mdVlr(subtotal);
                    Double desc = pegaCampo("desconto").pEdt().pgVlr().pgNumLon(0.0);
                    Double acre = pegaCampo("acrescimo").pEdt().pgVlr().pgNumLon(0.0);
                    Double descPer = 0.0;
                    Double acrePer = 0.0;
                    if (subtotal > 0.0) {
                        descPer = UtlNumLon.racionaliza(desc * 100 / subtotal);
                        acrePer = UtlNumLon.racionaliza(acre * 100 / subtotal);
                    }
                    pegaCampo("desconto_per").pEdt().mdVlr(UtlNumLon.arredonda(descPer));
                    pegaCampo("acrescimo_per").pEdt().mdVlr(UtlNumLon.arredonda(acrePer));
                    Double total = (subtotal - desc) + acre;
                    pegaCampo("total").pEdt().mdVlr(UtlNumLon.arredonda(total));
                }
            }
        }
    }

    private class CnfCalculos extends Conferencia {

        @Override
        public void confere(Object comOrigem) throws Exception {
            if (ehModoNovoOuEditar()) {
                Double quantidade = pegaCampo("quantidade").pEdt().pgVlr().pgNumLon();
                Double preco = pegaCampo("preco").pEdt().pgVlr().pgNumLon();
                if (quantidade != null && preco != null) {
                    Double subtotal = quantidade * preco;
                    pegaCampo("subtotal").pEdt().mdVlr(subtotal);
                    Double descPer = pegaCampo("desconto_per").pEdt().pgVlr().pgNumLon(0.0);
                    Double acrePer = pegaCampo("acrescimo_per").pEdt().pgVlr().pgNumLon(0.0);
                    Double desc = subtotal * descPer / 100;
                    Double acre = subtotal * acrePer / 100;
                    Double total = (subtotal - desc) + acre;
                    pegaCampo("desconto").pEdt().mdVlr(UtlNumLon.arredonda(desc));
                    pegaCampo("acrescimo").pEdt().mdVlr(UtlNumLon.arredonda(acre));
                    pegaCampo("total").pEdt().mdVlr(UtlNumLon.arredonda(total));
                }
            }
        }
    }

    private class CnfTotais extends Conferencia {

        @Override
        public void confere(Object comOrigem) throws Exception {
            CadPrePedidos.calculaTotais(conexao, chavesUltimoSalvo[0].toString());
        }
    }

    private class CnfEnviado extends Conferencia {

        @Override
        public void confere(Object comOrigem) throws Exception {
            String prepedido = pegaCampo("prepedido").pEdt().pgVlr().pgCrs();
            if (conexao.busca("SELECT enviado FROM prepedidos WHERE codigo = ?",
                    new Vlrs(prepedido))
                    .pgCol().pgLog(false)) {
                throw new Erro("Este Pré-Pedido Já Foi Enviado");
            }
        }
    }
}
