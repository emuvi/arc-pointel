package pin.modamk;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import pin.libbas.Configs;
import pin.libbas.Globais;
import pin.libjan.Janelas;
import pin.libjan.PopMenu;
import pin.libout.GBCWrap;
import pin.libutl.Utl;
import pin.libutl.UtlCrs;
import pin.modpim.Pims;

public class Recursos extends ArrayList<Recurso> {

    private Configs configs;

    public Recursos() {
        super();
        configs = (Configs) Globais.pega("mainConf");
    }

    public Recurso bota(String comRaiz, String eNome) {
        return bota(comRaiz, eNome, null, null, null);
    }

    public Recurso bota(String comRaiz, String eNome, Class daClasse) {
        return bota(comRaiz, eNome, null, null, daClasse);
    }

    public Recurso bota(String comRaiz, String eNome, String eImgNome) {
        return bota(comRaiz, eNome, eImgNome, null, null);
    }

    public Recurso bota(String comRaiz, String eNome, Character comAtalho) {
        return bota(comRaiz, eNome, null, comAtalho, null);
    }

    public Recurso bota(String comRaiz, String eNome, String eImgNome, Character comAtalho) {
        return bota(comRaiz, eNome, eImgNome, comAtalho, null);
    }

    public Recurso bota(String comRaiz, String eNome, String eImgNome, Class daClasse) {
        return bota(comRaiz, eNome, eImgNome, null, daClasse);
    }

    public Recurso bota(String comRaiz, String eNome, Character comAtalho, Class daClasse) {
        return bota(comRaiz, eNome, null, comAtalho, daClasse);
    }

    public Recurso bota(String comRaiz, String eNome, String eImgNome, Character comAtalho, Class daClasse) {
        Recurso retorno = null;
        try {
            if (comRaiz == null) {
                retorno = new Recurso(comRaiz, eNome, eImgNome, comAtalho, daClasse);
                add(retorno);
            } else {
                Recurso noIcone = procura(this, comRaiz);
                if (noIcone != null) {
                    retorno = new Recurso(comRaiz, eNome, eImgNome, comAtalho, daClasse);
                    noIcone.botaFilho(retorno);
                } else {
                    throw new Exception("Não foi possível achar a raiz: " + comRaiz + "\n\nPara o icone:" + eNome);
                }
            }
        } catch (Exception ex) {
            Utl.registra(ex);
        }
        return retorno;
    }

    public Recurso procura(List<Recurso> naLista, String aRaiz) {
        Recurso retorno = null;
        if (aRaiz.contains(".")) {
            List<Recurso> apr = naLista;
            String[] rqb = aRaiz.split("\\.");
            for (String qbr : rqb) {
                retorno = procura(apr, qbr);
                if (retorno != null) {
                    apr = retorno.filhos();
                } else {
                    break;
                }
            }
        } else {
            if (naLista != null) {
                for (Recurso ico : naLista) {
                    if (Objects.equals(ico.pegaNome(), aRaiz)) {
                        retorno = ico;
                        break;
                    }
                }
            }
        }
        return retorno;
    }

    public Recurso pega(String daRaiz, String eNome) {
        Recurso retorno = null;
        List<Recurso> ondeProcurar = this;

        if (daRaiz != null) {
            if (!daRaiz.isEmpty()) {
                Recurso icn = procura(this, daRaiz);
                if (icn != null) {
                    ondeProcurar = icn.filhos();
                }
            }
        }

        if (ondeProcurar != null) {
            for (Recurso icn : ondeProcurar) {
                if (Objects.equals(icn.pegaNome(), eNome)) {
                    retorno = icn;
                    break;
                }
            }
        }

        return retorno;
    }

    public List<Recurso> pega(String daRaiz) {
        List<Recurso> retorno = this;
        if (daRaiz != null) {
            if (!daRaiz.isEmpty()) {
                Recurso icn = procura(this, daRaiz);
                if (icn != null) {
                    retorno = icn.filhos();
                }
            }
        }
        return retorno;
    }

    public Boolean estaDisponivel(String daRaizENome) {
        String raiz = null;
        String nome = daRaizENome;
        if (nome.contains(".")) {
            int lp = nome.lastIndexOf(".");
            raiz = nome.substring(0, lp);
            nome = nome.substring(lp + 1);
        }
        return estaDisponivel(raiz, nome);
    }

    public Boolean estaDisponivel(String daRaiz, String eNome) {
        Recurso rec = pega(daRaiz, eNome);
        if (rec != null) {
            return rec.estaDisponivel();
        } else {
            return false;
        }
    }

    public void botaSuperUsuario() {
        botaSuperUsuario(this);
    }

    private void botaSuperUsuario(List<Recurso> naLista) {
        if (naLista != null) {
            for (Recurso icn : naLista) {
                icn.botaHabilitado();
                botaSuperUsuario(icn.filhos());
            }
        }
    }

    public void tiraSuperUsuario() {
        tiraSuperUsuario(this);
    }

    private void tiraSuperUsuario(List<Recurso> naLista) {
        if (naLista != null) {
            for (Recurso icn : naLista) {
                if (!icn.ehPrincipal()) {
                    icn.tiraHabilitado();
                }
                tiraSuperUsuario(icn.filhos());
            }
        }
    }

    public List<String> pega() {
        List<String> retorno = new ArrayList<>();
        insere(retorno, this);
        return retorno;
    }

    private void insere(List<String> naLista, List<Recurso> osRecursos) {
        if (naLista != null) {
            if (osRecursos != null) {
                for (Recurso icn : osRecursos) {
                    naLista.add(UtlCrs.junta(icn.pegaRaiz(), icn.pegaNome(), "."));
                    insere(naLista, icn.filhos());
                }
            }
        }
    }

    public void habilita(String aRaizENome) {
        if (aRaizENome != null) {
            if (aRaizENome.contains(".")) {
                String raiz = null;
                String nomes[] = aRaizENome.split("\\.");
                for (int in = 0; in < nomes.length; in++) {
                    Recurso icn = pega(raiz, nomes[in]);
                    if (icn != null) {
                        icn.botaHabilitado();
                    }
                    raiz = UtlCrs.junta(raiz, nomes[in], ".");
                }
            } else {
                Recurso icn = pega(null, aRaizENome);
                if (icn != null) {
                    icn.botaHabilitado();
                }
            }
        }
    }

    public PopMenu criaMenu(String daRaiz, JFrame naJanela) {
        PopMenu menu = new PopMenu();
        criaMenu(daRaiz, naJanela, menu);
        return menu;
    }

    public Recursos criaMenu(String daRaiz, JFrame naJanela, PopMenu comMenu) {
        List<Recurso> dosRecursos = pega(daRaiz);
        List<String> atalhos = new ArrayList<>();
        JPanel jpnJanela = new JPanel();
        jpnJanela.setLayout(new GridBagLayout());
        jpnJanela.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        naJanela.setContentPane(jpnJanela);
        int ilin = 0;
        int icol = 0;
        JPanel jpnLinha = null;
        if (dosRecursos != null) {
            for (Recurso icn : dosRecursos) {
                if (jpnLinha == null) {
                    jpnLinha = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 4));
                    jpnLinha.setAlignmentX(JComponent.LEFT_ALIGNMENT);
                }
                JButton jbtMenu = new JButton();
                Dimension dim = new Dimension(140, 100);
                jbtMenu.setSize(dim);
                jbtMenu.setPreferredSize(dim);
                jbtMenu.setMinimumSize(dim);
                jbtMenu.setMaximumSize(dim);
                if (!UtlCrs.vazio(icn.pegaImgNome())) {
                    try {
                        Pims.botaPim(jbtMenu, icn.pegaImgNome(), icn.pegaNome());
                    } catch (Exception ex) {
                        Utl.registra(ex);
                    }
                } else {
                    jbtMenu.setText(icn.pegaNome());
                }
                AbstractAction actMenu = new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            icn.executa(jbtMenu);
                        } catch (Exception ex) {
                            Utl.registra(ex);
                        }
                    }
                };

                jbtMenu.addActionListener(actMenu);
                if (comMenu != null) {
                    String atalhado = "";
                    Character atalho = icn.pegaAtalho();
                    if (atalho == null) {
                        int iac = 0;
                        String preat = "";
                        atalhado = preat + icn.pegaNome().toUpperCase().charAt(iac);
                        iac++;
                        while (atalhos.contains(atalhado)) {
                            if (iac >= icn.pegaImgNome().length()) {
                                iac = 0;
                                if (preat.isEmpty()) {
                                    preat = "control ";
                                } else {
                                    preat = "control alt ";
                                }
                            }
                            atalhado = preat + icn.pegaNome().toUpperCase().charAt(iac);
                            iac++;
                        }
                    } else {
                        atalhado = atalho + "";
                    }

                    Janelas.botaAtalhoEMenu(naJanela, comMenu, icn.pegaRaiz() + "." + icn.pegaNome(), icn.pegaNome(), atalhado, actMenu);
                    atalhos.add(atalhado);
                }

                jpnLinha.add(jbtMenu);
                icol++;
                if (icol >= 4) {
                    jpnJanela.add(jpnLinha, new GBCWrap(0, ilin).setAnchor(GridBagConstraints.FIRST_LINE_START).setFill(GridBagConstraints.NONE).setWeight(0, 0));
                    ilin++;
                    icol = 0;
                    jpnLinha = null;
                }
            }

            if (jpnLinha != null) {
                jpnJanela.add(jpnLinha, new GBCWrap(0, ilin).setAnchor(GridBagConstraints.FIRST_LINE_START).setFill(GridBagConstraints.NONE).setWeight(0, 0));
                ilin++;
            }
        }
        return this;
    }

    public static void criaMenu(String aPartirRaiz, PopMenu noMenu, List<Recurso> osRecursos) {
        for (Recurso icn : osRecursos) {
            if (icn.estaDisponivel()) {
                ImageIcon icone = null;
                if (icn.pegaImgNome() != null) {
                    icone = Pims.pega(icn.pegaImgNome());
                }
                Character atalho = icn.pegaAtalho();

                AbstractAction acao = null;
                if (icn.pegaClasse() != null) {
                    acao = new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                icn.executa();
                            } catch (Exception ex) {
                                Utl.registra(ex);
                            }
                        }
                    };
                }
                String rz = UtlCrs.removeDoInicio(icn.pegaRaiz(), aPartirRaiz);
                if (rz != null) {
                    if (rz.startsWith(".")) {
                        rz = rz.substring(1);
                    }
                }
                noMenu.bota(rz, icn.pegaNome(), atalho, icone, acao);
                if (icn.pegaClasse() == null) {
                    criaMenu(aPartirRaiz, noMenu, icn.filhos());
                }
            }
        }
    }

    public Boolean verificaRestricao(JFrame daJanela, String comTitulo) throws Exception {
        String restringida = configs.pegaCrs("Pointel - Restringida", "");
        if (!restringida.isEmpty()) {
            if (!Objects.equals(restringida, comTitulo)) {
                Janelas.fecha(daJanela);
                pega("Pointel", restringida).executa();
                return false;
            }
        }
        return true;
    }
}
