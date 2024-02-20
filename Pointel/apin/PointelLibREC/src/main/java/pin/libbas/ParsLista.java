package pin.libbas;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public class ParsLista<T> extends CopyOnWriteArrayList<T> {

    public ParsLista() {
    }

    public ParsLista(Collection<? extends T> comValores) {
        super(comValores);
    }

    public ParsLista(T[] comValores) {
        super(comValores);
    }

    public ParsLista bota(Integer noIndice, T oValor) {
        add(noIndice, oValor);
        return this;
    }

    public ParsLista botaPrimeiro(T oValor) {
        add(0, oValor);
        return this;
    }

    public ParsLista botaUltimo(T oValor) {
        add(size(), oValor);
        return this;
    }

    public ParsLista botaTodos(Collection<? extends T> osValores) {
        addAll(osValores);
        return this;
    }

    public T pega(Integer doIndice) {
        if (temValor(doIndice)) {
            return get(doIndice);
        } else {
            return null;
        }
    }

    public T pegaPrimeiro() {
        if (temValor(0)) {
            return get(0);
        } else {
            return null;
        }
    }

    public T pegaUltimo() {
        if (temValor(size() - 1)) {
            return get(size() - 1);
        } else {
            return null;
        }
    }

    public T tira(int doIndice) {
        if (temValor(doIndice)) {
            return remove(doIndice);
        } else {
            return null;
        }
    }

    public T tiraPrimeiro() {
        if (temValor(0)) {
            return remove(0);
        } else {
            return null;
        }
    }

    public T tiraUltimo() {
        if (temValor(size() - 1)) {
            return remove(size() - 1);
        } else {
            return null;
        }
    }

    public ParsLista remover(int doIndice) {
        if (temValor(doIndice)) {
            remove(doIndice);
        }
        return this;
    }

    public ParsLista removerPrimeiro() {
        if (temValor(0)) {
            remove(0);
        }
        return this;
    }

    public ParsLista removerUltimo() {
        if (temValor(size() - 1)) {
            remove(size() - 1);
        }
        return this;
    }

    public Boolean temValor(Integer noIndice) {
        return noIndice > -1 && noIndice < size();
    }

    public Boolean temValor() {
        return !isEmpty();
    }

    public Boolean vazio() {
        return isEmpty();
    }

    public Integer tamanho() {
        return size();
    }

    public Boolean tem(T oValor) {
        return contains(oValor);
    }

    public Boolean temTodos(Collection<T> osValores) {
        return containsAll(osValores);
    }

    public ParsLista limpa() {
        clear();
        return this;
    }

    public ParsLista tira(T oValor) {
        remove(oValor);
        return this;
    }

    public ParsLista tiraSe(Analisado<T, Boolean> comAnalisador) throws Exception {
        final Iterator<T> iter = iterator();
        while (iter.hasNext()) {
            if (comAnalisador.analisa(iter.next())) {
                iter.remove();
            }
        }
        return this;
    }

    public ParsLista tiraPrimeiro(T oValor) {
        for (int ix = 0; ix < size(); ix++) {
            if (Objects.equals(oValor, get(ix))) {
                remove(ix);
                break;
            }
        }
        return this;
    }

    public ParsLista tiraUltimo(T oValor) {
        for (int ix = size() - 1; ix >= 0; ix--) {
            if (Objects.equals(oValor, get(ix))) {
                remove(ix);
                break;
            }
        }
        return this;
    }

    public ParsLista tiraTodos(Collection<T> osValores) {
        removeAll(osValores);
        return this;
    }

    public ParsLista deixaTodos(Collection<T> osValores) {
        retainAll(osValores);
        return this;
    }

    public void iterage(Analisado<T, Boolean> comAnalisador) throws Exception {
        Iterator<T> iter = iterator();
        while (iter.hasNext()) {
            T item = iter.next();
            if (!comAnalisador.analisa(item)) {
                break;
            }
        }
    }

    public void iterageSubindo(Analisado<T, Boolean> comAnalisador) throws Exception {
        for (int ix = size() - 1; ix >= 0; ix--) {
            if (!comAnalisador.analisa(get(ix))) {
                break;
            }
        }
    }
}
