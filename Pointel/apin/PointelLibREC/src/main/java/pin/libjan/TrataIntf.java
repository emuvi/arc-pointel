package pin.libjan;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JTree;
import javax.swing.JViewport;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.text.JTextComponent;
import pin.libbas.Globais;
import pin.libutl.Utl;
import pin.libutl.UtlCrs;
import pin.libutl.UtlTex;
import pin.libvlr.Vlr;

public class TrataIntf {

    public static Object[] pegaOpcoes(Class daClasse) {
        if (daClasse != null) {
            List<Object> lst = new ArrayList<>();
            Iterator ite = EnumSet.allOf(daClasse).iterator();
            while (ite.hasNext()) {
                lst.add(ite.next());
            }
            return lst.toArray();
        }
        return null;
    }

    public static Object[] pegaOpcoesCrs(Class daClasse) {
        if (daClasse != null) {
            List<String> lst = new ArrayList<>();
            Iterator ite = EnumSet.allOf(daClasse).iterator();
            while (ite.hasNext()) {
                lst.add(ite.next().toString());
            }
            return lst.toArray();
        }
        return null;
    }

    public static void expandirToda(JTree aArvore) {
        int linhas = aArvore.getRowCount();
        for (int i1 = 0; i1 < linhas; i1++) {
            aArvore.expandRow(i1);
        }
        if (aArvore.getRowCount() > linhas) {
            expandirToda(aArvore);
        }
    }

    public static Object pegaValorDeCombo(JComboBox doCombo) {
        Object retorno = doCombo.getSelectedItem();

        if (retorno != null && !doCombo.isEditable()) {
            DefaultComboBoxModel dcm = ((DefaultComboBoxModel) doCombo.getModel());

            boolean ehChaveado = true;
            for (int ie = 0; ie < dcm.getSize(); ie++) {
                Object elm = dcm.getElementAt(ie);
                if (elm instanceof Vlr) {
                    elm = ((Vlr) elm).pg();
                }
                if (elm instanceof String) {
                    if (!((String) elm).startsWith("(")) {
                        ehChaveado = false;
                        break;
                    }
                } else {
                    ehChaveado = false;
                    break;
                }
            }

            if (ehChaveado) {
                retorno = UtlCrs.pegaEntreParenteses(UtlCrs.pega(retorno));
            }
        }

        return retorno;
    }

    public static void mudaValorDeCombo(JComboBox doCombo, Object para) {
        if (para instanceof Vlr) {
            para = ((Vlr) para).pg();
        }

        if (doCombo.isEditable()) {
            doCombo.setSelectedItem(para);
        } else {
            DefaultComboBoxModel dcm = ((DefaultComboBoxModel) doCombo.getModel());

            boolean ehChaveado = true;
            for (int ie = 0; ie < dcm.getSize(); ie++) {
                Object elm = dcm.getElementAt(ie);
                if (elm instanceof Vlr) {
                    elm = ((Vlr) elm).pg();
                }
                if (elm instanceof String) {
                    if (!((String) elm).startsWith("(")) {
                        ehChaveado = false;
                        break;
                    }
                } else {
                    ehChaveado = false;
                    break;
                }
            }

            int isel = -1;
            if (ehChaveado) {
                String pr = UtlCrs.pega(para);
                if (pr != null) {
                    for (int ie = 0; ie < dcm.getSize(); ie++) {
                        String elm = UtlCrs.pega(dcm.getElementAt(ie));
                        if (pr.equals(UtlCrs.pegaEntreParenteses(elm))) {
                            isel = ie;
                            break;
                        }
                    }
                }
            } else {
                for (int ie = 0; ie < dcm.getSize(); ie++) {
                    Object elm = dcm.getElementAt(ie);
                    if (Objects.equals(elm, para)) {
                        isel = ie;
                        break;
                    }
                }
            }

            doCombo.setSelectedIndex(isel);
        }
    }

    public static Object pegaValor(JComponent doComponente) {
        Object retorno = null;
        if (doComponente instanceof JTextArea) {
            retorno = ((JTextArea) doComponente).getText().getBytes();
        } else if (doComponente instanceof JFormattedTextField) {
            retorno = ((JFormattedTextField) doComponente).getValue();
        } else if (doComponente instanceof JTextField) {
            retorno = ((JTextField) doComponente).getText();
        } else if (doComponente instanceof JComboBox) {
            retorno = TrataIntf.pegaValorDeCombo((JComboBox) doComponente);
        } else if (doComponente instanceof JCheckBox) {
            retorno = (((JCheckBox) doComponente).isSelected() ? "S" : "N");
        }
        return retorno;
    }

    public static Boolean estaVazio(JComponent oComponente) {
        Boolean retorno = true;
        if (oComponente instanceof JTextArea) {
            retorno = ((JTextArea) oComponente).getText().isEmpty();
        } else if (oComponente instanceof JFormattedTextField) {
            retorno = ((JFormattedTextField) oComponente).getValue() == null;
        } else if (oComponente instanceof JTextField) {
            retorno = ((JTextField) oComponente).getText().isEmpty();
        } else if (oComponente instanceof JComboBox) {
            retorno = TrataIntf.pegaValorDeCombo((JComboBox) oComponente) == null;
        } else if (oComponente instanceof JCheckBox) {
            retorno = !((JCheckBox) oComponente).isSelected();
        }
        return retorno;
    }

    public static void botaNoCursor(JComponent doComponente, Object oValor) {
        if (doComponente instanceof JTextField) {
            botaNoCursor((JTextField) doComponente, (String) oValor);
        } else if (doComponente instanceof JTextArea) {
            botaNoCursor((JTextArea) doComponente, (String) oValor);
        }
    }

    public static void botaNoCursor(JTextField doComponente, String oTexto) {
        Integer iIni = doComponente.getSelectionStart();
        String antigo = doComponente.getText();
        String novo = antigo.substring(0, doComponente.getSelectionStart()) + oTexto + antigo.substring(doComponente.getSelectionEnd());
        doComponente.setText(novo);
        doComponente.setCaretPosition(iIni + oTexto.length());
    }

    public static void botaNoCursor(JTextArea doComponente, String oTexto) {
        Integer iIni = doComponente.getSelectionStart();
        String antigo = doComponente.getText();
        String novo = antigo.substring(0, doComponente.getSelectionStart()) + oTexto + antigo.substring(doComponente.getSelectionEnd());
        doComponente.setText(novo);
        doComponente.setCaretPosition(iIni + oTexto.length());
    }

    public static Boolean ehPaiSemLayout(JComponent doComponente) {
        if (doComponente != null) {
            if (doComponente.getParent() != null) {
                return doComponente.getParent().getLayout() == null;
            }
        }
        return false;
    }

    public static Dimension padraoDimensao(JComponent doComponente) {
        Dimension retorno = doComponente.getPreferredSize();
        if (retorno != null) {
            if (retorno.width == 0) {
                retorno.width = doComponente.getBounds().width;
            }
            if (retorno.height == 0) {
                retorno.height = doComponente.getBounds().height;
            }
        }
        return retorno;
    }

    public static Border padraoBorda(JComponent doComponente) {
        return UIManager.getBorder(doComponente.getClass().getSimpleName().substring(1) + ".border");
    }

    public static Color padraoCor(JComponent doComponente) {
        return UIManager.getColor(doComponente.getClass().getSimpleName().substring(1) + ".foreground");
    }

    public static Color padraoFundo(JComponent doComponente) {
        return UIManager.getColor(doComponente.getClass().getSimpleName().substring(1) + ".background");
    }

    public static Font padraoFonte(JComponent doComponente) {
        return UIManager.getFont(doComponente.getClass().getSimpleName().substring(1) + ".font");
    }

    public static void mudaLargura(JComponent doComponente, Integer para) {
        Dimension dim = padraoDimensao(doComponente);
        dim.width = para;
        Rectangle bnd = doComponente.getBounds();
        bnd.width = dim.width;
        bnd.height = dim.height;
        doComponente.setBounds(bnd);
        doComponente.setMinimumSize(dim);
        doComponente.setMaximumSize(dim);
        doComponente.setPreferredSize(dim);
        doComponente.setSize(dim);
        doComponente.validate();
    }

    public static void mudaAltura(JComponent doComponente, Integer para) {
        Dimension dim = padraoDimensao(doComponente);
        dim.height = para;
        Rectangle bnd = doComponente.getBounds();
        bnd.width = dim.width;
        bnd.height = dim.height;
        doComponente.setBounds(bnd);
        doComponente.setMinimumSize(dim);
        doComponente.setMaximumSize(dim);
        doComponente.setPreferredSize(dim);
        doComponente.setSize(dim);
        doComponente.validate();
    }

    public static void mDimensao(JComponent doComponente, Integer paraLargura, Integer eAltura) {
        Dimension dim = new Dimension(paraLargura, eAltura);
        Rectangle bnd = doComponente.getBounds();
        bnd.width = dim.width;
        bnd.height = dim.height;
        doComponente.setBounds(bnd);
        doComponente.setMinimumSize(dim);
        doComponente.setMaximumSize(dim);
        doComponente.setPreferredSize(dim);
        doComponente.setSize(dim);
        doComponente.validate();
    }

    public static void posicionaNaSelecao(JTable daTabela, JScrollPane naRolagem) {
        new Thread("Garante Foco") {
            @Override
            public void run() {
                for (int ifc = 0; ifc < 5; ifc++) {
                    try {
                        sleep(75);
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                int rowIndex = daTabela.getSelectedRow();
                                int vColIndex = daTabela.getSelectedColumn();
                                if ((rowIndex > -1) && (vColIndex > -1)) {
                                    JViewport viewport = naRolagem.getViewport();
                                    Rectangle rect = daTabela.getCellRect(rowIndex, vColIndex, true);
                                    Point pt = viewport.getViewPosition();
                                    rect.setLocation(rect.x - pt.x, rect.y - pt.y);
                                    viewport.scrollRectToVisible(rect);
                                }
                            }
                        });
                    } catch (InterruptedException ex) {
                    }
                }
            }
        }.start();
    }

    public static void posicionaNaSelecao(JList daLista, JScrollPane naRolagem) {
        new Thread("Garante Foco") {
            @Override
            public void run() {
                for (int ifc = 0; ifc < 5; ifc++) {
                    try {
                        sleep(75);
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                int rowIndex = daLista.getSelectedIndex();
                                if (rowIndex > -1) {
                                    JViewport viewport = naRolagem.getViewport();
                                    Rectangle rect = daLista.getCellBounds(rowIndex, rowIndex);
                                    Point pt = viewport.getViewPosition();
                                    rect.setLocation(rect.x - pt.x, rect.y - pt.y);
                                    viewport.scrollRectToVisible(rect);
                                }
                            }
                        });
                    } catch (InterruptedException ex) {
                    }
                }
            }
        }.start();
    }

    public static void posicionaNaSelecao(JTextArea doTexto, JScrollPane naRolagem) {
        new Thread("Garante Foco") {
            @Override
            public void run() {
                for (int ifc = 0; ifc < 5; ifc++) {
                    try {
                        sleep(75);
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Rectangle viewRect = doTexto.modelToView(doTexto.getSelectionStart());
                                    doTexto.scrollRectToVisible(viewRect);
                                } catch (Exception ex) {
                                }
                            }
                        });
                    } catch (Exception ex) {
                    }
                }
            }
        }.start();
    }

    public static void posicionaNaSelecao(JScrollPane naRolagem) {
        new Thread("Garante Foco") {
            @Override
            public void run() {
                for (int ifc = 0; ifc < 5; ifc++) {
                    try {
                        sleep(75);
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                JViewport viewport = naRolagem.getViewport();
                                Component cmp = viewport.getView();
                                if (cmp instanceof JTable) {
                                    posicionaNaSelecao((JTable) cmp, naRolagem);
                                } else if (cmp instanceof JList) {
                                    posicionaNaSelecao((JList) cmp, naRolagem);
                                } else if (cmp instanceof JTextArea) {
                                    posicionaNaSelecao((JTextArea) cmp, naRolagem);
                                } else {
                                    Utl.alerta("Tipo de Componente Não Preparado para Selecionar na Seleção");
                                }
                            }
                        });
                    } catch (InterruptedException ex) {
                    }
                }
            }
        }.start();
    }

    public static void posicionaNoComeco(JScrollPane doPainel) {
        new Thread("Garante Foco") {
            @Override
            public void run() {
                for (int ifc = 0; ifc < 5; ifc++) {
                    try {
                        sleep(75);
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                JScrollBar vertical = doPainel.getVerticalScrollBar();
                                vertical.setValue(vertical.getMinimum());
                                JScrollBar horizontal = doPainel.getHorizontalScrollBar();
                                horizontal.setValue(vertical.getMinimum());
                            }
                        });
                    } catch (InterruptedException ex) {
                    }
                }
            }
        }.start();
    }

    public static void posicionaNoFim(JScrollPane doPainel) {
        new Thread("Garante Foco") {
            @Override
            public void run() {
                for (int ifc = 0; ifc < 5; ifc++) {
                    try {
                        sleep(75);
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                JScrollBar vertical = doPainel.getVerticalScrollBar();
                                vertical.setValue(vertical.getMaximum());
                                JScrollBar horizontal = doPainel.getHorizontalScrollBar();
                                horizontal.setValue(horizontal.getMaximum());
                            }
                        });
                    } catch (InterruptedException ex) {
                    }
                }
            }
        }.start();
    }

    public static void posicionaNoComecoVertical(JScrollPane doPainel) {
        new Thread("Garante Foco") {
            @Override
            public void run() {
                for (int ifc = 0; ifc < 5; ifc++) {
                    try {
                        sleep(75);
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                JScrollBar vertical = doPainel.getVerticalScrollBar();
                                vertical.setValue(vertical.getMinimum());
                            }
                        });
                    } catch (InterruptedException ex) {
                    }
                }
            }
        }.start();
    }

    public static void posicionaNoComecoHorizontal(JScrollPane doPainel) {
        new Thread("Garante Foco") {
            @Override
            public void run() {
                for (int ifc = 0; ifc < 5; ifc++) {
                    try {
                        sleep(75);
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                JScrollBar horizontal = doPainel.getHorizontalScrollBar();
                                horizontal.setValue(horizontal.getMinimum());
                            }
                        });
                    } catch (InterruptedException ex) {
                    }
                }
            }
        }.start();
    }

    public static void posicionaNoFimVertical(JScrollPane doPainel) {
        new Thread("Garante Foco") {
            @Override
            public void run() {
                for (int ifc = 0; ifc < 5; ifc++) {
                    try {
                        sleep(75);
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                JScrollBar vertical = doPainel.getVerticalScrollBar();
                                vertical.setValue(vertical.getMaximum());
                            }
                        });
                    } catch (InterruptedException ex) {
                    }
                }
            }
        }.start();
    }

    public static void posicionaNoFimHorizontal(JScrollPane doPainel) {
        new Thread("Garante Foco") {
            @Override
            public void run() {
                for (int ifc = 0; ifc < 5; ifc++) {
                    try {
                        sleep(75);
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                JScrollBar horizontal = doPainel.getHorizontalScrollBar();
                                horizontal.setValue(horizontal.getMaximum());
                            }
                        });
                    } catch (InterruptedException ex) {
                    }
                }
            }
        }.start();
    }

    public static void garanteFoco(JFrame naJanela) {
        new Thread("Garante Foco") {
            @Override
            public void run() {
                for (int ifc = 0; ifc < 5; ifc++) {
                    try {
                        sleep(75);
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                if (!naJanela.isVisible()) {
                                    naJanela.setVisible(true);
                                }
                                naJanela.toFront();
                                naJanela.requestFocus();
                            }
                        });
                    } catch (InterruptedException ex) {
                    }
                }
            }
        }.start();
    }

    public static void garanteFoco(JFrame naJanela, JDialog esperaFechar) {
        new Thread("Garante Foco") {
            @Override
            public void run() {
                while (esperaFechar.isVisible()) {
                    try {
                        sleep(75);
                    } catch (InterruptedException ex) {
                    }
                }
                for (int ifc = 0; ifc < 5; ifc++) {
                    try {
                        sleep(75);
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                if (!naJanela.isVisible()) {
                                    naJanela.setVisible(true);
                                }
                                naJanela.toFront();
                                naJanela.requestFocus();
                            }
                        });
                    } catch (InterruptedException ex) {
                    }
                }
            }
        }.start();
    }

    public static void garanteFoco(JComponent noComponente) {
        garanteFoco(noComponente, true);
    }

    public static void garanteFoco(JComponent noComponente, Boolean selecionaTudo) {
        new Thread("Garante Foco") {
            @Override
            public void run() {
                for (int ifc = 0; ifc < 5; ifc++) {
                    try {
                        sleep(75);
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                noComponente.requestFocusInWindow();
                                noComponente.requestFocus();
                                if (selecionaTudo) {
                                    if (noComponente instanceof JTextComponent) {
                                        ((JTextComponent) noComponente).selectAll();
                                    }
                                }
                            }
                        });
                    } catch (InterruptedException ex) {
                    }
                }
            }
        }.start();
    }

    public static void piscaVisao(JFrame daJanela) {
        new Thread("Pisca Visão") {
            @Override
            public void run() {
                for (int ifc = 0; ifc < 7; ifc++) {
                    try {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                daJanela.setVisible(false);
                            }
                        });
                        sleep(75);
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                daJanela.setVisible(true);
                            }
                        });
                        sleep(75);
                    } catch (Exception ex) {
                        Utl.registra(ex, false);
                    }
                }
            }
        }.start();
    }

    public static Pattern padraoAtalho = Pattern.compile("\\s\\s\\s\\[\\s.\\s\\]$");

    public static String tiraAtalho(String doTitulo) {
        if (doTitulo == null) {
            return doTitulo;
        }
        if (padraoAtalho.matcher(doTitulo).find()) {
            return doTitulo.substring(0, doTitulo.length() - 8);
        } else {
            return doTitulo;
        }
    }

    public static JComponent pItem(JPopupMenu doPopup, String comCaminho) {
        JComponent retorno = doPopup;
        if (comCaminho != null) {
            String[] partes = comCaminho.split("\\.");
            if (partes.length > 0) {
                for (String parte : partes) {
                    if (retorno instanceof JMenu) {
                        boolean achou = false;
                        for (Component comp : ((JMenu) retorno).getMenuComponents()) {
                            if (comp instanceof JMenu) {
                                if (parte.equals(tiraAtalho(((JMenu) comp).getText()))) {
                                    retorno = (JMenu) comp;
                                    achou = true;
                                }
                            } else if (comp instanceof JMenuItem) {
                                if (parte.equals(tiraAtalho(((JMenuItem) comp).getText()))) {
                                    retorno = (JMenuItem) comp;
                                    achou = true;
                                }
                            }
                        }
                        if (!achou) {
                            return null;
                        }
                    } else {
                        boolean achou = false;
                        for (Component comp : retorno.getComponents()) {
                            if (comp instanceof JMenu) {
                                if (parte.equals(tiraAtalho(((JMenu) comp).getText()))) {
                                    retorno = (JMenu) comp;
                                    achou = true;
                                }
                            } else if (comp instanceof JMenuItem) {
                                if (parte.equals(tiraAtalho(((JMenuItem) comp).getText()))) {
                                    retorno = (JMenuItem) comp;
                                    achou = true;
                                }
                            }
                        }
                        if (!achou) {
                            return null;
                        }
                    }
                }
            }
        }
        return retorno;
    }

    public static JMenu pMenu(JPopupMenu doPopup, String comCaminho) {
        JMenu retorno = null;
        if (comCaminho != null) {
            String[] partes = comCaminho.split("\\.");
            if (partes.length > 0) {
                for (Component comp : doPopup.getComponents()) {
                    if (comp instanceof JMenu) {
                        if (partes[0].equals(tiraAtalho(((JMenu) comp).getText()))) {
                            retorno = (JMenu) comp;
                            break;
                        }
                    }
                }
                for (int ip = 1; ip < partes.length; ip++) {
                    retorno = pMenu(retorno, partes[ip]);
                    if (retorno == null) {
                        break;
                    }
                }
            }
        }
        return retorno;
    }

    public static JMenu pMenu(JMenu doMenu, String comTitulo) {
        JMenu retorno = null;
        if (comTitulo != null) {
            for (Component comp : doMenu.getMenuComponents()) {
                if (comp instanceof JMenu) {
                    if (comTitulo.equals(tiraAtalho(((JMenu) comp).getText()))) {
                        retorno = (JMenu) comp;
                        break;
                    }
                }
            }
        }
        return retorno;
    }

    public static JMenuItem pMenuItem(JPopupMenu doPopup, String comCaminho) {
        if (comCaminho != null) {
            String[] partes = comCaminho.split("\\.");
            if (partes.length > 0) {
                JMenu menu = null;
                for (Component comp : doPopup.getComponents()) {
                    if (comp instanceof JMenu) {
                        if (partes[0].equals(tiraAtalho(((JMenu) comp).getText()))) {
                            menu = (JMenu) comp;
                            break;
                        }
                    } else if (comp instanceof JMenuItem) {
                        if (partes[0].equals(tiraAtalho(((JMenuItem) comp).getText()))) {
                            return (JMenuItem) comp;
                        }
                    }
                }
                if (menu == null) {
                    return null;
                }
                for (int ip = 1; ip < partes.length - 1; ip++) {
                    menu = pMenu(menu, partes[ip]);
                    if (menu == null) {
                        return null;
                    }
                }
                return pMenuItem(menu, partes[partes.length - 1]);
            }
        }
        return null;
    }

    public static JMenuItem pMenuItem(JMenu doMenu, String comTitulo) {
        JMenuItem retorno = null;
        if (comTitulo != null) {
            for (Component comp : doMenu.getMenuComponents()) {
                if (comp instanceof JMenuItem) {
                    if (comTitulo.equals(tiraAtalho(((JMenuItem) comp).getText()))) {
                        retorno = (JMenuItem) comp;
                        break;
                    }
                }
            }
        }
        return retorno;
    }

    public static Component pegaAba(String comTitulo, JTabbedPane dasAbas) {
        Component retorno = null;
        for (int it = 0; it < dasAbas.getTabCount(); it++) {
            if (comTitulo.equals(dasAbas.getTitleAt(it))) {
                retorno = dasAbas.getTabComponentAt(it);
                break;
            }
        }
        return retorno;
    }

    public static void executa(ActionListener[] asAcoes) {
        if (asAcoes != null) {
            for (ActionListener act : asAcoes) {
                act.actionPerformed(null);
            }
        }
    }

    public static Boolean compara(KeyEvent oEvento, String comAtalho) {
        return KeyStroke.getKeyStroke(oEvento.getKeyCode(), oEvento.getModifiers()).equals(KeyStroke.getKeyStroke(comAtalho));
    }

    public static <T> List<T> procura(Frame naJanela, Class<? extends T> daClasse) {
        return procura(naJanela.getComponents(), daClasse);
    }

    public static <T> List<T> procura(JFrame naJanela, Class<? extends T> daClasse) {
        return procura(naJanela.getComponents(), daClasse);
    }

    public static <T> List<T> procura(JDialog naJanela, Class<? extends T> daClasse) {
        return procura(naJanela.getComponents(), daClasse);
    }

    public static <T> List<T> procura(Component[] nosComponents, Class<? extends T> daClasse) {
        List<T> retorno = new ArrayList<>();
        for (Component cmp : nosComponents) {
            if (daClasse.isInstance(cmp)) {
                retorno.add((T) cmp);
            }
            if (cmp instanceof JComponent) {
                retorno.addAll(procura(((JComponent) cmp).getComponents(), daClasse));
            }

        }
        return retorno;
    }

    public static JLabel maisProximo(JTextComponent doComponente, List<JLabel> nosMostradores) {
        JLabel retorno = null;
        if (doComponente != null) {
            Double dist = null;
            Point cmpLoc = doComponente.getLocation();
            if (nosMostradores != null) {
                for (JLabel most : nosMostradores) {
                    Point mostLoc = most.getLocation();
                    if (retorno == null) {
                        retorno = most;
                        dist = Point2D.distance(cmpLoc.x, cmpLoc.y, mostLoc.x, mostLoc.y);
                    } else {
                        Double mostDist = Point2D.distance(cmpLoc.x, cmpLoc.y, mostLoc.x, mostLoc.y);
                        if (mostDist < dist) {
                            retorno = most;
                            dist = mostDist;
                        }
                    }
                }
            }
        }
        return retorno;
    }

    public static void aciona(JComponent oComponente, ActionEvent comEvento) {
        String cm = null;
        Integer id = 0;
        Long wn = System.currentTimeMillis();
        Integer md = 0;
        if (oComponente instanceof AbstractButton) {
            cm = ((AbstractButton) oComponente).getActionCommand();
        }
        if (comEvento != null) {
            if (cm == null) {
                cm = comEvento.getActionCommand();
            }
            id = comEvento.getID();
            wn = comEvento.getWhen();
            md = comEvento.getModifiers();
        }
        comEvento = new ActionEvent(oComponente, id, cm, wn, md);
        if (oComponente instanceof JButton) {
            aciona(((JButton) oComponente).getActionListeners(), comEvento);
        } else if (oComponente instanceof JToggleButton) {
            aciona(((JToggleButton) oComponente).getActionListeners(), comEvento);
        } else if (oComponente instanceof JComboBox) {
            aciona(((JComboBox) oComponente).getActionListeners(), comEvento);
        } else if (oComponente instanceof JLabel) {
            aciona(((JLabel) oComponente).getMouseListeners(), comEvento);
        } else if (oComponente instanceof JTextComponent) {
            aciona(((JTextComponent) oComponente).getKeyListeners(), comEvento);
        } else if (oComponente instanceof JList) {
            aciona(((JList) oComponente).getMouseListeners(), comEvento);
            aciona(((JList) oComponente).getKeyListeners(), comEvento);
        } else if (oComponente instanceof JTable) {
            aciona(((JTable) oComponente).getMouseListeners(), comEvento);
            aciona(((JTable) oComponente).getKeyListeners(), comEvento);
        }
    }

    public static void aciona(ActionListener[] asAcoes, ActionEvent comEvento) {
        if (asAcoes != null) {
            for (ActionListener acao : asAcoes) {
                acao.actionPerformed(comEvento);
            }
        }
    }

    public static void aciona(MouseListener[] asAcoes, ActionEvent comEvento) {
        if (asAcoes != null) {
            Component src = null;
            Integer id = null;
            if (comEvento != null) {
                if (comEvento.getSource() instanceof Component) {
                    src = (Component) comEvento.getSource();
                }
                id = comEvento.getID();
            }
            MouseEvent me = new MouseEvent(src, id, new Date().getTime(), 0, 0, 0, 2, false, MouseEvent.BUTTON1);
            for (MouseListener acao : asAcoes) {
                acao.mouseClicked(me);
            }
        }
    }

    public static void aciona(KeyListener[] asAcoes, ActionEvent comEvento) {
        if (asAcoes != null) {
            Component src = null;
            Integer id = null;
            if (comEvento != null) {
                if (comEvento.getSource() instanceof Component) {
                    src = (Component) comEvento.getSource();
                }
                id = comEvento.getID();
            }
            KeyEvent ke = new KeyEvent(src, id, new Date().getTime(), KeyEvent.ALT_MASK, KeyEvent.VK_ENTER, (char) KeyEvent.VK_ENTER);
            for (KeyListener acao : asAcoes) {
                acao.keyPressed(ke);
            }
        }
    }

    public static ActionEvent pegaEventoAcao(MouseEvent doEventoMouse) {
        if (doEventoMouse == null) {
            return null;
        } else {
            return new ActionEvent(doEventoMouse.getSource(), doEventoMouse.getID(), "EventoMouse", doEventoMouse.getModifiers());
        }
    }

    public static ActionEvent pegaEventoAcao(KeyEvent doEventoTeclado) {
        if (doEventoTeclado == null) {
            return null;
        } else {
            return new ActionEvent(doEventoTeclado.getSource(), doEventoTeclado.getID(), "EventoTeclado", doEventoTeclado.getModifiers());
        }
    }

    public static void botaAcao(JComponent noComponente, AbstractAction aAcao) {
        if (noComponente instanceof JButton) {
            ((JButton) noComponente).addActionListener(aAcao);
        } else if (noComponente instanceof JToggleButton) {
            ((JToggleButton) noComponente).addActionListener(aAcao);
        } else if (noComponente instanceof JComboBox) {
            ((JComboBox) noComponente).addActionListener(aAcao);
        } else if (noComponente instanceof JLabel) {
            botaAcao((JLabel) noComponente, aAcao);
        } else if (noComponente instanceof JTextComponent) {
            botaAcao((JTextComponent) noComponente, aAcao);
        } else if (noComponente instanceof JList) {
            botaAcao((JList) noComponente, aAcao);
        } else if (noComponente instanceof JTable) {
            botaAcao((JTable) noComponente, aAcao);
        }
    }

    public static void botaAcao(JLabel noComponente, AbstractAction aAcao) {
        noComponente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (e.getClickCount() >= 2) {
                        aAcao.actionPerformed(pegaEventoAcao(e));
                    }
                }
            }
        });
    }

    public static void botaAcao(JTextComponent noComponente, AbstractAction aAcao) {
        noComponente.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getModifiers() == KeyEvent.ALT_MASK && e.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
                    aAcao.actionPerformed(pegaEventoAcao(e));
                }
            }
        });
    }

    public static void botaAcao(JList noComponente, AbstractAction aAcao) {
        noComponente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (e.getClickCount() >= 2) {
                        aAcao.actionPerformed(pegaEventoAcao(e));
                    }
                }
            }
        });
        noComponente.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getModifiers() == KeyEvent.ALT_MASK && e.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
                    aAcao.actionPerformed(pegaEventoAcao(e));
                }
            }
        });
    }

    public static void botaAcao(JTable noComponente, AbstractAction aAcao) {
        noComponente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (e.getClickCount() >= 2) {
                        aAcao.actionPerformed(pegaEventoAcao(e));
                    }
                }
            }
        });
        noComponente.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getModifiers() == KeyEvent.ALT_MASK && e.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
                    aAcao.actionPerformed(pegaEventoAcao(e));
                }
            }
        });
    }

    public static Point pegaCentroMonitorMouse() {
        Point mos = MouseInfo.getPointerInfo().getLocation();
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = env.getScreenDevices();
        for (GraphicsDevice gd : gs) {
            Rectangle rect = gd.getDefaultConfiguration().getBounds();
            if (rect.contains(mos)) {
                return new Point((rect.width - rect.x) / 2, (rect.height - rect.y) / 2);
            }
        }
        return null;
    }

    public static void botaCentroMonitorMouse(JFrame aJanela) {
        Point pnt = pegaCentroMonitorMouse();
        pnt.x -= aJanela.getWidth() / 2;
        pnt.y -= aJanela.getHeight() / 2;
        aJanela.setLocation(pnt);
    }

    public static void botaNoCentroDoMonitorDoMouse(JDialog aJanela) {
        Point pnt = pegaCentroMonitorMouse();
        pnt.x -= aJanela.getWidth() / 2;
        pnt.y -= aJanela.getHeight() / 2;
        aJanela.setLocation(pnt);
    }

    public static Point pegaCentroPointelArea() {
        Rectangle mainArea = (Rectangle) Globais.pega("mainArea");
        if (mainArea != null) {
            return new Point((mainArea.width - mainArea.x) / 2, (mainArea.width - mainArea.x) / 2);
        }
        return null;
    }

    public static void botaCentroPointelArea(JFrame aJanela) {
        Point pnt = pegaCentroPointelArea();
        pnt.x -= aJanela.getWidth() / 2;
        pnt.y -= aJanela.getHeight() / 2;
        aJanela.setLocation(pnt);
    }

    public static void botaCentroPointelArea(JDialog aJanela) {
        Point pnt = pegaCentroPointelArea();
        pnt.x -= aJanela.getWidth() / 2;
        pnt.y -= aJanela.getHeight() / 2;
        aJanela.setLocation(pnt);
    }

    public static String pegaTituloDaJanela(Component doComponente) {
        Window janela = SwingUtilities.getWindowAncestor(doComponente);
        if (janela instanceof JFrame) {
            return ((JFrame) janela).getTitle();
        } else if (janela instanceof JDialog) {
            return ((JDialog) janela).getTitle();
        } else {
            return null;
        }
    }

    public static void revalida(JComponent oComponente) {
        oComponente.revalidate();
        revalida(SwingUtilities.getWindowAncestor(oComponente));
    }

    public static void revalida(Window aJanela) {
        aJanela.pack();
    }

    public static Dimension pAutoDimensao(JComponent doComponente) {
        int larg = 33;
        int altr = 33;
        if (doComponente instanceof JTextField) {
            AffineTransform affinetransform = new AffineTransform();
            FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
            Font font = doComponente.getFont();
            String txt = UtlCrs.pega(((JTextField) doComponente).getText(), "");
            larg = (int) (font.getStringBounds(txt, frc).getWidth()) + 10;
            altr = (int) (font.getStringBounds(txt, frc).getHeight()) + 10;
        } else if (doComponente instanceof JTextArea) {
            AffineTransform affinetransform = new AffineTransform();
            FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
            Font font = doComponente.getFont();
            String txt = UtlCrs.pega(((JTextArea) doComponente).getText(), "");
            String[] linhas = UtlTex.pLinhas(txt);
            String maior = UtlTex.pMaior(linhas);
            larg = (int) (font.getStringBounds(maior, frc).getWidth()) + 10;
            altr = (int) (font.getStringBounds(maior, frc).getHeight()) + 2;
            altr = altr * (linhas.length);
            altr += 8;
        } else if (doComponente instanceof JPanel) {
            Dimension dimAct = ((JPanel) doComponente).getLayout().preferredLayoutSize((JPanel) doComponente);
            larg = Math.max(larg, dimAct.width);
            altr = Math.max(altr, dimAct.height);
        }
        return new Dimension(larg, altr);
    }

    public static Component pega(JComponent doAncestral, Integer comCodigo) {
        for (Component cmp : doAncestral.getComponents()) {
            if (Objects.equals(cmp.hashCode(), comCodigo)) {
                return cmp;
            }
        }
        return null;
    }

    public static <T> T pega(JComponent doAncestral, Integer comCodigo, Class<? extends T> daClasse) {
        for (Component cmp : doAncestral.getComponents()) {
            if (Objects.equals(cmp.hashCode(), comCodigo)) {
                return (T) cmp;
            }
        }
        return null;
    }

    public static void age(List<AbstractAction> lstAcao) {
        age(lstAcao, null);
    }

    public static void age(List<AbstractAction> lstAcao, ActionEvent oEvento) {
        if (lstAcao == null) {
            return;
        }
        for (AbstractAction acao : lstAcao) {
            acao.actionPerformed(oEvento);
        }
    }
}
