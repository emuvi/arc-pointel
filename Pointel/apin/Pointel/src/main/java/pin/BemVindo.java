package pin;

import javax.swing.JFrame;
import javax.swing.border.LineBorder;
import pin.libjan.Janelas;
import pin.libutl.Utl;
import pin.libutl.UtlCores;
import pin.libutl.UtlRed;

public class BemVindo extends javax.swing.JDialog {

    public BemVindo() {
        super((JFrame) null, true);
        initComponents();
        getRootPane().setBorder(new LineBorder(UtlCores.Preto.pgCor(), 3, false));
        getContentPane().setBackground(UtlCores.Amarelo_ouro_claro.clareia(30).pgCor());
        jlbBemVindo.setForeground(UtlCores.Vermelho_sangue.pgCor());
        jlbPointel.setForeground(UtlCores.Vermelho_sangue.pgCor());
        Janelas.inicia(this);
    }

    public BemVindo mostra() {
        Janelas.mostra(this);
        return this;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlbBemVindo = new javax.swing.JLabel();
        jlbPointel = new javax.swing.JLabel();
        jbtFecha = new javax.swing.JButton();
        jbtOrientacoes = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("BemVindo");
        setAlwaysOnTop(true);
        setUndecorated(true);

        jlbBemVindo.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        jlbBemVindo.setText("Bem-Vindo");

        jlbPointel.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        jlbPointel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlbPointel.setText("ao Pointel!");

        jbtFecha.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jbtFecha.setText("Começar a Usar");
        jbtFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtFechaActionPerformed(evt);
            }
        });

        jbtOrientacoes.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jbtOrientacoes.setText("Ver Orientações");
        jbtOrientacoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOrientacoesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbPointel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jlbBemVindo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jbtOrientacoes, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jbtFecha))))
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jlbBemVindo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbPointel)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtOrientacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtFechaActionPerformed
        Janelas.fecha(this);
    }//GEN-LAST:event_jbtFechaActionPerformed

    private void jbtOrientacoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOrientacoesActionPerformed
        Janelas.fecha(this);
        try {
            UtlRed.abre("www.pointel.com.br/orientacoes.php");
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jbtOrientacoesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtFecha;
    private javax.swing.JButton jbtOrientacoes;
    private javax.swing.JLabel jlbBemVindo;
    private javax.swing.JLabel jlbPointel;
    // End of variables declaration//GEN-END:variables
}
