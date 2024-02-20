package pin.modinf;

import java.util.Objects;
import pin.libbas.Erro;
import pin.libjan.Janelas;
import pin.libutl.Utl;
import pin.libutl.UtlCrs;

public class ConectaBaseCfg extends javax.swing.JDialog {

    private ConectaBase conectaBase;

    public ConectaBaseCfg(ConectaBase daConectaBase) {
        super(daConectaBase, true);
        conectaBase = daConectaBase;
        initComponents();
        inicia();
        getRootPane().setDefaultButton(jbtSalvar);
        Janelas.inicia(this);
    }

    private void inicia() {
        jtfBanco.setText(conectaBase.pegaConBanco().toString());
        jtfServidor.setText(conectaBase.pegaConServidor());
        jtfPorta.setText(conectaBase.pegaConPorta().toString());
        jtfBase.setText(conectaBase.pegaConBase());
        jtfUsuario.setText(conectaBase.pegaConUsuario());
    }

    public ConectaBaseCfg mostra() {
        Janelas.mostra(this);
        return this;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlbBanco = new javax.swing.JLabel();
        jtfBanco = new javax.swing.JTextField();
        jlbServidor = new javax.swing.JLabel();
        jtfServidor = new javax.swing.JTextField();
        jlbPorta = new javax.swing.JLabel();
        jtfPorta = new javax.swing.JTextField();
        jlbBase = new javax.swing.JLabel();
        jtfBase = new javax.swing.JTextField();
        jlbUsuario = new javax.swing.JLabel();
        jtfUsuario = new javax.swing.JTextField();
        jlbSenha = new javax.swing.JLabel();
        jpfSenha = new javax.swing.JPasswordField();
        jbtSalvar = new javax.swing.JButton();
        jbtCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configs de Acesso");

        jlbBanco.setText("Banco");

        jlbServidor.setText("Servidor");

        jlbPorta.setText("Porta");

        jlbBase.setText("Base");

        jlbUsuario.setText("Usuário");

        jlbSenha.setText("Senha");

        jbtSalvar.setText("Salvar");
        jbtSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtSalvarActionPerformed(evt);
            }
        });

        jbtCancelar.setText("Cancelar");
        jbtCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbBanco)
                            .addComponent(jtfBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbServidor)
                            .addComponent(jtfServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbPorta)
                            .addComponent(jtfPorta)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbBase)
                            .addComponent(jtfBase, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbUsuario)
                            .addComponent(jtfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbSenha)
                            .addComponent(jpfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jbtSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtCancelar)))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlbBanco)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlbServidor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfServidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlbPorta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlbBase)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jtfBase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jpfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jlbSenha)
                                .addGap(29, 29, 29))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbUsuario)
                        .addGap(30, 30, 30)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtCancelar)
                    .addComponent(jbtSalvar))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelarActionPerformed
        Janelas.fecha(this);
    }//GEN-LAST:event_jbtCancelarActionPerformed

    private void jbtSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSalvarActionPerformed
        try {
            String senha = new String(jpfSenha.getPassword());
            if (!senha.isEmpty()) {
                String confirmacao = UtlCrs.perguntaSenha("Confirme a Senha");
                if (!Objects.equals(senha, confirmacao)) {
                    throw new Erro("Senha e Confirmação Devem Ser Iguais");
                }
            }
            conectaBase.botaConBanco(jtfBanco.getText());
            conectaBase.botaConServidor(jtfServidor.getText());
            conectaBase.botaConPorta(Integer.parseInt(jtfPorta.getText()));
            conectaBase.botaConBase(jtfBase.getText());
            conectaBase.botaConUsuario(jtfUsuario.getText());
            if (!senha.isEmpty()) {
                conectaBase.botaConSenha(senha);
            }
            Janelas.fecha(this);
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jbtSalvarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtCancelar;
    private javax.swing.JButton jbtSalvar;
    private javax.swing.JLabel jlbBanco;
    private javax.swing.JLabel jlbBase;
    private javax.swing.JLabel jlbPorta;
    private javax.swing.JLabel jlbSenha;
    private javax.swing.JLabel jlbServidor;
    private javax.swing.JLabel jlbUsuario;
    private javax.swing.JPasswordField jpfSenha;
    private javax.swing.JTextField jtfBanco;
    private javax.swing.JTextField jtfBase;
    private javax.swing.JTextField jtfPorta;
    private javax.swing.JTextField jtfServidor;
    private javax.swing.JTextField jtfUsuario;
    // End of variables declaration//GEN-END:variables
}
