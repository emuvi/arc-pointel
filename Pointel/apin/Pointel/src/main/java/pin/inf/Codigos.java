package pin.inf;

import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import pin.libbas.Globais;
import pin.libjan.Janelas;
import pin.libout.Base62;
import pin.libutl.Utl;
import pin.libutl.UtlCrs;
import pin.libutl.UtlIntLon;
import pin.libvlr.Vlrs;
import pin.modinf.CodigosMod;
import pin.modinf.Conexao;
import pin.modinf.Tabela;

public class Codigos extends javax.swing.JFrame {

    public Conexao conexao;

    public DefaultComboBoxModel dcmTabelas;

    public Codigos() {
        initComponents();
        conexao = (Conexao) Globais.pega("mainConc");

        dcmTabelas = new DefaultComboBoxModel();
        try {
            for (int it = 0; it < conexao.bancoTabelas().tamanho(); it++) {
                Tabela tab = conexao.bancoTabelas().pega(it);
                dcmTabelas.addElement(tab.pNome());
            }
        } catch (Exception ex) {
            Utl.registra(ex);
        }

        jcbTabela.setModel(dcmTabelas);

        Janelas.inicia(this, this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpmUltimo = new javax.swing.JPopupMenu();
        jmiUltPegaBase62 = new javax.swing.JMenuItem();
        jmiUltPegaAntigo = new javax.swing.JMenuItem();
        jlbTabela = new javax.swing.JLabel();
        jbtCarregar = new javax.swing.JButton();
        jbtModificar = new javax.swing.JButton();
        jlbFormato = new javax.swing.JLabel();
        jcbFormato = new javax.swing.JComboBox<>();
        jlbSequencia = new javax.swing.JLabel();
        jtfSequencia = new javax.swing.JTextField();
        jlbUltimo = new javax.swing.JLabel();
        jtfUltimo = new javax.swing.JTextField();
        jbtUltimoCarregar = new javax.swing.JButton();
        jbtUltimoModificar = new javax.swing.JButton();
        jcbTabela = new javax.swing.JComboBox<>();
        jbtSequencia = new javax.swing.JButton();

        jmiUltPegaBase62.setText("Pega de Base62");
        jmiUltPegaBase62.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiUltPegaBase62ActionPerformed(evt);
            }
        });
        jpmUltimo.add(jmiUltPegaBase62);

        jmiUltPegaAntigo.setText("Pega de Antigo");
        jmiUltPegaAntigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiUltPegaAntigoActionPerformed(evt);
            }
        });
        jpmUltimo.add(jmiUltPegaAntigo);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Códigos");
        setResizable(false);

        jlbTabela.setText("Tabela");

        jbtCarregar.setText("Carregar");
        jbtCarregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCarregarActionPerformed(evt);
            }
        });

        jbtModificar.setText("Modificar");
        jbtModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtModificarActionPerformed(evt);
            }
        });

        jlbFormato.setText("Formato");

        jcbFormato.setEditable(true);
        jcbFormato.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CS;", "NS;", "MX;", "CX;" }));

        jlbSequencia.setText("Sequência");

        jlbUltimo.setText("Último");

        jtfUltimo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtfUltimoMouseClicked(evt);
            }
        });

        jbtUltimoCarregar.setText("Carregar");
        jbtUltimoCarregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtUltimoCarregarActionPerformed(evt);
            }
        });

        jbtUltimoModificar.setText("Modificar");
        jbtUltimoModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtUltimoModificarActionPerformed(evt);
            }
        });

        jcbTabela.setEditable(true);
        jcbTabela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbTabelaActionPerformed(evt);
            }
        });

        jbtSequencia.setText("*");
        jbtSequencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtSequenciaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbUltimo)
                    .addComponent(jlbTabela)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jcbTabela, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtCarregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtModificar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jtfUltimo)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jbtUltimoCarregar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jbtUltimoModificar))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jcbFormato, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jlbFormato))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jlbSequencia)
                                .addComponent(jtfSequencia, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jbtSequencia))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbTabela)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbTabela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtCarregar)
                    .addComponent(jbtModificar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbFormato)
                    .addComponent(jlbSequencia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbFormato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfSequencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtSequencia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbUltimo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtUltimoModificar)
                    .addComponent(jbtUltimoCarregar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtUltimoModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtUltimoModificarActionPerformed
        try {
            CodigosMod.botaUltimo(conexao, jtfSequencia.getText(), UtlIntLon.pega(jtfUltimo.getText()));
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jbtUltimoModificarActionPerformed

    private void jbtCarregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCarregarActionPerformed
        try {
            jcbFormato.setSelectedItem(CodigosMod.pegaFormato(conexao, jcbTabela.getSelectedItem().toString()));
            jtfSequencia.setText(CodigosMod.pegaSequencia(conexao, jcbTabela.getSelectedItem().toString()));
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jbtCarregarActionPerformed

    private void jbtModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtModificarActionPerformed
        try {
            CodigosMod.botaFormato(conexao, jcbTabela.getSelectedItem().toString(), jcbFormato.getSelectedItem().toString());
            if (!jtfSequencia.getText().isEmpty()) {
                CodigosMod.botaSequencia(conexao, jcbTabela.getSelectedItem().toString(), jtfSequencia.getText());
            }
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jbtModificarActionPerformed

    private void jbtUltimoCarregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtUltimoCarregarActionPerformed
        try {
            jtfUltimo.setText(UtlIntLon.formata(CodigosMod.pegaUltimo(conexao, jtfSequencia.getText())));
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jbtUltimoCarregarActionPerformed

    private void jmiUltPegaBase62ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiUltPegaBase62ActionPerformed
        String bas62 = UtlCrs.pergunta("Qual a Base62");
        jtfUltimo.setText(UtlIntLon.formata(Base62.toBase10Lon(bas62)));
    }//GEN-LAST:event_jmiUltPegaBase62ActionPerformed

    private void jtfUltimoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfUltimoMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) {
            jpmUltimo.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jtfUltimoMouseClicked

    private void jmiUltPegaAntigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiUltPegaAntigoActionPerformed
        try {
            String tabela = (String) jcbTabela.getSelectedItem();
            if (tabela != null) {
                String ultimo = conexao.busca("SELECT ultimo FROM codigos_ultimos WHERE tabela = ?",
                        new Vlrs(tabela))
                        .pgCol().pgCrs();
                if (ultimo != null) {
                    jtfUltimo.setText(ultimo);
                } else {
                    Utl.alerta("Não encontrado");
                }
            }
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jmiUltPegaAntigoActionPerformed

    private void jbtSequenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSequenciaActionPerformed
        String tabela = (String) jcbTabela.getSelectedItem();
        if (tabela != null) {
            jtfSequencia.setText("seq" + tabela.replaceAll("\\.", ""));
        }
    }//GEN-LAST:event_jbtSequenciaActionPerformed

    private void jcbTabelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbTabelaActionPerformed
        jbtCarregarActionPerformed(evt);
    }//GEN-LAST:event_jcbTabelaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtCarregar;
    private javax.swing.JButton jbtModificar;
    private javax.swing.JButton jbtSequencia;
    private javax.swing.JButton jbtUltimoCarregar;
    private javax.swing.JButton jbtUltimoModificar;
    private javax.swing.JComboBox<String> jcbFormato;
    private javax.swing.JComboBox<String> jcbTabela;
    private javax.swing.JLabel jlbFormato;
    private javax.swing.JLabel jlbSequencia;
    private javax.swing.JLabel jlbTabela;
    private javax.swing.JLabel jlbUltimo;
    private javax.swing.JMenuItem jmiUltPegaAntigo;
    private javax.swing.JMenuItem jmiUltPegaBase62;
    private javax.swing.JPopupMenu jpmUltimo;
    private javax.swing.JTextField jtfSequencia;
    private javax.swing.JTextField jtfUltimo;
    // End of variables declaration//GEN-END:variables
}
