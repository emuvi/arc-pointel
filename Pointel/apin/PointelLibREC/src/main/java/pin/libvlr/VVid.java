package pin.libvlr;

import pin.libutl.UtlArq;

public class VVid extends Vlr {

    private volatile byte[] valor;

    public VVid() {
        valor = null;
    }

    public VVid(Object doValor) {
        valor = UtlArq.pega(doValor);
    }

    public static VVid descrito(String nosCaracteres) {
        return null;
    }

    @Override
    public Object pg(Object comPadrao) {
        if (valor != null) {
            return valor;
        } else {
            return comPadrao;
        }
    }

    @Override
    public VVid md(Object oValor) {
        valor = UtlArq.pega(oValor);
        return this;
    }

    @Override
    public String paraConsSQL() {
        return descreve();
    }

    @Override
    public Object paraParamSQL() {
        return descreve();
    }

    @Override
    public String toString() {
        return UtlArq.formataB64(valor);
    }

    @Override
    public String descreve() {
        return null;
    }

}
