package pin.libamk;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import pin.libbas.Globais;
import pin.libitr.FormulaCrs;
import pin.libitr.PerguntaTpVr;
import pin.libjan.JanTutela;
import pin.libjan.Janelas;
import pin.libout.JCheckList;
import pin.libutl.Utl;
import pin.libutl.UtlBin;
import pin.libutl.UtlCrs;

public class JanelasIntf extends javax.swing.JFrame {

    private JCheckList<JanTutela> jclJanelas;
    private Rectangle area;

    public JanelasIntf() {
        initComponents();
        jclJanelas = new JCheckList();
        jclJanelas.setSelectionMultiple();
        jspJanelas.setViewportView(jclJanelas);
        area = (Rectangle) Globais.pega("mainArea");
        jclJanelas.addMouseListener(new JansClickado());
        addWindowListener(new JansAtivada());
        Janelas.inicia(this);
    }

    public JanelasIntf mostra() {
        Janelas.mostra(this);
        return this;
    }

    public JanelasIntf atualiza() {
        List<JanTutela> selecionados = jclJanelas.getSelectedValuesList();
        jclJanelas.clear();
        jclJanelas.addItems(Janelas.pTutelas());
        jclJanelas.select(selecionados);
        return this;
    }

    public static void atualizaTodas() {
        for (Frame frm : JFrame.getFrames()) {
            if (frm instanceof JanelasIntf) {
                ((JanelasIntf) frm).atualiza();
            }
        }
    }

    public static Boolean temAberta() {
        for (JanTutela janTut : Janelas.pTutelas()) {
            if (janTut.janela().isVisible()) {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpmJanelas = new javax.swing.JPopupMenu();
        jmnJanSelecionar = new javax.swing.JMenu();
        jmiJanSelTodas = new javax.swing.JMenuItem();
        jmiJanSelNenhuma = new javax.swing.JMenuItem();
        jmiJanSelInverter = new javax.swing.JMenuItem();
        jmiJanSelComNome = new javax.swing.JMenuItem();
        jmiJanSelDaClasse = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jmiJanAbrir = new javax.swing.JMenuItem();
        jmiJanExecutar = new javax.swing.JMenuItem();
        jmiJanCascata = new javax.swing.JMenuItem();
        jmiJanLadoALado = new javax.swing.JMenuItem();
        jmiJanMinimizar = new javax.swing.JMenuItem();
        jmiJanNormalizar = new javax.swing.JMenuItem();
        jmiJanMaximizar = new javax.swing.JMenuItem();
        jmiJanFechar = new javax.swing.JMenuItem();
        jspJanelas = new javax.swing.JScrollPane();

        jmnJanSelecionar.setText("Selecionar");

        jmiJanSelTodas.setText("Todas");
        jmiJanSelTodas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiJanSelTodasActionPerformed(evt);
            }
        });
        jmnJanSelecionar.add(jmiJanSelTodas);

        jmiJanSelNenhuma.setText("Nenhuma");
        jmiJanSelNenhuma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiJanSelNenhumaActionPerformed(evt);
            }
        });
        jmnJanSelecionar.add(jmiJanSelNenhuma);

        jmiJanSelInverter.setText("Inverter");
        jmiJanSelInverter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiJanSelInverterActionPerformed(evt);
            }
        });
        jmnJanSelecionar.add(jmiJanSelInverter);

        jmiJanSelComNome.setText("Com Nome");
        jmiJanSelComNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiJanSelComNomeActionPerformed(evt);
            }
        });
        jmnJanSelecionar.add(jmiJanSelComNome);

        jmiJanSelDaClasse.setText("Da Classe");
        jmiJanSelDaClasse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiJanSelDaClasseActionPerformed(evt);
            }
        });
        jmnJanSelecionar.add(jmiJanSelDaClasse);

        jpmJanelas.add(jmnJanSelecionar);
        jpmJanelas.add(jSeparator1);

        jmiJanAbrir.setText("Abrir");
        jmiJanAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiJanAbrirActionPerformed(evt);
            }
        });
        jpmJanelas.add(jmiJanAbrir);

        jmiJanExecutar.setText("Executar");
        jmiJanExecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiJanExecutarActionPerformed(evt);
            }
        });
        jpmJanelas.add(jmiJanExecutar);

        jmiJanCascata.setText("Cascata");
        jmiJanCascata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiJanCascataActionPerformed(evt);
            }
        });
        jpmJanelas.add(jmiJanCascata);

        jmiJanLadoALado.setText("Lado a Lado");
        jmiJanLadoALado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiJanLadoALadoActionPerformed(evt);
            }
        });
        jpmJanelas.add(jmiJanLadoALado);

        jmiJanMinimizar.setText("Minimizar");
        jmiJanMinimizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiJanMinimizarActionPerformed(evt);
            }
        });
        jpmJanelas.add(jmiJanMinimizar);

        jmiJanNormalizar.setText("Normalizar");
        jmiJanNormalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiJanNormalizarActionPerformed(evt);
            }
        });
        jpmJanelas.add(jmiJanNormalizar);

        jmiJanMaximizar.setText("Maximizar");
        jmiJanMaximizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiJanMaximizarActionPerformed(evt);
            }
        });
        jpmJanelas.add(jmiJanMaximizar);

        jmiJanFechar.setText("Fechar");
        jmiJanFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiJanFecharActionPerformed(evt);
            }
        });
        jpmJanelas.add(jmiJanFechar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Janelas");

        jspJanelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jspJanelasMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jspJanelas, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jspJanelas, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jspJanelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jspJanelasMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) {
            jpmJanelas.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jspJanelasMouseClicked

    private void jmiJanSelTodasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiJanSelTodasActionPerformed
        jclJanelas.selectAll();
    }//GEN-LAST:event_jmiJanSelTodasActionPerformed

    private void jmiJanSelNenhumaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiJanSelNenhumaActionPerformed
        jclJanelas.unselectAll();
    }//GEN-LAST:event_jmiJanSelNenhumaActionPerformed

    private void jmiJanSelInverterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiJanSelInverterActionPerformed
        jclJanelas.invertSelectAll();
    }//GEN-LAST:event_jmiJanSelInverterActionPerformed

    private void jmiJanSelComNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiJanSelComNomeActionPerformed
        new FormulaCrs() {
            @Override
            public Boolean executa(FormulaCrs comFormula) {
                atualiza();
                Janelas.mostra(JanelasIntf.this);
                for (JanTutela jic : Janelas.pTutelas()) {
                    if (comFormula.ehContendo()) {
                        if (comFormula.padrao().matcher(jic.janela().getTitle()).find()) {
                            jclJanelas.select(jic);
                        }
                    }
                }
                return true;
            }
        }.mostra("Procurar Janelas");
    }//GEN-LAST:event_jmiJanSelComNomeActionPerformed

    private void jmiJanSelDaClasseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiJanSelDaClasseActionPerformed
        String classeNome = UtlCrs.pergunta("Nome Completo da Classe");
        if (classeNome != null) {
            try {
                Class classe = Class.forName(classeNome);
                atualiza();
                for (JanTutela jic : Janelas.pTutelas()) {
                    if (jic.eh(classe)) {
                        jclJanelas.select(jic);
                    }
                }
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }//GEN-LAST:event_jmiJanSelDaClasseActionPerformed

    private void jmiJanAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiJanAbrirActionPerformed
        for (JanTutela janTut : jclJanelas.getSelectedValuesList()) {
            Janelas.mostra(janTut.janela(), false);
        }
    }//GEN-LAST:event_jmiJanAbrirActionPerformed

    private void jmiJanExecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiJanExecutarActionPerformed
        String nome = UtlCrs.pergunta("Nome do Método");
        if (nome == null) {
            return;
        }
        List<Object> pars = new ArrayList<>();
        int ip = 1;
        Object par = null;
        do {
            par = new PerguntaTpVr("Valor para o Parâmetro " + ip).retorno();
            if (par != null) {
                pars.add(par);
            }
            ip++;
        } while (par != null);
        Object[] comParametros = null;
        if (!pars.isEmpty()) {
            comParametros = pars.toArray();
        }
        for (JanTutela sel : jclJanelas.getSelectedValuesList()) {
            UtlBin.chama(sel.pega(), nome, comParametros);
        }
    }//GEN-LAST:event_jmiJanExecutarActionPerformed

    private void jmiJanCascataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiJanCascataActionPerformed
        int pos = 0;
        for (JanTutela sel : jclJanelas.getSelectedValuesList()) {
            sel.janela().setExtendedState(JFrame.NORMAL);
            sel.setLocation(area.x + 45 + (pos * 45), area.y + 45 + (pos * 45));
            pos++;
        }
    }//GEN-LAST:event_jmiJanCascataActionPerformed

    private void jmiJanLadoALadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiJanLadoALadoActionPerformed
        List<JanTutela> sels = jclJanelas.getSelectedValuesList();
        int ql = 1;
        if (sels.size() >= 3) {
            ql = 2;
        }
        if (sels.size() >= 7) {
            ql = 3;
        }
        int qc = (sels.size() / ql) + (sels.size() % ql == 0 ? 0 : 1);
        int wt = area.width / qc;
        int ht = area.height / ql;
        Dimension dimN = new Dimension(wt, ht);
        int ip = 0;
        int px = area.x;
        int py = area.y;
        for (int il = 0; il < ql; il++) {
            for (int ic = 0; ic < qc; ic++) {
                sels.get(ip).janela().setExtendedState(JFrame.NORMAL);
                sels.get(ip).janela().toFront();
                sels.get(ip).setLocation(px, py);
                if (sels.get(ip).janela().isResizable()) {
                    sels.get(ip).janela().setPreferredSize(dimN);
                    sels.get(ip).janela().setSize(dimN);
                }
                px += wt;
                ip++;
                if (ip > sels.size() - 1) {
                    break;
                }
            }
            px = area.x;
            py += ht;
            if (ip > sels.size() - 1) {
                break;
            }
        }
    }//GEN-LAST:event_jmiJanLadoALadoActionPerformed

    private void jmiJanMinimizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiJanMinimizarActionPerformed
        for (JanTutela jic : jclJanelas.getSelectedValuesList()) {
            jic.janela().setExtendedState(JFrame.ICONIFIED);
        }
    }//GEN-LAST:event_jmiJanMinimizarActionPerformed

    private void jmiJanNormalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiJanNormalizarActionPerformed
        for (JanTutela jic : jclJanelas.getSelectedValuesList()) {
            jic.janela().setExtendedState(JFrame.NORMAL);
        }
    }//GEN-LAST:event_jmiJanNormalizarActionPerformed

    private void jmiJanMaximizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiJanMaximizarActionPerformed
        for (JanTutela jic : jclJanelas.getSelectedValuesList()) {
            jic.janela().setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
    }//GEN-LAST:event_jmiJanMaximizarActionPerformed

    private void jmiJanFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiJanFecharActionPerformed
        for (JanTutela jic : jclJanelas.getSelectedValuesList()) {
            Janelas.fecha(jic.janela());
        }
    }//GEN-LAST:event_jmiJanFecharActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuItem jmiJanAbrir;
    private javax.swing.JMenuItem jmiJanCascata;
    private javax.swing.JMenuItem jmiJanExecutar;
    private javax.swing.JMenuItem jmiJanFechar;
    private javax.swing.JMenuItem jmiJanLadoALado;
    private javax.swing.JMenuItem jmiJanMaximizar;
    private javax.swing.JMenuItem jmiJanMinimizar;
    private javax.swing.JMenuItem jmiJanNormalizar;
    private javax.swing.JMenuItem jmiJanSelComNome;
    private javax.swing.JMenuItem jmiJanSelDaClasse;
    private javax.swing.JMenuItem jmiJanSelInverter;
    private javax.swing.JMenuItem jmiJanSelNenhuma;
    private javax.swing.JMenuItem jmiJanSelTodas;
    private javax.swing.JMenu jmnJanSelecionar;
    private javax.swing.JPopupMenu jpmJanelas;
    private javax.swing.JScrollPane jspJanelas;
    // End of variables declaration//GEN-END:variables

    private class JansAtivada extends WindowAdapter {

        @Override
        public void windowActivated(WindowEvent e) {
            atualiza();
        }
    }

    private class JansClickado extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON3) {
                jpmJanelas.show(e.getComponent(), e.getX(), e.getY());
            }
        }

    }

}
