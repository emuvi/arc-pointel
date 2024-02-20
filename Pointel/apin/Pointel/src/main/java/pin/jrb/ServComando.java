package pin.jrb;

import java.io.File;
import pin.libbas.Comando;

public class ServComando extends Serv {

    public String comando;
    public Boolean mostrador;
    public String[] argumentos;
    public String[] variaveis;
    public File diretorio;

    public ServComando() {
        super(Servico.Tp.Comando);
        this.comando = null;
        this.mostrador = false;
        this.argumentos = null;
        this.variaveis = null;
        this.diretorio = null;
    }

    @Override
    public void executa() throws Exception {
        Comando cmd = new Comando(pNome(), comando);
        cmd.mudaArgumentos(argumentos);
        cmd.mudaVariaveis(variaveis);
        cmd.mudaDiretorio(diretorio);
        cmd.mudaRetornos(pRetornos());
        if (mostrador) {
            cmd.retornos().mostrador("Comando");
        }
        cmd.inicia();
        cmd.espera();
    }

}
