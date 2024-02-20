package pin.adm;

import pin.libbas.Dados;
import pin.modamk.CadRelacao;
import pin.modamk.Cadastro;
import pin.libamk.Cmp;
import pin.modamk.CadReferenciar;

public class CadCentros extends Cadastro {

    public CadCentros() throws Exception {
        super("centros", "Centros", new Cmp[]{
            new Cmp(1, 1, "codigo", "Código", Dados.Tp.Crs).mTamanho(6).botaDetalhe("chave", true),
            new Cmp(1, 2, "departamento", "Departamento - Cód.", Dados.Tp.Crs).mTamanho(4),
            new Cmp(1, 3, "departamentos.nome", "Departamento - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true),
            new Cmp(2, 3, "ativo", "Ativo", Dados.Tp.Log),
            new Cmp(2, 4, "nome", "Nome", Dados.Tp.Crs).mTamanho(60),
            new Cmp(3, 1, "negocio", "Negócio - Cód.", Dados.Tp.Crs).mTamanho(6),
            new Cmp(3, 2, "negocios.nome", "Negócio - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true)
        });
    }

    @Override
    public void preparaEstr() throws Exception {
        botaRelacao(new CadRelacao("departamentos").botaRelacao("departamento", "codigo"));
        botaReferencia(new CadReferenciar(this, "departamento", CadDepartamentos.class).botaChave("departamento", "codigo").botaEstrangeiro("departamentos.nome", "nome"));
        botaRelacao(new CadRelacao("negocios").botaRelacao("negocio", "codigo"));
        botaReferencia(new CadReferenciar(this, "negocio", CadNegocios.class).botaChave("negocio", "codigo").botaEstrangeiro("negocios.nome", "nome"));
    }
}
