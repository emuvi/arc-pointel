package pin.libvlr;

import java.util.ArrayList;
import java.util.List;
import pin.libutl.UtlLis;

public class VApr extends Vlr {

    private volatile List<VFrm> valor;

    public VApr() {
        valor = new ArrayList<>();
    }

    public VApr(Object doValor) {
        md(doValor);
    }

    public static VApr descrito(String nosCaracteres) {
        return new VApr(UtlLis.descrito(nosCaracteres));
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
    public VApr md(Object oValor) {
        if (oValor == null) {
            novo();
        } else {
            List val = UtlLis.pega(oValor);
            try {
                valor = UtlLis.pega(val, VFrm.class);
            } catch (Exception ex) {
                valor = val;
            }
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

    public List<VFrm> pgLisFrm() {
        return valor;
    }

    public VApr novo() {
        valor = new ArrayList<>();
        return this;
    }

    public VApr anular() {
        valor = null;
        return this;
    }

    public VApr mPagina(int noIndice, VFrm para) {
        while (noIndice >= valor.size()) {
            valor.add(new VFrm());
        }
        valor.set(noIndice, para);
        return this;
    }

    public VFrm pPagina(int doIndice) {
        return VApr.this.pPagina(doIndice, null);
    }

    public VFrm pPagina(int doIndice, VFrm comPadrao) {
        VFrm retorno = comPadrao;
        if (valor != null) {
            if (doIndice < valor.size()) {
                retorno = valor.get(doIndice);
            }
        }
        if (retorno == null) {
            retorno = comPadrao;
        }
        return retorno;
    }

    public VApr botaPagina(int noIndice, VFrm aPagina) {
        while (noIndice > valor.size()) {
            valor.add(new VFrm());
        }
        valor.add(noIndice, aPagina);
        return this;
    }

    public VApr botaPagina(VFrm aPagina) {
        valor.add(aPagina);
        return this;
    }

    public VApr tiraPagina(int doIndice) {
        if (doIndice < valor.size()) {
            valor.remove(doIndice);
        }
        return this;
    }

    public Integer tamanho() {
        if (valor == null) {
            return 0;
        }
        return valor.size();
    }

}
