package pin.libamk;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Objects;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import pin.libamg.EdtTex;
import pin.libbas.Roteiro;
import pin.libjan.Janelas;
import pin.libutl.Utl;
import pin.libutl.UtlArq;
import pin.libutl.UtlCrs;
import pin.libutl.UtlPro;
import pin.libutl.UtlTex;

public class RoteirosIntf extends javax.swing.JFrame {

    private String nome;
    private Integer prioridade;
    private JFileChooser escolhedor;
    private AbstractAction aoAbrir;
    private AbstractAction aoSalvar;

    public RoteirosIntf() {
        initComponents();
        nome = "Sem Nome";
        aoAbrir = null;
        aoSalvar = null;
        prioridade = Thread.NORM_PRIORITY;
        escolhedor = new JFileChooser();
        escolhedor.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return ((f.isDirectory()) || (UtlArq.ehArquivoDasExtencoes(f, new String[]{".pro"})));
            }

            @Override
            public String getDescription() {
                return "Pointel Roteiro (*.pro)";
            }
        });
        edtCodigo.mudaAoAbrir(new ActAbrir());
        edtCodigo.mudaAoSalvar(new ActSalvar());
        edtCodigo.pPopMenu().botaSeparador();
        edtCodigo.pPopMenu().bota("Nome", 'N', new ActNome()).botaAtalho(edtCodigo.controle(), "control shift F2");
        edtCodigo.pPopMenu().bota("Auxiliar", new ActAuxiliar()).botaAtalho(edtCodigo.controle(), "control shift SPACE");
        edtCodigo.pPopMenu().bota("Prioridade");
        edtCodigo.pPopMenu().bota("Prioridade", "Alta", new ActPrioridadeAlta());
        edtCodigo.pPopMenu().bota("Prioridade", "Média", new ActPrioridadeMedia());
        edtCodigo.pPopMenu().bota("Prioridade", "Baixa", new ActPrioridadeBaixa());
        edtCodigo.pPopMenu().bota("Executar", new ActExecutar()).botaAtalho(edtCodigo.controle(), "control shift ENTER");
        edtCodigo.pPopMenu().bota("Iniciar", new ActIniciar()).botaAtalho(edtCodigo.controle(), "control shift alt ENTER");
        Janelas.inicia(this);
    }

    public AbstractAction pAoAbrir() {
        return aoAbrir;
    }

    public RoteirosIntf mAoAbrir(AbstractAction aoAbrir) {
        this.aoAbrir = aoAbrir;
        return this;
    }

    public AbstractAction pAoSalvar() {
        return aoSalvar;
    }

    public RoteirosIntf mAoSalvar(AbstractAction aoSalvar) {
        this.aoSalvar = aoSalvar;
        return this;
    }

    public RoteirosIntf mostra() {
        Janelas.mostra(this);
        return this;
    }

    public RoteirosIntf mostra(String comTitulo) {
        setTitle(comTitulo);
        Janelas.mostra(this);
        return this;
    }

    public RoteirosIntf fecha() {
        Janelas.fecha(this);
        return this;
    }

    public String pNome() {
        return nome;
    }

    public RoteirosIntf mNome(String nome) {
        this.nome = nome;
        return this;
    }

    public Integer pPrioridade() {
        return prioridade;
    }

    public RoteirosIntf mPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
        return this;
    }

    public String pCodigo() throws Exception {
        return edtCodigo.pgVlr().pgCrs();
    }

    public RoteirosIntf mCodigo(String paraCodigo) throws Exception {
        edtCodigo.mdVlr(paraCodigo);
        return this;
    }

    public EdtTex edtCodigo() {
        return edtCodigo;
    }

    public Roteiro pRoteiro() throws Exception {
        return new Roteiro(nome, prioridade, edtCodigo.pgVlr().pgCrs(""));
    }

    public RoteirosIntf abre(File oArquivo) {
        try {
            edtCodigo.mdVlr(UtlTex.abre(oArquivo));
            escolhedor.setSelectedFile(oArquivo);
        } catch (Exception ex) {
            Utl.registra(ex);
        }
        return this;
    }

    public RoteirosIntf salva(File noArquivo) throws Exception {
        File escolhido = noArquivo;
        if (!escolhido.getAbsolutePath().endsWith(".pro")) {
            escolhido = new File(escolhido.getAbsolutePath() + ".pro");
        }
        UtlTex.salva(edtCodigo.pgVlr().pgCrs(""), escolhido);
        return this;
    }

    public RoteirosIntf inicia() throws Exception {
        new Roteiro(nome, prioridade, edtCodigo.pgVlr().pgCrs())
                .mostrador("Roteiro " + nome)
                .inicia();
        return this;
    }

    public RoteirosIntf executa() throws Exception {
        Utl.alerta(UtlPro.roteiro(edtCodigo.pgVlr().pgCrs()));
        return this;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        edtCodigo = new pin.libamg.EdtTex();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Roteiros");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(edtCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(edtCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private pin.libamg.EdtTex edtCodigo;
    // End of variables declaration//GEN-END:variables

    private class ActAbrir extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (aoAbrir != null) {
                aoAbrir.actionPerformed(e);
            } else {
                if (escolhedor.showOpenDialog(edtCodigo) == JFileChooser.APPROVE_OPTION) {
                    abre(escolhedor.getSelectedFile());
                    if (Objects.equals("Sem Nome", nome)) {
                        nome = escolhedor.getSelectedFile().getName();
                    }
                }
            }
        }
    }

    private class ActSalvar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (aoSalvar != null) {
                aoSalvar.actionPerformed(e);
            } else if (escolhedor.showSaveDialog(edtCodigo) == JFileChooser.APPROVE_OPTION) {
                try {
                    salva(escolhedor.getSelectedFile());
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        }
    }

    public class ActNome extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            nome = UtlCrs.pergunta("Nome Para o Roteiro", nome);
        }

    }

    public class ActAuxiliar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            new RoteirosIntfAux(RoteirosIntf.this).mostra();
        }

    }

    public class ActPrioridadeAlta extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            prioridade = Thread.MAX_PRIORITY;
        }

    }

    public class ActPrioridadeMedia extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            prioridade = Thread.NORM_PRIORITY;
        }

    }

    public class ActPrioridadeBaixa extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            prioridade = Thread.MIN_PRIORITY;
        }

    }

    public class ActExecutar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                executa();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }

    }

    public class ActIniciar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                inicia();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }

    }

}
