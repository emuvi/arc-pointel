package pin.libvlr;

import pin.libbas.Retornos;
import pin.libutl.UtlCrs;

public class VBot extends Vlr {

    private volatile String valor;
    private volatile Retornos retornos;
    private volatile Object origem;

    public VBot() {
        valor = null;
    }

    public VBot(Object doValor) {
        valor = UtlCrs.pega(doValor);
    }

    public VBot(String comCodigo, Object eOrigem) {
        valor = comCodigo;
        retornos = null;
        origem = eOrigem;
    }

    public VBot(String comCodigo, Retornos eRetornos, Object eOrigem) {
        valor = comCodigo;
        retornos = eRetornos;
        origem = eOrigem;
    }

    public static VBot descrito(String nosCaracteres) {
        return new VBot(nosCaracteres);
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
    public VBot md(Object oValor) {
        valor = UtlCrs.pega(oValor);
        return this;
    }

    @Override
    public String paraConsSQL() {
        return UtlCrs.formataSQL(valor);
    }

    @Override
    public Object paraParamSQL() {
        return UtlCrs.pegaSQL(valor);
    }

    @Override
    public String toString() {
        return valor;
    }

    @Override
    public String descreve() {
        return valor;
    }

    public Retornos pRetornos() {
        return retornos;
    }

    public VBot mRetornos(Retornos retornos) {
        this.retornos = retornos;
        return this;
    }

    public Object pOrigem() {
        return origem;
    }

    public VBot mOrigem(Object origem) {
        this.origem = origem;
        return this;
    }

}
