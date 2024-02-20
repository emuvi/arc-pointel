package pin.adp;

import pin.libbas.Globais;
import pin.libjan.Janelas;
import pin.modinf.Conexao;
import pin.modpim.Pims;

public class Arquivos extends javax.swing.JFrame {

    public Conexao conexao;

    public Arquivos() throws Exception {
        initComponents();
        setIconImage(Pims.pega("Arquivos.png").getImage());
        conexao = (Conexao) Globais.pega("mainConc");
        conexao.deveConectar(this);
        Janelas.inicia(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jspArquivos = new javax.swing.JScrollPane();
        jlsArquivos = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Arquivos");

        jspArquivos.setViewportView(jlsArquivos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jspArquivos, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jspArquivos, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> jlsArquivos;
    private javax.swing.JScrollPane jspArquivos;
    // End of variables declaration//GEN-END:variables
}
