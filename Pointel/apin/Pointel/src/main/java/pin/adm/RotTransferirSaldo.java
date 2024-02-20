package pin.adm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pin.libamk.Cmp;
import pin.libbas.Dados;
import pin.libutl.Utl;
import pin.libutl.UtlDat;
import pin.libutl.UtlTem;
import pin.libvlr.Vlrs;
import pin.modamk.Rotina;

public class RotTransferirSaldo extends Rotina {

    public RotTransferirSaldo() {
        super("Transferir Saldo", new Cmp[]{
            new Cmp(1, 1, "mes", "Mês", Dados.Tp.Int).botaObrigatorio().mVlrInicial(UtlTem.pegaMesAtual()).botaOpcoes(new Object[]{1, 12}),
            new Cmp(1, 1, "ano", "Ano", Dados.Tp.Int).botaObrigatorio().mVlrInicial(UtlTem.pegaAnoAtual()),});

        aoConfirmar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    fecharAoConfirmar = false;
                    Integer mes = pegaInt("mes");
                    Integer ano = pegaInt("ano");
                    Integer mesSv = conexao.busca("SELECT mes FROM saldos WHERE mes = ? AND ano = ?",
                            new Vlrs(mes, ano))
                            .pgCol().pgInt(0);
                    if (mesSv > 0) {
                        Utl.alerta("Saldo Já Transferido.");
                    } else {
                        Integer mesAnt = mes - 1;
                        Integer anoAnt = ano;
                        if (mesAnt == 0) {
                            mesAnt = 12;
                            anoAnt--;
                        }
                        Double saldoAnt = conexao.busca("SELECT saldo FROM saldos WHERE mes = ? AND ano = ?",
                                new Vlrs(mesAnt, anoAnt))
                                .pgCol().pgNumLon(0.0);
                        String dsDe = "01/" + mesAnt + "/" + anoAnt;
                        String dsAte = UtlTem.pegaUltimoDia(mesAnt, anoAnt) + "/" + mesAnt + "/" + anoAnt;
                        java.sql.Date tsDe = UtlDat.pegaSQL(UtlDat.pega(dsDe));
                        java.sql.Date tsAte = UtlDat.pegaSQL(UtlDat.pega(dsAte));
                        if ((tsDe != null) && (tsAte != null)) {
                            Double saldoAntCax = conexao.busca("SELECT SUM(valor) "
                                    + " FROM lancamentos "
                                    + " WHERE data >= ? AND data <= ? "
                                    + "   AND ((tipo = 'E') OR (tipo = 'S'))",
                                    new Vlrs(tsDe, tsAte))
                                    .pgCol().pgNumLon(0.0);
                            Double saldoFim = saldoAnt + saldoAntCax;
                            if (conexao.opera("INSERT INTO saldos (mes, ano, saldo) VALUES (?, ?, ?)",
                                    new Vlrs(mes, ano, saldoFim)) > 0) {
                                Utl.alerta("Saldo Transferido com Sucesso.");
                                fecharAoConfirmar = true;
                            } else {
                                Utl.alerta("Não Foi Possível Transferir o Saldo.");
                            }
                        } else {
                            Utl.alerta("Não Foi Possível Somar o Caixa.");
                        }
                    }
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        };
    }

}
