package pin.inf;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.DefaultListModel;
import pin.libbas.Globais;
import pin.libjan.Janelas;
import pin.libutl.Utl;
import pin.libutl.UtlCrs;
import pin.modinf.BancoTabelas;
import pin.modinf.Conexao;
import pin.modinf.Tabela;

public class TabelasIntf extends javax.swing.JFrame {

    public Conexao conexao;

    public DefaultListModel dlmTabelas;
    public BancoTabelas tabelas;

    public TabelasIntf() throws Exception {
        initComponents();
        conexao = (Conexao) Globais.pega("mainConc");
        dlmTabelas = new DefaultListModel();
        jlsTabelas.setModel(dlmTabelas);
        tabelas = conexao.bancoTabelas();
        dlmTabelas.clear();
        for (int it = 0; it < tabelas.tamanho(); it++) {
            Tabela tab = tabelas.pega(it);
            dlmTabelas.addElement(tab.pEsquemaENome());
        }
        Janelas.inicia(this);
    }

    public List<String> pegaSelecionadas() {
        return jlsTabelas.getSelectedValuesList();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jspTabelas = new javax.swing.JScrollPane();
        jlsTabelas = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tabelas");

        jlsTabelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlsTabelasMouseClicked(evt);
            }
        });
        jlsTabelas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jlsTabelasKeyPressed(evt);
            }
        });
        jspTabelas.setViewportView(jlsTabelas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jspTabelas, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jspTabelas, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jlsTabelasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jlsTabelasKeyPressed
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            abrir();
        } else if (evt.getModifiers() == KeyEvent.CTRL_MASK && evt.getKeyCode() == KeyEvent.VK_C) {
            UtlCrs.copiaParaTransferencia(jlsTabelas.getSelectedValue());
        }
    }//GEN-LAST:event_jlsTabelasKeyPressed

    private void jlsTabelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlsTabelasMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1) {
            if (evt.getClickCount() >= 2) {
                abrir();
            }
        }
    }//GEN-LAST:event_jlsTabelasMouseClicked

    public void mostra() {
        Janelas.mostra(this);
    }

    private void abrir() {
        for (String tab : pegaSelecionadas()) {
            try {
                new TabelaIntf(tabelas.pega(tab)).mostra();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> jlsTabelas;
    private javax.swing.JScrollPane jspTabelas;
    // End of variables declaration//GEN-END:variables
}
