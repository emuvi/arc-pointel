package pin.libvlr;

import java.util.Date;
import pin.libutl.UtlDat;

public class VDat extends Vlr {

    private volatile Date valor;

    public VDat() {
        valor = null;
    }

    public VDat(Object doValor) {
        valor = UtlDat.pega(doValor);
    }

    public static VDat descrito(String nosCaracteres) {
        return new VDat(nosCaracteres);
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
    public VDat md(Object oValor) {
        valor = UtlDat.pega(oValor);
        return this;
    }

    @Override
    public String paraConsSQL() {
        return UtlDat.formataSQL(valor);
    }

    @Override
    public Object paraParamSQL() {
        return UtlDat.pegaSQL(valor);
    }

    @Override
    public String toString() {
        return UtlDat.formata(valor);
    }

    @Override
    public String descreve() {
        return UtlDat.formata(valor);
    }

}
