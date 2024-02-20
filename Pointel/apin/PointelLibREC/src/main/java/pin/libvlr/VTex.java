package pin.libvlr;

import pin.libutl.UtlCrs;
import pin.libutl.UtlTex;

public class VTex extends Vlr {

    private volatile String valor;

    public VTex() {
        valor = null;
    }

    public VTex(Object doValor) {
        valor = UtlCrs.pega(doValor);
    }

    public static VTex descrito(String nosCaracteres) {
        return new VTex(nosCaracteres);
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
    public VTex md(Object oValor) {
        valor = UtlCrs.pega(oValor);
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
        return valor;
    }

    @Override
    public String descreve() {
        return valor;
    }

    public VTex soma(String... asLinhas) {
        valor = UtlTex.soma(valor, asLinhas);
        return this;
    }

}
