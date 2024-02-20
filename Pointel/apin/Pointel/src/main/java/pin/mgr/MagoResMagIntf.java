package pin.mgr;

import javax.swing.DefaultListModel;
import javax.swing.SwingUtilities;
import pin.libjan.Janelas;
import pin.libitr.Mensagem;
import pin.libutl.Utl;
import pin.modinf.Conexao;
import pin.modinf.Tabela;

public class MagoResMagIntf extends javax.swing.JFrame {

    private final MagoResIntf magoRes;
    private final DefaultListModel dlmTabelas;
    private volatile Boolean fechar;

    public MagoResMagIntf(MagoResIntf doMagoRes) {
        initComponents();
        magoRes = doMagoRes;
        dlmTabelas = new DefaultListModel<>();
        jlsTabelas.setModel(dlmTabelas);
        fechar = false;
        Janelas.inicia(this);
    }

    public MagoResMagIntf mostra() {
        Janelas.mostra(this);
        new ThdCarregar().start();
        return this;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlbStatus = new javax.swing.JLabel();
        jspTabelas = new javax.swing.JScrollPane();
        jlsTabelas = new javax.swing.JList<>();
        jtbAdicionar = new javax.swing.JButton();
        jbtFechar = new javax.swing.JButton();
        jbtDescrever = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mago Restruturação Mago");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jlbStatus.setText("Status");

        jlsTabelas.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlsTabelasValueChanged(evt);
            }
        });
        jspTabelas.setViewportView(jlsTabelas);

        jtbAdicionar.setText("Adicionar");
        jtbAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbAdicionarActionPerformed(evt);
            }
        });

        jbtFechar.setText("Fechar");
        jbtFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtFecharActionPerformed(evt);
            }
        });

        jbtDescrever.setText("Descrever");
        jbtDescrever.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtDescreverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbStatus)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jspTabelas)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbtDescrever)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtbAdicionar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtFechar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspTabelas, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtFechar)
                    .addComponent(jtbAdicionar)
                    .addComponent(jbtDescrever))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtbAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbAdicionarActionPerformed
        int[] sels = jlsTabelas.getSelectedIndices();
        for (int isel : sels) {
            magoRes.botaTabela(dlmTabelas.get(isel).toString());
        }
    }//GEN-LAST:event_jtbAdicionarActionPerformed

    private void jbtFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtFecharActionPerformed
        Janelas.fecha(this);
    }//GEN-LAST:event_jbtFecharActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        fechar = true;
    }//GEN-LAST:event_formWindowClosing

    private void jbtDescreverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtDescreverActionPerformed
        try {
            Tabela tab = (Tabela) dlmTabelas.get(jlsTabelas.getSelectedIndex());
            new Mensagem(tab.pDescricao(), true).mostra();
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jbtDescreverActionPerformed

    private void jlsTabelasValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlsTabelasValueChanged
        int sel = jlsTabelas.getSelectedIndex();
        if (sel > -1) {
            Tabela tab = (Tabela) dlmTabelas.get(sel);
            jlbStatus.setText("Banco: " + tab.pBanco().pegaNome());
        }
    }//GEN-LAST:event_jlsTabelasValueChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtDescrever;
    private javax.swing.JButton jbtFechar;
    private javax.swing.JLabel jlbStatus;
    private javax.swing.JList<String> jlsTabelas;
    private javax.swing.JScrollPane jspTabelas;
    private javax.swing.JButton jtbAdicionar;
    // End of variables declaration//GEN-END:variables

    private class ThdCarregar extends Thread {

        public ThdCarregar() {
            super("MagoResMag Carregamento");
        }

        @Override
        public void run() {
            jlbStatus.setText("Procurando Tabelas...");
            for (ConexaoCfg cng : magoRes.pPointelMigre().pMigracao().conexoes) {
                try {
                    Conexao con = cng.conecta();
                    for (int ib = 0; ib < con.bancoTabelas().tamanho(); ib++) {
                        Tabela tb = con.bancoTabelas().pega(ib);
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                dlmTabelas.addElement(tb);
                                jlsTabelas.revalidate();
                            }
                        });
                        if (fechar) {
                            break;
                        }
                    }
                } catch (Exception ex) {
                    jlbStatus.setText("Erro: " + ex.getMessage());
                }
                if (fechar) {
                    break;
                }
            }
            jlbStatus.setText("Tabelas");
        }
    }

}
