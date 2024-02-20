package pin.ldttex;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import pin.libitr.FormulaCrs;
import pin.libjan.TrataIntf;
import pin.libutl.Utl;
import pin.libutl.UtlArq;
import pin.libutl.UtlBin;
import pin.libutl.UtlRed;
import pin.libutl.UtlTex;

public class LdtTex extends JPanel {

    private final JTextArea controle;
    private final JScrollPane rolagem;
    private final JFileChooser escolhedor;
    private volatile Boolean carregando;
    private volatile Boolean parar;
    private volatile Boolean fechar;

    public LdtTex() {
        super(new BorderLayout());
        controle = new JTextArea();
        rolagem = new JScrollPane(controle);
        add(rolagem, BorderLayout.CENTER);
        carregando = false;
        fechar = false;
        controle.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 15));
        escolhedor = new JFileChooser();
        escolhedor.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return ((f.isDirectory()) || (UtlArq.ehArquivoDasExtencoes(f, UtlTex.pegaExtensoes())));
            }

            @Override
            public String getDescription() {
                return UtlTex.pegaExtensoesDescricao();
            }
        });
        rolagem.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        rolagem.setAlignmentY(JComponent.TOP_ALIGNMENT);
        controle.setDisabledTextColor(UIManager.getColor("TextField.foreground"));
    }

    public JTextArea controle() {
        return controle;
    }

    public JScrollPane rolagem() {
        return rolagem;
    }

    public LdtTex novo() {
        controle.setText("");
        return this;
    }

    public LdtTex abre() throws Exception {
        if (escolhedor.showOpenDialog(controle) == JFileChooser.APPROVE_OPTION) {
            abre(escolhedor.getSelectedFile());
        }
        return this;
    }

    public LdtTex abre(File oArquivo) throws Exception {
        controle.setText(UtlTex.abre(oArquivo));
        return this;
    }

    public LdtTex carrega() {
        if (escolhedor.showOpenDialog(controle) == JFileChooser.APPROVE_OPTION) {
            carrega(escolhedor.getSelectedFile());
        }
        return this;
    }

    public LdtTex carrega(File oArquivo) {
        new Thread("Carregar Arquivo de Texto") {
            @Override
            public void run() {
                parar = true;
                while (carregando) {
                    UtlBin.esperaMilis(100);
                }
                parar = false;
                try {
                    carregando = true;
                    if (oArquivo.exists()) {
                        escolhedor.setSelectedFile(oArquivo);
                        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(oArquivo)));
                        String linha;
                        Boolean primeiro = true;
                        while ((linha = br.readLine()) != null) {
                            if (primeiro) {
                                controle.setText(linha);
                                primeiro = false;
                            } else {
                                controle.append(UtlTex.quebra() + linha);
                            }
                            if (fechar || parar) {
                                break;
                            }
                        }
                        br.close();
                    }
                } catch (Exception ex) {
                    Utl.registra(ex);
                } finally {
                    carregando = false;
                }

            }
        }.start();
        return this;
    }

    public LdtTex baixa() throws Exception {
        String caminho = UtlRed.pergunta();
        if (caminho != null) {
            baixa(caminho);
        }
        return this;
    }

    public LdtTex baixa(String doCaminho) throws Exception {
        controle.setText(UtlRed.baixaTex(doCaminho));
        return this;
    }

    public LdtTex botaLinha(String aLinha) {
        botaLinha(aLinha, true);
        return this;
    }

    public LdtTex botaLinha(String aLinha, Boolean posicionaNoFim) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                controle.append(aLinha + UtlTex.quebra());
                TrataIntf.posicionaNoFimVertical(rolagem);
            }
        });
        return this;
    }

    public LdtTex compara() throws Exception {
        if (escolhedor.showOpenDialog(controle) == JFileChooser.APPROVE_OPTION) {
            compara(escolhedor.getSelectedFile());
        }
        return this;
    }

    public LdtTex compara(File comArquivo) throws Exception {
        String ladoa = controle.getText();
        String ladob = UtlTex.abre(comArquivo);
        Highlighter hilt = controle.getHighlighter();
        Highlighter.HighlightPainter htpn = new DefaultHighlighter.DefaultHighlightPainter(Color.red);
        for (int i1 = 0; i1 < ladoa.length(); i1++) {
            if (i1 < ladob.length()) {
                if (ladoa.charAt(i1) != ladob.charAt(i1)) {
                    hilt.addHighlight(i1, i1 + 1, htpn);
                }
            } else {
                break;
            }
        }
        return this;
    }

    public LdtTex linhasExcluir() {
        new FormulaCrs(true) {
            @Override
            public Boolean executa(FormulaCrs comFormula) {
                String novo = "";
                String[] linhas = UtlTex.pLinhas(controle.getText());
                for (String lin : linhas) {
                    if (comFormula.ehContendo()) {
                        if (!comFormula.padrao().matcher(lin).find()) {
                            novo = UtlTex.soma(novo, lin);
                        }
                    } else {
                        if (comFormula.padrao().matcher(lin).find()) {
                            novo = UtlTex.soma(novo, lin);
                        }
                    }
                }
                controle.setText(novo);
                return true;
            }
        }.mostra();
        return this;
    }

    public LdtTex botaAoModificar(AbstractAction aAcao) {
        controle.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                aAcao.actionPerformed(new ActionEvent(controle, 0, e.getLength() + "," + e.getOffset()));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                aAcao.actionPerformed(new ActionEvent(controle, 1, e.getLength() + "," + e.getOffset()));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                aAcao.actionPerformed(new ActionEvent(controle, 2, e.getLength() + "," + e.getOffset()));
            }
        });
        return this;
    }

    public LdtTex salva() throws Exception {
        if (escolhedor.showOpenDialog(controle) == JFileChooser.APPROVE_OPTION) {
            salva(escolhedor.getSelectedFile());
        }
        return this;
    }

    public LdtTex salva(File noArquivo) throws Exception {
        UtlTex.salva(controle.getText(), noArquivo);
        return this;
    }

    public LdtTex localiza() {
        new TexLocalizar(this).mostra();
        return this;
    }

    public Boolean estaQuebraLinha() {
        return controle.getLineWrap();
    }

    public LdtTex botaQuebraLinha() {
        controle.setLineWrap(true);
        controle.setWrapStyleWord(true);
        return this;
    }

    public LdtTex tiraQuebraLinha() {
        controle.setLineWrap(false);
        controle.setWrapStyleWord(false);
        return this;
    }

    public LdtTex fecha() {
        fechar = true;
        return this;
    }

}
