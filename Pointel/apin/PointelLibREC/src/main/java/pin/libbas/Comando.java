package pin.libbas;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import pin.libtex.Marcados;
import pin.libutl.UtlBin;
import pin.libutl.UtlCrs;
import pin.libutl.UtlLis;
import pin.libutl.UtlTex;

public class Comando extends Paralelo {

    private Runtime ambiente;
    private Process processo;
    private String comando;
    private String[] argumentos;
    private String[] variaveis;
    private File diretorio;
    private String[] execucao;

    public AbstractAction aoTerminar;

    public Comando() {
        this("Sem Nome", null);
    }

    public Comando(String doComando) {
        this("Sem Nome", doComando);
    }

    public Comando(String comNome, String doComando) {
        super("Comando " + comNome);
        ambiente = null;
        comando = doComando;
        argumentos = null;
        variaveis = null;
        diretorio = null;
        aoTerminar = null;
    }

    public static Comando descrito(String nosCaracteres) {
        String nome = Marcados.preparado(nosCaracteres, "nome");
        String comando = Marcados.preparado(nosCaracteres, "comando");
        Comando retorno = new Comando(nome, comando);
        String argumentos = Marcados.preparado(nosCaracteres, "argumentos");
        if (argumentos != null) {
            retorno.mudaArgumentos(Partes.pega(argumentos));
        }
        String variaveis = Marcados.preparado(nosCaracteres, "variaveis");
        if (variaveis != null) {
            retorno.mudaVariaveis(Partes.pega(variaveis));
        }
        String diretorio = Marcados.preparado(nosCaracteres, "diretorio");
        if (diretorio != null) {
            retorno.mudaDiretorio(new File(diretorio));
        }
        return retorno;
    }

    public Comando mudaComando(String oComando) {
        comando = oComando;
        return this;
    }

    public Comando botaArgumento(String oArgumento) {
        argumentos = UtlLis.bota(oArgumento, argumentos);
        return this;
    }

    public Comando botaArgumentos(String... osArgumentos) {
        if (osArgumentos != null) {
            for (String arg : osArgumentos) {
                botaArgumento(arg);
            }
        }
        return this;
    }

    public Comando mudaArgumentos(String... osArgumentos) {
        argumentos = osArgumentos;
        return this;
    }

    public Comando botaVariavel(String aVariavel) {
        variaveis = UtlLis.bota(aVariavel, variaveis);
        return this;
    }

    public Comando botaVariaveis(String... asVariaveis) {
        if (asVariaveis != null) {
            for (String var : asVariaveis) {
                botaVariavel(var);
            }
        }
        return this;
    }

    public Comando mudaVariaveis(String... asVariaveis) {
        variaveis = asVariaveis;
        return this;
    }

    public Comando mudaDiretorio(File oDiretorio) {
        diretorio = oDiretorio;
        return this;
    }

    public Comando mudaAoTerminar(AbstractAction aAcao) {
        aoTerminar = aAcao;
        return this;
    }

    @Override
    public Comando inicia() {
        super.inicia();
        return this;
    }

    @Override
    public Comando espera() throws Exception {
        super.espera();
        return this;
    }

    @Override
    public void run() {
        try {
            retorna("Comando Iniciado", toString());
            ambiente = Runtime.getRuntime();
            processo = ambiente.exec(execucao, variaveis, diretorio);
            if (retornos() != null) {
                PegaCorrente inCor = new PegaCorrente(processo.getInputStream(), retornos(), "Comando").inicia();
                PegaCorrente erCor = new PegaCorrente(processo.getErrorStream(), retornos(), "Comando").inicia();
                while (erCor.isAlive() || inCor.isAlive()) {
                    UtlBin.esperaMilis(100);
                }
            }
            processo.waitFor();
            retorna("Comando Terminado", true);
            if (aoTerminar != null) {
                aoTerminar.actionPerformed(null);
            }
        } catch (Exception ex) {
            retorna(ex);
        }
    }

    @Override
    public String toString() {
        List<String> exec = new ArrayList<>();
        if (comando != null) {
            if (!comando.isEmpty()) {
                exec.add(comando);
            }
        }
        if (argumentos != null) {
            for (String arg : argumentos) {
                exec.add(arg);
            }
        }
        execucao = exec.toArray(new String[0]);
        String retorno = "Comando: " + UtlCrs.soma(execucao, " ");
        if (variaveis != null) {
            retorno += UtlTex.quebra() + "Variáveis: " + UtlCrs.soma(variaveis, ", ");
        }
        if (diretorio != null) {
            retorno += UtlTex.quebra() + "Diretório: " + diretorio.getAbsolutePath();
        }
        return retorno;
    }

    public String descreve() {
        String retorno = Marcados.prepara(pegaNomeSimples(), "nome");
        retorno += UtlTex.quebra() + Marcados.prepara(comando, "comando");
        if (argumentos != null) {
            retorno += UtlTex.quebra() + Marcados.prepara(Partes.junta(argumentos), "argumentos");
        }
        if (variaveis != null) {
            retorno += UtlTex.quebra() + Marcados.prepara(Partes.junta(variaveis), "variaveis");
        }
        if (diretorio != null) {
            retorno += UtlTex.quebra() + Marcados.prepara(diretorio.getAbsolutePath(), "diretorio");
        }
        return retorno;
    }

}
