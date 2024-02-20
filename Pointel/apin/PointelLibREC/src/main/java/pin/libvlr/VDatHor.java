package pin.libvlr;

import java.util.Date;
import pin.libutl.UtlDatHor;

public class VDatHor extends Vlr {

    private volatile Date valor;

    public VDatHor() {
        valor = null;
    }

    public VDatHor(Object doValor) {
        valor = UtlDatHor.pega(doValor);
    }

    public static VDatHor descrito(String nosCaracteres) {
        return new VDatHor(nosCaracteres);
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
    public VDatHor md(Object oValor) {
        valor = UtlDatHor.pega(oValor);
        return this;
    }

    @Override
    public String paraConsSQL() {
        return UtlDatHor.formataSQL(valor);
    }

    @Override
    public Object paraParamSQL() {
        return UtlDatHor.pegaSQL(valor);
    }

    @Override
    public String toString() {
        return UtlDatHor.formata(valor);
    }

    @Override
    public String descreve() {
        return UtlDatHor.formata(valor);
    }

}
