package pin.libitr;

import java.awt.Color;
import javax.swing.SwingUtilities;
import pin.libjan.Janelas;
import pin.libjan.TrataIntf;
import pin.libutl.UtlCrs;

public class Ver extends javax.swing.JFrame {

    public Ver() {
        this(null);
    }

    public Ver(String comTitulo) {
        initComponents();
        if (comTitulo != null) {
            setTitle(comTitulo);
        }
        jtaVer.setOpaque(false);
        jtaVer.setBackground(new Color(0, 0, 0, 0));
        Janelas.inicia(this);
    }

    public void bota(Object oValor) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                String antes = jtaVer.getText().trim();
                jtaVer.setText(antes + (antes.isEmpty() ? "" : System.lineSeparator()) + UtlCrs.pega(oValor));
                TrataIntf.posicionaNoFimVertical(jspVer);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jspVer = new javax.swing.JScrollPane();
        jtaVer = new javax.swing.JTextArea();

        setTitle("Ver");

        jtaVer.setEditable(false);
        jtaVer.setColumns(20);
        jtaVer.setRows(5);
        jspVer.setViewportView(jtaVer);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jspVer, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jspVer, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jspVer;
    private javax.swing.JTextArea jtaVer;
    // End of variables declaration//GEN-END:variables
}
