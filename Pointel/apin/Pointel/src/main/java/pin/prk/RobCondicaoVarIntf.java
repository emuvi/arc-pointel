package pin.prk;

import pin.libamg.Edt;
import pin.libamk.Campos;
import pin.libamk.Cmp;
import pin.libamk.Intf;
import pin.libbas.Dados;
import pin.modrec.XMLReg;

public class RobCondicaoVarIntf extends Intf {

    private Edt editor;

    public RobCondicaoVarIntf(Edt paraEditor, RobCondicaoVar comInicial) throws Exception {
        super(new Campos(
                new Cmp(1, 1, "expressao", "Expressão", Dados.Tp.Crs).mVlrInicial(comInicial.expressao)
        ));
        editor = paraEditor;
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Robótico Condição Por Variável");
    }

    @Override
    public void preparaIntf() throws Exception {
        super.preparaIntf();
        botaConfirmarECancelar();
    }

    @Override
    public void aoConfirmar(Object comOrigem) throws Exception {
        RobCondicaoVar cnd = new RobCondicaoVar();
        cnd.expressao = cmps().pgVlr("expressao").pgCrs();
        editor.mdVlr(XMLReg.novo().pReg(cnd));
        editor.botaFoco();
        fecha();
    }

}
