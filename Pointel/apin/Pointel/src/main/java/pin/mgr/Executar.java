package pin.mgr;

public class Executar {

    public String conexao;
    public Boolean pausarSeErrar;
    public String comandos;

    public Executar() {
        this.conexao = "";
        this.pausarSeErrar = true;
        this.comandos = "";
    }

    public Executar(String conexao, Boolean pausarSeErrar, String comandos) {
        this.conexao = conexao;
        this.pausarSeErrar = pausarSeErrar;
        this.comandos = comandos;
    }

    public static String pTipo() {
        return "Executar";
    }
}
