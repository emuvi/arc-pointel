package pin.prk;

import pin.libamg.Edt;
import pin.libamk.Campos;
import pin.libamk.Cmp;
import pin.libamk.Intf;
import pin.libbas.Dados;
import pin.modrec.XMLReg;

public class RobCondicaoImaIntf extends Intf {

    private Edt editor;

    public RobCondicaoImaIntf(Edt paraEditor, RobCondicaoIma comInicial) throws Exception {
        super(new Campos(
                new Cmp(1, 1, "tipo", "Tipo", Dados.Tp.Enu).botaOpcoes(RobCondicaoIma.Tp.class).mVlrInicial(comInicial.tipo),
                new Cmp(1, 2, "variavel", "Variável", Dados.Tp.Crs).mVlrInicial(comInicial.variavel),
                new Cmp(2, 1, "posX", "Pos. X", Dados.Tp.Crs).mVlrInicial(comInicial.posX),
                new Cmp(2, 2, "posY", "Pos. Y", Dados.Tp.Crs).mVlrInicial(comInicial.posY),
                new Cmp(3, 1, "largura", "Largura", Dados.Tp.Crs).mVlrInicial(comInicial.largura),
                new Cmp(3, 2, "altura", "Altura", Dados.Tp.Crs).mVlrInicial(comInicial.altura),
                new Cmp(3, 3, "toleranciaPer", "% Tolerância", Dados.Tp.Crs).mVlrInicial(comInicial.toleranciaPer),
                new Cmp(4, 1, "imagem", "Imagem", Dados.Tp.Ima).mVlrInicial(comInicial.imagem)
        ));
        editor = paraEditor;
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Robótico Condição Tem Imagem");
    }

    @Override
    public void preparaIntf() throws Exception {
        super.preparaIntf();
        botaConfirmarECancelar();
    }

    @Override
    public void aoConfirmar(Object comOrigem) throws Exception {
        RobCondicaoIma cnd = new RobCondicaoIma();
        cnd.tipo = (RobCondicaoIma.Tp) cmps().pgVlr("tipo").pg();
        cnd.variavel = cmps().pgVlr("variavel").pgCrs();
        cnd.posX = cmps().pgVlr("posX").pgCrs();
        cnd.posY = cmps().pgVlr("posY").pgCrs();
        cnd.largura = cmps().pgVlr("largura").pgCrs();
        cnd.altura = cmps().pgVlr("altura").pgCrs();
        cnd.toleranciaPer = cmps().pgVlr("toleranciaPer").pgCrs();
        cnd.imagem = cmps().pgVlr("imagem").pgIma();
        editor.mdVlr(XMLReg.novo().pReg(cnd));
        editor.botaFoco();
        fecha();
    }

}
