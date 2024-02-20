package pin.adm;

import pin.libbas.Dados;
import pin.modamk.CadRelacao;
import pin.modamk.Cadastro;
import pin.libamk.Cmp;
import pin.modamk.CadReferenciar;

public class CadCidades extends Cadastro {

    public CadCidades() throws Exception {
        super("cidades", "Cidades", new Cmp[]{
            new Cmp(1, 1, "codigo", "Código", Dados.Tp.Crs).mTamanho(6).botaDetalhe("chave", true),
            new Cmp(1, 2, "pais", "País - Cód.", Dados.Tp.Crs).mTamanho(4),
            new Cmp(1, 3, "paises.nome", "País - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true),
            new Cmp(2, 1, "estado", "Estado - Cód.", Dados.Tp.Crs).mTamanho(4),
            new Cmp(2, 2, "estados.nome", "Estado - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true),
            new Cmp(2, 3, "ativo", "Ativo", Dados.Tp.Log),
            new Cmp(2, 4, "nome", "Nome", Dados.Tp.Crs).mTamanho(60),
            new Cmp(3, 1, "negocio", "Negócio - Cód.", Dados.Tp.Crs).mTamanho(6),
            new Cmp(3, 2, "negocios.nome", "Negócio - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true)
        });
    }

    @Override
    public void preparaEstr() throws Exception {
        botaRelacao(new CadRelacao("paises").botaRelacao("pais", "codigo"));
        botaRelacao(new CadRelacao("estados").botaRelacao("pais", "pais").botaRelacao("estado", "codigo"));
        botaReferencia(new CadReferenciar(this, "pais", CadPaises.class).botaChave("pais", "codigo").botaEstrangeiro("paises.nome", "nome"));
        botaReferencia(new CadReferenciar(this, "estado", CadEstados.class).botaChaveFixo("pais", "pais").botaEstranFixo("paises.nome", "nome").botaChave("estado", "codigo").botaEstrangeiro("estados.nome", "nome"));
        botaRelacao(new CadRelacao("negocios").botaRelacao("negocio", "codigo"));
        botaReferencia(new CadReferenciar(this, "negocio", CadNegocios.class).botaChave("negocio", "codigo").botaEstrangeiro("negocios.nome", "nome"));
    }
}
