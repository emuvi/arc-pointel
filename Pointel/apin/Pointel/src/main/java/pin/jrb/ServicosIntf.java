package pin.jrb;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.Date;
import javax.swing.AbstractAction;
import javax.swing.table.DefaultTableModel;
import pin.Pointel;
import pin.libamk.ComandosIntf;
import pin.libamk.RoteirosIntf;
import pin.libjan.Janelas;
import pin.libjan.TabCarregador;
import pin.libjan.TrataIntf;
import pin.libpic.Pics;
import pin.libutl.Utl;
import pin.libutl.UtlHor;
import pin.libutl.UtlMom;
import pin.libutl.UtlTab;
import pin.libutl.UtlTem;
import pin.modpim.Pims;
import pin.modrec.XMLReg;
import pin.prk.ServProcessarRobotico;
import pin.prk.ServProcessarRoboticoIntf;
import pin.prk.ServPublicarWebSite;
import pin.prk.ServPublicarWebSiteIntf;

public class ServicosIntf extends javax.swing.JFrame {

    private Servicos servicos;
    private DefaultTableModel dtmRotinas;
    private TabCarregador carregador;

    public ServicosIntf() throws Exception {
        initComponents();
        setIconImage(Pims.pega("Servicos.png").getImage());
        servicos = Pointel.mainJarbs.servicos();
        dtmRotinas = UtlTab.fazTabela(jtbServicos, this, "Código", "Executar", "Tipo", "Fonte", "Periodicidade", "Vezes", "De", "Até", "Último");
        carregador = new TabCarregador(jtbServicos);
        edtTipo.botaOpcoes(Servico.Tp.class);
        edtPeriodicidade.botaOpcoes(UtlTem.Temporidade.class);
        jbtAdicionar.setIcon(Pics.pega("add.png"));
        jbtAdicionar.setToolTipText("Novo Serviço");
        jbtRemover.setIcon(Pics.pega("delete.png"));
        jbtRemover.setToolTipText("Remover Serviço");
        jbtSalvar.setIcon(Pics.pega("disk.png"));
        jbtSalvar.setToolTipText("Salvar Serviço");
        jbtConfig.setIcon(Pics.pega("cog.png"));
        jbtConfig.setToolTipText("Configurar Serviço");
        Janelas.inicia(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpmServicos = new javax.swing.JPopupMenu();
        jmiAtualizar = new javax.swing.JMenuItem();
        jmiReiniciar = new javax.swing.JMenuItem();
        jmiReiniciarTodos = new javax.swing.JMenuItem();
        jspServicos = new javax.swing.JScrollPane();
        jtbServicos = new javax.swing.JTable();
        jpnEdicao = new javax.swing.JPanel();
        jlbCodigo = new javax.swing.JLabel();
        edtCodigo = new pin.libamg.EdtInt();
        jlbExecutar = new javax.swing.JLabel();
        edtExecutar = new pin.libamg.EdtLog();
        jbtExecutados = new javax.swing.JButton();
        jlbTipo = new javax.swing.JLabel();
        edtTipo = new pin.libamg.EdtEnu();
        jlbVezes = new javax.swing.JLabel();
        edtVezes = new pin.libamg.EdtInt();
        jlbPeriodicidade = new javax.swing.JLabel();
        edtPeriodicidade = new pin.libamg.EdtEnu();
        jlbUltimo = new javax.swing.JLabel();
        edtUltimo = new pin.libamg.EdtMom();
        jlbFonte = new javax.swing.JLabel();
        edtFonte = new pin.libamg.EdtTex();
        jbtAdicionar = new javax.swing.JButton();
        jbtRemover = new javax.swing.JButton();
        jbtConfig = new javax.swing.JButton();
        jbtSalvar = new javax.swing.JButton();
        jlbDe = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        edtDe = new pin.libamg.EdtHor();
        edtAte = new pin.libamg.EdtHor();

        jmiAtualizar.setText("Atualizar");
        jmiAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiAtualizarActionPerformed(evt);
            }
        });
        jpmServicos.add(jmiAtualizar);

        jmiReiniciar.setText("Reiniciar");
        jmiReiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiReiniciarActionPerformed(evt);
            }
        });
        jpmServicos.add(jmiReiniciar);

        jmiReiniciarTodos.setText("Reiniciar Todos");
        jmiReiniciarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiReiniciarTodosActionPerformed(evt);
            }
        });
        jpmServicos.add(jmiReiniciarTodos);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Serviços");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jspServicos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jspServicosMouseClicked(evt);
            }
        });

        jtbServicos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbServicosMouseClicked(evt);
            }
        });
        jspServicos.setViewportView(jtbServicos);

        jlbCodigo.setText("Código");

        jlbExecutar.setText("Executar");

        jbtExecutados.setText("...");
        jbtExecutados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtExecutadosActionPerformed(evt);
            }
        });

        jlbTipo.setText("Tipo");

        jlbVezes.setText("Vezes");

        jlbPeriodicidade.setText("A Cada");

        jlbUltimo.setText("Último");

        jlbFonte.setText("Fonte");

        jbtAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAdicionarActionPerformed(evt);
            }
        });

        jbtRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRemoverActionPerformed(evt);
            }
        });

        jbtConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtConfigActionPerformed(evt);
            }
        });

        jbtSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtSalvarActionPerformed(evt);
            }
        });

        jlbDe.setText("Iniciar De");

        jLabel2.setText("Até");

        javax.swing.GroupLayout jpnEdicaoLayout = new javax.swing.GroupLayout(jpnEdicao);
        jpnEdicao.setLayout(jpnEdicaoLayout);
        jpnEdicaoLayout.setHorizontalGroup(
            jpnEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnEdicaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnEdicaoLayout.createSequentialGroup()
                        .addComponent(jlbTipo)
                        .addGap(100, 100, 100))
                    .addGroup(jpnEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpnEdicaoLayout.createSequentialGroup()
                            .addComponent(jlbVezes)
                            .addGap(61, 61, 61)
                            .addComponent(jlbPeriodicidade)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE))
                        .addGroup(jpnEdicaoLayout.createSequentialGroup()
                            .addComponent(edtVezes, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(edtPeriodicidade, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                            .addGap(18, 18, 18))
                        .addGroup(jpnEdicaoLayout.createSequentialGroup()
                            .addGroup(jpnEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnEdicaoLayout.createSequentialGroup()
                                    .addGroup(jpnEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(edtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jlbCodigo))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jpnEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jlbExecutar)
                                        .addComponent(edtExecutar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jbtExecutados))
                                .addComponent(edtTipo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(edtUltimo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jpnEdicaoLayout.createSequentialGroup()
                                    .addGroup(jpnEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(edtDe, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jlbDe)
                                        .addComponent(jlbUltimo))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jpnEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jpnEdicaoLayout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(edtAte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGap(18, 18, 18))))
                .addGroup(jpnEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnEdicaoLayout.createSequentialGroup()
                        .addComponent(edtFonte, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpnEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpnEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jpnEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jbtSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jbtAdicionar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jbtConfig, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jbtRemover, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpnEdicaoLayout.createSequentialGroup()
                        .addComponent(jlbFonte)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpnEdicaoLayout.setVerticalGroup(
            jpnEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnEdicaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpnEdicaoLayout.createSequentialGroup()
                        .addGroup(jpnEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jpnEdicaoLayout.createSequentialGroup()
                                .addComponent(jlbCodigo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpnEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(edtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(edtExecutar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jbtExecutados))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlbTipo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpnEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbPeriodicidade, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jlbVezes, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpnEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edtVezes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtPeriodicidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpnEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbDe)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpnEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpnEdicaoLayout.createSequentialGroup()
                                .addComponent(edtDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlbUltimo))
                            .addComponent(edtAte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpnEdicaoLayout.createSequentialGroup()
                        .addGroup(jpnEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbFonte)
                            .addComponent(jlbExecutar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpnEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edtFonte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jpnEdicaoLayout.createSequentialGroup()
                                .addComponent(jbtAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jbtConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnEdicao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jspServicos))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jspServicos, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpnEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void limpa() throws Exception {
        edtCodigo.limpa();
        edtExecutar.limpa();
        edtTipo.limpa();
        edtPeriodicidade.limpa();
        edtVezes.limpa();
        edtDe.limpa();
        edtAte.limpa();
        edtUltimo.limpa();
        edtFonte.limpa();
    }

    private void jbtAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAdicionarActionPerformed
        if (Utl.continua()) {
            try {
                limpa();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }//GEN-LAST:event_jbtAdicionarActionPerformed

    private void jbtRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRemoverActionPerformed
        try {
            if (edtCodigo.vazio()) {
                Utl.alerta("Nenhum Serviço Selecionado");
            } else if (Utl.continua()) {
                if (!edtCodigo.vazio()) {
                    Integer codigo = edtCodigo.pgVlr().pgInt();
                    servicos.tira(codigo);
                    for (int i1 = 0; i1 < dtmRotinas.getRowCount(); i1++) {
                        if (dtmRotinas.getValueAt(i1, 0).equals(codigo)) {
                            dtmRotinas.removeRow(i1);
                            break;
                        }
                    }
                }
                limpa();
            }
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jbtRemoverActionPerformed

    private void jbtSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSalvarActionPerformed
        try {
            Integer codigo = edtCodigo.pgVlr().pgInt();
            Boolean executar = edtExecutar.pgVlr().pgLog();
            Servico.Tp tipo = (Servico.Tp) edtTipo.pgVlr().pg();
            String fonte = edtFonte.pgVlr().pgCrs();
            UtlTem.Temporidade acada = (UtlTem.Temporidade) edtPeriodicidade.pgVlr().pg();
            Integer vezes = edtVezes.pgVlr().pgInt();
            Date de = edtDe.pgVlr().pgHor();
            Date ate = edtAte.pgVlr().pgHor();
            Date ultimo = edtUltimo.pgVlr().pgMom();
            Servico serv = new Servico(codigo, executar, tipo, fonte, acada, vezes, de, ate, ultimo);
            if (edtCodigo.vazio()) {
                serv.codigo = servicos.bota(serv);
                dtmRotinas.addRow(new Object[]{serv.codigo, serv.executar, serv.tipo, serv.fonte, serv.aCada, serv.vezes, UtlHor.formata(de), UtlHor.formata(ate), UtlMom.formataMaquina(serv.ultimo)});
                limpa();
            } else {
                servicos.muda(serv);
                for (int i1 = 0; i1 < dtmRotinas.getRowCount(); i1++) {
                    if (dtmRotinas.getValueAt(i1, 0).equals(serv.codigo)) {
                        dtmRotinas.setValueAt(serv.executar, i1, 1);
                        dtmRotinas.setValueAt(serv.tipo, i1, 2);
                        dtmRotinas.setValueAt(serv.fonte, i1, 3);
                        dtmRotinas.setValueAt(serv.aCada, i1, 4);
                        dtmRotinas.setValueAt(serv.vezes, i1, 5);
                        dtmRotinas.setValueAt(UtlHor.formata(serv.de), i1, 6);
                        dtmRotinas.setValueAt(UtlHor.formata(serv.ate), i1, 7);
                        dtmRotinas.setValueAt(UtlMom.formataMaquina(serv.ultimo), i1, 8);
                        break;
                    }
                }
            }
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jbtSalvarActionPerformed

    private void jtbServicosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbServicosMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1) {
            if (evt.getClickCount() >= 2) {
                int iSel = jtbServicos.getSelectedRow();
                if (iSel > -1) {
                    try {
                        Integer codigo = (Integer) dtmRotinas.getValueAt(iSel, 0);
                        Servico serv = servicos.pega(codigo);
                        dtmRotinas.setValueAt(serv.executar, iSel, 1);
                        dtmRotinas.setValueAt(serv.tipo, iSel, 2);
                        dtmRotinas.setValueAt(serv.fonte, iSel, 3);
                        dtmRotinas.setValueAt(serv.aCada, iSel, 4);
                        dtmRotinas.setValueAt(serv.vezes, iSel, 5);
                        dtmRotinas.setValueAt(UtlHor.formata(serv.de), iSel, 6);
                        dtmRotinas.setValueAt(UtlHor.formata(serv.ate), iSel, 7);
                        dtmRotinas.setValueAt(UtlMom.formataMaquina(serv.ultimo), iSel, 8);
                        edtCodigo.mdVlr(dtmRotinas.getValueAt(iSel, 0));
                        edtExecutar.mdVlr(dtmRotinas.getValueAt(iSel, 1));
                        edtTipo.mdVlr(dtmRotinas.getValueAt(iSel, 2));
                        edtFonte.mdVlr(dtmRotinas.getValueAt(iSel, 3));
                        edtPeriodicidade.mdVlr(dtmRotinas.getValueAt(iSel, 4));
                        edtVezes.mdVlr(dtmRotinas.getValueAt(iSel, 5));
                        edtDe.mdVlr(UtlHor.pega(dtmRotinas.getValueAt(iSel, 6)));
                        edtAte.mdVlr(UtlHor.pega(dtmRotinas.getValueAt(iSel, 7)));
                        edtUltimo.mdVlr(UtlMom.pegaMaquina(dtmRotinas.getValueAt(iSel, 8)));
                    } catch (Exception ex) {
                        Utl.registra(ex);
                    }
                }
            }
        } else if (evt.getButton() == MouseEvent.BUTTON3) {
            jpmServicos.show((Component) evt.getSource(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jtbServicosMouseClicked

    private void jmiAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAtualizarActionPerformed
        atualiza();
    }//GEN-LAST:event_jmiAtualizarActionPerformed

    public void atualiza() {
        carregador.carrega(servicos.pegaTab());
    }

    private void jspServicosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jspServicosMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) {
            jpmServicos.show((Component) evt.getSource(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jspServicosMouseClicked

    private void jbtConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtConfigActionPerformed
        try {
            String fnt = edtFonte.pgVlr("").pgCrs("");
            switch (edtTipo.pgVlr().pgCrs()) {
                case "Roteiro":
                    ServRoteiro servRot = null;
                    if (!fnt.isEmpty()) {
                        servRot = (ServRoteiro) XMLReg.novo().pValor(fnt);
                    }
                    if (servRot == null) {
                        servRot = new ServRoteiro();
                    }
                    RoteirosIntf roe = new RoteirosIntf();
                    roe.mNome(servRot.nome);
                    roe.mPrioridade(servRot.prioridade);
                    roe.edtCodigo().mdVlr(servRot.codigo);
                    roe.mAoSalvar(new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                ServRoteiro sro = new ServRoteiro();
                                sro.nome = roe.pNome();
                                sro.prioridade = roe.pPrioridade();
                                sro.codigo = roe.edtCodigo().pgVlr("").pgCrs();
                                edtFonte.mdVlr(sro.descreve());
                                Janelas.fecha(roe);
                                edtFonte.botaFoco();
                            } catch (Exception ex) {
                                Utl.registra(ex);
                            }
                        }
                    });
                    roe.mostra();
                    break;
                case "Comando":
                    ServComando servCmd = null;
                    if (!fnt.isEmpty()) {
                        servCmd = (ServComando) XMLReg.novo().pValor(fnt);
                    }
                    if (servCmd == null) {
                        servCmd = new ServComando();
                    }
                    ComandosIntf cmd = new ComandosIntf();
                    cmd.mostra();
                    cmd.cmps().pega("nome").pEdt().mdVlr(servCmd.nome);
                    cmd.cmps().pega("comando").pEdt().mdVlr(servCmd.comando);
                    cmd.cmps().pega("mostrador").pEdt().mdVlr(servCmd.mostrador);
                    cmd.cmps().pega("argumentos").pEdt().mdVlr(servCmd.argumentos);
                    cmd.cmps().pega("variaveis").pEdt().mdVlr(servCmd.variaveis);
                    cmd.cmps().pega("diretorio").pEdt().mdVlr(servCmd.diretorio);
                    cmd.mudaAoConfirmar(new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                ServComando scmd = new ServComando();
                                scmd.nome = cmd.cmps().pgVlr("nome").pgCrs();
                                scmd.comando = cmd.cmps().pgVlr("comando").pgCrs();
                                scmd.mostrador = cmd.cmps().pgVlr("mostrador").pgLog();
                                scmd.argumentos = cmd.cmps().pgVlrLis("argumentos").pgMatCrs();
                                scmd.variaveis = cmd.cmps().pgVlrLis("variaveis").pgMatCrs();
                                scmd.diretorio = cmd.cmps().pgVlr("diretorio").pgArqCam();
                                edtFonte.mdVlr(scmd.descreve());
                                cmd.fecha();
                                edtFonte.botaFoco();
                            } catch (Exception ex) {
                                Utl.registra(ex);
                            }
                        }
                    });
                    break;
                case "Conectar":
                    ServConectar servCnt = null;
                    if (!fnt.isEmpty()) {
                        servCnt = (ServConectar) XMLReg.novo().pValor(fnt);
                    }
                    if (servCnt == null) {
                        servCnt = new ServConectar();
                    }
                    new ServConectarIntf(edtFonte, servCnt).mostra();
                    break;
                case "ProcessarRobotico":
                    ServProcessarRobotico servPRob = null;
                    if (!fnt.isEmpty()) {
                        servPRob = (ServProcessarRobotico) XMLReg.novo().pValor(fnt);
                    }
                    if (servPRob == null) {
                        servPRob = new ServProcessarRobotico();
                    }
                    new ServProcessarRoboticoIntf(edtFonte, servPRob).mostra();
                    break;
                case "PublicarWebSite":
                    ServPublicarWebSite servPws = null;
                    if (!fnt.isEmpty()) {
                        servPws = (ServPublicarWebSite) XMLReg.novo().pValor(fnt);
                    }
                    if (servPws == null) {
                        servPws = new ServPublicarWebSite();
                    }
                    new ServPublicarWebSiteIntf(edtFonte, servPws).mostra();
                    break;
                case "PointelMigre":
                    ServPointelMigre servPim = null;
                    if (!fnt.isEmpty()) {
                        servPim = (ServPointelMigre) XMLReg.novo().pValor(fnt);
                    }
                    if (servPim == null) {
                        servPim = new ServPointelMigre();
                    }
                    new ServPointelMigreIntf(edtFonte, servPim).mostra();
                    break;
                case "Arquivador":
                    ServArquivador servArv = null;
                    if (!fnt.isEmpty()) {
                        servArv = (ServArquivador) XMLReg.novo().pValor(fnt);
                    }
                    if (servArv == null) {
                        servArv = new ServArquivador();
                    }
                    new ServArquivadorIntf(edtFonte, servArv).mostra();
                    break;
                default:
                    throw new Exception("Tipo do Serviço Desconhecido");
            }
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jbtConfigActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if (!Pointel.mainJarbs.confereLogado()) {
            Janelas.fecha(this);
        } else {
            TrataIntf.garanteFoco(this);
        }
        atualiza();
    }//GEN-LAST:event_formWindowActivated

    private void jmiReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiReiniciarActionPerformed
        int iSel = jtbServicos.getSelectedRow();
        if (iSel > -1) {
            try {
                Integer codigo = (Integer) dtmRotinas.getValueAt(iSel, 0);
                Servico serv = servicos.pega(codigo);
                serv.ultimo = null;
                servicos.muda(serv);
                jmiAtualizarActionPerformed(evt);
            } catch (Exception ex) {
                Utl.registra(ex);
            }

        }
    }//GEN-LAST:event_jmiReiniciarActionPerformed

    private void jmiReiniciarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiReiniciarTodosActionPerformed
        for (int il = 0; il < jtbServicos.getRowCount(); il++) {
            try {
                Integer codigo = (Integer) dtmRotinas.getValueAt(il, 0);
                Servico serv = servicos.pega(codigo);
                serv.ultimo = null;
                servicos.muda(serv);
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
        jmiAtualizarActionPerformed(evt);
    }//GEN-LAST:event_jmiReiniciarTodosActionPerformed

    private void jbtExecutadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtExecutadosActionPerformed
        try {
            if (!edtCodigo.vazio()) {
                Integer servico = edtCodigo.pgVlr().pgInt();
                ServicosExecutadosIntf executados = new ServicosExecutadosIntf(servicos, servico, edtTipo.pgVlr().pgCrs());
                executados.mostra();
                servicos.carregaExecutados(servico, executados.edt());
            } else {
                throw new Exception("Serviço Inexistente");
            }
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jbtExecutadosActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private pin.libamg.EdtHor edtAte;
    private pin.libamg.EdtInt edtCodigo;
    private pin.libamg.EdtHor edtDe;
    private pin.libamg.EdtLog edtExecutar;
    public pin.libamg.EdtTex edtFonte;
    private pin.libamg.EdtEnu edtPeriodicidade;
    private pin.libamg.EdtEnu edtTipo;
    private pin.libamg.EdtMom edtUltimo;
    private pin.libamg.EdtInt edtVezes;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton jbtAdicionar;
    private javax.swing.JButton jbtConfig;
    private javax.swing.JButton jbtExecutados;
    private javax.swing.JButton jbtRemover;
    private javax.swing.JButton jbtSalvar;
    private javax.swing.JLabel jlbCodigo;
    private javax.swing.JLabel jlbDe;
    private javax.swing.JLabel jlbExecutar;
    private javax.swing.JLabel jlbFonte;
    private javax.swing.JLabel jlbPeriodicidade;
    private javax.swing.JLabel jlbTipo;
    private javax.swing.JLabel jlbUltimo;
    private javax.swing.JLabel jlbVezes;
    private javax.swing.JMenuItem jmiAtualizar;
    private javax.swing.JMenuItem jmiReiniciar;
    private javax.swing.JMenuItem jmiReiniciarTodos;
    private javax.swing.JPopupMenu jpmServicos;
    private javax.swing.JPanel jpnEdicao;
    private javax.swing.JScrollPane jspServicos;
    private javax.swing.JTable jtbServicos;
    // End of variables declaration//GEN-END:variables
}
