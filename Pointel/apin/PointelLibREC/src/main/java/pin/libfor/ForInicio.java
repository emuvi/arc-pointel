package pin.libfor;

import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

public class ForInicio extends ForParte {

    public ForInicio() {
        this(null);
    }

    public ForInicio(Point2D... comPontos) {
        super(comPontos);
    }

    @Override
    public void constroi(Path2D noFormato) {
        noFormato.moveTo(pontos().get(0).getX(), pontos().get(0).getY());
    }

    @Override
    public String toString() {
        return "Início";
    }

}
