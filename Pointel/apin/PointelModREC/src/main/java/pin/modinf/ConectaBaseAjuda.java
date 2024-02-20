package pin.modinf;

import pin.libjan.Janelas;
import pin.libutl.Utl;
import pin.modpim.Pims;

public class ConectaBaseAjuda extends javax.swing.JDialog {

    java.awt.Frame associada;

    public ConectaBaseAjuda(java.awt.Frame comAssociada) throws Exception {
        super(comAssociada, true);
        initComponents();
        associada = comAssociada;
        setIconImage(Pims.pega("Conexao.png").getImage());
        jtpAjuda.setContentType("text/html");
        jtpAjuda.setText("<html>"
                + "<b>Ajuda</b>"
                + "</html>");
        Janelas.inicia(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbtFechar = new javax.swing.JButton();
        jbtNova = new javax.swing.JButton();
        jspAjuda = new javax.swing.JScrollPane();
        jtpAjuda = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Conexão - Ajuda");

        jbtFechar.setText("Fechar");
        jbtFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtFecharActionPerformed(evt);
            }
        });

        jbtNova.setText("Solicitar Nova");
        jbtNova.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtNovaActionPerformed(evt);
            }
        });

        jtpAjuda.setEditable(false);
        jspAjuda.setViewportView(jtpAjuda);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 352, Short.MAX_VALUE)
                        .addComponent(jbtNova)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtFechar))
                    .addComponent(jspAjuda))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jspAjuda, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtFechar)
                    .addComponent(jbtNova))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtFecharActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jbtFecharActionPerformed

    private void jbtNovaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtNovaActionPerformed
        try {
            setVisible(false);
            new ConectaBaseNova(associada).setVisible(true);
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jbtNovaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtFechar;
    private javax.swing.JButton jbtNova;
    private javax.swing.JScrollPane jspAjuda;
    private javax.swing.JTextPane jtpAjuda;
    // End of variables declaration//GEN-END:variables
}
