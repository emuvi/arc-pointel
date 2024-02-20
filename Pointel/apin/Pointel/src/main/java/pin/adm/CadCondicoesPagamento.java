package pin.adm;

import pin.libamk.Botao;
import pin.libamk.Cmp;
import pin.libbas.Dados;
import pin.modamk.CadRelacao;
import pin.modamk.CadReferenciar;
import pin.modamk.Cadastro;

public class CadCondicoesPagamento extends Cadastro {

    public CadCondicoesPagamento() throws Exception {
        super("condicoes_pagamento", "Condições de Pagamento", new Cmp[]{
            new Cmp(1, 1, "codigo", "Código", Dados.Tp.Crs).mTamanho(4).botaDetalhe("chave", true),
            new Cmp(1, 2, "ativo", "Ativo", Dados.Tp.Log),
            new Cmp(1, 3, "nome", "Nome", Dados.Tp.Crs).mTamanho(60),
            new Cmp(1, 4, "desconto", "Desconto", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3),
            new Cmp(2, 1, "negocio", "Negócio - Cód.", Dados.Tp.Crs).mTamanho(6),
            new Cmp(2, 2, "negocios.nome", "Negócio - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true)
        });
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        botaRelacao(new CadRelacao("negocios").botaRelacao("negocio", "codigo"));
        botaReferencia(new CadReferenciar(this, "negocio", CadNegocios.class).botaChave("negocio", "codigo").botaEstrangeiro("negocios.nome", "nome"));
    }

    @Override
    public void preparaIntf() throws Exception {
        botaBotao(new BotPrazos());
        botaBotao(new BotParcelas());
    }

    private class BotPrazos extends Botao {

        public BotPrazos() {
            super("Prazos", 'P');
        }

        @Override
        public void aoExecutar(Object comOrigem) throws Exception {
            if (confirmar(true)) {
                CadPrazos filho = new CadPrazos();
                filho.pegaCampo("cond_pagamento").mVlrFixo(pegaCampo("codigo").pEdt().pgVlr());
                filho.pegaCampo("condicoes_pagamento.nome").mVlrFixo(pegaCampo("nome").pEdt().pgVlr());
                filho.mostra();
            }
        }
    }

    private class BotParcelas extends Botao {

        public BotParcelas() {
            super("Parcelas", 'P');
        }

        @Override
        public void aoExecutar(Object comOrigem) throws Exception {
            if (confirmar(true)) {
                CadParcelas filho = new CadParcelas();
                filho.pegaCampo("cond_pagamento").mVlrFixo(pegaCampo("codigo").pEdt().pgVlr());
                filho.pegaCampo("condicoes_pagamento.nome").mVlrFixo(pegaCampo("nome").pEdt().pgVlr());
                filho.mostra();
            }
        }
    }

}
