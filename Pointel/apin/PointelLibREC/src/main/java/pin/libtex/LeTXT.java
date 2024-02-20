package pin.libtex;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.AbstractAction;
import pin.libbas.Erro;
import pin.libutl.UtlCrs;
import pin.libbas.Analisado;

public class LeTXT {

    public static enum Tp {
        Linhas, Marcado
    };

    private File arquivo;
    private String codificacao;
    private Tp tipo;
    private List<LeTXTCmp> campos;
    private Analisado<String[], Boolean> analisador;
    private AbstractAction aoInterromper;
    private AbstractAction aoTerminar;

    private LeitorTXT leitor;

    public LeTXT(File arquivo, LeTXTCmp... campos) {
        this(arquivo, "UTF8", Tp.Linhas, campos);
    }

    public LeTXT(File arquivo, Tp tipo, LeTXTCmp... campos) {
        this(arquivo, "UTF8", tipo, campos);
    }

    public LeTXT(File arquivo, String codificacao, LeTXTCmp... campos) {
        this(arquivo, codificacao, Tp.Linhas, campos);
    }

    public LeTXT(File arquivo, String codificacao, Tp tipo, LeTXTCmp... campos) {
        this.arquivo = arquivo;
        this.codificacao = codificacao;
        this.tipo = tipo;
        this.campos = Arrays.asList(campos);
        this.analisador = null;
    }

    public Analisado<String[], Boolean> analisador() {
        return analisador;
    }

    public LeTXT botaAnalisador(Analisado<String[], Boolean> analisador) {
        this.analisador = analisador;
        return this;
    }

    public AbstractAction aoTerminar() {
        return aoTerminar;
    }

    public LeTXT botaAoTerminar(AbstractAction aoTerminar) {
        this.aoTerminar = aoTerminar;
        return this;
    }

    public AbstractAction aoInterromper() {
        return aoInterromper;
    }

    public LeTXT botaAoInterromper(AbstractAction aoInterromper) {
        this.aoInterromper = aoInterromper;
        return this;
    }

    public LeitorTXT leitor() {
        return leitor;
    }

    public Boolean inicia() throws Exception {
        Boolean retorno = true;
        leitor = new LeitorTXT(arquivo, codificacao);
        if (tipo.equals(Tp.Linhas)) {
            while (!leitor.terminou()) {
                boolean achouFim = false;
                String[] valores = new String[campos.size()];
                List<String> linhas = new ArrayList<>();
                for (int ic = 0; ic < campos.size(); ic++) {
                    LeTXTCmp campo = campos.get(ic);
                    if (ic == 0) {
                        campo.inicio = 0;
                    } else {
                        LeTXTCmp cmpAnt = campos.get(ic - 1);
                        if (cmpAnt.tipo == LeTXTCmp.Tp.Fixo) {
                            campo.inicio = cmpAnt.fim;
                        } else {
                            campo.inicio = cmpAnt.fim + cmpAnt.separador.length();
                        }
                    }

                    while (linhas.size() < campo.ordem) {
                        String linha = leitor.leLinha();
                        if (linha == null) {
                            achouFim = true;
                            break;
                        } else {
                            linhas.add(linha);
                        }
                    }
                    if (achouFim) {
                        break;
                    }
                    String lnCampo = linhas.get(campo.ordem - 1);
                    String valCampo = null;
                    if (lnCampo != null) {
                        if (!campo.ateOFim) {
                            switch (campo.tipo) {
                                case Fixo:
                                    campo.fim = campo.inicio + campo.tamanho;
                                    break;
                                case Separado:
                                    int ini = campo.inicio;
                                    campo.fim = -1;
                                    while (campo.fim == -1) {
                                        int idx = lnCampo.indexOf(campo.separador, ini);
                                        if (idx == -1) {
                                            campo.fim = lnCampo.length();
                                        } else {
                                            if (campo.auxiliar != null) {
                                                if (idx > 0) {
                                                    if (lnCampo.charAt(idx - 1) != campo.auxiliar) {
                                                        campo.fim = idx;
                                                    } else {
                                                        ini = idx + 1;
                                                    }
                                                }
                                            } else {
                                                campo.fim = idx;
                                            }
                                        }
                                    }
                                    break;
                                case Marcado:
                                    String fmr = "</" + campo.marcacao + ">";
                                    campo.fim = lnCampo.indexOf(fmr, campo.inicio);
                                    if (campo.fim > -1) {
                                        campo.fim += fmr.length();
                                    } else {
                                        campo.fim = campo.inicio;
                                    }
                                    break;
                            }
                        } else {
                            campo.fim = lnCampo.length();
                        }
                        valCampo = UtlCrs.pParte(lnCampo, campo.inicio, campo.fim);
                        if (campo.tipo.equals(LeTXTCmp.Tp.Marcado)) {
                            valCampo = Marcados.preparado(valCampo, campo.marcacao);
                        }
                        if (campo.entreAspas) {
                            if (campo.aspasSimples) {
                                valCampo = UtlCrs.pEntreAspasSimples(valCampo);
                            } else {
                                valCampo = UtlCrs.pEntreAspas(valCampo);
                            }
                        }
                    }
                    valores[ic] = valCampo;
                }
                if (achouFim) {
                    break;
                }
                retorno = analisador.analisa(valores);
                if (!retorno) {
                    if (aoInterromper != null) {
                        aoInterromper.actionPerformed(null);
                    }
                    break;
                }
            }
        } else if (tipo.equals(Tp.Marcado)) {
            throw new Erro("Funcionalidade Ainda Não Está Pronta");
        }
        if (aoTerminar != null) {
            aoTerminar.actionPerformed(null);
        }
        return retorno;
    }
}
