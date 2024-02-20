package pin.inf;

import pin.adm.CadNegocios;
import pin.adm.CadPessoas;
import pin.libbas.Dados;
import pin.libbas.Globais;
import pin.modamk.CadRelacao;
import pin.modamk.Cadastro;
import pin.libamk.Cmp;
import pin.modamk.CadReferenciar;
import pin.modamk.Recursos;

public class CadGruposUsuariosHabilitacoes extends Cadastro {

    public CadGruposUsuariosHabilitacoes() throws Exception {
        super("grupos_usuarios_habilitacoes", "Habilitações dos Grupos de Usuários", new Cmp[]{
            new Cmp(1, 1, "grupo", "Grupo - Cód.", Dados.Tp.Crs).mTamanho(4).botaDetalhe("chave", true),
            new Cmp(1, 2, "grupos_usuarios.nome", "Grupo - Nome", Dados.Tp.Crs).mTamanho(40).botaDetalhe("estrangeiro", true),
            new Cmp(2, 1, "nome", "Nome", Dados.Tp.Sug).mTamanho(250).botaDetalhe("chave", true).botaOpcoes(((Recursos) Globais.pega("mainRecs")).pega().toArray()).mLargura(500),
            new Cmp(2, 2, "habilitado", "Habilitado", Dados.Tp.Log).mVlrInicial("S"),
            new Cmp(3, 1, "negocio", "Negócio - Cód.", Dados.Tp.Crs).mTamanho(6),
            new Cmp(3, 2, "negocios.nome", "Negócio - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true)
        });
    }

    @Override
    public void preparaEstr() throws Exception {
        botaRelacao(new CadRelacao("grupos_usuarios").botaRelacao("grupo", "codigo"));
        botaReferencia(new CadReferenciar(this, "grupo", CadPessoas.class).botaChave("grupo", "codigo").botaEstrangeiro("grupos_usuarios.nome", "nome"));
        botaRelacao(new CadRelacao("negocios").botaRelacao("negocio", "codigo"));
        botaReferencia(new CadReferenciar(this, "negocio", CadNegocios.class).botaChave("negocio", "codigo").botaEstrangeiro("negocios.nome", "nome"));
    }

    @Override
    public void preparaIntf() throws Exception {
    }
}
