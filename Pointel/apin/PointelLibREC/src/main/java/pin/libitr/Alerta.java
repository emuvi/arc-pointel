package pin.libitr;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import pin.libjan.FocoNavegador;
import pin.libjan.Janelas;
import pin.libjan.TrataIntf;
import pin.libutl.Utl;
import pin.libutl.UtlBin;
import pin.libutl.UtlCrs;

public class Alerta extends javax.swing.JDialog {

    public static volatile AlertaEnvio alertaEnvio = null;

    private String origem;
    private Boolean confirmado;

    public Alerta(String aMsg) {
        this(aMsg, "Mensagem", null, true, false, false);
    }

    public Alerta(String aMsg, String comTitulo) {
        this(aMsg, comTitulo, null, true, false, false);
    }

    public Alerta(String aMsg, String comTitulo, Boolean cancelavel) {
        this(aMsg, comTitulo, null, true, cancelavel, false);
    }

    public Alerta(String aMsg, String comTitulo, Boolean cancelavel, Boolean longo) {
        this(aMsg, comTitulo, null, true, cancelavel, longo);
    }

    public Alerta(String aMsg, String comTitulo, String eOrigem) {
        this(aMsg, comTitulo, eOrigem, true, false, false);
    }

    public Alerta(String aMsg, String comTitulo, String eOrigem, Boolean cancelavel) {
        this(aMsg, comTitulo, eOrigem, true, cancelavel, false);
    }

    public Alerta(String aMsg, String comTitulo, String eOrigem, Boolean cancelavel, Boolean longo) {
        this(aMsg, comTitulo, eOrigem, true, cancelavel, longo);
    }

    public Alerta(String aMsg, String comTitulo, String eOrigem, Boolean originavel, Boolean cancelavel, Boolean longo) {
        super((JFrame) null, true);
        initComponents();
        try {
            setIconImage(new ImageIcon(Alerta.class.getResource("bell.png")).getImage());
            jbtCopia.setIcon(new ImageIcon(Alerta.class.getResource("copy.png")));
            jbtOrigem.setIcon(new ImageIcon(Alerta.class.getResource("relate.png")));
            jbtEnviar.setIcon(new ImageIcon(Alerta.class.getResource("send.png")));
            jbtConfirmar.setIcon(new ImageIcon(Alerta.class.getResource("accept.png")));
            jbtCancelar.setIcon(new ImageIcon(Alerta.class.getResource("cancel.png")));
        } catch (Exception ex) {
            Utl.registra(ex, false);
        }
        if (comTitulo == null) {
            comTitulo = "Pointel";
        }
        setTitle(comTitulo);
        jtaMsg.setBackground(getBackground());
        jtaMsg.setFont(new Font(Font.SERIF, Font.BOLD, 18));
        jtaMsg.setText(aMsg);
        TrataIntf.posicionaNoComeco(jspMsg);
        if (eOrigem == null) {
            origem = UtlBin.pegaOrigem();
        } else {
            origem = eOrigem;
        }
        jbtOrigem.setVisible(originavel);
        jbtEnviar.setVisible(alertaEnvio != null);
        confirmado = !cancelavel;
        jbtCancelar.setVisible(cancelavel);
        if (longo) {
            Dimension dim = new Dimension(900, 700);
            setPreferredSize(dim);
            setSize(dim);
        }
        new FocoNavegador(jbtOrigem, jbtCopia, jbtCancelar, jbtConfirmar).inicia();
        getRootPane().setDefaultButton(jbtConfirmar);
        Janelas.inicia(this);
    }

    public Alerta botaSimNao() {
        return botaSimNao(false);
    }

    public Alerta botaSimNao(Boolean selecionaSim) {
        confirmado = false;
        jbtCancelar.setVisible(true);
        jbtConfirmar.setText("Sim");
        jbtCancelar.setText("Não");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                if (selecionaSim) {
                    TrataIntf.garanteFoco(jbtConfirmar);
                } else {
                    TrataIntf.garanteFoco(jbtCancelar);
                }
            }
        });
        return this;
    }

    public Alerta mostra() {
        Janelas.mostra(this);
        return this;
    }

    public Boolean confirmado() {
        setVisible(true);
        return confirmado;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jspMsg = new javax.swing.JScrollPane();
        jtaMsg = new javax.swing.JTextArea();
        jbtOrigem = new javax.swing.JButton();
        jbtConfirmar = new javax.swing.JButton();
        jbtCancelar = new javax.swing.JButton();
        jbtCopia = new javax.swing.JButton();
        jbtEnviar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jspMsg.setBorder(null);

        jtaMsg.setEditable(false);
        jtaMsg.setColumns(20);
        jtaMsg.setLineWrap(true);
        jtaMsg.setRows(1);
        jtaMsg.setWrapStyleWord(true);
        jspMsg.setViewportView(jtaMsg);

        jbtOrigem.setMnemonic('o');
        jbtOrigem.setText("Origem");
        jbtOrigem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOrigemActionPerformed(evt);
            }
        });

        jbtConfirmar.setMnemonic('C');
        jbtConfirmar.setText("Confirmar");
        jbtConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtConfirmarActionPerformed(evt);
            }
        });

        jbtCancelar.setMnemonic('A');
        jbtCancelar.setText("Cancelar");
        jbtCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCancelarActionPerformed(evt);
            }
        });

        jbtCopia.setMnemonic('p');
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
                    .addComponent(jspMsg)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbtCopia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtOrigem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtEnviar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 276, Short.MAX_VALUE)
                        .addComponent(jbtCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtConfirmar)))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jspMsg, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtOrigem)
                    .addComponent(jbtConfirmar)
                    .addComponent(jbtCancelar)
                    .addComponent(jbtCopia)
                    .addComponent(jbtEnviar))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtOrigemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOrigemActionPerformed
        new Alerta(origem, "Origem", "", false, false, true).setVisible(true);
    }//GEN-LAST:event_jbtOrigemActionPerformed

    private void jbtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelarActionPerformed
        confirmado = false;
        Janelas.fecha(this);
    }//GEN-LAST:event_jbtCancelarActionPerformed

    private void jbtConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtConfirmarActionPerformed
        confirmado = true;
        Janelas.fecha(this);
    }//GEN-LAST:event_jbtConfirmarActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if (jbtCancelar.isVisible()) {
            jbtCancelar.requestFocus();
        } else {
            jbtConfirmar.requestFocus();
        }
    }//GEN-LAST:event_formWindowActivated

    private void jbtCopiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCopiaActionPerformed
        UtlCrs.copiaParaTransferencia(jtaMsg.getText());
    }//GEN-LAST:event_jbtCopiaActionPerformed

    private void jbtEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtEnviarActionPerformed
        alertaEnvio.enviar(jtaMsg.getText(), origem);
    }//GEN-LAST:event_jbtEnviarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtCancelar;
    private javax.swing.JButton jbtConfirmar;
    private javax.swing.JButton jbtCopia;
    private javax.swing.JButton jbtEnviar;
    private javax.swing.JButton jbtOrigem;
    private javax.swing.JScrollPane jspMsg;
    private javax.swing.JTextArea jtaMsg;
    // End of variables declaration//GEN-END:variables

    private class ActDireita extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (jbtOrigem.isFocusOwner()) {
                jbtCopia.requestFocus();
            } else if (jbtCopia.isFocusOwner()) {
                jbtCopia.requestFocus();
            }
        }
    }

    private class ActEsquerda extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

}
