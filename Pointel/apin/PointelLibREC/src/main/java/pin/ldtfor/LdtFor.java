package pin.ldtfor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import pin.libfor.Fonte;
import pin.libfor.FonteImp;
import pin.libfor.Generico;
import pin.libfor.Seta;
import pin.libfor.Uniao;
import pin.libima.Rotaciona;
import pin.libjan.TrataIntf;
import pin.libutl.Utl;
import pin.libutl.UtlBin;
import pin.libvlr.VFor;

public class LdtFor extends JPanel {

    private volatile VFor valor;
    private volatile Boolean desenhando;
    private volatile Boolean reprocessar;
    private volatile BufferedImage imgProcessada;

    public LdtFor() {
        this(VFor.Tp.Quadrado);
    }

    public LdtFor(VFor.Tp doTipo) {
        super(null);
        setOpaque(false);
        setBackground(new Color(0, 0, 0, 0));
        TrataIntf.mDimensao(this, 200, 200);
        valor = new VFor(doTipo);
        addComponentListener(new EvtAoRedimensionar());
        desenhando = false;
        reprocessar = false;
        imgProcessada = null;
        repaint();
    }

    public synchronized VFor pgVlr() {
        return valor;
    }

    public synchronized LdtFor mdVlr(VFor para) {
        valor = para;
        reprocessar();
        return this;
    }

    public LdtFor auxGenerico() {
        if (isEnabled()) {
            new AuxGenericos(this).mostra();
        } else {
            Utl.alerta("Editor Não está Editável");
        }
        return this;
    }

    public LdtFor auxAngulo() {
        if (isEnabled()) {
            new AuxAngulo(this, valor.pAngulo()) {
                @Override
                public Boolean aoMudar(Double paraAngulo) throws Exception {
                    valor.mAngulo(pAngulo());
                    LdtFor.this.repaint();
                    return true;
                }
            }.mostra();
        } else {
            Utl.alerta("Editor Não está Editável");
        }
        return this;
    }

    @Override
    protected void paintComponent(Graphics g) {
        try {
            desenhando = true;
            super.paintComponent(g);
            Graphics2D gr = (Graphics2D) g;
            gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            gr.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            switch (valor.pTipo()) {
                case Generico:
                    if (valor.pGenerico() == null) {
                        valor.mGenerico(new Generico());
                    }
                    desenhaGenerico(gr);
                    break;
                case Texto:
                    if (valor.pTexto() != null) {
                        if (valor.pTextoFonte() == null) {
                            valor.mTextoFonte(new Fonte());
                        }
                        gr.setFont(valor.pTextoFonte().pNativa());
                        desenhaTexto(gr);
                    }
                    break;
                case Imagem:
                    desenhaImagem(gr);
                    break;
                case Quadrado:
                    desenhaQuadrado(gr);
                    break;
                case Retangulo:
                    desenhaRetangulo(gr);
                    break;
                case Circulo:
                    desenhaCirculo(gr);
                    break;
                case Elipse:
                    desenhaElipse(gr);
                    break;
                case Seta:
                    desenhaSeta(gr);
                    break;
                case Uniao:
                    desenhaUniao(gr);
                    break;
                case Linha:
                    desenhaLinha(gr);
                    break;
                case Ponto:
                    desenhaPonto(gr);
                    break;

            }
        } catch (Exception ex) {
            Utl.registra(ex);
        } finally {
            desenhando = false;
        }
    }

    private Double difContorno() {
        if (valor.pContorno() != null) {
            return valor.pContorno().pLargura() / 1.8;
        } else {
            return 1 / 1.8;
        }
    }

    public Double pDifX(Shape daForma) {
        return (LdtFor.this.getWidth() - daForma.getBounds2D().getWidth()) / 2.0;
    }

    public Double pDifY(Shape daForma) {
        return (LdtFor.this.getHeight() - daForma.getBounds2D().getHeight()) / 2.0;
    }

    private Double pLargura() {
        return LdtFor.this.getWidth() - (difContorno() * 2.0);
    }

    private Double pAltura() {
        return LdtFor.this.getHeight() - (difContorno() * 2.0);
    }

    private void mAltura(Integer para) {
        TrataIntf.mudaAltura(LdtFor.this, para);
// TODO       if (getParent() instanceof EdtFor) {
//            TrataIntf.mudaAltura(((JComponent) getParent()), para);
//        }
    }

    private void mDimensao(Integer comLargura, Integer eAltura) {
        TrataIntf.mDimensao(LdtFor.this, comLargura, eAltura);
// TODOs        if (getParent() instanceof EdtFor) {
//            TrataIntf.mudaDimensao(((JComponent) getParent()), comLargura, eAltura);
//        }
    }

    private GeneralPath prepara(GeneralPath oFormato) {
        GeneralPath retorno = oFormato;
        if (valor.ehFormatoInscrito()) {
            double lrg = pLargura();
            double alt = pAltura();
            Shape org = AffineTransform.getScaleInstance(1, 1).createTransformedShape(oFormato);
            AffineTransform at = AffineTransform.getTranslateInstance(0.0, 0.0);
            if (valor.pAngulo() != null) {
                at = AffineTransform.getRotateInstance(Math.toRadians(valor.pAngulo()), org.getBounds2D().getCenterX(), org.getBounds2D().getCenterY());
            }
            Shape shp = at.createTransformedShape(org);
            while (shp.getBounds2D().getWidth() > lrg || shp.getBounds2D().getHeight() > alt) {
                org = AffineTransform.getScaleInstance(0.999, 0.999).createTransformedShape(org);
                shp = at.createTransformedShape(org);
            }
            while (shp.getBounds2D().getWidth() < lrg && shp.getBounds2D().getHeight() < alt) {
                org = AffineTransform.getScaleInstance(1.0001, 1.0001).createTransformedShape(org);
                shp = at.createTransformedShape(org);
            }
            retorno = new GeneralPath(shp);
            double tx = pDifX(retorno) - retorno.getBounds2D().getX();
            double ty = pDifY(retorno) - retorno.getBounds2D().getY();
            if (tx != 0.0 || ty != 0.0) {
                retorno.transform(AffineTransform.getTranslateInstance(tx, ty));
            }
        } else {
            if (valor.pAngulo() != null) {
                AffineTransform ag = AffineTransform.getRotateInstance(Math.toRadians(valor.pAngulo()), retorno.getBounds2D().getCenterX(), retorno.getBounds2D().getCenterY());
                retorno.transform(ag);
            }
        }
        return retorno;
    }

    private void desenhaGenerico(Graphics2D gr) {
        if (reprocessar) {
            valor.pGenerico().constroi();
        }
        GeneralPath fig = prepara(new GeneralPath(valor.pGenerico()));
        if (valor.pPreenchimento() != null) {
            valor.pPreenchimento().aplica(gr);
            gr.fill(fig);
        }
        if (valor.pPreenchimento() == null || valor.pContorno() != null) {
            if (valor.pContorno() != null) {
                valor.pContorno().aplica(gr);
            }
            gr.draw(fig);
        }
    }

    private void desenhaTexto(Graphics2D gr) {
        if (imgProcessada == null || reprocessar) {
            boolean temAngulo = valor.pAngulo() != null;
            imgProcessada = new FonteImp(valor.pTextoFonte(), valor.pTexto(), (temAngulo ? null : pLargura().intValue() + 1)).geraImg(gr.getFontRenderContext(), valor.pContorno(), valor.pPreenchimento());
            if (temAngulo) {
                imgProcessada = Rotaciona.faz(imgProcessada, valor.pAngulo());
                if (valor.ehFormatoInscrito()) {
                    mDimensao(imgProcessada.getWidth(), imgProcessada.getHeight());
                }
            } else {
                if (valor.ehFormatoInscrito()) {
                    mAltura(imgProcessada.getHeight());
                }
            }
            reprocessar = false;
        }
        gr.drawImage(imgProcessada, 0, 0, imgProcessada.getWidth(), imgProcessada.getHeight(), null);
    }

    private void desenhaImagem(Graphics2D gr) {
        if (imgProcessada == null || reprocessar) {
            imgProcessada = valor.pImagem();
            if (valor.pAngulo() != null) {
                imgProcessada = Rotaciona.faz(imgProcessada, valor.pAngulo());
            }
            if (valor.ehFormatoInscrito()) {
                mDimensao(imgProcessada.getWidth(), imgProcessada.getHeight());
            }
            reprocessar = false;
        }
        gr.drawImage(imgProcessada, 0, 0, imgProcessada.getWidth(), imgProcessada.getHeight(), null);
    }

    private void desenhaQuadrado(Graphics2D gr) {
        Double lad = Math.min(pLargura(), pAltura());
        Rectangle2D qad = new Rectangle2D.Double(0, 0, lad, lad);
        GeneralPath fig = prepara(new GeneralPath(qad));
        if (valor.pPreenchimento() != null) {
            valor.pPreenchimento().aplica(gr);
            gr.fill(fig);
        }
        if (valor.pPreenchimento() == null || valor.pContorno() != null) {
            if (valor.pContorno() != null) {
                valor.pContorno().aplica(gr);
            }
            gr.draw(fig);
        }
    }

    private void desenhaRetangulo(Graphics2D gr) {
        Rectangle2D ret = new Rectangle2D.Double(0, 0, pLargura(), pAltura());
        GeneralPath fig = prepara(new GeneralPath(ret));
        if (valor.pPreenchimento() != null) {
            valor.pPreenchimento().aplica(gr);
            gr.fill(fig);
        }
        if (valor.pPreenchimento() == null || valor.pContorno() != null) {
            if (valor.pContorno() != null) {
                valor.pContorno().aplica(gr);
            }
            gr.draw(fig);
        }
    }

    private void desenhaCirculo(Graphics2D gr) {
        Double lad = Math.min(pLargura(), pAltura());
        Ellipse2D cir = new Ellipse2D.Double(0, 0, lad, lad);
        GeneralPath fig = prepara(new GeneralPath(cir));
        if (valor.pPreenchimento() != null) {
            valor.pPreenchimento().aplica(gr);
            gr.fill(fig);
        }
        if (valor.pPreenchimento() == null || valor.pContorno() != null) {
            if (valor.pContorno() != null) {
                valor.pContorno().aplica(gr);
            }
            gr.draw(fig);
        }
    }

    private void desenhaElipse(Graphics2D gr) {
        Ellipse2D eli = new Ellipse2D.Double(0, 0, pLargura(), pAltura());
        GeneralPath fig = prepara(new GeneralPath(eli));
        if (valor.pPreenchimento() != null) {
            valor.pPreenchimento().aplica(gr);
            gr.fill(fig);
        }
        if (valor.pPreenchimento() == null || valor.pContorno() != null) {
            if (valor.pContorno() != null) {
                valor.pContorno().aplica(gr);
            }
            gr.draw(fig);
        }
    }

    private void desenhaSeta(Graphics2D gr) {
        double grossura = 1;
        if (valor.pContorno() != null) {
            grossura = valor.pContorno().pLargura();
        }
        Seta set = new Seta(pLargura(), grossura);
        GeneralPath fig = prepara(new GeneralPath(set));
        if (valor.pPreenchimento() != null) {
            valor.pPreenchimento().aplica(gr);
            gr.fill(fig);
        }
        if (valor.pPreenchimento() == null || valor.pContorno() != null) {
            if (valor.pContorno() != null) {
                valor.pContorno().aplica(gr);
            }
            gr.draw(fig);
        }
    }

    private void desenhaUniao(Graphics2D gr) {
        double grossura = 1;
        if (valor.pContorno() != null) {
            grossura = valor.pContorno().pLargura();
        }
        Uniao uni = new Uniao(pLargura(), grossura);
        GeneralPath fig = prepara(new GeneralPath(uni));
        if (valor.pPreenchimento() != null) {
            valor.pPreenchimento().aplica(gr);
            gr.fill(fig);
        }
        if (valor.pPreenchimento() == null || valor.pContorno() != null) {
            if (valor.pContorno() != null) {
                valor.pContorno().aplica(gr);
            }
            gr.draw(fig);
        }
    }

    private void desenhaLinha(Graphics2D gr) {
        double grossura = 1;
        if (valor.pContorno() != null) {
            grossura = valor.pContorno().pLargura();
        }
        Rectangle2D lin = new Rectangle2D.Double(0, 0, pLargura(), grossura);
        GeneralPath fig = prepara(new GeneralPath(lin));
        if (valor.pPreenchimento() != null) {
            valor.pPreenchimento().aplica(gr);
            gr.fill(fig);
        }
        if (valor.pPreenchimento() == null || valor.pContorno() != null) {
            if (valor.pContorno() != null) {
                valor.pContorno().aplica(gr);
            }
            gr.draw(fig);
        }
    }

    private void desenhaPonto(Graphics2D gr) {
        double grossura = 1;
        if (valor.pContorno() != null) {
            grossura = valor.pContorno().pLargura();
        }
        GeneralPath pnt = new GeneralPath(new Ellipse2D.Double(0, 0, grossura, grossura));
        double tx = pDifX(pnt) - pnt.getBounds2D().getX();
        double ty = pDifY(pnt) - pnt.getBounds2D().getY();
        if (tx != 0.0 || ty != 0.0) {
            pnt.transform(AffineTransform.getTranslateInstance(tx, ty));
        }
        if (valor.pPreenchimento() != null) {
            valor.pPreenchimento().aplica(gr);
            gr.fill(pnt);
        }
        if (valor.pPreenchimento() == null || valor.pContorno() != null) {
            if (valor.pContorno() != null) {
                valor.pContorno().aplica(gr);
            }
            gr.draw(pnt);
        }
    }

    public LdtFor reprocessar() {
        if (!desenhando) {
            reprocessar = true;
            repaint();
        } else {
            new Thread("Agenda Reprocessar") {
                @Override
                public void run() {
                    while (desenhando) {
                        UtlBin.esperaMilis(100);
                    }
                    reprocessar = true;
                    repaint();
                }
            }.start();
        }
        return this;
    }

    private class EvtAoRedimensionar extends ComponentAdapter {

        @Override
        public void componentResized(ComponentEvent e) {
            reprocessar();
        }

    }

}
