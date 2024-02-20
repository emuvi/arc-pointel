package pin.libbas;

import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.Iterator;
import javax.swing.AbstractAction;
import pin.libutl.Utl;
import pin.libutl.UtlBin;

public class MultiParalelo<T> {

    private volatile String nome;
    private volatile Boolean iniciado;
    private volatile Retornos retornos;
    private volatile ParsParams<String, Object> parametros;
    private volatile ParsVals<T> valores;
    private volatile ParsLista<Paralelo<T>> carregadores;
    private volatile ParsLista<Paralelo<T>> executores;
    private volatile Boolean salvaRetornos;

    public MultiParalelo(String comNome) {
        this(comNome, null);
    }

    public MultiParalelo(String comNome, Retornos eRetornos) {
        nome = comNome;
        retornos = eRetornos;
        iniciado = false;
        parametros = new ParsParams();
        carregadores = new ParsLista<>();
        executores = new ParsLista<>();
        valores = new ParsVals<>();
        salvaRetornos = true;
    }

    public String pegaNome() {
        return nome;
    }

    public MultiParalelo botaNome(String oNome) {
        nome = oNome;
        return this;
    }

    public Retornos retornos() {
        return retornos;
    }

    public MultiParalelo botaRetornos() {
        if (retornos == null) {
            retornos = new Retornos();
        }
        return this;
    }

    public MultiParalelo botaRetornos(Retornos osRetornos) {
        retornos = osRetornos;
        return this;
    }

    public Progresso pMostrador() {
        if (retornos == null) {
            return null;
        }
        return retornos.pMostrador();
    }

    public MultiParalelo mostrador() {
        if (retornos == null) {
            retornos = new Retornos();
        }
        retornos.mostrador(nome);
        return this;
    }

    public MultiParalelo mostrador(Boolean pausavel) {
        if (retornos == null) {
            retornos = new Retornos();
        }
        retornos.mostrador(nome, pausavel);
        return this;
    }

    public MultiParalelo mostrador(Boolean pausavel, Boolean paravel) {
        if (retornos == null) {
            retornos = new Retornos();
        }
        retornos.mostrador(nome, pausavel, paravel);
        return this;
    }

    public MultiParalelo mostrador(String comTitulo) {
        if (retornos == null) {
            retornos = new Retornos();
        }
        retornos.mostrador(comTitulo);
        return this;
    }

    public MultiParalelo mostrador(String comTitulo, String eDescricao) {
        if (retornos == null) {
            retornos = new Retornos();
        }
        retornos.mostrador(comTitulo, eDescricao);
        return this;
    }

    public MultiParalelo mostrador(String comTitulo, Boolean pausavel) {
        if (retornos == null) {
            retornos = new Retornos();
        }
        retornos.mostrador(comTitulo, pausavel);
        return this;
    }

    public MultiParalelo mostrador(String comTitulo, String eDescricao, Boolean pausavel) {
        if (retornos == null) {
            retornos = new Retornos();
        }
        retornos.mostrador(comTitulo, eDescricao, pausavel);
        return this;
    }

    public MultiParalelo mostrador(String comTitulo, Boolean pausavel, Boolean paravel) {
        if (retornos == null) {
            retornos = new Retornos();
        }
        retornos.mostrador(comTitulo, pausavel, paravel);
        return this;
    }

    public MultiParalelo mostrador(String comTitulo, String eDescricao, Boolean pausavel, Boolean paravel) {
        if (retornos == null) {
            retornos = new Retornos();
        }
        retornos.mostrador(comTitulo, eDescricao, pausavel, paravel);
        return this;
    }

    public MultiParalelo botaSalvarRetornos() {
        salvaRetornos = true;
        return this;
    }

    public MultiParalelo tiraSalvarRetornos() {
        salvaRetornos = false;
        return this;
    }

    public MultiParalelo mudaSalvarRetornos(Boolean para) {
        salvaRetornos = para;
        return this;
    }

    public ParsParams<String, Object> parametros() {
        return parametros;
    }

    public MultiParalelo botaParametro(String comNome, Object eValor) {
        parametros.bota(comNome, eValor);
        return this;
    }

    public Object pParametro(String comNome) {
        return pParametro(comNome, null);
    }

    public Object pParametro(String comNome, Object ePadrao) {
        return parametros.pega(comNome, ePadrao);
    }

    public MultiParalelo botaCarregador(Paralelo<T> oCarregador) {
        if (oCarregador.pegaNomeSimples() == null) {
            oCarregador.botaNomeSimples("Carregador");
        }
        if (retornos != null) {
            oCarregador.mudaRetornos(retornos);
        }
        carregadores.add(oCarregador);
        return this;
    }

    public MultiParalelo botaExecutor(Paralelo<T> oExecutor) {
        if (oExecutor.pegaNomeSimples() == null) {
            oExecutor.botaNomeSimples("Executor");
        }
        if (retornos != null) {
            oExecutor.mudaRetornos(retornos);
        }
        executores.add(oExecutor);
        return this;
    }

    public Boolean iniciado() {
        return iniciado;
    }

    public void inicia() {
        inicia(300);
    }

    public void inicia(Integer demora) {
        if (carregadores.temValor()) {
            for (Paralelo par : carregadores) {
                par.inicia();
            }
            UtlBin.esperaMilis(demora);
        }
        for (Paralelo par : executores) {
            par.inicia();
        }
        botaAoTerminar(new ActAoTerminar());
        iniciado = true;
    }

    public MultiParalelo espera() {
        while (temVivo()) {
            UtlBin.esperaMilis(100);
        }
        return this;
    }

    public void iniciaEEspera() {
        inicia();
        espera();
    }

    public ParsVals<T> valores() {
        return valores;
    }

    public MultiParalelo botaValores(ParsVals<T> osValores) {
        valores = osValores;
        return this;
    }

    public MultiParalelo botaPrimeiro(T oValor) {
        valores.addFirst(oValor);
        return this;
    }

    public MultiParalelo botaUltimo(T oValor) {
        valores.addLast(oValor);
        return this;
    }

    public MultiParalelo botaTodos(Collection<? extends T> osValores) {
        valores.addAll(osValores);
        return this;
    }

    public T pegaPrimeiro() {
        return valores.getFirst();
    }

    public T pegaUltimo() {
        return valores.getLast();
    }

    public T tiraPrimeiro() {
        return valores.pollFirst();
    }

    public T tiraUltimo() {
        return valores.pollLast();
    }

    public MultiParalelo removePrimeiro() {
        valores.removeFirst();
        return this;
    }

    public MultiParalelo removeUltimo() {
        valores.removeLast();
        return this;
    }

    public Boolean temValor() {
        return !valores.isEmpty();
    }

    public Boolean vazio() {
        return valores.isEmpty();
    }

    public Integer tamanho() {
        return valores.size();
    }

    public Boolean tem(T oValor) {
        return valores.contains(oValor);
    }

    public Boolean temTodos(Collection<T> osValores) {
        return valores.containsAll(osValores);
    }

    public MultiParalelo limpa() {
        valores.clear();
        return this;
    }

    public MultiParalelo tira(T oValor) {
        valores.remove(oValor);
        return this;
    }

    public MultiParalelo tiraSe(Analisado<T, Boolean> comAnalisador) throws Exception {
        final Iterator<T> iter = valores.iterator();
        while (iter.hasNext()) {
            if (comAnalisador.analisa(iter.next())) {
                iter.remove();
            }
        }
        return this;
    }

    public MultiParalelo tiraPrimeiro(T oValor) {
        valores.removeFirstOccurrence(oValor);
        return this;
    }

    public MultiParalelo tiraUltimo(T oValor) {
        valores.removeLastOccurrence(oValor);
        return this;
    }

    public MultiParalelo tiraTodos(Collection<T> osValores) {
        valores.removeAll(osValores);
        return this;
    }

    public MultiParalelo deixaTodos(Collection<T> osValores) {
        valores.retainAll(osValores);
        return this;
    }

    public void iterage(Analisado<T, Boolean> comAnalisador) throws Exception {
        Iterator<T> iter = valores.iterator();
        while (iter.hasNext()) {
            T item = iter.next();
            if (!comAnalisador.analisa(item)) {
                break;
            }
        }
    }

    public void iterageSubindo(Analisado<T, Boolean> comAnalisador) throws Exception {
        Iterator<T> iter = valores.descendingIterator();
        while (iter.hasNext()) {
            T item = iter.next();
            if (!comAnalisador.analisa(item)) {
                break;
            }
        }
    }

    public ParsLista<Paralelo<T>> carregadores() {
        return carregadores;
    }

    public ParsLista<Paralelo<T>> executores() {
        return executores;
    }

    public Boolean temCarregador() {
        Boolean retorno = false;
        for (Paralelo par : carregadores) {
            if (par.isAlive()) {
                retorno = true;
                break;
            }
        }
        return retorno;
    }

    public Boolean temExecutor() {
        Boolean retorno = false;
        for (Paralelo par : executores) {
            if (par.isAlive()) {
                retorno = true;
                break;
            }
        }
        return retorno;
    }

    public Boolean temVivo() {
        return temCarregador() || temExecutor();
    }

    public Boolean pausarEdeveCarregadorTerminar() {
        return pausarEdeveParar();
    }

    public Boolean pausarEdeveExecutorTerminar() {
        if (pausarEdeveParar()) {
            return true;
        } else {
            return (!(temCarregador() || temValor()));
        }
    }

    public void botaAoTerminar(AbstractAction aAcao) {
        new Thread(nome + " - Verifica Término") {
            @Override
            public void run() {
                try {
                    while (temVivo()) {
                        UtlBin.esperaSegundo();
                    }
                    aAcao.actionPerformed(new ActionEvent(MultiParalelo.this, 0, "Terminado"));
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        }.start();
    }

    public static void botaAoTerminar(AbstractAction aAcao, MultiParalelo... dosMultiplos) {
        new Thread("Verifica Término de Múltiplos") {
            @Override
            public void run() {
                try {
                    while (true) {
                        UtlBin.esperaSegundo();
                        Boolean temVivo = false;
                        for (MultiParalelo mlt : dosMultiplos) {
                            if (mlt.temVivo()) {
                                temVivo = true;
                                break;
                            }
                        }
                        if (!temVivo) {
                            break;
                        }
                    }
                    aAcao.actionPerformed(new ActionEvent(dosMultiplos, 0, "Terminou"));
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        }.start();
    }

    public void retorna(Object eValor) {
        Retornos.bota(retornos, nome, eValor);
    }

    public void retorna(String comNome, Object eValor) {
        Retornos.bota(retornos, nome + " - " + comNome, eValor);
    }

    public void retorna(Throwable oErro) {
        Retornos.bota(retornos, nome + " - Erro", oErro);
    }

    public void retorna(String comNome, Throwable oErro) {
        Retornos.bota(retornos, nome + " - " + comNome, oErro);
    }

    public Object retornado(String comNome) {
        return Retornos.pega(retornos, nome + " - " + comNome);
    }

    public Object retornado(String comNome, Object ePadrao) {
        return Retornos.pega(retornos, nome + " - " + comNome, ePadrao);
    }

    public void aumenta() {
        Retornos.aumenta(retornos);
    }

    public void aumenta(Integer posicoes) {
        Retornos.aumenta(retornos, posicoes);
    }

    public void mudaProgresso(Long oTamanho) {
        Retornos.mudaProgresso(retornos, oTamanho);
    }

    public void mudaProgresso(Integer oTamanho) {
        Retornos.mudaProgresso(retornos, oTamanho);
    }

    public void mudaTamanho(Long oTamanho) {
        Retornos.mudaTamanho(retornos, oTamanho);
    }

    public void mudaTamanho(Integer oTamanho) {
        Retornos.mudaTamanho(retornos, oTamanho);
    }

    public void avanca() {
        Retornos.avanca(retornos);
    }

    public void avanca(String comMensagem) {
        Retornos.avanca(retornos, comMensagem);
    }

    public void mudaPosicao(Integer naPosicao) {
        Retornos.mudaPosicao(retornos, naPosicao);
    }

    public void criaAutoCorrer() {
        Retornos.criaAutoCorrer(retornos);
    }

    public void pausar() {
        Retornos.pausar(retornos);
    }

    public Boolean devePausar() {
        return Retornos.devePausar(retornos);
    }

    public Boolean deveParar() {
        return Retornos.deveParar(retornos);
    }

    public Boolean pausarEdeveParar() {
        return Retornos.pausarEdeveParar(retornos);
    }

    public Boolean avancaPausarEdeveParar() {
        return Retornos.avancaPausarEdeveParar(retornos);
    }

    public Boolean avancaPausarEdeveParar(String comMensagem) {
        return Retornos.avancaPausarEdeveParar(retornos, comMensagem);
    }

    public void termina() {
        Retornos.termina(retornos);
    }

    public void fechar() {
        Retornos.fechar(retornos);
    }

    private class ActAoTerminar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            termina();
            if (salvaRetornos) {
                try {
                    Retornos.salvaEmRetsDir(retornos, nome);
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        }
    }
}
