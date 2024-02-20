package pin.modinf;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import pin.libamg.EdtArq;
import pin.libamk.Cmp;
import pin.libbas.Conjunto;
import pin.libbas.Dados;
import pin.libutl.UtlLis;
import pin.libvlr.Vlrs;

public class Postgre extends Banco {

    public Postgre(String comNome, String noServidor, Integer ePorta, String daBase, String comUsuario, String eSenha) {
        super(comNome, Bancos.Tp.PostgreSQL, noServidor, ePorta, daBase, comUsuario, eSenha);
    }

    @Override
    public Boolean conecta() throws Exception {
        Class.forName("org.postgresql.Driver");
        String conStr = "jdbc:postgresql://" + pegaServidor() + ":" + pegaPorta() + "/" + pegaBase();
        botaLink(DriverManager.getConnection(conStr, pegaUsuario(), pegaSenha()));
        link().setAutoCommit(true);
        return true;
    }

    @Override
    public void carregaTabelas(Tabelas nasTabelas) throws Exception {
        Conjunto rst = busca("SELECT table_schema, table_name "
                + " FROM information_schema.tables "
                + " WHERE table_schema != 'information_schema' "
                + " AND table_schema != 'pg_catalog' "
                + " ORDER BY table_schema, table_name");
        while (rst.proximo()) {
            nasTabelas.bota(new Tabela(this, rst.pgVlr(1).pgCrs(), rst.pgVlr(2).pgCrs()).mDasTabelas(nasTabelas));
        }
    }

    @Override
    public void carregaCampos(Tabela daTabela) throws Exception {
        List<TabCampo> campos = new ArrayList<>();
        Conjunto rstCols = busca("SELECT column_name, data_type, character_maximum_length, "
                + "   numeric_precision, numeric_scale, is_nullable, column_default, ordinal_position "
                + " FROM information_schema.columns "
                + " WHERE table_schema = ? "
                + " AND table_name = ? "
                + " ORDER BY ordinal_position",
                new Vlrs(daTabela.pEsquema(), daTabela.pNome()));
        while (rstCols.proximo()) {
            TabCampo campo = new TabCampo(rstCols.pgVlr(8).pgInt(), rstCols.pgVlr(1).pgCrs());
            String ptp = rstCols.pgVlr(2).pgCrs();
            if (ptp.contains(" ")) {
                ptp = ptp.split("\\s+")[0];
            }
            switch (ptp) {
                case "boolean":
                    campo.mTipo(Dados.Tp.Log);
                    break;
                case "character":
                    campo.mTipo(Dados.Tp.Crs);
                    campo.mTamanho(rstCols.pgVlr(3).pgInt());
                    break;
                case "integer":
                case "smallint":
                case "smallserial":
                case "serial":
                    campo.mTipo(Dados.Tp.Int);
                    break;
                case "bigint":
                case "bigserial":
                case "uuid":
                    campo.mTipo(Dados.Tp.IntLon);
                    break;
                case "real":
                    campo.mTipo(Dados.Tp.Num);
                    campo.mTamanho(rstCols.pgVlr(4).pgInt());
                    campo.bmPrecisao(rstCols.pgVlr(5).pgInt());
                    break;
                case "double":
                case "money":
                case "numeric":
                    campo.mTipo(Dados.Tp.NumLon);
                    campo.mTamanho(rstCols.pgVlr(4).pgInt());
                    campo.bmPrecisao(rstCols.pgVlr(5).pgInt());
                    break;
                case "date":
                    campo.mTipo(Dados.Tp.Dat);
                    break;
                case "time":
                    campo.mTipo(Dados.Tp.Hor);
                    break;
                case "interval":
                case "timestamp":
                    campo.mTipo(Dados.Tp.DatHor);
                    break;
                case "bytea":
                case "text":
                    campo.mTipo(Dados.Tp.Arq);
                    break;
            }
            if (rstCols.pgVlr(6) != null) {
                campo.mObrigatorio(rstCols.pgVlr(6).pgCrs("").equals("YES"));
            }
            campo.mPadrao(rstCols.pgVlr(7, "").pgCrs());
            campos.add(campo);
        }
        Conjunto rstCha = busca("SELECT kcu.column_name "
                + " FROM information_schema.tables t "
                + " LEFT JOIN information_schema.table_constraints tc "
                + "   ON tc.table_catalog = t.table_catalog "
                + "   AND tc.table_schema = t.table_schema "
                + "   AND tc.table_name = t.table_name "
                + "   AND tc.constraint_type = 'PRIMARY KEY' "
                + " LEFT JOIN information_schema.key_column_usage kcu "
                + "   ON kcu.table_catalog = tc.table_catalog "
                + "   AND kcu.table_schema = tc.table_schema "
                + "   AND kcu.table_name = tc.table_name "
                + "   AND kcu.constraint_name = tc.constraint_name "
                + " WHERE t.table_schema = ? "
                + " AND t.table_name = ? "
                + " ORDER BY kcu.ordinal_position ",
                new Vlrs(daTabela.pEsquema(), daTabela.pNome()));
        while (rstCha.proximo()) {
            for (TabCampo cmp : campos) {
                if (Objects.equals(cmp.pNome(), rstCha.pgVlr(1).pgCrs())) {
                    cmp.botaChave();
                    break;
                }
            }
        }
        daTabela.mCampos(campos);
    }

    @Override
    public void carregaEstrangeiros(Tabela daTabela) throws Exception {
        List<TabEstrangeiro> estrangeiros = new ArrayList<>();
        Conjunto rstEst = busca("SELECT tc.constraint_name "
                + "FROM information_schema.table_constraints AS tc "
                + "WHERE tc.constraint_type = 'FOREIGN KEY' "
                + "AND tc.table_schema = ? AND tc.table_name = ?",
                new Vlrs(daTabela.pEsquema(), daTabela.pNome()));
        while (rstEst.proximo()) {
            String cnsNome = rstEst.pgVlr(1).pgCrs();
            Conjunto rstReferenciados = busca("SELECT kcu.column_name "
                    + "FROM information_schema.key_column_usage AS kcu "
                    + "WHERE kcu.constraint_name = ?",
                    new Vlrs(cnsNome));
            Conjunto rstReferencias = busca("SELECT ccu.table_schema, ccu.table_name, ccu.column_name "
                    + "FROM information_schema.constraint_column_usage AS ccu "
                    + "WHERE ccu.constraint_name = ?",
                    new Vlrs(cnsNome));
            TabEstrangeiro tabEst = new TabEstrangeiro(cnsNome);
            boolean primeiro = true;
            while (rstReferencias.proximo()) {
                if (primeiro) {
                    tabEst.mEsquema(rstReferencias.pgVlr(1).pgCrs());
                    tabEst.mTabela(rstReferencias.pgVlr(2).pgCrs());
                    primeiro = false;
                }
                String referencia = rstReferencias.pgVlr(3).pgCrs();
                if (rstReferenciados.proximo()) {
                    String referenciado = rstReferenciados.pgVlr(1).pgCrs();
                    tabEst.botaRelacao(referenciado, referencia);
                }
            }
            estrangeiros.add(tabEst);
        }
        daTabela.mEstrangeiros(estrangeiros);
    }

    @Override
    public void carregaIndices(Tabela daTabela) throws Exception {
        List<TabIndice> indices = new ArrayList<>();
        Conjunto rstInd = busca(" SELECT\n"
                + "  tnsp.nspname AS schema_name,\n"
                + "  trel.relname AS table_name,\n"
                + "  irel.relname AS index_name,\n"
                + "  array_to_string(array_agg (\n"
                + "     a.attname \n"
                + "  || '-' || CASE o.option & 1 WHEN 1 THEN 'DESC' ELSE 'ASC' END\n"
                + "  || '-' || CASE o.option & 2 WHEN 2 THEN 'NULLS FIRST' ELSE 'NULLS LAST' END\n"
                + "    ORDER BY c.ordinality\n"
                + "  ), ';') AS columns\n"
                + " FROM pg_index AS i\n"
                + " JOIN pg_class AS trel ON trel.oid = i.indrelid\n"
                + " JOIN pg_namespace AS tnsp ON trel.relnamespace = tnsp.oid\n"
                + " JOIN pg_class AS irel ON irel.oid = i.indexrelid\n"
                + " CROSS JOIN LATERAL unnest (i.indkey) WITH ORDINALITY AS c (colnum, ordinality)\n"
                + " LEFT JOIN LATERAL unnest (i.indoption) WITH ORDINALITY AS o (option, ordinality)\n"
                + "   ON c.ordinality = o.ordinality\n"
                + " JOIN pg_attribute AS a ON trel.oid = a.attrelid AND a.attnum = c.colnum\n"
                + " WHERE tnsp.nspname = ? AND trel.relname = ?"
                + " GROUP BY tnsp.nspname, trel.relname, irel.relname",
                new Vlrs(daTabela.pEsquema(), daTabela.pNome()));
        while (rstInd.proximo()) {
            TabIndice ind = new TabIndice(rstInd.pgVlr("index_name").pgCrs());
            String colunas = rstInd.pgVlr("columns").pgCrs();
            String[] cols = colunas.split("\\;");
            for (String col : cols) {
                String[] colPts = col.split("\\-");
                TabIndiceColuna indCol = new TabIndiceColuna(colPts[0], "ASC".equals(colPts[1]), "NULLS FIRST".equals(colPts[2]));
                ind.botaColuna(indCol);
            }
            indices.add(ind);
        }
        daTabela.mIndices(indices);
    }

    @Override
    public void carregaVerificadores(Tabela daTabela) throws Exception {
        List<TabVerificador> vers = new ArrayList<>();
        String sql = "SELECT con.conname AS ver_nome, con.consrc AS ver_codigo\n"
                + "  FROM pg_catalog.pg_constraint con\n"
                + "    INNER JOIN pg_catalog.pg_class rel\n"
                + "      ON rel.oid = con.conrelid\n"
                + "    INNER JOIN pg_catalog.pg_namespace nsp\n"
                + "      ON nsp.oid = connamespace\n"
                + "  WHERE nsp.nspname = ?\n"
                + "    AND rel.relname = ?\n"
                + "    AND con.contype = 'c'";
        Conjunto rstVer = busca(sql, new Vlrs(daTabela.pEsquema(), daTabela.pNome()));
        while (rstVer.proximo()) {
            vers.add(new TabVerificador(rstVer.pgVlr("ver_nome").pgCrs(), rstVer.pgVlr("ver_codigo").pgCrs()));
        }
        daTabela.mVerificadores(vers);
    }

    @Override
    public List<String> pUsuarios() throws Exception {
        List<String> retorno = new ArrayList<>();
        Conjunto rst = busca("SELECT usename "
                + " FROM pg_catalog.pg_user");
        while (rst.proximo()) {
            retorno.add(rst.pgVlr(1, "").pgCrs());
        }
        return retorno;
    }

    @Override
    public Postgre replica() throws Exception {
        Postgre retorno = new Postgre(pegaNome() + " Replicado", pegaServidor(), pegaPorta(), pegaBase(), pegaUsuario(), pegaSenha());
        retorno.botaAoConectar(aoConectar());
        retorno.botaAoDesconectar(aoDesconectar());
        retorno.botaAoExecutar(aoExecutar());
        retorno.botaAoProcurar(aoProcurar());
        retorno.conecta();
        return retorno;
    }

    @Override
    public Postgre replicaLeve() throws Exception {
        Postgre retorno = new Postgre(pegaNome() + " Replicado", pegaServidor(), pegaPorta(), pegaBase(), pegaUsuario(), pegaSenha());
        retorno.conecta();
        return retorno;
    }

    @Override
    public Postgre iniciaTransacao() throws Exception {
        Postgre retorno = new Postgre(pegaNome() + " Transação", pegaServidor(), pegaPorta(), pegaBase(), pegaUsuario(), pegaSenha());
        retorno.botaAoConectar(aoConectar());
        retorno.botaAoDesconectar(aoDesconectar());
        retorno.botaAoExecutar(aoExecutar());
        retorno.botaAoProcurar(aoProcurar());
        retorno.conecta();
        retorno.link().setAutoCommit(false);
        retorno.opera("BEGIN");
        return retorno;
    }

    @Override
    public String pCriacao(Cmp doCampo) {
        String retorno = doCampo.pNome();
        switch (doCampo.pTipo()) {
            case Log:
            case Cr:
                retorno += " VARCHAR(1)";
                break;
            case Cor:
                doCampo.mTamanho(9);
            case Enu:
                if (doCampo.pTamanho() == null || doCampo.pTamanho() == 0) {
                    doCampo.mTamanho(3);
                }
            case Crs:
            case Sen:
            case Sug:
                if (doCampo.pTamanho() == null || doCampo.pTamanho() == 0) {
                    doCampo.mTamanho(45);
                }
                retorno += " VARCHAR(" + doCampo.pTamanho() + ")";
                break;
            case Int:
                retorno += " INTEGER";
                break;
            case IntLon:
                retorno += " BIGINT";
                break;
            case Num:
            case NumLon:
                if (doCampo.pTamanho() == null || doCampo.pTamanho() == 0) {
                    doCampo.mTamanho(12);
                }
                if (doCampo.pPrecisao() == null || doCampo.pPrecisao() == 0) {
                    doCampo.mPrecisao(12);
                }
                retorno += " NUMERIC(" + doCampo.pTamanho() + "," + doCampo.pPrecisao() + ")";
                break;
            case Ser:
                retorno += " SERIAL";
                break;
            case SerLon:
                retorno += " BIGSERIAL";
                break;
            case Dat:
                retorno += " DATE";
                break;
            case Hor:
                retorno += " TIME";
                break;
            case DatHor:
            case Mom:
                retorno += " TIMESTAMP";
                break;
            case Esc:
            case Lis:
            case Tex:
            case For:
            case Ima:
            case Pla:
            case Doc:
            case Aud:
            case Vid:
            case Frm:
            case Apr:
                retorno += " TEXT";
                break;
            case Arq:
                if (UtlLis.tem(EdtArq.Params.SO_CAMINHO, doCampo.pParams())) {
                    if (doCampo.pTamanho() == null || doCampo.pTamanho() == 0) {
                        doCampo.mTamanho(210);
                    }
                    retorno += " VARCHAR(" + doCampo.pTamanho() + ")";
                } else {
                    retorno += " TEXT";
                }
                break;
        }
        if (doCampo.pVlrPadrao() != null) {
            if (!doCampo.pVlrPadrao().isEmpty()) {
                retorno += " DEFAULT " + doCampo.pVlrPadrao();
            }
        }

        return retorno;
    }

}
