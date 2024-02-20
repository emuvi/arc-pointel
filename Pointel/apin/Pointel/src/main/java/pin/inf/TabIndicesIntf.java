package pin.inf;

import pin.libjan.Janelas;
import pin.modinf.TabIndice;
import pin.modinf.Tabela;

public class TabIndicesIntf extends javax.swing.JFrame {

    public Tabela tabela;

    public TabIndicesIntf(Tabela daTabela) throws Exception {
        initComponents();
        setTitle("Indices da Tabela " + daTabela.pEsquemaENome());
        tabela = daTabela;
        edtCampos.controlador().botaMostraNumeroLinhas();
        edtCampos.controlador().botaColunas(new String[]{"Nome", "Colunas"});
        for (TabIndice ind : tabela.pIndices()) {
            edtCampos.controlador().botaLinha(ind.pNome(), ind.narraColunas());
        }
        edtCampos.mEditavel(false);
        Janelas.inicia(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        edtCampos = new pin.libamg.EdtPla();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Indices da Tabela");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(edtCampos, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(edtCampos, javax.swing.GroupLayout.PREFERRED_SIZE, 264, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void mostra() {
        Janelas.mostra(this);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private pin.libamg.EdtPla edtCampos;
    // End of variables declaration//GEN-END:variables
}
