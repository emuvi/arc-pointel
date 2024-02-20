package pin.bib;

import pin.adm.CadNegocios;
import pin.libbas.Dados;
import pin.modamk.CadRelacao;
import pin.modamk.Cadastro;
import pin.libamk.Cmp;
import pin.modamk.CadReferenciar;

public class CadSintaxes extends Cadastro {

    public CadSintaxes() throws Exception {
        super("sintaxes", "Sintaxes", new Cmp[]{
            new Cmp(1, 1, "idioma", "Idioma", Dados.Tp.Crs).mTamanho(4).botaDetalhe("chave", true),
            new Cmp(1, 2, "idiomas.nome", "Idioma - Nome", Dados.Tp.Crs).botaDetalhe("estrangeiro", true),
            new Cmp(1, 3, "codigo", "Código", Dados.Tp.Crs).mTamanho(6).botaDetalhe("chave", true),
            new Cmp(1, 4, "nome", "Nome", Dados.Tp.Crs).mTamanho(80),
            new Cmp(2, 1, "descricao", "Descrição", Dados.Tp.Crs).mTamanho(360).mLargura(650),
            new Cmp(3, 1, "negocio", "Negócio - Cód.", Dados.Tp.Crs).mTamanho(6),
            new Cmp(3, 2, "negocios.nome", "Negócio - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true)
        });
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        botaRelacao(new CadRelacao("idiomas").botaRelacao("idioma", "codigo"));
        botaReferencia(new CadReferenciar(this, "idioma", CadIdiomas.class).botaChave("idioma", "codigo").botaEstrangeiro("idiomas.nome", "nome"));
        botaRelacao(new CadRelacao("negocios").botaRelacao("negocio", "codigo"));
        botaReferencia(new CadReferenciar(this, "negocio", CadNegocios.class).botaChave("negocio", "codigo").botaEstrangeiro("negocios.nome", "nome"));
    }

}
