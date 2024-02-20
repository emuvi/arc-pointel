package pin.libvlr;

import pin.libutl.UtlNum;

public class VNum extends Vlr {

    private volatile Float valor;

    public VNum() {
        valor = null;
    }

    public VNum(Object doValor) {
        valor = UtlNum.pega(doValor);
    }

    public static VNum descrito(String nosCaracteres) {
        return new VNum(nosCaracteres);
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
    public VNum md(Object oValor) {
        valor = UtlNum.pega(oValor);
        return this;
    }

    @Override
    public String paraConsSQL() {
        return UtlNum.formataSQL(valor);
    }

    @Override
    public Object paraParamSQL() {
        return UtlNum.pegaSQL(valor);
    }

    @Override
    public String toString() {
        return UtlNum.formata(valor);
    }

    @Override
    public String descreve() {
        return UtlNum.formata(valor);
    }

}
