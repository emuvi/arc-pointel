package pin.adm;

import pin.libbas.Dados;
import pin.modamk.CadRelacao;
import pin.modamk.Cadastro;
import pin.libamk.Cmp;
import pin.modamk.CadReferenciar;

public class CadParcelas extends Cadastro {

    public CadParcelas() throws Exception {
        super("parcelas", "Parcelas", new Cmp[]{
            new Cmp(1, 1, "cond_pagamento", "Cond. Pgto - Cód.", Dados.Tp.Crs).mTamanho(4).botaDetalhe("chave", true),
            new Cmp(1, 2, "condicoes_pagamento.nome", "Cond. Pgto - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true),
            new Cmp(2, 1, "dias", "Dias", Dados.Tp.Int).botaOpcoes(new Object[]{0, Integer.MAX_VALUE, 1}).botaDetalhe("chave", true),
            new Cmp(2, 2, "tipo_pagamento", "Tipo Pgto - Cód.", Dados.Tp.Crs).mTamanho(4).botaDetalhe("chave", true),
            new Cmp(2, 3, "tipos_pagamento.nome", "Tipo Pgto - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true),
            new Cmp(2, 4, "parcela", "Parcela", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3),
            new Cmp(2, 5, "juros", "Juros", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3),
            new Cmp(3, 1, "negocio", "Negócio - Cód.", Dados.Tp.Crs).mTamanho(6),
            new Cmp(3, 2, "negocios.nome", "Negócio - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true)
        });
    }

    @Override
    public void preparaEstr() throws Exception {
        botaRelacao(new CadRelacao("condicoes_pagamento").botaRelacao("cond_pagamento", "codigo"));
        botaRelacao(new CadRelacao("tipos_pagamento").botaRelacao("tipo_pagamento", "codigo"));
        botaReferencia(new CadReferenciar(this, "cond_pagamento", CadCondicoesPagamento.class).botaChave("cond_pagamento", "codigo").botaEstrangeiro("condicoes_pagamento.nome", "nome"));
        botaReferencia(new CadReferenciar(this, "tipo_pagamento", CadTiposPagamento.class).botaChave("tipo_pagamento", "codigo").botaEstrangeiro("tipos_pagamento.nome", "nome"));
        botaRelacao(new CadRelacao("negocios").botaRelacao("negocio", "codigo"));
        botaReferencia(new CadReferenciar(this, "negocio", CadNegocios.class).botaChave("negocio", "codigo").botaEstrangeiro("negocios.nome", "nome"));
    }
}
