package pin.libitr;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import pin.libjan.FocoNavegador;
import pin.libjan.Janelas;
import pin.libjan.TrataIntf;
import pin.libutl.Utl;
import pin.libutl.UtlBin;
import pin.libutl.UtlCrs;

public class Mensagem extends javax.swing.JFrame {

    private String origem;
    private Boolean confirmado;
    private AbstractAction aoConfirmar;
    private AbstractAction aoCancelar;

    public Mensagem(String aMsg) {
        this(aMsg, "Mensagem", null, true, false, false);
    }

    public Mensagem(String aMsg, Boolean longa) {
        this(aMsg, "Mensagem", null, true, false, longa);
    }

    public Mensagem(String aMsg, String comTitulo) {
        this(aMsg, comTitulo, null, true, false, false);
    }

    public Mensagem(String aMsg, String comTitulo, Boolean cancelavel) {
        this(aMsg, comTitulo, null, true, cancelavel, false);
    }

    public Mensagem(String aMsg, String comTitulo, Boolean cancelavel, Boolean longa) {
        this(aMsg, comTitulo, null, true, cancelavel, longa);
    }

    public Mensagem(String aMsg, String comTitulo, String eOrigem) {
        this(aMsg, comTitulo, eOrigem, true, false, false);
    }

    public Mensagem(String aMsg, String comTitulo, String eOrigem, Boolean cancelavel) {
        this(aMsg, comTitulo, eOrigem, true, cancelavel, false);
    }

    public Mensagem(String aMsg, String comTitulo, String eOrigem, Boolean cancelavel, Boolean longa) {
        this(aMsg, comTitulo, eOrigem, true, cancelavel, longa);
    }

    public Mensagem(String aMsg, String comTitulo, String eOrigem, Boolean originavel, Boolean cancelavel, Boolean longa) {
        super();
        initComponents();
        try {
            setIconImage(new ImageIcon(Mensagem.class.getResource("bell.png")).getImage());
            jbtConfirmar.setIcon(new ImageIcon(Mensagem.class.getResource("accept.png")));
            jbtCancelar.setIcon(new ImageIcon(Mensagem.class.getResource("cancel.png")));
            jbtOrigem.setIcon(new ImageIcon(Notificar.class.getResource("relate.png")));
            jbtCopia.setIcon(new ImageIcon(Notificar.class.getResource("copy.png")));
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
        confirmado = !cancelavel;
        jbtCancelar.setVisible(cancelavel);
        if (longa) {
            Dimension dim = new Dimension(900, 700);
            setPreferredSize(dim);
            setSize(dim);
        }
        new FocoNavegador(jbtOrigem, jbtCopia, jbtCancelar, jbtConfirmar).inicia();
        getRootPane().setDefaultButton(jbtConfirmar);
        Janelas.inicia(this);
    }

    public Mensagem botaSimNao() {
        return botaSimNao(false);
    }

    public Mensagem botaSimNao(Boolean selecionaSim) {
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

    public Boolean aoExecutar(Boolean ehConfirmacao) {
        return ehConfirmacao;
    }

    public Mensagem botaAoConfirmar(AbstractAction aAcao) {
        aoConfirmar = aAcao;
        return this;
    }

    public Mensagem botaAoCancelar(AbstractAction aAcao) {
        aoCancelar = aAcao;
        return this;
    }

    public Mensagem mostra() {
        Janelas.mostra(this);
        return this;
    }

    public Boolean confirmado() {
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mensagem");
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

        jbtOrigem.setMnemonic('O');
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

        jbtCopia.setMnemonic('P');
        jbtCopia.setText("Copia");
        jbtCopia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCopiaActionPerformed(evt);
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 350, Short.MAX_VALUE)
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
                    .addComponent(jbtCopia))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtOrigemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOrigemActionPerformed
        new Mensagem(origem, "Origem", "", false, false, true).mostra();
    }//GEN-LAST:event_jbtOrigemActionPerformed

    private void jbtConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtConfirmarActionPerformed
        confirmado = true;
        if (aoExecutar(confirmado)) {
            if (aoConfirmar != null) {
                aoConfirmar.actionPerformed(evt);
            }
        } else {
            if (aoCancelar != null) {
                aoCancelar.actionPerformed(evt);
            }
        }
        Janelas.fecha(this);
    }//GEN-LAST:event_jbtConfirmarActionPerformed

    private void jbtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelarActionPerformed
        confirmado = false;
        if (aoExecutar(confirmado)) {
            if (aoConfirmar != null) {
                aoConfirmar.actionPerformed(evt);
            }
        } else {
            if (aoCancelar != null) {
                aoCancelar.actionPerformed(evt);
            }
        }
        Janelas.fecha(this);
    }//GEN-LAST:event_jbtCancelarActionPerformed

    private void jbtCopiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCopiaActionPerformed
        UtlCrs.copiaParaTransferencia(jtaMsg.getText());
    }//GEN-LAST:event_jbtCopiaActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        jbtConfirmar.requestFocus();
    }//GEN-LAST:event_formWindowActivated

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtCancelar;
    private javax.swing.JButton jbtConfirmar;
    private javax.swing.JButton jbtCopia;
    private javax.swing.JButton jbtOrigem;
    private javax.swing.JScrollPane jspMsg;
    private javax.swing.JTextArea jtaMsg;
    // End of variables declaration//GEN-END:variables
}
