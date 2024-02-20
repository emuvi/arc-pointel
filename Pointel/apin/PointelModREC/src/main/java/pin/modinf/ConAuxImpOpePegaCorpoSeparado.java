package pin.modinf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import pin.libbas.ParsChaves;
import pin.libbas.Progresso;
import pin.libutl.UtlBin;

public class ConAuxImpOpePegaCorpoSeparado extends ConAuxImpOpe {

    private String separador;

    public ConAuxImpOpePegaCorpoSeparado() {
        super(ConAuxImpOpe.Tp.PegaCorpoSeparado);
        separador = "\\r\\n|\\n|\\r";
    }

    public String pSeparador() {
        return separador;
    }

    public ConAuxImpOpePegaCorpoSeparado mSeparador(String separador) {
        this.separador = separador;
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
        Pattern ptn = Pattern.compile(separador);
        Matcher mtc = ptn.matcher(texto);
        if (mtc.find(prgCorpo)) {
            corpo = texto.substring(prgCorpo, mtc.start());
            prgCorpo = mtc.end();
        } else {
            corpo = texto.substring(prgCorpo);
            prgCorpo = texto.length();
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
