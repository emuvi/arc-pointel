package pin.libjan;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JViewport;
import pin.libitr.ItrPics;
import pin.libutl.UtlCrs;

public class PopMenu {

    private JPopupMenu jpmMenu;
    private AbstractAction ultimaAcao;
    private JComponent ultimoCpn;
    private String ultimoNome;

    public PopMenu() {
        jpmMenu = new JPopupMenu();
        ultimaAcao = null;
        ultimoCpn = null;
        ultimoNome = null;
    }

    public PopMenu bota(String comTitulo) {
        bota(null, null, comTitulo, null, null, null);
        return this;
    }

    public PopMenu bota(Integer noIndice, String comTitulo) {
        bota(null, noIndice, comTitulo, null, null, null);
        return this;
    }

    public PopMenu bota(String comTitulo, Character eAtalho) {
        bota(null, null, comTitulo, eAtalho, null, null);
        return this;
    }

    public PopMenu bota(String naOrigem, String comTitulo, Character eAtalho) {
        bota(naOrigem, null, comTitulo, eAtalho, null, null);
        return this;
    }

    public PopMenu bota(Integer noIndice, String comTitulo, Character eAtalho) {
        bota(null, noIndice, comTitulo, eAtalho, null, null);
        return this;
    }

    public PopMenu bota(String comTitulo, AbstractAction eAcao) {
        bota(null, null, comTitulo, null, null, eAcao);
        return this;
    }

    public PopMenu bota(Integer noIndice, String comTitulo, AbstractAction eAcao) {
        bota(null, noIndice, comTitulo, null, null, eAcao);
        return this;
    }

    public PopMenu bota(String comTitulo, Character eAtalho, AbstractAction eAcao) {
        bota(null, null, comTitulo, eAtalho, null, eAcao);
        return this;
    }

    public PopMenu bota(Integer noIndice, String comTitulo, Character eAtalho, AbstractAction eAcao) {
        bota(null, noIndice, comTitulo, eAtalho, null, eAcao);
        return this;
    }

    public PopMenu bota(String comTitulo, Icon eIcone) {
        bota(null, null, comTitulo, null, eIcone, null);
        return this;
    }

    public PopMenu bota(Integer noIndice, String comTitulo, Icon eIcone) {
        bota(null, noIndice, comTitulo, null, eIcone, null);
        return this;
    }

    public PopMenu bota(String comTitulo, Character eAtalho, Icon eIcone) {
        bota(null, null, comTitulo, eAtalho, eIcone, null);
        return this;
    }

    public PopMenu bota(Integer noIndice, String comTitulo, Character eAtalho, Icon eIcone) {
        bota(null, noIndice, comTitulo, eAtalho, eIcone, null);
        return this;
    }

    public PopMenu bota(String comTitulo, Icon eIcone, AbstractAction eAcao) {
        bota(null, null, comTitulo, null, eIcone, eAcao);
        return this;
    }

    public PopMenu bota(Integer noIndice, String comTitulo, Icon eIcone, AbstractAction eAcao) {
        bota(null, noIndice, comTitulo, null, eIcone, eAcao);
        return this;
    }

    public PopMenu bota(String comTitulo, Character eAtalho, Icon eIcone, AbstractAction eAcao) {
        bota(null, null, comTitulo, eAtalho, eIcone, eAcao);
        return this;
    }

    public PopMenu bota(Integer noIndice, String comTitulo, Character eAtalho, Icon eIcone, AbstractAction eAcao) {
        bota(null, noIndice, comTitulo, eAtalho, eIcone, eAcao);
        return this;
    }

    public PopMenu bota(String naOrigem, String comTitulo) {
        bota(naOrigem, null, comTitulo, null, null, null);
        return this;
    }

    public PopMenu bota(String naOrigem, Integer eIndice, String comTitulo) {
        bota(naOrigem, eIndice, comTitulo, null, null, null);
        return this;
    }

    public PopMenu bota(String naOrigem, Character eAtalho, String comTitulo) {
        bota(naOrigem, null, comTitulo, eAtalho, null, null);
        return this;
    }

    public PopMenu bota(String naOrigem, Integer eIndice, Character eAtalho, String comTitulo) {
        bota(naOrigem, eIndice, comTitulo, eAtalho, null, null);
        return this;
    }

    public PopMenu bota(String naOrigem, String comTitulo, AbstractAction eAcao) {
        bota(naOrigem, null, comTitulo, null, null, eAcao);
        return this;
    }

    public PopMenu bota(String naOrigem, Integer eIndice, String comTitulo, AbstractAction eAcao) {
        bota(naOrigem, eIndice, comTitulo, null, null, eAcao);
        return this;
    }

    public PopMenu bota(String naOrigem, String comTitulo, Character eAtalho, AbstractAction eAcao) {
        bota(naOrigem, null, comTitulo, eAtalho, null, eAcao);
        return this;
    }

    public PopMenu bota(String naOrigem, Integer eIndice, String comTitulo, Character eAtalho, AbstractAction eAcao) {
        bota(naOrigem, eIndice, comTitulo, eAtalho, null, eAcao);
        return this;
    }

    public PopMenu bota(String naOrigem, String comTitulo, Icon eIcone) {
        bota(naOrigem, null, comTitulo, null, eIcone, null);
        return this;
    }

    public PopMenu bota(String naOrigem, Integer eIndice, String comTitulo, Icon eIcone) {
        bota(naOrigem, eIndice, comTitulo, null, eIcone, null);
        return this;
    }

    public PopMenu bota(String naOrigem, String comTitulo, Character eAtalho, Icon eIcone) {
        bota(naOrigem, null, comTitulo, eAtalho, eIcone, null);
        return this;
    }

    public PopMenu bota(String naOrigem, Integer eIndice, String comTitulo, Character eAtalho, Icon eIcone) {
        bota(naOrigem, eIndice, comTitulo, eAtalho, eIcone, null);
        return this;
    }

    public PopMenu bota(String naOrigem, String comTitulo, Character eAtalho, Icon eIcone, AbstractAction eAcao) {
        bota(naOrigem, null, comTitulo, eAtalho, eIcone, eAcao);
        return this;
    }

    public PopMenu bota(String naOrigem, Integer eIndice, String comTitulo, Character eAtalho, Icon eIcone, AbstractAction eAcao) {
        ultimoNome = UtlCrs.soma(naOrigem, ".", comTitulo);
        JComponent ondeInserir = jpmMenu;
        if (naOrigem != null) {
            if (!naOrigem.isEmpty()) {
                ondeInserir = TrataIntf.pMenu(jpmMenu, naOrigem);
            }
        }
        if (ondeInserir == null) {
            ondeInserir = jpmMenu;
        }
        if (eAtalho != null) {
            comTitulo = comTitulo + "   [ " + eAtalho + " ]";
        }
        if (eAcao != null) {
            JMenuItem nvItem = new JMenuItem();
            if (comTitulo != null) {
                nvItem.setText(comTitulo);
            }
            if (eAtalho != null) {
                nvItem.setMnemonic(eAtalho);
            }
            if (eIcone != null) {
                nvItem.setIcon(eIcone);
            }
            nvItem.addActionListener(eAcao);
            if (eIndice == null) {
                ondeInserir.add(nvItem);
            } else {
                ondeInserir.add(nvItem, eIndice.intValue());
            }
            ultimaAcao = eAcao;
            ultimoCpn = nvItem;
        } else {
            JMenu nvItem = new JMenu();
            if (comTitulo != null) {
                nvItem.setText(comTitulo);
            }
            if (eAtalho != null) {
                nvItem.setMnemonic(eAtalho);
            }
            if (eIcone != null) {
                nvItem.setIcon(eIcone);
            }
            if (eIndice == null) {
                ondeInserir.add(nvItem);
            } else {
                ondeInserir.add(nvItem, eIndice.intValue());
            }
            ultimoCpn = nvItem;
        }
        return this;
    }

    public PopMenu bota(String naOrigem, PopMenu oMenu) {
        JComponent ondeInserir = jpmMenu;
        if (naOrigem != null) {
            if (!naOrigem.isEmpty()) {
                ondeInserir = TrataIntf.pMenu(jpmMenu, naOrigem);
            }
        }
        if (ondeInserir == null) {
            ondeInserir = jpmMenu;
        }
        for (Component cmp : oMenu.pPopupMenu().getComponents()) {
            ondeInserir.add(cmp);
        }
        return this;
    }

    public PopMenu muda(String comTitulo, Character oAtalho) {
        return muda(null, comTitulo, oAtalho, null, null);
    }

    public PopMenu muda(String naOrigem, String comTitulo, Character oAtalho) {
        return muda(naOrigem, comTitulo, oAtalho, null, null);
    }

    public PopMenu muda(String comTitulo, Icon oIcone) {
        return muda(null, comTitulo, null, oIcone, null);
    }

    public PopMenu muda(String naOrigem, String comTitulo, Icon oIcone) {
        return muda(naOrigem, comTitulo, null, oIcone, null);
    }

    public PopMenu muda(String comTitulo, AbstractAction eAcao) {
        return muda(null, comTitulo, null, null, eAcao);
    }

    public PopMenu muda(String naOrigem, String comTitulo, AbstractAction aAcao) {
        return muda(naOrigem, comTitulo, null, null, aAcao);
    }

    public PopMenu muda(String comTitulo, Character oAtalho, AbstractAction eAcao) {
        return muda(null, comTitulo, oAtalho, null, eAcao);
    }

    public PopMenu muda(String naOrigem, String comTitulo, Character oAtalho, AbstractAction eAcao) {
        return muda(naOrigem, comTitulo, oAtalho, null, eAcao);
    }

    public PopMenu muda(String comTitulo, Icon oIcone, AbstractAction eAcao) {
        return muda(null, comTitulo, null, null, eAcao);
    }

    public PopMenu muda(String naOrigem, String comTitulo, Icon oIcone, AbstractAction eAcao) {
        return muda(naOrigem, comTitulo, null, oIcone, eAcao);
    }

    public PopMenu muda(String comTitulo, Character oAtalho, Icon eIcone, AbstractAction eAcao) {
        return muda(null, comTitulo, oAtalho, eIcone, eAcao);
    }

    public PopMenu muda(String naOrigem, String comTitulo, Character oAtalho, Icon eIcone, AbstractAction eAcao) {
        String nome = UtlCrs.soma(naOrigem, ".", comTitulo);
        JComponent elemento = TrataIntf.pItem(jpmMenu, nome);
        if (elemento instanceof JMenu) {
            JMenu elem = (JMenu) elemento;
            if (oAtalho != null) {
                elem.setMnemonic(oAtalho);
                elem.setText(comTitulo + "   [ " + oAtalho + " ]");
            }
            if (eIcone != null) {
                elem.setIcon(eIcone);
            }
            if (eAcao != null) {
                for (ActionListener act : elem.getActionListeners()) {
                    elem.removeActionListener(act);
                }
                elem.addActionListener(eAcao);
            }
        } else if (elemento instanceof JMenuItem) {
            JMenuItem elem = (JMenuItem) elemento;
            if (oAtalho != null) {
                elem.setMnemonic(oAtalho);
                elem.setText(comTitulo + "   [ " + oAtalho + " ]");
            }
            if (eIcone != null) {
                elem.setIcon(eIcone);
            }
            if (eAcao != null) {
                for (ActionListener act : elem.getActionListeners()) {
                    elem.removeActionListener(act);
                }
                elem.addActionListener(eAcao);
            }
        }
        return this;
    }

    public ImageIcon pIcone(Boolean doSelecionado) {
        if (doSelecionado) {
            return ItrPics.pega("checked.png");
        } else {
            return ItrPics.pega("unchecked.png");
        }
    }

    public PopMenu mudaSelecionado(String comTitulo, Boolean para) {
        return mudaSelecionado(null, comTitulo, para);
    }

    public PopMenu mudaSelecionado(String naOrigem, String comTitulo, Boolean para) {
        String nome = UtlCrs.soma(naOrigem, ".", comTitulo);
        JComponent elemento = TrataIntf.pItem(jpmMenu, nome);
        if (elemento instanceof JMenu) {
            JMenu elem = (JMenu) elemento;
            elem.setIcon(pIcone(para));
        } else if (elemento instanceof JMenuItem) {
            JMenuItem elem = (JMenuItem) elemento;
            elem.setIcon(pIcone(para));
        }
        return this;
    }

    public PopMenu botaSeparador() {
        botaSeparador(null, null);
        return this;
    }

    public PopMenu botaSeparador(String naOrigem) {
        botaSeparador(naOrigem, null);
        return this;
    }

    public PopMenu botaSeparador(Integer noIndice) {
        botaSeparador(null, noIndice);
        return this;
    }

    public PopMenu botaSeparador(String naOrigem, Integer eIndice) {
        JComponent ondeInserir = jpmMenu;
        if (naOrigem != null) {
            if (!naOrigem.isEmpty()) {
                ondeInserir = TrataIntf.pMenu(jpmMenu, naOrigem);
            }
        }
        if (ondeInserir == null) {
            ondeInserir = jpmMenu;
        }
        JSeparator nvItem = new JSeparator();
        if (eIndice == null) {
            ondeInserir.add(nvItem);
        } else {
            ondeInserir.add(nvItem, eIndice.intValue());
        }
        return this;
    }

    public PopMenu limpa() {
        jpmMenu.removeAll();
        return this;
    }

    public PopMenu botaAtalho(JComponent noEditor, String comPadrao) {
        String nome = noEditor.getClass().getSimpleName() + "." + ultimoNome;
        String atalho = JanAtalhos.pega(nome, comPadrao);
        if (ultimaAcao != null) {
            Janelas.botaAtalho(noEditor, nome, atalho, ultimaAcao);
        }
        if (ultimoCpn instanceof JMenuItem) {
            ((JMenuItem) ultimoCpn).setText(((JMenuItem) ultimoCpn).getText() + "   [" + atalho + "]");
        } else if (ultimoCpn instanceof JMenu) {
            ((JMenu) ultimoCpn).setText(((JMenu) ultimoCpn).getText() + "   [" + atalho + "]");
        }
        return this;
    }

    public PopMenu botaAcao(JComponent noComponente) {
        if (ultimaAcao != null) {
            TrataIntf.botaAcao(noComponente, ultimaAcao);
        }
        return this;
    }

    public Boolean tem(String comTitulo) {
        return tem(null, comTitulo);
    }

    public Boolean tem(String naOrigem, String comTitulo) {
        ultimoNome = UtlCrs.soma(naOrigem, ".", comTitulo);
        JComponent ondeInserir = jpmMenu;
        if (naOrigem != null) {
            if (!naOrigem.isEmpty()) {
                ondeInserir = TrataIntf.pMenu(jpmMenu, naOrigem);
            }
        }
        return ondeInserir != null;
    }

    public void troca(Component noComponente) {
        troca(noComponente, true);
    }

    public void troca(Component noComponente, Boolean emBaixo) {
        if (emBaixo) {
            troca(noComponente, 0, noComponente.getHeight());
        } else {
            troca(noComponente, 0, 0);
        }
    }

    public void troca(Component noComponente, Integer naPosX, Integer naPosY) {
        if (naPosX == null) {
            naPosX = 0;
        }
        if (naPosY == null) {
            naPosY = 0;
        }
        if (jpmMenu.isShowing()) {
            jpmMenu.setVisible(false);
        } else {
            jpmMenu.show(noComponente, naPosX, naPosY);
        }
    }

    public void mostra(Component noComponente) {
        mostra(noComponente, true);
    }

    public void mostra(Component noComponente, Boolean emBaixo) {
        if (emBaixo) {
            mostra(noComponente, 0, noComponente.getHeight());
        } else {
            mostra(noComponente, 0, 0);
        }
    }

    public void mostra(Component noComponente, Integer naPosX, Integer naPosY) {
        if (naPosX == null) {
            naPosX = 0;
        }
        if (naPosY == null) {
            naPosY = 0;
        }
        jpmMenu.show(noComponente, naPosX, naPosY);
    }

    public void coloca(Component noComponente) {
        coloca(noComponente, false, MouseEvent.BUTTON3, false);
    }

    public void coloca(Component noComponente, Integer comBotao) {
        coloca(noComponente, false, comBotao, false);
    }

    public void coloca(Component noComponente, Boolean emBaixo) {
        coloca(noComponente, emBaixo, MouseEvent.BUTTON3, false);
    }

    public void coloca(Component noComponente, Boolean emBaixo, Integer comBotao) {
        coloca(noComponente, emBaixo, comBotao, false);
    }

    public void coloca(Component noComponente, Boolean emBaixo, Boolean garantePop) {
        coloca(noComponente, emBaixo, MouseEvent.BUTTON3, garantePop);
    }

    public void coloca(Component noComponente, Boolean emBaixo, Integer comBotao, Boolean garantePop) {
        if (noComponente == null) {
            return;
        }
        if (noComponente instanceof JButton && !garantePop) {
            ((JButton) noComponente).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostra(noComponente, 0, noComponente.getHeight());
                }
            });
        } else {
            noComponente.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getButton() == comBotao) {
                        if (emBaixo) {
                            mostra(e.getComponent());
                        } else {
                            mostra(e.getComponent(), e.getX(), e.getY());
                        }
                    }
                }
            });
            if (noComponente instanceof JScrollPane) {
                JViewport viewport = ((JScrollPane) noComponente).getViewport();
                if (viewport != null) {
                    for (Component cmp : viewport.getComponents()) {
                        cmp.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                if (e.getButton() == comBotao) {
                                    if (emBaixo) {
                                        mostra(e.getComponent());
                                    } else {
                                        mostra(e.getComponent(), e.getX(), e.getY());
                                    }
                                }
                            }
                        });
                    }
                }
            }
        }
    }

    public void executa(String comTitulo) {
        executa(null, comTitulo);
    }

    public void executa(String naOrigem, String comTitulo) {
        JMenuItem item = TrataIntf.pMenuItem(jpmMenu, UtlCrs.soma(naOrigem, ".", comTitulo));
        TrataIntf.executa(item.getActionListeners());
    }

    public JPopupMenu pPopupMenu() {
        return jpmMenu;
    }
}
