package pin.modamk;

import javax.swing.JComponent;

public class RelatorioParametro {
    public String nome;
    public Object valor;
    public JComponent editor;
    
    public Tipo tipo;
            
    public enum Tipo {FIXO, VARIAVEL}

    public RelatorioParametro(String aNome, Object aValor) {
        nome = aNome;
        valor = aValor;
        tipo = Tipo.FIXO;
    }
    
    public RelatorioParametro(String aNome, JComponent aEditor) {
        nome = aNome;
        editor = aEditor;
        valor = null;
        tipo = Tipo.VARIAVEL;
    }
    
}
