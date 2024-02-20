package pin.libvlr;

import java.awt.Color;
import pin.libutl.UtlCor;
import pin.libutl.UtlCrs;

public class VCor extends Vlr {

    private volatile Color valor;

    public VCor() {
        valor = null;
    }

    public VCor(Object doValor) {
        valor = UtlCor.pega(doValor);
    }

    public static VCor descrito(String nosCaracteres) {
        return new VCor(nosCaracteres);
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
    public VCor md(Object oValor) {
        valor = UtlCor.pega(oValor);
        return this;
    }

    @Override
    public String paraConsSQL() {
        return UtlCrs.formataSQL(UtlCor.formata(valor));
    }

    @Override
    public Object paraParamSQL() {
        return UtlCrs.pegaSQL(UtlCor.formata(valor));
    }

    @Override
    public String toString() {
        return UtlCor.formata(valor);
    }

    @Override
    public String descreve() {
        return UtlCor.formata(valor);
    }

    public VCor clareia(Integer naIntensidade) {
        valor = UtlCor.clareia(valor, naIntensidade);
        return this;
    }

    public VCor escurece(Integer naIntensidade) {
        valor = UtlCor.escurece(valor, naIntensidade);
        return this;
    }

}
