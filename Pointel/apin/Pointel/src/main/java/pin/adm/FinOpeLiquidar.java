package pin.adm;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.DefaultComboBoxModel;
import pin.libbas.Conferencia;
import pin.libbas.Configs;
import pin.libbas.Conjunto;
import pin.libbas.Globais;
import pin.libjan.Janelas;
import pin.libutl.Utl;
import pin.libutl.UtlCrs;
import pin.libutl.UtlDat;
import pin.libutl.UtlNumLon;
import pin.libvlr.Vlrs;
import pin.modinf.Banco;
import pin.modinf.CodigosMod;
import pin.modinf.Conexao;

public class FinOpeLiquidar extends javax.swing.JFrame {

    public Configs cfgs;
    public Conexao conexao;
    public DefaultComboBoxModel dcmLancamentos;

    public String perNF;
    public String tipo;
    public String situacao;

    public String padHistLiqu = "";
    public String padContLiqu = "";
    public String padPortLiqu = "";

    public String padHistDebi = "";
    public String padContDebi = "";
    public String padPortDebi = "";

    public String padHistDesc = "";
    public String padContDesc = "";
    public String padPortDesc = "";

    public String padHistJuro = "";
    public String padContJuro = "";
    public String padPortJuro = "";

    public String padHistMult = "";
    public String padContMult = "";
    public String padPortMult = "";

    public FinOpeLiquidar() throws Exception {
        initComponents();
        cfgs = (Configs) Globais.pega("mainConf");
        conexao = (Conexao) Globais.pega("mainConc");
        dcmLancamentos = new DefaultComboBoxModel();
        jcbLancamentoLiq.setModel(dcmLancamentos);
        jcbLancamentoLiq.getEditor().getEditorComponent().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                jcbLancamentoLiqFocusLost(e);
            }
        });
        Conjunto res = conexao.conCfgs().pegaIniciando("PointelAdmin - Opções - Financeiro");
        while (res.proximo()) {
            String chave = res.pgVlr("chave").pgCrs("");
            String valor = res.pgVlr("valor").pgCrs("");
            switch (chave) {
                case "PointelAdmin - Opções - Financeiro - Histórico das Liquidações":
                    padHistLiqu = valor;
                    break;
                case "PointelAdmin - Opções - Financeiro - Conta das Liquidações":
                    padContLiqu = valor;
                    break;
                case "PointelAdmin - Opções - Financeiro - Portador das Liquidações":
                    padPortLiqu = valor;
                    break;
                case "PointelAdmin - Opções - Financeiro - Histórico das Débitos":
                    padHistDebi = valor;
                    break;
                case "PointelAdmin - Opções - Financeiro - Conta das Débitos":
                    padContDebi = valor;
                    break;
                case "PointelAdmin - Opções - Financeiro - Portador das Débitos":
                    padPortDebi = valor;
                    break;
                case "PointelAdmin - Opções - Financeiro - Histórico dos Descontos":
                    padHistDesc = valor;
                    break;
                case "PointelAdmin - Opções - Financeiro - Conta dos Descontos":
                    padContDesc = valor;
                    break;
                case "PointelAdmin - Opções - Financeiro - Portador dos Descontos":
                    padPortDesc = valor;
                    break;
                case "PointelAdmin - Opções - Financeiro - Histórico dos Juros":
                    padHistJuro = valor;
                    break;
                case "PointelAdmin - Opções - Financeiro - Conta dos Juros":
                    padContJuro = valor;
                    break;
                case "PointelAdmin - Opções - Financeiro - Portador dos Juros":
                    padPortJuro = valor;
                    break;
                case "PointelAdmin - Opções - Financeiro - Histórico das Multas":
                    padHistMult = valor;
                    break;
                case "PointelAdmin - Opções - Financeiro - Conta das Multas":
                    padContMult = valor;
                    break;
                case "PointelAdmin - Opções - Financeiro - Portador das Multas":
                    padPortMult = valor;
                    break;

            }
        }
        CnfTotal cnfTotal = new CnfTotal();
        edtLiquidar.botaConferencia(cnfTotal);
        edtDesconto.botaConferencia(cnfTotal);
        edtJuros.botaConferencia(cnfTotal);
        edtMulta.botaConferencia(cnfTotal);
        edtLiquidar.controle().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent fe) {
                jtbOpcoes.setSelectedIndex(0);
            }
        });
        edtDesconto.controle().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent fe) {
                jtbOpcoes.setSelectedIndex(1);
            }
        });
        edtJuros.controle().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent fe) {
                jtbOpcoes.setSelectedIndex(2);
            }
        });
        edtMulta.controle().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent fe) {
                jtbOpcoes.setSelectedIndex(3);
            }
        });
        edtDataLiquidacao.mdVlr(new java.util.Date());
        edtLiqTotal.mEditavel(false);
        Janelas.inicia(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlbSerieLiq = new javax.swing.JLabel();
        jtfSerieLiq = new javax.swing.JTextField();
        jlbFaturaLiq = new javax.swing.JLabel();
        jtfFaturaLiq = new javax.swing.JTextField();
        jtbOpcoes = new javax.swing.JTabbedPane();
        jpnLiquidacao = new javax.swing.JPanel();
        jlbLiqHistorico = new javax.swing.JLabel();
        jtfLiqHistorico = new javax.swing.JTextField();
        jlbLiqHistoricoNome = new javax.swing.JLabel();
        jtfLiqHistoricoNome = new javax.swing.JTextField();
        jlbLiqConta = new javax.swing.JLabel();
        jtfLiqConta = new javax.swing.JTextField();
        jlbLiqContaNome = new javax.swing.JLabel();
        jtfLiqContaNome = new javax.swing.JTextField();
        jlbLiqTipoPgto = new javax.swing.JLabel();
        jtfLiqTipoPgto = new javax.swing.JTextField();
        jlbLiqTipoPgtoNome = new javax.swing.JLabel();
        jtfLiqTipoPgtoNome = new javax.swing.JTextField();
        jlbLiqPortador = new javax.swing.JLabel();
        jtfLiqPortador = new javax.swing.JTextField();
        jlbLiqPortadorNome = new javax.swing.JLabel();
        jtfLiqPortadorNome = new javax.swing.JTextField();
        jlbLiqComplemento = new javax.swing.JLabel();
        jtfLiqComplemento = new javax.swing.JTextField();
        jpnDesconto = new javax.swing.JPanel();
        jlbDesTipoPgto = new javax.swing.JLabel();
        jtfDesContaNome = new javax.swing.JTextField();
        jlbDesContaNome = new javax.swing.JLabel();
        jtfDesComplemento = new javax.swing.JTextField();
        jtfDesConta = new javax.swing.JTextField();
        jlbDesComplemento = new javax.swing.JLabel();
        jlbDesConta = new javax.swing.JLabel();
        jtfDesPortadorNome = new javax.swing.JTextField();
        jlbDesHistoricoNome = new javax.swing.JLabel();
        jlbDesPortadorNome = new javax.swing.JLabel();
        jtfDesHistoricoNome = new javax.swing.JTextField();
        jtfDesPortador = new javax.swing.JTextField();
        jlbDesPortador = new javax.swing.JLabel();
        jtfDesHistorico = new javax.swing.JTextField();
        jlbDesHistorico = new javax.swing.JLabel();
        jtfDesTipoPgtoNome = new javax.swing.JTextField();
        jlbDesTipoPgtoNome = new javax.swing.JLabel();
        jtfDesTipoPgto = new javax.swing.JTextField();
        jpnJuros = new javax.swing.JPanel();
        jlbJurTipoPgto = new javax.swing.JLabel();
        jtfJurContaNome = new javax.swing.JTextField();
        jlbJurContaNome = new javax.swing.JLabel();
        jtfJurComplemento = new javax.swing.JTextField();
        jtfJurConta = new javax.swing.JTextField();
        jlbJurComplemento = new javax.swing.JLabel();
        jlbJurConta = new javax.swing.JLabel();
        jtfJurPortadorNome = new javax.swing.JTextField();
        jlbJurHistoricoNome = new javax.swing.JLabel();
        jlbJurPortadorNome = new javax.swing.JLabel();
        jtfJurHistoricoNome = new javax.swing.JTextField();
        jtfJurPortador = new javax.swing.JTextField();
        jlbJurPortador = new javax.swing.JLabel();
        jtfJurHistorico = new javax.swing.JTextField();
        jlbJurHistorico = new javax.swing.JLabel();
        jtfJurTipoPgtoNome = new javax.swing.JTextField();
        jlbJurTipoPgtoNome = new javax.swing.JLabel();
        jtfJurTipoPgto = new javax.swing.JTextField();
        jpnMulta = new javax.swing.JPanel();
        jlbMulTipoPgto = new javax.swing.JLabel();
        jtfMulContaNome = new javax.swing.JTextField();
        jlbMulContaNome = new javax.swing.JLabel();
        jtfMulComplemento = new javax.swing.JTextField();
        jtfMulConta = new javax.swing.JTextField();
        jlbMulComplemento = new javax.swing.JLabel();
        jlbMulConta = new javax.swing.JLabel();
        jtfMulPortadorNome = new javax.swing.JTextField();
        jlbMulHistoricoNome = new javax.swing.JLabel();
        jlbMulPortadorNome = new javax.swing.JLabel();
        jtfMulHistoricoNome = new javax.swing.JTextField();
        jtfMulPortador = new javax.swing.JTextField();
        jlbMulPortador = new javax.swing.JLabel();
        jtfMulHistorico = new javax.swing.JTextField();
        jlbMulHistorico = new javax.swing.JLabel();
        jtfMulTipoPgtoNome = new javax.swing.JTextField();
        jlbMulTipoPgtoNome = new javax.swing.JLabel();
        jtfMulTipoPgto = new javax.swing.JTextField();
        jlbDataLiq = new javax.swing.JLabel();
        jlbTotalLiq = new javax.swing.JLabel();
        jbtLiquidar = new javax.swing.JButton();
        jlbLancamentoLiq = new javax.swing.JLabel();
        jcbLancamentoLiq = new javax.swing.JComboBox<>();
        jlbData = new javax.swing.JLabel();
        jftData = new javax.swing.JFormattedTextField();
        jlbEmissao = new javax.swing.JLabel();
        jftEmissao = new javax.swing.JFormattedTextField();
        jlbVencimento = new javax.swing.JLabel();
        jftVencimento = new javax.swing.JFormattedTextField();
        jlbSerie = new javax.swing.JLabel();
        jtfSerie = new javax.swing.JTextField();
        jlbFatura = new javax.swing.JLabel();
        jtfFatura = new javax.swing.JTextField();
        jlbPedVendedor = new javax.swing.JLabel();
        jtfPedVendedor = new javax.swing.JTextField();
        jlbPessoa = new javax.swing.JLabel();
        jtfPessoa = new javax.swing.JTextField();
        jlbPessoaNome = new javax.swing.JLabel();
        jtfPessoaNome = new javax.swing.JTextField();
        jlbCobranca = new javax.swing.JLabel();
        jtfCobranca = new javax.swing.JTextField();
        jlbCobrancaNome = new javax.swing.JLabel();
        jtfCobrancaNome = new javax.swing.JTextField();
        jlbHistorico = new javax.swing.JLabel();
        jtfHistorico = new javax.swing.JTextField();
        jlbHistoricoNome = new javax.swing.JLabel();
        jtfHistoricoNome = new javax.swing.JTextField();
        jlbConta = new javax.swing.JLabel();
        jtfConta = new javax.swing.JTextField();
        jlbContaNome = new javax.swing.JLabel();
        jtfContaNome = new javax.swing.JTextField();
        jlbTipoPgto = new javax.swing.JLabel();
        jtfTipoPgto = new javax.swing.JTextField();
        jlbTipoPgtoNome = new javax.swing.JLabel();
        jtfTipoPgtoNome = new javax.swing.JTextField();
        jlbPortador = new javax.swing.JLabel();
        jtfPortador = new javax.swing.JTextField();
        jlbPortadorNome = new javax.swing.JLabel();
        jtfPortadorNome = new javax.swing.JTextField();
        jlbValor = new javax.swing.JLabel();
        jftValor = new javax.swing.JFormattedTextField();
        jlbSaldo = new javax.swing.JLabel();
        jftSaldo = new javax.swing.JFormattedTextField();
        jlbRepresentante = new javax.swing.JLabel();
        jtfRepresentante = new javax.swing.JTextField();
        jlbRepresentanteNome = new javax.swing.JLabel();
        jtfRepresentanteNome = new javax.swing.JTextField();
        jlbComissao = new javax.swing.JLabel();
        jftComissao = new javax.swing.JFormattedTextField();
        jlbLiquidar = new javax.swing.JLabel();
        jlbDesconto = new javax.swing.JLabel();
        jlbJuros = new javax.swing.JLabel();
        jlbMulta = new javax.swing.JLabel();
        edtLiquidar = new pin.libamg.EdtNumLon();
        edtDesconto = new pin.libamg.EdtNumLon();
        edtJuros = new pin.libamg.EdtNumLon();
        edtMulta = new pin.libamg.EdtNumLon();
        edtLiqTotal = new pin.libamg.EdtNumLon();
        edtDataLiquidacao = new pin.libamg.EdtDat();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PointelAdmin - Liquidar");
        setResizable(false);

        jlbSerieLiq.setText("Série");

        jlbFaturaLiq.setText("Fatura");

        jtfFaturaLiq.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfFaturaLiqFocusLost(evt);
            }
        });

        jtbOpcoes.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jlbLiqHistorico.setText("Histórico");

        jlbLiqHistoricoNome.setText("Histórico - Nome");

        jtfLiqHistoricoNome.setEnabled(false);

        jlbLiqConta.setText("Conta");

        jlbLiqContaNome.setText("Conta - Nome");

        jtfLiqContaNome.setEnabled(false);

        jlbLiqTipoPgto.setText("Tipo Pgto");

        jlbLiqTipoPgtoNome.setText("Tipo Pgto - Nome");

        jtfLiqTipoPgtoNome.setEnabled(false);

        jlbLiqPortador.setText("Portador");

        jlbLiqPortadorNome.setText("Portador - Nome");

        jtfLiqPortadorNome.setEnabled(false);

        jlbLiqComplemento.setText("Complemento");

        javax.swing.GroupLayout jpnLiquidacaoLayout = new javax.swing.GroupLayout(jpnLiquidacao);
        jpnLiquidacao.setLayout(jpnLiquidacaoLayout);
        jpnLiquidacaoLayout.setHorizontalGroup(
            jpnLiquidacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnLiquidacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnLiquidacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfLiqComplemento)
                    .addGroup(jpnLiquidacaoLayout.createSequentialGroup()
                        .addGroup(jpnLiquidacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpnLiquidacaoLayout.createSequentialGroup()
                                .addGroup(jpnLiquidacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfLiqHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbLiqHistorico))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpnLiquidacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbLiqHistoricoNome)
                                    .addComponent(jtfLiqHistoricoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpnLiquidacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfLiqConta, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbLiqConta))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpnLiquidacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbLiqContaNome)
                                    .addComponent(jtfLiqContaNome, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jpnLiquidacaoLayout.createSequentialGroup()
                                .addGroup(jpnLiquidacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfLiqTipoPgto, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbLiqTipoPgto))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpnLiquidacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbLiqTipoPgtoNome)
                                    .addComponent(jtfLiqTipoPgtoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpnLiquidacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfLiqPortador, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbLiqPortador))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpnLiquidacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbLiqPortadorNome)
                                    .addComponent(jtfLiqPortadorNome, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jlbLiqComplemento))
                        .addGap(0, 242, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpnLiquidacaoLayout.setVerticalGroup(
            jpnLiquidacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnLiquidacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnLiquidacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpnLiquidacaoLayout.createSequentialGroup()
                        .addGroup(jpnLiquidacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbLiqConta)
                            .addComponent(jlbLiqContaNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpnLiquidacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfLiqConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfLiqContaNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpnLiquidacaoLayout.createSequentialGroup()
                        .addGroup(jpnLiquidacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbLiqHistorico)
                            .addComponent(jlbLiqHistoricoNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpnLiquidacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfLiqHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfLiqHistoricoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnLiquidacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpnLiquidacaoLayout.createSequentialGroup()
                        .addGroup(jpnLiquidacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbLiqTipoPgto)
                            .addComponent(jlbLiqTipoPgtoNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpnLiquidacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfLiqTipoPgto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfLiqTipoPgtoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpnLiquidacaoLayout.createSequentialGroup()
                        .addGroup(jpnLiquidacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbLiqPortador)
                            .addComponent(jlbLiqPortadorNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpnLiquidacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfLiqPortador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfLiqPortadorNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbLiqComplemento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfLiqComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtbOpcoes.addTab("Liquidação", jpnLiquidacao);

        jlbDesTipoPgto.setText("Tipo Pgto");

        jtfDesContaNome.setEnabled(false);

        jlbDesContaNome.setText("Conta - Nome");

        jlbDesComplemento.setText("Complemento");

        jlbDesConta.setText("Conta");

        jtfDesPortadorNome.setEnabled(false);

        jlbDesHistoricoNome.setText("Histórico - Nome");

        jlbDesPortadorNome.setText("Portador - Nome");

        jtfDesHistoricoNome.setEnabled(false);

        jlbDesPortador.setText("Portador");

        jlbDesHistorico.setText("Histórico");

        jtfDesTipoPgtoNome.setEnabled(false);

        jlbDesTipoPgtoNome.setText("Tipo Pgto - Nome");

        javax.swing.GroupLayout jpnDescontoLayout = new javax.swing.GroupLayout(jpnDesconto);
        jpnDesconto.setLayout(jpnDescontoLayout);
        jpnDescontoLayout.setHorizontalGroup(
            jpnDescontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnDescontoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnDescontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfDesComplemento)
                    .addGroup(jpnDescontoLayout.createSequentialGroup()
                        .addGroup(jpnDescontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpnDescontoLayout.createSequentialGroup()
                                .addGroup(jpnDescontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfDesHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbDesHistorico))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpnDescontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbDesHistoricoNome)
                                    .addComponent(jtfDesHistoricoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpnDescontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfDesConta, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbDesConta))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpnDescontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbDesContaNome)
                                    .addComponent(jtfDesContaNome, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jpnDescontoLayout.createSequentialGroup()
                                .addGroup(jpnDescontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfDesTipoPgto, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbDesTipoPgto))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpnDescontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbDesTipoPgtoNome)
                                    .addComponent(jtfDesTipoPgtoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpnDescontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfDesPortador, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbDesPortador))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpnDescontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbDesPortadorNome)
                                    .addComponent(jtfDesPortadorNome, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jlbDesComplemento))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpnDescontoLayout.setVerticalGroup(
            jpnDescontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnDescontoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnDescontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpnDescontoLayout.createSequentialGroup()
                        .addGroup(jpnDescontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbDesConta)
                            .addComponent(jlbDesContaNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpnDescontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfDesConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfDesContaNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpnDescontoLayout.createSequentialGroup()
                        .addGroup(jpnDescontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbDesHistorico)
                            .addComponent(jlbDesHistoricoNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpnDescontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfDesHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfDesHistoricoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnDescontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpnDescontoLayout.createSequentialGroup()
                        .addGroup(jpnDescontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbDesTipoPgto)
                            .addComponent(jlbDesTipoPgtoNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpnDescontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfDesTipoPgto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfDesTipoPgtoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpnDescontoLayout.createSequentialGroup()
                        .addGroup(jpnDescontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbDesPortador)
                            .addComponent(jlbDesPortadorNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpnDescontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfDesPortador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfDesPortadorNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbDesComplemento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfDesComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtbOpcoes.addTab("Desconto", jpnDesconto);

        jlbJurTipoPgto.setText("Tipo Pgto");

        jtfJurContaNome.setEnabled(false);

        jlbJurContaNome.setText("Conta - Nome");

        jlbJurComplemento.setText("Complemento");

        jlbJurConta.setText("Conta");

        jtfJurPortadorNome.setEnabled(false);

        jlbJurHistoricoNome.setText("Histórico - Nome");

        jlbJurPortadorNome.setText("Portador - Nome");

        jtfJurHistoricoNome.setEnabled(false);

        jlbJurPortador.setText("Portador");

        jlbJurHistorico.setText("Histórico");

        jtfJurTipoPgtoNome.setEnabled(false);

        jlbJurTipoPgtoNome.setText("Tipo Pgto - Nome");

        javax.swing.GroupLayout jpnJurosLayout = new javax.swing.GroupLayout(jpnJuros);
        jpnJuros.setLayout(jpnJurosLayout);
        jpnJurosLayout.setHorizontalGroup(
            jpnJurosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnJurosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnJurosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfJurComplemento)
                    .addGroup(jpnJurosLayout.createSequentialGroup()
                        .addGroup(jpnJurosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpnJurosLayout.createSequentialGroup()
                                .addGroup(jpnJurosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfJurHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbJurHistorico))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpnJurosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbJurHistoricoNome)
                                    .addComponent(jtfJurHistoricoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpnJurosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfJurConta, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbJurConta))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpnJurosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbJurContaNome)
                                    .addComponent(jtfJurContaNome, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jpnJurosLayout.createSequentialGroup()
                                .addGroup(jpnJurosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfJurTipoPgto, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbJurTipoPgto))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpnJurosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbJurTipoPgtoNome)
                                    .addComponent(jtfJurTipoPgtoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpnJurosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfJurPortador, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbJurPortador))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpnJurosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbJurPortadorNome)
                                    .addComponent(jtfJurPortadorNome, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jlbJurComplemento))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpnJurosLayout.setVerticalGroup(
            jpnJurosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnJurosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnJurosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpnJurosLayout.createSequentialGroup()
                        .addGroup(jpnJurosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbJurConta)
                            .addComponent(jlbJurContaNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpnJurosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfJurConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfJurContaNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpnJurosLayout.createSequentialGroup()
                        .addGroup(jpnJurosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbJurHistorico)
                            .addComponent(jlbJurHistoricoNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpnJurosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfJurHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfJurHistoricoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnJurosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpnJurosLayout.createSequentialGroup()
                        .addGroup(jpnJurosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbJurTipoPgto)
                            .addComponent(jlbJurTipoPgtoNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpnJurosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfJurTipoPgto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfJurTipoPgtoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpnJurosLayout.createSequentialGroup()
                        .addGroup(jpnJurosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbJurPortador)
                            .addComponent(jlbJurPortadorNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpnJurosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfJurPortador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfJurPortadorNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbJurComplemento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfJurComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtbOpcoes.addTab("Juros", jpnJuros);

        jlbMulTipoPgto.setText("Tipo Pgto");

        jtfMulContaNome.setEnabled(false);

        jlbMulContaNome.setText("Conta - Nome");

        jlbMulComplemento.setText("Complemento");

        jlbMulConta.setText("Conta");

        jtfMulPortadorNome.setEnabled(false);

        jlbMulHistoricoNome.setText("Histórico - Nome");

        jlbMulPortadorNome.setText("Portador - Nome");

        jtfMulHistoricoNome.setEnabled(false);

        jlbMulPortador.setText("Portador");

        jlbMulHistorico.setText("Histórico");

        jtfMulTipoPgtoNome.setEnabled(false);

        jlbMulTipoPgtoNome.setText("Tipo Pgto - Nome");

        javax.swing.GroupLayout jpnMultaLayout = new javax.swing.GroupLayout(jpnMulta);
        jpnMulta.setLayout(jpnMultaLayout);
        jpnMultaLayout.setHorizontalGroup(
            jpnMultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnMultaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnMultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfMulComplemento)
                    .addGroup(jpnMultaLayout.createSequentialGroup()
                        .addGroup(jpnMultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpnMultaLayout.createSequentialGroup()
                                .addGroup(jpnMultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfMulHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbMulHistorico))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpnMultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbMulHistoricoNome)
                                    .addComponent(jtfMulHistoricoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpnMultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfMulConta, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbMulConta))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpnMultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbMulContaNome)
                                    .addComponent(jtfMulContaNome, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jpnMultaLayout.createSequentialGroup()
                                .addGroup(jpnMultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfMulTipoPgto, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbMulTipoPgto))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpnMultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbMulTipoPgtoNome)
                                    .addComponent(jtfMulTipoPgtoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpnMultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfMulPortador, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbMulPortador))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpnMultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbMulPortadorNome)
                                    .addComponent(jtfMulPortadorNome, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jlbMulComplemento))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpnMultaLayout.setVerticalGroup(
            jpnMultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnMultaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnMultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpnMultaLayout.createSequentialGroup()
                        .addGroup(jpnMultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbMulConta)
                            .addComponent(jlbMulContaNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpnMultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfMulConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfMulContaNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpnMultaLayout.createSequentialGroup()
                        .addGroup(jpnMultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbMulHistorico)
                            .addComponent(jlbMulHistoricoNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpnMultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfMulHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfMulHistoricoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnMultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpnMultaLayout.createSequentialGroup()
                        .addGroup(jpnMultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbMulTipoPgto)
                            .addComponent(jlbMulTipoPgtoNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpnMultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfMulTipoPgto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfMulTipoPgtoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpnMultaLayout.createSequentialGroup()
                        .addGroup(jpnMultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbMulPortador)
                            .addComponent(jlbMulPortadorNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpnMultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfMulPortador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfMulPortadorNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbMulComplemento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfMulComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtbOpcoes.addTab("Multa", jpnMulta);

        jlbDataLiq.setText("Data");

        jlbTotalLiq.setText("Total");

        jbtLiquidar.setText("Liquidar");
        jbtLiquidar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtLiquidarActionPerformed(evt);
            }
        });

        jlbLancamentoLiq.setText("Lançamento");

        jcbLancamentoLiq.setEditable(true);
        jcbLancamentoLiq.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jcbLancamentoLiqFocusLost(evt);
            }
        });

        jlbData.setText("Data");

        jftData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        jftData.setEnabled(false);

        jlbEmissao.setText("Emissão");

        jftEmissao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        jftEmissao.setEnabled(false);

        jlbVencimento.setText("Vencimento");

        jftVencimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        jftVencimento.setEnabled(false);

        jlbSerie.setText("Série");

        jtfSerie.setEnabled(false);

        jlbFatura.setText("Fatura");

        jtfFatura.setEnabled(false);

        jlbPedVendedor.setText("Ped. Vendedor");

        jtfPedVendedor.setEnabled(false);

        jlbPessoa.setText("Pessoa");

        jtfPessoa.setEnabled(false);

        jlbPessoaNome.setText("Pessoa - Nome");

        jtfPessoaNome.setEnabled(false);

        jlbCobranca.setText("Cobrança");

        jtfCobranca.setEnabled(false);

        jlbCobrancaNome.setText("Cobrança - Nome");

        jtfCobrancaNome.setEnabled(false);

        jlbHistorico.setText("Histórico");

        jtfHistorico.setEnabled(false);

        jlbHistoricoNome.setText("Histórico - Nome");

        jtfHistoricoNome.setEnabled(false);

        jlbConta.setText("Conta");

        jtfConta.setEnabled(false);

        jlbContaNome.setText("Conta - Nome");

        jtfContaNome.setEnabled(false);

        jlbTipoPgto.setText("Tipo Pgto");

        jtfTipoPgto.setEnabled(false);

        jlbTipoPgtoNome.setText("Tipo Pgto - Nome");

        jtfTipoPgtoNome.setEnabled(false);

        jlbPortador.setText("Portador");

        jtfPortador.setEnabled(false);

        jlbPortadorNome.setText("Portador - Nome");

        jtfPortadorNome.setEnabled(false);

        jlbValor.setText("Valor");

        jftValor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.000"))));
        jftValor.setEnabled(false);

        jlbSaldo.setText("Saldo");

        jftSaldo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.000"))));
        jftSaldo.setEnabled(false);

        jlbRepresentante.setText("Representante");

        jtfRepresentante.setEnabled(false);

        jlbRepresentanteNome.setText("Representante - Nome");

        jtfRepresentanteNome.setEnabled(false);

        jlbComissao.setText("Comissão");

        jftComissao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.000"))));
        jftComissao.setEnabled(false);

        jlbLiquidar.setText("Liquidar");

        jlbDesconto.setText("Desconto");

        jlbJuros.setText("Juros");

        jlbMulta.setText("Multa");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtbOpcoes)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbValor)
                            .addComponent(jftValor, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jftSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbSaldo))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edtLiquidar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbLiquidar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edtDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbDesconto))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edtJuros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbJuros))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbMulta)
                            .addComponent(edtMulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfSerieLiq, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbSerieLiq))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbFaturaLiq)
                                    .addComponent(jtfFaturaLiq, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbLancamentoLiq)
                                    .addComponent(jcbLancamentoLiq, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbData)
                                    .addComponent(jftData, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbEmissao)
                                    .addComponent(jftEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbVencimento)
                                    .addComponent(jftVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbTipoPgto)
                                    .addComponent(jtfTipoPgto, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbTipoPgtoNome)
                                    .addComponent(jtfTipoPgtoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfRepresentante, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbRepresentante))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbRepresentanteNome)
                                    .addComponent(jtfRepresentanteNome, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbComissao)
                                    .addComponent(jftComissao, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbSerie)
                                    .addComponent(jtfSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbFatura)
                                    .addComponent(jtfFatura, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbPedVendedor)
                                    .addComponent(jtfPedVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbPessoa))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbPessoaNome)
                                    .addComponent(jtfPessoaNome, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfCobranca, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbCobranca))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbCobrancaNome)
                                    .addComponent(jtfCobrancaNome, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbHistorico))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbHistoricoNome)
                                    .addComponent(jtfHistoricoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfConta, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbConta))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbContaNome)
                                    .addComponent(jtfContaNome, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfPortador, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbPortador))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbPortadorNome)
                                    .addComponent(jtfPortadorNome, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(edtDataLiquidacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbDataLiq))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbTotalLiq)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(edtLiqTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jbtLiquidar)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbSerieLiq)
                            .addComponent(jlbFaturaLiq)
                            .addComponent(jlbLancamentoLiq))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbLancamentoLiq, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jtfSerieLiq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtfFaturaLiq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbVencimento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jftVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbEmissao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jftEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbData)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jftData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbPedVendedor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfPedVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbFatura)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfFatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbSerie)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbPessoa)
                            .addComponent(jlbPessoaNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfPessoaNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbCobranca)
                            .addComponent(jlbCobrancaNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfCobranca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfCobrancaNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbHistorico)
                            .addComponent(jlbHistoricoNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfHistoricoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbConta)
                            .addComponent(jlbContaNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfContaNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbPortador)
                            .addComponent(jlbPortadorNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfPortador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfPortadorNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jlbTipoPgto)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jtfTipoPgto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jlbTipoPgtoNome)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jtfTipoPgtoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jlbRepresentante)
                                .addComponent(jlbRepresentanteNome))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jtfRepresentante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtfRepresentanteNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbComissao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jftComissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbSaldo)
                            .addComponent(jlbLiquidar)
                            .addComponent(jlbDesconto)
                            .addComponent(jlbValor)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbJuros)
                            .addComponent(jlbMulta))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jftSaldo)
                        .addComponent(jftValor))
                    .addComponent(edtLiquidar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(edtDesconto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(edtJuros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(edtMulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jtbOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbDataLiq)
                    .addComponent(jlbTotalLiq))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(edtLiqTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtLiquidar)
                    .addComponent(edtDataLiquidacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtfFaturaLiqFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfFaturaLiqFocusLost
        try {
            Conjunto rst = conexao.busca("SELECT codigo FROM lancamentos WHERE serie = ? AND fatura = ? AND per_nf = ? AND situacao = ?",
                    new Vlrs(jtfSerieLiq.getText(), jtfFaturaLiq.getText(), "S", "A"));
            dcmLancamentos.removeAllElements();
            while (rst.proximo()) {
                dcmLancamentos.addElement(rst.pgVlr(1));
            }
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jtfFaturaLiqFocusLost

    private void jcbLancamentoLiqFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jcbLancamentoLiqFocusLost
        try {
            jftData.setValue(null);
            jftEmissao.setValue(null);
            jftVencimento.setValue(null);
            jtfSerie.setText("");
            jtfFatura.setText("");
            jtfPedVendedor.setText("");
            jtfPessoa.setText("");
            jtfPessoaNome.setText("");
            jtfCobranca.setText("");
            jtfCobrancaNome.setText("");
            jtfHistorico.setText("");
            jtfHistoricoNome.setText("");
            jtfConta.setText("");
            jtfContaNome.setText("");
            jtfTipoPgto.setText("");
            jtfTipoPgtoNome.setText("");
            jtfPortador.setText("");
            jtfPortadorNome.setText("");
            jtfRepresentante.setText("");
            jtfRepresentanteNome.setText("");
            jftComissao.setValue(null);
            jftValor.setValue(null);
            jftSaldo.setValue(null);
            edtLiquidar.mdVlr(null);
            edtDesconto.mdVlr(null);
            edtJuros.mdVlr(null);
            edtMulta.mdVlr(null);
            jtfLiqHistorico.setText("");
            jtfLiqHistoricoNome.setText("");
            jtfLiqConta.setText("");
            jtfLiqContaNome.setText("");
            jtfLiqTipoPgto.setText("");
            jtfLiqTipoPgtoNome.setText("");
            jtfLiqPortador.setText("");
            jtfLiqPortadorNome.setText("");
            jtfLiqComplemento.setText("");
            jtfDesHistorico.setText("");
            jtfDesHistoricoNome.setText("");
            jtfDesConta.setText("");
            jtfDesContaNome.setText("");
            jtfDesTipoPgto.setText("");
            jtfDesTipoPgtoNome.setText("");
            jtfDesPortador.setText("");
            jtfDesPortadorNome.setText("");
            jtfDesComplemento.setText("");
            jtfJurHistorico.setText("");
            jtfJurHistoricoNome.setText("");
            jtfJurConta.setText("");
            jtfJurContaNome.setText("");
            jtfJurTipoPgto.setText("");
            jtfJurTipoPgtoNome.setText("");
            jtfJurPortador.setText("");
            jtfJurPortadorNome.setText("");
            jtfJurComplemento.setText("");
            jtfMulHistorico.setText("");
            jtfMulHistoricoNome.setText("");
            jtfMulConta.setText("");
            jtfMulContaNome.setText("");
            jtfMulTipoPgto.setText("");
            jtfMulTipoPgtoNome.setText("");
            jtfMulPortador.setText("");
            jtfMulPortadorNome.setText("");
            jtfMulComplemento.setText("");
            edtDataLiquidacao.mdVlr(null);
            edtLiqTotal.mdVlr(null);
            perNF = "";
            tipo = "";
            situacao = "";
            Conjunto rst = conexao.busca("SELECT lancamentos.data, lancamentos.emissao, lancamentos.vencimento, lancamentos.serie, lancamentos.fatura, "
                    + "   lancamentos.ped_vendedor, lancamentos.pessoa, pessoas.nome, lancamentos.cobranca, cobrancas.nome, lancamentos.historico, "
                    + "   historicos.nome, lancamentos.conta, contas.nome, lancamentos.tipo_pagamento, tipos_pagamento.nome, lancamentos.portador, "
                    + "   portadores.nome, lancamentos.valor, lancamentos.saldo, historicos.hist_liquidacao, historicos.desconto, historicos.juros, "
                    + "   historicos.multa, contas.conta_liq, contas.desconto, contas.juros, contas.multa, portadores.portador_liq, portadores.desconto, "
                    + "   portadores.juros, portadores.multa, lancamentos.per_nf, lancamentos.tipo, lancamentos.situacao, lancamentos.representante, "
                    + "   representante.nome, lancamentos.comissao "
                    + " FROM lancamentos "
                    + "   LEFT JOIN pessoas ON pessoas.codigo = lancamentos.pessoa"
                    + "   LEFT JOIN pessoas AS cobrancas ON cobrancas.codigo = lancamentos.cobranca"
                    + "   LEFT JOIN historicos ON historicos.codigo = lancamentos.historico"
                    + "   LEFT JOIN contas ON contas.codigo = lancamentos.conta"
                    + "   LEFT JOIN tipos_pagamento ON tipos_pagamento.codigo = lancamentos.tipo_pagamento"
                    + "   LEFT JOIN portadores ON portadores.codigo = lancamentos.portador"
                    + "   LEFT JOIN pessoas AS representante ON representante.codigo = lancamentos.representante"
                    + " WHERE lancamentos.codigo = ?",
                    new Vlrs(jcbLancamentoLiq.getSelectedItem()));
            if (rst.proximo()) {
                if (!rst.pgVlr(35).equals("A")) {
                    Utl.alerta("Lançamento não está aberto para ser liquidado.");
                } else {
                    jftData.setValue(rst.pgVlr(1).pgDat());
                    jftEmissao.setValue(rst.pgVlr(2).pgDat());
                    jftVencimento.setValue(rst.pgVlr(3).pgDat());
                    jtfSerie.setText(rst.pgVlr(4).pgCrs());
                    jtfFatura.setText(rst.pgVlr(5).pgCrs());
                    jtfPedVendedor.setText(rst.pgVlr(6).pgCrs());
                    jtfPessoa.setText(rst.pgVlr(7).pgCrs());
                    jtfPessoaNome.setText(rst.pgVlr(8).pgCrs());
                    jtfCobranca.setText(rst.pgVlr(9).pgCrs());
                    jtfCobrancaNome.setText(rst.pgVlr(10).pgCrs());
                    jtfHistorico.setText(rst.pgVlr(11).pgCrs());
                    jtfHistoricoNome.setText(rst.pgVlr(12).pgCrs());
                    jtfConta.setText(rst.pgVlr(13).pgCrs());
                    jtfContaNome.setText(rst.pgVlr(14).pgCrs());
                    jtfTipoPgto.setText(rst.pgVlr(15).pgCrs());
                    jtfTipoPgtoNome.setText(rst.pgVlr(16).pgCrs());
                    jtfPortador.setText(rst.pgVlr(17).pgCrs());
                    jtfPortadorNome.setText(rst.pgVlr(18).pgCrs());
                    jtfRepresentante.setText(rst.pgVlr(36).pgCrs());
                    jtfRepresentanteNome.setText(rst.pgVlr(37).pgCrs());
                    jftComissao.setValue(rst.pgVlr(38).pgNumLon());
                    jftValor.setValue(rst.pgVlr(19).pgNumLon());
                    jftSaldo.setValue(rst.pgVlr(20).pgNumLon());
                    edtLiquidar.mdVlr(rst.pgVlr(20));
                    edtDesconto.mdVlr(0.0);
                    edtJuros.mdVlr(0.0);
                    edtMulta.mdVlr(0.0);
                    edtDataLiquidacao.mdVlr(new java.util.Date());
                    edtLiqTotal.mdVlr(rst.pgVlr(20).pgNumLon());
                    jtfLiqTipoPgto.setText(rst.pgVlr(15).pgCrs());
                    jtfLiqTipoPgtoNome.setText(rst.pgVlr(16).pgCrs());
                    jtfDesTipoPgto.setText(rst.pgVlr(15).pgCrs());
                    jtfDesTipoPgtoNome.setText(rst.pgVlr(16).pgCrs());
                    jtfJurTipoPgto.setText(rst.pgVlr(15).pgCrs());
                    jtfJurTipoPgtoNome.setText(rst.pgVlr(16).pgCrs());
                    jtfMulTipoPgto.setText(rst.pgVlr(15).pgCrs());
                    jtfMulTipoPgtoNome.setText(rst.pgVlr(16).pgCrs());
                    String gerLiqHis = edtLiquidar.pgVlr().pgNumLon() >= 0 ? padHistLiqu : padHistDebi;
                    String gerDesHis = padHistDesc;
                    String gerJusHis = padHistJuro;
                    String gerMulHis = padHistMult;
                    String gerLiqCon = edtLiquidar.pgVlr().pgNumLon() >= 0 ? padContLiqu : padContDebi;
                    String gerDesCon = padContDesc;
                    String gerJusCon = padContJuro;
                    String gerMulCon = padContMult;
                    String gerLiqPor = edtLiquidar.pgVlr().pgNumLon() >= 0 ? padPortLiqu : padPortDebi;
                    String gerDesPor = padPortDesc;
                    String gerJusPor = padPortJuro;
                    String gerMulPor = padPortMult;
                    String parLiqHis = rst.pgVlr(21).pgCrs();
                    String parDesHis = rst.pgVlr(22).pgCrs();
                    String parJusHis = rst.pgVlr(23).pgCrs();
                    String parMulHis = rst.pgVlr(24).pgCrs();
                    String parLiqCon = rst.pgVlr(25).vazio() ? rst.pgVlr(13).pgCrs() : rst.pgVlr(25).pgCrs();
                    String parDesCon = rst.pgVlr(26).pgCrs();
                    String parJusCon = rst.pgVlr(27).pgCrs();
                    String parMulCon = rst.pgVlr(28).pgCrs();
                    String parLiqPor = rst.pgVlr(29).pgCrs();
                    String parDesPor = rst.pgVlr(30).pgCrs();
                    String parJusPor = rst.pgVlr(31).pgCrs();
                    String parMulPor = rst.pgVlr(32).pgCrs();
                    perNF = rst.pgVlr(33).pgCrs();
                    tipo = rst.pgVlr(34).pgCrs();
                    situacao = rst.pgVlr(35).pgCrs();
                    String usaLiqHis = null;
                    String usaDesHis = null;
                    String usaJusHis = null;
                    String usaMulHis = null;
                    String usaLiqCon = null;
                    String usaDesCon = null;
                    String usaJusCon = null;
                    String usaMulCon = null;
                    String usaLiqPor = null;
                    String usaDesPor = null;
                    String usaJusPor = null;
                    String usaMulPor = null;
                    if (!UtlCrs.vazio(parLiqHis)) {
                        usaLiqHis = parLiqHis;
                    } else {
                        usaLiqHis = gerLiqHis;
                    }
                    if (!UtlCrs.vazio(parDesHis)) {
                        usaDesHis = parDesHis;
                    } else {
                        usaDesHis = gerDesHis;
                    }
                    if (!UtlCrs.vazio(parJusHis)) {
                        usaJusHis = parJusHis;
                    } else {
                        usaJusHis = gerJusHis;
                    }
                    if (!UtlCrs.vazio(parMulHis)) {
                        usaMulHis = parMulHis;
                    } else {
                        usaMulHis = gerMulHis;
                    }
                    if (!UtlCrs.vazio(parLiqCon)) {
                        usaLiqCon = parLiqCon;
                    } else {
                        usaLiqCon = gerLiqCon;
                    }
                    if (!UtlCrs.vazio(parDesCon)) {
                        usaDesCon = parDesCon;
                    } else {
                        usaDesCon = gerDesCon;
                    }
                    if (!UtlCrs.vazio(parJusCon)) {
                        usaJusCon = parJusCon;
                    } else {
                        usaJusCon = gerJusCon;
                    }
                    if (!UtlCrs.vazio(parMulCon)) {
                        usaMulCon = parMulCon;
                    } else {
                        usaMulCon = gerMulCon;
                    }
                    if (!UtlCrs.vazio(parLiqPor)) {
                        usaLiqPor = parLiqPor;
                    } else {
                        usaLiqPor = gerLiqPor;
                    }
                    if (!UtlCrs.vazio(parDesPor)) {
                        usaDesPor = parDesPor;
                    } else {
                        usaDesPor = gerDesPor;
                    }
                    if (!UtlCrs.vazio(parJusPor)) {
                        usaJusPor = parJusPor;
                    } else {
                        usaJusPor = gerJusPor;
                    }
                    if (!UtlCrs.vazio(parMulPor)) {
                        usaMulPor = parMulPor;
                    } else {
                        usaMulPor = gerMulPor;
                    }
                    String usaLiqHisNom = conexao.recorrentes().busca("SELECT nome FROM historicos WHERE codigo = ?",
                            new Vlrs(usaLiqHis))
                            .pgCol().pgCrs("");
                    String usaDesHisNom = conexao.recorrentes().busca("SELECT nome FROM historicos WHERE codigo = ?",
                            new Vlrs(usaDesHis))
                            .pgCol().pgCrs("");
                    String usaJusHisNom = conexao.recorrentes().busca("SELECT nome FROM historicos WHERE codigo = ?",
                            new Vlrs(usaJusHis))
                            .pgCol().pgCrs("");
                    String usaMulHisNom = conexao.recorrentes().busca("SELECT nome FROM historicos WHERE codigo = ?",
                            new Vlrs(usaMulHis))
                            .pgCol().pgCrs("");
                    String usaLiqConNom = conexao.recorrentes().busca("SELECT nome FROM contas WHERE codigo = ?",
                            new Vlrs(usaLiqCon))
                            .pgCol().pgCrs("");
                    String usaDesConNom = conexao.recorrentes().busca("SELECT nome FROM contas WHERE codigo = ?",
                            new Vlrs(usaDesCon))
                            .pgCol().pgCrs("");
                    String usaJusConNom = conexao.recorrentes().busca("SELECT nome FROM contas WHERE codigo = ?",
                            new Vlrs(usaJusCon))
                            .pgCol().pgCrs("");
                    String usaMulConNom = conexao.recorrentes().busca("SELECT nome FROM contas WHERE codigo = ?",
                            new Vlrs(usaMulCon))
                            .pgCol().pgCrs("");
                    String usaLiqPorNom = conexao.recorrentes().busca("SELECT nome FROM portadores WHERE codigo = ?",
                            new Vlrs(usaLiqPor))
                            .pgCol().pgCrs("");
                    String usaDesPorNom = conexao.recorrentes().busca("SELECT nome FROM portadores WHERE codigo = ?",
                            new Vlrs(usaDesPor))
                            .pgCol().pgCrs("");
                    String usaJusPorNom = conexao.recorrentes().busca("SELECT nome FROM portadores WHERE codigo = ?",
                            new Vlrs(usaJusPor))
                            .pgCol().pgCrs("");
                    String usaMulPorNom = conexao.recorrentes().busca("SELECT nome FROM portadores WHERE codigo = ?",
                            new Vlrs(usaMulPor))
                            .pgCol().pgCrs("");
                    jtfLiqHistorico.setText(usaLiqHis);
                    jtfLiqHistoricoNome.setText(usaLiqHisNom);
                    jtfDesHistorico.setText(usaDesHis);
                    jtfDesHistoricoNome.setText(usaDesHisNom);
                    jtfJurHistorico.setText(usaJusHis);
                    jtfJurHistoricoNome.setText(usaJusHisNom);
                    jtfMulHistorico.setText(usaMulHis);
                    jtfMulHistoricoNome.setText(usaMulHisNom);
                    jtfLiqConta.setText(usaLiqCon);
                    jtfLiqContaNome.setText(usaLiqConNom);
                    jtfDesConta.setText(usaDesCon);
                    jtfDesContaNome.setText(usaDesConNom);
                    jtfJurConta.setText(usaJusCon);
                    jtfJurContaNome.setText(usaJusConNom);
                    jtfMulConta.setText(usaMulCon);
                    jtfMulContaNome.setText(usaMulConNom);
                    jtfLiqPortador.setText(usaLiqPor);
                    jtfLiqPortadorNome.setText(usaLiqPorNom);
                    jtfDesPortador.setText(usaDesPor);
                    jtfDesPortadorNome.setText(usaDesPorNom);
                    jtfJurPortador.setText(usaJusPor);
                    jtfJurPortadorNome.setText(usaJusPorNom);
                    jtfMulPortador.setText(usaMulPor);
                    jtfMulPortadorNome.setText(usaMulPorNom);
                }
            }
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jcbLancamentoLiqFocusLost

    private void jbtLiquidarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtLiquidarActionPerformed
        try {
            Double valor = UtlNumLon.pega(jftValor.getValue(), 0.0);
            Double saldo = UtlNumLon.pega(jftSaldo.getValue(), 0.0);
            Double comissao = UtlNumLon.pega(jftComissao.getValue(), 0.0);

            Double liquidar = UtlNumLon.pega(edtLiquidar.pgVlr(), 0.0);
            Double desconto = UtlNumLon.pega(edtDesconto.pgVlr(), 0.0);
            Double juros = UtlNumLon.pega(edtJuros.pgVlr(), 0.0);
            Double multa = UtlNumLon.pega(edtMulta.pgVlr(), 0.0);

            if ((saldo == 0.0) || (liquidar == 0.0)) {
                Utl.alerta("Não há nada a ser liquidado.");
            } else {
                Banco conExe = conexao.iniciaTransacao();

                try {
                    conExe.opera("INSERT INTO lancamentos (codigo, data, emissao, vencimento, serie, fatura, per_nf, ped_vendedor, "
                            + " historico, tipo, situacao, complemento, conta, valor, saldo, pessoa, cobranca, tipo_pagamento, portador, "
                            + " representante, comissao, vinculo) "
                            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                            new Vlrs(CodigosMod.pegaProximo(conexao, "lancamentos"), UtlDat.pegaSQL(edtDataLiquidacao.pgVlr()), UtlDat.pegaSQL(jftEmissao.getValue()),
                                    UtlDat.pegaSQL(jftVencimento.getValue()), jtfSerie.getText(), jtfFatura.getText(), perNF, jtfPedVendedor.getText(),
                                    jtfLiqHistorico.getText(), (liquidar > 0.0 ? "E" : "S"), "Q", jtfLiqComplemento.getText(),
                                    jtfLiqConta.getText(), liquidar, 0.0, jtfPessoa.getText(), jtfCobranca.getText(),
                                    jtfLiqTipoPgto.getText(), jtfLiqPortador.getText(), (jtfRepresentante.getText().isEmpty() ? null : jtfRepresentante.getText()), liquidar * comissao / valor,
                                    jcbLancamentoLiq.getSelectedItem()));

                    if (desconto != 0.0) {
                        conExe.opera("INSERT INTO lancamentos (codigo, data, emissao, vencimento, serie, fatura, per_nf, ped_vendedor, "
                                + " historico, tipo, situacao, complemento, conta, valor, saldo, pessoa, cobranca, tipo_pagamento, portador, "
                                + " representante, comissao, vinculo) "
                                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                                new Vlrs(CodigosMod.pegaProximo(conexao, "lancamentos"), UtlDat.pegaSQL(edtDataLiquidacao.pgVlr()), UtlDat.pegaSQL(jftEmissao.getValue()),
                                        UtlDat.pegaSQL(jftVencimento.getValue()), jtfSerie.getText(), jtfFatura.getText(), perNF, jtfPedVendedor.getText(),
                                        jtfDesHistorico.getText(), (desconto > 0.0 ? "E" : "S"), "D", jtfDesComplemento.getText(),
                                        jtfDesConta.getText(), desconto, 0.0, jtfPessoa.getText(), jtfCobranca.getText(),
                                        jtfDesTipoPgto.getText(), jtfDesPortador.getText(), (jtfRepresentante.getText().isEmpty() ? null : jtfRepresentante.getText()), desconto * comissao / valor,
                                        jcbLancamentoLiq.getSelectedItem()));
                    }

                    if (juros != 0.0) {
                        conExe.opera("INSERT INTO lancamentos (codigo, data, emissao, vencimento, serie, fatura, per_nf, ped_vendedor, "
                                + " historico, tipo, situacao, complemento, conta, valor, saldo, pessoa, cobranca, tipo_pagamento, portador, "
                                + " representante, comissao, vinculo) "
                                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                                new Vlrs(CodigosMod.pegaProximo(conexao, "lancamentos"), UtlDat.pegaSQL(edtDataLiquidacao.pgVlr()), UtlDat.pegaSQL(jftEmissao.getValue()),
                                        UtlDat.pegaSQL(jftVencimento.getValue()), jtfSerie.getText(), jtfFatura.getText(), perNF, jtfPedVendedor.getText(),
                                        jtfJurHistorico.getText(), (juros > 0.0 ? "E" : "S"), "J", jtfJurComplemento.getText(),
                                        jtfJurConta.getText(), juros, 0.0, jtfPessoa.getText(), jtfCobranca.getText(),
                                        jtfJurTipoPgto.getText(), jtfJurPortador.getText(), (jtfRepresentante.getText().isEmpty() ? null : jtfRepresentante.getText()), 0.0,
                                        jcbLancamentoLiq.getSelectedItem()));
                    }

                    if (multa != 0.0) {
                        conExe.opera("INSERT INTO lancamentos (codigo, data, emissao, vencimento, serie, fatura, per_nf, ped_vendedor, "
                                + " historico, tipo, situacao, complemento, conta, valor, saldo, pessoa, cobranca, tipo_pagamento, portador, "
                                + " representante, comissao, vinculo) "
                                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                                new Vlrs(CodigosMod.pegaProximo(conexao, "lancamentos"), UtlDat.pegaSQL(edtDataLiquidacao.pgVlr()), UtlDat.pegaSQL(jftEmissao.getValue()),
                                        UtlDat.pegaSQL(jftVencimento.getValue()), jtfSerie.getText(), jtfFatura.getText(), perNF, jtfPedVendedor.getText(),
                                        jtfMulHistorico.getText(), (multa > 0.0 ? "E" : "S"), "M", jtfMulComplemento.getText(),
                                        jtfMulConta.getText(), multa, 0.0, jtfPessoa.getText(), jtfCobranca.getText(),
                                        jtfMulTipoPgto.getText(), jtfMulPortador.getText(), (jtfRepresentante.getText().isEmpty() ? null : jtfRepresentante.getText()), 0.0,
                                        jcbLancamentoLiq.getSelectedItem()));
                    }

                    Double novoSaldo = saldo - liquidar;
                    String novoTipo = "";
                    String novaSituacao = "";
                    if (liquidar < 0) {
                        if (novoSaldo == 0.0) {
                            novoTipo = "G";
                            novaSituacao = "Q";
                        } else {
                            novoTipo = "P";
                            novaSituacao = "A";
                        }
                    } else {
                        if (novoSaldo == 0.0) {
                            novoTipo = "C";
                            novaSituacao = "Q";
                        } else {
                            novoTipo = "R";
                            novaSituacao = "A";
                        }
                    }

                    conExe.opera("UPDATE lancamentos SET saldo = ? , tipo = ? , situacao = ? WHERE codigo = ?",
                            new Vlrs(novoSaldo, novoTipo, novaSituacao, jcbLancamentoLiq.getSelectedItem()));

                    conExe.salvaTransacao();

                    jtfFaturaLiq.setText("");

                    while (dcmLancamentos.getSize() > 0) {
                        dcmLancamentos.removeElementAt(0);
                    }

                    jcbLancamentoLiq.setSelectedItem("");
                    jcbLancamentoLiqFocusLost(null);
                    jtfFaturaLiq.requestFocus();

                    Utl.alertaPop("Lançamento Liquidado Com Sucesso");
                } catch (Exception ex) {
                    Utl.registra(ex);
                    conExe.cancelaTransacao();
                }
            }
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jbtLiquidarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private pin.libamg.EdtDat edtDataLiquidacao;
    private pin.libamg.EdtNumLon edtDesconto;
    private pin.libamg.EdtNumLon edtJuros;
    private pin.libamg.EdtNumLon edtLiqTotal;
    public pin.libamg.EdtNumLon edtLiquidar;
    private pin.libamg.EdtNumLon edtMulta;
    private javax.swing.JButton jbtLiquidar;
    public javax.swing.JComboBox<String> jcbLancamentoLiq;
    private javax.swing.JFormattedTextField jftComissao;
    private javax.swing.JFormattedTextField jftData;
    private javax.swing.JFormattedTextField jftEmissao;
    private javax.swing.JFormattedTextField jftSaldo;
    private javax.swing.JFormattedTextField jftValor;
    private javax.swing.JFormattedTextField jftVencimento;
    private javax.swing.JLabel jlbCobranca;
    private javax.swing.JLabel jlbCobrancaNome;
    private javax.swing.JLabel jlbComissao;
    private javax.swing.JLabel jlbConta;
    private javax.swing.JLabel jlbContaNome;
    private javax.swing.JLabel jlbData;
    private javax.swing.JLabel jlbDataLiq;
    private javax.swing.JLabel jlbDesComplemento;
    private javax.swing.JLabel jlbDesConta;
    private javax.swing.JLabel jlbDesContaNome;
    private javax.swing.JLabel jlbDesHistorico;
    private javax.swing.JLabel jlbDesHistoricoNome;
    private javax.swing.JLabel jlbDesPortador;
    private javax.swing.JLabel jlbDesPortadorNome;
    private javax.swing.JLabel jlbDesTipoPgto;
    private javax.swing.JLabel jlbDesTipoPgtoNome;
    private javax.swing.JLabel jlbDesconto;
    private javax.swing.JLabel jlbEmissao;
    private javax.swing.JLabel jlbFatura;
    private javax.swing.JLabel jlbFaturaLiq;
    private javax.swing.JLabel jlbHistorico;
    private javax.swing.JLabel jlbHistoricoNome;
    private javax.swing.JLabel jlbJurComplemento;
    private javax.swing.JLabel jlbJurConta;
    private javax.swing.JLabel jlbJurContaNome;
    private javax.swing.JLabel jlbJurHistorico;
    private javax.swing.JLabel jlbJurHistoricoNome;
    private javax.swing.JLabel jlbJurPortador;
    private javax.swing.JLabel jlbJurPortadorNome;
    private javax.swing.JLabel jlbJurTipoPgto;
    private javax.swing.JLabel jlbJurTipoPgtoNome;
    private javax.swing.JLabel jlbJuros;
    private javax.swing.JLabel jlbLancamentoLiq;
    private javax.swing.JLabel jlbLiqComplemento;
    private javax.swing.JLabel jlbLiqConta;
    private javax.swing.JLabel jlbLiqContaNome;
    private javax.swing.JLabel jlbLiqHistorico;
    private javax.swing.JLabel jlbLiqHistoricoNome;
    private javax.swing.JLabel jlbLiqPortador;
    private javax.swing.JLabel jlbLiqPortadorNome;
    private javax.swing.JLabel jlbLiqTipoPgto;
    private javax.swing.JLabel jlbLiqTipoPgtoNome;
    private javax.swing.JLabel jlbLiquidar;
    private javax.swing.JLabel jlbMulComplemento;
    private javax.swing.JLabel jlbMulConta;
    private javax.swing.JLabel jlbMulContaNome;
    private javax.swing.JLabel jlbMulHistorico;
    private javax.swing.JLabel jlbMulHistoricoNome;
    private javax.swing.JLabel jlbMulPortador;
    private javax.swing.JLabel jlbMulPortadorNome;
    private javax.swing.JLabel jlbMulTipoPgto;
    private javax.swing.JLabel jlbMulTipoPgtoNome;
    private javax.swing.JLabel jlbMulta;
    private javax.swing.JLabel jlbPedVendedor;
    private javax.swing.JLabel jlbPessoa;
    private javax.swing.JLabel jlbPessoaNome;
    private javax.swing.JLabel jlbPortador;
    private javax.swing.JLabel jlbPortadorNome;
    private javax.swing.JLabel jlbRepresentante;
    private javax.swing.JLabel jlbRepresentanteNome;
    private javax.swing.JLabel jlbSaldo;
    private javax.swing.JLabel jlbSerie;
    private javax.swing.JLabel jlbSerieLiq;
    private javax.swing.JLabel jlbTipoPgto;
    private javax.swing.JLabel jlbTipoPgtoNome;
    private javax.swing.JLabel jlbTotalLiq;
    private javax.swing.JLabel jlbValor;
    private javax.swing.JLabel jlbVencimento;
    private javax.swing.JPanel jpnDesconto;
    private javax.swing.JPanel jpnJuros;
    private javax.swing.JPanel jpnLiquidacao;
    private javax.swing.JPanel jpnMulta;
    private javax.swing.JTabbedPane jtbOpcoes;
    private javax.swing.JTextField jtfCobranca;
    private javax.swing.JTextField jtfCobrancaNome;
    private javax.swing.JTextField jtfConta;
    private javax.swing.JTextField jtfContaNome;
    private javax.swing.JTextField jtfDesComplemento;
    private javax.swing.JTextField jtfDesConta;
    private javax.swing.JTextField jtfDesContaNome;
    private javax.swing.JTextField jtfDesHistorico;
    private javax.swing.JTextField jtfDesHistoricoNome;
    private javax.swing.JTextField jtfDesPortador;
    private javax.swing.JTextField jtfDesPortadorNome;
    private javax.swing.JTextField jtfDesTipoPgto;
    private javax.swing.JTextField jtfDesTipoPgtoNome;
    private javax.swing.JTextField jtfFatura;
    public javax.swing.JTextField jtfFaturaLiq;
    private javax.swing.JTextField jtfHistorico;
    private javax.swing.JTextField jtfHistoricoNome;
    private javax.swing.JTextField jtfJurComplemento;
    private javax.swing.JTextField jtfJurConta;
    private javax.swing.JTextField jtfJurContaNome;
    private javax.swing.JTextField jtfJurHistorico;
    private javax.swing.JTextField jtfJurHistoricoNome;
    private javax.swing.JTextField jtfJurPortador;
    private javax.swing.JTextField jtfJurPortadorNome;
    private javax.swing.JTextField jtfJurTipoPgto;
    private javax.swing.JTextField jtfJurTipoPgtoNome;
    private javax.swing.JTextField jtfLiqComplemento;
    private javax.swing.JTextField jtfLiqConta;
    private javax.swing.JTextField jtfLiqContaNome;
    private javax.swing.JTextField jtfLiqHistorico;
    private javax.swing.JTextField jtfLiqHistoricoNome;
    private javax.swing.JTextField jtfLiqPortador;
    private javax.swing.JTextField jtfLiqPortadorNome;
    private javax.swing.JTextField jtfLiqTipoPgto;
    private javax.swing.JTextField jtfLiqTipoPgtoNome;
    private javax.swing.JTextField jtfMulComplemento;
    private javax.swing.JTextField jtfMulConta;
    private javax.swing.JTextField jtfMulContaNome;
    private javax.swing.JTextField jtfMulHistorico;
    private javax.swing.JTextField jtfMulHistoricoNome;
    private javax.swing.JTextField jtfMulPortador;
    private javax.swing.JTextField jtfMulPortadorNome;
    private javax.swing.JTextField jtfMulTipoPgto;
    private javax.swing.JTextField jtfMulTipoPgtoNome;
    private javax.swing.JTextField jtfPedVendedor;
    private javax.swing.JTextField jtfPessoa;
    private javax.swing.JTextField jtfPessoaNome;
    private javax.swing.JTextField jtfPortador;
    private javax.swing.JTextField jtfPortadorNome;
    private javax.swing.JTextField jtfRepresentante;
    private javax.swing.JTextField jtfRepresentanteNome;
    private javax.swing.JTextField jtfSerie;
    public javax.swing.JTextField jtfSerieLiq;
    private javax.swing.JTextField jtfTipoPgto;
    private javax.swing.JTextField jtfTipoPgtoNome;
    // End of variables declaration//GEN-END:variables

    private class CnfTotal extends Conferencia {

        @Override
        public void confere(Object comOrigem) throws Exception {
            Double valLiq = edtLiquidar.pgVlr().pgNumLon(0.0);
            Double valDes = edtDesconto.pgVlr().pgNumLon(0.0);
            Double valJur = edtJuros.pgVlr().pgNumLon(0.0);
            Double valMul = edtMulta.pgVlr().pgNumLon(0.0);
            if (valLiq < 0) {
                if (valDes < 0) {
                    valDes = -1 * valDes;
                }
                if (valJur > 0) {
                    valJur = -1 * valJur;
                }
                if (valMul > 0) {
                    valMul = -1 * valMul;
                }
            }
            if (valLiq > 0) {
                if (valDes > 0) {
                    valDes = -1 * valDes;
                }
                if (valJur < 0) {
                    valJur = -1 * valJur;
                }
                if (valMul < 0) {
                    valMul = -1 * valMul;
                }
            }
            Double valTot = valLiq + valDes + valJur + valMul;
            edtLiquidar.mdVlr(valLiq);
            edtDesconto.mdVlr(valDes);
            edtJuros.mdVlr(valJur);
            edtMulta.mdVlr(valMul);
            edtLiqTotal.mdVlr(valTot);
        }
    }

}
