package pin.prk;

import pin.jrb.ServComando;
import pin.libamg.Edt;
import pin.libamg.EdtArq;
import pin.libamk.Campos;
import pin.libamk.Cmp;
import pin.libamk.Intf;
import pin.libbas.Dados;
import pin.modrec.XMLReg;

public class RobComandoIntf extends Intf {

    private Edt editor;

    public RobComandoIntf(Edt paraEditor, ServComando comInicial) throws Exception {
        super(new Campos(
                new Cmp(1, 1, "comando", "Comando", Dados.Tp.Crs).mVlrInicial(comInicial.comando),
                new Cmp(2, 1, "argumentos", "Argumentos", Dados.Tp.Lis).mVlrInicial(comInicial.argumentos),
                new Cmp(2, 2, "variaveis", "Variáveis", Dados.Tp.Lis).mVlrInicial(comInicial.variaveis),
                new Cmp(3, 1, "diretorio", "Diretório", Dados.Tp.Arq).botaParams(EdtArq.Params.SO_DIRETORIOS).mVlrInicial(comInicial.diretorio)
        ));
        editor = paraEditor;
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Robótico Comando");
    }

    @Override
    public void preparaIntf() throws Exception {
        super.preparaIntf();
        botaConfirmarECancelar();
    }

    @Override
    public void aoConfirmar(Object comOrigem) throws Exception {
        RobComando scmd = new RobComando();
        scmd.comando = cmps().pgVlr("comando").pgCrs();
        scmd.argumentos = cmps().pgVlrLis("argumentos").pgMatCrs();
        scmd.variaveis = cmps().pgVlrLis("variaveis").pgMatCrs();
        scmd.diretorio = cmps().pgVlr("diretorio").pgArqCam();
        editor.mdVlr(XMLReg.novo().pReg(scmd));
        editor.botaFoco();
        fecha();
    }

}
