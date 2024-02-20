package pin.prk;

import pin.Pointel;
import pin.libbas.Globais;
import pin.libjan.Janelas;
import pin.modamk.Recursos;
import pin.modpim.Pims;

public class PointelProk extends javax.swing.JFrame {

    public PointelProk() throws Exception {
        initComponents();
        setIconImage(Pims.pega("PointelProk.png").getImage());
        Recursos mainRecs = (Recursos) Globais.pega("mainRecs");
        mainRecs.criaMenu("Pointel.PointelAllMagi.PointelProk", this);
        Janelas.inicia(this, this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PointelProk");
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
            .addGap(0, 65, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 57, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        String restringida = Pointel.restringida;
        switch (restringida) {
            case "PointelAllMake":
                Janelas.fecha(this);
                Pointel.mainIntel.mostraPointelAllMake();
                return;
            case "PointelAllMagi":
                Janelas.fecha(this);
                Pointel.mainIntel.mostraPointelAllMagi();
                return;
            case "PointelEduca":
                Janelas.fecha(this);
                Pointel.mainIntel.mostraPointelEduca();
                return;
            case "PointelBiblio":
                Janelas.fecha(this);
                Pointel.mainIntel.mostraPointelBiblio();
                return;
            case "PointelAdPro":
                Janelas.fecha(this);
                Pointel.mainIntel.mostraPointelAdPro();
                return;
            case "PointelJarbs":
                Janelas.fecha(this);
                Pointel.mainIntel.mostraPointelJarbs();
                return;
            case "PointelHelp":
                Janelas.fecha(this);
                Pointel.mainIntel.mostraPointelHelp();
                return;
            case "PointelInfo":
                Janelas.fecha(this);
                Pointel.mainIntel.mostraPointelInfo();
                return;
            case "PointelApps":
                Janelas.fecha(this);
                Pointel.mainIntel.mostraPointelApps();
                return;
            case "PointelMigre":
                Janelas.fecha(this);
                Pointel.mainIntel.mostraPointelMigre();
                return;
            case "PointelProk":
                Janelas.fecha(this);
                Pointel.mainIntel.mostraPointelProk();
                return;
        }
    }//GEN-LAST:event_formWindowActivated

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
