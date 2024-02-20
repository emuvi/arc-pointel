package pin.jrb;

import pin.modinf.SQLite;

public class Entendidos {

    private SQLite banco;

    public Entendidos(String noArquivo) throws Exception {
        super();
        banco = new SQLite("Entendidos", noArquivo + ".etd");
        banco.conecta();
        banco.opera("CREATE TABLE IF NOT EXISTS entendidos (falado TEXT, acao TEXT)");
    }

    public void fecha() {
        banco.desconecta();
    }

}
