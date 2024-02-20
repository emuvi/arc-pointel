package pin.adm;

import java.util.Date;
import pin.libbas.Conjunto;
import pin.libutl.Utl;
import pin.libutl.UtlDat;
import pin.libutl.UtlInt;
import pin.libutl.UtlNumLon;
import pin.libutl.UtlTem;
import pin.libvlr.Vlrs;
import pin.modamk.Relatorio;
import pin.modamk.RelatorioCampo;
import pin.modamk.RelatorioPagina;
import pin.modamk.RelatorioParametro;

public class RelFaturasRecibos extends Relatorio {

    public static String pegaParcela(String doIndice, Relatorio doRelatorio, Vlrs dosDados) {
        String retorno = "";
        try {
            Integer indice = UtlInt.pega(doIndice);
            Date emissao = UtlDat.pega(dosDados.pgVlr("emissao"));
            Double valor = UtlNumLon.pega(dosDados.pgVlr("valor"));

            if ((indice != null) && (emissao != null)) {
                Conjunto rst = doRelatorio.conexao.busca("SELECT tipos_pagamento.nome, parcelas.dias, parcelas.parcela "
                        + " FROM parcelas "
                        + " JOIN tipos_pagamento ON tipos_pagamento.codigo = parcelas.tipo_pagamento "
                        + " WHERE parcelas.cond_pagamento = ? "
                        + " ORDER BY parcelas.dias ",
                        new Vlrs(dosDados.pgVlr("cond_pagamento")));
                Integer indRst = 0;
                while (rst.proximo()) {
                    indRst++;
                    if (indRst == indice) {
                        String tipo = rst.pgVlr(1).pgCrs();
                        Integer dias = rst.pgVlr(2).pgInt();
                        Double parcela = rst.pgVlr(3).pgNumLon();
                        Date paraVenc = UtlTem.somaDias(emissao, dias);
                        retorno = tipo + " PARA " + UtlDat.formata(paraVenc) + " R$ " + UtlNumLon.formata(valor * parcela / 100, "#,##0.00");
                    }
                }
            }
        } catch (Exception ex) {
            Utl.registra(ex);
        }

        return retorno;
    }

    public RelFaturasRecibos() {
        this(null, null);
    }

    public RelFaturasRecibos(Object aSerie, Object aCodigo) {
        super("Recibo", Tipo.Mat, RelatorioPagina.padraoMatricial(), new RelatorioCampo[]{
            new RelatorioCampo(1, 1, "RECIBO", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(1, 50, "VALOR DO RECIBO:", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(1, 67, "valor", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaTamanho(14).botaFormato(RelatorioCampo.Formatos.NUMERO_LON, "R$ #0.00"),
            new RelatorioCampo(3, 1, "RECEBEMOS DE :", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(3, 16, "cliente_nome", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaTamanho(49),
            new RelatorioCampo(3, 65, "CODIGO:", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(3, 73, "cliente_codigo", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaTamanho(8),
            new RelatorioCampo(5, 1, "FANTASIA     :", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(5, 16, "fantasia", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaTamanho(47),
            new RelatorioCampo(5, 61, "FONE:", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(5, 67, "fone1", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaTamanho(15),
            new RelatorioCampo(6, 61, "FONE:", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(6, 67, "fone2", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaTamanho(15),
            new RelatorioCampo(7, 1, "ENDERECO     :", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(7, 16, "endereco", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaTamanho(57),
            new RelatorioCampo(8, 16, "endereco", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaTamanho(64).botaInicio(57),
            new RelatorioCampo(7, 75, "UF:", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(7, 79, "estado", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaTamanho(2),
            new RelatorioCampo(9, 1, "CIDADE       :", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(9, 16, "cidade_bairro", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaTamanho(63),
            new RelatorioCampo(11, 1, "HISTORICO    :", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(11, 16, "historico", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaTamanho(63),
            new RelatorioCampo(13, 1, "REFERENTE AO :", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(13, 16, "ped_vendedor", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaTamanho(17),
            new RelatorioCampo(13, 34, "DA FATURA :", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(13, 46, "fatura", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaTamanho(14),
            new RelatorioCampo(13, 61, "EMITIDO :", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(13, 71, "emissao", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaTamanho(10).botaFormato(RelatorioCampo.Formatos.DATA, "dd/MM/yyyy"),
            new RelatorioCampo(14, 36, "DO LCTO :", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(14, 46, "codigo", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaTamanho(14),
            new RelatorioCampo(16, 1, "VALOR EXTENSO:", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(16, 17, "valor", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.CLASSE).botaTamanho(63).botaClasse("pin.libutl.UtlNumLon", "porExtensoMatricial"),
            new RelatorioCampo(17, 1, "valor", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.CLASSE).botaTamanho(80).botaClasse("pin.libutl.UtlNumLon", "porExtensoMatricial").botaInicio(63),
            new RelatorioCampo(19, 1, "NO VENCIMENTO:", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(20, 1, "vencimento", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaTamanho(14).botaFormato(RelatorioCampo.Formatos.DATA, "dd/MM/yyyy"),
            new RelatorioCampo(19, 27, "O VALOR :", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO).botaTamanho(14),
            new RelatorioCampo(20, 27, "valor", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaTamanho(14).botaFormato(RelatorioCampo.Formatos.NUMERO_LON, "R$ #0.00"),
            new RelatorioCampo(19, 52, "PARA PGTO:", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(20, 52, "tipo_pgto_nome", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaTamanho(14),
            new RelatorioCampo(22, 1, "MAIS MULTA:", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(23, 1, "_________________", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(22, 27, "MAIS JUROS:", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(23, 27, "________________", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(22, 52, "TOTAL DE:", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(23, 52, "________________", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(25, 1, "PARCELAS:", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(27, 1, "'1'", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.CLASSE).botaClasse("pin.adm.RelFaturasRecibos", "pegaParcela"),
            new RelatorioCampo(28, 1, "'2'", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.CLASSE).botaClasse("pin.adm.RelFaturasRecibos", "pegaParcela"),
            new RelatorioCampo(29, 1, "'3'", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.CLASSE).botaClasse("pin.adm.RelFaturasRecibos", "pegaParcela"),
            new RelatorioCampo(30, 1, "'4'", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.CLASSE).botaClasse("pin.adm.RelFaturasRecibos", "pegaParcela"),
            new RelatorioCampo(31, 1, "'5'", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.CLASSE).botaClasse("pin.adm.RelFaturasRecibos", "pegaParcela"),
            new RelatorioCampo(32, 1, "'6'", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.CLASSE).botaClasse("pin.adm.RelFaturasRecibos", "pegaParcela"),
            new RelatorioCampo(30, 54, "____________________________", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(31, 54, "ASSINATURA", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO).botaTamanho(28).botaAoCentro(),
            new RelatorioCampo(33, 1, ".", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO)
        });

        botaParametro(new RelatorioParametro("serie", aSerie));
        botaParametro(new RelatorioParametro("codigo", aCodigo));
    }

    Integer quantimp;

    @Override
    public void produzir() throws Exception {
        quantimp = conexao.busca("SELECT quantimp FROM lancamentos WHERE per_nf = 'S' AND serie = ? AND fatura = ?",
                new Vlrs(pegaParametroValor("serie"), pegaParametroValor("codigo")))
                .pgCol().pgInt(0);

        if (quantimp >= 1) {
            if (!Utl.continua("Já foi feita essa impressão " + quantimp + " vez(es).")) {
                return;
            }
        }

        resultados = conexao.busca("SELECT DISTINCT ON (lancamentos.vencimento, lancamentos.codigo) "
                + "  lancamentos.valor, clientes.nome AS cliente_nome, "
                + "  clientes.codigo AS cliente_codigo, clientes.fantasia, clientes.fone1, clientes.fone2, "
                + "  CONCAT(clientes.logradouro, ' ', clientes.endereco, ' , ', clientes.numero, ' - ', clientes.complemento) AS endereco, "
                + "  cidades.estado, CONCAT(cidades.nome, ' - ', bairros.nome) AS cidade_bairro, CONCAT(historicos.nome, ' ', lancamentos.complemento) AS historico, "
                + "  lancamentos.ped_vendedor, lancamentos.fatura, lancamentos.emissao, lancamentos.codigo, "
                + "  lancamentos.vencimento, tipos_pagamento.nome AS tipo_pgto_nome, faturas.cond_pagamento "
                + " FROM lancamentos "
                + " LEFT JOIN pessoas AS clientes ON "
                + "   clientes.codigo = lancamentos.pessoa "
                + " LEFT JOIN cidades ON "
                + "   cidades.codigo = clientes.cidade "
                + " LEFT JOIN bairros ON "
                + "   bairros.cidade = clientes.cidade AND "
                + "   bairros.codigo = clientes.bairro "
                + " LEFT JOIN historicos ON "
                + "   historicos.codigo = lancamentos.historico "
                + " LEFT JOIN tipos_pagamento ON "
                + "   tipos_pagamento.codigo = lancamentos.tipo_pagamento "
                + " JOIN faturas ON "
                + "   faturas.serie = lancamentos.serie AND "
                + "   faturas.codigo = lancamentos.fatura "
                + " WHERE lancamentos.serie = ? AND "
                + "   lancamentos.fatura = ? AND "
                + "   lancamentos.per_nf = 'S' "
                + " ORDER BY lancamentos.vencimento, lancamentos.codigo ",
                new Vlrs(pegaParametroValor("serie"), pegaParametroValor("codigo")));

        finalizar();
    }

    @Override
    public void finalizar() throws Exception {
        imprimir();

        quantimp++;
        conexao.opera("UPDATE lancamentos SET quantimp = ? WHERE per_nf = 'S' AND serie = ? AND fatura = ?",
                new Vlrs(quantimp, pegaParametroValor("serie"), pegaParametroValor("codigo")));
    }
}
