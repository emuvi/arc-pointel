package pin.adm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pin.libamk.Cmp;
import pin.libbas.Conjunto;
import pin.libbas.Dados;
import pin.libbas.Progresso;
import pin.libutl.Utl;
import pin.libutl.UtlNumLon;
import pin.libvlr.Vlrs;
import pin.modamk.Rotina;

public class RotAjustarPrecos extends Rotina {

    public RotAjustarPrecos() {
        super("Ajustar Preços", false, new Cmp[]{
            new Cmp(1, 1, "tabela", "A Tabela", Dados.Tp.Crs).mTamanho(6).botaObrigatorio(),
            new Cmp(1, 2, "itabela", "Igual a Tabela", Dados.Tp.Crs).mTamanho(6).botaObrigatorio(),
            new Cmp(1, 3, "operacao", "Operação", Dados.Tp.Enu).botaOpcoes("(M) Multiplicar", "(S) Somar").botaObrigatorio(),
            new Cmp(1, 3, "valor", "Com Valor", Dados.Tp.NumLon).botaObrigatorio(),
            new Cmp(2, 1, "grupo", "Grupo", Dados.Tp.Crs).mTamanho(4),
            new Cmp(2, 1, "subgrupo", "SubGrupo", Dados.Tp.Crs).mTamanho(4)
        });

        aoConfirmar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    String tabela = intf.cmps().pEdt("tabela").pgVlr().pgCrs();
                    String itabela = intf.cmps().pEdt("itabela").pgVlr().pgCrs();
                    String operacao = intf.cmps().pEdt("operacao").pgVlr().pgCrs();
                    Double valorOperador = intf.cmps().pEdt("valor").pgVlr().pgNumLon();
                    String grupo = intf.cmps().pEdt("grupo").pgVlr().pgCrs();
                    String subgrupo = intf.cmps().pEdt("subgrupo").pgVlr().pgCrs();

                    String msg = "Deseja ajustar"
                            + " a Tabela: " + tabela
                            + " Igual a: " + itabela
                            + " Operando: " + operacao
                            + " no Valor de: " + valorOperador
                            + " do Grupo: " + grupo
                            + " e SubGrupo: " + subgrupo
                            + "?";

                    if (!Utl.continua(msg)) {
                        return;
                    }

                    Conjunto rst = null;

                    Progresso prog = new Progresso("Carregando Preços", false);
                    prog.abre();

                    if (grupo.isEmpty()) {
                        rst = conexao.busca("SELECT produto, valor FROM precos WHERE tabela = ?",
                                new Vlrs(itabela));
                    } else {
                        if (subgrupo.isEmpty()) {
                            rst = conexao.busca("SELECT precos.produto, precos.valor "
                                    + " FROM precos "
                                    + " LEFT JOIN produtos ON produtos.codigo = precos.produto "
                                    + " WHERE precos.tabela = ? "
                                    + "   AND produtos.grupo = ? ",
                                    new Vlrs(itabela, grupo));
                        } else {
                            rst = conexao.busca("SELECT precos.produto, precos.valor "
                                    + " FROM precos "
                                    + " LEFT JOIN produtos ON produtos.codigo = precos.produto "
                                    + " WHERE precos.tabela = ? "
                                    + "   AND produtos.grupo = ? "
                                    + "   AND produtos.subgrupo = ?",
                                    new Vlrs(itabela, grupo, subgrupo));
                        }
                    }

                    prog.abre("Alterando Preços", 0, rst.tamanho() - 1);

                    while (rst.proximo()) {
                        String produto = rst.pgVlr("produto").pgCrs();
                        Double valorAntigo = rst.pgVlr("valor").pgNumLon(0.0);
                        Double valorNovo = valorAntigo;
                        switch (operacao) {
                            case "M":
                                valorNovo = valorAntigo * valorOperador;
                                break;
                            case "S":
                                valorNovo = valorAntigo + valorOperador;
                                break;
                        }

                        conexao.opera("DELETE FROM precos WHERE produto = ? AND tabela = ?",
                                new Vlrs(produto, tabela));
                        conexao.opera("INSERT INTO precos (produto, tabela, valor) VALUES (?, ?, ?)",
                                new Vlrs(produto, tabela, valorNovo));

                        prog.bota("Ajustado produto " + produto + " na tabela " + tabela + " de " + UtlNumLon.formata(valorAntigo) + " para " + UtlNumLon.formata(valorNovo));
                        prog.avanca();
                    }

                    prog.bota("Terminou de Ajustar os Preços.\nPara Tabela: " + tabela + " A Partir da Tabela " + itabela);
                } catch (Exception ex) {
                    Utl.registra(ex);
                }

            }
        };
    }
}
