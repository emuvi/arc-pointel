package pin.modinf;

import java.util.ArrayList;
import java.util.List;
import pin.libutl.UtlCrs;

public class TabIndice {

    private String nome;
    private List<TabIndiceColuna> colunas;

    public TabIndice(String nome) {
        this.nome = nome;
        this.colunas = new ArrayList<>();
    }

    public TabIndice(String nome, List<TabIndiceColuna> colunas) {
        this.nome = nome;
        this.colunas = colunas;
    }

    public String pNome() {
        return nome;
    }

    public TabIndice mNome(String nome) {
        this.nome = nome;
        return this;
    }

    public List<TabIndiceColuna> pColunas() {
        return colunas;
    }

    public TabIndice mColunas(List<TabIndiceColuna> colunas) {
        this.colunas = colunas;
        return this;
    }

    public TabIndice botaColuna(TabIndiceColuna coluna) {
        colunas.add(coluna);
        return this;
    }

    public String narraColunas() {
        String retorno = "";
        for (TabIndiceColuna col : colunas) {
            retorno = UtlCrs.soma(retorno, " , ", col.toString());
        }
        return "( " + retorno + " )";
    }

    public String pDescricao() {
        return "Índice " + nome + " " + narraColunas();
    }

}
