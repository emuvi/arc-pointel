package pin.libvlr;

import java.util.ArrayList;
import java.util.List;
import pin.libutl.UtlBin;
import pin.libutl.UtlCrs;
import pin.libutl.UtlLis;

public class VEsc extends Vlr {

    private volatile Boolean ehLista;
    private volatile List<Object> lista;
    private volatile Object simples;

    public VEsc() {
        ehLista = false;
        lista = null;
        simples = null;
    }

    public VEsc(Object doValor) {
        processa(doValor);
    }

    public static VEsc descrito(String nosCaracteres) {
        return new VEsc(nosCaracteres);
    }

    private void processa(Object oValor) {
        lista = null;
        simples = null;
        if (UtlLis.eh(oValor)) {
            ehLista = true;
            lista = UtlLis.pega(oValor);
        } else if (oValor instanceof String) {
            String dosCaracteres = (String) oValor;
            if (UtlLis.eh(dosCaracteres)) {
                ehLista = true;
                lista = UtlLis.descrito(dosCaracteres);
            } else {
                ehLista = false;
                simples = dosCaracteres;
            }
        } else {
            ehLista = false;
            simples = oValor;
        }
    }

    @Override
    public Object pg(Object comPadrao) {
        if (ehLista) {
            if (lista != null) {
                return lista;
            } else {
                return comPadrao;
            }
        } else {
            if (simples != null) {
                return simples;
            } else {
                return comPadrao;
            }
        }
    }

    @Override
    public VEsc md(Object oValor) {
        processa(oValor);
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
        if (ehLista) {
            return UtlLis.formata(lista);
        } else {
            return UtlCrs.pega(simples);
        }
    }

    @Override
    public String descreve() {
        if (ehLista) {
            return UtlLis.descreve(lista);
        } else {
            return UtlBin.descreve(simples);
        }
    }

    public Object[] pgMat() {
        if (lista != null) {
            return UtlLis.pegaMat(lista);
        } else if (simples != null) {
            return new Object[]{simples};
        } else {
            return null;
        }
    }

    public String[] pgMatCrs() {
        if (lista != null) {
            return UtlLis.pegaMatCrs(lista);
        } else if (simples != null) {
            return new String[]{UtlCrs.pega(simples)};
        } else {
            return null;
        }
    }

    public VEsc novo() {
        lista = new ArrayList<>();
        return this;
    }

}
