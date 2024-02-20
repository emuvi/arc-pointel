package pin.libout;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;

class Page implements Printable {

    BufferedImage toPrint;

    public Page(BufferedImage toPrint) {
        super();
        this.toPrint = toPrint;
    }

    public int print(Graphics g, PageFormat pf, int pageIndex) {
        if (toPrint != null) {
            Graphics2D gra = (Graphics2D) g;
            gra.translate((int) (pf.getImageableX()), (int) (pf.getImageableY()));
            Double pageWidth = pf.getImageableWidth();
            Double pageHeight = pf.getImageableHeight();
            gra.drawImage(toPrint.getScaledInstance(pageWidth.intValue(), pageHeight.intValue(), Image.SCALE_SMOOTH), 0, 0, null);
            return Printable.PAGE_EXISTS;
        }
        return Printable.NO_SUCH_PAGE;
    }
}
