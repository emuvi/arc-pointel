package pin.adm;

import pin.libamk.Botao;
import pin.libamk.Cmp;
import pin.libbas.Conferencia;
import pin.libbas.Dados;
import pin.libutl.Utl;
import pin.libvlr.Vlrs;
import pin.modamk.CadRelacao;
import pin.modamk.CadReferenciar;
import pin.modamk.Cadastro;
import pin.modamk.RelatorioParametro;
import pin.modamk.Relatorios;

public class CadLancamentos extends Cadastro {

    public CadLancamentos() throws Exception {
        super("lancamentos", "Lançamentos", new Cmp[]{
            new Cmp(1, 1, "codigo", "Código", Dados.Tp.Crs).mTamanho(10).botaDetalhe("chave", true),
            new Cmp(1, 2, "data", "Data", Dados.Tp.Dat),
            new Cmp(1, 3, "emissao", "Emissão", Dados.Tp.Dat),
            new Cmp(1, 4, "vencimento", "Vencimento", Dados.Tp.Dat),
            new Cmp(1, 5, "serie", "Série", Dados.Tp.Crs).mTamanho(4),
            new Cmp(1, 6, "fatura", "Fatura", Dados.Tp.Crs).mTamanho(10),
            new Cmp(1, 7, "ped_vendedor", "Ped. Vendedor", Dados.Tp.Crs).mTamanho(18),
            new Cmp(2, 1, "historico", "Histórico - Cód.", Dados.Tp.Crs).mTamanho(4),
            new Cmp(2, 2, "historicos.nome", "Histórico - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true),
            new Cmp(2, 3, "tipo", "Tipo", Dados.Tp.Enu).botaOpcoes(new Object[]{"(E) Entrada", "(S) Saída", "(R) Receber", "(P) Pagar", "(C) Recebido", "(G) Pago", "(X) Nulo"}).botaDetalhe("podeEditar", false),
            new Cmp(2, 4, "situacao", "Situação", Dados.Tp.Enu).botaOpcoes(new Object[]{"(A) Aberto", "(Q) Liquidado", "(D) Desconto", "(J) Juros", "(M) Multa", "(F) Transferência", "(X) Nulo"}).botaDetalhe("podeEditar", false),
            new Cmp(2, 5, "complemento", "Complemento", Dados.Tp.Crs).mTamanho(100).mLargura(180),
            new Cmp(3, 1, "pessoa", "Pessoa - Cód.", Dados.Tp.Crs).mTamanho(8),
            new Cmp(3, 2, "pessoas.nome", "Pessoa - Nome", Dados.Tp.Crs).mTamanho(80).botaDetalhe("estrangeiro", true),
            new Cmp(3, 3, "cobranca", "Cobrança - Cód.", Dados.Tp.Crs).mTamanho(8),
            new Cmp(3, 4, "cobrancas.nome", "Cobrança - Nome", Dados.Tp.Crs).mTamanho(80).botaDetalhe("estrangeiro", true),
            new Cmp(4, 1, "conta", "Conta - Cód.", Dados.Tp.Crs).mTamanho(6),
            new Cmp(4, 2, "contas.nome", "Conta - Nome", Dados.Tp.Crs).mTamanho(80).botaDetalhe("estrangeiro", true).mLargura(180),
            new Cmp(4, 3, "tipo_pagamento", "Tipo Pgto - Cód.", Dados.Tp.Crs).mTamanho(4),
            new Cmp(4, 4, "tipos_pagamento.nome", "Tipo Pgto - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true).mLargura(180),
            new Cmp(4, 5, "portador", "Portador - Cód.", Dados.Tp.Crs).mTamanho(4),
            new Cmp(4, 6, "portadores.nome", "Portador - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true).mLargura(160),
            new Cmp(5, 1, "valor", "Valor", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3),
            new Cmp(5, 2, "saldo", "Saldo", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3),
            new Cmp(5, 3, "representante", "Representante - Cód.", Dados.Tp.Crs).mTamanho(8),
            new Cmp(5, 4, "representantes.nome", "Representante - Nome", Dados.Tp.Crs).mTamanho(80).botaDetalhe("estrangeiro", true),
            new Cmp(5, 5, "comissao", "Comissão", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3),
            new Cmp(5, 6, "vinculo", "Vínculo", Dados.Tp.Crs).mTamanho(10).botaDetalhe("podeInserir", false).botaDetalhe("podeEditar", false),
            new Cmp(6, 1, "negocio", "Negócio - Cód.", Dados.Tp.Crs).mTamanho(6),
            new Cmp(6, 2, "negocios.nome", "Negócio - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true)
        });
    }

    @Override
    public void preparaEstr() throws Exception {
        botaRelacao(new CadRelacao("historicos").botaRelacao("historico", "codigo"));
        botaRelacao(new CadRelacao("contas").botaRelacao("conta", "codigo"));
        botaRelacao(new CadRelacao("pessoas").botaRelacao("pessoa", "codigo"));
        botaRelacao(new CadRelacao("cobrancas", "pessoas").botaRelacao("cobranca", "codigo"));
        botaRelacao(new CadRelacao("tipos_pagamento").botaRelacao("tipo_pagamento", "codigo"));
        botaRelacao(new CadRelacao("portadores").botaRelacao("portador", "codigo"));
        botaRelacao(new CadRelacao("representantes", "pessoas").botaRelacao("representante", "codigo"));
        botaReferencia(new CadReferenciar(this, "historico", CadHistoricos.class).botaChave("historico", "codigo").botaEstrangeiro("historicos.nome", "nome"));
        botaReferencia(new CadReferenciar(this, "conta", CadContas.class).botaChave("conta", "codigo").botaEstrangeiro("contas.nome", "nome"));
        botaReferencia(new CadReferenciar(this, "pessoa", CadPessoas.class).botaChave("pessoa", "codigo").botaEstrangeiro("pessoas.nome", "nome"));
        botaReferencia(new CadReferenciar(this, "cobranca", CadPessoas.class).botaChave("cobranca", "codigo").botaEstrangeiro("cobrancas.nome", "nome"));
        botaReferencia(new CadReferenciar(this, "tipo_pagamento", CadTiposPagamento.class).botaChave("tipo_pagamento", "codigo").botaEstrangeiro("tipos_pagamento.nome", "nome"));
        botaReferencia(new CadReferenciar(this, "portador", CadPortadores.class).botaChave("portador", "codigo").botaEstrangeiro("portadores.nome", "nome"));
        botaReferencia(new CadReferenciar(this, "representante", CadPessoas.class).botaChave("cobranca", "codigo").botaEstrangeiro("cobrancas.nome", "nome"));
        botaRelacao(new CadRelacao("negocios").botaRelacao("negocio", "codigo"));
        botaReferencia(new CadReferenciar(this, "negocio", CadNegocios.class).botaChave("negocio", "codigo").botaEstrangeiro("negocios.nome", "nome"));
    }

    @Override
    public void preparaIntf() throws Exception {
        botaBotao(new BotImprimir());
        botaBotao(new BotLiquidar());
        pegaCampo("historico").botaConferencia(new CnfHistorico());
        pegaCampo("valor").botaConferencia(new CnfValor());
        pegaCampo("pessoa").botaConferencia(new CnfPessoa());
    }

    private class CnfHistorico extends Conferencia {

        @Override
        public void confere(Object comOrigem) throws Exception {
            if (ehModoNovo()) {
                String tipo = conexao.busca("SELECT tipo_financeiro FROM historicos WHERE codigo = ?",
                        new Vlrs(pegaCampo("historico").pEdt().pgVlr()))
                        .pgCol().pgCrs("");
                String situacao = "";
                switch (tipo) {
                    case "E":
                    case "S":
                        situacao = "Q";
                        break;
                    case "R":
                    case "P":
                        situacao = "A";
                        break;
                    default:
                        situacao = tipo;
                        break;
                }
                pegaCampo("tipo").pEdt().mdVlr(tipo);
                pegaCampo("situacao").pEdt().mdVlr(situacao);
            }
        }
    }

    private class CnfValor extends Conferencia {

        @Override
        public void confere(Object comOrigem) throws Exception {
            if (ehModoNovo()) {
                if (pegaCampo("tipo").ehVlrIgual("R") || pegaCampo("tipo").ehVlrIgual("E")) {
                    if (pegaCampo("valor").ehVlrMenor(0.0, 0.0)) {
                        pegaCampo("valor").pEdt().mdVlr(-1 * pegaCampo("valor").pEdt().pgVlr().pgNumLon(0.0));
                    }
                }
                if (pegaCampo("tipo").ehVlrIgual("P") || pegaCampo("tipo").ehVlrIgual("S")) {
                    if (pegaCampo("valor").ehVlrMaior(0.0, 0.0)) {
                        pegaCampo("valor").pEdt().mdVlr(-1 * pegaCampo("valor").pEdt().pgVlr().pgNumLon(0.0));
                    }
                }
                if (pegaCampo("situacao").ehVlrIgual("Q")) {
                    pegaCampo("saldo").pEdt().mdVlr(0.0);
                } else if (pegaCampo("situacao").ehVlrIgual("A")) {
                    pegaCampo("saldo").pEdt().mdVlr(pegaCampo("valor").pEdt().pgVlr());
                }
            }
        }
    }

    private class CnfPessoa extends Conferencia {

        @Override
        public void confere(Object comOrigem) throws Exception {
            if (ehModoNovoOuEditar()) {
                if (!pegaCampo("pessoa").pEdt().vazio()) {
                    if (pegaCampo("cobrancas.nome").pEdt().vazio()) {
                        pegaCampo("cobranca").pEdt().mdVlr(pegaCampo("pessoa").pEdt().pgVlr());
                    }
                    if (pegaCampo("tipo").ehVlrIgual("E") || pegaCampo("tipo").ehVlrIgual("R")) {
                        pegaCampo("representante").pEdt().mdVlr(
                                conexao.busca("SELECT representante FROM pessoas WHERE codigo = ?",
                                        new Vlrs(pegaCampo("pessoa").pEdt().pgVlr()))
                                        .pgCol());
                    }
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
                Relatorios relats = new Relatorios(
                        new RelRecibos(
                                new RelatorioParametro("codigo", pegaCampo("codigo").pEdt().pgVlr())
                        )
                );
                relats.escolher();
            }
        }
    }

    private class BotLiquidar extends Botao {

        public BotLiquidar() {
            super("Liquidar", 'L');
        }

        @Override
        public void aoExecutar(Object comOrigem) throws Exception {
            if (confirmar(true)) {
                if (pegaCampo("situacao").pEdt().pgVlr().pgCrs().equals("A")) {
                    FinOpeLiquidar filho = new FinOpeLiquidar();
                    filho.setVisible(true);
                    filho.jtfSerieLiq.setText(pegaCampo("serie").pEdt().pgVlr().pgCrs());
                    filho.jtfFaturaLiq.setText(pegaCampo("fatura").pEdt().pgVlr().pgCrs());
                    filho.jcbLancamentoLiq.setSelectedItem(pegaCampo("codigo").pEdt().pgVlr());
                    filho.edtLiquidar.requestFocus();
                } else {
                    Utl.alerta("Lançamento não está aberto para ser liquidado.");
                }
            }
        }
    }
}
