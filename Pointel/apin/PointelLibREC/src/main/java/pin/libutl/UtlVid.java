package pin.libutl;

import pin.libvlr.VVid;

public class UtlVid {

    public static Boolean eh(Object oValor) {
        return null;
    }

    public static VVid pega(Object doValor) {
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
        return new String[]{"mp4", "ogv", "webm", "mov"};
    }

    public static String pegaExtensoesDescricao() {
        return "Vídeos (*.mp4, *.ogv, *.webm, *.mov)";
    }

}
