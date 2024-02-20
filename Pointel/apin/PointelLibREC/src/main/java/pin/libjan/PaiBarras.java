package pin.libjan;

import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class PaiBarras extends JPanel {

    private PaiTabela painel;

    public PaiBarras() {
        super(new BorderLayout());
        painel = new PaiTabela();
        add(painel, BorderLayout.CENTER);
    }

    public PaiBarras bota(JMenuBar aBarra) {
        painel.bota(aBarra)
                .preencheHorizontal()
                .insere();
        return this;
    }

    public PaiBarras bota(JToolBar aBarra) {
        aBarra.setFloatable(false);
        painel.bota(aBarra)
                .preencheHorizontal()
                .insere();
        return this;
    }

    public PaiBarras bota(JComponent oComponente) {
        painel.bota(oComponente)
                .preencheAmbos()
                .insere();
        return this;
    }

    public PaiBarras trocaVisivel(JMenuBar daBarra) {
        daBarra.setVisible(!daBarra.isVisible());
        TrataIntf.revalida(this);
        return this;
    }

    public PaiBarras trocaVisivel(JToolBar daBarra) {
        daBarra.setVisible(!daBarra.isVisible());
        TrataIntf.revalida(this);
        return this;
    }

}
