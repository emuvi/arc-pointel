package pin.libutl;

import pin.libvlr.VFrm;

public class UtlFrm {

    public static Boolean eh(Object oValor) {
        return null;
    }

    public static VFrm pega(Object doValor) {
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
        return new String[]{"svg", "odg"};
    }

    public static String pegaExtensoesDescricao() {
        return "Desenhos (*.svg, *.odg)";
    }

}
