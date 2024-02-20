package pin.libamg;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.JScrollPane;
import pin.ldtfrm.LdtFrmDoc;
import pin.libutl.Utl;
import pin.libutl.UtlArq;
import pin.libutl.UtlTex;
import pin.libvlr.VApr;
import pin.libvlr.VFrm;
import pin.libvlr.Vlr;

public class EdtApr extends Edt {

    private final EdtFrm controlador;
    private volatile File selecionado;
    private volatile VApr valor;
    private volatile Integer pagina;

    public EdtApr() {
        super();
        controlador = new EdtFrm(true);
        add(controlador, BorderLayout.CENTER);
        inicia();
    }

    @Override
    public void inicia() {
        valor = new VApr();
        pagina = 0;
        pPopMenu().bota("Novo", 'N', new ActNovo());
        pPopMenu().bota("Abrir", 'A', new ActAbrir());
        pPopMenu().bota("Salvar", 'S', new ActSalvar());
        pPopMenu().botaSeparador();
        pPopMenu().bota("Inserir", 'I', new ActInserir());
        pPopMenu().bota("Remover", 'R', new ActRemover());
        pPopMenu().bota("Página", 'G');
        pPopMenu().bota("Página", controlador.pPopMenu());
        pPopMenu().bota("Visualizar", 'V');
        pPopMenu().bota("Visualizar", "Primeira", 'P', new ActPrimeira());
        pPopMenu().bota("Visualizar", "Anterior", 'A', new ActAnterior());
        pPopMenu().bota("Visualizar", "Próxima", 'R', new ActProxima());
        pPopMenu().bota("Visualizar", "Última", 'U', new ActUltima());
        super.inicia();
    }

    @Override
    public void atualizaOpcoes() {

    }

    @Override
    public void atualizaParams() {

    }

    @Override
    public LdtFrmDoc controle() {
        return controlador.controle();
    }

    @Override
    public JScrollPane rolagem() {
        return controlador.rolagem();
    }

    @Override
    public synchronized Vlr pgVlr(Object comPadrao) throws Exception {
        salvaAtual();
        if (vazio()) {
            return Vlr.novo(comPadrao);
        } else {
            return valor;
        }
    }

    @Override
    public synchronized EdtApr mdVlr(Object para) throws Exception {
        valor = new VApr(para);
        pagina = 0;
        carregaAtual();
        return this;
    }

    @Override
    public EdtApr botaAoModificar(AbstractAction aAcao) {

        return this;
    }

    @Override
    public EdtApr auxiliar() throws Exception {

        return this;
    }

    @Override
    public EdtApr aleatorio() {

        return this;
    }

    @Override
    public Boolean vazio() {
        return valor.vazio();
    }

    @Override
    public EdtApr mEditavel(Boolean para) {
        controlador.mEditavel(para);
        return this;
    }

    @Override
    public Boolean editavel() {
        return controlador.editavel();
    }

    public EdtApr novo() {
        valor.novo();
        controlador.novo();
        pagina = 0;
        return this;
    }

    public EdtApr abre() throws Exception {
        File arq = UtlArq.abreArq(selecionado, "Pointel Apresentação (*.pap)", "pap");
        if (arq != null) {
            abre(arq);
        }
        return this;
    }

    public EdtApr abre(File oArquivo) throws Exception {
        mdVlr(UtlTex.abre(oArquivo));
        selecionado = oArquivo;
        return this;
    }

    public EdtApr salva() throws Exception {
        File arq = UtlArq.salvaArq(selecionado, "Pointel Apresentação (*.pap)", "pap");
        if (arq != null) {
            salva(arq);
        }
        return this;
    }

    public EdtApr salva(File noArquivo) throws Exception {
        UtlTex.salva(pgVlr().descreve(), noArquivo);
        selecionado = noArquivo;
        return this;
    }

    public EdtApr salvaAtual() throws Exception {
        if (controlador.editavel()) {
            Vlr vl = controlador.pgVlr();
            if (vl instanceof VFrm) {
                valor.mPagina(pagina, (VFrm) vl);
            } else {
                valor.mPagina(pagina, new VFrm());
            }
        }
        return this;
    }

    public EdtApr carregaAtual() throws Exception {
        controlador.mdVlr(valor.pPagina(pagina));
        return this;
    }

    public EdtApr primeira() throws Exception {
        salvaAtual();
        pagina = 0;
        carregaAtual();
        return this;
    }

    public EdtApr anterior() throws Exception {
        salvaAtual();
        if (pagina > 0) {
            pagina--;
        }
        carregaAtual();
        return this;
    }

    public EdtApr proxima() throws Exception {
        salvaAtual();
        if (pagina < valor.tamanho() - 1) {
            pagina++;
        }
        carregaAtual();
        return this;
    }

    public EdtApr ultima() throws Exception {
        salvaAtual();
        pagina = valor.tamanho() - 1;
        if (pagina < 0) {
            pagina = 0;
        }
        carregaAtual();
        return this;
    }

    public EdtApr insere() throws Exception {
        salvaAtual();
        pagina++;
        valor.botaPagina(pagina, new VFrm());
        carregaAtual();
        return this;
    }

    public EdtApr remove() throws Exception {
        valor.tiraPagina(pagina);
        carregaAtual();
        return this;
    }

    private class ActNovo extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (Utl.continua()) {
                novo();
            }
        }
    }

    private class ActAbrir extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                abre();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    private class ActSalvar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                salva();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    private class ActPrimeira extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                primeira();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    private class ActAnterior extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                anterior();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    private class ActProxima extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                proxima();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    private class ActUltima extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ultima();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    private class ActInserir extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                insere();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    private class ActRemover extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (Utl.continua()) {
                    remove();
                }
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

}
