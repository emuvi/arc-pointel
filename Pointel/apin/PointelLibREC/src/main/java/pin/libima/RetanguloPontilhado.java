package pin.libima;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.image.BufferedImage;

public class RetanguloPontilhado {

    public static BufferedImage faz(BufferedImage naImagem, Point comPonto1, Point ePonto2) {
        if (comPonto1.x > ePonto2.x) {
            int aux = comPonto1.x;
            comPonto1.x = ePonto2.x;
            ePonto2.x = aux;
        }
        if (comPonto1.y > ePonto2.y) {
            int aux = comPonto1.y;
            comPonto1.y = ePonto2.y;
            ePonto2.y = aux;
        }
        Graphics2D grafico = naImagem.createGraphics();
        grafico.setColor(Color.BLACK);
        Stroke dashed1 = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{5}, 0);
        grafico.setStroke(dashed1);
        grafico.drawRect(comPonto1.x, comPonto1.y, ePonto2.x - comPonto1.x, ePonto2.y - comPonto1.y);
        grafico.setColor(Color.LIGHT_GRAY);
        Stroke dashed2 = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{5}, 5);
        grafico.setStroke(dashed2);
        grafico.drawRect(comPonto1.x, comPonto1.y, ePonto2.x - comPonto1.x, ePonto2.y - comPonto1.y);
        grafico.dispose();
        return naImagem;
    }
}
