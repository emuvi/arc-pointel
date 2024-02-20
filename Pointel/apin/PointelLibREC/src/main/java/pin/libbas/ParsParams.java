package pin.libbas;

import java.util.Enumeration;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ParsParams<T1, T2> extends ConcurrentHashMap<T1, T2> {

    public synchronized T2 pega(T1 chave) {
        return super.get(chave);
    }

    public synchronized T2 pega(T1 chave, T2 comPadrao) {
        return super.getOrDefault(chave, comPadrao);
    }

    public synchronized T2 bota(T1 chave, T2 valor) {
        return super.put(chave, valor);
    }

    public synchronized T2 botaSeAusente(T1 chave, T2 valor) {
        return super.putIfAbsent(chave, valor);
    }

    public synchronized T2 tira(T1 chave) {
        return super.remove(chave);
    }

    public synchronized boolean tira(T1 chave, T2 eValor) {
        return super.remove(chave, eValor);
    }

    public synchronized void limpa() {
        super.clear();
    }

    public synchronized boolean vazio() {
        return super.isEmpty();
    }

    public synchronized boolean tem() {
        return !super.isEmpty();
    }

    public synchronized boolean temChave(T1 chave) {
        return super.containsKey(chave);
    }

    public synchronized boolean temValor(T2 valor) {
        return super.containsValue(valor);
    }

    public synchronized Set<Entry<T1, T2>> lista() {
        return super.entrySet();
    }

    public synchronized Enumeration<T2> valores() {
        return super.elements();
    }

    public synchronized int tamanho() {
        return super.size();
    }

    public synchronized void soma(T1 naChave, T2 oValor) {
        if (!temChave(naChave)) {
            bota(naChave, oValor);
        } else {
            T2 tval = pega(naChave);
            if (oValor instanceof Integer) {
                Integer aux = (Integer) tval;
                if (aux == null) {
                    aux = 0;
                }
                Integer nov = (Integer) oValor;
                aux += nov;
                tval = (T2) aux;
            } else if (oValor instanceof Long) {
                Long aux = (Long) tval;
                if (aux == null) {
                    aux = 0l;
                }
                Long nov = (Long) oValor;
                aux += nov;
                tval = (T2) aux;
            } else if (oValor instanceof Float) {
                Float aux = (Float) tval;
                if (aux == null) {
                    aux = 0.0f;
                }
                Float nov = (Float) oValor;
                aux += nov;
                tval = (T2) aux;
            } else if (oValor instanceof Double) {
                Double aux = (Double) tval;
                if (aux == null) {
                    aux = 0.0;
                }
                Double nov = (Double) oValor;
                aux += nov;
                tval = (T2) aux;
            } else if (oValor instanceof String) {
                String aux = (String) tval;
                if (aux == null) {
                    aux = "";
                }
                String nov = (String) oValor;
                aux += nov;
                tval = (T2) aux;
            }
            bota(naChave, tval);
        }
    }

}
