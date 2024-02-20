package pin.adm;

import pin.libbas.Dados;
import pin.modamk.CadRelacao;
import pin.modamk.Cadastro;
import pin.libamk.Cmp;
import pin.modamk.CadReferenciar;

public class CadSeries extends Cadastro {

    public CadSeries() throws Exception {
        super("series", "Séries", new Cmp[]{
            new Cmp(1, 1, "codigo", "Código", Dados.Tp.Crs).mTamanho(4).botaDetalhe("chave", true),
            new Cmp(1, 2, "ultima_nf", "Último", Dados.Tp.Int),
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

}
