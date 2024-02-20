package pin.libutl;

import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.KeyStroke;

public class UtlRob {

    private static Robot mecanismo = null;
    public static Integer intertempo = 300;

    private static void inicia() throws Exception {
        if (mecanismo == null) {
            mecanismo = new Robot();
        }
    }

    public static BufferedImage pegaTela() throws Exception {
        inicia();
        return mecanismo.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
    }

    public static BufferedImage pegaTela(Integer daPosX, Integer ePosY, Integer comLargura, Integer eAltura) throws Exception {
        inicia();
        return mecanismo.createScreenCapture(new Rectangle(daPosX, ePosY, comLargura, eAltura));
    }

    public static Color pegaTela(Integer daPosX, Integer ePosY) throws Exception {
        inicia();
        return mecanismo.getPixelColor(daPosX, ePosY);
    }

    public static Point pegaMouse() {
        return MouseInfo.getPointerInfo().getLocation();
    }

    public static Boolean eh(Color aCor, Integer naPosX, Integer ePosY) throws Exception {
        return eh(aCor, naPosX, ePosY, 0.0);
    }

    public static Boolean eh(Color aCor, Integer naPosX, Integer ePosY, Double toleranciaPer) throws Exception {
        Color cor = pegaTela(naPosX, ePosY);
        return UtlCor.diferencaPer(cor, aCor) <= toleranciaPer;
    }

    public static void aperta(Integer naPosX, Integer ePosY) throws Exception {
        aperta(naPosX, ePosY, false);
    }

    public static void aperta(Integer naPosX, Integer ePosY, Boolean botaoEsquerdo) throws Exception {
        inicia();
        mecanismo.mouseMove(naPosX, ePosY);
        int codbot = InputEvent.BUTTON1_MASK;
        if (botaoEsquerdo) {
            codbot = InputEvent.BUTTON3_MASK;
        }
        mecanismo.mousePress(codbot);
    }

    public static void solta(Integer naPosX, Integer ePosY) throws Exception {
        solta(naPosX, ePosY, false);
    }

    public static void solta(Integer naPosX, Integer ePosY, Boolean botaoEsquerdo) throws Exception {
        inicia();
        mecanismo.mouseMove(naPosX, ePosY);
        int codbot = InputEvent.BUTTON1_MASK;
        if (botaoEsquerdo) {
            codbot = InputEvent.BUTTON3_MASK;
        }
        mecanismo.mouseRelease(codbot);
    }

    public static void move(Integer paraPosX, Integer ePosY) throws Exception {
        inicia();
        mecanismo.mouseMove(paraPosX, ePosY);
    }

    public static void cliqua(Integer naPosX, Integer ePosY) throws Exception {
        cliqua(naPosX, ePosY, 1, false);
    }

    public static void cliqua(Integer naPosX, Integer ePosY, Integer vezes) throws Exception {
        cliqua(naPosX, ePosY, vezes, false);
    }

    public static void cliqua(Integer naPosX, Integer ePosY, Integer vezes, Boolean botaoEsquerdo) throws Exception {
        inicia();
        for (int iv = 0; iv < vezes; iv++) {
            if (iv == 0) {
                mecanismo.mouseMove(naPosX, ePosY);
            }
            int codbot = InputEvent.BUTTON1_MASK;
            if (!botaoEsquerdo) {
                codbot = InputEvent.BUTTON3_MASK;
            }
            mecanismo.mousePress(codbot);
            UtlBin.esperaMilis(intertempo);
            mecanismo.mouseRelease(codbot);
            UtlBin.esperaMilis(intertempo);
        }
    }

    public static void cliquaDuplo(Integer naPosX, Integer ePosY) throws Exception {
        cliqua(naPosX, ePosY, 2);
    }

    public static void aperta(String aTecla) throws Exception {
        inicia();
        String mods = aTecla.toLowerCase();
        String chave = aTecla.toUpperCase();
        if (chave.contains(" ")) {
            chave = chave.substring(chave.lastIndexOf(" ") + 1);
        }
        if (mods.contains("control")) {
            mecanismo.keyPress(KeyEvent.VK_CONTROL);
        }
        if (mods.contains("shift")) {
            mecanismo.keyPress(KeyEvent.VK_SHIFT);
        }
        if (mods.contains("alt")) {
            mecanismo.keyPress(KeyEvent.VK_ALT);
        }
        KeyStroke kst = KeyStroke.getKeyStroke(chave);
        mecanismo.keyPress(kst.getKeyCode());
    }

    public static void solta(String aTecla) throws Exception {
        inicia();
        String mods = aTecla.toLowerCase();
        String chave = aTecla.toUpperCase();
        if (chave.contains(" ")) {
            chave = chave.substring(chave.lastIndexOf(" ") + 1);
        }
        KeyStroke kst = KeyStroke.getKeyStroke(chave);
        mecanismo.keyRelease(kst.getKeyCode());
        if (mods.contains("control")) {
            mecanismo.keyRelease(KeyEvent.VK_CONTROL);
        }
        if (mods.contains("shift")) {
            mecanismo.keyRelease(KeyEvent.VK_SHIFT);
        }
        if (mods.contains("alt")) {
            mecanismo.keyRelease(KeyEvent.VK_ALT);
        }
    }

    public static void pressiona(String aTecla) throws Exception {
        inicia();
        aperta(aTecla);
        UtlBin.esperaMilis(intertempo);
        solta(aTecla);
        UtlBin.esperaMilis(intertempo);
    }

    public static void digita(String osCaracteres) throws Exception {
        for (int ic = 0; ic < osCaracteres.length(); ic++) {
            String tecla = "" + osCaracteres.charAt(ic);
            if (Character.isUpperCase(osCaracteres.charAt(ic))) {
                tecla = "shift " + tecla;
            }
            pressiona(tecla);
        }
    }
}
