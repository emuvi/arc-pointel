package pin.adp;

import pin.adm.CadNegocios;
import pin.libbas.Dados;
import pin.modamk.CadRelacao;
import pin.modamk.Cadastro;
import pin.libamk.Cmp;
import pin.modamk.CadReferenciar;

public class CadProjetos extends Cadastro {

    public CadProjetos() throws Exception {
        super("projetos", "Projetos", new Cmp[]{
            new Cmp(1, 1, "codigo", "Código", Dados.Tp.Crs).mTamanho(8).botaDetalhe("chave", true),
            new Cmp(1, 3, "titulo", "Título", Dados.Tp.Crs).mTamanho(240),
            new Cmp(1, 5, "situacao", "Situação", Dados.Tp.Enu).botaOpcoes("(D) Descrição", "(P) Planejamento", "(E) Espera", "(R) Realização", "(A) Avaliação", "(T) Terminado", "(C) Cancelado").mVlrInicial("D"),
            new Cmp(2, 1, "prev_inicio", "Previsão de Início", Dados.Tp.DatHor).mDica("Previsão de Início"),
            new Cmp(2, 2, "prev_fim", "Previsão de Fim", Dados.Tp.DatHor).mDica("Previsão de Fim"),
            new Cmp(2, 3, "real_inicio", "Início da Realização", Dados.Tp.DatHor).mDica("Início da Realização"),
            new Cmp(2, 4, "real_fim", "Fim da Realização", Dados.Tp.DatHor).mDica("Fim da Realização"),
            new Cmp(3, 1, "descricao", "Descrição", Dados.Tp.Tex).mLargura(600).mAltura(180),
            new Cmp(4, 1, "negocio", "Negócio - Cód.", Dados.Tp.Crs).mTamanho(6),
            new Cmp(4, 2, "negocios.nome", "Negócio - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true)
        });
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        botaRelacao(new CadRelacao("negocios").botaRelacao("negocio", "codigo"));
        botaReferencia(new CadReferenciar(this, "negocio", CadNegocios.class).botaChave("negocio", "codigo").botaEstrangeiro("negocios.nome", "nome"));
    }

}
