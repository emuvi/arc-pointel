package pin.modamk;

import java.util.ArrayList;
import java.util.List;

public class CadEstrangeiro {

    private String nome;
    private String esquema;
    private String tabela;
    private List<CmpRelacao> relacoes;

    public CadEstrangeiro(String nome, String tabela) {
        this(nome, "public", tabela);
    }

    public CadEstrangeiro(String nome, String esquema, String tabela) {
        this.nome = nome;
        this.esquema = esquema;
        this.tabela = tabela;
        this.relacoes = new ArrayList<>();
    }

    public String pNome() {
        return nome;
    }

    public CadEstrangeiro mNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String pEsquema() {
        return esquema;
    }

    public CadEstrangeiro mEsquema(String esquema) {
        this.esquema = esquema;
        return this;
    }

    public String pTabela() {
        return tabela;
    }

    public CadEstrangeiro mTabela(String tabela) {
        this.tabela = tabela;
        return this;
    }

    public List<CmpRelacao> relacoes() {
        return relacoes;
    }

    public CadEstrangeiro mudaRelacoes(List<CmpRelacao> asRelacoes) {
        relacoes = asRelacoes;
        return this;
    }

    public CadEstrangeiro botaRelacao(String cmpReferenciado, String cmpReferencia) {
        relacoes.add(new CmpRelacao(cmpReferenciado, cmpReferencia));
        return this;
    }

    public CadEstrangeiro botaRelacao(CmpRelacao aRelacao) {
        relacoes.add(aRelacao);
        return this;
    }

    public CadEstrangeiro botaRelacoes(CmpRelacao... asRelacoes) {
        if (asRelacoes != null) {
            for (CmpRelacao relacao : asRelacoes) {
                relacoes.add(relacao);
            }
        }
        return this;
    }

    public CadEstrangeiro botaRelacoes(List<CmpRelacao> asRelacoes) {
        if (asRelacoes != null) {
            relacoes.addAll(asRelacoes);
        }
        return this;
    }

    public CadEstrangeiro tiraRelacao(CmpRelacao aRelacao) {
        relacoes.remove(aRelacao);
        return this;
    }
}
