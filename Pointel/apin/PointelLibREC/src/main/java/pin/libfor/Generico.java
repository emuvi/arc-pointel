package pin.libfor;

import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class Generico extends Path2D.Double {

    private List<ForParte> partes;

    public Generico() {
        this(null);
    }

    public Generico(Point2D comInicio) {
        super();
        partes = new ArrayList<>();
        if (comInicio != null) {
            partes.add(new ForInicio(comInicio));
        }
    }

    public static Generico Duplica(Generico oFormato) throws Exception {
        Generico retorno = new Generico();
        if (oFormato.temParte()) {
            for (ForParte parte : oFormato.partes()) {
                retorno.bota(ForParte.Duplica(parte));
            }
        }
        return retorno;
    }

    public Boolean temParte() {
        return !partes.isEmpty();
    }

    public List<ForParte> partes() {
        return partes;
    }

    public Generico mudaPartes(List<ForParte> para) {
        partes = para;
        return this;
    }

    public Generico limpa() {
        if (partes != null) {
            partes.clear();
        }
        return this;
    }

    public Generico bota(ForParte aParte) {
        if (partes == null) {
            partes = new ArrayList<>();
        }
        partes.add(aParte);
        return this;
    }

    public Generico tira(ForParte aParte) {
        if (partes != null) {
            partes.remove(aParte);
        }
        return this;
    }

    public Generico constroi() {
        reset();
        if (temParte()) {
            for (ForParte parte : partes) {
                parte.constroi(this);
            }
//            closePath();
        }
        return this;
    }

    public Generico moveProCentro(Rectangle2D doRetangulo) {
        Rectangle2D bnd = getBounds2D();
        double pmx = doRetangulo.getWidth() / 2;
        double pmy = doRetangulo.getHeight() / 2;
        double tmx = bnd.getWidth() / 2;
        double tmy = bnd.getHeight() / 2;
        double dfx = (pmx + doRetangulo.getX()) - (tmx + bnd.getX());
        double dfy = (pmy + doRetangulo.getY()) - (tmy + bnd.getY());
        AffineTransform af = AffineTransform.getTranslateInstance(dfx, dfy);
        transform(af);
        return this;
    }

}
