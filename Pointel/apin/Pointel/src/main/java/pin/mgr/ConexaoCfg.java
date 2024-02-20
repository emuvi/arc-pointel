package pin.mgr;

import pin.modinf.Bancos;
import pin.modinf.Conexao;

public class ConexaoCfg {

    public String banco;
    public String nome;
    public String servidor;
    public Integer porta;
    public String base;
    public String usuario;
    public String senha;

    public ConexaoCfg() {
        this.banco = null;
        this.nome = null;
        this.servidor = null;
        this.porta = null;
        this.base = null;
        this.usuario = null;
        this.senha = null;
    }

    public ConexaoCfg(String banco, String nome, String servidor, Integer porta, String base, String usuario, String senha) {
        this.banco = banco;
        this.nome = nome;
        this.servidor = servidor;
        this.porta = porta;
        this.base = base;
        this.usuario = usuario;
        this.senha = senha;
    }

    public Conexao conecta() throws Exception {
        Conexao retorno = new Conexao(nome, Bancos.Tp.valueOf(banco), servidor, porta, base, usuario, senha);
        retorno.conecta();
        return retorno;
    }

    @Override
    public String toString() {
        return nome;
    }
}
