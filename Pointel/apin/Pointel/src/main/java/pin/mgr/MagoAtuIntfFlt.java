package pin.mgr;

import pin.libvlr.VLog;
import pin.libbas.Dados;
import pin.libjan.Janelas;

public class MagoAtuIntfFlt extends javax.swing.JFrame {

    public MagoAtuIntf magoAtualizacao;

    public MagoAtuIntfFlt(MagoAtuIntf doMagoAtualizacao) {
        initComponents();
        magoAtualizacao = doMagoAtualizacao;
        Janelas.inicia(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnsOperacao = new javax.swing.ButtonGroup();
        jrbSelecionar = new javax.swing.JRadioButton();
        jrbDeselecionar = new javax.swing.JRadioButton();
        jlbRaiz = new javax.swing.JLabel();
        jtfRaiz = new javax.swing.JTextField();
        jlbTabela = new javax.swing.JLabel();
        jtfTabela = new javax.swing.JTextField();
        jlbCampo = new javax.swing.JLabel();
        jtfCampo = new javax.swing.JTextField();
        jlbTipo = new javax.swing.JLabel();
        jtfTipo = new javax.swing.JTextField();
        jlbTamanho = new javax.swing.JLabel();
        jspTamanho = new javax.swing.JSpinner();
        jlbPrecisao = new javax.swing.JLabel();
        jspPrecisao = new javax.swing.JSpinner();
        jbtFiltrar = new javax.swing.JButton();
        jbtCancelar = new javax.swing.JButton();
        jlbColuna = new javax.swing.JLabel();
        jlbContendo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mago - Atualização - Filtrar");
        setResizable(false);

        btnsOperacao.add(jrbSelecionar);
        jrbSelecionar.setSelected(true);
        jrbSelecionar.setText("Selecionar");

        btnsOperacao.add(jrbDeselecionar);
        jrbDeselecionar.setText("Deselecionar");

        jlbRaiz.setText("Raiz:");

        jlbTabela.setText("Tabela:");

        jlbCampo.setText("Campo:");

        jlbTipo.setText("Tipo:");

        jlbTamanho.setText("Tamanho:");

        jlbPrecisao.setText("Precisão:");

        jbtFiltrar.setText("Filtrar");
        jbtFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtFiltrarActionPerformed(evt);
            }
        });

        jbtCancelar.setText("Cancelar");
        jbtCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCancelarActionPerformed(evt);
            }
        });

        jlbColuna.setText("Coluna:");

        jlbContendo.setText("Contendo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlbTabela)
                    .addComponent(jlbCampo)
                    .addComponent(jlbTipo)
                    .addComponent(jlbTamanho)
                    .addComponent(jlbPrecisao)
                    .addComponent(jlbColuna)
                    .addComponent(jrbSelecionar)
                    .addComponent(jlbRaiz))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbtFiltrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtCancelar))
                    .addComponent(jtfTabela)
                    .addComponent(jtfCampo)
                    .addComponent(jtfTipo)
                    .addComponent(jspPrecisao)
                    .addComponent(jspTamanho, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jrbDeselecionar)
                    .addComponent(jlbContendo)
                    .addComponent(jtfRaiz))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbSelecionar)
                    .addComponent(jrbDeselecionar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbColuna)
                    .addComponent(jlbContendo))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbRaiz)
                    .addComponent(jtfRaiz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfTabela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbTabela))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfCampo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbCampo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbTipo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jspTamanho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbTamanho))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jspPrecisao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbPrecisao))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtFiltrar)
                    .addComponent(jbtCancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelarActionPerformed
        Janelas.fecha(this);
    }//GEN-LAST:event_jbtCancelarActionPerformed

    private void jbtFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtFiltrarActionPerformed
        Boolean valor = jrbSelecionar.isSelected();

        String raizFlt = jtfRaiz.getText();
        String tabelaFlt = jtfTabela.getText();
        String campoFlt = jtfCampo.getText();
        String tipoFlt = jtfTipo.getText();
        Integer tamanhoFlt = (Integer) jspTamanho.getValue();
        Integer precisaoFlt = (Integer) jspPrecisao.getValue();

        for (int il = 0; il < magoAtualizacao.dtmCampos.getRowCount(); il++) {
            String raiz = (String) magoAtualizacao.dtmCampos.getValueAt(il, 1);
            String tabela = (String) magoAtualizacao.dtmCampos.getValueAt(il, 2);
            String campo = (String) magoAtualizacao.dtmCampos.getValueAt(il, 3);
            Dados.Tp tipo = (Dados.Tp) magoAtualizacao.dtmCampos.getValueAt(il, 4);
            String tipoCrs = tipo.toString();
            Integer tamanho = (Integer) magoAtualizacao.dtmCampos.getValueAt(il, 5);
            Integer precisao = (Integer) magoAtualizacao.dtmCampos.getValueAt(il, 6);

            Boolean eh = true;
            if (!raizFlt.isEmpty()) {
                if (!raiz.contains(raizFlt)) {
                    eh = false;
                }
            }
            if (!tabelaFlt.isEmpty()) {
                if (!tabela.contains(tabelaFlt)) {
                    eh = false;
                }
            }
            if (!campoFlt.isEmpty()) {
                if (!campo.contains(campoFlt)) {
                    eh = false;
                }
            }
            if (!tipoFlt.isEmpty()) {
                if (!tipoCrs.contains(tipoFlt)) {
                    eh = false;
                }
            }
            if (tamanhoFlt != 0) {
                if (tamanho != tamanhoFlt) {
                    eh = false;
                }
            }
            if (precisaoFlt != 0) {
                if (precisao != precisaoFlt) {
                    eh = false;
                }
            }

            if (eh) {
                magoAtualizacao.dtmCampos.setValueAt(new VLog(valor), il, 0);
            }
        }

        Janelas.fecha(this);
    }//GEN-LAST:event_jbtFiltrarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnsOperacao;
    private javax.swing.JButton jbtCancelar;
    private javax.swing.JButton jbtFiltrar;
    private javax.swing.JLabel jlbCampo;
    private javax.swing.JLabel jlbColuna;
    private javax.swing.JLabel jlbContendo;
    private javax.swing.JLabel jlbPrecisao;
    private javax.swing.JLabel jlbRaiz;
    private javax.swing.JLabel jlbTabela;
    private javax.swing.JLabel jlbTamanho;
    private javax.swing.JLabel jlbTipo;
    private javax.swing.JRadioButton jrbDeselecionar;
    private javax.swing.JRadioButton jrbSelecionar;
    private javax.swing.JSpinner jspPrecisao;
    private javax.swing.JSpinner jspTamanho;
    private javax.swing.JTextField jtfCampo;
    private javax.swing.JTextField jtfRaiz;
    private javax.swing.JTextField jtfTabela;
    private javax.swing.JTextField jtfTipo;
    // End of variables declaration//GEN-END:variables
}
