package pin.libvlr;

import pin.libutl.UtlInt;

public class VInt extends Vlr {

    private volatile Integer valor;

    public VInt() {
        valor = null;
    }

    public VInt(Object doValor) {
        valor = UtlInt.pega(doValor);
    }

    public static VInt descrito(String nosCaracteres) {
        return new VInt(nosCaracteres);
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
    public VInt md(Object oValor) {
        valor = UtlInt.pega(oValor);
        return this;
    }

    @Override
    public String paraConsSQL() {
        return UtlInt.formataSQL(valor);
    }

    @Override
    public Object paraParamSQL() {
        return UtlInt.pegaSQL(valor);
    }

    @Override
    public String toString() {
        return UtlInt.formata(valor);
    }

    @Override
    public String descreve() {
        return UtlInt.formata(valor);
    }

}
