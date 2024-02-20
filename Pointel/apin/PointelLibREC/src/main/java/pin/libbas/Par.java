package pin.libbas;

import pin.libutl.UtlBin;
import pin.libutl.UtlCrs;

public class Par<T1, T2> {

    private T1 lado1;
    private T2 lado2;

    public Par() {
        lado1 = null;
        lado2 = null;
    }

    public Par(T1 lado1, T2 lado2) {
        this.lado1 = lado1;
        this.lado2 = lado2;
    }

    public T1 pLado1() {
        return lado1;
    }

    public Par mLado1(T1 lado1) {
        this.lado1 = lado1;
        return this;
    }

    public T2 pLado2() {
        return lado2;
    }

    public Par mLado2(T2 lado2) {
        this.lado2 = lado2;
        return this;
    }

    public String descreve() {
        return UtlBin.descreveMembros(this);
    }

    @Override
    public String toString() {
        return UtlBin.mostraMembros(this);
    }

}
