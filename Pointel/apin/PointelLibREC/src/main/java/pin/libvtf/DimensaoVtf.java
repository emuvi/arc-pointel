package pin.libvtf;

import java.awt.Dimension;
import pin.libamk.Campos;
import pin.libamk.Cmp;
import pin.libbas.Dados;

public class DimensaoVtf extends ValIntf {

    public DimensaoVtf() {
        this(new Dimension());
    }

    public DimensaoVtf(Integer comLargura, Integer eAltura) {
        this(new Dimension(comLargura, eAltura));
    }

    public DimensaoVtf(Dimension naPosicao) {
        super("Dimensão Vtf",
                new Campos(
                        new Cmp(1, 1, "largura", "Largura", Dados.Tp.Int).mVlrInicial(naPosicao.width),
                        new Cmp(1, 2, "altura", "Altura", Dados.Tp.Int).mVlrInicial(naPosicao.height)
                )
        );
    }

    @Override
    public Dimension pgVal() throws Exception {
        return new Dimension(
                cmps().pgVlr("largura").pgInt(),
                cmps().pgVlr("altura").pgInt()
        );
    }

}
