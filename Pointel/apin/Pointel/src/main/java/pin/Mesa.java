package pin;

import java.awt.BorderLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FilenameFilter;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import pin.libetf.AprEtf;
import pin.libetf.AudEtf;
import pin.libetf.DocEtf;
import pin.libetf.ForEtf;
import pin.libetf.FrmEtf;
import pin.libetf.ImaEtf;
import pin.libetf.LisEtf;
import pin.libetf.PlaEtf;
import pin.libetf.TexEtf;
import pin.libetf.VidEtf;
import pin.libamk.JanelasIntf;
import pin.libbas.Globais;
import pin.libjan.JanEspeciais;
import pin.libjan.Janelas;
import pin.libutl.Utl;
import pin.libutl.UtlArq;
import pin.libutl.UtlBin;
import pin.libutl.UtlCrs;
import pin.modamk.Recurso;
import pin.modinf.ConexaoVer;

public class Mesa extends javax.swing.JFrame {

    private Rectangle area;
    private Lancador lancador;
    private Point ultimoClique;

    public Mesa() {
        initComponents();
        setLayout(new BorderLayout());
        if (!"Pointel".equals(Pointel.instancia)) {
            setTitle(getTitle() + " " + Pointel.instancia);
        }
        iniciaInstancias();
        iniciaMonitores();
        iniciaLancador();
        JanEspeciais janIntf = (JanEspeciais) Globais.pega("janelasIntf");
        if (janIntf != null) {
            janIntf.botaIcone(this, getClass());
            janIntf.botaAtalhos(this, getClass());
        }
        Janelas.botaAtalho(this, "Novo Arquivo de Lista", "control alt L", new ActNovArqLis());
        Janelas.botaAtalho(this, "Novo Arquivo de Texto", "control alt T", new ActNovArqTex());
        Janelas.botaAtalho(this, "Novo Arquivo de Imagem", "control alt I", new ActNovArqIma());
        Janelas.botaAtalho(this, "Novo Arquivo de Aúdio", "control alt A", new ActNovArqAud());
        Janelas.botaAtalho(this, "Novo Arquivo de Vídeo", "control alt V", new ActNovArqVid());
        Janelas.botaAtalho(this, "Novo Arquivo de Forma", "control alt F", new ActNovArqFor());
        Janelas.botaAtalho(this, "Novo Arquivo de Planilha", "control alt P", new ActNovArqPla());
        Janelas.botaAtalho(this, "Novo Arquivo de Documento", "control alt D", new ActNovArqDoc());
        Janelas.botaAtalho(this, "Novo Arquivo de Formulário", "control alt R", new ActNovArqFrm());
        Janelas.botaAtalho(this, "Novo Arquivo de Apresentação", "control alt S", new ActNovArqApr());
    }

    private void iniciaInstancias() {
        try {
            File pinDir = UtlArq.pegaPointelDir();
            File[] insts = pinDir.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    if (name.endsWith(".cgs")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });
            if (insts != null) {
                if (insts.length > 0) {
                    jmnInstancias.addSeparator();
                    for (File inst : insts) {
                        String bnam = UtlArq.pNomeBasico(inst.getName());
                        JMenuItem jmiInst = new JMenuItem(bnam);
                        jmiInst.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                abreInstancia(bnam);
                            }
                        });
                        jmnInstancias.add(jmiInst);
                    }
                }
            }
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }

    private void iniciaMonitores() {
        setBounds(0, 0, 0, 0);
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = env.getScreenDevices();
        area = null;
        int im = 0;
        if (gs != null) {
            if (gs.length > 0) {
                Mesa.this.setBounds(gs[0].getDefaultConfiguration().getBounds());
                area = Mesa.this.getBounds();
                Globais.bota("mainArea", area);
            }
            for (GraphicsDevice gd : gs) {
                JMenuItem jmiMonitor = new JMenuItem("Monitor " + im);
                jmiMonitor.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            Mesa.this.setBounds(gd.getDefaultConfiguration().getBounds());
                            area = Mesa.this.getBounds();
                            Globais.bota("mainArea", area);
                        } catch (Exception ex) {
                            Utl.registra(ex);
                        }
                    }
                });
                jmnMonitores.add(jmiMonitor);
                im++;
            }
        }
        if (area == null) {
            area = env.getMaximumWindowBounds();
        }
        if (im <= 1) {
            jpmMesa.remove(jmnMonitores);
        }
    }

    private void iniciaLancador() {
        lancador = new Lancador();
        lancador.addMouseListener(new MouLancador());
        add(lancador, BorderLayout.CENTER);
    }

    private void atualizaMenuExecutar(List<Recurso> daLista, JMenu noMenu) {
        if (daLista != null) {
            for (Recurso ico : daLista) {
                if (ico.estaDisponivel()) {
                    if (ico.pegaClasse() != null) {
                        JMenuItem jmiIco = new JMenuItem(ico.pegaNome());
                        jmiIco.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    ico.executa();
                                } catch (Exception ex) {
                                    Utl.registra(ex);
                                }
                            }
                        });
                        noMenu.add(jmiIco);
                        if (ico.temFilhoDisponivel()) {
                            JMenu jmnIco = new JMenu("-> Opções");
                            atualizaMenuExecutar(ico.filhos(), jmnIco);
                            noMenu.add(jmnIco);
                            noMenu.addSeparator();
                        }
                    } else {
                        if (ico.temFilhoDisponivel()) {
                            JMenu jmnIco = new JMenu(ico.pegaNome());
                            atualizaMenuExecutar(ico.filhos(), jmnIco);
                            noMenu.add(jmnIco);
                        }
                    }
                }
            }
        }
    }

    private void atualizaMenuAtalho(List<Recurso> daLista, JMenu noMenu) {
        if (daLista != null) {
            for (Recurso rcr : daLista) {
                if (rcr.estaDisponivel()) {
                    if (rcr.pegaClasse() != null) {
                        JMenuItem jmiIco = new JMenuItem(rcr.pegaNome());
                        jmiIco.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                lancador.botaAtalho(ultimoClique, rcr);
                            }
                        });
                        noMenu.add(jmiIco);
                        if (rcr.temFilhoDisponivel()) {
                            JMenu jmnIco = new JMenu("-> Opções");
                            atualizaMenuAtalho(rcr.filhos(), jmnIco);
                            noMenu.add(jmnIco);
                            noMenu.addSeparator();
                        }
                    } else {
                        if (rcr.temFilhoDisponivel()) {
                            JMenu jmnIco = new JMenu(rcr.pegaNome());
                            atualizaMenuAtalho(rcr.filhos(), jmnIco);
                            noMenu.add(jmnIco);
                        }
                    }
                }
            }
        }
    }

    public Mesa atualiza() {
        jmnExecutar.removeAll();
        atualizaMenuExecutar(Pointel.mainRecs, jmnExecutar);
        jmnNovoAtalho.removeAll();
        atualizaMenuAtalho(Pointel.mainRecs, jmnNovoAtalho);
        lancador.atualizaAtalhos();
        lancador.atualizaAtalhosDaConexao();
        lancador.atualizaConexaoDescricao();
        return this;
    }

    public Rectangle pegaArea() {
        return area;
    }

    public Mesa abreInstancia(String comNome) {
        try {
            UtlBin.abrePointel("Pointel", "-inst", comNome);
        } catch (Exception ex) {
            Utl.registra(ex);
        }
        return this;
    }

    public Mesa mudaRestricao() {
        try {
            String senJarbs = Pointel.mainConf.pegaCrsCrpt("PointelJarbs - Senha", "jarbozo");
            if (UtlCrs.confereSenha(senJarbs, "Senha do PointelJarbs")) {
                String restAnt = Pointel.mainConf.pegaCrs("Pointel - Restringida", "");
                String rest = UtlCrs.pergunta("Sistema A Ser Usado", restAnt);
                if (rest != null) {
                    if (!restAnt.equals(rest)) {
                        Pointel.restringida = rest;
                        Pointel.mainConf.botaCrs("Pointel - Restringida", Pointel.restringida);
                        Pointel.mainIntel.atualizaRestricao();
                    } else {
                        Utl.alerta("Sistema Já Configurado.");
                    }
                }
            } else {
                Utl.alerta("Senha do PointelJarbs Não Conferiu.");
            }
        } catch (Exception ex) {
            Utl.registra(ex);
        }
        return this;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpmMesa = new javax.swing.JPopupMenu();
        jmnExecutar = new javax.swing.JMenu();
        jmnNovoAtalho = new javax.swing.JMenu();
        jmnNovoArquivo = new javax.swing.JMenu();
        jmiNovArqLis = new javax.swing.JMenuItem();
        jmiNovArqTex = new javax.swing.JMenuItem();
        jmiNovArqIma = new javax.swing.JMenuItem();
        jmiNovArqAud = new javax.swing.JMenuItem();
        jmiNovArqVid = new javax.swing.JMenuItem();
        jmiNovArqFor = new javax.swing.JMenuItem();
        jmiNovArqPla = new javax.swing.JMenuItem();
        jmiNovArqDoc = new javax.swing.JMenuItem();
        jmiNovArqFrm = new javax.swing.JMenuItem();
        jmiNovArqApr = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jmnInstancias = new javax.swing.JMenu();
        jmiInsNova = new javax.swing.JMenuItem();
        jmnMonitores = new javax.swing.JMenu();
        jmnPlanoFundo = new javax.swing.JMenu();
        jmnSistema = new javax.swing.JMenu();
        jmiVersao = new javax.swing.JMenuItem();
        jmiRestricao = new javax.swing.JMenuItem();
        jmiConexao = new javax.swing.JMenuItem();
        jmiConsole = new javax.swing.JMenuItem();
        jmiEncerrar = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jmiJanelas = new javax.swing.JMenuItem();
        jmiMinimizar = new javax.swing.JMenuItem();
        jmiFechar = new javax.swing.JMenuItem();

        jmnExecutar.setText("Executar");
        jpmMesa.add(jmnExecutar);

        jmnNovoAtalho.setText("Novo Atalho");
        jpmMesa.add(jmnNovoAtalho);

        jmnNovoArquivo.setText("Novo Arquivo");

        jmiNovArqLis.setText("Lista   [ control alt L ]");
        jmiNovArqLis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiNovArqLisActionPerformed(evt);
            }
        });
        jmnNovoArquivo.add(jmiNovArqLis);

        jmiNovArqTex.setText("Texto   [ control alt T ]");
        jmiNovArqTex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiNovArqTexActionPerformed(evt);
            }
        });
        jmnNovoArquivo.add(jmiNovArqTex);

        jmiNovArqIma.setText("Imagem   [ control alt I ]");
        jmiNovArqIma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiNovArqImaActionPerformed(evt);
            }
        });
        jmnNovoArquivo.add(jmiNovArqIma);

        jmiNovArqAud.setText("Áudio   [ control alt A ]");
        jmiNovArqAud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiNovArqAudActionPerformed(evt);
            }
        });
        jmnNovoArquivo.add(jmiNovArqAud);

        jmiNovArqVid.setText("Vídeo   [ control alt V ]");
        jmiNovArqVid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiNovArqVidActionPerformed(evt);
            }
        });
        jmnNovoArquivo.add(jmiNovArqVid);

        jmiNovArqFor.setText("Forma   [ control alt F ]");
        jmiNovArqFor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiNovArqForActionPerformed(evt);
            }
        });
        jmnNovoArquivo.add(jmiNovArqFor);

        jmiNovArqPla.setText("Planilha   [ control alt P ]");
        jmiNovArqPla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiNovArqPlaActionPerformed(evt);
            }
        });
        jmnNovoArquivo.add(jmiNovArqPla);

        jmiNovArqDoc.setText("Documento   [ control alt D ]");
        jmiNovArqDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiNovArqDocActionPerformed(evt);
            }
        });
        jmnNovoArquivo.add(jmiNovArqDoc);

        jmiNovArqFrm.setText("Formulário   [ control alt R ]");
        jmiNovArqFrm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiNovArqFrmActionPerformed(evt);
            }
        });
        jmnNovoArquivo.add(jmiNovArqFrm);

        jmiNovArqApr.setText("Apresentação   [ control alt S ]");
        jmiNovArqApr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiNovArqAprActionPerformed(evt);
            }
        });
        jmnNovoArquivo.add(jmiNovArqApr);

        jpmMesa.add(jmnNovoArquivo);
        jpmMesa.add(jSeparator3);

        jmnInstancias.setText("Instâncias");

        jmiInsNova.setText("Nova");
        jmiInsNova.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiInsNovaActionPerformed(evt);
            }
        });
        jmnInstancias.add(jmiInsNova);

        jpmMesa.add(jmnInstancias);

        jmnMonitores.setText("Monitores");
        jpmMesa.add(jmnMonitores);

        jmnPlanoFundo.setText("Plano de Fundo");
        jpmMesa.add(jmnPlanoFundo);

        jmnSistema.setText("Sistema");

        jmiVersao.setText("Versão");
        jmiVersao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiVersaoActionPerformed(evt);
            }
        });
        jmnSistema.add(jmiVersao);

        jmiRestricao.setText("Restrição");
        jmiRestricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRestricaoActionPerformed(evt);
            }
        });
        jmnSistema.add(jmiRestricao);

        jmiConexao.setText("Conexão");
        jmiConexao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiConexaoActionPerformed(evt);
            }
        });
        jmnSistema.add(jmiConexao);

        jmiConsole.setText("Console");
        jmiConsole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiConsoleActionPerformed(evt);
            }
        });
        jmnSistema.add(jmiConsole);

        jmiEncerrar.setText("Encerrar");
        jmiEncerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiEncerrarActionPerformed(evt);
            }
        });
        jmnSistema.add(jmiEncerrar);

        jpmMesa.add(jmnSistema);
        jpmMesa.add(jSeparator4);

        jmiJanelas.setText("Janelas");
        jmiJanelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiJanelasActionPerformed(evt);
            }
        });
        jpmMesa.add(jmiJanelas);

        jmiMinimizar.setText("Minimizar");
        jmiMinimizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiMinimizarActionPerformed(evt);
            }
        });
        jpmMesa.add(jmiMinimizar);

        jmiFechar.setText("Fechar");
        jmiFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFecharActionPerformed(evt);
            }
        });
        jpmMesa.add(jmiFechar);

        setTitle("Mesa");
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 88, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 89, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        Pointel.mainIntel.atualizaRestricao();
    }//GEN-LAST:event_formWindowActivated

    private void jmiEncerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiEncerrarActionPerformed
        //Verifica se foi clicado com control shift alt pressionado.
        if (evt.getModifiers() == 27) {
            System.exit(0);
        } else if (Utl.continua("Deseja Encerrar Todos os Processos do Pointel?")) {
            Pointel.mainIntel.encerrar();
        }
    }//GEN-LAST:event_jmiEncerrarActionPerformed

    private void jmiConexaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiConexaoActionPerformed
        new ConexaoVer(Pointel.mainConc).setVisible(true);
    }//GEN-LAST:event_jmiConexaoActionPerformed

    private void jmiNovArqTexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNovArqTexActionPerformed
        try {
            new TexEtf().mostra();
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jmiNovArqTexActionPerformed

    private void jmiInsNovaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiInsNovaActionPerformed
        String nome = UtlCrs.pergunta("Nome da Nova Instância");
        if (nome != null) {
            abreInstancia(nome);
        }
    }//GEN-LAST:event_jmiInsNovaActionPerformed

    private void jmiMinimizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiMinimizarActionPerformed
        setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jmiMinimizarActionPerformed

    private void jmiConsoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiConsoleActionPerformed
        try {
            new TexEtf().mostra("Console").abre(Pointel.mainCons.registro);
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jmiConsoleActionPerformed

    private void jmiVersaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiVersaoActionPerformed
        Utl.alerta("Pointel Build Versão " + Pointel.versao);
    }//GEN-LAST:event_jmiVersaoActionPerformed

    private void jmiFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFecharActionPerformed
        Janelas.fecha(this);
    }//GEN-LAST:event_jmiFecharActionPerformed

    private void jmiNovArqLisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNovArqLisActionPerformed
        try {
            new LisEtf().mostra();
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jmiNovArqLisActionPerformed

    private void jmiNovArqPlaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNovArqPlaActionPerformed
        try {
            new PlaEtf().mostra();
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jmiNovArqPlaActionPerformed

    private void jmiNovArqImaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNovArqImaActionPerformed
        try {
            new ImaEtf().mostra();
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jmiNovArqImaActionPerformed

    private void jmiNovArqAudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNovArqAudActionPerformed
        try {
            new AudEtf().mostra();
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jmiNovArqAudActionPerformed

    private void jmiNovArqVidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNovArqVidActionPerformed
        try {
            new VidEtf().mostra();
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jmiNovArqVidActionPerformed

    private void jmiNovArqDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNovArqDocActionPerformed
        try {
            new DocEtf().mostra();
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jmiNovArqDocActionPerformed

    private void jmiNovArqForActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNovArqForActionPerformed
        try {
            new ForEtf().mostra();
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jmiNovArqForActionPerformed

    private void jmiNovArqAprActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNovArqAprActionPerformed
        try {
            new AprEtf().mostra();
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jmiNovArqAprActionPerformed

    private void jmiNovArqFrmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNovArqFrmActionPerformed
        try {
            new FrmEtf().mostra();
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jmiNovArqFrmActionPerformed

    private void jmiRestricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRestricaoActionPerformed
        mudaRestricao();
    }//GEN-LAST:event_jmiRestricaoActionPerformed

    private void jmiJanelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiJanelasActionPerformed
        new JanelasIntf().mostra();
    }//GEN-LAST:event_jmiJanelasActionPerformed

    private class ActNovArqLis extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            jmiNovArqLisActionPerformed(e);
        }
    }

    private class ActNovArqTex extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            jmiNovArqTexActionPerformed(e);
        }
    }

    private class ActNovArqIma extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            jmiNovArqImaActionPerformed(e);
        }
    }

    private class ActNovArqAud extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            jmiNovArqAudActionPerformed(e);
        }
    }

    private class ActNovArqVid extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            jmiNovArqVidActionPerformed(e);
        }
    }

    private class ActNovArqFor extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            jmiNovArqForActionPerformed(e);
        }
    }

    private class ActNovArqPla extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            jmiNovArqPlaActionPerformed(e);
        }
    }

    private class ActNovArqDoc extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            jmiNovArqDocActionPerformed(e);
        }
    }

    private class ActNovArqFrm extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            jmiNovArqFrmActionPerformed(e);
        }
    }

    private class ActNovArqApr extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            jmiNovArqAprActionPerformed(e);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JMenuItem jmiConexao;
    private javax.swing.JMenuItem jmiConsole;
    private javax.swing.JMenuItem jmiEncerrar;
    private javax.swing.JMenuItem jmiFechar;
    private javax.swing.JMenuItem jmiInsNova;
    private javax.swing.JMenuItem jmiJanelas;
    private javax.swing.JMenuItem jmiMinimizar;
    private javax.swing.JMenuItem jmiNovArqApr;
    private javax.swing.JMenuItem jmiNovArqAud;
    private javax.swing.JMenuItem jmiNovArqDoc;
    private javax.swing.JMenuItem jmiNovArqFor;
    private javax.swing.JMenuItem jmiNovArqFrm;
    private javax.swing.JMenuItem jmiNovArqIma;
    private javax.swing.JMenuItem jmiNovArqLis;
    private javax.swing.JMenuItem jmiNovArqPla;
    private javax.swing.JMenuItem jmiNovArqTex;
    private javax.swing.JMenuItem jmiNovArqVid;
    private javax.swing.JMenuItem jmiRestricao;
    private javax.swing.JMenuItem jmiVersao;
    private javax.swing.JMenu jmnExecutar;
    private javax.swing.JMenu jmnInstancias;
    private javax.swing.JMenu jmnMonitores;
    private javax.swing.JMenu jmnNovoArquivo;
    private javax.swing.JMenu jmnNovoAtalho;
    private javax.swing.JMenu jmnPlanoFundo;
    private javax.swing.JMenu jmnSistema;
    private javax.swing.JPopupMenu jpmMesa;
    // End of variables declaration//GEN-END:variables

    private class MouLancador extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON3) {
                ultimoClique = new Point(e.getX(), e.getY());
                jpmMesa.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }
}
