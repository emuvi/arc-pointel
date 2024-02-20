package pin.mgr;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import pin.libamk.Campos;
import pin.libamk.Cmp;
import pin.libamk.Intf;
import pin.libbas.Dados;
import pin.libutl.Utl;

public class ImportarTXTCmpIntf extends Intf {

    private Tratamento[] tratamentos;

    public ImportarTXTCmpIntf() {
        this(new ImportarTXTCmp());
    }

    public ImportarTXTCmpIntf(ImportarTXTCmp comInicial) {
        super(new Campos(
                        new Cmp(1, 1, "nome", "Nome", Dados.Tp.Crs).mVlrInicial(comInicial.nome),
                        new Cmp(1, 2, "tipo", "Tipo", Dados.Tp.Enu).botaOpcoes(ImportarTXTCmp.Tp.class).mVlrInicial(comInicial.tipo),
                        new Cmp(1, 3, "tratamentos", "Tratamentos", Dados.Tp.Bot),
                        new Cmp(2, 1, "tipo_cts", Dados.Tp.Cts),
                        new Cmp("tipo_cts", 1, 1, "importado", Dados.Tp.Pai),
                        new Cmp("tipo_cts", 1, 1, "fixo", Dados.Tp.Pai),
                        new Cmp("tipo_cts", 1, 1, "roteiro", Dados.Tp.Pai),
                        new Cmp("importado", 1, 1, "impIndice", "Indice", Dados.Tp.Int).mVlrInicial(comInicial.impIndice),
                        new Cmp("importado", 1, 2, "impTipo", "Tipo", Dados.Tp.Enu).botaOpcoes(Dados.pTiposValorEnu()).mVlrInicial(comInicial.impTipo),
                        new Cmp("fixo", 1, 1, "fixValor", "Valor", Dados.Tp.Ind).mVlrInicial(comInicial.fixValor),
                        new Cmp("roteiro", 1, 1, "rotCodigo", "Código", Dados.Tp.Tex).mVlrInicial(comInicial.rotCodigo)
                )
        );
        tratamentos = comInicial.tratamentos;
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Campo Onde Importar");
    }

    @Override
    public void preparaIntf() throws Exception {
        super.preparaIntf();
        vinculaCts("tipo", ImportarTXTCmp.Tp.Importado, "importado", "tipo_cts");
        vinculaCts("tipo", ImportarTXTCmp.Tp.Fixo, "fixo", "tipo_cts");
        vinculaCts("tipo", ImportarTXTCmp.Tp.Roteiro, "roteiro", "tipo_cts");
        botaConfirmarECancelar();
    }

    @Override
    public void especializaIntf() throws Exception {
        super.especializaIntf();
        cmps().pega("tratamentos").pEdt().botaAcao(new ActTratamentos());
    }

    public ImportarTXTCmp pegaImportarTXTCmp() throws Exception {
        ImportarTXTCmp retorno = new ImportarTXTCmp();
        retorno.nome = cmps().pgVlr("nome").pgCrs();
        retorno.tipo = (ImportarTXTCmp.Tp) cmps().pgVlr("tipo").pg();
        retorno.impIndice = cmps().pgVlr("impIndice").pgInt();
        retorno.impTipo = cmps().pgVlr("impTipo").pgCrs();
        retorno.fixValor = cmps().pgVlr("fixValor");
        retorno.rotCodigo = cmps().pgVlr("rotCodigo").pgCrs();
        retorno.tratamentos = tratamentos;
        return retorno;
    }

    private class ActTratamentos extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                new TratamentosIntf(tratamentos) {
                    @Override
                    public void aoConfirmar(Object comOrigem) throws Exception {
                        tratamentos = pegaTratamentos();
                        fecha();
                    }
                }.mostra();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

}
