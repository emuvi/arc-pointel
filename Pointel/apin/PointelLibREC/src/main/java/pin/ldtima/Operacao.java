package pin.ldtima;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class Operacao {

    private final Tp tipo;
    private final Boolean transitoria;
    private final List<Ponto> pontos;

    public static enum Tp {
        Selecionar_Retangulo
    };

    public Operacao(Tp doTipo, Boolean ehTransitoria) {
        tipo = doTipo;
        transitoria = ehTransitoria;
        pontos = new ArrayList<>();
    }

    public Tp pegaTipo() {
        return tipo;
    }

    public Boolean ehTransitoria() {
        return transitoria;
    }

    public Operacao botaPonto(Integer posX, Integer posY) {
        pontos.add(new Ponto(posX, posY));
        return this;
    }

    public Operacao botaPonto(Integer posX, Integer posY, Integer puxadoX, Integer puxadoY) {
        pontos.add(new Ponto(posX, posY, puxadoX, puxadoY));
        return this;
    }

    public Boolean temPonto() {
        return !pontos.isEmpty();
    }

    public List<Ponto> pontos() {
        return pontos;
    }

    public Ponto pegaPonto() throws Exception {
        if (pontos.size() - 1 >= 0) {
            return pontos.get(0);
        } else {
            throw new Exception("Faltou o Ponto " + 1);
        }
    }

    public Ponto tiraPonto() throws Exception {
        if (pontos.size() - 1 >= 0) {
            Ponto pnt = pontos.get(0);
            pontos.remove(0);
            return pnt;
        } else {
            throw new Exception("Faltou o Ponto " + 1);
        }
    }

    public Ponto pegaPonto(Integer doIndice) throws Exception {
        if (pontos.size() - 1 >= doIndice) {
            return pontos.get(doIndice);
        } else {
            throw new Exception("Faltou o Ponto " + (doIndice + 1));
        }
    }

    public Ponto tiraPonto(Integer doIndice) throws Exception {
        if (pontos.size() - 1 >= doIndice) {
            Ponto pnt = pontos.get(doIndice);
            pontos.remove(doIndice.intValue());
            return pnt;
        } else {
            throw new Exception("Faltou o Ponto " + (doIndice + 1));
        }
    }

    public abstract BufferedImage executa(BufferedImage naImagem) throws Exception;
}
