package pin.libexp;

import java.awt.Desktop;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.AbstractAction;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import pin.libamk.Campos;
import pin.libamk.Cmp;
import pin.libamk.Intf;
import pin.libbas.Dados;
import pin.libbas.Progresso;
import pin.libbas.Retornos;
import pin.libbas.RetornosIntf;
import pin.libetf.EdtIntf;
import pin.libetf.TexEtf;
import pin.libitr.FormulaCrs;
import pin.libjan.Janelas;
import pin.libutl.Utl;
import pin.libutl.UtlArq;
import pin.libutl.UtlBin;
import pin.libutl.UtlCrs;
import pin.libutl.UtlIma;
import pin.libutl.UtlPro;
import pin.libutl.UtlRed;
import pin.libutl.UtlTex;

public class Explorar extends javax.swing.JPanel {

    private File pastaAberta;
    private ExpModelo modelo;
    private EvtExplorarAoSelecionarModelo aoSelecionar;

    public Explorar() {
        this(null);
    }

    public Explorar(File abrirPasta) {
        initComponents();
        if (abrirPasta == null) {
            pastaAberta = new File(System.getProperty("user.home"));
        } else {
            pastaAberta = abrirPasta;
        }
        jlsArquivos.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        jlsArquivos.setVisibleRowCount(-1);
        jlsArquivos.setCellRenderer(new ExpRenderizador());
        modelo = new ExpModelo();
        jlsArquivos.setModel(modelo);
        modelo.carregar(pastaAberta.listFiles(), null);
        aoSelecionar = null;
        jlsArquivos.getSelectionModel().addListSelectionListener(new AoSelecionarHandler());
        Janelas.botaAtalho(this, "Nova Pasta", "alt P", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jmiNovPastaActionPerformed(ae);
            }
        });
        Janelas.botaAtalho(this, "Novo Texto", "alt T", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jmiNovTextoActionPerformed(ae);
            }
        });
    }

    public File pegaPastaAberta() {
        return pastaAberta;
    }

    public Explorar mudaPastaAberta(File para) {
        this.pastaAberta = para;
        abrir();
        return this;
    }

    public ExpModelo pegaModelo() {
        return modelo;
    }

    public Explorar mudaModelo(ExpModelo para) {
        this.modelo = para;
        return this;
    }

    public EvtExplorarAoSelecionarModelo pegaAoSelecionar() {
        return aoSelecionar;
    }

    public Explorar mudaAoSelecionar(EvtExplorarAoSelecionarModelo para) {
        this.aoSelecionar = para;
        return this;
    }

    public List<File> pegaSelecionados() {
        return jlsArquivos.getSelectedValuesList();
    }

    public Explorar abrir() {
        List<File> selecionados = pegaSelecionados();
        for (File selecionado : selecionados) {
            if (selecionado.getName().equals("..")) {
                if (selecionados.size() == 1) {
                    if (!pastaAberta.getName().isEmpty()) {
                        pastaAberta = pastaAberta.getParentFile();
                        modelo.carregar(pastaAberta.listFiles(), null);
                    } else {
                        pastaAberta = pastaAberta.getParentFile();
                        modelo.carregarRoots(File.listRoots());
                    }
                }
            } else if (selecionado.isDirectory()) {
                if (selecionados.size() > 1) {
                    try {
                        new ExplorarIntf(selecionado).setVisible(true);
                    } catch (Exception ex) {
                        Utl.registra(ex);
                    }
                } else {
                    pastaAberta = selecionado;
                    modelo.carregar(pastaAberta.listFiles(), null);
                }
            } else {
                try {
                    String ext = UtlArq.pExtensao(selecionado.getName());
                    if (Retornos.ehExtensao(ext)) {
                        new RetornosIntf().mostra().abre(Files.readAllBytes(selecionado.toPath()));
                    } else {
                        Dados.Tp tipo = Dados.pTipoPelaExtensao(ext);
                        if (tipo != null) {
                            EdtIntf.abreEtf(Files.readAllBytes(selecionado.toPath()), tipo);
                        } else {
                            Desktop.getDesktop().open(selecionado);
                        }
                    }
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }

        }
        return this;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpmArquivos = new javax.swing.JPopupMenu();
        jmiAtualizar = new javax.swing.JMenuItem();
        jmnProcurar = new javax.swing.JMenu();
        jmnProArquivos = new javax.swing.JMenu();
        jmiProArqSubPastas = new javax.swing.JMenuItem();
        jmiProArqSelecionados = new javax.swing.JMenuItem();
        jmiProArqTodos = new javax.swing.JMenuItem();
        jmnProPastas = new javax.swing.JMenu();
        jmiProPasSubPastas = new javax.swing.JMenuItem();
        jmiProPasSelecionados = new javax.swing.JMenuItem();
        jmiProPasTodos = new javax.swing.JMenuItem();
        jmnProAmbos = new javax.swing.JMenu();
        jmiProAmbSubPastas = new javax.swing.JMenuItem();
        jmiProAmbSelecionados = new javax.swing.JMenuItem();
        jmiProAmbTodos = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jmnNovo = new javax.swing.JMenu();
        jmiNovPasta = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jmiNovTexto = new javax.swing.JMenuItem();
        jmiBaixar = new javax.swing.JMenuItem();
        jmiAbrir = new javax.swing.JMenuItem();
        jmiRenomear = new javax.swing.JMenuItem();
        jmiExcluir = new javax.swing.JMenuItem();
        jmiInformacoes = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jmnProcessar = new javax.swing.JMenu();
        jmiProComparar = new javax.swing.JMenuItem();
        jmnSubPastas = new javax.swing.JMenu();
        jmiSubDesRedundar = new javax.swing.JMenuItem();
        jmiSubLimparArvore = new javax.swing.JMenuItem();
        jmnTextos = new javax.swing.JMenu();
        jmiTexContar = new javax.swing.JMenuItem();
        jmiTexProcurar = new javax.swing.JMenuItem();
        jmnImagens = new javax.swing.JMenu();
        jmiImaCortar = new javax.swing.JMenuItem();
        jmiImaCortarMargens = new javax.swing.JMenuItem();
        jmiImaRedimensionar = new javax.swing.JMenuItem();
        jmnPDF = new javax.swing.JMenu();
        jmnPDFTransformar = new javax.swing.JMenu();
        jmiPDFTraTXT = new javax.swing.JMenuItem();
        jmiPDFTraPNG = new javax.swing.JMenuItem();
        jmnComprimir = new javax.swing.JMenu();
        jmiComMultiplos = new javax.swing.JMenuItem();
        jmnExecutar = new javax.swing.JMenu();
        jmiExeExterno = new javax.swing.JMenuItem();
        jspArquivos = new javax.swing.JScrollPane();
        jlsArquivos = new javax.swing.JList();

        jmiAtualizar.setText("Atualizar");
        jmiAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiAtualizarActionPerformed(evt);
            }
        });
        jpmArquivos.add(jmiAtualizar);

        jmnProcurar.setText("Procurar");
        jmnProcurar.setToolTipText("");

        jmnProArquivos.setText("Arquivos");

        jmiProArqSubPastas.setText("Nas SubPastas");
        jmiProArqSubPastas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiProArqSubPastasActionPerformed(evt);
            }
        });
        jmnProArquivos.add(jmiProArqSubPastas);

        jmiProArqSelecionados.setText("Nos Selecionados");
        jmiProArqSelecionados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiProArqSelecionadosActionPerformed(evt);
            }
        });
        jmnProArquivos.add(jmiProArqSelecionados);

        jmiProArqTodos.setText("Em Todos");
        jmiProArqTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiProArqTodosActionPerformed(evt);
            }
        });
        jmnProArquivos.add(jmiProArqTodos);

        jmnProcurar.add(jmnProArquivos);

        jmnProPastas.setText("Pastas");

        jmiProPasSubPastas.setText("Nas SubPastas");
        jmiProPasSubPastas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiProPasSubPastasActionPerformed(evt);
            }
        });
        jmnProPastas.add(jmiProPasSubPastas);

        jmiProPasSelecionados.setText("Nos Selecionados");
        jmiProPasSelecionados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiProPasSelecionadosActionPerformed(evt);
            }
        });
        jmnProPastas.add(jmiProPasSelecionados);

        jmiProPasTodos.setText("Em Todos");
        jmiProPasTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiProPasTodosActionPerformed(evt);
            }
        });
        jmnProPastas.add(jmiProPasTodos);

        jmnProcurar.add(jmnProPastas);

        jmnProAmbos.setText("Ambos");

        jmiProAmbSubPastas.setText("Nas SubPastas");
        jmiProAmbSubPastas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiProAmbSubPastasActionPerformed(evt);
            }
        });
        jmnProAmbos.add(jmiProAmbSubPastas);

        jmiProAmbSelecionados.setText("Nos Selecionados");
        jmiProAmbSelecionados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiProAmbSelecionadosActionPerformed(evt);
            }
        });
        jmnProAmbos.add(jmiProAmbSelecionados);

        jmiProAmbTodos.setText("Em Todos");
        jmiProAmbTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiProAmbTodosActionPerformed(evt);
            }
        });
        jmnProAmbos.add(jmiProAmbTodos);

        jmnProcurar.add(jmnProAmbos);

        jpmArquivos.add(jmnProcurar);
        jpmArquivos.add(jSeparator1);

        jmnNovo.setText("Novo");

        jmiNovPasta.setText("Pasta");
        jmiNovPasta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiNovPastaActionPerformed(evt);
            }
        });
        jmnNovo.add(jmiNovPasta);
        jmnNovo.add(jSeparator3);

        jmiNovTexto.setText("Texto");
        jmiNovTexto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiNovTextoActionPerformed(evt);
            }
        });
        jmnNovo.add(jmiNovTexto);

        jpmArquivos.add(jmnNovo);

        jmiBaixar.setText("Baixar");
        jmiBaixar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiBaixarActionPerformed(evt);
            }
        });
        jpmArquivos.add(jmiBaixar);

        jmiAbrir.setText("Abrir");
        jmiAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiAbrirActionPerformed(evt);
            }
        });
        jpmArquivos.add(jmiAbrir);

        jmiRenomear.setText("Renomear");
        jmiRenomear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRenomearActionPerformed(evt);
            }
        });
        jpmArquivos.add(jmiRenomear);

        jmiExcluir.setText("Excluir");
        jmiExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiExcluirActionPerformed(evt);
            }
        });
        jpmArquivos.add(jmiExcluir);

        jmiInformacoes.setText("Informações");
        jmiInformacoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiInformacoesActionPerformed(evt);
            }
        });
        jpmArquivos.add(jmiInformacoes);
        jpmArquivos.add(jSeparator2);

        jmnProcessar.setText("Processar");

        jmiProComparar.setText("Comparar");
        jmiProComparar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiProCompararActionPerformed(evt);
            }
        });
        jmnProcessar.add(jmiProComparar);

        jpmArquivos.add(jmnProcessar);

        jmnSubPastas.setText("SubPastas");

        jmiSubDesRedundar.setText("DesRedundar");
        jmiSubDesRedundar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiSubDesRedundarActionPerformed(evt);
            }
        });
        jmnSubPastas.add(jmiSubDesRedundar);

        jmiSubLimparArvore.setText("Limpar Árvore");
        jmiSubLimparArvore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiSubLimparArvoreActionPerformed(evt);
            }
        });
        jmnSubPastas.add(jmiSubLimparArvore);

        jpmArquivos.add(jmnSubPastas);

        jmnTextos.setText("Textos");

        jmiTexContar.setText("Contar");
        jmiTexContar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiTexContarActionPerformed(evt);
            }
        });
        jmnTextos.add(jmiTexContar);

        jmiTexProcurar.setText("Procurar");
        jmiTexProcurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiTexProcurarActionPerformed(evt);
            }
        });
        jmnTextos.add(jmiTexProcurar);

        jpmArquivos.add(jmnTextos);

        jmnImagens.setText("Imagens");

        jmiImaCortar.setText("Cortar");
        jmiImaCortar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiImaCortarActionPerformed(evt);
            }
        });
        jmnImagens.add(jmiImaCortar);

        jmiImaCortarMargens.setText("Cortar Margens");
        jmiImaCortarMargens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiImaCortarMargensActionPerformed(evt);
            }
        });
        jmnImagens.add(jmiImaCortarMargens);

        jmiImaRedimensionar.setText("Redimensionar");
        jmiImaRedimensionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiImaRedimensionarActionPerformed(evt);
            }
        });
        jmnImagens.add(jmiImaRedimensionar);

        jpmArquivos.add(jmnImagens);

        jmnPDF.setText("PDF");

        jmnPDFTransformar.setText("Transformar");

        jmiPDFTraTXT.setText("Para TXT");
        jmiPDFTraTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiPDFTraTXTActionPerformed(evt);
            }
        });
        jmnPDFTransformar.add(jmiPDFTraTXT);

        jmiPDFTraPNG.setText("Para PNG");
        jmiPDFTraPNG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiPDFTraPNGActionPerformed(evt);
            }
        });
        jmnPDFTransformar.add(jmiPDFTraPNG);

        jmnPDF.add(jmnPDFTransformar);

        jpmArquivos.add(jmnPDF);

        jmnComprimir.setText("Comprimir");

        jmiComMultiplos.setText("Múltiplos");
        jmiComMultiplos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiComMultiplosActionPerformed(evt);
            }
        });
        jmnComprimir.add(jmiComMultiplos);

        jpmArquivos.add(jmnComprimir);

        jmnExecutar.setText("Executar");

        jmiExeExterno.setText("Externo");
        jmiExeExterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiExeExternoActionPerformed(evt);
            }
        });
        jmnExecutar.add(jmiExeExterno);

        jpmArquivos.add(jmnExecutar);

        jlsArquivos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlsArquivosMouseClicked(evt);
            }
        });
        jlsArquivos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jlsArquivosKeyReleased(evt);
            }
        });
        jspArquivos.setViewportView(jlsArquivos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jspArquivos, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jspArquivos, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE));
    }// </editor-fold>//GEN-END:initComponents

    private void jlsArquivosMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jlsArquivosMouseClicked
        if ((evt.getClickCount() == 2) && (evt.getButton() == MouseEvent.BUTTON1)) {
            abrir();
        } else if (evt.getButton() == MouseEvent.BUTTON3) {
            jpmArquivos.show(jlsArquivos, evt.getX(), evt.getY());
        }
    }// GEN-LAST:event_jlsArquivosMouseClicked

    private void jlsArquivosKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_jlsArquivosKeyReleased
        if (evt.getExtendedKeyCode() == KeyEvent.VK_SPACE) {
            abrir();
        } else if (evt.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE) {
            if (!pastaAberta.getName().isEmpty()) {
                pastaAberta = pastaAberta.getParentFile();
                modelo.carregar(pastaAberta.listFiles(), null);
            } else {
                pastaAberta = pastaAberta.getParentFile();
                modelo.carregarRoots(File.listRoots());
            }
        }
    }// GEN-LAST:event_jlsArquivosKeyReleased

    private void jmiAtualizarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jmiAtualizarActionPerformed
        modelo.carregar(pastaAberta.listFiles(), null);
    }// GEN-LAST:event_jmiAtualizarActionPerformed

    private void jmiProArqSubPastasActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jmiProArqSubPastasActionPerformed
        List<File> selecionados = jlsArquivos.getSelectedValuesList();
        if (selecionados.size() > 0) {
            new FormulaCrs() {
                @Override
                public Boolean executa(FormulaCrs comFormula) {
                    new Thread("Arquivos Proc ProArqSubPastas") {
                        @Override
                        public void run() {
                            ArrayList<File> encontrados = new ArrayList<>();
                            for (File arq : selecionados) {
                                if (!arq.getName().equals("..")) {
                                    try {
                                        UtlArq.procurarArquivos(arq, true, comFormula.padrao(), encontrados);
                                    } catch (Exception ex) {
                                        Utl.registra(ex);
                                    }
                                }
                            }
                            File[] encs = new File[encontrados.size()];
                            encs = encontrados.toArray(encs);
                            modelo.carregar(encs, null);
                            Utl.alertaPop("Terminou de Procurar", true);
                        }
                    }.start();
                    return true;
                }
            }.mostra("Procurar Arquivos nas SubPastas");
        }
    }// GEN-LAST:event_jmiProArqSubPastasActionPerformed

    private void jmiProArqSelecionadosActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jmiProArqSelecionadosActionPerformed
        List<File> selecionados = jlsArquivos.getSelectedValuesList();
        if (selecionados.size() > 0) {
            new FormulaCrs() {
                @Override
                public Boolean executa(FormulaCrs comFormula) {
                    new Thread("Arquivos Proc ProArqSelecionados") {
                        @Override
                        public void run() {
                            ArrayList<File> encontrados = new ArrayList<>();
                            for (File arq : selecionados) {
                                if (!arq.getName().equals("..")) {
                                    try {
                                        UtlArq.procurarArquivos(arq, false, comFormula.padrao(), encontrados);
                                    } catch (Exception ex) {
                                        Utl.registra(ex);
                                    }
                                }
                            }
                            File[] encs = new File[encontrados.size()];
                            encs = encontrados.toArray(encs);
                            modelo.carregar(encs, null);

                            Utl.alertaPop("Terminou de Procurar", true);
                        }
                    }.start();
                    return true;
                }
            }.mostra("Procurar Arquivos nos Selecionados");
        }
    }// GEN-LAST:event_jmiProArqSelecionadosActionPerformed

    private void jmiProArqTodosActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jmiProArqTodosActionPerformed
        File[] todos = modelo.getTodos();
        if (todos.length > 1) {
            new FormulaCrs() {
                @Override
                public Boolean executa(FormulaCrs comFormula) {
                    new Thread("Arquivos Proc ProArqTodos") {
                        @Override
                        public void run() {
                            ArrayList<File> encontrados = new ArrayList<>();
                            for (File arq : todos) {
                                if (!arq.getName().equals("..")) {
                                    try {
                                        UtlArq.procurarArquivos(arq, false, comFormula.padrao(), encontrados);
                                    } catch (Exception ex) {
                                        Utl.registra(ex);
                                    }
                                }
                            }
                            File[] encs = new File[encontrados.size()];
                            encs = encontrados.toArray(encs);
                            modelo.carregar(encs, null);

                            Utl.alertaPop("Terminou de Procurar", true);
                        }
                    }.start();
                    return true;
                }
            }.mostra("Procurar Arquivos em Todos");
        }
    }// GEN-LAST:event_jmiProArqTodosActionPerformed

    private void jmiProPasSubPastasActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jmiProPasSubPastasActionPerformed
        List<File> selecionados = jlsArquivos.getSelectedValuesList();
        if (selecionados.size() > 0) {
            new FormulaCrs() {
                @Override
                public Boolean executa(FormulaCrs comFormula) {
                    new Thread("Arquivos Proc ProPasSubPastas") {
                        @Override
                        public void run() {
                            ArrayList<File> encontrados = new ArrayList<>();
                            for (File arq : selecionados) {
                                if (!arq.getName().equals("..")) {
                                    try {
                                        UtlArq.procurarPastas(arq, true, comFormula.padrao(), encontrados);
                                    } catch (Exception ex) {
                                        Utl.registra(ex);
                                    }
                                }
                            }
                            File[] encs = new File[encontrados.size()];
                            encs = encontrados.toArray(encs);
                            modelo.carregar(encs, null);

                            Utl.alertaPop("Terminou de Procurar", true);
                        }
                    }.start();
                    return true;
                }
            }.mostra("Procurar Pastas nas SubPastas");
        }
    }// GEN-LAST:event_jmiProPasSubPastasActionPerformed

    private void jmiProPasSelecionadosActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jmiProPasSelecionadosActionPerformed
        List<File> selecionados = jlsArquivos.getSelectedValuesList();
        if (selecionados.size() > 0) {
            new FormulaCrs() {
                @Override
                public Boolean executa(FormulaCrs comFormula) {
                    new Thread("Arquivos Proc ProPasSelecionados") {
                        @Override
                        public void run() {
                            ArrayList<File> encontrados = new ArrayList<>();
                            for (File arq : selecionados) {
                                if (!arq.getName().equals("..")) {
                                    try {
                                        UtlArq.procurarPastas(arq, false, comFormula.padrao(), encontrados);
                                    } catch (Exception ex) {
                                        Utl.registra(ex);
                                    }
                                }
                            }
                            File[] encs = new File[encontrados.size()];
                            encs = encontrados.toArray(encs);
                            modelo.carregar(encs, null);
                            Utl.alertaPop("Terminou de Procurar", true);
                        }
                    }.start();
                    return true;
                }
            }.mostra("Procurar Pastas nos Selecionados");
        }
    }// GEN-LAST:event_jmiProPasSelecionadosActionPerformed

    private void jmiProPasTodosActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jmiProPasTodosActionPerformed
        File[] todos = modelo.getTodos();
        if (todos.length > 1) {
            new FormulaCrs() {
                @Override
                public Boolean executa(FormulaCrs comFormula) {
                    new Thread("Arquivos Proc ProPasTodos") {
                        @Override
                        public void run() {
                            ArrayList<File> encontrados = new ArrayList<>();
                            for (File arq : todos) {
                                if (!arq.getName().equals("..")) {
                                    try {
                                        UtlArq.procurarPastas(arq, false, comFormula.padrao(), encontrados);
                                    } catch (Exception ex) {
                                        Utl.registra(ex);
                                    }
                                }
                            }
                            File[] encs = new File[encontrados.size()];
                            encs = encontrados.toArray(encs);
                            modelo.carregar(encs, null);

                            Utl.alertaPop("Terminou de Procurar", true);
                        }
                    }.start();
                    return true;
                }
            }.mostra("Procurar Pastas em Todos");
        }
    }// GEN-LAST:event_jmiProPasTodosActionPerformed

    private void jmiProAmbSubPastasActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jmiProAmbSubPastasActionPerformed
        List<File> selecionados = jlsArquivos.getSelectedValuesList();
        if (selecionados.size() > 0) {
            new FormulaCrs() {
                @Override
                public Boolean executa(FormulaCrs comFormula) {
                    new Thread("Arquivos Proc ProAmbSubPastas") {
                        @Override
                        public void run() {
                            ArrayList<File> encontrados = new ArrayList<>();
                            for (File arq : selecionados) {
                                if (!arq.getName().equals("..")) {
                                    try {
                                        UtlArq.procurarPastas(arq, true, comFormula.padrao(), encontrados);
                                    } catch (Exception ex) {
                                        Utl.registra(ex);
                                    }
                                }
                            }
                            File[] encs = new File[encontrados.size()];
                            encs = encontrados.toArray(encs);
                            modelo.carregar(encs, null);

                            Utl.alertaPop("Terminou de Procurar", true);
                        }
                    }.start();
                    return true;
                }
            }.mostra("Procurar Ambos em SubPastas");
        }
    }// GEN-LAST:event_jmiProAmbSubPastasActionPerformed

    private void jmiProAmbSelecionadosActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jmiProAmbSelecionadosActionPerformed
        List<File> selecionados = jlsArquivos.getSelectedValuesList();
        if (selecionados.size() > 0) {
            new FormulaCrs() {
                @Override
                public Boolean executa(FormulaCrs comFormula) {
                    new Thread("Arquivos Proc ProAmbsSelecionados") {
                        @Override
                        public void run() {
                            ArrayList<File> encontrados = new ArrayList<>();
                            for (File arq : selecionados) {
                                if (!arq.getName().equals("..")) {
                                    try {
                                        UtlArq.procurarAmbos(arq, false, comFormula.padrao(), encontrados);
                                    } catch (Exception ex) {
                                        Utl.registra(ex);
                                    }
                                }
                            }
                            File[] encs = new File[encontrados.size()];
                            encs = encontrados.toArray(encs);
                            modelo.carregar(encs, null);

                            Utl.alertaPop("Terminou de Procurar", true);
                        }
                    }.start();
                    return true;
                }
            }.mostra("Procurar Ambos nos Selecionados");
        }
    }// GEN-LAST:event_jmiProAmbSelecionadosActionPerformed

    private void jmiProAmbTodosActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jmiProAmbTodosActionPerformed
        File[] todos = modelo.getTodos();
        if (todos.length > 1) {
            new FormulaCrs() {
                @Override
                public Boolean executa(FormulaCrs comFormula) {
                    new Thread("Arquivos Proc ProAmbTodos") {
                        @Override
                        public void run() {
                            ArrayList<File> encontrados = new ArrayList<>();
                            for (File arq : todos) {
                                if (!arq.getName().equals("..")) {
                                    try {
                                        UtlArq.procurarAmbos(arq, false, comFormula.padrao(), encontrados);
                                    } catch (Exception ex) {
                                        Utl.registra(ex);
                                    }
                                }
                            }
                            File[] encs = new File[encontrados.size()];
                            encs = encontrados.toArray(encs);
                            modelo.carregar(encs, null);

                            Utl.alertaPop("Terminou de Procurar", true);
                        }
                    }.start();
                    return true;
                }
            }.mostra("Procurar Ambos em Todos");
        }
    }// GEN-LAST:event_jmiProAmbTodosActionPerformed

    private void jmiNovPastaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jmiNovPastaActionPerformed
        String nome = UtlCrs.pergunta("Nome da Nova Pasta");
        if (nome != null) {
            if (!nome.isEmpty()) {
                String caminho = pastaAberta.getAbsolutePath();
                if (!caminho.endsWith(File.separator)) {
                    caminho += File.separator;
                }
                caminho += nome;
                try {
                    Files.createDirectories(new File(caminho).toPath());
                    jmiAtualizarActionPerformed(evt);
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        }
    }// GEN-LAST:event_jmiNovPastaActionPerformed

    private void jmiNovTextoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jmiNovTextoActionPerformed
        try {
            new TexEtf().mostra();
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }// GEN-LAST:event_jmiNovTextoActionPerformed

    private void jmiBaixarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jmiBaixarActionPerformed
        try {
            String url = UtlRed.pergunta();
            if (url != null) {
                String nm = UtlCrs.pergunta("Salvar Com Nome:");
                if (nm != null) {
                    File fl = new File(pastaAberta, nm);
                    UtlRed.baixa(url, fl);
                }
            }
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }// GEN-LAST:event_jmiBaixarActionPerformed

    private void jmiAbrirActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jmiAbrirActionPerformed
        abrir();
    }// GEN-LAST:event_jmiAbrirActionPerformed

    private void jmiRenomearActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jmiRenomearActionPerformed
        List<File> selecionados = jlsArquivos.getSelectedValuesList();
        if (selecionados.size() > 0) {
            String novo = UtlCrs.pergunta("Substituir Por?");
            if (novo == null) {
                return;
            }
            new FormulaCrs() {
                @Override
                public Boolean executa(FormulaCrs comFormula) {
                    new Thread("Arquivos Proc Renomear") {
                        @Override
                        public void run() {
                            for (File arq : selecionados) {
                                if (!arq.getName().equals("..")) {
                                    try {
                                        String nvpd = arq.getName().replaceAll(comFormula.pegaFormula(), novo);
                                        if (!nvpd.equals(arq.getName())) {
                                            String nvnm = nvpd;
                                            File rename = new File(
                                                    arq.getParentFile().getAbsolutePath() + File.separator + nvnm);
                                            int it = 1;
                                            while (rename.exists()) {
                                                it++;
                                                nvnm = nvpd + it;
                                                rename = new File(
                                                        arq.getParentFile().getAbsolutePath() + File.separator + nvnm);
                                            }
                                            arq.renameTo(rename);
                                        }
                                    } catch (Exception ex) {
                                        Utl.registra(ex);
                                    }
                                }
                            }
                            Utl.alerta("Terminou de Renomear.");
                        }
                    }.start();
                    return true;
                }
            }.mostra("Renomear");
        }
    }// GEN-LAST:event_jmiRenomearActionPerformed

    private void jmiExcluirActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jmiExcluirActionPerformed
        if (Utl.continua()) {
            new Thread("Arquivos Proc Excluir") {
                @Override
                public void run() {
                    List<File> selecionados = jlsArquivos.getSelectedValuesList();
                    if (selecionados.size() > 0) {
                        for (File arq : selecionados) {
                            if (!arq.getName().equals("..")) {
                                try {
                                    UtlArq.exclui(arq);
                                } catch (Exception ex) {
                                    Utl.registra(ex);
                                }
                            }
                        }
                    }
                    Utl.alerta("Terminou de Excluir.");
                }
            }.start();
        }
    }// GEN-LAST:event_jmiExcluirActionPerformed

    private void jmiInformacoesActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jmiInformacoesActionPerformed
        new Thread("Arquivos Info") {
            @Override
            public void run() {
                List<File> selecionados = jlsArquivos.getSelectedValuesList();
                if (selecionados.size() > 0) {
                    for (File arq : selecionados) {
                        if (!arq.getName().equals("..")) {
                            new ExpInfo(arq).setVisible(true);
                        }
                    }
                }
            }
        }.start();
    }// GEN-LAST:event_jmiInformacoesActionPerformed

    private void jmiProCompararActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jmiProCompararActionPerformed
        List<File> selecionados = jlsArquivos.getSelectedValuesList();
        ExpComparador comp = new ExpComparador();
        if (selecionados.size() > 0) {
            comp.jtfEsquerda.setText(selecionados.get(0).getAbsolutePath());
        }
        if (selecionados.size() > 1) {
            comp.jtfDireita.setText(selecionados.get(1).getAbsolutePath());
        }
        comp.setVisible(true);
    }// GEN-LAST:event_jmiProCompararActionPerformed

    private void jmiSubDesRedundarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jmiSubDesRedundarActionPerformed
        if (Utl.continua()) {
            new Thread("Arquivos Proc SubPastas DesRedundar") {
                @Override
                public void run() {
                    List<File> selecionados = jlsArquivos.getSelectedValuesList();
                    if (selecionados.size() > 0) {
                        Progresso prog = new Progresso("DesRedundar SubPastas");
                        prog.abre(selecionados.size());
                        for (File arq : selecionados) {
                            if (!arq.getName().equals("..")) {
                                try {
                                    prog.bota(arq.getName());
                                    UtlArq.subPastasDesredundar(arq);
                                    prog.avanca();
                                } catch (Exception ex) {
                                    Utl.registra(ex);
                                }
                            }
                        }
                    }
                }
            }.start();
        }
    }// GEN-LAST:event_jmiSubDesRedundarActionPerformed

    private void jmiSubLimparArvoreActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jmiSubLimparArvoreActionPerformed
        if (Utl.continua()) {
            new Thread("Arquivos Proc SubPastas Limpar Árvore") {
                @Override
                public void run() {
                    List<File> selecionados = jlsArquivos.getSelectedValuesList();
                    if (selecionados.size() > 0) {
                        Progresso prog = new Progresso("Limpar Árvore");
                        prog.abre(selecionados.size());
                        for (File arq : selecionados) {
                            if (!arq.getName().equals("..")) {
                                try {
                                    prog.bota(arq.getName());
                                    UtlArq.subPastasLimparArvore(arq);
                                    prog.avanca();
                                } catch (Exception ex) {
                                    Utl.registra(ex);
                                }
                            }
                        }
                    }
                }
            }.start();
        }
    }// GEN-LAST:event_jmiSubLimparArvoreActionPerformed

    private void jmiTexContarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jmiTexContarActionPerformed
        List<File> selecionados = jlsArquivos.getSelectedValuesList();
        if (selecionados.size() > 0) {
            new Thread("Arquivos Proc TexContar") {
                @Override
                public void run() {
                    Progresso prog = new Progresso("Contar Linhas de Textos");
                    prog.abre(0, selecionados.size() - 1);

                    Integer quant = 0;
                    for (int i1 = 0; i1 < selecionados.size(); i1++) {
                        try {
                            File arq = selecionados.get(i1);
                            prog.bota("Contando Linhas de " + arq.getName());
                            quant = quant + UtlTex.contaLinhas(selecionados.get(i1));
                            prog.avanca();
                        } catch (Exception ex) {
                            Utl.registra(ex);
                        }
                    }

                    prog.bota("Terminou de Contar.");
                    Utl.alerta("Quantidade Total de Linhas: " + quant);
                }
            }.start();
        }
    }// GEN-LAST:event_jmiTexContarActionPerformed

    private void jmiTexProcurarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jmiTexProcurarActionPerformed
        List<File> selecionados = jlsArquivos.getSelectedValuesList();
        if (selecionados.size() > 0) {
            new FormulaCrs() {
                @Override
                public Boolean executa(FormulaCrs comFormula) {
                    new Thread("Arquivos Proc TexProcurar") {
                        @Override
                        public void run() {
                            Progresso prog = new Progresso("Procurar em Textos");
                            prog.abre(0, selecionados.size() - 1);
                            try {
                                // EncontradosTex encTex = new EncontradosTex();
                                // encTex.mostra();
                                for (int i1 = 0; i1 < selecionados.size(); i1++) {
                                    try {
                                        File arq = selecionados.get(i1);
                                        prog.bota("Procurando em " + arq.getName());
                                        if (arq.exists() && !arq.getName().equals("..")) {
                                            List<Point> encontrados = UtlTex.procura(UtlTex.abre(arq),
                                                    comFormula.padrao());
                                            for (Point enc : encontrados) {
                                                // encTex.edt().controlador().botaLinha(arq, enc.x, enc.y);
                                            }
                                        }
                                    } catch (Exception ex) {
                                        Utl.registra(ex);
                                    } finally {
                                        prog.avanca();
                                    }
                                }
                                prog.bota("Terminou de Procurar.");
                            } catch (Exception ex) {
                                prog.bota(ex);
                            }
                        }
                    }.start();
                    return true;
                }
            }.mostra("Procura nos Textos");
        }
    }// GEN-LAST:event_jmiTexProcurarActionPerformed

    private void jmiImaCortarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jmiImaCortarActionPerformed
        List<File> selecionados = jlsArquivos.getSelectedValuesList();
        if (selecionados.size() > 0) {
            try {
                new Intf("Cortar Imagens",
                        new Campos(
                                new Cmp(1, 1, "pos1x", "Posição 1 X", Dados.Tp.Int).botaObrigatorio()
                                        .botaOpcoes(new Object[] { 1, Integer.MAX_VALUE, 1 }),
                                new Cmp(1, 2, "pos1y", "Posição 1 Y", Dados.Tp.Int).botaObrigatorio()
                                        .botaOpcoes(new Object[] { 1, Integer.MAX_VALUE, 1 }),
                                new Cmp(2, 1, "pos2x", "Posição 2 X", Dados.Tp.Int).botaObrigatorio()
                                        .botaOpcoes(new Object[] { 1, Integer.MAX_VALUE, 1 }),
                                new Cmp(2, 2, "pos2y", "Posição 2 Y", Dados.Tp.Int).botaObrigatorio()
                                        .botaOpcoes(new Object[] { 1, Integer.MAX_VALUE, 1 }),
                                new Cmp(3, 1, "quali", "Qualidade (0 a 1)", Dados.Tp.NumLon).botaObrigatorio()
                                        .botaOpcoes(new Object[] { 0.0, 1.0, 0.1 }).mVlrInicial(0.8)
                                        .mDica("Qualidade de 0 = 0% a 1 = 100%."))) {
                    @Override
                    public void preparaIntf() throws Exception {
                        botaConfirmarECancelar();
                    }

                    @Override
                    public void aoConfirmar(Object comOrigem) throws Exception {
                        new Thread("Arquivos Proc ImaCortar") {
                            @Override
                            public void run() {
                                try {
                                    Integer pox = cmps().pgVlr("pos1x").pgInt();
                                    Integer poy = cmps().pgVlr("pos1y").pgInt();
                                    Integer lar = cmps().pgVlr("pos2x").pgInt();
                                    Integer alt = cmps().pgVlr("pos2y").pgInt();
                                    Double jql = cmps().pgVlr("quali").pgNumLon();
                                    Progresso prog = new Progresso("Cortando Imagens");
                                    prog.abre(0, selecionados.size() - 1);
                                    for (int i1 = 0; i1 < selecionados.size(); i1++) {
                                        File arq = selecionados.get(i1);
                                        prog.bota("Cortando " + arq.getName());
                                        if (!arq.getName().equals("..")) {
                                            try {
                                                UtlIma.corta(arq, jql, pox, poy, lar, alt);
                                            } catch (Exception ex) {
                                                Utl.registra(ex);
                                            }
                                        }
                                        prog.avanca();
                                    }
                                    prog.bota("Terminou de Cortar.");
                                } catch (Exception ex) {
                                    Utl.registra(ex);
                                }
                            }
                        }.start();
                    }
                }.mostra();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }// GEN-LAST:event_jmiImaCortarActionPerformed

    private void jmiImaCortarMargensActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jmiImaCortarMargensActionPerformed
        List<File> selecionados = jlsArquivos.getSelectedValuesList();
        if (selecionados.size() > 0) {
            try {
                new Intf("Cortar Margens",
                        new Campos(
                                new Cmp(1, 1, "refEsq", "Referência Esquerda", Dados.Tp.Enu).botaObrigatorio()
                                        .botaOpcoes(new Object[] { "Superior", "Centro", "Inferior" }),
                                new Cmp(1, 2, "refDir", "Referência Direita", Dados.Tp.Enu).botaObrigatorio()
                                        .botaOpcoes(new Object[] { "Superior", "Centro", "Inferior" }),
                                new Cmp(2, 1, "sensi", "Sensibilidade (0 a 1)", Dados.Tp.NumLon).botaObrigatorio()
                                        .botaOpcoes(new Object[] { 0.0, 1.0, 0.1 }).mVlrInicial(0.18)
                                        .mDica("Qualidade de 0 = 0% a 1 = 100%."),
                                new Cmp(2, 2, "quali", "Qualidade (0 a 1)", Dados.Tp.NumLon).botaObrigatorio()
                                        .botaOpcoes(new Object[] { 0.0, 1.0, 0.1 }).mVlrInicial(0.82)
                                        .mDica("Qualidade de 0 = 0% a 1 = 100%."))) {
                    @Override
                    public void preparaIntf() throws Exception {
                        botaConfirmarECancelar();
                    }

                    @Override
                    public void aoConfirmar(Object comOrigem) throws Exception {
                        new Thread("Arquivos Proc ImaCortarMargens") {
                            @Override
                            public void run() {
                                try {
                                    String res = cmps().pgVlr("refEsq").pgCrs();
                                    String rdi = cmps().pgVlr("refDir").pgCrs();
                                    Double sns = cmps().pgVlr("sensi").pgNumLon();
                                    Double jql = cmps().pgVlr("quali").pgNumLon();
                                    Progresso prog = new Progresso("Cortando Margens das Imagens");
                                    prog.abre(0, selecionados.size() - 1);
                                    for (int i1 = 0; i1 < selecionados.size(); i1++) {
                                        File sel = selecionados.get(i1);
                                        prog.bota("Cortando Margem de " + sel.getName());
                                        if (!sel.getName().equals("..")) {
                                            try {
                                                UtlIma.cortaMargens(sel, res, rdi, sns, jql);
                                            } catch (Exception ex) {
                                                Utl.registra(ex);
                                            }
                                        }
                                        prog.avanca();
                                    }
                                    prog.bota("Terminou de Cortar.");
                                } catch (Exception ex) {
                                    Utl.registra(ex);
                                }
                            }
                        }.start();
                    }
                }.mostra();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }// GEN-LAST:event_jmiImaCortarMargensActionPerformed

    private void jmiImaRedimensionarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jmiImaRedimensionarActionPerformed
        List<File> selecionados = jlsArquivos.getSelectedValuesList();
        if (selecionados.size() > 0) {
            try {
                new ExpImaRedimensionarIntf(selecionados).mostra();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }// GEN-LAST:event_jmiImaRedimensionarActionPerformed

    private void jmiPDFTraTXTActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jmiPDFTraTXTActionPerformed
        new Thread("Arquivos Proc PDFTraTXT") {
            @Override
            public void run() {
                List<File> selecionados = jlsArquivos.getSelectedValuesList();
                if (selecionados.size() > 0) {
                    Progresso prog = new Progresso("Transformar PDF em TXT");
                    prog.abre(0, selecionados.size() - 1);
                    for (int i1 = 0; i1 < selecionados.size(); i1++) {
                        File arq = selecionados.get(i1);
                        if (!arq.getName().equals("..")) {
                            try {
                                prog.bota("Transformando: " + arq.getName());
                                File para = new File(arq.getAbsolutePath() + ".txt");
                                String retorno = UtlBin.abreModulo("PointelSrvPDF", "-ope", "pegaTXT", "-de",
                                        arq.getAbsolutePath(), "-para", para.getAbsolutePath());
                                if (Objects.equals(retorno, "<feito/>")) {
                                    prog.bota("Transformado: " + arq.getName());
                                }
                            } catch (Exception ex) {
                                Utl.registra(ex);
                            }
                        }
                        prog.avanca();
                    }
                    prog.termina();
                }
            }
        }.start();
    }// GEN-LAST:event_jmiPDFTraTXTActionPerformed

    private void jmiPDFTraPNGActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jmiPDFTraPNGActionPerformed
        new Thread("Arquivos Proc PDFTraPNG") {
            @Override
            public void run() {
                List<File> selecionados = jlsArquivos.getSelectedValuesList();
                if (selecionados.size() > 0) {
                    File salvar = UtlArq.abreDir();
                    Progresso prog = new Progresso("Transformar PDF em PNG");
                    prog.abre(0, selecionados.size() - 1);
                    for (File arq : selecionados) {
                        if (!arq.getName().equals("..")) {
                            try {
                                prog.bota("Transformando: " + arq.getName());
                                String retorno = UtlBin.abreModulo("PointelSrvPDF", "-ope", "pegaPNG", "-de",
                                        arq.getAbsolutePath(), "-pags", "<todas/>", "-para", salvar.getAbsolutePath());
                                if (Objects.equals(retorno, "<feito/>")) {
                                    prog.bota("Transformado: " + arq.getName());
                                }
                            } catch (Exception ex) {
                                Utl.registra(ex);
                            }
                        }
                        prog.avanca();
                    }
                    prog.termina();
                }
            }
        }.start();
    }// GEN-LAST:event_jmiPDFTraPNGActionPerformed

    private void jmiComMultiplosActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jmiComMultiplosActionPerformed
        new Thread("Arquivos Proc ComMultiplos") {
            @Override
            public void run() {
                List<File> selecionados = jlsArquivos.getSelectedValuesList();
                if (selecionados.size() > 0) {
                    Progresso prog = new Progresso("Comprimindo Múltiplos");
                    prog.abre(0, selecionados.size() - 1);

                    for (int i1 = 0; i1 < selecionados.size(); i1++) {
                        File arq = selecionados.get(i1);

                        prog.bota("Comprimindo " + arq.getName());

                        if (!arq.getName().equals("..")) {
                            try {
                                if (arq.isDirectory()) {
                                    File zip = new File(arq.getAbsolutePath() + ".zip");
                                    UtlArq.botaDiretorioNoZip(arq, zip);
                                } else {
                                    File zip = new File(
                                            arq.getAbsolutePath().substring(0, arq.getAbsolutePath().lastIndexOf("."))
                                                    + ".zip");
                                    UtlArq.botaArquivoNoZip(arq, zip);
                                }
                            } catch (Exception ex) {
                                Utl.registra(ex);
                            }
                        }

                        prog.avanca();
                    }

                    prog.bota("Terminou de Comprimir");
                }

            }
        }.start();
    }// GEN-LAST:event_jmiComMultiplosActionPerformed

    private void jmiExeExternoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jmiExeExternoActionPerformed
        new Thread("Executar Externo") {
            @Override
            public void run() {
                List<File> selecionados = jlsArquivos.getSelectedValuesList();
                if (selecionados.size() > 0) {
                    String comando = UtlCrs.pergunta("Comando");
                    Progresso prog = new Progresso("Executar Externo");
                    prog.abre(0, selecionados.size());
                    for (File arq : selecionados) {
                        prog.bota(arq.getName());
                        if (!arq.getName().equals("..")) {
                            String pcom = comando;
                            while (pcom.contains("<nome>")) {
                                pcom = pcom.replace("<nome>", UtlArq.pNomeBasico(arq.getName()));
                            }
                            while (pcom.contains("<extensão>")) {
                                pcom = pcom.replace("<extensão>", UtlArq.pExtensao(arq.getName()));
                            }
                            while (pcom.contains("<completo>")) {
                                pcom = pcom.replace("<completo>", arq.getName());
                            }
                            while (pcom.contains("<caminho>")) {
                                pcom = pcom.replace("<caminho>", arq.getAbsolutePath());
                            }
                            while (pcom.contains("<diretorio>")) {
                                pcom = pcom.replace("<diretorio>", arq.getParent());
                            }

                            try {
                                UtlPro.comando(pcom, true);
                            } catch (Exception ex) {
                                Utl.registra(ex);
                            }
                        }
                        prog.avanca();
                    }
                    prog.bota("Terminado");
                }
            }
        }.start();
    }// GEN-LAST:event_jmiExeExternoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JList jlsArquivos;
    private javax.swing.JMenuItem jmiAbrir;
    private javax.swing.JMenuItem jmiAtualizar;
    private javax.swing.JMenuItem jmiBaixar;
    private javax.swing.JMenuItem jmiComMultiplos;
    private javax.swing.JMenuItem jmiExcluir;
    private javax.swing.JMenuItem jmiExeExterno;
    private javax.swing.JMenuItem jmiImaCortar;
    private javax.swing.JMenuItem jmiImaCortarMargens;
    private javax.swing.JMenuItem jmiImaRedimensionar;
    private javax.swing.JMenuItem jmiInformacoes;
    private javax.swing.JMenuItem jmiNovPasta;
    private javax.swing.JMenuItem jmiNovTexto;
    private javax.swing.JMenuItem jmiPDFTraPNG;
    private javax.swing.JMenuItem jmiPDFTraTXT;
    private javax.swing.JMenuItem jmiProAmbSelecionados;
    private javax.swing.JMenuItem jmiProAmbSubPastas;
    private javax.swing.JMenuItem jmiProAmbTodos;
    private javax.swing.JMenuItem jmiProArqSelecionados;
    private javax.swing.JMenuItem jmiProArqSubPastas;
    private javax.swing.JMenuItem jmiProArqTodos;
    private javax.swing.JMenuItem jmiProComparar;
    private javax.swing.JMenuItem jmiProPasSelecionados;
    private javax.swing.JMenuItem jmiProPasSubPastas;
    private javax.swing.JMenuItem jmiProPasTodos;
    private javax.swing.JMenuItem jmiRenomear;
    private javax.swing.JMenuItem jmiSubDesRedundar;
    private javax.swing.JMenuItem jmiSubLimparArvore;
    private javax.swing.JMenuItem jmiTexContar;
    private javax.swing.JMenuItem jmiTexProcurar;
    private javax.swing.JMenu jmnComprimir;
    private javax.swing.JMenu jmnExecutar;
    private javax.swing.JMenu jmnImagens;
    private javax.swing.JMenu jmnNovo;
    private javax.swing.JMenu jmnPDF;
    private javax.swing.JMenu jmnPDFTransformar;
    private javax.swing.JMenu jmnProAmbos;
    private javax.swing.JMenu jmnProArquivos;
    private javax.swing.JMenu jmnProPastas;
    private javax.swing.JMenu jmnProcessar;
    private javax.swing.JMenu jmnProcurar;
    private javax.swing.JMenu jmnSubPastas;
    private javax.swing.JMenu jmnTextos;
    private javax.swing.JPopupMenu jpmArquivos;
    private javax.swing.JScrollPane jspArquivos;
    // End of variables declaration//GEN-END:variables

    private class AoSelecionarHandler implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (aoSelecionar != null) {
                if (!e.getValueIsAdjusting()) {
                    aoSelecionar.valueChanged(e);
                }
            }
        }
    }

    public static class EvtExplorarAoSelecionarModelo implements ListSelectionListener {

        public void valueChanged(ListSelectionEvent e) {

        }
    }

}
