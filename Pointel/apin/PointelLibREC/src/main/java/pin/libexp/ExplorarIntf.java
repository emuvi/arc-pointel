package pin.libexp;

import java.awt.BorderLayout;
import java.io.File;
import pin.libjan.Janelas;
import pin.libpic.Pics;

public class ExplorarIntf extends javax.swing.JFrame {

    private Explorar explorador;

    public ExplorarIntf() throws Exception {
        this(null);
    }

    public ExplorarIntf(File abrirPasta) throws Exception {
        initComponents();
        setIconImage(Pics.pega("Explorar.png").getImage());
        explorador = new Explorar(abrirPasta);
        setLayout(new BorderLayout());
        add(explorador, BorderLayout.CENTER);
        Janelas.inicia(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Explorar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
