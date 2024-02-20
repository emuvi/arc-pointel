package pin.mgr;

import com.thoughtworks.xstream.XStream;
import javax.swing.DefaultComboBoxModel;
import pin.libbas.Operacao;
import pin.libjan.Janelas;
import pin.libutl.Utl;

public class ExecutarIntf extends javax.swing.JFrame {

    private final PointelMigre pointelMigre;
    private final DefaultComboBoxModel dcmConexoes;
    private Operacao operacao;
    private String nomeAoAbrir;

    public ExecutarIntf(PointelMigre doPointelMigre, Operacao aOperacao) {
        initComponents();
        pointelMigre = doPointelMigre;
        operacao = aOperacao;
        nomeAoAbrir = operacao.nome;
        dcmConexoes = new DefaultComboBoxModel<>();
        for (ConexaoCfg con : pointelMigre.pMigracao().conexoes) {
            dcmConexoes.addElement(con.nome);
        }
        jcbConexao.setModel(dcmConexoes);
        if (!operacao.codigo.isEmpty()) {
            try {
                Executar exec = (Executar) new XStream().fromXML(operacao.codigo);
                jcbConexao.setSelectedItem(exec.conexao);
                jckPausarSeErrar.setSelected(exec.pausarSeErrar);
                jtaComandos.setText(exec.comandos);
            } catch (Exception ex) {
                Utl.registra(ex, true);
            }
        }

        Janelas.inicia(this, this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlbConexao = new javax.swing.JLabel();
        jcbConexao = new javax.swing.JComboBox<>();
        jlbComandos = new javax.swing.JLabel();
        jspComandos = new javax.swing.JScrollPane();
        jtaComandos = new javax.swing.JTextArea();
        jbtSalvar = new javax.swing.JButton();
        jbtCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jckPausarSeErrar = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Operação Executar");

        jlbConexao.setText("Conexão");

        jlbComandos.setText("Comandos");

        jtaComandos.setColumns(20);
        jtaComandos.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jtaComandos.setRows(5);
        jspComandos.setViewportView(jtaComandos);

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

        jLabel1.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jLabel1.setText("-prox- separa comandos.");

        jckPausarSeErrar.setText("Pausar Se Errar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbtSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtCancelar))
                    .addComponent(jspComandos, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbComandos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbConexao)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jcbConexao, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jckPausarSeErrar)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbConexao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbConexao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jckPausarSeErrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbComandos)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspComandos, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtCancelar)
                    .addComponent(jbtSalvar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelarActionPerformed
        Janelas.execWindowClosing(this);
        setVisible(false);
    }//GEN-LAST:event_jbtCancelarActionPerformed

    private void jbtSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSalvarActionPerformed
        Executar exec = new Executar();
        exec.conexao = (String) jcbConexao.getSelectedItem();
        exec.pausarSeErrar = jckPausarSeErrar.isSelected();
        exec.comandos = jtaComandos.getText();
        operacao.codigo = new XStream().toXML(exec);
        int iOpe = pointelMigre.jlsOperacoes.getSelectedIndex();
        if (iOpe > -1) {
            Operacao opeSel = pointelMigre.pMigracao().operacoes.get(iOpe);
            if (opeSel.nome.equals(nomeAoAbrir)) {
                pointelMigre.jtaCodigo.setText(operacao.codigo);
            }
        }
        Janelas.fecha(this);
    }//GEN-LAST:event_jbtSalvarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton jbtCancelar;
    private javax.swing.JButton jbtSalvar;
    private javax.swing.JComboBox<String> jcbConexao;
    private javax.swing.JCheckBox jckPausarSeErrar;
    private javax.swing.JLabel jlbComandos;
    private javax.swing.JLabel jlbConexao;
    private javax.swing.JScrollPane jspComandos;
    private javax.swing.JTextArea jtaComandos;
    // End of variables declaration//GEN-END:variables
}
