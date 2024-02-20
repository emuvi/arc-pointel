package pin.jrb;

import java.io.File;
import pin.libbas.Erro;
import pin.mgr.Migracao;

public class ServPointelMigre extends Serv {

    public File migracao;
    public String operacao;
    public String parametro;
    public Boolean mostrador;

    public ServPointelMigre() {
        super(Servico.Tp.PointelMigre);
        this.migracao = null;
        this.operacao = null;
        this.parametro = null;
    }

    @Override
    public void executa() throws Exception {
        Migracao migr = Migracao.abre(migracao);
        switch (operacao) {
            case ("Executar Migração"):
                migr.executa(pRetornos(), mostrador);
                break;
            case ("Backup da Conexão"):
                Integer bkpInd = migr.pIndiceDaConexao(parametro);
                if (bkpInd == -1) {
                    throw new Erro(parametro + " Conexão Não Encontrada");
                } else {
                    migr.fazBackup(bkpInd, pRetornos(), mostrador);
                }
                break;
            case ("Backup de Todas"):
                for (int ib = 0; ib < migr.conexoes.size(); ib++) {
                    migr.fazBackup(ib, pRetornos(), mostrador);
                }
                break;
            case ("Restore da Conexão"):
                Integer rtrInd = migr.pIndiceDaConexao(parametro);
                if (rtrInd == -1) {
                    throw new Erro(parametro + " Conexão Não Encontrada");
                } else {
                    migr.fazBackup(rtrInd, pRetornos(), mostrador);
                }
                break;
            case ("Restore de Todas"):
                for (int ib = 0; ib < migr.conexoes.size(); ib++) {
                    migr.fazRestore(ib, pRetornos(), mostrador);
                }
                break;
            default:
                throw new Erro("Operação Não Encontrada");
        }

    }
}
