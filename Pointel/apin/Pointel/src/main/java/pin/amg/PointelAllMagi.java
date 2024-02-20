package pin.amg;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import pin.libamk.Cmp;
import pin.libbas.Dados;
import pin.libbas.Globais;
import pin.libjan.Janelas;
import pin.libjan.PopMenu;
import pin.libutl.Utl;
import pin.modamk.Opcoes;
import pin.modamk.Recursos;
import pin.modpim.Pims;

public class PointelAllMagi extends javax.swing.JFrame {

    public PopMenu menu;

    public PointelAllMagi() throws Exception {
        initComponents();
        setIconImage(Pims.pega("PointelAllMagi.png").getImage());

        menu = new PopMenu();
        Recursos mainRecs = (Recursos) Globais.pega("mainRecs");
        mainRecs.criaMenu("Pointel.PointelAllMagi", this, menu);

        Janelas.botaAtalho(this, "Opções da Máquina", "control alt shift O", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Opcoes(false, new Cmp[]{
                        new Cmp(1, 1, "Campo Padrão da Procura dos Cadastros", Dados.Tp.Crs).botaDetalhe("referencia", "Cadastro - Procurar - Campo Padrão")
                    }).mostra();
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        });

        Janelas.inicia(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PointelAllMagi");
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
            .addGap(0, 59, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 59, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        try {
            Recursos mainRecs = (Recursos) Globais.pega("mainRecs");
            mainRecs.verificaRestricao(this, "PointelAdmin");
        } catch (Exception ex) {
            Utl.registra(ex);
            Janelas.fecha(this);
        }
    }//GEN-LAST:event_formWindowActivated

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
