package pin.libfor;

public class Seta extends Generico {

    public Seta(double comLargura, double eGrossura) {
        super();
        double lrg = Math.max(eGrossura * 3.0, 10.0);
        double dif = eGrossura / 2.0;
        moveTo(0.0, (lrg / 2.0) - dif);
        lineTo(comLargura - lrg, (lrg / 2.0) - dif);
        lineTo(comLargura - lrg, 0.0);
        lineTo(comLargura, lrg / 2.0);
        lineTo(comLargura - lrg, lrg);
        lineTo(comLargura - lrg, (lrg / 2.0) + dif);
        lineTo(0.0, (lrg / 2.0) + dif);
        closePath();
    }

}
