package pin.inf;

import pin.libjan.Janelas;
import pin.libutl.Utl;
import pin.libutl.UtlLog;
import pin.modinf.TabCampo;
import pin.modinf.Tabela;

public class TabelaIntf extends javax.swing.JFrame {

    public Tabela tabela;

    public TabelaIntf(Tabela daTabela) throws Exception {
        initComponents();
        setTitle("Colunas da Tabela " + daTabela.pEsquemaENome());
        tabela = daTabela;
        edtCampos.controlador().botaMostraNumeroLinhas();
        edtCampos.controlador().botaColunas(new String[]{"Chave", "Nome", "Tipo", "Tamanho", "Precisão", "Padrão"});
        edtCampos.mEditavel(false);
        for (TabCampo cmp : tabela.pCampos()) {
            edtCampos.controlador().botaLinha((UtlLog.pega(cmp.ehChave()) ? "Sim" : "Não"), cmp.pNome(), cmp.pTipo(), cmp.pTamanho(), cmp.pPrecisao(), cmp.pPadrao());
        }
        Janelas.inicia(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        edtCampos = new pin.libamg.EdtPla();
        jbtEstrangeiros = new javax.swing.JButton();
        jbtIndices = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Colunas da Tabela");

        jbtEstrangeiros.setText("Estrangeiros");
        jbtEstrangeiros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtEstrangeirosActionPerformed(evt);
            }
        });

        jbtIndices.setText("Indices");
        jbtIndices.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtIndicesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(edtCampos, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbtEstrangeiros)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtIndices)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(edtCampos, javax.swing.GroupLayout.PREFERRED_SIZE, 264, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtEstrangeiros)
                    .addComponent(jbtIndices))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtEstrangeirosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtEstrangeirosActionPerformed
        try {
            new TabEstrangeirosIntf(tabela).mostra();
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jbtEstrangeirosActionPerformed

    private void jbtIndicesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtIndicesActionPerformed
        try {
            new TabIndicesIntf(tabela).mostra();
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jbtIndicesActionPerformed

    public void mostra() {
        Janelas.mostra(this);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private pin.libamg.EdtPla edtCampos;
    private javax.swing.JButton jbtEstrangeiros;
    private javax.swing.JButton jbtIndices;
    // End of variables declaration//GEN-END:variables
}
