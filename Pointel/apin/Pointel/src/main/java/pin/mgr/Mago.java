package pin.mgr;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import pin.libbas.Erro;
import pin.libjan.Janelas;
import pin.libutl.Utl;

public class Mago extends javax.swing.JFrame {

    public PointelMigre pointelMigre;

    public DefaultComboBoxModel dcmTabelas;
    public DefaultListModel dlmOrdem;

    public Mago(PointelMigre doPointelMigre) {
        initComponents();
        pointelMigre = doPointelMigre;
        getRootPane().setDefaultButton(jbtProximo);
        Janelas.inicia(this, this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlbTipo = new javax.swing.JLabel();
        jcbTipo = new javax.swing.JComboBox<>();
        jbtCancelar = new javax.swing.JButton();
        jbtProximo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mago");
        setResizable(false);

        jlbTipo.setText("Tipo");

        jcbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Importação", "Atualização", "Restruturação" }));

        jbtCancelar.setText("Cancelar");
        jbtCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCancelarActionPerformed(evt);
            }
        });

        jbtProximo.setText("Próximo");
        jbtProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtProximoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbtCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtProximo))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jlbTipo)
                        .addComponent(jcbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbTipo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtProximo)
                    .addComponent(jbtCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelarActionPerformed
        Janelas.fecha(this);
    }//GEN-LAST:event_jbtCancelarActionPerformed

    private void jbtProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtProximoActionPerformed
        String sel = (String) jcbTipo.getSelectedItem();
        try {
            switch (sel) {
                case "Importação":
                    new MagoImpIntf(pointelMigre).mostra();
                    break;
                case "Atualização":
                    new MagoAtuIntf(pointelMigre).mostra();
                    break;
                case "Restruturação":
                    new MagoResIntf(pointelMigre).mostra();
                    break;
                default:
                    throw new Erro("Opção Inválida");
            }
            Janelas.fecha(this);
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jbtProximoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtCancelar;
    private javax.swing.JButton jbtProximo;
    private javax.swing.JComboBox<String> jcbTipo;
    private javax.swing.JLabel jlbTipo;
    // End of variables declaration//GEN-END:variables
}
