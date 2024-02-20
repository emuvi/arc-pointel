package pin.libbas;

public class Operacao {

    public String tipo;
    public String nome;
    public String codigo;

    public Operacao() {
        tipo = "";
        nome = "";
        codigo = "";
    }

    public Operacao(String tipo) {
        this.tipo = tipo;
        this.nome = "";
        this.codigo = "";
    }

    public Operacao(String tipo, String nome) {
        this.tipo = tipo;
        this.nome = nome;
        this.codigo = "";
    }

    public Operacao(String tipo, String nome, String codigo) {
        this.tipo = tipo;
        this.nome = nome;
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return Partes.junta(tipo, nome, codigo);
    }
}
