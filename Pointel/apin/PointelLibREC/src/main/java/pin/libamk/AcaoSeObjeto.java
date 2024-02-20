package pin.libamk;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;
import javax.swing.JComponent;

public class AcaoSeObjeto extends AcaoSe<Object> {

    private Class classe;
    private Object origem;
    private EscutadorTeclado escTeclado;
    private EscutadorMouse escMouse;

    public AcaoSeObjeto() {
        classe = null;
        origem = null;
        escTeclado = null;
        escMouse = null;
    }

    public AcaoSeObjeto seClasse(Class daClasse) {
        classe = daClasse;
        return this;
    }

    public AcaoSeObjeto tiraClasse() {
        classe = null;
        return this;
    }

    public AcaoSeObjeto seOrigem(Object aOrigem) {
        origem = aOrigem;
        return this;
    }

    public AcaoSeObjeto tiraOrigem() {
        origem = null;
        return this;
    }

    @Override
    public void prepara() {
    }

    @Override
    public AcaoSeObjeto inicia(JComponent... osComponentes) {
        if (escTeclado == null || escMouse == null) {
            escTeclado = new EscutadorTeclado();
            escMouse = new EscutadorMouse();
            prepara();
        }
        if (osComponentes != null) {
            for (JComponent oComponente : osComponentes) {
                oComponente.addKeyListener(escTeclado);
                oComponente.addMouseListener(escMouse);
            }
        }
        return this;
    }

    @Override
    public Boolean deveExecutar(Object comOrigem) {
        if (classe != null) {
            if (!classe.isInstance(comOrigem)) {
                return false;
            }
        }
        if (origem != null) {
            if (!Objects.equals(origem, comOrigem)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void executa(Object comOrigem) {
    }

    private class EscutadorTeclado extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (((e.getModifiers() & KeyEvent.CTRL_MASK) != 0) && e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (deveExecutar(e.getSource())) {
                    executa(e.getSource());
                }
            }
        }
    }

    private class EscutadorMouse extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() >= 2) {
                if (deveExecutar(e.getSource())) {
                    executa(e.getSource());
                }
            }
        }
    }

}
