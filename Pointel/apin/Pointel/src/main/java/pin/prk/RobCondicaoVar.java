package pin.prk;

import pin.libbas.Retornos;
import pin.libutl.UtlCrs;

public class RobCondicaoVar extends RobCondicaoAbs {

    public String expressao;

    public RobCondicaoVar() {
        this.expressao = null;
    }

    @Override
    public Boolean verifica() throws Exception {
        Boolean retorno = procLog(expressao);
        Retornos.bota(pRobotico().pRetornos(), "Condição Por Variável", UtlCrs.junta(retorno, expressao));
        return retorno;
    }

}
