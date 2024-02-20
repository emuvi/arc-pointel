package pin.libutl;

import pin.libvlr.VApr;

public class UtlApr {

    public static Boolean eh(Object oValor) {
        return null;
    }

    public static VApr pega(Object doValor) {
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
        return new String[]{"odp", "ppt", "pptx"};
    }

    public static String pegaExtensoesDescricao() {
        return "Apresentações (*.odp, *.ppt, *.pptx)";
    }

}
