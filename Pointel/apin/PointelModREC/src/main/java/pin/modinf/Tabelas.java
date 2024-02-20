package pin.modinf;

public abstract class Tabelas {

    public abstract Tabelas bota(Tabela aTabela);

    public abstract Tabela pega(int doIndice) throws Exception;

    public abstract Tabela pega(String noEsquema, String comNome) throws Exception;

    public abstract Tabela pega(String comEsquemaENome) throws Exception;

    public abstract Boolean tem(String noEsquema, String comNome) throws Exception;

    public abstract Boolean tem(String comEsquemaENome) throws Exception;

    public abstract Integer tamanho();

    public abstract void limpa();
}
