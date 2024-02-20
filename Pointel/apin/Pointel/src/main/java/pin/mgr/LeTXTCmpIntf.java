package pin.mgr;

import pin.libamk.Campos;
import pin.libamk.Cmp;
import pin.libamk.Intf;
import pin.libbas.Dados;
import pin.libtex.LeTXTCmp;

public class LeTXTCmpIntf extends Intf {

    public LeTXTCmpIntf() {
        this(new LeTXTCmp());
    }

    public LeTXTCmpIntf(LeTXTCmp comInicial) {
        super(new Campos(
                new Cmp(1, 1, "tipo", "Tipo", Dados.Tp.Enu).botaOpcoes(LeTXTCmp.Tp.class).mVlrInicial(comInicial.tipo),
                new Cmp(1, 2, "ordem", "Ordem", Dados.Tp.Int).mVlrInicial(comInicial.ordem),
                new Cmp(1, 3, "ateofim", "Até o Fim", Dados.Tp.Log).mVlrInicial(comInicial.ateOFim),
                new Cmp(2, 1, "tipo_cts", Dados.Tp.Cts),
                new Cmp("tipo_cts", 1, 1, "fixo", Dados.Tp.Pai),
                new Cmp("tipo_cts", 1, 1, "variavel", Dados.Tp.Pai),
                new Cmp("tipo_cts", 1, 1, "marcado", Dados.Tp.Pai),
                new Cmp("fixo", 1, 1, "tamanho", "Tamanho", Dados.Tp.Int).mVlrInicial(comInicial.tamanho),
                new Cmp("variavel", 1, 1, "separador", "Separador", Dados.Tp.Crs).mVlrInicial(comInicial.separador),
                new Cmp("variavel", 1, 2, "auxiliar", "Auxiliar", Dados.Tp.Cr).mVlrInicial(comInicial.auxiliar),
                new Cmp("marcado", 1, 1, "marcacao", "Marcação", Dados.Tp.Crs).mVlrInicial(comInicial.marcacao),
                new Cmp(3, 1, "entreaspas", "Entre Aspas", Dados.Tp.Log).mVlrInicial(comInicial.entreAspas),
                new Cmp(3, 2, "aspassimples", "Aspas Simples", Dados.Tp.Log).mVlrInicial(comInicial.aspasSimples)
        ));
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Campo para Leitura de TXT");
    }

    @Override
    public void preparaIntf() throws Exception {
        super.preparaIntf();
        vinculaCts("tipo", LeTXTCmp.Tp.Fixo, "fixo", "tipo_cts");
        vinculaCts("tipo", LeTXTCmp.Tp.Separado, "variavel", "tipo_cts");
        vinculaCts("tipo", LeTXTCmp.Tp.Marcado, "marcado", "tipo_cts");
        botaConfirmarECancelar();
    }

    public LeTXTCmp pegaLeTXTCmp() throws Exception {
        LeTXTCmp retorno = new LeTXTCmp();
        retorno.tipo = (LeTXTCmp.Tp) cmps().pgVlr("tipo").pg();
        retorno.ordem = cmps().pgVlr("ordem").pgInt();
        retorno.ateOFim = cmps().pgVlr("ateofim").pgLog();
        retorno.tamanho = cmps().pgVlr("tamanho").pgInt();
        retorno.separador = cmps().pgVlr("separador").pgCrs();
        retorno.auxiliar = cmps().pgVlr("auxiliar").pgCr();
        retorno.marcacao = cmps().pgVlr("marcacao").pgCrs();
        retorno.entreAspas = cmps().pgVlr("entreaspas").pgLog();
        retorno.aspasSimples = cmps().pgVlr("aspassimples").pgLog();
        return retorno;
    }

}
