package pin.jrb;

import pin.Pointel;
import pin.libutl.Utl;
import pin.libutl.UtlBin;
import pin.libutl.UtlCrs;
import pin.modinf.Conexao;

public class Jarbotico {

    private volatile Boolean logado;
    private volatile Conexao conexao;
    private volatile String interlocutor;
    private volatile Conversa conversa;
    private volatile Entendidos entendidos;
    private volatile Servicos servicos;
    private volatile ThdConversa thdConversa;
    private volatile ThdServicos thdServicos;
    private volatile Boolean iniciado;
    private volatile Boolean fechar;
    private volatile Boolean fechadoConversa;
    private volatile Boolean fechadoServicos;

    public Jarbotico() throws Exception {
        iniciado = false;
        logado = false;
        fechar = false;
        fechadoConversa = false;
        fechadoServicos = false;
        conexao = new Conexao("ConJarbs");
    }

    public Conexao conexao() {
        return conexao;
    }

    public String pegaInterlocutor() {
        return interlocutor;
    }

    public Jarbotico mudaInterlocutor(String para) {
        interlocutor = para;
        return this;
    }

    public Conversa conversa() {
        return conversa;
    }

    public Entendidos entendidos() {
        return entendidos;
    }

    public Servicos servicos() {
        return servicos;
    }

    public Boolean estaLogado() {
        return logado;
    }

    public Jarbotico botaLogado() {
        logado = true;
        return this;
    }

    public Jarbotico tiraLogado() {
        logado = true;
        return this;
    }

    public Boolean confereLogado() {
        if (!estaLogado()) {
            try {
                String atual = Pointel.mainConf.pegaCrsCrpt("PointelJarbs - Senha", "jarbozo");
                if (UtlCrs.confereSenhaRepete(atual, "Senha do PointelJarbs")) {
                    botaLogado();
                }
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
        return estaLogado();
    }

    public Jarbotico inicia() {
        if (!iniciado) {
            iniciado = true;
            new Thread("PointelJarbs Inicio") {
                @Override
                public void run() {
                    try {
                        interlocutor = Pointel.mainConf.pegaCrs("PointelJarbs - Interlocutor", "Usuário");
                        conversa = new Conversa();
                        entendidos = new Entendidos(Pointel.caminhoInsta);
                        servicos = new Servicos(Pointel.caminhoInsta);
                        thdConversa = new ThdConversa();
                        thdConversa.start();
                        thdServicos = new ThdServicos();
                        thdServicos.start();
                    } catch (Exception ex) {
                        Utl.registra(ex);
                        fechadoConversa = true;
                        fechadoServicos = true;
                    }
                }
            }.start();
        }
        return this;
    }

    private class ThdConversa extends Thread {

        public ThdConversa() {
            super("PointelJarbs Conversa");
        }

        @Override
        public void run() {
            try {
                while (!fechar) {
                    UtlBin.esperaSegundo();
                    if (Pointel.habilitaJarbs) {
                        conversa.executa();
                    }
                }
            } catch (Exception ex) {
                Utl.registra(ex);
            } finally {
                fechadoConversa = true;
            }
        }
    }

    private class ThdServicos extends Thread {

        public ThdServicos() {
            super("PointelJarbs Serviços");
        }

        @Override
        public void run() {
            try {
                while (!fechar) {
                    UtlBin.esperaSegundo();
                    if (Pointel.habilitaJarbs) {
                        servicos.executa();
                    }
                }
            } catch (Exception ex) {
                Utl.registra(ex);
            } finally {
                fechadoServicos = true;
            }
        }
    }

    public void fecha() {
        fechar = true;
        try {
            thdConversa.interrupt();
        } catch (Exception ex) {
        }
        try {
            thdServicos.interrupt();
        } catch (Exception ex) {
        }
        while (!fechadoConversa || !fechadoServicos) {
            UtlBin.esperaMilis(100);
        }
        conversa.fecha();
        entendidos.fecha();
        servicos.fecha();
        conexao.desconecta();
    }

}
