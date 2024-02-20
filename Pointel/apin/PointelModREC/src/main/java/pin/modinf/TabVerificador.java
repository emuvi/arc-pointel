package pin.modinf;

public class TabVerificador {

    private String nome;
    private String codigo;

    public TabVerificador(String nome) {
        this.nome = nome;
        this.codigo = null;
    }

    public TabVerificador(String nome, String codigo) {
        this.nome = nome;
        this.codigo = codigo;
    }

    public String pNome() {
        return nome;
    }

    public TabVerificador mNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String pCodigo() {
        return codigo;
    }

    public TabVerificador mCodigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    @Override
    public String toString() {
        return nome + " [ " + codigo + " ]";
    }

    public String pDescricao() {
        return "Verificador " + toString();
    }

}
