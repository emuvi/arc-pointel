package pin.bib;

import pin.adm.CadNegocios;
import pin.libbas.Dados;
import pin.modamk.CadRelacao;
import pin.modamk.Cadastro;
import pin.libamk.Cmp;
import pin.modamk.CadReferenciar;

public class CadSemanticas extends Cadastro {

    public CadSemanticas() throws Exception {
        super("semanticas", "Semânticas", new Cmp[]{
            new Cmp(1, 1, "idioma", "Idioma", Dados.Tp.Crs).mTamanho(4).botaDetalhe("chave", true),
            new Cmp(1, 2, "idiomas.nome", "Idioma - Nome", Dados.Tp.Crs).botaDetalhe("estrangeiro", true),
            new Cmp(1, 3, "termo", "Termo", Dados.Tp.Crs).mTamanho(80).botaDetalhe("chave", true),
            new Cmp(2, 1, "sintaxe", "Sintaxe", Dados.Tp.Crs).mTamanho(6).botaDetalhe("chave", true),
            new Cmp(2, 2, "sintaxes.nome", "Sintaxe - Nome", Dados.Tp.Crs).botaDetalhe("estrangeiro", true),
            new Cmp(2, 3, "codigo", "Código", Dados.Tp.Crs).mTamanho(4).botaDetalhe("chave", true),
            new Cmp(3, 1, "significado", "Significado", Dados.Tp.Crs).mTamanho(360).mLargura(640),
            new Cmp(4, 1, "negocio", "Negócio - Cód.", Dados.Tp.Crs).mTamanho(6),
            new Cmp(4, 2, "negocios.nome", "Negócio - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true)
        });
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        botaRelacao(new CadRelacao("idiomas").botaRelacao("idioma", "codigo"));
        botaRelacao(new CadRelacao("sintaxes").botaRelacao("idioma", "idioma").botaRelacao("sintaxe", "codigo"));
        botaReferencia(new CadReferenciar(this, "idioma", CadIdiomas.class).botaChave("idioma", "codigo").botaEstrangeiro("idiomas.nome", "nome"));
        botaReferencia(new CadReferenciar(this, "sintaxe", CadSintaxes.class).botaChave("idioma", "idioma").botaChave("sintaxe", "codigo").botaEstrangeiro("sintaxes.nome", "nome"));
        botaRelacao(new CadRelacao("negocios").botaRelacao("negocio", "codigo"));
        botaReferencia(new CadReferenciar(this, "negocio", CadNegocios.class).botaChave("negocio", "codigo").botaEstrangeiro("negocios.nome", "nome"));
    }

}
