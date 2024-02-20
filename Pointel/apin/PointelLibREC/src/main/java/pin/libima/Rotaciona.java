package pin.libima;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Rotaciona {

    public static BufferedImage faz(BufferedImage naImagem, Double comAngulo) {
        Double rad = Math.toRadians(comAngulo);
        Rectangle2D rct = new Rectangle2D.Double(0, 0, naImagem.getWidth(), naImagem.getHeight());
        AffineTransform atr = AffineTransform.getRotateInstance(rad, rct.getWidth() / 2.0, rct.getHeight() / 2.0);
        Shape shp = atr.createTransformedShape(rct);
        Double ladL = shp.getBounds().getWidth();
        Double ladA = shp.getBounds().getHeight();
        AffineTransform at = AffineTransform.getRotateInstance(rad, ladL / 2.0, ladA / 2.0);
        double tx = 0;
        double ty = 0;
        tx += (ladL - naImagem.getWidth()) / 2.0;
        ty += (ladA - naImagem.getHeight()) / 2.0;
        at.translate(tx, ty);
        BufferedImage retorno = new BufferedImage(ladL.intValue() + 1, ladA.intValue() + 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gr = retorno.createGraphics();
        gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gr.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        gr.drawImage(naImagem, at, null);
        gr.dispose();
        return retorno;
    }

}
