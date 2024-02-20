package pin.mgr;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import pin.libbas.Configs;
import pin.libbas.Globais;
import pin.libjan.Janelas;
import pin.libutl.Utl;
import pin.libutl.UtlCrs;
import pin.libutl.UtlInt;
import pin.modinf.TabCampo;
import pin.modinf.TabRelacao;
import pin.modinf.Tabela;

public class MagoImpCstIntf extends javax.swing.JFrame {

    private Configs cfgs;
    private MagoImpIntf magoImp;
    private MagoImpCst construcao;
    private Tabela deTab;
    private Tabela paraTab;
    private String deNome;
    private String paraNome;
    private List<CstCampo> camposDe;
    private List<CstCampo> camposPara;
    private CstRelacoes camposRels;

    public MagoImpCstIntf(MagoImpIntf doMagoImp, MagoImpCst comConstrucao) throws Exception {
        initComponents();
        cfgs = (Configs) Globais.pega("mainConf");
        magoImp = doMagoImp;
        construcao = comConstrucao;
        deTab = construcao.tabelaDe;
        paraTab = construcao.tabelaPara;
        deNome = deTab.pEsquemaENome();
        paraNome = paraTab.pEsquemaENome();
        jlbTitulo.setText(construcao.toString());
        camposDe = new ArrayList<>();
        camposPara = new ArrayList<>();
        for (TabCampo cmp : deTab.pCampos()) {
            camposDe.add(new CstCampo(constroiNome(cmp), cmp.pNome(), true));
        }
        for (TabCampo cmp : paraTab.pCampos()) {
            camposPara.add(new CstCampo(constroiNome(cmp), cmp.pNome(), false));
        }
        iniciaRelacoes();
        camposRels = new CstRelacoes(camposDe, camposPara);
        jspCampos.add(camposRels);
        jspCampos.setViewportView(camposRels);
        if (comConstrucao.excluir != null) {
            jckExcluir.setSelected(comConstrucao.excluir);
        } else {
            jckExcluir.setSelected(cfgs.pegaLog("PointelMigre - Mago - Importação - Construir - Excluir", false));
        }
        if (comConstrucao.inserir != null) {
            jckInserir.setSelected(comConstrucao.inserir);
        } else {
            jckInserir.setSelected(cfgs.pegaLog("PointelMigre - Mago - Importação - Construir - Inserir", true));
        }
        if (comConstrucao.atualizar != null) {
            jckAtualizar.setSelected(comConstrucao.atualizar);
        } else {
            jckAtualizar.setSelected(cfgs.pegaLog("PointelMigre - Mago - Importação - Construir - Atualizar", true));
        }
        if (comConstrucao.pausarSeErrar != null) {
            jckPausarSeErrar.setSelected(comConstrucao.pausarSeErrar);
        } else {
            jckPausarSeErrar.setSelected(cfgs.pegaLog("PointelMigre - Mago - Importação - Construir - PausarSeErrar", false));
        }
        getRootPane().setDefaultButton(jbtSalvar);
        Janelas.inicia(this);
    }

    public MagoImpCstIntf mostra() {
        Janelas.mostra(this);
        return this;
    }

    public CstCampo pegaReferencia(String doNomeTabela) {
        for (CstCampo cmp : camposDe) {
            if (Objects.equals(cmp.nomeTabela, doNomeTabela)) {
                return cmp;
            }
        }
        return null;
    }

    public CstCampo pegaReferenciado(String doNomeTabela) {
        for (CstCampo cmp : camposPara) {
            if (Objects.equals(cmp.nomeTabela, doNomeTabela)) {
                return cmp;
            }
        }
        return null;
    }

    private void iniciaRelacoes() {
        if (construcao.relacoes.isEmpty()) {
            for (CstCampo cmpDe : camposDe) {
                List<Integer> sims = new ArrayList<>();
                for (CstCampo cmpPara : camposPara) {
                    String[] pDe = cmpDe.nomeCompleto.split(" ");
                    String[] pPara = cmpPara.nomeCompleto.split(" ");
                    int sim = 0;
                    for (int i1 = 0; i1 < pDe.length; i1++) {
                        sim = sim + UtlCrs.diferenciaPalavras(pDe[i1], pPara[i1]);
                    }
                    sims.add(sim);
                }
                Integer vnc = UtlInt.pegaMenor(sims);
                if (vnc > -1) {
                    cmpDe.vinculos.add(camposPara.get(vnc));
                }
            }
        } else {
            for (TabRelacao rel : construcao.relacoes) {
                CstCampo cmpDe = pegaReferencia(rel.referencia);
                if (cmpDe != null) {
                    CstCampo cmpPara = pegaReferenciado(rel.referenciado);
                    if (cmpPara != null) {
                        cmpDe.vinculos.add(cmpPara);
                    } else {
                        Utl.alerta("Relação Com Problemas, Não Encontrado Referenciado: " + rel.referenciado);
                    }
                } else {
                    Utl.alerta("Relação Com Problemas, Não Encontrada Referencia: " + rel.referencia);
                }
            }
        }
    }

    public String constroiNome(TabCampo cmp) {
        return cmp.pNome() + " " + cmp.pTipo().toString() + " (" + cmp.pTamanho() + "," + cmp.pPrecisao() + ")";
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlbTitulo = new javax.swing.JLabel();
        jspCampos = new javax.swing.JScrollPane();
        jckExcluir = new javax.swing.JCheckBox();
        jckInserir = new javax.swing.JCheckBox();
        jckAtualizar = new javax.swing.JCheckBox();
        jckPausarSeErrar = new javax.swing.JCheckBox();
        jbtSalvar = new javax.swing.JButton();
        jbtCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mago - Importação - Construir");
        setResizable(false);

        jlbTitulo.setText("titulo");

        jckExcluir.setText("Excluir");

        jckInserir.setText("Inserir");

        jckAtualizar.setText("Atualizar");

        jckPausarSeErrar.setText("Pausar Se Errar");

        jbtSalvar.setText("Salvar");
        jbtSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtSalvarActionPerformed(evt);
            }
        });

        jbtCancelar.setText("Cancelar");
        jbtCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCancelarActionPerformed(evt);
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
                        .addComponent(jckExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jckInserir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jckAtualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jckPausarSeErrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 188, Short.MAX_VALUE)
                        .addComponent(jbtSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbTitulo)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jspCampos))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspCampos, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtCancelar)
                    .addComponent(jbtSalvar)
                    .addComponent(jckExcluir)
                    .addComponent(jckInserir)
                    .addComponent(jckAtualizar)
                    .addComponent(jckPausarSeErrar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelarActionPerformed
        Janelas.fecha(this);
    }//GEN-LAST:event_jbtCancelarActionPerformed

    private void jbtSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSalvarActionPerformed
        try {
            construcao.relacoes.clear();
            for (CstCampo cmpDe : camposDe) {
                for (CstCampo cmpPara : cmpDe.vinculos) {
                    construcao.relacoes.add(new TabRelacao(cmpPara.nomeTabela, cmpDe.nomeTabela));
                }
            }
            construcao.excluir = jckExcluir.isSelected();
            construcao.inserir = jckInserir.isSelected();
            construcao.atualizar = jckAtualizar.isSelected();
            construcao.pausarSeErrar = jckPausarSeErrar.isSelected();
            magoImp.salva(construcao);
            magoImp.requestFocus();
            cfgs.botaLog("PointelMigre - Mago - Importação - Construir - Excluir", jckExcluir.isSelected());
            cfgs.botaLog("PointelMigre - Mago - Importação - Construir - Inserir", jckInserir.isSelected());
            cfgs.botaLog("PointelMigre - Mago - Importação - Construir - Atualizar", jckAtualizar.isSelected());
            cfgs.botaLog("PointelMigre - Mago - Importação - Construir - PausarSeErrar", jckPausarSeErrar.isSelected());
            Janelas.fecha(this);
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jbtSalvarActionPerformed

    public class CstCampo extends JPanel {

        public String nomeCompleto;
        public String nomeTabela;
        public Boolean campoDe;
        public List<CstCampo> vinculos;
        public JLabel jlbNome;
        public int posX;
        public int posY;
        public int largura;
        public int altura;

        public CstCampo(String comNomeCompleto, String eNomeTabela, Boolean ehCampoDe) {
            nomeCompleto = comNomeCompleto;
            nomeTabela = eNomeTabela;
            campoDe = ehCampoDe;
            vinculos = new ArrayList<>();
            setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.BLACK, 2, true),
                    BorderFactory.createEmptyBorder(2, 2, 2, 2)));
            setLayout(new BorderLayout());
            jlbNome = new JLabel(nomeCompleto);
            jlbNome.setToolTipText(nomeCompleto);
            add(jlbNome, BorderLayout.CENTER);
            if (ehCampoDe) {
                jlbNome.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            fazClique();
                        }
                    }
                });
            }
        }

        private void fazClique() {
            JPopupMenu pmn = new JPopupMenu();
            JMenu mnRelaciona = new JMenu("Relaciona");
            for (JMenuItem jmi : fazRelacionas()) {
                mnRelaciona.add(jmi);
            }
            pmn.add(mnRelaciona);
            JMenu mnCancela = new JMenu("Cancela");
            JMenuItem jmtc = new JMenuItem("Todos");
            jmtc.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    vinculos.clear();
                    camposRels.repaint();
                }
            });
            mnCancela.add(jmtc);
            mnCancela.addSeparator();
            for (JMenuItem jmi : fazCancelas()) {
                mnCancela.add(jmi);
            }
            pmn.add(mnCancela);
            pmn.show(jlbNome, 0, jlbNome.getHeight());
        }

        private List<JMenuItem> fazRelacionas() {
            List<JMenuItem> retorno = new ArrayList<>();
            for (CstCampo cmp : camposPara) {
                JMenuItem jmi = new JMenuItem(cmp.nomeCompleto);
                jmi.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        vinculos.add(cmp);
                        camposRels.repaint();
                    }
                });
                retorno.add(jmi);
            }
            return retorno;
        }

        private List<JMenuItem> fazCancelas() {
            List<JMenuItem> retorno = new ArrayList<>();
            for (CstCampo cmp : vinculos) {
                JMenuItem jmi = new JMenuItem(cmp.nomeCompleto);
                jmi.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        vinculos.remove(cmp);
                        camposRels.repaint();
                    }
                });
                retorno.add(jmi);
            }
            return retorno;
        }
    }

    public class CstRelacoes extends JPanel {

        public List<CstCampo> esquerda;
        public List<CstCampo> direita;

        public CstRelacoes(List<CstCampo> naEsquerda, List<CstCampo> naDireita) {
            setLayout(null);
            esquerda = naEsquerda;
            direita = naDireita;
            int idx = 0;
            for (CstCampo cmp : esquerda) {
                cmp.posX = 10;
                cmp.posY = 10 + (idx * 40);
                cmp.largura = 180;
                cmp.altura = 30;
                cmp.setBounds(cmp.posX, cmp.posY, cmp.largura, cmp.altura);
                add(cmp);
                idx++;
            }
            idx = 0;
            for (CstCampo cmp : direita) {
                cmp.posX = jspCampos.getWidth() - 230;
                cmp.posY = 10 + (idx * 40);
                cmp.largura = 180;
                cmp.altura = 30;
                cmp.setBounds(cmp.posX, cmp.posY, cmp.largura, cmp.altura);

                add(cmp);
                idx++;
            }
            Dimension dim = new Dimension();
            dim.width = jspCampos.getWidth() - 15;
            if (esquerda.size() > direita.size()) {
                dim.height = esquerda.get(esquerda.size() - 1).posY + 40;
            } else {
                dim.height = direita.get(direita.size() - 1).posY + 40;
            }
            setPreferredSize(dim);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D gr = (Graphics2D) g;
            gr.setStroke(new BasicStroke(2));
            for (CstCampo cmp : esquerda) {
                for (CstCampo vnc : cmp.vinculos) {
                    int mdest = vnc.posY + (vnc.altura / 2);
                    gr.drawLine(cmp.posX + cmp.largura, cmp.posY + (cmp.altura / 2), vnc.posX, mdest);
                }
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtCancelar;
    private javax.swing.JButton jbtSalvar;
    private javax.swing.JCheckBox jckAtualizar;
    private javax.swing.JCheckBox jckExcluir;
    private javax.swing.JCheckBox jckInserir;
    private javax.swing.JCheckBox jckPausarSeErrar;
    private javax.swing.JLabel jlbTitulo;
    private javax.swing.JScrollPane jspCampos;
    // End of variables declaration//GEN-END:variables
}
