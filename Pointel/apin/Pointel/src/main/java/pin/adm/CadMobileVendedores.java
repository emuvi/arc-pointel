package pin.adm;

import pin.libbas.Dados;
import pin.modamk.CadRelacao;
import pin.modamk.Cadastro;
import pin.libamk.Cmp;
import pin.modamk.CadReferenciar;

public class CadMobileVendedores extends Cadastro {

    public CadMobileVendedores() throws Exception {
        super("mobile_vendedores", "Vendedores do Mobile", new Cmp[]{
            new Cmp(1, 1, "codigo", "Código", Dados.Tp.Crs).mTamanho(4).botaDetalhe("chave", true),
            new Cmp(1, 2, "ativo", "Ativo", Dados.Tp.Log),
            new Cmp(1, 3, "nome", "Nome", Dados.Tp.Crs).mTamanho(60),
            new Cmp(1, 2, "gerente", "Gerente", Dados.Tp.Log),
            new Cmp(2, 1, "representante", "Representante - Cód.", Dados.Tp.Crs).mTamanho(8),
            new Cmp(2, 2, "representantes.nome", "Representante - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true),
            new Cmp(2, 3, "tabelas", "Tabelas", Dados.Tp.Crs).mTamanho(140),
            new Cmp(3, 1, "usuario", "Usuário", Dados.Tp.Crs).mTamanho(40),
            new Cmp(3, 2, "senha", "Senha", Dados.Tp.Crs).mTamanho(40),
            new Cmp(4, 1, "negocio", "Negócio - Cód.", Dados.Tp.Crs).mTamanho(6),
            new Cmp(4, 2, "negocios.nome", "Negócio - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true)
        });
    }

    @Override
    public void preparaEstr() throws Exception {
        botaRelacao(new CadRelacao("representantes", "pessoas").botaRelacao("representante", "codigo"));
        botaReferencia(new CadReferenciar(this, "representante", CadPessoas.class).botaChave("representante", "codigo").botaEstrangeiro("representantes.nome", "nome"));
        botaRelacao(new CadRelacao("negocios").botaRelacao("negocio", "codigo"));
        botaReferencia(new CadReferenciar(this, "negocio", CadNegocios.class).botaChave("negocio", "codigo").botaEstrangeiro("negocios.nome", "nome"));
    }
}
