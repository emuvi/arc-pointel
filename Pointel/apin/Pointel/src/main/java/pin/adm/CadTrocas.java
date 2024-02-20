package pin.adm;

import pin.libamk.Botao;
import pin.libamk.Cmp;
import pin.libbas.Conferencia;
import pin.libbas.Dados;
import pin.libvlr.Vlrs;
import pin.modamk.CadReferenciar;
import pin.modamk.CadRelacao;
import pin.modamk.Cadastro;
import pin.modamk.Relatorios;

public class CadTrocas extends Cadastro {

    public CadTrocas() throws Exception {
        super("trocas", "Trocas",
                new Cmp[] { new Cmp(1, 1, "codigo", "Código", Dados.Tp.Crs).mTamanho(8).botaDetalhe("chave", true),
                        new Cmp(1, 2, "data", "Data", Dados.Tp.Dat).botaObrigatorio(),
                        new Cmp(1, 2, "tipo", "Tipo", Dados.Tp.Enu).mTamanho(1).botaOpcoes("(T) Troca", "(R) Reposição",
                                "(B) Bonificação"),
                        new Cmp(2, 1, "cliente", "Cliente - Cód.", Dados.Tp.Crs).mTamanho(8).botaObrigatorio(),
                        new Cmp(2, 2, "clientes.nome", "Cliente - Nome", Dados.Tp.Crs).mTamanho(80)
                                .botaDetalhe("estrangeiro", true),
                        new Cmp(2, 3, "clientes.fantasia", "Cliente - Fantasia", Dados.Tp.Crs).mTamanho(60)
                                .botaDetalhe("estrangeiro", true),
                        new Cmp(3, 1, "obs", "Obs", Dados.Tp.Crs).mTamanho(400).mLargura(540),
                        new Cmp(4, 1, "negocio", "Negócio - Cód.", Dados.Tp.Crs).mTamanho(6),
                        new Cmp(4, 2, "negocios.nome", "Negócio - Nome", Dados.Tp.Crs).mTamanho(60)
                                .botaDetalhe("estrangeiro", true) });
    }

    @Override
    public void preparaEstr() throws Exception {
        botaRelacao(new CadRelacao("clientes", "pessoas").botaRelacao("cliente", "codigo"));
        botaReferencia(new CadReferenciar(this, "cliente", CadPessoas.class).botaChave("cliente", "codigo")
                .botaEstrangeiro("clientes.nome", "nome").botaEstrangeiro("clientes.fantasia", "fantasia"));
        botaRelacao(new CadRelacao("negocios").botaRelacao("negocio", "codigo"));
        botaReferencia(new CadReferenciar(this, "negocio", CadNegocios.class).botaChave("negocio", "codigo")
                .botaEstrangeiro("negocios.nome", "nome"));
    }

    @Override
    public void preparaIntf() throws Exception {
        botaAntesDeExcluir(new CnfExcluir());
        botaBotao(new BotLevar());
        botaBotao(new BotTrazer());
        botaBotao(new BotImprimir());
    }

    private class CnfExcluir extends Conferencia {

        @Override
        public void confere(Object comOrigem) throws Exception {
            String codigo = pegaCampo("codigo").pEdt().pgVlr().pgCrs();
            conexao.opera("DELETE FROM trocas_levar WHERE troca = ?", new Vlrs(codigo));
            conexao.opera("DELETE FROM trocas_trazer WHERE troca = ?", new Vlrs(codigo));
        }
    }

    private class BotLevar extends Botao {

        public BotLevar() {
            super("Levar", 'L');
        }

        @Override
        public void aoExecutar(Object comOrigem) throws Exception {
            if (confirmar(true)) {
                CadTrocasLevar filho = new CadTrocasLevar();
                filho.pegaCampo("troca").mVlrFixo(pegaCampo("codigo").pEdt().pgVlr());
                filho.mostra();
            }
        }
    }

    private class BotTrazer extends Botao {

        public BotTrazer() {
            super("Trazer", 'T');
        }

        @Override
        public void aoExecutar(Object comOrigem) throws Exception {
            if (confirmar(true)) {
                CadTrocasTrazer filho = new CadTrocasTrazer();
                filho.pegaCampo("troca").mVlrFixo(pegaCampo("codigo").pEdt().pgVlr());
                filho.mostra();
            }
        }
    }

    private class BotImprimir extends Botao {

        public BotImprimir() {
            super("Imprimir", 'I');
        }

        @Override
        public void aoExecutar(Object comOrigem) throws Exception {
            if (confirmar(true)) {
                Relatorios relats = new Relatorios(new RelTrocas(pegaCampo("codigo").pEdt().pgVlr()));
                relats.escolher();
            }
        }
    }
}
