package pin.libamk;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import pin.libjan.Janelas;
import pin.libjan.TrataIntf;
import pin.libutl.Utl;
import pin.libutl.UtlBin;
import pin.libutl.UtlCrs;
import pin.libutl.UtlTex;

public class RoteirosIntfAux extends javax.swing.JFrame {

    public RoteirosIntf editor;
    public DefaultComboBoxModel dcmProcuras;
    public DefaultListModel dlmPacotes;
    public DefaultComboBoxModel dcmClasses;
    public DefaultListModel dlmElementos;

    public RoteirosIntfAux(RoteirosIntf doEditor) {
        initComponents();
        editor = doEditor;
        jcbClasses.getEditor().getEditorComponent().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent fe) {
                jcbClassesFocusLost(fe);
            }
        });
        dcmProcuras = new DefaultComboBoxModel();
        dlmPacotes = new DefaultListModel();
        dcmClasses = new DefaultComboBoxModel();
        dlmElementos = new DefaultListModel();
        jcbProcurar.setModel(dcmProcuras);
        jlsPacotes.setModel(dlmPacotes);
        jcbClasses.setModel(dcmClasses);
        jlsElementos.setModel(dlmElementos);
        try {
            List<String> pacotes = pegaPacotes();
            for (String pacote : pacotes) {
                dlmPacotes.addElement(pacote);
            }
        } catch (Exception ex) {
            Utl.registra(ex);
        }
        Janelas.inicia(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlbPacotes = new javax.swing.JLabel();
        jspPacotes = new javax.swing.JScrollPane();
        jlsPacotes = new javax.swing.JList<>();
        jtfPacotes = new javax.swing.JTextField();
        jbtPacotes = new javax.swing.JButton();
        jlbClasses = new javax.swing.JLabel();
        jcbClasses = new javax.swing.JComboBox<>();
        jbtClassesAdd = new javax.swing.JButton();
        jlbElementos = new javax.swing.JLabel();
        jspElementos = new javax.swing.JScrollPane();
        jlsElementos = new javax.swing.JList<>();
        jtfElementos = new javax.swing.JTextField();
        jbtElementos = new javax.swing.JButton();
        jbtElementosAdd = new javax.swing.JButton();
        jbtAjuda = new javax.swing.JButton();
        jlbProcurar = new javax.swing.JLabel();
        jbtProcurar = new javax.swing.JButton();
        jcbProcurar = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Roteiros Auxiliar");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jlbPacotes.setText("Pacotes");

        jlsPacotes.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlsPacotesValueChanged(evt);
            }
        });
        jspPacotes.setViewportView(jlsPacotes);

        jbtPacotes.setText("...");
        jbtPacotes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtPacotesActionPerformed(evt);
            }
        });

        jlbClasses.setText("Classes");

        jcbClasses.setEditable(true);
        jcbClasses.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jcbClassesFocusLost(evt);
            }
        });
        jcbClasses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbClassesActionPerformed(evt);
            }
        });

        jbtClassesAdd.setText("+");
        jbtClassesAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtClassesAddActionPerformed(evt);
            }
        });

        jlbElementos.setText("Elementos");

        jspElementos.setViewportView(jlsElementos);

        jbtElementos.setText("...");
        jbtElementos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtElementosActionPerformed(evt);
            }
        });

        jbtElementosAdd.setText("+");
        jbtElementosAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtElementosAddActionPerformed(evt);
            }
        });

        jbtAjuda.setText("?");
        jbtAjuda.setFocusable(false);

        jlbProcurar.setText("Procurar Classe");

        jbtProcurar.setText("...");
        jbtProcurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtProcurarActionPerformed(evt);
            }
        });

        jcbProcurar.setEditable(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jtfPacotes)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtPacotes)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtAjuda))
                            .addComponent(jspPacotes, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbPacotes))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jspElementos, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jcbClasses, 0, 207, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtClassesAdd))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbClasses)
                                    .addComponent(jlbElementos))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jtfElementos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtElementos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtElementosAdd))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbProcurar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbProcurar, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtProcurar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbProcurar)
                    .addComponent(jbtProcurar)
                    .addComponent(jcbProcurar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbPacotes)
                    .addComponent(jlbClasses))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspPacotes)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcbClasses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtClassesAdd))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlbElementos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jspElementos, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfPacotes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtPacotes)
                    .addComponent(jtfElementos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtElementos)
                    .addComponent(jbtElementosAdd)
                    .addComponent(jbtAjuda))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public RoteirosIntfAux mostra() {
        Janelas.mostra(this);
        return this;
    }

    private void jbtPacotesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtPacotesActionPerformed
        Boolean achou = false;
        for (int i1 = jlsPacotes.getSelectedIndex() + 1; i1 < dlmPacotes.size() - 1; i1++) {
            String pacote = (String) dlmPacotes.getElementAt(i1);
            if (pacote.contains(jtfPacotes.getText())) {
                jlsPacotes.setSelectedIndex(i1);
                jlsPacotes.ensureIndexIsVisible(i1);
                achou = true;
                break;
            }
        }
        if (!achou) {
            for (int i1 = 0; i1 < dlmPacotes.size() - 1; i1++) {
                String pacote = (String) dlmPacotes.getElementAt(i1);
                if (pacote.contains(jtfPacotes.getText())) {
                    jlsPacotes.setSelectedIndex(i1);
                    jlsPacotes.ensureIndexIsVisible(i1);
                    break;
                }
            }
        }
    }//GEN-LAST:event_jbtPacotesActionPerformed

    private void jlsPacotesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlsPacotesValueChanged
        String pacote = jlsPacotes.getSelectedValue();
        dcmClasses.removeAllElements();
        jcbClasses.setSelectedItem(null);
        if (pacote != null) {
            try {
                int corte = pacote.length() + 1;
                List<String> classes = pegaClasses();
                for (String classe : classes) {
                    if (classe.startsWith(pacote)) {
                        String nome = classe.substring(corte);
                        if (!nome.contains(".")) {
                            dcmClasses.addElement(nome);
                        }
                    }
                }
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }//GEN-LAST:event_jlsPacotesValueChanged

    private void jbtProcurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtProcurarActionPerformed
        try {
            Class classe = Class.forName((String) jcbProcurar.getSelectedItem());
            String pacote = classe.getPackage().getName();
            Integer ipc = dlmPacotes.indexOf(pacote);
            if (ipc == -1) {
                dlmPacotes.addElement(pacote);
                ipc = dlmPacotes.size() - 1;
            }
            jlsPacotes.setSelectedIndex(ipc);
            jlsPacotes.ensureIndexIsVisible(ipc);
            jcbClasses.setSelectedItem(classe.getSimpleName());
            if (dcmProcuras.getIndexOf(jcbProcurar.getSelectedItem()) == -1) {
                dcmProcuras.addElement((String) jcbProcurar.getSelectedItem());
            }
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jbtProcurarActionPerformed

    private void jcbClassesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jcbClassesFocusLost
        dlmElementos.removeAllElements();
        if (jlsPacotes.getSelectedValue() != null) {
            if (jcbClasses.getSelectedItem() != null) {
                try {
                    String nome = jlsPacotes.getSelectedValue() + "." + (String) jcbClasses.getSelectedItem();
                    Class classe = Class.forName(nome);
                    ArrayList<String> elementos = new ArrayList<>();
                    for (java.lang.reflect.Constructor c : classe.getConstructors()) {
                        String elemento = "(";
                        for (java.lang.reflect.Parameter p : c.getParameters()) {
                            elemento += p.getType().getSimpleName() + " " + p.getName() + ", ";
                        }
                        if (elemento.endsWith(", ")) {
                            elemento = UtlCrs.corta(elemento, 2);
                        }
                        elemento += ")";
                        elementos.add(elemento);
                    }
                    for (java.lang.reflect.Method m : classe.getMethods()) {
                        String elemento = "";
                        if (!java.lang.reflect.Modifier.isStatic(m.getModifiers())) {
                            elemento = ".";
                        }
                        elemento += m.getName() + "(";
                        for (java.lang.reflect.Parameter p : m.getParameters()) {
                            elemento += p.getType().getSimpleName() + " " + p.getName() + ", ";
                        }
                        if (elemento.endsWith(", ")) {
                            elemento = UtlCrs.corta(elemento, 2);
                        }
                        elemento += ")";
                        elementos.add(elemento);
                    }
                    for (java.lang.reflect.Field f : classe.getFields()) {
                        Boolean stc = java.lang.reflect.Modifier.isStatic(f.getModifiers());
                        String elemento = f.getType().getSimpleName() + " " + (stc ? "" : ".") + f.getName();
                        elementos.add(elemento);
                    }
                    Collections.sort(elementos);
                    for (String e : elementos) {
                        dlmElementos.addElement(e);
                    }
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        }
    }//GEN-LAST:event_jcbClassesFocusLost

    private void jbtClassesAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtClassesAddActionPerformed
        String nome = UtlCrs.pergunta("Nome");
        if (nome != null) {
            String pacla = jlsPacotes.getSelectedValue() + "." + ((String) jcbClasses.getSelectedItem());
            TrataIntf.botaNoCursor(editor.edtCodigo().controle(), "var " + nome + " = Java.type('" + pacla + "');");
            editor.edtCodigo().botaFoco();
        }
    }//GEN-LAST:event_jbtClassesAddActionPerformed

    private void jbtElementosAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtElementosAddActionPerformed
        String elemento = jlsElementos.getSelectedValue();
        if (elemento != null) {
            if (!elemento.isEmpty()) {
                String pacla = jlsPacotes.getSelectedValue() + "." + ((String) jcbClasses.getSelectedItem());
                new RoteirosIntfElems(editor, pacla, elemento).setVisible(true);
            }
        }
    }//GEN-LAST:event_jbtElementosAddActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if (!editor.isVisible()) {
            Janelas.fecha(this);
        }
    }//GEN-LAST:event_formWindowActivated

    private void jcbClassesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbClassesActionPerformed
        jcbClassesFocusLost(null);
    }//GEN-LAST:event_jcbClassesActionPerformed

    private void jbtElementosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtElementosActionPerformed
        Boolean achou = false;
        for (int i1 = jlsElementos.getSelectedIndex() + 1; i1 < dlmElementos.size() - 1; i1++) {
            String pacote = (String) dlmElementos.getElementAt(i1);
            if (pacote.contains(jtfElementos.getText())) {
                jlsElementos.setSelectedIndex(i1);
                jlsElementos.ensureIndexIsVisible(i1);
                achou = true;
                break;
            }
        }
        if (!achou) {
            for (int i1 = 0; i1 < dlmElementos.size() - 1; i1++) {
                String pacote = (String) dlmElementos.getElementAt(i1);
                if (pacote.contains(jtfElementos.getText())) {
                    jlsElementos.setSelectedIndex(i1);
                    jlsElementos.ensureIndexIsVisible(i1);
                    break;
                }
            }
        }
    }//GEN-LAST:event_jbtElementosActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtAjuda;
    private javax.swing.JButton jbtClassesAdd;
    private javax.swing.JButton jbtElementos;
    private javax.swing.JButton jbtElementosAdd;
    private javax.swing.JButton jbtPacotes;
    private javax.swing.JButton jbtProcurar;
    private javax.swing.JComboBox<String> jcbClasses;
    private javax.swing.JComboBox<String> jcbProcurar;
    private javax.swing.JLabel jlbClasses;
    private javax.swing.JLabel jlbElementos;
    private javax.swing.JLabel jlbPacotes;
    private javax.swing.JLabel jlbProcurar;
    private javax.swing.JList<String> jlsElementos;
    private javax.swing.JList<String> jlsPacotes;
    private javax.swing.JScrollPane jspElementos;
    private javax.swing.JScrollPane jspPacotes;
    private javax.swing.JTextField jtfElementos;
    private javax.swing.JTextField jtfPacotes;
    // End of variables declaration//GEN-END:variables

    private static List<String> classes = null;
    private static List<String> pacotes = null;

    public static List<String> pegaClasses() throws Exception {
        if (classes == null) {
            classes = UtlTex.pegaLinhas(RoteirosIntfAux.class.getResource("classes.dat"));
            UtlBin.botaClasses(classes);
            Collections.sort(classes);
        }
        return classes;
    }

    public static List<String> pegaPacotes() throws Exception {
        if (pacotes == null) {
            pacotes = UtlTex.pegaLinhas(RoteirosIntfAux.class.getResource("pacotes.dat"));
            UtlBin.botaPacotes(pacotes);
            Collections.sort(pacotes);
        }
        return pacotes;
    }

}
