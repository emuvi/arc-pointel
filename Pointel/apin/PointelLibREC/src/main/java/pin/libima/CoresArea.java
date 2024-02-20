package pin.libima;

import java.awt.Color;
import java.awt.Rectangle;

public class CoresArea {

    private Color[] cores;
    private Rectangle area;

    public CoresArea() {
        this.cores = null;
        this.area = null;
    }

    public CoresArea(Color[] cores, Rectangle area) {
        this.cores = cores;
        this.area = area;
    }

    public Color[] cores() {
        return cores;
    }

    public CoresArea botaCores(Color[] cores) {
        this.cores = cores;
        return this;
    }

    public Rectangle area() {
        return area;
    }

    public CoresArea botaArea(Rectangle area) {
        this.area = area;
        return this;
    }

    private int pegaIndice(Integer daPosX, Integer ePosY) {
        return (cores.length / area.width * (ePosY - area.y)) + (daPosX - area.x);
    }

    public synchronized Color pega(Integer daPosX, Integer ePosY) {
        return cores[pegaIndice(daPosX, ePosY)];
    }

    public synchronized CoresArea bota(Integer naPosX, Integer ePosY, Color aCor) {
        cores[pegaIndice(naPosX, ePosY)] = aCor;
        return this;
    }

}
