package pin.modamk;

public class CmpRelacao {

    private String referenciado;
    private String referencia;
    private Boolean chave;
    private Boolean fixo;

    public CmpRelacao(String cmpReferenciado, String cmpReferencia) {
        referenciado = cmpReferenciado;
        referencia = cmpReferencia;
        chave = false;
        fixo = false;
    }

    public String pegaReferenciado() {
        return referenciado;
    }

    public CmpRelacao mudaReferenciado(String para) {
        this.referenciado = para;
        return this;
    }

    public String pegaReferencia() {
        return referencia;
    }

    public CmpRelacao mudaReferencia(String para) {
        this.referencia = para;
        return this;
    }

    public Boolean ehChave() {
        return chave;
    }

    public CmpRelacao mudaChave(Boolean para) {
        chave = para;
        return this;
    }

    public CmpRelacao botaChave() {
        chave = true;
        return this;
    }

    public CmpRelacao tiraChave() {
        chave = false;
        return this;
    }

    public Boolean ehFixo() {
        return fixo;
    }

    public CmpRelacao mudaFixo(Boolean para) {
        fixo = para;
        return this;
    }

    public CmpRelacao botaFixo() {
        fixo = true;
        return this;
    }

    public CmpRelacao tiraFixo() {
        fixo = false;
        return this;
    }

}
