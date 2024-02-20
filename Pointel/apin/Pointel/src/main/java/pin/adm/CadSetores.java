package pin.adm;

import pin.libbas.Dados;
import pin.modamk.CadRelacao;
import pin.modamk.Cadastro;
import pin.libamk.Cmp;
import pin.modamk.CadReferenciar;

public class CadSetores extends Cadastro {

    public CadSetores() throws Exception {
        super("setores", "Setores", new Cmp[]{
            new Cmp(1, 1, "codigo", "Código", Dados.Tp.Crs).mTamanho(6).botaDetalhe("chave", true),
            new Cmp(1, 2, "centro", "Centro - Cód.", Dados.Tp.Crs).mTamanho(6),
            new Cmp(1, 3, "centros.nome", "Centro - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true),
            new Cmp(2, 3, "ativo", "Ativo", Dados.Tp.Log),
            new Cmp(2, 4, "nome", "Nome", Dados.Tp.Crs).mTamanho(60),
            new Cmp(3, 1, "negocio", "Negócio - Cód.", Dados.Tp.Crs).mTamanho(6),
            new Cmp(3, 2, "negocios.nome", "Negócio - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true)
        });
    }

    @Override
    public void preparaEstr() throws Exception {
        botaRelacao(new CadRelacao("centros").botaRelacao("centro", "codigo"));
        botaReferencia(new CadReferenciar(this, "centro", CadCentros.class).botaChave("centro", "codigo").botaEstrangeiro("centros.nome", "nome"));
        botaRelacao(new CadRelacao("negocios").botaRelacao("negocio", "codigo"));
        botaReferencia(new CadReferenciar(this, "negocio", CadNegocios.class).botaChave("negocio", "codigo").botaEstrangeiro("negocios.nome", "nome"));
    }
}
