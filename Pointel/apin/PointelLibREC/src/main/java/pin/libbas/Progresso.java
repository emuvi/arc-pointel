package pin.libbas;

import java.awt.Font;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.SwingUtilities;
import pin.libjan.Janelas;
import pin.libutl.Utl;
import pin.libutl.UtlBin;
import pin.libutl.UtlTex;

public class Progresso extends javax.swing.JFrame {

    private final Retornos retornos;
    private final Retornos erros;
    private volatile String descricao;
    private volatile AtomicInteger contador;
    private volatile Boolean pausar;
    private volatile Boolean parar;
    private volatile Boolean terminado;

    public Progresso(String comTitulo) {
        this(comTitulo, true, true);
    }

    public Progresso(String comTitulo, Boolean pausavel) {
        this(comTitulo, pausavel, true);
    }

    public Progresso(String comTitulo, Boolean pausavel, Boolean paravel) {
        initComponents();
        setTitle(comTitulo);
        retornos = new Retornos();
        erros = new Retornos();
        descricao = null;
        contador = new AtomicInteger();
        jbtRetornos.setVisible(true);
        jlbRetornos.setVisible(true);
        jpbProgresso.setMinimum(0);
        jpbProgresso.setMaximum(0);
        jpbProgresso.setValue(0);
        pausar = false;
        parar = false;
        terminado = false;
        jckPausar.setSelected(false);
        jckPausar.setVisible(pausavel);
        jbtPausar.setVisible(pausavel);
        jbtParar.setVisible(paravel);
        jtaMensagem.setFont(new Font(Font.SERIF, Font.BOLD, 14));
        jtaMensagem.setText(comTitulo);
        Janelas.inicia(this);
    }

    public Retornos pRetornos() {
        return retornos;
    }

    public Retornos pErros() {
        return erros;
    }

    public String pDescricao() {
        return descricao;
    }

    public Progresso mDescricao(String para) {
        this.descricao = para;
        return this;
    }

    public Progresso abre() {
        return abre(null, 0, 0);
    }

    public Progresso abre(String comMensagem) {
        return abre(comMensagem, 0, 0);
    }

    public Progresso abre(Integer comMaximo) {
        return abre(null, 0, comMaximo);
    }

    public Progresso abre(Integer comMinimo, Integer eMaximo) {
        return abre(null, comMinimo, eMaximo);
    }

    public Progresso abre(String comMensagem, Integer eMinimo, Integer eMaximo) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (comMensagem != null) {
                    bota(comMensagem);
                }
                jpbProgresso.setMinimum(eMinimo);
                jpbProgresso.setMaximum(eMaximo);
                jpbProgresso.setValue(eMinimo);
                setVisible(true);
            }
        });
        return this;
    }

    public Progresso bota(Object oValor) {
        return bota(null, oValor);
    }

    public Progresso bota(String comDescricao, Object oValor) {
        contador.incrementAndGet();
        Retornos.bota(retornos, comDescricao != null ? comDescricao : descricao, oValor);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                String msg = String.valueOf(oValor);
                if (descricao != null) {
                    msg = descricao + UtlTex.quebra(2) + msg;
                }
                if (comDescricao != null) {
                    msg = comDescricao + UtlTex.quebra(2) + msg;
                }
                jtaMensagem.setText(msg);
                if (jlbRetornos.isVisible()) {
                    jlbRetornos.setText("(" + contador.get() + ")");
                }
            }
        });
        return this;
    }

    public Progresso bota(Throwable oErro) {
        return bota(null, oErro);
    }

    public Progresso bota(String comDescricao, Throwable oErro) {
        contador.incrementAndGet();
        Retornos.bota(retornos, comDescricao != null ? comDescricao : descricao, oErro);
        Retornos.bota(erros, comDescricao != null ? comDescricao : descricao, oErro);
        if (jckPausar.isSelected()) {
            pausar = true;
            toFront();
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                String msg = oErro.getMessage();
                if (comDescricao != null) {
                    msg = comDescricao + "\n" + msg;
                } else if (descricao != null) {
                    msg = descricao + "\n" + msg;
                }
                jtaMensagem.setText(msg);
                if (jlbRetornos.isVisible()) {
                    jlbRetornos.setText("(" + contador.get() + ")");
                }
                if (jckPausar.isSelected()) {
                    jbtPausar.setText("Reiniciar");
                }
            }
        });
        return this;
    }

    public Integer pegaContador() {
        return contador.get();
    }

    public Progresso aumenta() {
        return aumenta(1);
    }

    public Progresso aumenta(Integer posicoes) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                jpbProgresso.setMaximum(jpbProgresso.getMaximum() + posicoes);
            }
        });

        return this;
    }

    public Progresso mudaProgresso(Long oProgresso) {
        return mProgresso(oProgresso.intValue());
    }

    public Progresso mProgresso(Integer oProgresso) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                jpbProgresso.setValue(oProgresso);
            }
        });
        return this;
    }

    public Progresso mudaTamanho(Long oTamanho) {
        return mTamanho(oTamanho.intValue());
    }

    public Progresso mTamanho(Integer oTamanho) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                jpbProgresso.setMinimum(0);
                jpbProgresso.setValue(0);
                jpbProgresso.setMaximum(oTamanho);
            }
        });
        return this;
    }

    public Progresso avanca() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (jpbProgresso.getValue() + 1 <= jpbProgresso.getMaximum()) {
                    jpbProgresso.setValue(jpbProgresso.getValue() + 1);
                }
            }
        });
        return this;
    }

    public Progresso avanca(String comMensagem) {
        bota(comMensagem);
        return avanca();
    }

    public Progresso mudaPosicao(Integer naPosicao) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (naPosicao < jpbProgresso.getMinimum()) {
                    jpbProgresso.setValue(jpbProgresso.getMinimum());
                } else if (naPosicao <= jpbProgresso.getMaximum()) {
                    jpbProgresso.setValue(naPosicao);
                } else {
                    jpbProgresso.setValue(jpbProgresso.getMaximum());
                }
            }
        });
        return this;
    }

    public Progresso criaAutoCorrer() {
        jpbProgresso.setMinimum(0);
        jpbProgresso.setMaximum(100);
        jpbProgresso.setValue(0);
        new Thread("Progresso AutoCorrer") {
            @Override
            public void run() {
                boolean crescendo = true;
                while (!terminado && Progresso.this.isVisible()) {
                    UtlBin.esperaMilis(100);
                    if (jpbProgresso.getValue() == jpbProgresso.getMaximum()) {
                        crescendo = false;
                    } else if (jpbProgresso.getValue() == jpbProgresso.getMinimum()) {
                        crescendo = true;
                    }

                    if (crescendo) {
                        jpbProgresso.setValue(jpbProgresso.getValue() + 1);
                    } else {
                        jpbProgresso.setValue(jpbProgresso.getValue() - 1);
                    }
                }
                if (terminado) {
                    jpbProgresso.setValue(jpbProgresso.getMaximum());
                }
            }
        }.start();
        return this;
    }

    public Progresso botaPausa() {
        pausar = true;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                jbtPausar.setText("Continuar");
            }
        });
        return this;
    }

    public Progresso tiraPausa() {
        pausar = false;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                jbtPausar.setText("Pausar");
            }
        });
        return this;
    }

    public Progresso pausar() {
        while (pausar) {
            UtlBin.esperaMilis(100);
        }
        return this;
    }

    public Boolean devePausar() {
        return pausar;
    }

    public Boolean deveParar() {
        return parar;
    }

    public Boolean pausarEdeveParar() {
        pausar();
        return deveParar();
    }

    public Boolean avancaPausarEdeveParar() {
        avanca();
        pausar();
        return deveParar();
    }

    public Boolean avancaPausarEdeveParar(String comMensagem) {
        bota(comMensagem);
        avanca();
        pausar();
        return deveParar();
    }

    public Progresso termina() {
        descricao = null;
        terminado = true;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                jpbProgresso.setValue(jpbProgresso.getMaximum());
                bota("Terminado");
                jbtPausar.setText("Pausar");
                jbtParar.setText("Terminado");
                jckPausar.setSelected(false);
                jckPausar.setEnabled(false);
                jbtPausar.setEnabled(false);
                jbtParar.setEnabled(false);
            }
        });
        return this;
    }

    public Boolean terminado() {
        return terminado;
    }

    public void fechar() {
        termina();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Janelas.fecha(Progresso.this);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jspMensagem = new javax.swing.JScrollPane();
        jtaMensagem = new javax.swing.JTextArea();
        jpbProgresso = new javax.swing.JProgressBar();
        jbtErros = new javax.swing.JButton();
        jbtRetornos = new javax.swing.JButton();
        jlbRetornos = new javax.swing.JLabel();
        jckPausar = new javax.swing.JCheckBox();
        jbtPausar = new javax.swing.JButton();
        jbtParar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Progresso");

        jtaMensagem.setEditable(false);
        jtaMensagem.setColumns(20);
        jtaMensagem.setLineWrap(true);
        jtaMensagem.setRows(1);
        jtaMensagem.setWrapStyleWord(true);
        jtaMensagem.setOpaque(false);
        jspMensagem.setViewportView(jtaMensagem);

        jbtErros.setText("Erros");
        jbtErros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtErrosActionPerformed(evt);
            }
        });

        jbtRetornos.setText("Retornos");
        jbtRetornos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRetornosActionPerformed(evt);
            }
        });

        jlbRetornos.setText("(0)");

        jckPausar.setText("Se Errar");

        jbtPausar.setText("Pausar");
        jbtPausar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtPausarActionPerformed(evt);
            }
        });

        jbtParar.setText("Parar");
        jbtParar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtPararActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspMensagem, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
                    .addComponent(jpbProgresso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jbtErros)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtRetornos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlbRetornos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jckPausar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtPausar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtParar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jspMensagem, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpbProgresso, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtPausar)
                    .addComponent(jbtParar)
                    .addComponent(jckPausar)
                    .addComponent(jbtRetornos)
                    .addComponent(jlbRetornos)
                    .addComponent(jbtErros))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtPararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtPararActionPerformed
        jbtPausar.setText("Pausar");
        jbtParar.setText("Parando");
        jckPausar.setSelected(false);
        jckPausar.setEnabled(false);
        jbtPausar.setEnabled(false);
        jbtParar.setEnabled(false);
        parar = true;
        pausar = false;
    }//GEN-LAST:event_jbtPararActionPerformed

    private void jbtPausarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtPausarActionPerformed
        pausar = !pausar;
        if (pausar) {
            jbtPausar.setText("Continuar");
        } else {
            jbtPausar.setText("Pausar");
        }
    }//GEN-LAST:event_jbtPausarActionPerformed

    private void jbtRetornosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRetornosActionPerformed
        new Thread("Abre Retornos") {
            @Override
            public void run() {
                try {
                    new RetornosIntf().abre(retornos).mostra();
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        }.start();

    }//GEN-LAST:event_jbtRetornosActionPerformed

    private void jbtErrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtErrosActionPerformed
        new Thread("Abre Erros") {
            @Override
            public void run() {
                try {
                    new RetornosIntf().abre(erros).mostra();
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        }.start();
    }//GEN-LAST:event_jbtErrosActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtErros;
    private javax.swing.JButton jbtParar;
    private javax.swing.JButton jbtPausar;
    private javax.swing.JButton jbtRetornos;
    private javax.swing.JCheckBox jckPausar;
    private javax.swing.JLabel jlbRetornos;
    private javax.swing.JProgressBar jpbProgresso;
    private javax.swing.JScrollPane jspMensagem;
    private javax.swing.JTextArea jtaMensagem;
    // End of variables declaration//GEN-END:variables
}
