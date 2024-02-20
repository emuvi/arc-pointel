package pin.bib;

import pin.adm.CadNegocios;
import pin.libamg.Edt;
import pin.libamk.Botao;
import pin.libamk.Cmp;
import pin.libbas.Dados;
import pin.libbas.Partes;
import pin.libtex.Marcados;
import pin.libutl.UtlLis;
import pin.modamk.CadRelacao;
import pin.modamk.CadReferenciar;
import pin.modamk.Cadastro;

public class CadTraducoes extends Cadastro {

    public CadTraducoes() throws Exception {
        super("traducoes", "Traduções", new Cmp[]{
            new Cmp(1, 1, "origem", "Origem", Dados.Tp.Crs).mTamanho(360).botaDetalhe("chave", true).mLargura(640),
            new Cmp(2, 1, "do_idioma", "Do Idioma", Dados.Tp.Crs).mTamanho(4).botaDetalhe("chave", true),
            new Cmp(2, 2, "do_idiomas.nome", "Do Idioma - Nome", Dados.Tp.Crs).botaDetalhe("estrangeiro", true),
            new Cmp(2, 3, "para_idioma", "Para Idioma", Dados.Tp.Crs).mTamanho(4).botaDetalhe("chave", true),
            new Cmp(2, 4, "para_idiomas.nome", "Para Idioma - Nome", Dados.Tp.Crs).botaDetalhe("estrangeiro", true),
            new Cmp(2, 5, "codigo", "Código", Dados.Tp.Int).botaDetalhe("chave", true),
            new Cmp(3, 1, "traducao", "Tradução", Dados.Tp.Crs).mTamanho(360).mLargura(640),
            new Cmp(4, 1, "negocio", "Negócio - Cód.", Dados.Tp.Crs).mTamanho(6),
            new Cmp(4, 2, "negocios.nome", "Negócio - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true)
        });
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        botaRelacao(new CadRelacao("do_idiomas", "idiomas").botaRelacao("do_idioma", "codigo"));
        botaRelacao(new CadRelacao("para_idiomas", "idiomas").botaRelacao("para_idioma", "codigo"));
        botaReferencia(new CadReferenciar(this, "idioma", CadIdiomas.class).botaChave("do_idioma", "codigo").botaEstrangeiro("do_idiomas.nome", "nome"));
        botaReferencia(new CadReferenciar(this, "idioma", CadIdiomas.class).botaChave("para_idioma", "codigo").botaEstrangeiro("para_idiomas.nome", "nome"));
        botaRelacao(new CadRelacao("negocios").botaRelacao("negocio", "codigo"));
        botaReferencia(new CadReferenciar(this, "negocio", CadNegocios.class).botaChave("negocio", "codigo").botaEstrangeiro("negocios.nome", "nome"));
    }

    @Override
    public void especializaIntf() throws Exception {
        super.especializaIntf();
        pegaCampo("origem").pEdt().botaBotao(new BotOrigem());
    }

    private class BotOrigem extends Botao {

        public BotOrigem() {
            super();
        }

        @Override
        public void aoExecutar(Object comOrigem) throws Exception {
            Edt editor = pegaCampo("origem").pEdt();
            String orn = editor.pgVlr().pgCrs("");
            String[] orgs = Partes.pega(orn);
            String tab = UtlLis.pegaGen(orgs, 0, "");
            String[] chvs = Partes.pega(UtlLis.pegaGen(orgs, 1, ""));
            String cmp = UtlLis.pegaGen(orgs, 2, "");
            new CadTraducoesOrigemIntf(editor, tab, chvs, cmp).mostra();
        }
    }

    public static String montaOrigem(String daTabela, String eCampo, String... eChaves) {
        String chvs = Partes.junta(eChaves);
        String jnt = Partes.junta(daTabela, eCampo, chvs);
        return Marcados.prepara(jnt, "tab");
    }

}
