package pin.libvlr;

import java.awt.Point;
import java.awt.image.BufferedImage;
import pin.libima.LinhaPontilhada;
import pin.libima.RetanguloPontilhado;
import pin.libutl.UtlIma;

public class VIma extends Vlr {

    private volatile BufferedImage valor;

    public VIma() {
        valor = null;
    }

    public VIma(Object doValor) {
        valor = UtlIma.pega(doValor);
    }

    public static VIma descrito(String nosCaracteres) {
        return new VIma(nosCaracteres);
    }

    @Override
    public Object pg(Object comPadrao) {
        return valor;
    }

    @Override
    public BufferedImage pgIma() {
        return valor;
    }

    @Override
    public BufferedImage pgIma(BufferedImage comPadrao) {
        if (valor == null) {
            return comPadrao;
        } else {
            return valor;
        }
    }

    @Override
    public VIma md(Object oValor) {
        valor = UtlIma.pega(oValor);
        return this;
    }

    @Override
    public String paraConsSQL() {
        return descreve();
    }

    @Override
    public Object paraParamSQL() {
        return descreve();
    }

    @Override
    public String toString() {
        return UtlIma.formata(valor);
    }

    @Override
    public String descreve() {
        return UtlIma.descreve(valor);
    }

    public VIma linhaPontilhada(Point comPonto1, Point ePonto2) {
        valor = LinhaPontilhada.faz(valor, comPonto1, ePonto2);
        return this;
    }

    public VIma retanguloPontilhado(Point comPonto1, Point ePonto2) {
        valor = RetanguloPontilhado.faz(valor, comPonto1, ePonto2);
        return this;
    }

}
