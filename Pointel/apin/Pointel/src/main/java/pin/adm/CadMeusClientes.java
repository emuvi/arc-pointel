package pin.adm;

import pin.libvlr.Vlrs;

public class CadMeusClientes extends CadPessoas {

    public CadMeusClientes() throws Exception {
        super();
        titulo = "Meus Clientes";
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        pegaCampo("cliente").mVlrFixo("S");
        pegaCampo("representante").mVlrFixo(conexao.pegaBaseUsuarioPessoa());
        pegaCampo("representantes.nome").mVlrFixo(conexao.pegaBaseUsuarioNome());
        campos.remove(pegaCampo("fornecedor"));
        campos.remove(pegaCampo("transportadora"));
        campos.remove(pegaCampo("colaborador"));
        campos.remove(pegaCampo("consultor"));
//        List<Object> tabsRests = new ArrayList<>();
//        Conjunto res = conexao.recorrentes().busca("SELECT tabela FROM pessoas_comissoes WHERE pessoa = ?",
//                new Vlrs(conexao.pegaBaseUsuarioPessoa()));
//        while (res.proximo()) {
//            tabsRests.add(res.pgVlr(1));
//        }
//        pegaCampo("tabela_preco").botaRestritos(tabsRests.toArray());
//        pegaCampo("tabela_secundaria").botaRestritos(tabsRests.toArray());
//        trocaReferencia(CadTabelasPrecos.class, CadMinhasTabelasPrecos.class);
        String creditoInicial = conexao.conCfgs().pegaCrs("PointelAdmin - Opções - Empresa - Crédito Inicial", "");
        if (!creditoInicial.isEmpty()) {
            String creditoNome = conexao.recorrentes().busca("SELECT nome FROM creditos WHERE codigo = ?",
                    new Vlrs(creditoInicial))
                    .pgCol().pgCrs();
            pegaCampo("credito").mVlrInicial(creditoInicial);
            pegaCampo("creditos.nome").mVlrInicial(creditoNome);
        }
        pegaCampo("credito").botaDetalhe("podeInserir", false).botaDetalhe("podeEditar", false);
        pegaCampo("creditos.nome").botaDetalhe("podeInserir", false).botaDetalhe("podeEditar", false);
        pegaCampo("credito_obs").botaDetalhe("podeInserir", false).botaDetalhe("podeEditar", false);
        pegaCampo("bloqueado").botaDetalhe("podeInserir", false).botaDetalhe("podeEditar", false);
    }

    @Override
    public void preparaIntf() throws Exception {
        super.preparaIntf();
        removeBotao("Comissões");
    }

}
