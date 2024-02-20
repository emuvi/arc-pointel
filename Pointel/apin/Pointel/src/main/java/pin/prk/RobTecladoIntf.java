package pin.prk;

import pin.libamg.Edt;
import pin.libamk.Campos;
import pin.libamk.Cmp;
import pin.libamk.Intf;
import pin.libbas.Dados;
import pin.modrec.XMLReg;

public class RobTecladoIntf extends Intf {

    private Edt editor;

    public RobTecladoIntf(Edt paraEditor, RobTeclado comInicial) {
        super(new Campos(
                new Cmp(1, 1, "tipo", "Tipo", Dados.Tp.Enu).botaOpcoes(RobTeclado.Tp.class).mVlrInicial(comInicial.tipo),
                new Cmp(1, 2, "parametro", "Parâmetro", Dados.Tp.Crs).mVlrInicial(comInicial.parametro)
        ));
        editor = paraEditor;
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Robótico Teclado");
    }

    @Override
    public void preparaIntf() throws Exception {
        super.preparaIntf();
        botaConfirmarECancelar();
    }

    @Override
    public void aoConfirmar(Object comOrigem) throws Exception {
        RobTeclado tec = new RobTeclado();
        tec.tipo = (RobTeclado.Tp) cmps().pgVlr("tipo").pg();
        tec.parametro = cmps().pgVlr("parametro").pgCrs();
        editor.mdVlr(XMLReg.novo().pReg(tec));
        editor.botaFoco();
        fecha();
    }

}
