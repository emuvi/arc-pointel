package pin.inf;

import pin.adm.CadNegocios;
import pin.adm.CadPessoas;
import pin.libamk.Botao;
import pin.libamk.Cmp;
import pin.libbas.Conferencia;
import pin.libbas.Dados;
import pin.libutl.Utl;
import pin.libvlr.Vlrs;
import pin.modamk.CadRelacao;
import pin.modamk.CadReferenciar;
import pin.modamk.Cadastro;

public class CadUsuarios extends Cadastro {

    public CadUsuarios() throws Exception {
        super("usuarios", "Usuários", new Cmp[]{
            new Cmp(1, 1, "codigo", "Código", Dados.Tp.Crs).mTamanho(4).botaDetalhe("chave", true),
            new Cmp(1, 2, "ativo", "Ativo", Dados.Tp.Log),
            new Cmp(1, 3, "super", "Super", Dados.Tp.Log),
            new Cmp(1, 4, "usuario", "Usuário", Dados.Tp.Crs).mTamanho(40),
            new Cmp(1, 5, "senha", "Senha", Dados.Tp.Crs).mTamanho(40),
            new Cmp(2, 1, "pessoa", "Pessoa - Cód.", Dados.Tp.Crs).mTamanho(8),
            new Cmp(2, 2, "pessoas.nome", "Pessoa - Nome", Dados.Tp.Crs).mTamanho(80).botaDetalhe("estrangeiro", true),
            new Cmp(2, 3, "grupo", "Grupo - Cód.", Dados.Tp.Crs).mTamanho(4),
            new Cmp(2, 4, "grupos_usuarios.nome", "Grupo - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true),
            new Cmp(3, 1, "negocio", "Negócio - Cód.", Dados.Tp.Crs).mTamanho(6),
            new Cmp(3, 2, "negocios.nome", "Negócio - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true)
        });
    }

    @Override
    public void preparaEstr() throws Exception {
        botaRelacao(new CadRelacao("pessoas").botaRelacao("pessoa", "codigo"));
        botaRelacao(new CadRelacao("grupos_usuarios").botaRelacao("grupo", "codigo"));
        botaReferencia(new CadReferenciar(this, "pessoa", CadPessoas.class).botaChave("pessoa", "codigo").botaEstrangeiro("pessoas.nome", "nome"));
        botaReferencia(new CadReferenciar(this, "grupo", CadGruposUsuarios.class).botaChave("grupo", "codigo").botaEstrangeiro("grupos_usuarios.nome", "nome"));
        botaRelacao(new CadRelacao("negocios").botaRelacao("negocio", "codigo"));
        botaReferencia(new CadReferenciar(this, "negocio", CadNegocios.class).botaChave("negocio", "codigo").botaEstrangeiro("negocios.nome", "nome"));
    }

    @Override
    public void preparaIntf() throws Exception {
        botaBotao(new BotHabilitacoes());
        botaBotao(new BotLimpar());

        botaAntesDeExcluir(new Conferencia() {
            @Override
            public void confere(Object comOrigem) throws Exception {
                String codigo = pegaCampo("codigo").pEdt().pgVlr("").pgCrs();
                conexao.opera("DELETE FROM usuarios_habilitacoes WHERE usuario = ?",
                        new Vlrs(codigo));
            }
        });
    }

    private class BotHabilitacoes extends Botao {

        public BotHabilitacoes() {
            super("Habilitações", 'H');
        }

        @Override
        public void aoExecutar(Object comOrigem) throws Exception {
            if (confirmar(true)) {
                CadUsuariosHabilitacoes filho = new CadUsuariosHabilitacoes();
                filho.pegaCampo("usuario").mVlrFixo(pegaCampo("codigo").pEdt().pgVlr());
                filho.pegaCampo("usuarios.usuario").mVlrFixo(pegaCampo("usuario").pEdt().pgVlr());
                filho.mostra();
            }
        }
    }

    private class BotLimpar extends Botao {

        public BotLimpar() {
            super("Limpar", 'L');
        }

        @Override
        public void aoExecutar(Object comOrigem) throws Exception {
            if (confirmar(true)) {
                String codigo = pegaCampo("codigo").pEdt().pgVlr("").pgCrs();
                conexao.opera("DELETE FROM usuarios_habilitacoes WHERE usuario = ?",
                        new Vlrs(codigo));
                Utl.alerta("Limpada Habilitações do Usuário");
            }
        }
    }
}
