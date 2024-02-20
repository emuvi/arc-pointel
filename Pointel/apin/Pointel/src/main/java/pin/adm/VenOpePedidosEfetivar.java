package pin.adm;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import pin.libbas.Conjunto;
import pin.libbas.Globais;
import pin.libbas.Progresso;
import pin.libjan.Janelas;
import pin.libutl.Utl;
import pin.libutl.UtlCrs;
import pin.libutl.UtlInt;
import pin.libutl.UtlNumLon;
import pin.libutl.UtlTab;
import pin.libvlr.VNumLon;
import pin.libvlr.Vlrs;
import pin.modinf.Banco;
import pin.modinf.CodigosMod;
import pin.modinf.Conexao;

public class VenOpePedidosEfetivar extends javax.swing.JFrame {

    public Conexao conexao;
    private DefaultTableModel dtmLista;

    private volatile Boolean verificando;
    private volatile Boolean processando;

    public VenOpePedidosEfetivar() {
        initComponents();
        conexao = (Conexao) Globais.pega("mainConc");
        dtmLista = UtlTab.fazTabela(jtbItens, this, new String[]{"Codigo", "Produto", "Produto - Nome", "Unidade", "Quantidade", "Saldo", "Efetivar"}, new Integer[]{5, 6});
        dtmLista.addTableModelListener(new VerificaDigitado());
        verificando = false;
        processando = false;
        getRootPane().setDefaultButton(jbtEfetivar);
        Janelas.inicia(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlbCodigo = new javax.swing.JLabel();
        jtfCodigo = new javax.swing.JTextField();
        jspItens = new javax.swing.JScrollPane();
        jtbItens = new javax.swing.JTable();
        jlbEmitidoData = new javax.swing.JLabel();
        jftEmitidoData = new javax.swing.JFormattedTextField();
        jlbEmitidoHora = new javax.swing.JLabel();
        jftEmitidoHora = new javax.swing.JFormattedTextField();
        jlbCliente = new javax.swing.JLabel();
        jtfCliente = new javax.swing.JTextField();
        jlbClienteNome = new javax.swing.JLabel();
        jtfClienteNome = new javax.swing.JTextField();
        jlbClienteFantasia = new javax.swing.JLabel();
        jtfClienteFantasia = new javax.swing.JTextField();
        jlbSerie = new javax.swing.JLabel();
        jtfSerie = new javax.swing.JTextField();
        jbtEfetivar = new javax.swing.JButton();
        jtfFatura = new javax.swing.JTextField();
        jlbFatura = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Efetivar Pedidos");

        jlbCodigo.setText("Código");

        jtfCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfCodigoFocusLost(evt);
            }
        });

        jspItens.setViewportView(jtbItens);

        jlbEmitidoData.setText("Emitido");

        jftEmitidoData.setEditable(false);
        jftEmitidoData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));

        jlbEmitidoHora.setText("Hora");

        jftEmitidoHora.setEditable(false);
        jftEmitidoHora.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT))));

        jlbCliente.setText("Cliente");

        jtfCliente.setEditable(false);

        jlbClienteNome.setText("Cliente - Nome");

        jtfClienteNome.setEditable(false);

        jlbClienteFantasia.setText("Cliente - Fantasia");

        jtfClienteFantasia.setEditable(false);

        jlbSerie.setText("Série");

        jbtEfetivar.setText("Efetivar");
        jbtEfetivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtEfetivarActionPerformed(evt);
            }
        });

        jlbFatura.setText("Fatura");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jspItens)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbSerie))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbFatura)
                            .addComponent(jtfFatura, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtEfetivar))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbCodigo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jftEmitidoData, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbEmitidoData))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jftEmitidoHora, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbEmitidoHora))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbCliente))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfClienteNome, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbClienteNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbClienteFantasia)
                            .addComponent(jtfClienteFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 66, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbCodigo)
                    .addComponent(jlbEmitidoData)
                    .addComponent(jlbEmitidoHora)
                    .addComponent(jlbCliente)
                    .addComponent(jlbClienteNome)
                    .addComponent(jlbClienteFantasia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jftEmitidoData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jftEmitidoHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfClienteNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfClienteFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspItens, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbSerie)
                    .addComponent(jlbFatura))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtEfetivar)
                    .addComponent(jtfFatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void abrePedido(String doCodigo) {
        jtfCodigo.setText(doCodigo);
        jtfCodigoFocusLost(null);
        Janelas.mostra(this);
    }

    private void habilitaTudo() {
        jtfCodigo.setEnabled(true);
        jtbItens.setEnabled(true);
        jtfSerie.setEnabled(true);
        jtfFatura.setEnabled(true);
        jbtEfetivar.setEnabled(true);
    }

    private void deshabilitaTudo() {
        jtfCodigo.setEnabled(false);
        jtbItens.setEnabled(false);
        jtfSerie.setEnabled(false);
        jtfFatura.setEnabled(false);
        jbtEfetivar.setEnabled(false);
    }

    private void jtfCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfCodigoFocusLost
        if (!processando) {
            try {
                verificando = true;
                jftEmitidoData.setValue(null);
                jftEmitidoHora.setValue(null);
                jtfCliente.setText(null);
                jtfClienteNome.setText(null);
                jtfClienteFantasia.setText(null);
                jtfFatura.setText(null);
                while (dtmLista.getRowCount() > 0) {
                    dtmLista.removeRow(0);
                }
                if (!jtfCodigo.getText().isEmpty()) {
                    try {
                        Conjunto res = conexao.busca("SELECT codigo, situacao FROM pedidos WHERE codigo = ?",
                                new Vlrs(jtfCodigo.getText()));

                        if (res.vazio()) {
                            throw new Exception("Pedido Não Encontrado");
                        }

                        if (!"L".equals(res.pgVlr("situacao").pgCrs())) {
                            throw new Exception("Este pedido não pode ser efetivado pois não está liberado.");
                        }

                        if (!conexao.busca("SELECT codigo FROM itens_pedido WHERE pedido = ? AND situacao = 'P'",
                                new Vlrs(jtfCodigo.getText()))
                                .vazio()) {
                            throw new Exception("Pedido Não Pode Ser Efetivado Pois Há Itens Pendentes");
                        }

                        if (!conexao.busca("SELECT codigo FROM itens_pedido WHERE pedido = ? AND (comissao = 0 OR comissao = NULL)",
                                new Vlrs(jtfCodigo.getText()))
                                .vazio()) {
                            throw new Exception("Pedido Não Pode Ser Efetivado Pois Há Itens Sem Comissão");
                        }

                        Conjunto rst = conexao.busca("SELECT pedidos.emitido_data, pedidos.emitido_hora, pessoas.codigo, pessoas.nome, pessoas.fantasia "
                                + " FROM pedidos "
                                + " JOIN pessoas ON pessoas.codigo = pedidos.cliente "
                                + " WHERE pedidos.codigo = ? ",
                                new Vlrs(jtfCodigo.getText()));

                        if (rst.proximo()) {
                            jftEmitidoData.setValue(rst.pgVlrDat(1).pgDat());
                            jftEmitidoHora.setValue(rst.pgVlrHor(2).pgHor());
                            jtfCliente.setText(rst.pgVlr(3).pgCrs());
                            jtfClienteNome.setText(rst.pgVlr(4).pgCrs());
                            jtfClienteFantasia.setText(rst.pgVlr(5).pgCrs());

                            Conjunto rst2 = conexao.busca("SELECT itens_pedido.codigo, itens_pedido.produto, CONCAT(produtos.nome, ' ', itens_pedido.obs) AS item_nome, produtos.unidade, itens_pedido.saldo "
                                    + " FROM itens_pedido "
                                    + " JOIN produtos ON produtos.codigo = itens_pedido.produto "
                                    + " WHERE itens_pedido.pedido = ? "
                                    + " AND itens_pedido.saldo > 0 "
                                    + " AND itens_pedido.situacao = 'L' "
                                    + " ORDER BY itens_pedido.pedido, itens_pedido.codigo ",
                                    new Vlrs(jtfCodigo.getText()));
                            while (rst2.proximo()) {
                                dtmLista.addRow(new Object[]{rst2.pgVlr(1), rst2.pgVlr(2), rst2.pgVlr(3), rst2.pgVlr(4), rst2.pgVlrNumLon(5), new VNumLon(0.0), rst2.pgVlrNumLon(5)});
                            }
                        }
                    } catch (Exception ex) {
                        Utl.registra(ex);
                    }
                }
            } catch (Exception ex) {
                Utl.registra(ex);
            } finally {
                verificando = false;
            }
        } else {
            Utl.alerta("Outro Pedido em Processamento, Por Favor Aguarde");
        }
    }//GEN-LAST:event_jtfCodigoFocusLost

    private void jbtEfetivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtEfetivarActionPerformed
        if (!processando) {
            deshabilitaTudo();
            processando = true;
            new ThdEfetivar().start();
        } else {
            Utl.alerta("Outro Pedido em Processamento, Por Favor Aguarde");
        }

    }//GEN-LAST:event_jbtEfetivarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtEfetivar;
    private javax.swing.JFormattedTextField jftEmitidoData;
    private javax.swing.JFormattedTextField jftEmitidoHora;
    private javax.swing.JLabel jlbCliente;
    private javax.swing.JLabel jlbClienteFantasia;
    private javax.swing.JLabel jlbClienteNome;
    private javax.swing.JLabel jlbCodigo;
    private javax.swing.JLabel jlbEmitidoData;
    private javax.swing.JLabel jlbEmitidoHora;
    private javax.swing.JLabel jlbFatura;
    private javax.swing.JLabel jlbSerie;
    private javax.swing.JScrollPane jspItens;
    private javax.swing.JTable jtbItens;
    private javax.swing.JTextField jtfCliente;
    private javax.swing.JTextField jtfClienteFantasia;
    private javax.swing.JTextField jtfClienteNome;
    private javax.swing.JTextField jtfCodigo;
    private javax.swing.JTextField jtfFatura;
    private javax.swing.JTextField jtfSerie;
    // End of variables declaration//GEN-END:variables

    private class VerificaDigitado implements TableModelListener {

        public void tableChanged(TableModelEvent e) {
            if (!verificando) {
                try {
                    verificando = true;
                    int row = e.getFirstRow();
                    int column = e.getColumn();
                    TableModel model = (TableModel) e.getSource();
                    Object data = model.getValueAt(row, column);

                    if (column >= 5) {
                        String digitado = UtlCrs.pega(data);
                        Double valor = UtlNumLon.pega(digitado, "#,##0.000");
                        data = UtlNumLon.formata(valor, "#,##0.000");

                        if (column == 6) {
                            Double quantidade = UtlNumLon.pega(model.getValueAt(row, 4));
                            Double saldo = quantidade - valor;
                            if (saldo <= 0) {
                                saldo = 0.0;
                            }
                            model.setValueAt(UtlNumLon.formata(saldo, "#,##0.000"), row, 5);
                        }
                    }

                    model.setValueAt(data, row, column);
                } catch (Exception ex) {
                    Utl.registra(ex);
                } finally {
                    verificando = false;
                }
            }
        }

    }

    private class ThdEfetivar extends Thread {

        private String pedido;
        private String serie;
        private String fatura;

        private Progresso prog;
        private Banco conEfetivar;

        public ThdEfetivar() {
            super("Efetivar Pedido");
        }

        private void parar() throws Exception {
            throw new Exception("Parou de Efetivar o Pedido");
        }

        private void fazItens() throws Exception {
            if (prog.avancaPausarEdeveParar("Processando os Itens...")) {
                parar();
            }

            Integer codFatIten = 0;
            Integer quantItnFat = 0;

            for (int i1 = 0; i1 < dtmLista.getRowCount(); i1++) {
                if (prog.avancaPausarEdeveParar("Processando o Item " + dtmLista.getValueAt(i1, 2))) {
                    parar();
                }

                if (dtmLista.getValueAt(i1, 6) != null) {
                    Double saldo = UtlNumLon.pega(dtmLista.getValueAt(i1, 5));
                    Double efetivar = UtlNumLon.pega(dtmLista.getValueAt(i1, 6));
                    if (efetivar != null) {
                        if (efetivar > 0) {
                            codFatIten = codFatIten + 1;
                            String codPedIten = UtlCrs.pega(dtmLista.getValueAt(i1, 0));

                            Conjunto rst2 = conEfetivar.busca("SELECT itens_pedido.tabela, itens_pedido.preco, itens_pedido.desconto_per, itens_pedido.acrescimo_per, "
                                    + "   itens_pedido.comissao_per, itens_pedido.obs, itens_pedido.situacao "
                                    + " FROM itens_pedido "
                                    + " WHERE itens_pedido.pedido = ? AND itens_pedido.codigo = ? ",
                                    new Vlrs(pedido, codPedIten));

                            if (rst2.proximo()) {
                                String itnCodigo = UtlCrs.pega(CodigosMod.pegaProximo(conEfetivar, "itens_faturas", "codigo", "serie = '" + serie + "' AND nf = '" + fatura + "'"));
                                String itnProduto = UtlCrs.pega(dtmLista.getValueAt(i1, 1));
                                Double itnQuantidade = efetivar;
                                String itnTabela = rst2.pgVlr(1).pgCrs();
                                Double itnPreco = rst2.pgVlr(2).pgNumLon(0.0);
                                Double itnSubTotal = itnQuantidade * itnPreco;
                                Double itnDescontoPer = rst2.pgVlr(3).pgNumLon(0.0);
                                Double itnDesconto = itnSubTotal * itnDescontoPer / 100;
                                Double itnAcrescimoPer = rst2.pgVlr(4).pgNumLon(0.0);
                                Double itnAcrescimo = itnSubTotal * itnAcrescimoPer / 100;
                                Double itnTotal = itnSubTotal + itnAcrescimo - itnDesconto;
                                Double itnComissaoPer = rst2.pgVlr(5).pgNumLon(0.0);
                                Double itnComissao = itnTotal * itnComissaoPer / 100;
                                String itnObs = rst2.pgVlr(6).pgCrs();

                                if (conEfetivar.opera("INSERT INTO itens_faturas (serie, nf, codigo, data, produto, tabela, quantidade, preco, subtotal, desconto, desconto_per, acrescimo, acrescimo_per, total, comissao, comissao_per, obs) "
                                        + " VALUES (?, ?, ?, current_date, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                                        new Vlrs(serie, fatura, itnCodigo, itnProduto, itnTabela, itnQuantidade, itnPreco, itnSubTotal, itnDesconto, itnDescontoPer, itnAcrescimo, itnAcrescimoPer, itnTotal, itnComissao, itnComissaoPer, itnObs)) > 0) {
                                    String situacao = "L";
                                    if (saldo <= 0) {
                                        situacao = "F";
                                        quantItnFat++;
                                    }

                                    conEfetivar.opera("UPDATE itens_pedido SET saldo = ?, situacao = ? "
                                            + " WHERE itens_pedido.pedido = ? AND itens_pedido.codigo = ?",
                                            new Vlrs(saldo, situacao, pedido, codPedIten));
                                }
                            }
                        }
                    }
                }
            }

            if (prog.avancaPausarEdeveParar("Atualizando Situação do Pedido...")) {
                parar();
            }

            if (quantItnFat == dtmLista.getRowCount()) {
                conEfetivar.opera("UPDATE pedidos SET situacao = 'F', emitido_data = CURRENT_DATE, emitido_hora = CURRENT_TIME WHERE codigo = ?",
                        new Vlrs(jtfCodigo.getText()));
            }

            if (prog.avancaPausarEdeveParar("Atualizando Totais do Pedido...")) {
                parar();
            }

            CadFaturas.calculaTotais(conEfetivar, serie, fatura);
        }

        @Override
        public void run() {
            try {
                boolean achou = false;
                for (int i1 = 0; i1 < dtmLista.getRowCount(); i1++) {
                    if (dtmLista.getValueAt(i1, 6) != null) {
                        Double efetivar = UtlNumLon.pega(dtmLista.getValueAt(i1, 6));
                        if (efetivar != null) {
                            if (efetivar > 0) {
                                achou = true;
                                break;
                            }
                        }
                    }
                }

                if (!achou) {
                    Utl.alerta("Nenhum item a ser efetivado");
                } else {
                    pedido = UtlCrs.pega(jtfCodigo.getText(), "");
                    serie = UtlCrs.pega(jtfSerie.getText(), "");
                    fatura = UtlCrs.pega(jtfFatura.getText(), "");

                    Boolean novaFatura = false;
                    if (fatura.isEmpty()) {
                        novaFatura = true;
                    }

                    prog = new Progresso("Efetivando Pedido " + pedido);
                    prog.abre(dtmLista.getRowCount() + 7 + (novaFatura ? 2 : 0));

                    Boolean limparTela = true;

                    try {
                        if (prog.avancaPausarEdeveParar("Estabelecendo Transação...")) {
                            parar();
                        }

                        conEfetivar = conexao.iniciaTransacao();

                        try {
                            if (prog.avancaPausarEdeveParar("Baixando Parâmetros do Pedido...")) {
                                parar();
                            }

                            if (novaFatura) {
                                fatura = conEfetivar.busca("SELECT ultima_nf FROM series WHERE codigo = ?",
                                        new Vlrs(serie))
                                        .pgCol().pgCrs();
                                fatura = UtlCrs.repete("0", 10 - fatura.length()) + fatura;
                                fatura = UtlCrs.pegaProxima(fatura, true);
                            } else {
                                Boolean lancados = conEfetivar.busca("SELECT lan_ger FROM faturas WHERE serie = ? AND codigo = ?",
                                        new Vlrs(serie, fatura))
                                        .pgCol().pgLog(false);
                                if (lancados) {
                                    throw new Exception("Não é Possível Efetivar Nessa Fatura Pois Os Lançamentos Dela Já Foram Gerados");
                                }
                            }

                            if (prog.avancaPausarEdeveParar("Avaliando Consistência do Pedido...")) {
                                parar();
                            }

                            if (novaFatura) {
                                Conjunto rst = conEfetivar.busca("SELECT pedidos.ped_vendedor, pedidos.cliente, pedidos.cobranca, pedidos.entrega, pedidos.representante, "
                                        + " pedidos.cond_pagamento, pedidos.out_desc, pedidos.out_acresc, pedidos.obs1, pedidos.obs2"
                                        + " FROM pedidos"
                                        + " WHERE pedidos.codigo = ?",
                                        new Vlrs(jtfCodigo.getText()));

                                if (rst.proximo()) {
                                    String pedVendedor = rst.pgVlr(1).pgCrs();
                                    String cliente = rst.pgVlr(2).pgCrs();
                                    String cobranca = rst.pgVlr(3).pgCrs();
                                    String entrega = rst.pgVlr(4).pgCrs();
                                    String representante = rst.pgVlr(5).pgCrs();
                                    String condPgto = rst.pgVlr(6).pgCrs();
                                    Double outDesc = rst.pgVlr(7).pgNumLon();
                                    Double outAcre = rst.pgVlr(8).pgNumLon();
                                    String obs1 = rst.pgVlr(9).pgCrs();
                                    String obs2 = rst.pgVlr(10).pgCrs();

                                    if (prog.avancaPausarEdeveParar("Inserindo Nova Fatura...")) {
                                        parar();
                                    }

                                    String sql = "INSERT INTO faturas (serie, codigo, ped_vendedor, emitido_data, emitido_hora, cliente, cobranca, entrega, "
                                            + " representante, cond_pagamento, val_out_desc, val_out_acres, obs1, obs2) "
                                            + " VALUES (?, ?, ?, CURRENT_DATE, CURRENT_TIME, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                                    Vlrs params = new Vlrs(serie, fatura, pedVendedor, cliente, cobranca, entrega, representante, condPgto, outDesc, outAcre, obs1, obs2);

                                    if (conEfetivar.opera(sql, params) > 0) {
                                        if (prog.avancaPausarEdeveParar("Atualizando Códigos de Fatura...")) {
                                            parar();
                                        }

                                        conEfetivar.opera("UPDATE series SET ultima_nf = ? WHERE codigo = ?",
                                                new Vlrs(UtlInt.pega(fatura), serie));
                                        fazItens();
                                    }
                                } else {
                                    throw new Exception("Não Foi Possível Encontrar os Dados desse Pedido");
                                }

                            } else {
                                fazItens();
                            }

                            if (prog.avancaPausarEdeveParar("Salvando Transação...")) {
                                parar();
                            }

                            conEfetivar.salvaTransacaoEFecha();

                            final String serAbri = serie;
                            final String codAbri = fatura;

                            new Thread("Abri Fatura") {
                                @Override
                                public void run() {
                                    try {
                                        CadFaturas filho = new CadFaturas();
                                        filho.pegaCampo("serie").mVlrFixo(serAbri);
                                        filho.pegaCampo("codigo").mVlrFixo(codAbri);
                                        filho.mostra(true);
                                    } catch (Exception ex) {
                                        Utl.registra(ex, "Erro Ao Abrir Fatura", new String[]{"Série", "Código"}, new Object[]{serAbri, codAbri});
                                    }
                                }
                            }.start();

                            prog.termina();
                        } catch (Exception ex) {
                            limparTela = false;
                            Utl.registra(ex);
                            conEfetivar.cancelaTransacaoEFecha();

                        }
                    } catch (Exception ex) {
                        limparTela = false;
                        Utl.registra(ex);
                    } finally {
                        processando = false;
                        prog.fechar();

                        if (limparTela) {
                            jtfCodigo.setText("");
                            jtfCodigoFocusLost(null);
                        }
                    }
                }
            } catch (Exception ex) {
                Utl.registra(ex);
            } finally {
                processando = false;
                habilitaTudo();
            }
        }
    }

}
