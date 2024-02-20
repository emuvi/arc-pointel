package pin.prk;

import java.io.File;
import pin.libbas.Comando;
import pin.libbas.Retornos;

public class RobComando extends RobAbs {

    public String comando;
    public String[] argumentos;
    public String[] variaveis;
    public File diretorio;

    public RobComando() {
        this.comando = null;
        this.argumentos = null;
        this.variaveis = null;
        this.diretorio = null;
    }

    @Override
    public String executa() throws Exception {
        Retornos.bota(pRobotico().pRetornos(), "Iniciando Comando", pNome());
        Comando cmd = new Comando(pNome(), comando);
        cmd.mudaArgumentos(argumentos);
        cmd.mudaVariaveis(variaveis);
        cmd.mudaDiretorio(diretorio);
        cmd.mudaRetornos(pRobotico().pRetornos());
        cmd.inicia();
        cmd.espera();
        return "<próximo>";
    }

}
