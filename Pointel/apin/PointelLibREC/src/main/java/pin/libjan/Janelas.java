package pin.libjan;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.JTextComponent;

import pin.libbas.Configs;
import pin.libbas.Globais;
import pin.libutl.Utl;
import pin.libutl.UtlCrs;

public class Janelas {

    private static List<JanTutela> jansOrdenadas = new ArrayList<>();

    public static void limpa() {
        for (int ij = jansOrdenadas.size() - 1; ij >= 0; ij--) {
            if (!jansOrdenadas.get(ij).janela().isDisplayable()) {
                jansOrdenadas.remove(ij);
            }
        }
    }

    public static JFrame pJanela(Class daClasse) {
        limpa();
        for (JanTutela jrl : jansOrdenadas) {
            if (jrl.eh(daClasse)) {
                return jrl.janela();
            }
        }
        return null;
    }

    public static List<JFrame> pJanelas(Class daClasse) {
        limpa();
        List<JFrame> retorno = new ArrayList<>();
        for (JanTutela jrl : jansOrdenadas) {
            if (jrl.eh(daClasse)) {
                retorno.add(jrl.janela());
            }
        }
        return retorno;
    }

    public static JanTutela pTutela(JFrame daJanela) {
        limpa();
        for (JanTutela jrl : jansOrdenadas) {
            if (jrl.janela().equals(daJanela)) {
                return jrl;
            }
        }
        return null;
    }

    public static JanTutela pTutela(Class daClasse) {
        limpa();
        for (JanTutela jrl : jansOrdenadas) {
            if (jrl.eh(daClasse)) {
                return jrl;
            }
        }
        return null;
    }

    public static List<JanTutela> pTutelas(Class daClasse) {
        limpa();
        List<JanTutela> retorno = new ArrayList<>();
        for (JanTutela jrl : jansOrdenadas) {
            if (jrl.eh(daClasse)) {
                retorno.add(jrl);
            }
        }
        return retorno;
    }

    public static List<JanTutela> pTutelas() {
        limpa();
        return jansOrdenadas;
    }

    public static void abrePosicionamento(JFrame naJanela, Integer comPosX, Integer comPosY, Integer comLargura,
            Integer comAltura) {
        Configs dasCfgs = ((Configs) Globais.pega("mainConf"));
        if (dasCfgs != null) {
            int iniX = 0;
            int iniY = 0;
            Rectangle mainArea = (Rectangle) Globais.pega("mainArea");
            if (mainArea != null) {
                iniX = mainArea.x;
                iniY = mainArea.y;
            }
            naJanela.setLocationRelativeTo(null);
            naJanela.setLocationByPlatform(true);
            Integer posX = dasCfgs.pegaInt("Janela " + naJanela.getTitle() + " - PosX", comPosX) + iniX;
            Integer posY = dasCfgs.pegaInt("Janela " + naJanela.getTitle() + " - PosY", comPosY) + iniY;
            if ((posX != null) && (posY != null)) {
                naJanela.setLocation(posX, posY);
            }
            if (naJanela.isResizable()) {
                Integer largura = dasCfgs.pegaInt("Janela " + naJanela.getTitle() + " - Width", comLargura);
                Integer altura = dasCfgs.pegaInt("Janela " + naJanela.getTitle() + " - Height", comAltura);
                if ((largura != null) && (altura != null)) {
                    naJanela.setSize(largura, altura);
                }
                switch (dasCfgs.pegaCrs("Janela " + naJanela.getTitle() + " - PosEstado", "NORMAL")) {
                    case "MAXIMIZED_BOTH":
                        naJanela.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        break;
                    case "MAXIMIZED_HORIZ":
                        naJanela.setExtendedState(JFrame.MAXIMIZED_HORIZ);
                        break;
                    case "MAXIMIZED_VERT":
                        naJanela.setExtendedState(JFrame.MAXIMIZED_VERT);
                        break;
                    default:
                        naJanela.setExtendedState(JFrame.NORMAL);
                        break;
                }
            }
        }
    }

    public static void salvaPosicionamento(JFrame daJanela) {
        Configs nasCfgs = (Configs) Globais.pega("mainConf");
        if (nasCfgs != null) {
            int iniX = 0;
            int iniY = 0;
            Rectangle mainArea = (Rectangle) Globais.pega("mainArea");
            if (mainArea != null) {
                iniX = mainArea.x;
                iniY = mainArea.y;
            }
            if (!daJanela.isResizable()) {
                nasCfgs.botaCrs("Janela " + daJanela.getTitle() + " - PosEstado", "NORMAL");
                nasCfgs.botaInt("Janela " + daJanela.getTitle() + " - PosX", daJanela.getLocation().x - iniX);
                nasCfgs.botaInt("Janela " + daJanela.getTitle() + " - PosY", daJanela.getLocation().y - iniY);
            } else {
                switch (daJanela.getExtendedState()) {
                    case JFrame.MAXIMIZED_BOTH:
                        nasCfgs.botaCrs("Janela " + daJanela.getTitle() + " - PosEstado", "MAXIMIZED_BOTH");
                        break;
                    case JFrame.MAXIMIZED_HORIZ:
                        nasCfgs.botaCrs("Janela " + daJanela.getTitle() + " - PosEstado", "MAXIMIZED_HORIZ");
                        break;
                    case JFrame.MAXIMIZED_VERT:
                        nasCfgs.botaCrs("Janela " + daJanela.getTitle() + " - PosEstado", "MAXIMIZED_VERT");
                        break;
                    case JFrame.NORMAL:
                        nasCfgs.botaCrs("Janela " + daJanela.getTitle() + " - PosEstado", "NORMAL");
                        nasCfgs.botaInt("Janela " + daJanela.getTitle() + " - PosX", daJanela.getLocation().x - iniX);
                        nasCfgs.botaInt("Janela " + daJanela.getTitle() + " - PosY", daJanela.getLocation().y - iniY);
                        nasCfgs.botaInt("Janela " + daJanela.getTitle() + " - Width", daJanela.getSize().width);
                        nasCfgs.botaInt("Janela " + daJanela.getTitle() + " - Height", daJanela.getSize().height);
                        break;
                }
            }
        }
    }

    public static void botaAjuda(JFrame naJanela, String paraEndereco) {
        Janelas.botaAtalho(naJanela, "PointelHelp - Procurar Ajuda no Endereço", "F1", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(paraEndereco));
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        });
    }

    public static void botaAtalho(JFrame naJanela, String comNome, String oAtalho, Action aAcao) {
        if (!UtlCrs.vazio(comNome)) {
            String atalho = JanAtalhos.pega(comNome, oAtalho);
            if (!UtlCrs.vazio(atalho)) {
                ActionMap aMap = naJanela.getRootPane().getActionMap();
                InputMap iMap = naJanela.getRootPane().getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
                aMap.put(comNome, aAcao);
                iMap.put(KeyStroke.getKeyStroke(atalho), comNome);
            }
        }
    }

    public static void botaAtalho(JDialog naJanela, String comNome, String oAtalho, Action aAcao) {
        if (!UtlCrs.vazio(comNome)) {
            String atalho = JanAtalhos.pega(comNome, oAtalho);
            if (!UtlCrs.vazio(atalho)) {
                ActionMap aMap = naJanela.getRootPane().getActionMap();
                InputMap iMap = naJanela.getRootPane().getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
                aMap.put(comNome, aAcao);
                iMap.put(KeyStroke.getKeyStroke(atalho), comNome);
            }
        }
    }

    public static void botaAtalho(JComponent noComponente, String comNome, String oAtalho, Action aAcao) {
        if (!UtlCrs.vazio(comNome)) {
            if (noComponente != null) {
                String atalho = JanAtalhos.pega(comNome, oAtalho);
                if (!UtlCrs.vazio(atalho)) {
                    ActionMap aMap = noComponente.getActionMap();
                    InputMap iMap = noComponente.getInputMap(JComponent.WHEN_FOCUSED);
                    aMap.put(comNome, aAcao);
                    iMap.put(KeyStroke.getKeyStroke(atalho), comNome);
                }
            }
        }
    }

    public static void botaAtalhoEMenu(JFrame naJanela, PopMenu doMenu, String comNome, String eTitulo, String oAtalho,
            AbstractAction aAcao) {
        botaAtalho(naJanela, comNome, oAtalho, aAcao);
        Character pAtalho = null;
        if (oAtalho.length() > 1) {
            pAtalho = oAtalho.charAt(0);
        }
        doMenu.bota(eTitulo, pAtalho, aAcao);
    }

    public static void execWindowClosed(JFrame aJanela) {
        aJanela.dispatchEvent(new WindowEvent(aJanela, WindowEvent.WINDOW_CLOSED));
    }

    public static void execWindowClosed(JDialog aJanela) {
        aJanela.dispatchEvent(new WindowEvent(aJanela, WindowEvent.WINDOW_CLOSED));
    }

    public static void execWindowClosing(JFrame aJanela) {
        aJanela.dispatchEvent(new WindowEvent(aJanela, WindowEvent.WINDOW_CLOSING));
    }

    public static void execWindowClosing(JDialog aJanela) {
        aJanela.dispatchEvent(new WindowEvent(aJanela, WindowEvent.WINDOW_CLOSING));
    }

    public static void abreEdt(JComponent oEditor) {
        if (oEditor != null) {
            try {
                Method met = oEditor.getClass().getMethod("abreEdt");
                met.invoke(oEditor);
            } catch (Exception ex) {
            }
        }
    }

    public static void abreEdt(Component[] dosComponentes) {
        if (dosComponentes != null) {
            for (Component cmp : dosComponentes) {
                if (cmp instanceof JComponent) {
                    abreEdt(((JComponent) cmp).getComponents());
                    abreEdt((JComponent) cmp);
                }
            }
        }
    }

    public static void abreEdt(JFrame daJanela) {
        abreEdt(daJanela.getComponents());
    }

    public static void abreEdt(JDialog daJanela) {
        abreEdt(daJanela.getComponents());
    }

    public static void fechaEdt(JComponent oEditor) {
        if (oEditor != null) {
            try {
                Method met = oEditor.getClass().getMethod("fechaEdt");
                met.invoke(oEditor);
            } catch (Exception ex) {
            }
        }
    }

    public static void fechaEdt(Component[] dosComponentes) {
        if (dosComponentes != null) {
            for (Component cmp : dosComponentes) {
                if (cmp instanceof JComponent) {
                    fechaEdt(((JComponent) cmp).getComponents());
                    fechaEdt((JComponent) cmp);
                }
            }
        }
    }

    public static void fechaEdt(JFrame daJanela) {
        fechaEdt(daJanela.getComponents());
    }

    public static void fechaEdt(JDialog daJanela) {
        fechaEdt(daJanela.getComponents());
    }

    public static Boolean fecha(JFrame aJanela) {
        if (aJanela.getDefaultCloseOperation() != JFrame.DO_NOTHING_ON_CLOSE) {
            execWindowClosing(aJanela);
            aJanela.setVisible(false);
            if (aJanela.getDefaultCloseOperation() == JFrame.DISPOSE_ON_CLOSE) {
                aJanela.dispose();
            }
            execWindowClosed(aJanela);
            return true;
        }
        return false;
    }

    public static Boolean fecha(JDialog aJanela) {
        if (aJanela.getDefaultCloseOperation() != JFrame.DO_NOTHING_ON_CLOSE) {
            execWindowClosing(aJanela);
            aJanela.setVisible(false);
            execWindowClosed(aJanela);
            return true;
        }
        return false;
    }

    public static void botaAtivacao(JFrame naJanela) {
        naJanela.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                JanTutela jrl = Janelas.pTutela(naJanela);
                if (jrl != null) {
                    int ord = jansOrdenadas.indexOf(jrl);
                    if (ord > -1) {
                        if (ord < jansOrdenadas.size() - 1) {
                            for (int io = ord; io < jansOrdenadas.size() - 1; io++) {
                                jansOrdenadas.set(io, jansOrdenadas.get(io + 1));
                            }
                            jansOrdenadas.set(jansOrdenadas.size() - 1, jrl);
                        }
                    }
                }
                JanEspeciais janIntf = (JanEspeciais) Globais.pega("janelasIntf");
                if (janIntf != null) {
                    janIntf.atualizaListaJanelas();
                }
            }
        });
    }

    private static volatile Boolean iniciadoDsg = false;

    public static void iniciaJFrm() {
        if (!iniciadoDsg) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ex) {
                Utl.registra(ex);
            } finally {
                iniciadoDsg = true;
            }
        }

    }

    public static void salvaPosicionamentos() {
        for (Frame frm : JFrame.getFrames()) {
            if (frm instanceof JFrame) {
                JFrame jfrm = (JFrame) frm;
                if (jfrm.isVisible()) {
                    salvaPosicionamento(jfrm);
                }
            }
        }
    }

    private static BufferedImage iconePadrao = null;

    public static BufferedImage pIconePadrao() {
        return iconePadrao;
    }

    public static void mIconePadrao(BufferedImage para) {
        iconePadrao = para;
    }

    public static void inicia(JDialog aJanela) {
        inicia(aJanela, aJanela);
    }

    public static void inicia(JDialog aJanela, Object comRelacao) {
        iniciaJFrm();
        aJanela.pack();
        TrataIntf.botaNoCentroDoMonitorDoMouse(aJanela);
        botaAtalho(aJanela, "Janela - Fechar", "ESCAPE", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Janelas.fecha(aJanela);
            }
        });
        JanEspeciais janIntf = (JanEspeciais) Globais.pega("janelasIntf");
        if (janIntf != null) {
            janIntf.botaIcone(aJanela, comRelacao.getClass());
            janIntf.botaAtalhos(aJanela, comRelacao.getClass());
        }
        Boolean deve = false;
        if (aJanela.getIconImages() == null) {
            deve = true;
        } else {
            deve = aJanela.getIconImages().isEmpty();
        }
        if (deve) {
            aJanela.setIconImage(iconePadrao);
        }
        abreEdt(aJanela);
        aJanela.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (aJanela.getDefaultCloseOperation() == JFrame.DISPOSE_ON_CLOSE) {
                    fechaEdt(aJanela);
                }
                if (janIntf != null) {
                    janIntf.atualizaListaJanelas();
                }
            }
        });
    }

    public static void inicia(JFrame aJanela) {
        inicia(aJanela, aJanela);
    }

    public static void inicia(JFrame aJanela, Object comRelacao) {
        iniciaJFrm();
        aJanela.pack();
        int iniX = 45;
        int iniY = 45;
        limpa();
        for (JanTutela jnr : jansOrdenadas) {
            if (jnr.janela().getLocation().x >= iniX) {
                iniX = jnr.janela().getLocation().x + 45;
            }
            if (jnr.janela().getLocation().y >= iniY) {
                iniY = jnr.janela().getLocation().y + 45;
            }
        }
        Rectangle mainArea = (Rectangle) Globais.pega("mainArea");
        if (mainArea == null) {
            mainArea = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        }
        if (mainArea.x + iniX + aJanela.getWidth() + 45 > mainArea.width) {
            iniX = mainArea.width - (aJanela.getWidth() + mainArea.x + 45);
        }
        if (mainArea.y + iniY + aJanela.getHeight() + 45 > mainArea.height) {
            iniY = mainArea.height - (aJanela.getHeight() + mainArea.y + 45);
        }
        JanTutela janRel = new JanTutela(aJanela, comRelacao);
        jansOrdenadas.add(janRel);
        botaAtivacao(aJanela);
        botaAtalho(aJanela, "Janela - Fechar", "ESCAPE", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Janelas.fecha(aJanela);
            }
        });
        JanEspeciais janIntf = (JanEspeciais) Globais.pega("janelasIntf");
        if (janIntf != null) {
            janIntf.botaIcone(aJanela, comRelacao.getClass());
            janIntf.botaAtalhos(aJanela, comRelacao.getClass());
        }
        if (aJanela.getIconImage() == null) {
            aJanela.setIconImage(iconePadrao);
        }
        abrePosicionamento(aJanela, iniX, iniY, aJanela.getWidth(), aJanela.getHeight());
        abreEdt(aJanela);
        aJanela.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                salvaPosicionamento(aJanela);
                if (aJanela.getDefaultCloseOperation() == JFrame.DISPOSE_ON_CLOSE) {
                    fechaEdt(aJanela);
                    jansOrdenadas.remove(janRel);
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (janIntf != null) {
                    janIntf.atualizaListaJanelas();
                }
            }
        });
    }

    public static void botaQuadro(JDialog naJanela) {
        naJanela.setUndecorated(true);
        naJanela.getRootPane().setBorder(BorderFactory.createLineBorder(naJanela.getForeground(), 1, false));
        botaAutoMovedor(naJanela);
    }

    public static void botaQuadro(JFrame naJanela) {
        naJanela.setUndecorated(true);
        naJanela.getRootPane().setBorder(BorderFactory.createLineBorder(naJanela.getForeground(), 1, false));
        botaAutoMovedor(naJanela);
    }

    public static void botaAutoMovedor(Window paraJanela) {
        paraJanela.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                MouseAdapter mod = new MouseAdapter() {
                    private Point ini = paraJanela.getLocation();
                    private Point prs = null;

                    @Override
                    public void mousePressed(MouseEvent e) {
                        prs = e.getLocationOnScreen();
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        prs = null;
                    }

                    @Override
                    public void mouseDragged(MouseEvent e) {
                        if (prs != null) {
                            int dx = e.getLocationOnScreen().x - prs.x;
                            int dy = e.getLocationOnScreen().y - prs.y;
                            paraJanela.setLocation(ini.x + dx, ini.y + dy);
                        }
                    }
                };
                paraJanela.addMouseListener(mod);
                paraJanela.addMouseMotionListener(mod);
            }
        });
    }

    public static void mostra(JDialog aJanela) {
        mostra(aJanela, false);
    }

    public static void mostra(JDialog aJanela, Boolean garanteFoco) {
        if (garanteFoco) {
            aJanela.addWindowListener(new WindowAdapter() {
                private Boolean primeiro = true;

                @Override
                public void windowActivated(WindowEvent e) {
                    if (primeiro) {
                        primeiro = false;
                        aJanela.toFront();
                        List<Component> txtCmps = TrataIntf.procura(aJanela, JTextComponent.class);
                        if (!txtCmps.isEmpty()) {
                            TrataIntf.garanteFoco((JComponent) txtCmps.get(0));
                        }
                    }
                }
            });
        }
        aJanela.setVisible(true);
    }

    public static void mostra(JFrame aJanela) {
        mostra(aJanela, false);
    }

    public static void mostra(JFrame aJanela, Boolean garanteFoco) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                aJanela.setVisible(true);
                if (aJanela.getExtendedState() == JFrame.ICONIFIED) {
                    aJanela.setExtendedState(JFrame.NORMAL);
                }
                aJanela.toFront();
                aJanela.requestFocusInWindow();
                if (garanteFoco) {
                    aJanela.addWindowListener(new WindowAdapter() {
                        private Boolean primeiro = true;

                        @Override
                        public void windowActivated(WindowEvent e) {
                            if (primeiro) {
                                primeiro = false;
                                aJanela.toFront();
                                aJanela.requestFocusInWindow();
                                List<Component> txtCmps = TrataIntf.procura(aJanela, JTextComponent.class);
                                if (!txtCmps.isEmpty()) {
                                    TrataIntf.garanteFoco((JComponent) txtCmps.get(0));
                                } else {
                                    TrataIntf.garanteFoco(aJanela);
                                }
                            }
                        }
                    });
                }
            }
        });
    }

}
