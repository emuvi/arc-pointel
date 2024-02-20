package pin.modinf;

import pin.libjan.Janelas;
import pin.modpim.Pims;

public class ConectaBaseNova extends javax.swing.JDialog {

    public ConectaBaseNova(java.awt.Frame comAssociada) throws Exception {
        super(comAssociada, true);
        initComponents();
        setLocationRelativeTo(null);
        setIconImage(Pims.pega("Conexao.png").getImage());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlbEmpresa = new javax.swing.JLabel();
        jbtCancelar = new javax.swing.JButton();
        jtfEmpresa = new javax.swing.JTextField();
        jbtSolicitar = new javax.swing.JButton();
        jlbAviso = new javax.swing.JLabel();
        jlbTitulo = new javax.swing.JLabel();
        jlbEmail = new javax.swing.JLabel();
        jlbNome = new javax.swing.JLabel();
        jtfEmail = new javax.swing.JTextField();
        jlbTelefone = new javax.swing.JLabel();
        jtfTelefone = new javax.swing.JTextField();
        jtfCidade = new javax.swing.JTextField();
        jlbCidade = new javax.swing.JLabel();
        jcbFinalidade = new javax.swing.JComboBox<>();
        jlbFinalidade = new javax.swing.JLabel();
        jtfNome = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Conexão - Solicitar Nova");
        setResizable(false);

        jlbEmpresa.setText("Empresa");

        jbtCancelar.setText("Cancelar");
        jbtCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCancelarActionPerformed(evt);
            }
        });

        jbtSolicitar.setText("Solicitar Nova");
        jbtSolicitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtSolicitarActionPerformed(evt);
            }
        });

        jlbAviso.setText("Os dados de acesso à sua nova base serão enviados por e-mail");

        jlbTitulo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlbTitulo.setText("Solicitar Nova Base de Dados");

        jlbEmail.setText("Email");

        jlbNome.setText("Nome");

        jlbTelefone.setText("Telefone");

        jlbCidade.setText("Cidade");

        jcbFinalidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Testar", "Pessoal", "Profissional" }));

        jlbFinalidade.setText("Finalidade");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbtSolicitar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtCancelar))
                    .addComponent(jlbAviso)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jlbTitulo)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jlbNome)
                                .addComponent(jtfNome, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jtfEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jlbEmpresa))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jlbFinalidade)
                                .addComponent(jcbFinalidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jlbCidade)
                                .addComponent(jtfCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jlbTelefone)
                                .addComponent(jtfTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jlbEmail)
                                .addComponent(jtfEmail)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbEmail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbTitulo)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlbNome)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jlbEmpresa)
                                    .addComponent(jlbFinalidade))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jtfEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jcbFinalidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlbCidade)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlbTelefone)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addComponent(jlbAviso)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtCancelar)
                    .addComponent(jbtSolicitar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelarActionPerformed
        Janelas.fecha(this);
    }//GEN-LAST:event_jbtCancelarActionPerformed

    private void jbtSolicitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSolicitarActionPerformed
        Janelas.fecha(this);
    }//GEN-LAST:event_jbtSolicitarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtCancelar;
    private javax.swing.JButton jbtSolicitar;
    private javax.swing.JComboBox<String> jcbFinalidade;
    private javax.swing.JLabel jlbAviso;
    private javax.swing.JLabel jlbCidade;
    private javax.swing.JLabel jlbEmail;
    private javax.swing.JLabel jlbEmpresa;
    private javax.swing.JLabel jlbFinalidade;
    private javax.swing.JLabel jlbNome;
    private javax.swing.JLabel jlbTelefone;
    private javax.swing.JLabel jlbTitulo;
    private javax.swing.JTextField jtfCidade;
    private javax.swing.JTextField jtfEmail;
    private javax.swing.JTextField jtfEmpresa;
    private javax.swing.JTextField jtfNome;
    private javax.swing.JTextField jtfTelefone;
    // End of variables declaration//GEN-END:variables
}
