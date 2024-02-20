package pin.libvlr;

import java.io.File;
import pin.libtex.Marcados;
import pin.libutl.UtlArq;
import pin.libutl.UtlCrs;

public class VArq extends Vlr {

    private volatile String nome;
    private volatile byte[] valor;

    public VArq() {
        valor = null;
        nome = null;
    }

    public VArq(Object doValor) {
        processa(doValor);
    }

    public VArq(String nome, byte[] valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public static VArq descrito(String nosCaracteres) {
        VArq retorno = new VArq();
        retorno.nome = null;
        retorno.valor = null;
        if (nosCaracteres != null) {
            String vlrArq = Marcados.preparado(nosCaracteres, "VlrArq");
            retorno.nome = Marcados.preparado(vlrArq, "nome");
            String fmt64 = Marcados.preparado(vlrArq, "valor");
            retorno.valor = UtlArq.pegaB64(fmt64);
        }
        return retorno;
    }

    private void processa(Object oValor) {
        nome = null;
        if (oValor instanceof File) {
            nome = ((File) oValor).getAbsolutePath();
            valor = UtlArq.pega(oValor);
        } else if (oValor instanceof VArq) {
            nome = ((VArq) oValor).pegaNome();
            valor = ((VArq) oValor).pgArq();
        } else if (oValor instanceof byte[]) {
            valor = (byte[]) oValor;
        } else if (oValor instanceof String) {
            VArq temp = descrito((String) oValor);
            nome = temp.nome;
            valor = temp.valor;
        } else {
            valor = UtlArq.pega(oValor);
        }
    }

    @Override
    public Object pg(Object comPadrao) {
        if (!vazio()) {
            return paraParamSQL();
        } else {
            return comPadrao;
        }
    }

    @Override
    public VArq md(Object oValor) {
        processa(oValor);
        return this;
    }

    public String pegaNome() {
        return nome;
    }

    public VArq botaNome(String nome) {
        this.nome = nome;
        return this;
    }

    @Override
    public String paraConsSQL() {
        return descreve();
    }

    @Override
    public Object paraParamSQL() {
        return descreve();
    }

    @Override
    public Boolean vazio() {
        return (valor == null && nome == null);
    }

    @Override
    public String toString() {
        return "Nome: " + UtlCrs.pega(nome, "") + " , Tamanho: " + (valor == null ? "nulo" : valor.length);
    }

    @Override
    public String descreve() {
        String retorno = Marcados.prepara(UtlCrs.pega(nome, ""), "nome");
        retorno += Marcados.prepara(UtlArq.formataB64(valor, ""), "valor");
        return Marcados.prepara(retorno, "VlrArq");
    }

    @Override
    public byte[] pgArq() {
        return valor;
    }

    @Override
    public byte[] pgArq(byte[] comPadrao) {
        if (valor == null) {
            return comPadrao;
        } else {
            return valor;
        }
    }

    public File pgArqCam() {
        return pgArqCam(null);
    }

    public File pgArqCam(File comPadrao) {
        File retorno = comPadrao;
        if (nome != null) {
            if (!nome.isEmpty()) {
                retorno = new File(nome);
            }
        }
        return retorno;
    }

}
