package pin.mgr;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Objects;
import javax.swing.AbstractAction;
import pin.libamg.EdtArq;
import pin.libamg.EdtLis;
import pin.libamk.Botao;
import pin.libamk.Campos;
import pin.libamk.Cmp;
import pin.libetf.LisEtf;
import pin.libbas.Dados;
import pin.libbas.Progresso;
import pin.libtex.LeTXT;
import pin.libtex.LeTXTCmp;
import pin.libutl.Utl;
import pin.libutl.UtlArq;
import pin.libutl.UtlCrs;
import pin.libutl.UtlPro;
import pin.libutl.UtlTex;
import pin.libvlr.Vlr;
import pin.libvlr.VCrs;
import pin.libvlr.Vlrs;
import pin.modinf.Bancos;
import pin.modinf.Conexao;
import pin.modrec.XMLReg;
import pin.libbas.Analisado;

public class ImportarTXT extends LisEtf {

    private ConexaoCfg[] conexoes;
    private Campos supCmps;
    private Campos infCmps;
    private EdtLis tabCmpsLis;
    private File selecionado;

    public ImportarTXT(ConexaoCfg[] nasConexoes) {
        this.conexoes = nasConexoes;
        supCmps = new Campos(
                new Cmp(1, 1, "arquivo", "Arquivo", Dados.Tp.Arq).botaParams(EdtArq.Params.SO_CAMINHO, EdtArq.Params.SO_ARQUIVOS, new EdtArq.ParamExtensao(UtlTex.pegaExtensoesDescricao(), UtlTex.pegaExtensoes())),
                new Cmp(1, 2, "codificacao", "Codificação", Dados.Tp.Crs).mVlrInicial("UTF8").mLargura(100),
                new Cmp(1, 3, "tipo", "Tipo", Dados.Tp.Enu).botaOpcoes(LeTXT.Tp.class).mVlrInicial(LeTXT.Tp.Linhas)
        );
        infCmps = new Campos(
                new Cmp(1, 1, "tabela", "Tabela Nome", Dados.Tp.Crs),
                new Cmp(1, 2, "campos", "Campos da Tabela", Dados.Tp.Lis).botaParams(Dados.Tp.Crs),
                new Cmp(2, 1, "pararSeErrar", "Parar Se Errar", Dados.Tp.Log),
                new Cmp(2, 2, "tambemNosTratamentos", "Também nos Tratamentos", Dados.Tp.Log)
        );
        selecionado = null;
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Importar do TXT");
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
        botaLinha(50, supCmps.constroi(this));
        edt().botaTitulo("Campos do TXT");
        mAoAdicionar(new ActTXTAdicionar());
        mAoAlterar(new ActTXTAlterar());
        botaLinha(80, infCmps.constroi(this));
        tabCmpsLis = (EdtLis) infCmps.pega("campos").pEdt();
        tabCmpsLis.mAoAdicionar(new ActCmpAdicionar());
        tabCmpsLis.mAoAlterar(new ActCmpAlterar());
    }

    public ImportarTXTReg pegaReg() throws Exception {
        ImportarTXTReg reg = new ImportarTXTReg();
        reg.arquivo = supCmps.pgVlr("arquivo").pgArqCam();
        reg.codificacao = supCmps.pgVlr("codificacao").pgCrs();
        reg.tipo = (LeTXT.Tp) supCmps.pgVlr("tipo").pg();
        reg.campos = edt().pgVlrLis().pgMat(LeTXTCmp.class);
        reg.tabelaNome = infCmps.pgVlr("tabela").pgCrs();
        reg.tabelaCampos = infCmps.pgVlrLis("campos").pgMat(ImportarTXTCmp.class);
        reg.pararSeErrar = infCmps.pgVlr("pararSeErrar").pgLog();
        reg.tambemNosTratamentos = infCmps.pgVlr("tambemNosTratamentos").pgLog();
        return reg;
    }

    @Override
    public void aoConfirmar(Object comOrigem) throws Exception {
        new ThdExecutar().start();
    }

    private class ActTXTAdicionar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                new LeTXTCmpIntf() {
                    @Override
                    public void aoConfirmar(Object comOrigem) throws Exception {
                        edt().botaItem(pegaLeTXTCmp());
                        fecha();
                    }
                }.mostra();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    private class ActTXTAlterar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int sel = edt().pSelecionado();
                if (sel > -1) {
                    LeTXTCmp inicial = (LeTXTCmp) edt().pItem();
                    new LeTXTCmpIntf(inicial) {
                        @Override
                        public void aoConfirmar(Object comOrigem) throws Exception {
                            edt().mItem(pegaLeTXTCmp(), sel);
                            fecha();
                        }
                    }.mostra();
                }
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    private class ActCmpAdicionar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                new ImportarTXTCmpIntf() {
                    @Override
                    public void aoConfirmar(Object comOrigem) throws Exception {
                        tabCmpsLis.botaItem(pegaImportarTXTCmp());
                        fecha();
                    }
                }.mostra();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    private class ActCmpAlterar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int sel = tabCmpsLis.pSelecionado();
                if (sel > -1) {
                    ImportarTXTCmp inicial = (ImportarTXTCmp) tabCmpsLis.pItem();
                    new ImportarTXTCmpIntf(inicial) {
                        @Override
                        public void aoConfirmar(Object comOrigem) throws Exception {
                            tabCmpsLis.mItem(pegaImportarTXTCmp(), sel);
                            fecha();
                        }
                    }.mostra();
                }
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    private class BotNovo extends Botao {

        public BotNovo() {
            super("Novo");
        }

        @Override
        public void aoExecutar(Object comOrigem) throws Exception {
            if (Utl.continua()) {
                supCmps.pega("arquivo").pEdt().limpa();
                supCmps.pega("codificacao").pEdt().limpa();
                supCmps.pega("tipo").pEdt().limpa();
                edt().limpa();
                infCmps.pega("tabela").pEdt().limpa();
                infCmps.pega("campos").pEdt().limpa();
            }
        }
    }

    private class BotAbrir extends Botao {

        public BotAbrir() {
            super("Abrir");
        }

        @Override
        public void aoExecutar(Object comOrigem) throws Exception {
            selecionado = UtlArq.abreArq(selecionado, "Pointel ImportarTXT (*.pit)", "pit");
            if (selecionado == null) {
                return;
            }
            String txtReg = UtlTex.abre(selecionado);
            ImportarTXTReg reg = (ImportarTXTReg) XMLReg.novo().pValor(txtReg);
            supCmps.pega("arquivo").pEdt().mdVlr(reg.arquivo);
            supCmps.pega("codificacao").pEdt().mdVlr(reg.codificacao);
            supCmps.pega("tipo").pEdt().mdVlr(reg.tipo);
            edt().mdVlr(reg.campos);
            infCmps.pega("tabela").pEdt().mdVlr(reg.tabelaNome);
            infCmps.pega("campos").pEdt().mdVlr(reg.tabelaCampos);
            infCmps.pega("pararSeErrar").pEdt().mdVlr(reg.pararSeErrar);
            infCmps.pega("tambemNosTratamentos").pEdt().mdVlr(reg.tambemNosTratamentos);
        }
    }

    private class BotSalvar extends Botao {

        public BotSalvar() {
            super("Salvar");
        }

        @Override
        public void aoExecutar(Object comOrigem) throws Exception {
            selecionado = UtlArq.salvaArq(selecionado, "Pointel ImportarTXT (*.pit)", "pit");
            if (selecionado == null) {
                return;
            }
            ImportarTXTReg reg = pegaReg();
            String txtReg = XMLReg.novo().pReg(reg);
            UtlTex.salva(txtReg, selecionado);
        }
    }

    private class ThdExecutar extends Thread {

        public ThdExecutar() {
            super("Importar TXT");
        }

        @Override
        public void run() {
            Progresso prog = new Progresso("Importando TXT");
            prog.abre();
            try {
                ImportarTXTReg reg = pegaReg();
                prog.bota("Realizando Conexões");
                Conexao[] cons = new Conexao[conexoes.length];
                for (int ic = 0; ic < conexoes.length; ic++) {
                    String nome = conexoes[ic].nome;
                    Bancos.Tp tipo = Bancos.Tp.valueOf(conexoes[ic].banco);
                    String servidor = conexoes[ic].servidor;
                    Integer porta = conexoes[ic].porta;
                    String base = conexoes[ic].base;
                    String usuario = conexoes[ic].usuario;
                    String senha = conexoes[ic].senha;
                    cons[ic] = new Conexao(nome, tipo, servidor, porta, base, usuario, senha);
                    cons[ic].conecta();
                }
                prog.bota("Conexões Feitas");
                String ins = "";
                for (ImportarTXTCmp tabCm : reg.tabelaCampos) {
                    ins = UtlCrs.soma(ins, ", ", tabCm.nome);
                }
                ins = "INSERT INTO " + reg.tabelaNome + " (" + ins + ") VALUES (";
                ins += new VCrs().repete("?, ", reg.tabelaCampos.length).corta(2) + ")";
                final String insrt = ins;
                prog.bota("Comando Montado: " + insrt);
                LeTXT leTXT = new LeTXT(reg.arquivo, reg.codificacao, reg.tipo, reg.campos);
                Analisado<String[], Boolean> analisador = new Analisado<String[], Boolean>() {
                    private Boolean primeiro = true;

                    @Override
                    public Boolean analisa(String[] osValores) {
                        try {
                            if (prog.pausarEdeveParar()) {
                                return false;
                            }
                            if (primeiro) {
                                primeiro = false;
                                prog.mudaTamanho(leTXT.leitor().pegaTamanho());
                            }
                            prog.mudaProgresso(leTXT.leitor().pegaProgresso());
                            Vlrs params = new Vlrs();
                            for (ImportarTXTCmp cmp : reg.tabelaCampos) {
                                Object valor = null;
                                if (Objects.equals(cmp.tipo, ImportarTXTCmp.Tp.Importado)) {
                                    Dados.Tp tipo = Dados.pTipoEnu(cmp.impTipo);
                                    valor = Dados.pegaVlr(tipo, osValores[cmp.impIndice]);
                                } else if (Objects.equals(cmp.tipo, ImportarTXTCmp.Tp.Fixo)) {
                                    valor = cmp.fixValor;
                                } else if (Objects.equals(cmp.tipo, ImportarTXTCmp.Tp.Roteiro)) {
                                    valor = UtlPro.roteiro(cmp.rotCodigo, osValores);
                                }
                                if (cmp.tratamentos != null) {
                                    for (Tratamento trat : cmp.tratamentos) {
                                        try {
                                            valor = trat.executa(valor);
                                        } catch (Exception ex) {
                                            prog.bota(ex);
                                            if (reg.pararSeErrar && reg.tambemNosTratamentos) {
                                                return false;
                                            }
                                        }
                                    }
                                }
                                params.bota(Vlr.novo(valor));
                            }
                            for (Conexao con : cons) {
                                con.opera(insrt, params);
                                if (params.tem()) {
                                    prog.bota("Inserido na Conexão " + con.pegaNome() + " Primeiro: " + params.pegaPrimeirosCrs(1));
                                } else {
                                    prog.bota("Inserido na Conexão " + con.pegaNome());
                                }
                            }
                        } catch (Exception ex) {
                            prog.bota(ex);
                            if (reg.pararSeErrar) {
                                return false;
                            }
                        }
                        return true;
                    }
                };
                leTXT.botaAnalisador(analisador);
                leTXT.botaAoInterromper(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        prog.bota("Interrompido");
                    }
                });
                leTXT.botaAoTerminar(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        prog.termina();
                    }
                });
                leTXT.inicia();
            } catch (Exception ex) {
                prog.bota(ex);
            }
        }
    }

}
