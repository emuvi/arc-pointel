package pin.bib;

import pin.adm.CadNegocios;
import pin.adm.CadPessoas;
import pin.libamk.Botao;
import pin.libamk.Cmp;
import pin.libbas.Dados;
import pin.libutl.UtlCrs;
import pin.modamk.CadRelacao;
import pin.modamk.CadReferenciar;
import pin.modamk.Cadastro;

public class CadAutores extends Cadastro {

    public CadAutores() throws Exception {
        super("autores", "Autores", new Cmp[]{
            new Cmp(1, 1, "codigo", "Código", Dados.Tp.Crs).mTamanho(8).botaDetalhe("chave", true),
            new Cmp(1, 2, "ativo", "Ativo", Dados.Tp.Log),
            new Cmp(1, 3, "sobrenome", "SobreNome", Dados.Tp.Crs).mTamanho(45),
            new Cmp(1, 4, "outrosnomes", "Outros Nomes", Dados.Tp.Crs).mTamanho(60),
            new Cmp(1, 5, "webnome", "WebNome", Dados.Tp.Crs).mTamanho(80),
            new Cmp(2, 1, "pessoa", "Pessoa - Cód.", Dados.Tp.Crs).mTamanho(8),
            new Cmp(2, 2, "pessoas.nome", "Pessoa - Nome", Dados.Tp.Crs).mTamanho(80).botaDetalhe("estrangeiro", true),
            new Cmp(2, 3, "negocio", "Negócio - Cód.", Dados.Tp.Crs).mTamanho(6),
            new Cmp(2, 4, "negocios.nome", "Negócio - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true)
        });
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        botaRelacao(new CadRelacao("pessoas").botaRelacao("pessoa", "codigo"));
        botaReferencia(new CadReferenciar(this, "pessoa", CadPessoas.class).botaChave("pessoa", "codigo").botaEstrangeiro("pessoas.nome", "nome"));
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
            String nomeCompleto = pegaCampo("sobrenome").pEdt().pgVlr().pgCrs() + " "
                    + pegaCampo("outrosnomes").pEdt().pgVlr().pgCrs();
            pegaCampo("webnome").pEdt().mdVlr(UtlCrs.pWebNome(nomeCompleto));
        }
    }

}
