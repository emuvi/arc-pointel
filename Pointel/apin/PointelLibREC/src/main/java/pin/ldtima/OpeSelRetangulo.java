package pin.ldtima;

import java.awt.Point;
import java.awt.image.BufferedImage;
import pin.libima.RetanguloPontilhado;

public class OpeSelRetangulo extends Operacao {

    public OpeSelRetangulo() {
        super(Tp.Selecionar_Retangulo, true);
    }

    @Override
    public BufferedImage executa(BufferedImage naImagem) throws Exception {
        Point p0 = null;
        Point p1 = null;
        switch (pontos().size()) {
            case 1:
                p0 = pegaPonto().pega();
                p1 = pegaPonto().pegaPuxado();
                break;
            case 2:
                p0 = tiraPonto().pega();
                p1 = tiraPonto().pega();
                break;
            default:
                throw new Exception("Operação Mal Formada");
        }
        return RetanguloPontilhado.faz(naImagem, p1, p1);
    }

}
