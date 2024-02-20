package pin.prk;

import pin.libamg.Edt;
import pin.libamk.Campos;
import pin.libamk.Cmp;
import pin.libamk.Intf;
import pin.libbas.Dados;
import pin.modrec.XMLReg;

public class RobEsperaIntf extends Intf {

    private Edt editor;

    public RobEsperaIntf(Edt paraEditor, RobEspera comInicial) {
        super(new Campos(
                new Cmp(1, 1, "milisegundos", "Milisegundos", Dados.Tp.Crs).mVlrInicial(comInicial.milisegundos)
        ));
        editor = paraEditor;
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Robótico Espera");
    }

    @Override
    public void preparaIntf() throws Exception {
        super.preparaIntf();
        botaConfirmarECancelar();
    }

    @Override
    public void aoConfirmar(Object comOrigem) throws Exception {
        RobEspera esp = new RobEspera();
        esp.milisegundos = cmps().pgVlr("milisegundos").pgCrs();
        editor.mdVlr(XMLReg.novo().pReg(esp));
        editor.botaFoco();
        fecha();
    }

}
