package pin.libvlr;

import java.util.ArrayList;
import java.util.List;
import pin.libtex.Marcados;
import pin.libutl.UtlBin;
import pin.libutl.UtlCrs;
import pin.libutl.UtlLis;
import pin.libutl.UtlTex;

public class VArvItn {

    private volatile String nome;
    private volatile Object valor;
    private volatile List<VArvItn> filhos;

    public VArvItn() {
        this.nome = null;
        this.valor = null;
        this.filhos = null;
    }

    public VArvItn(String nome, Object valor, List<VArvItn> filhos) {
        this.nome = nome;
        this.valor = valor;
        this.filhos = filhos;
    }

    public static VArvItn descrito(String nosCaracteres) throws Exception {
        if (nosCaracteres == null) {
            return null;
        }
        VArvItn retorno = new VArvItn();
        retorno.mNome(Marcados.marcado(nosCaracteres, "nm"));
        retorno.mValor(UtlBin.descrito(Marcados.marcado(nosCaracteres, "vl")));
        retorno.mFilhos(UtlLis.descrito(Marcados.marcado(nosCaracteres, "fl")));
        return retorno;
    }

    public String pNome() {
        return nome;
    }

    public VArvItn mNome(String nome) {
        this.nome = nome;
        return this;
    }

    public Object pValor() {
        return valor;
    }

    public VArvItn mValor(Object valor) {
        this.valor = valor;
        return this;
    }

    public List<VArvItn> pFilhos() {
        return filhos;
    }

    public VArvItn mFilhos(List<VArvItn> filhos) {
        this.filhos = filhos;
        return this;
    }

    public VArvItn botaFilho(VArvItn oFilho) {
        if (filhos == null) {
            filhos = new ArrayList<>();
        }
        filhos.add(oFilho);
        return this;
    }

    public VArvItn botaFilho(int noIndice, VArvItn oFilho) {
        if (filhos == null) {
            filhos = new ArrayList<>();
        }
        filhos.add(noIndice, oFilho);
        return this;
    }

    public VArvItn tiraFilho(VArvItn oFilho) {
        if (filhos != null) {
            filhos.remove(oFilho);
        }
        return this;
    }

    public VArvItn tiraFilho(int doIndice) {
        if (filhos != null) {
            filhos.remove(doIndice);
        }
        return this;
    }

    @Override
    public String toString() {
        return "Nome: " + nome
                + UtlTex.quebra(" Valor: ") + UtlCrs.pega(valor)
                + UtlTex.quebra(" Filhos: ") + UtlLis.formata(filhos);
    }

    public String descreve() {
        return Marcados.marca(nome, "nm")
                + Marcados.marca(UtlBin.descreve(valor), "vl")
                + Marcados.marca(UtlLis.descreve(filhos), "fl");
    }

}
