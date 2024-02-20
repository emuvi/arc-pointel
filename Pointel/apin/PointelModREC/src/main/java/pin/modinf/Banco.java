package pin.modinf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import pin.libamk.Cmp;
import pin.libbas.Conjunto;
import pin.libbas.Dados;
import pin.libutl.Utl;
import pin.libutl.UtlBin;
import pin.libvlr.Vlrs;

public abstract class Banco {

    private volatile String nome;
    private volatile Bancos.Tp tipo;
    private volatile String servidor;
    private volatile Integer porta;
    private volatile String base;
    private volatile String usuario;
    private volatile String senha;
    private volatile Connection link;
    private volatile BancoAoDefinir aoConectar;
    private volatile BancoAoDefinir aoDesconectar;
    private volatile BancoAoRealizar aoExecutar;
    private volatile BancoAoRealizar aoProcurar;
    private volatile Recorrentes recorrentes;
    private volatile BancoTabelas bancoTabelas;

    public Banco() {
        this(null, null, null, null, null, null, null);
    }

    public Banco(String comNome, Bancos.Tp doTipo, String noServidor, Integer ePorta, String daBase,
            String comUsuario, String eSenha) {
        link = null;
        aoConectar = null;
        aoDesconectar = null;
        aoExecutar = null;
        aoProcurar = null;
        recorrentes = new Recorrentes(this);
        bancoTabelas = null;
        configura(comNome, doTipo, noServidor, ePorta, daBase, comUsuario, eSenha);
    }

    public String pegaNome() {
        return nome;
    }

    public Banco botaNome(String nome) {
        this.nome = nome;
        return this;
    }

    public Bancos.Tp pegaTipo() {
        return tipo;
    }

    public Banco botaTipo(Bancos.Tp tipo) {
        this.tipo = tipo;
        return this;
    }

    public String pegaServidor() {
        return servidor;
    }

    public Banco botaServidor(String servidor) {
        this.servidor = servidor;
        return this;
    }

    public Integer pegaPorta() {
        return porta;
    }

    public Banco botaPorta(Integer porta) {
        this.porta = porta;
        return this;
    }

    public String pegaBase() {
        return base;
    }

    public Banco botaBase(String base) {
        this.base = base;
        return this;
    }

    public String pegaUsuario() {
        return usuario;
    }

    public Banco botaUsuario(String usuario) {
        this.usuario = usuario;
        return this;
    }

    public String pegaSenha() {
        return senha;
    }

    public Banco botaSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public Connection link() {
        return link;
    }

    public Banco botaLink(Connection link) {
        this.link = link;
        return this;
    }

    public BancoAoDefinir aoConectar() {
        return aoConectar;
    }

    public Banco botaAoConectar(BancoAoDefinir aoConectar) {
        this.aoConectar = aoConectar;
        return this;
    }

    public BancoAoDefinir aoDesconectar() {
        return aoDesconectar;
    }

    public Banco botaAoDesconectar(BancoAoDefinir aoDesconectar) {
        this.aoDesconectar = aoDesconectar;
        return this;
    }

    public BancoAoRealizar aoExecutar() {
        return aoExecutar;
    }

    public Banco botaAoExecutar(BancoAoRealizar aoExecutar) {
        this.aoExecutar = aoExecutar;
        return this;
    }

    public BancoAoRealizar aoProcurar() {
        return aoProcurar;
    }

    public Banco botaAoProcurar(BancoAoRealizar aoProcurar) {
        this.aoProcurar = aoProcurar;
        return this;
    }

    public Recorrentes recorrentes() {
        return recorrentes;
    }

    public Banco botaRecorrentes(Recorrentes recorrentes) {
        this.recorrentes = recorrentes;
        return this;
    }

    public void configura(Bancos.Tp doTipo, String noServidor, Integer ePorta, String daBase,
            String comUsuario, String eSenha) {
        desconecta();
        tipo = doTipo;
        servidor = noServidor;
        porta = ePorta;
        base = daBase;
        usuario = comUsuario;
        senha = eSenha;
    }

    public void configura(String comNome, Bancos.Tp doTipo, String noServidor, Integer ePorta,
            String daBase, String comUsuario, String eSenha) {
        desconecta();
        nome = comNome;
        tipo = doTipo;
        servidor = noServidor;
        porta = ePorta;
        base = daBase;
        usuario = comUsuario;
        senha = eSenha;
    }

    public void limpa() {
        desconecta();
        tipo = null;
        servidor = null;
        porta = null;
        base = null;
        usuario = null;
        senha = null;
    }

    public Boolean estaConfigurado() {
        return tipo != null && base != null;
    }

    public abstract Boolean conecta() throws Exception;

    public void desconecta() {
        if (link != null) {
            try {
                if (!link.isClosed()) {
                    link.close();
                    if (aoDesconectar != null) {
                        aoDesconectar.definido(this, BancoAoDefinir.Tp.Desconectar);
                    }
                }
            } catch (Exception ex) {
                Utl.registra(ex, false);
            }
        }
        link = null;
        if (bancoTabelas != null) {
            bancoTabelas.limpa();
        }
        bancoTabelas = null;
    }

    public Boolean estaConectado() {
        Boolean retorno = true;
        try {
            if (link == null) {
                retorno = false;
            } else if (link.isClosed()) {
                retorno = false;
            }
        } catch (Exception ex) {
            Utl.registra(ex, false);
            retorno = false;
        }
        return retorno;
    }

    private boolean testeDeConexao() {
        if (link == null) {
            return false;
        }
        try {
            link.createStatement().executeQuery("SELECT 1");
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public void verificaConectadoReconectando() throws Exception {
        if (!testeDeConexao()) {
            try {
                conecta();
            } catch (Exception e) {
            }
        } else
            return;
        if (!testeDeConexao()) {
            try {
                UtlBin.esperaMilis(300);
                conecta();
            } catch (Exception e) {
            }
        } else
            return;
        if (!testeDeConexao()) {
            try {
                UtlBin.esperaMilis(300);
                conecta();
            } catch (Exception e) {
            }
        } else
            return;
        if (!testeDeConexao()) {
            try {
                UtlBin.esperaMilis(300);
                conecta();
            } catch (Exception e) {
            }
        } else
            return;
        if (!testeDeConexao()) {
            try {
                UtlBin.esperaMilis(300);
                conecta();
            } catch (Exception e) {
            }
        } else
            return;
        if (!testeDeConexao()) {
            throw new Exception("Banco de Dados Não Conectado.");
        }
    }

    public synchronized BancoTabelas bancoTabelas() throws Exception {
        if (bancoTabelas == null) {
            bancoTabelas = new BancoTabelas(this);
            carregaTabelas(bancoTabelas);
        }
        return bancoTabelas;
    }

    public abstract void carregaTabelas(Tabelas nasTabelas) throws Exception;

    public abstract void carregaCampos(Tabela daTabela) throws Exception;

    public abstract void carregaEstrangeiros(Tabela daTabela) throws Exception;

    public abstract void carregaIndices(Tabela daTabela) throws Exception;

    public abstract void carregaVerificadores(Tabela daTabela) throws Exception;

    public abstract String pCriacao(Cmp doCampo);

    public abstract List<String> pUsuarios() throws Exception;

    public abstract Banco replica() throws Exception;

    public abstract Banco replicaLeve() throws Exception;

    public abstract Banco iniciaTransacao() throws Exception;

    public void cancelaTransacao() throws Exception {
        opera("ROLLBACK");
    }

    public void salvaTransacao() throws Exception {
        opera("COMMIT");
    }

    public void cancelaTransacaoEFecha() throws Exception {
        cancelaTransacao();
        desconecta();
    }

    public void salvaTransacaoEFecha() throws Exception {
        salvaTransacao();
        desconecta();
    }

    public Boolean operaLog(String oComando) throws Exception {
        verificaConectadoReconectando();
        Boolean retorno = link.createStatement().execute(oComando);
        if (aoExecutar != null) {
            aoExecutar.realizado(this, BancoAoRealizar.Tp.Executar, oComando, null,
                    retorno ? 1 : -1);
        }
        return retorno;
    }

    public Integer opera(String oComando) throws Exception {
        verificaConectadoReconectando();
        Integer retorno = link.createStatement().executeUpdate(oComando);
        if (aoExecutar != null) {
            aoExecutar.realizado(this, BancoAoRealizar.Tp.Executar, oComando, null, retorno);
        }
        return retorno;
    }

    public Integer opera(String oComando, Vlrs comParametros) throws Exception {
        verificaConectadoReconectando();
        PreparedStatement pstm = link.prepareStatement(oComando);
        Dados.botaParametros(pstm, comParametros);
        Integer retorno = pstm.executeUpdate();
        if (aoExecutar != null) {
            aoExecutar.realizado(this, BancoAoRealizar.Tp.Executar, oComando, comParametros,
                    retorno);
        }
        return retorno;
    }

    public Conjunto busca(String comSelecao) throws Exception {
        verificaConectadoReconectando();
        PreparedStatement pstm = link.prepareStatement(comSelecao);
        Conjunto retorno = new Conjunto(pstm.executeQuery());
        if (aoProcurar != null) {
            aoProcurar.realizado(this, BancoAoRealizar.Tp.Procurar, comSelecao, null,
                    retorno.size());
        }
        return retorno;
    }

    public Conjunto busca(String comSelecao, List<Dados.Tp> eTipos) throws Exception {
        verificaConectadoReconectando();
        PreparedStatement pstm = link.prepareStatement(comSelecao);
        Conjunto retorno = new Conjunto(pstm.executeQuery(), eTipos);
        if (aoProcurar != null) {
            aoProcurar.realizado(this, BancoAoRealizar.Tp.Procurar, comSelecao, null,
                    retorno.size());
        }
        return retorno;
    }

    public Conjunto busca(String comSelecao, Vlrs eParametros) throws Exception {
        verificaConectadoReconectando();
        PreparedStatement pstm = link.prepareStatement(comSelecao);
        Dados.botaParametros(pstm, eParametros);
        Conjunto retorno = new Conjunto(pstm.executeQuery());
        if (aoProcurar != null) {
            aoProcurar.realizado(this, BancoAoRealizar.Tp.Procurar, comSelecao, eParametros,
                    retorno.size());
        }
        return retorno;
    }

    public Conjunto busca(String comSelecao, Vlrs eParametros, List<Dados.Tp> eTipos)
            throws Exception {
        verificaConectadoReconectando();
        PreparedStatement pstm = link.prepareStatement(comSelecao);
        Dados.botaParametros(pstm, eParametros);
        Conjunto retorno = new Conjunto(pstm.executeQuery(), eTipos);
        if (aoProcurar != null) {
            aoProcurar.realizado(this, BancoAoRealizar.Tp.Procurar, comSelecao, eParametros,
                    retorno.size());
        }
        return retorno;
    }

    public Boolean ehMesmosServico(Banco eBanco) {
        Boolean retorno = true;

        if (!Objects.equals(tipo, eBanco.tipo)) {
            retorno = false;
        }
        if (!Objects.equals(servidor, eBanco.servidor)) {
            retorno = false;
        }
        if (!Objects.equals(porta, eBanco.porta)) {
            retorno = false;
        }
        if (!Objects.equals(base, eBanco.base)) {
            retorno = false;
        }

        return retorno;
    }

}
