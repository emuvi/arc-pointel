package pin.adm;

import pin.libbas.Conjunto;
import pin.libutl.Utl;
import pin.libutl.UtlCrs;
import pin.libvlr.VCrs;
import pin.libvlr.Vlrs;
import pin.modamk.Relatorio;
import pin.modamk.RelatorioCampo;
import pin.modamk.RelatorioPagina;
import pin.modamk.RelatorioParametro;

public class RelFaturasDuplicatas extends Relatorio {

    public static Boolean ultimoPrimeiro = true;
    public static String ultimoCodigo = "A";

    public static void reiniciarCodigo() {
        ultimoCodigo = "A";
    }

    public static String ajustarCodigo(VCrs dosCaracteres) {
        String retorno = UtlCrs.cortaAFrente(dosCaracteres.pgCrs(), '0') + " " + ultimoCodigo;
        ultimoCodigo = UtlCrs.pegaProxima(ultimoCodigo, false);
        return retorno;
    }

    public RelFaturasDuplicatas() {
        this(null, null);
    }

    public RelFaturasDuplicatas(Object aSerie, Object aCodigo) {
        super("Duplicata", Tipo.Mat, RelatorioPagina.padraoMatricial(), new RelatorioCampo[]{
            new RelatorioCampo(1, 1, "cabfantasia", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.PARAMETRO),
            new RelatorioCampo(2, 1, "cabnome", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.PARAMETRO),
            new RelatorioCampo(3, 1, "cabcontato", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.PARAMETRO),
            new RelatorioCampo(4, 1, "cabendereco", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.PARAMETRO),
            new RelatorioCampo(5, 54, "EMISSAO", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(5, 62, "emissao", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaFormato(RelatorioCampo.Formatos.DATA, "dd/MM/yyyy"),
            new RelatorioCampo(6, 11, "PEDIDO", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(6, 18, "ped_vendedor", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO),
            new RelatorioCampo(9, 24, "FATURA", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(10, 24, "fatura", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.CLASSE).botaClasse("pin.adm.RelFaturasDuplicatas", "ajustarCodigo"),
            new RelatorioCampo(9, 34, "VALOR", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(10, 34, "valor", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaFormato(RelatorioCampo.Formatos.NUMERO_LON, "#0.00"),
            new RelatorioCampo(9, 55, "VENCIMENTO", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(10, 55, "vencimento", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaFormato(RelatorioCampo.Formatos.DATA, "dd/MM/yyyy"),
            new RelatorioCampo(9, 68, "DUPLICATA", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(10, 68, "tipo_pgto_nome", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO),
            new RelatorioCampo(15, 15, "NOME:", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(15, 21, "cliente_nome", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaTamanho(45),
            new RelatorioCampo(15, 69, "cliente_codigo", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaTamanho(8),
            new RelatorioCampo(16, 11, "ENDERECO:", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(16, 21, "endereco", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO),
            new RelatorioCampo(17, 21, "cidade_nome", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO).botaTamanho(28),
            new RelatorioCampo(17, 56, "estado", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO),
            new RelatorioCampo(17, 66, "cep", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO),
            new RelatorioCampo(18, 14, "PRACA: A MESMA", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(19, 21, "cnpjcpf", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.CLASSE).botaClasse("pin.libutl.UtlCrs", "pegaCNPJCPFFormatado"),
            new RelatorioCampo(19, 58, "insestadual", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.DADO),
            new RelatorioCampo(22, 11, "EXTENSO:", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(22, 20, "valor", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.CLASSE).botaTamanho(57).botaClasse("pin.libutl.UtlNumLon", "porExtensoMatricial"),
            new RelatorioCampo(23, 20, "valor", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.CLASSE).botaTamanho(57).botaClasse("pin.libutl.UtlNumLon", "porExtensoMatricial").botaInicio(57),
            new RelatorioCampo(25, 13, "RECONHECEMOS A EXATIDAO DESTA DUPLICATA DE VENDA MERCANTIL COM", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(26, 13, "PAGAMENTO PARCELADO NA IMPORTANCIA ACIMA QUE PAGAREI(EMOS) A", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(27, 13, "INCORPEL IND COM REPR DE PAPEL LTDA OU A SUA ORDEM NA PRACA E NO", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(27, 13, "VENCIMENTO ACIMA INDICADOS", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO),
            new RelatorioCampo(33, 1, ".", RelatorioCampo.Area.DADOS_DETALHES, RelatorioCampo.Tp.TEXTO)
        });

        botaParametro(new RelatorioParametro("serie", aSerie));
        botaParametro(new RelatorioParametro("codigo", aCodigo));

        try {
            Object pscod = conexao.conCfgs().pegaCrs("PointelAdmin - Opções - Empresa - Pessoa do Cabeçalho", "");

            Conjunto pscab = conexao.busca("SELECT pessoas.nome, pessoas.fantasia, pessoas.fone1, cidades.estado, cidades.nome, bairros.nome, "
                    + " CONCAT(pessoas.logradouro, ' ', pessoas.endereco, ' , ', pessoas.numero, ' - ', pessoas.complemento) AS endereco "
                    + " FROM pessoas "
                    + " LEFT JOIN cidades ON "
                    + "   cidades.codigo = pessoas.cidade "
                    + " LEFT JOIN bairros ON "
                    + "   bairros.cidade = pessoas.cidade AND "
                    + "   bairros.codigo = pessoas.bairro "
                    + " WHERE pessoas.codigo = ?",
                    new Vlrs(pscod));

            Object psnom = pscab.pgVlr(1).pgCrs("");
            Object psfan = pscab.pgVlr(2).pgCrs("");
            Object psfon = pscab.pgVlr(3).pgCrs("");
            Object psest = pscab.pgVlr(4).pgCrs("");
            Object pscid = pscab.pgVlr(5).pgCrs("");
            Object psbar = pscab.pgVlr(6).pgCrs("");
            Object psend = pscab.pgVlr(7).pgCrs("");

            botaParametro(new RelatorioParametro("cabnome", psnom));
            botaParametro(new RelatorioParametro("cabfantasia", psfan));
            botaParametro(new RelatorioParametro("cabcontato", psfon + " - " + psest + " - " + pscid + " - " + psbar));
            botaParametro(new RelatorioParametro("cabendereco", psend));

            reiniciarCodigo();
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }

    @Override
    public void produzir() throws Exception {
        Integer quantimp = conexao.busca("SELECT quantimp FROM lancamentos WHERE per_nf = 'S' AND serie = ? AND fatura = ?",
                new Vlrs(pegaParametroValor("serie"), pegaParametroValor("codigo")))
                .pgCol().pgInt(0);

        if (quantimp >= 1) {
            if (!Utl.continua("Já foi feita essa impressão " + quantimp + " vez(es).")) {
                return;
            }
        }

        resultados = conexao.busca("SELECT DISTINCT ON (lancamentos.vencimento, lancamentos.codigo) "
                + "  lancamentos.emissao, lancamentos.ped_vendedor, lancamentos.fatura, lancamentos.valor, lancamentos.vencimento, "
                + "  tipos_pagamento.nome AS tipo_pgto_nome, clientes.nome AS cliente_nome, clientes.codigo AS cliente_codigo, clientes.fantasia, clientes.fone1, "
                + "  CONCAT(clientes.logradouro, ' ', clientes.endereco, ' , ', clientes.numero, ' - ', clientes.complemento) AS endereco, "
                + "  CONCAT(cidades.nome, ' - ', bairros.nome) AS cidade_nome, cidades.estado, clientes.cep, clientes.cnpjcpf, clientes.insestadual "
                + " FROM lancamentos "
                + " LEFT JOIN pessoas AS clientes ON "
                + "   clientes.codigo = lancamentos.pessoa "
                + " LEFT JOIN cidades ON "
                + "   cidades.codigo = clientes.cidade "
                + " LEFT JOIN bairros ON "
                + "   bairros.cidade = clientes.cidade AND "
                + "   bairros.codigo = clientes.bairro "
                + " LEFT JOIN tipos_pagamento ON "
                + "   tipos_pagamento.codigo = lancamentos.tipo_pagamento "
                + " WHERE lancamentos.serie = ? AND "
                + "   lancamentos.fatura = ? AND "
                + "   lancamentos.per_nf = 'S' "
                + " ORDER BY lancamentos.vencimento, lancamentos.codigo ",
                new Vlrs(pegaParametroValor("serie"), pegaParametroValor("codigo")));
        imprimir();

        quantimp++;
        conexao.opera("UPDATE lancamentos SET quantimp = ? WHERE per_nf = 'S' AND serie = ? AND fatura = ?",
                new Vlrs(quantimp, pegaParametroValor("serie"), pegaParametroValor("codigo")));
    }

    @Override
    public void finalizar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
