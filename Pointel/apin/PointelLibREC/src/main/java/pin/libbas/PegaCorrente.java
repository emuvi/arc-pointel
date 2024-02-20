package pin.libbas;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import pin.libutl.Utl;
import pin.libutl.UtlTex;

public class PegaCorrente extends Thread {

    private final InputStream corrente;
    private final Retornos retornos;
    private final Progresso progresso;
    private final String retornosNome;
    private final Boolean imprimir;
    private final Boolean registrar;
    private volatile Boolean parar;
    private final StringBuilder registrado;

    public PegaCorrente(InputStream daCorrente) {
        this(daCorrente, null, null, null, false, true);
    }

    public PegaCorrente(InputStream daCorrente, Boolean paraImprimir) {
        this(daCorrente, null, null, null, paraImprimir, true);
    }

    public PegaCorrente(InputStream daCorrente, Boolean paraImprimir, Boolean eRegistrar) {
        this(daCorrente, null, null, null, paraImprimir, eRegistrar);
    }

    public PegaCorrente(InputStream daCorrente, Retornos paraRetornos) {
        this(daCorrente, paraRetornos, null, null, false, true);
    }

    public PegaCorrente(InputStream daCorrente, Retornos paraRetornos, Boolean paraImprimir) {
        this(daCorrente, paraRetornos, null, null, paraImprimir, true);
    }

    public PegaCorrente(InputStream daCorrente, Retornos paraRetornos, Boolean paraImprimir, Boolean eRegistrar) {
        this(daCorrente, paraRetornos, null, null, paraImprimir, eRegistrar);
    }

    public PegaCorrente(InputStream daCorrente, Retornos paraRetornos, String comNome) {
        this(daCorrente, paraRetornos, null, comNome, false, true);
    }

    public PegaCorrente(InputStream daCorrente, Progresso paraProgresso) {
        this(daCorrente, null, paraProgresso, null, false, true);
    }

    public PegaCorrente(InputStream daCorrente, Progresso paraProgresso, Boolean paraImprimir) {
        this(daCorrente, null, paraProgresso, null, paraImprimir, true);
    }

    public PegaCorrente(InputStream daCorrente, Progresso paraProgresso, Boolean paraImprimir, Boolean eRegistrar) {
        this(daCorrente, null, paraProgresso, null, paraImprimir, eRegistrar);
    }

    public PegaCorrente(InputStream daCorrente, Retornos paraRetornos, Progresso eProgresso, String comNome, Boolean paraImprimir, Boolean eRegistrar) {
        super("Pega Corrente " + comNome);
        corrente = daCorrente;
        retornos = paraRetornos;
        progresso = eProgresso;
        retornosNome = comNome;
        imprimir = paraImprimir;
        registrar = eRegistrar;
        parar = false;
        registrado = new StringBuilder();
    }

    public PegaCorrente parar() {
        parar = true;
        return this;
    }

    public PegaCorrente inicia() {
        start();
        return this;
    }

    @Override
    public void run() {
        try {
            InputStreamReader isr = new InputStreamReader(corrente);
            BufferedReader br = new BufferedReader(isr);
            String linha;
            while ((linha = br.readLine()) != null) {
                if (registrar) {
                    registrado.append(linha);
                    registrado.append(UtlTex.quebra());
                }
                if (imprimir) {
                    Utl.imp(linha);
                }
                if (retornosNome != null) {
                    Retornos.bota(retornos, retornosNome, linha);
                } else {
                    Retornos.bota(retornos, linha);
                }
                if (progresso != null) {
                    progresso.bota(linha);
                }
                if (parar) {
                    break;
                }
            }
        } catch (Exception ex) {
            Retornos.bota(retornos, ex);
            Utl.registra(ex, retornos == null);
        }
    }

    public String registrado() {
        return registrado.toString();
    }
}
