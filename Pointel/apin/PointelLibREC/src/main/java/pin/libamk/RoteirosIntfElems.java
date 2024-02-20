package pin.libamk;

import pin.libjan.Janelas;
import pin.libjan.TrataIntf;
import pin.libutl.Utl;
import pin.libutl.UtlCrs;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

public class RoteirosIntfElems extends javax.swing.JFrame {

    public DefaultComboBoxModel dcmNomes;
    public RoteirosIntf editor;

    public RoteirosIntfElems(RoteirosIntf doEditor, String daClasse, String oElemento) {
        initComponents();
        editor = doEditor;
        jtfClasse.setText(daClasse);
        jtfElemento.setText(oElemento);
        dcmNomes = new DefaultComboBoxModel<>();
        jcbNomes.setModel(dcmNomes);
        Janelas.inicia(this, this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlbNomes = new javax.swing.JLabel();
        jcbNomes = new javax.swing.JComboBox<>();
        jbtCancelar = new javax.swing.JButton();
        jbtOk = new javax.swing.JButton();
        jlbElemento = new javax.swing.JLabel();
        jtfElemento = new javax.swing.JTextField();
        jlbClasse = new javax.swing.JLabel();
        jtfClasse = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Roteiros Elementos");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jlbNomes.setText("Aplicar Sobre");

        jbtCancelar.setText("Cancelar");
        jbtCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCancelarActionPerformed(evt);
            }
        });

        jbtOk.setText("Ok");
        jbtOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOkActionPerformed(evt);
            }
        });

        jlbElemento.setText("Elemento");

        jtfElemento.setEditable(false);

        jlbClasse.setText("Classe");

        jtfClasse.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcbNomes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 154, Short.MAX_VALUE)
                        .addComponent(jbtOk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtCancelar))
                    .addComponent(jtfElemento)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbNomes)
                            .addComponent(jlbElemento)
                            .addComponent(jlbClasse))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jtfClasse))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbClasse)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfClasse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbElemento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfElemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbNomes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbNomes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtCancelar)
                    .addComponent(jbtOk))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        try {
            if (editor.isVisible()) {
                ArrayList<String> nomes = new ArrayList<>();

                String codigo = editor.edtCodigo().pgVlr("").pgCrs();
                codigo = UtlCrs.troca(codigo, "  ", " ");
                String codL = codigo.toLowerCase();

                String procurarPor = "java.type('" + jtfClasse.getText().toLowerCase() + "');";

                Integer pos = codL.indexOf(procurarPor);
                while (pos > -1) {
                    Integer posT = pos;

                    String codMk = codL.substring(0, pos);
                    pos = codMk.lastIndexOf("var");

                    if (pos > -1) {
                        Integer ps = codL.indexOf(" ", pos + 4);
                        Integer pl = codL.indexOf("\n", pos + 4);
                        Integer pf = null;
                        if ((ps > -1) && (pl > -1)) {
                            if (ps < pl) {
                                pf = ps;
                            } else {
                                pf = pl;
                            }
                        } else if (ps > -1) {
                            pf = ps;
                        } else if (pl > -1) {
                            pf = pl;
                        } else {
                            pf = codL.length();
                        }

                        nomes.add(codigo.substring(pos + 4, pf));
                    }

                    pos = codL.indexOf(procurarPor, posT + 15);
                }

                ArrayList<String> nomesSec = new ArrayList<>();

                if (jtfElemento.getText().contains(".")) {
                    for (String nm : nomes) {
                        procurarPor = "new " + nm.toLowerCase();

                        pos = codL.indexOf(procurarPor);
                        while (pos > -1) {
                            Integer posT = pos;

                            String codMk = codL.substring(0, pos);
                            pos = codMk.lastIndexOf("var");

                            if (pos > -1) {
                                Integer ps = codL.indexOf(" ", pos + 4);
                                Integer pl = codL.indexOf("\n", pos + 4);
                                Integer pf = null;
                                if ((ps > -1) && (pl > -1)) {
                                    if (ps < pl) {
                                        pf = ps;
                                    } else {
                                        pf = pl;
                                    }
                                } else if (ps > -1) {
                                    pf = ps;
                                } else if (pl > -1) {
                                    pf = pl;
                                } else {
                                    pf = codL.length();
                                }

                                nomesSec.add(codigo.substring(pos + 4, pf));
                            }

                            pos = codL.indexOf(procurarPor, posT + 5);
                        }
                    }
                }

                dcmNomes.removeAllElements();

                if (jtfElemento.getText().contains(".")) {
                    for (String nm : nomesSec) {
                        dcmNomes.addElement(nm);
                    }
                } else {
                    for (String nm : nomes) {
                        dcmNomes.addElement(nm);
                    }
                }
            } else {
                Janelas.fecha(this);
            }
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_formWindowActivated

    private void jbtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelarActionPerformed
        Janelas.fecha(this);
    }//GEN-LAST:event_jbtCancelarActionPerformed

    private void jbtOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOkActionPerformed
        if (jcbNomes.getSelectedItem() == null) {
            Utl.alerta("Não é possível aplicar sobre nada.");
            return;
        }

        String elemento = jtfElemento.getText();

        if (!elemento.contains("(")) {
            elemento = elemento.substring(elemento.indexOf(" ") + 1);
        }

        if (elemento.startsWith("(")) {
            String nome = UtlCrs.pergunta("Nome");
            if (nome != null) {
                elemento = "var " + nome + " = new " + (String) jcbNomes.getSelectedItem() + elemento;
            } else {
                return;
            }
        } else {
            elemento = (String) jcbNomes.getSelectedItem() + (elemento.startsWith(".") ? "" : ".") + elemento;

            if (!elemento.contains("(")) {
                elemento = elemento + " = null";
            }
        }

        elemento = elemento + ";";

        TrataIntf.botaNoCursor(editor.edtCodigo().controle(), elemento);
        editor.edtCodigo().botaFoco();
        Janelas.fecha(this);
    }//GEN-LAST:event_jbtOkActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtCancelar;
    private javax.swing.JButton jbtOk;
    private javax.swing.JComboBox<String> jcbNomes;
    private javax.swing.JLabel jlbClasse;
    private javax.swing.JLabel jlbElemento;
    private javax.swing.JLabel jlbNomes;
    private javax.swing.JTextField jtfClasse;
    private javax.swing.JTextField jtfElemento;
    // End of variables declaration//GEN-END:variables
}
