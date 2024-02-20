package pin.adm;

import pin.libutl.Utl;
import pin.libvlr.Vlrs;
import pin.modamk.Relatorio;
import pin.modamk.RelatorioCampo;
import pin.modamk.RelatorioPagina;
import pin.modamk.RelatorioParametro;

public class RelPedidosSaldos extends Relatorio {

    public RelPedidosSaldos() {
        this(null);
    }

    public RelPedidosSaldos(Object aCodigo) {
        super("Saldo", Relatorio.Tipo.Mat, RelatorioPagina.padraoMatricial(), new RelatorioCampo[]{
            new RelatorioCampo(1, 1, "SALDO        :", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(1, 16, "codigo", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.DADO).botaTamanho(15),
            new RelatorioCampo(1, 33, "PED. VEND.:", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(1, 45, "ped_vendedor", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.DADO).botaTamanho(20),
            new RelatorioCampo(1, 115, "####################", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(2, 1, "RAZAO SOCIAL :", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(2, 16, "cliente_nome", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.DADO).botaTamanho(53),
            new RelatorioCampo(2, 70, "CODIGO :", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(2, 79, "cliente_codigo", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.DADO).botaTamanho(8),
            new RelatorioCampo(2, 88, "REC.:", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(2, 94, "recebido_data", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.DADO).botaTamanho(10).botaFormato(RelatorioCampo.Formatos.DATA, "dd/MM/yyyy"),
            new RelatorioCampo(2, 105, "AS", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(2, 108, "recebido_hora", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.DADO).botaTamanho(5).botaFormato(RelatorioCampo.Formatos.HORA, "HH:mm"),
            new RelatorioCampo(2, 115, "#                  #", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(3, 1, "FANTASIA     :", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(3, 16, "fantasia", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.DADO).botaTamanho(52),
            new RelatorioCampo(3, 69, "FONE :", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(3, 76, "fone1", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.DADO).botaTamanho(11),
            new RelatorioCampo(3, 88, "EMI.:", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(3, 94, "emitido_data", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.DADO).botaTamanho(10).botaFormato(RelatorioCampo.Formatos.DATA, "dd/MM/yyyy"),
            new RelatorioCampo(3, 105, "AS", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(3, 108, "emitido_hora", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.DADO).botaTamanho(5).botaFormato(RelatorioCampo.Formatos.HORA, "HH:mm"),
            new RelatorioCampo(3, 115, "#      SALDO       #", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(4, 1, "ENDERECO     :", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(4, 16, "endereco", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.DADO).botaTamanho(88),
            new RelatorioCampo(4, 105, "UF:", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(4, 109, "estado", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.DADO).botaTamanho(4),
            new RelatorioCampo(4, 115, "#                  #", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(5, 1, "CIDADE       :", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(5, 16, "cidade_bairro", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.DADO).botaTamanho(68),
            new RelatorioCampo(5, 85, "CNPJ/CPF:", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(5, 95, "cnpjcpf", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.CLASSE).botaTamanho(18).botaClasse("pin.libutl.UtlCrs", "pegaCNPJCPFFormatado"),
            new RelatorioCampo(5, 115, "####################", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(6, 1, "COND. PGTO   :", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(6, 16, "cond_nome", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.DADO).botaTamanho(35),
            new RelatorioCampo(6, 52, "CREDITO :", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(6, 62, "credito", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.DADO).botaTamanho(10),
            new RelatorioCampo(6, 73, "OPT:", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(6, 78, "optante", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.DADO).botaTamanho(1),
            new RelatorioCampo(6, 80, "| SIT:", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(6, 87, "situacao", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.DADO).botaTamanho(1),
            new RelatorioCampo(6, 89, "| REPR:", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(6, 97, "repr_nome", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.DADO).botaTamanho(15),
            new RelatorioCampo(7, 1, "OBSERVACAO   :", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(7, 16, "obs", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.DADO).botaTamanho(119),
            new RelatorioCampo(8, 16, "obs", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.DADO).botaTamanho(119).botaInicio(119),
            new RelatorioCampo(9, 1, "COD", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(9, 6, "SALDO", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaTamanho(8).botaADireita(),
            new RelatorioCampo(9, 15, "UND", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(9, 19, "DESCRICAO", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(9, 109, "DESC", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaTamanho(8).botaADireita(),
            new RelatorioCampo(9, 118, "UNIT", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaTamanho(8).botaADireita(),
            new RelatorioCampo(9, 127, "TOTAL", RelatorioCampo.Area.INICIO_PAGINA, RelatorioCampo.Tp.TEXTO).botaTamanho(8).botaADireita(),
            new RelatorioCampo(1, 1, "produto", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaTamanho(4),
            new RelatorioCampo(1, 6, "saldo", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaTamanho(8).botaFormato(RelatorioCampo.Formatos.NUMERO_LON, "#0.00").botaADireita(),
            new RelatorioCampo(1, 15, "unidade", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaTamanho(3),
            new RelatorioCampo(1, 19, "item", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaTamanho(89),
            new RelatorioCampo(1, 109, "desconto_per", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaTamanho(8).botaFormato(RelatorioCampo.Formatos.NUMERO_LON, "#0.00").botaADireita(),
            new RelatorioCampo(1, 118, "preco", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaTamanho(8).botaFormato(RelatorioCampo.Formatos.NUMERO_LON, "#0.00").botaADireita(),
            new RelatorioCampo(1, 127, "subtotal", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaTamanho(8).botaFormato(RelatorioCampo.Formatos.NUMERO_LON, "#0.00").botaADireita(),
            new RelatorioCampo(1, 1, "__________________________________", RelatorioCampo.Area.FIM_RELATORIO, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(2, 1, "           NOME LEGIVEL           ", RelatorioCampo.Area.FIM_RELATORIO, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(2, 101, "DESCONTO:", RelatorioCampo.Area.FIM_RELATORIO, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(2, 111, "ped_desc", RelatorioCampo.Area.FIM_RELATORIO, RelatorioCampo.Tp.FUNCAO).botaTamanho(8).botaFuncao("somar", "desconto").botaFormato(RelatorioCampo.Formatos.NUMERO_LON, "#0.00").botaADireita(),
            new RelatorioCampo(2, 120, "TOTAL:", RelatorioCampo.Area.FIM_RELATORIO, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(2, 127, "ped_total", RelatorioCampo.Area.FIM_RELATORIO, RelatorioCampo.Tp.FUNCAO).botaTamanho(8).botaFuncao("somar", "total").botaFormato(RelatorioCampo.Formatos.NUMERO_LON, "#0.00").botaADireita(),
            new RelatorioCampo(3, 1, ".", RelatorioCampo.Area.FIM_RELATORIO, RelatorioCampo.Tp.TEXTO)
        });

        botaParametro(new RelatorioParametro("codigo", aCodigo));
        botaCondensado();
    }

    @Override
    public void produzir() throws Exception {
        Integer quantimp = conexao.busca("SELECT quantimp FROM pedidos WHERE codigo = ?",
                new Vlrs(pegaParametroValor("codigo")))
                .pgCol().pgInt(0);

        if (quantimp >= 2) {
            if (!Utl.continua("Já foi feita essa impressão " + quantimp + " vez(es).")) {
                return;
            }
        }

        resultados = conexao.busca("SELECT DISTINCT ON (itens_pedido.pedido, itens_pedido.codigo) pedidos.codigo, pedidos.ped_vendedor, "
                + "  clientes.nome AS cliente_nome, clientes.codigo AS cliente_codigo, pedidos.recebido_data, pedidos.recebido_hora, "
                + "  clientes.fantasia, clientes.fone1, pedidos.emitido_data, pedidos.emitido_hora,"
                + "  CONCAT(clientes.logradouro, ' ', clientes.endereco, ' , ', clientes.numero, ' - ', clientes.complemento) AS endereco, cidades.estado, "
                + "  CONCAT(cidades.nome, ' - ', bairros.nome) AS cidade_bairro, clientes.cnpjcpf, "
                + "  condicoes_pagamento.nome AS cond_nome, clientes.credito, clientes.optante, pedidos.situacao, representante.nome AS repr_nome, "
                + "  CONCAT(pedidos.obs1, pedidos.obs2) AS obs, "
                + "  itens_pedido.produto, itens_pedido.saldo, produtos.unidade, CONCAT(produtos.nome, ' ', itens_pedido.obs) AS item, "
                + "  itens_pedido.desconto, itens_pedido.desconto_per, itens_pedido.preco, "
                + "  (itens_pedido.saldo * itens_pedido.preco) AS subtotal, "
                + "  ((itens_pedido.saldo * itens_pedido.preco) - (itens_pedido.saldo * itens_pedido.preco * itens_pedido.desconto_per)) AS total "
                + " FROM itens_pedido "
                + " JOIN pedidos ON "
                + "   pedidos.codigo = itens_pedido.pedido "
                + " LEFT JOIN pessoas AS clientes ON "
                + "   clientes.codigo = pedidos.cliente "
                + " LEFT JOIN cidades ON "
                + "   cidades.codigo = clientes.cidade "
                + " LEFT JOIN bairros ON "
                + "   bairros.cidade = clientes.cidade AND "
                + "   bairros.codigo = clientes.bairro "
                + " LEFT JOIN condicoes_pagamento ON "
                + "   condicoes_pagamento.codigo = pedidos.cond_pagamento "
                + " LEFT JOIN pessoas AS representante ON "
                + "   representante.codigo = pedidos.representante "
                + " LEFT JOIN produtos ON "
                + "   produtos.codigo = itens_pedido.produto "
                + " WHERE itens_pedido.pedido = ? AND "
                + "   itens_pedido.saldo > 0 "
                + " ORDER BY itens_pedido.pedido, itens_pedido.codigo ",
                new Vlrs(pegaParametroValor("codigo")));

        imprimir();

        quantimp++;
        conexao.opera("UPDATE pedidos SET quantimp = ? WHERE codigo = ?",
                new Vlrs(quantimp, pegaParametroValor("codigo")));
    }

    @Override
    public void finalizar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
