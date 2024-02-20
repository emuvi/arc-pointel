package pin.libvtf;

import java.awt.Color;
import pin.libamk.Botao;
import pin.libamk.Campos;
import pin.libamk.Cmp;
import pin.libamk.Intf;
import pin.libbas.Dados;
import pin.libfor.Contorno;
import pin.libfor.Fundo;

public class ContornoVtf extends Intf {

    private Contorno valor;
    private Boolean anular;

    public ContornoVtf() {
        this(new Contorno());
    }

    public ContornoVtf(Contorno doContorno) {
        super(new Campos(
                new Cmp(1, 1, "largura", "Largura", Dados.Tp.Num).mVlrInicial(doContorno.pLargura()),
                new Cmp(1, 2, "decoracao", "Decoração", Dados.Tp.Enu).botaOpcoes(Contorno.Decoracao.class).mVlrInicial(doContorno.pDecoracao()),
                new Cmp(1, 3, "uniao", "União", Dados.Tp.Enu).botaOpcoes(Contorno.Uniao.class).mVlrInicial(doContorno.pUniao()),
                new Cmp(2, 1, "traco", "Traço", Dados.Tp.Lis).botaParams(Dados.Tp.Num).mVlrInicial(doContorno.pTraco()),
                new Cmp(2, 2, "limite", "Limite", Dados.Tp.Num).mVlrInicial(doContorno.pLimite()),
                new Cmp(2, 3, "fase", "Fase", Dados.Tp.Num).mVlrInicial(doContorno.pFase())
        ));
        valor = doContorno;
        anular = false;
    }

    public Contorno pgVal() throws Exception {
        if (anular) {
            return null;
        }
        Float largura = cmps().pega("largura").pEdt().pgVlr().pgNum();
        Contorno.Decoracao decoracao = (Contorno.Decoracao) cmps().pega("decoracao").pEdt().pgVlr().pg();
        Contorno.Uniao uniao = (Contorno.Uniao) cmps().pega("uniao").pEdt().pgVlr().pg();
        Float[] traco = cmps().pega("traco").pEdt().pgVlrLis().pgMat(Float.class);
        Float limite = cmps().pega("limite").pEdt().pgVlr().pgNum();
        Float fase = cmps().pega("fase").pEdt().pgVlr().pgNum();
        return valor.muda(largura, decoracao, uniao, limite, traco, fase);
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Contorno");
    }

    @Override
    public void preparaIntf() throws Exception {
        super.preparaIntf();
        botaBotao(new BotFundo());
        botaConfirmarECancelar();
        botaBotao(new BotAnular());
    }

    private class BotFundo extends Botao {

        public BotFundo() {
            super("Fundo");
        }

        @Override
        public void aoExecutar(Object comOrigem) throws Exception {
            Fundo pr = valor.pFundo();
            if (pr == null) {
                pr = new Fundo(Color.BLACK);
            }
            new FundoVtf(pr) {
                @Override
                public void aoConfirmar(Object comOrigem) throws Exception {
                    valor.mFundo(pFundo());
                    fecha();
                }
            }.mostra();
        }
    }

    private class BotAnular extends Botao {

        public BotAnular() {
            super("Anular");
        }

        @Override
        public void aoExecutar(Object comOrigem) throws Exception {
            anular = true;
            confirma(comOrigem);
        }
    }

}
