package pin.mgr;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import pin.libitr.FormulaCrs;
import pin.libbas.Operacao;
import pin.libbas.Paralelo;
import pin.libjan.Janelas;
import pin.libjan.TrataIntf;
import pin.libpic.Pics;
import pin.libutl.Utl;
import pin.libutl.UtlArq;
import pin.libutl.UtlCrs;
import pin.libutl.UtlInt;
import pin.libutl.UtlTex;
import pin.modinf.BancoTabelas;
import pin.modinf.Conexao;
import pin.modinf.Tabela;
import pin.modrec.XMLReg;

public class MagoImpIntf extends javax.swing.JFrame {

    private PointelMigre pointelMigre;
    private HashMap<ConexaoCfg, Conexao> conexoes;
    private DefaultComboBoxModel dcmOrigem;
    private DefaultComboBoxModel dcmDestino;
    private DefaultListModel dlmDe;
    private DefaultListModel dlmPara;
    private DefaultListModel dlmConstruidos;
    private File arquivo;
    private Boolean modificado;

    public MagoImpIntf(PointelMigre doPointelMigre) {
        initComponents();
        pointelMigre = doPointelMigre;
        conexoes = new HashMap<>();
        dlmDe = new DefaultListModel();
        jlsDe.setModel(dlmDe);
        dlmPara = new DefaultListModel();
        jlsPara.setModel(dlmPara);
        dcmOrigem = new DefaultComboBoxModel();
        jcbOrigem.setModel(dcmOrigem);
        dcmDestino = new DefaultComboBoxModel();
        jcbDestino.setModel(dcmDestino);
        dlmConstruidos = new DefaultListModel();
        jlsConstruidos.setModel(dlmConstruidos);
        for (ConexaoCfg con : pointelMigre.pMigracao().conexoes) {
            dcmOrigem.addElement(con);
            dcmDestino.addElement(con);
        }
        jbtProcurarDe.setIcon(Pics.pega("Find.png"));
        jbtProcurarPara.setIcon(Pics.pega("Find.png"));
        jbtProximo.setIcon(Pics.pega("FindAgain.png"));
        jbtAdicionar.setIcon(Pics.pega("add.png"));
        jbtProcurarConstruidos.setIcon(Pics.pega("Find.png"));
        jbtEditar.setIcon(Pics.pega("pencil.png"));
        jbtExcluir.setIcon(Pics.pega("delete.png"));
        jbtAcima.setIcon(Pics.pega("arrow_up.png"));
        jbtAbaixo.setIcon(Pics.pega("arrow_down.png"));
        jbtAbrir.setIcon(Pics.pega("folder.png"));
        jbtSalvar.setIcon(Pics.pega("disk.png"));
        arquivo = null;
        modificado = false;
        Janelas.inicia(this);
    }

    public MagoImpIntf mostra() {
        Janelas.mostra(this);
        return this;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlbOrigem = new javax.swing.JLabel();
        jcbOrigem = new javax.swing.JComboBox<>();
        jlbDestino = new javax.swing.JLabel();
        jcbDestino = new javax.swing.JComboBox<>();
        jbtProcurarDe = new javax.swing.JButton();
        jbtProcurarPara = new javax.swing.JButton();
        jbtProximo = new javax.swing.JButton();
        jbtAdicionar = new javax.swing.JButton();
        jspDe = new javax.swing.JScrollPane();
        jlsDe = new javax.swing.JList<>();
        jspPara = new javax.swing.JScrollPane();
        jlsPara = new javax.swing.JList<>();
        jlbConstruidos = new javax.swing.JLabel();
        jspConstruidos = new javax.swing.JScrollPane();
        jlsConstruidos = new javax.swing.JList<>();
        jbtProcurarConstruidos = new javax.swing.JButton();
        jbtEditar = new javax.swing.JButton();
        jbtExcluir = new javax.swing.JButton();
        jbtAcima = new javax.swing.JButton();
        jbtAbaixo = new javax.swing.JButton();
        jbtAbrir = new javax.swing.JButton();
        jbtSalvar = new javax.swing.JButton();
        jbtRealizar = new javax.swing.JButton();
        jbtFechar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mago - Importação");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jlbOrigem.setText("Origem");

        jcbOrigem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbOrigemActionPerformed(evt);
            }
        });

        jlbDestino.setText("Destino");

        jcbDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbDestinoActionPerformed(evt);
            }
        });

        jbtProcurarDe.setMnemonic('1');
        jbtProcurarDe.setToolTipText("Procurar De (alt 1)");
        jbtProcurarDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtProcurarDeActionPerformed(evt);
            }
        });

        jbtProcurarPara.setMnemonic('2');
        jbtProcurarPara.setToolTipText("Procurar Para (alt P)");
        jbtProcurarPara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtProcurarParaActionPerformed(evt);
            }
        });

        jbtProximo.setMnemonic('P');
        jbtProximo.setToolTipText("Próximo (alt P)");
        jbtProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtProximoActionPerformed(evt);
            }
        });

        jbtAdicionar.setMnemonic('D');
        jbtAdicionar.setToolTipText("Adicionar (alt D)");
        jbtAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAdicionarActionPerformed(evt);
            }
        });

        jlsDe.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jlsDe.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlsDeValueChanged(evt);
            }
        });
        jspDe.setViewportView(jlsDe);

        jlsPara.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jspPara.setViewportView(jlsPara);

        jlbConstruidos.setText("Construídos");

        jlsConstruidos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jspConstruidos.setViewportView(jlsConstruidos);

        jbtProcurarConstruidos.setMnemonic('3');
        jbtProcurarConstruidos.setToolTipText("Procurar Construídos (alt 3)");
        jbtProcurarConstruidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtProcurarConstruidosActionPerformed(evt);
            }
        });

        jbtEditar.setMnemonic('E');
        jbtEditar.setToolTipText("Editar (alt E)");
        jbtEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtEditarActionPerformed(evt);
            }
        });

        jbtExcluir.setMnemonic('X');
        jbtExcluir.setToolTipText("Excluir (alt X)");
        jbtExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtExcluirActionPerformed(evt);
            }
        });

        jbtAcima.setMnemonic('C');
        jbtAcima.setToolTipText("Para Cima (alt C)");
        jbtAcima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAcimaActionPerformed(evt);
            }
        });

        jbtAbaixo.setMnemonic('B');
        jbtAbaixo.setToolTipText("Para Baixo (alt B)");
        jbtAbaixo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAbaixoActionPerformed(evt);
            }
        });

        jbtAbrir.setMnemonic('A');
        jbtAbrir.setToolTipText("Abrir (alt A)");
        jbtAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAbrirActionPerformed(evt);
            }
        });

        jbtSalvar.setMnemonic('S');
        jbtSalvar.setToolTipText("Salvar (alt S)");
        jbtSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtSalvarActionPerformed(evt);
            }
        });

        jbtRealizar.setMnemonic('R');
        jbtRealizar.setText("Criar");
        jbtRealizar.setToolTipText("Realizar (alt R)");
        jbtRealizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRealizarActionPerformed(evt);
            }
        });

        jbtFechar.setMnemonic('F');
        jbtFechar.setText("Fechar");
        jbtFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtFecharActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbConstruidos)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jspConstruidos)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jbtProcurarConstruidos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jbtEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jbtExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jbtAbaixo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jbtAcima, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jbtAbrir, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jbtSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbtRealizar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jbtFechar))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jspDe, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                                .addComponent(jlbOrigem, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jcbOrigem, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jcbDestino, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jlbDestino, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jspPara, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jbtAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jbtProximo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jbtProcurarDe, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jbtProcurarPara, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbOrigem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbDestino)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspDe)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jbtProcurarDe, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jbtProcurarPara, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jbtProximo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbtAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jspPara, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbConstruidos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbtProcurarConstruidos, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtAcima, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtAbaixo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jspConstruidos, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbtAbrir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtRealizar, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(jbtFechar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public MagoImpIntf salva(MagoImpCst oConstruido) {
        if (oConstruido.indice == null) {
            dlmConstruidos.addElement(oConstruido);
            oConstruido.indice = dlmConstruidos.getSize() - 1;
            modificado = true;
        } else {
            dlmConstruidos.set(oConstruido.indice, oConstruido);
            modificado = true;
        }
        jlsConstruidos.setSelectedIndex(oConstruido.indice);
        TrataIntf.posicionaNaSelecao(jspConstruidos);
        return this;
    }

    private void jbtAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAdicionarActionPerformed
        int deSel = jlsDe.getSelectedIndex();
        int paraSel = jlsPara.getSelectedIndex();
        if (deSel > -1 && paraSel > -1) {
            try {
                String deCon = dcmOrigem.getSelectedItem().toString();
                String paraCon = dcmDestino.getSelectedItem().toString();
                Tabela deTab = (Tabela) dlmDe.get(deSel);
                Tabela paraTab = (Tabela) dlmPara.get(paraSel);
                boolean continua = true;
                for (int ic = 0; ic < dlmConstruidos.getSize(); ic++) {
                    MagoImpCst construido = (MagoImpCst) dlmConstruidos.get(ic);
                    if (Objects.equals(construido.conexaoDe, deCon) && Objects.equals(construido.tabelaDe, deTab)
                            && Objects.equals(construido.conexaoPara, paraCon) && Objects.equals(construido.tabelaPara, paraTab)) {
                        continua = false;
                        break;
                    }
                }
                if (!continua) {
                    continua = Utl.continua("Já Existe essa Importação Construída");
                }
                if (continua) {
                    MagoImpCst construcao = new MagoImpCst(deCon, paraCon, deTab, paraTab);
                    new MagoImpCstIntf(this, construcao).mostra();
                }
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }//GEN-LAST:event_jbtAdicionarActionPerformed

    private void jlsDeValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlsDeValueChanged
        int sel = jlsDe.getSelectedIndex();
        if (sel > -1) {
            String nome = dlmDe.get(sel).toString();
            List<Integer> sims = new ArrayList<>();
            for (int i1 = 0; i1 < dlmPara.getSize(); i1++) {
                sims.add(UtlCrs.diferenciaPalavras(nome, dlmPara.get(i1).toString()));
            }
            int mni = UtlInt.pegaMenor(sims);
            if (mni > -1) {
                jlsPara.setSelectedIndex(mni);
                TrataIntf.posicionaNaSelecao(jlsPara, jspPara);
            }
        }
    }//GEN-LAST:event_jlsDeValueChanged

    private void jbtFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtFecharActionPerformed
        if (modificado) {
            if (Utl.continua("Mago Importação Tem Modificações")) {
                Janelas.fecha(this);
            }
        } else {
            Janelas.fecha(this);
        }
    }//GEN-LAST:event_jbtFecharActionPerformed

    private void jbtEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtEditarActionPerformed
        int sel = jlsConstruidos.getSelectedIndex();
        if (sel > -1) {
            try {
                MagoImpCst construcao = (MagoImpCst) dlmConstruidos.get(sel);
                construcao.indice = sel;
                new MagoImpCstIntf(this, construcao).mostra();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }//GEN-LAST:event_jbtEditarActionPerformed

    private void jbtExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtExcluirActionPerformed
        int sel = jlsConstruidos.getSelectedIndex();
        if (sel > -1 && Utl.continua()) {
            dlmConstruidos.remove(sel);
            while (sel >= dlmConstruidos.getSize()) {
                sel--;
            }
            if (sel < dlmConstruidos.getSize()) {
                jlsConstruidos.setSelectedIndex(sel);
                TrataIntf.posicionaNaSelecao(jlsConstruidos, jspConstruidos);
            }
        }
    }//GEN-LAST:event_jbtExcluirActionPerformed

    private void jbtAbaixoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAbaixoActionPerformed
        int sel = jlsConstruidos.getSelectedIndex();
        if (sel < dlmConstruidos.getSize() - 1) {
            Object aux = dlmConstruidos.get(sel);
            dlmConstruidos.set(sel, dlmConstruidos.get(sel + 1));
            dlmConstruidos.set(sel + 1, aux);
            jlsConstruidos.setSelectedIndex(sel + 1);
            TrataIntf.posicionaNaSelecao(jspConstruidos);
        }
    }//GEN-LAST:event_jbtAbaixoActionPerformed

    private void jbtAcimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAcimaActionPerformed
        int sel = jlsConstruidos.getSelectedIndex();
        if (sel > 0) {
            Object aux = dlmConstruidos.get(sel);
            dlmConstruidos.set(sel, dlmConstruidos.get(sel - 1));
            dlmConstruidos.set(sel - 1, aux);
            jlsConstruidos.setSelectedIndex(sel - 1);
            TrataIntf.posicionaNaSelecao(jspConstruidos);
        }
    }//GEN-LAST:event_jbtAcimaActionPerformed

    private void jbtAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAbrirActionPerformed
        File fl = UtlArq.abreArq(arquivo, "PointelMigre Mago Importação (*.mai)", "mai");
        if (fl != null) {
            new ParAbrir(fl).inicia();
            arquivo = fl;
        }
    }//GEN-LAST:event_jbtAbrirActionPerformed

    private class ParAbrir extends Paralelo {

        private File arquivo;

        public ParAbrir(File oArquivo) {
            super("Abrindo Mago Importação");
            arquivo = oArquivo;
            mostrador();
        }

        @Override
        public void run() {
            try {
                String reg = UtlTex.abre(arquivo);
                MagoImp imp = (MagoImp) XMLReg.novo().pValor(reg);
                dlmConstruidos.clear();
                mudaTamanho(imp.construidos.size());
                for (MagoImpCstReg cst : imp.construidos) {
                    MagoImpCst nov = new MagoImpCst();
                    nov.conexaoDe = cst.conexaoDe;
                    nov.conexaoPara = cst.conexaoPara;
                    ConexaoCfg cfgDe = pointelMigre.pMigracao().pConexao(nov.conexaoDe);
                    ConexaoCfg cfgPara = pointelMigre.pMigracao().pConexao(nov.conexaoPara);
                    Conexao conDe = pegaConexao(cfgDe);
                    Conexao conPara = pegaConexao(cfgPara);
                    nov.tabelaDe = conDe.bancoTabelas().pega(cst.tabelaDe);
                    nov.tabelaPara = conPara.bancoTabelas().pega(cst.tabelaPara);
                    nov.relacoes = cst.relacoes;
                    nov.excluir = cst.excluir;
                    nov.inserir = cst.inserir;
                    nov.atualizar = cst.atualizar;
                    nov.pausarSeErrar = cst.pausarSeErrar;
                    dlmConstruidos.addElement(nov);
                    avanca("Carregado " + nov.toString());
                }
                termina();
                fechar();
            } catch (Exception ex) {
                retorna(ex);
            }
        }
    }

    private void jbtSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSalvarActionPerformed
        File fl = UtlArq.salvaArq(arquivo, "PointelMigre Mago Importação (*.mai)", "mai");
        if (fl != null) {
            try {
                MagoImp imp = new MagoImp();
                for (int ic = 0; ic < dlmConstruidos.getSize(); ic++) {
                    imp.construidos.add(new MagoImpCstReg((MagoImpCst) dlmConstruidos.get(ic)));
                }
                UtlTex.salva(XMLReg.novo().pReg(imp), fl);
                arquivo = fl;
                modificado = false;
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }//GEN-LAST:event_jbtSalvarActionPerformed

    private void jbtRealizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRealizarActionPerformed
        if (Utl.continua()) {
            try {
                for (int ic = 0; ic < dlmConstruidos.getSize(); ic++) {
                    MagoImpCst construido = (MagoImpCst) dlmConstruidos.get(ic);
                    String nome = construido.toString();
                    String fonte = construido.pegaOperacao();
                    Operacao nova = new Operacao("Executar Para Cada", nome, fonte);
                    pointelMigre.bota(nova);
                }
                Janelas.fecha(this);
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }//GEN-LAST:event_jbtRealizarActionPerformed

    private Conexao pegaConexao(ConexaoCfg daConCfg) throws Exception {
        Conexao con = conexoes.get(daConCfg);
        if (con == null) {
            con = daConCfg.conecta();
            conexoes.put(daConCfg, con);
        }
        return con;
    }

    private void carregaTabelas(ConexaoCfg daConCfg, DefaultListModel naLista) {
        try {
            Conexao con = pegaConexao(daConCfg);
            naLista.clear();
            BancoTabelas bt = con.bancoTabelas();
            for (int ib = 0; ib < bt.tamanho(); ib++) {
                naLista.addElement(bt.pega(ib));
            }
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }

    private void jcbOrigemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbOrigemActionPerformed
        ConexaoCfg conCfg = (ConexaoCfg) jcbOrigem.getSelectedItem();
        if (conCfg != null) {
            carregaTabelas(conCfg, dlmDe);
        }
    }//GEN-LAST:event_jcbOrigemActionPerformed

    private void jcbDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbDestinoActionPerformed
        ConexaoCfg conCfg = (ConexaoCfg) jcbDestino.getSelectedItem();
        if (conCfg != null) {
            carregaTabelas(conCfg, dlmPara);
        }
    }//GEN-LAST:event_jcbDestinoActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        for (Conexao con : conexoes.values()) {
            con.desconecta();
        }
    }//GEN-LAST:event_formWindowClosing

    private void jbtProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtProximoActionPerformed
        ConexaoCfg conCfg = (ConexaoCfg) jcbOrigem.getSelectedItem();
        if (conCfg != null) {
            boolean encontrou = false;
            for (int it = jlsDe.getSelectedIndex() + 1; it < dlmDe.getSize(); it++) {
                Tabela tabDe = (Tabela) dlmDe.get(it);
                boolean achou = false;
                for (int ic = 0; ic < dlmConstruidos.getSize(); ic++) {
                    MagoImpCst mic = (MagoImpCst) dlmConstruidos.get(ic);
                    if (Objects.equals(mic.conexaoDe, conCfg.nome) && Objects.equals(mic.tabelaDe, tabDe)) {
                        achou = true;
                        break;
                    }
                }
                if (!achou) {
                    jlsDe.setSelectedValue(tabDe, true);
                    encontrou = true;
                    break;
                }
            }
            if (!encontrou) {
                for (int it = 0; it <= jlsDe.getSelectedIndex(); it++) {
                    Tabela tabDe = (Tabela) dlmDe.get(it);
                    boolean achou = false;
                    for (int ic = 0; ic < dlmConstruidos.getSize(); ic++) {
                        MagoImpCst mic = (MagoImpCst) dlmConstruidos.get(ic);
                        if (Objects.equals(mic.conexaoDe, conCfg.nome) && Objects.equals(mic.tabelaDe, tabDe)) {
                            achou = true;
                            break;
                        }
                    }
                    if (!achou) {
                        jlsDe.setSelectedValue(tabDe, true);
                        encontrou = true;
                        break;
                    }
                }
            }
            if (!encontrou) {
                Utl.alerta("Não Encontrado Nenhum Próximo");
            }
        } else {
            Utl.alerta("Nenhuma Conexão Selecionada");
        }
    }//GEN-LAST:event_jbtProximoActionPerformed

    private void jbtProcurarDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtProcurarDeActionPerformed
        new FormulaCrs() {
            @Override
            public Boolean executa(FormulaCrs comFormula) {
                boolean achou = false;
                for (int id = jlsDe.getSelectedIndex() + 1; id < dlmDe.getSize(); id++) {
                    if (verifica(dlmDe.get(id).toString())) {
                        jlsDe.setSelectedIndex(id);
                        TrataIntf.posicionaNaSelecao(jlsDe, jspDe);
                        achou = true;
                        break;
                    }
                }
                if (!achou) {
                    for (int id = 0; id <= jlsDe.getSelectedIndex(); id++) {
                        if (verifica(dlmDe.get(id).toString())) {
                            jlsDe.setSelectedIndex(id);
                            TrataIntf.posicionaNaSelecao(jlsDe, jspDe);
                            break;
                        }
                    }
                }
                return true;
            }
        }.mostra("Procurar na Origem");
    }//GEN-LAST:event_jbtProcurarDeActionPerformed

    private void jbtProcurarParaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtProcurarParaActionPerformed
        new FormulaCrs() {
            @Override
            public Boolean executa(FormulaCrs comFormula) {
                boolean achou = false;
                for (int id = jlsPara.getSelectedIndex() + 1; id < dlmPara.getSize(); id++) {
                    if (comFormula.verifica(dlmPara.get(id).toString())) {
                        jlsPara.setSelectedIndex(id);
                        TrataIntf.posicionaNaSelecao(jlsPara, jspPara);
                        achou = true;
                        break;
                    }
                }
                if (!achou) {
                    for (int id = 0; id <= jlsPara.getSelectedIndex(); id++) {
                        if (comFormula.verifica(dlmPara.get(id).toString())) {
                            jlsPara.setSelectedIndex(id);
                            TrataIntf.posicionaNaSelecao(jlsPara, jspPara);
                            break;
                        }
                    }
                }
                return true;
            }
        }.mostra("Procurar no Destino");
    }//GEN-LAST:event_jbtProcurarParaActionPerformed

    private void jbtProcurarConstruidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtProcurarConstruidosActionPerformed
        new FormulaCrs() {
            @Override
            public Boolean executa(FormulaCrs comFormula) {
                boolean achou = false;
                for (int id = jlsConstruidos.getSelectedIndex() + 1; id < dlmConstruidos.getSize(); id++) {
                    if (verifica(dlmConstruidos.get(id).toString())) {
                        jlsConstruidos.setSelectedIndex(id);
                        TrataIntf.posicionaNaSelecao(jlsConstruidos, jspConstruidos);
                        achou = true;
                        break;
                    }
                }
                if (!achou) {
                    for (int id = 0; id <= jlsConstruidos.getSelectedIndex(); id++) {
                        if (verifica(dlmConstruidos.get(id).toString())) {
                            jlsConstruidos.setSelectedIndex(id);
                            TrataIntf.posicionaNaSelecao(jlsConstruidos, jspConstruidos);
                            break;
                        }
                    }
                }
                return true;
            }
        }.mostra("Procurar nos Construídos");
    }//GEN-LAST:event_jbtProcurarConstruidosActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtAbaixo;
    private javax.swing.JButton jbtAbrir;
    private javax.swing.JButton jbtAcima;
    private javax.swing.JButton jbtAdicionar;
    private javax.swing.JButton jbtEditar;
    private javax.swing.JButton jbtExcluir;
    private javax.swing.JButton jbtFechar;
    private javax.swing.JButton jbtProcurarConstruidos;
    private javax.swing.JButton jbtProcurarDe;
    private javax.swing.JButton jbtProcurarPara;
    private javax.swing.JButton jbtProximo;
    private javax.swing.JButton jbtRealizar;
    private javax.swing.JButton jbtSalvar;
    private javax.swing.JComboBox<String> jcbDestino;
    private javax.swing.JComboBox<String> jcbOrigem;
    private javax.swing.JLabel jlbConstruidos;
    private javax.swing.JLabel jlbDestino;
    private javax.swing.JLabel jlbOrigem;
    private javax.swing.JList<String> jlsConstruidos;
    private javax.swing.JList<String> jlsDe;
    private javax.swing.JList<String> jlsPara;
    private javax.swing.JScrollPane jspConstruidos;
    private javax.swing.JScrollPane jspDe;
    private javax.swing.JScrollPane jspPara;
    // End of variables declaration//GEN-END:variables
}
