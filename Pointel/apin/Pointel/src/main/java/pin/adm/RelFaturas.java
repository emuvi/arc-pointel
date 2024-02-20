package pin.adm;

import pin.libutl.Utl;
import pin.libvlr.Vlrs;
import pin.modamk.Relatorio;
import pin.modamk.RelatorioCampo;
import pin.modamk.RelatorioPagina;
import pin.modamk.RelatorioParametro;

public class RelFaturas extends Relatorio {

  public RelFaturas() {
    this(null, null);
  }

  public RelFaturas(Object aSerie, Object aCodigo) {
    super("Fatura", Tipo.Mat, RelatorioPagina.padraoMatricial(), new RelatorioCampo[]{
      new RelatorioCampo(1, 1, "FATURA       :", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
      new RelatorioCampo(1, 16, "serie", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.DADO).botaTamanho(6),
      new RelatorioCampo(1, 23, "-", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
      new RelatorioCampo(1, 25, "codigo", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.DADO).botaTamanho(13),
      new RelatorioCampo(1, 39, "PED.:", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
      new RelatorioCampo(1, 45, "ped_vendedor", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.DADO).botaTamanho(20),
      new RelatorioCampo(1, 67, "EM:", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
      new RelatorioCampo(1, 71, "emitido_data", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.DADO).botaTamanho(10).botaFormato(RelatorioCampo.Formatos.DATA, "dd/MM/yyyy"),
      new RelatorioCampo(2, 1, "RAZAO SOCIAL :", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
      new RelatorioCampo(2, 16, "cliente_nome", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.DADO).botaTamanho(50),
      new RelatorioCampo(2, 64, "CODIGO:", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
      new RelatorioCampo(2, 72, "cliente_codigo", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.DADO).botaTamanho(10),
      new RelatorioCampo(3, 1, "FANTASIA     :", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
      new RelatorioCampo(3, 16, "fantasia", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.DADO).botaTamanho(52),
      new RelatorioCampo(3, 62, "FONE:", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
      new RelatorioCampo(3, 68, "fone1", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.DADO).botaTamanho(14),
      new RelatorioCampo(4, 1, "ENDERECO     :", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
      new RelatorioCampo(4, 16, "endereco", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.DADO).botaTamanho(58),
      new RelatorioCampo(4, 74, "UF:", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
      new RelatorioCampo(4, 78, "estado", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.DADO).botaTamanho(4),
      new RelatorioCampo(5, 1, "CIDADE       :", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
      new RelatorioCampo(5, 16, "cidade_bairro", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.DADO).botaTamanho(67),
      new RelatorioCampo(6, 1, "COND. PGTO   :", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
      new RelatorioCampo(6, 16, "cond_nome", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.DADO).botaTamanho(35),
      new RelatorioCampo(6, 53, "REPRES. :", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
      new RelatorioCampo(6, 63, "repr_nome", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.DADO).botaTamanho(20),
      new RelatorioCampo(7, 1, "OBSERVACOES  :", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
      new RelatorioCampo(7, 16, "obs", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.DADO).botaTamanho(66),
      new RelatorioCampo(8, 16, "obs", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.DADO).botaTamanho(66).botaInicio(66),
      new RelatorioCampo(9, 1, "QUANT", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaTamanho(7).botaADireita(),
      new RelatorioCampo(9, 9, "UND", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
      new RelatorioCampo(9, 13, "DESCRICAO", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
      new RelatorioCampo(9, 66, "UNIT", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaTamanho(7).botaADireita(),
      new RelatorioCampo(9, 74, "TOTAL", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaTamanho(7).botaADireita(),
      new RelatorioCampo(1, 1, "quantidade", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaTamanho(7).botaFormato(RelatorioCampo.Formatos.NUMERO_LON, "#0.00").botaADireita(),
      new RelatorioCampo(1, 9, "unidade", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaTamanho(3),
      new RelatorioCampo(1, 13, "item", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaTamanho(54),
      new RelatorioCampo(1, 66, "preco", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaTamanho(7).botaFormato(RelatorioCampo.Formatos.NUMERO_LON, "#0.00").botaADireita(),
      new RelatorioCampo(1, 74, "subtotal", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaTamanho(7).botaFormato(RelatorioCampo.Formatos.NUMERO_LON, "#0.00").botaADireita(),
      new RelatorioCampo(0, 1, "_________________________", RelatorioCampo.Area.FIM_PAGINA, RelatorioCampo.Tp.TEXTO),
      new RelatorioCampo(1, 1, "ASSINATURA", RelatorioCampo.Area.FIM_PAGINA, RelatorioCampo.Tp.TEXTO).botaTamanho(25).botaAoCentro(),
      new RelatorioCampo(1, 49, "DESCONTO:", RelatorioCampo.Area.FIM_PAGINA, RelatorioCampo.Tp.TEXTO),
      new RelatorioCampo(1, 59, "fat_desc", RelatorioCampo.Area.FIM_PAGINA, RelatorioCampo.Tp.DADO).botaTamanho(7).botaFormato(RelatorioCampo.Formatos.NUMERO_LON, "#0.00").botaADireita(),
      new RelatorioCampo(1, 67, "TOTAL:", RelatorioCampo.Area.FIM_PAGINA, RelatorioCampo.Tp.TEXTO),
      new RelatorioCampo(1, 74, "fat_total", RelatorioCampo.Area.FIM_PAGINA, RelatorioCampo.Tp.DADO).botaTamanho(7).botaFormato(RelatorioCampo.Formatos.NUMERO_LON, "#0.00").botaADireita(),
      new RelatorioCampo(3, 1, ".", RelatorioCampo.Area.FIM_PAGINA, RelatorioCampo.Tp.TEXTO)
    });

    botaParametro(new RelatorioParametro("serie", aSerie));
    botaParametro(new RelatorioParametro("codigo", aCodigo));
  }

  @Override
  public void produzir() throws Exception {
    Integer quantimp = conexao.busca("SELECT quantimp FROM faturas WHERE serie = ? AND codigo = ?",
            new Vlrs(pegaParametroValor("serie"), pegaParametroValor("codigo")))
            .pgCol().pgInt(0);

    if (quantimp >= 1) {
      if (!Utl.continua("Já foi feita essa impressão " + quantimp + " vez(es).")) {
        return;
      }
    }

    resultados = conexao.busca("SELECT DISTINCT ON (itens_faturas.serie, itens_faturas.nf, itens_faturas.codigo) "
            + "  faturas.serie, faturas.codigo, faturas.ped_vendedor, faturas.emitido_data, "
            + "  clientes.nome AS cliente_nome, clientes.codigo AS cliente_codigo, clientes.fantasia, clientes.fone1, "
            + "  CONCAT(clientes.logradouro, ' ', clientes.endereco, ' , ', clientes.numero, ' - ', clientes.complemento) AS endereco, "
            + "  cidades.estado, CONCAT(cidades.nome, ' - ', bairros.nome) AS cidade_bairro, "
            + "  condicoes_pagamento.nome AS cond_nome, representante.nome AS repr_nome, "
            + "  CONCAT('( ', clientes.obs, ' ) ', faturas.obs1, faturas.obs2) AS obs, "
            + "  itens_faturas.quantidade, produtos.unidade, CONCAT(produtos.nome, ' ', itens_faturas.obs) AS item, "
            + "  itens_faturas.preco, itens_faturas.desconto, itens_faturas.subtotal, itens_faturas.total, "
            + "  (COALESCE(faturas.desc_itens, 0) + COALESCE(faturas.val_out_desc, 0)) AS fat_desc, faturas.total AS fat_total "
            + " FROM itens_faturas "
            + " JOIN faturas ON "
            + "   faturas.serie = itens_faturas.serie AND "
            + "   faturas.codigo = itens_faturas.nf "
            + " LEFT JOIN pessoas AS clientes ON "
            + "   clientes.codigo = faturas.cliente "
            + " LEFT JOIN cidades ON "
            + "   cidades.codigo = clientes.cidade "
            + " LEFT JOIN bairros ON "
            + "   bairros.cidade = clientes.cidade AND "
            + "   bairros.codigo = clientes.bairro "
            + " LEFT JOIN condicoes_pagamento ON "
            + "   condicoes_pagamento.codigo = faturas.cond_pagamento "
            + " LEFT JOIN pessoas AS representante ON "
            + "   representante.codigo = faturas.representante "
            + " LEFT JOIN produtos ON "
            + "   produtos.codigo = itens_faturas.produto "
            + " WHERE itens_faturas.serie = ? AND "
            + "   itens_faturas.nf = ? "
            + " ORDER BY itens_faturas.serie, itens_faturas.nf, itens_faturas.codigo ",
            new Vlrs(pegaParametroValor("serie"), pegaParametroValor("codigo")));
    imprimir();

    quantimp++;
    conexao.opera("UPDATE faturas SET quantimp = ? WHERE serie = ? AND codigo = ?",
            new Vlrs(quantimp, pegaParametroValor("serie"), pegaParametroValor("codigo")));
  }

  @Override
  public void finalizar() throws Exception {
  }
}
