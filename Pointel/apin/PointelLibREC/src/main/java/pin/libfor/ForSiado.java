package pin.libfor;

import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

public class ForSiado extends ForParte {

    public ForSiado() {
        this(null);
    }

    public ForSiado(Point2D... comPontos) {
        super(comPontos);
    }

    @Override
    public void constroi(Path2D noFormato) {
        noFormato.curveTo(pontos().get(0).getX(), pontos().get(0).getY(), pontos().get(1).getX(), pontos().get(1).getY(), pontos().get(2).getX(), pontos().get(2).getY());
    }

    @Override
    public String toString() {
        return "Siado";
    }

}
