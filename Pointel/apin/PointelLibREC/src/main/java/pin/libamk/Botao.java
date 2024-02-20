package pin.libamk;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JButton;
import pin.libpic.Pics;
import pin.libutl.Utl;

public class Botao {

    private String titulo;
    private Character atalho;
    private Icon icone;
    private Integer ordem;
    private String dica;
    private Boolean padrao;
    private List<String> permissoes;
    private Object origem;
    private AbstractAction aoConfirmar;
    private JButton controle;

    public Botao() {
        this(null, null, Pics.pegaProcessar(), null, null);
    }

    public Botao(String comTitulo) {
        this(comTitulo, null, null, null, null);
    }

    public Botao(String comTitulo, Character eAtalho) {
        this(comTitulo, eAtalho, null, null, null);
    }

    public Botao(String comTitulo, Icon eIcone) {
        this(comTitulo, null, eIcone, null, null);
    }

    public Botao(String comTitulo, Character eAtalho, Icon eIcone) {
        this(comTitulo, eAtalho, eIcone, null, null);
    }

    public Botao(String comTitulo, Icon eIcone, String eDica) {
        this(comTitulo, null, eIcone, eDica, null);
    }

    public Botao(String comTitulo, Character eAtalho, Icon eIcone, String eDica) {
        this(comTitulo, eAtalho, eIcone, eDica, null);
    }

    public Botao(String comTitulo, Icon eIcone, String eDica, Integer naOrdem) {
        this(comTitulo, null, eIcone, eDica, naOrdem);
    }

    public Botao(String comTitulo, String eDica) {
        this(comTitulo, null, null, eDica, null);
    }

    public Botao(String comTitulo, Character eAtalho, String eDica) {
        this(comTitulo, eAtalho, null, eDica, null);
    }

    public Botao(String comTitulo, String eDica, Integer naOrdem) {
        this(comTitulo, null, null, eDica, naOrdem);
    }

    public Botao(String comTitulo, Character eAtalho, String eDica, Integer naOrdem) {
        this(comTitulo, eAtalho, null, eDica, naOrdem);
    }

    public Botao(String comTitulo, Integer naOrdem) {
        this(comTitulo, null, null, null, naOrdem);
    }

    public Botao(String comTitulo, Character eAtalho, Integer naOrdem) {
        this(comTitulo, eAtalho, null, null, naOrdem);
    }

    public Botao(Character comAtalho) {
        this(null, comAtalho, null, null, null);
    }

    public Botao(Icon comIcone) {
        this(null, null, comIcone, null, null);
    }

    public Botao(Character comAtalho, Icon eIcone) {
        this(null, comAtalho, eIcone, null, null);
    }

    public Botao(Icon comIcone, String eDica) {
        this(null, null, comIcone, eDica, null);
    }

    public Botao(Character comAtalho, Icon eIcone, String eDica) {
        this(null, comAtalho, eIcone, eDica, null);
    }

    public Botao(Icon comIcone, String eDica, Integer naOrdem) {
        this(null, null, comIcone, eDica, naOrdem);
    }

    public Botao(Character comAtalho, Icon eIcone, String eDica, Integer naOrdem) {
        this(null, comAtalho, eIcone, eDica, naOrdem);
    }

    public Botao(Icon comIcone, Integer naOrdem) {
        this(null, null, comIcone, null, naOrdem);
    }

    public Botao(Character comAtalho, Icon eIcone, Integer naOrdem) {
        this(null, comAtalho, eIcone, null, naOrdem);
    }

    public Botao(Integer naOrdem) {
        this(null, null, null, null, naOrdem);
    }

    public Botao(Character comAtalho, Integer naOrdem) {
        this(null, comAtalho, null, null, naOrdem);
    }

    public Botao(Integer naOrdem, String eDica) {
        this(null, null, null, eDica, naOrdem);
    }

    public Botao(Character comAtalho, Integer naOrdem, String eDica) {
        this(null, comAtalho, null, eDica, naOrdem);
    }

    public Botao(String comTitulo, Character eAtalho, Icon eIcone, String eDica, Integer naOrdem) {
        this.titulo = comTitulo;
        this.atalho = eAtalho;
        this.icone = eIcone;
        this.ordem = naOrdem;
        this.dica = eDica;
        this.padrao = false;
        this.permissoes = null;
        this.origem = null;
        this.aoConfirmar = null;
        this.controle = null;
        if (titulo == null && icone == null) {
            icone = Pics.pegaProcessar();
        }
    }

    public String pTitulo() {
        return titulo;
    }

    public Botao mTitulo(String oTitulo) {
        this.titulo = oTitulo;
        return this;
    }

    public Character pAtalho() {
        return atalho;
    }

    public Botao mAtalho(Character oAtalho) {
        this.atalho = oAtalho;
        return this;
    }

    public Icon pIcone() {
        return icone;
    }

    public Botao mIcone(Icon oIcone) {
        this.icone = oIcone;
        return this;
    }

    public Boolean temOrdem() {
        return ordem != null;
    }

    public Integer pOrdem() {
        return ordem;
    }

    public Botao mOrdem(Integer naOrdem) {
        this.ordem = naOrdem;
        return this;
    }

    public String pDica() {
        return dica;
    }

    public Botao mDica(String aDica) {
        this.dica = aDica;
        return this;
    }

    public Boolean ehPadrao() {
        return padrao;
    }

    public Botao botaPadrao() {
        this.padrao = true;
        return this;
    }

    public Botao tiraPadrao() {
        this.padrao = false;
        return this;
    }

    public Boolean temPermissao(String comNome) {
        return temPermissao(comNome, false);
    }

    public Boolean temPermissao(String comNome, Boolean ePadrao) {
        Boolean retorno = ePadrao;
        if (permissoes != null) {
            retorno = permissoes.contains(comNome);
        }
        return retorno;
    }

    public Botao botaPermissao(String comNome) {
        if (permissoes == null) {
            permissoes = new ArrayList<>();
        }
        if (!permissoes.contains(comNome)) {
            permissoes.add(comNome);
        }
        return this;
    }

    public Botao tiraPermissao(String comNome) {
        if (permissoes == null) {
            permissoes = new ArrayList<>();
        }
        permissoes.remove(comNome);
        return this;
    }

    public Object pOrigem() {
        return origem;
    }

    public Botao mOrigem(Object aOrigem) {
        this.origem = aOrigem;
        return this;
    }

    public AbstractAction pAoConfirmar() {
        return aoConfirmar;
    }

    public void mAoConfirmar(AbstractAction aoConfirmar) {
        this.aoConfirmar = aoConfirmar;
    }

    public void aoExecutar(Object comOrigem) throws Exception {
    }

    public JButton cria(Object comOrigem) {
        JButton retorno = new JButton();
        if (titulo != null) {
            retorno.setText(titulo);
        }
        if (atalho != null) {
            retorno.setMnemonic(atalho);
        }
        if (icone != null) {
            retorno.setIcon(icone);
        }
        if (dica != null) {
            retorno.setToolTipText(dica);;
        }
        retorno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (aoConfirmar != null) {
                        if (origem != null) {
                            e.setSource(origem);
                        }
                        aoConfirmar.actionPerformed(e);
                    } else {
                        if (origem != null) {
                            aoExecutar(origem);
                        } else {
                            aoExecutar((comOrigem != null ? comOrigem : Botao.this));
                        }
                    }
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        });
        controle = retorno;
        return retorno;
    }

    public JButton pControle() {
        return controle;
    }

    public Botao habilita() {
        controle.setEnabled(true);
        return this;
    }

    public Botao deshabilita() {
        controle.setEnabled(false);
        return this;
    }

    public Botao habilitado(Boolean para) {
        controle.setEnabled(para);
        return this;
    }

    public Boolean estaHabilitado() {
        return controle.isEnabled();
    }

    public Botao focavel(Boolean para) {
        controle.setFocusable(true);
        return this;
    }

    public Botao botaFoco() {
        controle.requestFocus();
        return this;
    }

    public Boolean estaFocado() {
        return controle.isFocusOwner();
    }
}
