package pin.jrb;

import pin.libamg.Edt;
import pin.libamk.Botao;
import pin.libamk.Campos;
import pin.libamk.Cmp;
import pin.libamk.Intf;
import pin.libbas.Dados;
import pin.modinf.Bancos;

public class ServConectarIntf extends Intf {

    public Edt editor;

    public ServConectarIntf(Edt paraEditor, ServConectar comInicial) throws Exception {
        super(new Campos(
                new Cmp(1, 1, "conexao", "Conexão", Dados.Tp.Enu).botaOpcoes("(P) Principal", "(J) PointelJarbs").mVlrInicial(comInicial.conexao),
                new Cmp(1, 2, "banco", "Banco", Dados.Tp.Enu).botaOpcoes((Object[]) Bancos.Tp.values()).mVlrInicial(comInicial.banco),
                new Cmp(2, 1, "servidor", "Servidor", Dados.Tp.Crs).mLargura(240).mVlrInicial(comInicial.servidor),
                new Cmp(2, 2, "porta", "Porta", Dados.Tp.Int).mVlrInicial(comInicial.porta),
                new Cmp(2, 3, "base", "Base", Dados.Tp.Crs).mLargura(140).mVlrInicial(comInicial.base),
                new Cmp(3, 1, "usuario", "Usuário", Dados.Tp.Crs).mLargura(140).mVlrInicial(comInicial.usuario),
                new Cmp(3, 2, "senha", "Senha", Dados.Tp.Sen).mLargura(140).mVlrInicial(comInicial.senha),
                new Cmp(3, 3, "base_usuario", "Base Usuário", Dados.Tp.Crs).mLargura(140).mVlrInicial(comInicial.baseUsuario),
                new Cmp(3, 4, "base_senha", "Base Senha", Dados.Tp.Sen).mLargura(140).mVlrInicial(comInicial.baseSenha)
        ));
        editor = paraEditor;
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Serviço Conectar");
    }

    @Override
    public void preparaIntf() throws Exception {
        super.preparaIntf();
        botaBotao(new BotConfimar());
    }

    private class BotConfimar extends Botao {

        public BotConfimar() {
            super("Confirmar");
        }

        @Override
        public void aoExecutar(Object comOrigem) throws Exception {
            ServConectar sac = new ServConectar();
            sac.conexao = cmps().pgVlr("conexao").pgCrs();
            switch (sac.conexao) {
                case "P":
                    sac.mNome("Principal");
                    break;
                case "J":
                    sac.mNome("PointelJarbs");
                    break;
            }
            sac.banco = cmps().pgVlr("banco").pgCrs();
            sac.servidor = cmps().pgVlr("servidor").pgCrs();
            sac.porta = cmps().pgVlr("porta").pgInt();
            sac.base = cmps().pgVlr("base").pgCrs();
            sac.usuario = cmps().pgVlr("usuario").pgCrs();
            sac.senha = cmps().pgVlr("senha").pgCrs();
            sac.baseUsuario = cmps().pgVlr("base_usuario").pgCrs();
            sac.baseSenha = cmps().pgVlr("base_senha").pgCrs();
            editor.mdVlr(sac.descreve());
            editor.botaFoco();
        }

    }

}
