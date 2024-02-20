package pin.libbas;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ParsVals<T> extends ConcurrentLinkedDeque<T> {

    public ParsVals() {
        super();
    }

    public ParsVals(T... valores) {
        super(Arrays.asList(valores));
    }

    public ParsVals(Collection<? extends T> comValores) {
        super(comValores);
    }

    public ParsVals botaPrimeiro(T oValor) {
        addFirst(oValor);
        return this;
    }

    public ParsVals botaUltimo(T oValor) {
        addLast(oValor);
        return this;
    }

    public ParsVals botaTodos(Collection<? extends T> osValores) {
        addAll(osValores);
        return this;
    }

    public T pegaPrimeiro() {
        return getFirst();
    }

    public T pegaUltimo() {
        return getLast();
    }

    public T tiraPrimeiro() {
        return pollFirst();
    }

    public T tiraUltimo() {
        return pollLast();
    }

    public ParsVals removerPrimeiro() {
        removeFirst();
        return this;
    }

    public ParsVals removerUltimo() {
        removeLast();
        return this;
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

    public ParsVals limpa() {
        clear();
        return this;
    }

    public ParsVals tira(T oValor) {
        remove(oValor);
        return this;
    }

    public ParsVals tiraSe(Analisado<T, Boolean> comAnalisador) throws Exception {
        final Iterator<T> iter = iterator();
        while (iter.hasNext()) {
            if (comAnalisador.analisa(iter.next())) {
                iter.remove();
            }
        }
        return this;
    }

    public ParsVals tiraPrimeiro(T oValor) {
        removeFirstOccurrence(oValor);
        return this;
    }

    public ParsVals tiraUltimo(T oValor) {
        removeLastOccurrence(oValor);
        return this;
    }

    public ParsVals tiraTodos(Collection<T> osValores) {
        removeAll(osValores);
        return this;
    }

    public ParsVals deixaTodos(Collection<T> osValores) {
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
        Iterator<T> iter = descendingIterator();
        while (iter.hasNext()) {
            T item = iter.next();
            if (!comAnalisador.analisa(item)) {
                break;
            }
        }
    }

}
