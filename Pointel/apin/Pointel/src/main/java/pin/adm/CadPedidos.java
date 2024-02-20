package pin.adm;

import pin.libamk.Botao;
import pin.libamk.Cmp;
import pin.libbas.Conferencia;
import pin.libbas.Dados;
import pin.libbas.Erro;
import pin.libbas.Globais;
import pin.libutl.Utl;
import pin.libutl.UtlNumLon;
import pin.libvlr.Vlrs;
import pin.modamk.CadReferenciar;
import pin.modamk.CadRelacao;
import pin.modamk.Cadastro;
import pin.modamk.Recursos;
import pin.modamk.Relatorio;
import pin.modamk.Relatorios;
import pin.modinf.Banco;

public class CadPedidos extends Cadastro {

    public CadPedidos() throws Exception {
        super("pedidos", "Pedidos", new Cmp[] { new Cmp(1, 1, "Abs", Dados.Tp.Abs),
                new Cmp("Abs", 1, 1, "A1", "1 - Principal", Dados.Tp.Pai),
                new Cmp("Abs", 1, 2, "A2", "2 - Acessório", Dados.Tp.Pai),
                new Cmp("A1", 1, 1, "codigo", "Código", Dados.Tp.Crs).mTamanho(10).botaDetalhe("chave", true),
                new Cmp("A1", 1, 2, "ped_vendedor", "Ped. Vendedor", Dados.Tp.Crs).mTamanho(18),
                new Cmp("A1", 1, 3, "recebido_data", "Recebido Em", Dados.Tp.Dat).botaDetalhe("podeInserir", false)
                        .botaDetalhe("podeEditar", false),
                new Cmp("A1", 1, 4, "recebido_hora", "Recebido As", Dados.Tp.Hor).botaDetalhe("podeInserir", false)
                        .botaDetalhe("podeEditar", false),
                new Cmp("A1", 1, 5, "emitido_data", "Emitido Em", Dados.Tp.Dat).mVlrPadrao("CURRENT_DATE")
                        .botaDetalhe("podeInserir", false).botaDetalhe("podeEditar", false),
                new Cmp("A1", 1, 6, "emitido_hora", "Emitido As", Dados.Tp.Hor).mVlrPadrao("CURRENT_TIME")
                        .botaDetalhe("podeInserir", false).botaDetalhe("podeEditar", false),
                new Cmp("A1", 1, 7, "situacao", "Situação", Dados.Tp.Enu).botaOpcoes(new Object[] { "(A) Aprovado",
                        "(C) Cancelado", "(L) Liberado", "(P) Pendente", "(X) Sem Limite", "(F) Faturado" }),
                new Cmp("A1", 2, 1, "cliente", "Cliente - Cód.", Dados.Tp.Crs).mTamanho(8).botaObrigatorio(),
                new Cmp("A1", 2, 2, "clientes.nome", "Cliente - Nome", Dados.Tp.Crs).mTamanho(80)
                        .botaDetalhe("estrangeiro", true),
                new Cmp("A1", 2, 3, "clientes.fantasia", "Cliente - Fantasia", Dados.Tp.Crs).mTamanho(60)
                        .botaDetalhe("estrangeiro", true),
                new Cmp("A1", 2, 4, "representante", "Representante - Cód.", Dados.Tp.Crs).mTamanho(8),
                new Cmp("A1", 2, 5, "representantes.nome", "Representante - Nome", Dados.Tp.Crs).mTamanho(60)
                        .botaDetalhe("estrangeiro", true),
                new Cmp("A1", 3, 1, "cond_pagamento", "Cond. Pgto - Cód.", Dados.Tp.Crs).mTamanho(4).botaObrigatorio(),
                new Cmp("A1", 3, 2, "condicoes_pagamento.nome", "Cond. Pgto - Nome", Dados.Tp.Crs).mTamanho(60)
                        .botaDetalhe("estrangeiro", true),
                new Cmp("A1", 3, 3, "obs1", "Obs. 1", Dados.Tp.Crs).mTamanho(200).mLargura(300),
                new Cmp("A1", 3, 4, "obs2", "Obs. 2", Dados.Tp.Crs).mTamanho(200).mLargura(200),
                new Cmp("A1", 4, 1, "out_desc", "Out. Descontos", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3)
                        .mLargura(100),
                new Cmp("A1", 4, 2, "out_acresc", "Out. Acréscimos", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3)
                        .mLargura(100),
                new Cmp("A1", 4, 3, "subtotal", "SubTotal", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3)
                        .botaDetalhe("podeInserir", false).botaDetalhe("podeEditar", false).mLargura(100),
                new Cmp("A1", 4, 4, "desc_itens", "Desc. Itens", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3)
                        .botaDetalhe("podeInserir", false).botaDetalhe("podeEditar", false).mLargura(100),
                new Cmp("A1", 4, 5, "acresc_itens", "Acresc. Itens", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3)
                        .botaDetalhe("podeInserir", false).botaDetalhe("podeEditar", false).mLargura(100),
                new Cmp("A1", 4, 6, "total", "Total", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3)
                        .botaDetalhe("podeInserir", false).botaDetalhe("podeEditar", false).mLargura(100),
                new Cmp("A1", 4, 7, "comissao", "Comissão", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3)
                        .botaDetalhe("podeInserir", false).botaDetalhe("podeEditar", false).mLargura(100),
                new Cmp("A2", 1, 1, "cobranca", "Cobrança - Cód.", Dados.Tp.Crs).mTamanho(8),
                new Cmp("A2", 1, 2, "cobrancas.nome", "Cobrança - Nome", Dados.Tp.Crs).mTamanho(80)
                        .botaDetalhe("estrangeiro", true),
                new Cmp("A2", 2, 1, "entrega", "Entrega - Cód.", Dados.Tp.Crs).mTamanho(8),
                new Cmp("A2", 2, 2, "entregas.nome", "Entrega - Nome", Dados.Tp.Crs).mTamanho(60)
                        .botaDetalhe("estrangeiro", true),
                new Cmp("A2", 2, 3, "cidades.nome", "Entrega - Cidade", Dados.Tp.Crs).mTamanho(40)
                        .botaDetalhe("estrangeiro", true),
                new Cmp("A2", 2, 4, "bairros.nome", "Entrega - Bairro", Dados.Tp.Crs).mTamanho(40)
                        .botaDetalhe("estrangeiro", true),
                new Cmp("A2", 3, 1, "negocio", "Negócio - Cód.", Dados.Tp.Crs).mTamanho(6),
                new Cmp("A2", 3, 2, "negocios.nome", "Negócio - Nome", Dados.Tp.Crs).mTamanho(60)
                        .botaDetalhe("estrangeiro", true) });
    }

    @Override
    public void preparaEstr() throws Exception {
        botaRelacao(new CadRelacao("clientes", "pessoas").botaRelacao("cliente", "codigo"));
        botaRelacao(new CadRelacao("cobrancas", "pessoas").botaRelacao("cobranca", "codigo"));
        botaRelacao(new CadRelacao("entregas", "pessoas").botaRelacao("entrega", "codigo"));
        botaRelacao(new CadRelacao("cidades").botaRelacao("entregas.cidade", "codigo"));
        botaRelacao(new CadRelacao("bairros").botaRelacao("entregas.cidade", "cidade").botaRelacao("entregas.bairro",
                "codigo"));
        botaRelacao(new CadRelacao("representantes", "pessoas").botaRelacao("representante", "codigo"));
        botaRelacao(new CadRelacao("condicoes_pagamento").botaRelacao("cond_pagamento", "codigo"));
        botaReferencia(new CadReferenciar(this, "cliente", CadPessoas.class).botaChave("cliente", "codigo")
                .botaEstrangeiro("clientes.nome", "nome").botaEstrangeiro("clientes.fantasia", "fantasia"));
        botaReferencia(new CadReferenciar(this, "cobranca", CadPessoas.class).botaChave("cobranca", "codigo")
                .botaEstrangeiro("cobrancas.nome", "nome"));
        botaReferencia(new CadReferenciar(this, "entrega", CadPessoas.class).botaChave("entrega", "codigo")
                .botaEstrangeiro("entregas.nome", "nome"));
        botaReferencia(new CadReferenciar(this, "representante", CadPessoas.class).botaChave("representante", "codigo")
                .botaEstrangeiro("representantes.nome", "nome"));
        botaReferencia(new CadReferenciar(this, "cond_pagamento", CadCondicoesPagamento.class)
                .botaChave("cond_pagamento", "codigo").botaEstrangeiro("condicoes_pagamento.nome", "nome"));
        botaRelacao(new CadRelacao("negocios").botaRelacao("negocio", "codigo"));
        botaReferencia(new CadReferenciar(this, "negocio", CadNegocios.class).botaChave("negocio", "codigo")
                .botaEstrangeiro("negocios.nome", "nome"));
    }

    @Override
    public void preparaIntf() throws Exception {
        botaParaAtualizar("subtotal");
        botaParaAtualizar("desc_itens");
        botaParaAtualizar("acresc_itens");
        botaParaAtualizar("total");
        botaParaAtualizar("comissao");
        pegaCampo("cliente").botaConferencia(new CnfCliente());
        CnfTotal confTotal = new CnfTotal();
        pegaCampo("out_desc").botaConferencia(confTotal);
        pegaCampo("out_acresc").botaConferencia(confTotal);
        CnfBloqueado cnfBloqueado = new CnfBloqueado();
        botaAntesDeSalvar(cnfBloqueado);
        botaAntesDeSalvar(new CnfLiberado());
        botaAntesDeExcluir(cnfBloqueado);
        botaAntesDeExcluir(new CnfExcluir());
        botaAntesDeFechar(new CnfFechar());
        botaBotao(new BotItens());
        botaBotao(new BotImprimir());
        botaBotao(new BotEfetivar());
        botaBotao(new BotDesbloquear());
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
            Double desc = pegaCampo("out_desc").pEdt().pgVlr().pgNumLon(0.0);
            Double acres = pegaCampo("out_acresc").pEdt().pgVlr().pgNumLon(0.0);
            Double subtotal = pegaCampo("subtotal").pEdt().pgVlr().pgNumLon(0.0);
            Double descitns = pegaCampo("desc_itens").pEdt().pgVlr().pgNumLon(0.0);
            Double acreitns = pegaCampo("acresc_itens").pEdt().pgVlr().pgNumLon(0.0);
            Double total = subtotal + (acreitns + acres) - (descitns + desc);
            pegaCampo("total").pEdt().mdVlr(total);
        }
    }

    private class CnfBloqueado extends Conferencia {

        @Override
        public void confere(Object comOrigem) throws Exception {
            String codigo = pegaCampo("codigo").pEdt().pgVlr().pgCrs();
            String situacao = conexao.busca("SELECT situacao FROM pedidos WHERE codigo = ?", new Vlrs(codigo)).pgCol()
                    .pgCrs();
            if ("F".equals(situacao)) {
                throw new Erro("Este Pedido Está Bloqueado");
            }
        }
    }

    private class CnfLiberado extends Conferencia {

        @Override
        public void confere(Object comOrigem) throws Exception {
            String situacao = pegaCampo("situacao").pEdt().pgVlr().pgCrs();
            if ("L".equals(situacao)) {
                String codigo = pegaCampo("codigo").pEdt().pgVlr().pgCrs();
                if (!conexao.busca("SELECT codigo FROM itens_pedido WHERE pedido = ? AND situacao = 'P' LIMIT 1",
                        new Vlrs(codigo)).vazio()) {
                    throw new Erro("Este Pedido Ainda Tem Itens Pendentes");
                }
                String pessoa = pegaCampo("cliente").pEdt().pgVlr().pgCrs();
                Double limite = conexao.busca("SELECT limite_credito FROM pessoas WHERE codigo = ?", new Vlrs(pessoa))
                        .pgCol().pgNumLon(0.0);
                Double aberto = conexao.busca("SELECT SUM(saldo) FROM lancamentos WHERE situacao = 'A' AND pessoa = ?",
                        new Vlrs(pessoa)).pgCol().pgNumLon(0.0);
                Double total = pegaCampo("total").pEdt().pgVlr().pgNumLon(0.0);
                if (aberto + total > limite) {
                    pegaCampo("situacao").pEdt().mdVlr("X");
                    throw new Erro("Este cliente tem limite de crédito de R$ " + UtlNumLon.formata(limite) + "\n"
                            + "e possui lançamentos em aberto no valor de R$ " + UtlNumLon.formata(aberto) + "\n"
                            + "por isso não é possível liberar o valor de R$ " + UtlNumLon.formata(total) + "\n"
                            + "e este pedido será salvo como 'Sem Limite'.");
                }
            }
        }
    }

    private class CnfExcluir extends Conferencia {

        @Override
        public void confere(Object comOrigem) throws Exception {
            String codigo = pegaCampo("codigo").pEdt().pgVlr().pgCrs();
            conexao.opera("DELETE FROM itens_pedido WHERE pedido = ?", new Vlrs(codigo));
        }
    }

    private class CnfFechar extends Conferencia {

        @Override
        public void confere(Object comOrigem) throws Exception {
            if (pegaCampo("codigo").pVlrFixo() != null) {
                Integer quantimp = conexao.busca("SELECT quantimp FROM pedidos WHERE codigo = ?",
                        new Vlrs(pegaCampo("codigo").pEdt().pgVlr())).pgCol().pgInt(0);
                if (quantimp <= 0) {
                    if (!Utl.continua("Esse Pedido Ainda Não Foi Impresso")) {
                        throw new Erro("Esse Pedido Ainda Não Foi Impresso").tiraAlertavel();
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
                String pedido = pegaCampo("codigo").pEdt().pgVlr().pgCrs();
                CadPedidosItens filho = new CadPedidosItens(pedido);
                filho.mostra();
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
                Relatorios relats = new Relatorios(new Relatorio[] { new RelPedidos(pegaCampo("codigo").pEdt().pgVlr()),
                        new RelPedidosSaldos(pegaCampo("codigo").pEdt().pgVlr()) });
                relats.escolher();
            }
        }
    }

    private class BotEfetivar extends Botao {

        public BotEfetivar() {
            super("Efetivar", 'E');
        }

        @Override
        public void aoExecutar(Object comOrigem) throws Exception {
            if (confirmar(true)) {
                Boolean pode = true;
                if (!"L".equals(pegaCampo("situacao").pEdt().pgVlr().pgCrs())) {
                    Utl.alerta("Pedido não pode ser efetivado pois não está liberado.");
                    pode = false;
                }
                if (pode) {
                    if (conexao.busca("SELECT codigo FROM itens_pedido WHERE pedido = ? AND situacao = 'P'",
                            new Vlrs(pegaCampo("codigo").pEdt().pgVlr())).tem()) {
                        Utl.alerta("Pedido não pode ser efetivado pois há itens pendentes.");
                        pode = false;
                    }
                }
                if (pode) {
                    VenOpePedidosEfetivar filho = new VenOpePedidosEfetivar();
                    filho.abrePedido(pegaCampo("codigo").pEdt().pgVlr().pgCrs());
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
            String codigo = pegaCampo("codigo").pEdt().pgVlr().pgCrs();
            Recursos mainRecs = (Recursos) Globais.pega("mainRecs");
            mainRecs.pega("Pointel.PointelAdmin.Negócios.Rotinas", "Desbloquear Pedido")
                    .abreOuDisponibiliza(new Object[] { codigo });
        }
    }

    public static Double calculaTotais(Banco noBanco, String doCodigo) throws Exception {
        Object pedido = doCodigo;
        Vlrs valores = new Vlrs(pedido, pedido);
        String sql = "UPDATE pedidos SET subtotal = (SELECT SUM(subtotal) FROM itens_pedido WHERE pedido = ?) WHERE codigo = ?";
        noBanco.opera(sql, valores);
        sql = "UPDATE pedidos SET desc_itens = (SELECT SUM(desconto) FROM itens_pedido WHERE pedido = ?) WHERE codigo = ?";
        noBanco.opera(sql, valores);
        sql = "UPDATE pedidos SET acresc_itens = (SELECT SUM(acrescimo) FROM itens_pedido WHERE pedido = ?) WHERE codigo = ?";
        noBanco.opera(sql, valores);
        Double outDesc = noBanco.busca("SELECT out_desc FROM pedidos WHERE codigo = ?", new Vlrs(pedido)).pgCol()
                .pgNumLon(0.0);
        Double outAcre = noBanco.busca("SELECT out_acresc FROM pedidos WHERE codigo = ?", new Vlrs(pedido)).pgCol()
                .pgNumLon(0.0);
        Double totItns = noBanco.busca("SELECT SUM(total) FROM itens_pedido WHERE pedido = ?", new Vlrs(pedido)).pgCol()
                .pgNumLon(0.0);
        Double totFim = totItns + outAcre - outDesc;
        sql = "UPDATE pedidos SET total = ? WHERE codigo = ?";
        noBanco.opera(sql, new Vlrs(UtlNumLon.arredonda(totFim), pedido));
        sql = "UPDATE pedidos SET comissao = (SELECT SUM(comissao) FROM itens_pedido WHERE pedido = ?) WHERE codigo = ?";
        noBanco.opera(sql, valores);
        return totFim;
    }
}
