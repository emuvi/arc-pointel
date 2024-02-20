package pin.jrb;

import pin.libbas.Roteiro;

public class ServRoteiro extends Serv {

    public Integer prioridade;
    public String codigo;

    public ServRoteiro() {
        super(Servico.Tp.Roteiro);
        this.prioridade = Thread.NORM_PRIORITY;
        this.codigo = null;
    }

    @Override
    public void executa() throws Exception {
        Roteiro rot = new Roteiro((nome == null ? "Sem Nome" : nome), (prioridade == null ? Thread.NORM_PRIORITY : prioridade), codigo);
        rot.mudaRetornos(pRetornos());
        rot.mudaOrigem(this);
        rot.inicia();
        rot.espera();
    }

}
