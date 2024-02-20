package pin.adm;

import java.util.Objects;
import pin.libamk.Cmp;
import pin.libbas.Conferencia;
import pin.libbas.Conjunto;
import pin.libbas.Dados;
import pin.libbas.Erro;
import pin.libutl.Utl;
import pin.libutl.UtlNumLon;
import pin.libvlr.Vlrs;
import pin.modamk.CadReferenciar;
import pin.modamk.CadRelacao;
import pin.modamk.Cadastro;

public class CadPedidosItens extends Cadastro {

  public CadPedidosItens() throws Exception {
    this(null);
  }

  public CadPedidosItens(String doPedido) throws Exception {
    super("itens_pedido", "Itens dos Pedidos", new Cmp[]{
      new Cmp(1, 1, "pedido", "Pedido - Cód.", Dados.Tp.Crs).mTamanho(10).botaDetalhe("chave", true).botaObrigatorio().mVlrFixo(doPedido),
      new Cmp(1, 2, "codigo", "Código", Dados.Tp.Crs).mTamanho(4).botaDetalhe("chave", true),
      new Cmp(1, 3, "produto", "Produto - Cód.", Dados.Tp.Crs).mTamanho(6).botaObrigatorio(),
      new Cmp(1, 4, "produtos.nome", "Produto - Nome", Dados.Tp.Crs).mTamanho(80).botaDetalhe("estrangeiro", true),
      new Cmp(1, 5, "quantidade", "Quantidade", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3).botaObrigatorio(),
      new Cmp(1, 6, "saldo", "Saldo", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3),
      new Cmp(2, 1, "tabela", "Tabela", Dados.Tp.Crs).mTamanho(6),
      new Cmp(2, 2, "preco", "Preço", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3).botaObrigatorio(),
      new Cmp(2, 3, "subtotal", "SubTotal", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3).botaDetalhe("podeInserir", false).botaDetalhe("podeEditar", false),
      new Cmp(2, 4, "desconto_per", "Desc. %", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3),
      new Cmp(2, 5, "desconto", "Desconto", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3),
      new Cmp(2, 6, "acrescimo_per", "Acre. %", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3),
      new Cmp(2, 7, "acrescimo", "Acréscimo", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3),
      new Cmp(2, 8, "total", "Total", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3).botaDetalhe("podeInserir", false).botaDetalhe("podeEditar", false),
      new Cmp(3, 1, "situacao", "Situação", Dados.Tp.Enu).botaOpcoes(new Object[]{"(A) Aprovado", "(C) Cancelado", "(L) Liberado", "(P) Pendente", "(F) Faturado"}),
      new Cmp(3, 2, "obs", "Obs", Dados.Tp.Crs).mTamanho(100),
      new Cmp(3, 3, "comissao_per", "Com. %", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3),
      new Cmp(3, 4, "comissao", "Comissão", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3),
      new Cmp(3, 5, "negocio", "Negócio - Cód.", Dados.Tp.Crs).mTamanho(6),
      new Cmp(3, 6, "negocios.nome", "Negócio - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true)
    });
  }

  @Override
  public void preparaEstr() throws Exception {
    if (pegaCampo("pedido").pVlrFixo() != null) {
      String tabela = conexao.busca("SELECT pessoas.tabela_preco "
              + " FROM pedidos "
              + " JOIN pessoas ON pessoas.codigo = pedidos.cliente"
              + " WHERE pedidos.codigo = ?",
              new Vlrs(pegaCampo("pedido").pVlrFixo()))
              .pgCol().pgCrs();
      pegaCampo("tabela").mVlrInicial(tabela);
    }
    botaRelacao(new CadRelacao("pedidos").botaRelacao("pedido", "codigo"));
    botaRelacao(new CadRelacao("produtos").botaRelacao("produto", "codigo"));
    botaRelacao(new CadRelacao("tabelas_precos").botaRelacao("tabela", "codigo"));
    botaReferencia(new CadReferenciar(this, "pedido", CadPedidos.class).botaChave("pedido", "codigo"));
    botaReferencia(new CadReferenciar(this, "produto", CadProdutos.class).botaChave("produto", "codigo").botaEstrangeiro("produtos.nome", "nome"));
    botaReferencia(new CadReferenciar(this, "tabela", CadTabelasPrecos.class).botaChave("tabela", "codigo"));
    botaReferencia(new CadReferenciar(this, "preco", CadPrecos.class).botaChaveFixo("produto", "produto").botaEstranFixo("produtos.nome", "produtos.nome").botaChave("tabela", "tabela").botaEstrangeiro("preco", "valor"));
    botaRelacao(new CadRelacao("negocios").botaRelacao("negocio", "codigo"));
    botaReferencia(new CadReferenciar(this, "negocio", CadNegocios.class).botaChave("negocio", "codigo").botaEstrangeiro("negocios.nome", "nome"));
  }

  private CnfProduto cnfProduto;
  private CnfSaldo cnfSaldo;
  private CnfPreco cnfPreco;
  private CnfComissao cnfComissao;
  private CnfCalculosPer cnfCalculosPer;
  private CnfCalculos cnfCalculos;
  private CnfTotais cnfTotais;
  private CnfFaturado cnfFaturado;

  @Override
  public void preparaIntf() throws Exception {
    cnfProduto = new CnfProduto();
    cnfSaldo = new CnfSaldo();
    cnfPreco = new CnfPreco();
    cnfComissao = new CnfComissao();
    cnfCalculosPer = new CnfCalculosPer();
    cnfCalculos = new CnfCalculos();
    cnfTotais = new CnfTotais();
    cnfFaturado = new CnfFaturado();
    pegaCampo("produto").botaConferencia(cnfProduto);
    pegaCampo("quantidade").botaConferencia(cnfSaldo);
    pegaCampo("quantidade").botaConferencia(cnfCalculos);
    pegaCampo("tabela").botaConferencia(cnfPreco);
    pegaCampo("tabela").botaConferencia(cnfComissao);
    pegaCampo("tabela").botaConferencia(cnfCalculos);
    pegaCampo("preco").botaConferencia(cnfComissao);
    pegaCampo("preco").botaConferencia(cnfCalculos);
    pegaCampo("desconto_per").botaConferencia(cnfCalculos);
    pegaCampo("acrescimo_per").botaConferencia(cnfCalculos);
    pegaCampo("comissao_per").botaConferencia(cnfCalculos);
    pegaCampo("desconto").botaConferencia(cnfCalculosPer);
    pegaCampo("desconto").botaConferencia(cnfCalculos);
    pegaCampo("acrescimo").botaConferencia(cnfCalculosPer);
    pegaCampo("acrescimo").botaConferencia(cnfCalculos);
    pegaCampo("comissao").botaConferencia(cnfCalculosPer);
    pegaCampo("comissao").botaConferencia(cnfCalculos);
    botaAntesDeEditar(cnfFaturado);
    botaAntesDeExcluir(cnfFaturado);
    botaAntesDeSalvar(cnfFaturado);
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
          pegaCampo("preco").pEdt().mdVlr(0.0);
          cnfPreco.confere(this);
          cnfCalculos.confere(this);
        }
      }
    }
  }

  private class CnfSaldo extends Conferencia {

    @Override
    public void confere(Object comOrigem) throws Exception {
      if (ehModoNovoOuEditar()) {
        Double quantidade = pegaCampo("quantidade").pEdt().pgVlr().pgNumLon(0.0);
        Double saldo = pegaCampo("saldo").pEdt().pgVlr().pgNumLon(0.0);
        if (saldo == 0.0) {
          pegaCampo("saldo").pEdt().mdVlr(quantidade);
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

  private class CnfComissao extends Conferencia {

    private volatile boolean conferindo = false;

    private Double getComissaoSugerida(String pedido, String tabela) throws Exception {
      String repres = conexao.busca("SELECT representante FROM pedidos WHERE codigo = ?",
              new Vlrs(pedido))
              .pgCol().pgCrs();
      Double result = conexao.busca("SELECT valor FROM pessoas_comissoes WHERE pessoa = ? AND tabela = ?",
              new Vlrs(repres, tabela))
              .pgCol().pgNumLon();
      if (result == null) {
        result = conexao.busca("SELECT comissao FROM tabelas_precos WHERE codigo = ?",
                new Vlrs(tabela))
                .pgCol().pgNumLon();
      }
      return result;
    }

    @Override
    public void confere(Object comOrigem) throws Exception {
      if (conferindo) {
        return;
      }
      try {
        conferindo = true;

        if (ehModoNovoOuEditar()) {
          String pedido = pegaCampo("pedido").pEdt().pgVlr().pgCrs("");
          String produto = pegaCampo("produto").pEdt().pgVlr().pgCrs("");
          String tabela = pegaCampo("tabela").pEdt().pgVlr().pgCrs("");
          Double preco = pegaCampo("preco").pEdt().pgVlr().pgNumLon(0.0);
          if (preco > 0.0) {
            Conjunto res = conexao.busca("SELECT tabela FROM precos WHERE produto = ? AND valor = ?",
                    new Vlrs(produto, preco));
            if (!res.vazio()) {
              if (tabela.isEmpty()) {
                tabela = res.pgCol().pgCrs();
              } else if (!res.tem(1, tabela)) {
                tabela = res.pgCol().pgCrs();
              }
            } else {
              tabela = "";
            }
            pegaCampo("tabela").pEdt().mdVlr(tabela);
          }
          Double comiSug = getComissaoSugerida(pedido, tabela);
          Double comiPer = pegaCampo("comissao_per").pEdt().pgVlr().pgNumLon(0.0);
          if (comiSug != null) {
            if (comiPer == 0.0) {
              pegaCampo("comissao_per").pEdt().mdVlr(comiSug);
            } else if (!Objects.equals(comiSug, comiPer)) {
              if (Utl.continua("A comissão sugerida é diferente da atual. Podemos atualizar?")) {
                pegaCampo("comissao_per").pEdt().mdVlr(comiSug);
              }
            }
          }
        }
      } catch (Exception e) {
        throw e;
      } finally {
        conferindo = false;
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
          if (janela.getFocusOwner() != null) {
            if ((janela.getFocusOwner().equals(pegaCampo("comissao").pEdt().controle()))) {
              Double comissao = pegaCampo("comissao").pEdt().pgVlr().pgNumLon(0.0);
              Double comiPer = comissao * 100 / total;
              pegaCampo("comissao_per").pEdt().mdVlr(UtlNumLon.arredonda(comiPer));
            }
          }
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
          Double comiPer = pegaCampo("comissao_per").pEdt().pgVlr().pgNumLon(0.0);
          Double comissao = total * comiPer / 100;
          pegaCampo("comissao").pEdt().mdVlr(UtlNumLon.arredonda(comissao));
        }
      }
    }
  }

  private class CnfTotais extends Conferencia {

    @Override
    public void confere(Object comOrigem) throws Exception {
      CadPedidos.calculaTotais(conexao, chavesUltimoSalvo[0].toString());
    }
  }

  private class CnfFaturado extends Conferencia {

    @Override
    public void confere(Object comOrigem) throws Exception {
      String pedido = pegaCampo("pedido").pEdt().pgVlr().pgCrs();
      String situacao = conexao.busca("SELECT situacao FROM pedidos WHERE codigo = ?",
              new Vlrs(pedido))
              .pgCol().pgCrs();
      if ("F".equals(situacao)) {
        throw new Erro("Este Pedido Já Foi Faturado");
      }
    }
  }
}
