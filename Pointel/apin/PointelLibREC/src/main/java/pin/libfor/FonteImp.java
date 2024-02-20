package pin.libfor;

import pin.libfor.Fundo;
import pin.libfor.Contorno;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import pin.libutl.UtlCrs;
import pin.libutl.UtlTex;

public class FonteImp {

    private Fonte fonte;
    private String texto;
    private Integer larguraDaQuebra;

    public FonteImp(Fonte fonte, String texto, Integer larguraDaQuebra) {
        this.fonte = fonte;
        this.texto = texto;
        this.larguraDaQuebra = larguraDaQuebra;
    }

    public Fonte pegaFonte() {
        return fonte;
    }

    public FonteImp mudaFonte(Fonte fonte) {
        this.fonte = fonte;
        return this;
    }

    public String pegaTexto() {
        return texto;
    }

    public FonteImp mudaTexto(String texto) {
        this.texto = texto;
        return this;
    }

    public Integer pegaLarguraDaQuebra() {
        return larguraDaQuebra;
    }

    public FonteImp mudaLarguraDaQuebra(Integer larguraDaQuebra) {
        this.larguraDaQuebra = larguraDaQuebra;
        return this;
    }

    private Rectangle nrm(Rectangle oRetangulo, FontRenderContext noContexto, String aLinha) {
        oRetangulo.x = 0;
        oRetangulo.height = new Float(fonte.pNativa().getLineMetrics(aLinha, noContexto).getHeight()).intValue() + 1;
        return oRetangulo;
    }

    public List<FonteImpCmp> geraImp(FontRenderContext noContexto) {
        List<FonteImpCmp> retorno = new ArrayList<>();
        if (texto != null) {
            Rectangle ultimaLinha = null;
            String[] linhas = UtlTex.pLinhas(texto);
            for (String linha : linhas) {
                if (larguraDaQuebra == null) {
                    Rectangle atual = nrm(fonte.pNativa().getStringBounds(linha, noContexto).getBounds(), noContexto, linha);
                    if (ultimaLinha != null) {
                        atual.y = ultimaLinha.y + ultimaLinha.height;
                    } else {
                        atual.y = 0;
                    }
                    retorno.add(new FonteImpCmp(atual.x, atual.y, atual.width, atual.height, linha));
                    ultimaLinha = atual;
                } else {
                    Rectangle atual = nrm(fonte.pNativa().getStringBounds(linha, noContexto).getBounds(), noContexto, linha);
                    if (atual.width <= larguraDaQuebra) {
                        if (ultimaLinha != null) {
                            atual.y = ultimaLinha.y + ultimaLinha.height;
                        } else {
                            atual.y = 0;
                        }
                        retorno.add(new FonteImpCmp(atual.x, atual.y, atual.width, atual.height, linha));
                        ultimaLinha = atual;
                    } else {
                        List<String> palavras = new ArrayList<>(Arrays.asList(linha.split("\\s")));
                        while (!palavras.isEmpty()) {
                            int qnt = 0;
                            String ln = "";
                            Rectangle at = nrm(fonte.pNativa().getStringBounds(ln, noContexto).getBounds(), noContexto, ln);
                            while (at.width <= larguraDaQuebra) {
                                qnt++;
                                if (qnt > palavras.size()) {
                                    break;
                                }
                                ln = UtlCrs.junta(palavras.subList(0, qnt), " ");
                                at = nrm(fonte.pNativa().getStringBounds(ln, noContexto).getBounds(), noContexto, ln);
                            }
                            qnt--;
                            if (qnt <= 0) {
                                qnt = 1;
                            }
                            List<String> sub = palavras.subList(0, qnt);
                            ln = UtlCrs.junta(sub, " ");
                            at = nrm(fonte.pNativa().getStringBounds(ln, noContexto).getBounds(), noContexto, ln);
                            if (ultimaLinha != null) {
                                at.y = ultimaLinha.y + ultimaLinha.height;
                            } else {
                                at.y = 0;
                            }
                            retorno.add(new FonteImpCmp(at.x, at.y, at.width, at.height, ln));
                            ultimaLinha = at;
                            sub.clear();
                        }
                    }
                }
            }
        }
        return retorno;
    }

    public BufferedImage geraImg(FontRenderContext noContexto, Contorno comContorno, Fundo ePreenchimento) {
        List<FonteImpCmp> impTexto = geraImp(noContexto);
        int larg = 0;
        int alt = 0;
        if (larguraDaQuebra != null) {
            larg = larguraDaQuebra;
        } else {
            for (FonteImpCmp cmp : impTexto) {
                if (cmp.pegaLargura() > larg) {
                    larg = cmp.pegaLargura();
                }
            }
        }
        int seg = 3 * fonte.pTamanho() / 10;
        FonteImpCmp ult = impTexto.get(impTexto.size() - 1);
        alt = ult.pegaY() + ult.pegaAltura() + seg;
        BufferedImage retorno = new BufferedImage(larg, alt, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gr = retorno.createGraphics();
        gr.setBackground(new Color(0, 0, 0, 0));
        gr.setColor(Color.black);
        gr.setFont(fonte.pNativa());
        if (comContorno != null) {
            comContorno.aplica(gr);
        }
        if (ePreenchimento != null) {
            ePreenchimento.aplica(gr);
        }

        for (FonteImpCmp cmp : impTexto) {
            gr.drawString(cmp.pegaLinha(), cmp.pegaX(), cmp.pegaY() + cmp.pegaAltura());
        }
        return retorno;
    }

}
