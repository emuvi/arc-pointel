package pin.libvlr;

public class VArv extends Vlr {

    private volatile VArvItn valor;

    public VArv() {
        valor = new VArvItn();
    }

    public VArv(Object doValor) throws Exception {
        md(doValor);
    }

    public static VArv descrito(String nosCaracteres) throws Exception {
        return new VArv(VArvItn.descrito(nosCaracteres));
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
    public VArv md(Object oValor) throws Exception {
        if (oValor == null) {
            novo();
        } else {
            if (oValor instanceof VArvItn) {
                valor = (VArvItn) oValor;
            } else if (oValor instanceof String) {
                valor = VArvItn.descrito((String) oValor);
            } else {
                valor = new VArvItn();
                valor.mValor(oValor);
            }
        }
        return this;
    }

    @Override
    public Boolean vazio() {
        if (valor == null) {
            return true;
        }
        return valor.pNome() == null && valor.pValor() == null && valor.pFilhos() == null;
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
        return valor.toString();
    }

    @Override
    public String descreve() {
        return valor.descreve();
    }

    public VArv novo() {
        valor = new VArvItn();
        return this;
    }

    public VArv anular() {
        valor = null;
        return this;
    }

}
