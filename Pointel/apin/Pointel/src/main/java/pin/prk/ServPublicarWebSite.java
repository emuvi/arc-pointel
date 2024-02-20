package pin.prk;

import java.io.File;
import pin.jrb.Serv;
import pin.jrb.Servico;
import pin.libbas.MultiParalelo;
import pin.libbas.Paralelo;

public class ServPublicarWebSite extends Serv {

    public String endereco;
    public Integer porta;

    public String tipo;
    public String usuario;
    public String senha;

    public String diretorio;
    public Integer tempoEspera;
    public Integer tentativas;

    public File origem;
    public Boolean soModificados;

    public Integer paralelos;
    public Boolean retornos;
    public Boolean mostrador;

    public ServPublicarWebSite() {
        super(Servico.Tp.PublicarWebSite);
        this.nome = null;
        this.endereco = null;
        this.porta = null;

        this.tipo = null;
        this.usuario = null;
        this.senha = null;

        this.diretorio = null;
        this.tempoEspera = null;
        this.tentativas = 3;

        this.origem = null;
        this.soModificados = true;
        this.paralelos = 5;
        this.retornos = true;
        this.mostrador = true;
    }

    @Override
    public void executa() {
        MultiParalelo multi = new MultiParalelo("Publicar WebSite " + nome, pRetornos());
        multi.botaCarregador(new ParCarrega(multi));
        for (int ip = 1; ip <= paralelos; ip++) {
            multi.botaExecutor(new ParPublica(multi, ip));
        }
        multi.inicia();
        multi.espera();
    }

    private class ParCarrega extends Paralelo {

        public ParCarrega(MultiParalelo doMultiplo) {
            super(doMultiplo);
        }

        public void carrega(File aOrigem) {
            try {
                if (pausarEdeveParar()) {
                    return;
                }
                if (aOrigem.isDirectory()) {
                    retorna("Carregando Dir", aOrigem.getAbsolutePath());
                    for (File arq : aOrigem.listFiles()) {
                        if (!arq.isDirectory()) {
                            carrega(arq);
                        }
                        if (pausarEdeveParar()) {
                            return;
                        }
                    }
                    for (File arq : aOrigem.listFiles()) {
                        if (arq.isDirectory()) {
                            carrega(arq);
                        }
                        if (pausarEdeveParar()) {
                            return;
                        }
                    }
                } else {
                    pMulti().botaPrimeiro(aOrigem);
                    retorna("Carregado", aOrigem.getAbsolutePath());
                    aumenta();
                }
            } catch (Exception ex) {
                retorna(ex);
            }
        }

        @Override
        public void run() {
            carrega(origem);
        }
    }

    private class ParPublica extends Paralelo {

        public ParPublica(MultiParalelo doMultiplo, Integer noIndice) {
            super(doMultiplo, noIndice);
        }

        @Override
        public void run() {
            try {

            } catch (Exception ex) {
                retorna(ex);
            }
        }
    }
}
