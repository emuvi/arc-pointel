package pin.modamk;

import java.util.ArrayList;

public class CadReferenciar {

    public Cadastro cadastro;
    public Rotina rotina;
    public RelatorioFiltro relatorio;

    public String selecionado;
    public Class buscar;

    public ArrayList<CmpRelacao> relacoes;

    public CadReferenciar(Cadastro noCadastro, String paraSelecionado, Class buscarNaClasse) {
        cadastro = noCadastro;
        rotina = null;
        relatorio = null;
        selecionado = paraSelecionado;
        buscar = buscarNaClasse;
        relacoes = new ArrayList<>();
    }

    public CadReferenciar(RelatorioFiltro noRelatorio, String paraSelecionado, Class buscarNaClasse) {
        cadastro = null;
        rotina = null;
        relatorio = noRelatorio;
        selecionado = paraSelecionado;
        buscar = buscarNaClasse;
        relacoes = new ArrayList<>();
    }

    public CadReferenciar(Rotina naRotina, String paraSelecionado, Class buscarNaClasse) {
        cadastro = null;
        rotina = naRotina;
        relatorio = null;
        selecionado = paraSelecionado;
        buscar = buscarNaClasse;
        relacoes = new ArrayList<>();
    }

    public CadReferenciar botaChave(String noReferenciado, String aReferencia) {
        relacoes.add(new CmpRelacao(noReferenciado, aReferencia).botaChave());
        return this;
    }

    public CadReferenciar botaChaveFixo(String noReferenciado, String aReferencia) {
        relacoes.add(new CmpRelacao(noReferenciado, aReferencia).botaChave().botaFixo());
        return this;
    }

    public CadReferenciar botaEstrangeiro(String noReferenciado, String aReferencia) {
        relacoes.add(new CmpRelacao(noReferenciado, aReferencia));
        return this;
    }

    public CadReferenciar botaEstranFixo(String noReferenciado, String aReferencia) {
        relacoes.add(new CmpRelacao(noReferenciado, aReferencia).botaFixo());
        return this;
    }
}
