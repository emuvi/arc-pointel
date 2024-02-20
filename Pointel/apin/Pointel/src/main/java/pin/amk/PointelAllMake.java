package pin.amk;

import pin.libbas.Configs;
import pin.libbas.Globais;
import pin.libjan.Janelas;
import pin.libpic.Pics;
import pin.libutl.Utl;
import pin.libutl.UtlIma;
import pin.modamk.Recursos;
import pin.modinf.Conexao;
import pin.modpim.Pims;

public class PointelAllMake extends javax.swing.JFrame {

    public Configs cfgs;
    public Conexao conexao;

    public PointelAllMake() throws Exception {
        initComponents();
        cfgs = (Configs) Globais.pega("mainConf");
        conexao = (Conexao) Globais.pega("mainConc");
        setIconImage(Pims.pega("PointelAllMake.png").getImage());
        try {
            UtlIma.botaPic(jlbIcone, "PointelAllMake64.png", "PointelAllMake");
        } catch (Exception ex) {
            Utl.registra(ex);
        }
        Janelas.inicia(this, this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlbIcone = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PointelAllMake");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jlbIcone.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbIcone.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbIcone, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbIcone, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
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
    private javax.swing.JLabel jlbIcone;
    // End of variables declaration//GEN-END:variables
}
