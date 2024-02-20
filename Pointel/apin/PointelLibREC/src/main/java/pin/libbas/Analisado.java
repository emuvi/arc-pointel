package pin.libbas;

public interface Analisado<T, R> {

    public R analisa(T oValor) throws Exception;

}
