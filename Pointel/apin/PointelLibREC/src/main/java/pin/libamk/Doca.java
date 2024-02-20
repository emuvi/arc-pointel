package pin.libamk;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import pin.libbas.Analisa;
import pin.libjan.Janelas;
import pin.libutl.Utl;
import pin.libutl.UtlCor;

public class Doca extends JFrame {

    private final List<Analisa> aoReceber;
    private final Painel painel;

    public Doca(String comTitulo) {
        super(comTitulo);
        aoReceber = new ArrayList<>();
        painel = new Painel();
        painel.setDropTarget(new EvtTransferir());
        setContentPane(painel);
        setUndecorated(true);
        setAlwaysOnTop(true);
        Janelas.inicia(this);
        setSize(150, 150);
    }

    public Doca botaAoReceber(Analisa aAnalise) {
        aoReceber.add(aAnalise);
        return this;
    }

    public Doca tiraAoReceber(Analisa aAnalise) {
        aoReceber.remove(aAnalise);
        return this;
    }

    public Doca botaNoTopo() {
        setAlwaysOnTop(true);
        return this;
    }

    public Doca tiraNoTopo() {
        setAlwaysOnTop(false);
        return this;
    }

    public Doca mostra() {
        Janelas.mostra(this);
        return this;
    }

    private class Painel extends JPanel {

        public Painel() {
            super();
            setBorder(BorderFactory.createLineBorder(getForeground(), 1));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D gr = (Graphics2D) g;
            gr.setPaint(UtlCor.escurece(getBackground(), 30));
            int idy = 0;
            int idx = 0;
            int lrg = 30;
            int brd = 6;
            int grs = 4;
            while (idy < getHeight()) {
                while (idx < getWidth()) {
                    gr.fillRect(idx + ((lrg / 2) - (grs / 2)), idy + (brd), grs, lrg - (brd * 2));
                    gr.fillRect(idx + (brd), idy + ((lrg / 2) - (grs / 2)), lrg - (brd * 2), grs);
                    idx += lrg;
                }
                idx = 0;
                idy += lrg;
            }
            gr.setFont(getFont().deriveFont(Font.BOLD));
            gr.setPaint(getForeground());
            gr.drawString(getTitle(), 15, getHeight() - 15);
        }

    }

    private void recebido(Object oValor) throws Exception {
        for (Analisa anl : aoReceber) {
            anl.analisa(oValor);
        }
    }

    private class EvtTransferir extends DropTarget {

        protected void processDrag(DropTargetDragEvent dtde) {
            if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                dtde.acceptDrag(DnDConstants.ACTION_COPY);
            } else if (dtde.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                dtde.acceptDrag(DnDConstants.ACTION_COPY);
            } else if (dtde.isDataFlavorSupported(DataFlavor.imageFlavor)) {
                dtde.acceptDrag(DnDConstants.ACTION_COPY);
            } else if (dtde.isDataFlavorSupported(DataFlavor.allHtmlFlavor)) {
                dtde.acceptDrag(DnDConstants.ACTION_COPY);
            } else if (dtde.isDataFlavorSupported(DataFlavor.fragmentHtmlFlavor)) {
                dtde.acceptDrag(DnDConstants.ACTION_COPY);
            } else if (dtde.isDataFlavorSupported(DataFlavor.selectionHtmlFlavor)) {
                dtde.acceptDrag(DnDConstants.ACTION_COPY);
            } else {
                dtde.rejectDrag();
            }
        }

        @Override
        public void dragEnter(DropTargetDragEvent dtde) {
            processDrag(dtde);
        }

        @Override
        public void dragOver(DropTargetDragEvent dtde) {
            processDrag(dtde);
        }

        @Override
        public void drop(DropTargetDropEvent dtde) {
            try {
                Transferable transferable = dtde.getTransferable();
                if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                    dtde.acceptDrop(dtde.getDropAction());
                    List transferData = (List) transferable.getTransferData(DataFlavor.javaFileListFlavor);
                    if (transferData != null && transferData.size() > 0) {
                        for (int ia = 0; ia < transferData.size(); ia++) {
                            recebido(transferData.get(ia));
                        }
                        dtde.dropComplete(true);
                    }
                } else if (dtde.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    dtde.acceptDrop(dtde.getDropAction());
                    Object transferData = transferable.getTransferData(DataFlavor.stringFlavor);
                    if (transferData != null) {
                        recebido(transferData);
                        dtde.dropComplete(true);
                    }
                } else if (dtde.isDataFlavorSupported(DataFlavor.imageFlavor)) {
                    dtde.acceptDrop(dtde.getDropAction());
                    Object transferData = transferable.getTransferData(DataFlavor.imageFlavor);
                    if (transferData != null) {
                        recebido(transferData);
                        dtde.dropComplete(true);
                    }
                } else if (dtde.isDataFlavorSupported(DataFlavor.allHtmlFlavor)) {
                    dtde.acceptDrop(dtde.getDropAction());
                    Object transferData = transferable.getTransferData(DataFlavor.allHtmlFlavor);
                    if (transferData != null) {
                        recebido(transferData);
                        dtde.dropComplete(true);
                    }
                } else if (dtde.isDataFlavorSupported(DataFlavor.fragmentHtmlFlavor)) {
                    dtde.acceptDrop(dtde.getDropAction());
                    Object transferData = transferable.getTransferData(DataFlavor.allHtmlFlavor);
                    if (transferData != null) {
                        recebido(transferData);
                        dtde.dropComplete(true);
                    }
                } else if (dtde.isDataFlavorSupported(DataFlavor.selectionHtmlFlavor)) {
                    dtde.acceptDrop(dtde.getDropAction());
                    Object transferData = transferable.getTransferData(DataFlavor.allHtmlFlavor);
                    if (transferData != null) {
                        recebido(transferData);
                        dtde.dropComplete(true);
                    }
                } else {
                    dtde.rejectDrop();
                }
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }

    }

}
