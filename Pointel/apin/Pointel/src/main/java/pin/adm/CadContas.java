package pin.adm;

import pin.libbas.Dados;
import pin.modamk.CadRelacao;
import pin.modamk.Cadastro;
import pin.libamk.Cmp;
import pin.modamk.CadReferenciar;

public class CadContas extends Cadastro {

    public CadContas() throws Exception {
        super("contas", "Contas", new Cmp[]{
            new Cmp(1, 1, "codigo", "Código", Dados.Tp.Crs).mTamanho(6).botaDetalhe("chave", true),
            new Cmp(1, 2, "ativo", "Ativo", Dados.Tp.Log),
            new Cmp(1, 3, "nome", "Nome", Dados.Tp.Crs).mTamanho(80),
            new Cmp(2, 1, "grupo", "Grupo - Cód.", Dados.Tp.Crs).mTamanho(4),
            new Cmp(2, 2, "grupos_contas.nome", "Grupo - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true),
            new Cmp(2, 3, "subgrupo", "SubGrupo - Cód.", Dados.Tp.Crs).mTamanho(4),
            new Cmp(2, 4, "subgrupos_contas.nome", "SubGrupo - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true),
            new Cmp(3, 1, "conta_liq", "Liquidação - Cód.", Dados.Tp.Crs).mTamanho(6),
            new Cmp(3, 2, "liquidacoes.nome", "Liquidação - Nome", Dados.Tp.Crs).mTamanho(80).botaDetalhe("estrangeiro", true),
            new Cmp(3, 3, "desconto", "Desconto - Cód.", Dados.Tp.Crs).mTamanho(6),
            new Cmp(3, 4, "descontos.nome", "Desconto - Nome", Dados.Tp.Crs).mTamanho(80).botaDetalhe("estrangeiro", true),
            new Cmp(4, 1, "juros", "Juro - Cód.", Dados.Tp.Crs).mTamanho(6),
            new Cmp(4, 2, "juros.nome", "Juro - Nome", Dados.Tp.Crs).mTamanho(80).botaDetalhe("estrangeiro", true),
            new Cmp(4, 3, "multa", "Multa - Cód.", Dados.Tp.Crs).mTamanho(6),
            new Cmp(4, 4, "multas.nome", "Multa - Nome", Dados.Tp.Crs).mTamanho(80).botaDetalhe("estrangeiro", true),
            new Cmp(5, 1, "negocio", "Negócio - Cód.", Dados.Tp.Crs).mTamanho(6),
            new Cmp(5, 2, "negocios.nome", "Negócio - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true)
        });
    }

    @Override
    public void preparaEstr() throws Exception {
        botaRelacao(new CadRelacao("grupos_contas").botaRelacao("grupo", "codigo"));
        botaRelacao(new CadRelacao("subgrupos_contas").botaRelacao("grupo", "grupo").botaRelacao("subgrupo", "codigo"));
        botaRelacao(new CadRelacao("liquidacoes", "contas").botaRelacao("conta_liq", "codigo"));
        botaRelacao(new CadRelacao("descontos", "contas").botaRelacao("desconto", "codigo"));
        botaRelacao(new CadRelacao("juros", "contas").botaRelacao("juros", "codigo"));
        botaRelacao(new CadRelacao("multas", "contas").botaRelacao("multa", "codigo"));
        botaReferencia(new CadReferenciar(this, "grupo", CadGruposContas.class).botaChave("grupo", "codigo").botaEstrangeiro("grupos_contas.nome", "nome"));
        botaReferencia(new CadReferenciar(this, "subgrupo", CadSubGruposContas.class).botaChaveFixo("grupo", "grupo").botaEstranFixo("grupos_contas.nome", "grupos_contas.nome").botaChave("subgrupo", "codigo").botaEstrangeiro("subgrupos_contas.nome", "nome"));
        botaReferencia(new CadReferenciar(this, "conta_liq", CadContas.class).botaChave("conta_liq", "codigo").botaEstrangeiro("liquidacoes.nome", "nome"));
        botaReferencia(new CadReferenciar(this, "desconto", CadContas.class).botaChave("desconto", "codigo").botaEstrangeiro("descontos.nome", "nome"));
        botaReferencia(new CadReferenciar(this, "juros", CadContas.class).botaChave("juros", "codigo").botaEstrangeiro("juros.nome", "nome"));
        botaReferencia(new CadReferenciar(this, "multa", CadContas.class).botaChave("multa", "codigo").botaEstrangeiro("multas.nome", "nome"));
        botaRelacao(new CadRelacao("negocios").botaRelacao("negocio", "codigo"));
        botaReferencia(new CadReferenciar(this, "negocio", CadNegocios.class).botaChave("negocio", "codigo").botaEstrangeiro("negocios.nome", "nome"));

    }
}
