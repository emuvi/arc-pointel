package pin.libjan;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.StrokeBorder;
import pin.libfor.Contorno;
import pin.libtex.Marcados;
import pin.libutl.UtlBin;
import pin.libutl.UtlTex;

public class Borda {

    public static enum Tp {
        Limpa, Linha, Chanfro, ChanfroLeve, Gravada, Icone, Contorno, Composta
    };

    private Tp tipo;
    private final Map<String, Object> pars;
    private Border nativa;

    public Borda() {
        this(Tp.Limpa);
    }

    public Borda(Tp tipo) {
        this.tipo = tipo;
        this.pars = new HashMap();
        this.nativa = null;
    }

    public Borda(Border pelaNativa) {
        this.tipo = null;
        this.pars = new HashMap();
        this.nativa = pelaNativa;
        if (pelaNativa instanceof EmptyBorder) {
            tipo = Tp.Limpa;
            pars.put("margemSuperior", ((EmptyBorder) pelaNativa).getBorderInsets().top);
            pars.put("margemEsquerda", ((EmptyBorder) pelaNativa).getBorderInsets().left);
            pars.put("margemInferior", ((EmptyBorder) pelaNativa).getBorderInsets().bottom);
            pars.put("margemDireita", ((EmptyBorder) pelaNativa).getBorderInsets().right);
        } else if (pelaNativa instanceof LineBorder) {
            tipo = Tp.Linha;
            pars.put("cor", ((LineBorder) pelaNativa).getLineColor());
            pars.put("grossura", ((LineBorder) pelaNativa).getThickness());
            pars.put("redonda", ((LineBorder) pelaNativa).getRoundedCorners());
        } else if (pelaNativa instanceof BevelBorder) {
            tipo = Tp.Chanfro;
            pars.put("levantada", ((BevelBorder) pelaNativa).getBevelType() == BevelBorder.RAISED);
            pars.put("corInterna", ((BevelBorder) pelaNativa).getHighlightInnerColor());
            pars.put("corExterna", ((BevelBorder) pelaNativa).getHighlightOuterColor());
            pars.put("sombraInterna", ((BevelBorder) pelaNativa).getShadowInnerColor());
            pars.put("sombraExterna", ((BevelBorder) pelaNativa).getShadowOuterColor());
        } else if (pelaNativa instanceof SoftBevelBorder) {
            tipo = Tp.ChanfroLeve;
            pars.put("levantada", ((SoftBevelBorder) pelaNativa).getBevelType() == BevelBorder.RAISED);
            pars.put("corInterna", ((SoftBevelBorder) pelaNativa).getHighlightInnerColor());
            pars.put("corExterna", ((SoftBevelBorder) pelaNativa).getHighlightOuterColor());
            pars.put("sombraInterna", ((SoftBevelBorder) pelaNativa).getShadowInnerColor());
            pars.put("sombraExterna", ((SoftBevelBorder) pelaNativa).getShadowOuterColor());
        } else if (pelaNativa instanceof EtchedBorder) {
            tipo = Tp.Gravada;
            pars.put("levantada", ((EtchedBorder) pelaNativa).getEtchType() == EtchedBorder.RAISED);
            pars.put("cor", ((EtchedBorder) pelaNativa).getHighlightColor());
            pars.put("sombra", ((EtchedBorder) pelaNativa).getShadowColor());
        } else if (pelaNativa instanceof MatteBorder) {
            tipo = Tp.Icone;
            pars.put("margemSuperior", ((MatteBorder) pelaNativa).getBorderInsets().top);
            pars.put("margemEsquerda", ((MatteBorder) pelaNativa).getBorderInsets().left);
            pars.put("margemInferior", ((MatteBorder) pelaNativa).getBorderInsets().bottom);
            pars.put("margemDireita", ((MatteBorder) pelaNativa).getBorderInsets().right);
            pars.put("icone", ((MatteBorder) pelaNativa).getTileIcon());
        } else if (pelaNativa instanceof StrokeBorder) {
            tipo = Tp.Contorno;
            pars.put("contorno", new Contorno(((StrokeBorder) pelaNativa).getStroke()));
        } else if (pelaNativa instanceof CompoundBorder) {
            tipo = Tp.Composta;
            pars.put("externa", new Borda(((CompoundBorder) pelaNativa).getOutsideBorder()));
            pars.put("interna", new Borda(((CompoundBorder) pelaNativa).getInsideBorder()));
        };
    }

    public static Borda descrito(String nosCaracteres) throws Exception {
        if (nosCaracteres == null) {
            return null;
        }
        String tipo = Marcados.marcado(nosCaracteres, "tp");
        Borda retorno = new Borda(Tp.valueOf(tipo));
        List<String> params = Marcados.marcados(nosCaracteres, "pr");
        for (String param : params) {
            String nome = Marcados.marcado(param, "nm");
            Object valor = UtlBin.descrito(Marcados.marcado(param, "vlr"));
            retorno.params().put(nome, valor);
        }
        return retorno;
    }

    public Tp pTipo() {
        return tipo;
    }

    public Borda mLimpa(Integer margemSuperior, Integer margemEsquerda, Integer margemInferior, Integer margemDireita) {
        tipo = Tp.Limpa;
        pars.clear();
        pars.put("margemSuperior", margemSuperior);
        pars.put("margemEsquerda", margemEsquerda);
        pars.put("margemInferior", margemInferior);
        pars.put("margemDireita", margemDireita);
        nativa = null;
        return this;
    }

    public Borda mLinha(Color cor, Integer grossura, Boolean redonda) {
        tipo = Tp.Linha;
        pars.clear();
        pars.put("cor", cor);
        pars.put("grossura", grossura);
        pars.put("redonda", redonda);
        nativa = null;
        return this;
    }

    public Borda mChanfro(Boolean levantada, Color corInterna, Color corExterna, Color sombraInterna, Color sombraExterna) {
        tipo = Tp.Chanfro;
        pars.clear();
        pars.put("levantada", levantada);
        pars.put("corInterna", corInterna);
        pars.put("corExterna", corExterna);
        pars.put("sombraInterna", sombraInterna);
        pars.put("sombraExterna", sombraExterna);
        nativa = null;
        return this;
    }

    public Borda mChanfroLeve(Boolean levantada, Color corInterna, Color corExterna, Color sombraInterna, Color sombraExterna) {
        tipo = Tp.ChanfroLeve;
        pars.clear();
        pars.put("levantada", levantada);
        pars.put("corInterna", corInterna);
        pars.put("corExterna", corExterna);
        pars.put("sombraInterna", sombraInterna);
        pars.put("sombraExterna", sombraExterna);
        nativa = null;
        return this;
    }

    public Borda mGravada(Boolean levantada, Color cor, Color sombra) {
        tipo = Tp.Gravada;
        pars.clear();
        pars.put("levantada", levantada);
        pars.put("cor", cor);
        pars.put("sombra", sombra);
        nativa = null;
        return this;
    }

    public Borda mIcone(Integer margemSuperior, Integer margemEsquerda, Integer margemInferior, Integer margemDireita, Icon icone) {
        tipo = Tp.Icone;
        pars.clear();
        pars.put("margemSuperior", margemSuperior);
        pars.put("margemEsquerda", margemEsquerda);
        pars.put("margemInferior", margemInferior);
        pars.put("margemDireita", margemDireita);
        pars.put("icone", icone);
        nativa = null;
        return this;
    }

    public Borda mContorno(Contorno contorno) {
        tipo = Tp.Contorno;
        pars.clear();
        pars.put("contorno", contorno);
        nativa = null;
        return this;
    }

    public Borda mComposta(Borda externa, Borda interna) {
        tipo = Tp.Composta;
        pars.clear();
        pars.put("externa", externa);
        pars.put("interna", interna);
        nativa = null;
        return this;
    }

    public Map<String, Object> params() {
        return pars;
    }

    public Object pParam(String comNome) {
        return pars.get(comNome);
    }

    public Borda mParam(String comNome, Object eValor) {
        pars.put(comNome, eValor);
        return this;
    }

    public Border pNativa() {
        if (nativa == null) {
            if (tipo != null) {
                switch (tipo) {
                    case Limpa:
                        nativa = new EmptyBorder(
                                UtlBin.pega(Integer.class, pars.get("margemSuperior"), 0),
                                UtlBin.pega(Integer.class, pars.get("margemEsquerda"), 0),
                                UtlBin.pega(Integer.class, pars.get("margemInferior"), 0),
                                UtlBin.pega(Integer.class, pars.get("margemDireita"), 0)
                        );
                        break;
                    case Linha:
                        nativa = new LineBorder(
                                UtlBin.pega(Color.class, pars.get("cor"), Color.BLACK),
                                UtlBin.pega(Integer.class, pars.get("grossura"), 1),
                                UtlBin.pega(Boolean.class, pars.get("redonda"), false)
                        );
                        break;
                    case Chanfro:
                        nativa = new BevelBorder(
                                UtlBin.pega(Boolean.class, pars.get("levantada"), true) ? BevelBorder.RAISED : BevelBorder.LOWERED,
                                UtlBin.pega(Color.class, pars.get("corInterna"), null),
                                UtlBin.pega(Color.class, pars.get("corExterna"), null),
                                UtlBin.pega(Color.class, pars.get("sombraInterna"), null),
                                UtlBin.pega(Color.class, pars.get("sombraExterna"), null)
                        );
                        break;
                    case ChanfroLeve:
                        nativa = new SoftBevelBorder(
                                UtlBin.pega(Boolean.class, pars.get("levantada"), true) ? BevelBorder.RAISED : BevelBorder.LOWERED,
                                UtlBin.pega(Color.class, pars.get("corInterna"), null),
                                UtlBin.pega(Color.class, pars.get("corExterna"), null),
                                UtlBin.pega(Color.class, pars.get("sombraInterna"), null),
                                UtlBin.pega(Color.class, pars.get("sombraExterna"), null)
                        );
                        break;
                    case Gravada:
                        nativa = new EtchedBorder(
                                UtlBin.pega(Boolean.class, pars.get("levantada"), true) ? BevelBorder.RAISED : BevelBorder.LOWERED,
                                UtlBin.pega(Color.class, pars.get("cor"), Color.BLACK),
                                UtlBin.pega(Color.class, pars.get("sombra"), Color.DARK_GRAY)
                        );
                        break;
                    case Icone:
                        nativa = new MatteBorder(
                                UtlBin.pega(Integer.class, pars.get("margemSuperior"), 0),
                                UtlBin.pega(Integer.class, pars.get("margemEsquerda"), 0),
                                UtlBin.pega(Integer.class, pars.get("margemInferior"), 0),
                                UtlBin.pega(Integer.class, pars.get("margemDireita"), 0),
                                UtlBin.pega(Icon.class, pars.get("icone"), null)
                        );
                        break;
                    case Contorno:
                        nativa = new StrokeBorder(
                                UtlBin.pega(Contorno.class, pars.get("contorno"), null).pNativo()
                        );
                        break;
                    case Composta:
                        nativa = new CompoundBorder(
                                UtlBin.pega(Borda.class, pars.get("externa"), null).pNativa(),
                                UtlBin.pega(Borda.class, pars.get("interna"), null).pNativa()
                        );
                        break;
                }
            }
        }
        return nativa;
    }

    public String descreve() {
        String retorno = Marcados.marca(tipo.toString(), "tp") + UtlTex.quebra();
        for (Map.Entry<String, Object> ent : pars.entrySet()) {
            String par = Marcados.marca(ent.getKey(), "nm")
                    + Marcados.marca(UtlBin.descreve(ent.getValue()), "vlr");
            retorno += Marcados.marca(par, "pr") + UtlTex.quebra();
        }
        return retorno;
    }

}
