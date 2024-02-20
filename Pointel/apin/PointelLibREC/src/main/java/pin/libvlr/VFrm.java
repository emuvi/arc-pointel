package pin.libvlr;

import java.util.ArrayList;
import java.util.List;
import pin.libutl.UtlLis;

public class VFrm extends Vlr {

    private volatile List<Object> valor;

    public VFrm() {
        valor = new ArrayList<>();
    }

    public VFrm(Object doValor) {
        valor = UtlLis.pega(doValor);
    }

    public static VFrm descrito(String nosCaracteres) {
        return new VFrm(UtlLis.descrito(nosCaracteres));
    }

    @Override
    public Object pg(Object comPadrao) {
        if (valor != null) {
            return valor;
        }
        return comPadrao;
    }

    @Override
    public VFrm md(Object oValor) {
        if (oValor == null) {
            novo();
        } else {
            valor = UtlLis.pega(oValor);
        }
        return this;
    }

    @Override
    public Boolean vazio() {
        if (valor == null) {
            return true;
        }
        return valor.isEmpty();
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
        return UtlLis.formata(valor);
    }

    @Override
    public String descreve() {
        return UtlLis.descreve(valor);
    }

    public VFrm novo() {
        valor = new ArrayList<>();
        return this;
    }

    public VFrm anular() {
        valor = null;
        return this;
    }

}
