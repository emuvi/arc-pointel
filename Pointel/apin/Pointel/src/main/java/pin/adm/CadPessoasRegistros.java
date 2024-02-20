package pin.adm;

import pin.libbas.Dados;
import pin.modamk.CadRelacao;
import pin.modamk.Cadastro;
import pin.libamk.Cmp;
import pin.modamk.CadReferenciar;

public class CadPessoasRegistros extends Cadastro {

    public CadPessoasRegistros() throws Exception {
        super("pessoas_registros", "Registros das Pessoas", new Cmp[]{
            new Cmp(1, 1, "pessoa", "Pessoa - Cód.", Dados.Tp.Crs).mTamanho(8).botaDetalhe("chave", true),
            new Cmp(1, 2, "pessoas.nome", "Pessoa - Nome", Dados.Tp.Crs).mTamanho(80).botaDetalhe("estrangeiro", true),
            new Cmp(1, 3, "codigo", "Código", Dados.Tp.Crs).mTamanho(6).botaDetalhe("chave", true),
            new Cmp(2, 1, "data", "Data", Dados.Tp.Dat).mVlrPadrao("CURRENT_DATE"),
            new Cmp(2, 2, "hora", "Hora", Dados.Tp.Hor).mVlrPadrao("CURRENT_TIME"),
            new Cmp(2, 3, "registro_tipo", "Tipo do Registro - Cód.", Dados.Tp.Crs).mTamanho(4),
            new Cmp(2, 4, "registros_tipos.nome", "Tipo do Registro - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true),
            new Cmp(2, 5, "indicador", "Indicador", Dados.Tp.NumLon).mTamanhoPrecisao(12, 3),
            new Cmp(3, 1, "obs", "Observação", Dados.Tp.Tex).mLargura(720),
            new Cmp(4, 1, "negocio", "Negócio - Cód.", Dados.Tp.Crs).mTamanho(6),
            new Cmp(4, 2, "negocios.nome", "Negócio - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true)
        });
    }

    @Override
    public void preparaEstr() throws Exception {
        botaRelacao(new CadRelacao("pessoas").botaRelacao("pessoa", "codigo"));
        botaRelacao(new CadRelacao("registros_tipos").botaRelacao("registro_tipo", "codigo"));
        botaReferencia(new CadReferenciar(this, "pessoa", CadPessoas.class).botaChave("pessoa", "codigo").botaEstrangeiro("pessoas.nome", "nome"));
        botaReferencia(new CadReferenciar(this, "registro_tipo", CadRegistrosTipos.class).botaChave("registro_tipo", "codigo").botaEstrangeiro("registros_tipos.nome", "nome"));
        botaRelacao(new CadRelacao("negocios").botaRelacao("negocio", "codigo"));
        botaReferencia(new CadReferenciar(this, "negocio", CadNegocios.class).botaChave("negocio", "codigo").botaEstrangeiro("negocios.nome", "nome"));
    }
}
