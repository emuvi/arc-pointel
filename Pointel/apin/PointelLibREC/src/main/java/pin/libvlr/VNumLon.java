package pin.libvlr;

import pin.libutl.UtlNumLon;

public class VNumLon extends Vlr {

    private volatile Double valor;

    public VNumLon() {
        valor = null;
    }

    public VNumLon(Object doValor) {
        valor = UtlNumLon.pega(doValor);
    }

    public static VNumLon descrito(String nosCaracteres) {
        return new VNumLon(nosCaracteres);
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
    public VNumLon md(Object oValor) {
        valor = UtlNumLon.pega(oValor);
        return this;
    }

    @Override
    public String paraConsSQL() {
        return UtlNumLon.formataSQL(valor);
    }

    @Override
    public Object paraParamSQL() {
        return UtlNumLon.pegaSQL(valor);
    }

    @Override
    public String toString() {
        return UtlNumLon.formata(valor);
    }

    @Override
    public String descreve() {
        return UtlNumLon.formata(valor);
    }

}
