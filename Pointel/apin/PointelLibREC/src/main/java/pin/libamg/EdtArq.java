package pin.libamg;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.file.Files;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import pin.libpic.Pics;
import pin.libutl.Utl;
import pin.libutl.UtlArq;
import pin.libutl.UtlCrs;
import pin.libvlr.Vlr;
import pin.libvlr.VArq;
import pin.libvlr.VCrs;

public class EdtArq extends Edt {

    public static enum Params {
        SO_ARQUIVOS, SO_DIRETORIOS, SO_CAMINHO, SALVAR
    };

    public static class ParamExtensao {

        String descricao;
        String[] extensoes;

        public ParamExtensao(String descricao, String... extensoes) {
            this.descricao = descricao;
            this.extensoes = extensoes;
        }

    }

    private JPanel jpnEditor;
    private JTextField editor;
    private JButton botao;
    private JPopupMenu menu;
    private JMenuItem mniCarregar;
    private JMenuItem mniSalvar;
    private JMenuItem mniSalvarAbrir;
    private JMenuItem mniTamanho;
    private JMenuItem mniLimpar;
    private Integer tamanho;
    private VArq valor;

    public EdtArq() {
        this(null);
    }

    public EdtArq(Integer comTamanho) {
        super();
        tamanho = comTamanho;
        jpnEditor = new JPanel(new BorderLayout(4, 4));
        botao = new JButton();
        editor = new JTextField();
        jpnEditor.add(editor, BorderLayout.CENTER);
        jpnEditor.add(botao, BorderLayout.EAST);
        add(jpnEditor, BorderLayout.CENTER);
        inicia();
    }

    private void mantemTamanho() {
        if (tamanho != null) {
            if (tamanho > 0) {
                if (editor.getText().length() > tamanho) {
                    editor.setText(editor.getText().substring(0, tamanho));
                }
            }
        }
    }

    @Override
    public void inicia() {
        menu = new JPopupMenu();
        valor = new VArq(null);
        editor.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                editor.selectAll();
            }
        });
        editor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                mantemTamanho();
            }
        });
        botao.setIcon(Pics.pega("cog.png"));
        editor.setEditable(true);
        botao.addActionListener(new ActAcionador());
        mniCarregar = new JMenuItem("Carregar");
        mniCarregar.addActionListener(new ActCarregar());
        menu.add(mniCarregar);
        mniSalvar = new JMenuItem("Salvar");
        mniSalvar.addActionListener(new ActSalvar());
        menu.add(mniSalvar);
        mniSalvarAbrir = new JMenuItem("Salvar e Abrir");
        mniSalvarAbrir.addActionListener(new ActSalvarAbrir());
        menu.add(mniSalvarAbrir);
        mniTamanho = new JMenuItem("Tamanho");
        mniTamanho.addActionListener(new ActTamanho());
        menu.add(mniTamanho);
        mniLimpar = new JMenuItem("Limpar");
        mniLimpar.addActionListener(new ActLimpar());
        menu.add(mniLimpar);
        jpnEditor.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        jpnEditor.setAlignmentY(JComponent.TOP_ALIGNMENT);
        mLargura(180);
        super.inicia();
    }

    @Override
    public void atualizaOpcoes() {
    }

    @Override
    public void atualizaParams() {
    }

    @Override
    public Edt botaFoco() {
        editor.requestFocus();
        return this;
    }

    @Override
    public JTextField controle() {
        return editor;
    }

    @Override
    public JScrollPane rolagem() {
        return null;
    }

    @Override
    public synchronized Vlr pgVlr(Object comPadrao) throws Exception {
        if (vazio()) {
            return Vlr.novo(comPadrao);
        } else {
            if (temParam(Params.SO_CAMINHO)) {
                if (editor.getText().isEmpty()) {
                    return Vlr.novo(comPadrao);
                } else {
                    return new VCrs(editor.getText());
                }
            } else {
                if (valor != null) {
                    valor.botaNome(editor.getText());
                }
                if (!editor.getText().isEmpty()) {
                    if (valor == null) {
                        valor = new VArq(null);
                        valor.botaNome(editor.getText());
                    }
                }
                return valor;
            }
        }
    }

    @Override
    public synchronized EdtArq mdVlr(Object para) throws Exception {
        if (temParam(Params.SO_CAMINHO)) {
            if (para instanceof File) {
                editor.setText(((File) para).getAbsolutePath());
            } else {
                editor.setText(UtlCrs.pega(para));
            }
        } else {
            if (para == null) {
                valor = null;
                editor.setText(null);
            } else if (para instanceof VArq) {
                valor = (VArq) para;
                editor.setText(valor.pegaNome());
            } else {
                valor = new VArq(para);
                editor.setText(valor.pegaNome());
            }
        }
        return this;
    }

    @Override
    public EdtArq botaAoModificar(AbstractAction aAcao) {

        return this;
    }

    @Override
    public EdtArq auxiliar() throws Exception {

        return this;
    }

    @Override
    public EdtArq aleatorio() {

        return this;
    }

    @Override
    public Boolean vazio() {
        if (temParam(Params.SO_CAMINHO)) {
            return editor.getText().isEmpty();
        } else {
            if (valor == null) {
                return true;
            } else if (valor.vazio()) {
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public EdtArq mEditavel(Boolean para) {
        editor.setEditable(para);
        return this;
    }

    @Override
    public Boolean editavel() {
        return editor.isEditable();
    }

    private class ActAcionador extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (temParam(Params.SO_CAMINHO)) {
                if (editavel()) {
                    File arq = null;
                    String descricao = null;
                    String[] extensoes = null;
                    ParamExtensao pExt = (ParamExtensao) pegaParam(ParamExtensao.class);
                    if (pExt != null) {
                        descricao = pExt.descricao;
                        extensoes = pExt.extensoes;
                    }
                    if (temParam(Params.SALVAR)) {
                        if (temParam(Params.SO_ARQUIVOS)) {
                            arq = UtlArq.salvaArq(descricao, extensoes);
                        } else if (temParam(Params.SO_DIRETORIOS)) {
                            arq = UtlArq.salvaDir(descricao, extensoes);
                        } else {
                            arq = UtlArq.salvaArqOuDir(descricao, extensoes);
                        }
                    } else {
                        if (temParam(Params.SO_ARQUIVOS)) {
                            arq = UtlArq.abreArq(descricao, extensoes);
                        } else if (temParam(Params.SO_DIRETORIOS)) {
                            arq = UtlArq.abreDir(descricao, extensoes);
                        } else {
                            arq = UtlArq.abreArqOuDir(descricao, extensoes);
                        }
                    }

                    if (arq != null) {
                        try {
                            mdVlr(new VCrs(arq.getAbsolutePath()));
                        } catch (Exception ex) {
                            Utl.registra(ex);
                        }
                    }
                } else {
                    Utl.alerta("Editor Não Está Editável");
                }
            } else {
                menu.show(botao, 0, botao.getHeight());
            }
        }

    }

    private class ActCarregar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                File arq = null;
                String descricao = null;
                String[] extensoes = null;
                ParamExtensao pExt = (ParamExtensao) pegaParam(ParamExtensao.class);
                if (pExt != null) {
                    descricao = pExt.descricao;
                    extensoes = pExt.extensoes;
                }
                if (temParam(Params.SO_ARQUIVOS)) {
                    arq = UtlArq.abreArq(descricao, extensoes);
                } else if (temParam(Params.SO_DIRETORIOS)) {
                    arq = UtlArq.abreDir(descricao, extensoes);
                } else {
                    arq = UtlArq.abreArqOuDir(descricao, extensoes);
                }
                if (arq != null) {
                    try {
                        mdVlr(new VArq(arq));
                    } catch (Exception ex) {
                        Utl.registra(ex);
                    }
                }
            } else {
                Utl.alerta("Editor Não Está Editável");
            }
        }

    }

    private class ActSalvar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (!vazio()) {
                    File padrao = null;
                    try {
                        padrao = new File(valor.pegaNome());
                    } catch (Exception ex) {
                    }
                    File arq = UtlArq.salvaArq(padrao);
                    if (arq != null) {
                        Files.write(arq.toPath(), valor.pgArq());
                    }
                } else {
                    Utl.alerta("Editor Está Vazio");
                }
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }

    }

    private class ActSalvarAbrir extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (!vazio()) {
                    File padrao = null;
                    try {
                        padrao = new File(valor.pegaNome());
                    } catch (Exception ex) {
                    }
                    File arq = UtlArq.salvaArq(padrao);
                    if (arq != null) {
                        Files.write(arq.toPath(), valor.pgArq());
                        Desktop.getDesktop().open(arq);
                    }
                } else {
                    Utl.alerta("Editor Está Vazio");
                }
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }

    }

    private class ActLimpar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                try {
                    mdVlr(null);
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            } else {
                Utl.alerta("Editor Não Está Editável");
            }
        }

    }

    private class ActTamanho extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            Utl.mensagem("Tamanho: " + UtlArq.formataTamanho(valor.pgArq()));
        }

    }

}
