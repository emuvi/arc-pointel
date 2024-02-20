package pin.app;

import pin.adm.CadNegocios;
import pin.libbas.Dados;
import pin.modamk.CadRelacao;
import pin.modamk.Cadastro;
import pin.libamk.Cmp;
import pin.modamk.CadReferenciar;

public class CadFolguedos extends Cadastro {

    public CadFolguedos() throws Exception {
        super("folguedos", "Folguedos", new Cmp[]{
            new Cmp(1, 1, "instancia", "Instância", Dados.Tp.Crs).mTamanho(12).botaDetalhe("chave", true),
            new Cmp(1, 2, "ativo", "Ativo", Dados.Tp.Log),
            new Cmp(1, 3, "titulo", "Título", Dados.Tp.Crs).mTamanho(120),
            new Cmp(2, 1, "atualizacao", "Atualização", Dados.Tp.Int).mVlrInicial(90),
            new Cmp(2, 2, "renderizacao", "Renderização", Dados.Tp.Int).mVlrInicial(45),
            new Cmp(3, 1, "negocio", "Negócio - Cód.", Dados.Tp.Crs).mTamanho(6),
            new Cmp(3, 2, "negocios.nome", "Negócio - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true)
        });
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        botaRelacao(new CadRelacao("negocios").botaRelacao("negocio", "codigo"));
        botaReferencia(new CadReferenciar(this, "negocio", CadNegocios.class).botaChave("negocio", "codigo").botaEstrangeiro("negocios.nome", "nome"));
    }

}
