package pin.adp;

import pin.libbas.Globais;
import pin.libjan.Janelas;
import pin.modamk.Recursos;
import pin.modinf.Conexao;
import pin.modpim.Pims;

public class Agenda extends javax.swing.JFrame {

    public Conexao conexao;

    public Agenda() throws Exception {
        initComponents();
        setIconImage(Pims.pega("Agenda.png").getImage());
        Recursos mainRecs = (Recursos) Globais.pega("mainRecs");
        mainRecs.criaMenu("Pointel.PointelAdPro.Agenda", this);
        conexao = (Conexao) Globais.pega("mainConc");
        conexao.deveConectar(this);
        Janelas.inicia(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agenda");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 69, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
