package pin.libamk;

import pin.libvtf.TecladoVtf;
import pin.libjan.JanAtalhos;
import pin.libjan.Janelas;

public class JanAtalhosIntf extends javax.swing.JFrame {

    public JanAtalhosIntf() {
        initComponents();
        Janelas.inicia(this);
    }

    public JanAtalhosIntf mostra() {
        Janelas.mostra(this);
        return this;
    }

    public JanAtalhosIntf fecha() {
        Janelas.fecha(this);
        return this;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlbNome = new javax.swing.JLabel();
        jtfNome = new javax.swing.JTextField();
        jlbAtalho = new javax.swing.JLabel();
        jtfAtalho = new javax.swing.JTextField();
        jbtAuxiliar = new javax.swing.JButton();
        jbtPega = new javax.swing.JButton();
        jbtBota = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Atalhos");

        jlbNome.setText("Nome");

        jlbAtalho.setText("Atalho");

        jbtAuxiliar.setText("jButton1");
        jbtAuxiliar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAuxiliarActionPerformed(evt);
            }
        });

        jbtPega.setText("Pega");
        jbtPega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtPegaActionPerformed(evt);
            }
        });

        jbtBota.setText("Bota");
        jbtBota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtBotaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbNome)
                    .addComponent(jlbAtalho)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jbtPega)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jbtBota))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jtfAtalho)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtAuxiliar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jtfNome, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbAtalho)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfAtalho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtAuxiliar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtBota)
                    .addComponent(jbtPega))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtPegaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtPegaActionPerformed
        jtfAtalho.setText(JanAtalhos.pega(jtfNome.getText(), ""));
    }//GEN-LAST:event_jbtPegaActionPerformed

    private void jbtBotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtBotaActionPerformed
        JanAtalhos.bota(jtfNome.getText(), jtfAtalho.getText());
    }//GEN-LAST:event_jbtBotaActionPerformed

    private void jbtAuxiliarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAuxiliarActionPerformed
        new TecladoVtf(jtfAtalho, jtfAtalho.getText()) {
            @Override
            public Boolean aoMudar(String comTeclado) {
                jtfAtalho.setText(comTeclado);
                return true;
            }
        }.mostra();
    }//GEN-LAST:event_jbtAuxiliarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtAuxiliar;
    private javax.swing.JButton jbtBota;
    private javax.swing.JButton jbtPega;
    private javax.swing.JLabel jlbAtalho;
    private javax.swing.JLabel jlbNome;
    private javax.swing.JTextField jtfAtalho;
    private javax.swing.JTextField jtfNome;
    // End of variables declaration//GEN-END:variables
}
