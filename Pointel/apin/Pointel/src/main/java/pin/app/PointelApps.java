package pin.app;

import pin.libbas.Globais;
import pin.libjan.Janelas;
import pin.libutl.Utl;
import pin.modamk.Recursos;
import pin.modinf.Conexao;
import pin.modpim.Pims;

public class PointelApps extends javax.swing.JFrame {

    public Conexao conexao;

    public PointelApps() throws Exception {
        initComponents();
        setIconImage(Pims.pega("PointelApps.png").getImage());
        conexao = (Conexao) Globais.pega("mainConc");
        Recursos mainRecs = (Recursos) Globais.pega("mainRecs");
        mainRecs.criaMenu("Pointel.PointelAllMagi.PointelApps", this);
        Janelas.inicia(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PointelApps");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 54, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        try {
            Recursos mainRecs = (Recursos) Globais.pega("mainRecs");
            if (mainRecs.verificaRestricao(this, "PointelAdmin")) {
                conexao.conectaBase(this, true);
            }
        } catch (Exception ex) {
            Utl.registra(ex);
            Janelas.fecha(this);
        }
    }//GEN-LAST:event_formWindowActivated

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
