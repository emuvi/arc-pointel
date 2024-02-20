package pin.modinf;

import pin.libbas.ParsChaves;
import pin.libbas.Progresso;
import pin.libutl.UtlBin;

public class ConAuxImpOpePegaCorpoMarcado extends ConAuxImpOpe {

    private String inicio;
    private String fim;

    public ConAuxImpOpePegaCorpoMarcado() {
        super(ConAuxImpOpe.Tp.PegaCorpoMarcado);
        inicio = null;
        fim = null;
    }

    public String pInicio() {
        return inicio;
    }

    public ConAuxImpOpePegaCorpoMarcado mInicio(String inicio) {
        this.inicio = inicio;
        return this;
    }

    public String pFim() {
        return fim;
    }

    public ConAuxImpOpePegaCorpoMarcado mFim(String fim) {
        this.fim = fim;
        return this;
    }

    @Override
    public int opera(ParsChaves chaves) throws Exception {
        Progresso prg = (Progresso) chaves.pega("prg");
        String texto = (String) chaves.pega("texto");
        if (texto == null) {
            return -1;
        }
        if (texto.isEmpty()) {
            return -1;
        }
        Integer prgCorpo = (Integer) chaves.pega("prgCorpo", 0);
        if (prgCorpo >= texto.length()) {
            return -1;
        }
        String corpo = null;
        int mtcIni = texto.indexOf(inicio, prgCorpo);
        if (mtcIni > -1) {
            int mtcFim = texto.indexOf(fim, mtcIni + inicio.length());
            if (mtcFim > -1) {
                corpo = texto.substring(mtcIni + inicio.length(), mtcFim);
                prgCorpo = mtcFim + fim.length();
            }
        }
        chaves.bota("prgCorpo", prgCorpo);
        chaves.bota("prgParte", 0);
        prg.bota("Corpo", corpo);
        chaves.bota("corpo", corpo);
        chaves.limpaComecando("Parte ");
        if (corpo == null) {
            return -1;
        } else {
            return ((Integer) chaves.pega("indice")) + 1;
        }
    }

    public String descreve() {
        return UtlBin.descreveMembros(this);
    }

    @Override
    public String toString() {
        return super.toString() + " - " + UtlBin.mostraMembros(this);
    }

}
