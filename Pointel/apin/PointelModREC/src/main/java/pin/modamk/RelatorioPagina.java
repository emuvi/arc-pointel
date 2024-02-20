package pin.modamk;

public class RelatorioPagina {
    
    public static RelatorioPagina[] todas() {
        RelatorioPagina[] retorno = null;
        Tamanho[] tams = Tamanho.values();
        retorno = new RelatorioPagina[tams.length];
        for (int i1 = 0; i1 < tams.length; i1++)
            retorno[i1] = new RelatorioPagina(tams[i1]);
        return retorno;
    }
    
    public static RelatorioPagina[] padraoMatricial() {
        RelatorioPagina[] retorno = null;
        retorno = new RelatorioPagina[2];
        retorno[0] = new RelatorioPagina(Tamanho.HALFLETTER);
        retorno[1] = new RelatorioPagina(Tamanho.LETTER);
        return retorno;
    }
    
    public static RelatorioPagina[] padraoGrafico() {
        RelatorioPagina[] retorno = null;
        retorno = new RelatorioPagina[2];
        retorno[0] = new RelatorioPagina(Tamanho.A4);
        retorno[1] = new RelatorioPagina(Tamanho.LETTER);
        return retorno;
    }
    
    public static enum Tamanho {LETTER, HALFLETTER, NOTE, LEGAL, A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10,
    B0, B1, B2, B3, B4, B5, B6, B7, B8, B9, B10, C0, C1, C2, C3, C4, C5, C6, C7, C8, C9, C10,
    ARCH_E, ARCH_D, ARCH_C, ARCH_B, ARCH_A, FLSA, FLSE, LEDGER, _11X17};
    
    public static enum Orientacao {PORTRAIT, LANDSCAPE};
    
    public String nome;
    public Tamanho tamanho;
    public Orientacao orientacao;

    public RelatorioPagina(String aNome, Tamanho aTamanho, Orientacao aOrientacao) {
        nome = aNome;
        tamanho = aTamanho;
        orientacao = aOrientacao;
    }
    
    public RelatorioPagina(Tamanho aTamanho, Orientacao aOrientacao) {
        nome = aTamanho.toString();
        tamanho = aTamanho;
        orientacao = aOrientacao;
        
        if (tamanho == Tamanho.LETTER)
            nome = "Carta";
        else if (tamanho == Tamanho.HALFLETTER)
            nome = "Razão";
        else if (tamanho == Tamanho.NOTE)
            nome = "Nota";
        else if (tamanho == Tamanho.LEGAL)
            nome = "Legal";
    }
    
    public RelatorioPagina(Tamanho aTamanho) {
        this(aTamanho, Orientacao.PORTRAIT);
    }
}
