package pin.libamk;

import javax.swing.JComponent;

public abstract class AcaoSe<T> {

    public abstract void prepara();

    public abstract AcaoSe inicia(JComponent... osComponentes);

    public abstract Boolean deveExecutar(T comParametro);

    public abstract void executa(T comParametro);
}
