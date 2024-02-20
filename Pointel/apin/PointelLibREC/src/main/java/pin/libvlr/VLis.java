package pin.libvlr;

import java.util.ArrayList;
import java.util.List;
import pin.libutl.UtlLis;

public class VLis extends Vlr {

    private volatile List<Object> valor;

    public VLis() {
        valor = new ArrayList<>();
    }

    public VLis(Object doValor) {
        valor = UtlLis.pega(doValor);
    }

    public static VLis descrito(String nosCaracteres) {
        return new VLis().bota(UtlLis.descrito(nosCaracteres));
    }

    @Override
    public Object pg(Object comPadrao) {
        if (valor != null) {
            if (!valor.isEmpty()) {
                return valor;
            }
        }
        return comPadrao;
    }

    @Override
    public VLis md(Object oValor) {
        valor = UtlLis.pega(oValor);
        return this;
    }

    @Override
    public Boolean vazio() {
        if (valor == null) {
            return true;
        }
        return valor.isEmpty();
    }

    public VLis bota(List<Object> oValor) {
        valor = oValor;
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
        return UtlLis.formata(valor);
    }

    @Override
    public String descreve() {
        return UtlLis.descreve(valor);
    }

    public VLis novo() {
        valor = new ArrayList<>();
        return this;
    }

    public VLis anular() {
        valor = null;
        return this;
    }
}
