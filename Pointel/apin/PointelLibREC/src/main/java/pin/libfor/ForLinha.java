package pin.libfor;

import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

public class ForLinha extends ForParte {

    public ForLinha() {
        this(null);
    }

    public ForLinha(Point2D... comPontos) {
        super(comPontos);
    }

    @Override
    public void constroi(Path2D noFormato) {
        noFormato.lineTo(pontos().get(0).getX(), pontos().get(0).getY());
    }

    @Override
    public String toString() {
        return "Linha";
    }

}
