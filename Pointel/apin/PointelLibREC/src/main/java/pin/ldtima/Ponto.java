package pin.ldtima;

import java.awt.Point;
import pin.libbas.Partes;

public class Ponto {

    public Integer posX;
    public Integer posY;
    public Integer puxadoX;
    public Integer puxadoY;

    public Ponto() {
        this.posX = null;
        this.posY = null;
        this.puxadoX = null;
        this.puxadoY = null;
    }

    public Ponto(Integer posX, Integer posY) {
        this.posX = posX;
        this.posY = posY;
        this.puxadoX = null;
        this.puxadoY = null;
    }

    public Ponto(Integer posX, Integer posY, Integer puxadoX, Integer puxadoY) {
        this.posX = posX;
        this.posY = posY;
        this.puxadoX = puxadoX;
        this.puxadoY = puxadoY;
    }

    public static Ponto descrito(String nosCaracteres) throws Exception {
        Object[] jtd = Partes.juntados(nosCaracteres);
        return new Ponto((Integer) jtd[0], (Integer) jtd[1], (Integer) jtd[2], (Integer) jtd[3]);
    }

    public Boolean tem() {
        return (posX != null && posY != null);
    }

    public Boolean temPuxado() {
        return (puxadoX != null && puxadoY != null);
    }

    public Point pega() throws Exception {
        if (tem()) {
            return new Point(posX, posY);
        } else {
            throw new Exception("Ponto Mal Formado");
        }
    }

    public Point pega(Integer somaNoX, Integer somaNoY) throws Exception {
        if (tem()) {
            return new Point(posX + somaNoX, posY + somaNoY);
        } else {
            throw new Exception("Ponto Mal Formado");
        }
    }

    public Point pega(Double vezesNoX, Double vezesNoY) throws Exception {
        if (tem()) {
            return new Point(new Double(posX * vezesNoX).intValue(), new Double(posY * vezesNoY).intValue());
        } else {
            throw new Exception("Ponto Mal Formado");
        }
    }

    public Point pega(Integer somaNoX, Double vezesNoX, Integer somaNoY, Double vezesNoY) throws Exception {
        if (tem()) {
            return new Point(new Double((posX + somaNoX) * vezesNoX).intValue(), new Double((posY + somaNoY) * vezesNoY).intValue());
        } else {
            throw new Exception("Ponto Mal Formado");
        }
    }

    public Point pegaPuxado() throws Exception {
        if (temPuxado()) {
            return new Point(puxadoX, puxadoY);
        } else {
            throw new Exception("Ponto Puxado Mal Formado");
        }
    }

    public Point pegaPuxado(Integer somaNoX, Integer somaNoY) throws Exception {
        if (tem()) {
            return new Point(puxadoX + somaNoX, puxadoY + somaNoY);
        } else {
            throw new Exception("Ponto Mal Formado");
        }
    }

    public Point pegaPuxado(Double vezesNoX, Double vezesNoY) throws Exception {
        if (tem()) {
            return new Point(new Double(puxadoX * vezesNoX).intValue(), new Double(puxadoY * vezesNoY).intValue());
        } else {
            throw new Exception("Ponto Mal Formado");
        }
    }

    public Point pegaPuxado(Integer somaNoX, Double vezesNoX, Integer somaNoY, Double vezesNoY) throws Exception {
        if (tem()) {
            return new Point(new Double((puxadoX + somaNoX) * vezesNoX).intValue(), new Double((puxadoY + somaNoY) * vezesNoY).intValue());
        } else {
            throw new Exception("Ponto Mal Formado");
        }
    }

    public String descreve() {
        return Partes.juntaVals(posX, posY, puxadoX, puxadoY);
    }

    @Override
    public String toString() {
        return "X = " + posX + " Y = " + posY + " | pX = " + puxadoX + " pY = " + puxadoY;
    }
}
