package pin.bib;

import pin.libamg.Edt;
import pin.libamk.Campos;
import pin.libamk.Cmp;
import pin.libamk.Intf;
import pin.libbas.Dados;

public class CadTraducoesOrigemIntf extends Intf {

    private Edt editor;

    public CadTraducoesOrigemIntf(Edt paraEditor, String comTabela, String[] eChaves, String eCampo) {
        super(new Campos(
                        new Cmp(1, 1, "tabela", "Tabela", Dados.Tp.Crs).mVlrInicial(comTabela),
                        new Cmp(2, 1, "chave", "Chave", Dados.Tp.Lis).botaParams(Dados.Tp.Crs).mVlrInicial(eChaves),
                        new Cmp(2, 2, "campo", "Campo", Dados.Tp.Crs).mVlrInicial(eCampo)
                )
        );
        editor = paraEditor;
    }

    @Override
    public void preparaIntf() throws Exception {
        super.preparaIntf();
        botaConfirmarECancelar();
    }

    @Override
    public void aoConfirmar(Object comOrigem) throws Exception {
        String tab = cmps().pega("tabela").pEdt().pgVlr().pgCrs("");
        String[] chvs = cmps().pega("chave").pEdt().pgVlrLis().pgMatCrs();
        String cmp = cmps().pega("campo").pEdt().pgVlr().pgCrs("");
        editor.mdVlr(CadTraducoes.montaOrigem(tab, cmp, chvs));
        fecha();
    }

}
