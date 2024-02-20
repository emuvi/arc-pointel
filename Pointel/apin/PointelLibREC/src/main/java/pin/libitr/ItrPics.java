package pin.libitr;

import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import pin.libout.CompoundIcon;
import pin.libout.TextIcon;
import pin.libutl.Utl;

public class ItrPics {

    public static ImageIcon pega(String doNome) {
        ImageIcon retorno = null;
        try {
            URL res = ItrPics.class.getResource(doNome);
            retorno = new ImageIcon(res);
        } catch (Exception ex) {
            Utl.registra(ex);
        }
        return retorno;
    }

    public static BufferedImage pegaIma(String doNome) {
        BufferedImage retorno = null;
        try {
            URL res = ItrPics.class.getResource(doNome);
            retorno = ImageIO.read(res);
        } catch (Exception ex) {
            Utl.registra(ex);
        }
        return retorno;
    }

    public static URL pegaRec(String doNome) {
        URL retorno = null;
        try {
            retorno = ItrPics.class.getResource(doNome);
        } catch (Exception ex) {
            Utl.registra(ex);
        }
        return retorno;
    }

    public static void botaPic(JComponent noComponente, String comNome, String eTitulo) {
        try {
            CompoundIcon icone = new CompoundIcon(CompoundIcon.Axis.Y_AXIS, 3, pega(comNome), new TextIcon(noComponente, eTitulo));
            if (noComponente instanceof JButton) {
                ((JButton) noComponente).setIcon(icone);
                ((JButton) noComponente).setFocusPainted(false);
            } else if (noComponente instanceof JLabel) {
                ((JLabel) noComponente).setIcon(icone);
            }
        } catch (Exception ex) {
            Utl.imp("Erro ao Botar Pim " + comNome + ": " + ex.getMessage());
        }
    }

    public static void botaPic(JComponent noComponente, String comNome) {
        try {
            ImageIcon icone = pega(comNome);
            if (noComponente.getClass().getName().equals("javax.swing.JButton")) {
                ((JButton) noComponente).setIcon(icone);
                ((JButton) noComponente).setFocusPainted(false);
            } else if (noComponente.getClass().getName().equals("javax.swing.JLabel")) {
                ((JLabel) noComponente).setIcon(icone);
            }
        } catch (Exception ex) {
            Utl.imp("Erro ao Botar Pim " + comNome + ": " + ex.getMessage());
        }
    }

}
