package pin.libvlr;

import java.util.Date;
import pin.libutl.UtlHor;

public class VHor extends Vlr {

    private volatile Date valor;

    public VHor() {
        valor = null;
    }

    public VHor(Object doValor) {
        valor = UtlHor.pega(doValor);
    }

    public static VHor descrito(String nosCaracteres) {
        return new VHor(nosCaracteres);
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
    public VHor md(Object oValor) {
        valor = UtlHor.pega(oValor);
        return this;
    }

    @Override
    public String paraConsSQL() {
        return UtlHor.formataSQL(valor);
    }

    @Override
    public Object paraParamSQL() {
        return UtlHor.pegaSQL(valor);
    }

    @Override
    public String toString() {
        return UtlHor.formata(valor);
    }

    @Override
    public String descreve() {
        return UtlHor.formata(valor);
    }

}
