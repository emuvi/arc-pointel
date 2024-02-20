package pin.adm;

import java.util.Date;
import java.util.Objects;
import pin.libamk.Botao;
import pin.libamk.Cmp;
import pin.libbas.Conferencia;
import pin.libbas.Conjunto;
import pin.libbas.Dados;
import pin.libbas.Erro;
import pin.libbas.Globais;
import pin.libutl.Utl;
import pin.libutl.UtlBin;
import pin.libutl.UtlCrs;
import pin.libutl.UtlNumLon;
import pin.libutl.UtlTex;
import pin.libvlr.VTex;
import pin.libvlr.Vlrs;
import pin.modamk.CadReferenciar;
import pin.modamk.CadRelacao;
import pin.modamk.Cadastro;
import pin.modamk.Recursos;
import pin.modinf.Conexao;

public class CadPrePedidos extends Cadastro {

    public CadPrePedidos() throws Exception {
        super("prepedidos", "Pré-Pedidos", new Cmp[]{
            new Cmp(1, 1, "Abs", Dados.Tp.Abs),
            new Cmp("Abs", 1, 1, "A1", "1 - Principal", Dados.Tp.Pai),
            new Cmp("Abs", 1, 2, "A2", "2 - Acessório", Dados.Tp.Pai),
            new Cmp("A1", 1, 1, "codigo", "Código", Dados.Tp.Crs).mTamanho(10).botaDetalhe("chave", true),
            new Cmp("A1", 1, 2, "emitido_data", "Emitido Em", Dados.Tp.Dat).mVlrPadrao("CURRENT_DATE").botaDetalhe("podeInserir", false).botaDetalhe("podeEditar", false),
            new Cmp("A1", 1, 3, "emitido_hora", "Emitido As", Dados.Tp.Hor).mVlrPadrao("CURRENT_TIME").botaDetalhe("podeInserir", false).botaDetalhe("podeEditar", false),
            new Cmp("A1", 1, 4, "enviado", "Enviado", Dados.Tp.Log).botaDetalhe("podeInserir", false).botaDetalhe("podeEditar", false),
            new Cmp("A1", 1, 5, "enviado_data", "Enviado Em", Dados.Tp.Dat).botaDetalhe("podeInserir", false).botaDetalhe("podeEditar", false),
            new Cmp("A1", 1, 6, "enviado_hora", "Enviado As", Dados.Tp.Hor).botaDetalhe("podeInserir", false).botaDetalhe("podeEditar", false),
            new Cmp("A1", 2, 1, "cliente", "Cliente - Cód.", Dados.Tp.Crs).mTamanho(8).botaObrigatorio(),
            new Cmp("A1", 2, 2, "clientes.nome", "Cliente - Nome", Dados.Tp.Crs).mTamanho(80).botaDetalhe("estrangeiro", true),
            new Cmp("A1", 2, 3, "clientes.fantasia", "Cliente - Fantasia", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true),
            new Cmp("A1", 3, 1, "out_desc", "Out. Descontos", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3).mLargura(100),
            new Cmp("A1", 3, 2, "out_acresc", "Out. Acréscimos", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3).mLargura(100),
            new Cmp("A1", 3, 3, "subtotal", "SubTotal", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3).botaDetalhe("podeInserir", false).botaDetalhe("podeEditar", false).mLargura(100),
            new Cmp("A1", 3, 4, "desc_itens", "Desc. Itens", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3).botaDetalhe("podeInserir", false).botaDetalhe("podeEditar", false).mLargura(100),
            new Cmp("A1", 3, 5, "acresc_itens", "Acresc. Itens", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3).botaDetalhe("podeInserir", false).botaDetalhe("podeEditar", false).mLargura(100),
            new Cmp("A1", 3, 6, "total", "Total", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3).botaDetalhe("podeInserir", false).botaDetalhe("podeEditar", false).mLargura(100),
            new Cmp("A1", 4, 1, "cond_pagamento", "Cond. Pgto - Cód.", Dados.Tp.Crs).mTamanho(4).botaObrigatorio(),
            new Cmp("A1", 4, 2, "condicoes_pagamento.nome", "Cond. Pgto - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true).mLargura(150),
            new Cmp("A1", 4, 3, "obs", "Observações", Dados.Tp.Crs).mTamanho(400).mLargura(400),
            new Cmp("A2", 1, 1, "cobranca", "Cobrança - Cód.", Dados.Tp.Crs).mTamanho(8),
            new Cmp("A2", 1, 2, "cobrancas.nome", "Cobrança - Nome", Dados.Tp.Crs).mTamanho(80).botaDetalhe("estrangeiro", true),
            new Cmp("A2", 2, 1, "entrega", "Entrega - Cód.", Dados.Tp.Crs).mTamanho(8),
            new Cmp("A2", 2, 2, "entregas.nome", "Entrega - Nome", Dados.Tp.Crs).mTamanho(80).botaDetalhe("estrangeiro", true),
            new Cmp("A2", 3, 1, "representante", "Representante - Cód.", Dados.Tp.Crs).mTamanho(8),
            new Cmp("A2", 3, 2, "representantes.nome", "Representante - Nome", Dados.Tp.Crs).mTamanho(80).botaDetalhe("estrangeiro", true),
            new Cmp("A2", 4, 1, "negocio", "Negócio - Cód.", Dados.Tp.Crs).mTamanho(6),
            new Cmp("A2", 4, 2, "negocios.nome", "Negócio - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true)

        });
    }

    @Override
    public void preparaEstr() throws Exception {
        botaRelacao(new CadRelacao("clientes", "pessoas").botaRelacao("cliente", "codigo"));
        botaRelacao(new CadRelacao("cobrancas", "pessoas").botaRelacao("cobranca", "codigo"));
        botaRelacao(new CadRelacao("entregas", "pessoas").botaRelacao("entrega", "codigo"));
        botaRelacao(new CadRelacao("representantes", "pessoas").botaRelacao("representante", "codigo"));
        botaRelacao(new CadRelacao("condicoes_pagamento").botaRelacao("cond_pagamento", "codigo"));
        botaReferencia(new CadReferenciar(this, "cliente", CadMeusClientes.class).botaChave("cliente", "codigo").botaEstrangeiro("clientes.nome", "nome").botaEstrangeiro("clientes.fantasia", "fantasia"));
        botaReferencia(new CadReferenciar(this, "cobranca", CadMeusClientes.class).botaChave("cobranca", "codigo").botaEstrangeiro("cobrancas.nome", "nome"));
        botaReferencia(new CadReferenciar(this, "entrega", CadMeusClientes.class).botaChave("entrega", "codigo").botaEstrangeiro("entregas.nome", "nome"));
        botaReferencia(new CadReferenciar(this, "representante", CadMeusClientes.class).botaChave("representante", "codigo").botaEstrangeiro("representantes.nome", "nome"));
        botaReferencia(new CadReferenciar(this, "cond_pagamento", CadCondicoesPagamento.class).botaChave("cond_pagamento", "codigo").botaEstrangeiro("condicoes_pagamento.nome", "nome"));
        botaRelacao(new CadRelacao("negocios").botaRelacao("negocio", "codigo"));
        botaReferencia(new CadReferenciar(this, "negocio", CadNegocios.class).botaChave("negocio", "codigo").botaEstrangeiro("negocios.nome", "nome"));
    }

    @Override
    public void preparaIntf() throws Exception {
        botaParaAtualizar("emitido_data");
        botaParaAtualizar("emitido_hora");
        botaParaAtualizar("enviado_data");
        botaParaAtualizar("enviado_hora");
        botaParaAtualizar("subtotal");
        botaParaAtualizar("desc_itens");
        botaParaAtualizar("acresc_itens");
        botaParaAtualizar("total");
        botaParaAtualizar("enviado");
        pegaCampo("representante").mVlrFixo(conexao.pegaBaseUsuarioPessoa());
        pegaCampo("representantes.nome").mVlrFixo(conexao.pegaBaseUsuarioNome());
        pegaCampo("cliente").botaConferencia(new CnfCliente());
        CnfTotal cnfTotal = new CnfTotal();
        pegaCampo("out_desc").botaConferencia(cnfTotal);
        pegaCampo("out_acresc").botaConferencia(cnfTotal);
        CnfBloqueado cnfEnviado = new CnfBloqueado();
        botaAntesDeEditar(cnfEnviado);
        botaAntesDeExcluir(cnfEnviado);
        botaAntesDeSalvar(cnfEnviado);
        botaAntesDeExcluir(new CnfExcluir());
        botaBotao(new BotItens());
        botaBotao(new BotEnviar());
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
                    if (pegaCampo("condicoes_pagamento.nome").pEdt().vazio()) {
                        new Thread("Pega Condição de Pagamento do Cliente") {
                            @Override
                            public void run() {
                                try {
                                    pegaCampo("cond_pagamento").pEdt().mdVlr(
                                            conexao.busca("SELECT cond_pagamento FROM pessoas WHERE codigo = ?",
                                                    new Vlrs(pegaCampo("cliente").pEdt().pgVlr())
                                            ).pgCol()
                                    );
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
            if (pegaCampo("enviado").pEdt().pgVlr().pgLog(false)) {
                throw new Erro("Este Pré-Pedido Está Bloqueado");
            }
        }
    }

    private class CnfExcluir extends Conferencia {

        @Override
        public void confere(Object comOrigem) throws Exception {
            String codigo = pegaCampo("codigo").pEdt().pgVlr().pgCrs();
            conexao.opera("DELETE FROM itens_prepedidos WHERE prepedido = ?",
                    new Vlrs(codigo));
        }
    }

    private class CnfClientes extends Conferencia {

        @Override
        public void confere(Object comOrigem) throws Exception {
            String cliente = pegaCampo("cliente").pEdt().pgVlr().pgCrs();
            String reprsCli = conexao.busca("SELECT representante FROM pessoas WHERE codigo = ?",
                    new Vlrs(cliente)).pgCol().pgCrs();
            if (!Objects.equals(reprsCli, conexao.pegaBaseUsuarioPessoa())) {
                throw new Erro("O Cliente Selecionado Não Faz Parte Dos Seus Clientes");
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
                String prePedido = pegaCampo("codigo").pEdt().pgVlr().pgCrs();
                CadPrePedidosItens filho = new CadPrePedidosItens(prePedido);
                filho.mostra();
            }
        }
    }

    private static volatile Boolean enviando = false;

    private class BotEnviar extends Botao {

        public BotEnviar() {
            super("Enviar", 'E');
        }

        @Override
        public void aoExecutar(Object comOrigem) throws Exception {
            if (enviando) {
                throw new Erro("Outro Pré-Pedido Está Sendo Enviado");
            }
            if (confirmar(true)) {
                Boolean enviado = pegaCampo("enviado").pEdt().pgVlr().pgLog(false);
                if (enviado) {
                    throw new Exception("Este Pré-Pedido Já Foi Enviado");
                }
                enviando = true;
                String prepedido = pegaCampo("codigo").pEdt().pgVlr().pgCrs("");
                new ThdEnviar(prepedido).start();
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
            mainRecs.pega("Pointel.PointelAdmin.Negócios.Rotinas", "Desbloquear Pré-Pedido").abreOuDisponibiliza(new Object[]{codigo});
        }
    }

    private class ThdEnviar extends Thread {

        private final String prepedido;

        public ThdEnviar(String prepedido) {
            super("Pré-Pedido Enviar");
            this.prepedido = prepedido;
        }

        @Override
        public void run() {
            Integer imsg = Utl.alertaPop("Envio do Pré-Pedido: " + prepedido + " Realizando...", false);
            try {
                String codPVen = prepedido;
                String dataPed = pegaCampo("emitido_data").pEdt().pgVlrDat(new Date()).pgCrs();
                String codCli = pegaCampo("cliente").pEdt().pgVlr().pgCrs("");
                String nomCli = pegaCampo("clientes.nome").pEdt().pgVlr().pgCrs("");
                String fanCli = pegaCampo("clientes.fantasia").pEdt().pgVlr().pgCrs("");
                String nomPgto = pegaCampo("condicoes_pagamento.nome").pEdt().pgVlr().pgCrs("");
                String codPgto = pegaCampo("cond_pagamento").pEdt().pgVlr().pgCrs("");
                String obsPed = pegaCampo("obs").pEdt().pgVlr().pgCrs("");
                String totQtd = "";
                String subtPed = pegaCampo("subtotal").pEdt().pgVlrNumLon(0.0).pgCrs();
                String descPed = pegaCampo("out_desc").pEdt().pgVlrNumLon(0.0).pgCrs();
                String acrePed = pegaCampo("out_acresc").pEdt().pgVlrNumLon(0.0).pgCrs();
                String totPed = pegaCampo("total").pEdt().pgVlrNumLon(0.0).pgCrs();
                String reprPed = pegaCampo("representante").pEdt().pgVlr().pgCrs("");
                String mobVend = conexao.busca("SELECT codigo FROM mobile_vendedores WHERE representante = ?",
                        new Vlrs(reprPed))
                        .pgCol().pgCrs("");
                if (mobVend.isEmpty()) {
                    throw new Exception("Usuário Não Está Cadastrado No Mobile Vendedores.");
                } else {
                    botaModoNovo();
                    String arquivo = codPVen + UtlTex.quebra();
                    arquivo += dataPed + UtlTex.quebra();
                    arquivo += codCli + UtlTex.quebra();
                    arquivo += nomCli + UtlTex.quebra();
                    arquivo += fanCli + UtlTex.quebra();
                    arquivo += nomPgto + UtlTex.quebra();
                    arquivo += codPgto + UtlTex.quebra();
                    arquivo += obsPed + UtlTex.quebra();
                    arquivo += totQtd + UtlTex.quebra();
                    arquivo += subtPed + UtlTex.quebra();
                    arquivo += descPed + UtlTex.quebra();
                    arquivo += acrePed + UtlTex.quebra();
                    arquivo += totPed + UtlTex.quebra();
                    arquivo += "<Itens>\n";
                    Conjunto res = conexao.busca("SELECT itens_prepedidos.produto, produtos.nome, produtos.unidade, "
                            + " itens_prepedidos.quantidade, itens_prepedidos.preco, itens_prepedidos.desconto_per, itens_prepedidos.desconto, "
                            + " itens_prepedidos.subtotal, itens_prepedidos.total, itens_prepedidos.obs "
                            + " FROM itens_prepedidos "
                            + " JOIN produtos ON produtos.codigo = itens_prepedidos.produto "
                            + " WHERE prepedido = ? ORDER BY itens_prepedidos.codigo ",
                            new Vlrs(codPVen));
                    if (res.vazio()) {
                        throw new Erro("Pré-Pedido Não Tem Itens. Impossível Enviar.");
                    }
                    while (res.proximo()) {
                        String codPro = res.pgVlr(1, "").pgCrs();
                        String nomPro = res.pgVlr(2, "").pgCrs();
                        String unidPro = res.pgVlr(3, "").pgCrs();
                        String quatPro = res.pgVlrNumLon(4, 0.0).pgCrs();
                        String precPro = res.pgVlrNumLon(5, 0.0).pgCrs();
                        String perdPro = res.pgVlrNumLon(6, 0.0).pgCrs();
                        String descPro = res.pgVlrNumLon(7, 0.0).pgCrs();
                        String subtPro = res.pgVlrNumLon(8, 0.0).pgCrs();
                        String totPro = res.pgVlrNumLon(9, 0.0).pgCrs();
                        String obsPro = res.pgVlr(10, "").pgCrs();
                        String item = codPro + "-";
                        item += UtlCrs.troca(nomPro, "-", "_") + "-";
                        item += unidPro + "-";
                        item += quatPro + "-";
                        item += precPro + "-";
                        item += perdPro + "-";
                        item += descPro + "-";
                        item += subtPro + "-";
                        item += totPro + "-";
                        item += UtlCrs.troca(obsPro, "-", "_");
                        arquivo += item + UtlTex.quebra();
                    }
                    arquivo += "</Itens>\n";
                    conexao.opera("INSERT INTO mobile_arquivos (vendedor, nome, tipo, arquivo) VALUES (?, ?, ?, ?)",
                            new Vlrs(mobVend, codPVen + ".ped", "B", new VTex(arquivo)));
                    conexao.opera("UPDATE prepedidos SET enviado = 'S', enviado_data = CURRENT_DATE, enviado_hora = CURRENT_TIME WHERE codigo = ?",
                            new Vlrs(prepedido));
                    mudaValor(new Object[]{prepedido}, "enviado", "S");
                    Utl.alertaPopMudar(imsg, "Envio do Pré-Pedido: " + prepedido + " Realizado.");
                }
            } catch (Exception ex) {
                try {
                    conexao.opera("UPDATE prepedidos SET enviado = 'N' WHERE codigo = ?",
                            new Vlrs(prepedido));
                } catch (Exception ex2) {
                    Utl.registra(ex2);
                }
                Utl.alertaPopMudar(imsg, "Envio do Pré-Pedido: " + prepedido + " NÃO Realizado.");
                Utl.alerta("Erro ao Enviar o Pré-Pedido: " + prepedido + "\nErro: " + ex.getMessage());

            } finally {
                enviando = false;
                new Thread() {
                    @Override
                    public void run() {
                        UtlBin.esperaSegundos(5);
                        Utl.alertaPopFechar(imsg);
                    }
                }.start();
            }
        }
    }

    public static Boolean calculaTotais(Conexao naConexao, String doCodigo) {
        try {
            Object prepedido = doCodigo;
            Vlrs valores = new Vlrs(prepedido, prepedido);
            String sql = "UPDATE prepedidos SET subtotal = (SELECT SUM(subtotal) FROM itens_prepedidos WHERE prepedido = ?) WHERE codigo = ?";
            naConexao.opera(sql, valores);
            sql = "UPDATE prepedidos SET desc_itens = (SELECT SUM(desconto) FROM itens_prepedidos WHERE prepedido = ?) WHERE codigo = ?";
            naConexao.opera(sql, valores);
            sql = "UPDATE prepedidos SET acresc_itens = (SELECT SUM(acrescimo) FROM itens_prepedidos WHERE prepedido = ?) WHERE codigo = ?";
            naConexao.opera(sql, valores);
            Double outDesc = naConexao.busca("SELECT out_desc FROM prepedidos WHERE codigo = ?",
                    new Vlrs(prepedido))
                    .pgCol().pgNumLon(0.0);
            Double outAcre = naConexao.busca("SELECT out_acresc FROM prepedidos WHERE codigo = ?",
                    new Vlrs(prepedido))
                    .pgCol().pgNumLon(0.0);
            Double totItns = naConexao.busca("SELECT SUM(total) FROM itens_prepedidos WHERE prepedido = ?",
                    new Vlrs(prepedido))
                    .pgCol().pgNumLon(0.0);
            Double totFim = totItns + outAcre - outDesc;
            sql = "UPDATE prepedidos SET total = ? WHERE codigo = ?";
            naConexao.opera(sql, new Vlrs(UtlNumLon.arredonda(totFim), prepedido));
            return true;
        } catch (Exception ex) {
            Utl.registra(ex);
            return false;
        }
    }

}
