package pin.libbas;

import pin.libutl.UtlBin;
import pin.libutl.UtlCrs;
import pin.libutl.UtlMom;
import pin.libutl.UtlTex;

public class Retorno {

    private final String momento;
    private final String descricao;
    private final Object valor;

    public Retorno(Object oValor) {
        momento = UtlMom.pegaAtualParaMaquina();
        descricao = "";
        valor = oValor;
    }

    public Retorno(String comDescricao, Object eValor) {
        momento = UtlMom.pegaAtualParaMaquina();
        descricao = comDescricao == null ? "" : comDescricao;
        valor = eValor;
    }

    public Retorno(String noMomento, String comDescricao, Object eValor) {
        momento = noMomento;
        descricao = comDescricao;
        valor = eValor;
    }

    public Retorno(Throwable oErro) {
        momento = UtlMom.pegaAtualParaMaquina();
        descricao = "Erro";
        valor = oErro.getMessage() + UtlTex.quebra(2) + UtlBin.pegaOrigem(oErro);
    }

    public Retorno(String comDescricao, Throwable oErro) {
        momento = UtlMom.pegaAtualParaMaquina();
        descricao = (comDescricao == null ? "Erro" : comDescricao);
        valor = oErro.getMessage() + UtlTex.quebra(2) + UtlBin.pegaOrigem(oErro);
    }

    public Retorno(String noMomento, String comDescricao, Throwable oErro) {
        momento = noMomento;
        descricao = (comDescricao == null ? "Erro" : comDescricao);
        valor = oErro.getMessage() + UtlTex.quebra(2) + UtlBin.pegaOrigem(oErro);
    }

    public static Retorno descrito(String nosCaracteres) throws Exception {
        String[] partes = Partes.pega(nosCaracteres);
        String momento = partes[0];
        String descricao = partes[1];
        Object valor = UtlBin.descrito(partes[2]);
        return new Retorno(momento, descricao, valor);
    }

    public String pegaMomento() {
        return momento;
    }

    public String pegaDescricao() {
        return descricao;
    }

    public Object pegaValor() {
        return valor;
    }

    @Override
    public String toString() {
        return UtlCrs.pega(momento, "") + " - " + UtlCrs.pega(descricao, "") + " - " + UtlCrs.pega(valor, "");
    }

    public String descreve() {
        return Partes.junta(momento, descricao, UtlBin.descreve(valor));
    }

}
