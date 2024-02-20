package pin.libamk;

import pin.libutl.Utl;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.util.HashMap;
import javax.swing.JComponent;

public class AcaoSeTeclado extends AcaoSe<KeyEvent> {

    private String tecla;
    private Boolean alt;
    private Boolean control;
    private Boolean shift;
    private Boolean meta;
    private Boolean apertado;
    private Boolean liberado;
    private Boolean digitado;
    private Escutador escutador;

    public AcaoSeTeclado() {
        tecla = null;
        alt = null;
        control = null;
        shift = null;
        meta = null;
        apertado = null;
        liberado = null;
        digitado = null;
        iniciaMapa();
    }

    public AcaoSeTeclado seTecla(String aTecla) {
        tecla = aTecla;
        return this;
    }

    public AcaoSeTeclado tiraTecla() {
        tecla = null;
        return this;
    }

    public AcaoSeTeclado soAlt() {
        alt = true;
        control = false;
        shift = false;
        meta = false;
        return this;
    }

    public AcaoSeTeclado seAlt() {
        alt = true;
        return this;
    }

    public AcaoSeTeclado semAlt() {
        alt = false;
        return this;
    }

    public AcaoSeTeclado tiraAlt() {
        alt = null;
        return this;
    }

    public AcaoSeTeclado soControl() {
        alt = false;
        control = true;
        shift = false;
        meta = false;
        return this;
    }

    public AcaoSeTeclado seControl() {
        control = true;
        return this;
    }

    public AcaoSeTeclado semControl() {
        control = false;
        return this;
    }

    public AcaoSeTeclado tiraControl() {
        control = null;
        return this;
    }

    public AcaoSeTeclado soShift() {
        alt = false;
        control = false;
        shift = true;
        meta = false;
        return this;
    }

    public AcaoSeTeclado seShift() {
        shift = true;
        return this;
    }

    public AcaoSeTeclado semShift() {
        shift = false;
        return this;
    }

    public AcaoSeTeclado tiraShift() {
        shift = null;
        return this;
    }

    public AcaoSeTeclado soMeta() {
        alt = false;
        control = false;
        shift = false;
        meta = true;
        return this;
    }

    public AcaoSeTeclado seMeta() {
        meta = true;
        return this;
    }

    public AcaoSeTeclado semMeta() {
        meta = false;
        return this;
    }

    public AcaoSeTeclado tiraMeta() {
        meta = null;
        return this;
    }

    public AcaoSeTeclado soApertado() {
        apertado = true;
        liberado = false;
        digitado = false;
        return this;
    }

    public AcaoSeTeclado seApertado() {
        apertado = true;
        return this;
    }

    public AcaoSeTeclado semApertado() {
        apertado = false;
        return this;
    }

    public AcaoSeTeclado tiraApertado() {
        apertado = null;
        return this;
    }

    public AcaoSeTeclado soLiberado() {
        apertado = false;
        liberado = true;
        digitado = false;
        return this;
    }

    public AcaoSeTeclado seLiberado() {
        liberado = true;
        return this;
    }

    public AcaoSeTeclado semLiberado() {
        liberado = false;
        return this;
    }

    public AcaoSeTeclado tiraLiberado() {
        liberado = null;
        return this;
    }

    public AcaoSeTeclado soDigitado() {
        apertado = false;
        liberado = false;
        digitado = true;
        return this;
    }

    public AcaoSeTeclado seDigitado() {
        digitado = true;
        return this;
    }

    public AcaoSeTeclado semDigitado() {
        digitado = false;
        return this;
    }

    public AcaoSeTeclado tiraDigitado() {
        digitado = null;
        return this;
    }

    @Override
    public void prepara() {
    }

    @Override
    public AcaoSeTeclado inicia(JComponent... osComponentes) {
        if (escutador == null) {
            escutador = new Escutador();
            prepara();
        }
        if (osComponentes != null) {
            for (JComponent oComponente : osComponentes) {
                oComponente.addKeyListener(escutador);
            }
        }
        return this;
    }

    @Override
    public Boolean deveExecutar(KeyEvent comTecladoEvento) {
        if (tecla != null) {
            Integer mapeado = mapa.get(tecla);
            if (!((comTecladoEvento.getExtendedKeyCode() & mapeado) == mapeado)) {
                return false;
            }
        }
        if (alt != null) {
            if (!((comTecladoEvento.getModifiersEx() & KeyEvent.ALT_DOWN_MASK) == (alt ? KeyEvent.ALT_DOWN_MASK : 0))) {
                return false;
            }
        }
        if (control != null) {
            if (!((comTecladoEvento.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) == (control ? KeyEvent.CTRL_DOWN_MASK : 0))) {
                return false;
            }
        }
        if (shift != null) {
            if (!((comTecladoEvento.getModifiersEx() & KeyEvent.SHIFT_DOWN_MASK) == (shift ? KeyEvent.SHIFT_DOWN_MASK : 0))) {
                return false;
            }
        }
        if (meta != null) {
            if (!((comTecladoEvento.getModifiersEx() & KeyEvent.META_DOWN_MASK) == (meta ? KeyEvent.META_DOWN_MASK : 0))) {
                return false;
            }
        }
        if (apertado != null) {
            if (!((comTecladoEvento.getID() & KeyEvent.KEY_PRESSED) == (apertado ? KeyEvent.KEY_PRESSED : 0))) {
                return false;
            }
        }
        if (liberado != null) {
            if (!((comTecladoEvento.getID() & KeyEvent.KEY_RELEASED) == (liberado ? KeyEvent.KEY_RELEASED : 0))) {
                return false;
            }
        }
        if (digitado != null) {
            if (!((comTecladoEvento.getID() & KeyEvent.KEY_TYPED) == (digitado ? KeyEvent.KEY_TYPED : 0))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void executa(KeyEvent comTecladoEvento) {
    }

    private class Escutador extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (deveExecutar(e)) {
                executa(e);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (deveExecutar(e)) {
                executa(e);
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
            if (deveExecutar(e)) {
                executa(e);
            }
        }
    }

    private static HashMap<String, Integer> mapa = null;

    private static void iniciaMapa() {
        if (mapa == null) {
            mapa = new HashMap<>();
            Field[] fields = KeyEvent.class.getDeclaredFields();
            for (Field field : fields) {
                String name = field.getName();
                if (name.startsWith("VK_")) {
                    try {
                        mapa.put(name.substring(3).toUpperCase(), field.getInt(null));
                    } catch (Exception ex) {
                        Utl.registra(ex);
                    }
                }
            }
        }
    }

}
