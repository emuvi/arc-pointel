package pin.libvlr;

import pin.libutl.UtlCr;

public class VCr extends Vlr {

    private volatile Character valor;

    public VCr() {
        valor = null;
    }

    public VCr(Object doValor) {
        valor = UtlCr.pega(doValor);
    }

    public static VCr descrito(String nosCaracteres) {
        return new VCr(nosCaracteres);
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
    public VCr md(Object oValor) {
        valor = UtlCr.pega(oValor);
        return this;
    }

    @Override
    public String paraConsSQL() {
        return UtlCr.formataSQL(valor);
    }

    @Override
    public Object paraParamSQL() {
        return UtlCr.pegaSQL(valor);
    }

    @Override
    public String toString() {
        return UtlCr.formata(valor);
    }

    @Override
    public String descreve() {
        return UtlCr.formata(valor);
    }

}
