package pin.libvlr;

import pin.libbas.Conjunto;
import pin.libutl.UtlPla;

public class VPla extends Vlr {

    private volatile Conjunto valor;

    public VPla() {
        valor = null;
    }

    public VPla(Object doValor) {
        valor = UtlPla.pega(doValor);
    }

    public static VPla descrito(String nosCaracteres) throws Exception {
        return new VPla().md(UtlPla.descrito(nosCaracteres));
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
    public VPla md(Object oValor) {
        valor = UtlPla.pega(oValor);
        return this;
    }

    @Override
    public String paraConsSQL() {
        return descreve();
    }

    @Override
    public Object paraParamSQL() {
        return descreve();
    }

    @Override
    public String toString() {
        return UtlPla.formata(valor);
    }

    @Override
    public String descreve() {
        return UtlPla.descreve(valor);
    }

}
