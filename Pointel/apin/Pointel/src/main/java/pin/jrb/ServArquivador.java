package pin.jrb;

import pin.jrb.Serv;
import pin.jrb.Servico;
import pin.libbas.Retornos;

public class ServArquivador extends Serv {

    public volatile String conexao;
    public volatile String conjunto;
    public volatile Integer anterior;
    public volatile String medida;
    public volatile Boolean excluir;
    public volatile String destino;
    public volatile String base;
    public volatile Integer porta;
    public volatile String usuario;
    public volatile String senha;
    public volatile Integer velocidade;
    public volatile Boolean retornos;
    public volatile Boolean mostrador;

    public ServArquivador() {
        super(Servico.Tp.Arquivador);
        this.conexao = "P";
        this.conjunto = "A";
        this.anterior = null;
        this.medida = null;
        this.excluir = true;
        this.destino = "pointel.sytes.net";
        this.base = "pointel";
        this.porta = 27017;
        this.usuario = "pointel";
        this.senha = "";
        this.velocidade = 5;
        this.retornos = true;
        this.mostrador = true;
    }

    @Override
    public void executa() throws Exception {
        if (retornos && pRetornos() == null) {
            mRetornos(new Retornos());
        }
        if (mostrador && pRetornos() != null) {
            pRetornos().mostrador("Arquivador");
            pRetornos().abre();
        }
        ServArquivadorExc exc = new ServArquivadorExc(this, pRetornos());
        exc.executar();
    }

}
