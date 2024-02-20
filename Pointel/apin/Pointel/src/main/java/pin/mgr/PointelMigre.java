package pin.mgr;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import pin.libbas.Configs;
import pin.libbas.Globais;
import pin.libbas.Operacao;
import pin.libbas.Paralelo;
import pin.libbas.Partes;
import pin.libetf.TexEtf;
import pin.libjan.Janelas;
import pin.libjan.PopMenu;
import pin.libjan.TrataIntf;
import pin.libpic.Pics;
import pin.libutl.Utl;
import pin.libutl.UtlArq;
import pin.libutl.UtlCrs;
import pin.libutl.UtlLis;
import pin.modamk.Recursos;
import pin.modinf.Bancos;
import pin.modinf.ConAuxiliar;
import pin.modinf.Conexao;
import pin.modpim.Pims;

public class PointelMigre extends javax.swing.JFrame {

    private final Configs cfgs;
    private final JFileChooser selecionador;
    private final DefaultListModel dlmConexoes;
    private final DefaultListModel dlmOperacoes;
    private final PopMenu menuExecutar;
    private final PopMenu menuAuxiliar;
    private Migracao migracao;

    public PointelMigre() throws Exception {
        initComponents();
        cfgs = (Configs) Globais.pega("mainConf");
        setIconImage(Pims.pega("PointelMigre.png").getImage());
        selecionador = UtlArq.abridor("PointelMigre (*.pmi)", "pmi");
        jbtNova.setIcon(Pics.pegaNovo());
        jbtAbrir.setIcon(Pics.pegaAbrir());
        jbtRecentes.setIcon(Pics.pegaAtualizar());
        jbtSalvar.setIcon(Pics.pegaSalvar());
        jbtExecutar.setIcon(Pics.pegaProcessar());
        jbtMago.setIcon(Pics.pMagica());
        jbtConAdicionar.setIcon(Pics.pBota());
        jbtConRemover.setIcon(Pics.pTira());
        jbtConSalvar.setIcon(Pics.pegaSalvar());
        jbtConCancelar.setIcon(Pics.pegaCancela());
        jbtConTestar.setIcon(Pics.pegaTestar());
        jbtConUp.setIcon(Pics.pegaAcima());
        jbtConDown.setIcon(Pics.pegaAbaixo());
        jbtOpeAdicionar.setIcon(Pics.pBota());
        jbtOpeRemover.setIcon(Pics.pTira());
        jbtOpeSalvar.setIcon(Pics.pegaSalvar());
        jbtOpeCancelar.setIcon(Pics.pegaCancela());
        jbtOpeAssistente.setIcon(Pics.pegaProcessar());
        jbtOpeUp.setIcon(Pics.pegaAcima());
        jbtOpeDown.setIcon(Pics.pegaAbaixo());
        migracao = new Migracao();
        dlmConexoes = new DefaultListModel();
        dlmOperacoes = new DefaultListModel();
        jlsConexoes.setModel(dlmConexoes);
        jlsOperacoes.setModel(dlmOperacoes);
        menuExecutar = new PopMenu();
        menuAuxiliar = new PopMenu();
        iniciaMenuExecutar();
        Janelas.inicia(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnConexao = new javax.swing.JPanel();
        jtfUsuario = new javax.swing.JTextField();
        jspConexoes = new javax.swing.JScrollPane();
        jlsConexoes = new javax.swing.JList<>();
        jlbUsuario = new javax.swing.JLabel();
        jlbConNome = new javax.swing.JLabel();
        jtfBase = new javax.swing.JTextField();
        jbtConAdicionar = new javax.swing.JButton();
        jpfSenha = new javax.swing.JPasswordField();
        jtfServidor = new javax.swing.JTextField();
        jbtConSalvar = new javax.swing.JButton();
        jlbBase = new javax.swing.JLabel();
        jbtConCancelar = new javax.swing.JButton();
        jtfConNome = new javax.swing.JTextField();
        jcbBanco = new javax.swing.JComboBox<>();
        jlbSenha = new javax.swing.JLabel();
        jlbConexoes = new javax.swing.JLabel();
        jbtConRemover = new javax.swing.JButton();
        jlbServidor = new javax.swing.JLabel();
        jlbBanco = new javax.swing.JLabel();
        jspPorta = new javax.swing.JSpinner();
        jlbPorta = new javax.swing.JLabel();
        jbtConTestar = new javax.swing.JButton();
        jbtExecutarConexoes = new javax.swing.JButton();
        jbtConDown = new javax.swing.JButton();
        jbtConUp = new javax.swing.JButton();
        jbtBase = new javax.swing.JButton();
        jbtAuxiliar = new javax.swing.JButton();
        jpnOperacao = new javax.swing.JPanel();
        jtfOpeNome = new javax.swing.JTextField();
        jbtOpeRemover = new javax.swing.JButton();
        jbtOpeSalvar = new javax.swing.JButton();
        jbtOpeAssistente = new javax.swing.JButton();
        jcbTipo = new javax.swing.JComboBox<>();
        jbtOpeCancelar = new javax.swing.JButton();
        jspCodigo = new javax.swing.JScrollPane();
        jtaCodigo = new javax.swing.JTextArea();
        jbtOpeAdicionar = new javax.swing.JButton();
        jlbOpeNome = new javax.swing.JLabel();
        jlbTipo = new javax.swing.JLabel();
        jbtOpeUp = new javax.swing.JButton();
        jbtOpeDown = new javax.swing.JButton();
        jpnBarraLateral = new javax.swing.JPanel();
        jspOperacoes = new javax.swing.JScrollPane();
        jlsOperacoes = new javax.swing.JList<>();
        jbtNova = new javax.swing.JButton();
        jbtAbrir = new javax.swing.JButton();
        jbtRecentes = new javax.swing.JButton();
        jbtSalvar = new javax.swing.JButton();
        jbtExecutar = new javax.swing.JButton();
        jlbOperacoes = new javax.swing.JLabel();
        jbtMago = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PointelMigre");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jlsConexoes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jlsConexoes.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlsConexoesValueChanged(evt);
            }
        });
        jspConexoes.setViewportView(jlsConexoes);

        jlbUsuario.setText("Usuário");

        jlbConNome.setText("Nome");

        jbtConAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtConAdicionarActionPerformed(evt);
            }
        });

        jbtConSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtConSalvarActionPerformed(evt);
            }
        });

        jlbBase.setText("Base");

        jbtConCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtConCancelarActionPerformed(evt);
            }
        });

        jcbBanco.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SQLite", "Derby", "MySQL", "FireBird", "PostgreSQL", "MongoDB" }));

        jlbSenha.setText("Senha");

        jlbConexoes.setText("Conexões");

        jbtConRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtConRemoverActionPerformed(evt);
            }
        });

        jlbServidor.setText("Servidor");

        jlbBanco.setText("Banco");

        jlbPorta.setText("Porta");

        jbtConTestar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtConTestarActionPerformed(evt);
            }
        });

        jbtExecutarConexoes.setMnemonic('X');
        jbtExecutarConexoes.setText("Executar");

        jbtConDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtConDownActionPerformed(evt);
            }
        });

        jbtConUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtConUpActionPerformed(evt);
            }
        });

        jbtBase.setText("...");
        jbtBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtBaseActionPerformed(evt);
            }
        });

        jbtAuxiliar.setMnemonic('U');
        jbtAuxiliar.setText("Auxiliar");
        jbtAuxiliar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAuxiliarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnConexaoLayout = new javax.swing.GroupLayout(jpnConexao);
        jpnConexao.setLayout(jpnConexaoLayout);
        jpnConexaoLayout.setHorizontalGroup(
            jpnConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnConexaoLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jpnConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnConexaoLayout.createSequentialGroup()
                        .addComponent(jspConexoes, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpnConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpnConexaoLayout.createSequentialGroup()
                                .addComponent(jbtConAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtConRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtConSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtConCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtConTestar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtConUp, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtConDown, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtExecutarConexoes)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtAuxiliar))
                            .addGroup(jpnConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpnConexaoLayout.createSequentialGroup()
                                    .addGroup(jpnConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jlbBase)
                                        .addGroup(jpnConexaoLayout.createSequentialGroup()
                                            .addComponent(jtfBase)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jbtBase)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jpnConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jlbUsuario)
                                        .addComponent(jtfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jpnConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jlbSenha)
                                        .addComponent(jpfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpnConexaoLayout.createSequentialGroup()
                                    .addGroup(jpnConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jcbBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jlbBanco))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jpnConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jtfConNome, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jlbConNome))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jpnConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jtfServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jlbServidor))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jpnConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jlbPorta)
                                        .addComponent(jspPorta, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addComponent(jlbConexoes))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jpnConexaoLayout.setVerticalGroup(
            jpnConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnConexaoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbConexoes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnConexaoLayout.createSequentialGroup()
                        .addGroup(jpnConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jpnConexaoLayout.createSequentialGroup()
                                .addGroup(jpnConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpnConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jbtConAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jbtConRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jbtConSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jbtConCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jbtConTestar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpnConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jlbBanco)
                                    .addComponent(jlbConNome))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpnConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jcbBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtfConNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jpnConexaoLayout.createSequentialGroup()
                                .addGroup(jpnConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jbtConDown, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jbtConUp, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jbtExecutarConexoes, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jbtAuxiliar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6)
                                .addGroup(jpnConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jlbServidor)
                                    .addComponent(jlbPorta))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpnConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jtfServidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jspPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(11, 11, 11)
                        .addGroup(jpnConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbUsuario)
                            .addComponent(jlbSenha)
                            .addComponent(jlbBase))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpnConexaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jpfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtBase)
                            .addComponent(jtfBase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jspConexoes, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jbtOpeRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOpeRemoverActionPerformed(evt);
            }
        });

        jbtOpeSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOpeSalvarActionPerformed(evt);
            }
        });

        jbtOpeAssistente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOpeAssistenteActionPerformed(evt);
            }
        });

        jcbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Executar", "Executar Para Cada" }));

        jbtOpeCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOpeCancelarActionPerformed(evt);
            }
        });

        jtaCodigo.setColumns(20);
        jtaCodigo.setRows(5);
        jspCodigo.setViewportView(jtaCodigo);

        jbtOpeAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOpeAdicionarActionPerformed(evt);
            }
        });

        jlbOpeNome.setText("Nome");

        jlbTipo.setText("Operação - Tipo");

        jbtOpeUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOpeUpActionPerformed(evt);
            }
        });

        jbtOpeDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOpeDownActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnOperacaoLayout = new javax.swing.GroupLayout(jpnOperacao);
        jpnOperacao.setLayout(jpnOperacaoLayout);
        jpnOperacaoLayout.setHorizontalGroup(
            jpnOperacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnOperacaoLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jpnOperacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspCodigo)
                    .addGroup(jpnOperacaoLayout.createSequentialGroup()
                        .addGroup(jpnOperacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbTipo)
                            .addComponent(jcbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpnOperacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbOpeNome)
                            .addComponent(jtfOpeNome, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtOpeAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtOpeRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtOpeSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtOpeCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtOpeAssistente, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtOpeUp, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtOpeDown, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpnOperacaoLayout.setVerticalGroup(
            jpnOperacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnOperacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnOperacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbOpeNome)
                    .addGroup(jpnOperacaoLayout.createSequentialGroup()
                        .addComponent(jlbTipo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpnOperacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfOpeNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnOperacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpnOperacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbtOpeAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtOpeRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtOpeSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtOpeCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtOpeAssistente, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jbtOpeUp, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbtOpeDown, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jspCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                .addContainerGap())
        );

        jlsOperacoes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jlsOperacoes.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlsOperacoesValueChanged(evt);
            }
        });
        jspOperacoes.setViewportView(jlsOperacoes);

        jbtNova.setMnemonic('N');
        jbtNova.setText("Nova");
        jbtNova.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtNovaActionPerformed(evt);
            }
        });

        jbtAbrir.setMnemonic('A');
        jbtAbrir.setText("Abrir");
        jbtAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAbrirActionPerformed(evt);
            }
        });

        jbtRecentes.setMnemonic('R');
        jbtRecentes.setToolTipText("Reabrir");
        jbtRecentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRecentesActionPerformed(evt);
            }
        });

        jbtSalvar.setMnemonic('S');
        jbtSalvar.setText("Salvar");
        jbtSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtSalvarActionPerformed(evt);
            }
        });

        jbtExecutar.setMnemonic('E');
        jbtExecutar.setText("Executar");
        jbtExecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtExecutarActionPerformed(evt);
            }
        });

        jlbOperacoes.setText("Operações");
        jlbOperacoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbOperacoesMouseClicked(evt);
            }
        });

        jbtMago.setMnemonic('M');
        jbtMago.setText("Mago");
        jbtMago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtMagoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnBarraLateralLayout = new javax.swing.GroupLayout(jpnBarraLateral);
        jpnBarraLateral.setLayout(jpnBarraLateralLayout);
        jpnBarraLateralLayout.setHorizontalGroup(
            jpnBarraLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnBarraLateralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnBarraLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspOperacoes, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addGroup(jpnBarraLateralLayout.createSequentialGroup()
                        .addComponent(jbtNova, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtAbrir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtRecentes, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpnBarraLateralLayout.createSequentialGroup()
                        .addComponent(jbtSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtExecutar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnBarraLateralLayout.createSequentialGroup()
                        .addComponent(jlbOperacoes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtMago))))
        );
        jpnBarraLateralLayout.setVerticalGroup(
            jpnBarraLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnBarraLateralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnBarraLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbtAbrir, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtRecentes, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtNova, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnBarraLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbtSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtExecutar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpnBarraLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtMago, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbOperacoes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspOperacoes)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jpnBarraLateral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnOperacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jpnConexao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 37, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnBarraLateral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jpnConexao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpnOperacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void iniciaMenuExecutar() {
        menuExecutar.bota("Na Selecionada");
        menuExecutar.bota("Na Selecionada", "Importar TXT", new ActSelImportarTXT());
        menuExecutar.bota("Na Selecionada", "Comandos", new ActSelComandos());
        menuExecutar.bota("Na Selecionada", "Backup", new ActSelBackup());
        menuExecutar.bota("Na Selecionada", "Restore", new ActSelRestore());
        menuExecutar.bota("Em Todas");
        menuExecutar.bota("Em Todas", "Importar TXT", new ActTodImportarTXT());
        menuExecutar.bota("Em Todas", "Comandos", new ActTodComandos());
        menuExecutar.bota("Em Todas", "Backup", new ActTodBackup());
        menuExecutar.bota("Em Todas", "Restore", new ActTodRestore());
        menuExecutar.coloca(jbtExecutarConexoes);
    }

    public Migracao pMigracao() {
        return migracao;
    }

    private void limpaConexao() {
        jcbBanco.setSelectedIndex(0);
        jtfConNome.setText("");
        jtfServidor.setText("");
        jtfBase.setText("");
        jtfUsuario.setText("");
        jpfSenha.setText("");
    }

    private void limpaOperacao() {
        jcbTipo.setSelectedIndex(0);
        jtfOpeNome.setText("");
        jtaCodigo.setText("");
    }

    private void limpaTudo() {
        dlmConexoes.clear();
        migracao.conexoes.clear();
        dlmOperacoes.clear();
        migracao.operacoes.clear();
        migracao.tratamentos.clear();
        limpaConexao();
        limpaOperacao();
    }

    private void limpaTela() {
        while (dlmConexoes.size() > 0) {
            dlmConexoes.remove(0);
        }
        while (dlmOperacoes.size() > 0) {
            dlmOperacoes.remove(0);
        }
        limpaConexao();
        limpaOperacao();
    }

    public PointelMigre bota(Operacao aOperacao) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                migracao.operacoes.add(aOperacao);
                dlmOperacoes.addElement(aOperacao.nome);
            }
        });
        return this;
    }

    private void jbtConAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtConAdicionarActionPerformed
        ConexaoCfg nova = new ConexaoCfg();
        nova.nome = "conexão" + (dlmConexoes.getSize() + 1);
        migracao.conexoes.add(nova);
        dlmConexoes.addElement(nova.nome);
        jlsConexoes.setSelectedIndex(dlmConexoes.getSize() - 1);
    }//GEN-LAST:event_jbtConAdicionarActionPerformed

    private void jbtConRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtConRemoverActionPerformed
        int iSel = jlsConexoes.getSelectedIndex();
        if (iSel > -1) {
            if (Utl.continua()) {
                dlmConexoes.remove(iSel);
                migracao.conexoes.remove(iSel);
                limpaConexao();
            }
        }
        if (iSel < dlmConexoes.size()) {
            jlsConexoes.setSelectedIndex(iSel);
        }
    }//GEN-LAST:event_jbtConRemoverActionPerformed

    private void jbtConSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtConSalvarActionPerformed
        if (jtfConNome.getText().isEmpty()) {
            jtfConNome.setText("sem nome");
        }

        int iSel = jlsConexoes.getSelectedIndex();
        ConexaoCfg salvar = null;
        if (iSel > -1) {
            salvar = migracao.conexoes.get(iSel);
        } else {
            salvar = new ConexaoCfg();
        }
        salvar.banco = (String) jcbBanco.getSelectedItem();
        salvar.nome = jtfConNome.getText();
        salvar.servidor = jtfServidor.getText();
        salvar.porta = (Integer) jspPorta.getValue();
        salvar.base = jtfBase.getText();
        salvar.usuario = jtfUsuario.getText();
        salvar.senha = new String(jpfSenha.getPassword());
        if (iSel == -1) {
            migracao.conexoes.add(salvar);
            dlmConexoes.addElement(salvar.nome);
            jlsConexoes.setSelectedIndex(dlmConexoes.getSize() - 1);
        } else {
            dlmConexoes.setElementAt(salvar.nome, iSel);
        }
    }//GEN-LAST:event_jbtConSalvarActionPerformed

    private void jlsConexoesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlsConexoesValueChanged
        limpaConexao();
        int iSel = jlsConexoes.getSelectedIndex();
        if (iSel > -1) {
            ConexaoCfg abrir = migracao.conexoes.get(iSel);
            jcbBanco.setSelectedItem(abrir.banco);
            jtfConNome.setText(abrir.nome);
            jtfServidor.setText(abrir.servidor);
            jspPorta.setValue(abrir.porta);
            jtfBase.setText(abrir.base);
            jtfUsuario.setText(abrir.usuario);
            jpfSenha.setText(abrir.senha);
        }
    }//GEN-LAST:event_jlsConexoesValueChanged

    private void jbtConCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtConCancelarActionPerformed
        if (Utl.continua()) {
            jlsConexoesValueChanged(null);
        }
    }//GEN-LAST:event_jbtConCancelarActionPerformed

    private void jbtOpeAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOpeAdicionarActionPerformed
        Operacao nova = new Operacao("Executar", "operação" + (dlmOperacoes.getSize() + 1), "");
        migracao.operacoes.add(nova);
        dlmOperacoes.addElement(nova.nome);
        jlsOperacoes.setSelectedIndex(dlmOperacoes.getSize() - 1);
        TrataIntf.posicionaNaSelecao(jlsOperacoes, jspOperacoes);
    }//GEN-LAST:event_jbtOpeAdicionarActionPerformed

    private void jbtOpeRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOpeRemoverActionPerformed
        int iSel = jlsOperacoes.getSelectedIndex();
        if (iSel > -1) {
            if (Utl.continua()) {
                dlmOperacoes.remove(iSel);
                migracao.operacoes.remove(iSel);
                limpaOperacao();
            }
        }
        if (iSel < dlmOperacoes.size()) {
            jlsOperacoes.setSelectedIndex(iSel);
        }
        TrataIntf.posicionaNaSelecao(jlsOperacoes, jspOperacoes);
    }//GEN-LAST:event_jbtOpeRemoverActionPerformed

    private void jbtOpeSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOpeSalvarActionPerformed
        if (jtfOpeNome.getText().isEmpty()) {
            jtfOpeNome.setText("sem nome");
        }

        int iSel = jlsOperacoes.getSelectedIndex();
        Operacao salvar = null;
        if (iSel > -1) {
            salvar = migracao.operacoes.get(iSel);
            salvar.tipo = (String) jcbTipo.getSelectedItem();
            salvar.nome = jtfOpeNome.getText();
            salvar.codigo = jtaCodigo.getText();
            dlmOperacoes.setElementAt(salvar.nome, iSel);
        } else {
            salvar = new Operacao((String) jcbTipo.getSelectedItem(), jtfOpeNome.getText(), jtaCodigo.getText());
            migracao.operacoes.add(salvar);
            dlmOperacoes.addElement(salvar.nome);
            jlsOperacoes.setSelectedIndex(dlmOperacoes.getSize() - 1);
        }
        TrataIntf.posicionaNaSelecao(jlsOperacoes, jspOperacoes);
    }//GEN-LAST:event_jbtOpeSalvarActionPerformed

    private void jlsOperacoesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlsOperacoesValueChanged
        limpaOperacao();
        int iSel = jlsOperacoes.getSelectedIndex();
        if (iSel > -1) {
            Operacao abrir = migracao.operacoes.get(iSel);
            jcbTipo.setSelectedItem(abrir.tipo);
            jtfOpeNome.setText(abrir.nome);
            jtaCodigo.setText(abrir.codigo);
        }
    }//GEN-LAST:event_jlsOperacoesValueChanged

    private void jbtOpeCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOpeCancelarActionPerformed
        if (Utl.continua()) {
            jlsConexoesValueChanged(null);
        }
        TrataIntf.posicionaNaSelecao(jlsOperacoes, jspOperacoes);
    }//GEN-LAST:event_jbtOpeCancelarActionPerformed

    private void jbtNovaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtNovaActionPerformed
        if (Utl.continua()) {
            limpaTudo();
        }
    }//GEN-LAST:event_jbtNovaActionPerformed

    private void jbtAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAbrirActionPerformed
        if (selecionador.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File fl = selecionador.getSelectedFile();
            abre(fl.getAbsolutePath());
        }
    }//GEN-LAST:event_jbtAbrirActionPerformed

    public void salvarAlteracoes() {
        if (jlsConexoes.getSelectedIndex() > -1) {
            jbtConSalvarActionPerformed(null);
        }
        if (jlsOperacoes.getSelectedIndex() > -1) {
            jbtOpeSalvarActionPerformed(null);
        }
    }

    private void jbtSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSalvarActionPerformed
        if (selecionador.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File fl = selecionador.getSelectedFile();
            if (!fl.getAbsolutePath().endsWith(".pmi")) {
                fl = new File(fl.getAbsolutePath() + ".pmi");
            }
            salvarAlteracoes();

            try {
                migracao.salva(fl);
                String rcnt = cfgs.pegaCrs("PointelMigre - Recentes", "");
                String[] rcnts = Partes.pega(rcnt);
                if (rcnts != null) {
                    if (!UtlLis.tem(fl.getAbsolutePath(), rcnts)) {
                        cfgs.botaCrs("PointelMigre - Recentes", Partes.bota(rcnt, fl.getAbsolutePath()));
                    }
                } else {
                    cfgs.botaCrs("PointelMigre - Recentes", Partes.bota("", fl.getAbsolutePath()));
                }
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }//GEN-LAST:event_jbtSalvarActionPerformed

    private void jbtOpeAssistenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOpeAssistenteActionPerformed
        jbtOpeSalvarActionPerformed(null);
        int iOpe = jlsOperacoes.getSelectedIndex();

        Operacao oper = migracao.operacoes.get(iOpe);
        if (jcbTipo.getSelectedItem().equals("Executar")) {
            new ExecutarIntf(this, oper).setVisible(true);
        } else if (jcbTipo.getSelectedItem().equals("Executar Para Cada")) {
            new ExecutarParaCadaIntf(this, oper).setVisible(true);
        }
        TrataIntf.posicionaNaSelecao(jlsOperacoes, jspOperacoes);
    }//GEN-LAST:event_jbtOpeAssistenteActionPerformed

    private void jbtExecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtExecutarActionPerformed
        salvarAlteracoes();
        Migracao mig = new Migracao();
        mig.conexoes = new ArrayList<>(migracao.conexoes);
        mig.operacoes = new ArrayList<>(migracao.operacoes);
        mig.tratamentos = new ArrayList<>(migracao.tratamentos);
        new MigracaoExc(mig, true).salvaAoTerminar().inicia();
    }//GEN-LAST:event_jbtExecutarActionPerformed

    private void jbtOpeUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOpeUpActionPerformed
        int sel = jlsOperacoes.getSelectedIndex();
        if (sel > 0) {
            Operacao ax = migracao.operacoes.get(sel - 1);
            migracao.operacoes.set(sel - 1, migracao.operacoes.get(sel));
            migracao.operacoes.set(sel, ax);
            Object aux = dlmOperacoes.get(sel - 1);
            dlmOperacoes.set(sel - 1, dlmOperacoes.get(sel));
            dlmOperacoes.set(sel, aux);
            jlsOperacoes.setSelectedIndex(sel - 1);
        }
        TrataIntf.posicionaNaSelecao(jlsOperacoes, jspOperacoes);
    }//GEN-LAST:event_jbtOpeUpActionPerformed

    private void jbtOpeDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOpeDownActionPerformed
        int sel = jlsOperacoes.getSelectedIndex();
        if (sel > -1) {
            if (sel < dlmOperacoes.getSize() - 1) {
                Operacao ax = migracao.operacoes.get(sel + 1);
                migracao.operacoes.set(sel + 1, migracao.operacoes.get(sel));
                migracao.operacoes.set(sel, ax);

                Object aux = dlmOperacoes.get(sel + 1);
                dlmOperacoes.set(sel + 1, dlmOperacoes.get(sel));
                dlmOperacoes.set(sel, aux);
                jlsOperacoes.setSelectedIndex(sel + 1);
            }
        }
        TrataIntf.posicionaNaSelecao(jlsOperacoes, jspOperacoes);
    }//GEN-LAST:event_jbtOpeDownActionPerformed

    private void jbtConTestarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtConTestarActionPerformed
        try {
            Conexao con = new Conexao("Testar " + jtfConNome.getText());
            con.configura(Bancos.Tp.valueOf((String) jcbBanco.getSelectedItem()), jtfServidor.getText(), (Integer) jspPorta.getValue(), jtfBase.getText(), jtfUsuario.getText(), new String(jpfSenha.getPassword()));
            con.conecta();
            if (con.estaConectado()) {
                Utl.alerta("Conexão Realizada com Sucesso.");
            }
            con.desconecta();
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jbtConTestarActionPerformed

    public void abre(String oArquivo) {
        try {
            limpaTela();
            migracao = Migracao.abre(new File(oArquivo));

            if (migracao.conexoes == null) {
                migracao.conexoes = new ArrayList<>();
            }
            if (migracao.operacoes == null) {
                migracao.operacoes = new ArrayList<>();
            }
            if (migracao.tratamentos == null) {
                migracao.tratamentos = new ArrayList<>();
            }

            for (ConexaoCfg con : migracao.conexoes) {
                dlmConexoes.addElement(con.nome);
            }
            for (Operacao ope : migracao.operacoes) {
                dlmOperacoes.addElement(ope.nome);
            }

            String rcnt = cfgs.pegaCrs("PointelMigre - Recentes", "");
            String[] rcnts = Partes.pega(rcnt);
            if (rcnts != null) {
                if (!UtlLis.tem(oArquivo, rcnts)) {
                    cfgs.botaCrs("PointelMigre - Recentes", Partes.bota(rcnt, oArquivo));
                }
            } else {
                cfgs.botaCrs("PointelMigre - Recentes", Partes.bota("", oArquivo));
            }

        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }

    private void jbtRecentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRecentesActionPerformed
        try {
            String rcnt = cfgs.pegaCrs("PointelMigre - Recentes", "");
            String[] rcnts = Partes.pega(rcnt);
            if (rcnts != null) {
                Boolean teve = false;
                PopMenu menu = new PopMenu();
                for (String rnt : rcnts) {
                    if (!rnt.isEmpty()) {
                        teve = true;
                        menu.bota(rnt, new AbstractAction() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                abre(rnt);
                                selecionador.setSelectedFile(new File(rnt));
                            }
                        });
                    }
                }
                if (teve) {
                    menu.botaSeparador();
                    menu.bota("Limpar", new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            cfgs.botaCrs("PointelMigre - Recentes", "");
                            Utl.alerta("PointelMigre Recentes Limpado");
                        }
                    });
                    menu.mostra(jbtRecentes);
                } else {
                    Utl.alerta("Nenhum PointelMigre Recente Encontrado");
                }
            }
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jbtRecentesActionPerformed

    private void jbtMagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtMagoActionPerformed
        new Mago(this).setVisible(true);
    }//GEN-LAST:event_jbtMagoActionPerformed

    private void jlbOperacoesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbOperacoesMouseClicked
        if (evt.getClickCount() >= 2) {
            String proc = UtlCrs.pergunta("Procurar Por");
            if (proc != null) {
                boolean achou = false;
                for (int i1 = jlsOperacoes.getSelectedIndex() + 1; i1 < dlmOperacoes.getSize(); i1++) {
                    if (dlmOperacoes.get(i1).toString().contains(proc)) {
                        jlsOperacoes.setSelectedIndex(i1);
                        TrataIntf.posicionaNaSelecao(jlsOperacoes, jspOperacoes);
                        achou = true;
                        break;
                    }
                }
                if (!achou) {
                    for (int i1 = 0; i1 < dlmOperacoes.getSize(); i1++) {
                        if (dlmOperacoes.get(i1).toString().contains(proc)) {
                            jlsOperacoes.setSelectedIndex(i1);
                            TrataIntf.posicionaNaSelecao(jlsOperacoes, jspOperacoes);
                            break;
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_jlbOperacoesMouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        try {
            Recursos mainRecs = (Recursos) Globais.pega("mainRecs");
            mainRecs.verificaRestricao(this, "PointelMigre");
        } catch (Exception ex) {
            Utl.registra(ex);
            Janelas.fecha(this);
        }
    }//GEN-LAST:event_formWindowActivated

    private void jbtConDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtConDownActionPerformed
        int sel = jlsConexoes.getSelectedIndex();
        if (sel > -1) {
            if (sel < dlmConexoes.getSize() - 1) {
                ConexaoCfg ax = migracao.conexoes.get(sel + 1);
                migracao.conexoes.set(sel + 1, migracao.conexoes.get(sel));
                migracao.conexoes.set(sel, ax);

                Object aux = dlmConexoes.get(sel + 1);
                dlmConexoes.set(sel + 1, dlmConexoes.get(sel));
                dlmConexoes.set(sel, aux);
                jlsConexoes.setSelectedIndex(sel + 1);
            }
        }
        TrataIntf.posicionaNaSelecao(jlsConexoes, jspConexoes);
    }//GEN-LAST:event_jbtConDownActionPerformed

    private void jbtConUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtConUpActionPerformed
        int sel = jlsConexoes.getSelectedIndex();
        if (sel > 0) {
            ConexaoCfg ax = migracao.conexoes.get(sel - 1);
            migracao.conexoes.set(sel - 1, migracao.conexoes.get(sel));
            migracao.conexoes.set(sel, ax);

            Object aux = dlmConexoes.get(sel - 1);
            dlmConexoes.set(sel - 1, dlmConexoes.get(sel));
            dlmConexoes.set(sel, aux);
            jlsConexoes.setSelectedIndex(sel - 1);
        }
        TrataIntf.posicionaNaSelecao(jlsConexoes, jspConexoes);
    }//GEN-LAST:event_jbtConUpActionPerformed

    private void jbtBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtBaseActionPerformed
        File arq = UtlArq.abreArq();
        if (arq != null) {
            jtfBase.setText(arq.getAbsolutePath());
        }
    }//GEN-LAST:event_jbtBaseActionPerformed

    private void jbtAuxiliarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAuxiliarActionPerformed
        new ConAuxiliar().mostra();
    }//GEN-LAST:event_jbtAuxiliarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtAbrir;
    private javax.swing.JButton jbtAuxiliar;
    private javax.swing.JButton jbtBase;
    private javax.swing.JButton jbtConAdicionar;
    private javax.swing.JButton jbtConCancelar;
    private javax.swing.JButton jbtConDown;
    private javax.swing.JButton jbtConRemover;
    private javax.swing.JButton jbtConSalvar;
    private javax.swing.JButton jbtConTestar;
    private javax.swing.JButton jbtConUp;
    private javax.swing.JButton jbtExecutar;
    private javax.swing.JButton jbtExecutarConexoes;
    private javax.swing.JButton jbtMago;
    private javax.swing.JButton jbtNova;
    private javax.swing.JButton jbtOpeAdicionar;
    private javax.swing.JButton jbtOpeAssistente;
    private javax.swing.JButton jbtOpeCancelar;
    private javax.swing.JButton jbtOpeDown;
    private javax.swing.JButton jbtOpeRemover;
    private javax.swing.JButton jbtOpeSalvar;
    private javax.swing.JButton jbtOpeUp;
    private javax.swing.JButton jbtRecentes;
    private javax.swing.JButton jbtSalvar;
    private javax.swing.JComboBox<String> jcbBanco;
    private javax.swing.JComboBox<String> jcbTipo;
    private javax.swing.JLabel jlbBanco;
    private javax.swing.JLabel jlbBase;
    private javax.swing.JLabel jlbConNome;
    private javax.swing.JLabel jlbConexoes;
    private javax.swing.JLabel jlbOpeNome;
    private javax.swing.JLabel jlbOperacoes;
    private javax.swing.JLabel jlbPorta;
    private javax.swing.JLabel jlbSenha;
    private javax.swing.JLabel jlbServidor;
    private javax.swing.JLabel jlbTipo;
    private javax.swing.JLabel jlbUsuario;
    private javax.swing.JList<String> jlsConexoes;
    public javax.swing.JList<String> jlsOperacoes;
    private javax.swing.JPasswordField jpfSenha;
    private javax.swing.JPanel jpnBarraLateral;
    private javax.swing.JPanel jpnConexao;
    private javax.swing.JPanel jpnOperacao;
    private javax.swing.JScrollPane jspCodigo;
    private javax.swing.JScrollPane jspConexoes;
    private javax.swing.JScrollPane jspOperacoes;
    private javax.swing.JSpinner jspPorta;
    public javax.swing.JTextArea jtaCodigo;
    private javax.swing.JTextField jtfBase;
    private javax.swing.JTextField jtfConNome;
    public javax.swing.JTextField jtfOpeNome;
    private javax.swing.JTextField jtfServidor;
    private javax.swing.JTextField jtfUsuario;
    // End of variables declaration//GEN-END:variables

    private class ActSelComandos extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            int sel = jlsConexoes.getSelectedIndex();
            if (sel > -1) {
                try {
                    ConexaoCfg cng = migracao.conexoes.get(sel);
                    TexEtf excCon = new TexEtf();
                    excCon.constroi();
                    excCon.edt().pPopMenu().botaSeparador();
                    excCon.edt().pPopMenu().bota("Executar", new ActSelComandosExc(cng, excCon)).botaAtalho(excCon.edt(), "control shift ENTER");
                    excCon.mostra("Executar Na Conexão " + cng.nome);
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        }
    }

    private class ActSelImportarTXT extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            int sel = jlsConexoes.getSelectedIndex();
            if (sel > -1) {
                try {
                    ConexaoCfg[] cons = new ConexaoCfg[]{migracao.conexoes.get(sel)};
                    new ImportarTXT(cons).mostra();
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        }
    }

    private class ActTodImportarTXT extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ConexaoCfg[] cons = migracao.conexoes.toArray(new ConexaoCfg[]{});
                new ImportarTXT(cons).mostra();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    private class ActSelComandosExc extends AbstractAction {

        private ConexaoCfg conexaoCfg;
        private TexEtf texIntf;

        public ActSelComandosExc(ConexaoCfg conexaoCfg, TexEtf texIntf) {
            this.conexaoCfg = conexaoCfg;
            this.texIntf = texIntf;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            new Thread("Executar na Conexão") {
                @Override
                public void run() {
                    try {
                        TexEtf txtRes = new TexEtf();
                        txtRes.mostra("Resultados das Execuções nas Conexões");
                        String comandos[] = texIntf.edt().pgVlr("").pgCrs().split("-prox-");
                        if (comandos != null) {
                            for (String comando : comandos) {
                                try {
                                    comando = comando.trim();
                                    if (!comando.isEmpty()) {
                                        txtRes.edt().controlador().botaLinha("------------------------------------");
                                        txtRes.edt().controlador().botaLinha("Executando:");
                                        txtRes.edt().controlador().botaLinha(comando);
                                        txtRes.edt().controlador().botaLinha("------------------------------------");

                                        Conexao con = new Conexao("Execução em " + conexaoCfg.nome);
                                        txtRes.edt().controlador().botaLinha("Conectando a " + conexaoCfg.nome);
                                        con.configura(Bancos.Tp.valueOf(conexaoCfg.banco), conexaoCfg.servidor, conexaoCfg.porta, conexaoCfg.base, conexaoCfg.usuario, conexaoCfg.senha);
                                        con.conecta();
                                        if (con.estaConectado()) {
                                            txtRes.edt().controlador().botaLinha("Conectado");
                                            txtRes.edt().controlador().botaLinha("Executando...");
                                            int afetados = con.opera(comando);
                                            if (afetados > -1) {
                                                txtRes.edt().controlador().botaLinha("Executado, afetou " + afetados + " registros.");
                                            } else {
                                                txtRes.edt().controlador().botaLinha("Gerrou um Erro.");
                                            }
                                        } else {
                                            txtRes.edt().controlador().botaLinha("Gerrou um Erro.");
                                        }
                                        con.desconecta();
                                        txtRes.edt().controlador().botaLinha("------------------------------------");

                                        txtRes.edt().controlador().botaLinha("Terminou de Executar");
                                    }
                                } catch (Exception ex) {
                                    Utl.registra(ex);
                                }
                            }
                        }
                    } catch (Exception ex) {
                        Utl.registra(ex);
                    }
                }
            }.start();
        }
    }

    private class ActTodComandos extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                TexEtf excCon = new TexEtf();
                excCon.constroi();
                excCon.edt().pPopMenu().botaSeparador();
                excCon.edt().pPopMenu().bota("Executar", new ActTodComandosExc(excCon)).botaAtalho(excCon.edt(), "control shift ENTER");
                excCon.mostra("Executar Em Todas Conexões");
            } catch (Exception ex) {
                Logger.getLogger(PointelMigre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private class ActTodComandosExc extends AbstractAction {

        private TexEtf texIntf;

        public ActTodComandosExc(TexEtf texIntf) {
            this.texIntf = texIntf;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            new Paralelo("Executar nas Conexões") {
                @Override
                public void run() {
                    try {
                        TexEtf txtRes = new TexEtf();
                        txtRes.mostra("Resultados das Execuções nas Conexões");
                        String comandos[] = texIntf.edt().pgVlr("").pgCrs().split("-prox-");
                        if (comandos != null) {
                            for (String comando : comandos) {
                                try {
                                    comando = comando.trim();
                                    if (!comando.isEmpty()) {
                                        txtRes.edt().controlador().botaLinha("------------------------------------");
                                        txtRes.edt().controlador().botaLinha("Executando:");
                                        txtRes.edt().controlador().botaLinha(comando);
                                        txtRes.edt().controlador().botaLinha("------------------------------------");

                                        for (ConexaoCfg cng : migracao.conexoes) {
                                            Conexao con = new Conexao("Execução em " + cng.nome);
                                            txtRes.edt().controlador().botaLinha("Conectando a " + cng.nome);
                                            con.configura(Bancos.Tp.valueOf(cng.banco), cng.servidor, cng.porta, cng.base, cng.usuario, cng.senha);
                                            con.conecta();
                                            if (con.estaConectado()) {
                                                txtRes.edt().controlador().botaLinha("Conectado");
                                                txtRes.edt().controlador().botaLinha("Executando...");
                                                int afetados = con.opera(comando);
                                                if (afetados > -1) {
                                                    txtRes.edt().controlador().botaLinha("Executado, afetou " + afetados + " registros.");
                                                } else {
                                                    txtRes.edt().controlador().botaLinha("Gerrou um Erro.");
                                                }
                                            } else {
                                                txtRes.edt().controlador().botaLinha("Gerrou um Erro.");
                                            }
                                            con.desconecta();
                                            txtRes.edt().controlador().botaLinha("------------------------------------");
                                        }

                                        txtRes.edt().controlador().botaLinha("Terminou de Executar em Todas as Conexões");
                                    }
                                } catch (Exception ex) {
                                    Utl.registra(ex);
                                }
                            }
                        }
                    } catch (Exception ex) {
                        Utl.registra(ex);
                    }
                }
            }.start();
        }
    }

    private class ActSelBackup extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            int sel = jlsConexoes.getSelectedIndex();
            if (sel > -1) {
                try {
                    migracao.fazBackup(sel);
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        }
    }

    private class ActTodBackup extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                migracao.fazBackupTodas();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    private class ActSelRestore extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            int sel = jlsConexoes.getSelectedIndex();
            if (sel > -1) {
                try {
                    migracao.fazRestore(sel);
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        }

    }

    private class ActTodRestore extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                migracao.fazRestoreTodas();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }

    }
}
