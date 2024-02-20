package pin.libitr;

import java.awt.Font;
import java.awt.Frame;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import pin.libjan.Janelas;
import pin.libutl.Utl;
import pin.libutl.UtlBin;
import pin.libutl.UtlCrs;

public class Notificar extends javax.swing.JFrame {

    public String origem;

    public static void ver(String aMensagem) {
        Boolean achou = false;

        for (Frame frm : JFrame.getFrames()) {
            if (frm instanceof Notificar) {
                Notificar notf = (Notificar) frm;
                if (notf.jtaMsg.getText().equals(aMensagem)) {
                    Janelas.mostra(notf);
                    achou = true;
                }
            }
        }

        if (!achou) {
            Notificar notf = new Notificar(aMensagem);
            Janelas.mostra(notf);
        }
    }

    private Notificar(String aMensagem) {
        initComponents();
        try {
            setIconImage(new ImageIcon(Notificar.class.getResource("bell.png")).getImage());
            jbtCopia.setIcon(new ImageIcon(Notificar.class.getResource("copy.png")));
            jbtOrigem.setIcon(new ImageIcon(Notificar.class.getResource("relate.png")));
            jbtEnviar.setIcon(new ImageIcon(Notificar.class.getResource("send.png")));
            jbtConfirmar.setIcon(new ImageIcon(Notificar.class.getResource("accept.png")));
        } catch (Exception ex) {
            Utl.registra(ex, false);
        }
        jtaMsg.setBackground(getBackground());
        jtaMsg.setFont(new Font(Font.SERIF, Font.BOLD, 18));
        jtaMsg.setText(aMensagem);
        origem = UtlBin.pegaOrigem();
        jbtEnviar.setVisible(Alerta.alertaEnvio != null);
        getRootPane().setDefaultButton(jbtConfirmar);
        Janelas.inicia(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jspMensagem = new javax.swing.JScrollPane();
        jtaMsg = new javax.swing.JTextArea();
        jbtConfirmar = new javax.swing.JButton();
        jbtOrigem = new javax.swing.JButton();
        jbtCopia = new javax.swing.JButton();
        jbtEnviar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Notificação");
        setAlwaysOnTop(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jtaMsg.setEditable(false);
        jtaMsg.setColumns(20);
        jtaMsg.setLineWrap(true);
        jtaMsg.setRows(5);
        jtaMsg.setWrapStyleWord(true);
        jspMensagem.setViewportView(jtaMsg);

        jbtConfirmar.setMnemonic('C');
        jbtConfirmar.setText("Confirmar");
        jbtConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtConfirmarActionPerformed(evt);
            }
        });

        jbtOrigem.setMnemonic('O');
        jbtOrigem.setText("Origem");
        jbtOrigem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOrigemActionPerformed(evt);
            }
        });

        jbtCopia.setMnemonic('P');
        jbtCopia.setText("Copia");
        jbtCopia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCopiaActionPerformed(evt);
            }
        });

        jbtEnviar.setText("Enviar");
        jbtEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtEnviarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspMensagem, javax.swing.GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jbtCopia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtOrigem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtEnviar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtConfirmar)))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jspMensagem, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtConfirmar)
                    .addComponent(jbtOrigem)
                    .addComponent(jbtCopia)
                    .addComponent(jbtEnviar))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtConfirmarActionPerformed
        Janelas.fecha(this);
    }//GEN-LAST:event_jbtConfirmarActionPerformed

    private void jbtOrigemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOrigemActionPerformed
        new Alerta(origem, "Origem", "", false, false, true).setVisible(true);
    }//GEN-LAST:event_jbtOrigemActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        jbtConfirmar.requestFocus();
    }//GEN-LAST:event_formWindowActivated

    private void jbtCopiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCopiaActionPerformed
        UtlCrs.copiaParaTransferencia(jtaMsg.getText());
    }//GEN-LAST:event_jbtCopiaActionPerformed

    private void jbtEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtEnviarActionPerformed
        Alerta.alertaEnvio.enviar(jtaMsg.getText(), origem);
    }//GEN-LAST:event_jbtEnviarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtConfirmar;
    private javax.swing.JButton jbtCopia;
    private javax.swing.JButton jbtEnviar;
    private javax.swing.JButton jbtOrigem;
    private javax.swing.JScrollPane jspMensagem;
    public javax.swing.JTextArea jtaMsg;
    // End of variables declaration//GEN-END:variables
}
