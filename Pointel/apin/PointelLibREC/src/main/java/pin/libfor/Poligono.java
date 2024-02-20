package pin.libfor;

import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

public class Poligono extends Path2D.Double {

    public Poligono(Integer comLados, double eTamanho) {
        super();
        double theta = 2 * Math.PI / comLados;
        for (int i = 0; i < comLados; ++i) {
            double x = Math.cos(theta * i);
            double y = Math.sin(theta * i);
            if (i == 0) {
                moveTo(x, y);
            } else {
                lineTo(x, y);
            }
        }
        closePath();
        double sx = eTamanho / getBounds2D().getWidth();
        double sy = eTamanho / getBounds2D().getHeight();
        transform(AffineTransform.getScaleInstance(sx, sy));
        transform(AffineTransform.getTranslateInstance(getBounds2D().getX() * -1, getBounds2D().getY() * -1));
    }

}
