package pin.prk;

import java.awt.image.BufferedImage;
import java.util.Objects;
import pin.libbas.Retornos;
import pin.libutl.UtlCrs;
import pin.libutl.UtlIma;
import pin.libutl.UtlRob;

public class RobCondicaoIma extends RobCondicaoAbs {

    public static enum Tp {
        Variavel, Imagem
    }

    public Tp tipo;
    public String variavel;
    public String posX;
    public String posY;
    public String largura;
    public String altura;
    public String toleranciaPer;
    public BufferedImage imagem;

    public RobCondicaoIma() {
        this.tipo = Tp.Variavel;
        this.variavel = null;
        this.posX = null;
        this.posY = null;
        this.largura = null;
        this.altura = null;
        this.toleranciaPer = null;
        this.imagem = null;
    }

    @Override
    public Boolean verifica() throws Exception {
        BufferedImage ima = imagem;
        if (Objects.equals(tipo, Tp.Variavel)) {
            ima = (BufferedImage) proc(variavel);
        }
        Integer rPosX = procInt(posX);
        Integer rPosY = procInt(posY);
        Integer rLargura = procInt(largura);
        Integer rAltura = procInt(altura);
        Float rToleranciaPer = procNum(toleranciaPer);
        Boolean retorno = UtlIma.temImagemNaImagem(UtlRob.pegaTela(rPosX, rPosY, rLargura, rAltura), ima, rToleranciaPer);
        Retornos.bota(pRobotico().pRetornos(), "Tem Imagem", UtlCrs.junta(retorno, tipo, variavel, rPosX, rPosY, rLargura, rAltura, rToleranciaPer));
        return retorno;
    }

}
