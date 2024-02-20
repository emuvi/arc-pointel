package pin.modinf;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import pin.libamk.Cmp;
import pin.libbas.Configs;
import pin.libbas.Conjunto;
import pin.libbas.Dados;
import pin.libbas.Globais;
import pin.libjan.Janelas;
import pin.libutl.Utl;
import pin.libutl.UtlAle;
import pin.libutl.UtlBin;
import pin.libvlr.VTex;
import pin.libvlr.Vlrs;

public class Conexao extends Banco {

    private volatile Banco banco;
    private volatile String baseUsuario;
    private volatile String baseUsuarioSenha;
    private volatile String baseUsuarioCodigo;
    private volatile Boolean baseUsuarioSuper;
    private volatile String baseUsuarioPessoa;
    private volatile String baseUsuarioNome;
    private volatile Configs cfgs;
    private volatile ConexaoCfgs conCfgs;
    private volatile Boolean deveriaConectar;
    private volatile JFrame janelaAssociada;
    private volatile Boolean fecharJanelaSeCancelar;
    private final Boolean auditoria = false;
    private volatile Boolean principal;
    private volatile Boolean fechar;

    public Conexao() {
        this("Sem Nome", null, null, null, null, null, null);
    }

    public Conexao(String comNome) {
        this(comNome, null, null, null, null, null, null);
    }

    public Conexao(String comNome, Bancos.Tp doTipo, String daBase) {
        this(comNome, doTipo, null, null, daBase, null, null);
    }

    public Conexao(String comNome, Bancos.Tp doTipo, String noServidor, Integer ePorta) {
        this(comNome, doTipo, noServidor, ePorta, null, null, null);
    }

    public Conexao(String comNome, Bancos.Tp doTipo, String noServidor, Integer ePorta, String daBase) {
        this(comNome, doTipo, noServidor, ePorta, daBase, null, null);
    }

    public Conexao(String comNome, Bancos.Tp doTipo, String noServidor, Integer ePorta, String daBase, String comUsuario, String eSenha) {
        this(comNome, doTipo, noServidor, ePorta, daBase, comUsuario, eSenha, null, null);
    }

    public Conexao(String comNome, Bancos.Tp doTipo, String noServidor, Integer ePorta, String daBase, String comUsuario, String eSenha, String comBaseUsuario, String eBaseSenha) {
        super(comNome, doTipo, noServidor, ePorta, daBase, comUsuario, eSenha);
        baseUsuario = comBaseUsuario;
        baseUsuarioSenha = eBaseSenha;
        cfgs = ((Configs) Globais.pega("mainConf"));
        conCfgs = new ConexaoCfgs(this);
        deveriaConectar = false;
        janelaAssociada = null;
        fecharJanelaSeCancelar = false;
        principal = false;
        fechar = false;
    }

    public void configura(Bancos.Tp doTipo, String noServidor, Integer ePorta, String daBase, String comUsuario, String eSenha, String comBaseUsuario, String eBaseSenha) {
        super.configura(pegaNome(), doTipo, noServidor, ePorta, daBase, comUsuario, eSenha);
        baseUsuario = comBaseUsuario;
        baseUsuarioSenha = eBaseSenha;
    }

    public void configura(String comNome, Bancos.Tp doTipo, String noServidor, Integer ePorta, String daBase, String comUsuario, String eSenha, String comBaseUsuario, String eBaseSenha) {
        super.configura(comNome, doTipo, noServidor, ePorta, daBase, comUsuario, eSenha);
        baseUsuario = comBaseUsuario;
        baseUsuarioSenha = eBaseSenha;
    }

    public void limpa() {
        desconecta();
        super.limpa();
        baseUsuario = null;
        baseUsuarioSenha = null;
        baseUsuarioCodigo = null;
        baseUsuarioSuper = null;
        baseUsuarioPessoa = null;
        baseUsuarioNome = null;
    }

    @Override
    public void desconecta() {
        super.desconecta();
        if (banco != null) {
            banco.desconecta();
        }
        banco = null;
        deveriaConectar = false;
    }

    public Conexao fechar() {
        desconecta();
        fechar = true;
        return this;
    }

    @Override
    public Boolean conecta() throws Exception {
        Boolean retorno = false;
        desconecta();
        switch (pegaTipo()) {
            case PostgreSQL:
                banco = new Postgre(pegaNome(), pegaServidor(), pegaPorta(), pegaBase(), pegaUsuario(), pegaSenha());
                break;
            case SQLite:
                banco = new SQLite(pegaNome(), pegaBase());
                break;
            default:
                throw new Exception("Tipo do Banco Não Preparado");
        }
        banco.botaAoConectar(aoConectar());
        banco.botaAoDesconectar(aoDesconectar());
        banco.botaAoExecutar(aoExecutar());
        banco.botaAoProcurar(aoProcurar());
        retorno = banco.conecta();
        if (retorno) {
            botaLink(banco.link());
            if (baseUsuario != null) {
                Conjunto rus = busca("SELECT usuarios.codigo, usuarios.super, usuarios.pessoa, pessoas.nome "
                        + " FROM usuarios "
                        + "   LEFT JOIN pessoas "
                        + "   ON pessoas.codigo = usuarios.pessoa "
                        + " WHERE usuario = ? AND senha = ?",
                        new Vlrs(baseUsuario, baseUsuarioSenha),
                        Arrays.asList(Dados.Tp.Crs, Dados.Tp.Log, Dados.Tp.Crs, Dados.Tp.Crs));

                if (rus.proximo()) {
                    baseUsuarioCodigo = rus.pgVlr(1).pgCrs();
                    baseUsuarioSuper = rus.pgVlr(2, false).pgLog();
                    baseUsuarioPessoa = rus.pgVlr(3).pgCrs();
                    baseUsuarioNome = rus.pgVlr(4).pgCrs();
                } else {
                    desconecta();
                    throw new Exception("Não Foi Possível Conectar Ao Banco De Dados\n"
                            + "Usuário e Senha NÃO Encontrados.");
                }
            }
            if (aoConectar() != null) {
                retorno = aoConectar().definido(this, BancoAoDefinir.Tp.Conectar);
                if (!retorno) {
                    desconecta();
                }
            }
        }
        deveriaConectar = retorno;
        return retorno;
    }

    public Boolean conectaBase() throws Exception {
        return conectaBase(null, false);
    }

    public Boolean conectaBase(JFrame comJanelaAssociada, Boolean eFecharJanelaSeCancelar) throws Exception {
        janelaAssociada = comJanelaAssociada;
        fecharJanelaSeCancelar = eFecharJanelaSeCancelar;

        if (!estaConectado()) {
            ConectaBase conDlg = new ConectaBase(janelaAssociada, this);
            conDlg.mostra();
            return conDlg.foiConectado();
        }
        return true;
    }

    public void deveConectar(JFrame paraJanela) {
        deveConectar(paraJanela, null);
    }

    public void deveConectar(JFrame paraJanela, AbstractAction aoConfirmar) {
        paraJanela.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                try {
                    if (conectaBase(paraJanela, true)) {
                        if (aoConfirmar != null) {
                            aoConfirmar.actionPerformed(null);
                        }
                    }
                } catch (Exception ex) {
                    Utl.registra(ex);
                    Janelas.fecha(paraJanela);
                }
            }
        });
    }

    public Banco banco() {
        return banco;
    }

    public Conexao botaBanco(Banco banco) {
        this.banco = banco;
        return this;
    }

    public String pegaBaseUsuario() {
        return baseUsuario;
    }

    public Conexao botaBaseUsuario(String baseUsuario) {
        this.baseUsuario = baseUsuario;
        return this;
    }

    public String pegaBaseUsuarioSenha() {
        return baseUsuarioSenha;
    }

    public Conexao botaBaseUsuarioSenha(String baseUsuarioSenha) {
        this.baseUsuarioSenha = baseUsuarioSenha;
        return this;
    }

    public String pegaBaseUsuarioCodigo() {
        return baseUsuarioCodigo;
    }

    public Conexao botaBaseUsuarioCodigo(String baseUsuarioCodigo) {
        this.baseUsuarioCodigo = baseUsuarioCodigo;
        return this;
    }

    public Boolean pegaBaseUsuarioSuper() {
        return baseUsuarioSuper;
    }

    public Conexao botaBaseUsuarioSuper(Boolean baseUsuarioSuper) {
        this.baseUsuarioSuper = baseUsuarioSuper;
        return this;
    }

    public String pegaBaseUsuarioPessoa() {
        return baseUsuarioPessoa;
    }

    public Conexao botaBaseUsuarioPessoa(String baseUsuarioPessoa) {
        this.baseUsuarioPessoa = baseUsuarioPessoa;
        return this;
    }

    public String pegaBaseUsuarioNome() {
        return baseUsuarioNome;
    }

    public Conexao botaBaseUsuarioNome(String baseUsuarioNome) {
        this.baseUsuarioNome = baseUsuarioNome;
        return this;
    }

    public Configs cfgs() {
        return cfgs;
    }

    public Conexao botaCfgs(Configs cfgs) {
        this.cfgs = cfgs;
        return this;
    }

    public ConexaoCfgs conCfgs() {
        return conCfgs;
    }

    public Conexao botaConCfgs(ConexaoCfgs conCfgs) {
        this.conCfgs = conCfgs;
        return this;
    }

    public JFrame janelaAssociada() {
        return janelaAssociada;
    }

    public Conexao botaJanelaAssociada(JFrame janelaAssociada) {
        this.janelaAssociada = janelaAssociada;
        return this;
    }

    public Boolean paraFecharJanelaSeCancelar() {
        return fecharJanelaSeCancelar;
    }

    public Conexao botaFecharJanelaSeCancelar(Boolean fecharJanelaSeCancelar) {
        this.fecharJanelaSeCancelar = fecharJanelaSeCancelar;
        return this;
    }

    public Boolean temAuditoria() {
        return auditoria;
    }

    public Conexao mudaAuditoria(Boolean para) {
//        this.auditoria = para;
        return this;
    }

    public Conexao botaAuditoria() {
//        auditoria = true;
        return this;
    }

    public Conexao tiraAuditoria() {
//        auditoria = false;
        return this;
    }

    public Boolean ehPrincipal() {
        return principal;
    }

    public Conexao botaPrincipal(Boolean principal) {
        this.principal = principal;
        return this;
    }

    public Conexao botaPrincipal() {
        principal = true;
        return this;
    }

    public Conexao tiraPrincipal() {
        principal = false;
        return this;
    }

    public Conexao criaMantenedor() {
        new Mantenedor().start();
        return this;
    }

    @Override
    public BancoTabelas bancoTabelas() throws Exception {
        return banco.bancoTabelas();
    }

    @Override
    public void carregaTabelas(Tabelas nasTabelas) throws Exception {
        banco.carregaTabelas(nasTabelas);
    }

    @Override
    public void carregaCampos(Tabela daTabela) throws Exception {
        banco.carregaCampos(daTabela);
    }

    @Override
    public void carregaEstrangeiros(Tabela daTabela) throws Exception {
        banco.carregaEstrangeiros(daTabela);
    }

    @Override
    public void carregaIndices(Tabela daTabela) throws Exception {
        banco.carregaIndices(daTabela);
    }

    @Override
    public void carregaVerificadores(Tabela daTabela) throws Exception {
        banco.carregaVerificadores(daTabela);
    }

    @Override
    public String pCriacao(Cmp doCampo) {
        return banco.pCriacao(doCampo);
    }

    @Override
    public List<String> pUsuarios() throws Exception {
        return banco.pUsuarios();
    }

    public List<String> pegaBaseUsuarios() throws Exception {
        List<String> retorno = new ArrayList<>();
        Conjunto rst = busca("SELECT usuario FROM usuarios");
        while (rst.proximo()) {
            retorno.add(rst.pgCol("").pgCrs());
        }
        return retorno;
    }

    public void alterarSenha() {
        try {
            new ConexaoSenha(this).mostra();
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }

    @Override
    public Conexao replica() throws Exception {
        Conexao retorno = new Conexao(pegaNome() + " Replicado", pegaTipo(), pegaServidor(), pegaPorta(), pegaBase(), pegaUsuario(), pegaSenha(), baseUsuario, baseUsuarioSenha);
        retorno.mudaAuditoria(auditoria);
        retorno.botaAoConectar(aoConectar());
        retorno.botaAoDesconectar(aoDesconectar());
        retorno.botaAoExecutar(aoExecutar());
        retorno.botaAoProcurar(aoProcurar());
        retorno.conecta();
        return retorno;
    }

    @Override
    public Conexao replicaLeve() throws Exception {
        Conexao retorno = new Conexao(pegaNome() + " Replicado", pegaTipo(), pegaServidor(), pegaPorta(), pegaBase(), pegaUsuario(), pegaSenha(), baseUsuario, baseUsuarioSenha);
        retorno.tiraAuditoria();
        retorno.conecta();
        return retorno;
    }

    @Override
    public Conexao iniciaTransacao() throws Exception {
        Conexao retorno = new Conexao(pegaNome() + " Transação", pegaTipo(), pegaServidor(), pegaPorta(), pegaBase(), pegaUsuario(), pegaSenha(), baseUsuario, baseUsuarioSenha);
        retorno.mudaAuditoria(auditoria);
        retorno.botaBanco(banco.iniciaTransacao());
        retorno.botaLink(retorno.banco().link());
        retorno.botaBaseUsuarioCodigo(pegaBaseUsuarioCodigo());
        retorno.botaAoConectar(aoConectar());
        retorno.botaAoDesconectar(aoDesconectar());
        retorno.botaAoExecutar(aoExecutar());
        retorno.botaAoProcurar(aoProcurar());
        return retorno;
    }

    @Override
    public void salvaTransacao() throws Exception {
        tiraAuditoria();
        super.salvaTransacao();
    }

    @Override
    public void salvaTransacaoEFecha() throws Exception {
        tiraAuditoria();
        super.salvaTransacaoEFecha();
    }

    @Override
    public void cancelaTransacao() throws Exception {
        tiraAuditoria();
        super.cancelaTransacao();
    }

    @Override
    public void cancelaTransacaoEFecha() throws Exception {
        tiraAuditoria();
        super.cancelaTransacaoEFecha();
    }

    @Override
    public Boolean operaLog(String oComando) throws Exception {
        Boolean retorno = super.operaLog(oComando);
        if (auditoria) {
            new Thread("Auditoria") {
                @Override
                public void run() {
                    try {
                        PreparedStatement pstm = link().prepareStatement("INSERT INTO auditoria (usuario, comando, parametros, retorno) VALUES (?, ?, NULL, ?)");
                        Dados.botaParametros(pstm, new Vlrs(baseUsuarioCodigo, new VTex(oComando), retorno ? 1 : 0));
                        pstm.executeUpdate();
                    } catch (Exception ex) {
                        Utl.registra(ex, false);
                    }
                }
            }.start();
        }
        return retorno;
    }

    @Override
    public Integer opera(String oComando) throws Exception {
        Integer retorno = super.opera(oComando);
        if (auditoria) {
            new Thread("Auditoria") {
                @Override
                public void run() {
                    try {
                        PreparedStatement pstm = link().prepareStatement("INSERT INTO auditoria (usuario, comando, parametros, retorno) VALUES (?, ?, NULL, ?)");
                        Dados.botaParametros(pstm, new Vlrs(baseUsuarioCodigo, new VTex(oComando), retorno));
                        pstm.executeUpdate();
                    } catch (Exception ex) {
                        Utl.registra(ex, false);
                    }
                }
            }.start();
        }
        return retorno;
    }

    @Override
    public Integer opera(String oComando, Vlrs comParametros) throws Exception {
        Integer retorno = super.opera(oComando, comParametros);
        if (auditoria) {
            new Thread("Auditoria") {
                @Override
                public void run() {
                    try {
                        PreparedStatement pstm = link().prepareStatement("INSERT INTO auditoria (usuario, comando, parametros, retorno) VALUES (?, ?, ?, ?)");
                        Dados.botaParametros(pstm, new Vlrs(baseUsuarioCodigo, new VTex(oComando), new VTex(comParametros.toString()), retorno));
                        pstm.executeUpdate();
                    } catch (Exception ex) {
                        Utl.registra(ex, false);
                    }
                }
            }.start();
        }
        return retorno;
    }

    @Override
    public Conjunto busca(String comSelecao) throws Exception {
        Conjunto retorno = super.busca(comSelecao);
        if (auditoria) {
            new Thread("Auditoria") {
                @Override
                public void run() {
                    try {
                        PreparedStatement pstm = link().prepareStatement("INSERT INTO auditoria (usuario, comando, parametros, retorno) VALUES (?, ?, NULL, ?)");
                        Dados.botaParametros(pstm, new Vlrs(baseUsuarioCodigo, new VTex(comSelecao), retorno.tamanho()));
                        pstm.executeUpdate();
                    } catch (Exception ex) {
                        Utl.registra(ex, false);
                    }
                }
            }.start();
        }
        return retorno;
    }

    @Override
    public Conjunto busca(String comSelecao, List<Dados.Tp> eTipos) throws Exception {
        Conjunto retorno = super.busca(comSelecao, eTipos);
        if (auditoria) {
            new Thread("Auditoria") {
                @Override
                public void run() {
                    try {
                        PreparedStatement pstm = link().prepareStatement("INSERT INTO auditoria (usuario, comando, parametros, retorno) VALUES (?, ?, NULL, ?)");
                        Dados.botaParametros(pstm, new Vlrs(baseUsuarioCodigo, new VTex(comSelecao), retorno.tamanho()));
                        pstm.executeUpdate();
                    } catch (Exception ex) {
                        Utl.registra(ex, false);
                    }
                }
            }.start();
        }
        return retorno;
    }

    @Override
    public Conjunto busca(String comSelecao, Vlrs eParametros) throws Exception {
        Conjunto retorno = super.busca(comSelecao, eParametros);
        if (auditoria) {
            new Thread("Auditoria") {
                @Override
                public void run() {
                    try {
                        PreparedStatement pstm = link().prepareStatement("INSERT INTO auditoria (usuario, comando, parametros, retorno) VALUES (?, ?, ?, ?)");
                        Dados.botaParametros(pstm, new Vlrs(baseUsuarioCodigo, new VTex(comSelecao), new VTex(eParametros), retorno.tamanho()));
                        pstm.executeUpdate();
                    } catch (Exception ex) {
                        Utl.registra(ex, false);
                    }
                }
            }.start();
        }

        return retorno;
    }

    @Override
    public Conjunto busca(String comSelecao, Vlrs eParametros, List<Dados.Tp> eTipos) throws Exception {
        Conjunto retorno = super.busca(comSelecao, eParametros, eTipos);
        if (auditoria) {
            new Thread("Auditoria") {
                @Override
                public void run() {
                    try {
                        PreparedStatement pstm = link().prepareStatement("INSERT INTO auditoria (usuario, comando, parametros, retorno) VALUES (?, ?, ?, ?)");
                        Dados.botaParametros(pstm, new Vlrs(baseUsuarioCodigo, new VTex(comSelecao), new VTex(eParametros), retorno.tamanho()));
                        pstm.executeUpdate();
                    } catch (Exception ex) {
                        Utl.registra(ex, false);
                    }
                }
            }.start();
        }
        return retorno;
    }

    private class Mantenedor extends Thread {

        public Mantenedor() {
            super("Conexão Mantenedor");
        }

        @Override
        public void run() {
            try {
                UtlBin.esperaSegundos(30);
                while (!fechar) {
                    try {
                        UtlBin.esperaSegundos(UtlAle.pegaInt(10, 30));
                        if (deveriaConectar) {
                            if (!estaConectado()) {
                                conecta();
                            }
                        }
                    } catch (Exception ex) {
                        Utl.registra(ex, false);
                    }
                }
            } catch (Exception ex) {
                Utl.registra(ex, false);
            }

        }
    }
}
