package pin.ldtima;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import pin.libjan.TrataIntf;
import pin.libutl.UtlIma;
import pin.libutl.UtlInt;

public class ImaDoc extends JComponent {

    private BufferedImage original;
    private BufferedImage processada;
    private BufferedImage exibida;
    private String zoom;

    public ImaDoc() {
        this(null);
    }

    public ImaDoc(BufferedImage comImagem) {
        super();
        zoom = "Original";
        muda(comImagem);
    }

    public BufferedImage pega() {
        return processada;
    }

    public ImaDoc muda(BufferedImage paraImagem) {
        original = paraImagem;
        processada = UtlIma.copiaParaARGB(original);
        atualiza();
        return this;
    }

    public ImaDoc desfazTudo() {
        return muda(original);
    }

    public ImaDoc limpa() {
        return muda(null);
    }

    public Boolean vazio() {
        return original == null;
    }

    public Boolean tem() {
        return original != null;
    }

    public String pegaZoom() {
        return zoom;
    }

    public ImaDoc botaZoom(String zoom) {
        this.zoom = zoom;
        atualiza();
        return this;
    }

    public Integer pegaLargura() {
        if (original == null) {
            return null;
        }
        return original.getWidth();
    }

    public Integer pegaAltura() {
        if (original == null) {
            return null;
        }
        return original.getHeight();
    }

    public Integer pegaLarguraExibida() {
        if (exibida == null) {
            return null;
        }
        return exibida.getWidth();
    }

    public Integer pegaAlturaExibida() {
        if (exibida == null) {
            return null;
        }
        return exibida.getHeight();
    }

    public ImaDoc executa(Operacao aOperacao) throws Exception {
        processada = aOperacao.executa(processada);
        atualiza();
        return this;
    }

    public ImaDoc atualiza() {
        if (processada == null) {
            exibida = null;
            TrataIntf.mDimensao(this, 0, 0);
        } else {
            switch (zoom) {
                case "Original":
                    exibida = processada;
                    break;
                case "Janela":
                    Container cnt = getParent();
                    if (cnt != null) {
                        exibida = UtlIma.redimensiona(processada, cnt.getWidth(), cnt.getHeight());
                    } else {
                        exibida = processada;
                    }
                    break;
                default:
                    Integer percentagem = UtlInt.pega(zoom, 100);
                    exibida = UtlIma.redimensiona(processada, processada.getWidth() * percentagem / 100, processada.getHeight() * percentagem / 100);
                    break;
            }
            TrataIntf.mDimensao(this, exibida.getWidth(), exibida.getHeight());
        }
        repaint();
        revalidate();
        return this;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (exibida != null) {
            g2.drawImage(exibida, 0, 0, null);
        }
    }

}
