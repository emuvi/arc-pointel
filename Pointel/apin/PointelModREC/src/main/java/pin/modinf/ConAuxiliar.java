package pin.modinf;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import pin.libbas.Dados;
import pin.libetf.EdtIntf;
import pin.libetf.TexEtf;
import pin.libjan.Janelas;
import pin.libutl.Utl;
import pin.libutl.UtlTex;

public class ConAuxiliar extends javax.swing.JFrame {

    private Conexao conexao;
    private DefaultComboBoxModel dcmTipo;

    public ConAuxiliar() {
        initComponents();
        conexao = new Conexao();
        dcmTipo = new DefaultComboBoxModel(Bancos.Tp.values());
        jcbTipo.setModel(dcmTipo);
        Janelas.inicia(this);
    }

    public ConAuxiliar mostra() {
        Janelas.mostra(this);
        return this;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlbTipo = new javax.swing.JLabel();
        jcbTipo = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jtfServidor = new javax.swing.JTextField();
        jlbPorta = new javax.swing.JLabel();
        jsnPorta = new javax.swing.JSpinner();
        jlbBase = new javax.swing.JLabel();
        jtfBase = new javax.swing.JTextField();
        jlbUsuario = new javax.swing.JLabel();
        jtfUsuario = new javax.swing.JTextField();
        jlbSenha = new javax.swing.JLabel();
        jpfSenha = new javax.swing.JPasswordField();
        jbtAbre = new javax.swing.JButton();
        jbtComandos = new javax.swing.JButton();
        jbtImportar = new javax.swing.JButton();
        jbtExportar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Conexão Auxiliar");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jlbTipo.setText("Tipo");

        jLabel1.setText("Servidor");

        jlbPorta.setText("Porta");

        jlbBase.setText("Base");

        jlbUsuario.setText("Usuário");

        jlbSenha.setText("Senha");

        jbtAbre.setMnemonic('A');
        jbtAbre.setText("Abre");
        jbtAbre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAbreActionPerformed(evt);
            }
        });

        jbtComandos.setMnemonic('C');
        jbtComandos.setText("Comandos");
        jbtComandos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtComandosActionPerformed(evt);
            }
        });

        jbtImportar.setMnemonic('I');
        jbtImportar.setText("Importar");
        jbtImportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtImportarActionPerformed(evt);
            }
        });

        jbtExportar.setMnemonic('E');
        jbtExportar.setText("Exportar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jtfBase, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlbSenha)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jpfSenha)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jcbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbTipo)
                                    .addComponent(jlbBase))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jtfServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel1))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jsnPorta, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jlbPorta)))
                                    .addComponent(jlbUsuario)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbtAbre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtComandos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtImportar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtExportar)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbTipo)
                    .addComponent(jLabel1)
                    .addComponent(jlbPorta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfServidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jsnPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbBase)
                    .addComponent(jlbUsuario)
                    .addComponent(jlbSenha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfBase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtAbre)
                    .addComponent(jbtComandos)
                    .addComponent(jbtImportar)
                    .addComponent(jbtExportar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        conexao.limpa();
    }//GEN-LAST:event_formWindowClosing

    private void jbtAbreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAbreActionPerformed
        try {
            if (conexao.estaConectado()) {
                conexao.limpa();
                jbtAbre.setText("Abre");
            } else {
                conexao.configura((Bancos.Tp) jcbTipo.getSelectedItem(), jtfServidor.getText(), (Integer) jsnPorta.getValue(), jtfBase.getText(), jtfUsuario.getText(), new String(jpfSenha.getPassword()));
                conexao.conecta();
                jbtAbre.setText("Fecha");
            }
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jbtAbreActionPerformed

    private void jbtComandosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtComandosActionPerformed
        try {
            TexEtf texIntf = new TexEtf();
            texIntf.constroi();
            texIntf.edt().pPopMenu().botaSeparador();
            texIntf.edt().pPopMenu().bota("Procurar", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        EdtIntf.abreEtf(conexao.busca(texIntf.pgVlr().pgCrs("")), Dados.Tp.Pla).edt().mEditavel(false);
                    } catch (Exception ex) {
                        Utl.registra(ex);
                    }
                }
            });
            texIntf.edt().pPopMenu().bota("Executar", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String comando = texIntf.edt().pgVlr("").pgCrs();
                        if (!comando.contains(";")) {
                            Utl.alerta("Realizado, afetando " + conexao.opera(comando) + " registros.");
                        } else {
                            String cmds[] = comando.split("-prox-");
                            TexEtf resultados = new TexEtf();
                            resultados.mostra();
                            String retornos = "";
                            for (String cmd : cmds) {
                                cmd = cmd.trim();
                                retornos = UtlTex.soma(retornos, "SQL:");
                                retornos = UtlTex.soma(retornos, cmd);
                                retornos = UtlTex.soma(retornos, "Resultado:");
                                try {
                                    int afetados = conexao.opera(cmd);
                                    retornos = UtlTex.soma(retornos, "Realizado, afetando " + afetados + " registros.");
                                } catch (Exception ex) {
                                    retornos = UtlTex.soma(retornos, "Erro: " + ex.getMessage());
                                }
                                retornos = UtlTex.soma(retornos, "");
                                resultados.edt().mdVlr(retornos);
                            }
                        }
                    } catch (Exception ex) {
                        Utl.registra(ex);
                    }
                }
            });
            texIntf.mostra("Comandos no Auxiliar");
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jbtComandosActionPerformed

    private void jbtImportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtImportarActionPerformed
        new ConAuxImportar(conexao).mostra();
    }//GEN-LAST:event_jbtImportarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton jbtAbre;
    private javax.swing.JButton jbtComandos;
    private javax.swing.JButton jbtExportar;
    private javax.swing.JButton jbtImportar;
    private javax.swing.JComboBox<String> jcbTipo;
    private javax.swing.JLabel jlbBase;
    private javax.swing.JLabel jlbPorta;
    private javax.swing.JLabel jlbSenha;
    private javax.swing.JLabel jlbTipo;
    private javax.swing.JLabel jlbUsuario;
    private javax.swing.JPasswordField jpfSenha;
    private javax.swing.JSpinner jsnPorta;
    private javax.swing.JTextField jtfBase;
    private javax.swing.JTextField jtfServidor;
    private javax.swing.JTextField jtfUsuario;
    // End of variables declaration//GEN-END:variables
}
