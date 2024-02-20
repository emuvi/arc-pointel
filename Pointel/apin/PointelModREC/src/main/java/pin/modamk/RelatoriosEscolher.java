package pin.modamk;

import pin.libbas.Configs;
import pin.libbas.Globais;
import pin.libjan.Janelas;
import pin.libutl.Utl;
import javax.swing.DefaultComboBoxModel;

public class RelatoriosEscolher extends javax.swing.JFrame {

    public Configs cfgs;

    public Relatorios relatorios;

    public DefaultComboBoxModel<String> dcmRelatorio;
    public DefaultComboBoxModel<String> dcmPagina;
    public DefaultComboBoxModel<String> dcmSaida;

    public RelatoriosEscolher(Relatorios aRelatorios) {
        try {
            initComponents();

            cfgs = (Configs) Globais.pega("mainConf");

            relatorios = aRelatorios;
            getRootPane().setDefaultButton(jbtOk);

            dcmRelatorio = new DefaultComboBoxModel<>();
            jcbRelatorio.setModel(dcmRelatorio);

            dcmPagina = new DefaultComboBoxModel<>();
            jcbPagina.setModel(dcmPagina);

            dcmSaida = new DefaultComboBoxModel<>();
            jcbSaida.setModel(dcmSaida);

            for (String relatorio : aRelatorios.getTitulos()) {
                dcmRelatorio.addElement(relatorio);
            }

            for (String saida : relatorios.getSaidas()) {
                dcmSaida.addElement(saida);
            }

            String ultimo = cfgs.pegaCrs("PointelProk - Relatório - Último", "");
            String penultimo = cfgs.pegaCrs("PointelProk - Relatório - Penúltimo", "");
            String antepenultimo = cfgs.pegaCrs("PointelProk - Relatório - Antepenúltimo", "");

            jcbRelatorio.setSelectedIndex(-1);

            if (!ultimo.isEmpty()) {
                jcbRelatorio.setSelectedIndex(dcmRelatorio.getIndexOf(ultimo));
            }

            if (jcbRelatorio.getSelectedIndex() == -1) {
                if (!penultimo.isEmpty()) {
                    jcbRelatorio.setSelectedIndex(dcmRelatorio.getIndexOf(penultimo));
                }
            }

            if (jcbRelatorio.getSelectedIndex() == -1) {
                if (!antepenultimo.isEmpty()) {
                    jcbRelatorio.setSelectedIndex(dcmRelatorio.getIndexOf(antepenultimo));
                }
            }

            if (jcbRelatorio.getSelectedIndex() == -1) {
                jcbRelatorio.setSelectedIndex(0);
            }

            jcbRelatorioFocusLost(null);

            Janelas.inicia(this, this);
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlbRelatorio = new javax.swing.JLabel();
        jcbRelatorio = new javax.swing.JComboBox();
        jlbSaida = new javax.swing.JLabel();
        jcbSaida = new javax.swing.JComboBox();
        jbtOk = new javax.swing.JButton();
        jbtCancelar = new javax.swing.JButton();
        jlbCopias = new javax.swing.JLabel();
        jspCopias = new javax.swing.JSpinner();
        jlbPagina = new javax.swing.JLabel();
        jcbPagina = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Escolher Relatório");
        setResizable(false);

        jlbRelatorio.setText("Relatório");

        jcbRelatorio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jcbRelatorioFocusLost(evt);
            }
        });
        jcbRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbRelatorioActionPerformed(evt);
            }
        });

        jlbSaida.setText("Saída");

        jbtOk.setMnemonic('P');
        jbtOk.setText("Confirmar");
        jbtOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOkActionPerformed(evt);
            }
        });

        jbtCancelar.setMnemonic('F');
        jbtCancelar.setText("Cancelar");
        jbtCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCancelarActionPerformed(evt);
            }
        });

        jlbCopias.setText("Cópias");

        jlbPagina.setText("Página");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbtOk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtCancelar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jlbSaida)
                                .addComponent(jcbSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jlbCopias)
                                .addComponent(jspCopias, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jlbRelatorio)
                                .addComponent(jcbRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jlbPagina)
                                .addComponent(jcbPagina, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbRelatorio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbPagina)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbPagina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbSaida)
                    .addComponent(jlbCopias))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jspCopias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtCancelar)
                    .addComponent(jbtOk))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public String getEscolhido() {
        return (String) jcbRelatorio.getSelectedItem();
    }

    public String getPagina() {
        return (String) jcbPagina.getSelectedItem();
    }

    public String getSaida() {
        return (String) jcbSaida.getSelectedItem();
    }

    public Integer getCopias() {
        return (Integer) jspCopias.getValue();
    }

    private void jbtOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOkActionPerformed
        new Thread("Produzir Relatorio Escolhido") {
            @Override
            public void run() {
                try {
                    Relatorio rel = relatorios.getRelatorio(getEscolhido());
                    rel.botaPagina(getPagina());
                    rel.saida = getSaida();
                    rel.copias = getCopias();

                    rel.produzir();

                    String ultimoRelatorio = cfgs.pegaCrs("PointelProk - Relatório - Último", "");

                    if (!ultimoRelatorio.equals(getEscolhido())) {
                        String penultimo = cfgs.pegaCrs("PointelProk - Relatório - Penúltimo", "");
                        cfgs.botaCrs("PointelProk - Relatório - Antepenúltimo", penultimo);
                        cfgs.botaCrs("PointelProk - Relatório - Penúltimo", ultimoRelatorio);
                        cfgs.botaCrs("PointelProk - Relatório - Último", getEscolhido());
                    }

                    cfgs.botaCrs("PointelProk - Relatório - " + getEscolhido() + " - Última Página", getPagina());
                    cfgs.botaCrs("PointelProk - Relatório - " + getEscolhido() + " - Última Saída", getSaida());
                    cfgs.botaInt("PointelProk - Relatório - " + getEscolhido() + " - Última Cópias", getCopias());
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        }.start();

        Janelas.fecha(this);
    }//GEN-LAST:event_jbtOkActionPerformed

    private void jbtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelarActionPerformed
        Janelas.execWindowClosing(this);
        setVisible(false);
    }//GEN-LAST:event_jbtCancelarActionPerformed

    private void jcbRelatorioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jcbRelatorioFocusLost
        if (jcbRelatorio.getSelectedIndex() > -1) {
            try {
                dcmPagina.removeAllElements();
                Relatorio rel = relatorios.getRelatorio(getEscolhido());
                for (String pag : rel.pegaPaginas()) {
                    dcmPagina.addElement(pag);
                }

                String ultimaPagina = cfgs.pegaCrs("PointelProk - Relatório - " + getEscolhido() + " - Última Página", dcmPagina.getElementAt(0));
                String ultimaSaida = cfgs.pegaCrs("PointelProk - Relatório - " + getEscolhido() + " - Última Saída", dcmSaida.getElementAt(0));
                Integer ultimaCopia = cfgs.pegaInt("PointelProk - Relatório - " + getEscolhido() + " - Última Cópias", 1);

                jcbPagina.setSelectedIndex(dcmPagina.getIndexOf(ultimaPagina));
                jcbSaida.setSelectedIndex(dcmSaida.getIndexOf(ultimaSaida));
                jspCopias.setValue(ultimaCopia);
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }//GEN-LAST:event_jcbRelatorioFocusLost

    private void jcbRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbRelatorioActionPerformed
        jcbRelatorioFocusLost(null);
    }//GEN-LAST:event_jcbRelatorioActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtCancelar;
    private javax.swing.JButton jbtOk;
    private javax.swing.JComboBox<String> jcbPagina;
    private javax.swing.JComboBox jcbRelatorio;
    private javax.swing.JComboBox jcbSaida;
    private javax.swing.JLabel jlbCopias;
    private javax.swing.JLabel jlbPagina;
    private javax.swing.JLabel jlbRelatorio;
    private javax.swing.JLabel jlbSaida;
    private javax.swing.JSpinner jspCopias;
    // End of variables declaration//GEN-END:variables
}
