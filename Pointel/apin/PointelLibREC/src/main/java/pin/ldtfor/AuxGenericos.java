package pin.ldtfor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import pin.libfor.ForCurva;
import pin.libfor.ForFim;
import pin.libfor.ForInicio;
import pin.libfor.ForLinha;
import pin.libfor.ForParte;
import pin.libfor.ForSiado;
import pin.libfor.Generico;
import pin.libfor.Poligono;
import pin.libjan.Janelas;
import pin.libpic.Pics;
import pin.libutl.Utl;
import pin.libutl.UtlInt;
import pin.libvlr.VFor;

public class AuxGenericos extends javax.swing.JFrame {

    private enum Livre {
        Nenhum, Caneta, Borracha
    }

    private enum LivreTp {
        Elipse, Poligono
    }

    private final LdtFor editor;
    private final DefaultListModel dlmPartes;
    private final DefaultListModel dlmPontos;
    private final AuxEditor auxEdt;
    private Generico formato;
    private Livre livre;
    private LivreTp livTipo;
    private Integer livLados;
    private Double livTamanho;
    private Double livExcentricidade;
    private Double livAngulo;
    private Shape livFormato;

    public AuxGenericos(LdtFor doEditor) {
        initComponents();
        editor = doEditor;
        jbtCaneta.setIcon(Pics.pega("pen.png"));
        jbtApagador.setIcon(Pics.pega("apagador.png"));
        jbtPonta.setIcon(Pics.pega("no3d.png"));
        jbtAngulo.setIcon(Pics.pega("arrow_rotate_clockwise.png"));
        jbtAumentar.setIcon(Pics.pega("up.png"));
        jbtDiminuir.setIcon(Pics.pega("down.png"));
        jbtBota.setIcon(Pics.pBota());
        jbtTira.setIcon(Pics.pTira());
        jbtAcima.setIcon(Pics.pegaAcima());
        jbtAbaixo.setIcon(Pics.pegaAbaixo());
        jbtConfirma.setIcon(Pics.pegaConfirma());
        jbtCancela.setIcon(Pics.pegaCancela());
        Janelas.inicia(this);
        if (editor.pgVlr().pGenerico() != null) {
            try {
                formato = Generico.Duplica(editor.pgVlr().pGenerico());
            } catch (Exception ex) {
                Utl.registra(ex);
                novoFormato();
            }
        } else {
            novoFormato();
        }
        auxEdt = new AuxEditor();
        jpnEditor.add(auxEdt, BorderLayout.CENTER);
        dlmPartes = new DefaultListModel<>();
        jlsPartes.setModel(dlmPartes);
        dlmPontos = new DefaultListModel<>();
        jlsPontos.setModel(dlmPontos);
        carregaPartes();
        iniciaAtalhos();
        livre = Livre.Nenhum;
        constroiLivre(LivreTp.Elipse, null, 30.0, 1.0);
        livAngulo = null;
    }

    private void novoFormato() {
        formato = new Generico(new Point2D.Double(30, 30));
    }

    private void constroiLivre() {
        constroiLivre(livTipo, livLados, livTamanho, livExcentricidade);
    }

    private void constroiLivre(LivreTp noTipo, Integer comLados, Double eTamanho, Double eExcentricidade) {
        livTipo = noTipo;
        livLados = comLados;
        livTamanho = eTamanho;
        livExcentricidade = eExcentricidade;
        livFormato = null;
        if (noTipo != null) {
            switch (noTipo) {
                case Elipse:
                    livFormato = new Ellipse2D.Double(0, 0, livTamanho * livExcentricidade, livTamanho);
                    break;
                case Poligono:
                    Poligono poli = new Poligono(livLados, livTamanho);
                    poli.transform(AffineTransform.getScaleInstance(livExcentricidade, 1));
                    livFormato = poli;
                    break;
            }
        }
    }

    private void carregaPartes() {
        dlmPartes.clear();
        auxEdt.removeAll();
        auxEdt.temporarios().clear();
        for (ForParte parte : formato.partes()) {
            dlmPartes.addElement(parte);
            for (Point2D ponto : parte.pontos()) {
                auxEdt.add(new AuxPonto(parte, ponto));
            }
        }
        auxEdt.repaint();
    }

    private void iniciaAtalhos() {
        Janelas.botaAtalho(this, "Genérico Caneta", "C", new ActCaneta());
        Janelas.botaAtalho(this, "Genérico Apagador", "A", new ActApagador());
        Janelas.botaAtalho(this, "Genérico Ponta", "P", new ActPonta());
        Janelas.botaAtalho(this, "Genérico Ângulo", "U", new ActAngulo());
        Janelas.botaAtalho(this, "Genérico Aumentar", "PLUS", new ActAumentar());
        Janelas.botaAtalho(this, "Genérico Diminuir", "MINUS", new ActDiminuir());
        Janelas.botaAtalho(this, "Genérico Bota", "INSERT", new ActBota());
        Janelas.botaAtalho(this, "Genérico Bota Início", "I", new ActBotaInicio());
        Janelas.botaAtalho(this, "Genérico Bota Linha", "L", new ActBotaLinha());
        Janelas.botaAtalho(this, "Genérico Bota Curva", "R", new ActBotaCurva());
        Janelas.botaAtalho(this, "Genérico Bota Siado", "S", new ActBotaSiado());
        Janelas.botaAtalho(this, "Genérico Bota Fim", "F", new ActBotaFim());
        Janelas.botaAtalho(this, "Genérico Tira", "DELETE", new ActTira());
        Janelas.botaAtalho(this, "Genérico Acima", "PAGE_UP", new ActAcima());
        Janelas.botaAtalho(this, "Genérico Abaixo", "PAGE_DOWN", new ActAbaixo());
        Janelas.botaAtalho(this, "Genérico Confirma", "control ENTER", new ActConfirma());
        Janelas.botaAtalho(this, "Genérico Partes Seleciona Tudo", "control A", new ActPartesSelecionaTudo());
        Janelas.botaAtalho(this, "Genérico Pontos Seleciona Tudo", "control shift A", new ActPontosSelecionaTudo());
        Janelas.botaAtalho(this, "Genérico Move Parte Acima", "control UP", new ActMoveParteY(-1));
        Janelas.botaAtalho(this, "Genérico Move Parte Abaixo", "control DOWN", new ActMoveParteY(1));
        Janelas.botaAtalho(this, "Genérico Move Parte Aesquerda", "control LEFT", new ActMoveParteX(-1));
        Janelas.botaAtalho(this, "Genérico Move Parte Adireita", "control RIGHT", new ActMoveParteX(1));
        Janelas.botaAtalho(this, "Genérico Move Parte Muito Acima", "shift UP", new ActMoveParteY(-10));
        Janelas.botaAtalho(this, "Genérico Move Parte Muito Abaixo", "shift DOWN", new ActMoveParteY(10));
        Janelas.botaAtalho(this, "Genérico Move Parte Muito Aesquerda", "shift LEFT", new ActMoveParteX(-10));
        Janelas.botaAtalho(this, "Genérico Move Parte Muito Adireita", "shift RIGHT", new ActMoveParteX(10));
        Janelas.botaAtalho(this, "Genérico Move Ponto Acima", "UP", new ActMovePontoY(-1));
        Janelas.botaAtalho(this, "Genérico Move Ponto Abaixo", "DOWN", new ActMovePontoY(1));
        Janelas.botaAtalho(this, "Genérico Move Ponto Aesquerda", "LEFT", new ActMovePontoX(-1));
        Janelas.botaAtalho(this, "Genérico Move Ponto Adireita", "RIGHT", new ActMovePontoX(1));
        Janelas.botaAtalho(this, "Genérico Move Ponto Muito Acima", "alt UP", new ActMovePontoY(-10));
        Janelas.botaAtalho(this, "Genérico Move Ponto Muito Abaixo", "alt DOWN", new ActMovePontoY(10));
        Janelas.botaAtalho(this, "Genérico Move Ponto Muito Aesquerda", "alt LEFT", new ActMovePontoX(-10));
        Janelas.botaAtalho(this, "Genérico Move Ponto Muito Adireita", "alt RIGHT", new ActMovePontoX(10));
    }

    public AuxGenericos mostra() {
        Janelas.mostra(this);
        return this;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnEditor = new javax.swing.JPanel();
        jbtCaneta = new javax.swing.JButton();
        jbtApagador = new javax.swing.JButton();
        jbtPonta = new javax.swing.JButton();
        jbtAngulo = new javax.swing.JButton();
        jbtAumentar = new javax.swing.JButton();
        jbtDiminuir = new javax.swing.JButton();
        jbtBota = new javax.swing.JButton();
        jbtTira = new javax.swing.JButton();
        jbtAcima = new javax.swing.JButton();
        jbtAbaixo = new javax.swing.JButton();
        jbtConfirma = new javax.swing.JButton();
        jbtCancela = new javax.swing.JButton();
        jspPartes = new javax.swing.JScrollPane();
        jlsPartes = new javax.swing.JList<>();
        jspPontos = new javax.swing.JScrollPane();
        jlsPontos = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editor de Formatos Genéricos");

        jpnEditor.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jpnEditor.setLayout(new java.awt.BorderLayout());

        jbtCaneta.setFocusable(false);
        jbtCaneta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCanetaActionPerformed(evt);
            }
        });

        jbtApagador.setFocusable(false);
        jbtApagador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtApagadorActionPerformed(evt);
            }
        });

        jbtPonta.setFocusable(false);
        jbtPonta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtPontaActionPerformed(evt);
            }
        });

        jbtAngulo.setFocusable(false);
        jbtAngulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAnguloActionPerformed(evt);
            }
        });

        jbtAumentar.setFocusable(false);
        jbtAumentar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAumentarActionPerformed(evt);
            }
        });

        jbtDiminuir.setFocusable(false);
        jbtDiminuir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtDiminuirActionPerformed(evt);
            }
        });

        jbtBota.setFocusable(false);
        jbtBota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtBotaActionPerformed(evt);
            }
        });

        jbtTira.setFocusable(false);
        jbtTira.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtTiraActionPerformed(evt);
            }
        });

        jbtAcima.setFocusable(false);
        jbtAcima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAcimaActionPerformed(evt);
            }
        });

        jbtAbaixo.setFocusable(false);
        jbtAbaixo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAbaixoActionPerformed(evt);
            }
        });

        jbtConfirma.setFocusable(false);
        jbtConfirma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtConfirmaActionPerformed(evt);
            }
        });

        jbtCancela.setFocusable(false);
        jbtCancela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCancelaActionPerformed(evt);
            }
        });

        jlsPartes.setFocusable(false);
        jlsPartes.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlsPartesValueChanged(evt);
            }
        });
        jspPartes.setViewportView(jlsPartes);

        jlsPontos.setFocusable(false);
        jlsPontos.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlsPontosValueChanged(evt);
            }
        });
        jspPontos.setViewportView(jlsPontos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpnEditor, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jspPontos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                                .addComponent(jspPartes, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jbtApagador, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jbtAngulo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jbtDiminuir, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jbtCaneta, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jbtPonta, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jbtAumentar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jbtBota, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jbtAcima, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jbtConfirma, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jbtTira, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtAbaixo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtCancela, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbtAumentar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtPonta, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtCaneta, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbtDiminuir, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtAngulo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtApagador, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbtConfirma, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtAcima, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtBota, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbtCancela, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtAbaixo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbtTira, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jspPartes, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jspPontos, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jpnEditor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void atualizaPartes() {
        dlmPontos.clear();
        int ps = 0;
        if (jlsPartes.getSelectedIndex() > -1) {
            int[] sels = jlsPartes.getSelectedIndices();
            for (int sel : sels) {
                ForParte parte = (ForParte) dlmPartes.get(sel);
                for (Point2D ponto : parte.pontos()) {
                    dlmPontos.addElement(ponto);
                    ps++;
                }
            }
        }
        if (ps > 0) {
            jlsPontos.setSelectionInterval(0, ps - 1);
        }
    }

    private void jlsPartesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlsPartesValueChanged
        atualizaPartes();
    }//GEN-LAST:event_jlsPartesValueChanged

    private void atualizaPontos() {
        for (Component cmp : auxEdt.getComponents()) {
            if (cmp instanceof AuxPonto) {
                AuxPonto ap = (AuxPonto) cmp;
                ap.setBackground(Color.red);
            }
        }
        if (jlsPontos.getSelectedIndex() > -1) {
            int[] sels = jlsPontos.getSelectedIndices();
            for (int sel : sels) {
                Point2D ponto = (Point2D) dlmPontos.get(sel);
                pegaAuxPonto(ponto).setBackground(Color.blue);
            }
        }
        auxEdt.repaint();
    }

    private AuxPonto pegaAuxPonto(Point2D doPonto) {
        for (Component cmp : auxEdt.getComponents()) {
            if (cmp instanceof AuxPonto) {
                AuxPonto ap = (AuxPonto) cmp;
                if (Objects.equals(ap.ponto(), doPonto)) {
                    return ap;
                }
            }
        }
        return null;
    }

    private void jlsPontosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlsPontosValueChanged
        atualizaPontos();
    }//GEN-LAST:event_jlsPontosValueChanged

    private Point2D[] proximos(int quantidade) {
        Point2D ultimo = null;
        Point2D proximo = null;
        Double difX = null;
        Double difY = null;
        int sel = jlsPartes.getSelectedIndex();
        if (sel > -1) {
            ultimo = ((ForParte) dlmPartes.get(sel)).pegaUltimo(null);
            if (sel < dlmPartes.size() - 1) {
                proximo = ((ForParte) dlmPartes.get(sel + 1)).pega(0, null);
                difX = (proximo.getX() - ultimo.getX()) / (quantidade + 1.0);
                difY = (proximo.getY() - ultimo.getY()) / (quantidade + 1.0);
            }
        } else {
            if (formato.partes().size() > 0) {
                ultimo = formato.partes().get(formato.partes().size() - 1).pegaUltimo(null);
            }
        }
        Point2D[] retorno = new Point2D[quantidade];
        for (int iq = 0; iq < quantidade; iq++) {
            Point2D novo = null;
            if (ultimo == null) {
                novo = new Point2D.Double(30, 30);
            } else {
                if (proximo == null) {
                    novo = new Point2D.Double(ultimo.getX() + 30, ultimo.getY() + 30);
                } else {
                    novo = new Point2D.Double(ultimo.getX() + difX, ultimo.getY() + difY);
                }
            }
            retorno[iq] = novo;
            ultimo = novo;
        }
        return retorno;
    }

    private void insereParte(Integer noIndice, ForParte aParte) {
        dlmPartes.add(noIndice, aParte);
        formato.partes().add(noIndice, aParte);
        for (Point2D ponto : aParte.pontos()) {
            auxEdt.add(new AuxPonto(aParte, ponto), 0);
        }
        jlsPartes.setSelectedIndex(noIndice);
        atualizaPartes();
    }

    private void novoInicio() {
        int idx = jlsPartes.getSelectedIndex() + 1;
        if (jlsPartes.getSelectedIndex() <= -1) {
            idx = dlmPartes.getSize();
        }
        ForInicio novo = new ForInicio(proximos(1));
        insereParte(idx, novo);
    }

    private void novaLinha() {
        int idx = jlsPartes.getSelectedIndex() + 1;
        if (jlsPartes.getSelectedIndex() <= -1) {
            idx = dlmPartes.getSize();
        }
        ForLinha novo = new ForLinha(proximos(1));
        insereParte(idx, novo);
    }

    private void novaCurva() {
        int idx = jlsPartes.getSelectedIndex() + 1;
        if (jlsPartes.getSelectedIndex() <= -1) {
            idx = dlmPartes.getSize();
        }
        ForCurva novo = new ForCurva(proximos(2));
        insereParte(idx, novo);
    }

    private void novoSiado() {
        int idx = jlsPartes.getSelectedIndex() + 1;
        if (jlsPartes.getSelectedIndex() <= -1) {
            idx = dlmPartes.getSize();
        }
        ForSiado novo = new ForSiado(proximos(3));
        insereParte(idx, novo);
    }

    private void novoFim() {
        int idx = jlsPartes.getSelectedIndex() + 1;
        if (jlsPartes.getSelectedIndex() <= -1) {
            idx = dlmPartes.getSize();
        }
        ForFim novo = new ForFim();
        insereParte(idx, novo);
    }

    private void novoFechar() {
        int idx = jlsPartes.getSelectedIndex() + 1;
        if (jlsPartes.getSelectedIndex() <= -1) {
            idx = dlmPartes.getSize();
        }
        for (int in = idx - 1; in >= 0; in--) {
            if (dlmPartes.get(in) instanceof ForInicio) {
                ForInicio ini = (ForInicio) dlmPartes.get(in);
                Point2D pnt = ini.pega(0);
                ForLinha novo = new ForLinha(pnt);
                insereParte(idx, novo);
                break;
            }
        }
    }

    private void jbtBotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtBotaActionPerformed
//        try {
//            new Intf(new Campos(
//                    new Cmp(1, 1, "tipo", "Tipo", Dados.Tp.Enu)
//                            .botaOpcoes("(I) Início", "(L) Linha", "(C) Curva", "(S) Siado", "(F) Fim", "(R) Fechar")
//                            .mInicial("L")
//            )) {
//                @Override
//                public void preparaIntf() throws Exception {
//                    super.preparaIntf();
//                    botaConfirmarECancelar();
//                }
//
//                @Override
//                public void aoConfirmar(Object comOrigem) throws Exception {
//                    String tp = cmps().pega("tipo").edt().pgVlr().pgCrs();
//                    if (tp != null) {
//                        switch (tp) {
//                            case "I":
//                                novoInicio();
//                                break;
//                            case "L":
//                                novaLinha();
//                                break;
//                            case "C":
//                                novaCurva();
//                                break;
//                            case "S":
//                                novoSiado();
//                                break;
//                            case "F":
//                                novoFim();
//                                break;
//                            case "R":
//                                novoFechar();
//                                break;
//                        }
//                    }
//                    fecha();
//                }
//            }.mostra("Editor de Formato - Nova Parte");
//        } catch (Exception ex) {
//            Utl.registra(ex);
//        }
    }//GEN-LAST:event_jbtBotaActionPerformed

    private void jbtTiraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtTiraActionPerformed
        int sel = jlsPartes.getSelectedIndex();
        if (sel > -1) {
            ForParte parte = (ForParte) dlmPartes.get(sel);
            for (Point2D ponto : parte.pontos()) {
                AuxPonto ap = pegaAuxPonto(ponto);
                if (ap != null) {
                    auxEdt.remove(ap);
                }
            }
            dlmPartes.removeElement(parte);
            formato.partes().remove(parte);
        }
        sel--;
        jlsPartes.setSelectedIndex(sel);
        atualizaPartes();
    }//GEN-LAST:event_jbtTiraActionPerformed

    private void reestabelecePartes() {
        dlmPartes.clear();
        for (ForParte parte : formato.partes()) {
            dlmPartes.addElement(parte);
        }
    }

    private void jbtAcimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAcimaActionPerformed
        int sel = jlsPartes.getSelectedIndex();
        if (sel > 0) {
            Collections.swap(formato.partes(), sel, sel - 1);
            reestabelecePartes();
            jlsPartes.setSelectedIndex(sel - 1);
            atualizaPartes();
        }
    }//GEN-LAST:event_jbtAcimaActionPerformed

    private void jbtAbaixoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAbaixoActionPerformed
        int sel = jlsPartes.getSelectedIndex();
        if (sel < dlmPartes.getSize() - 1) {
            Collections.swap(formato.partes(), sel, sel + 1);
            reestabelecePartes();
            jlsPartes.setSelectedIndex(sel + 1);
            atualizaPartes();
        }
    }//GEN-LAST:event_jbtAbaixoActionPerformed

    private void jbtConfirmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtConfirmaActionPerformed
        editor.pgVlr().mGenerico(formato);
        editor.pgVlr().mTipo(VFor.Tp.Generico);
        editor.reprocessar();
        Janelas.fecha(this);
    }//GEN-LAST:event_jbtConfirmaActionPerformed

    private void jbtCancelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelaActionPerformed
        Janelas.fecha(this);
    }//GEN-LAST:event_jbtCancelaActionPerformed

    private void jbtCanetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCanetaActionPerformed
        if (Objects.equals(livre, Livre.Caneta)) {
            livre = Livre.Nenhum;
        } else {
            livre = Livre.Caneta;
        }
        auxEdt.repaint();
    }//GEN-LAST:event_jbtCanetaActionPerformed

    private void jbtApagadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtApagadorActionPerformed
        if (Objects.equals(livre, Livre.Borracha)) {
            livre = Livre.Nenhum;
        } else {
            livre = Livre.Borracha;
        }
        auxEdt.repaint();
    }//GEN-LAST:event_jbtApagadorActionPerformed

    private void jbtPontaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtPontaActionPerformed
//        try {
//            new Intf(new Campos(
//                    new Cmp(1, 1, "tipo", "Tipo", Dados.Tp.Enu).botaOpcoes(LivreTp.class).mInicial(livTipo),
//                    new Cmp(2, 1, "lados", "Lados", Dados.Tp.Int).mInicial(livLados),
//                    new Cmp(3, 1, "tamanho", "Tamanho", Dados.Tp.NumLon).mInicial(livTamanho),
//                    new Cmp(4, 1, "excentricidade", "Excentricidade", Dados.Tp.NumLon).mInicial(livExcentricidade)
//            )) {
//                @Override
//                public void preparaIntf() throws Exception {
//                    super.preparaIntf();
//                    botaConfirmarECancelar();
//                }
//
//                @Override
//                public void aoConfirmar(Object comOrigem) throws Exception {
//                    livTipo = (LivreTp) cmps().pega("tipo").edt().pgVlr().pg();
//                    livLados = cmps().pega("lados").edt().pgVlr().pgInt();
//                    livTamanho = cmps().pega("tamanho").edt().pgVlr().pgNumLon();
//                    livExcentricidade = cmps().pega("excentricidade").edt().pgVlr().pgNumLon();
//                    constroiLivre();
//                    auxEdt.repaint();
//                    fecha();
//                }
//            }.mostra("Ponta");
//        } catch (Exception ex) {
//            Utl.registra(ex);
//        }
    }//GEN-LAST:event_jbtPontaActionPerformed

    private void jbtAnguloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAnguloActionPerformed
        new AuxAngulo(jbtAngulo, livAngulo) {
            @Override
            public Boolean aoMudar(Double paraAngulo) throws Exception {
                livAngulo = paraAngulo;
                auxEdt.repaint();
                return true;
            }
        }.mostra();
    }//GEN-LAST:event_jbtAnguloActionPerformed

    private void jbtAumentarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAumentarActionPerformed
        livTamanho++;
        constroiLivre(livTipo, livLados, livTamanho, livExcentricidade);
        auxEdt.repaint();
    }//GEN-LAST:event_jbtAumentarActionPerformed

    private void jbtDiminuirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtDiminuirActionPerformed
        livTamanho--;
        constroiLivre(livTipo, livLados, livTamanho, livExcentricidade);
        auxEdt.repaint();
    }//GEN-LAST:event_jbtDiminuirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtAbaixo;
    private javax.swing.JButton jbtAcima;
    private javax.swing.JButton jbtAngulo;
    private javax.swing.JButton jbtApagador;
    private javax.swing.JButton jbtAumentar;
    private javax.swing.JButton jbtBota;
    private javax.swing.JButton jbtCancela;
    private javax.swing.JButton jbtCaneta;
    private javax.swing.JButton jbtConfirma;
    private javax.swing.JButton jbtDiminuir;
    private javax.swing.JButton jbtPonta;
    private javax.swing.JButton jbtTira;
    private javax.swing.JList<String> jlsPartes;
    private javax.swing.JList<String> jlsPontos;
    private javax.swing.JPanel jpnEditor;
    private javax.swing.JScrollPane jspPartes;
    private javax.swing.JScrollPane jspPontos;
    // End of variables declaration//GEN-END:variables

    private class AuxEditor extends JPanel {

        private List<AuxTemp> temps;
        private EvtEdtMouse evtMouse;
        private int mposX;
        private int mposY;

        public AuxEditor() {
            super();
            setLayout(null);
            setBackground(Color.white);
            evtMouse = new EvtEdtMouse();
            addMouseListener(evtMouse);
            addMouseMotionListener(evtMouse);
            temps = new ArrayList<>();
            mposX = 0;
            mposY = 0;
        }

        public List<AuxTemp> temporarios() {
            return temps;
        }

        public EvtEdtMouse evtMouse() {
            return evtMouse;
        }

        private GeneralPath pegaLivre() {
            GeneralPath retorno = new GeneralPath(livFormato);
            if (livAngulo != null) {
                retorno.transform(AffineTransform.getRotateInstance(Math.toRadians(livAngulo), retorno.getBounds2D().getCenterX(), retorno.getBounds2D().getCenterY()));
            }
            double tx = mposX - livFormato.getBounds2D().getCenterX();
            double ty = mposY - livFormato.getBounds2D().getCenterY();
            retorno.transform(AffineTransform.getTranslateInstance(tx, ty));
            return retorno;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D gr = (Graphics2D) g;
            try {
                formato.constroi();
                gr.setPaint(Color.black);
                gr.draw(formato);
                gr.fill(formato);
            } catch (Exception ex) {
            }
            if (livre != null && livre != Livre.Nenhum) {
                Shape livTr = pegaLivre();
                switch (livre) {
                    case Caneta:
                        gr.setPaint(Color.green);
                        gr.draw(livTr);
                        gr.fill(livTr);
                        break;
                    case Borracha:
                        gr.setPaint(Color.red);
                        gr.draw(livTr);
                        gr.fill(livTr);
                        break;
                }
            }
        }

        private class EvtEdtMouse extends MouseAdapter {

            private void insereTmp(MouseEvent e) {
                AuxTemp tmp = new AuxTemp(new Point2D.Double(e.getX(), e.getY()), e.isShiftDown());
                add(tmp);
                temps.add(tmp);
                repaint();
            }

            private void finaliza() {
                int idx = jlsPartes.getSelectedIndex() + 1;
                if (jlsPartes.getSelectedIndex() <= -1) {
                    idx = dlmPartes.getSize();
                }
                int it = 0;
                while (it < temps.size()) {
                    int rst = temps.size() - (it + 1);
                    if (rst <= 0) {
                        ForLinha prt = new ForLinha(temps.get(it).ponto());
                        insereParte(idx, prt);
                        idx++;
                        it = it + 1;
                    } else if (rst == 1) {
                        ForCurva prt = new ForCurva(temps.get(it).ponto(), temps.get(it + 1).ponto());
                        insereParte(idx, prt);
                        idx++;
                        it = it + 2;
                    } else {
                        if (temps.get(it).prefSiado()) {
                            ForSiado prt = new ForSiado(temps.get(it).ponto(), temps.get(it + 1).ponto(), temps.get(it + 2).ponto());
                            insereParte(idx, prt);
                            idx++;
                            it = it + 3;
                        } else {
                            ForCurva prt = new ForCurva(temps.get(it).ponto(), temps.get(it + 1).ponto());
                            insereParte(idx, prt);
                            idx++;
                            it = it + 2;
                        }
                    }
                }
                for (AuxTemp tmp : temps) {
                    remove(tmp);
                }
                temps.clear();
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mposX = e.getX();
                mposY = e.getY();
                if (livre != null && livre != Livre.Nenhum) {
                    repaint();
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (livre != null && livre != Livre.Nenhum) {
                    Shape livTr = pegaLivre();
                    Area ar = new Area(formato.constroi());
                    if (livre == Livre.Caneta) {
                        ar.add(new Area(livTr));
                    } else if (livre == Livre.Borracha) {
                        ar.subtract(new Area(livTr));
                    }
                    formato.mudaPartes(ForParte.infere(ar.getPathIterator(null)));
                    carregaPartes();
                } else if (e.isAltDown() || e.isShiftDown()) {
                    insereTmp(e);
                } else if (e.isControlDown()) {
                    insereTmp(e);
                    finaliza();
                }
            }

        }
    }

    private class AuxPonto extends JPanel {

        private final ForParte parte;
        private final Point2D ponto;
        private final EvtPntMouse evtMouse;

        public AuxPonto(ForParte daParte, Point2D ePonto) {
            this.parte = daParte;
            this.ponto = ePonto;
            setBounds(UtlInt.pega(ePonto.getX() - 5), UtlInt.pega(ePonto.getY() - 5), 10, 10);
            setBorder(BorderFactory.createLineBorder(Color.black, 1));
            setBackground(Color.red);
            evtMouse = new EvtPntMouse();
            addMouseListener(evtMouse);
            addMouseMotionListener(evtMouse);
        }

        public ForParte parte() {
            return parte;
        }

        public Point2D ponto() {
            return ponto;
        }

        private class EvtPntMouse extends MouseAdapter {

            private Point iniMouse = null;
            private Point iniLocal = null;

            @Override
            public void mousePressed(MouseEvent e) {
                if (livre != null && livre != Livre.Nenhum) {
                    e.translatePoint(getLocation().x, getLocation().y);
                    auxEdt.evtMouse.mouseClicked(e);
                } else if (e.isAltDown() || e.isControlDown() || e.isShiftDown()) {
                    e.translatePoint(getLocation().x, getLocation().y);
                    auxEdt.evtMouse.mouseClicked(e);
                } else {
                    iniMouse = e.getLocationOnScreen();
                    iniLocal = getLocation();
                    jlsPartes.setSelectedValue(parte, true);
                    jlsPontos.setSelectedValue(ponto, true);
                    auxEdt.repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                iniMouse = null;
                iniLocal = null;
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                e.translatePoint(getLocation().x, getLocation().y);
                auxEdt.evtMouse.mouseMoved(e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (iniMouse != null) {
                    int difX = e.getXOnScreen() - iniMouse.x;
                    int difY = e.getYOnScreen() - iniMouse.y;
                    Point novo = new Point(iniLocal.x + difX, iniLocal.y + difY);
                    setLocation(novo);
                    ponto.setLocation(novo.getX() + 5, novo.getY() + 5);
                    auxEdt.repaint();
                }
            }

        }

    }

    private class AuxTemp extends JPanel {

        private final Point2D ponto;
        private final Boolean prefSiado;
        private final EvtTmpMouse evtMouse;

        public AuxTemp(Point2D oPonto, Boolean ePrefSiado) {
            this.ponto = oPonto;
            this.prefSiado = ePrefSiado;
            setBounds(UtlInt.pega(oPonto.getX() - 5), UtlInt.pega(oPonto.getY() - 5), 10, 10);
            setBorder(BorderFactory.createLineBorder(Color.black, 1));
            setBackground(Color.green);
            evtMouse = new EvtTmpMouse();
            addMouseListener(evtMouse);
            addMouseMotionListener(evtMouse);
        }

        public Point2D ponto() {
            return ponto;
        }

        public Boolean prefSiado() {
            return prefSiado;
        }

        private class EvtTmpMouse extends MouseAdapter {

            private Point iniMouse = null;
            private Point iniLocal = null;

            @Override
            public void mousePressed(MouseEvent e) {
                if (livre != null && livre != Livre.Nenhum) {
                    e.translatePoint(getLocation().x, getLocation().y);
                    auxEdt.evtMouse.mouseClicked(e);
                } else if (e.isAltDown() || e.isControlDown() || e.isShiftDown()) {
                    e.translatePoint(getLocation().x, getLocation().y);
                    auxEdt.evtMouse.mouseClicked(e);
                } else {
                    iniMouse = e.getLocationOnScreen();
                    iniLocal = getLocation();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                iniMouse = null;
                iniLocal = null;
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                e.translatePoint(getLocation().x, getLocation().y);
                auxEdt.evtMouse.mouseMoved(e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (iniMouse != null) {
                    int difX = e.getXOnScreen() - iniMouse.x;
                    int difY = e.getYOnScreen() - iniMouse.y;
                    Point novo = new Point(iniLocal.x + difX, iniLocal.y + difY);
                    setLocation(novo);
                    ponto.setLocation(novo.getX() + 5, novo.getY() + 5);
                }
            }
        }
    }

    private class ActCaneta extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            jbtCanetaActionPerformed(e);
        }
    }

    private class ActApagador extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            jbtApagadorActionPerformed(e);
        }
    }

    private class ActPonta extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            jbtPontaActionPerformed(e);
        }
    }

    private class ActAngulo extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            jbtAnguloActionPerformed(e);
        }
    }

    private class ActAumentar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            jbtAumentarActionPerformed(e);
        }
    }

    private class ActDiminuir extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            jbtDiminuirActionPerformed(e);
        }
    }

    private class ActBota extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            jbtBotaActionPerformed(e);
        }
    }

    private class ActBotaInicio extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            novoInicio();
        }
    }

    private class ActBotaLinha extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            novaLinha();
        }
    }

    private class ActBotaCurva extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            novaCurva();
        }
    }

    private class ActBotaSiado extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            novoSiado();
        }
    }

    private class ActBotaFim extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            novoFim();
        }
    }

    private class ActTira extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            jbtTiraActionPerformed(e);
        }
    }

    private class ActAcima extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            jbtAcimaActionPerformed(e);
        }
    }

    private class ActAbaixo extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            jbtAbaixoActionPerformed(e);
        }
    }

    private class ActConfirma extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            jbtConfirmaActionPerformed(e);
        }
    }

    private class ActPartesSelecionaTudo extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!dlmPartes.isEmpty()) {
                jlsPartes.setSelectionInterval(0, dlmPartes.getSize() - 1);
            }
        }
    }

    private class ActPontosSelecionaTudo extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!dlmPontos.isEmpty()) {
                jlsPontos.setSelectionInterval(0, dlmPontos.getSize() - 1);
            }
        }
    }

    private void atualizaAux(Point2D doPonto) {
        AuxPonto ax = pegaAuxPonto(doPonto);
        ax.setBounds(UtlInt.pega(doPonto.getX() - 5), UtlInt.pega(doPonto.getY() - 5), 10, 10);
        auxEdt.repaint();
    }

    private class ActMoveParteX extends AbstractAction {

        private final Integer movedor;

        public ActMoveParteX(Integer movedor) {
            this.movedor = movedor;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (jlsPartes.getSelectedIndex() > -1) {
                int[] sels = jlsPartes.getSelectedIndices();
                for (int sel : sels) {
                    ForParte parte = (ForParte) dlmPartes.get(sel);
                    for (Point2D ponto : parte.pontos()) {
                        ponto.setLocation(ponto.getX() + movedor, ponto.getY());
                        atualizaAux(ponto);
                    }
                }
            }
        }
    }

    private class ActMoveParteY extends AbstractAction {

        private final Integer movedor;

        public ActMoveParteY(Integer movedor) {
            this.movedor = movedor;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (jlsPartes.getSelectedIndex() > -1) {
                int[] sels = jlsPartes.getSelectedIndices();
                for (int sel : sels) {
                    ForParte parte = (ForParte) dlmPartes.get(sel);
                    for (Point2D ponto : parte.pontos()) {
                        ponto.setLocation(ponto.getX(), ponto.getY() + movedor);
                        atualizaAux(ponto);
                    }
                }
            }
        }
    }

    private class ActMovePontoX extends AbstractAction {

        private final Integer movedor;

        public ActMovePontoX(Integer movedor) {
            this.movedor = movedor;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (jlsPontos.getSelectedIndex() > -1) {
                int[] sels = jlsPontos.getSelectedIndices();
                for (int sel : sels) {
                    Point2D ponto = (Point2D) dlmPontos.get(sel);
                    ponto.setLocation(ponto.getX() + movedor, ponto.getY());
                    atualizaAux(ponto);
                }
            }
        }
    }

    private class ActMovePontoY extends AbstractAction {

        private final Integer movedor;

        public ActMovePontoY(Integer movedor) {
            this.movedor = movedor;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (jlsPontos.getSelectedIndex() > -1) {
                int[] sels = jlsPontos.getSelectedIndices();
                for (int sel : sels) {
                    Point2D ponto = (Point2D) dlmPontos.get(sel);
                    ponto.setLocation(ponto.getX(), ponto.getY() + movedor);
                    atualizaAux(ponto);
                }
            }
        }
    }

}
