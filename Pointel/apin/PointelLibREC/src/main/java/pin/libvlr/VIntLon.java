package pin.libvlr;

import pin.libutl.UtlIntLon;

public class VIntLon extends Vlr {

    private volatile Long valor;

    public VIntLon() {
        valor = null;
    }

    public VIntLon(Object doValor) {
        valor = UtlIntLon.pega(doValor);
    }

    public static VIntLon descrito(String nosCaracteres) {
        return new VIntLon(nosCaracteres);
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
    public VIntLon md(Object oValor) {
        valor = UtlIntLon.pega(oValor);
        return this;
    }

    @Override
    public String paraConsSQL() {
        return UtlIntLon.formataSQL(valor);
    }

    @Override
    public Object paraParamSQL() {
        return UtlIntLon.pegaSQL(valor);
    }

    @Override
    public String toString() {
        return UtlIntLon.formata(valor);
    }

    @Override
    public String descreve() {
        return UtlIntLon.formata(valor);
    }

}
