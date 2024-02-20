package pin.adm;

import pin.libbas.Dados;
import pin.modamk.CadRelacao;
import pin.modamk.Cadastro;
import pin.libamk.Cmp;
import pin.modamk.CadReferenciar;

public class CadBairros extends Cadastro {

    public CadBairros() throws Exception {
        super("bairros", "Bairros", new Cmp[]{
            new Cmp(1, 1, "cidade", "Cidade - Cód.", Dados.Tp.Crs).mTamanho(6).botaDetalhe("chave", true),
            new Cmp(1, 2, "cidades.pais", "Cidade - País", Dados.Tp.Crs).mTamanho(4).botaDetalhe("estrangeiro", true),
            new Cmp(1, 3, "cidades.estado", "Cidade - Estado", Dados.Tp.Crs).mTamanho(4).botaDetalhe("estrangeiro", true),
            new Cmp(1, 4, "cidades.nome", "Cidade - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true),
            new Cmp(2, 1, "codigo", "Código", Dados.Tp.Crs).mTamanho(4).botaDetalhe("chave", true),
            new Cmp(2, 2, "ativo", "Ativo", Dados.Tp.Log),
            new Cmp(2, 3, "nome", "Nome", Dados.Tp.Crs).mTamanho(60),
            new Cmp(3, 1, "negocio", "Negócio - Cód.", Dados.Tp.Crs).mTamanho(6),
            new Cmp(3, 2, "negocios.nome", "Negócio - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true)
        });
    }

    @Override
    public void preparaEstr() throws Exception {
        botaRelacao(new CadRelacao("cidades").botaRelacao("cidade", "codigo"));
        botaReferencia(new CadReferenciar(this, "cidade", CadCidades.class).botaChave("cidade", "codigo").botaEstrangeiro("cidades.pais", "pais").botaEstrangeiro("cidades.estado", "estado").botaEstrangeiro("cidades.nome", "nome"));
        botaRelacao(new CadRelacao("negocios").botaRelacao("negocio", "codigo"));
        botaReferencia(new CadReferenciar(this, "negocio", CadNegocios.class).botaChave("negocio", "codigo").botaEstrangeiro("negocios.nome", "nome"));
    }
}
