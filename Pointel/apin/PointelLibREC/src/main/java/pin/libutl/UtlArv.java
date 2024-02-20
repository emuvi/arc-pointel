package pin.libutl;

import pin.libvlr.VArv;

public class UtlArv {

    public static Boolean eh(Object oValor) {
        return null;
    }

    public static VArv pega(Object doValor) {
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
        return new String[]{"xml", "htm", "html"};
    }

    public static String pegaExtensoesDescricao() {
        return "Árvores (*.xml, *.htm, *.html)";
    }

}
