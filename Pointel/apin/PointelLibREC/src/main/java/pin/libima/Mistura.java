package pin.libima;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;
import pin.libbas.Analisado;
import pin.libbas.MultiParalelo;
import pin.libbas.Paralelo;
import pin.libutl.Utl;
import pin.libutl.UtlCor;
import pin.libutl.UtlIma;

public class Mistura {

    private BufferedImage imagem;
    private Color mistura;
    private Float percentual;
    private List<Rectangle> partes;
    private Cores cores;

    public Mistura(BufferedImage naImagem) {
        imagem = naImagem;
        partes = null;
        cores = null;
    }

    public Mistura botaPartes(List<Rectangle> asPartes) {
        partes = asPartes;
        return this;
    }

    public Mistura botaCores(Cores asCores) {
        cores = asCores;
        return this;
    }

    public BufferedImage faz(Color paraCor, Float noPercentual) {
        mistura = paraCor;
        percentual = noPercentual;
        if (imagem.getType() != BufferedImage.TYPE_INT_ARGB) {
            imagem = UtlIma.copiaParaARGB(imagem);
        }
        if (partes == null) {
            partes = UtlIma.demarca(imagem);
        }
        if (cores == null) {
            cores = new Cores(imagem);
        }
        MultiParalelo mltMst = new MultiParalelo("Mistura");
        for (int io = 0; io < partes.size(); io++) {
            mltMst.botaExecutor(new ParMistura(mltMst, io));
        }
        mltMst.iniciaEEspera();
        return imagem;
    }

    private class ParMistura extends Paralelo {

        public ParMistura(MultiParalelo doMultiplo, Integer noIndice) {
            super(doMultiplo, noIndice);
        }

        @Override
        public void run() {
            try {
                cores.processa(new ProMistura(), partes.get(pegaIndice()));
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }

        private class ProMistura implements Analisado<PontoColorido, Color> {

            @Override
            public Color analisa(PontoColorido oValor) {
                return UtlCor.mistura(mistura, percentual, oValor.cor);
            }

        }
    }
}
