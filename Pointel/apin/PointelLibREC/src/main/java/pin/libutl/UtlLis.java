package pin.libutl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import pin.libtex.Marcados;
import pin.libvlr.VLis;
import pin.libvlr.Vlr;

public class UtlLis {

    public static Boolean ehExtensao(String aExtensao) {
        if (!aExtensao.startsWith(".")) {
            aExtensao = "." + aExtensao;
        }
        aExtensao = aExtensao.toLowerCase();
        return UtlLis.tem(aExtensao, pegaExtensoes());
    }

    public static String[] pegaExtensoes() {
        return new String[]{"lis"};
    }

    public static String pegaExtensoesDescricao() {
        return "Listas (*.lis)";
    }

    public static Boolean eh(Object oValor) {
        Boolean retorno = false;
        if (oValor != null) {
            if (oValor instanceof List) {
                retorno = true;
            } else if (oValor instanceof Object[]) {
                retorno = true;
            } else if (oValor instanceof VLis) {
                retorno = true;
            } else if (oValor.getClass().isArray()) {
                retorno = true;
            }
        }
        return retorno;
    }

    public static Boolean eh(Class daClasse) {
        Boolean retorno = false;
        if (daClasse != null) {
            if (List.class.isAssignableFrom(daClasse)) {
                retorno = true;
            } else if (VLis.class.isAssignableFrom(daClasse)) {
                retorno = true;
            } else if (daClasse.isArray()) {
                retorno = true;
            }
        }
        return retorno;
    }

    public static Boolean eh(String nosCaracteres) {
        return eh(nosCaracteres, "lista");
    }

    public static Boolean eh(String nosCaracteres, String marcaLista) {
        Boolean retorno = false;
        if (nosCaracteres != null) {
            nosCaracteres = nosCaracteres.trim();
            if (nosCaracteres.startsWith("<" + marcaLista + ">") && nosCaracteres.endsWith("</" + marcaLista + ">")) {
                retorno = true;
            }
        }
        return retorno;
    }

    public static List pega(Object doValor) {
        return pega(doValor, null);
    }

    public static List pega(Object doValor, List comPadrao) {
        if (doValor instanceof Vlr) {
            doValor = ((Vlr) doValor).pg();
        }
        List<Object> retorno = comPadrao;
        if (doValor != null) {
            if (doValor instanceof List) {
                retorno = (List) doValor;
            } else if (doValor instanceof Object[]) {
                retorno = Arrays.asList(((Object[]) doValor));
            } else if (doValor.getClass().isArray()) {
                retorno = new ArrayList<>();
                if (doValor instanceof byte[]) {
                    for (byte ob : ((byte[]) doValor)) {
                        retorno.add(ob);
                    }
                } else if (doValor instanceof short[]) {
                    for (short ob : ((short[]) doValor)) {
                        retorno.add(ob);
                    }
                } else if (doValor instanceof int[]) {
                    for (int ob : ((int[]) doValor)) {
                        retorno.add(ob);
                    }
                } else if (doValor instanceof long[]) {
                    for (long ob : ((long[]) doValor)) {
                        retorno.add(ob);
                    }
                } else if (doValor instanceof float[]) {
                    for (float ob : ((float[]) doValor)) {
                        retorno.add(ob);
                    }
                } else if (doValor instanceof double[]) {
                    for (double ob : ((double[]) doValor)) {
                        retorno.add(ob);
                    }
                } else if (doValor instanceof boolean[]) {
                    for (boolean ob : ((boolean[]) doValor)) {
                        retorno.add(ob);
                    }
                } else if (doValor instanceof char[]) {
                    for (char ob : ((char[]) doValor)) {
                        retorno.add(ob);
                    }
                }
            } else if (doValor instanceof String) {
                if (estaDescrito((String) doValor)) {
                    return descrito((String) doValor);
                } else {
                    retorno = new ArrayList<>();
                    retorno.add(doValor);
                }
            } else {
                retorno = new ArrayList<>();
                retorno.add(doValor);
            }
        }
        return retorno;
    }

    public static String formata(List aLista) {
        return UtlLis.formata(aLista, "Lista");
    }

    public static String formata(List aLista, String comTitulo) {
        String retorno = (comTitulo != null ? comTitulo + ":" : "");
        if (aLista != null) {
            for (Object itn : aLista) {
                retorno += UtlTex.quebra() + UtlCrs.pega(itn);
            }
        }
        return retorno;
    }

    public static String formataLinha(List aLista) {
        return UtlLis.formataLinha(aLista, " , ");
    }

    public static String formataLinha(List aLista, String comJuncao) {
        String retorno = "";
        if (aLista != null) {
            for (Object itn : aLista) {
                if (!retorno.isEmpty()) {
                    retorno += comJuncao;
                }
                retorno += UtlCrs.pega(itn);
            }
        }
        return retorno;
    }

    public static Boolean estaDescrito(String nosCaracteres) {
        return estaDescrito(nosCaracteres, "lista");
    }

    public static Boolean estaDescrito(String nosCaracteres, String marcaLista) {
        if (nosCaracteres == null) {
            return false;
        }
        nosCaracteres = nosCaracteres.trim();
        if (nosCaracteres.isEmpty()) {
            return false;
        }
        return (nosCaracteres.startsWith("<" + marcaLista));
    }

    public static List descrito(String nosCaracteres) {
        return descrito(nosCaracteres, null, "lista", "item");
    }

    public static List descrito(String nosCaracteres, List comPadrao) {
        return descrito(nosCaracteres, comPadrao, "lista", "item");
    }

    public static List descrito(String nosCaracteres, List comPadrao, String marcaLista, String marcaItem) {
        if (nosCaracteres == null) {
            return comPadrao;
        }
        if (nosCaracteres.isEmpty()) {
            return comPadrao;
        }
        if (nosCaracteres.equals("null")) {
            return comPadrao;
        }
        try {
            String lista = Marcados.marcado(nosCaracteres, marcaLista);
            List<String> marcados = Marcados.marcados(lista, marcaItem);
            List<Object> retorno = new ArrayList<>();
            for (String marc : marcados) {
                retorno.add(UtlBin.descrito(marc));
            }
            return retorno;
        } catch (Exception ex) {
            return comPadrao;
        }
    }

    public static <T> List<T> descrito(String nosCaracteres, Class<? extends T> daClasse) {
        return descrito(nosCaracteres, null, "lista", "item", daClasse);
    }

    public static <T> List<T> descrito(String nosCaracteres, List<T> comPadrao, Class<? extends T> daClasse) {
        return descrito(nosCaracteres, comPadrao, "lista", "item", daClasse);
    }

    public static <T> List<T> descrito(String nosCaracteres, List<T> comPadrao, String marcaLista, String marcaItem, Class<? extends T> daClasse) {
        if (nosCaracteres == null) {
            return comPadrao;
        }
        if (nosCaracteres.isEmpty()) {
            return comPadrao;
        }
        if (nosCaracteres.equals("null")) {
            return comPadrao;
        }
        try {
            String lista = Marcados.marcado(nosCaracteres, marcaLista);
            List<String> vals = Marcados.marcados(lista, marcaItem);
            List<T> retorno = new ArrayList<>();
            for (int ir = 0; ir < vals.size(); ir++) {
                retorno.add(UtlBin.pega(daClasse, UtlBin.descrito(vals.get(ir))));
            }
            return retorno;
        } catch (Exception ex) {
            return comPadrao;
        }
    }

    public static String descreve(List aLista) {
        return descreve(aLista, "lista", "item");
    }

    public static String descreve(List aLista, String marcaLista, String marcaItem) {
        String retorno = Marcados.pAbridor(marcaLista) + UtlTex.quebra();
        if (aLista != null) {
            for (Object vlr : aLista) {
                retorno += Marcados.marca(UtlBin.descreve(vlr), marcaItem) + UtlTex.quebra();
            }
        }
        return retorno + Marcados.pFechador(marcaLista);
    }

    public static List<String> descritoLCrs(String nosCaracteres) {
        return descritoLCrs(nosCaracteres, "lista", "item");
    }

    public static List<String> descritoLCrs(String nosCaracteres, String marcaLista, String marcaItem) {
        if (nosCaracteres == null) {
            return null;
        }
        String lista = Marcados.marcado(nosCaracteres, marcaLista);
        return Marcados.marcados(lista, marcaItem);
    }

    public static String descreveLCrs(List<String> aLista) {
        return descreveLCrs(aLista, "lista", "item");
    }

    public static String descreveLCrs(List<String> aLista, String marcaLista, String marcaItem) {
        String retorno = Marcados.pAbridor(marcaLista) + UtlTex.quebra();
        if (aLista != null) {
            for (String vlr : aLista) {
                retorno += Marcados.marca(vlr, marcaItem);
            }
        }
        return retorno + Marcados.pFechador(marcaLista);
    }

    public static <T> T pegaGen(T[] daLista, Integer noIndice) {
        return UtlLis.pegaGen(daLista, noIndice, null);
    }

    public static <T> T pegaGen(T[] daLista, Integer noIndice, T comPadrao) {
        T retorno = comPadrao;
        if (daLista != null) {
            if (noIndice < daLista.length) {
                retorno = daLista[noIndice];
            }
        }
        if (retorno == null) {
            retorno = comPadrao;
        }
        return retorno;
    }

    public static <T> List<T> pega(List daLista, Class<? extends T> daClasse) throws Exception {
        if (daLista == null) {
            return null;
        }
        List<T> retorno = new ArrayList<>();
        for (Object val : daLista) {
            retorno.add(UtlBin.pega(daClasse, val));
        }
        return retorno;
    }

    public static <T> T pega(T[] daLista, Integer noIndice) {
        return pega(daLista, noIndice, null);
    }

    public static <T> T pega(T[] daLista, Integer noIndice, T comPadrao) {
        T retorno = comPadrao;
        if (daLista != null) {
            if (noIndice < daLista.length) {
                retorno = daLista[noIndice];
            }
        }
        if (retorno == null) {
            retorno = comPadrao;
        }
        return retorno;
    }

    public static Object pegaVal(Object[] daLista, Integer noIndice) {
        return pegaVal(daLista, noIndice, null);
    }

    public static Object pegaVal(Object[] daLista, Integer noIndice, Object comPadrao) {
        Object retorno = comPadrao;
        if (daLista != null) {
            if (noIndice < daLista.length) {
                retorno = daLista[noIndice];
            }
        }
        if (retorno == null) {
            retorno = comPadrao;
        }
        return retorno;
    }

    public static <T> Object[] pegaMat(T[] daLista) {
        Object[] retorno = null;
        if (daLista != null) {
            retorno = new Object[daLista.length];
            for (int i = 0; i < daLista.length; i++) {
                retorno[i] = daLista[i];
            }
        }
        return retorno;
    }

    public static Object[] pegaMat(List daLista) {
        return pegaMat(daLista, (Object[]) null);
    }

    public static Object[] pegaMat(List daLista, Object[] comPadrao) {
        Object[] retorno = comPadrao;
        if (daLista != null) {
            retorno = new Object[daLista.size()];
            for (int i = 0; i < daLista.size(); i++) {
                retorno[i] = daLista.get(i);
            }
        }
        return retorno;
    }

    public static <T> T[] pegaMat(List daLista, Class<? extends T> naClasse) throws Exception {
        return pegaMat(daLista, naClasse, null);
    }

    public static <T> T[] pegaMat(List daLista, Class<? extends T> naClasse, T[] comPadrao) throws Exception {
        T[] retorno = comPadrao;
        if (daLista != null) {
            retorno = (T[]) Array.newInstance(naClasse, daLista.size());
            for (int i = 0; i < daLista.size(); i++) {
                Object val = daLista.get(i);
                if (naClasse.isInstance(val)) {
                    retorno[i] = (T) val;
                } else {
                    retorno[i] = UtlBin.pega(naClasse, val);
                }
            }
        }
        return retorno;
    }

    public static String[] pegaMatCrs(List daLista) {
        return pegaMatCrs(daLista, null);
    }

    public static String[] pegaMatCrs(List daLista, String[] comPadrao) {
        String[] retorno = comPadrao;
        if (daLista != null) {
            retorno = new String[daLista.size()];
            for (int i = 0; i < daLista.size(); i++) {
                retorno[i] = UtlCrs.pega(daLista.get(i));
            }
        }
        return retorno;
    }

    public static String[] pMatCrs(Object[] daMatriz) {
        String[] retorno = null;
        if (daMatriz != null) {
            retorno = new String[daMatriz.length];
            for (int i = 0; i < daMatriz.length; i++) {
                retorno[i] = UtlCrs.pega(daMatriz[i]);
            }
        }
        return retorno;
    }

    public static <T> String pegaCrs(T[] daLista, Integer noIndice) {
        return pegaCrs(daLista, noIndice, null);
    }

    public static <T> String pegaCrs(T[] daLista, Integer noIndice, String comPadrao) {
        return UtlCrs.pega(pegaGen(daLista, noIndice), comPadrao);
    }

    public static <T> Integer pegaIndice(T[] dosValores, List<T[]> naLista) {
        Integer retorno = -1;
        int cols = dosValores.length;
        for (int i0 = 0; i0 < naLista.size(); i0++) {
            Object[] itn = naLista.get(i0);
            Boolean igual = true;
            for (int i1 = 0; i1 < cols; i1++) {
                if (!Objects.equals(dosValores[i1], itn[i1])) {
                    igual = false;
                    break;
                }
            }
            if (igual) {
                retorno = i0;
                break;
            }
        }
        return retorno;
    }

    public static <T> void bota(T[] osValores, List<T> naLista) {
        if (naLista != null) {
            if (osValores != null) {
                for (T vlr : osValores) {
                    naLista.add(vlr);
                }
            }
        }
    }

    public static <T> T[] bota(T oValor, T[] naLista) {
        T[] retorno = null;
        if (naLista == null) {
            retorno = (T[]) Array.newInstance(oValor.getClass(), 1);
            retorno[0] = oValor;
        } else {
            retorno = Arrays.copyOf(naLista, naLista.length + 1);
            retorno[naLista.length] = oValor;
        }
        return retorno;
    }

    public static <T> T[] bota(T oValor, T[] naLista, Integer noIndice) {
        T[] retorno = naLista;
        if (retorno == null) {
            retorno = (T[]) Array.newInstance(oValor.getClass(), 1);
        }
        if (noIndice >= naLista.length) {
            retorno = Arrays.copyOf(naLista, noIndice + 1);
        }
        retorno[noIndice] = oValor;
        return retorno;
    }

    public static <T> Boolean tem(T oValor, List<T> naLista) {
        Boolean retorno = false;
        if (naLista != null) {
            for (T daLista : naLista) {
                if (Objects.equals(oValor, daLista)) {
                    retorno = true;
                    break;
                }
            }
        }
        return retorno;
    }

    public static Boolean tem(List naLista, Object oValor) {
        Boolean retorno = false;
        if (naLista != null) {
            for (Object daLista : naLista) {
                if (Objects.equals(oValor, daLista)) {
                    retorno = true;
                    break;
                }
            }
        }
        return retorno;
    }

    public static <T> Boolean tem(T oValor, T[] naLista) {
        Boolean retorno = false;
        if (naLista != null) {
            for (T daLista : naLista) {
                if (Objects.equals(oValor, daLista)) {
                    retorno = true;
                    break;
                }
            }
        }
        return retorno;
    }

    public static Boolean tem(Object[] naLista, Object oValor) {
        Boolean retorno = false;
        if (naLista != null) {
            for (Object daLista : naLista) {
                if (Objects.equals(oValor, daLista)) {
                    retorno = true;
                    break;
                }
            }
        }
        return retorno;
    }

    public static Boolean tem(char oValor, char[] naLista) {
        Boolean retorno = false;
        if (naLista != null) {
            for (char daLista : naLista) {
                if (Objects.equals(oValor, daLista)) {
                    retorno = true;
                    break;
                }
            }
        }
        return retorno;
    }

    public static <T> Integer conta(T oValor, List<T> naLista) {
        Integer retorno = 0;
        if (naLista != null) {
            for (T daLista : naLista) {
                if (Objects.equals(oValor, daLista)) {
                    retorno++;
                    break;
                }
            }
        }
        return retorno;
    }

    public static Integer conta(List naLista, Object oValor) {
        Integer retorno = 0;
        if (naLista != null) {
            for (Object daLista : naLista) {
                if (Objects.equals(oValor, daLista)) {
                    retorno++;
                    break;
                }
            }
        }
        return retorno;
    }

    public static <T> Integer conta(T oValor, T[] naLista) {
        Integer retorno = 0;
        if (naLista != null) {
            for (T daLista : naLista) {
                if (Objects.equals(oValor, daLista)) {
                    retorno++;
                    break;
                }
            }
        }
        return retorno;
    }

    public static Integer conta(Object[] naLista, Object oValor) {
        Integer retorno = 0;
        if (naLista != null) {
            for (Object daLista : naLista) {
                if (Objects.equals(oValor, daLista)) {
                    retorno++;
                    break;
                }
            }
        }
        return retorno;
    }

    public static <T> Integer conta(char oValor, char[] naLista) {
        Integer retorno = 0;
        if (naLista != null) {
            for (char daLista : naLista) {
                if (Objects.equals(oValor, daLista)) {
                    retorno++;
                    break;
                }
            }
        }
        return retorno;
    }

    public static <T> Boolean compara(T[] osValores, T[] comValores) {
        if (osValores == null && comValores == null) {
            return true;
        }
        if (osValores == null && comValores != null) {
            return false;
        }
        if (osValores != null && comValores == null) {
            return false;
        }
        Boolean retorno = true;
        if (osValores.length == comValores.length) {
            for (int i1 = 0; i1 < osValores.length; i1++) {
                if (!Objects.equals(osValores[i1], comValores[i1])) {
                    retorno = false;
                    break;
                }
            }
        } else {
            retorno = false;
        }
        return retorno;
    }

    public static <T> T[] copia(T[] de, T[] para) {
        T[] retorno = para;
        if (de != null) {
            if (retorno == null) {
                retorno = (T[]) Array.newInstance(de[0].getClass(), de.length);
            }
            for (int i1 = 0; i1 < UtlInt.pegaMenor(de.length, para.length); i1++) {
                retorno[i1] = de[i1];
            }
        }
        return retorno;
    }

    public static <T> List<T> copia(List de, Class<? extends T> naClasse) {
        return copia(de, naClasse, null);
    }

    public static <T> List<T> copia(List de, Class<? extends T> naClasse, List<T> comPadrao) {
        List<T> retorno = comPadrao;
        if (de != null) {
            if (retorno == null) {
                retorno = new ArrayList<>();
            }
            for (Object obj : de) {
                try {
                    retorno.add(UtlBin.pega(naClasse, obj));
                } catch (Exception ex) {
                }
            }
        }
        return retorno;
    }

    public static <T> T[] anular(T[] aLista) {
        if (aLista != null) {
            for (int i = 0; i < aLista.length; i++) {
                aLista[i] = null;
            }
        }
        return aLista;
    }

    public static float[] trocaPrimitivo(final Float[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return new float[0];
        }
        final float[] result = new float[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i].floatValue();
        }
        return result;
    }

    public static Float[] trocaPrimitivo(final float[] array) {
        if (array == null) {
            return null;
        } else if (array.length == 0) {
            return new Float[0];
        }
        final Float[] result = new Float[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

}
