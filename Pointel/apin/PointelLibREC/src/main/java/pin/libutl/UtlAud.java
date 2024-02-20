package pin.libutl;

import pin.libvlr.VAud;

public class UtlAud {

    public static Boolean eh(Object oValor) {
        return null;
    }

    public static VAud pega(Object doValor) {
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
        return new String[]{"mp3", "wav"};
    }

    public static String pegaExtensoesDescricao() {
        return "Áudios (*.mp3, *.wav)";
    }

}
