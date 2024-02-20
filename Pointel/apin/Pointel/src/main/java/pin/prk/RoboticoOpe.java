package pin.prk;

public class RoboticoOpe {

    public static enum Tp {
        Mouse, Teclado, Roteiro, Comando, Espera, Condicao
    };

    public String nome;
    public Tp tipo;
    public String fonte;
    public String comentario;

    public RoboticoOpe() {
        nome = null;
        tipo = null;
        fonte = null;
        comentario = null;
    }

    @Override
    public String toString() {
        return nome + " - " + (tipo == null ? "null" : tipo.toString()) + " - " + comentario;
    }

}
