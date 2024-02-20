package pin.libtex;

public class LeTXTCmp {

    public static enum Tp {
        Fixo, Separado, Marcado
    }

    public Tp tipo;
    public Integer ordem;
    public Boolean ateOFim;
    public Integer tamanho;
    public String separador;
    public String marcacao;
    public Character auxiliar;
    public Boolean entreAspas;
    public Boolean aspasSimples;
    public Integer inicio;
    public Integer fim;

    public LeTXTCmp() {
        this(Tp.Fixo);
    }

    public LeTXTCmp(Tp doTipo) {
        tipo = doTipo;
        ordem = 1;
        ateOFim = false;
        tamanho = null;
        separador = null;
        auxiliar = null;
        marcacao = null;
        entreAspas = false;
        aspasSimples = false;
        inicio = null;
        fim = null;
    }

    public LeTXTCmp botaOrdem(Integer aOrdem) {
        ordem = aOrdem;
        return this;
    }

    public LeTXTCmp botaAteOFim() {
        ateOFim = true;
        return this;
    }

    public LeTXTCmp tiraAteOFim() {
        ateOFim = false;
        return this;
    }

    public LeTXTCmp botaTamanho(Integer oTamanho) {
        tamanho = oTamanho;
        ateOFim = false;
        return this;
    }

    public LeTXTCmp botaSeparador(String oSeparador) {
        separador = oSeparador;
        ateOFim = false;
        return this;
    }

    public LeTXTCmp botaAuxiliar(Character oAuxiliar) {
        auxiliar = oAuxiliar;
        return this;
    }

    public LeTXTCmp botaMarcacao(String aMarcacao) {
        marcacao = aMarcacao;
        ateOFim = false;
        return this;
    }

    public LeTXTCmp botaEntreAspas() {
        entreAspas = true;
        aspasSimples = false;
        return this;
    }

    public LeTXTCmp botaEntreAspasSimples() {
        entreAspas = true;
        aspasSimples = true;
        return this;
    }

    public LeTXTCmp tiraEntreAspas() {
        entreAspas = false;
        return this;
    }

    public LeTXTCmp tiraEntreAspasSimples() {
        aspasSimples = false;
        return this;
    }

    @Override
    public String toString() {
        String retorno = "O: " + ordem;
        if (ateOFim) {
            retorno += " / Até o Fim";
        } else {
            retorno += " / T: " + tipo + " = ";
            if (Tp.Fixo.equals(tipo)) {
                retorno += tamanho;
            } else if (Tp.Separado.equals(tipo)) {
                retorno += "'" + separador + "'";
                if (auxiliar != null) {
                    retorno += ", Aux = " + "'" + auxiliar + "'";
                }
            } else if (Tp.Marcado.equals(tipo)) {
                retorno += "'" + marcacao + "'";
            }
        }
        if (entreAspas) {
            retorno += " / Entre Aspas";
            if (aspasSimples) {
                retorno += " Simples";
            }
        }
        return retorno;
    }

}
