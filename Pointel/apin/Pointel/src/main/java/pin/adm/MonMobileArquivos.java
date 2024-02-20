package pin.adm;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.AbstractAction;
import pin.libbas.Conjunto;
import pin.libbas.Dados;
import pin.libbas.Erro;
import pin.libbas.Progresso;
import pin.libetf.TexEtf;
import pin.libjan.PopMenu;
import pin.libtex.Marcados;
import pin.libutl.Utl;
import pin.libutl.UtlCrs;
import pin.libutl.UtlDat;
import pin.libutl.UtlLog;
import pin.libutl.UtlNumLon;
import pin.libutl.UtlTab;
import pin.libutl.UtlTem;
import pin.libutl.UtlTex;
import pin.libvlr.Vlrs;
import pin.modamk.Monitor;
import pin.modinf.Banco;
import pin.modinf.CodigosMod;

public class MonMobileArquivos extends Monitor {

    public volatile Boolean processando;

    public MonMobileArquivos() {
        super();
        processando = false;
        botaTitulo("Mobile Arquivos");
        botaConsulta("SELECT mobile_arquivos.vendedor, mobile_vendedores.nome, mobile_arquivos.nome"
                + " FROM mobile_arquivos"
                + " JOIN mobile_vendedores ON mobile_vendedores.codigo = mobile_arquivos.vendedor"
                + " WHERE mobile_arquivos.processando != 'S' OR mobile_arquivos.processando IS NULL"
                + " ORDER BY mobile_vendedores.nome, mobile_arquivos.data, mobile_arquivos.hora", null);
        botaTitulos(new String[]{"Vendedor", "Nome", "Arquivo"});
        botaAcao(new ActProcessar());
        PopMenu menu = new PopMenu();
        menu.bota("Atualizar", new ActAtualizar());
        menu.bota("Ver Fonte", new ActVerFonte());
        menu.bota("Excluir", new ActExcluir());
        botaMenuPop(menu);
    }

    private class ActProcessar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!processando) {
                int iSel = editor().controlador().pegaLinhaSelecionada();
                if (iSel > -1) {
                    processando = true;
                    deshabilita();
                    new ThdProcessar(iSel).start();
                }
            } else {
                Utl.alerta("Outro Arquivo em Processamento, Por Favor Aguarde");
            }
        }
    }

    private class ThdProcessar extends Thread {

        private Integer selecionado;

        private String vendedor;
        private String vendedorNome;
        private String arquivo;

        private Vlrs parsArquivo;

        private String aProcessar;
        private Object dataRecebido;
        private Object horaRecebido;

        private Progresso prog;
        private Banco conProcessar;

        private Boolean excluir;

        private String codPed;
        private Boolean abrePed;

        private String codCli;
        private Boolean abreCli;

        public ThdProcessar(Integer oSelecionado) {
            super("MobileArquivos Processar");
            this.selecionado = oSelecionado;
        }

        @Override
        public void run() {
            try {
                excluir = true;

                codPed = null;
                abrePed = false;

                codCli = null;
                abreCli = false;

                vendedor = editor().controlador().pegaLinhaSelecionadaVlrs()[0].pgCrs();
                vendedorNome = editor().controlador().pegaLinhaSelecionadaVlrs()[1].pgCrs();
                arquivo = editor().controlador().pegaLinhaSelecionadaVlrs()[2].pgCrs();

                Boolean ehPedido = arquivo.toLowerCase().endsWith(".ped");

                prog = new Progresso("Processando MobileArquivo " + arquivo);
                prog.abre(4 + (ehPedido ? 6 : 4));

                try {
                    if (prog.avancaPausarEdeveParar("Estabelecendo Transação...")) {
                        parar();
                    }

                    conProcessar = conexao().iniciaTransacao();

                    try {
                        if (prog.avancaPausarEdeveParar("Carregando Fontes do Arquivo...")) {
                            parar();
                        }

                        parsArquivo = new Vlrs(vendedor, arquivo);

                        Conjunto rst = conProcessar.busca("SELECT arquivo, data, hora FROM mobile_arquivos WHERE vendedor = ? AND nome = ?",
                                parsArquivo,
                                Arrays.asList(Dados.Tp.Tex, Dados.Tp.Dat, Dados.Tp.Hor));

                        if (rst.proximo()) {
                            aProcessar = rst.pgVlr(1).pgCrs();
                            dataRecebido = rst.pgVlr(2);
                            horaRecebido = rst.pgVlr(3);
                        } else {
                            throw new Exception("Não Foi Possível Carregar as Fontes do Arquivo");
                        }

                        if (ehPedido) {
                            prkPedido();
                        } else {
                            prkCliente();
                        }

                        if (prog.avancaPausarEdeveParar("Salvando Transação...")) {
                            parar();
                        }

                        conProcessar.salvaTransacaoEFecha();
                    } catch (Exception ex) {
                        Utl.registra(ex);
                        conProcessar.cancelaTransacaoEFecha();
                        excluir = false;
                    }

                    if (abrePed) {
                        new Thread("Abri Fatura") {
                            @Override
                            public void run() {
                                try {
                                    CadPedidos filho = new CadPedidos();
                                    filho.pegaCampo("codigo").mVlrFixo(codPed);
                                    filho.mostra(true);
                                } catch (Exception ex) {
                                    Utl.registra(ex, "Erro Ao Abrir Pedido", new String[]{"Código"}, new Object[]{codPed});
                                }
                            }
                        }.start();
                    }

                    if (abreCli) {
                        new Thread("Abri Cliente") {
                            @Override
                            public void run() {
                                try {
                                    CadPessoas filho = new CadPessoas();
                                    filho.pegaCampo("codigo").mVlrFixo(codCli);
                                    filho.mostra(true);
                                } catch (Exception ex) {
                                    Utl.registra(ex, "Erro Ao Abrir Cliente", new String[]{"Código"}, new Object[]{codCli});
                                }
                            }
                        }.start();
                    }

                    if (excluir) {
                        prog.avanca("Excluindo Mobile Arquivo...");

                        conexao().opera("DELETE FROM mobile_arquivos WHERE vendedor = ? AND nome = ?",
                                parsArquivo);
                        UtlTab.tiraLinha(editor().controlador().modelador(), new Object[]{vendedor, vendedorNome, arquivo});
                    }

                    prog.termina();
                } catch (Exception ex) {
                    Utl.registra(ex);
                } finally {
                    prog.fechar();
                }
            } catch (Exception ex) {
                Utl.registra(ex);
            } finally {
                processando = false;
                habilita();
            }
        }

        private void parar() throws Exception {
            throw new Exception("Parou de Processar o Arquivo");
        }

        private void prkPedido() throws Exception {
            if (prog.avancaPausarEdeveParar("Processando Pedido...")) {
                parar();
            }

            String[] linhas = UtlTex.pLinhas(aProcessar);
            if (linhas.length < 15) {
                throw new Erro("Pedido Com Número de Linhas com Problema");
            }

            codPed = UtlCrs.pega(CodigosMod.pegaProximo(conProcessar, "pedidos"));

            String codPVen = linhas[0].trim();
            String dataPed = linhas[1].trim();
            String codCli = linhas[2].trim();
            String nomCli = linhas[3].trim();
            String fanCli = linhas[4].trim();
            String nomPgto = linhas[5].trim();
            String codPgto = linhas[6].trim();
            String obsPed = linhas[7].trim();
            String totQtd = linhas[8].trim();
            String subtPed = linhas[9].trim();
            String descPed = ""; //linhas[10].trim();
            String acrePed = ""; //linhas[11].trim();
            String totPed = linhas[12].trim();

            if (prog.avancaPausarEdeveParar("Carregando Dados Complementares...")) {
                parar();
            }

            String codRepres = conProcessar.recorrentes().busca("SELECT representante FROM mobile_vendedores WHERE codigo = ?",
                    new Vlrs(vendedor))
                    .pgCol().pgCrs();
            String codSigPedVend = conProcessar.recorrentes().busca("SELECT nome FROM pessoas WHERE codigo = ?",
                    new Vlrs(codRepres))
                    .pgCol().pgCrs();
            codSigPedVend = UtlCrs.cortaMaior(codSigPedVend, 5) + " " + codPVen;
            Double condPagtoDesc = conProcessar.busca("SELECT desconto FROM condicoes_pagamento WHERE codigo = ?",
                    new Vlrs(codPgto))
                    .pgCol().pgNumLon(0.0);
            Conjunto resTab = conProcessar.busca("SELECT tabela_preco, tabela_secundaria FROM pessoas WHERE codigo = ?",
                    new Vlrs(codCli));
            String tabPri = resTab.pgVlr(1).pgCrs("");
            String tabSec = resTab.pgVlr(2).pgCrs("");

            String tabVend = conProcessar.busca("SELECT tabelas FROM mobile_vendedores WHERE codigo = ?",
                    new Vlrs(vendedor))
                    .pgCol().pgCrs("");
            String[] tabsVend = tabVend.split(";");
            Vlrs parsPed = new Vlrs();
            parsPed.novo(codPed);
            parsPed.novo(codSigPedVend);
            parsPed.novo(dataRecebido);
            parsPed.novo(horaRecebido);
            parsPed.novo("P");
            parsPed.novo(codCli);
            parsPed.novo(codCli);
            parsPed.novo(codCli);
            parsPed.novo(codRepres);
            parsPed.novo(codPgto);
            parsPed.novo(UtlNumLon.pega(descPed, 0.0));
            parsPed.novo(UtlNumLon.pega(acrePed, 0.0));
            parsPed.novo(UtlTex.pegaParte(obsPed, 0, 200));
            parsPed.novo(UtlTex.pegaParte(obsPed, 200, 400));

            Boolean algumPendente = false;
            Boolean algumaPendencia = false;

            if (prog.avancaPausarEdeveParar("Inserindo Pedido...")) {
                parar();
            }

            if (conProcessar.opera("INSERT INTO pedidos (codigo, ped_vendedor, recebido_data, recebido_hora, situacao, "
                    + " cliente, cobranca, entrega, representante, cond_pagamento, out_desc, out_acresc, obs1, obs2)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", parsPed) < 1) {
                throw new Exception("Erro ao Inserir o Pedido");
            } else {
                if (prog.avancaPausarEdeveParar("Processando Itens Pedido...")) {
                    parar();
                }
                String codItens = Marcados.marcado(aProcessar, "Itens");
                if (codItens == null) {
                    throw new Erro("Pedido Sem Itens");
                }
                codItens = codItens.trim();
                String[] itens = UtlTex.pLinhas(codItens);
                if (itens.length == 0) {
                    throw new Erro("Pedido Sem Itens");
                }
                prog.aumenta(itens.length * 2);
                String mensagem = "";
                for (int iItn = 0; iItn < itens.length; iItn++) {
                    String itnCrs = itens[iItn];
                    if (itnCrs.endsWith("-")) {
                        itnCrs = itnCrs + " ";
                    }
                    String[] itnData = itnCrs.split("-");
                    if (itnData.length != 10) {
                        throw new Erro("Erro no Número de Partes do Item %d do Pedido", iItn);
                    }
                    String codPro = itnData[0].trim();
                    String nomPro = itnData[1].trim();
                    String unidPro = itnData[2].trim();
                    String quatPro = itnData[3].trim();
                    String precPro = itnData[4].trim();
                    String perdPro = itnData[5].trim(); //per desconto
                    String descPro = itnData[6].trim(); //tot desconto
                    String subtPro = itnData[7].trim();
                    String totPro = itnData[8].trim();
                    String obsPro = itnData[9].trim();
                    if (prog.avancaPausarEdeveParar("Processando Item " + nomPro)) {
                        parar();
                    }
                    Double dqutPro = UtlNumLon.pega(quatPro, 0.0);
                    Double dprcPro = UtlNumLon.pega(precPro, 0.0);
                    Double dsbtPro = dprcPro * dqutPro;
                    Double dpdsPro = UtlNumLon.pega(perdPro, 0.0);
                    Double ddesPro = dsbtPro * dpdsPro / 100;
                    Double dtotPro = dsbtPro - ddesPro;

                    Double comItn = 0.0;
                    Double comPerItn = 0.0;

                    String tabPro = "";

                    if (tabPri == null) {
                        tabPri = "";
                    }
                    if (tabSec == null) {
                        tabSec = "";
                    }

                    Boolean pendente = true;

                    for (String tvnd : tabsVend) {
                        Double prcTab = conProcessar.busca("SELECT valor FROM precos WHERE produto = ? AND tabela = ?",
                                new Vlrs(codPro, tvnd))
                                .pgCol().pgNumLon();
                        if (dprcPro.equals(prcTab)) {
                            if ((!tabPri.isEmpty()) || (!tabSec.isEmpty())) {
                                if (tvnd != null) {
                                    if ((tvnd.equals(tabPri) || tvnd.equals(tabSec))) {
                                        tabPro = tvnd;
                                        pendente = false;
                                        break;
                                    }
                                }
                            } else {
                                tabPro = tvnd;
                                pendente = false;
                                break;
                            }
                        }
                    }

                    if (pendente) {
                        mensagem += "O Vendedor vendeu o produto:\n\n" + nomPro + "\n\nfora de uma tabela de preço autorizada para ele vender.\n\n";
                    }

                    if (!pendente) {
                        if (tabPro == null) {
                            tabPro = "";
                        }

                        if ((!tabPri.isEmpty()) || (!tabSec.isEmpty())) {
                            if (!(tabPro.equals(tabPri) || tabPro.equals(tabSec))) {
                                pendente = true;
                                mensagem += "O Vendedor vendeu o produto:\n\n" + nomPro + "\n\nfora de uma tabela de preço autorizada para o cliente comprar.\n\n";
                            }
                        }
                    }

                    comPerItn = conProcessar.busca("SELECT valor FROM pessoas_comissoes WHERE pessoa = ? AND tabela = ?",
                            new Vlrs(codRepres, tabPro))
                            .pgCol().pgNumLon(0.0);
                    if (comPerItn == 0.0) {
                        comPerItn = conProcessar.busca("SELECT comissao FROM tabelas_precos WHERE codigo = ?",
                                new Vlrs(tabPro)).pgCol().pgNumLon(0.0);
                    }
                    if (comPerItn == 0.0) {
                        pendente = true;
                        mensagem += "Não foi possível encontrar um percentual de comissão a aplicar\npara a tabela de preços utilizada pelo vendedor no produto:\n\n" + nomPro + "\n\n";
                    } else {
                        comItn = dtotPro * comPerItn / 100;
                    }

                    if (dpdsPro > condPagtoDesc) {
                        pendente = true;
                        mensagem += "O Vendedor vendeu o produto:\n\n" + nomPro + "\n\ncom um desconto acima do permitido.\n\n";
                    }

                    String codItn = UtlCrs.pega(CodigosMod.pegaProximo(conProcessar, "itens_pedido", "codigo", "pedido = '" + codPed + "'"));

                    if (tabPro.isEmpty()) {
                        tabPro = null;
                    }

                    Vlrs parIten = new Vlrs();
                    parIten.novo(codPed); // pedido
                    parIten.novo(codItn); // codigo
                    parIten.novo(codPro); // produto
                    parIten.novo(tabPro); // tabela
                    parIten.novo((pendente ? "P" : "L")); // situacao
                    parIten.novo(dqutPro); // quantidade
                    parIten.novo(dqutPro); // saldo
                    parIten.novo(dprcPro); // preco
                    parIten.novo(dsbtPro); // subtotal
                    parIten.novo(ddesPro); // desconto
                    parIten.novo(dpdsPro); // desconto_per
                    parIten.novo(0.0); // acrescimo
                    parIten.novo(0.0); // acrescimo_per
                    parIten.novo(dtotPro); // total
                    parIten.novo(comItn); // comissao
                    parIten.novo(comPerItn); // comissao_per
                    parIten.novo(obsPro); // obs

                    if (prog.avancaPausarEdeveParar("Inserindo Item " + nomPro)) {
                        parar();
                    }

                    if (conProcessar.opera("INSERT INTO itens_pedido (pedido, codigo, produto, tabela, situacao, quantidade, saldo, preco, "
                            + " subtotal, desconto, desconto_per, acrescimo, acrescimo_per, total, comissao, comissao_per, obs)"
                            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", parIten) < 1) {
                        throw new Exception("Erro ao Inserir Item " + nomPro);
                    }

                    if (pendente) {
                        algumPendente = true;
                        algumaPendencia = true;
                    }
                }

                if (!mensagem.isEmpty()) {
                    Utl.alerta(mensagem);
                }

                if (prog.avancaPausarEdeveParar("Processando Crédito do Pedido...")) {
                    parar();
                }

                Conjunto credRes = conProcessar.busca("SELECT creditos.situacao, creditos.mensagem, pessoas.credito_obs, pessoas.bloqueado "
                        + " FROM pessoas "
                        + " LEFT JOIN creditos ON creditos.codigo = pessoas.credito"
                        + " WHERE pessoas.codigo = ?",
                        new Vlrs(codCli));

                String credSit = credRes.pgVlr(1).pgCrs("");
                String credMsg = credRes.pgVlr(2).pgCrs("");
                String credBlq = credRes.pgVlr(4).pgCrs("");

                if (!"S".equals(credBlq)) {
                    Boolean usaCredSit = false;
                    if (credSit != null) {
                        if (!credSit.isEmpty()) {
                            conProcessar.opera("UPDATE pedidos SET situacao = ? WHERE codigo = ?",
                                    new Vlrs(credSit, codPed));
                            usaCredSit = true;
                            algumaPendencia = true;
                        }
                    }

                    if (credMsg != null) {
                        if (!credMsg.isEmpty()) {
                            Utl.alerta(credMsg);
                        }
                    }

                    if (!usaCredSit) {
                        if (algumPendente) {
                            conProcessar.opera("UPDATE pedidos SET situacao = 'P' WHERE codigo = ?",
                                    new Vlrs(codPed));
                            algumaPendencia = true;
                        }
                    }
                } else {
                    conProcessar.opera("UPDATE pedidos SET situacao = 'C' WHERE codigo = ?",
                            new Vlrs(codPed));
                    Utl.alerta("Este Pedido Foi Cancelado Porque o Crédito Do Cliente Está Bloqueado");
                    algumaPendencia = true;
                }

                if (prog.avancaPausarEdeveParar("Calculando Totais do Pedido...")) {
                    parar();
                }

                Double total = CadPedidos.calculaTotais(conProcessar, codPed);

                String pessoa = codCli;
                Double limite = conProcessar.busca("SELECT limite_credito FROM pessoas WHERE codigo = ?",
                        new Vlrs(pessoa)).pgCol().pgNumLon(0.0);
                Double aberto = conProcessar.busca("SELECT SUM(saldo) FROM lancamentos WHERE situacao = 'A' AND pessoa = ?",
                        new Vlrs(pessoa)).pgCol().pgNumLon(0.0);

                if (aberto + total > limite) {
                    conProcessar.opera("UPDATE pedidos SET situacao = 'X' WHERE codigo = ?", new Vlrs(codPed));
                    Utl.alerta("Este cliente tem limite de crédito de R$ " + UtlNumLon.formata(limite) + "\n"
                            + "e possui lançamentos em aberto no valor de R$ " + UtlNumLon.formata(aberto) + "\n"
                            + "por isso não é possível liberar o valor de R$ " + UtlNumLon.formata(total) + "\n"
                            + "e este pedido será salvo como 'Sem Limite'.");
                } else if (!algumaPendencia) {
                    conProcessar.opera("UPDATE pedidos SET situacao = 'L' WHERE codigo = ?",
                            new Vlrs(codPed));
                }

                abrePed = true;
            }

        }

        private void prkCliente() throws Exception {
            if (prog.avancaPausarEdeveParar("Processando Cliente...")) {
                parar();
            }

            String[] linhas = UtlTex.pLinhas(aProcessar);

            String operacao = linhas[0].trim(); //Cliente.Alterar
            String codigo = linhas[1].trim(); //000246
            String nome = linhas[2].trim(); //FARMACIA FLAVIA LTDA EPP(NOME)
            String fantasia = linhas[3].trim(); //FARM FLAVIA(FANTASIA)
            String natureza = linhas[4].trim(); //J
            String contato = linhas[5].trim(); //(CONTATO)
            String fone1 = linhas[6].trim(); //36471322(FONE1)
            String fone2 = linhas[7].trim(); //(FONE2)
            String fone3 = linhas[8].trim(); //(FONE3)
            String cep = linhas[9].trim(); //88.790-000
            String uf = linhas[10].trim(); //SC
            String cidade = linhas[11].trim(); //LAGUNA(CIDADE)
            String bairro = linhas[12].trim(); //MAR GROSSO(BAIRRO)
            String logradouro = linhas[13].trim(); //R.
            String endereco = linhas[14].trim(); //JOAO PINHO(ENDERECO)
            String numero = linhas[15].trim(); //215(NUM)
            String complemento = linhas[16].trim(); //(COMPLEMENTO)
            String website = linhas[17].trim(); //(WEBSITE)
            String email = linhas[18].trim(); //(EMAIL)
            String cnpjcpf = linhas[19].trim(); //02866190000150
            String insest = linhas[20].trim(); //253.849.519
            String aniversario = linhas[21].trim(); //01/01/2000
            String cadastro = linhas[22].trim(); //01/02/2008
            String atualizacao = linhas[23].trim(); //01/02/2009

            MonMobileArquivosCliente monCli = new MonMobileArquivosCliente(janela());

            monCli.jtfOperacao.setText(operacao);
            monCli.jtfCodigo.setText(codigo);
            monCli.jtfNome.setText(nome);
            monCli.jtfFantasia.setText(fantasia);
            monCli.jtfNatureza.setText(natureza);
            monCli.jtfContato.setText(contato);
            monCli.jtfFone1.setText(fone1);
            monCli.jtfFone2.setText(fone2);
            monCli.jtfFone3.setText(fone3);
            monCli.jtfCep.setText(cep);
            monCli.jtfUF.setText(uf);
            monCli.jtfCidade.setText(cidade);
            monCli.jtfBairro.setText(bairro);
            monCli.jtfLog.setText(logradouro);
            monCli.jtfEndereco.setText(endereco);
            monCli.jtfNumero.setText(numero);
            monCli.jtfComplemento.setText(complemento);
            monCli.jtfWebSite.setText(website);
            monCli.jtfEMail.setText(email);
            monCli.jtfCNPJCPF.setText(cnpjcpf);
            monCli.jtfInsEst.setText(insest);
            monCli.jtfAniversario.setText(aniversario);
            monCli.jtfCadastro.setText(cadastro);
            monCli.jtfAtualizacao.setText(atualizacao);

            if (prog.avancaPausarEdeveParar("Verifique os Dados Enviados...")) {
                parar();
            }

            monCli.setVisible(true);
            if (monCli.ok) {
                operacao = monCli.jtfOperacao.getText();
                codigo = monCli.jtfCodigo.getText();
                nome = monCli.jtfNome.getText();
                fantasia = monCli.jtfFantasia.getText();
                natureza = monCli.jtfNatureza.getText();
                contato = monCli.jtfContato.getText();
                fone1 = monCli.jtfFone1.getText();
                fone2 = monCli.jtfFone2.getText();
                fone3 = monCli.jtfFone3.getText();
                cep = monCli.jtfCep.getText();
                uf = monCli.jtfUF.getText();
                cidade = monCli.jtfCidade.getText();
                bairro = monCli.jtfBairro.getText();
                logradouro = monCli.jtfLog.getText();
                endereco = monCli.jtfEndereco.getText();
                numero = monCli.jtfNumero.getText();
                complemento = monCli.jtfComplemento.getText();
                website = monCli.jtfWebSite.getText();
                email = monCli.jtfEMail.getText();
                cnpjcpf = monCli.jtfCNPJCPF.getText();
                insest = monCli.jtfInsEst.getText();
                aniversario = monCli.jtfAniversario.getText();
                cadastro = monCli.jtfCadastro.getText();
                atualizacao = monCli.jtfAtualizacao.getText();

                if (operacao.trim().toLowerCase().equals("cliente.alterar")) {
                    if (prog.avancaPausarEdeveParar("Processando Alteração do Cliente...")) {
                        parar();
                    }

                    String sqlUpdt = "";
                    ArrayList<Object> parUpdt = new ArrayList<>();

                    if (!nome.isEmpty()) {
                        sqlUpdt = sqlUpdt + (sqlUpdt.isEmpty() ? "" : " , ") + " nome = ? ";
                        parUpdt.add(nome);
                    }

                    if (!fantasia.isEmpty()) {
                        sqlUpdt = sqlUpdt + (sqlUpdt.isEmpty() ? "" : " , ") + " fantasia = ? ";
                        parUpdt.add(fantasia);
                    }

                    if (!natureza.isEmpty()) {
                        sqlUpdt = sqlUpdt + (sqlUpdt.isEmpty() ? "" : " , ") + " natureza = ? ";
                        parUpdt.add(natureza);
                    }

                    if (!contato.isEmpty()) {
                        sqlUpdt = sqlUpdt + (sqlUpdt.isEmpty() ? "" : " , ") + " contato = ? ";
                        parUpdt.add(contato);
                    }

                    if (!fone1.isEmpty()) {
                        sqlUpdt = sqlUpdt + (sqlUpdt.isEmpty() ? "" : " , ") + " fone1 = ? ";
                        parUpdt.add(fone1);
                    }

                    if (!fone2.isEmpty()) {
                        sqlUpdt = sqlUpdt + (sqlUpdt.isEmpty() ? "" : " , ") + " fone2 = ? ";
                        parUpdt.add(fone2);
                    }

                    if (!fone3.isEmpty()) {
                        sqlUpdt = sqlUpdt + (sqlUpdt.isEmpty() ? "" : " , ") + " fone3 = ? ";
                        parUpdt.add(fone3);
                    }

                    if (!cep.isEmpty()) {
                        sqlUpdt = sqlUpdt + (sqlUpdt.isEmpty() ? "" : " , ") + " cep = ? ";
                        parUpdt.add(cep);
                    }

                    if (!cidade.isEmpty()) {
                        String codCidade = conProcessar.busca("SELECT codigo FROM cidades WHERE nome = ? AND estado = ?",
                                new Vlrs(cidade, uf))
                                .pgCol().pgCrs("");
                        if (!codCidade.isEmpty()) {
                            sqlUpdt = sqlUpdt + (sqlUpdt.isEmpty() ? "" : " , ") + " cidade = ? ";
                            parUpdt.add(codCidade);

                            if (!bairro.isEmpty()) {
                                String codBairro = conProcessar.busca("SELECT codigo FROM bairros WHERE nome = ? AND cidade = ?",
                                        new Vlrs(bairro, codCidade))
                                        .pgCol().pgCrs("");

                                if (!codBairro.isEmpty()) {
                                    sqlUpdt = sqlUpdt + (sqlUpdt.isEmpty() ? "" : " , ") + " bairro = ? ";
                                    parUpdt.add(codBairro);
                                } else {
                                    throw new Exception("O Bairro Especificado Não Foi Encontrado");
                                }
                            }
                        } else {
                            throw new Exception("A Cidade Especificada Não Foi Encontrada");
                        }
                    }

                    if (!logradouro.isEmpty()) {
                        sqlUpdt = sqlUpdt + (sqlUpdt.isEmpty() ? "" : " , ") + " logradouro = ? ";
                        parUpdt.add(logradouro);
                    }

                    if (!endereco.isEmpty()) {
                        sqlUpdt = sqlUpdt + (sqlUpdt.isEmpty() ? "" : " , ") + " endereco = ? ";
                        parUpdt.add(endereco);
                    }

                    if (!numero.isEmpty()) {
                        sqlUpdt = sqlUpdt + (sqlUpdt.isEmpty() ? "" : " , ") + " numero = ? ";
                        parUpdt.add(numero);
                    }

                    if (!complemento.isEmpty()) {
                        sqlUpdt = sqlUpdt + (sqlUpdt.isEmpty() ? "" : " , ") + " complemento = ? ";
                        parUpdt.add(complemento);
                    }

                    if (!website.isEmpty()) {
                        sqlUpdt = sqlUpdt + (sqlUpdt.isEmpty() ? "" : " , ") + " website1 = ? ";
                        parUpdt.add(website);
                    }

                    if (!email.isEmpty()) {
                        sqlUpdt = sqlUpdt + (sqlUpdt.isEmpty() ? "" : " , ") + " email1 = ? ";
                        parUpdt.add(email);
                    }

                    if (!cnpjcpf.isEmpty()) {
                        if (UtlCrs.ehCNPJCPFValido(cnpjcpf)) {
                            sqlUpdt = sqlUpdt + (sqlUpdt.isEmpty() ? "" : " , ") + " cnpjcpf = ? ";
                            parUpdt.add(cnpjcpf);
                        } else {
                            throw new Exception("O CNPJ/CPF Não É Válido.");
                        }
                    }

                    if (!insest.isEmpty()) {
                        sqlUpdt = sqlUpdt + (sqlUpdt.isEmpty() ? "" : " , ") + " insestadual = ? ";
                        parUpdt.add(insest);
                    }

                    java.util.Date dtAniversario = UtlTem.pega(aniversario, "dd/MM/yyyy", null);
                    if (dtAniversario != null) {
                        sqlUpdt = sqlUpdt + (sqlUpdt.isEmpty() ? "" : " , ") + " aniversario = ? ";
                        parUpdt.add(UtlDat.pegaSQL(dtAniversario));
                    }

                    java.util.Date dtCadastro = UtlTem.pega(cadastro, "dd/MM/yyyy", null);
                    if (dtCadastro != null) {
                        sqlUpdt = sqlUpdt + (sqlUpdt.isEmpty() ? "" : " , ") + " cadastro = ? ";
                        parUpdt.add(UtlDat.pegaSQL(dtCadastro));
                    }

                    java.util.Date dtAtualizacao = UtlTem.pega(atualizacao, "dd/MM/yyyy", null);
                    if (dtAtualizacao != null) {
                        sqlUpdt = sqlUpdt + (sqlUpdt.isEmpty() ? "" : " , ") + " atualizacao = ? ";
                        parUpdt.add(UtlDat.pegaSQL(dtAtualizacao));
                    }

                    if (!sqlUpdt.isEmpty()) {
                        sqlUpdt = "SET " + sqlUpdt + " WHERE codigo = ?";
                        parUpdt.add(codigo);

                        if (prog.avancaPausarEdeveParar("Realizando Alteração do Cliente...")) {
                            parar();
                        }

                        if (conProcessar.opera("UPDATE pessoas " + sqlUpdt, new Vlrs(parUpdt.toArray())) < 1) {
                            throw new Exception("Não Foi Possível Alterar o Cliente");
                        }
                    } else {
                        throw new Exception("Nada a Ser Alterado no Cliente");
                    }
                } else {
                    throw new Exception("Operação de Cliente Não Reconhecida");
                }
            } else {
                excluir = UtlLog.pergunta("Deseja Excluir esse Arquivo de Cadastro?");
            }
        }
    }

    private class ActAtualizar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                atualizar();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }

    }

    private class ActVerFonte extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                int iSel = editor().controlador().pegaLinhaSelecionada();
                if (iSel > -1) {
                    String vendedor = editor().controlador().pegaLinhaSelecionadaVlrs()[0].pgCrs();
                    String arquivo = editor().controlador().pegaLinhaSelecionadaVlrs()[2].pgCrs();
                    try {
                        Conjunto rst = conexao().busca("SELECT arquivo FROM mobile_arquivos WHERE vendedor = ? AND nome = ?",
                                new Vlrs(vendedor, arquivo),
                                Arrays.asList(Dados.Tp.Tex));
                        if (rst.proximo()) {
                            String arquivofonte = rst.pgVlr(1).pgCrs("");
                            TexEtf txtItf = new TexEtf();
                            txtItf.mostra("Edição Mobile Arquivo");
                            txtItf.abre(arquivofonte);
                            txtItf.edt().mudaAoSalvar(new AbstractAction() {
                                @Override
                                public void actionPerformed(ActionEvent ae) {
                                    try {
                                        conexao().opera("UPDATE mobile_arquivos SET arquivo = ? WHERE vendedor = ? AND nome = ?",
                                                new Vlrs(txtItf.edt().pgVlr(), vendedor, arquivo));
                                        Utl.alerta("Arquivo Fonte do Mobile Salvo na Base de Dados");
                                    } catch (Exception ex) {
                                        Utl.registra(ex);
                                    }
                                }
                            });
                        }
                    } catch (Exception ex) {
                        Utl.registra(ex);
                    }
                }
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }

    }

    private class ActExcluir extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int iSel = editor().controlador().pegaLinhaSelecionada();
                if (iSel > -1) {
                    if (Utl.continua()) {
                        String vendedor = editor().controlador().pegaLinhaSelecionadaVlrs()[0].pgCrs();
                        String arquivo = editor().controlador().pegaLinhaSelecionadaVlrs()[2].pgCrs();
                        conexao().opera("DELETE FROM mobile_arquivos WHERE vendedor = ? AND nome = ?",
                                new Vlrs(vendedor, arquivo));
                        editor().controlador().modelador().removeRow(iSel);
                    }
                }
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }

    }
}
