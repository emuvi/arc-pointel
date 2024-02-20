package pin.libvlr;

import java.util.Date;
import pin.libutl.UtlMom;

public class VMom extends Vlr {

    private volatile Date valor;

    public VMom() {
        valor = null;
    }

    public VMom(Object doValor) {
        valor = UtlMom.pega(doValor);
    }

    public static VMom descrito(String nosCaracteres) {
        return new VMom(nosCaracteres);
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
    public VMom md(Object oValor) {
        valor = UtlMom.pega(oValor);
        return this;
    }

    @Override
    public String paraConsSQL() {
        return UtlMom.formataSQL(valor);
    }

    @Override
    public Object paraParamSQL() {
        return UtlMom.pegaSQL(valor);
    }

    @Override
    public String toString() {
        return UtlMom.formata(valor);
    }

    @Override
    public String descreve() {
        return UtlMom.formata(valor);
    }

}
