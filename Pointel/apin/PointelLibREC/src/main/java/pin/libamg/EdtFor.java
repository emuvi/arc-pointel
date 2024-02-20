package pin.libamg;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import javax.swing.AbstractAction;
import javax.swing.JScrollPane;
import pin.ldtfor.LdtFor;
import pin.libvtf.ContornoVtf;
import pin.libvtf.FundoVtf;
import pin.libfor.Contorno;
import pin.libfor.Fonte;
import pin.libfor.Fundo;
import pin.libitr.QuestaoFonte;
import pin.libutl.Utl;
import pin.libutl.UtlCrs;
import pin.libutl.UtlIma;
import pin.libvlr.VFor;
import pin.libvlr.Vlr;

public class EdtFor extends Edt {

    private final LdtFor controlador;
    private Boolean editavel;

    public EdtFor() {
        super();
        controlador = new LdtFor();
        add(controlador, BorderLayout.CENTER);
        inicia();
    }

    @Override
    public void inicia() {
        editavel = true;
        pPopMenu().bota("Forma", 'F');
        pPopMenu().bota("Forma", "Genérico", 'G', new ActGenerico());
        pPopMenu().bota("Forma", "Texto", 'T', new ActTexto());
        pPopMenu().bota("Forma", "Imagem", 'I', new ActImagem());
        pPopMenu().bota("Forma", "Quadrado", 'Q', new ActQuadrado());
        pPopMenu().bota("Forma", "Retângulo", 'R', new ActRetangulo());
        pPopMenu().bota("Forma", "Circulo", 'C', new ActCirculo());
        pPopMenu().bota("Forma", "Elipse", 'E', new ActElipse());
        pPopMenu().bota("Forma", "Seta", 'S', new ActSeta());
        pPopMenu().bota("Forma", "União", 'U', new ActUniao());
        pPopMenu().bota("Forma", "Linha", 'L', new ActLinha());
        pPopMenu().bota("Forma", "Ponto", 'P', new ActPonto());
        pPopMenu().bota("Forma", "Vazia", 'V', new ActVazia());
        pPopMenu().bota("Inscrita", 'I', new ActInscrita());
        pPopMenu().bota("Ângulo", 'A', new ActAngulo());
        pPopMenu().bota("Posição", 'O', new ActApaPosPuxa());
        pPopMenu().bota("Tamanho", 'M', new ActApaLimPuxa());
        pPopMenu().bota("Contorno", 'N', new ActContorno());
        pPopMenu().bota("Preenchimento", 'H', new ActPreenchimento());

        super.inicia();
    }

    @Override
    public void atualizaOpcoes() {
    }

    @Override
    public void atualizaParams() {
    }

    @Override
    public LdtFor controle() {
        return controlador;
    }

    @Override
    public JScrollPane rolagem() {
        return null;
    }

    @Override
    public synchronized Vlr pgVlr(Object comPadrao) throws Exception {
        if (vazio()) {
            return Vlr.novo(comPadrao);
        } else {
            return controlador.pgVlr();
        }
    }

    @Override
    public synchronized EdtFor mdVlr(Object para) throws Exception {
        controlador.mdVlr(new VFor(para));
        return this;
    }

    @Override
    public EdtFor botaAoModificar(AbstractAction aAcao) {

        return this;
    }

    @Override
    public EdtFor auxiliar() throws Exception {

        return this;
    }

    @Override
    public EdtFor aleatorio() {

        return this;
    }

    @Override
    public Boolean vazio() {
        return controlador.pgVlr().vazio();
    }

    @Override
    public EdtFor mEditavel(Boolean para) {
        editavel = para;
        return this;
    }

    @Override
    public Boolean editavel() {
        return editavel;
    }

    private class ActGenerico extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                controlador.auxGenerico();
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActTexto extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                String texto = UtlCrs.pergunta("Texto:", controlador.pgVlr().pTexto());
                if (texto != null) {
                    Fonte fonte = controlador.pgVlr().pTextoFonte();
                    if (fonte == null) {
                        fonte = new Fonte();
                    }
                    new QuestaoFonte(fonte.pNativa()) {
                        @Override
                        public Boolean aoEscolher(Font comEscolhida) {
                            controlador.pgVlr().mTextoFonte(new Fonte(comEscolhida));
                            controlador.pgVlr().mTexto(texto);
                            controlador.pgVlr().mTipo(VFor.Tp.Texto);
                            controlador.reprocessar();
                            return true;
                        }
                    }.mostra();
                }
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActImagem extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                BufferedImage imagem = UtlIma.pergunta();
                if (imagem != null) {
                    controlador.pgVlr().mImagem(imagem);
                    controlador.pgVlr().mTipo(VFor.Tp.Imagem);
                    controlador.reprocessar();
                }
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActQuadrado extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                controlador.pgVlr().mTipo(VFor.Tp.Quadrado);
                controlador.reprocessar();
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActRetangulo extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                controlador.pgVlr().mTipo(VFor.Tp.Retangulo);
                controlador.reprocessar();
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActCirculo extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                controlador.pgVlr().mTipo(VFor.Tp.Circulo);
                controlador.reprocessar();
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActElipse extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                controlador.pgVlr().mTipo(VFor.Tp.Elipse);
                controlador.reprocessar();
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActSeta extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                controlador.pgVlr().mTipo(VFor.Tp.Seta);
                controlador.reprocessar();
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActUniao extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                controlador.pgVlr().mTipo(VFor.Tp.Uniao);
                controlador.reprocessar();
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActLinha extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                controlador.pgVlr().mTipo(VFor.Tp.Linha);
                controlador.reprocessar();
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActPonto extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                controlador.pgVlr().mTipo(VFor.Tp.Ponto);
                controlador.reprocessar();
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActVazia extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                controlador.pgVlr().mTipo(VFor.Tp.Vazia);
                controlador.reprocessar();
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActAngulo extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                controlador.auxAngulo();
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActContorno extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                try {
                    Contorno cnt = controlador.pgVlr().pContorno();
                    if (cnt == null) {
                        cnt = new Contorno();
                    }
                    new ContornoVtf(cnt) {
                        @Override
                        public void aoConfirmar(Object comOrigem) throws Exception {
                            controlador.pgVlr().mContorno(pgVal());
                            controlador.reprocessar();
                            fecha();
                        }
                    }.mostra();
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActPreenchimento extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                try {
                    Fundo pre = controlador.pgVlr().pPreenchimento();
                    if (pre == null) {
                        pre = new Fundo();
                    }
                    new FundoVtf(pre) {
                        @Override
                        public void aoConfirmar(Object comOrigem) throws Exception {
                            controlador.pgVlr().mPreenchimento(pFundo());
                            controlador.reprocessar();
                            fecha();
                        }
                    }.mostra();
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActInscrita extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                controlador.pgVlr().trocaFormatoInscrito();
                controlador.reprocessar();
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

}
