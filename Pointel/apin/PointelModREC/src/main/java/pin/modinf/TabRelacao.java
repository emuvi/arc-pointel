package pin.modinf;

public class TabRelacao {

    public String referenciado;
    public String referencia;

    public TabRelacao(String oReferenciado, String naReferencia) {
        this.referenciado = oReferenciado;
        this.referencia = naReferencia;
    }

    @Override
    public String toString() {
        return referenciado + " > " + referencia;
    }

}
