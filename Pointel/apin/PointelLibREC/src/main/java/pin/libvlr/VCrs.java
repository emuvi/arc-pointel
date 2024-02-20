package pin.libvlr;

import pin.libutl.UtlCrs;

public class VCrs extends Vlr {

    private volatile String valor;

    public VCrs() {
        valor = null;
    }

    public VCrs(Object doValor) {
        valor = UtlCrs.pega(doValor);
    }

    public VCrs(Object doValor, String comPadrao) {
        valor = UtlCrs.pega(doValor, comPadrao);
    }

    public static VCrs descrito(String nosCaracteres) {
        return new VCrs(nosCaracteres);
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
    public VCrs md(Object oValor) {
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

    public VCrs limpa() {
        if (valor != null) {
            valor = valor.trim();
        }
        return this;
    }

    public VCrs corta(Integer aQuantidade) {
        valor = UtlCrs.corta(valor, aQuantidade);
        return this;
    }

    public VCrs cortaAFrente(Integer aQuantidade) {
        valor = UtlCrs.cortaAFrente(valor, aQuantidade);
        return this;
    }

    public VCrs soma(String osCaracteres) {
        valor = UtlCrs.soma(valor, osCaracteres);
        return this;
    }

    public VCrs soma(String usaUniao, String osCaracteres) {
        valor = UtlCrs.soma(valor, usaUniao, osCaracteres);
        return this;
    }

    public VCrs somaAFrente(String osCaracteres) {
        valor = UtlCrs.somaAFrente(valor, osCaracteres);
        return this;
    }

    public VCrs somaAFrente(String usaUniao, String osCaracteres) {
        valor = UtlCrs.somaAFrente(valor, usaUniao, osCaracteres);
        return this;
    }

    public VCrs repete(String osCaracteres, Integer naQuantidade) {
        return soma(UtlCrs.repete(osCaracteres, naQuantidade));
    }

    public VCrs repeteAFrente(String osCaracteres, Integer naQuantidade) {
        return somaAFrente(UtlCrs.repete(osCaracteres, naQuantidade));
    }

    public VCrs mSoPrimeirosMaiusculos() {
        valor = UtlCrs.mSoPrimeirosMaiusculos(valor);
        return this;
    }

    public VCrs troca(String de, String para) {
        valor = UtlCrs.troca(valor, de, para);
        return this;
    }

}
