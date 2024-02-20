package pin.modamk;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import pin.libamk.Doca;
import pin.libamk.Intf;
import pin.libbas.Globais;
import pin.libjan.Janelas;
import pin.libjan.PopMenu;
import pin.libutl.Utl;
import pin.libutl.UtlBin;
import pin.libutl.UtlCrs;
import static pin.modamk.Recursos.criaMenu;
import pin.modinf.Conexao;

public class Recurso {

    private Conexao conexao;
    private String raiz;
    private String nome;
    private String icone;
    private Character atalho;
    private Class classe;
    private Object[] parametros;

    private Boolean principal;
    private Boolean habilitado;
    private Boolean sempreHabilitado;
    private Boolean soConectado;

    private List<Recurso> filhos;

    public Recurso(String comRaiz, String comNome, String eIcone, Character eAtalho, Class daClasse) {
        conexao = (Conexao) Globais.pega("mainConc");
        raiz = comRaiz;
        nome = comNome;
        icone = eIcone;
        atalho = eAtalho;
        classe = daClasse;
        parametros = null;
        principal = false;
        habilitado = true;
        sempreHabilitado = false;
        soConectado = true;
        filhos = new ArrayList<>();
    }

    public String pegaRaiz() {
        return raiz;
    }

    public Recurso botaRaiz(String aRaiz) {
        this.raiz = aRaiz;
        return this;
    }

    public String pegaNome() {
        return nome;
    }

    public Recurso botaNome(String oNome) {
        this.nome = oNome;
        return this;
    }

    public String pegaRaizENome() {
        return UtlCrs.soma(raiz, ".", nome);
    }

    public String pegaImgNome() {
        return icone;
    }

    public Recurso botaImgNome(String aImgNome) {
        this.icone = aImgNome;
        return this;
    }

    public Character pegaAtalho() {
        return atalho;
    }

    public Recurso botaAtalho(Character oAtalho) {
        this.atalho = oAtalho;
        return this;
    }

    public Class pegaClasse() {
        return classe;
    }

    public Recurso botaClasse(Class aClasse) {
        this.classe = aClasse;
        return this;
    }

    public Object[] pegaParams() {
        return parametros;
    }

    public Recurso botaParams(Object... osParametros) {
        this.parametros = osParametros;
        return this;
    }

    public Recurso botaPrincipal() {
        principal = true;
        soConectado = false;
        return this;
    }

    public Boolean ehPrincipal() {
        return principal;
    }

    public Recurso tiraSoConectado() {
        soConectado = false;
        return this;
    }

    public Recurso botaSoConectado() {
        soConectado = true;
        return this;
    }

    public Recurso tiraHabilitado() {
        if (!sempreHabilitado) {
            habilitado = false;
        }
        return this;
    }

    public Recurso botaHabilitado() {
        habilitado = true;
        return this;
    }

    public Recurso tiraSempreHabilitado() {
        sempreHabilitado = false;
        return this;
    }

    public Recurso botaSempreHabilitado() {
        sempreHabilitado = true;
        return this;
    }

    public Boolean estaDisponivel() {
        Boolean retorno = false;
        if (habilitado) {
            if (!soConectado) {
                retorno = true;
            } else if (conexao.estaConectado()) {
                retorno = true;
            }
        }
        return retorno;
    }

    public Boolean temFilhoDisponivel() {
        Boolean retorno = false;
        if (filhos != null) {
            for (Recurso ico : filhos) {
                if (ico.estaDisponivel()) {
                    retorno = true;
                    break;
                }
            }
        }
        return retorno;
    }

    public Recurso botaFilho(Recurso oFilho) {
        filhos.add(oFilho);
        return this;
    }

    public List<Recurso> filhos() {
        return filhos;
    }

    public Object executa() throws Exception {
        return executa(null, null, null, null);
    }

    public Object executa(Object[] comParametros) throws Exception {
        return executa(comParametros, null, null, null);
    }

    public Object executa(Component naOrigem) throws Exception {
        return executa(null, naOrigem, 0, naOrigem.getHeight());
    }

    public Object executa(Object[] comParametros, Component naOrigem) throws Exception {
        return executa(comParametros, naOrigem, 0, naOrigem.getHeight());
    }

    public Object executa(Component naOrigem, Integer naPosX, Integer ePosY) throws Exception {
        return executa(null, naOrigem, naPosX, ePosY);
    }

    public Object executa(Object[] comParametros, Component naOrigem, Integer naPosX, Integer ePosY) throws Exception {
        if (estaDisponivel()) {
            if (classe != null) {
                Object[] pars = parametros;
                if (comParametros != null) {
                    pars = comParametros;
                }
                Object novo = UtlBin.novo(classe, pars);
                if (novo instanceof JFrame) {
                    Janelas.mostra((JFrame) novo);
                } else if (novo instanceof JDialog) {
                    Janelas.mostra((JDialog) novo);
                } else if (novo instanceof Cadastro) {
                    ((Cadastro) novo).mudaIcone(icone);
                    ((Cadastro) novo).mostra();
                } else if (novo instanceof Rotina) {
                    ((Rotina) novo).mostra();
                } else if (novo instanceof Monitor) {
                    ((Monitor) novo).mostra();
                } else if (novo instanceof Doca) {
                    ((Doca) novo).mostra();
                } else if (novo instanceof Intf) {
                    ((Intf) novo).mostra();
                } else if (novo instanceof Relatorio) {
                    new Relatorios((Relatorio) novo).escolher();
                } else {
                    Utl.registra(new Exception("Tipo de Recurso Não Reconhecido"));
                }
                return novo;
            } else {
                PopMenu menu = new PopMenu();
                criaMenu(UtlCrs.soma(raiz, ".", nome), menu, filhos);
                menu.mostra(naOrigem, naPosX, ePosY);
                return menu;
            }
        }
        return null;
    }

    public Recurso abreOuDisponibiliza() throws Exception {
        return abreOuDisponibiliza(null, null, null, null);
    }

    public Recurso abreOuDisponibiliza(Object[] comParametros) throws Exception {
        return abreOuDisponibiliza(comParametros, null, null, null);
    }

    public Recurso abreOuDisponibiliza(Component naOrigem) throws Exception {
        return abreOuDisponibiliza(null, naOrigem, 0, naOrigem.getHeight());
    }

    public Recurso abreOuDisponibiliza(Object[] comParametros, Component naOrigem) throws Exception {
        return abreOuDisponibiliza(comParametros, naOrigem, 0, naOrigem.getHeight());
    }

    public Recurso abreOuDisponibiliza(Component comOrigem, Integer naPosX, Integer ePosY) throws Exception {
        return abreOuDisponibiliza(null, comOrigem, naPosX, ePosY);
    }

    public Recurso abreOuDisponibiliza(Object[] comParametros, Component comOrigem, Integer naPosX, Integer ePosY) throws Exception {
        if (estaDisponivel()) {
            executa(comParametros, comOrigem, naPosX, ePosY);
        } else {
            new DisponibilizarIntf(this, comParametros, comOrigem, naPosX, ePosY).mostra();
        }
        return this;
    }

}
