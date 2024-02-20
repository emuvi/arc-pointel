package pin.libutl;

import java.util.Random;

public class UtlAle {

    private static volatile Random rand = null;

    private static void iniciaRand() {
        if (rand == null) {
            rand = new Random();
        } else if (rand.nextInt() % 3 == 0 && rand.nextInt() % 2 == 0) {
            rand = new Random();
        }
    }

    public static Boolean pegaLog() {
        iniciaRand();
        return rand.nextBoolean();
    }

    public static Boolean pegaLog(Integer comMinimo, Integer eMaximo, Integer eProbabilidade) {
        iniciaRand();
        return pegaInt(comMinimo, eMaximo) < eProbabilidade;
    }

    public static Integer pegaDig() {
        iniciaRand();
        return rand.nextInt(10);
    }

    public static Integer pegaInt() {
        iniciaRand();
        return rand.nextInt();
    }

    public static Integer pegaInt(Integer comMaximo) {
        Integer retorno = null;
        iniciaRand();
        retorno = rand.nextInt(comMaximo);
        return retorno;
    }

    public static Integer pegaInt(Integer comMinimo, Integer eMaximo) {
        Integer retorno = null;
        Integer largura = eMaximo - comMinimo;
        iniciaRand();
        retorno = comMinimo + rand.nextInt(largura);
        return retorno;
    }

    public static char pegaCrSimples() {
        return UtlCr.crsSimples[pegaInt(0, UtlCr.crsSimples.length)];
    }

    public static String pegaCrsSimples(int quantidade) {
        String retorno = "";
        while (retorno.length() < quantidade) {
            retorno = retorno + pegaCrSimples();
        }
        return retorno;
    }

    public static char pegaCrMaiusculosSimplesInteiros() {
        return UtlCr.crsMaiusculosSimplesInteiros[pegaInt(0, UtlCr.crsMaiusculosSimplesInteiros.length)];
    }

    public static String pegaCrsMaiusculosSimplesInteiros(int quantidade) {
        String retorno = "";
        while (retorno.length() < quantidade) {
            retorno = retorno + pegaCrMaiusculosSimplesInteiros();
        }
        return retorno;
    }

    public static Double pegaNumLon() {
        Integer decimal = pegaInt();
        if (decimal < 0) {
            decimal = decimal * -1;
        }
        return new Double(pegaInt() + "." + decimal);
    }

    public static Object pega(Object[] daLista) {
        Object retorno = null;
        if (daLista != null) {
            if (daLista.length > 0) {
                retorno = daLista[pegaInt(0, daLista.length - 1)];
            }
        }
        return retorno;
    }
}
