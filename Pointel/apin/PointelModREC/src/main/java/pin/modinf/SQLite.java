package pin.modinf;

import java.io.File;
import java.sql.DriverManager;
import java.util.List;
import pin.libamk.Cmp;

public class SQLite extends Banco {

    public SQLite() {
        this("Sem Nome", (String) null);
    }

    public SQLite(File noArquivo) {
        this("Sem Nome", noArquivo.getAbsolutePath());
    }

    public SQLite(String comNome, File noArquivo) {
        this(comNome, noArquivo.getAbsolutePath());
    }

    public SQLite(String noArquivo) {
        this("Sem Nome", noArquivo);
    }

    public SQLite(String comNome, String noArquivo) {
        super(comNome, Bancos.Tp.SQLite, null, null, noArquivo, null, null);
    }

    public void configura(String noArquivo) {
        desconecta();
        botaBase(noArquivo);
    }

    @Override
    public Boolean conecta() throws Exception {
        Class.forName("org.sqlite.JDBC");
        botaLink(DriverManager.getConnection("jdbc:sqlite:" + pegaBase()));
        link().setAutoCommit(true);
        return true;
    }

    @Override
    public void carregaTabelas(Tabelas nasTabelas) throws Exception {

    }

    @Override
    public void carregaCampos(Tabela daTabela) throws Exception {

    }

    @Override
    public void carregaEstrangeiros(Tabela daTabela) throws Exception {

    }

    @Override
    public void carregaIndices(Tabela daTabela) throws Exception {

    }

    @Override
    public void carregaVerificadores(Tabela daTabela) throws Exception {

    }

    public String pCriacao(Cmp doCampo) {
        return null;
    }

    @Override
    public List<String> pUsuarios() throws Exception {
        return null;
    }

    @Override
    public SQLite replica() throws Exception {
        SQLite retorno = new SQLite(pegaNome() + " Replicada", pegaBase());
        retorno.botaAoConectar(aoConectar());
        retorno.botaAoDesconectar(aoDesconectar());
        retorno.botaAoExecutar(aoExecutar());
        retorno.botaAoProcurar(aoProcurar());
        retorno.conecta();
        return retorno;
    }

    @Override
    public SQLite replicaLeve() throws Exception {
        SQLite retorno = new SQLite(pegaNome() + " Replicada", pegaBase());
        retorno.conecta();
        return retorno;
    }

    @Override
    public SQLite iniciaTransacao() throws Exception {
        SQLite retorno = new SQLite(pegaNome() + " Transação", pegaBase());
        retorno.botaAoConectar(aoConectar());
        retorno.botaAoDesconectar(aoDesconectar());
        retorno.botaAoExecutar(aoExecutar());
        retorno.botaAoProcurar(aoProcurar());
        retorno.conecta();
        retorno.link().setAutoCommit(false);
        retorno.opera("BEGIN");
        return retorno;
    }

}
