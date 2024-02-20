package pin.adm;

import pin.libbas.Dados;
import pin.modamk.CadRelacao;
import pin.modamk.Cadastro;
import pin.libamk.Cmp;
import pin.modamk.CadReferenciar;

public class CadCreditos extends Cadastro {

    public CadCreditos() throws Exception {
        super("creditos", "Créditos", new Cmp[]{
            new Cmp(1, 1, "codigo", "Código", Dados.Tp.Crs).mTamanho(6).botaDetalhe("chave", true),
            new Cmp(1, 2, "ativo", "Ativo", Dados.Tp.Log),
            new Cmp(1, 3, "nome", "Nome", Dados.Tp.Crs).mTamanho(60),
            new Cmp(1, 4, "situacao", "Situação", Dados.Tp.Enu).botaOpcoes(new Object[]{"(A) Aprovado", "(C) Cancelado", "(L) Liberado", "(P) Pendente"}),
            new Cmp(2, 1, "mensagem", "Mensagem", Dados.Tp.Crs).mTamanho(200),
            new Cmp(2, 2, "cond_pagamento", "Cond. Pgto - Cód.", Dados.Tp.Crs).mTamanho(4),
            new Cmp(2, 3, "condicoes_pagamento.nome", "Cond. Pgto - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true),
            new Cmp(3, 1, "negocio", "Negócio - Cód.", Dados.Tp.Crs).mTamanho(6),
            new Cmp(3, 2, "negocios.nome", "Negócio - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true)
        });
    }

    @Override
    public void preparaEstr() throws Exception {
        botaRelacao(new CadRelacao("condicoes_pagamento").botaRelacao("cond_pagamento", "codigo"));
        botaReferencia(new CadReferenciar(this, "cond_pagamento", CadCondicoesPagamento.class).botaChave("cond_pagamento", "codigo").botaEstrangeiro("condicoes_pagamento.nome", "nome"));
        botaRelacao(new CadRelacao("negocios").botaRelacao("negocio", "codigo"));
        botaReferencia(new CadReferenciar(this, "negocio", CadNegocios.class).botaChave("negocio", "codigo").botaEstrangeiro("negocios.nome", "nome"));
    }
}
