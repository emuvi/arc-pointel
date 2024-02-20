package pin.modinf;

import pin.libjan.Janelas;
import pin.libutl.Utl;

public class ConexaoVer extends javax.swing.JFrame {

    public Conexao conexao;

    public ConexaoVer(Conexao aConexao) {
        initComponents();
        conexao = aConexao;
        Janelas.inicia(this);
    }

    public void atualizar() {
        jtfServidor.setText(conexao.pegaServidor());
        if (conexao.banco() == null) {
            jtfBanco.setText("");
        } else {
            jtfBanco.setText(conexao.banco().pegaTipo().toString());
        }
        jtfBase.setText(conexao.pegaBase());
        if (conexao.pegaBaseUsuario() != null) {
            jtfUsuario.setText(conexao.pegaBaseUsuario());
        } else {
            jtfUsuario.setText(conexao.pegaUsuario());
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlbServidor = new javax.swing.JLabel();
        jtfServidor = new javax.swing.JTextField();
        jlbBanco = new javax.swing.JLabel();
        jtfBanco = new javax.swing.JTextField();
        jlbBase = new javax.swing.JLabel();
        jtfBase = new javax.swing.JTextField();
        jlbUsuario = new javax.swing.JLabel();
        jtfUsuario = new javax.swing.JTextField();
        jbtTestar = new javax.swing.JButton();
        jbtSenha = new javax.swing.JButton();
        jbtConectar = new javax.swing.JButton();
        jbtFechar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Conexão");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jlbServidor.setText("Servidor");

        jtfServidor.setEditable(false);

        jlbBanco.setText("Banco");

        jtfBanco.setEditable(false);

        jlbBase.setText("Base");

        jtfBase.setEditable(false);

        jlbUsuario.setText("Usuário");

        jtfUsuario.setEditable(false);

        jbtTestar.setText("Testar");
        jbtTestar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtTestarActionPerformed(evt);
            }
        });

        jbtSenha.setText("Senha");
        jbtSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtSenhaActionPerformed(evt);
            }
        });

        jbtConectar.setText("Conectar");
        jbtConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtConectarActionPerformed(evt);
            }
        });

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlbServidor, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jtfServidor, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jlbBanco)
                                .addComponent(jtfBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jlbBase)
                                .addComponent(jtfBase, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jlbUsuario)
                                .addComponent(jtfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jbtTestar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jbtSenha)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbtConectar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jbtFechar))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbServidor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfServidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbBanco)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbBase)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfBase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtFechar)
                    .addComponent(jbtConectar)
                    .addComponent(jbtTestar)
                    .addComponent(jbtSenha))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtFecharActionPerformed
        Janelas.fecha(this);
    }//GEN-LAST:event_jbtFecharActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        atualizar();
    }//GEN-LAST:event_formWindowActivated

    private void jbtConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtConectarActionPerformed
        try {
            conexao.limpa();
            conexao.conectaBase(this, false);
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jbtConectarActionPerformed

    private void jbtTestarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtTestarActionPerformed
        if (conexao.estaConectado()) {
            Utl.alerta("Conectada!");
        } else {
            Utl.alerta("Desconectada!");
        }
    }//GEN-LAST:event_jbtTestarActionPerformed

    private void jbtSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSenhaActionPerformed
        if (conexao.estaConectado()) {
            conexao.alterarSenha();
        } else {
            Utl.alerta("A Conexão Não Está Conectada.");
        }
    }//GEN-LAST:event_jbtSenhaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtConectar;
    private javax.swing.JButton jbtFechar;
    private javax.swing.JButton jbtSenha;
    private javax.swing.JButton jbtTestar;
    private javax.swing.JLabel jlbBanco;
    private javax.swing.JLabel jlbBase;
    private javax.swing.JLabel jlbServidor;
    private javax.swing.JLabel jlbUsuario;
    private javax.swing.JTextField jtfBanco;
    private javax.swing.JTextField jtfBase;
    private javax.swing.JTextField jtfServidor;
    private javax.swing.JTextField jtfUsuario;
    // End of variables declaration//GEN-END:variables
}
