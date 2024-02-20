package pin.modamk;

import pin.libamg.Edt;
import pin.libamg.EdtCrs;
import pin.libbas.Globais;
import pin.libutl.Utl;
import pin.libvlr.Vlrs;
import pin.modinf.CodigosMod;
import pin.modinf.Conexao;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class EdtMod {

    public static void botaEstrangeiro(Edt noEditor, String comSelecao, Edt[] eChaves) {
        noEditor.mEditavel(false);
        for (Edt chv : eChaves) {
            chv.controle().addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent fe) {
                    try {
                        noEditor.mdVlr(pegaEstrangeiroVlr(comSelecao, eChaves));
                    } catch (Exception ex) {
                        Utl.registra(ex);
                    }
                }
            });
        }
    }

    public static Object pegaEstrangeiroVlr(String comSelecao, Edt[] eChaves) throws Exception {
        Conexao conexao = ((Conexao) Globais.pega("mainConc"));

        if (conexao == null) {
            return null;
        }

        Vlrs pars = new Vlrs();
        for (int i1 = 0; i1 < eChaves.length; i1++) {
            pars.bota(eChaves[i1].pgVlr());
        }

        return conexao.busca(comSelecao, pars).pgCol();
    }

    public static Boolean botaProximo(Conexao naConexao, Edt noEditor, String daTabela) throws Exception {
        return botaProximo(naConexao, noEditor, daTabela, "", "");
    }

    public static Boolean botaProximo(Conexao naConexao, Edt noEditor, String daTabela, String comCampo, String eCondicao) throws Exception {
        String formato = CodigosMod.pegaFormato(naConexao, daTabela);

        if (formato.isEmpty()) {
            if (noEditor instanceof EdtCrs) {
                formato = "CS;" + ((EdtCrs) noEditor).tamanho;
            } else {
                formato = "NS;6";
            }
            CodigosMod.botaFormato(naConexao, daTabela, formato);
        }

        return botaProximo(naConexao, noEditor, daTabela, formato, comCampo, eCondicao);
    }

    public static Boolean botaProximo(Conexao naConexao, Edt noEditor, String daTabela, String eFormato, String comCampo, String eCondicao) throws Exception {
        Boolean retorno = false;
        Object novo = null;

        novo = CodigosMod.pegaProximo(naConexao, eFormato, daTabela, comCampo, eCondicao);
        noEditor.mdVlr(novo);
        if (novo != null) {
            retorno = true;
        }

        return retorno;
    }
}
