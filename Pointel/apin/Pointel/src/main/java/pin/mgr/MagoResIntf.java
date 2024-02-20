package pin.mgr;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.DefaultListModel;
import pin.libbas.Operacao;
import pin.libbas.Progresso;
import pin.libjan.Janelas;
import pin.libpic.Pics;
import pin.libtex.Marcados;
import pin.libtex.Porcao;
import pin.libitr.Questao;
import pin.libutl.Utl;
import pin.libutl.UtlCrs;
import pin.libutl.UtlLis;
import pin.libutl.UtlTex;
import pin.modinf.Conexao;
import pin.modinf.TabCampo;
import pin.modinf.Tabela;
import pin.modrec.XMLReg;

public class MagoResIntf extends javax.swing.JFrame {

    private final PointelMigre pointelMigre;
    private final DefaultListModel dlmConexoes;
    private final DefaultListModel dlmTabelas;

    public MagoResIntf(PointelMigre doPointelMigre) {
        initComponents();
        pointelMigre = doPointelMigre;
        dlmConexoes = new DefaultListModel();
        for (ConexaoCfg cof : pointelMigre.pMigracao().conexoes) {
            dlmConexoes.addElement(cof.nome);
        }
        jlsConexoes.setModel(dlmConexoes);
        dlmTabelas = new DefaultListModel();
        jlsTabelas.setModel(dlmTabelas);
        jbtMagica.setIcon(Pics.pMagica());
        jbtBota.setIcon(Pics.pBota());
        jbtTira.setIcon(Pics.pTira());
        jbtLimpa.setIcon(Pics.pLimpa());
        Janelas.botaAjuda(this, "http://pointel.com.br/2018/10/24/pointelmigre-mago-restruturacao/");
        Janelas.inicia(this);
    }

    public MagoResIntf mostra() {
        Janelas.mostra(this);
        return this;
    }

    public MagoResIntf botaTabela(String comNome) {
        dlmTabelas.addElement(comNome);
        return this;
    }

    public PointelMigre pPointelMigre() {
        return pointelMigre;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlbConexoes = new javax.swing.JLabel();
        jspConexoes = new javax.swing.JScrollPane();
        jlsConexoes = new javax.swing.JList<>();
        jlbCriterio = new javax.swing.JLabel();
        jckTodas = new javax.swing.JCheckBox();
        jcbUniao1 = new javax.swing.JComboBox<>();
        jcbCondicao = new javax.swing.JComboBox<>();
        jcbFonte = new javax.swing.JComboBox<>();
        jtfCondicao = new javax.swing.JTextField();
        jcbUniao2 = new javax.swing.JComboBox<>();
        jlbTabelas = new javax.swing.JLabel();
        jspTabelas = new javax.swing.JScrollPane();
        jlsTabelas = new javax.swing.JList<>();
        jbtMagica = new javax.swing.JButton();
        jbtBota = new javax.swing.JButton();
        jbtTira = new javax.swing.JButton();
        jbtLimpa = new javax.swing.JButton();
        jlbComandos = new javax.swing.JLabel();
        jspComandos = new javax.swing.JScrollPane();
        jtaComandos = new javax.swing.JTextArea();
        jckPausar = new javax.swing.JCheckBox();
        jbtRealizar = new javax.swing.JButton();
        jbtCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mago - Restruturação");

        jlbConexoes.setText("Conexões");

        jspConexoes.setViewportView(jlsConexoes);

        jlbCriterio.setText("Critério");

        jckTodas.setSelected(true);
        jckTodas.setText("Todas");

        jcbUniao1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Menos", "Mais", " " }));

        jcbCondicao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Igual", "Diferente", "Contendo", "Iniciando", "Terminando", "CrsPadrão" }));

        jcbFonte.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nome", "Descrição" }));

        jcbUniao2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mais", "Menos" }));

        jlbTabelas.setText("Tabelas");

        jspTabelas.setViewportView(jlsTabelas);

        jbtMagica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtMagicaActionPerformed(evt);
            }
        });

        jbtBota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtBotaActionPerformed(evt);
            }
        });

        jbtTira.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtTiraActionPerformed(evt);
            }
        });

        jbtLimpa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtLimpaActionPerformed(evt);
            }
        });

        jlbComandos.setText("Comandos");

        jtaComandos.setColumns(20);
        jtaComandos.setRows(5);
        jspComandos.setViewportView(jtaComandos);

        jckPausar.setText("Pausar Se Errar");

        jbtRealizar.setText("Realizar");
        jbtRealizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRealizarActionPerformed(evt);
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
                    .addComponent(jspComandos)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jspConexoes, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbConexoes))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jspTabelas, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jbtMagica, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jbtBota, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jbtTira, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jbtLimpa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlbTabelas)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jcbCondicao, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbCriterio))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jcbFonte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jckTodas)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jcbUniao1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jtfCondicao)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jcbUniao2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbComandos)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jckPausar)
                        .addGap(18, 18, 18)
                        .addComponent(jbtRealizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtCancelar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jlbConexoes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jspConexoes))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbCriterio)
                            .addComponent(jcbUniao1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jckTodas)
                            .addComponent(jcbFonte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcbCondicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbUniao2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfCondicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlbTabelas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbtMagica, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtBota, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtTira, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtLimpa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jspTabelas))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbComandos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspComandos, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtCancelar)
                    .addComponent(jbtRealizar)
                    .addComponent(jckPausar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtMagicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtMagicaActionPerformed
        new MagoResMagIntf(this).mostra();
    }//GEN-LAST:event_jbtMagicaActionPerformed

    private void jbtBotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtBotaActionPerformed
        new Questao("Quais Tabelas?") {
            @Override
            public Boolean executa(String comResposta) {
                String[] lns = UtlTex.pLinhas(comResposta);
                for (String ln : lns) {
                    String[] tabs = ln.split("\\,");
                    for (String tab : tabs) {
                        botaTabela(tab);
                    }
                }
                return true;
            }
        }.mostra();
    }//GEN-LAST:event_jbtBotaActionPerformed

    private void jbtTiraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtTiraActionPerformed
        int[] sels = jlsTabelas.getSelectedIndices();
        for (int isel = sels.length - 1; isel >= 0; isel--) {
            dlmTabelas.remove(isel);
        }
    }//GEN-LAST:event_jbtTiraActionPerformed

    private void jbtLimpaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtLimpaActionPerformed
        if (Utl.continua()) {
            dlmTabelas.clear();
        }
    }//GEN-LAST:event_jbtLimpaActionPerformed

    private void jbtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelarActionPerformed
        Janelas.fecha(this);
    }//GEN-LAST:event_jbtCancelarActionPerformed

    private void jbtRealizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRealizarActionPerformed
        Janelas.fecha(this);
        new ThdRealizar().start();
    }//GEN-LAST:event_jbtRealizarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtBota;
    private javax.swing.JButton jbtCancelar;
    private javax.swing.JButton jbtLimpa;
    private javax.swing.JButton jbtMagica;
    private javax.swing.JButton jbtRealizar;
    private javax.swing.JButton jbtTira;
    private javax.swing.JComboBox<String> jcbCondicao;
    private javax.swing.JComboBox<String> jcbFonte;
    private javax.swing.JComboBox<String> jcbUniao1;
    private javax.swing.JComboBox<String> jcbUniao2;
    private javax.swing.JCheckBox jckPausar;
    private javax.swing.JCheckBox jckTodas;
    private javax.swing.JLabel jlbComandos;
    private javax.swing.JLabel jlbConexoes;
    private javax.swing.JLabel jlbCriterio;
    private javax.swing.JLabel jlbTabelas;
    private javax.swing.JList<String> jlsConexoes;
    private javax.swing.JList<String> jlsTabelas;
    private javax.swing.JScrollPane jspComandos;
    private javax.swing.JScrollPane jspConexoes;
    private javax.swing.JScrollPane jspTabelas;
    private javax.swing.JTextArea jtaComandos;
    private javax.swing.JTextField jtfCondicao;
    // End of variables declaration//GEN-END:variables

    private class ThdRealizar extends Thread {

        private Progresso progresso;
        private Pattern padrao;
        private final List<String> tabelas;
        private final String comandos;
        private final Boolean pausarSeErrar;

        public ThdRealizar() {
            super("MagoRes Realizar");
            padrao = null;
            tabelas = Arrays.asList(UtlLis.pMatCrs(dlmTabelas.toArray()));
            comandos = jtaComandos.getText();
            pausarSeErrar = jckPausar.isSelected();
        }

        private Boolean verPadrao(String oPadrao, String naFonte) {
            if (padrao == null) {
                padrao = Pattern.compile(oPadrao);
            }
            return padrao.matcher(naFonte).find();
        }

        private Boolean deveIncluir(Tabela aTabela) throws Exception {
            String fonte = aTabela.pEsquemaENome();
            if ("Descrição".equals(jcbFonte.getSelectedItem())) {
                fonte = aTabela.pDescricao();
            }
            if (fonte == null) {
                fonte = "";
            }
            Boolean retorno = jckTodas.isSelected();
            String conteudo = jtfCondicao.getText();
            if (!conteudo.isEmpty()) {
                boolean cope = "Mais".equals(jcbUniao1.getSelectedItem());
                switch (jcbCondicao.getSelectedItem().toString()) {
                    case "Igual":
                        if (fonte.equals(conteudo)) {
                            retorno = cope;
                        }
                        break;
                    case "Diferente":
                        if (!fonte.equals(conteudo)) {
                            retorno = cope;
                        }
                        break;
                    case "Contendo":
                        if (fonte.contains(conteudo)) {
                            retorno = cope;
                        }
                        break;
                    case "Iniciando":
                        if (fonte.startsWith(conteudo)) {
                            retorno = cope;
                        }
                        break;
                    case "Terminando":
                        if (fonte.endsWith(conteudo)) {
                            retorno = cope;
                        }
                        break;
                    case "CrsPadrão":
                        if (verPadrao(conteudo, fonte)) {
                            retorno = cope;
                        }
                        break;
                }
            }
            if (tabelas != null) {
                if (!tabelas.isEmpty()) {
                    boolean cope = "Mais".equals(jcbUniao2.getSelectedItem());
                    if (tabelas.contains(aTabela.pEsquemaENome())) {
                        retorno = cope;
                    }
                }
            }
            return retorno;
        }

        private String produz(Tabela aTabela) throws Exception {
            String operacao = UtlCrs.troca(comandos, "<tabela>", aTabela.pEsquemaENome());
            if (operacao.contains("<campos>")) {
                int atual = 0;
                String fazendo = "";
                List<Porcao> porcs = Marcados.porcoes(operacao, "campos");
                for (Porcao porc : porcs) {
                    fazendo += operacao.substring(atual, porc.abridorIni);
                    String cmpValor = operacao.substring(porc.valorIni, porc.valorFim);
                    for (TabCampo cmp : aTabela.pCampos()) {
                        fazendo += UtlCrs.troca(cmpValor, "<campo>", cmp.pNome());
                    }
                    atual = porc.fechadorFim;
                }
                if (atual < operacao.length()) {
                    fazendo += operacao.substring(atual, operacao.length());
                }
                operacao = fazendo;
            }
            return operacao.trim();
        }

        @Override
        public void run() {
            progresso = new Progresso("Mago Restruturação")
                    .abre()
                    .criaAutoCorrer();
            try {
                progresso.bota("Iniciando...");
                int idx = 0;
                List<String> cngs = jlsConexoes.getSelectedValuesList();
                XMLReg xml = XMLReg.novo();
                for (String cng : cngs) {
                    progresso.bota("Conectando " + cng);
                    Conexao con = pointelMigre.pMigracao().pConexao(cng).conecta();
                    int tam = con.bancoTabelas().tamanho();
                    for (int it = 0; it < tam; it++) {
                        idx++;
                        Tabela tab = con.bancoTabelas().pega(it);
                        progresso.bota("Analisando", tab.pEsquemaENome());
                        Boolean deve = deveIncluir(tab);
                        if (deve) {
                            progresso.bota("Incluindo", tab.pEsquemaENome());
                            String operacao = produz(tab);
                            progresso.bota("Operação", operacao);
                            Executar ex = new Executar(cng, pausarSeErrar, operacao);
                            pointelMigre.bota(new Operacao(Executar.pTipo(), "Ope " + idx, xml.pReg(ex)));
                        } else {
                            progresso.bota("Não Deve Ser Incluída");
                        }
                    }
                }
                progresso.termina();
            } catch (Exception ex) {
                progresso.bota(ex);
            }
        }
    }

}
