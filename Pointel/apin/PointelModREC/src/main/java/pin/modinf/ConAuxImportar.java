package pin.modinf;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import javax.swing.AbstractAction;
import pin.libamk.Campos;
import pin.libamk.Cmp;
import pin.libamk.Intf;
import pin.libbas.Dados;
import pin.libbas.Erro;
import pin.libbas.Progresso;
import pin.libetf.TexEtf;
import pin.libjan.Janelas;
import pin.libtex.Marcados;
import pin.libutl.Utl;
import pin.libutl.UtlArq;
import pin.libutl.UtlLog;
import pin.libutl.UtlTex;

public class ConAuxImportar extends javax.swing.JFrame {

    private final Conexao conexao;
    private ConAuxImpOpe.Tp ultimoTp;
    private File selecionado;

    public ConAuxImportar(Conexao naConexao) {
        initComponents();
        conexao = naConexao;
        edtOperacoes.mAoAdicionar(new ActAdicionar());
        edtOperacoes.mAoInserir(new ActInserir());
        edtOperacoes.mAoAlterar(new ActAlterar());
        ultimoTp = ConAuxImpOpe.Tp.PegaCorpoSeparado;
        selecionado = null;
        Janelas.inicia(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlbOrigem = new javax.swing.JLabel();
        jtfOrigem = new javax.swing.JTextField();
        jckSubPastas = new javax.swing.JCheckBox();
        jbtOrigem = new javax.swing.JButton();
        jbtEspiar = new javax.swing.JButton();
        jlbOperacoes = new javax.swing.JLabel();
        edtOperacoes = new pin.libamg.EdtLis();
        jbtNovo = new javax.swing.JButton();
        jbtAbrir = new javax.swing.JButton();
        jbtSalvar = new javax.swing.JButton();
        jbtImportar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Auxiliar Importar");

        jlbOrigem.setText("Origem");

        jckSubPastas.setText("SubPastas");

        jbtOrigem.setText(".");
        jbtOrigem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOrigemActionPerformed(evt);
            }
        });

        jbtEspiar.setMnemonic('E');
        jbtEspiar.setText("Espiar");
        jbtEspiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtEspiarActionPerformed(evt);
            }
        });

        jlbOperacoes.setText("Operações");

        jbtNovo.setMnemonic('N');
        jbtNovo.setText("Novo");
        jbtNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtNovoActionPerformed(evt);
            }
        });

        jbtAbrir.setMnemonic('A');
        jbtAbrir.setText("Abrir");
        jbtAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAbrirActionPerformed(evt);
            }
        });

        jbtSalvar.setMnemonic('S');
        jbtSalvar.setText("Salvar");
        jbtSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtSalvarActionPerformed(evt);
            }
        });

        jbtImportar.setMnemonic('I');
        jbtImportar.setText("Importar");
        jbtImportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtImportarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlbOrigem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jckSubPastas))
                            .addComponent(jtfOrigem))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtOrigem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtEspiar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbOperacoes)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbtNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtAbrir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                        .addComponent(jbtImportar))
                    .addComponent(edtOperacoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbOrigem)
                    .addComponent(jckSubPastas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtOrigem)
                    .addComponent(jbtEspiar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbOperacoes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edtOperacoes, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtNovo)
                    .addComponent(jbtAbrir)
                    .addComponent(jbtSalvar)
                    .addComponent(jbtImportar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void mostra() {
        Janelas.mostra(this);
    }

    private void jbtOrigemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOrigemActionPerformed
        File sel = null;
        if (!jtfOrigem.getText().isEmpty()) {
            sel = new File(jtfOrigem.getText());
        }
        File arq = UtlArq.abreArqOuDir(sel);
        if (arq != null) {
            jtfOrigem.setText(arq.getAbsolutePath());
        }
    }//GEN-LAST:event_jbtOrigemActionPerformed

    private void jbtEspiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtEspiarActionPerformed
        File flImportar = new File(jtfOrigem.getText());
        Boolean subPastas = jckSubPastas.isSelected();
        Progresso prog = new Progresso("SQLite Importar Espiando");
        prog.abre();
        new Thread() {
            private void espia(File oEndereco) {
                if (prog.pausarEdeveParar()) {
                    return;
                }
                prog.bota("Espiando " + oEndereco);
                if (oEndereco.isDirectory()) {
                    for (File arq : oEndereco.listFiles()) {
                        if (prog.pausarEdeveParar()) {
                            return;
                        }
                        if (arq.isDirectory()) {
                            if (subPastas) {
                                espia(arq);
                            }
                        } else {
                            espia(arq);
                        }
                    }
                } else {
                    if (prog.pausarEdeveParar()) {
                        return;
                    }
                    try {
                        TexEtf ttf = new TexEtf();
                        ttf.mostra("Espiando " + oEndereco);
                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(oEndereco)));
                                    String linha;
                                    while ((linha = br.readLine()) != null) {
                                        if (prog.pausarEdeveParar()) {
                                            return;
                                        }
                                        ttf.edt().controlador().botaLinha(linha);
                                    }
                                } catch (Exception ex) {
                                    prog.bota(ex);
                                }
                            }
                        }.start();
                    } catch (Exception ex) {
                        prog.bota(ex);
                    }
                }
            }

            @Override
            public void run() {
                espia(flImportar);
            }
        }.start();
    }//GEN-LAST:event_jbtEspiarActionPerformed

    private void jbtImportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtImportarActionPerformed
        try {
            ConAuxImpExc exc = new ConAuxImpExc(conexao, jtfOrigem.getText(), jckSubPastas.isSelected());
            for (ConAuxImpOpe val : edtOperacoes.pgVlr().pgLis(ConAuxImpOpe.class)) {
                exc.botaOpe(val);
            }
            exc.executa();
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jbtImportarActionPerformed

    private void jbtNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtNovoActionPerformed
        if (Utl.continua()) {
            try {
                jtfOrigem.setText("");
                jckSubPastas.setSelected(false);
                edtOperacoes.limpa();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }//GEN-LAST:event_jbtNovoActionPerformed

    private void jbtAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAbrirActionPerformed
        File fl = UtlArq.abreArq(selecionado, "PointelMigre Auxiliar Importar (*.pai)", "pai");
        if (fl != null) {
            try {
                selecionado = fl;
                String salvo = UtlTex.abre(selecionado);
                jtfOrigem.setText(Marcados.marcado(salvo, "origem"));
                jckSubPastas.setSelected(UtlLog.pega(Marcados.marcado(salvo, "subpastas")));
                edtOperacoes.mdVlr(Marcados.marcado(salvo, "operações"));
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }//GEN-LAST:event_jbtAbrirActionPerformed

    private void jbtSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSalvarActionPerformed
        File fl = UtlArq.salvaArq(selecionado, "PointelMigre Auxiliar Importar (*.pai)", "pai");
        if (fl != null) {
            try {
                selecionado = fl;
                String salvo = Marcados.marca(jtfOrigem.getText(), "origem") + UtlTex.quebra()
                        + Marcados.marca(UtlLog.formata(jckSubPastas.isSelected()), "subpastas") + UtlTex.quebra()
                        + Marcados.marca(edtOperacoes.pgVlr().descreve(), "operações");
                UtlTex.salva(salvo, selecionado);
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }//GEN-LAST:event_jbtSalvarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private pin.libamg.EdtLis edtOperacoes;
    private javax.swing.JButton jbtAbrir;
    private javax.swing.JButton jbtEspiar;
    private javax.swing.JButton jbtImportar;
    private javax.swing.JButton jbtNovo;
    private javax.swing.JButton jbtOrigem;
    private javax.swing.JButton jbtSalvar;
    private javax.swing.JCheckBox jckSubPastas;
    private javax.swing.JLabel jlbOperacoes;
    private javax.swing.JLabel jlbOrigem;
    private javax.swing.JTextField jtfOrigem;
    // End of variables declaration//GEN-END:variables

    private class ActAdicionar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                new NovoIntf() {
                    @Override
                    public void aoConfirmar(Object comOrigem) throws Exception {
                        edtOperacoes.botaItem(pNovo());
                    }
                }.mostra();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    private class ActInserir extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                new NovoIntf() {
                    @Override
                    public void aoConfirmar(Object comOrigem) throws Exception {
                        edtOperacoes.insereItem(pNovo());
                    }
                }.mostra();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    private class ActAlterar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ConAuxImpOpe ope = (ConAuxImpOpe) edtOperacoes.pItem();
                if (ope instanceof ConAuxImpOpePegaCorpoSeparado) {
                    new ConAuxImpOpePegaCorpoSeparadoIntf((ConAuxImpOpePegaCorpoSeparado) ope).mostra();
                } else if (ope instanceof ConAuxImpOpePegaCorpoMarcado) {
                    new ConAuxImpOpePegaCorpoMarcadoIntf((ConAuxImpOpePegaCorpoMarcado) ope).mostra();
                } else if (ope instanceof ConAuxImpOpePegaParteSeparada) {
                    new ConAuxImpOpePegaParteSeparadaIntf((ConAuxImpOpePegaParteSeparada) ope).mostra();
                } else if (ope instanceof ConAuxImpOpePegaParteMarcada) {
                    new ConAuxImpOpePegaParteMarcadaIntf((ConAuxImpOpePegaParteMarcada) ope).mostra();
                } else if (ope instanceof ConAuxImpOpePegaParteFixada) {
                    new ConAuxImpOpePegaParteFixadaIntf((ConAuxImpOpePegaParteFixada) ope).mostra();
                } else if (ope instanceof ConAuxImpOpeInsere) {
                    new ConAuxImpOpeInsereIntf((ConAuxImpOpeInsere) ope).mostra();
                } else {
                    throw new Erro("Tipo da operação não esperada");
                }
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    private class NovoIntf extends Intf {

        public NovoIntf() {
            super(new Campos(
                    new Cmp(1, 1, "tipo", "Tipo", Dados.Tp.Enu).botaOpcoes(ConAuxImpOpe.Tp.class).mVlrInicial(ultimoTp)
            ));
        }

        @Override
        public void preparaEstr() throws Exception {
            super.preparaEstr();
            mTitulo("Nova Operação");
        }

        @Override
        public void preparaIntf() throws Exception {
            super.preparaIntf();
            botaConfirmarECancelar();
        }

        public ConAuxImpOpe.Tp pTipo() throws Exception {
            return (ConAuxImpOpe.Tp) cmps().pgVlr("tipo").pg();
        }

        public Object pNovo() throws Exception {
            ultimoTp = pTipo();
            return ConAuxImpOpe.novo(ultimoTp);

        }

    }

}
