package pin.prk;

import pin.libbas.Retornos;
import pin.libutl.UtlRob;

public class RobTeclado extends RobAbs {

    public static enum Tp {
        Aperta, Solta, Pressiona, Digita
    }

    public Tp tipo;
    public String parametro;

    public RobTeclado() {
        tipo = Tp.Aperta;
        parametro = null;
    }

    @Override
    public String executa() throws Exception {
        if (tipo != null) {
            String afazer = procCrs(parametro);
            switch (tipo) {
                case Aperta:
                    UtlRob.aperta(afazer);
                    break;
                case Solta:
                    UtlRob.solta(afazer);
                    break;
                case Pressiona:
                    UtlRob.pressiona(afazer);
                    break;
                case Digita:
                    UtlRob.digita(afazer);
                    break;
            }
            Retornos.bota(pRobotico().pRetornos(), "Teclado " + tipo.toString(), afazer);
        } else {
            Retornos.bota(pRobotico().pRetornos(), "Teclado Sem Tipo");
        }
        return "<próximo>";
    }

}
