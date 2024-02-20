package pin.libamg;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import pin.ldtdoc.LdtDoc;
import pin.libetf.TexEtf;
import pin.libutl.Utl;
import pin.libvlr.Vlr;

public class EdtDoc extends Edt {

    private final LdtDoc controlador;

    public EdtDoc() {
        super();
        controlador = new LdtDoc();
        add(controlador, BorderLayout.CENTER);
        inicia();
    }

    @Override
    public void inicia() {
        controlador.controle().addHyperlinkListener(new FazVinculos());
        pPopMenu().bota("Inserir", 'I');
        pPopMenu().bota("Inserir", "Tabela", 'T', new ActInsTabela());
        pPopMenu().bota("Editar", 'D');
        pPopMenu().bota("Editar", "Negrito", 'N', new ActEdiNegrito());
        pPopMenu().bota("Editar", "Itálico", 'I', new ActEdiItalico());
        pPopMenu().bota("Editar", "Sublinhado", 'S', new ActEdiSublinhado());
        pPopMenu().bota("Editar", "Riscado", 'R', new ActEdiRiscado());
        pPopMenu().bota("Editar", "Sobrescrito", 'O', new ActEdiSobrescrito());
        pPopMenu().bota("Editar", "Subscrito", 'U', new ActEdiSubscrito());
        pPopMenu().bota("Editar", "Lista", 'U', new ActEdiLista());
        pPopMenu().bota("Editar", "Ordenada", 'U', new ActEdiOrdenada());
        pPopMenu().bota("Editar", "Fonte", 'U', new ActEdiFonte());
        pPopMenu().bota("Editar", "À Esquerda", 'U', new ActEdiEsquerda());
        pPopMenu().bota("Editar", "Ao Centro", 'U', new ActEdiCentro());
        pPopMenu().bota("Editar", "À Direita", 'U', new ActEdiDireita());
        pPopMenu().bota("Editar", "Justificado", 'U', new ActEdiJustificado());
        pPopMenu().bota("Editar", "Âncora", 'U', new ActEdiAncora());
        pPopMenu().bota("Visualizar", 'V');
        pPopMenu().bota("Visualizar", "Atualiza", new ActVisAtualiza());
        pPopMenu().bota("Visualizar", "Fonte", new ActVisFonte());
        mDimensao(360, 270);
        super.inicia();
    }

    @Override
    public void atualizaOpcoes() {
    }

    @Override
    public void atualizaParams() {
    }

    @Override
    public JEditorPane controle() {
        return controlador.controle();
    }

    @Override
    public JScrollPane rolagem() {
        return controlador.rolagem();
    }

    @Override
    public synchronized Vlr pgVlr(Object comPadrao) throws Exception {
        if (vazio()) {
            return Vlr.novo(comPadrao);
        } else {
            return null;
        }
    }

    @Override
    public synchronized EdtDoc mdVlr(Object para) throws Exception {
        return this;
    }

    @Override
    public EdtDoc botaAoModificar(AbstractAction aAcao) {

        return this;
    }

    @Override
    public EdtDoc auxiliar() throws Exception {

        return this;
    }

    @Override
    public EdtDoc aleatorio() {

        return this;
    }

    @Override
    public Boolean vazio() {
        return null;
    }

    @Override
    public EdtDoc mEditavel(Boolean para) {
        controle().setEditable(para);
        return this;
    }

    @Override
    public Boolean editavel() {
        return controle().isEditable();
    }

    private class ActInsTabela extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class ActEdiNegrito extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            controlador.editaNegrito(e);
        }
    }

    private class ActEdiItalico extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            controlador.editaItalico(e);
        }
    }

    private class ActEdiSublinhado extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            controlador.editaSublinhado(e);
        }
    }

    private class ActEdiRiscado extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            controlador.editaRiscado(e);
        }
    }

    private class ActEdiSobrescrito extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            controlador.editaSobrescrito(e);
        }
    }

    private class ActEdiSubscrito extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            controlador.editaSubescrito(e);
        }
    }

    private class ActEdiLista extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            controlador.editaLista(e);
        }
    }

    private class ActEdiOrdenada extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            controlador.editaOrdenada(e);
        }
    }

    private class ActEdiFonte extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            controlador.editaFonte(e);
        }
    }

    private class ActEdiEsquerda extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            controlador.editaEsquerda(e);
        }
    }

    private class ActEdiCentro extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            controlador.editaCentro(e);
        }
    }

    private class ActEdiDireita extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            controlador.editaDireita(e);
        }
    }

    private class ActEdiJustificado extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            controlador.editaJustificado(e);
        }
    }

    private class ActEdiAncora extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            controlador.editaAncora(e);
        }
    }

    private class ActVisAtualiza extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            controlador.atualiza();
        }
    }

    private class ActVisFonte extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                new TexEtf()
                        .mostra("Fonte do Editor de Documento")
                        .abre(controlador.pFonte());
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    private class FazVinculos implements HyperlinkListener {

        @Override
        public void hyperlinkUpdate(HyperlinkEvent e) {
            Utl.alerta(e.getURL().toString());
        }

    }

}
