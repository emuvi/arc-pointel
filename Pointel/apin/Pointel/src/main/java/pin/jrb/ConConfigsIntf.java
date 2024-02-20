package pin.jrb;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import pin.Pointel;
import pin.libamg.EdtEsc;
import pin.libamk.Botao;
import pin.libetf.EscEtf;
import pin.libbas.Conjunto;
import pin.libbas.Partes;
import pin.libjan.Janelas;
import pin.libjan.TrataIntf;
import pin.libout.ComboBoxModelSorted;
import pin.libutl.Utl;
import pin.libutl.UtlArq;
import pin.libutl.UtlDatHor;
import pin.libutl.UtlInt;
import pin.libutl.UtlLog;
import pin.libutl.UtlMom;
import pin.libutl.UtlNumLon;
import pin.libutl.UtlRed;
import pin.libutl.UtlTex;
import pin.modinf.Carregador;
import pin.modinf.ConexaoCfgs;
import pin.libbas.Analisado;

public class ConConfigsIntf extends javax.swing.JFrame {

    private ConexaoCfgs conCfgs;
    private ComboBoxModelSorted<String> dcmChaves;

    private JPopupMenu jpmMenu;
    private JMenuItem jmiLocal;
    private JMenuItem jmiRemoto;

    public ConConfigsIntf(ConexaoCfgs dasConConfigs) {
        initComponents();
        iniciaMenu();
        conCfgs = dasConConfigs;
        dcmChaves = new ComboBoxModelSorted<>();
        jcbChaves.setModel(dcmChaves);
        Janelas.inicia(this);
        new Carregador(conCfgs.conexao, "SELECT chave FROM cfgs", new Analisado<Conjunto, Boolean>() {
            @Override
            public Boolean analisa(Conjunto oConjunto) {
                while (oConjunto.proximo()) {
                    dcmChaves.addElement(oConjunto.pgVlr(1).pgCrs());
                }
                return true;
            }
        }).inicia();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlbChave = new javax.swing.JLabel();
        jtfChave = new javax.swing.JTextField();
        jlbTipo = new javax.swing.JLabel();
        jcbTipo = new javax.swing.JComboBox<>();
        jlbValor = new javax.swing.JLabel();
        jtfValor = new javax.swing.JTextField();
        jcbChaves = new javax.swing.JComboBox<>();
        jbtRem = new javax.swing.JButton();
        jbtGet = new javax.swing.JButton();
        jbtSet = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jbtExportar = new javax.swing.JButton();
        jbtImportar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configs da Conexão");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jlbChave.setText("Chave");

        jlbTipo.setText("Tipo");

        jcbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Texto", "Inteiro", "Numérico", "Lógico", "Data e Hora", "Momento" }));

        jlbValor.setText("Valor");

        jcbChaves.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbChavesActionPerformed(evt);
            }
        });

        jbtRem.setText("Remove");
        jbtRem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRemActionPerformed(evt);
            }
        });

        jbtGet.setText("Pega");
        jbtGet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtGetActionPerformed(evt);
            }
        });

        jbtSet.setText("Bota");
        jbtSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtSetActionPerformed(evt);
            }
        });

        jLabel1.setText("Chaves Encontradas");

        jbtExportar.setText("Exportar");
        jbtExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtExportarActionPerformed(evt);
            }
        });

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
                    .addComponent(jcbChaves, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jbtExportar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtImportar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 251, Short.MAX_VALUE)
                        .addComponent(jbtRem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtGet)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtSet)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbChaves, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbTipo)
                    .addComponent(jlbValor)
                    .addComponent(jlbChave))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfChave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtRem)
                    .addComponent(jbtGet)
                    .addComponent(jbtSet)
                    .addComponent(jbtExportar)
                    .addComponent(jbtImportar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void iniciaMenu() {
        jpmMenu = new JPopupMenu();
        jmiLocal = new JMenuItem("Local");
        jmiLocal.addActionListener(new ActLocal());
        jpmMenu.add(jmiLocal);
        jmiRemoto = new JMenuItem("Remoto");
        jmiRemoto.addActionListener(new ActRemoto());
        jpmMenu.add(jmiRemoto);
    }

    private void jcbChavesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbChavesActionPerformed
        jtfChave.setText((String) jcbChaves.getSelectedItem());
    }//GEN-LAST:event_jcbChavesActionPerformed

    private void jbtGetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGetActionPerformed
        try {
            switch ((String) jcbTipo.getSelectedItem()) {
                case "Texto":
                    jtfValor.setText(conCfgs.pegaCrs(jtfChave.getText(), null));
                    break;
                case "Inteiro":
                    jtfValor.setText(UtlInt.formata(conCfgs.pegaInt(jtfChave.getText(), null)));
                    break;
                case "Numérico":
                    jtfValor.setText(UtlNumLon.formata(conCfgs.pegaNumLon(jtfChave.getText(), null)));
                    break;
                case "Lógico":
                    jtfValor.setText(UtlLog.formata(conCfgs.pegaLog(jtfChave.getText(), null)));
                    break;
                case "Data e Hora":
                    jtfValor.setText(UtlDatHor.formata(conCfgs.pegaDatHor(jtfChave.getText(), null)));
                    break;
                case "Momento":
                    jtfValor.setText(UtlMom.formata(conCfgs.pegaMom(jtfChave.getText(), null)));
                    break;
            }
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jbtGetActionPerformed

    private void jbtRemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRemActionPerformed
        try {
            conCfgs.tira(jtfChave.getText());
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jbtRemActionPerformed

    private void jbtSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSetActionPerformed
        try {
            switch ((String) jcbTipo.getSelectedItem()) {
                case "Texto":
                    conCfgs.botaCrs(jtfChave.getText(), jtfValor.getText());
                    break;
                case "Inteiro":
                    conCfgs.botaInt(jtfChave.getText(), UtlInt.pega(jtfValor.getText()));
                    break;
                case "Numérico":
                    conCfgs.botaNumLon(jtfChave.getText(), UtlNumLon.pega(jtfValor.getText()));
                    break;
                case "Lógico":
                    conCfgs.botaLog(jtfChave.getText(), UtlLog.pega(jtfValor.getText()));
                    break;
                case "Data e Hora":
                    conCfgs.botaDatHor(jtfChave.getText(), UtlDatHor.pega(jtfValor.getText()));
                case "Momento":
                    conCfgs.botaMom(jtfChave.getText(), UtlMom.pega(jtfValor.getText()));
            }
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jbtSetActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if (!Pointel.mainJarbs.confereLogado()) {
            Janelas.fecha(this);
        } else {
            TrataIntf.garanteFoco(this);
        }
    }//GEN-LAST:event_formWindowActivated

    private void jbtExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtExportarActionPerformed
        try {
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
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jbtExportarActionPerformed

    private void jbtImportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtImportarActionPerformed
        jpmMenu.show(jbtImportar, 0, jbtImportar.getHeight());
    }//GEN-LAST:event_jbtImportarActionPerformed

    public void exporta(String[] asChaves) throws Exception {
        if (asChaves != null) {
            File arq = UtlArq.salvaArq("Configs (*.cgs) ", "cgs");
            if (arq != null) {
                String exp = "";
                for (String chave : asChaves) {
                    String valor = conCfgs.pegaCrs(chave);
                    String linha = Partes.junta(chave, valor);
                    exp = UtlTex.soma(exp, linha);
                }
                UtlTex.salva(exp, arq);
            }
        }
    }

    public void importa(File doArquivo) throws Exception {
        if (doArquivo != null) {
            List<String> lns = UtlTex.pegaLinhas(doArquivo);
            for (String ln : lns) {
                String[] vals = Partes.pega(ln);
                if (vals.length == 2) {
                    String chave = vals[0];
                    String valor = vals[1];
                    conCfgs.botaCrs(chave, valor);
                }
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton jbtExportar;
    private javax.swing.JButton jbtGet;
    private javax.swing.JButton jbtImportar;
    private javax.swing.JButton jbtRem;
    private javax.swing.JButton jbtSet;
    private javax.swing.JComboBox<String> jcbChaves;
    private javax.swing.JComboBox<String> jcbTipo;
    private javax.swing.JLabel jlbChave;
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
                    conCfgs.carrega(arq);
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
                        conCfgs.carrega(ln);
                    }
                }
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

}
