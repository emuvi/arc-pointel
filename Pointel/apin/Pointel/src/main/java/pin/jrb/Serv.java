package pin.jrb;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import pin.Pointel;
import pin.libbas.Retornos;
import pin.libutl.Utl;
import pin.libutl.UtlBin;
import pin.libutl.UtlMom;
import pin.libutl.UtlTex;
import pin.modrec.XMLReg;

public abstract class Serv {

    private final Servico.Tp tipo;
    public String nome;
    private Integer codigo;
    private Boolean registra;
    private Retornos retornos;
    private AbstractAction aoTerminar;

    public Serv(Servico.Tp tipo) {
        this.tipo = tipo;
        this.nome = null;
        this.codigo = null;
        this.registra = false;
        this.retornos = new Retornos();
        this.aoTerminar = null;
    }

    public static Serv descrito(String nosCaracteres) {
        return (Serv) XMLReg.novo()
                .omiti(Serv.class, "retornos")
                .omiti(Serv.class, "registra")
                .pValor(nosCaracteres);
    }

    public String descreve() {
        return XMLReg.novo()
                .omiti(Serv.class, "retornos")
                .omiti(Serv.class, "registra")
                .pReg(this);
    }

    public Servico.Tp pTipo() {
        return tipo;
    }

    public String pNome() {
        return nome;
    }

    public void mNome(String nome) {
        this.nome = nome;
    }

    public Integer pCodigo() {
        return codigo;
    }

    public Serv mCodigo(Integer codigo) {
        this.codigo = codigo;
        return this;
    }

    public Boolean pRegistra() {
        return registra;
    }

    public Serv mRegistra(Boolean registra) {
        this.registra = registra;
        return this;
    }

    public Serv botaRegistra() {
        this.registra = true;
        return this;
    }

    public Serv tiraRegistra() {
        this.registra = false;
        return this;
    }

    public Serv trocaRegistra() {
        this.registra = !this.registra;
        return this;
    }

    public Retornos pRetornos() {
        return retornos;
    }

    public Serv mRetornos(Retornos retornos) {
        this.retornos = retornos;
        return this;
    }

    public AbstractAction pAoTerminar() {
        return aoTerminar;
    }

    public Serv mAoTerminar(AbstractAction aoTerminar) {
        this.aoTerminar = aoTerminar;
        return this;
    }

    public abstract void executa() throws Exception;

    public Integer executado(String comRegistro) {
        return executado(codigo, comRegistro, null);
    }

    public Integer executado(String comRegistro, String eRetornosCam) {
        return executado(codigo, comRegistro, eRetornosCam);
    }

    public Integer executado(Integer doCodigo, String comRegistro, String eRetornosCam) {
        if (doCodigo != null) {
            try {
                return Pointel.mainJarbs.servicos().botaExecutado(codigo, comRegistro, eRetornosCam);
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
        return null;
    }

    public String paraSalvarRetornos() {
        return paraSalvarRetornos(pNome());
    }

    public String paraSalvarRetornos(String comNome) {
        String retorno = "Serv" + " - " + tipo;
        if (comNome != null) {
            if (!comNome.isEmpty()) {
                retorno += " - " + comNome;
            }
        }
        return retorno;
    }

    public static void inicia(Serv oServico) {
        new Thread("Serviço Inicia Execução") {
            @Override
            public void run() {
                try {
                    String registro = "";
                    if (oServico.pRegistra()) {
                        registro = "Iniciado em: " + UtlMom.pegaAtualParaMaquina() + UtlTex.quebra()
                                + "Executando Serviço do Tipo: " + oServico.pTipo().toString() + UtlTex.quebra()
                                + "Fonte do Serviço: " + UtlTex.quebra()
                                + "<inicio>" + UtlTex.quebra()
                                + oServico.descreve() + UtlTex.quebra()
                                + "<fim>" + UtlTex.quebra()
                                + "Resultado: " + UtlTex.quebra();
                    }
                    Exception erExc = null;
                    try {
                        oServico.executa();
                        if (oServico.pAoTerminar() != null) {
                            oServico.pAoTerminar().actionPerformed(new ActionEvent(oServico, 0, "aoTerminar"));
                        }
                        Retornos.termina(oServico.pRetornos());
                    } catch (Exception ex) {
                        Retornos.bota(oServico.pRetornos(), ex);
                        erExc = ex;
                    }
                    String retArq = "Sem Retornos";
                    if (oServico.pRetornos() != null) {
                        try {
                            retArq = oServico.pRetornos().salvaEmRetsDir(oServico.paraSalvarRetornos());
                        } catch (Exception ex) {
                            retArq = "Erro: " + ex.getMessage();
                        }
                    }
                    if (oServico.pRegistra()) {
                        if (erExc != null) {
                            registro += "Erro: " + erExc.getMessage() + UtlTex.quebra()
                                    + UtlBin.pegaOrigem(erExc);
                        } else {
                            registro += "Executado.";
                        }
                        registro += UtlTex.quebra() + "Retornos Salvo Em: " + retArq
                                + UtlTex.quebra() + "Terminado em: " + UtlMom.pegaAtualParaMaquina();
                        oServico.executado(registro, retArq);
                    }
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        }.start();
    }

}
