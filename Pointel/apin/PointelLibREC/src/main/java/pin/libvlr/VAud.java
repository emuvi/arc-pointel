package pin.libvlr;

import pin.libutl.UtlArq;

public class VAud extends Vlr {

    private volatile byte[] valor;

    public VAud() {
        valor = null;
    }

    public VAud(Object doValor) {
        valor = UtlArq.pega(doValor);
    }

    public static VAud descrito(String nosCaracteres) {
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
    public VAud md(Object oValor) {
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
