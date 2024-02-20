package pin.modinf;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import pin.libbas.Partes;
import pin.libjan.Janelas;
import pin.libjan.TrataIntf;
import pin.libpic.Pics;
import pin.libutl.Utl;
import pin.libutl.UtlArq;
import pin.libutl.UtlCrs;
import pin.libutl.UtlRed;
import pin.libutl.UtlTex;
import pin.modpim.Pims;

public class ConectaBase extends javax.swing.JDialog {

    private Boolean conectado;

    private final Conexao conexao;
    private final JFrame associada;

    private List<String> perfis;
    private String perfil;

    private String conServidor;
    private Integer conPorta;
    private String conBanco;
    private String conBase;
    private String conUsuario;
    private String conSenha;

    private JPopupMenu jpmMenu;
    private JMenuItem jmiLocal;
    private JMenuItem jmiRemoto;
    private JMenu jmnPerfil;
    private JMenuItem jmiPerNovo;
    private JMenuItem jmiPerExcluir;

    public ConectaBase(JFrame comAssociada, Conexao eConexao) throws Exception {
        super(comAssociada, true);
        setTitle("Conexão " + eConexao.pegaNome());
        initComponents();
        setLocationRelativeTo(null);
        setIconImage(Pims.pega("Conexao.png").getImage());
        jlbIcone.setIcon(Pims.pega("Conexao.png"));
        jbtVer.setIcon(Pics.pega("eye.png"));
        jbtEditar.setIcon(Pics.pega("pencil.png"));
        jbtMenu.setIcon(Pics.pega("folder_table.png"));
        conectado = false;
        associada = comAssociada;
        conexao = eConexao;
        perfis = null;
        perfil = null;
        conServidor = null;
        conPorta = null;
        conBanco = null;
        conBase = null;
        conUsuario = null;
        conSenha = null;
        iniciaCfgs();
        iniciaMenu();
        Janelas.botaAtalho(this, "Ver", "alt V", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jbtVerActionPerformed(e);
            }
        });
        Janelas.botaAtalho(this, "Editar", "alt E", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jbtEditarActionPerformed(e);
            }
        });
        Janelas.botaAtalho(this, "Menu", "alt M", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jbtMenuActionPerformed(e);
            }
        });
        getRootPane().setDefaultButton(jbtConectar);
        Janelas.inicia(this);
    }

    public void iniciaCfgs() {
        try {
            pegaPerfis();
            botaPerfil(conexao.cfgs().pegaCrs(conexao.pegaNome() + " Conexão - Perfil", "Principal"));
            botaConBanco(conexao.cfgs().pegaCrs(conexao.pegaNome() + " Conexão - ConBanco " + perfil, "PostgreSQL"));
            botaConServidor(conexao.cfgs().pegaCrs(conexao.pegaNome() + " Conexão - ConServidor " + perfil, "pointel.pointto.us"));
            botaConPorta(conexao.cfgs().pegaInt(conexao.pegaNome() + " Conexão - ConPorta " + perfil, 5432));
            botaConBase(conexao.cfgs().pegaCrs(conexao.pegaNome() + " Conexão - ConBase " + perfil, "minhaintel"));
            botaConUsuario(conexao.cfgs().pegaCrs(conexao.pegaNome() + " Conexão - ConUsuário " + perfil, "minhaintel"));
            botaConSenha(conexao.cfgs().pegaCrsCrpt(conexao.pegaNome() + " Conexão - ConSenha " + perfil, "mnhitl158"));
            botaUsuario(conexao.cfgs().pegaCrs(conexao.pegaNome() + " Conexão - Usuário " + perfil, ""));
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }

    private void iniciaMenu() {
        jpmMenu = new JPopupMenu();
        jmiLocal = new JMenuItem("Local");
        jmiLocal.addActionListener(new ActLocal());
        jpmMenu.add(jmiLocal);
        jmiRemoto = new JMenuItem("Remoto");
        jmiRemoto.addActionListener(new ActRemoto());
        jpmMenu.add(jmiRemoto);
        jmnPerfil = new JMenu("Perfil");
        jpmMenu.add(jmnPerfil);
        iniciaMenuPerfis();
    }

    private void iniciaMenuPerfis() {
        jmnPerfil.removeAll();
        jmiPerNovo = new JMenuItem("Novo");
        jmiPerNovo.addActionListener(new ActPerNovo());
        jmnPerfil.add(jmiPerNovo);
        jmiPerExcluir = new JMenuItem("Excluir");
        jmiPerExcluir.addActionListener(new ActPerExcluir());
        jmnPerfil.add(jmiPerExcluir);
        jmnPerfil.addSeparator();
        for (String pfl : perfis) {
            JMenuItem jmiPerItem = new JMenuItem(pfl);
            jmiPerItem.addActionListener(new ActPerItem());
            jmnPerfil.add(jmiPerItem);
        }
    }

    public void mostra() {
        Janelas.mostra(this, false);
    }

    public List<String> pegaPerfis() {
        if (perfis == null) {
            perfis = Partes.pegaLis(conexao.cfgs().pegaCrs(conexao.pegaNome() + " Conexão - Perfis"));
            if (perfis == null) {
                perfis = new ArrayList<>(Arrays.asList(new String[]{"Principal"}));
            } else if (perfis.isEmpty()) {
                perfis = new ArrayList<>(Arrays.asList(new String[]{"Principal"}));
            }
        }
        return perfis;
    }

    public void botaPerfis() {
        conexao.cfgs().botaCrs(conexao.pegaNome() + " Conexão - Perfis", Partes.junta(perfis));
    }

    public String pegaPerfil() {
        conexao.cfgs().botaCrs(conexao.pegaNome() + " Conexão - Perfil", perfil);
        return perfil;
    }

    public void botaPerfil(String oPerfil) {
        perfil = oPerfil;
        conexao.cfgs().botaCrs(conexao.pegaNome() + " Conexão - Perfil", oPerfil);
        jlbPerfil.setText("Perfil: " + perfil);
    }

    public Bancos.Tp pegaConBanco() {
        conexao.cfgs().botaCrs(conexao.pegaNome() + " Conexão - ConBanco " + perfil, conBanco);
        return Bancos.Tp.valueOf(conBanco);
    }

    public void botaConBanco(String oBanco) {
        conBanco = oBanco;
        conexao.cfgs().botaCrs(conexao.pegaNome() + " Conexão - ConBanco " + perfil, oBanco);
    }

    public String pegaConServidor() {
        conexao.cfgs().botaCrs(conexao.pegaNome() + " Conexão - ConServidor " + perfil, conServidor);
        return conServidor;
    }

    public void botaConServidor(String oServidor) {
        conServidor = oServidor;
        conexao.cfgs().botaCrs(conexao.pegaNome() + " Conexão - ConServidor " + perfil, oServidor);
    }

    public Integer pegaConPorta() {
        conexao.cfgs().botaInt(conexao.pegaNome() + " Conexão - ConPorta " + perfil, conPorta);
        return conPorta;
    }

    public void botaConPorta(Integer aPorta) {
        conPorta = aPorta;
        conexao.cfgs().botaInt(conexao.pegaNome() + " Conexão - ConPorta " + perfil, aPorta);
    }

    public String pegaConBase() {
        conexao.cfgs().botaCrs(conexao.pegaNome() + " Conexão - ConBase " + perfil, conBase);
        return conBase;
    }

    public void botaConBase(String aBase) {
        conBase = aBase;
        conexao.cfgs().botaCrs(conexao.pegaNome() + " Conexão - ConBase " + perfil, aBase);
    }

    public String pegaConUsuario() {
        conexao.cfgs().botaCrs(conexao.pegaNome() + " Conexão - ConUsuário " + perfil, conUsuario);
        return conUsuario;
    }

    public void botaConUsuario(String oUsuario) {
        conUsuario = oUsuario;
        conexao.cfgs().botaCrs(conexao.pegaNome() + " Conexão - ConUsuário " + perfil, oUsuario);
    }

    public String pegaConSenha() {
        try {
            conexao.cfgs().botaCrsCrpt(conexao.pegaNome() + " Conexão - ConSenha " + perfil, conSenha);
        } catch (Exception ex) {
            Utl.registra(ex);
        }
        return conSenha;
    }

    public void botaConSenha(String aSenha) {
        conSenha = aSenha;
        try {
            conexao.cfgs().botaCrsCrpt(conexao.pegaNome() + " Conexão - ConSenha " + perfil, aSenha);
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }

    public String pegaUsuario() {
        conexao.cfgs().botaCrs(conexao.pegaNome() + " Conexão - Usuário " + perfil, jtfUsuario.getText());
        return jtfUsuario.getText();
    }

    public void botaUsuario(String oUsuario) {
        jtfUsuario.setText(oUsuario);
        conexao.cfgs().botaCrs(conexao.pegaNome() + " Conexão - Usuário " + perfil, oUsuario);
    }

    public String pegaSenha() {
        return new String(jpfSenha.getPassword());
    }

    public void botaSenha(String aSenha) {
        jpfSenha.setText(aSenha);
    }

    public Boolean foiConectado() {
        return conectado;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlbIcone = new javax.swing.JLabel();
        jlbPerfil = new javax.swing.JLabel();
        jlbUsuario = new javax.swing.JLabel();
        jtfUsuario = new javax.swing.JTextField();
        jlbSenha = new javax.swing.JLabel();
        jpfSenha = new javax.swing.JPasswordField();
        jbtAjuda = new javax.swing.JButton();
        jbtVer = new javax.swing.JButton();
        jbtEditar = new javax.swing.JButton();
        jbtMenu = new javax.swing.JButton();
        jbtConectar = new javax.swing.JButton();
        jbtCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Conectar");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jlbPerfil.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jlbPerfil.setText("Perfil: Principal");

        jlbUsuario.setText("Usuário:");

        jlbSenha.setText("Senha:");

        jbtAjuda.setText("Ajuda");
        jbtAjuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAjudaActionPerformed(evt);
            }
        });

        jbtVer.setToolTipText("Ver (alt V)");
        jbtVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtVerActionPerformed(evt);
            }
        });

        jbtEditar.setToolTipText("Editar (alt E)");
        jbtEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtEditarActionPerformed(evt);
            }
        });

        jbtMenu.setToolTipText("Menu (alt M)");
        jbtMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtMenuActionPerformed(evt);
            }
        });

        jbtConectar.setText("Conectar");
        jbtConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtConectarActionPerformed(evt);
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
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jlbUsuario)
                                .addComponent(jtfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlbIcone, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(113, 113, 113)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jpfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlbSenha)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jbtAjuda))
                            .addComponent(jlbPerfil)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jbtVer, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtConectar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtCancelar)))
                .addGap(33, 33, 33))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbtAjuda)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbIcone, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbPerfil))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbUsuario)
                            .addComponent(jlbSenha))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtConectar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtVer, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void verificaFechar() {
        if (!conectado) {
            if (conexao.paraFecharJanelaSeCancelar()) {
                Janelas.fecha(conexao.janelaAssociada());
            }
        } else {
            TrataIntf.garanteFoco(conexao.janelaAssociada(), this);
        }
    }

    private void jbtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelarActionPerformed
        Janelas.fecha(this);
    }//GEN-LAST:event_jbtCancelarActionPerformed

    private void jbtConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtConectarActionPerformed
        conectado = false;
        try {
            if (pegaUsuario().isEmpty() || pegaSenha().isEmpty()) {
                throw new Exception("Necessário o Nome do Usuário");
            }
            conexao.configura(pegaConBanco(), pegaConServidor(), pegaConPorta(), pegaConBase(), pegaConUsuario(), pegaConSenha(), pegaUsuario(), pegaSenha());
            if (conexao.conecta()) {
                conectado = true;
                Janelas.fecha(this);
            } else {
                TrataIntf.garanteFoco(jpfSenha, true);
            }
        } catch (Exception ex) {
            Utl.registra(ex);
            TrataIntf.garanteFoco(jpfSenha, true);
        }
    }//GEN-LAST:event_jbtConectarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        verificaFechar();
    }//GEN-LAST:event_formWindowClosing

    private void jbtAjudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAjudaActionPerformed
        try {
            new ConectaBaseAjuda(associada).setVisible(true);
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jbtAjudaActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        JComponent paraFocar = jtfUsuario;
        if (!jtfUsuario.getText().isEmpty()) {
            paraFocar = jpfSenha;
        }
        TrataIntf.garanteFoco(paraFocar);
    }//GEN-LAST:event_formWindowActivated

    private void jbtEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtEditarActionPerformed
        new ConectaBaseCfg(this).mostra();
    }//GEN-LAST:event_jbtEditarActionPerformed

    private void jbtVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtVerActionPerformed
        String msg = "Banco: " + pegaConBanco().toString();
        msg += "\nServidor: " + pegaConServidor();
        msg += "\nPorta: " + pegaConPorta();
        msg += "\nBase: " + pegaConBase();
        msg += "\nUsuário: " + pegaConUsuario();
        Utl.alerta(msg);
    }//GEN-LAST:event_jbtVerActionPerformed

    private void jbtMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtMenuActionPerformed
        jpmMenu.show(jbtMenu, 0, jbtMenu.getHeight());
    }//GEN-LAST:event_jbtMenuActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtAjuda;
    private javax.swing.JButton jbtCancelar;
    private javax.swing.JButton jbtConectar;
    private javax.swing.JButton jbtEditar;
    private javax.swing.JButton jbtMenu;
    private javax.swing.JButton jbtVer;
    private javax.swing.JLabel jlbIcone;
    private javax.swing.JLabel jlbPerfil;
    private javax.swing.JLabel jlbSenha;
    private javax.swing.JLabel jlbUsuario;
    private javax.swing.JPasswordField jpfSenha;
    private javax.swing.JTextField jtfUsuario;
    // End of variables declaration//GEN-END:variables

    private class ActLocal extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                File arq = UtlArq.abreArq("Configs (*.cgx) ", "cgx");
                if (arq != null) {
                    conexao.cfgs().carrega(arq);
                    iniciaCfgs();
                    iniciaMenuPerfis();
                }
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    private class ActRemoto extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String tex = UtlRed.baixaTex();
                if (tex != null) {
                    String[] lns = UtlTex.pLinhas(tex);
                    for (String ln : lns) {
                        conexao.cfgs().carrega(ln);
                    }
                    iniciaCfgs();
                    Utl.alerta("Arquivo de configuração remoto carregado.");
                }
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    private class ActPerNovo extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String nv = UtlCrs.pergunta("Novo Perfil");
                if (nv != null) {
                    if (perfis.contains(nv)) {
                        throw new Exception("Já Existe um Perfil com esse Nome");
                    }
                    perfis.add(nv);
                    botaPerfis();
                    JMenuItem jmiPerItem = new JMenuItem(nv);
                    jmiPerItem.addActionListener(new ActPerItem());
                    jmnPerfil.add(jmiPerItem);
                    botaPerfil(nv);
                    iniciaCfgs();
                }
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    private class ActPerExcluir extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (perfis.size() <= 1) {
                    throw new Exception("Não é Possível Excluir o Último Perfil");
                }
                conexao.cfgs().tira(conexao.pegaNome() + " Conexão - ConBanco " + perfil);
                conexao.cfgs().tira(conexao.pegaNome() + " Conexão - ConServidor " + perfil);
                conexao.cfgs().tira(conexao.pegaNome() + " Conexão - ConPorta " + perfil);
                conexao.cfgs().tira(conexao.pegaNome() + " Conexão - ConBase " + perfil);
                conexao.cfgs().tira(conexao.pegaNome() + " Conexão - ConUsuário " + perfil);
                conexao.cfgs().tira(conexao.pegaNome() + " Conexão - ConSenha " + perfil);
                conexao.cfgs().tira(conexao.pegaNome() + " Conexão - Usuário " + perfil);
                perfis.remove(perfil);
                botaPerfis();
                jmnPerfil.removeAll();
                iniciaMenuPerfis();
                botaPerfil(perfis.get(0));
                iniciaCfgs();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    private class ActPerItem extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            JMenuItem jmiItem = (JMenuItem) e.getSource();
            botaPerfil(jmiItem.getText());
            iniciaCfgs();
        }
    }

}
