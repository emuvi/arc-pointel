package pin.modamk;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import pin.libamk.RoteirosIntfAux;

public class Cadastros {

    private static List<Cadastro> lista = null;

    public static Cadastro pega(Class<? extends Cadastro> daClasse) throws Exception {
        if (lista == null) {
            lista = new ArrayList<>();
        }
        for (Cadastro cad : lista) {
            if (daClasse.isInstance(cad)) {
                return cad;
            }
        }
        Cadastro retorno = daClasse.newInstance();
        lista.add(retorno);
        return retorno;
    }

    public static Cadastro procura(String daTabela) throws Exception {
        return procura("public", daTabela);
    }

    public static Cadastro procura(String doEsquema, String eTabela) throws Exception {
        String cadClsNome = Cadastro.class.getName();
        List<String> classes = RoteirosIntfAux.pegaClasses();
        for (String clNome : classes) {
            if (clNome.startsWith("pin.") && (!cadClsNome.equals(clNome))) {
                try {
                    Class cls = Class.forName(clNome);
                    if (Cadastro.class.isAssignableFrom(cls)) {
                        Cadastro cad = pega(cls);
                        if (Objects.equals(doEsquema, cad.esquema)) {
                            if (Objects.equals(eTabela, cad.tabela)) {
                                return cad;
                            }
                        }
                    }
                } catch (Exception ex) {
                }
            }
        }
        return null;
    }
}
