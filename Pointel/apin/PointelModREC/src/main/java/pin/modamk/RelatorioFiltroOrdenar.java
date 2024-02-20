package pin.modamk;

import pin.libjan.Janelas;
import pin.libutl.UtlCrs;
import javax.swing.DefaultListModel;

public class RelatorioFiltroOrdenar extends javax.swing.JFrame {

    public RelatorioFiltro filtro;
    private DefaultListModel dlmTodos;
    private DefaultListModel dlmSelecionados;

    public RelatorioFiltroOrdenar(RelatorioFiltro aFiltro) {
        initComponents();
        filtro = aFiltro;
        setTitle(filtro.relatorio.titulo + " - Ordenar Relatório");

        dlmTodos = new DefaultListModel();
        dlmSelecionados = new DefaultListModel();
        jlsTodos.setModel(dlmTodos);
        jlsSelecionados.setModel(dlmSelecionados);

        for (String cmpTit : filtro.pegaCamposTitulosOrdenaveis()) {
            dlmTodos.addElement(cmpTit);
        }

        for (String selecionado : filtro.ordemTitulos.split(";")) {
            if (selecionado != null) {
                if (!selecionado.isEmpty()) {
                    dlmSelecionados.addElement(selecionado);
                }
            }
        }

        getRootPane().setDefaultButton(jbtOrdenar);

        Janelas.inicia(this, this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnOrdenar = new javax.swing.JPanel();
        jspTodos = new javax.swing.JScrollPane();
        jlsTodos = new javax.swing.JList();
        jspSelecionados = new javax.swing.JScrollPane();
        jlsSelecionados = new javax.swing.JList();
        jbtOrdenar = new javax.swing.JButton();
        jbtCancelar = new javax.swing.JButton();
        jbtAdicionar = new javax.swing.JButton();
        ckCrescente = new javax.swing.JCheckBox();
        jbtRemover = new javax.swing.JButton();
        jbtLimpar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ordenar");
        setResizable(false);

        jlsTodos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlsTodosMouseClicked(evt);
            }
        });
        jspTodos.setViewportView(jlsTodos);

        jlsSelecionados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlsSelecionadosMouseClicked(evt);
            }
        });
        jspSelecionados.setViewportView(jlsSelecionados);

        jbtOrdenar.setMnemonic('O');
        jbtOrdenar.setText("Ordenar");
        jbtOrdenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOrdenarActionPerformed(evt);
            }
        });

        jbtCancelar.setMnemonic('C');
        jbtCancelar.setText("Cancelar");
        jbtCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCancelarActionPerformed(evt);
            }
        });

        jbtAdicionar.setText("Adicionar");
        jbtAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAdicionarActionPerformed(evt);
            }
        });

        ckCrescente.setSelected(true);
        ckCrescente.setText("Crescente");
        ckCrescente.setToolTipText("Descrescente");

        jbtRemover.setText("Remover");
        jbtRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRemoverActionPerformed(evt);
            }
        });

        jbtLimpar.setText("Limpar");
        jbtLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtLimparActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnOrdenarLayout = new javax.swing.GroupLayout(jpnOrdenar);
        jpnOrdenar.setLayout(jpnOrdenarLayout);
        jpnOrdenarLayout.setHorizontalGroup(
            jpnOrdenarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnOrdenarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnOrdenarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpnOrdenarLayout.createSequentialGroup()
                        .addComponent(jspTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpnOrdenarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jbtRemover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbtAdicionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbtLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ckCrescente))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jspSelecionados, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpnOrdenarLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbtOrdenar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtCancelar)))
                .addContainerGap())
        );
        jpnOrdenarLayout.setVerticalGroup(
            jpnOrdenarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnOrdenarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnOrdenarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspTodos)
                    .addComponent(jspSelecionados, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                    .addGroup(jpnOrdenarLayout.createSequentialGroup()
                        .addComponent(jbtAdicionar)
                        .addGap(7, 7, 7)
                        .addComponent(ckCrescente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtRemover)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtLimpar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnOrdenarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtCancelar)
                    .addComponent(jbtOrdenar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jpnOrdenar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jpnOrdenar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtOrdenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOrdenarActionPerformed
        filtro.ordem = "";
        filtro.ordemTitulos = "";

        for (int i1 = 0; i1 < dlmSelecionados.size(); i1++) {
            String campoOrdem = (String) dlmSelecionados.get(i1);
            filtro.ordemTitulos = filtro.ordemTitulos + (filtro.ordemTitulos.isEmpty() ? "" : ";") + campoOrdem;
            String campoNome = UtlCrs.corta(campoOrdem, 7);
            if (campoOrdem.endsWith(" (Decr)")) {
                campoOrdem = " DESC";
            } else {
                campoOrdem = "";
            }
            filtro.ordem = filtro.ordem + filtro.pegaCampoNomeCompostoTitulo(campoNome) + campoOrdem + ", ";
        }

        if (!filtro.ordem.isEmpty()) {
            filtro.ordem = UtlCrs.corta(filtro.ordem, 2);
        }

        filtro.jfrJanela.requestFocus();

        Janelas.execWindowClosing(this);
        setVisible(false);
    }//GEN-LAST:event_jbtOrdenarActionPerformed

    private void jbtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelarActionPerformed
        filtro.ordem = "";

        Janelas.execWindowClosing(this);
        setVisible(false);
    }//GEN-LAST:event_jbtCancelarActionPerformed

    private void jbtAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAdicionarActionPerformed
        int[] sels = jlsTodos.getSelectedIndices();
        for (int sel : sels) {
            dlmSelecionados.addElement(dlmTodos.get(sel) + (ckCrescente.isSelected() ? " (Cres)" : " (Decr)"));
        }
    }//GEN-LAST:event_jbtAdicionarActionPerformed

    private void jbtRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRemoverActionPerformed
        int[] sels = jlsSelecionados.getSelectedIndices();
        for (int i1 = sels.length - 1; i1 > -1; i1--) {
            dlmSelecionados.removeElementAt(sels[i1]);
        }
    }//GEN-LAST:event_jbtRemoverActionPerformed

    private void jbtLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtLimparActionPerformed
        dlmSelecionados.removeAllElements();
    }//GEN-LAST:event_jbtLimparActionPerformed

    private void jlsTodosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlsTodosMouseClicked
        if (evt.getClickCount() >= 2) {
            jbtAdicionarActionPerformed(null);
        }
    }//GEN-LAST:event_jlsTodosMouseClicked

    private void jlsSelecionadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlsSelecionadosMouseClicked
        if (evt.getClickCount() >= 2) {
            jbtRemoverActionPerformed(null);
        }
    }//GEN-LAST:event_jlsSelecionadosMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox ckCrescente;
    private javax.swing.JButton jbtAdicionar;
    private javax.swing.JButton jbtCancelar;
    private javax.swing.JButton jbtLimpar;
    private javax.swing.JButton jbtOrdenar;
    private javax.swing.JButton jbtRemover;
    private javax.swing.JList jlsSelecionados;
    private javax.swing.JList jlsTodos;
    private javax.swing.JPanel jpnOrdenar;
    private javax.swing.JScrollPane jspSelecionados;
    private javax.swing.JScrollPane jspTodos;
    // End of variables declaration//GEN-END:variables
}
