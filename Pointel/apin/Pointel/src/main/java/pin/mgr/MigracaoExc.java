package pin.mgr;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.AbstractAction;
import pin.libbas.Conjunto;
import pin.libbas.Operacao;
import pin.libbas.Paralelo;
import pin.libbas.Retornos;
import pin.libutl.Utl;
import pin.libutl.UtlCrs;
import pin.libutl.UtlTex;
import pin.libvlr.Vlrs;
import pin.modinf.Conexao;
import pin.modrec.XMLReg;

public class MigracaoExc extends Paralelo {

    private Migracao migracao;
    private Boolean mostrador;
    private Boolean salvaAoTerminar;
    private AbstractAction aoTerminar;
    private XMLReg xml;
    private Conexao[] conexoes;

    public MigracaoExc(Migracao daMigracao) {
        this(daMigracao, null, null);
    }

    public MigracaoExc(Migracao daMigracao, Retornos comRetornos) {
        this(daMigracao, comRetornos, true);
    }

    public MigracaoExc(Migracao daMigracao, Boolean comMostrador) {
        this(daMigracao, null, comMostrador);
    }

    public MigracaoExc(Migracao daMigracao, Retornos eRetornos, Boolean comMostrador) {
        super("Migração Executa");
        migracao = daMigracao;
        mostrador = comMostrador;
        if (eRetornos != null) {
            mudaRetornos(eRetornos);
        }
        if (mostrador) {
            retornos().mostrador("Migração Execução");
        }
        xml = XMLReg.novo();
        salvaAoTerminar = false;
    }

    public MigracaoExc salvaAoTerminar() {
        salvaAoTerminar = true;
        return this;
    }

    public MigracaoExc botaAoTerminar(AbstractAction aAcao) {
        aoTerminar = aAcao;
        return this;
    }

    @Override
    public void run() {
        try {
            conexoes = new Conexao[migracao.conexoes.size()];
            for (int i1 = 0; i1 < conexoes.length; i1++) {
                ConexaoCfg conCfg = migracao.conexoes.get(i1);
                botaMsg("Estabelecendo Conexão " + conCfg.nome);
                conexoes[i1] = conCfg.conecta();
                if (conexoes[i1].estaConectado()) {
                    botaMsg("Conexão " + conexoes[i1].pegaNome() + " Conectada");
                } else {
                    throw new Exception("Não Foi Possível Conectar a Conexão " + conexoes[i1].pegaNome());
                }
                if (pausarEdeveParar()) {
                    termina();
                    return;
                }
            }
            for (int i1 = 0; i1 < migracao.operacoes.size(); i1++) {
                try {
                    if (pausarEdeveParar()) {
                        termina();
                        return;
                    }
                    Operacao oper = migracao.operacoes.get(i1);
                    botaMsg("Operação: " + oper.nome + " do Tipo: " + oper.tipo + " iniciando...");
                    if (oper.tipo.equals(Executar.pTipo())) {
                        try {
                            Executar exec = (Executar) xml.pValor(oper.codigo);
                            Conexao con = pegaConexao(exec.conexao);
                            if (con == null) {
                                throw new Exception("Conexão " + exec.conexao + " não encontrada");
                            }
                            botaMsg("Conexão " + exec.conexao + " encontrada");
                            String[] cmds = pegaComandos(exec.comandos);
                            botaMsg("Executando " + cmds.length + " comando(s)");
                            for (String cmd : cmds) {
                                String cmdNm = cmd;
                                if (cmd.contains(" ")) {
                                    cmdNm = cmd.split(" ")[0];
                                }
                                cmdNm += " da Operação " + oper.nome;
                                try {
                                    int ret = con.opera(cmd);
                                    botaMsg("Executado o comando :\n" + cmdNm + "\nAfetando " + ret + " registros");
                                } catch (Exception ex) {
                                    botaErro(exec.pausarSeErrar, ex);
                                }
                            }
                        } catch (Exception ex) {
                            botaErro(true, ex);
                        }
                    } else if (oper.tipo.equals(ExecutarParaCada.pTipo())) {
                        try {
                            ExecutarParaCada execCada = (ExecutarParaCada) xml.pValor(oper.codigo);
                            Conexao conSel = pegaConexao(execCada.conexaoSelecao);
                            Conexao conCmd = pegaConexao(execCada.conexaoComandos);
                            if (conSel == null) {
                                throw new Exception("Conexão de Seleção " + execCada.conexaoSelecao + " não encontrada");
                            }
                            if (conCmd == null) {
                                throw new Exception("Conexão de Execução " + execCada.conexaoComandos + " não encontrada");
                            }
                            botaMsg("Conexão de Seleção " + execCada.conexaoSelecao + " encontrada");
                            botaMsg("Conexão de Execução " + execCada.conexaoComandos + " encontrada");
                            botaMsg("Abrindo Seleção:\n" + execCada.selecao);
                            try {
                                Conjunto rstCd = conSel.busca(execCada.selecao);
                                botaMsg("Seleção Aberta");
                                String[] cmds = pegaComandos(execCada.comandos);
                                botaMsg("Executando " + cmds.length + " comando(s)");
                                try {
                                    Integer registros = 0;
                                    while (rstCd.proximo()) {
                                        try {
                                            registros++;
                                            String primeiros = "( ";
                                            int ichTot = rstCd.quantasColunas();
                                            if (ichTot >= 3) {
                                                ichTot = 3;
                                            } else if (ichTot >= 2) {
                                                ichTot = 2;
                                            } else if (ichTot >= 1) {
                                                ichTot = 1;
                                            } else {
                                                ichTot = 0;
                                            }
                                            if (ichTot > 0) {
                                                for (int ich = 0; ich < ichTot; ich++) {
                                                    Object ichVal = rstCd.pgVlr(ich + 1);
                                                    if (ichVal == null) {
                                                        primeiros += "NULO, ";
                                                    } else {
                                                        primeiros += ichVal.toString() + ", ";
                                                    }
                                                }
                                            }
                                            primeiros = UtlCrs.corta(primeiros, 2);
                                            if (!primeiros.isEmpty()) {
                                                primeiros += " )";
                                            }
                                            botaMsg("Executando Para o Registro: " + registros
                                                    + "\nCom primeiros: " + primeiros);
                                            for (String cmdCmp : cmds) {
                                                String cmdNm = cmdCmp;
                                                if (cmdCmp.contains(" ")) {
                                                    cmdNm = cmdCmp.split(" ")[0];
                                                }
                                                cmdNm += " da Operação " + oper.nome;
                                                String excCadaCmd = pegaComando(cmdCmp);
                                                String[] excPars = pegaParametros(cmdCmp);
                                                Vlrs pars = new Vlrs();
                                                for (int iPars = 0; iPars < excPars.length; iPars++) {
                                                    pars.bota(rstCd.pgVlr(Integer.parseInt(excPars[iPars])));
                                                }
                                                boolean rfaz = true;
                                                boolean analisarParametros = false;
                                                String ultimoErro = "";
                                                while (rfaz) {
                                                    rfaz = false;
                                                    boolean toExec = true;
                                                    if (analisarParametros) {
                                                        analisarParametros = false;
                                                        ExecTratAnalisaParametros anap = new ExecTratAnalisaParametros(null, pars, execCada.selecao, cmdCmp, ultimoErro);
                                                        anap.setVisible(true);
                                                        if (anap.refazer) {
                                                            pars = anap.parametros;
                                                        } else {
                                                            toExec = false;
                                                        }
                                                    }
                                                    if (toExec) {
                                                        try {
                                                            int retExc = conCmd.opera(excCadaCmd, pars);
                                                            botaMsg("Executado o comando :\n" + cmdNm + "\nAfetando " + retExc + " registros");
                                                        } catch (Exception ex) {
                                                            botaErro(execCada.pausarSeErrar, ex);
                                                            ultimoErro = ex.getMessage();
                                                            for (Tratamento trat : pegaTratamentos("aoErrar")) {
                                                                if (trat.ehCaso(ultimoErro)) {
                                                                    rfaz = true;
                                                                    analisarParametros = true;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            if (pausarEdeveParar()) {
                                                termina();
                                                return;
                                            }
                                        } catch (Exception ex) {
                                            botaErro(execCada.pausarSeErrar, ex);
                                        }
                                    }
                                } catch (Exception ex) {
                                    botaErro(execCada.pausarSeErrar, ex);
                                }
                            } catch (Exception ex) {
                                botaErro(execCada.pausarSeErrar, ex);
                            }

                        } catch (Exception ex) {
                            botaErro(true, ex);
                        }
                    }
                } catch (Exception ex) {
                    botaErro(true, ex);
                }
            }
            for (int i1 = 0; i1 < conexoes.length; i1++) {
                conexoes[i1].desconecta();
                botaMsg(conexoes[i1].pegaNome() + " Desconectado");
            }
            termina();
        } catch (Exception ex) {
            botaErro(false, ex);
        }
    }

    public Conexao pegaConexao(String aNome) {
        Conexao retorno = null;
        for (Conexao con : conexoes) {
            if (Objects.equals(aNome, con.pegaNome())) {
                retorno = con;
                break;
            }
        }
        return retorno;
    }

    public String[] pegaComandos(String aComandos) {
        String[] retorno = new String[0];
        if (aComandos != null) {
            retorno = aComandos.split("-prox-");
        }
        UtlTex.limpa(retorno);
        return retorno;
    }

    public String pegaComando(String aComando) {
        String retorno = "";
        if (aComando != null) {
            if (!aComando.isEmpty()) {
                String[] pars = aComando.split("-pars-");
                if (pars.length >= 1) {
                    retorno = pars[0].trim();
                }
            }
        }
        return retorno;
    }

    public String[] pegaParametros(String aComando) {
        String[] retorno = new String[0];
        if (aComando != null) {
            if (!aComando.isEmpty()) {
                String[] pars = aComando.split("-pars-");
                if (pars.length >= 2) {
                    retorno = pars[1].trim().split(",");
                }
            }
        }
        return retorno;
    }

    public List<Tratamento> pegaTratamentos(String comCondicao) {
        List<Tratamento> retorno = new ArrayList<>();
        for (Tratamento trat : migracao.tratamentos) {
            if (trat.ehCaso(comCondicao)) {
                retorno.add(trat);
            }
        }
        return retorno;
    }

    public void botaMsg(String aMsg) {
        retorna(aMsg);
        if (devePausar()) {
            pausar();
        }
    }

    public void botaErro(Boolean devePausar, Throwable oErro) {
        retorna(oErro);
        if (devePausar) {
            if (mostrador) {
                retornos().pMostrador().botaPausa();
                pausar();
            } else {
                termina();
            }
        }
    }

    public MigracaoExc salva() throws Exception {
        return salva("migração");
    }

    public MigracaoExc salva(String comNome) throws Exception {
        retornos().salvaEmRetsDir(comNome);
        return this;
    }

    @Override
    public void termina() {
        for (Conexao con : conexoes) {
            if (con != null) {
                con.desconecta();
            }
        }
        super.termina();
        if (salvaAoTerminar) {
            try {
                salva();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
        if (aoTerminar != null) {
            aoTerminar.actionPerformed(null);
        }
    }
};
