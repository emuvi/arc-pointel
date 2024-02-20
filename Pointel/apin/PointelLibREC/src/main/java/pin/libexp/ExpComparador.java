package pin.libexp;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.swing.table.DefaultTableModel;
import pin.libbas.Paralelo;
import pin.libbas.Progresso;
import pin.libjan.Janelas;
import pin.libutl.Utl;
import pin.libutl.UtlArq;
import pin.libutl.UtlBin;
import pin.libutl.UtlTab;

public class ExpComparador extends javax.swing.JFrame {

    public DefaultTableModel dtmComparados;
    public Boolean parar;
    public Boolean parado;

    public ExpComparador() {
        initComponents();
        dtmComparados = UtlTab.fazTabela(jtbComparados, this, "Esquerda", "Direita", "Resultado");
        Janelas.inicia(this, this);

        parar = false;
        parado = true;

        jbtParar.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpmComparados = new javax.swing.JPopupMenu();
        jmnCopiar = new javax.swing.JMenu();
        jmiCopEsquerdaDireita = new javax.swing.JMenuItem();
        jmiCopDireitaEsquerda = new javax.swing.JMenuItem();
        jmnEspelhar = new javax.swing.JMenu();
        jmiEspEsquerdaDireita = new javax.swing.JMenuItem();
        jmiEspDireitaEsquerda = new javax.swing.JMenuItem();
        jlbEsquerda = new javax.swing.JLabel();
        jtfEsquerda = new javax.swing.JTextField();
        jbtEsquerdaEscolher = new javax.swing.JButton();
        jlbDireita = new javax.swing.JLabel();
        jtfDireita = new javax.swing.JTextField();
        jbtDireitaEscolher = new javax.swing.JButton();
        jbtComparar = new javax.swing.JButton();
        jspComparados = new javax.swing.JScrollPane();
        jtbComparados = new javax.swing.JTable();
        jbtParar = new javax.swing.JButton();
        jckEspelhar = new javax.swing.JCheckBox();

        jmnCopiar.setText("Copiar");

        jmiCopEsquerdaDireita.setText("Esquerda para Direita");
        jmiCopEsquerdaDireita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiCopEsquerdaDireitaActionPerformed(evt);
            }
        });
        jmnCopiar.add(jmiCopEsquerdaDireita);

        jmiCopDireitaEsquerda.setText("Direita para Esquerda");
        jmiCopDireitaEsquerda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiCopDireitaEsquerdaActionPerformed(evt);
            }
        });
        jmnCopiar.add(jmiCopDireitaEsquerda);

        jpmComparados.add(jmnCopiar);

        jmnEspelhar.setText("Espelhar");

        jmiEspEsquerdaDireita.setText("Esquerda para Direita");
        jmiEspEsquerdaDireita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiEspEsquerdaDireitaActionPerformed(evt);
            }
        });
        jmnEspelhar.add(jmiEspEsquerdaDireita);

        jmiEspDireitaEsquerda.setText("Direita para Esquerda");
        jmiEspDireitaEsquerda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiEspDireitaEsquerdaActionPerformed(evt);
            }
        });
        jmnEspelhar.add(jmiEspDireitaEsquerda);

        jpmComparados.add(jmnEspelhar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PointelProk - Arquivos Comparar");

        jlbEsquerda.setText("Esquerda");

        jbtEsquerdaEscolher.setText("...");
        jbtEsquerdaEscolher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtEsquerdaEscolherActionPerformed(evt);
            }
        });

        jlbDireita.setText("Direita");

        jbtDireitaEscolher.setText("...");
        jbtDireitaEscolher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtDireitaEscolherActionPerformed(evt);
            }
        });

        jbtComparar.setText("Comparar");
        jbtComparar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCompararActionPerformed(evt);
            }
        });

        jspComparados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jspComparadosMouseClicked(evt);
            }
        });

        jtbComparados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtbComparados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbComparadosMouseClicked(evt);
            }
        });
        jspComparados.setViewportView(jtbComparados);

        jbtParar.setText("Parar");
        jbtParar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtPararActionPerformed(evt);
            }
        });

        jckEspelhar.setText("Espelhar > ao Terminar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspComparados)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jtfEsquerda, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtEsquerdaEscolher))
                            .addComponent(jlbEsquerda))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jtfDireita, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtDireitaEscolher))
                            .addComponent(jlbDireita))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbtComparar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtParar))
                            .addComponent(jckEspelhar))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbEsquerda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfEsquerda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtEsquerdaEscolher)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbDireita)
                            .addComponent(jckEspelhar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfDireita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtDireitaEscolher)
                            .addComponent(jbtComparar)
                            .addComponent(jbtParar))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspComparados, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtEsquerdaEscolherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtEsquerdaEscolherActionPerformed
        File fl = UtlArq.abreArqOuDir();
        if (fl != null) {
            jtfEsquerda.setText(fl.getAbsolutePath());
        }
    }//GEN-LAST:event_jbtEsquerdaEscolherActionPerformed

    private void jbtDireitaEscolherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtDireitaEscolherActionPerformed
        File fl = UtlArq.abreArqOuDir();
        if (fl != null) {
            jtfDireita.setText(fl.getAbsolutePath());
        }
    }//GEN-LAST:event_jbtDireitaEscolherActionPerformed

    private void jbtCompararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCompararActionPerformed
        parar = true;
        while (!parado) {
            UtlBin.esperaSegundo();
        }

        parar = false;
        parado = false;

        new Paralelo("PointelProk Arquivos Comparar") {
            @Override
            public void run() {
                try {
                    jbtComparar.setText("Comparando...");
                    jbtComparar.setEnabled(false);
                    jbtParar.setVisible(true);

                    while (dtmComparados.getRowCount() > 0) {
                        dtmComparados.removeRow(0);
                    }

                    File flEsquerda = new File(jtfEsquerda.getText());
                    File flDireita = new File(jtfDireita.getText());

                    if (!flEsquerda.exists()) {
                        Utl.alerta("Endereço da Esquerda não Existe.");
                        return;
                    }

                    if (!flDireita.exists()) {
                        Utl.alerta("Endereço da Direita não Existe.");
                        return;
                    }

                    if (flEsquerda.isDirectory()) {
                        if (!flDireita.isDirectory()) {
                            Utl.alerta("Endereços de Tipos Diferentes.");
                            return;
                        }
                    }

                    if (flDireita.isDirectory()) {
                        if (!flEsquerda.isDirectory()) {
                            Utl.alerta("Endereços de Tipos Diferentes.");
                            return;
                        }
                    }

                    boolean diretorios = false;
                    if (flEsquerda.isDirectory()) {
                        diretorios = true;
                    }

                    if (!diretorios) {
                        compararFils(flEsquerda, flDireita);
                    } else {
                        compararDirs(flEsquerda, flDireita);
                    }

                    if (!parar) {
                        if (!jckEspelhar.isSelected()) {
                            Utl.alerta("Terminou de Comparar");
                        } else {
                            espelharEsquerdaDireita();
                        }
                    }
                } catch (Exception ex) {
                    Utl.registra(ex);
                } finally {
                    parado = true;
                    jbtComparar.setText("Comparar");
                    jbtComparar.setEnabled(true);
                    jbtParar.setVisible(false);
                }
            }
        }.start();
    }//GEN-LAST:event_jbtCompararActionPerformed

    public void compararFils(File aEsq, File aDir) {
        try {
            if (!aEsq.exists()) {
                dtmComparados.insertRow(0, new Object[]{aEsq.getAbsolutePath(), aDir.getAbsolutePath(), "Esquerda NÃO Existe"});
            } else if (!aDir.exists()) {
                dtmComparados.insertRow(0, new Object[]{aEsq.getAbsolutePath(), aDir.getAbsolutePath(), "Direita NÃO Existe"});
            } else {
                String md5Esquerda = UtlArq.pegaMD5(Files.readAllBytes(aEsq.toPath()));
                String md5Direita = UtlArq.pegaMD5(Files.readAllBytes(aDir.toPath()));
                if (md5Esquerda.equals(md5Direita)) {
                    dtmComparados.addRow(new Object[]{aEsq.getAbsolutePath(), aDir.getAbsolutePath(), "Iguais"});
                } else {
                    dtmComparados.insertRow(0, new Object[]{aEsq.getAbsolutePath(), aDir.getAbsolutePath(), "Diferentes"});
                }
            }
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }

    public void compararDirs(File aEsq, File aDir) {
        try {
            if (aEsq.exists()) {
                for (File flE : aEsq.listFiles()) {
                    File flEq = new File(aDir.getAbsolutePath() + File.separator + flE.getName());
                    if (flE.isDirectory()) {
                        compararDirs(flE, flEq);
                    } else {
                        compararFils(flE, flEq);
                    }

                    if (parar) {
                        break;
                    }
                }
            } else {
                dtmComparados.insertRow(0, new Object[]{aEsq.getAbsolutePath(), aDir.getAbsolutePath(), "Esquerda NÃO Existe"});
            }

            if (aDir.exists()) {
                for (File flD : aDir.listFiles()) {
                    File flEq = new File(aEsq.getAbsolutePath() + File.separator + flD.getName());
                    if (!flEq.exists()) {
                        dtmComparados.insertRow(0, new Object[]{flEq.getAbsolutePath(), flD.getAbsolutePath(), "Esquerda NÃO Existe"});
                    }
                }
            } else {
                dtmComparados.insertRow(0, new Object[]{aEsq.getAbsolutePath(), aDir.getAbsolutePath(), "Direita NÃO Existe"});
            }
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }

    private void jtbComparadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbComparadosMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) {
            jpmComparados.show(jtbComparados, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jtbComparadosMouseClicked

    private void jspComparadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jspComparadosMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON3) {
            jpmComparados.show(jspComparados, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jspComparadosMouseClicked

    private void jmiCopDireitaEsquerdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCopDireitaEsquerdaActionPerformed
        if (Utl.continua()) {
            new Paralelo("Copiar da Direita Para Esquerda") {
                @Override
                public void run() {
                    try {
                        parar = true;
                        jbtComparar.setEnabled(false);
                        jbtComparar.setText("Copiando");
                        Progresso prog = new Progresso("Copiar da Direita Para Esquerda");
                        prog.abre(0, dtmComparados.getRowCount() - 1);

                        for (int i1 = 0; i1 < dtmComparados.getRowCount(); i1++) {
                            File aEsq = new File((String) dtmComparados.getValueAt(i1, 0));
                            File aDir = new File((String) dtmComparados.getValueAt(i1, 1));
                            String aRes = (String) dtmComparados.getValueAt(i1, 2);
                            prog.bota("Processando " + aDir.getName());

                            boolean copiar = false;
                            if (aRes.equals("Diferentes")) {
                                copiar = true;
                            } else if (aRes.equals("Esquerda NÃO Existe")) {
                                copiar = true;
                            }

                            if (copiar) {
                                try {
                                    Files.createDirectories(aEsq.getParentFile().toPath());
                                    if (aDir.isDirectory()) {
                                        Files.createDirectories(aEsq.toPath());
                                    } else {
                                        Files.copy(aDir.toPath(), aEsq.toPath(), StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING);
                                    }
                                    dtmComparados.setValueAt("Copiado", i1, 2);
                                } catch (Exception ex) {
                                    dtmComparados.setValueAt("Erro", i1, 2);
                                    Utl.registra(ex, true);
                                }
                            }

                            prog.avanca();
                        }

                        prog.bota("Terminou de Copiar");
                    } catch (Exception ex) {
                        Utl.registra(ex);
                    } finally {
                        jbtComparar.setEnabled(true);
                        jbtComparar.setText("Comparar");
                    }
                }
            }.start();
        }
    }//GEN-LAST:event_jmiCopDireitaEsquerdaActionPerformed

    private void jmiCopEsquerdaDireitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCopEsquerdaDireitaActionPerformed
        if (Utl.continua()) {
            new Paralelo("Copiar da Esquerda Para Direita") {
                @Override
                public void run() {
                    try {
                        parar = true;
                        jbtComparar.setEnabled(false);
                        jbtComparar.setText("Copiando");
                        Progresso prog = new Progresso("Copiar da Esquerda Para Direita");
                        prog.abre(0, dtmComparados.getRowCount() - 1);

                        for (int i1 = 0; i1 < dtmComparados.getRowCount(); i1++) {
                            File aEsq = new File((String) dtmComparados.getValueAt(i1, 0));
                            File aDir = new File((String) dtmComparados.getValueAt(i1, 1));
                            String aRes = (String) dtmComparados.getValueAt(i1, 2);
                            prog.bota("Processando " + aEsq.getName());

                            boolean copiar = false;
                            if (aRes.equals("Diferentes")) {
                                copiar = true;
                            } else if (aRes.equals("Direita NÃO Existe")) {
                                copiar = true;
                            }

                            if (copiar) {
                                try {
                                    Files.createDirectories(aDir.getParentFile().toPath());
                                    if (aEsq.isDirectory()) {
                                        Files.createDirectories(aDir.toPath());
                                    } else {
                                        Files.copy(aEsq.toPath(), aDir.toPath(), StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING);
                                    }
                                    dtmComparados.setValueAt("Copiado", i1, 2);
                                } catch (Exception ex) {
                                    dtmComparados.setValueAt("Erro", i1, 2);
                                    Utl.registra(ex, true);
                                }
                            }

                            prog.avanca();
                        }

                        prog.bota("Terminou de Copiar");
                    } catch (Exception ex) {
                        Utl.registra(ex);
                    } finally {
                        jbtComparar.setEnabled(true);
                        jbtComparar.setText("Comparar");
                    }
                }
            }.start();
        }
    }//GEN-LAST:event_jmiCopEsquerdaDireitaActionPerformed

    private void espelharEsquerdaDireita() {
        new Paralelo("Espelhar da Esquerda Para Direita") {
            @Override
            public void run() {
                try {
                    parar = true;
                    jbtComparar.setEnabled(false);
                    jbtComparar.setText("Espelhando");
                    Progresso prog = new Progresso("Espelhar da Esquerda Para Direita");
                    prog.abre(0, dtmComparados.getRowCount() - 1);

                    for (int i1 = 0; i1 < dtmComparados.getRowCount(); i1++) {
                        File aEsq = new File((String) dtmComparados.getValueAt(i1, 0));
                        File aDir = new File((String) dtmComparados.getValueAt(i1, 1));
                        String aRes = (String) dtmComparados.getValueAt(i1, 2);
                        prog.bota("Processando " + aEsq.getName());

                        boolean copiar = false;
                        boolean deletar = false;
                        if (aRes.equals("Diferentes")) {
                            copiar = true;
                        } else if (aRes.equals("Direita NÃO Existe")) {
                            copiar = true;
                        } else if (aRes.equals("Esquerda NÃO Existe")) {
                            deletar = true;
                        }

                        if (copiar) {
                            try {
                                Files.createDirectories(aDir.getParentFile().toPath());
                                if (aEsq.isDirectory()) {
                                    Files.createDirectories(aDir.toPath());
                                } else {
                                    Files.copy(aEsq.toPath(), aDir.toPath(), StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING);
                                }
                                dtmComparados.setValueAt("Copiado", i1, 2);
                            } catch (IOException ex) {
                                dtmComparados.setValueAt("Erro", i1, 2);
                                Utl.registra(ex, true);
                            }
                        } else if (deletar) {
                            try {
                                if (aDir.isDirectory()) {
                                    UtlArq.exclui(aDir);
                                } else {
                                    aDir.delete();
                                }
                                dtmComparados.setValueAt("Deletado", i1, 2);
                            } catch (Exception ex) {
                                dtmComparados.setValueAt("Erro", i1, 2);
                                Utl.registra(ex, true);
                            }
                        }

                        prog.avanca();
                    }

                    prog.bota("Terminou de Espelhar");
                } catch (Exception ex) {
                    Utl.registra(ex);
                } finally {
                    jbtComparar.setEnabled(true);
                    jbtComparar.setText("Comparar");
                }
            }
        }.start();
    }

    private void jmiEspEsquerdaDireitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiEspEsquerdaDireitaActionPerformed
        if (Utl.continua()) {
            espelharEsquerdaDireita();
        }
    }//GEN-LAST:event_jmiEspEsquerdaDireitaActionPerformed

    private void jmiEspDireitaEsquerdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiEspDireitaEsquerdaActionPerformed
        if (Utl.continua()) {
            new Paralelo("Espelhar da Direita Para Esquerda") {
                @Override
                public void run() {
                    try {
                        parar = true;
                        jbtComparar.setEnabled(false);
                        jbtComparar.setText("Espelhando");
                        Progresso prog = new Progresso("Espelhar da Dieira Para Esquerda");
                        prog.abre(0, dtmComparados.getRowCount() - 1);

                        for (int i1 = 0; i1 < dtmComparados.getRowCount(); i1++) {
                            File aEsq = new File((String) dtmComparados.getValueAt(i1, 0));
                            File aDir = new File((String) dtmComparados.getValueAt(i1, 1));
                            String aRes = (String) dtmComparados.getValueAt(i1, 2);
                            prog.bota("Processando " + aDir.getName());

                            boolean copiar = false;
                            boolean deletar = false;
                            if (aRes.equals("Diferentes")) {
                                copiar = true;
                            } else if (aRes.equals("Esquerda NÃO Existe")) {
                                copiar = true;
                            } else if (aRes.equals("Direita NÃO Existe")) {
                                deletar = true;
                            }

                            if (copiar) {
                                try {
                                    Files.createDirectories(aEsq.getParentFile().toPath());
                                    if (aDir.isDirectory()) {
                                        Files.createDirectories(aEsq.toPath());
                                    } else {
                                        Files.copy(aDir.toPath(), aEsq.toPath(), StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING);
                                    }
                                    dtmComparados.setValueAt("Copiado", i1, 2);
                                } catch (Exception ex) {
                                    dtmComparados.setValueAt("Erro", i1, 2);
                                    Utl.registra(ex, true);
                                }
                            } else if (deletar) {
                                try {
                                    if (aEsq.isDirectory()) {
                                        UtlArq.exclui(aEsq);
                                    } else {
                                        aEsq.delete();
                                    }
                                    dtmComparados.setValueAt("Deletado", i1, 2);
                                } catch (Exception ex) {
                                    dtmComparados.setValueAt("Erro", i1, 2);
                                    Utl.registra(ex, true);
                                }
                            }

                            prog.avanca();
                        }

                        prog.bota("Terminou de Espelhar");
                    } catch (Exception ex) {
                        Utl.registra(ex);
                    } finally {
                        jbtComparar.setEnabled(true);
                        jbtComparar.setText("Comparar");
                    }
                }
            }.start();
        }
    }//GEN-LAST:event_jmiEspDireitaEsquerdaActionPerformed

    private void jbtPararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtPararActionPerformed
        parar = true;
    }//GEN-LAST:event_jbtPararActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtComparar;
    private javax.swing.JButton jbtDireitaEscolher;
    private javax.swing.JButton jbtEsquerdaEscolher;
    private javax.swing.JButton jbtParar;
    private javax.swing.JCheckBox jckEspelhar;
    private javax.swing.JLabel jlbDireita;
    private javax.swing.JLabel jlbEsquerda;
    private javax.swing.JMenuItem jmiCopDireitaEsquerda;
    private javax.swing.JMenuItem jmiCopEsquerdaDireita;
    private javax.swing.JMenuItem jmiEspDireitaEsquerda;
    private javax.swing.JMenuItem jmiEspEsquerdaDireita;
    private javax.swing.JMenu jmnCopiar;
    private javax.swing.JMenu jmnEspelhar;
    private javax.swing.JPopupMenu jpmComparados;
    private javax.swing.JScrollPane jspComparados;
    private javax.swing.JTable jtbComparados;
    public javax.swing.JTextField jtfDireita;
    public javax.swing.JTextField jtfEsquerda;
    // End of variables declaration//GEN-END:variables
}
