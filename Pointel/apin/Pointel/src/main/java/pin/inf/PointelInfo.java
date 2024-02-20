package pin.inf;

import pin.libbas.Globais;
import pin.libjan.Janelas;
import pin.libutl.Utl;
import pin.modamk.Recursos;
import pin.modinf.Conexao;
import pin.modpim.Pims;

public class PointelInfo extends javax.swing.JFrame {

    public Conexao conexao;

    public PointelInfo() throws Exception {
        initComponents();
        conexao = (Conexao) Globais.pega("mainConc");
        setIconImage(Pims.pega("PointelInfo.png").getImage());
        Recursos mainRecs = (Recursos) Globais.pega("mainRecs");
        mainRecs.criaMenu("Pointel.PointelAllMagi.PointelInfo", this);
        Janelas.inicia(this, this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpmEstrutura = new javax.swing.JPopupMenu();
        jmiEstTabelas = new javax.swing.JMenuItem();
        jpmUsuarios = new javax.swing.JPopupMenu();
        jmiUsuUsuarios = new javax.swing.JMenuItem();
        jmiUsuGrupos = new javax.swing.JMenuItem();
        jmiUsuHabilitacoes = new javax.swing.JMenuItem();

        jmiEstTabelas.setText("Tabelas");
        jmiEstTabelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiEstTabelasActionPerformed(evt);
            }
        });
        jpmEstrutura.add(jmiEstTabelas);

        jmiUsuUsuarios.setText("Usuários");
        jmiUsuUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiUsuUsuariosActionPerformed(evt);
            }
        });
        jpmUsuarios.add(jmiUsuUsuarios);

        jmiUsuGrupos.setText("Grupos");
        jpmUsuarios.add(jmiUsuGrupos);

        jmiUsuHabilitacoes.setText("Habilitações");
        jpmUsuarios.add(jmiUsuHabilitacoes);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PointelInfo");
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
            .addGap(0, 60, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 62, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        try {
            Recursos mainRecs = (Recursos) Globais.pega("mainRecs");
            if (mainRecs.verificaRestricao(this, "PointelAdmin")) {
                conexao.conectaBase(this, true);
            }
        } catch (Exception ex) {
            Utl.registra(ex);
            Janelas.fecha(this);
        }
    }//GEN-LAST:event_formWindowActivated

    private void jmiUsuUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiUsuUsuariosActionPerformed
        try {
            new CadUsuarios().mostra();
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jmiUsuUsuariosActionPerformed

    private void jmiEstTabelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiEstTabelasActionPerformed
        try {
            new TabelasIntf().mostra();
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jmiEstTabelasActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem jmiEstTabelas;
    private javax.swing.JMenuItem jmiUsuGrupos;
    private javax.swing.JMenuItem jmiUsuHabilitacoes;
    private javax.swing.JMenuItem jmiUsuUsuarios;
    private javax.swing.JPopupMenu jpmEstrutura;
    private javax.swing.JPopupMenu jpmUsuarios;
    // End of variables declaration//GEN-END:variables
}
