package pin.libfor;

public class FonteImpCmp {

    private Integer x;
    private Integer y;
    private Integer largura;
    private Integer altura;
    private String linha;

    public FonteImpCmp(Integer x, Integer y, Integer largura, Integer altura, String linha) {
        this.x = x;
        this.y = y;
        this.largura = largura;
        this.altura = altura;
        this.linha = linha;
    }

    public Integer pegaX() {
        return x;
    }

    public FonteImpCmp mudaX(Integer x) {
        this.x = x;
        return this;
    }

    public Integer pegaY() {
        return y;
    }

    public FonteImpCmp mudaY(Integer y) {
        this.y = y;
        return this;
    }

    public Integer pegaLargura() {
        return largura;
    }

    public FonteImpCmp mudaLargura(Integer largura) {
        this.largura = largura;
        return this;
    }

    public Integer pegaAltura() {
        return altura;
    }

    public FonteImpCmp mudaAltura(Integer altura) {
        this.altura = altura;
        return this;
    }

    public String pegaLinha() {
        return linha;
    }

    public FonteImpCmp mudaLinha(String linha) {
        this.linha = linha;
        return this;
    }

    @Override
    public String toString() {
        return "Linha: " + linha + " , x = " + x + " , y = " + x + " , w = " + largura + " , h = " + altura;
    }

}
