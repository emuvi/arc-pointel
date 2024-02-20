package pin.mgr;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.table.DefaultTableModel;
import pin.libitr.PerguntaTpVr;
import pin.libjan.Janelas;
import pin.libjan.TrataIntf;
import pin.libutl.Utl;
import pin.libutl.UtlTab;
import pin.libvlr.Vlrs;

public class ExecTratAnalisaParametros extends javax.swing.JDialog {

    public Vlrs parametros;
    public DefaultTableModel dtmParametros;
    public Boolean refazer;

    public ExecTratAnalisaParametros(java.awt.Frame daExecucao, Vlrs osParametros, String daSelecao, String eComando, String comErro) {
        super(daExecucao, true);
        initComponents();
        parametros = osParametros;

        jtaSelecao.setText(daSelecao);
        jtaComando.setText(eComando);
        jtaErro.setText(comErro);

        dtmParametros = UtlTab.fazTabela(jtbParametros, this, "Indice", "Valor");
        for (int ip = 0; ip < parametros.tamanho(); ip++) {
            dtmParametros.addRow(new Object[]{ip + 1, parametros.pgVlr(ip)});
        }

        refazer = false;

        Janelas.botaAtalho(this, "Alterar", "alt A", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jbtAlterarActionPerformed(e);
            }
        });

        Janelas.botaAtalho(this, "Refazer", "alt R", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jbtRefazerActionPerformed(e);
            }
        });

        Janelas.botaAtalho(this, "Pular", "alt P", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jbtPularActionPerformed(e);
            }
        });

        Janelas.botaAtalho(this, "Anular", "alt N", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jbtAnularActionPerformed(e);
            }
        });

        Janelas.inicia(this, this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtbInfo = new javax.swing.JTabbedPane();
        jspErro = new javax.swing.JScrollPane();
        jtaErro = new javax.swing.JTextArea();
        jspComando = new javax.swing.JScrollPane();
        jtaComando = new javax.swing.JTextArea();
        jspSelecao = new javax.swing.JScrollPane();
        jtaSelecao = new javax.swing.JTextArea();
        jspParametros = new javax.swing.JScrollPane();
        jtbParametros = new javax.swing.JTable();
        jbtAlterar = new javax.swing.JButton();
        jbtPular = new javax.swing.JButton();
        jbtRefazer = new javax.swing.JButton();
        jbtAnular = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Analisar Parâmetros");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jtaErro.setColumns(20);
        jtaErro.setRows(5);
        jspErro.setViewportView(jtaErro);

        jtbInfo.addTab("Erro", jspErro);

        jtaComando.setColumns(20);
        jtaComando.setRows(5);
        jspComando.setViewportView(jtaComando);

        jtbInfo.addTab("Comando", jspComando);

        jtaSelecao.setColumns(20);
        jtaSelecao.setRows(5);
        jspSelecao.setViewportView(jtaSelecao);

        jtbInfo.addTab("Seleção", jspSelecao);

        jspParametros.setViewportView(jtbParametros);

        jbtAlterar.setText("Alterar");
        jbtAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAlterarActionPerformed(evt);
            }
        });

        jbtPular.setText("Pular");
        jbtPular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtPularActionPerformed(evt);
            }
        });

        jbtRefazer.setText("Refazer");
        jbtRefazer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRefazerActionPerformed(evt);
            }
        });

        jbtAnular.setText("Anular");
        jbtAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAnularActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtbInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jspParametros, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jbtPular, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                            .addComponent(jbtRefazer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbtAlterar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbtAnular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtbInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspParametros, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbtAlterar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtAnular)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtRefazer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtPular)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAlterarActionPerformed
        int sel = jtbParametros.getSelectedRow();
        if (sel > -1) {
            try {
                Object valor = dtmParametros.getValueAt(sel, 1);
                PerguntaTpVr eval = new PerguntaTpVr("Novo Valor", valor);
                if (eval.confirmado()) {
                    valor = eval.retorno();
                    dtmParametros.setValueAt(valor, sel, 1);
                }
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }

    }//GEN-LAST:event_jbtAlterarActionPerformed

    private void jbtPularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtPularActionPerformed
        refazer = false;
        Janelas.fecha(this);
    }//GEN-LAST:event_jbtPularActionPerformed

    private void jbtRefazerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRefazerActionPerformed
        for (int i1 = 0; i1 < parametros.tamanho(); i1++) {
            parametros.troca(i1, dtmParametros.getValueAt(i1, 1));
        }
        refazer = true;
        Janelas.fecha(this);
    }//GEN-LAST:event_jbtRefazerActionPerformed

    private void jbtAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAnularActionPerformed
        int sel = jtbParametros.getSelectedRow();
        if (sel > -1) {
            dtmParametros.setValueAt(null, sel, 1);
        }
    }//GEN-LAST:event_jbtAnularActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        TrataIntf.garanteFoco(jtbParametros);
    }//GEN-LAST:event_formWindowOpened

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtAlterar;
    private javax.swing.JButton jbtAnular;
    private javax.swing.JButton jbtPular;
    private javax.swing.JButton jbtRefazer;
    private javax.swing.JScrollPane jspComando;
    private javax.swing.JScrollPane jspErro;
    private javax.swing.JScrollPane jspParametros;
    private javax.swing.JScrollPane jspSelecao;
    private javax.swing.JTextArea jtaComando;
    private javax.swing.JTextArea jtaErro;
    private javax.swing.JTextArea jtaSelecao;
    private javax.swing.JTabbedPane jtbInfo;
    private javax.swing.JTable jtbParametros;
    // End of variables declaration//GEN-END:variables

}
