package pin.modinf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import pin.libbas.ParsChaves;
import pin.libbas.Partes;
import pin.libbas.Progresso;
import pin.libutl.UtlBin;

public class ConAuxImpOpePegaParteSeparada extends ConAuxImpOpe {

    private String nome;
    private String separador;

    public ConAuxImpOpePegaParteSeparada() {
        super(ConAuxImpOpe.Tp.PegaParteSeparada);
        nome = "prt";
        separador = "\\,";
    }

    public static ConAuxImpOpePegaParteSeparada descrito(String nosCaracteres) {
        if (nosCaracteres == null) {
            return null;
        }
        String[] prts = Partes.pega(nosCaracteres);
        ConAuxImpOpePegaParteSeparada retorno = new ConAuxImpOpePegaParteSeparada();
        retorno.mNome(prts[0]);
        retorno.mSeparador(prts[1]);
        return retorno;
    }

    public String pNome() {
        return nome;
    }

    public ConAuxImpOpePegaParteSeparada mNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String pSeparador() {
        return separador;
    }

    public ConAuxImpOpePegaParteSeparada mSeparador(String separador) {
        this.separador = separador;
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
                    Pattern ptn = Pattern.compile(separador);
                    Matcher mtc = ptn.matcher(corpo);
                    if (mtc.find(prgParte)) {
                        parte = corpo.substring(prgParte, mtc.start());
                        prgParte = mtc.end();
                    } else {
                        parte = corpo.substring(prgParte);
                        prgParte = corpo.length();
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
