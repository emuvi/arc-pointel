package pin.adm;

import net.sf.dynamicreports.report.datasource.DRDataSource;
import pin.libbas.Conjunto;
import pin.libbas.Globais;
import pin.libutl.UtlDat;
import pin.libvlr.Vlrs;
import pin.modamk.Relatorio;
import pin.modamk.RelatorioCampo;
import pin.modamk.RelatorioPagina;
import pin.modinf.Conexao;

public class RelTrocas extends Relatorio {

    public Conexao conexao;
    public Object troca;

    public RelTrocas() {
        this(null);
    }

    public RelTrocas(Object daTroca) {
        super("Troca de Mercadorias", Relatorio.Tipo.Gra, RelatorioPagina.padraoGrafico(),
                new RelatorioCampo[] {
                        new RelatorioCampo(1, 2, "linha", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO)
                                .botaTamanho(10) });

        troca = daTroca;
        conexao = (Conexao) Globais.pega("mainConc");
    }

    @Override
    public void produzir() throws Exception {
        Conjunto resTroca = conexao.busca("SELECT trocas.tipo, trocas.cliente, trocas.obs, "
                + "  pessoas.nome, pessoas.fantasia, pessoas.fone1, "
                + "  CONCAT(pessoas.logradouro, ' ', pessoas.endereco, ' , ', pessoas.numero, ' - ', pessoas.complemento) AS endereco, "
                + "  cidades.nome AS cidade, bairros.nome AS bairro, cidades.estado " + "FROM trocas "
                + "  LEFT JOIN pessoas ON pessoas.codigo = trocas.cliente "
                + "  LEFT JOIN cidades ON cidades.codigo = pessoas.cidade "
                + "  LEFT JOIN bairros ON bairros.cidade = pessoas.cidade AND bairros.codigo = pessoas.bairro "
                + "WHERE trocas.codigo = ?", new Vlrs(troca));

        if (resTroca.vazio()) {
            throw new Exception("Não Encontrou a Troca");
        }

        resTroca.proximo();

        final String codTipo = resTroca.pgVlr("tipo").pgCrs();
        String trotipo = "TROCA";
        if ("R".equals(codTipo)) {
            trotipo = "REPOSIÇÃO";
        } else if ("B".equals(codTipo)) {
            trotipo = "BONIFICAÇÃO";
        }
        String troclie = resTroca.pgVlr("cliente").pgCrs();
        String troobse = resTroca.pgVlr("obs", "").pgCrs();
        String pesnome = resTroca.pgVlr("nome").pgCrs();
        String pesfant = resTroca.pgVlr("fantasia").pgCrs();
        String pesfone = resTroca.pgVlr("fone1").pgCrs();
        String pesende = resTroca.pgVlr("endereco").pgCrs();
        String pescida = resTroca.pgVlr("cidade").pgCrs();
        String pesbair = resTroca.pgVlr("bairro").pgCrs();
        String pesesta = resTroca.pgVlr("estado").pgCrs();

        fonteDados = new DRDataSource("linha");

        for (int vez = 0; vez < 4; vez++) {
            if (vez > 0) {
                fonteDados.add("");
                fonteDados.add("");
            }

            fonteDados.add("À " + pesnome + " - " + pesfant + " (" + troclie + ")");
            fonteDados.add("ENDEREÇO: " + pesende);
            fonteDados.add(pescida + " - " + pesbair + " - " + pesesta + " FONE: " + pesfone);
            fonteDados.add(trotipo);

            Conjunto resLevar = conexao.busca(
                    "SELECT trocas_levar.quantidade, trocas_levar.unidade, produtos.nome, trocas_levar.produto, trocas_levar.obs "
                            + "FROM trocas_levar " + "  LEFT JOIN produtos ON produtos.codigo = trocas_levar.produto "
                            + "WHERE trocas_levar.troca = ?",
                    new Vlrs(troca));

            while (resLevar.proximo()) {
                String levar = "";
                if (resLevar.ehPrimeiro()) {
                    levar += "LEVAR:  ";
                } else {
                    levar += "\t";
                }
                levar += resLevar.pgVlrNumLon("quantidade", 0.0).pgCrs();
                levar += " " + resLevar.pgVlr("unidade").pgCrs();
                levar += " " + resLevar.pgVlr("nome").pgCrs();
                levar += " (" + resLevar.pgVlr("produto") + ") - " + resLevar.pgVlr("obs").pgCrs();
                fonteDados.add(levar);
            }

            Conjunto resTrazer = conexao.busca(
                    "SELECT trocas_trazer.quantidade, trocas_trazer.unidade, produtos.nome, trocas_trazer.defeito, trocas_trazer.produto, trocas_trazer.obs "
                            + "FROM trocas_trazer " + "  LEFT JOIN produtos ON produtos.codigo = trocas_trazer.produto "
                            + "WHERE trocas_trazer.troca = ?",
                    new Vlrs(troca));

            while (resTrazer.proximo()) {
                String trazer = "";
                if (resTrazer.ehPrimeiro()) {
                    trazer += "RECOLHER:  ";
                } else {
                    trazer += "\t";
                }
                trazer += resTrazer.pgVlrNumLon("quantidade", 0.0).pgCrs();
                trazer += " " + resTrazer.pgVlr("unidade").pgCrs();
                trazer += " " + resTrazer.pgVlr("nome").pgCrs()
                        + (resTrazer.pgVlr("defeito").pgLog(false) ? " COM DEFEITO" : "");
                trazer += " (" + resTrazer.pgVlr("produto").pgCrs() + ") - " + resTrazer.pgVlr("obs").pgCrs();
                fonteDados.add(trazer);
            }

            if (!troobse.isEmpty()) {
                fonteDados.add("OBS:" + troobse);
            }

            fonteDados.add("\t\t\t\t\t\t\tPALHOÇA " + UtlDat.pegaAtual());
        }

        finalizar();
    }

    @Override
    public void finalizar() throws Exception {
        imprimir();
    }
}
