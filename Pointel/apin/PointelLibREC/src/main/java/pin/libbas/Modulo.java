package pin.libbas;

import javax.swing.AbstractAction;
import pin.libtex.Marcados;
import pin.libutl.UtlBin;
import pin.libutl.UtlCrs;
import pin.libutl.UtlLis;
import pin.libutl.UtlTex;

public class Modulo extends Paralelo {

    private String legenda;
    private String[] argumentos;

    public AbstractAction aoTerminar;

    public Modulo() {
        this("Sem Nome", null);
    }

    public Modulo(String daLegenda) {
        this("Sem Nome", daLegenda);
    }

    public Modulo(String comNome, String daLegenda) {
        super("Módulo " + comNome);
        legenda = daLegenda;
        argumentos = null;
        aoTerminar = null;
    }

    public static Modulo descrito(String nosCaracteres) {
        String nome = Marcados.preparado(nosCaracteres, "nome");
        String legenda = Marcados.preparado(nosCaracteres, "legenda");
        Modulo retorno = new Modulo(nome, legenda);
        String argumentos = Marcados.preparado(nosCaracteres, "argumentos");
        if (argumentos != null) {
            retorno.mudaArgumentos(Partes.pega(argumentos));
        }
        return retorno;
    }

    public Modulo mudaLegenda(String aLegenda) {
        legenda = aLegenda;
        return this;
    }

    public Modulo botaArgumento(String oArgumento) {
        argumentos = UtlLis.bota(oArgumento, argumentos);
        return this;
    }

    public Modulo botaArgumentos(String... osArgumentos) {
        if (osArgumentos != null) {
            for (String arg : osArgumentos) {
                botaArgumento(arg);
            }
        }
        return this;
    }

    public Modulo mudaArgumentos(String... osArgumentos) {
        argumentos = osArgumentos;
        return this;
    }

    public Modulo mudaAoTerminar(AbstractAction aAcao) {
        aoTerminar = aAcao;
        return this;
    }

    @Override
    public Modulo inicia() {
        super.inicia();
        return this;
    }

    @Override
    public Modulo espera() throws Exception {
        super.espera();
        return this;
    }

    @Override
    public void run() {
        try {
            retorna("Módulo Iniciado", toString());
            retorna(UtlBin.abreModulo(legenda, argumentos));
            retorna("Módulo Terminado", true);
            if (aoTerminar != null) {
                aoTerminar.actionPerformed(null);
            }
        } catch (Exception ex) {
            retorna(ex);
        }
    }

    @Override
    public String toString() {
        String retorno = "Legenda: " + legenda;
        if (argumentos != null) {
            retorno += UtlTex.quebra() + "Argumentos: " + UtlCrs.soma(argumentos, " ");
        }
        return retorno;
    }

    public String descreve() {
        String retorno = Marcados.prepara(pegaNomeSimples(), "nome");
        retorno += UtlTex.quebra() + Marcados.prepara(legenda, "legenda");
        if (argumentos != null) {
            retorno += UtlTex.quebra() + Marcados.prepara(Partes.junta(argumentos), "argumentos");
        }
        return retorno;
    }

}
