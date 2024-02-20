package pin.modamk;

import java.util.ArrayList;
import javax.swing.BoxLayout;
import pin.libjan.Janelas;
import pin.libutl.Utl;
import pin.libutl.UtlCrs;
import pin.libvlr.Vlrs;

public class CadProcurar extends javax.swing.JFrame {

    public Cadastro cadastro;
    public ArrayList<CadProcurarCmp> campos;
    private CadProcurarCmp lastCampo;

    public String condicao;
    public ArrayList<Object> parametros;

    public String ordem;
    public String ordemTitulos;

    public CadProcurar(Cadastro aCadastro) throws Exception {
        initComponents();
        cadastro = aCadastro;
        setTitle(cadastro.titulo + " - Procurar");

        campos = new ArrayList<>();
        jpnCampos.setLayout(new BoxLayout(jpnCampos, BoxLayout.Y_AXIS));

        lastCampo = new CadProcurarCmp(this, true);
        campos.add(lastCampo);
        camposRefazer();

        condicao = "";
        parametros = new ArrayList<Object>();

        ordem = "";
        ordemTitulos = "";

        getRootPane().setDefaultButton(jbtProcurar);
        Janelas.inicia(this, this);
    }

    public void camposAdicionar(CadProcurarCmp depoisDoCampo) {
        CadProcurarCmp novo = new CadProcurarCmp(this);
        camposAdicionar(depoisDoCampo, novo);
    }

    public void camposAdicionar(CadProcurarCmp depoisDoCampo, CadProcurarCmp oCampo) {
        int idx = campos.size();
        if (depoisDoCampo != null) {
            idx = campos.indexOf(depoisDoCampo) + 1;
        }
        lastCampo = oCampo;
        campos.add(idx, lastCampo);
        camposRefazer();
    }

    public void camposRemover(CadProcurarCmp oCampo) {
        jpnCampos.remove(oCampo);
        campos.remove(oCampo);
        if (campos.isEmpty()) {
            campos.add(new CadProcurarCmp(this));
        }
        camposRefazer();
    }

    public void camposRefazer() {
        for (CadProcurarCmp campo : campos) {
            jpnCampos.remove(campo);
        }
        for (CadProcurarCmp campo : campos) {
            jpnCampos.add(campo);
        }
        if (campos.contains(lastCampo)) {
            lastCampo.jcbCampo.requestFocus();
        }
        pack();
    }

    public String pegaCondicao(Vlrs comParametros) {
        for (Object vpar : parametros) {
            comParametros.novo(vpar);
        }
        return condicao;
    }

    public String pegaCondicao(String comComeco, Vlrs comParametros) {
        String retorno = pegaCondicao(comParametros);

        if (!retorno.isEmpty()) {
            retorno = (comComeco.isEmpty() ? "" : comComeco + " ") + retorno;
        }

        return " " + retorno + " ";
    }

    public String pegaCondicao(String comComeco, String comTermino, Vlrs comParametros) {
        String retorno = pegaCondicao(comParametros);
        if (!retorno.isEmpty()) {
            retorno = (comComeco.isEmpty() ? "" : comComeco + " ") + retorno;
        }
        if (!comTermino.isEmpty()) {
            retorno = retorno + (retorno.isEmpty() ? comComeco : " AND ") + comTermino;
        }
        return " " + retorno + " ";
    }

    public void fazCondicao() {
        condicao = "";
        parametros.clear();
        String lastMais = "";
        int ic = 1;
        for (CadProcurarCmp cmp : campos) {
            if (cmp.temCondicao()) {
                condicao = condicao + cmp.pegaCondicao(ic);
                if (cmp.temParametro()) {
                    parametros.add(cmp.pegaParametro());
                    ic++;
                }
                lastMais = cmp.pegaMais();
            }
        }
        if (!condicao.isEmpty()) {
            condicao = " WHERE " + condicao;
            condicao = UtlCrs.corta(condicao, lastMais.length());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnCampos = new javax.swing.JPanel();
        jpnBotoes = new javax.swing.JPanel();
        jbtOrdenar = new javax.swing.JButton();
        jbtProcurar = new javax.swing.JButton();
        jbtCancelar = new javax.swing.JButton();
        jbtLimpar = new javax.swing.JButton();

        setTitle("Procurar");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        javax.swing.GroupLayout jpnCamposLayout = new javax.swing.GroupLayout(jpnCampos);
        jpnCampos.setLayout(jpnCamposLayout);
        jpnCamposLayout.setHorizontalGroup(
            jpnCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpnCamposLayout.setVerticalGroup(
            jpnCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 62, Short.MAX_VALUE)
        );

        jbtOrdenar.setMnemonic('O');
        jbtOrdenar.setText("Ordenar");
        jbtOrdenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOrdenarActionPerformed(evt);
            }
        });

        jbtProcurar.setMnemonic('P');
        jbtProcurar.setText("Procurar");
        jbtProcurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtProcurarActionPerformed(evt);
            }
        });

        jbtCancelar.setMnemonic('C');
        jbtCancelar.setText("Cancelar");
        jbtCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCancelarActionPerformed(evt);
            }
        });

        jbtLimpar.setMnemonic('L');
        jbtLimpar.setText("Limpar");
        jbtLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtLimparActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnBotoesLayout = new javax.swing.GroupLayout(jpnBotoes);
        jpnBotoes.setLayout(jpnBotoesLayout);
        jpnBotoesLayout.setHorizontalGroup(
            jpnBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnBotoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbtOrdenar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 304, Short.MAX_VALUE)
                .addComponent(jbtProcurar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtLimpar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtCancelar)
                .addContainerGap())
        );
        jpnBotoesLayout.setVerticalGroup(
            jpnBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnBotoesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpnBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtCancelar)
                    .addComponent(jbtProcurar)
                    .addComponent(jbtOrdenar)
                    .addComponent(jbtLimpar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnCampos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnBotoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpnCampos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtOrdenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOrdenarActionPerformed
        new CadOrdenar(this).setVisible(true);
    }//GEN-LAST:event_jbtOrdenarActionPerformed

    public void realiza() throws Exception {
        fazCondicao();
        cadastro.pegaProcura(this);
    }

    private void jbtProcurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtProcurarActionPerformed
        try {
            realiza();
            Janelas.fecha(this);
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jbtProcurarActionPerformed

    private void jbtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelarActionPerformed
        Janelas.fecha(this);
    }//GEN-LAST:event_jbtCancelarActionPerformed

    public void limpa() {
        for (int i1 = campos.size() - 1; i1 >= 0; i1--) {
            camposRemover(campos.get(i1));
        }
    }

    public void limpaCompletamente() {
        for (int i1 = campos.size() - 1; i1 >= 0; i1--) {
            jpnCampos.remove(campos.get(i1));
        }
        campos.clear();
        pack();
    }

    private void jbtLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtLimparActionPerformed
        limpa();
    }//GEN-LAST:event_jbtLimparActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if (campos.size() > 0) {
            campos.get(0).jcbCampo.requestFocus();
        }
    }//GEN-LAST:event_formWindowActivated

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtCancelar;
    private javax.swing.JButton jbtLimpar;
    private javax.swing.JButton jbtOrdenar;
    private javax.swing.JButton jbtProcurar;
    private javax.swing.JPanel jpnBotoes;
    private javax.swing.JPanel jpnCampos;
    // End of variables declaration//GEN-END:variables
}
