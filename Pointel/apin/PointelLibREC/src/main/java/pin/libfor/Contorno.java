package pin.libfor;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.util.Objects;
import pin.libutl.UtlLis;

public class Contorno {

    public static enum Decoracao {
        Alvo(BasicStroke.CAP_BUTT), Redondo(BasicStroke.CAP_ROUND), Quadrado(BasicStroke.CAP_SQUARE);

        private final Integer codigo;

        private Decoracao(Integer codigo) {
            this.codigo = codigo;
        }

        public Integer pCodigo() {
            return codigo;
        }

        public static Decoracao pega(Integer doCodigo) {
            for (Decoracao dc : Decoracao.values()) {
                if (Objects.equals(dc.pCodigo(), doCodigo)) {
                    return dc;
                }
            }
            return null;
        }
    };

    public static enum Uniao {
        Redonda(BasicStroke.JOIN_ROUND), Chanfro(BasicStroke.JOIN_BEVEL), Malhete(BasicStroke.JOIN_MITER);

        private final Integer codigo;

        private Uniao(Integer codigo) {
            this.codigo = codigo;
        }

        public Integer pCodigo() {
            return codigo;
        }

        public static Uniao pega(Integer doCodigo) {
            for (Uniao un : Uniao.values()) {
                if (Objects.equals(un.pCodigo(), doCodigo)) {
                    return un;
                }
            }
            return null;
        }
    };

    private Float largura;
    private Decoracao decoracao;
    private Uniao uniao;
    private Float limite;
    private Float[] traco;
    private Float fase;
    private Fundo preenchimento;
    private BasicStroke nativo;

    public Contorno() {
        muda();
        this.preenchimento = null;
    }

    public Contorno(Float largura) {
        muda(largura);
        this.preenchimento = null;
    }

    public Contorno(Float largura, Decoracao decoracao, Uniao uniao) {
        muda(largura, decoracao, uniao);
        this.preenchimento = null;
    }

    public Contorno(Float largura, Decoracao decoracao, Uniao uniao, Float limite) {
        muda(largura, decoracao, uniao, limite);
        this.preenchimento = null;
    }

    public Contorno(Float largura, Decoracao decoracao, Uniao uniao, Float limite, Float[] traco, Float tracoFase) {
        muda(largura, decoracao, uniao, limite, traco, tracoFase);
        this.preenchimento = null;
    }

    public Contorno(BasicStroke peloStroke) {
        this(peloStroke.getLineWidth(), Decoracao.pega(peloStroke.getEndCap()), Uniao.pega(peloStroke.getLineJoin()), peloStroke.getMiterLimit(), UtlLis.trocaPrimitivo(peloStroke.getDashArray()), peloStroke.getDashPhase());
        nativo = peloStroke;
    }

    public Contorno muda() {
        return muda(1.0f, Decoracao.Quadrado, Uniao.Malhete, 10.0f, null, 0.0f);
    }

    public Contorno muda(Float largura) {
        return muda(largura, Decoracao.Quadrado, Uniao.Malhete, 10.0f, null, 0.0f);
    }

    public Contorno muda(Float largura, Decoracao decoracao, Uniao uniao) {
        return muda(largura, decoracao, uniao, 10.0f, null, 0.0f);
    }

    public Contorno muda(Float largura, Decoracao decoracao, Uniao uniao, Float limite) {
        return muda(largura, decoracao, uniao, limite, null, 0.0f);
    }

    public Contorno muda(Float largura, Decoracao decoracao, Uniao uniao, Float limite, Float[] traco, Float fase) {
        this.largura = largura;
        this.decoracao = decoracao;
        this.uniao = uniao;
        this.limite = limite;
        this.traco = traco;
        this.fase = fase;
        atualiza();
        return this;
    }

    private void atualiza() {
        nativo = new BasicStroke(largura, decoracao.pCodigo(), uniao.pCodigo(), limite, UtlLis.trocaPrimitivo(traco), fase);
    }

    public Float pLargura() {
        return largura;
    }

    public Contorno mLargura(Float largura) {
        this.largura = largura;
        atualiza();
        return this;
    }

    public Decoracao pDecoracao() {
        return decoracao;
    }

    public Contorno mDecoracao(Decoracao decoracao) {
        this.decoracao = decoracao;
        atualiza();
        return this;
    }

    public Uniao pUniao() {
        return uniao;
    }

    public Contorno mUniao(Uniao uniao) {
        this.uniao = uniao;
        atualiza();
        return this;
    }

    public Float pLimite() {
        return limite;
    }

    public Contorno mLimite(Float limite) {
        this.limite = limite;
        atualiza();
        return this;
    }

    public Float[] pTraco() {
        return traco;
    }

    public Contorno mTraco(Float[] traco) {
        this.traco = traco;
        atualiza();
        return this;
    }

    public Float pFase() {
        return fase;
    }

    public Contorno mFase(Float fase) {
        this.fase = fase;
        atualiza();
        return this;
    }

    public Fundo pFundo() {
        return preenchimento;
    }

    public Contorno mFundo(Fundo preenchimento) {
        this.preenchimento = preenchimento;
        return this;
    }

    public BasicStroke pNativo() {
        return nativo;
    }

    public void aplica(Graphics2D gr) {
        if (preenchimento != null) {
            preenchimento.aplica(gr);
        }
        gr.setStroke(nativo);
    }

}
