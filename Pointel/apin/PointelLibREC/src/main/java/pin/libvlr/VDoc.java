package pin.libvlr;

import pin.libutl.UtlCrs;

public class VDoc extends Vlr {

    private volatile String valor;

    public VDoc() {
        valor = null;
    }

    public VDoc(Object doValor) {
        valor = UtlCrs.pega(doValor);
    }

    public static VDoc descrito(String nosCaracteres) {
        return new VDoc(nosCaracteres);
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
    public VDoc md(Object oValor) {
        valor = UtlCrs.pega(oValor);
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
        return valor; //Talvez fazer um strip das marcações html
    }

    @Override
    public String descreve() {
        return valor;
    }

}
