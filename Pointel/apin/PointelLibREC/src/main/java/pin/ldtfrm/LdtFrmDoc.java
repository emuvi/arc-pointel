package pin.ldtfrm;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import pin.libjan.TrataIntf;

public class LdtFrmDoc extends JPanel {

    private Point ultimoClique;

    public LdtFrmDoc() {
        super();
        setLayout(null);
        setBackground(Color.WHITE);
        addMouseListener(new EvtMouse());
        TrataIntf.mDimensao(this, 300, 300);
    }

    public Boolean vazio() {
        return getComponents().length == 0;
    }

    public Point pUltimoClique() {
        return ultimoClique;
    }

    @Override
    public boolean isOptimizedDrawingEnabled() {
        return false;
    }

    private class EvtMouse extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            ultimoClique = new Point(e.getX(), e.getY());
        }
    }

}
