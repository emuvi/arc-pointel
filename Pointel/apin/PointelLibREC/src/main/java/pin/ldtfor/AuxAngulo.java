package pin.ldtfor;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import pin.libjan.Janelas;
import pin.libjan.TrataIntf;
import pin.libpic.Pics;
import pin.libutl.Utl;
import pin.libutl.UtlNumLon;

public class AuxAngulo extends javax.swing.JFrame {

    private final JComponent editor;
    private final SpinnerNumberModel model;
    private final Double inicio;
    private Boolean confirmado;

    public AuxAngulo(JComponent paraEditor, Double comInicio) {
        initComponents();
        editor = paraEditor;
        inicio = comInicio;
        getRootPane().setBorder(BorderFactory.createLineBorder(Color.black, 1));
        getRootPane().setDefaultButton(jbtConfirmar);
        addWindowListener(new EvtJanela());
        Janelas.botaAtalho(this, "Sair", "ESCAPE", new ActCancelar());
        model = new SpinnerNumberModel((inicio == null ? 0.0 : inicio), -360.0, 360.0, 0.5);
        jspAngulo.setModel(model);
        jspAngulo.addChangeListener(new EvtMudar());
        jbtConfirmar.setIcon(Pics.pegaConfirma());
        jbtCancelar.setIcon(Pics.pegaCancela());
        confirmado = false;
    }

    public AuxAngulo mostra() {
        setLocation(editor.getLocationOnScreen().x + editor.getWidth(), editor.getLocationOnScreen().y + editor.getHeight());
        setVisible(true);
        requestFocus();
        return this;
    }

    public Double pAngulo() {
        Double retorno = UtlNumLon.pega(jspAngulo.getValue());
        if (retorno != null) {
            if (retorno == 0.0 || retorno == 360.0 || retorno == -360.0) {
                retorno = null;
            }
        }
        return retorno;
    }

    public Boolean aoMudar(Double paraAngulo) throws Exception {
        return true;
    }

    public Boolean confirmado() {
        return confirmado;
    }

    private void confirmar() {
        try {
            if (aoMudar(pAngulo())) {
                confirmado = true;
                setVisible(false);
                editor.requestFocusInWindow();
                editor.requestFocus();
            }
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }

    private void cancelar() {
        try {
            if (!confirmado) {
                aoMudar(inicio);
                setVisible(false);
                editor.requestFocusInWindow();
                editor.requestFocus();
            }
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlbAngulo = new javax.swing.JLabel();
        jspAngulo = new javax.swing.JSpinner();
        jbtConfirmar = new javax.swing.JButton();
        jbtCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ângulo da Forma");
        setUndecorated(true);
        setResizable(false);

        jlbAngulo.setText("Ângulo");

        jbtConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtConfirmarActionPerformed(evt);
            }
        });

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
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbtConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jlbAngulo)
                        .addComponent(jspAngulo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jlbAngulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspAngulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtConfirmarActionPerformed
        confirmar();
    }//GEN-LAST:event_jbtConfirmarActionPerformed

    private void jbtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelarActionPerformed
        cancelar();
    }//GEN-LAST:event_jbtCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtCancelar;
    private javax.swing.JButton jbtConfirmar;
    private javax.swing.JLabel jlbAngulo;
    private javax.swing.JSpinner jspAngulo;
    // End of variables declaration//GEN-END:variables

    private class EvtMudar implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            try {
                aoMudar(pAngulo());
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }

    }

    private class EvtJanela extends WindowAdapter {

        @Override
        public void windowActivated(WindowEvent e) {
            TrataIntf.garanteFoco(jspAngulo.getEditor());
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
            cancelar();
        }

        @Override
        public void windowLostFocus(WindowEvent e) {
            cancelar();
        }

    }

    private class ActCancelar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            cancelar();
        }
    }
}
