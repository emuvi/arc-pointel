package pin.libtex;

import java.util.Map;

public class Marca {

    public String abridor;
    public String valor;
    public String fechador;

    public Marca(String abridor, String valor, String fechador) {
        this.abridor = abridor;
        this.valor = valor;
        this.fechador = fechador;
    }

    public Map<String, String> parametros() {
        return Marcados.parametros(abridor);
    }

}
