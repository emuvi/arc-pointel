package pin.ldtfrm;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import pin.libamg.Edt;
import pin.libbas.Dados;
import pin.libjan.PaiTabela;
import pin.libjan.TrataIntf;
import pin.libutl.Utl;
import pin.libutl.UtlInt;

public class LdtFrm extends JPanel {

    private LdtFrmDoc documento;
    private PaiTabela cntPainel;
    private JScrollPane rolagem;

    public LdtFrm() {
        super(new BorderLayout());
        documento = new LdtFrmDoc();
        cntPainel = new PaiTabela()
                .bota(documento).insere()
                .terminaComDimensionavel();
        rolagem = new JScrollPane(cntPainel);
        add(rolagem, BorderLayout.CENTER);
    }

    public LdtFrmDoc controle() {
        return documento;
    }

    public JScrollPane rolagem() {
        return rolagem;
    }

    public Point pUltimoClique() {
        return documento.pUltimoClique();
    }

    public LdtFrm mudaDesenhoDimensao(Integer comLargura, Integer eAltura) {
        TrataIntf.mDimensao(documento, comLargura, eAltura);
        return this;
    }

    public LdtFrm mudaDesenhoAltura(Integer para) {
        TrataIntf.mudaAltura(documento, para);
        return this;
    }

    public LdtFrm mudaDesenhoLargura(Integer para) {
        TrataIntf.mudaLargura(documento, para);
        return this;
    }

    public LdtFrm insere(Dados.Tp doTipo, Point noPonto) {
        Edt novo = Edt.novo(doTipo);
        novo.pPopMenu().botaSeparador();
        novo.pPopMenu().bota("Exibe");
        novo.pPopMenu().bota("Exibe", "Na Ordem", new ActExiNaOrdem(novo));
        novo.pPopMenu().bota("Exibe", "Na Frente", new ActExiNaFrente(novo));
        novo.pPopMenu().bota("Exibe", "Atrás", new ActExiAtras(novo));
        novo.pPopMenu().bota("Exclui", 'X', new ActExclui(novo));
        novo.mPosicao(noPonto.x, noPonto.y);
        documento.add(novo, 0);
        documento.repaint();
        validate();
        return this;
    }

    private class ActExiNaOrdem extends AbstractAction {

        private Edt editor;

        public ActExiNaOrdem(Edt editor) {
            this.editor = editor;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (documento.isEnabled()) {
                Integer ind = UtlInt.pergunta("Ordem:", documento.getComponentZOrder(editor));
                if (ind != null) {
                    documento.setComponentZOrder(editor, ind);
                    documento.repaint();
                }
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActExiNaFrente extends AbstractAction {

        private Edt editor;

        public ActExiNaFrente(Edt editor) {
            this.editor = editor;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (documento.isEnabled()) {
                documento.remove(editor);
                documento.add(editor, 0);
                documento.repaint();
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActExiAtras extends AbstractAction {

        private Edt editor;

        public ActExiAtras(Edt editor) {
            this.editor = editor;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (documento.isEnabled()) {
                documento.remove(editor);
                documento.add(editor);
                documento.repaint();
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActExclui extends AbstractAction {

        private Edt editor;

        public ActExclui(Edt editor) {
            this.editor = editor;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (documento.isEnabled()) {
                editor.fechaEdt();
                documento.remove(editor);
                documento.repaint();
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

}
