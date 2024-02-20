package pin.libbas;

import javax.swing.AbstractAction;
import pin.libtex.Marcados;
import pin.libutl.UtlCrs;
import pin.libutl.UtlInt;
import pin.libutl.UtlPro;
import pin.libutl.UtlTex;

public class Roteiro extends Paralelo {

    public String codigo;
    public Object origem;
    public Object retorno;

    public AbstractAction aoTerminar;

    public Roteiro() {
        this("Sem Nome", Thread.NORM_PRIORITY, null);
    }

    public Roteiro(String doCodigo) {
        this("Sem Nome", Thread.NORM_PRIORITY, doCodigo);
    }

    public Roteiro(String comNome, String doCodigo) {
        this(comNome, Thread.NORM_PRIORITY, doCodigo);
    }

    public Roteiro(String comNome, Integer ePrioridade, String doCodigo) {
        super("Roteiro " + comNome, ePrioridade);
        codigo = doCodigo;
        origem = null;
        aoTerminar = null;
    }

    public static Roteiro descrito(String nosCaracteres) {
        String nome = Marcados.preparado(nosCaracteres, "nome");
        Integer prioridade = UtlInt.pega(Marcados.preparado(nosCaracteres, "prioridade"));
        String codigo = Marcados.preparado(nosCaracteres, "codigo");
        return new Roteiro(nome, prioridade, codigo);
    }

    public Roteiro mudaCodigo(String oCodigo) {
        codigo = oCodigo;
        return this;
    }

    public Roteiro mudaOrigem(Object aOrigem) {
        origem = aOrigem;
        return this;
    }

    public Roteiro mudaAoTerminar(AbstractAction aAcao) {
        aoTerminar = aAcao;
        return this;
    }

    @Override
    public Roteiro inicia() {
        super.inicia();
        return this;
    }

    @Override
    public Roteiro espera() throws Exception {
        super.espera();
        return this;
    }

    public Object pegaRetorno() {
        return retorno;
    }

    @Override
    public void run() {
        try {
            retorna("Roteiro Iniciado", toString());
            retorno = UtlPro.roteiro(codigo, retornos(), origem);
            retorna("Retorno", retorno);
            retorna("Roteiro Terminado", true);
            if (aoTerminar != null) {
                aoTerminar.actionPerformed(null);
            }
        } catch (Exception ex) {
            retorna(ex);
        }
    }

    @Override
    public String toString() {
        return codigo;
    }

    public String descreve() {
        String retorno = Marcados.prepara(pegaNomeSimples(), "nome");
        retorno += UtlTex.quebra() + Marcados.prepara(UtlCrs.pega(getPriority()), "prioridade");
        retorno += UtlTex.quebra() + Marcados.prepara(codigo, "codigo");
        return retorno;
    }

}
