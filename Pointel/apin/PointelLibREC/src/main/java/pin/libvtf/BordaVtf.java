package pin.libvtf;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import pin.libamk.Campos;
import pin.libamk.Cmp;
import pin.libbas.Dados;
import pin.libfor.Contorno;
import pin.libjan.Borda;
import pin.libutl.Utl;
import pin.libutl.UtlIma;

public class BordaVtf extends ValIntf {

    private BufferedImage icone;
    private Contorno contorno;
    private Borda externa;
    private Borda interna;

    public BordaVtf() {
        this(new Borda());
    }

    public BordaVtf(Borda daBorda) {
        super("Borda Vtf",
                new Campos(
                        new Cmp(1, 1, "tipo", "Tipo", Dados.Tp.Enu).botaOpcoes(Borda.Tp.class).mVlrInicial(daBorda.pTipo()),
                        new Cmp(2, 1, "cts", Dados.Tp.Cts),
                        new Cmp("cts", 1, 1, "limpa", Dados.Tp.Pai),
                        new Cmp("limpa", 1, 1, "lp_ms", "Margem Superior", Dados.Tp.Int).mVlrInicial(daBorda.pParam("margemSuperior")),
                        new Cmp("limpa", 1, 2, "lp_me", "Margem Esquerda", Dados.Tp.Int).mVlrInicial(daBorda.pParam("margemEsquerda")),
                        new Cmp("limpa", 2, 1, "lp_mi", "Margem Inferior", Dados.Tp.Int).mVlrInicial(daBorda.pParam("margemInferior")),
                        new Cmp("limpa", 2, 2, "lp_md", "Margem Direita", Dados.Tp.Int).mVlrInicial(daBorda.pParam("margemDireita")),
                        new Cmp("cts", 1, 1, "linha", Dados.Tp.Pai),
                        new Cmp("linha", 1, 1, "ln_cr", "Cor", Dados.Tp.Cor).mVlrInicial(daBorda.pParam("cor")),
                        new Cmp("linha", 1, 2, "ln_gr", "Grossura", Dados.Tp.Int).mVlrInicial(daBorda.pParam("grossura")),
                        new Cmp("linha", 2, 1, "ln_rd", "Redonda", Dados.Tp.Log).mVlrInicial(daBorda.pParam("redonda")),
                        new Cmp("cts", 1, 1, "chanfro", Dados.Tp.Pai),
                        new Cmp("chanfro", 1, 1, "ch_lv", "Levantada", Dados.Tp.Log).mVlrInicial(daBorda.pParam("levantada")),
                        new Cmp("chanfro", 2, 1, "ch_ci", "Cor Interna", Dados.Tp.Cor).mVlrInicial(daBorda.pParam("corInterna")),
                        new Cmp("chanfro", 2, 2, "ch_ce", "Cor Externa", Dados.Tp.Cor).mVlrInicial(daBorda.pParam("corExterna")),
                        new Cmp("chanfro", 3, 1, "ch_si", "Sombra Interna", Dados.Tp.Cor).mVlrInicial(daBorda.pParam("sombraInterna")),
                        new Cmp("chanfro", 3, 2, "ch_se", "Sombra Externa", Dados.Tp.Cor).mVlrInicial(daBorda.pParam("sombraExterna")),
                        new Cmp("cts", 1, 1, "chanfroleve", Dados.Tp.Pai),
                        new Cmp("chanfroleve", 1, 1, "cl_lv", "Levantada", Dados.Tp.Log).mVlrInicial(daBorda.pParam("levantada")),
                        new Cmp("chanfroleve", 2, 1, "cl_ci", "Cor Interna", Dados.Tp.Cor).mVlrInicial(daBorda.pParam("corInterna")),
                        new Cmp("chanfroleve", 2, 2, "cl_ce", "Cor Externa", Dados.Tp.Cor).mVlrInicial(daBorda.pParam("corExterna")),
                        new Cmp("chanfroleve", 3, 1, "cl_si", "Sombra Interna", Dados.Tp.Cor).mVlrInicial(daBorda.pParam("sombraInterna")),
                        new Cmp("chanfroleve", 3, 2, "cl_se", "Sombra Externa", Dados.Tp.Cor).mVlrInicial(daBorda.pParam("sombraExterna")),
                        new Cmp("cts", 1, 1, "gravada", Dados.Tp.Pai),
                        new Cmp("gravada", 1, 1, "gr_lv", "Levantada", Dados.Tp.Log).mVlrInicial(daBorda.pParam("levantada")),
                        new Cmp("gravada", 2, 1, "gr_cr", "Cor", Dados.Tp.Cor).mVlrInicial(daBorda.pParam("cor")),
                        new Cmp("gravada", 2, 2, "gr_sm", "Sombra", Dados.Tp.Cor).mVlrInicial(daBorda.pParam("sombra")),
                        new Cmp("cts", 1, 1, "icone", Dados.Tp.Pai),
                        new Cmp("icone", 1, 1, "ic_ms", "Margem Superior", Dados.Tp.Int).mVlrInicial(daBorda.pParam("margemSuperior")),
                        new Cmp("icone", 1, 2, "ic_me", "Margem Esquerda", Dados.Tp.Int).mVlrInicial(daBorda.pParam("margemEsquerda")),
                        new Cmp("icone", 2, 1, "ic_mi", "Margem Inferior", Dados.Tp.Int).mVlrInicial(daBorda.pParam("margemInferior")),
                        new Cmp("icone", 2, 2, "ic_md", "Margem Direita", Dados.Tp.Int).mVlrInicial(daBorda.pParam("margemDireita")),
                        new Cmp("icone", 3, 1, "ic_ic", "Icone", Dados.Tp.Bot),
                        new Cmp("cts", 1, 1, "contorno", Dados.Tp.Pai),
                        new Cmp("contorno", 1, 1, "cn_cn", "Contorno", Dados.Tp.Bot),
                        new Cmp("cts", 1, 1, "composta", Dados.Tp.Pai),
                        new Cmp("composta", 1, 1, "cm_ex", "Externa", Dados.Tp.Bot),
                        new Cmp("composta", 2, 1, "cm_in", "Interna", Dados.Tp.Bot)
                )
        );
        icone = UtlIma.pega(daBorda.pParam("icone"));
        contorno = (Contorno) daBorda.pParam("contorno");
        externa = (Borda) daBorda.pParam("externa");
        interna = (Borda) daBorda.pParam("interna");
    }

    @Override
    public void preparaIntf() throws Exception {
        super.preparaIntf();
        vinculaCts("tipo", Borda.Tp.Limpa, "limpa", "cts");
        vinculaCts("tipo", Borda.Tp.Linha, "linha", "cts");
        vinculaCts("tipo", Borda.Tp.Chanfro, "chanfro", "cts");
        vinculaCts("tipo", Borda.Tp.ChanfroLeve, "chanfroleve", "cts");
        vinculaCts("tipo", Borda.Tp.Gravada, "gravada", "cts");
        vinculaCts("tipo", Borda.Tp.Icone, "icone", "cts");
        vinculaCts("tipo", Borda.Tp.Contorno, "contorno", "cts");
        vinculaCts("tipo", Borda.Tp.Composta, "composta", "cts");
    }

    @Override
    public void especializaIntf() throws Exception {
        super.especializaIntf();
        botaAcao("ic_ic", new ActIcone());
        botaAcao("cn_cn", new ActContorno());
        botaAcao("cm_ex", new ActExterna());
        botaAcao("cm_in", new ActInterna());
    }

    @Override
    public Borda pgVal() throws Exception {
        Borda retorno = new Borda();
        switch ((Borda.Tp) cmps().pgVlr("tipo").pg()) {
            case Limpa:
                retorno.mLimpa(
                        cmps().pgVlr("lp_ms").pgInt(),
                        cmps().pgVlr("lp_me").pgInt(),
                        cmps().pgVlr("lp_mi").pgInt(),
                        cmps().pgVlr("lp_md").pgInt()
                );
                break;
            case Linha:
                retorno.mLinha(
                        cmps().pgVlr("ln_cr").pgCor(),
                        cmps().pgVlr("ln_gr").pgInt(),
                        cmps().pgVlr("ln_rd").pgLog()
                );
                break;
            case Chanfro:
                retorno.mChanfro(
                        cmps().pgVlr("ch_lv").pgLog(),
                        cmps().pgVlr("ch_ci").pgCor(),
                        cmps().pgVlr("ch_ce").pgCor(),
                        cmps().pgVlr("ch_si").pgCor(),
                        cmps().pgVlr("ch_se").pgCor()
                );
                break;
            case ChanfroLeve:
                retorno.mChanfroLeve(
                        cmps().pgVlr("cl_lv").pgLog(),
                        cmps().pgVlr("cl_ci").pgCor(),
                        cmps().pgVlr("cl_ce").pgCor(),
                        cmps().pgVlr("cl_si").pgCor(),
                        cmps().pgVlr("cl_se").pgCor()
                );
                break;
            case Gravada:
                retorno.mGravada(
                        cmps().pgVlr("gr_lv").pgLog(),
                        cmps().pgVlr("gr_cr").pgCor(),
                        cmps().pgVlr("gr_sm").pgCor()
                );
                break;
            case Icone:
                Icon icn = null;
                if (icone != null) {
                    icn = new ImageIcon(icone);
                }
                retorno.mIcone(
                        cmps().pgVlr("ic_ms").pgInt(),
                        cmps().pgVlr("ic_me").pgInt(),
                        cmps().pgVlr("ic_mi").pgInt(),
                        cmps().pgVlr("ic_md").pgInt(),
                        icn
                );
                break;
            case Contorno:
                retorno.mContorno(contorno);
                break;
            case Composta:
                retorno.mComposta(externa, interna);
                break;
        }
        return retorno;
    }

    private class ActIcone extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                new ImaVtf() {
                    @Override
                    public void aoConfirmar(Object comOrigem) throws Exception {
                        icone = pgVal();
                        fecha();
                    }
                }.mostra().abre(icone);
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    private class ActContorno extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (contorno == null) {
                contorno = new Contorno();
            }
            try {
                new ContornoVtf(contorno) {
                    @Override
                    public void aoConfirmar(Object comOrigem) throws Exception {
                        contorno = pgVal();
                        fecha();
                    }
                }.mostra();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    private class ActExterna extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (externa == null) {
                externa = new Borda();
            }
            try {
                new BordaVtf(externa) {
                    @Override
                    public void aoConfirmar(Object comOrigem) throws Exception {
                        externa = pgVal();
                        fecha();
                    }
                }.mostra();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    private class ActInterna extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (interna == null) {
                interna = new Borda();
            }
            try {
                new BordaVtf(interna) {
                    @Override
                    public void aoConfirmar(Object comOrigem) throws Exception {
                        interna = pgVal();
                        fecha();
                    }
                }.mostra();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

}
