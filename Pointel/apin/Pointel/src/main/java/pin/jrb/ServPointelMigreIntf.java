package pin.jrb;

import pin.libamg.Edt;
import pin.libamg.EdtArq;
import pin.libamk.Botao;
import pin.libamk.Campos;
import pin.libamk.Cmp;
import pin.libamk.Intf;
import pin.libbas.Dados;

public class ServPointelMigreIntf extends Intf {

    public Edt editor;

    public ServPointelMigreIntf(Edt paraEditor, ServPointelMigre comInicial) throws Exception {
        super(new Campos(
                new Cmp(1, 1, "nome", "Nome", Dados.Tp.Crs).mVlrInicial(comInicial.nome),
                new Cmp(1, 2, "migracao", "Migração", Dados.Tp.Arq).botaParams(EdtArq.Params.SO_CAMINHO, EdtArq.Params.SO_ARQUIVOS).mVlrInicial(comInicial.migracao),
                new Cmp(2, 1, "operacao", "Operação", Dados.Tp.Enu).botaOpcoes("Executar Migração", "Backup da Conexão", "Backup de Todas", "Restore da Conexão", "Restore de Todas").mVlrInicial(comInicial.operacao),
                new Cmp(2, 2, "parametro", "Parâmetro", Dados.Tp.Crs).mVlrInicial(comInicial.parametro),
                new Cmp(2, 3, "mostrador", "Mostrador", Dados.Tp.Log).mVlrInicial(comInicial.mostrador)
        ));
        editor = paraEditor;
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Serviço PointelMigre");
    }

    @Override
    public void preparaIntf() throws Exception {
        super.preparaIntf();
        botaBotao(new BotConfimar());
    }

    private class BotConfimar extends Botao {

        public BotConfimar() {
            super("Confirmar");
        }

        @Override
        public void aoExecutar(Object comOrigem) throws Exception {
            ServPointelMigre spm = new ServPointelMigre();
            spm.mNome(cmps().pgVlr("nome").pgCrs());
            spm.migracao = cmps().pgVlr("migracao").pgArqCam();
            spm.operacao = cmps().pgVlr("operacao").pgCrs();
            spm.parametro = cmps().pgVlr("parametro").pgCrs();
            spm.mostrador = cmps().pgVlr("mostrador").pgLog();
            editor.mdVlr(spm.descreve());
            editor.botaFoco();
        }

    }

}
