package pin.modamk;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import pin.libamk.Cmp;
import pin.libbas.Dados;
import pin.libjan.PopMenu;
import pin.libjan.TrataIntf;
import pin.libpic.Pics;
import pin.libutl.Utl;
import pin.libutl.UtlCr;
import pin.libutl.UtlCrs;
import pin.libutl.UtlDat;
import pin.libutl.UtlDatHor;
import pin.libutl.UtlHor;
import pin.libutl.UtlInt;
import pin.libutl.UtlIntLon;
import pin.libutl.UtlLog;
import pin.libutl.UtlMom;
import pin.libutl.UtlNum;
import pin.libutl.UtlNumLon;

public class CadProcurarCmp extends javax.swing.JPanel {

    private CadProcurar procurar;
    private Cadastro cadastro;
    private DefaultComboBoxModel<String> cbmCampos;

    public PopMenu menuCampo;

    public CadProcurarCmp(CadProcurar naProcura) {
        this(naProcura, false);
    }

    public CadProcurarCmp(CadProcurar naProcura, Boolean ativado) {
        initComponents();
        procurar = naProcura;
        cadastro = naProcura.cadastro;

        cbmCampos = new DefaultComboBoxModel<>();
        jcbCampo.setModel(cbmCampos);

        menuCampo = new PopMenu();
        menuCampo.bota("Seleção Padrão", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cadastro.cfgs.botaCrs("PointelAllMake - Cadastro - Procurar Campo Seleção Padrão", (String) jcbCampo.getSelectedItem());
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        });
        menuCampo.coloca(jlbCampoOpcoes, true, MouseEvent.BUTTON1);

        String selecionadoPadrao = "Nome";
        try {
            selecionadoPadrao = cadastro.cfgs.pegaCrs("PointelAllMake - Cadastro - Procurar Campo Seleção Padrão", "Nome");
        } catch (Exception ex) {
            selecionadoPadrao = "Nome";
        }

        int autoSel = -1;
        int cmpIdx = 0;
        for (Cmp cmp : cadastro.camposDeValor) {
            if (cmp.pDetalhe("podeFiltrar", true)) {
                cbmCampos.addElement(cmp.pTitulo());

                if (selecionadoPadrao.equals(cmp.pTitulo())) {
                    autoSel = cmpIdx;
                }
                if (autoSel == -1 && cmpIdx > 1 && cmp.pTipo() == Dados.Tp.Crs) {
                    autoSel = cmpIdx;
                }

                cmpIdx++;
            }
        }

        String campoPadrao = naProcura.cadastro.cfgs.pegaCrs("Cadastro - Procurar - Campo Padrão", "");
        if (!campoPadrao.isEmpty()) {
            int icp = cbmCampos.getIndexOf(campoPadrao);
            if (icp > -1) {
                autoSel = icp;
            }
        }

        if (ativado && autoSel > -1) {
            jcbCampo.setSelectedIndex(autoSel);
            jcbCondicao.setSelectedItem("Contendo");
            TrataIntf.garanteFoco(jtfValor);
        } else {
            TrataIntf.garanteFoco(jcbCampo);
        }

        jcbCondicao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jckCampo.setSelected(true);
            }
        });

        jtfValor.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                temCondicao();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                temCondicao();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                temCondicao();
            }

            public void temCondicao() {
                jckCampo.setSelected(true);
            }
        });

        jbtAdicionar.setIcon(Pics.pega("add.png"));
        jbtRemover.setIcon(Pics.pega("delete.png"));
    }

    public Boolean temCondicao() {
        return jckCampo.isSelected();
    }

    public String pegaCampoNome() {
        String retorno = cadastro.pegaCampoNome((String) jcbCampo.getSelectedItem());
        if (!retorno.contains(".")) {
            retorno = cadastro.tabela + "." + retorno;
        }
        return retorno;
    }

    public Boolean ehNegativo() {
        return jckNao.isSelected();
    }

    public String pegaCondicao(Integer comIndice) {
        String retorno = "";
        String campo = pegaCampoNome();
        String condicao = (String) jcbCondicao.getSelectedItem();
        Cmp campoQ = cadastro.pegaCampoTitulo((String) jcbCampo.getSelectedItem());
        if (ehNegativo()) {
            retorno = "NOT ";
        }
        retorno = retorno + campo;
        if (condicao.equals("Contendo")) {
            if (campoQ.pTipo() == Dados.Tp.Crs) {
                retorno = retorno + " ILIKE '%' || ? || '%'";
            } else {
                retorno = retorno + " = ?";
            }
        } else if (condicao.equals("Iniciando")) {
            if (campoQ.pTipo() == Dados.Tp.Crs) {
                retorno = retorno + " ILIKE ? || '%'";
            } else {
                retorno = retorno + " >= ?";
            }
        } else if (condicao.equals("Terminando")) {
            if (campoQ.pTipo() == Dados.Tp.Crs) {
                retorno = retorno + " ILIKE '%' || ?";
            } else {
                retorno = retorno + " <= ?";
            }
        } else if (condicao.equals("Igual")) {
            retorno = retorno + " = ?";
        } else if (condicao.equals("Diferente")) {
            retorno = retorno + " != ?";
        } else if (condicao.equals("Maior")) {
            retorno = retorno + " > ?";
        } else if (condicao.equals("Menor")) {
            retorno = retorno + " < ?";
        } else if (condicao.equals("Maior-Igual")) {
            retorno = retorno + " >= ?";
        } else if (condicao.equals("Menor-Igual")) {
            retorno = retorno + " <= ?";
        } else if (condicao.equals("É Nulo")) {
            retorno = retorno + " IS NULL";
        } else if (condicao.equals("Não É Nulo")) {
            retorno = retorno + " IS NOT NULL";
        }

        retorno = retorno + pegaMais();
        return retorno;
    }

    public Boolean temParametro() {
        Boolean retorno = true;
        String condicao = (String) jcbCondicao.getSelectedItem();
        if ((condicao.equals("É Nulo")) || (condicao.equals("Não É Nulo"))) {
            retorno = false;
        }
        return retorno;
    }

    public Object pegaParametro() {
        Object retorno = jtfValor.getText();
        Cmp campo = cadastro.pegaCampoTitulo((String) jcbCampo.getSelectedItem());
        switch (campo.pTipo()) {
            case Log:
                retorno = UtlLog.pegaSQL(retorno);
                break;
            case Cr:
                retorno = UtlCr.pegaSQL(retorno);
                break;
            case Crs:
            case Enu:
            case Sug:
                retorno = UtlCrs.pegaSQL(retorno);
                break;
            case Int:
                retorno = UtlInt.pegaSQL(retorno);
                break;
            case IntLon:
                retorno = UtlIntLon.pegaSQL(retorno);
                break;
            case Num:
                retorno = UtlNum.pegaSQL(retorno);
                break;
            case NumLon:
                retorno = UtlNumLon.pegaSQL(retorno);
                break;
            case Dat:
                retorno = UtlDat.pegaSQL(UtlDat.pega(retorno));
                break;
            case Hor:
                retorno = UtlHor.pegaSQL(UtlHor.pega(retorno));
                break;
            case DatHor:
                retorno = UtlDatHor.pegaSQL(UtlDatHor.pega(retorno));
                break;
            case Mom:
                retorno = UtlMom.pegaSQL(UtlMom.pega(retorno));
                break;
            case Tex:
                break;
            case Ima:
                break;
            case Doc:
                break;
            case Pla:
                break;
            case Aud:
                break;
            case Vid:
                break;
            case Frm:
                break;
            case Apr:
                break;
            case Arq:
                break;
        }
        return retorno;
    }

    public String pegaMais() {
        String retorno = "";
        switch ((String) jcbMais.getSelectedItem()) {
            case "E":
                retorno = " AND ";
                break;
            case "Ou":
                retorno = " OR ";
                break;
        }
        return retorno;
    }

    public CadProcurarCmp mCampo(String para) {
        jcbCampo.setSelectedItem(para);
        return this;
    }

    public CadProcurarCmp mCondicao(String para) {
        jcbCondicao.setSelectedItem(para);
        return this;
    }

    public CadProcurarCmp mValor(String para) {
        jtfValor.setText(para);
        return this;
    }

    public CadProcurarCmp mMais(String para) {
        jcbMais.setSelectedItem(para);
        return this;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jckCampo = new javax.swing.JCheckBox();
        jcbCampo = new javax.swing.JComboBox();
        jlbCampoOpcoes = new javax.swing.JLabel();
        jlbCondicao = new javax.swing.JLabel();
        jckNao = new javax.swing.JCheckBox();
        jcbCondicao = new javax.swing.JComboBox();
        jlbValor = new javax.swing.JLabel();
        jtfValor = new javax.swing.JTextField();
        jlbMais = new javax.swing.JLabel();
        jcbMais = new javax.swing.JComboBox<>();
        jbtAdicionar = new javax.swing.JButton();
        jbtRemover = new javax.swing.JButton();

        jckCampo.setText("Campo");

        jlbCampoOpcoes.setText("...");

        jlbCondicao.setText("Condição");

        jckNao.setText("Não");

        jcbCondicao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Contendo", "Iniciando", "Terminando", "Igual", "Diferente", "Maior", "Menor", "Maior-Igual", "Menor-Igual", "É Nulo", "Não É Nulo" }));

        jlbValor.setText("Valor");

        jlbMais.setText("Mais");

        jcbMais.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "E", "Ou" }));

        jbtAdicionar.setToolTipText("Novo (Control+N)");
        jbtAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAdicionarActionPerformed(evt);
            }
        });

        jbtRemover.setToolTipText("Novo (Control+N)");
        jbtRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRemoverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcbCampo, 0, 174, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jckCampo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlbCampoOpcoes)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbCondicao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jckNao))
                    .addComponent(jcbCondicao, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfValor, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbValor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbMais)
                    .addComponent(jcbMais, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbtRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbCondicao)
                            .addComponent(jlbValor)
                            .addComponent(jlbMais)
                            .addComponent(jckNao)
                            .addComponent(jckCampo)
                            .addComponent(jlbCampoOpcoes))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcbCampo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbCondicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbMais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbtAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAdicionarActionPerformed
        try {
            procurar.camposAdicionar(this);
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jbtAdicionarActionPerformed

    private void jbtRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRemoverActionPerformed
        try {
            procurar.camposRemover(this);
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jbtRemoverActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jbtAdicionar;
    public javax.swing.JButton jbtRemover;
    public javax.swing.JComboBox jcbCampo;
    public javax.swing.JComboBox jcbCondicao;
    private javax.swing.JComboBox<String> jcbMais;
    private javax.swing.JCheckBox jckCampo;
    private javax.swing.JCheckBox jckNao;
    private javax.swing.JLabel jlbCampoOpcoes;
    private javax.swing.JLabel jlbCondicao;
    private javax.swing.JLabel jlbMais;
    private javax.swing.JLabel jlbValor;
    public javax.swing.JTextField jtfValor;
    // End of variables declaration//GEN-END:variables
}
