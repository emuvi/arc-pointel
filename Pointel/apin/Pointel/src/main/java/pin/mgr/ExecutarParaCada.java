package pin.mgr;

public class ExecutarParaCada {

    public String conexaoSelecao;
    public String conexaoComandos;
    public Boolean pausarSeErrar;
    public String selecao;
    public String comandos;

    public ExecutarParaCada() {
        this.conexaoSelecao = "";
        this.conexaoComandos = "";
        this.pausarSeErrar = true;
        this.selecao = "";
        this.comandos = "";
    }

    public ExecutarParaCada(String conexaoSelecao, String conexaoComandos, Boolean pararSeErrar, String selecao, String comandos) {
        this.conexaoSelecao = conexaoSelecao;
        this.conexaoComandos = conexaoComandos;
        this.pausarSeErrar = pararSeErrar;
        this.selecao = selecao;
        this.comandos = comandos;
    }

    public static String pTipo() {
        return "Executar Para Cada";
    }
}
