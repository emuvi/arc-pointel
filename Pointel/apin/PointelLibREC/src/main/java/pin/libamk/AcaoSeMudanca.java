package pin.libamk;

import pin.libutl.Utl;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;
import javax.swing.JComponent;

public class AcaoSeMudanca extends AcaoSe<PropertyChangeEvent> {

    private Class classe;
    private Object origem;
    private String nome;
    private String nomeIniciando;
    private String nomeContendo;
    private String nomeTerminando;
    private Boolean seAntigo;
    private Object vlrAntigo;
    private Boolean seNovo;
    private Object vlrNovo;
    private Escutador escutador;

    public AcaoSeMudanca() {
        classe = null;
        origem = null;
        nome = null;
        nomeContendo = null;
        seAntigo = false;
        vlrAntigo = null;
        seNovo = false;
        vlrNovo = null;
        escutador = null;
    }

    public AcaoSeMudanca seClasse(Class daClasse) {
        classe = daClasse;
        return this;
    }

    public AcaoSeMudanca tiraClasse() {
        classe = null;
        return this;
    }

    public AcaoSeMudanca seOrigem(Object aOrigem) {
        origem = aOrigem;
        return this;
    }

    public AcaoSeMudanca tiraOrigem() {
        origem = null;
        return this;
    }

    public AcaoSeMudanca seNome(String oNome) {
        nome = oNome;
        return this;
    }

    public AcaoSeMudanca tiraNome() {
        nome = null;
        return this;
    }

    public AcaoSeMudanca seNomeIniciando(String comNome) {
        nomeIniciando = comNome;
        return this;
    }

    public AcaoSeMudanca tiraNomeIniciando() {
        nomeIniciando = null;
        return this;
    }

    public AcaoSeMudanca seNomeContendo(String oNome) {
        nomeContendo = oNome;
        return this;
    }

    public AcaoSeMudanca tiraNomeContendo() {
        nomeContendo = null;
        return this;
    }

    public AcaoSeMudanca seNomeTerminado(String comNome) {
        nomeTerminando = comNome;
        return this;
    }

    public AcaoSeMudanca tiraNomeTerminando() {
        nomeTerminando = null;
        return this;
    }

    public AcaoSeMudanca seVlrAntigo(Object oValor) {
        seAntigo = true;
        vlrAntigo = oValor;
        return this;
    }

    public AcaoSeMudanca tiraVlrAntigo() {
        seAntigo = false;
        vlrAntigo = null;
        return this;
    }

    public AcaoSeMudanca seVlrNovo(Object oValor) {
        seNovo = true;
        vlrNovo = oValor;
        return this;
    }

    public AcaoSeMudanca tiraVlrNovo() {
        seNovo = false;
        vlrNovo = null;
        return this;
    }

    @Override
    public void prepara() {
    }

    @Override
    public AcaoSeMudanca inicia(JComponent... osComponentes) {
        if (escutador == null) {
            escutador = new Escutador();
            prepara();
        }
        if (osComponentes != null) {
            for (JComponent oComponente : osComponentes) {
                oComponente.addPropertyChangeListener(escutador);
            }
        }
        return this;
    }

    @Override
    public Boolean deveExecutar(PropertyChangeEvent comMudanca) {
        if (classe != null) {
            if (!classe.isInstance(comMudanca.getSource())) {
                return false;
            }
        }
        if (origem != null) {
            if (!Objects.equals(origem, comMudanca.getSource())) {
                return false;
            }
        }
        if (nome != null) {
            if (!Objects.equals(nome, comMudanca.getPropertyName())) {
                return false;
            }
        }
        if (nomeIniciando != null) {
            if (!comMudanca.getPropertyName().startsWith(nomeIniciando)) {
                return false;
            }
        }
        if (nomeContendo != null) {
            if (!comMudanca.getPropertyName().contains(nomeContendo)) {
                return false;
            }
        }
        if (nomeTerminando != null) {
            if (!comMudanca.getPropertyName().endsWith(nomeTerminando)) {
                return false;
            }
        }
        if (seAntigo) {
            if (!Objects.equals(vlrAntigo, comMudanca.getOldValue())) {
                return false;
            }
        }
        if (seNovo) {
            if (!Objects.equals(vlrNovo, comMudanca.getNewValue())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void executa(PropertyChangeEvent comMudanca) {
    }

    private class Escutador implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            Utl.imp(evt.getPropertyName());
            if (deveExecutar(evt)) {
                executa(evt);
            }
        }

    }

}
