package pin.prk;

import pin.libamg.Edt;
import pin.libamk.Botao;
import pin.libamk.Campos;
import pin.libamk.Cmp;
import pin.libamk.Intf;
import pin.libbas.Dados;
import pin.modrec.XMLReg;

public class RobCondicaoIntf extends Intf {

    private Edt editor;

    public RobCondicaoIntf(Edt paraEditor, RobCondicao comInicial) {
        super(new Campos(
                new Cmp(1, 1, "tipo", "Tipo", Dados.Tp.Enu).botaOpcoes(RobCondicao.Tp.class).mVlrInicial(comInicial.tipo),
                new Cmp(2, 1, "fonte", "Fonte", Dados.Tp.Tex).mVlrInicial(comInicial.fonte),
                new Cmp(3, 1, "sim", "Caso Sim", Dados.Tp.Crs).mVlrInicial(comInicial.sim),
                new Cmp(3, 2, "nao", "Caso Não", Dados.Tp.Crs).mVlrInicial(comInicial.nao)
        ));
        editor = paraEditor;
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Robótico Condição");
    }

    @Override
    public void preparaIntf() throws Exception {
        super.preparaIntf();
        botaBotao(new BotEditar());
        botaConfirmarECancelar();
    }

    @Override
    public void aoConfirmar(Object comOrigem) throws Exception {
        RobCondicao cnd = new RobCondicao();
        cnd.tipo = (RobCondicao.Tp) cmps().pgVlr("tipo").pg();
        cnd.fonte = cmps().pgVlr("fonte").pgCrs();
        cnd.sim = cmps().pgVlr("sim").pgCrs();
        cnd.nao = cmps().pgVlr("nao").pgCrs();
        editor.mdVlr(XMLReg.novo().pReg(cnd));
        editor.botaFoco();
        fecha();
    }

    private class BotEditar extends Botao {

        public BotEditar() {
            super("Editar");
        }

        @Override
        public void aoExecutar(Object comOrigem) throws Exception {
            String fnt = cmps().pgVlr("fonte").pgCrs("");
            Edt editor = cmps().pega("fonte").pEdt();
            switch (cmps().pgVlr("tipo").pgCrs()) {
                case "TemImagem":
                    RobCondicaoIma cima = null;
                    if (!fnt.isEmpty()) {
                        cima = (RobCondicaoIma) XMLReg.novo().pValor(fnt);
                    }
                    if (cima == null) {
                        cima = new RobCondicaoIma();
                    }
                    new RobCondicaoImaIntf(editor, cima).mostra();
                    break;
                case "PorVariavel":
                    RobCondicaoVar cvar = null;
                    if (!fnt.isEmpty()) {
                        cvar = (RobCondicaoVar) XMLReg.novo().pValor(fnt);
                    }
                    if (cvar == null) {
                        cvar = new RobCondicaoVar();
                    }
                    new RobCondicaoVarIntf(editor, cvar).mostra();
                    break;
            }
        }
    }

}
