package pin.bib;

import pin.adm.CadNegocios;
import pin.libamk.Botao;
import pin.libamk.Cmp;
import pin.libbas.Dados;
import pin.libutl.UtlCrs;
import pin.modamk.CadRelacao;
import pin.modamk.CadReferenciar;
import pin.modamk.Cadastro;

public class CadAssuntos extends Cadastro {

    public CadAssuntos() throws Exception {
        super("assuntos", "Assuntos", new Cmp[]{
            new Cmp(1, 1, "codigo", "Código", Dados.Tp.Crs).mTamanho(18).botaDetalhe("chave", true),
            new Cmp(1, 2, "ativo", "Ativo", Dados.Tp.Log),
            new Cmp(1, 3, "nome", "Nome", Dados.Tp.Crs).mTamanho(80),
            new Cmp(1, 4, "webnome", "WebNome", Dados.Tp.Crs).mTamanho(80),
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
        super.preparaIntf();
        pegaCampo("webnome").botaBotao(new BotWebNome());
    }

    private class BotWebNome extends Botao {

        public BotWebNome() {
            super();
        }

        @Override
        public void aoExecutar(Object comOrigem) throws Exception {
            String nome = pegaCampo("nome").pEdt().pgVlr().pgCrs();
            pegaCampo("webnome").pEdt().mdVlr(UtlCrs.pWebNome(nome));
        }
    }

}
