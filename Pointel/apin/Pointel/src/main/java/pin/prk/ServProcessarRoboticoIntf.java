package pin.prk;

import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.AbstractAction;
import pin.jrb.Serv;
import pin.libamg.Edt;
import pin.libamk.Botao;
import pin.libamk.Campos;
import pin.libamk.Cmp;
import pin.libbas.Dados;
import pin.libetf.LisEtf;
import pin.libutl.Utl;
import pin.libutl.UtlArq;
import pin.libutl.UtlTex;
import pin.modrec.XMLReg;

public class ServProcessarRoboticoIntf extends LisEtf {

    public Edt editor;
    public Campos complemento;
    private File selecionado;

    public ServProcessarRoboticoIntf() {
        this(null, new ServProcessarRobotico());
    }

    public ServProcessarRoboticoIntf(Edt paraEditor) {
        this(paraEditor, new ServProcessarRobotico());
    }

    public ServProcessarRoboticoIntf(ServProcessarRobotico comInicial) {
        this(null, comInicial);
    }

    public ServProcessarRoboticoIntf(Edt paraEditor, ServProcessarRobotico comInicial) {
        editor = paraEditor;
        complemento = new Campos(
                new Cmp(1, 1, "nome", "Nome", Dados.Tp.Crs).mVlrInicial(comInicial.nome),
                new Cmp(1, 2, "mostrador", "Mostrador", Dados.Tp.Log).mVlrInicial(comInicial.mostrador)
        );
        mInicial(comInicial.operacoes);
        selecionado = null;
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Processar Robótico");
    }

    @Override
    public void preparaIntf() throws Exception {
        super.preparaIntf();
        botaBotao(new BotNovo());
        botaBotao(new BotAbrir());
        botaBotao(new BotSalvar());
        botaConfirmarECancelar();
    }

    @Override
    public void especializaIntf() throws Exception {
        super.especializaIntf();
        botaLinha(5, complemento.constroi(this), PreencherTp.Horizontal, 1, 0);
        mAoAdicionar(new ActAdicionar());
        mAoAlterar(new ActAlterar());
    }

    public ServProcessarRobotico pRobotico() throws Exception {
        ServProcessarRobotico robotico = new ServProcessarRobotico();
        robotico.nome = complemento.pgVlr("nome").pgCrs();
        robotico.mostrador = complemento.pgVlr("mostrador").pgLog();
        robotico.operacoes = pgVlrLis().pgLis(RoboticoOpe.class);
        return robotico;
    }

    @Override
    public void aoConfirmar(Object comOrigem) throws Exception {
        ServProcessarRobotico rob = pRobotico();
        if (editor != null) {
            editor.mdVlr(rob);
            editor.botaFoco();
        } else {
            Serv.inicia(rob);
        }
        fecha();
    }

    private class BotNovo extends Botao {

        public BotNovo() {
            super("Novo");
        }

        @Override
        public void aoExecutar(Object comOrigem) throws Exception {
            if (Utl.continua()) {
                complemento.limpa();
                edt().limpa();
            }
        }
    }

    private class BotAbrir extends Botao {

        public BotAbrir() {
            super("Abrir");
        }

        @Override
        public void aoExecutar(Object comOrigem) throws Exception {
            File arq = UtlArq.abreArq(selecionado, "Pointel Robóticos (*.prb)", "prb");
            if (arq != null) {
                selecionado = arq;
                String codigo = UtlTex.abre(selecionado);
                ServProcessarRobotico robotico = (ServProcessarRobotico) XMLReg.novo().pValor(codigo);
                complemento.pEdt("nome").mdVlr(robotico.nome);
                complemento.pEdt("mostrador").mdVlr(robotico.mostrador);
                edt().mdVlr(robotico.operacoes);
            }
        }
    }

    private class BotSalvar extends Botao {

        public BotSalvar() {
            super("Salvar");
        }

        @Override
        public void aoExecutar(Object comOrigem) throws Exception {
            File arq = UtlArq.salvaArq(selecionado, "Pointel Robóticos (*.prb)", "prb");
            if (arq != null) {
                selecionado = arq;
                ServProcessarRobotico robotico = pRobotico();
                UtlTex.salva(XMLReg.novo().pReg(robotico), selecionado);
            }
        }
    }

    private class ActAdicionar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                new RoboticoOpeIntf() {
                    @Override
                    public void aoConfirmar(Object comOrigem) throws Exception {
                        botaItem(pRoboticoOpe());
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
            final int sel = pSelecionado();
            try {
                new RoboticoOpeIntf((RoboticoOpe) pItem()) {
                    @Override
                    public void aoConfirmar(Object comOrigem) throws Exception {
                        mItem(pRoboticoOpe(), sel);
                        fecha();
                    }
                }.mostra();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

}
