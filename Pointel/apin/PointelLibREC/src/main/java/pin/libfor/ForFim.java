package pin.libfor;

import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

public class ForFim extends ForParte {

    public ForFim() {
        this(null);
    }

    public ForFim(Point2D... comPontos) {
        super(comPontos);
    }

    @Override
    public void constroi(Path2D noFormato) {
        noFormato.closePath();
    }

    @Override
    public String toString() {
        return "Fim";
    }

}
