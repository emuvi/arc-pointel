package pin.libjan;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;
import javax.swing.JComponent;

public class FocoNavegador {

    private JComponent[] componentes;

    public FocoNavegador() {
        this.componentes = null;
    }

    public FocoNavegador(JComponent... componentes) {
        this.componentes = componentes;
    }

    public JComponent[] componentes() {
        return componentes;
    }

    public FocoNavegador inicia(JComponent... componentes) {
        this.componentes = componentes;
        inicia();
        return this;
    }

    public FocoNavegador inicia() {
        if (componentes != null) {
            EvtFoco evtFoco = new EvtFoco();
            for (JComponent cmp : componentes) {
                cmp.addKeyListener(evtFoco);
            }
        }
        return this;
    }

    public Integer pegaIndice(JComponent doComponente) {
        Integer retorno = 0;
        if (componentes != null) {
            for (JComponent cmp : componentes) {
                if (Objects.equals(doComponente, cmp)) {
                    return retorno;
                }
                retorno++;
            }
        }
        return -1;
    }

    public Integer pegaProximo(Integer doIndice) {
        Integer retorno = doIndice + 1;
        int tent = 1;
        while (tent <= componentes.length) {
            if (retorno >= componentes.length) {
                retorno = 0;
            }
            if (componentes[retorno].isEnabled() && componentes[retorno].isVisible() && componentes[retorno].isFocusable()) {
                return retorno;
            } else {
                retorno++;
                tent++;
            }
        }
        return -1;
    }

    public Integer pegaAnterior(Integer doIndice) {
        Integer retorno = doIndice - 1;
        int tent = 1;
        while (tent <= componentes.length) {
            if (retorno <= -1) {
                retorno = componentes.length - 1;
            }
            if (componentes[retorno].isEnabled() && componentes[retorno].isVisible() && componentes[retorno].isFocusable()) {
                return retorno;
            } else {
                retorno--;
                tent++;
            }
        }
        return -1;
    }

    private class EvtFoco extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getSource() instanceof JComponent) {
                int ind = pegaIndice((JComponent) e.getSource());
                if (ind > -1) {
                    if (TrataIntf.compara(e, "RIGHT") || TrataIntf.compara(e, "DOWN")) {
                        int prox = pegaProximo(ind);
                        if (prox > -1) {
                            componentes[prox].requestFocus();
                        }
                    } else if (TrataIntf.compara(e, "LEFT") || TrataIntf.compara(e, "UP")) {
                        int ant = pegaAnterior(ind);
                        if (ant > -1) {
                            componentes[ant].requestFocus();
                        }
                    }
                }
            }
        }

    }

}
