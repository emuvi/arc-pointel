package pin;

import java.io.File;
import pin.libitr.Alerta;
import pin.libitr.AlertaEnvio;
import pin.libutl.Utl;
import pin.libutl.UtlBin;
import pin.libutl.UtlTex;

public class PrinciIntel {

    public static void inicia() {
        new ThdShowExec().start();
        Alerta.alertaEnvio = new PntAlertaEnvio();
        Runtime.getRuntime().addShutdownHook(new PntEncerramento());
    }

    private static class ThdShowExec extends Thread {

        public ThdShowExec() {
            super("Pointel Abridor");
        }

        @Override
        public void run() {
            setPriority(Thread.MIN_PRIORITY);
            File flShw = new File(Pointel.caminhoInsta + ".shw");
            Utl.imp("Pointel Show Sintonizado Em: " + flShw.getAbsolutePath());
            Utl.imp("Terminada Inicialização");
            while (true) {
                UtlBin.esperaSegundo();
                if (flShw.exists()) {
                    if (Pointel.mainIntel != null) {
                        Pointel.mainIntel.mostraPointel();
                        flShw.delete();
                    }
                }
            }
        }
    }

    private static class PntAlertaEnvio implements AlertaEnvio {

        @Override
        public void enviar(String aMensagem, String comOrigem) {
            String descricao = "";
            if (aMensagem != null) {
                if (!aMensagem.isEmpty()) {
                    descricao = "Mensagem: " + aMensagem;
                }
            }
            if (comOrigem != null) {
                if (!comOrigem.isEmpty()) {
                    if (!descricao.isEmpty()) {
                        descricao += UtlTex.quebra(3);
                    }
                    descricao += comOrigem;
                }
            }
            if (descricao.isEmpty()) {
                descricao = null;
            }
        }

    }

    private static class PntEncerramento extends Thread {

        public PntEncerramento() {
            super("PntEncerramento");
        }

        public void run() {
            try {
                Utl.imp("Gatilho de Encerramento");
                Pointel.mainIntel.encerrar();
            } catch (Exception ex) {
                Utl.registra(ex, false);
            }
        }
    }

}
