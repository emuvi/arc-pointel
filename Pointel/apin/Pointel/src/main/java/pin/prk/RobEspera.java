package pin.prk;

import pin.libbas.Retornos;
import pin.libutl.UtlBin;

public class RobEspera extends RobAbs {

    public String milisegundos;

    public RobEspera() {
        milisegundos = null;
    }

    @Override
    public String executa() throws Exception {
        Integer afazer = procInt(milisegundos);
        UtlBin.esperaMilis(afazer);
        Retornos.bota(pRobotico().pRetornos(), "Esperado", afazer);
        return "<próximo>";
    }

}
