package pin.libbas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Paralelos {

    private static ParsLista<Paralelo> lista = null;

    public static ParsLista<Paralelo> lista() {
        if (lista == null) {
            lista = new ParsLista<>();
        }
        limpa();
        return lista;
    }

    public static void bota(Paralelo oParalelo) {
        if (lista == null) {
            lista = new ParsLista<>();
        }
        lista.add(oParalelo);
    }

    public static void limpa() {
        if (lista != null) {
            for (Paralelo par : lista) {
                if (par.iniciado() && !par.isAlive()) {
                    lista.remove(par);
                }
            }
        }
    }

    public static List<Object[]> pegaTab() {
        return pegaTab(Paralelo.class);
    }

    public static List<Object[]> pegaTab(Class daClasse) {
        List<Object[]> retorno = new ArrayList<>();
        for (Paralelo par : lista()) {
            if (daClasse.isInstance(par)) {
                retorno.add(new Object[]{par.getId(), par.getClass().getSimpleName(), par.getName(), pegaNome(par.getPriority())});
            }
        }
        return retorno;
    }

    public static String pegaNome(Integer daPrioridade) {
        String retorno = "Normal";
        if (daPrioridade >= Thread.MAX_PRIORITY) {
            retorno = "Máxima";
        } else if (daPrioridade <= Thread.MIN_PRIORITY) {
            retorno = "Mínima";
        }
        return retorno;
    }

    public static Paralelo pega(Long peloId) {
        if (lista != null) {
            for (Paralelo par : lista) {
                if (par.iniciado() && !par.isAlive()) {
                    lista.remove(par);
                } else if (Objects.equals(par.getId(), peloId)) {
                    return par;
                }
            }
        }
        return null;
    }

    public static void parar(Long peloId) {
        Paralelo par = pega(peloId);
        if (par != null) {
            par.stop();
        }
    }

}
