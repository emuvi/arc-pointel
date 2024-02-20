package pin.ldttex;

import pin.libjan.Janelas;
import pin.libjan.TrataIntf;
import pin.libpic.Pics;

public class TexLocalizar extends javax.swing.JFrame {

    private final LdtTex tex;

    public TexLocalizar(LdtTex doTex) {
        Janelas.botaQuadro(this);
        initComponents();
        tex = doTex;
        jbtFechar.setIcon(Pics.pFechar());
        jbtAnterior.setIcon(Pics.pEsquerda());
        jbtProximo.setIcon(Pics.pDireita());
        getRootPane().setDefaultButton(jbtProximo);
        Janelas.inicia(this);
    }

    public TexLocalizar mostra() {
        setLocation(
                tex.getLocationOnScreen().x,
                tex.getLocationOnScreen().y + tex.getHeight()
        );
        Janelas.mostra(this);
        return this;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlbLocalizar = new javax.swing.JLabel();
        jtfLocalizar = new javax.swing.JTextField();
        jbtFechar = new javax.swing.JButton();
        jbtProximo = new javax.swing.JButton();
        jbtAnterior = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Localizar Tex");
        setUndecorated(true);
        setResizable(false);

        jlbLocalizar.setText("Localizar");

        jbtFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtFecharActionPerformed(evt);
            }
        });

        jbtProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtProximoActionPerformed(evt);
            }
        });

        jbtAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAnteriorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbtFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtProximo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jlbLocalizar)
                        .addComponent(jtfLocalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbLocalizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfLocalizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtProximo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAnteriorActionPerformed
        int com = tex.controle().getSelectionStart() - 1;
        String txt = tex.controle().getText();
        String loc = jtfLocalizar.getText();
        int fnd = txt.lastIndexOf(loc, com);
        if (fnd == -1) {
            fnd = txt.lastIndexOf(loc);
        }
        if (fnd > -1) {
            tex.controle().setSelectionStart(fnd);
            tex.controle().setSelectionEnd(fnd + loc.length());
        }
        TrataIntf.posicionaNaSelecao(tex.rolagem());
    }//GEN-LAST:event_jbtAnteriorActionPerformed

    private void jbtProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtProximoActionPerformed
        int com = tex.controle().getSelectionEnd() + 1;
        String txt = tex.controle().getText();
        String loc = jtfLocalizar.getText();
        int fnd = txt.indexOf(loc, com);
        if (fnd == -1) {
            fnd = txt.indexOf(loc);
        }
        if (fnd > -1) {
            tex.controle().setSelectionStart(fnd);
            tex.controle().setSelectionEnd(fnd + loc.length());
        }
        TrataIntf.posicionaNaSelecao(tex.rolagem());
    }//GEN-LAST:event_jbtProximoActionPerformed

    private void jbtFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtFecharActionPerformed
        Janelas.fecha(this);
    }//GEN-LAST:event_jbtFecharActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtAnterior;
    private javax.swing.JButton jbtFechar;
    private javax.swing.JButton jbtProximo;
    private javax.swing.JLabel jlbLocalizar;
    private javax.swing.JTextField jtfLocalizar;
    // End of variables declaration//GEN-END:variables
}
