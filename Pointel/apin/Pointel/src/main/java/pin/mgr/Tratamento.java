package pin.mgr;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import pin.libutl.UtlBin;

public class Tratamento {

    private String classe;
    private String metodo;
    private List<Pattern> casos;

    public Tratamento() {
        this.classe = null;
        this.metodo = null;
        this.casos = null;
    }

    public Tratamento(String metodo) {
        this.classe = null;
        this.metodo = metodo;
        this.casos = null;
    }

    public Tratamento(String classe, String metodo) {
        this.classe = classe;
        this.metodo = metodo;
        this.casos = null;
    }

    public Tratamento botaCaso(String comFormulaCrs) {
        if (comFormulaCrs != null) {
            if (casos == null) {
                casos = new ArrayList<>();
            }
            casos.add(Pattern.compile(comFormulaCrs));
        }
        return this;
    }

    public Tratamento botaCasos(String[] comFormulasCrs) {
        if (comFormulasCrs != null) {
            for (String frmCrs : comFormulasCrs) {
                botaCaso(frmCrs);
            }
        }
        return this;
    }

    public Boolean ehCaso(String comCondicao) {
        if (casos == null) {
            return false;
        }
        for (Pattern caso : casos) {
            if (caso.matcher(comCondicao).find()) {
                return true;
            }
        }
        return false;
    }

    public List<String> pegaCasos() {
        if (casos == null) {
            return null;
        }
        List<String> retorno = new ArrayList<>();
        for (Pattern caso : casos) {
            retorno.add(caso.pattern());
        }
        return retorno;
    }

    public String pegaClasse() {
        return classe;
    }

    public Tratamento botaClasse(String classe) {
        this.classe = classe;
        return this;
    }

    public String pegaMetodo() {
        return metodo;
    }

    public Tratamento botaMetodo(String metodo) {
        this.metodo = metodo;
        return this;
    }

    public Object executa(Object noValor) throws Exception {
        boolean deObj = true;
        if (classe != null) {
            if (!classe.isEmpty()) {
                deObj = false;
            }
        }
        if (deObj) {
            return UtlBin.manda(noValor, metodo);
        } else {
            return UtlBin.manda(Class.forName(classe), metodo, noValor);
        }
    }

    @Override
    public String toString() {
        return classe + " - " + metodo;
    }

}
