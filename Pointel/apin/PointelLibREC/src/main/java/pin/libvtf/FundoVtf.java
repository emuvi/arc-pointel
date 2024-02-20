package pin.libvtf;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import javax.swing.AbstractAction;
import pin.libamk.Botao;
import pin.libamk.Campos;
import pin.libamk.Cmp;
import pin.libamk.Intf;
import pin.libbas.Dados;
import pin.libfor.Fundo;
import pin.libutl.Utl;

public class FundoVtf extends Intf {

    private Fundo valor;
    private BufferedImage imagem;
    private Boolean anular;

    public FundoVtf() {
        this(new Fundo());
    }

    public FundoVtf(Fundo doFundo) {
        super(new Campos(
                new Cmp(1, 1, "tipo", "Tipo", Dados.Tp.Enu).botaOpcoes(Fundo.Tp.class).mVlrInicial(doFundo.pTipo()),
                new Cmp(2, 1, "cts", Dados.Tp.Cts),
                new Cmp("cts", 1, 1, "solido", Dados.Tp.Pai),
                new Cmp("cts", 1, 1, "gradiente", Dados.Tp.Pai),
                new Cmp("cts", 1, 1, "textura", Dados.Tp.Pai),
                new Cmp("solido", 1, 1, "cor", "cor", Dados.Tp.Cor).mVlrInicial(doFundo.pCor()),
                new Cmp("gradiente", 1, 1, "pos1x", "Pos. 1 X", Dados.Tp.Num).mVlrInicial(doFundo.pX1()),
                new Cmp("gradiente", 1, 2, "pos1y", "Pos. 1 Y", Dados.Tp.Num).mVlrInicial(doFundo.pY1()),
                new Cmp("gradiente", 1, 2, "cor1", "Cor 1", Dados.Tp.Cor).mVlrInicial(doFundo.pCor()),
                new Cmp("gradiente", 2, 1, "pos2x", "Pos. 2 X", Dados.Tp.Num).mVlrInicial(doFundo.pX2()),
                new Cmp("gradiente", 2, 2, "pos2y", "Pos. 2 Y", Dados.Tp.Num).mVlrInicial(doFundo.pY2()),
                new Cmp("gradiente", 2, 2, "cor2", "Cor 2", Dados.Tp.Cor).mVlrInicial(doFundo.pCor2()),
                new Cmp("gradiente", 3, 1, "ciclico", "Cíclico", Dados.Tp.Log).mVlrInicial(doFundo.pCiclico()),
                new Cmp("textura", 1, 1, "posx", "Pos. X", Dados.Tp.Num).mVlrInicial(doFundo.pX1()),
                new Cmp("textura", 1, 2, "posy", "Pos. Y", Dados.Tp.Num).mVlrInicial(doFundo.pY1()),
                new Cmp("textura", 1, 3, "largura", "Largura", Dados.Tp.Num).mVlrInicial(doFundo.pX2()),
                new Cmp("textura", 1, 4, "altura", "Altura", Dados.Tp.Num).mVlrInicial(doFundo.pY2()),
                new Cmp("textura", 2, 1, "imagem", "Imagem", Dados.Tp.Ima)
        ));
        valor = doFundo;
        imagem = doFundo.pImagem();
        anular = false;
    }

    public Fundo pFundo() throws Exception {
        if (anular) {
            return null;
        }
        Fundo.Tp tipo = (Fundo.Tp) cmps().pega("tipo").pEdt().pgVlr().pg();
        Color cor = cmps().pega("cor").pEdt().pgVlr().pgCor();
        Float pos1x = cmps().pega("pos1x").pEdt().pgVlr().pgNum();
        Float pos1y = cmps().pega("pos1y").pEdt().pgVlr().pgNum();
        Color cor1 = cmps().pega("cor1").pEdt().pgVlr().pgCor();
        Float pos2x = cmps().pega("pos2x").pEdt().pgVlr().pgNum();
        Float pos2y = cmps().pega("pos2y").pEdt().pgVlr().pgNum();
        Color cor2 = cmps().pega("cor2").pEdt().pgVlr().pgCor();
        Boolean ciclico = cmps().pega("ciclico").pEdt().pgVlr().pgLog();
        Float posx = cmps().pega("pos1x").pEdt().pgVlr().pgNum();
        Float posy = cmps().pega("pos1y").pEdt().pgVlr().pgNum();
        Float largura = cmps().pega("pos2x").pEdt().pgVlr().pgNum();
        Float altura = cmps().pega("pos2y").pEdt().pgVlr().pgNum();
        BufferedImage img = imagem;
        switch (tipo) {
            case Solido:
                valor.muda(cor);
                break;
            case Gradiente:
                valor.muda(pos1x, pos1y, cor1, pos2x, pos2y, cor2, ciclico);
                break;
            case Textura:
                valor.muda(posx, posy, largura, altura, img);
                break;
        }
        return valor;
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Preenchimento");
    }

    @Override
    public void preparaIntf() throws Exception {
        super.preparaIntf();
        vinculaCts("tipo", Fundo.Tp.Solido, "solido", "cts");
        vinculaCts("tipo", Fundo.Tp.Gradiente, "gradiente", "cts");
        vinculaCts("tipo", Fundo.Tp.Textura, "textura", "cts");
        botaConfirmarECancelar();
        botaBotao(new BotAnular());
    }

    @Override
    public void especializaIntf() throws Exception {
        super.especializaIntf();
        botaAcao("imagem", new ActImagem());
    }

    private class ActImagem extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                new ImaVtf() {
                    @Override
                    public void aoConfirmar(Object comOrigem) throws Exception {
                        imagem = pgVal();
                        fecha();
                    }
                }.mostra().abre(imagem);
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    private class BotAnular extends Botao {

        public BotAnular() {
            super("Anular");
        }

        @Override
        public void aoExecutar(Object comOrigem) throws Exception {
            anular = true;
            confirma(comOrigem);
        }
    }

}
