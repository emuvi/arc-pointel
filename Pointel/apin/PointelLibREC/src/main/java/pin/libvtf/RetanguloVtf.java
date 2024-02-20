package pin.libvtf;

import java.awt.Rectangle;
import pin.libamk.Campos;
import pin.libamk.Cmp;
import pin.libamk.Intf;
import pin.libbas.Dados;

public class RetanguloVtf extends Intf {

    public RetanguloVtf() {
        this(new Rectangle());
    }

    public RetanguloVtf(Rectangle comRetangulo) {
        super("Retângulo", new Campos(
                new Cmp(1, 1, "posx", "Pos. X", Dados.Tp.Int).mVlrInicial(comRetangulo.x),
                new Cmp(1, 2, "posy", "Pos. Y", Dados.Tp.Int).mVlrInicial(comRetangulo.y),
                new Cmp(2, 1, "largura", "Largura", Dados.Tp.Int).mVlrInicial(comRetangulo.width),
                new Cmp(2, 2, "altura", "Altura", Dados.Tp.Int).mVlrInicial(comRetangulo.height)
        ));
    }

    @Override
    public void preparaIntf() throws Exception {
        super.preparaIntf();
        botaConfirmarECancelar();
    }

    public Rectangle pegaRetangulo() throws Exception {
        Rectangle retorno = new Rectangle();
        retorno.x = cmps().pgVlr("posx").pgInt();
        retorno.y = cmps().pgVlr("posy").pgInt();
        retorno.width = cmps().pgVlr("largura").pgInt();
        retorno.height = cmps().pgVlr("altura").pgInt();
        return retorno;
    }

}
