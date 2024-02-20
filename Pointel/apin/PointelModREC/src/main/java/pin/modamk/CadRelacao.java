package pin.modamk;

import java.util.ArrayList;
import java.util.List;

public class CadRelacao {

    private String alias;
    private Class classe;
    private String esquema;
    private String tabela;
    private Boolean obrigatorio;
    private List<CmpRelacao> relacoes;

    public CadRelacao(Class daClasse) {
        this("", daClasse);
    }

    public CadRelacao(String comAlias, Class daClasse) {
        alias = comAlias;
        classe = daClasse;
        esquema = null;
        tabela = null;
        obrigatorio = false;
        relacoes = new ArrayList<>();
    }

    public CadRelacao(String daTabela) {
        this("", "public", daTabela);
    }

    public CadRelacao(String comAlias, String daTabela) {
        this(comAlias, "public", daTabela);
    }

    public CadRelacao(String comAlias, String doEsquema, String eTabela) {
        alias = comAlias;
        classe = null;
        esquema = doEsquema;
        tabela = eTabela;
        obrigatorio = false;
        relacoes = new ArrayList<>();
    }

    public String pegaAlias() {
        return alias;
    }

    public CadRelacao mudaAlias(String para) {
        alias = para;
        return this;
    }

    public Class pegaClasse() {
        return classe;
    }

    public CadRelacao mudaClasse(Class para) {
        classe = para;
        return this;
    }

    public String pegaEsquema() throws Exception {
        if (classe != null) {
            return Cadastros.pega(classe).esquema;
        } else {
            return esquema;
        }
    }

    public CadRelacao mudaEsquema(String para) {
        classe = null;
        esquema = para;
        return this;
    }

    public String pegaTabela() throws Exception {
        if (classe != null) {
            return Cadastros.pega(classe).tabela;
        } else {
            return tabela;
        }
    }

    public CadRelacao mudaTabela(String para) {
        classe = null;
        tabela = para;
        return this;
    }

    public String pegaEsquemaETabela() throws Exception {
        return pegaEsquema() + "." + pegaTabela();
    }

    public String pegaNome() throws Exception {
        if (alias.isEmpty()) {
            return pegaTabela();
        } else {
            return alias;
        }
    }

    public String pegaNomeCompleto() throws Exception {
        if (alias.isEmpty()) {
            return pegaEsquemaETabela();
        } else {
            return alias;
        }
    }

    public Boolean ehObrigatorio() {
        return obrigatorio;
    }

    public CadRelacao mudaObrigatorio(Boolean para) {
        obrigatorio = para;
        return this;
    }

    public CadRelacao botaObrigatorio() {
        obrigatorio = true;
        return this;
    }

    public CadRelacao tiraObrigatorio() {
        obrigatorio = false;
        return this;
    }

    public List<CmpRelacao> relacoes() {
        return relacoes;
    }

    public CadRelacao mudaRelacoes(List<CmpRelacao> asRelacoes) {
        relacoes = asRelacoes;
        return this;
    }

    public CadRelacao botaRelacao(String cmpReferenciado, String cmpReferencia) {
        relacoes.add(new CmpRelacao(cmpReferenciado, cmpReferencia));
        return this;
    }

    public CadRelacao botaRelacao(CmpRelacao aRelacao) {
        relacoes.add(aRelacao);
        return this;
    }

    public CadRelacao botaRelacoes(CmpRelacao... asRelacoes) {
        if (asRelacoes != null) {
            for (CmpRelacao relacao : asRelacoes) {
                relacoes.add(relacao);
            }
        }
        return this;
    }

    public CadRelacao botaRelacoes(List<CmpRelacao> asRelacoes) {
        if (asRelacoes != null) {
            relacoes.addAll(asRelacoes);
        }
        return this;
    }

    public CadRelacao tiraRelacao(CmpRelacao aRelacao) {
        relacoes.remove(aRelacao);
        return this;
    }
}
