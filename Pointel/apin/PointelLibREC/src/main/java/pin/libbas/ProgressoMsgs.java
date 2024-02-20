package pin.libbas;

import pin.libjan.Janelas;

public class ProgressoMsgs extends javax.swing.JFrame {

    public ProgressoMsgs(String comTitulo, String aMsg) {
        initComponents();
        setTitle(comTitulo);
        jtaMsgs.setText(aMsg);
        Janelas.inicia(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jspMsgs = new javax.swing.JScrollPane();
        jtaMsgs = new javax.swing.JTextArea();
        jbtFechar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jtaMsgs.setEditable(false);
        jtaMsgs.setColumns(20);
        jtaMsgs.setLineWrap(true);
        jtaMsgs.setRows(5);
        jtaMsgs.setWrapStyleWord(true);
        jspMsgs.setViewportView(jtaMsgs);

        jbtFechar.setText("Fechar");
        jbtFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtFecharActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbtFechar))
                    .addComponent(jspMsgs, javax.swing.GroupLayout.DEFAULT_SIZE, 831, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jspMsgs, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtFechar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtFecharActionPerformed
        Janelas.fecha(this);
    }//GEN-LAST:event_jbtFecharActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtFechar;
    private javax.swing.JScrollPane jspMsgs;
    private javax.swing.JTextArea jtaMsgs;
    // End of variables declaration//GEN-END:variables
}
