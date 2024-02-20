package pin.modinf;

public class TabIndiceColuna {

    private String nome;
    private Boolean crescente;
    private Boolean nulosPrimeiro;

    public TabIndiceColuna(String nome) {
        this.nome = nome;
        this.crescente = true;
        this.nulosPrimeiro = true;
    }

    public TabIndiceColuna(String nome, Boolean crescente, Boolean nulosPrimeiro) {
        this.nome = nome;
        this.crescente = crescente;
        this.nulosPrimeiro = nulosPrimeiro;
    }

    public String pegaNome() {
        return nome;
    }

    public TabIndiceColuna mudaNome(String nome) {
        this.nome = nome;
        return this;
    }

    public Boolean getCrescente() {
        return crescente;
    }

    public TabIndiceColuna mudaCrescente(Boolean crescente) {
        this.crescente = crescente;
        return this;
    }

    public Boolean getNulosPrimeiro() {
        return nulosPrimeiro;
    }

    public TabIndiceColuna mudaNulosPrimeiro(Boolean nulosPrimeiro) {
        this.nulosPrimeiro = nulosPrimeiro;
        return this;
    }

    @Override
    public String toString() {
        return nome + " " + (crescente ? "{Crescente}" : "{Decrescente}") + " " + (nulosPrimeiro ? "{Primeiro Nulos}" : "");
    }

}
