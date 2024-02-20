package pin.prk;

import pin.libbas.Retornos;
import pin.libutl.UtlCrs;
import pin.libutl.UtlRob;

public class RobMouse extends RobAbs {

    public static enum Tp {
        Aperta, Solta, Move, Cliqua
    };

    public Tp tipo;
    public String posX;
    public String posY;
    public String esquerdo;
    public String vezes;

    public RobMouse() {
        tipo = Tp.Aperta;
        posX = null;
        posY = null;
        esquerdo = null;
        vezes = null;
    }

    @Override
    public String executa() throws Exception {
        if (tipo != null) {
            Integer rPosX = procInt(posX);
            Integer rPosY = procInt(posY);
            Boolean rEsquerdo = procLog(esquerdo);
            Integer rVezes = procInt(vezes);
            switch (tipo) {
                case Aperta:
                    UtlRob.aperta(rPosX, rPosY, rEsquerdo);
                    break;
                case Solta:
                    UtlRob.solta(rPosX, rPosY, rEsquerdo);
                    break;
                case Move:
                    UtlRob.move(rPosX, rPosY);
                    break;
                case Cliqua:
                    UtlRob.cliqua(rPosX, rPosY, rVezes, rEsquerdo);
                    break;
            }
            Retornos.bota(pRobotico().pRetornos(), "Mouse " + tipo.toString(), UtlCrs.junta(rPosX, rPosY, rEsquerdo, rVezes));
        } else {
            Retornos.bota(pRobotico().pRetornos(), "Mouse Sem Tipo");
        }
        return "<próximo>";
    }

}
