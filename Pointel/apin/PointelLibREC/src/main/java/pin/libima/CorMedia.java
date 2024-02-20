package pin.libima;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import pin.libbas.Analisado;
import pin.libbas.MultiParalelo;
import pin.libbas.Paralelo;
import pin.libbas.ParsParams;
import pin.libutl.Utl;
import pin.libutl.UtlCor;
import pin.libutl.UtlIma;

public class CorMedia {

    private BufferedImage imagem;
    private List<Rectangle> partes;
    private Cores cores;
    private ParsParams<Integer, Color> medias;

    public CorMedia(BufferedImage daImagem) {
        imagem = daImagem;
        partes = null;
        cores = null;
    }

    public CorMedia botaPartes(List<Rectangle> asPartes) {
        partes = asPartes;
        return this;
    }

    public CorMedia botaCores(Cores asCores) {
        cores = asCores;
        return this;
    }

    public Color faz() {
        if (imagem.getType() != BufferedImage.TYPE_INT_ARGB) {
            imagem = UtlIma.copiaParaARGB(imagem);
        }
        if (partes == null) {
            partes = UtlIma.demarca(imagem);
        }
        if (cores == null) {
            cores = new Cores(imagem);
        }
        medias = new ParsParams<>();
        MultiParalelo mltMed = new MultiParalelo("CorMédia");
        for (int io = 0; io < partes.size(); io++) {
            mltMed.botaExecutor(new ParCorMedia(mltMed, io));
        }
        mltMed.iniciaEEspera();
        Color retorno = null;
        for (Map.Entry<Integer, Color> ent : medias.lista()) {
            if (retorno == null) {
                retorno = ent.getValue();
            } else {
                retorno = UtlCor.media(retorno, ent.getValue());
            }
        }
        return retorno;
    }

    private class ParCorMedia extends Paralelo {

        public ParCorMedia(MultiParalelo doMultiplo, Integer noIndice) {
            super(doMultiplo, noIndice);
        }

        @Override
        public void run() {
            try {
                cores.processa(new AnsMedia(), partes.get(pegaIndice()));
            } catch (Exception ex) {
                Utl.registra(ex);
            }

        }

        private class AnsMedia implements Analisado<PontoColorido, Color> {

            @Override
            public Color analisa(PontoColorido oValor) {
                Color md = medias.pega(pegaIndice());
                if (md == null) {
                    md = oValor.cor;
                } else {
                    md = UtlCor.media(md, oValor.cor);
                }
                medias.bota(pegaIndice(), md);
                return null;
            }

        }
    }

}
