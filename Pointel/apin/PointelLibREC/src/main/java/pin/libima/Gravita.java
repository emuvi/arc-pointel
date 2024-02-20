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

public class Gravita {

    private BufferedImage imagem;
    private Float percentual;
    private List<Rectangle> partes;
    private Cores cores;
    private Color referencia;
    private int[] diferencas;

    public Gravita(BufferedImage aImagem) {
        imagem = aImagem;
        partes = null;
        cores = null;
        referencia = null;
    }

    public Gravita botaPartes(List<Rectangle> asPartes) {
        partes = asPartes;
        return this;
    }

    public Gravita botaCores(Cores asCores) {
        cores = asCores;
        return this;
    }

    public Gravita botaReferencia(Color aReferencia) {
        referencia = aReferencia;
        return this;
    }

    public BufferedImage faz(Color paraCor, Float noPercentual) {
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
        if (referencia == null) {
            referencia = new CorMedia(imagem)
                    .botaPartes(partes)
                    .botaCores(cores)
                    .faz();
        }
        diferencas = UtlCor.pegaDiferencas(referencia, paraCor);
        MultiParalelo mltGra = new MultiParalelo("Gravita");
        for (int io = 0; io < partes.size(); io++) {
            mltGra.botaExecutor(new ParGravita(mltGra, io));
        }
        mltGra.iniciaEEspera();
        return imagem;
    }

    private class ParGravita extends Paralelo {

        public ParGravita(MultiParalelo doMultiplo, Integer noIndice) {
            super(doMultiplo, noIndice);
        }

        @Override
        public void run() {
            try {
                cores.processa(new AnsGravita(), partes.get(pegaIndice()));
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }

        private class AnsGravita implements Analisado<PontoColorido, Color> {

            @Override
            public Color analisa(PontoColorido oValor) {
                return UtlCor.gravita(oValor.cor, diferencas, percentual);
            }

        }
    }
}
