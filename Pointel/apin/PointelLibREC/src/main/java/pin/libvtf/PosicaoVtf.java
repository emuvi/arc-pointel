package pin.libvtf;

import java.awt.Point;
import pin.libamk.Campos;
import pin.libamk.Cmp;
import pin.libbas.Dados;

public class PosicaoVtf extends ValIntf {

    public PosicaoVtf() {
        this(new Point());
    }

    public PosicaoVtf(Integer comPosX, Integer ePosY) {
        this(new Point(comPosX, ePosY));
    }

    public PosicaoVtf(Point naPosicao) {
        super("Posição Vtf",
                new Campos(
                        new Cmp(1, 1, "posx", "Pos. X", Dados.Tp.Int).mVlrInicial(naPosicao.x),
                        new Cmp(1, 2, "posy", "Pos. Y", Dados.Tp.Int).mVlrInicial(naPosicao.y)
                )
        );
    }

    @Override
    public Point pgVal() throws Exception {
        return new Point(
                cmps().pgVlr("posx").pgInt(),
                cmps().pgVlr("posy").pgInt()
        );
    }

}
