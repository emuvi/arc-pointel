package pin.libbas;

import java.util.Collection;
import java.util.Iterator;

public class Paralelo<T> extends Thread {

    private volatile MultiParalelo<T> multiplo;
    private volatile String nomeSimples;
    private volatile Integer indice;
    private volatile ParsParams<String, Object> parametros;
    private volatile ParsVals<T> valores;
    private volatile Retornos retornos;
    private volatile Boolean iniciado;
    private volatile Boolean errou;

    public Paralelo() {
        this(null, null, null, Thread.NORM_PRIORITY);
    }

    public Paralelo(String comNome) {
        this(null, null, comNome, Thread.NORM_PRIORITY);
    }

    public Paralelo(Integer noIndice) {
        this(null, noIndice, null, Thread.NORM_PRIORITY);
    }

    public Paralelo(String comNome, Integer ePrioridade) {
        this(null, null, comNome, ePrioridade);
    }

    public Paralelo(Integer noIndice, String comNome) {
        this(null, noIndice, comNome, Thread.NORM_PRIORITY);
    }

    public Paralelo(Integer noIndice, String comNome, Integer ePrioridade) {
        this(null, noIndice, comNome, ePrioridade);
    }

    public Paralelo(MultiParalelo<T> doMultiplo) {
        this(doMultiplo, null, null, Thread.NORM_PRIORITY);
    }

    public Paralelo(MultiParalelo<T> doMultiplo, String comNome) {
        this(doMultiplo, null, comNome, Thread.NORM_PRIORITY);
    }

    public Paralelo(MultiParalelo<T> doMultiplo, Integer noIndice) {
        this(doMultiplo, noIndice, null, Thread.NORM_PRIORITY);
    }

    public Paralelo(MultiParalelo<T> doMultiplo, Integer noIndice, String comNome) {
        this(doMultiplo, noIndice, comNome, Thread.NORM_PRIORITY);
    }

    public Paralelo(MultiParalelo<T> doMultiplo, String comNome, Integer ePrioridade) {
        this(doMultiplo, null, comNome, ePrioridade);
    }

    public Paralelo(MultiParalelo<T> doMultiplo, Integer noIndice, String comNome, Integer ePrioridade) {
        super();
        multiplo = doMultiplo;
        if (multiplo != null) {
            retornos = multiplo.retornos();
            parametros = multiplo.parametros();
            valores = multiplo.valores();
        } else {
            retornos = new Retornos();
            parametros = new ParsParams<>();
            valores = new ParsVals();
        }
        nomeSimples = comNome;
        indice = noIndice;
        preparaNomeCompleto();
        setPriority(ePrioridade);
        iniciado = false;
        errou = false;
        Paralelos.bota(this);
    }

    public MultiParalelo<T> pMulti() {
        return multiplo;
    }

    private void preparaNomeCompleto() {
        if (multiplo != null || nomeSimples != null || indice != null) {
            String nm = "";
            if (multiplo != null) {
                nm += multiplo.pegaNome();
            }
            if (nomeSimples != null) {
                nm += (nm.isEmpty() ? "" : " - ") + nomeSimples;
            }
            if (indice != null) {
                nm += (nm.isEmpty() ? "" : " - ") + indice;
            }
            setName(nm);
        }
    }

    public String pegaNomeSimples() {
        return nomeSimples;
    }

    public Paralelo botaNomeSimples(String oNome) {
        nomeSimples = oNome;
        preparaNomeCompleto();
        return this;
    }

    public Integer pegaIndice() {
        return indice;
    }

    public Paralelo botaIndice(Integer oIndice) {
        indice = oIndice;
        preparaNomeCompleto();
        return this;
    }

    public Paralelo botaParametro(String comNome, Object eValor) {
        if (parametros == null) {
            parametros = new ParsParams();
        }
        parametros.bota(comNome, eValor);
        return this;
    }

    public Object pegaParametro(String comNome) {
        return pegaParametro(comNome, null);
    }

    public Object pegaParametro(String comNome, Object ePadrao) {
        if (parametros == null) {
            return ePadrao;
        } else {
            return parametros.pega(comNome, ePadrao);
        }
    }

    public Boolean iniciado() {
        return iniciado;
    }

    public Boolean errou() {
        return errou;
    }

    public Paralelo botaPrioridade(Integer aPrioridade) {
        setPriority(aPrioridade);
        return this;
    }

    public Paralelo botaPrioridadeAlta() {
        setPriority(Thread.MAX_PRIORITY);
        return this;
    }

    public Paralelo botaPrioridadeMedia() {
        setPriority(Thread.NORM_PRIORITY);
        return this;
    }

    public Paralelo botaPrioridadeBaixa() {
        setPriority(Thread.MIN_PRIORITY);
        return this;
    }

    public Retornos retornos() {
        return retornos;
    }

    public Paralelo mudaRetornos(Retornos osRetornos) {
        retornos = osRetornos;
        return this;
    }

    public Paralelo mostrador() {
        mostrador(pegaNomeSimples());
        return this;
    }

    public Paralelo mostrador(String comTitulo) {
        retornos.mostrador(comTitulo);
        return this;
    }

    public Paralelo mostrador(String comTitulo, Boolean pausavel) {
        retornos.mostrador(comTitulo, pausavel);
        return this;
    }

    public Paralelo mostrador(String comTitulo, Boolean pausavel, Boolean paravel) {
        retornos.mostrador(comTitulo, pausavel, paravel);
        return this;
    }

    public Paralelo inicia() {
        start();
        return this;
    }

    @Override
    public synchronized void start() {
        iniciado = true;
        super.start();
    }

    public Paralelo espera() throws Exception {
        join();
        return this;
    }

    public ParsVals<T> valores() {
        return valores;
    }

    public Paralelo botaValores(ParsVals<T> osValores) {
        valores = osValores;
        return this;
    }

    public Paralelo botaPrimeiro(T oValor) {
        valores.addFirst(oValor);
        return this;
    }

    public Paralelo botaUltimo(T oValor) {
        valores.addLast(oValor);
        return this;
    }

    public Paralelo botaTodos(Collection<? extends T> osValores) {
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

    public Paralelo removePrimeiro() {
        valores.removeFirst();
        return this;
    }

    public Paralelo removeUltimo() {
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

    public Paralelo limpa() {
        valores.clear();
        return this;
    }

    public Paralelo tira(T oValor) {
        valores.remove(oValor);
        return this;
    }

    public Paralelo tiraSe(Analisado<T, Boolean> comAnalisador) throws Exception {
        final Iterator<T> iter = valores.iterator();
        while (iter.hasNext()) {
            if (comAnalisador.analisa(iter.next())) {
                iter.remove();
            }
        }
        return this;
    }

    public Paralelo tiraPrimeiro(T oValor) {
        valores.removeFirstOccurrence(oValor);
        return this;
    }

    public Paralelo tiraUltimo(T oValor) {
        valores.removeLastOccurrence(oValor);
        return this;
    }

    public Paralelo tiraTodos(Collection<T> osValores) {
        valores.removeAll(osValores);
        return this;
    }

    public Paralelo deixaTodos(Collection<T> osValores) {
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

    public void retorna(Object eValor) {
        Retornos.bota(retornos, getName(), eValor);
    }

    public void retorna(String comNome, Object eValor) {
        Retornos.bota(retornos, getName() + " - " + comNome, eValor);
    }

    public void retorna(Throwable oErro) {
        Retornos.bota(retornos, getName() + " - Erro", oErro);
        errou = true;
    }

    public void retorna(String comNome, Throwable oErro) {
        Retornos.bota(retornos, getName() + " - " + comNome, oErro);
    }

    public Object retornado(String comNome) {
        return Retornos.pega(retornos, getName() + " - " + comNome);
    }

    public Object retornado(String comNome, Object ePadrao) {
        return Retornos.pega(retornos, getName() + " - " + comNome, ePadrao);
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

}
