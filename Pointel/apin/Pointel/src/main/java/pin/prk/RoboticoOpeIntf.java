package pin.prk;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import pin.jrb.Serv;
import pin.jrb.ServComando;
import pin.libamg.Edt;
import pin.libamk.Botao;
import pin.libamk.Campos;
import pin.libamk.Cmp;
import pin.libamk.ComandosIntf;
import pin.libamk.Intf;
import pin.libamk.RoteirosIntf;
import pin.libbas.Conferencia;
import pin.libbas.Dados;
import pin.libbas.Erro;
import pin.libutl.Utl;
import pin.modrec.XMLReg;

public class RoboticoOpeIntf extends Intf {

    public RoboticoOpeIntf() {
        this(new RoboticoOpe());
    }

    public RoboticoOpeIntf(RoboticoOpe operacao) {
        super(new Campos(
                new Cmp(1, 1, "nome", "Nome", Dados.Tp.Crs).mVlrInicial(operacao.nome),
                new Cmp(1, 2, "tipo", "Tipo", Dados.Tp.Enu).botaOpcoes(RoboticoOpe.Tp.class).mVlrInicial(operacao.tipo),
                new Cmp(2, 1, "fonte", "Fonte", Dados.Tp.Tex).mVlrInicial(operacao.fonte),
                new Cmp(3, 1, "comentario", "Comentário", Dados.Tp.Tex).mVlrInicial(operacao.comentario)
        ));
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Robótico Operação");
    }

    @Override
    public void preparaIntf() throws Exception {
        super.preparaIntf();
        cmps().pega("nome").botaConferencia(new CnfNome());
        botaBotao(new BotEditar());
        botaBotao(new BotTestar());
        botaConfirmarECancelar();
    }

    public RoboticoOpe pRoboticoOpe() throws Exception {
        RoboticoOpe retorno = new RoboticoOpe();
        retorno.nome = cmps().pgVlr("nome").pgCrs();
        retorno.tipo = (RoboticoOpe.Tp) cmps().pgVlr("tipo").pg();
        retorno.fonte = cmps().pgVlr("fonte").pgCrs();
        retorno.comentario = cmps().pgVlr("comentario").pgCrs();
        return retorno;
    }

    private class CnfNome extends Conferencia {

        @Override
        public void confere(Object comOrigem) throws Exception {
            String nome = cmps().pgVlr("nome").pgCrs();
            if (("<próximo>".equals(nome) || "<anterior>".equals(nome) || "<último>".equals(nome) || "<primeiro>".equals(nome) || "<termina>".equals(nome))) {
                throw new Erro("Nome Inválido");
            }
        }
    }

    private class BotEditar extends Botao {

        public BotEditar() {
            super("Editar");
        }

        @Override
        public void aoExecutar(Object comOrigem) throws Exception {
            String fnt = cmps().pgVlr("fonte").pgCrs("");
            Edt editor = cmps().pega("fonte").pEdt();
            switch (cmps().pgVlr("tipo").pgCrs()) {
                case "Mouse":
                    RobMouse mos = null;
                    if (!fnt.isEmpty()) {
                        mos = (RobMouse) XMLReg.novo().pValor(fnt);
                    }
                    if (mos == null) {
                        mos = new RobMouse();
                    }
                    new RobMouseIntf(editor, mos).mostra();
                    break;
                case "Teclado":
                    RobTeclado tec = null;
                    if (!fnt.isEmpty()) {
                        tec = (RobTeclado) XMLReg.novo().pValor(fnt);
                    }
                    if (tec == null) {
                        tec = new RobTeclado();
                    }
                    new RobTecladoIntf(editor, tec).mostra();
                    break;
                case "Roteiro":
                    RobRoteiro rot = null;
                    if (!fnt.isEmpty()) {
                        rot = (RobRoteiro) XMLReg.novo().pValor(fnt);
                    }
                    if (rot == null) {
                        rot = new RobRoteiro();
                    }
                    RoteirosIntf roe = new RoteirosIntf();
                    roe.mPrioridade(rot.prioridade);
                    roe.mCodigo(rot.codigo);
                    roe.mAoSalvar(new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                RobRoteiro sro = new RobRoteiro();
                                sro.prioridade = roe.pPrioridade();
                                sro.codigo = roe.pCodigo();
                                editor.mdVlr(XMLReg.novo().pReg(sro));
                                editor.botaFoco();
                                roe.fecha();
                            } catch (Exception ex) {
                                Utl.registra(ex);
                            }
                        }
                    });
                    roe.mostra();
                    break;
                case "Comando":
                    ServComando servCmd = null;
                    if (!fnt.isEmpty()) {
                        servCmd = (ServComando) XMLReg.novo().pValor(fnt);
                    }
                    if (servCmd == null) {
                        servCmd = new ServComando();
                    }
                    ComandosIntf cmd = new ComandosIntf();
                    cmd.mostra();
                    cmd.cmps().pega("nome").pEdt().mdVlr(servCmd.nome);
                    cmd.cmps().pega("comando").pEdt().mdVlr(servCmd.comando);
                    cmd.cmps().pega("mostrador").pEdt().mdVlr(servCmd.mostrador);
                    cmd.cmps().pega("argumentos").pEdt().mdVlr(servCmd.argumentos);
                    cmd.cmps().pega("variaveis").pEdt().mdVlr(servCmd.variaveis);
                    cmd.cmps().pega("diretorio").pEdt().mdVlr(servCmd.diretorio);
                    cmd.mudaAoConfirmar(new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                ServComando scmd = new ServComando();
                                scmd.nome = cmd.cmps().pgVlr("nome").pgCrs();
                                scmd.comando = cmd.cmps().pgVlr("comando").pgCrs();
                                scmd.mostrador = cmd.cmps().pgVlr("mostrador").pgLog();
                                scmd.argumentos = cmd.cmps().pgVlrLis("argumentos").pgMatCrs();
                                scmd.variaveis = cmd.cmps().pgVlrLis("variaveis").pgMatCrs();
                                scmd.diretorio = cmd.cmps().pgVlr("diretorio").pgArqCam();
                                editor.mdVlr(XMLReg.novo().pReg(scmd));
                                cmd.fecha();
                                editor.botaFoco();
                            } catch (Exception ex) {
                                Utl.registra(ex);
                            }
                        }
                    });
                    break;
                case "Espera":
                    RobEspera esp = null;
                    if (!fnt.isEmpty()) {
                        esp = (RobEspera) XMLReg.novo().pValor(fnt);
                    }
                    if (esp == null) {
                        esp = new RobEspera();
                    }
                    new RobEsperaIntf(editor, esp).mostra();
                    break;
                case "Condicao":
                    RobCondicao cnd = null;
                    if (!fnt.isEmpty()) {
                        cnd = (RobCondicao) XMLReg.novo().pValor(fnt);
                    }
                    if (cnd == null) {
                        cnd = new RobCondicao();
                    }
                    new RobCondicaoIntf(editor, cnd).mostra();
                    break;
            }
        }
    }

    private class BotTestar extends Botao {

        public BotTestar() {
            super("Testar");
        }

        @Override
        public void aoExecutar(Object comOrigem) throws Exception {
            ServProcessarRobotico rob = new ServProcessarRobotico();
            rob.nome = "Testando Operação";
            rob.operacoes.add(pRoboticoOpe());
            Serv.inicia(rob);
        }
    }

}
