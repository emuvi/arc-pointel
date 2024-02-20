package pin.jrb;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import pin.Pointel;
import pin.libamk.ComandosIntf;
import pin.libamk.ModulosIntf;
import pin.libamk.RoteirosIntf;
import pin.libbas.Comando;
import pin.libbas.Modulo;
import pin.libbas.Paralelos;
import pin.libbas.Roteiro;
import pin.libjan.Janelas;
import pin.libjan.TabCarregador;
import pin.libjan.TrataIntf;
import pin.libutl.Utl;
import pin.libutl.UtlBin;
import pin.modpim.Pims;

public class ParalelosIntf extends javax.swing.JFrame {

    private TabCarregador carregador;

    public ParalelosIntf() {
        initComponents();
        setIconImage(Pims.pega("Paralelos.png").getImage());
        edtParalelos.mEditavel(false)
                .controlador().botaColunas("Id", "Tipo", "Nome", "Prioridade")
                .tiraMostraNumeroLinhas();
        carregador = new TabCarregador(edtParalelos.controle());
        edtParalelos.pPopMenu().limpa();
        edtParalelos.pPopMenu().bota("Atualizar", new ActAtualizar());
        edtParalelos.pPopMenu().bota("Prioridade");
        edtParalelos.pPopMenu().bota("Prioridade", "Normal", new ActPriNormal());
        edtParalelos.pPopMenu().bota("Prioridade", "Máxima", new ActPriMaxima());
        edtParalelos.pPopMenu().bota("Prioridade", "Mínima", new ActPriMinima());
        edtParalelos.pPopMenu().bota("Parar", new ActParar());
        Janelas.inicia(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlbTipo = new javax.swing.JLabel();
        jcbTipo = new javax.swing.JComboBox<>();
        jbtRoteiros = new javax.swing.JButton();
        jbtComandos = new javax.swing.JButton();
        jbtModulos = new javax.swing.JButton();
        edtParalelos = new pin.libamg.EdtPla();
        jbtAtualizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Paralelos");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jlbTipo.setText("Tipo");

        jcbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Roteiros", "Comandos", "Módulos" }));
        jcbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbTipoActionPerformed(evt);
            }
        });

        jbtRoteiros.setMnemonic('R');
        jbtRoteiros.setText("Roteiros");
        jbtRoteiros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRoteirosActionPerformed(evt);
            }
        });

        jbtComandos.setMnemonic('C');
        jbtComandos.setText("Comandos");
        jbtComandos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtComandosActionPerformed(evt);
            }
        });

        jbtModulos.setText("Módulos");
        jbtModulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtModulosActionPerformed(evt);
            }
        });

        jbtAtualizar.setText("Atualizar");
        jbtAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAtualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(edtParalelos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbTipo)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jcbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtAtualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 193, Short.MAX_VALUE)
                        .addComponent(jbtRoteiros)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtComandos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtModulos)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(edtParalelos, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbTipo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtComandos)
                    .addComponent(jbtRoteiros)
                    .addComponent(jbtModulos)
                    .addComponent(jbtAtualizar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void atualizar() {
        String tipo = (String) jcbTipo.getSelectedItem();
        if (tipo == null) {
            tipo = "Todos";
        }
        switch (tipo) {
            case "Todos":
                carregador.carrega(Paralelos.pegaTab());
                break;
            case "Roteiros":
                carregador.carrega(Paralelos.pegaTab(Roteiro.class));
                break;
            case "Comandos":
                carregador.carrega(Paralelos.pegaTab(Comando.class));
                break;
            case "Módulos":
                carregador.carrega(Paralelos.pegaTab(Modulo.class));
                break;
        }
    }

    private void prioridadeNormal() {
        if (edtParalelos.controlador().temSelecionado()) {
            Long id = edtParalelos.controlador().pegaLinhaSelecionadaVlrs()[0].pgIntLon();
            if (id != null) {
                Paralelos.pega(id).setPriority(Thread.NORM_PRIORITY);
                new Thread("Atualizar Paralelos") {
                    @Override
                    public void run() {
                        UtlBin.esperaSegundo();
                        atualizar();
                    }
                }.start();
            }
        }
    }

    private void prioridadeMaxima() {
        if (edtParalelos.controlador().temSelecionado()) {
            Long id = edtParalelos.controlador().pegaLinhaSelecionadaVlrs()[0].pgIntLon();
            if (id != null) {
                Paralelos.pega(id).setPriority(Thread.MAX_PRIORITY);
                new Thread("Atualizar Paralelos") {
                    @Override
                    public void run() {
                        UtlBin.esperaSegundo();
                        atualizar();
                    }
                }.start();
            }
        }
    }

    private void prioridadeMinima() {
        if (edtParalelos.controlador().temSelecionado()) {
            Long id = edtParalelos.controlador().pegaLinhaSelecionadaVlrs()[0].pgIntLon();
            if (id != null) {
                Paralelos.pega(id).setPriority(Thread.MIN_PRIORITY);
                new Thread("Atualizar Paralelos") {
                    @Override
                    public void run() {
                        UtlBin.esperaSegundo();
                        atualizar();
                    }
                }.start();
            }
        }
    }

    private void parar() {
        if (edtParalelos.controlador().temSelecionado()) {
            Long id = edtParalelos.controlador().pegaLinhaSelecionadaVlrs()[0].pgIntLon();
            if (id != null) {
                Paralelos.parar(id);
                new Thread("Atualizar Paralelos") {
                    @Override
                    public void run() {
                        UtlBin.esperaSegundo();
                        atualizar();
                    }
                }.start();
            }
        }
    }

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if (!Pointel.mainJarbs.confereLogado()) {
            Janelas.fecha(this);
        } else {
            TrataIntf.garanteFoco(this);
            atualizar();
        }
    }//GEN-LAST:event_formWindowActivated

    private void jbtRoteirosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRoteirosActionPerformed
        new RoteirosIntf().mostra();
    }//GEN-LAST:event_jbtRoteirosActionPerformed

    private void jbtComandosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtComandosActionPerformed
        try {
            new ComandosIntf().mostra();
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jbtComandosActionPerformed

    private void jcbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbTipoActionPerformed
        atualizar();
    }//GEN-LAST:event_jcbTipoActionPerformed

    private void jbtModulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtModulosActionPerformed
        try {
            new ModulosIntf().mostra();
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jbtModulosActionPerformed

    private void jbtAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAtualizarActionPerformed
        atualizar();
    }//GEN-LAST:event_jbtAtualizarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private pin.libamg.EdtPla edtParalelos;
    private javax.swing.JButton jbtAtualizar;
    private javax.swing.JButton jbtComandos;
    private javax.swing.JButton jbtModulos;
    private javax.swing.JButton jbtRoteiros;
    private javax.swing.JComboBox<String> jcbTipo;
    private javax.swing.JLabel jlbTipo;
    // End of variables declaration//GEN-END:variables

    public class ActAtualizar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            atualizar();
        }

    }

    public class ActPriNormal extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            prioridadeNormal();
        }
    }

    public class ActPriMaxima extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            prioridadeMaxima();
        }
    }

    public class ActPriMinima extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            prioridadeMinima();
        }
    }

    public class ActParar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            parar();
        }
    }
}
