package pin.libfor;

public class Uniao extends Generico {

    public Uniao(double comLargura, double eGrossura) {
        super();
        double lrg = Math.max(eGrossura * 3.0, 10.0);
        double dif = eGrossura / 2.0;
        moveTo(0, lrg / 2.0);
        quadTo(lrg / 2.0, 0, lrg, (lrg / 2.0) - dif);
        lineTo(comLargura - lrg, (lrg / 2.0) - dif);
        quadTo(comLargura - (lrg / 2.0), 0, comLargura, (lrg / 2.0));
        quadTo(comLargura - (lrg / 2.0), lrg, comLargura - lrg, (lrg / 2.0) + dif);
        lineTo(lrg, (lrg / 2.0) + dif);
        quadTo(lrg / 2.0, lrg, 0, lrg / 2.0);
        closePath();
    }

}
