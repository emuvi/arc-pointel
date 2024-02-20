package pin.libvtf;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import pin.libutl.Utl;
import pin.libutl.UtlCrs;

public class TecladoVtf extends javax.swing.JFrame {

    private final JComponent editor;
    private final String inicio;
    private Boolean confirmado;

    public TecladoVtf(JComponent paraEditor, String comInicio) {
        initComponents();
        editor = paraEditor;
        getRootPane().setBorder(BorderFactory.createLineBorder(Color.black, 1));
        addWindowListener(new EvtJanela());
        addKeyListener(new EvtTeclado());
        inicio = comInicio;
        confirmado = false;
    }

    public TecladoVtf mostra() {
        setLocation(editor.getLocationOnScreen().x + editor.getWidth(), editor.getLocationOnScreen().y + editor.getHeight());
        setVisible(true);
        requestFocus();
        return this;
    }

    public String pTeclado() {
        return jlbTeclado.getText();
    }

    public Boolean aoMudar(String comTeclado) {

        return true;
    }

    private void confirmar() {
        try {
            if (aoMudar(pTeclado())) {
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

        jlbTecla = new javax.swing.JLabel();
        jlbTeclado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Teclado Vtf");
        setUndecorated(true);
        setResizable(false);

        jlbTecla.setText("Tecla");

        jlbTeclado.setText("Digite uma tecla");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbTecla)
                    .addComponent(jlbTeclado, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jlbTecla)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlbTeclado)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jlbTecla;
    private javax.swing.JLabel jlbTeclado;
    // End of variables declaration//GEN-END:variables

    private class EvtJanela extends WindowAdapter {

        @Override
        public void windowDeactivated(WindowEvent e) {
            cancelar();
        }

        @Override
        public void windowLostFocus(WindowEvent e) {
            cancelar();
        }

    }

    private class EvtTeclado extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            String atalho = KeyStroke.getKeyStroke(e.getExtendedKeyCode(), e.getModifiers()).toString();
            atalho = UtlCrs.troca(atalho, "pressed ", "");
            jlbTeclado.setText(atalho);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (!e.isAltDown() && !e.isControlDown() && !e.isShiftDown() && !e.isMetaDown()) {
                confirmar();
            }
        }

    }
}
