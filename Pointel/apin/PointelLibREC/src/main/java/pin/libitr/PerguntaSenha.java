package pin.libitr;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import pin.libjan.Janelas;
import pin.libjan.TrataIntf;
import pin.libutl.Utl;

public class PerguntaSenha extends javax.swing.JDialog {

    private String resposta;
    private Boolean fecharDepois;
    private Boolean confirmado;

    public PerguntaSenha() {
        this("Insira a Senha", "Pointel Senha", true);
    }

    public PerguntaSenha(String aMsg) {
        this(aMsg, "Pointel Senha", true);
    }

    public PerguntaSenha(String aMsg, Boolean eFecharDepois) {
        this(aMsg, "Pointel Senha", eFecharDepois);
    }

    public PerguntaSenha(String aMsg, String comTitulo) {
        this(aMsg, comTitulo, true);
    }

    public PerguntaSenha(String aMsg, String comTitulo, Boolean eFecharDepois) {
        super((JFrame) null, true);
        initComponents();
        try {
            setIconImage(new ImageIcon(PerguntaSenha.class.getResource("bell.png")).getImage());
            jbtConfirmar.setIcon(new ImageIcon(PerguntaSenha.class.getResource("accept.png")));
            jbtCancelar.setIcon(new ImageIcon(PerguntaSenha.class.getResource("cancel.png")));
        } catch (Exception ex) {
            Utl.registra(ex);
        }
        setTitle(comTitulo);
        jtaMsg.setBackground(getBackground());
        jtaMsg.setFont(new Font(Font.SERIF, Font.BOLD, 18));
        jtaMsg.setText(aMsg);
        TrataIntf.posicionaNoComeco(jspMsg);
        fecharDepois = eFecharDepois;
        confirmado = false;
        resposta = null;
        getRootPane().setDefaultButton(jbtConfirmar);
        Janelas.inicia(this);
    }

    public String pegaResposta() {
        return resposta;
    }

    public PerguntaSenha botaFechar() {
        this.fecharDepois = true;
        return this;
    }

    public PerguntaSenha tiraFechar() {
        this.fecharDepois = false;
        return this;
    }

    public Boolean pegaFechar() {
        return fecharDepois;
    }

    public PerguntaSenha botaConfirmado() {
        this.confirmado = true;
        return this;
    }

    public PerguntaSenha tiraConfirmado() {
        this.confirmado = false;
        return this;
    }

    public Boolean pegaConfirmado() {
        return confirmado;
    }

    public PerguntaSenha botaConfirmadoEFechar() {
        this.confirmado = true;
        this.fecharDepois = true;
        return this;
    }

    public PerguntaSenha tiraConfirmadoEFechar() {
        this.confirmado = false;
        this.fecharDepois = false;
        return this;
    }

    public PerguntaSenha mostra() {
        Janelas.mostra(this);
        return this;
    }

    public PerguntaSenha mostraAlerta() {
        Utl.alerta("Senha Não Conferiu", getTitle());
        return this;
    }

    public void aoConfirmar(String comResposta) throws Exception {
    }

    public void aoCancelar() throws Exception {
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jspMsg = new javax.swing.JScrollPane();
        jtaMsg = new javax.swing.JTextArea();
        jpfSenha = new javax.swing.JPasswordField();
        jbtConfirmar = new javax.swing.JButton();
        jbtCancelar = new javax.swing.JButton();

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
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspMsg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                    .addComponent(jpfSenha)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbtConfirmar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtCancelar)))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jspMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtCancelar)
                    .addComponent(jbtConfirmar))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtConfirmarActionPerformed
        try {
            resposta = new String(jpfSenha.getPassword());
            aoConfirmar(resposta);
            if (fecharDepois) {
                Janelas.fecha(this);
            }
        } catch (Exception ex) {
            Utl.registra(ex);
            jpfSenha.requestFocus();
        }
    }//GEN-LAST:event_jbtConfirmarActionPerformed

    private void jbtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelarActionPerformed
        try {
            aoCancelar();
            if (fecharDepois) {
                Janelas.fecha(this);
            }
        } catch (Exception ex) {
            Utl.registra(ex);
            jpfSenha.requestFocus();
        }
    }//GEN-LAST:event_jbtCancelarActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        TrataIntf.garanteFoco(jpfSenha);
    }//GEN-LAST:event_formWindowActivated

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtCancelar;
    private javax.swing.JButton jbtConfirmar;
    private javax.swing.JPasswordField jpfSenha;
    private javax.swing.JScrollPane jspMsg;
    private javax.swing.JTextArea jtaMsg;
    // End of variables declaration//GEN-END:variables
}
