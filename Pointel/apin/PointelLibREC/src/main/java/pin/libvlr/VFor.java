package pin.libvlr;

import java.awt.image.BufferedImage;
import pin.libfor.Contorno;
import pin.libfor.Fonte;
import pin.libfor.Fundo;
import pin.libfor.Generico;

public class VFor extends Vlr {

    public static enum Tp {
        Generico,
        Texto, Imagem,
        Quadrado, Retangulo,
        Circulo, Elipse,
        Seta, Uniao,
        Linha, Ponto,
        Vazia
    };

    private volatile Tp tipo;
    private volatile Generico generico;
    private volatile String texto;
    private volatile Fonte textoFonte;
    private volatile BufferedImage imagem;
    private volatile Contorno contorno;
    private volatile Fundo preenchimento;
    private volatile Double angulo;
    private volatile Boolean formatoInscrito;

    public VFor() {
        this(Tp.Quadrado);
    }

    public VFor(Tp doTipo) {
        tipo = doTipo;
        generico = null;
        texto = null;
        textoFonte = null;
        imagem = null;
        contorno = null;
        preenchimento = null;
        angulo = null;
        formatoInscrito = true;
    }

    public VFor(Object doValor) {

    }

    public static VFor descrito(String nosCaracteres) {
        return null;
    }

    @Override
    public Object pg(Object comPadrao) {
        return comPadrao;
    }

    @Override
    public VFor md(Object oValor) {
        return this;
    }

    @Override
    public Boolean vazio() {
        return tipo == Tp.Vazia;
    }

    @Override
    public String paraConsSQL() {
        return descreve();
    }

    @Override
    public Object paraParamSQL() {
        return descreve();
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public String descreve() {
        return null;
    }

    public Tp pTipo() {
        return tipo;
    }

    public VFor mTipo(Tp para) {
        this.tipo = para;
        if (tipo != Tp.Imagem) {
            imagem = null;
        }
        return this;
    }

    public Generico pGenerico() {
        return generico;
    }

    public VFor mGenerico(Generico para) {
        this.generico = para;
        return this;
    }

    public String pTexto() {
        return texto;
    }

    public VFor mTexto(String para) {
        this.texto = para;
        return this;
    }

    public Fonte pTextoFonte() {
        return textoFonte;
    }

    public VFor mTextoFonte(Fonte para) {
        this.textoFonte = para;
        return this;
    }

    public BufferedImage pImagem() {
        return imagem;
    }

    public VFor mImagem(BufferedImage para) {
        this.imagem = para;
        return this;
    }

    public Contorno pContorno() {
        return contorno;
    }

    public VFor mContorno(Contorno para) {
        this.contorno = para;
        return this;
    }

    public Fundo pPreenchimento() {
        return preenchimento;
    }

    public VFor mPreenchimento(Fundo para) {
        this.preenchimento = para;
        return this;
    }

    public Double pAngulo() {
        return angulo;
    }

    public VFor mAngulo(Double para) {
        this.angulo = para;
        return this;
    }

    public Boolean ehFormatoInscrito() {
        return formatoInscrito;
    }

    public VFor botaFormatoInscrito() {
        this.formatoInscrito = true;
        return this;
    }

    public VFor tiraFormatoInscrito() {
        this.formatoInscrito = false;
        return this;
    }

    public VFor trocaFormatoInscrito() {
        this.formatoInscrito = !this.formatoInscrito;
        return this;
    }

    public VFor mFormatoInscrito(Boolean para) {
        this.formatoInscrito = para;
        return this;
    }

}
