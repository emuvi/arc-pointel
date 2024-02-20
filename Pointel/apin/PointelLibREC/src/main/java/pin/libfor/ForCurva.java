package pin.libfor;

import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

public class ForCurva extends ForParte {

    public ForCurva() {
        this(null);
    }

    public ForCurva(Point2D... comPontos) {
        super(comPontos);
    }

    @Override
    public void constroi(Path2D noFormato) {
        noFormato.quadTo(pontos().get(0).getX(), pontos().get(0).getY(), pontos().get(1).getX(), pontos().get(1).getY());
    }

    @Override
    public String toString() {
        return "Curva";
    }

}
