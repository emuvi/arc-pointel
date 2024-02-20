package pin.modinf;

import pin.libbas.ParsChaves;
import pin.libbas.Progresso;
import pin.libutl.UtlBin;

public class ConAuxImpOpePegaParteFixada extends ConAuxImpOpe {

    private String nome;
    private Integer inicio;
    private Integer tamanho;

    public ConAuxImpOpePegaParteFixada() {
        super(ConAuxImpOpe.Tp.PegaParteFixada);
        nome = "prt";
        inicio = 0;
        tamanho = null;
    }

    public String pNome() {
        return nome;
    }

    public ConAuxImpOpePegaParteFixada mNome(String nome) {
        this.nome = nome;
        return this;
    }

    public Integer pInicio() {
        return inicio;
    }

    public ConAuxImpOpePegaParteFixada mInicio(Integer inicio) {
        this.inicio = inicio;
        return this;
    }

    public Integer pTamanho() {
        return tamanho;
    }

    public ConAuxImpOpePegaParteFixada mTamanho(Integer fim) {
        this.tamanho = fim;
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
                    int mtcIni = prgParte + inicio;
                    if (mtcIni > -1 && mtcIni < corpo.length()) {
                        int mtcTam = prgParte + tamanho;
                        if (mtcTam > -1 && mtcTam < mtcIni && mtcTam <= corpo.length()) {
                            parte = corpo.substring(mtcIni, mtcTam);
                            prgParte = mtcTam;
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
