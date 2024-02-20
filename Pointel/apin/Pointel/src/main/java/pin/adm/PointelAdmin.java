package pin.adm;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import pin.Pointel;
import pin.libamk.Cmp;
import pin.libbas.Dados;
import pin.libbas.Globais;
import pin.libjan.Janelas;
import pin.libjan.PopMenu;
import pin.libutl.Utl;
import pin.modamk.Opcoes;
import pin.modamk.Recursos;
import pin.modinf.Conexao;
import pin.modpim.Pims;

public class PointelAdmin extends javax.swing.JFrame {

    public Conexao conexao;
    public PopMenu menu;

    public PointelAdmin() throws Exception {
        initComponents();
        setIconImage(Pims.pega("PointelAdmin.png").getImage());
        conexao = (Conexao) Globais.pega("mainConc");
        menu = new PopMenu();
        Recursos mainRecs = (Recursos) Globais.pega("mainRecs");
        mainRecs.criaMenu("Pointel.PointelAdmin", this, menu);
        menu.botaSeparador();
        Janelas.botaAtalhoEMenu(this, menu, "PointelAdmin.Opções", "Opções", "control alt O", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    Opcoes opcs = new Opcoes(new Cmp[]{
                        new Cmp("Empresa", 1, 1, "Pessoa do Cabeçalho", Dados.Tp.Crs).mTamanho(8).botaDetalhe("referencia", "PointelAdmin - Opções - Empresa - Pessoa do Cabeçalho"),
                        new Cmp("Empresa", 1, 2, "Crédito Inicial", Dados.Tp.Crs).mTamanho(6).botaDetalhe("referencia", "PointelAdmin - Opções - Empresa - Crédito Inicial"),
                        new Cmp("Vendas", 1, 1, "Histórico das Faturas", Dados.Tp.Crs).mTamanho(4).botaDetalhe("referencia", "PointelAdmin - Opções - Vendas - Histórico das Faturas"),
                        new Cmp("Vendas", 1, 2, "Conta das Faturas", Dados.Tp.Crs).mTamanho(6).botaDetalhe("referencia", "PointelAdmin - Opções - Vendas - Conta das Faturas"),
                        new Cmp("Vendas", 1, 3, "Portador das Faturas", Dados.Tp.Crs).mTamanho(6).botaDetalhe("referencia", "PointelAdmin - Opções - Vendas - Portador das Faturas"),
                        new Cmp("Financeiro", 1, 1, "Histórico das Liquidações", Dados.Tp.Crs).mTamanho(4).botaDetalhe("referencia", "PointelAdmin - Opções - Financeiro - Histórico das Liquidações"),
                        new Cmp("Financeiro", 1, 2, "Conta das Liquidações", Dados.Tp.Crs).mTamanho(6).botaDetalhe("referencia", "PointelAdmin - Opções - Financeiro - Conta das Liquidações"),
                        new Cmp("Financeiro", 1, 3, "Portador das Liquidações", Dados.Tp.Crs).mTamanho(6).botaDetalhe("referencia", "PointelAdmin - Opções - Financeiro - Portador das Liquidações"),
                        new Cmp("Financeiro", 2, 1, "Histórico dos Débitos", Dados.Tp.Crs).mTamanho(4).botaDetalhe("referencia", "PointelAdmin - Opções - Financeiro - Histórico das Débitos"),
                        new Cmp("Financeiro", 2, 2, "Conta dos Débitos", Dados.Tp.Crs).mTamanho(6).botaDetalhe("referencia", "PointelAdmin - Opções - Financeiro - Conta das Débitos"),
                        new Cmp("Financeiro", 2, 3, "Portador dos Débitos", Dados.Tp.Crs).mTamanho(6).botaDetalhe("referencia", "PointelAdmin - Opções - Financeiro - Portador das Débitos"),
                        new Cmp("Financeiro", 3, 1, "Histórico dos Descontos", Dados.Tp.Crs).mTamanho(4).botaDetalhe("referencia", "PointelAdmin - Opções - Financeiro - Histórico dos Descontos"),
                        new Cmp("Financeiro", 3, 2, "Conta dos Descontos", Dados.Tp.Crs).mTamanho(6).botaDetalhe("referencia", "PointelAdmin - Opções - Financeiro - Conta dos Descontos"),
                        new Cmp("Financeiro", 3, 3, "Portador dos Descontos", Dados.Tp.Crs).mTamanho(6).botaDetalhe("referencia", "PointelAdmin - Opções - Financeiro - Portador dos Descontos"),
                        new Cmp("Financeiro", 4, 1, "Histórico dos Juros", Dados.Tp.Crs).mTamanho(4).botaDetalhe("referencia", "PointelAdmin - Opções - Financeiro - Histórico dos Juros"),
                        new Cmp("Financeiro", 4, 2, "Conta dos Juros", Dados.Tp.Crs).mTamanho(6).botaDetalhe("referencia", "PointelAdmin - Opções - Financeiro - Conta dos Juros"),
                        new Cmp("Financeiro", 4, 3, "Portador dos Juros", Dados.Tp.Crs).mTamanho(6).botaDetalhe("referencia", "PointelAdmin - Opções - Financeiro - Portador dos Juros"),
                        new Cmp("Financeiro", 5, 1, "Histórico das Multas", Dados.Tp.Crs).mTamanho(4).botaDetalhe("referencia", "PointelAdmin - Opções - Financeiro - Histórico das Multas"),
                        new Cmp("Financeiro", 5, 2, "Conta das Multas", Dados.Tp.Crs).mTamanho(6).botaDetalhe("referencia", "PointelAdmin - Opções - Financeiro - Conta das Multas"),
                        new Cmp("Financeiro", 5, 3, "Portador das Multas", Dados.Tp.Crs).mTamanho(6).botaDetalhe("referencia", "PointelAdmin - Opções - Financeiro - Portador das Multas"),
                        new Cmp("Financeiro", 6, 1, "Comissão nos Juros", Dados.Tp.Log).mVlrInicial(false).botaDetalhe("referencia", "PointelAdmin - Opções - Financeiro - Comissão nos Juros"),
                        new Cmp("Financeiro", 6, 2, "Comissão nas Multas", Dados.Tp.Log).mVlrInicial(false).botaDetalhe("referencia", "PointelAdmin - Opções - Financeiro - Comissão nas Multas")
                    });
                    opcs.mostra();
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        });
        menu.coloca(getContentPane());
        Janelas.inicia(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PointelAdmin");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 58, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        try {
            Recursos mainRecs = (Recursos) Globais.pega("mainRecs");
            if (mainRecs.verificaRestricao(this, "PointelAdmin")) {
                conexao.conectaBase(this, true);
            }
        } catch (Exception ex) {
            Utl.registra(ex);
            Janelas.fecha(this);
        }
    }//GEN-LAST:event_formWindowActivated

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        try {
            Pointel.mainConc.fechar();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_formWindowClosed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
