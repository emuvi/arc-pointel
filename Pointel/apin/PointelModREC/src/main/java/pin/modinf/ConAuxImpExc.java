package pin.modinf;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import pin.libbas.Erro;
import pin.libbas.ParsChaves;
import pin.libbas.Progresso;
import pin.libutl.UtlTex;

public class ConAuxImpExc {

    private final Conexao conexao;
    private final String origem;
    private final Boolean subpastas;
    private final List<ConAuxImpOpe> operacoes;

    public ConAuxImpExc(Conexao daConexao, String comOrigem, Boolean eSubPastas) {
        this.conexao = daConexao;
        this.origem = comOrigem;
        this.subpastas = eSubPastas;
        this.operacoes = new ArrayList<>();
    }

    public ConAuxImpExc botaOpe(ConAuxImpOpe aOperacao) {
        operacoes.add(aOperacao);
        return this;
    }

    public ConAuxImpExc limpaOpes() {
        operacoes.clear();
        return this;
    }

    public ConAuxImpExc executa() {
        new Thread("Auxiliar Importar Executar") {

            private Progresso prg;
            private ParsChaves chvs;

            private void prc(File oEndereco) {
                prg.bota("Processando", oEndereco);
                if (oEndereco.isDirectory()) {
                    for (File fl : oEndereco.listFiles()) {
                        if (fl.isDirectory() && subpastas) {
                            prc(fl);
                        } else {
                            prc(fl);
                        }
                        if (prg.pausarEdeveParar()) {
                            break;
                        }
                    }
                } else {
                    try {
                        prg.bota("Abrindo", oEndereco);
                        chvs.bota("arquivo", oEndereco);
                        String txt = UtlTex.abre(oEndereco);
                        if (txt == null) {
                            throw new Erro("Arquivo Nulo: " + oEndereco);
                        }
                        if (txt.isEmpty()) {
                            throw new Erro("Arquivo Vazio: " + oEndereco);
                        }
                        chvs.bota("texto", txt);
                        prg.mTamanho(txt.length());
                        chvs.bota("prgCorpo", 0);
                        chvs.bota("prgParte", 0);
                        prg.mProgresso(0);
                        int iop = 0;
                        while (iop < operacoes.size()) {
                            prg.bota("Operação", iop);
                            ConAuxImpOpe ope = operacoes.get(iop);
                            prg.bota("Operando", ope.toString());
                            chvs.bota("indice", iop);
                            iop = ope.opera(chvs);
                            if (iop == -1) {
                                break;
                            }
                            Integer prgCorpo = (Integer) chvs.pega("prgCorpo");
                            Integer prgParte = (Integer) chvs.pega("prgParte");
                            prg.mProgresso(prgCorpo + prgParte);
                            if (prg.pausarEdeveParar()) {
                                break;
                            }
                        }
                    } catch (Exception ex) {
                        prg.bota(ex);
                    } finally {
                        prg.bota("Terminado", oEndereco);
                    }
                }
            }

            @Override
            public void run() {
                chvs = new ParsChaves();
                prg = new Progresso("Importando");
                prg.abre();
                chvs.bota("prg", prg);
                File flOrg = new File(origem);
                prc(flOrg);
                prg.termina();
            }
        }.start();
        return this;
    }

}
