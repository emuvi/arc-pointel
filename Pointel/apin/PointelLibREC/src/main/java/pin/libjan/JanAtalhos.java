package pin.libjan;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import pin.libbas.Configs;
import pin.libbas.Globais;
import pin.libutl.UtlCrs;
import pin.libutl.UtlTex;

public class JanAtalhos {

    private static Configs configs = null;

    public static Boolean bota(String comNome, String oAtalho) {
        if (configs == null) {
            configs = (Configs) Globais.pega("mainConf");
        }
        if (configs != null) {
            configs.botaCrs("Atalho " + comNome, oAtalho);
            return true;
        }
        return false;
    }

    public static String pega(String comNome, String ePadrao) {
        if (configs == null) {
            configs = (Configs) Globais.pega("mainConf");
        }
        if (configs != null) {
            String atalho = configs.pegaCrs("Atalho " + comNome, ePadrao);
            if (UtlCrs.vazio(atalho)) {
                atalho = ePadrao;
            }
            return atalho;
        } else {
            return ePadrao;
        }
    }

    private static void botaAtalhos(Component[] dosComponentes, List<String> naLista) {
        if (dosComponentes != null) {
            for (Component cmp : dosComponentes) {
                if (cmp instanceof JComponent) {
                    InputMap iMap = ((JComponent) cmp).getInputMap(JComponent.WHEN_FOCUSED);
                    if (iMap != null) {
                        if (iMap.size() > 0) {
                            for (KeyStroke kst : iMap.allKeys()) {
                                String comando = iMap.get(kst).toString() + " : " + UtlCrs.troca(kst.toString(), "pressed ", "");
                                String clsn = cmp.getClass().getSimpleName();
                                comando = clsn + "." + comando;
                                if (!naLista.contains(comando)) {
                                    naLista.add(comando);
                                }
                            }
                        }
                    }
                    botaAtalhos(((JComponent) cmp).getComponents(), naLista);
                }

            }
        }
    }

    public static void mostraAtalhos(JFrame daJanela) {
        List<String> comandos = new ArrayList<>();
        InputMap iMap = daJanela.getRootPane().getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        if (iMap != null) {
            if (iMap.size() > 0) {
                for (KeyStroke kst : iMap.allKeys()) {
                    String comando = "JFrame." + iMap.get(kst).toString() + " : " + UtlCrs.troca(kst.toString(), "pressed ", "");
                    if (!comandos.contains(comando)) {
                        comandos.add(comando);
                    }
                }
            }
        }
        botaAtalhos(daJanela.getComponents(), comandos);
        Collections.sort(comandos);
        String atalhos = "Atalhos da Janela " + daJanela.getTitle() + UtlTex.quebra();
        for (String cmd : comandos) {
            atalhos += UtlTex.quebra() + cmd;
        }
        JFrame mostrador = new JFrame("Atalhos da Janela " + daJanela.getTitle());
        ((JPanel) mostrador.getContentPane()).setBorder(new EmptyBorder(12, 12, 12, 12));
        mostrador.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mostrador.setLayout(new BorderLayout());
        mostrador.setLocationRelativeTo(null);
        Dimension dim = new Dimension(600, 400);
        mostrador.setPreferredSize(dim);
        mostrador.setSize(dim);
        JTextArea jtaAtalhos = new JTextArea(atalhos);
        jtaAtalhos.setEditable(false);
        JScrollPane jspAtalhos = new JScrollPane(jtaAtalhos);
        mostrador.add(jspAtalhos, BorderLayout.CENTER);
        Janelas.inicia(mostrador);
        Janelas.mostra(mostrador);
    }

}
