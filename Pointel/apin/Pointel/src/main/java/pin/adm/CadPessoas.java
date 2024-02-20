package pin.adm;

import pin.libamk.Botao;
import pin.libamk.Cmp;
import pin.libbas.Conferencia;
import pin.libbas.Dados;
import pin.libbas.Erro;
import pin.libutl.UtlCrs;
import pin.modamk.CadReferenciar;
import pin.modamk.CadRelacao;
import pin.modamk.Cadastro;

public class CadPessoas extends Cadastro {

  public CadPessoas() throws Exception {
    super("pessoas", "Pessoas", new Cmp[]{
      new Cmp(1, 1, "Abs", Dados.Tp.Abs),
      new Cmp("Abs", 1, 1, "A1", "1 - Principal", Dados.Tp.Pai),
      new Cmp("Abs", 1, 2, "A2", "2 - Contato", Dados.Tp.Pai),
      new Cmp("Abs", 1, 3, "A3", "3 - Endereço", Dados.Tp.Pai),
      new Cmp("Abs", 1, 4, "A4", "4 - Vendas", Dados.Tp.Pai),
      new Cmp("A1", 1, 1, "codigo", "Código", Dados.Tp.Crs).mTamanho(8).botaDetalhe("chave", true),
      new Cmp("A1", 1, 2, "ativo", "Ativo", Dados.Tp.Log),
      new Cmp("A1", 1, 3, "potencial", "Potencial", Dados.Tp.Log),
      new Cmp("A1", 1, 4, "cliente", "Cliente", Dados.Tp.Log),
      new Cmp("A1", 1, 5, "fornecedor", "Fornecedor", Dados.Tp.Log),
      new Cmp("A1", 1, 6, "transportadora", "Transportadora", Dados.Tp.Log),
      new Cmp("A1", 1, 7, "colaborador", "Colaborador", Dados.Tp.Log),
      new Cmp("A1", 1, 8, "consultor", "Consultor", Dados.Tp.Log),
      new Cmp("A1", 2, 1, "nome", "Nome", Dados.Tp.Crs).mTamanho(80).botaObrigatorio(),
      new Cmp("A1", 2, 2, "fantasia", "Fantasia", Dados.Tp.Crs).mTamanho(60),
      new Cmp("A1", 2, 3, "natureza", "Natureza", Dados.Tp.Enu).botaOpcoes(new Object[]{"(F) Física", "(J) Jurídica"}),
      new Cmp("A1", 3, 1, "cnpjcpf", "CNPJ/CPF", Dados.Tp.Crs).mTamanho(20),
      new Cmp("A1", 3, 2, "insestadual", "Ins. Estadual", Dados.Tp.Crs).mTamanho(20),
      new Cmp("A1", 3, 3, "aniversario", "Aniversário", Dados.Tp.Dat),
      new Cmp("A1", 3, 4, "cadastro", "Cadastro", Dados.Tp.Dat).botaDetalhe("podeInserir", false).botaDetalhe("podeEditar", false),
      new Cmp("A1", 3, 5, "atualizacao", "Atualização", Dados.Tp.Dat),
      new Cmp("A1", 4, 1, "grupo", "Grupo - Cód.", Dados.Tp.Crs).mTamanho(4),
      new Cmp("A1", 4, 2, "grupos_pessoas.nome", "Grupo - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true),
      new Cmp("A1", 4, 3, "subgrupo", "SubGrupo - Cód.", Dados.Tp.Crs).mTamanho(4),
      new Cmp("A1", 4, 4, "subgrupos_pessoas.nome", "SubGrupo - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true),
      new Cmp("A2", 1, 1, "tratamento", "Tratamento", Dados.Tp.Sug).botaOpcoes(new Object[]{"Você", "Senhor", "Senhora"}).mLargura(70),
      new Cmp("A2", 1, 2, "contato", "Contato", Dados.Tp.Crs).mTamanho(45),
      new Cmp("A2", 1, 3, "cargo", "Cargo", Dados.Tp.Crs).mTamanho(45),
      new Cmp("A2", 1, 4, "contato_aniversario", "Cont. Aniversário", Dados.Tp.Dat),
      new Cmp("A2", 2, 1, "tipo_fone1", "Tipo Tel 1", Dados.Tp.Sug).botaOpcoes(new Object[]{"Pessoal", "Profissional"}).mLargura(80),
      new Cmp("A2", 2, 2, "fone1", "Telefone 1", Dados.Tp.Crs).mTamanho(25).mLargura(140),
      new Cmp("A2", 2, 3, "tipo_fone2", "Tipo Tel 2", Dados.Tp.Sug).botaOpcoes(new Object[]{"Pessoal", "Profissional"}).mLargura(80),
      new Cmp("A2", 2, 4, "fone2", "Telefone 2", Dados.Tp.Crs).mTamanho(25).mLargura(140),
      new Cmp("A2", 2, 5, "tipo_fone3", "Tipo Tel 3", Dados.Tp.Sug).botaOpcoes(new Object[]{"Pessoal", "Profissional"}).mLargura(80),
      new Cmp("A2", 2, 6, "fone3", "Telefone 3", Dados.Tp.Crs).mTamanho(25).mLargura(140),
      new Cmp("A2", 3, 1, "tipo_email1", "Tipo Mail 1", Dados.Tp.Sug).botaOpcoes(new Object[]{"Pessoal", "Profissional"}).mLargura(80),
      new Cmp("A2", 3, 2, "email1", "E-Mail 1", Dados.Tp.Crs).mTamanho(140).mLargura(160),
      new Cmp("A2", 3, 3, "tipo_email2", "Tipo Mail 2", Dados.Tp.Sug).botaOpcoes(new Object[]{"Pessoal", "Profissional"}).mLargura(80),
      new Cmp("A2", 3, 4, "email2", "E-Mail 2", Dados.Tp.Crs).mTamanho(140).mLargura(160),
      new Cmp("A2", 3, 5, "tipo_email3", "Tipo Mail 3", Dados.Tp.Sug).botaOpcoes(new Object[]{"Pessoal", "Profissional"}).mLargura(80),
      new Cmp("A2", 3, 6, "email3", "E-Mail 3", Dados.Tp.Crs).mTamanho(140).mLargura(160),
      new Cmp("A2", 4, 1, "tipo_website1", "Tipo Web 1", Dados.Tp.Sug).botaOpcoes(new Object[]{"Pessoal", "Profissional"}).mLargura(80),
      new Cmp("A2", 4, 2, "website1", "WebSite 1", Dados.Tp.Crs).mTamanho(210).mLargura(160),
      new Cmp("A2", 4, 3, "tipo_website2", "Tipo Web 2", Dados.Tp.Sug).botaOpcoes(new Object[]{"Pessoal", "Profissional"}).mLargura(80),
      new Cmp("A2", 4, 4, "website2", "WebSite 2", Dados.Tp.Crs).mTamanho(210).mLargura(160),
      new Cmp("A2", 4, 5, "tipo_website3", "Tipo Web 3", Dados.Tp.Sug).botaOpcoes(new Object[]{"Pessoal", "Profissional"}).mLargura(80),
      new Cmp("A2", 4, 6, "website3", "WebSite 3", Dados.Tp.Crs).mTamanho(210).mLargura(160),
      new Cmp("A3", 1, 1, "tipo_endereco", "Tipo End.:", Dados.Tp.Sug).botaOpcoes(new Object[]{"Pessoal", "Profissional"}),
      new Cmp("A3", 1, 2, "cep", "CEP", Dados.Tp.Crs).mTamanho(10),
      new Cmp("A3", 2, 1, "cidade", "Cidade - Cód.", Dados.Tp.Crs).mTamanho(6),
      new Cmp("A3", 2, 2, "cidades.pais", "Cidade - País", Dados.Tp.Crs).mTamanho(4).botaDetalhe("estrangeiro", true),
      new Cmp("A3", 2, 3, "cidades.estado", "Cidade - Estado", Dados.Tp.Crs).mTamanho(4).botaDetalhe("estrangeiro", true),
      new Cmp("A3", 2, 4, "cidades.nome", "Cidade - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true),
      new Cmp("A3", 3, 1, "bairro", "Bairro - Cód.", Dados.Tp.Crs).mTamanho(4),
      new Cmp("A3", 3, 2, "bairros.nome", "Bairro - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true),
      new Cmp("A3", 3, 3, "regiao", "Região - Cód.", Dados.Tp.Crs).mTamanho(4),
      new Cmp("A3", 3, 4, "regioes.nome", "Região - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true),
      new Cmp("A3", 4, 1, "logradouro", "Logradouro", Dados.Tp.Sug).botaOpcoes(new Object[]{"R.", "AV.", "AL.", "LOT.", "TV.", "SER.", "ROD.", "PCA."}),
      new Cmp("A3", 4, 2, "endereco", "Endereço", Dados.Tp.Crs).mTamanho(80),
      new Cmp("A3", 4, 3, "numero", "Número", Dados.Tp.Crs).mTamanho(10),
      new Cmp("A3", 4, 4, "complemento", "Complemento", Dados.Tp.Crs).mTamanho(50),
      new Cmp("A4", 1, 1, "representante", "Representante - Cód.", Dados.Tp.Crs).mTamanho(8),
      new Cmp("A4", 1, 2, "representantes.nome", "Representante - Nome", Dados.Tp.Crs).mTamanho(80).botaDetalhe("estrangeiro", true),
      new Cmp("A4", 1, 3, "cond_pagamento", "Cond. Pgto - Cód.", Dados.Tp.Crs).mTamanho(4),
      new Cmp("A4", 1, 4, "condicoes_pagamento.nome", "Cond. Pgto - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true),
      new Cmp("A4", 1, 5, "optante", "Optante", Dados.Tp.Log),
      new Cmp("A4", 2, 1, "tabela_preco", "Tab. de Preço - Cód.", Dados.Tp.Crs).mTamanho(6),
      new Cmp("A4", 2, 2, "tabelas_precos.nome", "Tab. de Preço - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true),
      new Cmp("A4", 2, 3, "tabela_secundaria", "Tab. Secundária - Cód.", Dados.Tp.Crs).mTamanho(6),
      new Cmp("A4", 2, 4, "tabelas_secundarias.nome", "Tab. Secundária - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true),
      new Cmp("A4", 3, 1, "credito", "Crédito - Cód.", Dados.Tp.Crs).mTamanho(6),
      new Cmp("A4", 3, 2, "creditos.nome", "Crédito - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true),
      new Cmp("A4", 3, 3, "credito_obs", "Obs. do Crédito", Dados.Tp.Crs).mTamanho(250).mLargura(300),
      new Cmp("A4", 3, 4, "bloqueado", "Bloqueado", Dados.Tp.Log),
      new Cmp("A4", 4, 1, "negocio", "Negócio - Cód.", Dados.Tp.Crs).mTamanho(6),
      new Cmp("A4", 4, 2, "negocios.nome", "Negócio - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true),
      new Cmp("A4", 4, 3, "obs", "Obs", Dados.Tp.Crs).mTamanho(120),});
  }

  @Override
  public void preparaEstr() throws Exception {
    botaRelacao(new CadRelacao("grupos_pessoas").botaRelacao("grupo", "codigo"));
    botaRelacao(new CadRelacao("subgrupos_pessoas").botaRelacao("grupo", "grupo").botaRelacao("subgrupo", "codigo"));
    botaRelacao(new CadRelacao("cidades").botaRelacao("cidade", "codigo"));
    botaRelacao(new CadRelacao("bairros").botaRelacao("cidade", "cidade").botaRelacao("bairro", "codigo"));
    botaRelacao(new CadRelacao("regioes").botaRelacao("regiao", "codigo"));
    botaRelacao(new CadRelacao("representantes", "pessoas").botaRelacao("representante", "codigo"));
    botaRelacao(new CadRelacao("condicoes_pagamento").botaRelacao("cond_pagamento", "codigo"));
    botaRelacao(new CadRelacao("tabelas_precos").botaRelacao("tabela_preco", "codigo"));
    botaRelacao(new CadRelacao("tabelas_secundarias", "tabelas_precos").botaRelacao("tabela_secundaria", "codigo"));
    botaRelacao(new CadRelacao("creditos").botaRelacao("credito", "codigo"));
    botaReferencia(new CadReferenciar(this, "grupo", CadGruposPessoas.class).botaChave("grupo", "codigo").botaEstrangeiro("grupos_pessoas.nome", "nome"));
    botaReferencia(new CadReferenciar(this, "subgrupo", CadSubGruposPessoas.class).botaChaveFixo("grupo", "grupo").botaEstranFixo("grupos_pessoas.nome", "grupos_pessoas.nome").botaChave("subgrupo", "codigo").botaEstrangeiro("subgrupos_pessoas.nome", "nome"));
    botaReferencia(new CadReferenciar(this, "cidade", CadCidades.class).botaChave("cidade", "codigo").botaEstrangeiro("cidades.pais", "pais").botaEstrangeiro("cidades.estado", "estado").botaEstrangeiro("cidades.nome", "nome"));
    botaReferencia(new CadReferenciar(this, "bairro", CadBairros.class).botaChaveFixo("cidade", "cidade").botaEstranFixo("cidades.pais", "cidades.pais").botaEstranFixo("cidades.estado", "cidades.estado").botaEstranFixo("cidades.nome", "cidades.nome").botaChave("bairro", "codigo").botaEstrangeiro("bairros.nome", "nome"));
    botaReferencia(new CadReferenciar(this, "regiao", CadRegioes.class).botaChave("regiao", "codigo").botaEstrangeiro("regioes.nome", "nome"));
    botaReferencia(new CadReferenciar(this, "representante", CadPessoas.class).botaChave("representante", "codigo").botaEstrangeiro("representantes.nome", "nome"));
    botaReferencia(new CadReferenciar(this, "cond_pagamento", CadCondicoesPagamento.class).botaChave("cond_pagamento", "codigo").botaEstrangeiro("condicoes_pagamento.nome", "nome"));
    botaReferencia(new CadReferenciar(this, "tabela_preco", CadTabelasPrecos.class).botaChave("tabela_preco", "codigo").botaEstrangeiro("tabelas_precos.nome", "nome"));
    botaReferencia(new CadReferenciar(this, "tabela_secundaria", CadTabelasPrecos.class).botaChave("tabela_secundaria", "codigo").botaEstrangeiro("tabelas_secundarias.nome", "nome"));
    botaReferencia(new CadReferenciar(this, "credito", CadCreditos.class).botaChave("credito", "codigo").botaEstrangeiro("creditos.nome", "nome"));
    botaRelacao(new CadRelacao("negocios").botaRelacao("negocio", "codigo"));
    botaReferencia(new CadReferenciar(this, "negocio", CadNegocios.class).botaChave("negocio", "codigo").botaEstrangeiro("negocios.nome", "nome"));
  }

  @Override
  public void preparaIntf() throws Exception {
    botaParaAtualizar("cadastro");
    pegaCampo("cnpjcpf").botaConferencia(new CnfCNPJCPF());
    botaBotao(new BotComissoes());
    botaBotao(new BotRegistros());
  }

  private class CnfCNPJCPF extends Conferencia {

    @Override
    public void confere(Object comOrigem) throws Exception {
      Boolean retorno = true;
      if (ehModoNovoOuEditar()) {
        if (!pegaCampo("cnpjcpf").pEdt().vazio()) {
          if ("J".equals(pegaCampo("natureza").pEdt().pgVlr().pgCrs("F"))) {
            retorno = UtlCrs.ehCNPJValido(pegaCampo("cnpjcpf").pEdt().pgVlr().pgCrs());
          } else {
            retorno = UtlCrs.ehCPFValido(pegaCampo("cnpjcpf").pEdt().pgVlr().pgCrs());
          }
        }
      }
      if (!retorno) {
        throw new Erro("CNPJ/CPF Incorreto");
      }
    }
  }

  private class BotComissoes extends Botao {

    public BotComissoes() {
      super("Comissões", 'C');
    }

    @Override
    public void aoExecutar(Object comOrigem) throws Exception {
      if (confirmar(true)) {
        CadPessoasComissoes filho = new CadPessoasComissoes();
        filho.pegaCampo("pessoa").mVlrFixo(pegaCampo("codigo").pEdt().pgVlr());
        filho.pegaCampo("pessoas.nome").mVlrFixo(pegaCampo("nome").pEdt().pgVlr());
        filho.mostra();
      }
    }
  }

  private class BotRegistros extends Botao {

    public BotRegistros() {
      super("Registros", 'R');
    }

    @Override
    public void aoExecutar(Object comOrigem) throws Exception {

    }
  }

}
