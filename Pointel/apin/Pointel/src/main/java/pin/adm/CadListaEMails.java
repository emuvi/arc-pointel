package pin.adm;

import pin.libbas.Dados;
import pin.modamk.CadRelacao;
import pin.modamk.Cadastro;
import pin.libamk.Cmp;
import pin.modamk.CadReferenciar;

public class CadListaEMails extends Cadastro {

    public CadListaEMails() throws Exception {
        super("lista_emails", "EMails", new Cmp[]{
            new Cmp(1, 1, "codigo", "Código", Dados.Tp.Crs).mTamanho(18).botaDetalhe("chave", true),
            new Cmp(1, 2, "email", "EMail", Dados.Tp.Crs).mTamanho(450),
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
}
