package pin.adm;

import pin.libamk.Botao;
import pin.libamk.Cmp;
import pin.libbas.Dados;
import pin.modamk.CadRelacao;
import pin.modamk.CadReferenciar;
import pin.modamk.Cadastro;

public class CadTabelasPrecos extends Cadastro {

    public CadTabelasPrecos() throws Exception {
        super("tabelas_precos", "Tabelas de Preços", new Cmp[]{
            new Cmp(1, 1, "codigo", "Código", Dados.Tp.Crs).mTamanho(6).botaDetalhe("chave", true),
            new Cmp(1, 2, "ativo", "Ativo", Dados.Tp.Log),
            new Cmp(1, 3, "nome", "Nome", Dados.Tp.Crs).mTamanho(60),
            new Cmp(1, 4, "comissao", "Comissão", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3),
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
        botaBotao(new BotPrecos());
    }

    private class BotPrecos extends Botao {

        public BotPrecos() {
            super("Preços", 'P');
        }

        @Override
        public void aoExecutar(Object comOrigem) throws Exception {
            if (confirmar(true)) {
                CadPrecos filho = new CadPrecos();
                filho.pegaCampo("tabela").mVlrFixo(pegaCampo("codigo").pEdt().pgVlr());
                filho.pegaCampo("tabelas_precos.nome").mVlrFixo(pegaCampo("nome").pEdt().pgVlr());
                filho.mostra();
            }
        }
    }
}
