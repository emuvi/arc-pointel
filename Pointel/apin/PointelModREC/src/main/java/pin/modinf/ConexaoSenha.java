package pin.modinf;

import java.util.Objects;
import pin.libamg.EdtSen;
import pin.libamk.Campos;
import pin.libamk.Cmp;
import pin.libamk.Intf;
import pin.libbas.Dados;
import pin.libutl.Utl;
import pin.libutl.UtlCrs;
import pin.libvlr.Vlrs;

public class ConexaoSenha extends Intf {

    Conexao conexao;

    public ConexaoSenha(Conexao daConexao) {
        super(new Campos(
                new Cmp(1, 1, "atual", "Senha Atual", Dados.Tp.Sen),
                new Cmp(2, 1, "nova", "Nova Senha", Dados.Tp.Sen).botaParams(EdtSen.Params.CONFIRMAR)
        ));
        conexao = daConexao;
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Alterar Senha");
    }

    @Override
    public void preparaIntf() throws Exception {
        super.preparaIntf();
        botaConfirmarECancelar();
    }

    @Override
    public void aoConfirmar(Object comOrigem) throws Exception {
        try {
            if (!conexao.estaConectado()) {
                throw new Exception("É Preciso Estar Conectado para Trocar a Senha");
            }
            if (UtlCrs.vazio(conexao.pegaBaseUsuario())) {
                throw new Exception("É Preciso Conectar com um Usuário para Trocar a Senha");
            }
            if (UtlCrs.vazio(conexao.pegaBaseUsuarioSenha())) {
                throw new Exception("É Preciso Conectar com uma Senha para Trocar a Senha");
            }
            if (UtlCrs.vazio(conexao.pegaBaseUsuarioCodigo())) {
                throw new Exception("É Preciso Conectar com um Usuário que Tenha Codigo");
            }
            if (!Objects.equals(cmps().pgVlr("atual").pgCrs(), conexao.pegaBaseUsuarioSenha())) {
                throw new Exception("A Senha Digitada Não é Igual a Atual");
            }
            String nova = cmps().pgVlr("nova", "").pgCrs();
            if (nova.length() < 6) {
                throw new Exception("A Nova Senha Tem Que Ter no Mínimo 6 Caracteres");
            }
            conexao.opera("UPDATE usuarios SET senha = ? WHERE codigo = ?",
                    new Vlrs(nova, conexao.pegaBaseUsuarioCodigo()));
            conexao.botaBaseUsuarioSenha(nova);
            Utl.alerta("Senha Alterada Com Sucesso");
            fecha();
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }

}
