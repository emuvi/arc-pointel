package pin.jrb;

import pin.Pointel;
import pin.libbas.Erro;
import pin.modinf.Bancos;
import pin.modinf.Conexao;

public class ServConectar extends Serv {

    public String conexao;
    public String banco;
    public String servidor;
    public Integer porta;
    public String base;
    public String usuario;
    public String senha;
    public String baseUsuario;
    public String baseSenha;

    public ServConectar() {
        super(Servico.Tp.Conectar);
        this.conexao = null;
        this.banco = null;
        this.servidor = null;
        this.porta = null;
        this.base = null;
        this.usuario = null;
        this.senha = null;
        this.baseUsuario = null;
        this.baseSenha = null;
    }

    @Override
    public void executa() throws Exception {
        Conexao conAct = Pointel.mainIntel.pegaConexao(conexao);
        if (conAct == null) {
            throw new Erro("Conexão Não Selecionada Adequadamente");
        } else {
            if (!conAct.estaConectado()) {
                conAct.configura(Bancos.Tp.valueOf(banco), servidor, porta, base, usuario, senha, baseUsuario, baseSenha);
                conAct.conecta();
            }
        }
    }
}
