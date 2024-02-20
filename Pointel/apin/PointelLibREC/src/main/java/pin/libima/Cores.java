package pin.libima;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import pin.libbas.Analisado;
import pin.libutl.Utl;
import pin.libutl.UtlCor;

public class Cores {

    private int[] base;
    private Color[] cores;
    private Integer largura;

    public Cores(BufferedImage daImagemARGB) {
        this(((DataBufferInt) daImagemARGB.getRaster().getDataBuffer()).getData(), daImagemARGB.getWidth());
    }

    public Cores(int[] baseDeCores, Integer comLargura) {
        base = baseDeCores;
        largura = comLargura;
        cores = new Color[base.length];
        for (int ic = 0; ic < cores.length; ic++) {
            cores[ic] = new Color(base[ic]);
        }
    }

    private int pegaIndice(Integer daPosX, Integer ePosY) {
        return (cores.length / largura * ePosY) + daPosX;
    }

    public synchronized Color pega(Integer daPosX, Integer ePosY) {
        return cores[pegaIndice(daPosX, ePosY)];
    }

    public synchronized Cores bota(Integer naPosX, Integer ePosY, Color aCor) {
        int ind = pegaIndice(naPosX, ePosY);
        base[ind] = aCor.getRGB();
        cores[ind] = aCor;
        return this;
    }

    public synchronized CoresArea pega(Rectangle daArea) {
        Color[] acores = new Color[daArea.width * daArea.height];
        int ic = 0;
        for (int y = daArea.y; y < daArea.y + daArea.height; y++) {
            for (int x = daArea.x; x < daArea.x + daArea.width; x++) {
                acores[ic] = pega(x, y);
                ic++;
            }
        }
        return new CoresArea(acores, daArea);
    }

    public synchronized Cores bota(CoresArea daArea) {
        Color[] acores = daArea.cores();
        Rectangle area = daArea.area();
        int ic = 0;
        for (int y = area.y; y < area.y + area.height; y++) {
            for (int x = area.x; x < area.x + area.width; x++) {
                bota(x, y, acores[ic]);
                ic++;
            }
        }
        return this;
    }

    public synchronized Cores analisa(Integer daPosX, Integer ePosY, Analisado<Color, Color> comAnalisador) throws Exception {
        Color retorno = comAnalisador.analisa(pega(daPosX, ePosY));
        if (retorno != null) {
            bota(daPosX, ePosY, retorno);
        }
        return this;
    }

    public void processa(Analisado<PontoColorido, Color> comAnalisador, Rectangle noEspaco) throws Exception {
        CoresArea acores = pega(noEspaco);
        for (int y = noEspaco.y; y < noEspaco.y + noEspaco.height; y++) {
            for (int x = noEspaco.x; x < noEspaco.x + noEspaco.width; x++) {
                Color novaCor = comAnalisador.analisa(new PontoColorido(x, y, acores.pega(x, y)));
                if (novaCor != null) {
                    acores.bota(x, y, novaCor);
                }
            }
        }
        bota(acores);
    }

    public void imprimi(Rectangle doEspaco) throws Exception {
        processa(new AnsImprimi(), doEspaco);
    }

    private class AnsImprimi implements Analisado<PontoColorido, Color> {

        @Override
        public Color analisa(PontoColorido oValor) throws Exception {
            Utl.imp("x = " + oValor.x + " y = " + oValor.y + " cor = " + UtlCor.formata(oValor.cor));
            return null;
        }

    }

}
