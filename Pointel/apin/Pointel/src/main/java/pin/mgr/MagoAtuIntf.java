package pin.mgr;

import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import pin.libamg.EdtPlaLog;
import pin.libamk.Cmp;
import pin.libbas.Globais;
import pin.libbas.Operacao;
import pin.libjan.Janelas;
import pin.libjan.PopMenu;
import pin.libjan.TrataIntf;
import pin.libutl.Utl;
import pin.libutl.UtlCrs;
import pin.libutl.UtlLog;
import pin.libutl.UtlTab;
import pin.libvlr.VLog;
import pin.modamk.Cadastro;
import pin.modamk.Recurso;
import pin.modamk.Recursos;

public class MagoAtuIntf extends javax.swing.JFrame {

    public PointelMigre pointelMigre;
    public pin.modinf.Conexao mainConexao;

    public DefaultTableModel dtmCampos;
    public DefaultComboBoxModel dcmConexoes;

    public PopMenu pmnCampos;
    public PopMenu pmnComandos;

    public MagoAtuIntf(PointelMigre doPointelMigre) throws Exception {
        initComponents();
        pointelMigre = doPointelMigre;
        mainConexao = (pin.modinf.Conexao) Globais.pega("mainConc");
        if (!mainConexao.estaConectado()) {
            throw new Exception("Para Fazer Mágica de Atualização é Preciso Estar Conectado.");
        }

        dtmCampos = UtlTab.fazTabela(jtbCampos, this, new String[]{"Selecionado", "Raiz", "Tabela", "Campo", "Tipo", "Tamanho", "Precisão"}, new Integer[]{0});
        jtbCampos.getColumnModel().getColumn(0).setCellEditor(new EdtPlaLog());

        dcmConexoes = new DefaultComboBoxModel();
        for (ConexaoCfg con : pointelMigre.pMigracao().conexoes) {
            dcmConexoes.addElement(con.nome);
        }
        jcbConexao.setModel(dcmConexoes);

        new Thread("Carrega Campos") {
            private void insere(List<Recurso> dosIcones) {
                if (dosIcones != null) {
                    for (Recurso icone : dosIcones) {
                        if (icone.pegaClasse() != null) {
                            if (Cadastro.class.isAssignableFrom(icone.pegaClasse())) {
                                try {
                                    Cadastro cad = (Cadastro) icone.pegaClasse().newInstance();
                                    for (Cmp cmp : cad.campos) {
                                        dtmCampos.addRow(new Object[]{new VLog(false), icone.pegaRaiz(), cad.tabela, cmp.pNome(), cmp.pTipo(), cmp.pTamanho(), cmp.pPrecisao()});
                                    }
                                } catch (Exception ex) {
                                    Utl.registra(ex, false);
                                }
                            }
                        }
                        insere(icone.filhos());
                    }
                }
            }

            @Override
            public void run() {
                insere((Recursos) Globais.pega("mainRecs"));
                Utl.alertaPop("Terminou de Carregar Campos");
            }
        }.start();

        pmnCampos = new PopMenu();
        pmnCampos.bota("Filtrar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MagoAtuIntfFlt(MagoAtuIntf.this).setVisible(true);
            }
        });
        pmnCampos.coloca(jspCampos);

        pmnComandos = new PopMenu();

        pmnComandos.bota("Inserir");

        pmnComandos.bota("Inserir", "Nome Tabela", new AbstractAction() {
            ;
            @Override
            public void actionPerformed(ActionEvent e) {
                TrataIntf.botaNoCursor(jtaComandos, "<nometabela>");
            }
        });

        pmnComandos.bota("Inserir", "Nome Campo", new AbstractAction() {
            ;
            @Override
            public void actionPerformed(ActionEvent e) {
                TrataIntf.botaNoCursor(jtaComandos, "<nomecampo>");
            }
        });

        pmnComandos.coloca(jspComandos);

        Janelas.inicia(this);
    }

    public MagoAtuIntf mostra() {
        Janelas.mostra(this);
        return this;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jspCampos = new javax.swing.JScrollPane();
        jtbCampos = new javax.swing.JTable();
        jspComandos = new javax.swing.JScrollPane();
        jtaComandos = new javax.swing.JTextArea();
        jbtRealizar = new javax.swing.JButton();
        jbtCancelar = new javax.swing.JButton();
        jckParar = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        jcbConexao = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mago - Atualização");

        jspCampos.setViewportView(jtbCampos);

        jtaComandos.setColumns(20);
        jtaComandos.setRows(5);
        jspComandos.setViewportView(jtaComandos);

        jbtRealizar.setText("Realizar");
        jbtRealizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRealizarActionPerformed(evt);
            }
        });

        jbtCancelar.setText("Cancelar");
        jbtCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCancelarActionPerformed(evt);
            }
        });

        jckParar.setText("Parar Se Errar");

        jLabel11.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jLabel11.setText("-prox- separa comandos.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspCampos, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jcbConexao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jckParar)
                        .addGap(18, 18, 18)
                        .addComponent(jbtRealizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtCancelar))
                    .addComponent(jspComandos))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jspCampos, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspComandos, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtCancelar)
                    .addComponent(jbtRealizar)
                    .addComponent(jckParar)
                    .addComponent(jLabel11)
                    .addComponent(jcbConexao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelarActionPerformed
        Janelas.fecha(this);
    }//GEN-LAST:event_jbtCancelarActionPerformed

    private void jbtRealizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRealizarActionPerformed
        int idx = 1;
        String comandos = jtaComandos.getText();
        for (int il = 0; il < dtmCampos.getRowCount(); il++) {
            if (UtlLog.pega(dtmCampos.getValueAt(il, 0), false)) {
                String nomeTabela = (String) dtmCampos.getValueAt(il, 2);
                String nomeCampo = (String) dtmCampos.getValueAt(il, 3);
                String espComandos = UtlCrs.troca(comandos, "<nometabela>", nomeTabela);
                espComandos = UtlCrs.troca(espComandos, "<nomecampo>", nomeCampo);
                String fonte = "<pin.mgr.Executar>\n"
                        + "  <conexao>" + jcbConexao.getSelectedItem() + "</conexao>\n"
                        + "  <pararSeErrar>" + jckParar.isSelected() + "</pararSeErrar>\n"
                        + "  <comandos>" + espComandos + "</comandos>\n"
                        + "</pin.mgr.Executar>";

                Operacao nova = new Operacao("Executar", "atualização" + idx, fonte);
                pointelMigre.bota(nova);
                idx++;
            }
        }

        Janelas.fecha(this);
    }//GEN-LAST:event_jbtRealizarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel11;
    private javax.swing.JButton jbtCancelar;
    private javax.swing.JButton jbtRealizar;
    private javax.swing.JComboBox<String> jcbConexao;
    private javax.swing.JCheckBox jckParar;
    private javax.swing.JScrollPane jspCampos;
    private javax.swing.JScrollPane jspComandos;
    private javax.swing.JTextArea jtaComandos;
    private javax.swing.JTable jtbCampos;
    // End of variables declaration//GEN-END:variables

}
