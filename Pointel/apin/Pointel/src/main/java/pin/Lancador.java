package pin;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import pin.libjan.PopMenu;
import pin.libpic.Pics;
import pin.libutl.Utl;
import pin.libutl.UtlCrs;
import pin.modamk.Recurso;
import pin.modpim.Pims;

public class Lancador extends JPanel {

    private final List<Atalho> atalhos;
    private final BufferedImage tapete;

    public Lancador() {
        setLayout(null);
        atalhos = new ArrayList<>();
        botaAtalho(new Point(40, 40), Pointel.mainRecs.pega(null, "Pointel"), false);
        tapete = Pics.pegaIma("TapeteVerde.jpg");
    }

    public Lancador botaAtalho(Point naPosicao, Recurso oRecurso) {
        return botaAtalho(naPosicao, oRecurso, true);
    }

    public Lancador botaAtalho(Point naPosicao, Recurso oRecurso, Boolean ehMovivel) {
        Atalho novo = new Atalho(naPosicao, oRecurso, ehMovivel);
        atalhos.add(novo);
        add(novo);
        validate();
        return salvaAtalhos();
    }

    public Lancador atualizaAtalhos() {

        return this;
    }

    public Lancador atualizaAtalhosDaConexao() {

        return this;
    }

    public Lancador atualizaConexaoDescricao() {

        return this;
    }

    public Lancador salvaAtalhos() {

        return this;
    }

    private class Atalho extends JPanel {

        private final Recurso recurso;
        private final Boolean movivel;
        private final JLabel mostrador;
        private final PopMenu popMenu;
        private Point inicial;
        private Point pressionado;

        public Atalho(Point naPosicao, Recurso oRecurso) {
            this(naPosicao, oRecurso, true);
        }

        public Atalho(Point naPosicao, Recurso oRecurso, Boolean ehMovivel) {
            super(new BorderLayout());
            recurso = oRecurso;
            movivel = ehMovivel;
            inicial = null;
            pressionado = null;
            setBounds(naPosicao.x, naPosicao.y, 64, 64);
            setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            String imgNome = recurso.pegaImgNome();
            if (imgNome != null) {
                mostrador = new JLabel(Pims.pega(imgNome));
            } else {
                mostrador = new JLabel(UtlCrs.fazSigla(recurso.pegaNome()));
            }
            add(mostrador, BorderLayout.CENTER);
            EvtMouse evtMouse = new EvtMouse();
            mostrador.addMouseListener(evtMouse);
            mostrador.addMouseMotionListener(evtMouse);
            if (movivel) {
                popMenu = new PopMenu();
                popMenu.bota("Remove", new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        atalhos.remove(Atalho.this);
                        Lancador.this.remove(Atalho.this);
                        Lancador.this.repaint();
                    }
                });
                popMenu.coloca(mostrador);
            } else {
                popMenu = null;
            }
            validate();
        }

        public Boolean ehMovivel() {
            return movivel;
        }

        private class EvtMouse extends MouseAdapter {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() >= 2) {
                    try {
                        recurso.executa(Atalho.this);
                    } catch (Exception ex) {
                        Utl.registra(ex);
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (movivel) {
                    inicial = getLocation();
                    pressionado = new Point(e.getXOnScreen(), e.getYOnScreen());
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (movivel && inicial != null && pressionado != null) {
                    setLocation(inicial.x + (e.getXOnScreen() - pressionado.x), inicial.y + (e.getYOnScreen() - pressionado.y));
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gr = (Graphics2D) g;
        Rectangle area = getBounds();
        int il = 0;
        int ia = 0;
        while (true) {
            gr.drawImage(tapete, il, ia, null);
            il += tapete.getWidth();
            if (il > area.width) {
                il = 0;
                ia += tapete.getHeight();
                if (ia > area.height) {
                    break;
                }
            }
        }
    }

}
