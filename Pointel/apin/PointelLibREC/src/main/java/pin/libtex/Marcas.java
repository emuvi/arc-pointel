package pin.libtex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Marcas extends ArrayList<Marca> {

    public Marcas(int initialCapacity) {
        super(initialCapacity);
    }

    public Marcas() {
    }

    public Marcas(Collection<? extends Marca> c) {
        super(c);
    }

    public Marca marcada(String comMarcacao) {
        for (Marca mrc : this) {
            if (mrc.abridor != null) {
                if (mrc.abridor.startsWith("<" + comMarcacao + " ")) {
                    return mrc;
                } else if (mrc.abridor.equals("<" + comMarcacao + ">")) {
                    return mrc;
                }
            }
        }
        return null;
    }

    public List<Marca> marcadas(String comMarcacao) {
        List<Marca> retorno = new ArrayList<>();
        for (Marca mrc : this) {
            if (mrc.abridor != null) {
                if (mrc.abridor.startsWith("<" + comMarcacao + " ")) {
                    retorno.add(mrc);
                } else if (mrc.abridor.equals("<" + comMarcacao + ">")) {
                    retorno.add(mrc);
                }
            }
        }
        return retorno;
    }

    public String abridor(String comMarcacao) {
        return abridor(comMarcacao, null);
    }

    public String abridor(String comMarcacao, String comPadrao) {
        Marca mrc = marcada(comMarcacao);
        if (mrc != null) {
            return mrc.abridor;
        } else {
            return comPadrao;
        }
    }

    public String valor(String comMarcacao) {
        return valor(comMarcacao, null);
    }

    public String valor(String comMarcacao, String comPadrao) {
        Marca mrc = marcada(comMarcacao);
        if (mrc != null) {
            return mrc.valor;
        } else {
            return comPadrao;
        }
    }

    public String fechador(String comMarcacao) {
        return fechador(comMarcacao, null);
    }

    public String fechador(String comMarcacao, String comPadrao) {
        Marca mrc = marcada(comMarcacao);
        if (mrc != null) {
            return mrc.fechador;
        } else {
            return comPadrao;
        }
    }

}
