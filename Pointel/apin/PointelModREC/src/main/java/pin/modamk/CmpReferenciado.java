package pin.modamk;

public class CmpReferenciado {

    public String esquema;
    public String tabela;
    public String campo;

    public String[] chaves;

    public CmpReferenciado(String daTabela, String oCampo) {
        this("public", daTabela, oCampo);
    }

    public CmpReferenciado(String noEsquema, String daTabela, String oCampo) {
        esquema = noEsquema;
        tabela = daTabela;
        campo = oCampo;
        chaves = null;
    }

    public CmpReferenciado botaChaves(String... asChaves) {
        chaves = asChaves;
        return this;
    }

}
