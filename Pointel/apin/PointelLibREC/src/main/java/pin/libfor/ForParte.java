package pin.libfor;

import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public abstract class ForParte {

    private List<Point2D> pontos;

    public ForParte() {
        this(null);
    }

    public ForParte(Point2D... comPontos) {
        pontos = new ArrayList<>();
        if (comPontos != null) {
            for (Point2D ponto : comPontos) {
                pontos.add(ponto);
            }
        }
    }

    public static ForParte Duplica(ForParte aParte) throws Exception {
        ForParte retorno = aParte.getClass().newInstance();
        for (Point2D ponto : aParte.pontos()) {
            retorno.bota(new Point2D.Double(ponto.getX(), ponto.getY()));
        }
        return retorno;
    }

    public List<Point2D> pontos() {
        return pontos;
    }

    public ForParte limpa() {
        pontos.clear();
        return this;
    }

    public ForParte bota(Point2D oPonto) {
        pontos.add(oPonto);
        return this;
    }

    public Point2D pega(int noIndice) {
        return pega(noIndice, null);
    }

    public Point2D pega(int noIndice, Point2D comPadrao) {
        if (noIndice > -1) {
            if (pontos.size() > noIndice) {
                return pontos.get(noIndice);
            }
        }
        return comPadrao;
    }

    public Point2D pegaUltimo() {
        return pegaUltimo(null);
    }

    public Point2D pegaUltimo(Point2D comPadrao) {
        if (pontos.size() > 0) {
            return pontos.get(pontos.size() - 1);
        }
        return comPadrao;
    }

    public ForParte tira(Point2D oPonto) {
        pontos.remove(oPonto);
        return this;
    }

    public abstract void constroi(Path2D noFormato);

    private static Point2D[] pegaPontos(double[] nasCoordenadas, int quantidade) {
        Point2D[] retorno = new Point2D[quantidade];
        for (int ip = 0; ip < quantidade; ip++) {
            int ix = 2 * ip;
            int iy = ix + 1;
            retorno[ip] = new Point2D.Double(nasCoordenadas[ix], nasCoordenadas[iy]);
        }
        return retorno;
    }

    public static List<ForParte> infere(PathIterator doIterador) {
        List<ForParte> retorno = new ArrayList<>();
        while (!doIterador.isDone()) {
            double[] cr = new double[6];
            int rt = doIterador.currentSegment(cr);
            switch (rt) {
                case PathIterator.SEG_MOVETO:
                    retorno.add(new ForInicio(pegaPontos(cr, 1)));
                    break;
                case PathIterator.SEG_LINETO:
                    retorno.add(new ForLinha(pegaPontos(cr, 1)));
                    break;
                case PathIterator.SEG_QUADTO:
                    retorno.add(new ForCurva(pegaPontos(cr, 2)));
                    break;
                case PathIterator.SEG_CUBICTO:
                    retorno.add(new ForSiado(pegaPontos(cr, 3)));
                    break;
                case PathIterator.SEG_CLOSE:
                    retorno.add(new ForFim());
                    break;
            }
            doIterador.next();
        }
        return retorno;
    }

}
