package pin.jrb;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import pin.Pointel;
import pin.libamg.EdtEsc;
import pin.libamk.Botao;
import pin.libbas.Configs;
import pin.libbas.Partes;
import pin.libetf.EscEtf;
import pin.libjan.Janelas;
import pin.libjan.TrataIntf;
import pin.libout.ComboBoxModelSorted;
import pin.libutl.Utl;
import pin.libutl.UtlArq;
import pin.libutl.UtlDat;
import pin.libutl.UtlDatHor;
import pin.libutl.UtlHor;
import pin.libutl.UtlInt;
import pin.libutl.UtlLog;
import pin.libutl.UtlMom;
import pin.libutl.UtlNumLon;
import pin.libutl.UtlRed;
import pin.libutl.UtlTex;

public class ConfigsIntf extends javax.swing.JFrame {

    private Configs cfgs;
    private ComboBoxModelSorted<String> dcmChaves;

    private JPopupMenu jpmMenu;
    private JMenuItem jmiLocal;
    private JMenuItem jmiRemoto;

    public ConfigsIntf(Configs dasConfigs) {
        initComponents();
        iniciaMenu();
        cfgs = dasConfigs;
        dcmChaves = new ComboBoxModelSorted<>();
        jcbChaves.setModel(dcmChaves);
        Janelas.inicia(this);
        new Thread("Carregar Cfgs Chaves") {
            @Override
            public void run() {
                for (Map.Entry<String, String> entry : cfgs.entrySet()) {
                    dcmChaves.addElement(entry.getKey());
                }
            }
        }.start();
    }

    private void iniciaMenu() {
        jpmMenu = new JPopupMenu();
        jmiLocal = new JMenuItem("Local");
        jmiLocal.addActionListener(new ActLocal());
        jpmMenu.add(jmiLocal);
        jmiRemoto = new JMenuItem("Remoto");
        jmiRemoto.addActionListener(new ActRemoto());
        jpmMenu.add(jmiRemoto);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jcbChaves = new javax.swing.JComboBox<>();
        jlbChaves = new javax.swing.JLabel();
        jlbChave = new javax.swing.JLabel();
        jtfChave = new javax.swing.JTextField();
        jlbTipo = new javax.swing.JLabel();
        jcbTipo = new javax.swing.JComboBox<>();
        jlbValor = new javax.swing.JLabel();
        jtfValor = new javax.swing.JTextField();
        jbtBota = new javax.swing.JButton();
        jbtPega = new javax.swing.JButton();
        jbtRemove = new javax.swing.JButton();
        jbtExportar = new javax.swing.JButton();
        jbtImportar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configs");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jcbChaves.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbChavesActionPerformed(evt);
            }
        });

        jlbChaves.setText("Chaves Encontradas");

        jlbChave.setText("Chave");

        jlbTipo.setText("Tipo");

        jcbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Texto", "Inteiro", "Numérico", "Lógico", "Data", "Hora", "Data e Hora", "Momento" }));

        jlbValor.setText("Valor");

        jbtBota.setMnemonic('B');
        jbtBota.setText("Bota");
        jbtBota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtBotaActionPerformed(evt);
            }
        });

        jbtPega.setMnemonic('P');
        jbtPega.setText("Pega");
        jbtPega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtPegaActionPerformed(evt);
            }
        });

        jbtRemove.setMnemonic('R');
        jbtRemove.setText("Remove");
        jbtRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRemoveActionPerformed(evt);
            }
        });

        jbtExportar.setMnemonic('E');
        jbtExportar.setText("Exportar");
        jbtExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtExportarActionPerformed(evt);
            }
        });

        jbtImportar.setMnemonic('I');
        jbtImportar.setText("Importar");
        jbtImportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtImportarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcbChaves, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbChaves)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlbChave)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jtfChave))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbTipo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbValor)
                            .addComponent(jtfValor, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jbtExportar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtImportar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 251, Short.MAX_VALUE)
                        .addComponent(jbtRemove)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtPega)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtBota)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbChaves)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbChaves, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbValor)
                    .addComponent(jlbTipo)
                    .addComponent(jlbChave))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfChave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbtExportar)
                        .addComponent(jbtImportar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbtRemove)
                        .addComponent(jbtPega)
                        .addComponent(jbtBota)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcbChavesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbChavesActionPerformed
        jtfChave.setText((String) jcbChaves.getSelectedItem());
    }//GEN-LAST:event_jcbChavesActionPerformed

    private void jbtPegaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtPegaActionPerformed
        try {
            switch ((String) jcbTipo.getSelectedItem()) {
                case "Texto":
                    jtfValor.setText(cfgs.pegaCrs(jtfChave.getText(), null));
                    break;
                case "Inteiro":
                    jtfValor.setText(UtlInt.formata(cfgs.pegaInt(jtfChave.getText(), null)));
                    break;
                case "Numérico":
                    jtfValor.setText(UtlNumLon.formata(cfgs.pegaNumLon(jtfChave.getText(), null)));
                    break;
                case "Lógico":
                    jtfValor.setText(UtlLog.formata(cfgs.pegaLog(jtfChave.getText(), null)));
                    break;
                case "Data":
                    jtfValor.setText(UtlDatHor.formata(cfgs.pegaDat(jtfChave.getText(), null)));
                    break;
                case "Hora":
                    jtfValor.setText(UtlDatHor.formata(cfgs.pegaHor(jtfChave.getText(), null)));
                    break;
                case "Data e Hora":
                    jtfValor.setText(UtlDatHor.formata(cfgs.pegaDatHor(jtfChave.getText(), null)));
                    break;
                case "Momento":
                    jtfValor.setText(UtlMom.formata(cfgs.pegaMom(jtfChave.getText(), null)));
                    break;
            }
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jbtPegaActionPerformed

    private void jbtRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRemoveActionPerformed
        try {
            cfgs.remove(jtfChave.getText());
            cfgs.salva();
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jbtRemoveActionPerformed

    private void jbtBotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtBotaActionPerformed
        try {
            switch ((String) jcbTipo.getSelectedItem()) {
                case "Texto":
                    cfgs.botaCrs(jtfChave.getText(), jtfValor.getText());
                    break;
                case "Inteiro":
                    cfgs.botaInt(jtfChave.getText(), UtlInt.pega(jtfValor.getText()));
                    break;
                case "Numérico":
                    cfgs.botaNumLon(jtfChave.getText(), UtlNumLon.pega(jtfValor.getText()));
                    break;
                case "Lógico":
                    cfgs.botaLog(jtfChave.getText(), UtlLog.pega(jtfValor.getText()));
                    break;
                case "Data":
                    cfgs.botaDat(jtfChave.getText(), UtlDat.pega(jtfValor.getText()));
                    break;
                case "Hora":
                    cfgs.botaHor(jtfChave.getText(), UtlHor.pega(jtfValor.getText()));
                    break;
                case "Data e Hora":
                    cfgs.botaDatHor(jtfChave.getText(), UtlDatHor.pega(jtfValor.getText()));
                    break;
                case "Momento":
                    cfgs.botaMom(jtfChave.getText(), UtlMom.pega(jtfValor.getText()));
                    break;
            }
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jbtBotaActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if (!Pointel.mainJarbs.confereLogado()) {
            Janelas.fecha(this);
        } else {
            TrataIntf.garanteFoco(this);
        }
    }//GEN-LAST:event_formWindowActivated

    private void jbtExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtExportarActionPerformed
        try {
            exporta();
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jbtExportarActionPerformed

    private void jbtImportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtImportarActionPerformed
        try {
            importa();
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jbtImportarActionPerformed

    public void exporta() throws Exception {
        Object[] chaves = new Object[dcmChaves.getSize()];
        for (int ic = 0; ic < chaves.length; ic++) {
            chaves[ic] = dcmChaves.getElementAt(ic);
        }
        new EscEtf() {
            @Override
            public void preparaIntf() throws Exception {
                super.preparaIntf();
                botaBotao(new Botao("Exportar") {
                    @Override
                    public void aoExecutar(Object comOrigem) throws Exception {
                        exporta(pgVlr().pgMatCrs());
                    }
                });
            }

            @Override
            public void especializaIntf() throws Exception {
                super.especializaIntf();
                edt().botaParams(EdtEsc.Params.MULTIPLOS);
                botaOpcoes(chaves);
            }
        }.mostra("Exportar Configs");
    }

    public void exporta(String[] asChaves) throws Exception {
        if (asChaves != null) {
            File arq = UtlArq.salvaArq("Configs (*.cgx) ", "cgx");
            if (arq != null) {
                String exp = "";
                for (String chave : asChaves) {
                    String valor = cfgs.pegaCrs(chave);
                    String linha = Partes.junta(chave, valor);
                    exp = UtlTex.soma(exp, linha);
                }
                UtlTex.salva(exp, arq);
            }
        }
    }

    public void importa() throws Exception {
        File arq = UtlArq.abreArq("Configs (*.cgx) ", "cgx");
        if (arq != null) {
            cfgs.carrega(arq);
        }
    }

    public static void importaNoMain() throws Exception {
        File arq = UtlArq.abreArq("Configs (*.cgx) ", "cgx");
        if (arq != null) {
            Pointel.mainConf.carrega(arq);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtBota;
    private javax.swing.JButton jbtExportar;
    private javax.swing.JButton jbtImportar;
    private javax.swing.JButton jbtPega;
    private javax.swing.JButton jbtRemove;
    private javax.swing.JComboBox<String> jcbChaves;
    private javax.swing.JComboBox<String> jcbTipo;
    private javax.swing.JLabel jlbChave;
    private javax.swing.JLabel jlbChaves;
    private javax.swing.JLabel jlbTipo;
    private javax.swing.JLabel jlbValor;
    private javax.swing.JTextField jtfChave;
    private javax.swing.JTextField jtfValor;
    // End of variables declaration//GEN-END:variables

    private class ActLocal extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                File arq = UtlArq.abreArq("Configs (*.cgs) ", "cgs");
                if (arq != null) {
                    cfgs.carrega(arq);
                }
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    private class ActRemoto extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String tex = UtlRed.baixaTex();
                if (tex != null) {
                    String[] lns = UtlTex.pLinhas(tex);
                    for (String ln : lns) {
                        cfgs.carrega(ln);
                    }
                }
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

}
