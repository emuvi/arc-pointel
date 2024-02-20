package pin.modinf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import pin.libutl.UtlCrs;

public class TabEstrangeiro {

    private String nome;
    private String esquema;
    private String tabela;
    private List<TabRelacao> relacoes;

    public TabEstrangeiro(String comNome) {
        this(comNome, null, null, (TabRelacao[]) null);
    }

    public TabEstrangeiro(String comNome, String paraTabela) {
        this(comNome, "public", paraTabela, (TabRelacao[]) null);
    }

    public TabEstrangeiro(String comNome, String noEsquema, String paraTabela) {
        this(comNome, noEsquema, paraTabela, (TabRelacao[]) null);
    }

    public TabEstrangeiro(String comNome, String noEsquema, String paraTabela, TabRelacao[] eRelacoes) {
        this.nome = comNome;
        this.esquema = noEsquema;
        this.tabela = paraTabela;
        relacoes = new ArrayList<>();
        if (eRelacoes != null) {
            relacoes.addAll(Arrays.asList(eRelacoes));
        }
    }

    public TabEstrangeiro(String noEsquema, String paraTabela, List<TabRelacao> eRelacoes) {
        this.nome = null;
        this.esquema = noEsquema;
        this.tabela = paraTabela;
        this.relacoes = eRelacoes;
    }

    public String pNome() {
        return nome;
    }

    public TabEstrangeiro mNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String pEsquema() {
        return esquema;
    }

    public TabEstrangeiro mEsquema(String esquema) {
        this.esquema = esquema;
        return this;
    }

    public String pTabela() {
        return tabela;
    }

    public TabEstrangeiro mTabela(String tabela) {
        this.tabela = tabela;
        return this;
    }

    public String pEsquemaETabela() {
        return esquema + "." + tabela;
    }

    public List<TabRelacao> pRelacoes() {
        return relacoes;
    }

    public TabEstrangeiro mRelacoes(List<TabRelacao> relacoes) {
        this.relacoes = relacoes;
        return this;
    }

    public TabEstrangeiro botaRelacao(String doReferenciado, String naReferencia) {
        relacoes.add(new TabRelacao(doReferenciado, naReferencia));
        return this;
    }

    public String narraRelacoes() {
        String retorno = "";
        for (TabRelacao rel : relacoes) {
            retorno = UtlCrs.soma(retorno, " , ", rel.toString());
        }
        return "( " + retorno + " )";
    }

    public String pDescricao() {
        return "Estrangeiro " + nome + " [ " + pEsquemaETabela() + " ] " + narraRelacoes();
    }

}
