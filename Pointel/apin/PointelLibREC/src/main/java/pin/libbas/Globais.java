package pin.libbas;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import pin.libtex.Marcados;
import pin.libutl.*;

public class Globais {

    public static volatile Map<String, Object> globais = null;

    public static void bota(String naChave, Object oValor) {
        if (globais == null) {
            globais = new ConcurrentHashMap<>();
        }
        globais.put(naChave, oValor);
    }

    public static void remove(String aChave) {
        if (globais != null) {
            globais.remove(aChave);
        }
    }

    public static Object pega(String daChave) {
        return pega(daChave, null);
    }

    public static Object pega(String daChave, Object comPadrao) {
        if (globais != null) {
            return globais.getOrDefault(daChave, comPadrao);
        }
        return comPadrao;
    }

    public static Boolean pegaLog(String daChave) {
        return UtlLog.pega(pega(daChave));
    }

    public static Boolean pegaLog(String daChave, Boolean comPadrao) {
        return UtlLog.pega(pega(daChave), comPadrao);
    }

    public static Character pegaCr(String daChave) {
        return UtlCr.pega(pega(daChave));
    }

    public static Character pegaCr(String daChave, Character comPadrao) {
        return UtlCr.pega(pega(daChave), comPadrao);
    }

    public static String pegaCrs(String daChave) {
        return UtlCrs.pega(pega(daChave));
    }

    public static String pegaCrs(String daChave, String comPadrao) {
        return UtlCrs.pega(pega(daChave), comPadrao);
    }

    public static Integer pegaInt(String daChave) {
        return UtlInt.pega(pega(daChave));
    }

    public static Integer pegaInt(String daChave, Integer comPadrao) {
        return UtlInt.pega(pega(daChave), comPadrao);
    }

    public static Long pegaIntLon(String daChave) {
        return UtlIntLon.pega(pega(daChave));
    }

    public static Long pegaIntLon(String daChave, Long comPadrao) {
        return UtlIntLon.pega(pega(daChave), comPadrao);
    }

    public static Float pegaNum(String daChave) {
        return UtlNum.pega(pega(daChave));
    }

    public static Float pegaNum(String daChave, Float comPadrao) {
        return UtlNum.pega(pega(daChave), comPadrao);
    }

    public static Double pegaNumLon(String daChave) {
        return UtlNumLon.pega(pega(daChave));
    }

    public static Double pegaNumLon(String daChave, Double comPadrao) {
        return UtlNumLon.pega(pega(daChave), comPadrao);
    }

    public static java.util.Date pegaDat(String daChave) {
        return UtlDat.pega(pega(daChave));
    }

    public static java.util.Date pegaDat(String daChave, java.util.Date comPadrao) {
        return UtlDat.pega(pega(daChave), comPadrao);
    }

    public static java.util.Date pegaHor(String daChave) {
        return UtlHor.pega(pega(daChave));
    }

    public static java.util.Date pegaHor(String daChave, java.util.Date comPadrao) {
        return UtlHor.pega(pega(daChave), comPadrao);
    }

    public static java.util.Date pegaDatHor(String daChave) {
        return UtlDatHor.pega(pega(daChave));
    }

    public static java.util.Date pegaDatHor(String daChave, java.util.Date comPadrao) {
        return UtlDatHor.pega(pega(daChave), comPadrao);
    }

    public static java.util.Date pegaMom(String daChave) {
        return UtlMom.pega(pega(daChave));
    }

    public static java.util.Date pegaMom(String daChave, java.util.Date comPadrao) {
        return UtlMom.pega(pega(daChave), comPadrao);
    }

    public static byte[] pegaArq(String daChave) {
        return UtlArq.pega(pega(daChave));
    }

    public static byte[] pegaArq(String daChave, byte[] comPadrao) {
        return UtlArq.pega(pega(daChave), comPadrao);
    }

    public static String formata() {
        String retorno = "<globais>";
        for (Map.Entry<String, Object> elem : globais.entrySet()) {
            retorno += Marcados.prepara(elem.getKey(), "chave");
            retorno += Marcados.prepara(UtlBin.descreve(elem.getValue()), "valor");
        }
        retorno += "</globais>";
        return retorno;
    }
}
