package pin;

import javax.swing.border.LineBorder;
import pin.libutl.UtlCores;

public class Iniciador extends javax.swing.JFrame {

    public Iniciador() {
        initComponents();
        jlbVersao.setText("Versão: " + Pointel.versao);
        setLocation(100, 100);
        getRootPane().setBorder(new LineBorder(UtlCores.Preto.pgCor(), 3, false));
        getContentPane().setBackground(UtlCores.Amarelo_ouro_claro.pgCor());
        jlbStatus.setForeground(UtlCores.Vermelho_sangue.pgCor());
        jlbVersao.setForeground(UtlCores.Vermelho_sangue.pgCor());
        jlbPointel.setForeground(UtlCores.Vermelho_sangue.pgCor());
        jlbAtalhos.setForeground(UtlCores.Vermelho_sangue.pgCor());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlbPointel = new javax.swing.JLabel();
        jlbStatus = new javax.swing.JLabel();
        jlbVersao = new javax.swing.JLabel();
        jlbAtalhos = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setUndecorated(true);
        setResizable(false);

        jlbPointel.setFont(new java.awt.Font("Times New Roman", 1, 54)); // NOI18N
        jlbPointel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbPointel.setText("Pointel");

        jlbStatus.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jlbStatus.setText("Iniciando...");

        jlbVersao.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jlbVersao.setText("Versão:");

        jlbAtalhos.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jlbAtalhos.setText("Aperte Shift Ctrl Alt F12 para mostrar os atalhos.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlbAtalhos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbStatus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlbVersao))
                    .addComponent(jlbPointel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbStatus)
                    .addComponent(jlbVersao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbPointel)
                .addGap(18, 18, 18)
                .addComponent(jlbAtalhos)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel jlbAtalhos;
    private javax.swing.JLabel jlbPointel;
    public javax.swing.JLabel jlbStatus;
    public javax.swing.JLabel jlbVersao;
    // End of variables declaration//GEN-END:variables
}
