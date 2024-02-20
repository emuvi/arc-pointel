package pin.libbas;

import java.util.Locale;
import java.util.ResourceBundle;
import pin.libutl.UtlBin;

public class Erro extends Exception {

    public static ResourceBundle errosMsgs = ResourceBundle.getBundle(Erro.class.getName() + "Msgs", Locale.getDefault());

    public static String pTraducao(String daChave, String comPadrao) {
        String retorno = comPadrao;
        try {
            retorno = errosMsgs.getString(daChave);
        } catch (Exception e) {
        }
        return retorno;
    }

    private String origem;
    private Boolean alertavel;

    public Erro(Throwable daExcessao) {
        super(pTraducao(daExcessao.getClass().getName(), daExcessao.getMessage()), daExcessao);
        origem = UtlBin.pegaOrigem(daExcessao);
        alertavel = true;
    }

    public Erro(String comMensagem, Throwable daExcessao) {
        super(comMensagem, daExcessao);
        origem = UtlBin.pegaOrigem(daExcessao);
        alertavel = true;
    }

    public Erro(String comMensagemFmt, Object... eParams) {
        super(String.format(comMensagemFmt, eParams));
        origem = UtlBin.pegaOrigem();
        alertavel = true;
    }

    public String pOrigem() {
        return origem;
    }

    public Erro mOrigem(String para) {
        origem = para;
        return this;
    }

    public Boolean pAlertavel() {
        return alertavel;
    }

    public Erro mAlertavel(Boolean para) {
        alertavel = para;
        return this;
    }

    public Erro botaAlertavel() {
        alertavel = true;
        return this;
    }

    public Erro tiraAlertavel() {
        alertavel = false;
        return this;
    }

    public Erro trocaAlertavel() {
        alertavel = !alertavel;
        return this;
    }
}
