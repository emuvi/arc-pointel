package pin.adm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pin.libamk.Cmp;
import pin.libbas.Conjunto;
import pin.libbas.Dados;
import pin.libutl.Utl;
import pin.libvlr.Vlrs;
import pin.modamk.Rotina;

public class RotCancelamento extends Rotina {

    public String cliente;

    public String descreveFatura() {
        try {
            Conjunto res = conexao.busca("SELECT faturas.emitido_data, pessoas.nome "
                    + " FROM faturas "
                    + " LEFT JOIN pessoas ON "
                    + "   pessoas.codigo = faturas.cliente "
                    + " WHERE faturas.serie = ? AND faturas.codigo = ?",
                    new Vlrs(pegaVlr("FatSerie"), pegaVlr("FatCodigo")));

            cliente = res.pgVlr(2).pgCrs();

            return "Fatura:" + "\n"
                    + "  Série: " + pegaCrs("FatSerie")
                    + " - Codigo: " + pegaCrs("FatCodigo")
                    + " - Emissão: " + res.pgVlrDat(1).pgCrs() + "\n"
                    + "  Cliente: " + res.pgVlr(2).pgCrs()
                    + "\n\nDeseja Cancelar?";
        } catch (Exception ex) {
            Utl.registra(ex);
            return null;
        }
    }

    public String descrevePedido() {
        try {
            Conjunto res = conexao.busca("SELECT pedidos.emitido_data, pessoas.nome "
                    + " FROM pedidos "
                    + " LEFT JOIN pessoas ON "
                    + "   pessoas.codigo = pedidos.cliente "
                    + " WHERE pedidos.codigo = ?",
                    new Vlrs(pegaVlr("PedCodigo")));

            cliente = res.pgVlr(2).pgCrs();

            return "Pedido:" + "\n"
                    + "  Codigo: " + pegaCrs("PedCodigo")
                    + " - Emissão: " + res.pgVlrDat(1).pgCrs() + "\n"
                    + "  Cliente: " + res.pgVlr(2).pgCrs();
        } catch (Exception ex) {
            Utl.registra(ex);
            return null;
        }
    }

    public String descreveSaldo() {
        try {
            Conjunto res = conexao.busca("SELECT pedidos.emitido_data, pessoas.nome "
                    + " FROM pedidos "
                    + " LEFT JOIN pessoas ON "
                    + "   pessoas.codigo = pedidos.cliente "
                    + " WHERE pedidos.codigo = ?",
                    new Vlrs(pegaVlr("SalCodigo")));

            cliente = res.pgVlr(2).pgCrs();

            return "Saldo:" + "\n"
                    + "  Codigo: " + pegaCrs("SalCodigo")
                    + " - Emissão: " + res.pgVlrDat(1).pgCrs() + "\n"
                    + "  Cliente: " + res.pgVlr(2).pgCrs();
        } catch (Exception ex) {
            Utl.registra(ex);
            return null;
        }
    }

    public void cancelaFatura() {
        try {
            conexao.opera("INSERT INTO cancelados (tipo, codigo, obs, cliente) VALUES (?, ?, ?, ?)",
                    new Vlrs("F", pegaCrs("FatSerie") + "-" + pegaCrs("FatCodigo"), pegaVlr("FatObs"), cliente));
            conexao.opera("DELETE FROM lancamentos WHERE serie = ? AND fatura = ? AND per_nf = 'S'",
                    new Vlrs(pegaVlr("FatSerie"), pegaVlr("FatCodigo")));
            conexao.opera("DELETE FROM itens_faturas WHERE serie = ? AND nf = ?",
                    new Vlrs(pegaVlr("FatSerie"), pegaVlr("FatCodigo")));
            conexao.opera("DELETE FROM faturas WHERE serie = ? AND codigo = ?",
                    new Vlrs(pegaVlr("FatSerie"), pegaVlr("FatCodigo")));
            Utl.alerta("Cancelamento de Fatura Realizado.");
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }

    public void cancelaPedido() {
        try {
            conexao.opera("INSERT INTO cancelados (tipo, codigo, obs, cliente) VALUES (?, ?, ?, ?)",
                    new Vlrs("P", pegaCrs("PedCodigo"), pegaVlr("PedObs"), cliente));
            conexao.opera("DELETE FROM itens_pedido WHERE pedido = ?",
                    new Vlrs(pegaVlr("PedCodigo")));
            conexao.opera("DELETE FROM pedidos WHERE codigo = ?",
                    new Vlrs(pegaVlr("PedCodigo")));
            Utl.alerta("Cancelamento de Pedido Realizado.");
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }

    public void cancelaSaldo() {
        try {
            conexao.opera("INSERT INTO cancelados (tipo, codigo, obs, cliente) VALUES (?, ?, ?, ?)",
                    new Vlrs("S", pegaCrs("SalCodigo"), pegaVlr("SalObs"), cliente));
            conexao.opera("DELETE FROM itens_pedido WHERE pedido = ? AND situacao LIKE 'L'",
                    new Vlrs(pegaVlr("SalCodigo")));
            Utl.alerta("Cancelamento de Saldo Realizado.");
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }

    public RotCancelamento() {
        super("Cancelamento", false, new Cmp[]{
            new Cmp(1, 1, "abs", Dados.Tp.Abs),
            new Cmp("abs", 1, "Fatura", "Fatura", Dados.Tp.Pai),
            new Cmp("abs", 1, "Pedido", "Pedido", Dados.Tp.Pai),
            new Cmp("abs", 1, "Saldo", "Saldo", Dados.Tp.Pai),
            new Cmp("Fatura", 1, 1, "FatSerie", "Série", Dados.Tp.Crs).mTamanho(4),
            new Cmp("Fatura", 1, 2, "FatCodigo", "Código", Dados.Tp.Crs).mTamanho(10),
            new Cmp("Fatura", 2, 1, "FatObs", "Observação", Dados.Tp.Crs).mTamanho(240),
            new Cmp("Pedido", 1, 1, "PedCodigo", "Código", Dados.Tp.Crs).mTamanho(10),
            new Cmp("Pedido", 2, 1, "PedObs", "Observação", Dados.Tp.Crs).mTamanho(240),
            new Cmp("Saldo", 1, 1, "SalCodigo", "Código", Dados.Tp.Crs).mTamanho(10),
            new Cmp("Saldo", 2, 1, "SalObs", "Observação", Dados.Tp.Crs).mTamanho(240)
        });

        aoConfirmar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (campos.pAbaSelecionada("abs").equals("Fatura")) {
                        if (Utl.continua(descreveFatura())) {
                            cancelaFatura();
                        }
                    } else if (campos.pAbaSelecionada("abs").equals("Pedido")) {
                        if (Utl.continua(descrevePedido())) {
                            cancelaPedido();
                        }
                    } else if (campos.pAbaSelecionada("abs").equals("Saldo")) {
                        if (Utl.continua(descreveSaldo())) {
                            cancelaSaldo();
                        }
                    }

                    limpar();
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        };
    }
}
