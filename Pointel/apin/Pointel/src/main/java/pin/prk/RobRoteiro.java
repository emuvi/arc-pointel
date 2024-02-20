package pin.prk;

import pin.libbas.Retornos;
import pin.libutl.UtlCrs;
import pin.libutl.UtlPro;

public class RobRoteiro extends RobAbs {

    public Integer prioridade;
    public String codigo;

    public RobRoteiro() {
        this.prioridade = Thread.NORM_PRIORITY;
        this.codigo = null;
    }

    @Override
    public String executa() throws Exception {
        Retornos.bota(pRobotico().pRetornos(), "Iniciando Roteiro", pNome());
        Object retorno = UtlPro.roteiro(codigo, pRobotico());
        String rRob = "<próximo>";
        if (UtlCrs.eh(retorno)) {
            rRob = UtlCrs.pega(retorno);
        }
        Retornos.bota(pRobotico().pRetornos(), "Roteiro Retornou: " + rRob, codigo);
        return rRob;
    }

}
