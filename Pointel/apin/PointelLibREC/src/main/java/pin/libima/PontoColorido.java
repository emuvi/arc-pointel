package pin.libima;

import java.awt.Color;

public class PontoColorido {

    public Integer x;
    public Integer y;
    public Color cor;

    public PontoColorido(Integer comX, Integer eY, Color aCor) {
        x = comX;
        y = eY;
        cor = aCor;
    }

    public Integer pegaX() {
        return x;
    }

    public PontoColorido mudaX(Integer x) {
        this.x = x;
        return this;
    }

    public Integer pegaY() {
        return y;
    }

    public PontoColorido mudaY(Integer y) {
        this.y = y;
        return this;
    }

    public Color pegaCor() {
        return cor;
    }

    public PontoColorido mudaCor(Color cor) {
        this.cor = cor;
        return this;
    }

}
