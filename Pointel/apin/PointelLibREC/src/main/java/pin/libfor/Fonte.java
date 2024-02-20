package pin.libfor;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.util.Objects;
import pin.libbas.Partes;
import pin.libutl.UtlCrs;
import pin.libutl.UtlInt;

public class Fonte {

    public static enum Nm {
        Normal(Font.DIALOG), Entrada(Font.DIALOG_INPUT), ComSerifa(Font.SERIF), SemSerifa(Font.SANS_SERIF), Quadrada(Font.MONOSPACED);

        private final String nativa;

        private Nm(String codigo) {
            this.nativa = codigo;
        }

        public String pNativa() {
            return nativa;
        }
    }

    public static enum Est {
        Normal(Font.PLAIN), Negrito(Font.BOLD), Italico(Font.ITALIC), NegritoItalico(Font.BOLD | Font.ITALIC);

        private final Integer nativo;

        private Est(Integer codigo) {
            this.nativo = codigo;
        }

        public Integer pNativo() {
            return nativo;
        }

        public static Est pega(Integer peloNativo) {
            for (Est est : Est.values()) {
                if (Objects.equals(est.pNativo(), peloNativo)) {
                    return est;
                }
            }
            return Normal;
        }
    }

    private String nome;
    private Integer tamanho;
    private Integer estilo;
    private Font nativa;

    public Fonte() {
        this.nome = Nm.Normal.pNativa();
        this.tamanho = 14;
        this.estilo = Est.Normal.pNativo();
        this.nativa = null;
    }

    public Fonte(Nm nome, Integer tamanho, Est estilo) {
        this.nome = nome.pNativa();
        this.tamanho = tamanho;
        this.estilo = estilo.pNativo();
        this.nativa = null;
    }

    public Fonte(Nm nome, Integer tamanho, Integer estilo) {
        this.nome = nome.pNativa();
        this.tamanho = tamanho;
        this.estilo = estilo;
        this.nativa = null;
    }

    public Fonte(String nome, Integer tamanho, Est estilo) {
        this.nome = nome;
        this.tamanho = tamanho;
        this.estilo = estilo.pNativo();
        this.nativa = null;
    }

    public Fonte(String nome, Integer tamanho, Integer estilo) {
        this.nome = nome;
        this.tamanho = tamanho;
        this.estilo = estilo;
        this.nativa = null;
    }

    public Fonte(Font daFonte) {
        this.nome = daFonte.getFamily();
        this.tamanho = daFonte.getSize();
        this.estilo = daFonte.getStyle();
        this.nativa = null;
    }

    public static Fonte descrito(String nosCaracteres) {
        if (nosCaracteres == null) {
            return null;
        }
        String[] prts = Partes.pega(nosCaracteres);
        String nome = prts[0];
        Integer tamanho = UtlInt.pega(prts[1]);
        Integer estilo = UtlInt.pega(prts[2]);
        return new Fonte(nome, tamanho, estilo);
    }

    public String pNome() {
        return nome;
    }

    public Fonte mNome(String para) {
        this.nome = para;
        nativa = null;
        return this;
    }

    public Fonte mNome(Nm para) {
        this.nome = para.pNativa();
        nativa = null;
        return this;
    }

    public Integer pTamanho() {
        return tamanho;
    }

    public Fonte mTamanho(Integer para) {
        this.tamanho = para;
        nativa = null;
        return this;
    }

    public Integer pEstilo() {
        return estilo;
    }

    public Fonte mEstilo(Est para) {
        this.estilo = para.pNativo();
        nativa = null;
        return this;
    }

    public Fonte mEstilo(Integer para) {
        this.estilo = para;
        nativa = null;
        return this;
    }

    public Font pNativa() {
        if (nativa == null) {
            nativa = new Font(nome, estilo, tamanho);
        }
        return nativa;
    }

    public Dimension pDimensao(String doTexto, FontRenderContext contexto) {
        return pDimensao(doTexto, null, contexto);
    }

    public Dimension pDimensao(String doTexto, Integer larguraDaQuebra, FontRenderContext contexto) {
        Dimension retorno = new Dimension(0, 0);
        if (doTexto != null) {
            FonteImp imp = new FonteImp(this, doTexto, larguraDaQuebra);
            for (FonteImpCmp cmp : imp.geraImp(contexto)) {
                if (cmp.pegaLargura() > retorno.width) {
                    retorno.width = cmp.pegaLargura();
                }
                retorno.height = retorno.height + cmp.pegaAltura();
            }
        }
        return retorno;
    }

    public String descreve() {
        return Partes.junta(nome, UtlCrs.pega(tamanho, "null"), UtlCrs.pega(estilo, "null"));
    }

}
