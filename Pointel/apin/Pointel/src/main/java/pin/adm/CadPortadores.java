package pin.adm;

import pin.libbas.Dados;
import pin.modamk.CadRelacao;
import pin.modamk.Cadastro;
import pin.libamk.Cmp;
import pin.modamk.CadReferenciar;

public class CadPortadores extends Cadastro {

    public CadPortadores() throws Exception {
        super("portadores", "Portadores", new Cmp[]{
            new Cmp(1, 1, "codigo", "Código", Dados.Tp.Crs).mTamanho(4).botaDetalhe("chave", true),
            new Cmp(1, 2, "ativo", "Ativo", Dados.Tp.Log),
            new Cmp(1, 3, "nome", "Nome", Dados.Tp.Crs).mTamanho(60),
            new Cmp(2, 1, "portador_liq", "Liquidação - Cód.", Dados.Tp.Crs).mTamanho(4),
            new Cmp(2, 2, "liquidacoes.nome", "Liquidação - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true),
            new Cmp(2, 3, "desconto", "Desconto - Cód.", Dados.Tp.Crs).mTamanho(4),
            new Cmp(2, 4, "descontos.nome", "Desconto - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true),
            new Cmp(3, 1, "juros", "Juro - Cód.", Dados.Tp.Crs).mTamanho(4),
            new Cmp(3, 2, "juros.nome", "Juro - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true),
            new Cmp(3, 3, "multa", "Multa - Cód.", Dados.Tp.Crs).mTamanho(4),
            new Cmp(3, 4, "multas.nome", "Multa - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true),
            new Cmp(4, 1, "negocio", "Negócio - Cód.", Dados.Tp.Crs).mTamanho(6),
            new Cmp(4, 2, "negocios.nome", "Negócio - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true)
        });
    }

    @Override
    public void preparaEstr() throws Exception {
        botaRelacao(new CadRelacao("liquidacoes", "portadores").botaRelacao("portador_liq", "codigo"));
        botaRelacao(new CadRelacao("descontos", "portadores").botaRelacao("desconto", "codigo"));
        botaRelacao(new CadRelacao("juros", "portadores").botaRelacao("juros", "codigo"));
        botaRelacao(new CadRelacao("multas", "portadores").botaRelacao("multa", "codigo"));
        botaReferencia(new CadReferenciar(this, "portador_liq", CadPortadores.class).botaChave("portador_liq", "codigo").botaEstrangeiro("liquidacoes.nome", "nome"));
        botaReferencia(new CadReferenciar(this, "desconto", CadPortadores.class).botaChave("desconto", "codigo").botaEstrangeiro("descontos.nome", "nome"));
        botaReferencia(new CadReferenciar(this, "juros", CadPortadores.class).botaChave("juros", "codigo").botaEstrangeiro("juros.nome", "nome"));
        botaReferencia(new CadReferenciar(this, "multa", CadPortadores.class).botaChave("multa", "codigo").botaEstrangeiro("multas.nome", "nome"));
        botaRelacao(new CadRelacao("negocios").botaRelacao("negocio", "codigo"));
        botaReferencia(new CadReferenciar(this, "negocio", CadNegocios.class).botaChave("negocio", "codigo").botaEstrangeiro("negocios.nome", "nome"));
    }
}
