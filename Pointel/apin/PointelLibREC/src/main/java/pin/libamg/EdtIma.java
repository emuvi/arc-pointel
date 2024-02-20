package pin.libamg;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JScrollPane;
import pin.ldtima.ImaDoc;
import pin.ldtima.LdtIma;
import pin.libutl.Utl;
import pin.libutl.UtlIma;
import pin.libvlr.VIma;
import pin.libvlr.Vlr;

public class EdtIma extends Edt {

    private LdtIma controlador;

    public EdtIma() {
        super();
        controlador = new LdtIma();
        add(controlador, BorderLayout.CENTER);
        inicia();
    }

    @Override
    public void inicia() {
        pPopMenu().bota("Nova", 'N', new ActNovo());
        pPopMenu().bota("Abrir", 'A', new ActAbrir());
        pPopMenu().bota("Salvar", 'S', new ActSalvar());
        pPopMenu().bota("Editar");
        pPopMenu().bota("Editar", "Particionar", new ActEdiParticionar());
        pPopMenu().bota("Editar", "Comparar", new ActEdiComparar());
        pPopMenu().bota("Editar", "Selecionar");
        pPopMenu().bota("Editar.Selecionar", "Tudo", new ActEdiSelTudo());
        pPopMenu().bota("Editar.Selecionar", "Retângulo", new ActEdiSelRetangulo());
        pPopMenu().bota("Ver");
        pPopMenu().bota("Ver", "Zoom");
        pPopMenu().bota("Ver.Zoom", "Original", new ActVerZooOriginal());
        pPopMenu().bota("Ver.Zoom", "Janela", new ActVerZooJanela());
        if (cfgs() != null) {
            controlador.controle().botaZoom(cfgs().pegaCrs("PointelLibAMG - EdtIma - Zoom", "Original"));
        } else {
            controlador.controle().botaZoom("Original");
        }
        mDimensao(360, 270);
        super.inicia();
        pPopMenu().muda("Valor", "Recorta", new ActRecorta());
        pPopMenu().muda("Valor", "Copia", new ActCopia());
        pPopMenu().muda("Valor", "Cola", new ActCola());
    }

    @Override
    public void atualizaOpcoes() {
    }

    @Override
    public void atualizaParams() {
    }

    @Override
    public ImaDoc controle() {
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
            return new VIma(controlador.controle().pega());
        }
    }

    @Override
    public synchronized EdtIma mdVlr(Object para) throws Exception {
        controlador.controle().muda(UtlIma.pega(para));
        return this;
    }

    @Override
    public EdtIma botaAoModificar(AbstractAction aAcao) {

        return this;
    }

    @Override
    public EdtIma auxiliar() throws Exception {

        return this;
    }

    @Override
    public EdtIma aleatorio() {

        return this;
    }

    @Override
    public Boolean vazio() {
        return controlador.controle().vazio();
    }

    @Override
    public EdtIma mEditavel(Boolean para) {
        controlador.mudaEditavel(para);
        return this;
    }

    @Override
    public Boolean editavel() {
        return controlador.estaEditavel();
    }

    @Override
    public void fechaEdt() {
        super.fechaEdt();
        controle().limpa();
    }

    public class ActNovo extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            controlador.novo();
        }
    }

    public class ActAbrir extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            controlador.abre();
        }
    }

    public class ActSalvar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            controlador.salva();
        }
    }

    public class ActEdiParticionar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                controlador.particiona();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }

    }

    public class ActEdiComparar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                controlador.compara();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }

    }

    public class ActEdiSelTudo extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                controlador.selecionaTudo();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }

    }

    public class ActEdiSelRetangulo extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            controlador.selecionaRetangulo();
        }

    }

    public class ActVerZooOriginal extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                controlador.controle().botaZoom("Original");
                if (cfgs() != null) {
                    cfgs().botaCrs("PointelLibAMG - EdtIma - Zoom", "Original");
                }
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }

    }

    public class ActVerZooJanela extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                controlador.controle().botaZoom("Janela");
                if (cfgs() != null) {
                    cfgs().botaCrs("PointelLibAMG - EdtIma - Zoom", "Janela");
                }
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }

    }

    private class ActRecorta extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                controlador.recorta();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    private class ActCopia extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                controlador.copia();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    private class ActCola extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                controlador.cola();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }
}
