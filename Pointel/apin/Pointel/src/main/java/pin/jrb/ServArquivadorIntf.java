package pin.jrb;

import pin.libamg.Edt;
import pin.libamk.Botao;
import pin.libamk.Campos;
import pin.libamk.Cmp;
import pin.libamk.Intf;
import pin.libbas.Dados;
import pin.libutl.UtlTem;

public class ServArquivadorIntf extends Intf {

    public Edt editor;

    public ServArquivadorIntf() throws Exception {
        this(null, new ServArquivador());
    }

    public ServArquivadorIntf(Edt paraEditor) throws Exception {
        this(paraEditor, new ServArquivador());
    }

    public ServArquivadorIntf(Edt paraEditor, ServArquivador comInicial) throws Exception {
        super(new Campos(
                new Cmp(1, 1, "conexao", "Conexão", Dados.Tp.Enu).mTamanho(1).botaOpcoes("(P) Principal", "(J) PointelJarbs").mVlrInicial(comInicial.conexao),
                new Cmp(1, 2, "conjunto", "Conjunto", Dados.Tp.Enu).mTamanho(1).botaOpcoes("(A) Auditoria", "(I) Integral").mVlrInicial(comInicial.conjunto),
                new Cmp(1, 3, "anterior", "Anterior À", Dados.Tp.Int).mVlrInicial(comInicial.anterior),
                new Cmp(1, 4, "medida", "Medida", Dados.Tp.Enu).mTamanho(2).botaOpcoes(UtlTem.Temporidade.pegaOpcoes()).mVlrInicial(comInicial.medida),
                new Cmp(1, 5, "excluir", "Excluir", Dados.Tp.Log).mVlrInicial(comInicial.excluir),
                new Cmp(2, 1, "destino", "Destino", Dados.Tp.Crs).mVlrInicial(comInicial.destino),
                new Cmp(2, 2, "base", "Base", Dados.Tp.Crs).mVlrInicial(comInicial.base).mLargura(120),
                new Cmp(2, 3, "porta", "Porta", Dados.Tp.Int).mVlrInicial(comInicial.porta),
                new Cmp(2, 4, "usuario", "Usuário", Dados.Tp.Crs).mVlrInicial(comInicial.usuario).mLargura(120),
                new Cmp(2, 5, "senha", "Senha", Dados.Tp.Sen).mVlrInicial(comInicial.senha).mLargura(120),
                new Cmp(3, 1, "velocidade", "Velocidade", Dados.Tp.Int).mVlrInicial(comInicial.velocidade),
                new Cmp(3, 2, "retornos", "Retornos", Dados.Tp.Log).mVlrInicial(comInicial.retornos),
                new Cmp(3, 3, "mostrador", "Mostrador", Dados.Tp.Log).mVlrInicial(comInicial.mostrador)
        ));
        editor = paraEditor;
    }

    @Override
    public void preparaEstr() {
        mTitulo("Arquivador");
    }

    @Override
    public void preparaIntf() throws Exception {
        botaBotao(new BotConfimar());
    }

    private class BotConfimar extends Botao {

        public BotConfimar() {
            super("Confirmar");
        }

        @Override
        public void aoExecutar(Object comOrigem) throws Exception {
            ServArquivador sar = new ServArquivador();
            sar.conexao = cmps().pgVlr("conexao").pgCrs();
            sar.conjunto = cmps().pgVlr("conjunto").pgCrs();
            sar.anterior = cmps().pgVlr("anterior").pgInt();
            sar.medida = cmps().pgVlr("medida").pgCrs();
            sar.excluir = cmps().pgVlr("excluir").pgLog();
            sar.destino = cmps().pgVlr("destino").pgCrs();
            sar.base = cmps().pgVlr("base").pgCrs();
            sar.porta = cmps().pgVlr("porta").pgInt();
            sar.usuario = cmps().pgVlr("usuario").pgCrs();
            sar.senha = cmps().pgVlr("senha").pgCrs();
            sar.velocidade = cmps().pgVlr("velocidade").pgInt();
            sar.retornos = cmps().pgVlr("retornos").pgLog();
            sar.mostrador = cmps().pgVlr("mostrador").pgLog();
            if (editor != null) {
                editor.mdVlr(sar.descreve());
                editor.botaFoco();
            } else {
                Serv.inicia(sar);
            }
        }

    }

}
