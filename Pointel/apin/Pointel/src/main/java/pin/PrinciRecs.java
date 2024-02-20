package pin;

public class PrinciRecs {

  public static void inicia() {
    iniciaRecursos();
  }

  private static void iniciaRecursos() {
    Pointel.mainRecs.bota(null, "Pointel", "Pointel.png", 'P', pin.Pointel.class).botaPrincipal();

    Pointel.mainRecs.bota("Pointel", "PointelAdmin", "PointelAdmin.png", 'A', pin.adm.PointelAdmin.class).botaPrincipal();
    Pointel.mainRecs.bota("Pointel.PointelAdmin", "Empresa", "Empresa.png");
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Empresa", "Relatórios");
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Empresa.Relatórios", "Pessoas", pin.adm.RelPessoas.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Empresa.Relatórios", "Clientes Ociosos", pin.adm.RelClientesOciosos.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Empresa", "Cadastros");
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Empresa.Cadastros", "Meus Clientes", pin.adm.CadMeusClientes.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Empresa.Cadastros", "Pessoas", pin.adm.CadPessoas.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Empresa.Cadastros", "Grupos de Pessoas", pin.adm.CadGruposPessoas.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Empresa.Cadastros", "SubGrupos de Pessoas", pin.adm.CadSubGruposPessoas.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Empresa.Cadastros", "Tipos de Registros", pin.adm.CadRegistrosTipos.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Empresa.Cadastros", "Regiões", pin.adm.CadRegioes.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Empresa.Cadastros", "Bairros", pin.adm.CadBairros.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Empresa.Cadastros", "Cidades", pin.adm.CadCidades.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Empresa.Cadastros", "Estados", pin.adm.CadEstados.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Empresa.Cadastros", "Países", pin.adm.CadPaises.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Empresa.Cadastros", "Negócios", pin.adm.CadNegocios.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Empresa", "Rotinas");
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Empresa.Rotinas", "Limite de Crédito", pin.adm.EmpRotLimiteCredito.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin", "Provisões", "Provisoes.png");
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Provisões", "Relatórios");
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Provisões.Relatórios", "Produtos", pin.adm.RelProdutos.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Provisões.Relatórios", "Saídas dos Produtos", pin.adm.RelSaidasProdutos.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Provisões", "Operações");
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Provisões", "Cadastros");
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Provisões.Cadastros", "Meus Produtos", pin.adm.CadMeusProdutos.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Provisões.Cadastros", "Produtos", pin.adm.CadProdutos.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Provisões.Cadastros", "Grupos de Produtos", pin.adm.CadGruposProdutos.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Provisões.Cadastros", "SubGrupos de Produtos", pin.adm.CadSubGruposProdutos.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Provisões.Cadastros", "Unidades", pin.adm.CadUnidades.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Provisões.Cadastros", "Lista de EMails", pin.adm.CadListaEMails.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Provisões.Cadastros", "Departamentos", pin.adm.CadDepartamentos.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Provisões.Cadastros", "Centros", pin.adm.CadCentros.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Provisões.Cadastros", "Setores", pin.adm.CadSetores.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Provisões.Cadastros", "SubSetores", pin.adm.CadSubSetores.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Provisões", "Rotinas");
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Provisões.Rotinas", "Ajustar Preços", pin.adm.RotAjustarPrecos.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin", "Negócios", "Negocios.png");
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Negócios", "Relatórios");
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Negócios.Relatórios", "Tabelas de Preços", pin.adm.RelTabelasPrecos.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Negócios.Relatórios", "Total de Pedidos", pin.adm.RelPedidosTotal.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Negócios", "Operações");
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Negócios.Operações", "Pré-Pedidos", pin.adm.CadPrePedidos.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Negócios.Operações", "Itens dos Pré-Pedidos", pin.adm.CadPrePedidosItens.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Negócios.Operações", "Mobile Arquivos", pin.adm.MonMobileArquivos.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Negócios.Operações", "Pedidos", pin.adm.CadPedidos.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Negócios.Operações", "Itens dos Pedidos", pin.adm.CadPedidosItens.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Negócios.Operações", "Efetivar Pedidos", pin.adm.VenOpePedidosEfetivar.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Negócios.Operações", "Faturas", pin.adm.CadFaturas.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Negócios.Operações", "Itens das Faturas", pin.adm.CadFaturasItens.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Negócios.Operações", "Trocas", pin.adm.CadTrocas.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Negócios", "Cadastros");
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Negócios.Cadastros", "Condições de Pagamento", pin.adm.CadCondicoesPagamento.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Negócios.Cadastros", "Minhas Tabelas de Preços", pin.adm.CadMinhasTabelasPrecos.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Negócios.Cadastros", "Tabelas de Preços", pin.adm.CadTabelasPrecos.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Negócios.Cadastros", "Mobile Vendedores", pin.adm.CadMobileVendedores.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Negócios.Cadastros", "Séries", pin.adm.CadSeries.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Negócios", "Consultas");
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Negócios.Consultas", "Pedidos", pin.adm.ConPedidos.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Negócios.Consultas", "Itens dos Pedidos", pin.adm.ConPedidosItens.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Negócios", "Rotinas");
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Negócios.Rotinas", "Desbloquear Fatura", pin.adm.RotDesbloquearFatura.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Negócios.Rotinas", "Desbloquear Pedido", pin.adm.RotDesbloquearPedido.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Negócios.Rotinas", "Desbloquear Pré-Pedido", pin.adm.RotDesbloquearPrePedido.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Negócios.Rotinas", "Cancelamento", pin.adm.RotCancelamento.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Negócios.Rotinas", "Excluir Pendentes", pin.adm.RotExcluirPendentes.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin", "Processamento", "Processamento.png");
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Processamento", "Relatórios");
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Processamento.Relatórios", "Caixa", pin.adm.RelCaixa.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Processamento.Relatórios", "Contas a Pagar", pin.adm.RelContasPagar.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Processamento.Relatórios", "Contas a Receber", pin.adm.RelContasReceber.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Processamento.Relatórios", "Comissões", pin.adm.RelComissoes.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Processamento", "Operações");
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Processamento.Operações", "Liquidar", pin.adm.FinOpeLiquidar.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Processamento.Operações", "Lançamentos", pin.adm.CadLancamentos.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Processamento", "Cadastros");
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Processamento.Cadastros", "Históricos", pin.adm.CadHistoricos.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Processamento.Cadastros", "Contas", pin.adm.CadContas.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Processamento.Cadastros", "Grupos de Contas", pin.adm.CadGruposContas.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Processamento.Cadastros", "SubGrupos de Contas", pin.adm.CadSubGruposContas.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Processamento.Cadastros", "Tipos de Pagamento", pin.adm.CadTiposPagamento.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Processamento.Cadastros", "Portadores", pin.adm.CadPortadores.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Processamento.Cadastros", "Créditos", pin.adm.CadCreditos.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Processamento.Cadastros", "Mercâncias", pin.adm.CadMercancias.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Processamento.Cadastros", "Moedas", pin.adm.CadMoedas.class);
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Processamento", "Rotinas");
    Pointel.mainRecs.bota("Pointel.PointelAdmin.Processamento.Rotinas", "Transferir Saldo", pin.adm.RotTransferirSaldo.class);

    Pointel.mainRecs.bota("Pointel", "PointelAdPro", "PointelAdPro.png", 'P', pin.adp.PointelAdPro.class).botaPrincipal();
    Pointel.mainRecs.bota("Pointel.PointelAdPro", "Projetos", "Projetos.png", pin.adp.CadProjetos.class);
    Pointel.mainRecs.bota("Pointel.PointelAdPro", "Agenda", "Agenda.png", pin.adp.Agenda.class);
    Pointel.mainRecs.bota("Pointel.PointelAdPro.Agenda", "Dia", "Dia.png", pin.adp.AgendaDia.class);
    Pointel.mainRecs.bota("Pointel.PointelAdPro.Agenda", "Semana", "Semana.png", pin.adp.AgendaSemana.class);
    Pointel.mainRecs.bota("Pointel.PointelAdPro.Agenda", "Mês", "Mes.png", pin.adp.AgendaMes.class);
    Pointel.mainRecs.bota("Pointel.PointelAdPro.Agenda", "Ano", "Ano.png", pin.adp.AgendaAno.class);
    Pointel.mainRecs.bota("Pointel.PointelAdPro", "Comunicação", "Comunicacao.png", pin.adp.Comunicacao.class);
    Pointel.mainRecs.bota("Pointel.PointelAdPro", "Arquivos", "Arquivos.png", pin.adp.Arquivos.class);

    Pointel.mainRecs.bota("Pointel", "PointelEduca", "PointelEduca.png", 'E', pin.edu.PointelEduca.class).botaPrincipal();
    Pointel.mainRecs.bota("Pointel.PointelEduca", "Coordenação", "Coordenacao.png", pin.edu.Coordenacao.class);
    Pointel.mainRecs.bota("Pointel.PointelEduca", "Professores", "Professores.png", pin.edu.Professores.class);
    Pointel.mainRecs.bota("Pointel.PointelEduca", "Alunos", "Alunos.png", pin.edu.Alunos.class);
    Pointel.mainRecs.bota("Pointel.PointelEduca", "Processamento", "Processamento.png", pin.edu.Processamento.class);

    Pointel.mainRecs.bota("Pointel", "PointelBiblio", "PointelBiblio.png", 'B', pin.bib.PointelBiblio.class).botaPrincipal();
    Pointel.mainRecs.bota("Pointel.PointelBiblio", "Empréstimos", "Emprestimos.png", pin.bib.Emprestimos.class);
    Pointel.mainRecs.bota("Pointel.PointelBiblio", "Obras", "Obras.png", pin.bib.Obras.class);
    Pointel.mainRecs.bota("Pointel.PointelBiblio", "Periódicos", "Periodicos.png", pin.bib.Periodicos.class);
    Pointel.mainRecs.bota("Pointel.PointelBiblio", "Processamento", "Processamento.png");
    Pointel.mainRecs.bota("Pointel.PointelBiblio.Processamento", "Relatórios");
    Pointel.mainRecs.bota("Pointel.PointelBiblio.Processamento", "Operações");
    Pointel.mainRecs.bota("Pointel.PointelBiblio.Processamento.Operações", "Traduções", pin.bib.CadTraducoes.class);
    Pointel.mainRecs.bota("Pointel.PointelBiblio.Processamento.Operações", "Semânticas", pin.bib.CadSemanticas.class);
    Pointel.mainRecs.bota("Pointel.PointelBiblio.Processamento.Operações", "Sintaxes", pin.bib.CadSintaxes.class);
    Pointel.mainRecs.bota("Pointel.PointelBiblio.Processamento", "Cadastros");
    Pointel.mainRecs.bota("Pointel.PointelBiblio.Processamento.Cadastros", "Autores", pin.bib.CadAutores.class);
    Pointel.mainRecs.bota("Pointel.PointelBiblio.Processamento.Cadastros", "Assuntos", pin.bib.CadAssuntos.class);
    Pointel.mainRecs.bota("Pointel.PointelBiblio.Processamento.Cadastros", "Idiomas", pin.bib.CadIdiomas.class);
    Pointel.mainRecs.bota("Pointel.PointelBiblio.Processamento", "Rotinas");
    Pointel.mainRecs.bota("Pointel.PointelBiblio.Processamento.Rotinas", "Midiateca", pin.bib.DocaMidiateca.class);

    Pointel.mainRecs.bota("Pointel", "PointelJarbs", "PointelJarbotico.png", 'J', pin.jrb.PointelJarbs.class).botaPrincipal();
    Pointel.mainRecs.bota("Pointel.PointelJarbs", "Configs", "Configs.png", 'C', pin.jrb.JarbsConfigs.class).tiraSoConectado();
    Pointel.mainRecs.bota("Pointel.PointelJarbs.Configs", "Principal", "ConfigsPrincipal.png", 'P', pin.jrb.ConfigsIntf.class).tiraSoConectado().botaParams(Pointel.mainConf);
    Pointel.mainRecs.bota("Pointel.PointelJarbs.Configs", "Conexão", "ConfigsConexao.png", 'C', pin.jrb.ConConfigsIntf.class).botaParams(Pointel.mainConc.conCfgs());
    Pointel.mainRecs.bota("Pointel.PointelJarbs.Configs", "Conexão Jarbs", "ConfigsConexaoJarbs.png", 'J', pin.jrb.ConfigsIntf.class).tiraSoConectado().botaParams(Pointel.mainJarbs.conexao().conCfgs());
    Pointel.mainRecs.bota("Pointel.PointelJarbs.Configs", "Senha", "Senha.png", 'S', pin.jrb.JarbsSenha.class).tiraSoConectado();
    Pointel.mainRecs.bota("Pointel.PointelJarbs", "Serviços", "Servicos.png", 'S', pin.jrb.ServicosIntf.class).tiraSoConectado();
    Pointel.mainRecs.bota("Pointel.PointelJarbs", "Paralelos", "Paralelos.png", 'P', pin.jrb.ParalelosIntf.class).tiraSoConectado();
    Pointel.mainRecs.bota("Pointel.PointelJarbs", "Análises", "Analises.png", 'A', pin.jrb.AnalisesIntf.class).tiraSoConectado();

    Pointel.mainRecs.bota("Pointel", "PointelAllMake", "PointelAllMake.png", 'K', pin.amk.PointelAllMake.class).botaPrincipal();

    Pointel.mainRecs.bota("Pointel", "PointelAllMagi", "PointelAllMagi.png", 'M', pin.amg.PointelAllMagi.class).botaPrincipal();

    Pointel.mainRecs.bota("Pointel.PointelAllMagi", "PointelProk", "PointelProk.png", 'P', pin.prk.PointelProk.class).botaPrincipal();

    Pointel.mainRecs.bota("Pointel.PointelAllMagi.PointelProk", "Explorar", "Explorar.png", 'E', pin.libexp.ExplorarIntf.class).tiraSoConectado();
    Pointel.mainRecs.bota("Pointel.PointelAllMagi.PointelProk", "Processar", "Processar.png", 'P').tiraSoConectado();
    Pointel.mainRecs.bota("Pointel.PointelAllMagi.PointelProk.Processar", "Robótico", 'R', pin.prk.ServProcessarRoboticoIntf.class).tiraSoConectado();
    Pointel.mainRecs.bota("Pointel.PointelAllMagi.PointelProk", "Minerar", "Minerar.png", 'M').tiraSoConectado();
    Pointel.mainRecs.bota("Pointel.PointelAllMagi.PointelProk", "Publicar", "Publicar.png", 'U').tiraSoConectado();
    Pointel.mainRecs.bota("Pointel.PointelAllMagi.PointelProk.Publicar", "WebSite", 'W', pin.prk.ServPublicarWebSiteIntf.class).tiraSoConectado();

    Pointel.mainRecs.bota("Pointel.PointelAllMagi", "PointelMigre", "PointelMigre.png", 'M', pin.mgr.PointelMigre.class).botaPrincipal();

    Pointel.mainRecs.bota("Pointel.PointelAllMagi", "PointelInfo", "PointelInfo.png", 'I', pin.inf.PointelInfo.class).botaPrincipal();
    Pointel.mainRecs.bota("Pointel.PointelAllMagi.PointelInfo", "Segurança", "Seguranca.png");
    Pointel.mainRecs.bota("Pointel.PointelAllMagi.PointelInfo.Segurança", "Auditoria", pin.inf.CadAuditoria.class);
    Pointel.mainRecs.bota("Pointel.PointelAllMagi.PointelInfo.Segurança", "Usuários", pin.inf.CadUsuarios.class);
    Pointel.mainRecs.bota("Pointel.PointelAllMagi.PointelInfo.Segurança", "Habilitações dos Usuários", pin.inf.CadUsuariosHabilitacoes.class);
    Pointel.mainRecs.bota("Pointel.PointelAllMagi.PointelInfo.Segurança", "Grupos de Usuários", pin.inf.CadGruposUsuarios.class);
    Pointel.mainRecs.bota("Pointel.PointelAllMagi.PointelInfo.Segurança", "Habilitações dos Grupos de Usuários", pin.inf.CadGruposUsuariosHabilitacoes.class);
    Pointel.mainRecs.bota("Pointel.PointelAllMagi.PointelInfo", "Estrutura", "Estrutura.png");
    Pointel.mainRecs.bota("Pointel.PointelAllMagi.PointelInfo.Estrutura", "Tabelas", pin.inf.TabelasIntf.class);
    Pointel.mainRecs.bota("Pointel.PointelAllMagi.PointelInfo.Estrutura", "Códigos", pin.inf.Codigos.class);
    Pointel.mainRecs.bota("Pointel.PointelAllMagi.PointelInfo", "Operações", "Operacoes.png", pin.inf.Operacoes.class);
    Pointel.mainRecs.bota("Pointel.PointelAllMagi.PointelInfo", "Arquivador", "Arquivador.png", pin.jrb.ServArquivadorIntf.class);

    Pointel.mainRecs.bota("Pointel.PointelAllMagi", "PointelApps", "PointelApps.png", 'A', pin.app.PointelApps.class).botaPrincipal();
    Pointel.mainRecs.bota("Pointel.PointelAllMagi.PointelApps", "Processos", "Processos.png", pin.app.Processos.class);
    Pointel.mainRecs.bota("Pointel.PointelAllMagi.PointelApps", "Interfaces", "Interfaces.png", pin.app.Interfaces.class);
    Pointel.mainRecs.bota("Pointel.PointelAllMagi.PointelApps", "Folguedos", "Folguedos.png", pin.app.CadFolguedos.class);
    Pointel.mainRecs.bota("Pointel.PointelAllMagi.PointelApps", "WebSites", "WebSites.png", pin.app.WebSites.class);
  }
}
