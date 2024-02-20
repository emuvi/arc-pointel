package pin.ldtima;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileFilter;
import pin.libima.Salva;
import pin.libjan.PaiTabela;
import pin.libjan.PopMenu;
import pin.libout.ImageSelection;
import pin.libutl.Utl;
import pin.libutl.UtlArq;
import pin.libutl.UtlIma;

public class LdtIma extends JPanel {

    private ImaDoc documento;
    private PaiTabela cntPainel;
    private JScrollPane rolagem;
    private Relacionador relacionador;
    private JFileChooser selecionador;
    private Ponto clique;
    private Cursor cursorNormal;
    private Cursor cursorOperacao;
    private ArrayList<Operacao> operacoes;
    private Operacao operacao;
    private Boolean reprocessar;
    private PopMenu popMenu;
    private Boolean editavel;

    public LdtIma() {
        this(null);
    }

    public LdtIma(BufferedImage daImagem) {
        super(new BorderLayout());
        documento = new ImaDoc(daImagem);
        cntPainel = new PaiTabela()
                .bota(documento).insere()
                .terminaComDimensionavel();
        rolagem = new JScrollPane(cntPainel);
        add(rolagem, BorderLayout.CENTER);
        relacionador = new Relacionador();
        documento.addMouseListener(relacionador);
        documento.addMouseMotionListener(relacionador);
        selecionador = new JFileChooser();
        selecionador.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return ((f.isDirectory()) || (UtlArq.ehArquivoDasExtencoes(f, UtlIma.pegaExtensoes())));
            }

            @Override
            public String getDescription() {
                return UtlIma.pegaExtensoesDescricao();
            }
        });
        clique = new Ponto();
        cursorNormal = new Cursor(Cursor.DEFAULT_CURSOR);
        cursorOperacao = new Cursor(Cursor.CROSSHAIR_CURSOR);
        operacoes = new ArrayList<>();
        operacao = null;
        reprocessar = false;
    }

    public LdtIma botaPopMenu(PopMenu popMenu) {
        this.popMenu = popMenu;
        return this;
    }

    public ImaDoc controle() {
        return documento;
    }

    public JScrollPane rolagem() {
        return rolagem;
    }

    public LdtIma novo() {
//        new AuxDimEsp(400, 300) {
//            @Override
//            public void aoConfirmar(Integer comLargura, Integer eAltura) {
//                try {
//                    BufferedImage nova = new BufferedImage(comLargura, eAltura, BufferedImage.TYPE_INT_ARGB);
//                    Graphics2D graphics = nova.createGraphics();
//                    graphics.setPaint(new Color(255, 255, 255));
//                    graphics.fillRect(0, 0, comLargura, eAltura);
//                    documento.muda(nova);
//                    atualizaDescricao("Nova Imagem (" + comLargura + "x" + eAltura + ")");
//                } catch (Exception ex) {
//                    Utl.registra(ex);
//                }
//            }
//        }.mostra("Nova Imagem");
        return this;
    }

    public LdtIma abre() {
        if (selecionador.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                documento.muda(UtlIma.pega(selecionador.getSelectedFile()));
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
        return this;
    }

    public LdtIma salva() {
        if (selecionador.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                Salva.faz(documento.pega(), 1.0, selecionador.getSelectedFile());
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
        return this;
    }

    public LdtIma atualiza() {
        documento.atualiza();
        return this;
    }

    public Boolean estaEditavel() {
        return editavel;
    }

    public LdtIma mudaEditavel(Boolean editavel) {
        this.editavel = editavel;
        return this;
    }

    public LdtIma particiona() throws Exception {
//        new RetanguloIntf() {
//            @Override
//            public void aoConfirmar(Object comOrigem) throws Exception {
//                documento.muda(UtlIma.pegaParte(documento.pega(), pegaRetangulo()));
//                fecha();
//            }
//        }.mostra("Particiona");
        return this;
    }

    public LdtIma compara() throws Exception {
        BufferedImage img = UtlIma.pergunta();
        if (img != null) {
            Utl.alerta("Diferença %: " + UtlIma.diferencaPer(documento.pega(), img));
        }
        return this;
    }

    public LdtIma selecionaTudo() throws Exception {
        OpeSelRetangulo operac = new OpeSelRetangulo();
        operac.botaPonto(0, 0);
        operac.botaPonto(documento.pega().getWidth() - 1, documento.pega().getHeight() - 1);
        documento.executa(operac);
        return this;
    }

    public LdtIma selecionaRetangulo() {
        operacao = new OpeSelRetangulo();
        botaCursorOperacao();
        return this;
    }

    public LdtIma recorta() throws Exception {
        copia();
        documento.limpa();
        return this;
    }

    public LdtIma copia() throws Exception {
        ImageSelection imgSel = new ImageSelection(documento.pega());
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(imgSel, null);
        return this;
    }

    public LdtIma cola() throws Exception {
        Transferable transferable = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
        documento.muda(UtlIma.pega(transferable.getTransferData(DataFlavor.imageFlavor)));
        return this;
    }

    private void botaCursorOperacao() {
        documento.setCursor(cursorOperacao);
    }

    private void tiraCursorOperacao() {
        documento.setCursor(cursorNormal);
    }

    private void atualizaClique() {
        Double vezesX = 1.0;
        Double vezesY = 1.0;
        if (documento.tem()) {
            vezesX = new Double(documento.pegaLargura()) / documento.pegaLarguraExibida();
            vezesY = new Double(documento.pegaAltura()) / documento.pegaAlturaExibida();
        }
        Integer x = new Double(clique.posX * vezesX).intValue();
        Integer y = new Double(clique.posY * vezesY).intValue();
        String mous = " | X = " + x + " Y = " + y;
        String desc = "Novo Ponto (" + x + "x" + y;
        if (clique.temPuxado()) {
            Integer px = new Double(clique.puxadoX * vezesX).intValue();
            Integer py = new Double(clique.puxadoY * vezesY).intValue();
            mous += " pX = " + px + " pY = " + py;
            desc += "/" + px + "x" + py;
        }
        desc += ")";
    }

    private void atualizaMovido(MouseEvent me) {
        Double vezesX = 1.0;
        Double vezesY = 1.0;
        if (documento.tem()) {
            vezesX = new Double(documento.pegaLargura()) / documento.pegaLarguraExibida();
            vezesY = new Double(documento.pegaAltura()) / documento.pegaAlturaExibida();
        }
    }

    private class Relacionador extends MouseAdapter {

        private Point pegaClique(MouseEvent me) {
            Integer clickPosX = me.getX();
            Integer clickPosY = me.getY();
            if (documento.tem()) {
                if (!"Original".equals(documento.pegaZoom())) {
                    clickPosX = clickPosX * documento.pegaLargura() / documento.pegaLarguraExibida();
                    clickPosY = clickPosY * documento.pegaAltura() / documento.pegaAlturaExibida();
                }
            }
            return new Point(clickPosX--, clickPosY--);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                if (operacao != null) {
                    Point click = pegaClique(e);
                    clique.posX = click.x;
                    clique.posY = click.y;
                    clique.puxadoX = null;
                    clique.puxadoY = null;
                    atualizaClique();
                }
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            atualizaMovido(e);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (operacao != null) {
                Point click = pegaClique(e);
                clique.puxadoX = click.x;
                clique.puxadoY = click.y;
                atualizaClique();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                if (operacao != null) {
                    operacao.botaPonto(clique.posX, clique.posY, clique.puxadoX, clique.puxadoY);
                    atualiza();
                }
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                if (operacao != null) {
                    try {
                        documento.executa(operacao);
                        operacoes.add(operacao);
                    } catch (Exception ex) {
                        Utl.registra(ex);
                    } finally {
                        tiraCursorOperacao();
                        operacao = null;
                        atualiza();
                    }
                } else {
                    if (popMenu != null) {
                        popMenu.mostra(e.getComponent(), e.getX(), e.getY());
                    }
                }
            }
        }
    }

    private class ActPontos extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
//            if (operacao == null) {
//                Utl.alerta("Nenhuma Operação");
//            } else {
//                try {
//                    new IntfPontos()
//                            .mostra("Pontos da Operação")
//                            .abre(operacao.pontos());
//                } catch (Exception ex) {
//                    Utl.registra(ex);
//                }
//            }
        }
    }

//    private class IntfPontos extends EdtIntfLis {
//
//        @Override
//        public void preparaIntf() throws Exception {
//            super.preparaIntf();
//            botaBotao(new BotConfirmar());
//            mBotoesAlinhamento(Intf.AlinhamentoTp.Esquerda);
//        }
//
//        @Override
//        public void especializaIntf() throws Exception {
//            super.especializaIntf();
//            mAoAdicionar(new ActAdicionar());
//            mAoAlterar(new ActAlterar());
//        }
//
//        private class ActAdicionar extends AbstractAction {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    new PontoIntf() {
//                        @Override
//                        public void preparaIntf() throws Exception {
//                            botaBotao(new Botao() {
//                                @Override
//                                public void aoExecutar(Object comOrigem) {
//                                    try {
//                                        botaItem(pegaPnt());
//                                        fecha();
//                                    } catch (Exception ex) {
//                                        Utl.registra(ex);
//                                    }
//                                }
//                            });
//                        }
//                    }.mostra();
//                } catch (Exception ex) {
//                    Utl.registra(ex);
//                }
//            }
//        }
//
//        private class ActAlterar extends AbstractAction {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    new PontoIntf((Ponto) pItem()) {
//                        @Override
//                        public void preparaIntf() throws Exception {
//                            botaBotao(new Botao() {
//                                @Override
//                                public void aoExecutar(Object comOrigem) throws Exception {
//                                    mItem(pegaPnt());
//                                    fecha();
//                                }
//                            });
//                        }
//                    }.mostra();
//                } catch (Exception ex) {
//                    Utl.registra(ex);
//                }
//            }
//        }
//
//        private class BotConfirmar extends Botao {
//
//            public BotConfirmar() {
//                super("Confirmar");
//            }
//
//            @Override
//            public void aoExecutar(Object comOrigem) throws Exception {
//                if (operacao != null) {
//                    List<Object> itns = IntfPontos.this.pgLis();
//                    operacao.pontos().clear();
//                    if (itns != null) {
//                        for (Object itn : itns) {
//                            operacao.pontos().add((Ponto) itn);
//                        }
//                    }
//                    atualiza();
//                    fecha();
//                }
//            }
//        }
//    }
}
