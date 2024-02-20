package pin.libfor;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import pin.libtex.Marcados;
import pin.libutl.UtlTex;
import pin.libvlr.VCor;
import pin.libvlr.VIma;
import pin.libvlr.VLog;
import pin.libvlr.VNum;

public class Fundo {

    public static enum Tp {
        Solido, Gradiente, Textura
    }

    private Tp tipo;
    private Color cor;
    private Color cor2;
    private Float x1;
    private Float y1;
    private Float x2;
    private Float y2;
    private Boolean ciclico;
    private BufferedImage imagem;
    private Paint nativo;

    public Fundo() {
        this(Color.white);
    }

    public Fundo(Color naCor) {
        muda(naCor);
    }

    public Fundo(Float x1, Float y1, Color cor1, Float x2, Float y2, Color cor2, boolean ciclico) {
        muda(x1, y1, cor1, x2, y2, cor2, ciclico);
    }

    public Fundo(Float x, Float y, Float largura, Float altura, BufferedImage imagem) {
        muda(x, y, largura, altura, imagem);
    }

    public static Fundo descrito(String nosCaracteres) {
        if (nosCaracteres == null) {
            return null;
        }
        Fundo retorno = new Fundo();
        String tipo = Marcados.marcado(nosCaracteres, "tipo");
        if (tipo != null) {
            retorno.mTipo(Tp.valueOf(tipo));
        }
        retorno.mCor(new VCor(Marcados.marcado(nosCaracteres, "cor")).pgCor());
        retorno.mCor2(new VCor(Marcados.marcado(nosCaracteres, "cor2")).pgCor());
        retorno.mX1(new VNum(Marcados.marcado(nosCaracteres, "x1")).pgNum());
        retorno.mY1(new VNum(Marcados.marcado(nosCaracteres, "y1")).pgNum());
        retorno.mX2(new VNum(Marcados.marcado(nosCaracteres, "x2")).pgNum());
        retorno.mY2(new VNum(Marcados.marcado(nosCaracteres, "y2")).pgNum());
        retorno.mCiclico(new VLog(Marcados.marcado(nosCaracteres, "ciclico")).pgLog());
        retorno.mImagem(new VIma(Marcados.marcado(nosCaracteres, "imagem")).pgIma());
        return retorno;
    }

    public Fundo muda(Color paraCor) {
        this.tipo = Tp.Solido;
        this.cor = paraCor;
        this.cor2 = null;
        this.x1 = null;
        this.y1 = null;
        this.x2 = null;
        this.y2 = null;
        this.ciclico = null;
        this.imagem = null;
        atualiza();
        return this;
    }

    public Fundo muda(Float x1, Float y1, Color cor1, Float x2, Float y2, Color cor2, boolean ciclico) {
        this.tipo = Tp.Gradiente;
        this.cor = cor1;
        this.cor2 = cor2;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.ciclico = ciclico;
        this.imagem = null;
        atualiza();
        return this;
    }

    public Fundo muda(Float x, Float y, Float largura, Float altura, BufferedImage imagem) {
        this.tipo = Tp.Textura;
        this.cor = null;
        this.cor2 = null;
        this.x1 = x;
        this.y1 = y;
        this.x2 = largura;
        this.y2 = altura;
        this.ciclico = null;
        this.imagem = imagem;
        atualiza();
        return this;
    }

    private void atualiza() {
        nativo = null;
        if (tipo != null) {
            switch (tipo) {
                case Solido:
                    nativo = cor;
                    break;
                case Gradiente:
                    nativo = new GradientPaint(x1, y1, cor, x2, y2, cor2, ciclico);
                    break;
                case Textura:
                    nativo = new TexturePaint(imagem, new Rectangle(Math.round(x1), Math.round(y1), Math.round(x2), Math.round(y2)));
                    break;
            }
        }
    }

    public Tp pTipo() {
        return tipo;
    }

    public Fundo mTipo(Tp tipo) {
        this.tipo = tipo;
        atualiza();
        return this;
    }

    public Color pCor() {
        return cor;
    }

    public Fundo mCor(Color cor) {
        this.cor = cor;
        atualiza();
        return this;
    }

    public Color pCor2() {
        return cor2;
    }

    public Fundo mCor2(Color cor2) {
        this.cor2 = cor2;
        atualiza();
        return this;
    }

    public Float pX1() {
        return x1;
    }

    public Fundo mX1(Float x1) {
        this.x1 = x1;
        atualiza();
        return this;
    }

    public Float pY1() {
        return y1;
    }

    public Fundo mY1(Float y1) {
        this.y1 = y1;
        atualiza();
        return this;
    }

    public Float pX2() {
        return x2;
    }

    public Fundo mX2(Float x2) {
        this.x2 = x2;
        atualiza();
        return this;
    }

    public Float pY2() {
        return y2;
    }

    public Fundo mY2(Float y2) {
        this.y2 = y2;
        atualiza();
        return this;
    }

    public Boolean pCiclico() {
        return ciclico;
    }

    public Fundo mCiclico(Boolean ciclico) {
        this.ciclico = ciclico;
        atualiza();
        return this;
    }

    public BufferedImage pImagem() {
        return imagem;
    }

    public Fundo mImagem(BufferedImage imagem) {
        this.imagem = imagem;
        atualiza();
        return this;
    }

    public Paint pNativo() {
        return nativo;
    }

    public void aplica(Graphics2D gr) {
        gr.setPaint(nativo);

    }

    public String descreve() {
        String retorno = Marcados.marca(tipo.toString(), "tipo") + UtlTex.quebra();
        retorno += Marcados.marca(new VCor(cor).descreve(), "cor") + UtlTex.quebra();
        retorno += Marcados.marca(new VCor(cor2).descreve(), "cor2") + UtlTex.quebra();
        retorno += Marcados.marca(new VNum(x1).descreve(), "x1") + UtlTex.quebra();
        retorno += Marcados.marca(new VNum(y1).descreve(), "y1") + UtlTex.quebra();
        retorno += Marcados.marca(new VNum(x2).descreve(), "x2") + UtlTex.quebra();
        retorno += Marcados.marca(new VNum(y2).descreve(), "y2") + UtlTex.quebra();
        retorno += Marcados.marca(new VLog(ciclico).descreve(), "ciclico") + UtlTex.quebra();
        retorno += Marcados.marca(new VIma(imagem).descreve(), "imagem") + UtlTex.quebra();
        return retorno;
    }

}
