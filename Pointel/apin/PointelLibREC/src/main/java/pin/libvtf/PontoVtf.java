package pin.libvtf;

import pin.ldtima.Ponto;
import pin.libamk.Campos;
import pin.libamk.Cmp;
import pin.libbas.Dados;

public class PontoVtf extends ValIntf {

    public PontoVtf() {
        this(new Ponto());
    }

    public PontoVtf(Ponto doPonto) {
        super("Ponto Vtf",
                new Campos(
                        new Cmp(1, 1, "posX", "Posição X", Dados.Tp.Int).mVlrInicial(doPonto.posX),
                        new Cmp(1, 2, "posY", "Posição Y", Dados.Tp.Int).mVlrInicial(doPonto.posY),
                        new Cmp(2, 1, "puxadoX", "Puxado no X", Dados.Tp.Int).mVlrInicial(doPonto.puxadoX),
                        new Cmp(2, 2, "puxadoY", "Puxado no Y", Dados.Tp.Int).mVlrInicial(doPonto.puxadoY)
                )
        );
    }

    @Override
    public Ponto pgVal() throws Exception {
        return new Ponto(
                cmps().pgVlr("posX").pgInt(),
                cmps().pgVlr("posY").pgInt(),
                cmps().pgVlr("puxadoX").pgInt(),
                cmps().pgVlr("puxadoY").pgInt()
        );
    }

}
