package pin.inf;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import pin.libetf.PlaEtf;
import pin.libetf.TexEtf;
import pin.libbas.Configs;
import pin.libbas.Globais;
import pin.libjan.Janelas;
import pin.libutl.Utl;
import pin.libutl.UtlBin;
import pin.libutl.UtlTex;
import pin.modinf.Carregador;
import pin.modinf.Conexao;
import pin.modpim.Pims;

public class Operacoes extends javax.swing.JFrame {

    public Configs cfgs;
    public Conexao conexao;

    public Operacoes() {
        initComponents();
        setIconImage(Pims.pega("Operacoes.png").getImage());
        cfgs = (Configs) Globais.pega("mainConf");
        conexao = (Conexao) Globais.pega("mainConc");
        edtComandos.pPopMenu().botaSeparador();
        edtComandos.pPopMenu().bota("Procurar", new ActProcurar()).botaAtalho(edtComandos, "control shift alt ENTER");
        edtComandos.pPopMenu().bota("Executar", new ActExecutar()).botaAtalho(edtComandos, "control shift ENTER");
        Janelas.inicia(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        edtComandos = new pin.libamg.EdtTex();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Operações");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(edtComandos, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(edtComandos, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private pin.libamg.EdtTex edtComandos;
    // End of variables declaration//GEN-END:variables

    public class ActProcurar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String comando = edtComandos.pgVlr("").pgCrs().trim();
                if (!comando.isEmpty()) {
                    new Thread("Comandos Procura") {
                        @Override
                        public void run() {
                            try {
                                if (!comando.contains("-prox-")) {
                                    PlaEtf intf = new PlaEtf(comando);
                                    intf.mostra("Operações Procura");
                                    new Carregador(conexao, comando).mudaEditor(intf.edt()).inicia();
                                } else {
                                    String cmds[] = comando.split("-prox-");
                                    for (String cmd : cmds) {
                                        cmd = cmd.trim();
                                        PlaEtf intf = new PlaEtf(cmd);
                                        intf.mostra("Operações Procura");
                                        if (!cmd.isEmpty()) {
                                            new Carregador(conexao, cmd).mudaEditor(intf.edt()).inicia();
                                        }
                                    }
                                }
                            } catch (Exception ex) {
                                Utl.registra(ex);
                            }
                        }
                    }.start();
                } else {
                    Utl.alerta("Impossível Executar um Comando Vazio");
                }
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }

    }

    public class ActExecutar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String comando = edtComandos.pgVlr("").pgCrs().trim();
                if (!comando.isEmpty()) {
                    new Thread("Comandos Executa") {
                        @Override
                        public void run() {
                            int idm = Utl.alertaPopNovoId();
                            try {
                                Utl.alertaPop(idm, "Comando(s) Executando...");
                                if (!comando.contains("-prox-")) {
                                    Utl.alerta("Realizado, afetando " + conexao.opera(comando) + " registros.");
                                } else {
                                    String cmds[] = comando.split("-prox-");
                                    TexEtf resultados = new TexEtf();
                                    resultados.mostra();
                                    String retornos = "";
                                    for (String cmd : cmds) {
                                        cmd = cmd.trim();
                                        if (!cmd.isEmpty()) {
                                            retornos = UtlTex.soma(retornos, "Executando Comando:");
                                            retornos = UtlTex.soma(retornos, cmd);
                                            try {
                                                int afetados = conexao.opera(cmd);
                                                retornos = UtlTex.soma(retornos, "Resultado:");
                                                retornos = UtlTex.soma(retornos, "Realizado, afetando " + afetados + " registros.");
                                            } catch (Exception ex) {
                                                retornos = UtlTex.soma(retornos, "Resultado:");
                                                retornos = UtlTex.soma(retornos, "Erro: " + ex.getMessage());
                                            }
                                            retornos = UtlTex.soma(retornos, "");
                                            resultados.edt().mdVlr(retornos);
                                        }
                                    }
                                }
                            } catch (Exception ex) {
                                Utl.registra(ex);
                            } finally {
                                Utl.alertaPopMudar(idm, "Terminado");
                                UtlBin.esperaSegundos(3);
                                Utl.alertaPopFechar(idm);
                            }
                        }
                    }.start();
                } else {
                    Utl.alerta("Impossível Executar um Comando Vazio");
                }
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }

    }
}
