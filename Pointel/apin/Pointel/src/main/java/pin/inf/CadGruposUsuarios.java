package pin.inf;

import java.util.ArrayList;
import java.util.List;
import pin.adm.CadNegocios;
import pin.libamk.Botao;
import pin.libamk.Cmp;
import pin.libbas.Conferencia;
import pin.libbas.Conjunto;
import pin.libbas.Dados;
import pin.libbas.Partes;
import pin.libbas.Progresso;
import pin.libutl.Utl;
import pin.libutl.UtlCrs;
import pin.libvlr.Vlrs;
import pin.modamk.CadRelacao;
import pin.modamk.CadReferenciar;
import pin.modamk.Cadastro;

public class CadGruposUsuarios extends Cadastro {

    public CadGruposUsuarios() throws Exception {
        super("grupos_usuarios", "Grupos de Usuários", new Cmp[]{
            new Cmp(1, 1, "codigo", "Código", Dados.Tp.Crs).mTamanho(4).botaDetalhe("chave", true),
            new Cmp(1, 2, "ativo", "Ativo", Dados.Tp.Log),
            new Cmp(1, 3, "nome", "Nome", Dados.Tp.Crs).mTamanho(60),
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

    @Override
    public void preparaIntf() throws Exception {
        botaAntesDeExcluir(new CnfExcluir());
        botaBotao(new BotHabilitacoes());
        botaBotao(new BotLimpar());
        botaBotao(new BotAplicar());
    }

    private class CnfExcluir extends Conferencia {

        @Override
        public void confere(Object comOrigem) throws Exception {
            String codigo = pegaCampo("codigo").pEdt().pgVlr("").pgCrs();
            conexao.opera("DELETE FROM grupos_usuarios_habilitacoes WHERE grupo = ?",
                    new Vlrs(codigo));
            conexao.opera("UPDATE usuarios SET grupo = NULL WHERE grupo = ?",
                    new Vlrs(codigo));
        }
    }

    private class BotHabilitacoes extends Botao {

        public BotHabilitacoes() {
            super("Habilitações", 'H');
        }

        @Override
        public void aoExecutar(Object comOrigem) throws Exception {
            if (confirmar(true)) {
                CadGruposUsuariosHabilitacoes filho = new CadGruposUsuariosHabilitacoes();
                filho.pegaCampo("grupo").mVlrFixo(pegaCampo("codigo").pEdt().pgVlr());
                filho.pegaCampo("grupos_usuarios.nome").mVlrFixo(pegaCampo("nome").pEdt().pgVlr());
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
                conexao.opera("DELETE FROM grupos_usuarios_habilitacoes WHERE grupo = ?",
                        new Vlrs(codigo));
                Utl.alerta("Limpada Habilitações do Grupo");
            }
        }
    }

    private class BotAplicar extends Botao {

        public BotAplicar() {
            super("Aplicar", 'A');
        }

        @Override
        public void aoExecutar(Object comOrigem) throws Exception {
            if (confirmar(true)) {
                String codigo = pegaCampo("codigo").pEdt().pgVlr("").pgCrs();
                if (!codigo.isEmpty()) {
                    new Thread("Aplicando Habilitações do Grupo de Usuários") {

                        @Override
                        public void run() {
                            try {
                                Progresso prog = new Progresso("Aplicando Habilitações do Grupo de Usuários");
                                prog.abre("Carregando Informações...");

                                Conjunto hab = conexao.busca("SELECT nome FROM grupos_usuarios_habilitacoes WHERE grupo = ? AND habilitado = 'S'",
                                        new Vlrs(codigo));
                                Conjunto usu = conexao.busca("SELECT codigo, usuario FROM usuarios WHERE grupo = ?",
                                        new Vlrs(codigo));

                                prog.abre(usu.size() * hab.size());

                                try {
                                    while (usu.proximo()) {
                                        String codUsuario = usu.pgVlr(1).pgCrs();
                                        String nomUsuario = usu.pgVlr(2).pgCrs();

                                        prog.bota("Habilitando " + nomUsuario);

                                        conexao.opera("DELETE FROM usuarios_habilitacoes WHERE usuario = ?",
                                                new Vlrs(codUsuario));

                                        List<String> habilitados = new ArrayList<>();
                                        hab.reinicia();
                                        while (hab.proximo()) {
                                            String nomHab = hab.pgVlr(1).pgCrs();
                                            String[] partes = Partes.pega(nomHab, ".");
                                            String processa = "";
                                            for (String parte : partes) {
                                                processa = UtlCrs.soma(processa, ".", parte);
                                                if (!habilitados.contains(processa)) {
                                                    conexao.opera("INSERT INTO usuarios_habilitacoes (usuario, nome, habilitado) VALUES (?, ?, 'S')",
                                                            new Vlrs(codUsuario, processa));
                                                    habilitados.add(processa);
                                                }
                                            }
                                            prog.avanca();
                                        }
                                    }
                                } catch (Exception ex) {
                                    Utl.registra(ex);
                                } finally {
                                    prog.termina();
                                }
                            } catch (Exception ex) {
                                Utl.registra(ex);
                            }
                        }

                    }.start();
                }
            }
        }
    }
}
