package pin.mgr;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.AbstractAction;
import pin.libbas.Comando;
import pin.libbas.Configs;
import pin.libbas.Globais;
import pin.libbas.Operacao;
import pin.libbas.Retornos;
import pin.libutl.UtlArq;
import pin.libutl.UtlCrs;
import pin.modrec.XMLReg;

public class Migracao {

    public ArrayList<ConexaoCfg> conexoes;
    public ArrayList<Operacao> operacoes;
    public ArrayList<Tratamento> tratamentos;

    public Migracao() {
        conexoes = new ArrayList<>();
        operacoes = new ArrayList<>();
        tratamentos = new ArrayList<>();
    }

    public static Migracao abre(File doArquivo) throws Exception {
        return (Migracao) XMLReg.novo().pValor(doArquivo);
    }

    public void salva(File noArquivo) throws Exception {
        XMLReg.novo().salvaReg(this, noArquivo);
    }

    public ConexaoCfg pConexao(String comNome) {
        ConexaoCfg retorno = null;
        for (int ic = 0; ic < conexoes.size(); ic++) {
            if (Objects.equals(comNome, conexoes.get(ic).nome)) {
                retorno = conexoes.get(ic);
                break;
            }
        }
        return retorno;
    }

    public Integer pIndiceDaConexao(String comNome) {
        Integer retorno = -1;
        for (int ic = 0; ic < conexoes.size(); ic++) {
            if (Objects.equals(comNome, conexoes.get(ic).nome)) {
                retorno = ic;
                break;
            }
        }
        return retorno;
    }

    public void executa() throws Exception {
        executa(null, true, null);
    }

    public void executa(Retornos comRetornos) throws Exception {
        executa(comRetornos, true, null);
    }

    public void executa(Retornos comRetornos, Boolean eMostrador) throws Exception {
        executa(comRetornos, eMostrador, null);
    }

    public void executa(Retornos comRetornos, Boolean eMostrador, AbstractAction aoTerminar) throws Exception {
        MigracaoExc exc = new MigracaoExc(this, comRetornos, eMostrador).botaAoTerminar(aoTerminar);
        exc.start();
        exc.join();
    }

    public void fazBackup(Integer daConexaoIndice) throws Exception {
        fazBackup(daConexaoIndice, null, true, null);
    }

    public void fazBackup(Integer daConexaoIndice, Retornos comRetornos) throws Exception {
        fazBackup(daConexaoIndice, comRetornos, true, null);
    }

    public void fazBackup(Integer daConexaoIndice, Retornos comRetornos, Boolean eMostrador) throws Exception {
        fazBackup(daConexaoIndice, comRetornos, eMostrador, null);
    }

    public void fazBackup(Integer daConexaoIndice, Retornos comRetornos, Boolean eMostrador, AbstractAction aoTerminar) throws Exception {
        if (eMostrador) {
            if (comRetornos == null) {
                comRetornos = new Retornos();
            }
            if (comRetornos.pMostrador() == null) {
                comRetornos.mostrador("Migração Backup");
            }
        }
        Retornos.bota(comRetornos, "Iniciando Backup da Conexao " + daConexaoIndice);
        ConexaoCfg cng = conexoes.get(daConexaoIndice);
        Retornos.bota(comRetornos, "Conexao Nome: " + cng.nome);
        String pgCam = ((Configs) Globais.pega("mainConf")).pegaCrs("PGDump Caminho", "");
        if (pgCam.isEmpty()) {
            pgCam = UtlCrs.pergunta("Qual o Caminho para o pg_dump?");
            if (pgCam == null) {
                throw new Exception("Necessário Informar o Caminho para o pg_dump");
            } else if (pgCam.isEmpty()) {
                throw new Exception("Necessário Informar o Caminho para o pg_dump");
            } else {
                ((Configs) Globais.pega("mainConf")).botaCrs("PGDump Caminho", pgCam);
            }
        }
        Retornos.bota(comRetornos, "PGDump Caminho: " + pgCam);
        File bkpCam = UtlArq.pegaPointelArq("bkups", cng.nome + ".bkp");
        while (bkpCam.exists()) {
            Files.move(bkpCam.toPath(), UtlArq.naoSobrescrever(bkpCam).toPath());
        }
        String con = "--dbname=postgresql://" + cng.usuario + ":" + cng.senha + "@" + cng.servidor + ":" + cng.porta + "/" + cng.base;
        Comando cmd = new Comando("Migração Backup", pgCam);
        cmd.mudaRetornos(comRetornos);
        cmd.mudaAoTerminar(aoTerminar);
        cmd.botaArgumento(con);
        cmd.botaArgumento("--format");
        cmd.botaArgumento("tar");
        cmd.botaArgumento("--blobs");
        cmd.botaArgumento("--encoding");
        cmd.botaArgumento("UTF8");
        cmd.botaArgumento("--verbose");
        cmd.botaArgumento("-f");
        cmd.botaArgumento(bkpCam.getAbsolutePath());
        cmd.inicia();
    }

    public void fazBackupTodas() throws Exception {
        fazBackupTodas(null, true, null);
    }

    public void fazBackupTodas(Retornos comRetornos) throws Exception {
        fazBackupTodas(comRetornos, true, null);
    }

    public void fazBackupTodas(Retornos comRetornos, Boolean eMostrador) throws Exception {
        fazBackupTodas(comRetornos, eMostrador, null);
    }

    public void fazBackupTodas(Retornos comRetornos, Boolean eMostrador, AbstractAction aoTerminar) throws Exception {
        for (int ic = 0; ic < conexoes.size(); ic++) {
            fazBackup(ic, comRetornos, eMostrador, aoTerminar);
        }
    }

    public void fazRestore(Integer daConexaoIndice) throws Exception {
        fazRestore(daConexaoIndice, null, true, null);
    }

    public void fazRestore(Integer daConexaoIndice, Retornos comRetornos) throws Exception {
        fazRestore(daConexaoIndice, comRetornos, true, null);
    }

    public void fazRestore(Integer daConexaoIndice, Retornos comRetornos, Boolean eMostrador) throws Exception {
        fazRestore(daConexaoIndice, comRetornos, eMostrador, null);
    }

    public void fazRestore(Integer daConexaoIndice, Retornos comRetornos, Boolean eMostrador, AbstractAction aoTerminar) throws Exception {
        if (eMostrador) {
            if (comRetornos == null) {
                comRetornos = new Retornos();
            }
            if (comRetornos.pMostrador() == null) {
                comRetornos.mostrador("Migração Retore");
            }
        }
        ConexaoCfg cng = conexoes.get(daConexaoIndice);
        String pgCam = ((Configs) Globais.pega("mainConf")).pegaCrs("PGRestore Caminho", "");
        if (pgCam.isEmpty()) {
            pgCam = UtlCrs.pergunta("Qual o Caminho para o pg_restore?");
            if (pgCam == null) {
                throw new Exception("Necessário Informar o Caminho para o pg_restore");
            } else if (pgCam.isEmpty()) {
                throw new Exception("Necessário Informar o Caminho para o pg_restore");
            } else {
                ((Configs) Globais.pega("mainConf")).botaCrs("PGRestore Caminho", pgCam);
            }
        }
        File bkpCam = UtlArq.pegaPointelArq("bkups", cng.nome + ".bkp");
        String con = "--dbname=postgresql://" + cng.usuario + ":" + cng.senha + "@" + cng.servidor + ":" + cng.porta + "/" + cng.base;
        Comando cmd = new Comando("Migração Restore", pgCam);
        cmd.mudaRetornos(comRetornos);
        cmd.mudaAoTerminar(aoTerminar);
        cmd.botaArgumento(con);
        cmd.botaArgumento("--format");
        cmd.botaArgumento("tar");
        cmd.botaArgumento("--clean");
        cmd.botaArgumento("--verbose");
        cmd.botaArgumento(bkpCam.getAbsolutePath());
        cmd.inicia();
    }

    public void fazRestoreTodas() throws Exception {
        fazRestoreTodas(null, true, null);
    }

    public void fazRestoreTodas(Retornos comRetornos) throws Exception {
        fazRestoreTodas(comRetornos, true, null);
    }

    public void fazRestoreTodas(Retornos comRetornos, Boolean eMostrador) throws Exception {
        fazRestoreTodas(comRetornos, eMostrador, null);
    }

    public void fazRestoreTodas(Retornos comRetornos, Boolean eMostrador, AbstractAction aoTerminar) throws Exception {
        for (int ic = 0; ic < conexoes.size(); ic++) {
            fazRestore(ic, comRetornos);
        }
    }
}
