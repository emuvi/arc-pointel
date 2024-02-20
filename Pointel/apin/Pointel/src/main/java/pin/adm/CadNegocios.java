package pin.adm;

import pin.libbas.Dados;
import pin.modamk.Cadastro;
import pin.libamk.Cmp;

public class CadNegocios extends Cadastro {

    public CadNegocios() throws Exception {
        super("negocios", "Negócios", new Cmp[]{
            new Cmp(1, 1, "codigo", "Código", Dados.Tp.Crs).mTamanho(6).botaDetalhe("chave", true),
            new Cmp(1, 2, "ativo", "Ativo", Dados.Tp.Log),
            new Cmp(1, 3, "nome", "Nome", Dados.Tp.Crs).mTamanho(60)
        });
    }
}
