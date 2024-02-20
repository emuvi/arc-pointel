package pin.modamk;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import pin.libamg.EdtPla;
import pin.libbas.Globais;
import pin.libjan.Janelas;
import pin.libjan.PopMenu;
import pin.libitr.Notificar;
import pin.libutl.Utl;
import pin.libutl.UtlBin;
import pin.libbas.Conjunto;
import pin.libvlr.Vlrs;
import pin.modinf.Carregador;
import pin.modinf.Conexao;
import pin.libbas.Analisado;

public class Monitor {

    private Conexao conexao;
    private String titulo;
    private String consulta;
    private Vlrs parametros;
    private String[] titulos;
    private AbstractAction acao;
    private AbstractAction acaoPop;
    private PopMenu menuPop;
    private Boolean deveAutoAtualizar;
    private JFrame janela;
    private EdtPla edtMonitor;
    private Carregador conCarregador;
    private ActNotificar actNotificar;
    private Boolean notificar;
    private ThdAutoAtualizar autoAtualizar;
    private volatile Boolean fechar;

    public Monitor() {
        this("Monitor");
    }

    public Monitor(String comTitulo) {
        conexao = ((Conexao) Globais.pega("mainConc"));
        titulo = comTitulo;
        consulta = null;
        parametros = null;
        acao = null;
        acaoPop = null;
        menuPop = null;
        deveAutoAtualizar = true;
        actNotificar = new ActNotificar();
        autoAtualizar = null;
        notificar = true;
        consulta = null;
        parametros = null;
        janela = null;
        edtMonitor = null;
        conCarregador = null;
        fechar = false;
    }

    public Monitor botaTitulo(String oTitulo) {
        titulo = oTitulo;
        if (janela != null) {
            janela.setTitle(titulo);
        }
        return this;
    }

    public Monitor botaConsulta(String aConsulta) {
        consulta = aConsulta;
        parametros = null;
        return this;
    }

    public Monitor botaConsulta(String aConsulta, Vlrs comParametros) {
        consulta = aConsulta;
        parametros = comParametros;
        return this;
    }

    public Monitor botaTitulos(String[] aTitulos) {
        titulos = aTitulos;
        if (edtMonitor != null) {
            edtMonitor.controlador().botaColunas(aTitulos);
        }
        return this;
    }

    public Monitor botaAcao(AbstractAction aAcao) {
        acao = aAcao;
        return this;
    }

    public Monitor botaAcaoPop(AbstractAction aAcaoPop) {
        acaoPop = aAcaoPop;
        return this;
    }

    public Monitor botaMenuPop(PopMenu oMenu) {
        menuPop = oMenu;
        return this;
    }

    public Monitor mudaDeveAutoAtualizar(Boolean para) {
        deveAutoAtualizar = para;
        return this;
    }

    public Boolean pegaDeveAutoAtualizar() {
        return deveAutoAtualizar;
    }

    public Conexao conexao() {
        return conexao;
    }

    public Monitor botaConexao(Conexao aConexao) {
        conexao = aConexao;
        return this;
    }

    public Monitor mostra() {
        return mostra(null);
    }

    public Monitor mostra(String comTitulo) {
        if (comTitulo != null) {
            titulo = comTitulo;
        }
        janela = new JFrame(titulo);
        janela.setSize(300, 500);
        janela.setLayout(new BorderLayout(4, 4));
        edtMonitor = new EdtPla()
                .mEditavel(false);
        edtMonitor.controlador()
                .botaColunas(titulos)
                .tiraMostraNumeroLinhas();
        janela.add(edtMonitor, BorderLayout.CENTER);
        MouseAdapter mosHan = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    if (menuPop != null) {
                        menuPop.mostra(e.getComponent(), e.getX(), e.getY());
                    }
                    if (acaoPop != null) {
                        if (menuPop != null) {
                            new Thread("Monitor AçãoPop") {
                                @Override
                                public void run() {
                                    acaoPop.actionPerformed(new ActionEvent(this, 1, "Ação Pop"));
                                }
                            }.start();
                        } else {
                            acaoPop.actionPerformed(new ActionEvent(this, 1, "Ação Pop"));
                        }
                    }
                } else if ((e.getClickCount() >= 2) && (e.getButton() == MouseEvent.BUTTON1)) {
                    if (acao != null) {
                        acao.actionPerformed(new ActionEvent(this, 0, "Ação"));
                    }
                }
            }
        };
        edtMonitor.controle().addMouseListener(mosHan);
        edtMonitor.rolagem().addMouseListener(mosHan);
        edtMonitor.controle().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
                    if (acao != null) {
                        acao.actionPerformed(null);
                    }
                }
            }
        });
        conCarregador = new Carregador(conexao, consulta, parametros)
                .botaManterAberto();
        conCarregador.mudaEditor(edtMonitor);
        conCarregador.botaAoModificar(actNotificar);
        conCarregador.inicia();
        if (deveAutoAtualizar) {
            botaAutoAtualizar(10);
        }
        janela.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                conCarregador.fecha();
                fechar = true;
            }
        });
        Janelas.inicia(janela, Monitor.this);
        Janelas.mostra(janela);
        return this;
    }

    public JFrame janela() {
        return janela;
    }

    public EdtPla editor() {
        return edtMonitor;
    }

    public Monitor habilita() {
        edtMonitor.controle().setEnabled(true);
        return this;
    }

    public Monitor deshabilita() {
        edtMonitor.controle().setEnabled(false);
        return this;
    }

    public Monitor botaAutoAtualizar(Integer aCadaSegundos) {
        fechar = false;
        autoAtualizar = new ThdAutoAtualizar(aCadaSegundos);
        autoAtualizar.start();
        return this;
    }

    public Monitor paraAutoAtualizacao() {
        fechar = true;
        return this;
    }

    public AbstractAction aoModificar() {
        return conCarregador.aoModificar();
    }

    public Monitor botaAoModificar(AbstractAction aoModificar) {
        conCarregador.botaAoModificar(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actNotificar.actionPerformed(e);
                aoModificar.actionPerformed(e);
            }
        });
        return this;
    }

    public Analisado<Conjunto, Boolean> aoCarregar() {
        return conCarregador.aoCarregar();
    }

    public Monitor botaAoCarregar(Analisado<Conjunto, Boolean> aoCarregar) {
        conCarregador.mudaAoCarregar(aoCarregar);
        return this;
    }

    public Monitor atualizar() throws Exception {
        conCarregador.mudaSelecao(consulta, parametros);
        conCarregador.carrega();
        return this;
    }

    private class ActNotificar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (notificar) {
                Notificar.ver("Há Modificações em " + janela.getTitle());
            }
        }
    }

    public class ThdAutoAtualizar extends Thread {

        private Integer cadaSegundos;

        public ThdAutoAtualizar(Integer aCadaSegundos) {
            super(titulo + " AutoAtualizar");
            cadaSegundos = aCadaSegundos;
        }

        @Override
        public void run() {
            while (!fechar) {
                if (!fechar && cadaSegundos > 0) {
                    try {
                        atualizar();
                    } catch (Exception ex) {
                        Utl.registra(ex);
                    }
                    UtlBin.esperaSegundos(cadaSegundos);
                }
            }
        }

    }
}
