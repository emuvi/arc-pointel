package pin.libpic;

import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Pics {

    public static ImageIcon pegaAcima() {
        return pega("arrow_up.png");
    }

    public static ImageIcon pegaAbaixo() {
        return pega("arrow_down.png");
    }

    public static ImageIcon pEsquerda() {
        return pega("arrow_left.png");
    }

    public static ImageIcon pDireita() {
        return pega("arrow_right.png");
    }

    public static ImageIcon pegaNovo() {
        return pega("filenew2.png");
    }

    public static ImageIcon pBota() {
        return pega("add.png");
    }

    public static ImageIcon pegaAbrir() {
        return pega("folder_table.png");
    }

    public static ImageIcon pegaEdita() {
        return pega("pencil.png");
    }

    public static ImageIcon pTira() {
        return pega("delete.png"); //logout
    }

    public static ImageIcon pLimpa() {
        return pega("logout.png");
    }

    public static ImageIcon pegaSalvar() {
        return pega("disk.png");
    }

    public static ImageIcon pegaConfirma() {
        return pega("accept.png");
    }

    public static ImageIcon pegaCancela() {
        return pega("cancel.png");
    }

    public static ImageIcon pegaAtualizar() {
        return pega("arrow_rotate_clockwise.png");
    }

    public static ImageIcon pMagica() {
        return pega("wand.png");
    }

    public static ImageIcon pegaProcessar() {
        return pega("cog.png");
    }

    public static ImageIcon pegaTratamento() {
        return pega("cut_red.png");
    }

    public static ImageIcon pegaTestar() {
        return pega("asterisk_orange.png");
    }

    public static ImageIcon pFechar() {
        return pega("button_cancel.png");
    }

    public static ImageIcon pega(String doNome) {
        ImageIcon retorno = null;
        try {
            URL res = Pics.class.getResource(doNome);
            retorno = new ImageIcon(res);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return retorno;
    }

    public static BufferedImage pegaIma(String doNome) {
        BufferedImage retorno = null;
        try {
            URL res = Pics.class.getResource(doNome);
            retorno = ImageIO.read(res);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return retorno;
    }

    public static URL pegaRec(String doNome) {
        URL retorno = null;
        try {
            retorno = Pics.class.getResource(doNome);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return retorno;
    }

}
