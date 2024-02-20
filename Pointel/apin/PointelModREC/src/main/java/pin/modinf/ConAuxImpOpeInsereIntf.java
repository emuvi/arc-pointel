package pin.modinf;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import pin.libetf.TexEtf;
import pin.libutl.Utl;

public class ConAuxImpOpeInsereIntf extends TexEtf {

    private final ConAuxImpOpeInsere operacao;

    public ConAuxImpOpeInsereIntf(ConAuxImpOpeInsere operacao) {
        this.operacao = operacao;
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Operação Insere");
    }

    @Override
    public void especializaIntf() throws Exception {
        super.especializaIntf();
        edt().mudaAoSalvar(new ActAoSalvar());
        edt().mdVlr(operacao.pComando());
    }

    private class ActAoSalvar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                operacao.mComando(edt().pgVlr().pgCrs());
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

}
