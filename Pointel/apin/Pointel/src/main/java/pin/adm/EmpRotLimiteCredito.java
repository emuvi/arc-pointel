package pin.adm;

import pin.libbas.Configs;
import pin.libbas.Conjunto;
import pin.libbas.Globais;
import pin.libjan.Janelas;
import pin.libutl.Utl;
import pin.libutl.UtlNumLon;
import pin.libvlr.Vlrs;
import pin.modinf.Conexao;

public class EmpRotLimiteCredito extends javax.swing.JFrame {

    public Configs cfgs;
    public Conexao conexao;

    public EmpRotLimiteCredito() {
        initComponents();
        cfgs = (Configs) Globais.pega("mainConf");
        conexao = (Conexao) Globais.pega("mainConc");
        Janelas.inicia(this);
    }

    private void carrega() {
        try {
            Conjunto conjunto = conexao.busca("SELECT nome, limite_credito FROM pessoas WHERE codigo = ?",
                    new Vlrs(jtfCodigo.getText()));
            if (conjunto.primeiro()) {
                jtfNome.setText(conjunto.pgVlr("nome").pgCrs());
                jtfLimite.setText(conjunto.pgVlr("limite_credito").pgCrs());
                jtfLimite.requestFocus();
                jtfLimite.selectAll();
            } else {
                Utl.alerta("Pessoa não encontrada com o código informado.");
            }
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }

    private void salva() {
        try {
            if (conexao.opera("UPDATE pessoas SET limite_credito = ? WHERE codigo = ?",
                    new Vlrs(UtlNumLon.pega(jtfLimite.getText()), jtfCodigo.getText())) > 0) {
                Utl.alerta("Limite alterado com sucesso.");
            } else {
                Utl.alerta("Erro ao alterar o limite.");
            }
            carrega();
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlbCodigo = new javax.swing.JLabel();
        jtfCodigo = new javax.swing.JTextField();
        jlbNome = new javax.swing.JLabel();
        jtfNome = new javax.swing.JTextField();
        jlbLimite = new javax.swing.JLabel();
        jtfLimite = new javax.swing.JTextField();
        jlbSalvar = new javax.swing.JButton();
        jlbFechar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PointelAdmin - Limite de Crédito");
        setResizable(false);

        jlbCodigo.setText("Pessoa - Cod.");

        jtfCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfCodigoFocusLost(evt);
            }
        });
        jtfCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfCodigoActionPerformed(evt);
            }
        });

        jlbNome.setText("Pessoa - Nome");

        jtfNome.setEditable(false);

        jlbLimite.setText("Limite");

        jlbSalvar.setText("Salvar");
        jlbSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlbSalvarActionPerformed(evt);
            }
        });

        jlbFechar.setText("Fechar");
        jlbFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlbFecharActionPerformed(evt);
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
                        .addComponent(jlbSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlbFechar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbCodigo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfNome, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbLimite)
                            .addComponent(jtfLimite, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbCodigo)
                    .addComponent(jlbNome)
                    .addComponent(jlbLimite))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfLimite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbFechar)
                    .addComponent(jlbSalvar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtfCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfCodigoActionPerformed
        carrega();
    }//GEN-LAST:event_jtfCodigoActionPerformed

    private void jtfCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfCodigoFocusLost
        carrega();
    }//GEN-LAST:event_jtfCodigoFocusLost

    private void jlbSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jlbSalvarActionPerformed
        salva();
    }//GEN-LAST:event_jlbSalvarActionPerformed

    private void jlbFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jlbFecharActionPerformed
        Janelas.fecha(this);
    }//GEN-LAST:event_jlbFecharActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jlbCodigo;
    private javax.swing.JButton jlbFechar;
    private javax.swing.JLabel jlbLimite;
    private javax.swing.JLabel jlbNome;
    private javax.swing.JButton jlbSalvar;
    private javax.swing.JTextField jtfCodigo;
    private javax.swing.JTextField jtfLimite;
    private javax.swing.JTextField jtfNome;
    // End of variables declaration//GEN-END:variables
}
