package pin.prk;

import pin.libamg.Edt;
import pin.libamk.Campos;
import pin.libamk.Cmp;
import pin.libamk.Intf;
import pin.libbas.Conferencia;
import pin.libbas.Dados;
import pin.modrec.XMLReg;

public class RobMouseIntf extends Intf {

    private Edt editor;

    public RobMouseIntf(Edt paraEditor, RobMouse comInicial) {
        super(new Campos(
                new Cmp(1, 1, "tipo", "Tipo", Dados.Tp.Enu).botaOpcoes(RobMouse.Tp.class).mVlrInicial(comInicial.tipo),
                new Cmp(2, 1, "posx", "Pos. X", Dados.Tp.Crs).mVlrInicial(comInicial.posX),
                new Cmp(2, 2, "posy", "Pos. Y", Dados.Tp.Crs).mVlrInicial(comInicial.posY),
                new Cmp(3, 1, "esquerdo", "Esquerdo", Dados.Tp.Crs).mVlrInicial(comInicial.esquerdo),
                new Cmp(3, 2, "vezes", "Vezes", Dados.Tp.Crs).mVlrInicial(comInicial.vezes)
        ));
        editor = paraEditor;
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Robótico Mouse");
    }

    @Override
    public void preparaIntf() throws Exception {
        super.preparaIntf();
        cmps().pega("tipo").botaConferencia(new CnfTipo());
        botaConfirmarECancelar();
    }

    @Override
    public void aoConfirmar(Object comOrigem) throws Exception {
        RobMouse mos = new RobMouse();
        mos.tipo = (RobMouse.Tp) cmps().pgVlr("tipo").pg();
        mos.posX = cmps().pgVlr("posx").pgCrs();
        mos.posY = cmps().pgVlr("posy").pgCrs();
        mos.esquerdo = cmps().pgVlr("esquerdo").pgCrs();
        mos.vezes = cmps().pgVlr("vezes").pgCrs();
        editor.mdVlr(XMLReg.novo().pReg(mos));
        editor.botaFoco();
        fecha();
    }

    private class CnfTipo extends Conferencia {

        @Override
        public void confere(Object comOrigem) throws Exception {
            cmps().pega("posx").pEdt().botaVisivel();
            cmps().pega("posy").pEdt().botaVisivel();
            cmps().pega("esquerdo").pEdt().botaVisivel();
            cmps().pega("vezes").pEdt().botaVisivel();
            if (!cmps().pega("tipo").pEdt().vazio()) {
                switch (cmps().pgVlr("tipo").pgCrs()) {
                    case "Aperta":
                        cmps().pega("vezes").pEdt().botaInvisivel();
                        break;
                    case "Solta":
                        cmps().pega("vezes").pEdt().botaInvisivel();
                        break;
                    case "Move":
                        cmps().pega("esquerdo").pEdt().botaInvisivel();
                        cmps().pega("vezes").pEdt().botaInvisivel();
                        break;
                }
            }
        }
    }

}
