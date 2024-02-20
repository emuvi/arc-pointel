package pin.libutl;

import pin.libvlr.VDoc;

public class UtlDoc {

    public static Boolean eh(Object oValor) {
        return null;
    }

    public static VDoc pega(Object doValor) {
        return null;
    }

    public static Boolean ehExtensao(String aExtensao) {
        if (!aExtensao.startsWith(".")) {
            aExtensao = "." + aExtensao;
        }
        aExtensao = aExtensao.toLowerCase();
        return UtlLis.tem(aExtensao, pegaExtensoes());
    }

    public static String[] pegaExtensoes() {
        return new String[]{"pdf", "odt", "doc", "docx", "rtf"};
    }

    public static String pegaExtensoesDescricao() {
        return "Documentos (*.pdf, *.odt, *.doc, *.docx, *.rtf)";
    }

}
