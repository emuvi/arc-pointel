package pin.libbas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pares<T1, T2> extends ArrayList<Par<T1, T2>> {

    public Par<T1, T2> pegaL1(T1 comLado1) {
        for (Par<T1, T2> par : this) {
            if (Objects.equals(comLado1, par.pLado1())) {
                return par;
            }
        }
        return null;
    }

    public Par<T1, T2> pegaL2(T2 comLado2) {
        for (Par<T1, T2> par : this) {
            if (Objects.equals(comLado2, par.pLado2())) {
                return par;
            }
        }
        return null;
    }

    public List<Par<T1, T2>> listaL1(T1 comLado1) {
        List<Par<T1, T2>> retorno = new ArrayList<>();
        for (Par<T1, T2> par : this) {
            if (Objects.equals(comLado1, par.pLado1())) {
                retorno.add(par);
            }
        }
        return retorno;
    }

    public List<Par<T1, T2>> listaL2(T2 comLado2) {
        List<Par<T1, T2>> retorno = new ArrayList<>();
        for (Par<T1, T2> par : this) {
            if (Objects.equals(comLado2, par.pLado2())) {
                retorno.add(par);
            }
        }
        return retorno;
    }

    public Pares bota(Par<T1, T2> oPar) {
        add(oPar);
        return this;
    }

    public Pares tira(Par<T1, T2> oPar) {
        remove(oPar);
        return this;
    }

    public Pares tiraL1(T1 comLado1) {
        for (int ip = size() - 1; ip >= 0; ip--) {
            Par<T1, T2> par = get(ip);
            if (Objects.equals(comLado1, par.pLado1())) {
                remove(ip);
            }
        }
        return this;
    }

    public Pares tiraL2(T2 comLado2) {
        for (int ip = size() - 1; ip >= 0; ip--) {
            Par<T1, T2> par = get(ip);
            if (Objects.equals(comLado2, par.pLado2())) {
                remove(ip);
            }
        }
        return this;
    }

    public Pares limpa() {
        clear();
        return this;
    }

}
