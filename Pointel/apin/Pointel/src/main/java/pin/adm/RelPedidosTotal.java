package pin.adm;

import pin.libbas.Dados;
import pin.modamk.CadReferenciar;
import pin.modamk.CadRelacao;
import pin.modamk.Relatorio;
import pin.modamk.RelatorioCampo;
import pin.modamk.RelatorioFiltro;
import pin.modamk.RelatorioFiltroCampo;
import pin.modamk.RelatorioPagina;
import pin.modamk.RelatorioParametro;

public class RelPedidosTotal extends Relatorio {

  public RelatorioFiltro filtro;

  public RelPedidosTotal() {
    super("Total de Pedidos", Tipo.Gra, RelatorioPagina.padraoGrafico(), new RelatorioCampo[]{
      new RelatorioCampo(1, 1, "titulo", RelatorioCampo.Area.INICIO_RELATORIO, RelatorioCampo.Tp.PARAMETRO).botaFonteComSerifa().botaTamanho(30).botaNegrito().botaAoCentro().botaMargens(2, 2, 2, 10),
      new RelatorioCampo(2, 1, "subtitulo", RelatorioCampo.Area.INICIO_RELATORIO, RelatorioCampo.Tp.PARAMETRO).botaFonteComSerifa().botaTamanho(18).botaNegrito().botaAoCentro().botaMargens(2, 2, 2, 30),
      new RelatorioCampo(1, 1, "Código", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaNegrito().botaLargura(56),
      new RelatorioCampo(1, 2, "Data", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaNegrito().botaLargura(70),
      new RelatorioCampo(1, 3, "Pedido", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaNegrito().botaLargura(90),
      new RelatorioCampo(1, 4, "Cliente", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaNegrito(),
      new RelatorioCampo(1, 5, "Valor", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaNegrito().botaLargura(65),
      new RelatorioCampo(2, 1, "linha", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.FORMA),
      new RelatorioCampo(1, 1, "codigo", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaLargura(56),
      new RelatorioCampo(1, 1, "recebido_data", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaFormato(RelatorioCampo.Formatos.DATA, "dd/MM/yyyy").botaLargura(70),
      new RelatorioCampo(1, 3, "ped_vendedor", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaLargura(90),
      new RelatorioCampo(1, 5, "cliente", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO),
      new RelatorioCampo(1, 6, "total", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaFormato(RelatorioCampo.Formatos.NUMERO_LON, "R$ #,##0.00").botaLargura(65),
      new RelatorioCampo(2, 1, "linha", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.FORMA),
      new RelatorioCampo(1, 1, "Quantidade:", RelatorioCampo.Area.FIM_RELATORIO, RelatorioCampo.Tp.TEXTO).botaNegrito().botaADireita(),
      new RelatorioCampo(1, 2, "tot_ped", RelatorioCampo.Area.FIM_RELATORIO, RelatorioCampo.Tp.PARAMETRO).botaLargura(100),
      new RelatorioCampo(2, 1, "Total:", RelatorioCampo.Area.FIM_RELATORIO, RelatorioCampo.Tp.TEXTO).botaNegrito().botaADireita(),
      new RelatorioCampo(2, 2, "tot_val", RelatorioCampo.Area.FIM_RELATORIO, RelatorioCampo.Tp.FUNCAO).botaFuncao("somar", "total").botaFormato(RelatorioCampo.Formatos.NUMERO_LON, "R$ #,##0.00").botaLargura(100)
    });

    botaRodapePadraoData();
  }

  @Override
  public void produzir() throws Exception {
    filtro = new RelatorioFiltro(this, "pedidos", new RelatorioFiltroCampo[]{
      new RelatorioFiltroCampo(1, 1, "recebido_data", "Data De", Dados.Tp.Dat).botaObrigatorio().botaOperador(">="),
      new RelatorioFiltroCampo(1, 2, "recebido_data", "Data Até", Dados.Tp.Dat).botaObrigatorio().botaOperador("<="),
      new RelatorioFiltroCampo(2, 1, "representante", "Representante - Cód.", Dados.Tp.Crs).botaObrigatorio().botaTamanho(8),
      new RelatorioFiltroCampo(2, 2, "representantes.nome", "Representante - Nome", Dados.Tp.Crs).botaTamanho(80).botaEstrangeiro(),
      new RelatorioFiltroCampo(3, 1, "ped_vendedor", "Ped. Vendedor", Dados.Tp.Crs).botaTamanho(15),
      new RelatorioFiltroCampo(3, 2, "codigo", "Código", Dados.Tp.Crs).botaTamanho(10),});

    filtro.botaEstrangeiro(new CadRelacao("representantes", "pessoas").botaRelacao("representante", "codigo"));

    filtro.botaReferencia(new CadReferenciar(filtro, "representante", CadPessoas.class).botaChave("representante", "codigo").botaEstrangeiro("representantes.nome", "nome"));

    filtro.botaOrdemTitulos("Data De;Ped. Vendedor");
    filtro.abrir();
  }

  @Override
  public void finalizar() throws Exception {
    resultados = conexao.busca("SELECT DISTINCT ON (" + filtro.pegaOrdem("", "pedidos.codigo").replaceAll("DESC", "") + ") "
            + "   pedidos.codigo, pedidos.recebido_data, pedidos.ped_vendedor, pessoas.nome AS cliente, pedidos.total "
            + " FROM pedidos "
            + "   LEFT JOIN pessoas ON pessoas.codigo = pedidos.cliente "
            + filtro.pegaCondicao("WHERE")
            + filtro.pegaOrdem("ORDER BY", "pedidos.codigo"),
            filtro.pegaParametros());

    limparParametros();

    botaParametro(new RelatorioParametro("subtitulo", "De " + filtro.pegaCampoPeloTitulo("Data De").editor.pgVlrDat().pgCrs()
            + " Até " + filtro.pegaCampoPeloTitulo("Data Até").editor.pgVlrDat().pgCrs()
            + "\n Do Representante " + filtro.pegaCampoPeloTitulo("Representante - Nome").editor.pgVlrCrs().pgCrs()));

    botaParametro(new RelatorioParametro("tot_ped", resultados.size()));

    imprimir();
  }

}
