package pin.modinf;

import pin.libbas.ParsChaves;
import pin.libbas.Progresso;
import pin.libutl.UtlBin;

public class ConAuxImpOpePegaParteMarcada extends ConAuxImpOpe {

    private String nome;
    private String inicio;
    private String fim;

    public ConAuxImpOpePegaParteMarcada() {
        super(ConAuxImpOpe.Tp.PegaParteMarcada);
        nome = "prt";
        inicio = null;
        fim = null;
    }

    public String pNome() {
        return nome;
    }

    public ConAuxImpOpePegaParteMarcada mNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String pInicio() {
        return inicio;
    }

    public ConAuxImpOpePegaParteMarcada mInicio(String inicio) {
        this.inicio = inicio;
        return this;
    }

    public String pFim() {
        return fim;
    }

    public ConAuxImpOpePegaParteMarcada mFim(String fim) {
        this.fim = fim;
        return this;
    }

    @Override
    public int opera(ParsChaves chaves) throws Exception {
        Progresso prg = (Progresso) chaves.pega("prg");
        String corpo = (String) chaves.pega("corpo");
        Integer prgParte = (Integer) chaves.pega("prgParte", 0);
        String parte = null;
        if (corpo != null) {
            if (!corpo.isEmpty()) {
                if (prgParte < corpo.length()) {
                    int mtcIni = corpo.indexOf(inicio, prgParte);
                    if (mtcIni > -1) {
                        int mtcFim = corpo.indexOf(fim, mtcIni + inicio.length());
                        if (mtcFim > -1) {
                            parte = corpo.substring(mtcIni + inicio.length(), mtcFim);
                            prgParte = mtcFim + fim.length();
                        }
                    }
                }
            }
        }
        prg.bota("Parte " + nome, parte);
        chaves.bota("Parte " + nome, parte);
        chaves.bota("prgParte", prgParte);
        return ((Integer) chaves.pega("indice")) + 1;
    }

    public String descreve() {
        return UtlBin.descreveMembros(this);
    }

    @Override
    public String toString() {
        return super.toString() + " - " + UtlBin.mostraMembros(this);
    }

}
