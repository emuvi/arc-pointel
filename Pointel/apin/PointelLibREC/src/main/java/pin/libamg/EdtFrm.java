package pin.libamg;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JScrollPane;
import pin.ldtfrm.LdtFrm;
import pin.ldtfrm.LdtFrmDoc;
import pin.libbas.Dados;
import pin.libitr.QuestaoCor;
import pin.libjan.Janelas;
import pin.libout.ComponentResizer;
import pin.libutl.Utl;
import pin.libutl.UtlArq;
import pin.libutl.UtlInt;
import pin.libutl.UtlLis;
import pin.libutl.UtlTex;
import pin.libvlr.VFrm;
import pin.libvlr.Vlr;
import pin.libvtf.CorVtf;
import pin.libvtf.DimensaoVtf;
import pin.libvtf.NumVtf;

public class EdtFrm extends Edt {

    private final LdtFrm controlador;
    private volatile File selecionado;
    private volatile Boolean editavel;
    private volatile Color uniaoCor;
    private volatile Float uniaoGrossura;
    private volatile Edt uniaoDe;
    private final Boolean apresentacao;

    public EdtFrm() {
        this(false);
    }

    public EdtFrm(Boolean deApresentacao) {
        super();
        controlador = new LdtFrm();
        apresentacao = deApresentacao;
        add(controlador, BorderLayout.CENTER);
        inicia();
    }

    @Override
    public void inicia() {
        editavel = true;
        uniaoDe = null;
        pPopMenu().bota("Novo", 'N', new ActNovo());
        pPopMenu().bota("Abrir", 'A', new ActAbrir());
        pPopMenu().bota("Salvar", 'S', new ActSalvar());
        pPopMenu().botaSeparador();
        pPopMenu().bota("Inserir", 'I');
        pPopMenu().bota("Inserir", "Simples", 'S');
        pPopMenu().bota("Inserir.Simples", "Lógico", 'G', new ActSimLogico());
        pPopMenu().bota("Inserir.Simples", "Caracter", 'C', new ActSimCaracter());
        pPopMenu().bota("Inserir.Simples", "Caracteres", 'R', new ActSimCaracteres());
        pPopMenu().bota("Inserir.Simples", "Cor", 'O', new ActSimCor());
        pPopMenu().bota("Inserir.Simples", "Senha", 'S', new ActSimSenha());
        pPopMenu().bota("Inserir.Simples", "Enumeração", 'E', new ActSimEnumeracao());
        pPopMenu().bota("Inserir.Simples", "Sugestão", 'U', new ActSimSugestao());
        pPopMenu().bota("Inserir.Simples", "Inteiro", 'I', new ActSimInteiro());
        pPopMenu().bota("Inserir.Simples", "Inteiro Lon.", 'L', new ActSimInteiroLon());
        pPopMenu().bota("Inserir.Simples", "Número", 'N', new ActSimNumero());
        pPopMenu().bota("Inserir.Simples", "Número Lon.", 'M', new ActSimNumeroLon());
        pPopMenu().bota("Inserir.Simples", "Data", 'D', new ActSimData());
        pPopMenu().bota("Inserir.Simples", "Hora", 'H', new ActSimHora());
        pPopMenu().bota("Inserir.Simples", "DataHora", 'A', new ActSimDataHora());
        pPopMenu().bota("Inserir.Simples", "Momento", 'T', new ActSimMomento());
        pPopMenu().bota("Inserir", "Complexo", 'C');
        pPopMenu().bota("Inserir.Complexo", "Abas", 'A', new ActComAbas());
        pPopMenu().bota("Inserir.Complexo", "Cartões", 'C', new ActComCartoes());
        pPopMenu().bota("Inserir.Complexo", "Painel", 'P', new ActComPainel());
        pPopMenu().bota("Inserir.Complexo", "Botão", 'B', new ActComBotao());
        pPopMenu().bota("Inserir.Complexo", "Escolha", 'E', new ActComEscolha());
        pPopMenu().bota("Inserir.Complexo", "Lista", 'L', new ActComLista());
        pPopMenu().bota("Inserir.Complexo", "Texto", 'T', new ActComTexto());
        pPopMenu().bota("Inserir.Complexo", "Imagem", 'I', new ActComImagem());
        pPopMenu().bota("Inserir.Complexo", "Áudio", 'U', new ActComAudio());
        pPopMenu().bota("Inserir.Complexo", "Vídeo", 'V', new ActComVideo());
        pPopMenu().bota("Inserir.Complexo", "Forma", 'F', new ActComForma());
        pPopMenu().bota("Inserir.Complexo", "Planilha", 'N', new ActComPlanilha());
        pPopMenu().bota("Inserir.Complexo", "Documento", 'D', new ActComDocumento());
        pPopMenu().bota("Inserir.Complexo", "Formulário", 'O', new ActComFormulario());
        pPopMenu().bota("Inserir.Complexo", "Apresentação", 'R', new ActComApresentacao());
        pPopMenu().bota("Inserir.Complexo", "Arquivo", 'Q', new ActComArquivo());
        pPopMenu().bota("União", 'U');
        pPopMenu().bota("União", "Cor", 'C', new ActUniCor());
        pPopMenu().bota("União", "Grossura", 'G', new ActUniGrossura());
        pPopMenu().bota("Fundo", 'F');
        pPopMenu().bota("Fundo", "Cor", 'C', new ActFunCor());
        pPopMenu().bota("Fundo", "Tamanho", 'T', new ActFunTamanho());
        pPopMenu().bota("Fundo", "Específicar", 'E', new ActFunEspecificar());
        mDimensao(360, 360);
        uniaoCor = Color.BLACK;
        uniaoGrossura = 2f;
        super.inicia();
    }

    @Override
    public void atualizaOpcoes() {
    }

    @Override
    public void atualizaParams() {
    }

    @Override
    public LdtFrmDoc controle() {
        return controlador.controle();
    }

    @Override
    public JScrollPane rolagem() {
        return controlador.rolagem();
    }

    @Override
    public synchronized Vlr pgVlr(Object comPadrao) throws Exception {
        if (vazio()) {
            return Vlr.novo(comPadrao);
        } else {
            List<Object> itns = new ArrayList<>();
            for (Component cmp : controle().getComponents()) {
                if (cmp instanceof Edt) {
                    itns.add(cmp);
                }
            }
            return new VFrm(itns);
        }
    }

    @Override
    public synchronized EdtFrm mdVlr(Object para) throws Exception {
        novo();
        if (para != null) {
            List<Object> lst = UtlLis.pega(para);
            for (Object val : lst) {
                if (val instanceof Edt) {
                    iniciaEdt((Edt) val);
//                    controle().bota((Edt) val);
                }
            }
        }
        return this;
    }

    @Override
    public EdtFrm botaAoModificar(AbstractAction aAcao) {
//        controle().bota(aAcao);
        return this;
    }

    @Override
    public EdtFrm auxiliar() throws Exception {

        return this;
    }

    @Override
    public EdtFrm aleatorio() {

        return this;
    }

    @Override
    public Boolean vazio() {
        return controle().vazio();
    }

    @Override
    public EdtFrm mEditavel(Boolean para) {
        editavel = para;
        for (Component cmp : controle().getComponents()) {
            if (cmp instanceof Edt) {
                ((Edt) cmp).mEditavel(para);
            }
        }
        controle().setEnabled(para);
        return this;
    }

    @Override
    public Boolean editavel() {
        return editavel;
    }

    public EdtFrm novo() {
//        controle().limpa(!apresentacao);
        return this;
    }

    public EdtFrm abre() throws Exception {
        File arq = UtlArq.abreArq(selecionado, "Pointel Formulário (*.pfr)", "pfr");
        if (arq != null) {
            abre(arq);
        }
        return this;
    }

    public EdtFrm abre(File oArquivo) throws Exception {
        mdVlr(UtlTex.abre(oArquivo));
        selecionado = oArquivo;
        return this;
    }

    public EdtFrm salva() throws Exception {
        File arq = UtlArq.salvaArq(selecionado, "Pointel Formulário (*.pfr)", "pfr");
        if (arq != null) {
            salva(arq);
        }
        return this;
    }

    public EdtFrm salva(File noArquivo) throws Exception {
        UtlTex.salva(pgVlr().descreve(), noArquivo);
        selecionado = noArquivo;
        return this;
    }

    private void iniciaEdt(Edt oEditor) {
        if (!oEditor.pPopMenu().tem("União", "Bota De")) {
            oEditor.pPopMenu().botaSeparador();
            oEditor.pPopMenu().bota("União", 'U');
            oEditor.pPopMenu().bota("União", "Bota De", 'D', new ActUniBotDe(oEditor));
            oEditor.pPopMenu().bota("União", "Bota Para", 'P', new ActUniBotPara(oEditor));
            oEditor.pPopMenu().bota("União", "Tira De", 'T', new ActUniTirDe(oEditor));
            oEditor.pPopMenu().bota("União", "Tira Para", 'I', new ActUniTirPara(oEditor));
            oEditor.pPopMenu().bota("União", "Tira Todos", 'R', new ActUniTirTodos(oEditor));
            oEditor.pPopMenu().bota("Exibe", 'E');
            oEditor.pPopMenu().bota("Exibe", "Na Ordem", 'O', new ActExiNaOrdem(oEditor));
            oEditor.pPopMenu().bota("Exibe", "Para Frente", 'F', new ActExiFrente(oEditor));
            oEditor.pPopMenu().bota("Exibe", "Para Trás", 'T', new ActExiTras(oEditor));
            oEditor.pPopMenu().bota("Posição", 'P', oEditor.pActApaPosPuxa());
            oEditor.pPopMenu().bota("Tamanho", 'T', oEditor.pActApaLimPuxa());
            oEditor.pPopMenu().bota("Exclui", 'X', new ActExclui(oEditor));
        }
    }

    public Edt insere(Dados.Tp doTipo, Point noPonto) {
        Edt novo = Edt.novo(doTipo);
        iniciaEdt(novo);
        novo.mPosicao(noPonto.x, noPonto.y);
//        controle().bota(novo);
        novo.botaAutoDimensao();
        novo.botaFoco();
        return novo;
    }

    private class ActSimLogico extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                insere(Dados.Tp.Log, controle().pUltimoClique());
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActSimCaracter extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                insere(Dados.Tp.Cr, controle().pUltimoClique());
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActSimCaracteres extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                insere(Dados.Tp.Crs, controle().pUltimoClique());
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActSimCor extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                insere(Dados.Tp.Cor, controle().pUltimoClique());
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActSimSenha extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                insere(Dados.Tp.Sen, controle().pUltimoClique());
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActSimEnumeracao extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                insere(Dados.Tp.Enu, controle().pUltimoClique());
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActSimSugestao extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                insere(Dados.Tp.Sug, controle().pUltimoClique());
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActSimInteiro extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                insere(Dados.Tp.Int, controle().pUltimoClique());
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActSimInteiroLon extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                insere(Dados.Tp.IntLon, controle().pUltimoClique());
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActSimNumero extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                insere(Dados.Tp.Num, controle().pUltimoClique());
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActSimNumeroLon extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                insere(Dados.Tp.NumLon, controle().pUltimoClique());
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActSimData extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                insere(Dados.Tp.Dat, controle().pUltimoClique());
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActSimHora extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                insere(Dados.Tp.Hor, controle().pUltimoClique());
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActSimDataHora extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                insere(Dados.Tp.DatHor, controle().pUltimoClique());
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActSimMomento extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                insere(Dados.Tp.Mom, controle().pUltimoClique());
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActComAbas extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                insere(Dados.Tp.Abs, controle().pUltimoClique());
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActComCartoes extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                insere(Dados.Tp.Cts, controle().pUltimoClique());
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActComPainel extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                insere(Dados.Tp.Pai, controle().pUltimoClique());
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActComBotao extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                insere(Dados.Tp.Bot, controle().pUltimoClique());
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActComEscolha extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                insere(Dados.Tp.Esc, controle().pUltimoClique());
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActComLista extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                insere(Dados.Tp.Lis, controle().pUltimoClique());
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActComTexto extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                insere(Dados.Tp.Tex, controle().pUltimoClique());
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActComImagem extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                insere(Dados.Tp.Ima, controle().pUltimoClique());
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActComAudio extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                insere(Dados.Tp.Aud, controle().pUltimoClique());
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActComVideo extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                insere(Dados.Tp.Vid, controle().pUltimoClique());
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActComForma extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                insere(Dados.Tp.For, controle().pUltimoClique());
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActComPlanilha extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                insere(Dados.Tp.Pla, controle().pUltimoClique());
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActComDocumento extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                insere(Dados.Tp.Doc, controle().pUltimoClique());
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActComFormulario extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                insere(Dados.Tp.Frm, controle().pUltimoClique());
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActComApresentacao extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                insere(Dados.Tp.Apr, controle().pUltimoClique());
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActComArquivo extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                insere(Dados.Tp.Arq, controle().pUltimoClique());
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActUniBotDe extends AbstractAction {

        private final Edt editor;

        public ActUniBotDe(Edt editor) {
            this.editor = editor;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            uniaoDe = editor;
        }
    }

    private class ActUniBotPara extends AbstractAction {

        private final Edt editor;

        public ActUniBotPara(Edt editor) {
            this.editor = editor;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (uniaoDe == null) {
                Utl.alerta("União De Ainda Não Definida");
            } else {
//                if ((e.getModifiers() & ActionEvent.CTRL_MASK) == ActionEvent.CTRL_MASK) {
//                    controle().bota(new FrmUniao(FrmUniao.Tp.Seta, uniaoDe.hashCode(), editor.hashCode(), uniaoCor, uniaoGrossura));
//                } else {
//                    controle().bota(new FrmUniao(FrmUniao.Tp.Linha, uniaoDe.hashCode(), editor.hashCode(), uniaoCor, uniaoGrossura));
//                }
            }
        }
    }

    private class ActUniTirDe extends AbstractAction {

        private final Edt editor;

        public ActUniTirDe(Edt editor) {
            this.editor = editor;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
//            controle().tiraDe(editor.hashCode());
        }
    }

    private class ActUniTirPara extends AbstractAction {

        private final Edt editor;

        public ActUniTirPara(Edt editor) {
            this.editor = editor;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
//            controle().tiraPara(editor.hashCode());
        }
    }

    private class ActUniTirTodos extends AbstractAction {

        private final Edt editor;

        public ActUniTirTodos(Edt editor) {
            this.editor = editor;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
//            controle().tiraDe(editor.hashCode());
//            controle().tiraPara(editor.hashCode());
        }
    }

    private class ActExiNaOrdem extends AbstractAction {

        private final Edt editor;

        public ActExiNaOrdem(Edt editor) {
            this.editor = editor;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                Integer ind = UtlInt.pergunta("Ordem:", controle().getComponentZOrder(editor));
                if (ind != null) {
                    controle().setComponentZOrder(editor, ind);
                    controle().repaint();
                }
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActExiFrente extends AbstractAction {

        private final Edt editor;

        public ActExiFrente(Edt editor) {
            this.editor = editor;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                controle().remove(editor);
                controle().add(editor, 0);
                controle().repaint();
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActExiTras extends AbstractAction {

        private final Edt editor;

        public ActExiTras(Edt editor) {
            this.editor = editor;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                controle().remove(editor);
                controle().add(editor);
                controle().repaint();
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActExclui extends AbstractAction {

        private final Edt editor;

        public ActExclui(Edt editor) {
            this.editor = editor;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                Janelas.fechaEdt(editor);
//                controle().tira(editor);
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActUniCor extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                new CorVtf("União Cor") {
                    @Override
                    public void aoConfirmar(Object comOrigem) throws Exception {
                        uniaoCor = pgVal();
                        fecha();
                    }
                }.mInicial(uniaoCor).mostra();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    private class ActUniGrossura extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                new NumVtf("União Grossura") {
                    @Override
                    public void aoConfirmar(Object comOrigem) throws Exception {
                        uniaoGrossura = pgVal();
                        fecha();
                    }
                }.mInicial(uniaoGrossura).mostra();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    private class ActFunCor extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                new QuestaoCor(controle().getBackground()) {
                    @Override
                    public Boolean aoEscolher(Color comEscolhida) {
                        controle().setBackground(comEscolhida);
                        return true;
                    }
                }.mostra();
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }

    }

    private class ActFunTamanho extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                new ComponentResizer().init(controle());
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }

    }

    private class ActFunEspecificar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                try {
                    new DimensaoVtf(controle().getPreferredSize()) {
                        @Override
                        public void aoConfirmar(Object comOrigem) throws Exception {
//                            controlador.mDesenhoDimensao(pgVal());
                            fecha();
                        }
                    }.mostra();
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            } else {
                Utl.alerta("Editor Não está Editável");
            }
        }
    }

    private class ActNovo extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                if (Utl.continua()) {
                    novo();
                }
            } else {
                Utl.alerta("Editor Não Está Editável");
            }

        }
    }

    private class ActAbrir extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                try {
                    abre();
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            } else {
                Utl.alerta("Editor Não Está Editável");
            }
        }
    }

    private class ActSalvar extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (editavel()) {
                try {
                    salva();
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            } else {
                Utl.alerta("Editor Não Está Editável");
            }
        }
    }
}
