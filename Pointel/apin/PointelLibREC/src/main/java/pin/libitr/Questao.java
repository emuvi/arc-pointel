package pin.libitr;

import java.awt.Font;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import pin.libjan.Janelas;
import pin.libjan.TrataIntf;
import pin.libutl.Utl;

public class Questao extends javax.swing.JFrame {

    private String resposta;
    private AbstractAction aoConfirmar;
    private AbstractAction aoCancelar;

    public Questao(String aMsg) {
        this(aMsg, "Questão", "");
    }

    public Questao(String aMsg, String comTitulo) {
        this(aMsg, comTitulo, "");
    }

    public Questao(String aMsg, String comTitulo, String ePadrao) {
        super();
        initComponents();
        setTitle(comTitulo);
        setIconImage(new ImageIcon(Pergunta.class.getResource("bell.png")).getImage());
        jbtConfirmar.setIcon(new ImageIcon(Pergunta.class.getResource("accept.png")));
        jbtCancelar.setIcon(new ImageIcon(Pergunta.class.getResource("cancel.png")));
        jtaMsg.setBackground(getBackground());
        jtaMsg.setFont(new Font(Font.SERIF, Font.BOLD, 18));
        jtaMsg.setText(aMsg);
        TrataIntf.posicionaNoComeco(jspMsg);
        jtaResposta.setText(ePadrao);
        resposta = null;
        aoConfirmar = null;
        aoCancelar = null;
        getRootPane().setDefaultButton(jbtConfirmar);
        Janelas.inicia(this);
    }

    public Questao mostra() {
        Janelas.mostra(this);
        return this;
    }

    public String pegaResposta() {
        return resposta;
    }

    public Boolean executa(String comResposta) throws Exception {
        return true;
    }

    public AbstractAction aoConfirmar() {
        return aoConfirmar;
    }

    public Questao botaAoConfirmar(AbstractAction aoConfirmar) {
        this.aoConfirmar = aoConfirmar;
        return this;
    }

    public AbstractAction aoCancelar() {
        return aoCancelar;
    }

    public Questao botaAoCancelar(AbstractAction aoCancelar) {
        this.aoCancelar = aoCancelar;
        return this;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jspMsg = new javax.swing.JScrollPane();
        jtaMsg = new javax.swing.JTextArea();
        jspResposta = new javax.swing.JScrollPane();
        jtaResposta = new javax.swing.JTextArea();
        jbtConfirmar = new javax.swing.JButton();
        jbtCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Questão");
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

        jtaResposta.setColumns(20);
        jtaResposta.setRows(1);
        jspResposta.setViewportView(jtaResposta);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbtConfirmar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jspMsg, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
                            .addComponent(jspResposta))))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jspMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspResposta, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtCancelar)
                    .addComponent(jbtConfirmar))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtConfirmarActionPerformed
        resposta = jtaResposta.getText();
        try {
            if (executa(resposta)) {
                if (aoConfirmar != null) {
                    aoConfirmar.actionPerformed(evt);
                }
            } else {
                if (aoCancelar != null) {
                    aoCancelar.actionPerformed(evt);
                }
            }
        } catch (Exception ex) {
            Utl.registra(ex);
        }
        Janelas.fecha(this);
    }//GEN-LAST:event_jbtConfirmarActionPerformed

    private void jbtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelarActionPerformed
        if (aoCancelar != null) {
            aoCancelar.actionPerformed(evt);
        }
        Janelas.fecha(this);
    }//GEN-LAST:event_jbtCancelarActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        jtaResposta.requestFocus();
    }//GEN-LAST:event_formWindowActivated

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtCancelar;
    private javax.swing.JButton jbtConfirmar;
    private javax.swing.JScrollPane jspMsg;
    private javax.swing.JScrollPane jspResposta;
    private javax.swing.JTextArea jtaMsg;
    private javax.swing.JTextArea jtaResposta;
    // End of variables declaration//GEN-END:variables
}
