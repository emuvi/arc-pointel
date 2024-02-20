package pin.mgr;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import pin.libetf.LisEtf;
import pin.libutl.Utl;

public class TratamentosIntf extends LisEtf {

    public TratamentosIntf() {
        super();
    }

    public TratamentosIntf(Tratamento[] comTratamentos) {
        super();
        mInicial(comTratamentos);
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Tratamentos");
    }

    @Override
    public void preparaIntf() throws Exception {
        super.preparaIntf();
        botaConfirmarECancelar();
    }

    @Override
    public void especializaIntf() throws Exception {
        super.especializaIntf();
        mAoAdicionar(new ActAdicionar());
        mAoAlterar(new ActAlterar());
    }

    public Tratamento[] pegaTratamentos() throws Exception {
        return edt().pgVlrLis().pgMat(Tratamento.class);
    }

    private class ActAdicionar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                new TratamentoIntf() {
                    @Override
                    public void aoConfirmar(Object comOrigem) throws Exception {
                        botaItem(pegaTratamento());
                        fecha();
                    }
                }.mostra();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    private class ActAlterar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int sel = pSelecionado();
                if (sel > -1) {
                    new TratamentoIntf((Tratamento) pItem()) {
                        @Override
                        public void aoConfirmar(Object comOrigem) throws Exception {
                            mItem(pegaTratamento(), sel);
                            fecha();
                        }

                    }.mostra();
                }
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

}
