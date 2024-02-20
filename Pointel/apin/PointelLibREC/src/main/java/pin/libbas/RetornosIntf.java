package pin.libbas;

import javax.swing.table.DefaultTableModel;
import pin.libitr.Alerta;
import pin.libjan.Janelas;
import pin.libjan.TabCarregador;
import pin.libutl.Utl;
import pin.libutl.UtlCrs;
import pin.libutl.UtlTab;

public class RetornosIntf extends javax.swing.JFrame {

    private Retornos retornos;
    private DefaultTableModel dtmRetornos;
    private TabCarregador carregador;

    public RetornosIntf() {
        initComponents();
        dtmRetornos = UtlTab.fazTabela(jtbRetornos, this, "Momento", "Descrição", "Valor");
        carregador = new TabCarregador(jtbRetornos);
        Janelas.inicia(this);
    }

    public RetornosIntf abre(Object oValor) throws Exception {
        retornos = Retornos.abre(oValor);
        atualiza();
        return this;
    }

    public void atualiza() {
        try {
            carregador.carrega(retornos.pegaTab());
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }

    public RetornosIntf mostra() {
        Janelas.mostra(this);
        return this;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jspRetornos = new javax.swing.JScrollPane();
        jtbRetornos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Retornos");

        jtbRetornos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbRetornosMouseClicked(evt);
            }
        });
        jspRetornos.setViewportView(jtbRetornos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jspRetornos, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jspRetornos, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtbRetornosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbRetornosMouseClicked
        int sel = jtbRetornos.getSelectedRow();
        if (sel > -1 && evt.getClickCount() >= 2) {
            if (evt.isAltDown()) {
                new Alerta(UtlCrs.pega(dtmRetornos.getValueAt(sel, 1)), "Retorno Nome", false, false).mostra();
            } else {
                new Alerta(UtlCrs.pega(dtmRetornos.getValueAt(sel, 2)), "Retorno Valor", false, true).mostra();
            }
        }
    }//GEN-LAST:event_jtbRetornosMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jspRetornos;
    private javax.swing.JTable jtbRetornos;
    // End of variables declaration//GEN-END:variables

}
