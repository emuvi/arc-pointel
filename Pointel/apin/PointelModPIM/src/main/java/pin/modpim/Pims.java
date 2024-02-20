package pin.modpim;

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

public class Pims {

    public static ImageIcon pega(String doNome) {
        try {
            URL res = Pims.class.getResource(doNome);
            return new ImageIcon(res);
        } catch (Exception ex) {
            Utl.imp("Erro ao Pegar Pim " + doNome + ": " + ex.getMessage());
        }
        return null;
    }

    public static BufferedImage pegaIma(String doNome) {
        try {
            URL res = Pims.class.getResource(doNome);
            return ImageIO.read(res);
        } catch (Exception ex) {
            Utl.imp("Erro ao Pegar Pim " + doNome + ": " + ex.getMessage());
        }
        return null;
    }

    public static URL pegaRec(String doNome) {
        URL retorno = null;
        try {
            retorno = Pims.class.getResource(doNome);
        } catch (Exception ex) {
            Utl.imp("Erro ao Ler PimRec " + doNome + ": " + ex.getMessage());
        }
        return retorno;
    }

    public static void botaPim(JComponent noComponente, String comNome, String eTitulo) {
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

    public static void botaPim(JComponent noComponente, String comNome) {
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
