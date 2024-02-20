package pin.adm;

import pin.libamk.Botao;
import pin.libamk.Cmp;
import pin.libbas.Conferencia;
import pin.libbas.Conjunto;
import pin.libbas.Dados;
import pin.libbas.Erro;
import pin.libbas.Globais;
import pin.libutl.Utl;
import pin.libutl.UtlCrs;
import pin.libutl.UtlDat;
import pin.libutl.UtlNumLon;
import pin.libutl.UtlTem;
import pin.libvlr.Vlrs;
import pin.modamk.CadReferenciar;
import pin.modamk.CadRelacao;
import pin.modamk.Cadastro;
import pin.modamk.Recursos;
import pin.modamk.Relatorio;
import pin.modamk.Relatorios;
import pin.modinf.Banco;
import pin.modinf.CodigosMod;

public class CadFaturas extends Cadastro {

  public CadFaturas() throws Exception {
    super("faturas", "Faturas", new Cmp[]{
      new Cmp(1, 1, "serie", "Série", Dados.Tp.Crs).mTamanho(4).botaDetalhe("chave", true),
      new Cmp(1, 2, "codigo", "Código", Dados.Tp.Crs).mTamanho(10).botaDetalhe("chave", true),
      new Cmp(1, 3, "ped_vendedor", "Ped. Vendedor", Dados.Tp.Crs).mTamanho(18),
      new Cmp(1, 4, "emitido_data", "Emitido Em", Dados.Tp.Dat).mVlrPadrao("CURRENT_DATE")
      .botaDetalhe("podeInserir", false).botaDetalhe("podeEditar", false),
      new Cmp(1, 5, "emitido_hora", "Emitido As", Dados.Tp.Hor).mVlrPadrao("CURRENT_TIME")
      .botaDetalhe("podeInserir", false).botaDetalhe("podeEditar", false),
      new Cmp(2, 1, "cliente", "Cliente - Cód.", Dados.Tp.Crs).mTamanho(8).botaObrigatorio(),
      new Cmp(2, 2, "clientes.nome", "Cliente - Nome", Dados.Tp.Crs).mTamanho(80).botaDetalhe("estrangeiro",
      true),
      new Cmp(2, 3, "clientes.fantasia", "Cliente - Fantasia", Dados.Tp.Crs).mTamanho(60)
      .botaDetalhe("estrangeiro", true),
      new Cmp(2, 4, "cobranca", "Cobrança - Cód.", Dados.Tp.Crs).mTamanho(8),
      new Cmp(2, 5, "cobrancas.nome", "Cobrança - Nome", Dados.Tp.Crs).mTamanho(80)
      .botaDetalhe("estrangeiro", true),
      new Cmp(3, 1, "entrega", "Entrega - Cód.", Dados.Tp.Crs).mTamanho(8),
      new Cmp(3, 2, "entregas.nome", "Entrega - Nome", Dados.Tp.Crs).mTamanho(80).botaDetalhe("estrangeiro",
      true),
      new Cmp(3, 3, "cidades.nome", "Entrega - Cidade", Dados.Tp.Crs).mTamanho(60)
      .botaDetalhe("estrangeiro", true),
      new Cmp(3, 4, "representante", "Representante - Cód.", Dados.Tp.Crs).mTamanho(8),
      new Cmp(3, 5, "representantes.nome", "Representante - Nome", Dados.Tp.Crs).mTamanho(80)
      .botaDetalhe("estrangeiro", true),
      new Cmp(4, 1, "transportadora", "Transportadora - Cód.", Dados.Tp.Crs).mTamanho(8),
      new Cmp(4, 2, "transportadoras.nome", "Transportadora - Nome", Dados.Tp.Crs).mTamanho(80)
      .botaDetalhe("estrangeiro", true),
      new Cmp(4, 3, "frete", "Frete", Dados.Tp.Enu)
      .botaOpcoes(new Object[]{"(E) Emitente", "(D) Destinatário"}),
      new Cmp(4, 4, "volume", "Volume", Dados.Tp.Crs).mTamanho(40),
      new Cmp(4, 5, "peso_liquido", "Peso", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3),
      new Cmp(4, 6, "val_frete", "Valor", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3),
      new Cmp(5, 1, "cond_pagamento", "Cond. Pgto - Cód.", Dados.Tp.Crs).mTamanho(4).botaObrigatorio(),
      new Cmp(5, 2, "condicoes_pagamento.nome", "Cond. Pgto - Nome", Dados.Tp.Crs).mTamanho(60)
      .botaDetalhe("estrangeiro", true),
      new Cmp(5, 3, "val_out_desc", "Out. Descontos", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3).mLargura(100),
      new Cmp(5, 4, "val_out_acres", "Out. Acréscimos", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3)
      .mLargura(100),
      new Cmp(5, 5, "clientes.obs", "Cliente - Obs", Dados.Tp.Crs).mTamanho(120).mLargura(310).botaDetalhe("estrangeiro",
      true),
      new Cmp(6, 1, "obs1", "Obs. 1", Dados.Tp.Crs).mTamanho(200).mLargura(450),
      new Cmp(6, 2, "obs2", "Obs. 2", Dados.Tp.Crs).mTamanho(200).mLargura(350),
      new Cmp(7, 1, "negocio", "Negócio - Cód.", Dados.Tp.Crs).mTamanho(6),
      new Cmp(7, 2, "negocios.nome", "Negócio - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro",
      true),
      new Cmp(7, 3, "subtotal", "SubTotal", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3)
      .botaDetalhe("podeInserir", false).botaDetalhe("podeEditar", false),
      new Cmp(7, 4, "desc_itens", "Desc. Itens", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3)
      .botaDetalhe("podeInserir", false).botaDetalhe("podeEditar", false),
      new Cmp(7, 5, "acresc_itens", "Acresc. Itens", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3)
      .botaDetalhe("podeInserir", false).botaDetalhe("podeEditar", false),
      new Cmp(7, 6, "total", "Total", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3)
      .botaDetalhe("podeInserir", false).botaDetalhe("podeEditar", false),
      new Cmp(7, 7, "comissao", "Comissão", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3)
      .botaDetalhe("podeInserir", false).botaDetalhe("podeEditar", false),});
  }

  @Override
  public void preparaEstr() throws Exception {
    botaRelacao(new CadRelacao("clientes", "pessoas").botaRelacao("cliente", "codigo"));
    botaRelacao(new CadRelacao("cobrancas", "pessoas").botaRelacao("cobranca", "codigo"));
    botaRelacao(new CadRelacao("entregas", "pessoas").botaRelacao("entrega", "codigo"));
    botaRelacao(new CadRelacao("cidades").botaRelacao("entregas.cidade", "codigo"));
    botaRelacao(new CadRelacao("representantes", "pessoas").botaRelacao("representante", "codigo"));
    botaRelacao(new CadRelacao("transportadoras", "pessoas").botaRelacao("transportadora", "codigo"));
    botaRelacao(new CadRelacao("condicoes_pagamento").botaRelacao("cond_pagamento", "codigo"));
    botaReferencia(new CadReferenciar(this, "cliente", CadPessoas.class).botaChave("cliente", "codigo")
            .botaEstrangeiro("clientes.nome", "nome").botaEstrangeiro("clientes.fantasia", "fantasia"));
    botaReferencia(new CadReferenciar(this, "cobranca", CadPessoas.class).botaChave("cobranca", "codigo")
            .botaEstrangeiro("cobrancas.nome", "nome"));
    botaReferencia(new CadReferenciar(this, "entrega", CadPessoas.class).botaChave("entrega", "codigo")
            .botaEstrangeiro("entregas.nome", "nome").botaEstrangeiro("entregas.fantasia", "fantasia"));
    botaReferencia(new CadReferenciar(this, "representante", CadPessoas.class).botaChave("representante", "codigo")
            .botaEstrangeiro("representantes.nome", "nome"));
    botaReferencia(new CadReferenciar(this, "transportadora", CadPessoas.class)
            .botaChave("transportadora", "codigo").botaEstrangeiro("transportadoras.nome", "nome"));
    botaReferencia(new CadReferenciar(this, "cond_pagamento", CadCondicoesPagamento.class)
            .botaChave("cond_pagamento", "codigo").botaEstrangeiro("condicoes_pagamento.nome", "nome"));
    botaRelacao(new CadRelacao("negocios").botaRelacao("negocio", "codigo"));
    botaReferencia(new CadReferenciar(this, "negocio", CadNegocios.class).botaChave("negocio", "codigo")
            .botaEstrangeiro("negocios.nome", "nome"));
  }

  @Override
  public void preparaIntf() throws Exception {
    botaParaAtualizar("emitido_data");
    botaParaAtualizar("emitido_hora");
    botaParaAtualizar("subtotal");
    botaParaAtualizar("subtotal");
    botaParaAtualizar("desc_itens");
    botaParaAtualizar("acresc_itens");
    botaParaAtualizar("total");
    botaParaAtualizar("comissao");
    pegaCampo("cliente").botaConferencia(new CnfCliente());
    Conferencia confTotal = new CnfTotal();
    pegaCampo("val_frete").botaConferencia(confTotal);
    pegaCampo("val_out_desc").botaConferencia(confTotal);
    pegaCampo("val_out_acres").botaConferencia(confTotal);
    Conferencia confLancamentos = new CnfBloqueado();
    botaAntesDeEditar(confLancamentos);
    botaAntesDeExcluir(confLancamentos);
    botaAntesDeExcluir(new CnfExcluir());
    botaAntesDeSalvar(new CnfAntesSalvar());
    botaAntesDeSalvar(new CnfDepoisSalvar());
    botaAntesDeFechar(new CnfFechar());
    botaBotao(new BotItens());
    botaBotao(new BotFinanceiro());
    botaBotao(new BotImprimir());
    botaBotao(new BotDesbloquear());
  }

  public Boolean lancamentosGerados() throws Exception {
    String serie = pegaCampo("serie").pEdt().pgVlr().pgCrs();
    String codigo = pegaCampo("codigo").pEdt().pgVlr().pgCrs();
    return conexao.busca("SELECT lan_ger FROM faturas WHERE serie = ? AND codigo = ?", new Vlrs(serie, codigo))
            .pgVlrLog(1).pgLog(false);
  }

  private class CnfCliente extends Conferencia {

    @Override
    public void confere(Object comOrigem) throws Exception {
      if (ehModoNovoOuEditar()) {
        if (!pegaCampo("cliente").pEdt().vazio()) {
          if (pegaCampo("cobrancas.nome").pEdt().vazio()) {
            pegaCampo("cobranca").pEdt().mdVlr(pegaCampo("cliente").pEdt().pgVlr());
          }
          if (pegaCampo("entregas.nome").pEdt().vazio()) {
            pegaCampo("entrega").pEdt().mdVlr(pegaCampo("cliente").pEdt().pgVlr());
          }
          pegaCampo("representante").pEdt()
                  .mdVlr(conexao.busca("SELECT representante FROM pessoas WHERE codigo = ?",
                          new Vlrs(pegaCampo("cliente").pEdt().pgVlr())).pgCol());
          if (pegaCampo("condicoes_pagamento.nome").pEdt().vazio()) {
            new Thread("Pega Condição de Pagamento do Cliente") {
              @Override
              public void run() {
                try {
                  pegaCampo("cond_pagamento").pEdt()
                          .mdVlr(conexao.busca("SELECT cond_pagamento FROM pessoas WHERE codigo = ?",
                                  new Vlrs(pegaCampo("cliente").pEdt().pgVlr())).pgCol());
                } catch (Exception ex) {
                  Utl.registra(ex);
                }
              }
            }.start();
          }
        }
      }
    }
  }

  private class CnfTotal extends Conferencia {

    @Override
    public void confere(Object comOrigem) throws Exception {
      String frete = pegaCampo("frete").pEdt().pgVlr().pgCrs("");
      Double vfrete = pegaCampo("val_frete").pEdt().pgVlr().pgNumLon(0.0);
      if (!frete.equals("D")) {
        vfrete = 0.0;
      }
      Double desc = pegaCampo("val_out_desc").pEdt().pgVlr().pgNumLon(0.0);
      Double acres = pegaCampo("val_out_acres").pEdt().pgVlr().pgNumLon(0.0);
      Double subtotal = pegaCampo("subtotal").pEdt().pgVlr().pgNumLon(0.0);
      Double descitns = pegaCampo("desc_itens").pEdt().pgVlr().pgNumLon(0.0);
      Double acreitns = pegaCampo("acresc_itens").pEdt().pgVlr().pgNumLon(0.0);
      Double total = subtotal + (acreitns + acres + vfrete) - (descitns + desc);
      pegaCampo("total").pEdt().mdVlr(total);
    }

  }

  private class CnfBloqueado extends Conferencia {

    @Override
    public void confere(Object comOrigem) throws Exception {
      if (lancamentosGerados()) {
        throw new Erro("Esta Fatura Está Bloqueada");
      }
    }

  }

  private class CnfExcluir extends Conferencia {

    @Override
    public void confere(Object comOrigem) throws Exception {
      conexao.opera("DELETE FROM itens_faturas WHERE serie = ? AND nf = ?",
              new Vlrs(pegaCampo("serie").pEdt().pgVlr(), pegaCampo("codigo").pEdt().pgVlr()));
    }

  }

  private class CnfAntesSalvar extends Conferencia {

    @Override
    public void confere(Object comOrigem) throws Exception {
      String codEdt = pegaCampo("codigo").pEdt().pgVlr().pgCrs("");
      if (codEdt.isEmpty()) {
        String serie = pegaCampo("serie").pEdt().pgVlr().pgCrs();
        String codigo = conexao.busca("SELECT ultima_nf FROM series WHERE codigo = ?", new Vlrs(serie)).pgCol()
                .pgCrs("");
        codigo = UtlCrs.repete("0", 10 - codigo.length()) + codigo;
        codigo = UtlCrs.pegaProxima(codigo, true);
        pegaCampo("codigo").pEdt().mdVlr(codigo);
        conexao.opera("UPDATE series SET ultima_nf = ? WHERE codigo = ?",
                new Vlrs(Integer.parseInt(codigo), serie));
      }
    }
  }

  private class CnfDepoisSalvar extends Conferencia {

    @Override
    public void confere(Object comOrigem) throws Exception {
      String serie = pegaCampo("serie").pEdt().pgVlr().pgCrs();
      String codigo = pegaCampo("codigo").pEdt().pgVlr().pgCrs("");
      calculaTotais(conexao, serie, codigo);
    }
  }

  private class CnfFechar extends Conferencia {

    @Override
    public void confere(Object comOrigem) throws Exception {
      if (pegaCampo("codigo").pVlrFixo() != null) {
        Integer quantimp = conexao
                .busca("SELECT quantimp FROM faturas WHERE serie = ? AND codigo = ?",
                        new Vlrs(pegaCampo("serie").pEdt().pgVlr(), pegaCampo("codigo").pEdt().pgVlr()))
                .pgCol().pgInt(0);
        if (quantimp <= 0) {
          if (!Utl.continua("Esta Fatura Ainda Não Foi Impressa")) {
            throw new Erro("Esta Fatura Ainda Não Foi Impressa").tiraAlertavel();
          }
        }
      }
    }
  }

  private class BotItens extends Botao {

    public BotItens() {
      super("Itens", 'I');
    }

    @Override
    public void aoExecutar(Object comOrigem) throws Exception {
      if (confirmar(true)) {
        String serie = pegaCampo("serie").pEdt().pgVlr().pgCrs();
        String fatura = pegaCampo("codigo").pEdt().pgVlr().pgCrs();
        CadFaturasItens filho = new CadFaturasItens(serie, fatura);
        filho.mostra();
      }
    }
  }

  private class BotFinanceiro extends Botao {

    public BotFinanceiro() {
      super("Financeiro", 'F');
    }

    @Override
    public void aoExecutar(Object comOrigem) throws Exception {
      if (confirmar(true)) {
        if (!verificaAtualizacoes()) {
          Utl.alerta("Problema nas atualização. Não é possível gerar o financeiro.");
          return;
        }
        String serie = pegaCampo("serie").pEdt().pgVlr().pgCrs();
        String codigo = pegaCampo("codigo").pEdt().pgVlr().pgCrs();
        String pessoa = pegaCampo("cliente").pEdt().pgVlr().pgCrs();
        Boolean lancados = lancamentosGerados();
        if (!lancados) {
          Double limite = conexao
                  .busca("SELECT limite_credito FROM pessoas WHERE codigo = ?", new Vlrs(pessoa)).pgCol()
                  .pgNumLon(0.0);
          Double aberto = conexao
                  .busca("SELECT SUM(saldo) FROM lancamentos WHERE situacao = 'A' AND pessoa = ?",
                          new Vlrs(pessoa))
                  .pgCol().pgNumLon(0.0);
          Double total = pegaCampo("total").pEdt().pgVlr().pgNumLon(0.0);
          if (aberto + total > limite) {
            Utl.alerta("Este cliente tem limite de crédito de R$ " + UtlNumLon.formata(limite) + "\n"
                    + "e possui lançamentos em aberto no valor de R$ " + UtlNumLon.formata(aberto) + "\n"
                    + "por isso não é possível liberar o valor de R$ " + UtlNumLon.formata(total) + "\n"
                    + "por isso o lançamento desta fatura não será gerado.");
          } else {
            String cond_pgto = pegaCampo("cond_pagamento").pEdt().pgVlr().pgCrs();
            Conjunto rst = conexao.busca(
                    "SELECT dias, tipo_pagamento, parcela FROM prazos WHERE cond_pagamento = ?",
                    new Vlrs(cond_pgto));
            while (rst.proximo()) {
              Integer dias = rst.pgVlr("dias").pgInt();
              String tp_pgto = rst.pgVlr("tipo_pagamento").pgCrs();
              Double parcela = rst.pgVlr("parcela").pgNumLon();
              String codLan = UtlCrs.pega(CodigosMod.pegaProximo(conexao, "lancamentos"));
              Object emitido = pegaCampo("emitido_data").pEdt().pgVlr();
              java.sql.Date data = UtlDat.pegaSQL(emitido);
              java.sql.Date vencimento = UtlDat.pegaSQL(UtlTem.somaDias(UtlDat.pega(data), dias));
              String pedVend = pegaCampo("ped_vendedor").pEdt().pgVlr().pgCrs();
              String hist = conexao.conCfgs()
                      .pegaCrs("PointelAdmin - Opções - Vendas - Histórico das Faturas", "");
              if (hist.isEmpty()) {
                throw new Exception("Histórico padrão das Faturas não está selecionado.");
              }
              String cont = conexao.conCfgs()
                      .pegaCrs("PointelAdmin - Opções - Vendas - Conta das Faturas", "");
              if (cont.isEmpty()) {
                throw new Exception("Conta padrão das Faturas não está selecionada.");
              }
              String port = conexao.conCfgs()
                      .pegaCrs("PointelAdmin - Opções - Vendas - Portador das Faturas", "");
              if (port.isEmpty()) {
                throw new Exception("Portador padrão das Faturas não está selecionado.");
              }
              String cliente = pegaCampo("cliente").pEdt().pgVlr().pgCrs();
              String cobranca = pegaCampo("cobranca").pEdt().pgVlr().pgCrs();
              Double tot = pegaCampo("total").pEdt().pgVlr().pgNumLon(0.0);
              Double valTot = tot * parcela / 100;
              String repres = pegaCampo("representante").pEdt().pgVlr().pgCrs();
              Double com = pegaCampo("comissao").pEdt().pgVlr().pgNumLon(0.0);
              Double valCom = com * parcela / 100;
              String sqlLan = "INSERT INTO lancamentos (codigo, data, emissao, vencimento, serie, fatura, ped_vendedor, historico, "
                      + "tipo, situacao, conta, pessoa, cobranca, tipo_pagamento, portador, valor, saldo, representante, comissao, per_nf)"
                      + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, 'R', 'A', ?, ?, ?, ?, ?, ?, ?, ?, ?, 'S')";
              Vlrs parsLan = new Vlrs(codLan, data, data, vencimento, serie, codigo, pedVend, hist, cont,
                      cliente, cobranca, tp_pgto, port, valTot, valTot, repres, valCom);
              if (conexao.opera(sqlLan, parsLan) <= 0) {
                throw new Exception("Não foi Possível Gerar os Lançamentos");
              }
              lancados = true;
            }
          }
          if (!lancados) {
            Utl.alerta("Nenhum Lançamento foi Gerado");
          } else {
            conexao.opera("UPDATE faturas SET lan_ger = 'S' WHERE serie = ? AND codigo = ?",
                    new Vlrs(serie, codigo));
            Utl.alerta("Lançamentos Gerados com Sucesso");
            botaModoProcurando(false, false);
          }
        } else {
          Utl.alerta("Lançamento Já Gerados");
        }
      }
    }
  }

  private class BotImprimir extends Botao {

    public BotImprimir() {
      super("Imprimir", 'I');
    }

    @Override
    public void aoExecutar(Object comOrigem) throws Exception {
      if (confirmar(true)) {
        String serie = pegaCampo("serie").pEdt().pgVlr().pgCrs();
        String codigo = pegaCampo("codigo").pEdt().pgVlr().pgCrs();
        Boolean lancados = lancamentosGerados();
        if (lancados) {
          RelFaturasRecibosNF.reiniciarCodigo();
          RelFaturasDuplicatas.reiniciarCodigo();
          Relatorios relats = new Relatorios(
                  new Relatorio[]{new RelFaturas(serie, codigo), new RelFaturasRecibos(serie, codigo),
                    new RelFaturasRecibosNF(serie, codigo), new RelFaturasDuplicatas(serie, codigo)});
          relats.escolher();
        } else {
          throw new Exception("É necessário gerar os lançamentos antes de imprimir");
        }
      }
    }
  }

  private class BotDesbloquear extends Botao {

    public BotDesbloquear() {
      super("Desbloquear", 'D');
    }

    @Override
    public void aoExecutar(Object comOrigem) throws Exception {
      String serie = pegaCampo("serie").pEdt().pgVlr().pgCrs();
      String codigo = pegaCampo("codigo").pEdt().pgVlr().pgCrs();
      Recursos mainRecs = (Recursos) Globais.pega("mainRecs");
      mainRecs.pega("Pointel.PointelAdmin.Negócios.Rotinas", "Desbloquear Fatura")
              .abreOuDisponibiliza(new Object[]{serie, codigo});
    }
  }

  public static Boolean calculaTotais(Banco noBanco, String daSerie, String doCodigo) throws Exception {
    Object serie = daSerie;
    Object nf = doCodigo;
    Vlrs valores = new Vlrs(serie, nf, serie, nf);
    String sql = "UPDATE faturas SET subtotal = (SELECT SUM(subtotal) FROM itens_faturas WHERE serie = ? AND nf = ?) WHERE serie = ? AND codigo = ?";
    noBanco.opera(sql, valores);
    sql = "UPDATE faturas SET desc_itens = (SELECT SUM(desconto) FROM itens_faturas WHERE serie = ? AND nf = ?) WHERE serie = ? AND codigo = ?";
    noBanco.opera(sql, valores);
    sql = "UPDATE faturas SET acresc_itens = (SELECT SUM(acrescimo) FROM itens_faturas WHERE serie = ? AND nf = ?) WHERE serie = ? AND codigo = ?";
    noBanco.opera(sql, valores);
    Conjunto res = noBanco.busca(
            "SELECT frete, val_frete, val_out_desc, val_out_acres FROM faturas WHERE serie = ? AND codigo = ?",
            new Vlrs(serie, nf));
    String frete = res.pgVlr(1).pgCrs("");
    Double vlfrete = res.pgVlr(2).pgNumLon(0.0);
    if (!frete.equals("D")) {
      vlfrete = 0.0;
    }
    Double outDesc = res.pgVlr(3).pgNumLon(0.0);
    Double outAcre = res.pgVlr(4).pgNumLon(0.0);
    Double totItns = noBanco
            .busca("SELECT SUM(total) FROM itens_faturas WHERE serie = ? AND nf = ?", new Vlrs(serie, nf)).pgCol()
            .pgNumLon(0.0);
    Double totFat = (totItns + outAcre + vlfrete) - outDesc;
    sql = "UPDATE faturas SET total = ? WHERE serie = ? AND codigo = ?";
    noBanco.opera(sql, new Vlrs(UtlNumLon.arredonda(totFat), serie, nf));
    sql = "UPDATE faturas SET comissao = (SELECT SUM(comissao) FROM itens_faturas WHERE serie = ? AND nf = ?) WHERE serie = ? AND codigo = ?";
    noBanco.opera(sql, valores);
    return true;
  }
}
