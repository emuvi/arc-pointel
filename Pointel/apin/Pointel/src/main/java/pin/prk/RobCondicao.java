package pin.prk;

import java.util.Objects;
import pin.libbas.Retornos;
import pin.libutl.UtlBin;
import pin.libutl.UtlCrs;
import pin.modrec.XMLReg;

public class RobCondicao extends RobAbs {

    public static enum Tp {
        TemImagem, PorVariavel
    };

    public Tp tipo;
    public String fonte;
    public String sim;
    public String nao;

    @Override
    public String executa() throws Exception {
        String retorno = "<próximo>";
        while (true) {
            RobCondicaoAbs cond = (RobCondicaoAbs) XMLReg.novo().pValor(fonte);
            cond.mRobotico(pRobotico());
            String rSim = procCrs(sim);
            String rNao = procCrs(nao);
            Boolean verif = cond.verifica();
            if (verif) {
                retorno = rSim;
            } else {
                retorno = rNao;
            }
            Retornos.bota(pRobotico().pRetornos(), "Condição " + cond.getClass().getSimpleName(), UtlCrs.junta(verif, retorno, rSim, rNao));
            if (!Objects.equals(retorno, "<esperar>")) {
                break;
            } else {
                UtlBin.esperaMilis(100);
            }
        }
        return retorno;
    }

}
