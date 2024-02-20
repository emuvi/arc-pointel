package pin.mgr;

import com.thoughtworks.xstream.XStream;
import javax.swing.DefaultComboBoxModel;
import pin.libbas.Operacao;
import pin.libjan.Janelas;
import pin.libutl.Utl;

public class ExecutarParaCadaIntf extends javax.swing.JFrame {

    private final PointelMigre pointelMigre;
    private final DefaultComboBoxModel dcmConSelecao;
    private final DefaultComboBoxModel dcmConExecucao;
    private final Operacao operacao;
    private final String nomeAoAbrir;

    public ExecutarParaCadaIntf(PointelMigre doPointelMigre, Operacao aOperacao) {
        initComponents();
        pointelMigre = doPointelMigre;
        operacao = aOperacao;
        nomeAoAbrir = operacao.nome;
        dcmConSelecao = new DefaultComboBoxModel();
        dcmConExecucao = new DefaultComboBoxModel();
        for (ConexaoCfg con : pointelMigre.pMigracao().conexoes) {
            dcmConSelecao.addElement(con.nome);
            dcmConExecucao.addElement(con.nome);
        }
        jcbSelConexao.setModel(dcmConSelecao);
        jcbExeConexao.setModel(dcmConExecucao);
        if (!operacao.codigo.isEmpty()) {
            try {
                ExecutarParaCada execCada = (ExecutarParaCada) new XStream().fromXML(operacao.codigo);
                jcbSelConexao.setSelectedItem(execCada.conexaoSelecao);
                jcbExeConexao.setSelectedItem(execCada.conexaoComandos);
                jckPausarSeErrar.setSelected(execCada.pausarSeErrar);
                jtaSelComandos.setText(execCada.selecao);
                jtaExeComandos.setText(execCada.comandos);
            } catch (Exception ex) {
                Utl.registra(ex, true);
            }
        }

        Janelas.inicia(this, this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlbSelConexao = new javax.swing.JLabel();
        jcbSelConexao = new javax.swing.JComboBox<>();
        jlbSelComandos = new javax.swing.JLabel();
        jspSelComandos = new javax.swing.JScrollPane();
        jtaSelComandos = new javax.swing.JTextArea();
        jlbExeConexao = new javax.swing.JLabel();
        jcbExeConexao = new javax.swing.JComboBox<>();
        jlbExeComandos = new javax.swing.JLabel();
        jspExeComandos = new javax.swing.JScrollPane();
        jtaExeComandos = new javax.swing.JTextArea();
        jbtSalvar = new javax.swing.JButton();
        jbtCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jckPausarSeErrar = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Operação Executar Para Cada");

        jlbSelConexao.setText("Conexão Seleção");

        jlbSelComandos.setText("Seleção");

        jtaSelComandos.setColumns(20);
        jtaSelComandos.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jtaSelComandos.setRows(5);
        jspSelComandos.setViewportView(jtaSelComandos);

        jlbExeConexao.setText("Conexão Comandos");

        jlbExeComandos.setText("Comandos");

        jtaExeComandos.setColumns(20);
        jtaExeComandos.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jtaExeComandos.setRows(5);
        jspExeComandos.setViewportView(jtaExeComandos);

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
        jLabel1.setText("-prox- separa comandos e -pars- define parâmetros.");

        jckPausarSeErrar.setText("Pausar Se Errar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspSelComandos, javax.swing.GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbtSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtCancelar))
                    .addComponent(jspExeComandos, javax.swing.GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbSelComandos)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbSelConexao)
                                    .addComponent(jcbSelConexao, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbExeConexao)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jcbExeConexao, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jckPausarSeErrar)))))
                        .addGap(0, 212, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbExeComandos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbSelConexao)
                    .addComponent(jlbExeConexao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbSelConexao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbExeConexao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jckPausarSeErrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbSelComandos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspSelComandos, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbExeComandos)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspExeComandos, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
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
        ExecutarParaCada execCada = new ExecutarParaCada();
        execCada.conexaoSelecao = (String) jcbSelConexao.getSelectedItem();
        execCada.conexaoComandos = (String) jcbExeConexao.getSelectedItem();
        execCada.pausarSeErrar = jckPausarSeErrar.isSelected();
        execCada.selecao = jtaSelComandos.getText();
        execCada.comandos = jtaExeComandos.getText();
        operacao.codigo = new XStream().toXML(execCada);
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
    private javax.swing.JComboBox<String> jcbExeConexao;
    private javax.swing.JComboBox<String> jcbSelConexao;
    private javax.swing.JCheckBox jckPausarSeErrar;
    private javax.swing.JLabel jlbExeComandos;
    private javax.swing.JLabel jlbExeConexao;
    private javax.swing.JLabel jlbSelComandos;
    private javax.swing.JLabel jlbSelConexao;
    private javax.swing.JScrollPane jspExeComandos;
    private javax.swing.JScrollPane jspSelComandos;
    private javax.swing.JTextArea jtaExeComandos;
    private javax.swing.JTextArea jtaSelComandos;
    // End of variables declaration//GEN-END:variables
}
