package pin.libamk;

import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import javax.swing.JComponent;

public class AcaoSeMouse extends AcaoSe<MouseEvent> {

    private Integer clique;
    private Integer cliques;
    private Boolean alt;
    private Boolean control;
    private Boolean shift;
    private Boolean meta;
    private Boolean cliquado;
    private Boolean arrastado;
    private Boolean entrada;
    private Boolean saida;
    private Boolean movido;
    private Boolean apertado;
    private Boolean liberado;
    private Boolean rodado;
    private Shape dentro;
    private Shape dentroTela;
    private Shape fora;
    private Shape foraTela;
    private Escutador escutador;

    public AcaoSeMouse() {
        clique = null;
        cliques = null;
        alt = null;
        control = null;
        shift = null;
        meta = null;
        cliquado = null;
        arrastado = null;
        entrada = null;
        saida = null;
        movido = null;
        apertado = null;
        liberado = null;
        rodado = null;
        dentro = null;
        escutador = null;
    }

    public AcaoSeMouse seCliqueAcao() {
        clique = MouseEvent.BUTTON1;
        return this;
    }

    public AcaoSeMouse seCliqueMedio() {
        clique = MouseEvent.BUTTON2;
        return this;
    }

    public AcaoSeMouse seCliqueOpcao() {
        clique = MouseEvent.BUTTON3;
        return this;
    }

    public AcaoSeMouse tiraClique() {
        clique = null;
        return this;
    }

    public AcaoSeMouse seCliques(Integer naQuantidade) {
        cliques = naQuantidade;
        return this;
    }

    public AcaoSeMouse tiraCliques() {
        cliques = null;
        return this;
    }

    public AcaoSeMouse soAlt() {
        alt = true;
        control = false;
        shift = false;
        meta = false;
        return this;
    }

    public AcaoSeMouse seAlt() {
        alt = true;
        return this;
    }

    public AcaoSeMouse semAlt() {
        alt = false;
        return this;
    }

    public AcaoSeMouse tiraAlt() {
        alt = null;
        return this;
    }

    public AcaoSeMouse soControl() {
        alt = false;
        control = true;
        shift = false;
        meta = false;
        return this;
    }

    public AcaoSeMouse seControl() {
        control = true;
        return this;
    }

    public AcaoSeMouse semControl() {
        control = false;
        return this;
    }

    public AcaoSeMouse tiraControl() {
        control = null;
        return this;
    }

    public AcaoSeMouse soShift() {
        alt = false;
        control = false;
        shift = true;
        meta = false;
        return this;
    }

    public AcaoSeMouse seShift() {
        shift = true;
        return this;
    }

    public AcaoSeMouse semShift() {
        shift = false;
        return this;
    }

    public AcaoSeMouse tiraShift() {
        shift = null;
        return this;
    }

    public AcaoSeMouse soMeta() {
        alt = false;
        control = false;
        shift = false;
        meta = true;
        return this;
    }

    public AcaoSeMouse seMeta() {
        meta = true;
        return this;
    }

    public AcaoSeMouse semMeta() {
        meta = false;
        return this;
    }

    public AcaoSeMouse tiraMeta() {
        meta = null;
        return this;
    }

    public AcaoSeMouse soAoCliquado() {
        cliquado = true;
        arrastado = false;
        entrada = false;
        saida = false;
        movido = false;
        apertado = false;
        liberado = false;
        rodado = false;
        return this;
    }

    public AcaoSeMouse seAoCliquado() {
        cliquado = true;
        return this;
    }

    public AcaoSeMouse semAoCliquado() {
        cliquado = false;
        return this;
    }

    public AcaoSeMouse tiraAoCliquado() {
        cliquado = null;
        return this;
    }

    public AcaoSeMouse soAoArrastado() {
        cliquado = false;
        arrastado = true;
        entrada = false;
        saida = false;
        movido = false;
        apertado = false;
        liberado = false;
        rodado = false;
        return this;
    }

    public AcaoSeMouse seAoArrastado() {
        arrastado = true;
        return this;
    }

    public AcaoSeMouse semAoArrastado() {
        arrastado = false;
        return this;
    }

    public AcaoSeMouse tiraAoArrastado() {
        arrastado = null;
        return this;
    }

    public AcaoSeMouse soAoEntrada() {
        cliquado = false;
        arrastado = false;
        entrada = true;
        saida = false;
        movido = false;
        apertado = false;
        liberado = false;
        rodado = false;
        return this;
    }

    public AcaoSeMouse seAoEntrada() {
        entrada = true;
        return this;
    }

    public AcaoSeMouse semAoEntrada() {
        entrada = false;
        return this;
    }

    public AcaoSeMouse tiraAoEntrada() {
        entrada = null;
        return this;
    }

    public AcaoSeMouse soAoSaida() {
        cliquado = false;
        arrastado = false;
        entrada = false;
        saida = true;
        movido = false;
        apertado = false;
        liberado = false;
        rodado = false;
        return this;
    }

    public AcaoSeMouse seAoSaida() {
        saida = true;
        return this;
    }

    public AcaoSeMouse semAoSaida() {
        saida = false;
        return this;
    }

    public AcaoSeMouse tiraAoSaida() {
        saida = null;
        return this;
    }

    public AcaoSeMouse soAoMovido() {
        cliquado = false;
        arrastado = false;
        entrada = false;
        saida = false;
        movido = true;
        apertado = false;
        liberado = false;
        rodado = false;
        return this;
    }

    public AcaoSeMouse seAoMovido() {
        movido = true;
        return this;
    }

    public AcaoSeMouse semAoMovido() {
        movido = false;
        return this;
    }

    public AcaoSeMouse tiraAoMovido() {
        movido = null;
        return this;
    }

    public AcaoSeMouse soAoApertado() {
        cliquado = false;
        arrastado = false;
        entrada = false;
        saida = false;
        movido = false;
        apertado = true;
        liberado = false;
        rodado = false;
        return this;
    }

    public AcaoSeMouse seAoApertado() {
        apertado = true;
        return this;
    }

    public AcaoSeMouse semAoApertado() {
        apertado = false;
        return this;
    }

    public AcaoSeMouse tiraAoApertado() {
        apertado = null;
        return this;
    }

    public AcaoSeMouse soAoLiberado() {
        cliquado = false;
        arrastado = false;
        entrada = false;
        saida = false;
        movido = false;
        apertado = false;
        liberado = true;
        rodado = false;
        return this;
    }

    public AcaoSeMouse seAoLiberado() {
        liberado = true;
        return this;
    }

    public AcaoSeMouse semAoLiberado() {
        liberado = false;
        return this;
    }

    public AcaoSeMouse tiraAoLiberado() {
        liberado = null;
        return this;
    }

    public AcaoSeMouse soAoRodado() {
        cliquado = false;
        arrastado = false;
        entrada = false;
        saida = false;
        movido = false;
        apertado = false;
        liberado = false;
        rodado = true;
        return this;
    }

    public AcaoSeMouse seAoRodado() {
        rodado = true;
        return this;
    }

    public AcaoSeMouse semAoRodado() {
        rodado = false;
        return this;
    }

    public AcaoSeMouse tiraAoRodado() {
        rodado = null;
        return this;
    }

    public AcaoSeMouse seDentro(Shape daArea) {
        dentro = daArea;
        return this;
    }

    public AcaoSeMouse tiraDentro() {
        dentro = null;
        return this;
    }

    public AcaoSeMouse seDentroTela(Shape daArea) {
        dentroTela = daArea;
        return this;
    }

    public AcaoSeMouse tiraDentroTela() {
        dentroTela = null;
        return this;
    }

    public AcaoSeMouse seFora(Shape daArea) {
        fora = daArea;
        return this;
    }

    public AcaoSeMouse tiraFora() {
        fora = null;
        return this;
    }

    public AcaoSeMouse seForaTela(Shape daArea) {
        foraTela = daArea;
        return this;
    }

    public AcaoSeMouse tiraForaTela() {
        foraTela = null;
        return this;
    }

    @Override
    public void prepara() {
    }

    @Override
    public AcaoSeMouse inicia(JComponent... osComponentes) {
        if (escutador == null) {
            escutador = new Escutador();
            prepara();
        }
        if (osComponentes != null) {
            for (JComponent oComponente : osComponentes) {
                oComponente.addMouseListener(escutador);
                oComponente.addMouseMotionListener(escutador);
                oComponente.addMouseWheelListener(escutador);
            }
        }
        return this;
    }

    @Override
    public Boolean deveExecutar(MouseEvent comMouseEvento) {
        if (clique != null) {
            if (comMouseEvento.getButton() != clique) {
                return false;
            }
        }
        if (cliques != null) {
            if (comMouseEvento.getClickCount() != cliques) {
                return false;
            }
        }
        if (alt != null) {
            if (!((comMouseEvento.getModifiersEx() & MouseEvent.ALT_DOWN_MASK) == (alt ? MouseEvent.ALT_DOWN_MASK : 0))) {
                return false;
            }
        }
        if (control != null) {
            if (!((comMouseEvento.getModifiersEx() & MouseEvent.CTRL_DOWN_MASK) == (control ? MouseEvent.CTRL_DOWN_MASK : 0))) {
                return false;
            }
        }
        if (shift != null) {
            if (!((comMouseEvento.getModifiersEx() & MouseEvent.SHIFT_DOWN_MASK) == (shift ? MouseEvent.SHIFT_DOWN_MASK : 0))) {
                return false;
            }
        }
        if (meta != null) {
            if (!((comMouseEvento.getModifiersEx() & MouseEvent.META_DOWN_MASK) == (meta ? MouseEvent.META_DOWN_MASK : 0))) {
                return false;
            }
        }
        if (cliquado != null) {
            if (!((comMouseEvento.getID() & MouseEvent.MOUSE_CLICKED) == (cliquado ? MouseEvent.MOUSE_CLICKED : 0))) {
                return false;
            }
        }
        if (arrastado != null) {
            if (!((comMouseEvento.getID() & MouseEvent.MOUSE_DRAGGED) == (arrastado ? MouseEvent.MOUSE_DRAGGED : 0))) {
                return false;
            }
        }
        if (entrada != null) {
            if (!((comMouseEvento.getID() & MouseEvent.MOUSE_ENTERED) == (entrada ? MouseEvent.MOUSE_ENTERED : 0))) {
                return false;
            }
        }
        if (saida != null) {
            if (!((comMouseEvento.getID() & MouseEvent.MOUSE_EXITED) == (saida ? MouseEvent.MOUSE_EXITED : 0))) {
                return false;
            }
        }
        if (movido != null) {
            if (!((comMouseEvento.getID() & MouseEvent.MOUSE_MOVED) == (movido ? MouseEvent.MOUSE_MOVED : 0))) {
                return false;
            }
        }
        if (apertado != null) {
            if (!((comMouseEvento.getID() & MouseEvent.MOUSE_PRESSED) == (apertado ? MouseEvent.MOUSE_PRESSED : 0))) {
                return false;
            }
        }
        if (liberado != null) {
            if (!((comMouseEvento.getID() & MouseEvent.MOUSE_RELEASED) == (liberado ? MouseEvent.MOUSE_RELEASED : 0))) {
                return false;
            }
        }
        if (rodado != null) {
            if (!((comMouseEvento.getID() & MouseEvent.MOUSE_WHEEL) == (rodado ? MouseEvent.MOUSE_WHEEL : 0))) {
                return false;
            }
        }
        if (dentro != null) {
            if (!dentro.contains(new Point(comMouseEvento.getX(), comMouseEvento.getY()))) {
                return false;
            }
        }
        if (dentroTela != null) {
            if (!dentroTela.contains(new Point(comMouseEvento.getXOnScreen(), comMouseEvento.getYOnScreen()))) {
                return false;
            }
        }
        if (fora != null) {
            if (fora.contains(new Point(comMouseEvento.getX(), comMouseEvento.getY()))) {
                return false;
            }
        }
        if (foraTela != null) {
            if (foraTela.contains(new Point(comMouseEvento.getXOnScreen(), comMouseEvento.getYOnScreen()))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void executa(MouseEvent comMouseEvento) {
    }

    private class Escutador extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (deveExecutar(e)) {
                executa(e);
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (deveExecutar(e)) {
                executa(e);
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (deveExecutar(e)) {
                executa(e);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (deveExecutar(e)) {
                executa(e);
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            if (deveExecutar(e)) {
                executa(e);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (deveExecutar(e)) {
                executa(e);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (deveExecutar(e)) {
                executa(e);
            }
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            if (deveExecutar(e)) {
                executa(e);
            }
        }
    }

}
