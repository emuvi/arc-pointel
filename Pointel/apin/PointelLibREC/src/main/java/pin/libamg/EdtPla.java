package pin.libamg;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import pin.libamk.Botao;
import pin.libamk.Campos;
import pin.libamk.Cmp;
import pin.libamk.Intf;
import pin.libbas.Dados;
import pin.libjan.PaiBarras;
import pin.libjan.TrataIntf;
import pin.libutl.Utl;
import pin.libvlr.Vlr;
import pin.ldtpla.LdtPla;

public class EdtPla extends Edt {

    private LdtPla controlador;
    private PaiBarras barras;
    private JToolBar barraStatus;

    public EdtPla() {
        super();
        controlador = new LdtPla();
        barras = new PaiBarras();
        add(barras, BorderLayout.CENTER);
        inicia();
    }

    @Override
    public void inicia() {
        barras.bota(controlador);
        barraStatus = controlador.barraStatus();
        barraStatus.setVisible(false);
        barras.bota(barraStatus);
        pPopMenu().bota("Linha");
        pPopMenu().bota("Linha", "Altura", new ActLinAltura()).botaAtalho(controle(), "");
        pPopMenu().bota("Mostrar");
        pPopMenu().bota("Mostrar", "Numeração", new ActMosNumeracao()).botaAtalho(controle(), "");
        pPopMenu().bota("Mostrar", "Barra Status", new ActVerBarraStatus());
        mDimensao(360, 270);
        super.inicia();
    }

    @Override
    public void atualizaOpcoes() {
    }

    @Override
    public void atualizaParams() {
    }

    public LdtPla controlador() {
        return controlador;
    }

    @Override
    public JTable controle() {
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
            return controlador.pegaVlr();
        }
    }

    @Override
    public synchronized EdtPla mdVlr(Object para) throws Exception {
        controlador.mudaVlr(para);
        return this;
    }

    @Override
    public void limpa() {
        controlador.limpa();
    }

    @Override
    public EdtPla botaAoModificar(AbstractAction aAcao) {

        return this;
    }

    @Override
    public EdtPla auxiliar() throws Exception {

        return this;
    }

    @Override
    public EdtPla aleatorio() {

        return this;
    }

    @Override
    public EdtPla mEditavel(Boolean para) {
        controlador.mudaTodosEditaveis(para);
        return this;
    }

    @Override
    public Boolean vazio() {
        return controlador.vazio();
    }

    @Override
    public Boolean editavel() {
        return controlador.estaEditavel();
    }

    @Override
    public void abreEdt() {
        super.abreEdt();
        abreColunasLarguras();
    }

    public EdtPla abreColunasLarguras() {
        String titJan = TrataIntf.pegaTituloDaJanela(this);
        if (titJan != null && cfgs() != null) {
            String nome = pNome();
            if (nome == null) {
                nome = "Padrão";
            }
            if (nome.isEmpty()) {
                nome = "Padrão";
            }
            controlador.controle().putClientProperty("terminateEditOnFocusLost", true);
            String raiz = "Janela " + titJan + " - Tabela " + nome + " - Coluna ";
            for (int ic = 0; ic < controlador.controle().getColumnCount(); ic++) {
                String icn = String.valueOf(ic);
                if (controlador.colunasNomes() != null) {
                    if (ic < controlador.colunasNomes().length) {
                        icn = controlador.colunasNomes()[ic];
                    }
                }
                int largura = cfgs().pegaInt(raiz + icn, 120);
                controlador.colunador().getColumn(ic).setPreferredWidth(largura);
            }
        }
        return this;
    }

    @Override
    public void fechaEdt() {
        super.fechaEdt();
        salvaColunasLarguras();
    }

    public EdtPla salvaColunasLarguras() {
        String titJan = TrataIntf.pegaTituloDaJanela(this);
        if (titJan != null && cfgs() != null) {
            String nome = pNome();
            if (nome == null) {
                nome = "Padrão";
            }
            if (nome.isEmpty()) {
                nome = "Padrão";
            }
            String raiz = "Janela " + titJan + " - Tabela " + nome + " - Coluna ";
            for (int ic = 0; ic < controlador.controle().getColumnCount(); ic++) {
                String icn = String.valueOf(ic);
                if (controlador.colunasNomes() != null) {
                    if (ic < controlador.colunasNomes().length) {
                        icn = controlador.colunasNomes()[ic];
                    }
                }
                int largura = controlador.colunador().getColumn(ic).getPreferredWidth();
                cfgs().botaInt(raiz + icn, largura);
            }
        }
        return this;
    }

    private class IntfLinAltura extends Intf {

        private int[] linhas;

        public IntfLinAltura(int[] dasLinhas) {
            super(new Campos(new Cmp(1, 1, "altura", "Altura", Dados.Tp.Int).mVlrInicial(controle().getRowHeight(dasLinhas[0]))));
            linhas = dasLinhas;
        }

        @Override
        public void preparaIntf() throws Exception {
            botaBotao(new BotConfirmar());
        }

        private class BotConfirmar extends Botao {

            public BotConfirmar() {
                super("Confirmar");
            }

            @Override
            public void aoExecutar(Object comOrigem) throws Exception {
                for (int lin : linhas) {
                    controle().setRowHeight(lin, cmps().pgVlr("altura").pgInt());
                }
                fecha();
            }
        }
    }

    private class ActLinAltura extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            final int[] lins = controle().getSelectedRows();
            if (lins.length > 0) {
                try {
                    new IntfLinAltura(lins).mostra("Altura da Linha");
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        }
    }

    private class ActMosNumeracao extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (controlador.ehMostraNumeroLinhas()) {
                controlador.botaMostraNumeroLinhas();
            } else {
                controlador.tiraMostraNumeroLinhas();
            }
        }

    }

    public class ActVerBarraStatus extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                barras.trocaVisivel(barraStatus);
                if (cfgs() != null) {
                    cfgs().botaLog("PointelLibAMG - EdtIma - Status", barraStatus.isVisible());
                }
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }

    }
}
