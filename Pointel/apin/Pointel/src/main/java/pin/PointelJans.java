package pin;

import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JFrame;
import pin.adp.AgendaDia;
import pin.adp.Arquivos;
import pin.adp.Comunicacao;
import pin.jrb.ConfigsIntf;
import pin.jrb.Falado;
import pin.libamk.JanAtalhosIntf;
import pin.libamk.JanelasIntf;
import pin.libjan.JanAtalhos;
import pin.libjan.JanEspeciais;
import pin.libjan.Janelas;
import pin.libitr.Questao;
import pin.libutl.Utl;
import pin.libutl.UtlCrs;
import pin.libutl.UtlTex;
import pin.modinf.ConexaoSenha;
import pin.modpim.Pims;

public class PointelJans implements JanEspeciais {

    @Override
    public void botaAtalhos(JFrame naJanela, Class daClasse) {

        Janelas.botaAtalho(naJanela, "Pointel", "control alt P", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pointel.mainIntel.mostraPointel();
            }
        });

        Janelas.botaAtalho(naJanela, "Pointel - Aplicação Central", "control alt A", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pacote = daClasse.getPackage().getName();
                switch (pacote) {
                    case "pin":
                        Pointel.mainIntel.mostraPointel();
                        break;
                    case "pin.adm":
                        Pointel.mainIntel.mostraPointelAdmin();
                        break;
                    case "pin.adp":
                        Pointel.mainIntel.mostraPointelAdPro();
                        break;
                    case "pin.edu":
                        Pointel.mainIntel.mostraPointelEduca();
                        break;
                    case "pin.bib":
                        Pointel.mainIntel.mostraPointelBiblio();
                        break;
                    case "pin.jrb":
                        Pointel.mainIntel.mostraPointelJarbs();
                        break;
                    case "pin.amk":
                        Pointel.mainIntel.mostraPointelAllMake();
                        break;
                    case "pin.amg":
                        Pointel.mainIntel.mostraPointelAllMagi();
                        break;
                    case "pin.prk":
                        Pointel.mainIntel.mostraPointelProk();
                        break;
                    case "pin.mgr":
                        Pointel.mainIntel.mostraPointelMigre();
                        break;
                    case "pin.inf":
                        Pointel.mainIntel.mostraPointelInfo();
                        break;
                    case "pin.app":
                        Pointel.mainIntel.mostraPointelApps();
                        break;
                }
            }
        });

        Janelas.botaAtalho(naJanela, "Pointel - Build Versão", "control alt B", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Utl.alerta("Pointel Build Versão " + Pointel.versao);
            }
        });

        Janelas.botaAtalho(naJanela, "Pointel - Java Versão", "control alt V", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Utl.alerta("Pointel Java Versão " + System.getProperty("java.version"));
            }
        });

        Janelas.botaAtalho(naJanela, "Pointel - Mesa", "F12", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pointel.mainMesa.setExtendedState(JFrame.NORMAL);
                Pointel.mainMesa.setVisible(true);
                Pointel.mainMesa.toFront();
                Pointel.mainMesa.requestFocus();
            }
        });

        Janelas.botaAtalho(naJanela, "Pointel - Restringida", "shift control alt R", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pointel.mainMesa.mudaRestricao();
            }
        });

        Janelas.botaAtalho(naJanela, "Pointel - Sair", "control alt Q", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Utl.continua("Fechar o Pointel e Todos os Processos Associados?")) {
                    Pointel.mainIntel.encerrar();
                }
            }
        });

        Janelas.botaAtalho(naJanela, "Pointel Janela - Fechar Todas as Outras", "control alt W", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Frame frm : JFrame.getFrames()) {
                    if (frm instanceof JFrame) {
                        JFrame jan = (JFrame) frm;
                        if (!jan.equals(naJanela)) {
                            Janelas.fecha(jan);
                        }
                    }
                }
            }
        });

        Janelas.botaAtalho(naJanela, "Pointel Janela - Muda Nome", "shift control F12", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pacote = Pacotes.pegaTituloPacote(daClasse);
                String novo = UtlCrs.pergunta("Novo Nome para Janela");
                if (novo != null) {
                    if (!novo.isEmpty()) {
                        naJanela.setTitle(pacote + " - " + novo);
                        Janelas.abrePosicionamento(naJanela, null, null, null, null);
                    }
                }
            }
        });

        Janelas.botaAtalho(naJanela, "Pointel Janela - Mostra Atalhos", "shift control alt F12", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JanAtalhos.mostraAtalhos(naJanela);
            }
        });

        Janelas.botaAtalho(naJanela, "Pointel Janela - Atalhos Intf", "shift control alt F11", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JanAtalhosIntf().mostra();
            }
        });

        Janelas.botaAtalho(naJanela, "Pointel JanelasIntf - Exibir", "control F12", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JanelasIntf().mostra();
            }
        });

        Janelas.botaAtalho(naJanela, "PointelAdPro - Arquivos", "F9", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Janelas.mostra(new Arquivos());
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        });

        Janelas.botaAtalho(naJanela, "PointelAdPro - AgendaDia", "F10", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new AgendaDia().mostra();
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        });

        Janelas.botaAtalho(naJanela, "PointelAdPro - Comunicação", "F11", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Janelas.mostra(new Comunicacao());
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        });

        Janelas.botaAtalho(naJanela, "PointelJarbs", "control alt J", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pointel.mainIntel.mostraPointelJarbs();
            }
        });

        Janelas.botaAtalho(naJanela, "PointelJarbs - Interação", "shift F1", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Questao("O que Você Gostaria de Dizer?", "PointelJarbs") {
                    @Override
                    public Boolean executa(String comResposta) {
                        Pointel.mainJarbs.conversa().bota(new Falado(comResposta));
                        return true;
                    }
                }.mostra();
            }
        });

        Janelas.botaAtalho(naJanela, "PointelJarbs - Importa Configs", "shift control alt X", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ConfigsIntf.importaNoMain();
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        });

        Janelas.botaAtalho(naJanela, "PointelHelp - Solicitar Suporte", "control F1", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pointel.mainIntel.mostraPointelHelp();
            }
        });

        Janelas.botaAtalho(naJanela, "PointelInfo - Desconectar", "control alt D", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Pointel.mainConc != null) {
                    try {
                        Pointel.mainConc.limpa();
                        Pointel.mainConc.conectaBase();
                    } catch (Exception ex) {
                        Utl.registra(ex);
                    }
                }
            }
        });

        Janelas.botaAtalho(naJanela, "PointelInfo - Trocar Senha", "control alt S", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Pointel.mainConc != null) {
                    try {
                        new ConexaoSenha(Pointel.mainConc).mostra();
                    } catch (Exception ex) {
                        Utl.registra(ex);
                    }
                }
            }
        });
    }

    @Override
    public void botaAtalhos(JDialog naJanela, Class daClasse) {
        Janelas.botaAtalho(naJanela, "Pointel - Sair", "control alt Q", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Utl.continua("Fechar o Pointel e Todos os Processos Associados?")) {
                    Pointel.mainIntel.encerrar();
                }
            }
        });
    }

    @Override
    public void botaIcone(JFrame naJanela, Class daClasse) {
        if (naJanela.getIconImage() == null) {
            try {
                String pacote = daClasse.getPackage().getName();
                switch (pacote) {
                    case "pin":
                        naJanela.setIconImage(Pims.pega("Pointel.png").getImage());
                        break;
                    case "pin.adm":
                        naJanela.setIconImage(Pims.pega("PointelAdmin.png").getImage());
                        break;
                    case "pin.adp":
                        naJanela.setIconImage(Pims.pega("PointelAdPro.png").getImage());
                        break;
                    case "pin.edu":
                        naJanela.setIconImage(Pims.pega("PointelEduca.png").getImage());
                        break;
                    case "pin.bib":
                        naJanela.setIconImage(Pims.pega("PointelBiblio.png").getImage());
                        break;
                    case "pin.hlp":
                        naJanela.setIconImage(Pims.pega("PointelSuporte.png").getImage());
                        break;
                    case "pin.jrb":
                        naJanela.setIconImage(Pims.pega("PointelJarbotico.png").getImage());
                        break;
                    case "pin.amk":
                        naJanela.setIconImage(Pims.pega("PointelAllMake.png").getImage());
                        break;
                    case "pin.amg":
                        naJanela.setIconImage(Pims.pega("PointelAllMagi.png").getImage());
                        break;
                    case "pin.prk":
                        naJanela.setIconImage(Pims.pega("PointelProk.png").getImage());
                        break;
                    case "pin.mgr":
                        naJanela.setIconImage(Pims.pega("PointelMigre.png").getImage());
                        break;
                    case "pin.inf":
                        naJanela.setIconImage(Pims.pega("PointelInfo.png").getImage());
                        break;
                    case "pin.app":
                        naJanela.setIconImage(Pims.pega("PointelApps.png").getImage());
                        break;
                    default:
                        naJanela.setIconImage(Pims.pega("Pointel.png").getImage());
                        break;
                }
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    @Override
    public void botaIcone(JDialog naJanela, Class daClasse) {
        Boolean deve = false;
        List<Image> imgs = naJanela.getIconImages();
        if (imgs == null) {
            deve = true;
        } else {
            deve = imgs.isEmpty();
        }
        if (deve) {
            try {
                String pacote = daClasse.getPackage().getName();
                switch (pacote) {
                    case "pin":
                        naJanela.setIconImage(Pims.pega("Pointel.png").getImage());
                        break;
                    case "pin.adm":
                        naJanela.setIconImage(Pims.pega("PointelAdmin.png").getImage());
                        break;
                    case "pin.adp":
                        naJanela.setIconImage(Pims.pega("PointelAdPro.png").getImage());
                        break;
                    case "pin.edu":
                        naJanela.setIconImage(Pims.pega("PointelEduca.png").getImage());
                        break;
                    case "pin.bib":
                        naJanela.setIconImage(Pims.pega("PointelBiblio.png").getImage());
                        break;
                    case "pin.hlp":
                        naJanela.setIconImage(Pims.pega("PointelSuporte.png").getImage());
                        break;
                    case "pin.jrb":
                        naJanela.setIconImage(Pims.pega("PointelJarbotico.png").getImage());
                        break;
                    case "pin.amk":
                        naJanela.setIconImage(Pims.pega("PointelAllMake.png").getImage());
                        break;
                    case "pin.amg":
                        naJanela.setIconImage(Pims.pega("PointelAllMagi.png").getImage());
                        break;
                    case "pin.prk":
                        naJanela.setIconImage(Pims.pega("PointelProk.png").getImage());
                        break;
                    case "pin.mgr":
                        naJanela.setIconImage(Pims.pega("PointelMigre.png").getImage());
                        break;
                    case "pin.inf":
                        naJanela.setIconImage(Pims.pega("PointelInfo.png").getImage());
                        break;
                    case "pin.app":
                        naJanela.setIconImage(Pims.pega("PointelApps.png").getImage());
                        break;
                    default:
                        naJanela.setIconImage(Pims.pega("Pointel.png").getImage());
                        break;
                }
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    @Override
    public void atualizaListaJanelas() {
        JanelasIntf.atualizaTodas();
    }

}
