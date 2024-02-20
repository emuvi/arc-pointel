package pin.libvlr;

import pin.libutl.UtlLog;

public class VLog extends Vlr {

    private volatile Boolean valor;

    public VLog() {
        valor = null;
    }

    public VLog(Object doValor) {
        valor = UtlLog.pega(doValor);
    }

    public static VLog descrito(String nosCaracteres) {
        return new VLog(nosCaracteres);
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
    public VLog md(Object oValor) {
        valor = UtlLog.pega(oValor);
        return this;
    }

    @Override
    public String paraConsSQL() {
        return UtlLog.formataSQL(valor);
    }

    @Override
    public Object paraParamSQL() {
        return UtlLog.pegaSQL(valor);
    }

    @Override
    public String toString() {
        return UtlLog.formata(valor);
    }

    @Override
    public String descreve() {
        return UtlLog.formata(valor);
    }

}
