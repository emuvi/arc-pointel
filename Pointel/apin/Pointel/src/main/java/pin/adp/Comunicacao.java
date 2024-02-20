package pin.adp;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListModel;
import pin.libbas.Conjunto;
import pin.libbas.Globais;
import pin.libjan.Janelas;
import pin.libutl.Utl;
import pin.libvlr.Vlrs;
import pin.modinf.Conexao;
import pin.modpim.Pims;

public class Comunicacao extends javax.swing.JFrame {

    public Conexao conexao;

    public DefaultListModel dlmCanais;

    public Comunicacao() throws Exception {
        initComponents();
        setIconImage(Pims.pega("Comunicacao.png").getImage());
        conexao = (Conexao) Globais.pega("mainConc");
        conexao.deveConectar(this);
        dlmCanais = new DefaultListModel();
        jlsCanais.setModel(dlmCanais);
        Janelas.inicia(this);
    }

    public void atualizar() throws Exception {
        dlmCanais.clear();
        dlmCanais.addElement("Interno");
        Conjunto cns = conexao.busca("SELECT nome FROM canais WHERE dono = CURRENT_USER");
        while (cns.proximo()) {
            dlmCanais.addElement(cns.pgVlr(1));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpmCanais = new javax.swing.JPopupMenu();
        jmiAbre = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jmiAdiciona = new javax.swing.JMenuItem();
        jmiEdita = new javax.swing.JMenuItem();
        jmiExclui = new javax.swing.JMenuItem();
        jspCanais = new javax.swing.JScrollPane();
        jlsCanais = new javax.swing.JList<>();

        jmiAbre.setText("Abre");
        jmiAbre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiAbreActionPerformed(evt);
            }
        });
        jpmCanais.add(jmiAbre);
        jpmCanais.add(jSeparator1);

        jmiAdiciona.setText("Adiciona");
        jmiAdiciona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiAdicionaActionPerformed(evt);
            }
        });
        jpmCanais.add(jmiAdiciona);

        jmiEdita.setText("Edita");
        jmiEdita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiEditaActionPerformed(evt);
            }
        });
        jpmCanais.add(jmiEdita);

        jmiExclui.setText("Exclui");
        jmiExclui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiExcluiActionPerformed(evt);
            }
        });
        jpmCanais.add(jmiExclui);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Comunicação");
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        jspCanais.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jspCanaisMouseClicked(evt);
            }
        });

        jlsCanais.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jlsCanais.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlsCanaisMouseClicked(evt);
            }
        });
        jlsCanais.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jlsCanaisKeyPressed(evt);
            }
        });
        jspCanais.setViewportView(jlsCanais);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jspCanais, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jspCanais, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiAdicionaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAdicionaActionPerformed

    }//GEN-LAST:event_jmiAdicionaActionPerformed

    public void abreCanal() {

    }

    private void jlsCanaisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlsCanaisMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) {
            jpmCanais.show(evt.getComponent(), evt.getX(), evt.getY());
        } else if (evt.getButton() == MouseEvent.BUTTON1) {
            if (evt.getClickCount() >= 2) {
                abreCanal();
            }
        }
    }//GEN-LAST:event_jlsCanaisMouseClicked

    private void jspCanaisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jspCanaisMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) {
            jpmCanais.show(evt.getComponent(), evt.getX(), evt.getY());
        } else if (evt.getButton() == MouseEvent.BUTTON1) {
            if (evt.getClickCount() >= 2) {
                abreCanal();
            }
        }
    }//GEN-LAST:event_jspCanaisMouseClicked

    private void jmiEditaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiEditaActionPerformed

    }//GEN-LAST:event_jmiEditaActionPerformed

    private void jmiExcluiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiExcluiActionPerformed
        String sel = jlsCanais.getSelectedValue();
        if (sel != null) {
            if (sel.equals("Interno")) {
                Utl.alerta("Impossível Excluir o Canal Interno.");
            } else if (Utl.continua()) {
                try {
                    conexao.opera("DELETE FROM canais WHERE nome = ? AND dono = ?",
                            new Vlrs(sel, conexao.pegaBaseUsuario()));
                    atualizar();
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        }
    }//GEN-LAST:event_jmiExcluiActionPerformed

    private void jmiAbreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAbreActionPerformed
        abreCanal();
    }//GEN-LAST:event_jmiAbreActionPerformed

    private void jlsCanaisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jlsCanaisKeyPressed
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            abreCanal();
        }
    }//GEN-LAST:event_jlsCanaisKeyPressed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        try {
            atualizar();
            jlsCanais.requestFocus();
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_formWindowGainedFocus

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JList<String> jlsCanais;
    private javax.swing.JMenuItem jmiAbre;
    private javax.swing.JMenuItem jmiAdiciona;
    private javax.swing.JMenuItem jmiEdita;
    private javax.swing.JMenuItem jmiExclui;
    private javax.swing.JPopupMenu jpmCanais;
    private javax.swing.JScrollPane jspCanais;
    // End of variables declaration//GEN-END:variables
}
