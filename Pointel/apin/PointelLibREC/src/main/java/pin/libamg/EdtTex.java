package pin.libamg;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import pin.ldttex.LdtTex;
import pin.libutl.Utl;
import pin.libutl.UtlCrs;
import pin.libvlr.VTex;
import pin.libvlr.Vlr;

public class EdtTex extends Edt {

    private final LdtTex controlador;
    private AbstractAction aoNovo;
    private AbstractAction aoAbrir;
    private AbstractAction aoCarregar;
    private AbstractAction aoBaixar;
    private AbstractAction aoSalvar;

    public EdtTex() {
        super();
        controlador = new LdtTex();
        add(controlador, BorderLayout.CENTER);
        inicia();
    }

    @Override
    public void inicia() {
        aoNovo = null;
        aoAbrir = null;
        aoCarregar = null;
        aoBaixar = null;
        aoSalvar = null;
        pPopMenu().bota("Novo", 'N', new ActNovo());
        pPopMenu().bota("Abrir", 'A', new ActAbrir());
        pPopMenu().bota("Carregar", 'C', new ActCarregar());
        pPopMenu().bota("Baixar", 'B', new ActBaixar());
        pPopMenu().bota("Salvar", 'S', new ActSalvar());
        pPopMenu().botaSeparador();
        pPopMenu().bota("Editar", 'D');
        pPopMenu().bota("Editar", "Localizar", 'L', new ActEdiLocalizar());
        pPopMenu().bota("Editar", "Comparar", 'C', new ActEdiComparar());
        pPopMenu().bota("Editar", "Linhas", 'I');
        pPopMenu().bota("Editar.Linhas", "Excluir", 'E', new ActProLinExcluir());
        pPopMenu().bota("Visualizar", 'V');
        pPopMenu().bota("Visualizar", "Quebrar Linha", 'Q', new ActVisQuebrarLinha());
        if (cfgs() != null) {
            if (cfgs().pegaLog("PointelLibAMG - EdtTex - Visualizar - Quebrar Linha", false)) {
                controlador.botaQuebraLinha();
            } else {
                controlador.tiraQuebraLinha();
            }
        }
        mDimensao(360, 120);
        super.inicia();
    }

    @Override
    public void atualizaOpcoes() {
    }

    @Override
    public void atualizaParams() {
    }

    public LdtTex controlador() {
        return controlador;
    }

    @Override
    public JTextArea controle() {
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
            return new VTex(controlador.controle().getText());
        }
    }

    @Override
    public synchronized EdtTex mdVlr(Object para) throws Exception {
        controlador.controle().setText(UtlCrs.pega(para));
        return this;
    }

    @Override
    public EdtTex botaAoModificar(AbstractAction aAcao) {
        controlador.botaAoModificar(aAcao);
        return this;
    }

    @Override
    public EdtTex auxiliar() throws Exception {

        return this;
    }

    @Override
    public EdtTex aleatorio() {

        return this;
    }

    @Override
    public Boolean vazio() {
        return controlador.controle().getText().isEmpty();
    }

    @Override
    public EdtTex mEditavel(Boolean para) {
        controlador.controle().setEditable(para);
        if (para) {
            controlador.controle().setBackground(UIManager.getColor("TextField.background"));
        } else {
            controlador.controle().setBackground(UIManager.getColor("TextField.disabledBackground"));
        }
        return this;
    }

    @Override
    public Boolean editavel() {
        return controlador.controle().isEditable();
    }

    @Override
    public Edt botaDimensionavel() {
        super.botaDimensionavel();
        return this;
    }

    public AbstractAction aoNovo() {
        return aoNovo;
    }

    public EdtTex mudaAoNovo(AbstractAction aoNovo) {
        this.aoNovo = aoNovo;
        return this;
    }

    public AbstractAction aoAbrir() {
        return aoAbrir;
    }

    public EdtTex mudaAoAbrir(AbstractAction aoAbrir) {
        this.aoAbrir = aoAbrir;
        return this;
    }

    public AbstractAction aoCarregar() {
        return aoCarregar;
    }

    public EdtTex mudaAoCarregar(AbstractAction aoCarregar) {
        this.aoCarregar = aoCarregar;
        return this;
    }

    public AbstractAction aoBaixar() {
        return aoBaixar;
    }

    public EdtTex mudaAoBaixar(AbstractAction aoBaixar) {
        this.aoBaixar = aoBaixar;
        return this;
    }

    public AbstractAction aoSalvar() {
        return aoSalvar;
    }

    public EdtTex mudaAoSalvar(AbstractAction aoSalvar) {
        this.aoSalvar = aoSalvar;
        return this;
    }

    @Override
    public void fechaEdt() {
        controlador.fecha();
        super.fechaEdt();
    }

    public class ActNovo extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (aoNovo != null) {
                aoNovo.actionPerformed(e);
            } else if (Utl.continua()) {
                controlador.novo();
            }
        }

    }

    public class ActAbrir extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (aoAbrir != null) {
                aoAbrir.actionPerformed(e);
            } else {
                try {
                    controlador.abre();
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        }

    }

    public class ActCarregar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (aoCarregar != null) {
                aoCarregar.actionPerformed(e);
            } else {
                controlador.carrega();
            }
        }

    }

    public class ActBaixar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (aoBaixar != null) {
                aoBaixar.actionPerformed(e);
            } else {
                try {
                    controlador.baixa();
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        }

    }

    public class ActSalvar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (aoSalvar != null) {
                aoSalvar.actionPerformed(null);
            } else {
                try {
                    controlador.salva();
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        }

    }

    public class ActEdiLocalizar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            controlador.localiza();
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

    public class ActProLinExcluir extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            controlador.linhasExcluir();
        }

    }

    public class ActVisQuebrarLinha extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (!controlador.estaQuebraLinha()) {
                    controlador.botaQuebraLinha();
                } else {
                    controlador.tiraQuebraLinha();
                }
                cfgs().botaLog("PointelLibAMG - EdtTex - Visualizar - Quebrar Linha", controlador.estaQuebraLinha());
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }

    }

}
