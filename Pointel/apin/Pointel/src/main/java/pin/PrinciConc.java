package pin;

import pin.libbas.Conjunto;
import pin.libutl.Utl;
import pin.libvlr.Vlrs;
import pin.modinf.Banco;
import pin.modinf.BancoAoDefinir;
import pin.modinf.Conexao;

public class PrinciConc {

    public static void inicia() {
        AoDefinir conAoDefinir = new AoDefinir();
        Pointel.mainConc.botaAoConectar(conAoDefinir);
        Pointel.mainConc.botaAoDesconectar(conAoDefinir);
        Pointel.mainConc.botaAuditoria();
        Pointel.mainConc.criaMantenedor();
    }

    private static class AoDefinir extends BancoAoDefinir {

        @Override
        public Boolean definido(Banco oBanco, Tp doTipo) {
            Boolean retorno = true;
            if (oBanco instanceof Conexao) {
                if (((Conexao) oBanco).ehPrincipal()) {
                    if (doTipo.equals(BancoAoDefinir.Tp.Conectar)) {
                        retorno = false;
                        if (Pointel.mainConc.pegaBaseUsuarioSuper()) {
                            Pointel.mainRecs.botaSuperUsuario();
                            retorno = true;
                        } else {
                            Pointel.mainRecs.tiraSuperUsuario();
                            try {
                                Conjunto res = Pointel.mainConc.busca("SELECT usuarios_habilitacoes.nome, usuarios_habilitacoes.habilitado "
                                        + " FROM usuarios_habilitacoes  WHERE usuarios_habilitacoes.usuario = ?",
                                        new Vlrs(Pointel.mainConc.pegaBaseUsuarioCodigo()));
                                while (res.proximo()) {
                                    if (res.pgVlrLog(2).pgLog(false)) {
                                        Pointel.mainRecs.habilita(res.pgVlr(1).pgCrs());
                                    }
                                }
                                retorno = true;
                            } catch (Exception ex) {
                                Utl.registra(ex);
                            }
                        }
                        if (!retorno) {
                            Pointel.mainConc.desconecta();
                        }
                        Pointel.mainMesa.atualiza();
                    } else if (doTipo.equals(BancoAoDefinir.Tp.Desconectar)) {
                        retorno = false;
                        Pointel.mainRecs.tiraSuperUsuario();
                        Pointel.mainMesa.atualiza();
                        retorno = true;
                    }
                }
            }
            return retorno;
        }

    }

}
