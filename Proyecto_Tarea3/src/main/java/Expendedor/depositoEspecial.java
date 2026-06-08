package Expendedor;
/**
 * Clase que representa un depósito especial de capacidad un solo producto.
 *
 * @param <T> tipo de objeto que guarda el depósito
 */
public class depositoEspecial<T> extends Deposito {

    private  T objeto;
    public depositoEspecial(T objeto){
        this.objeto = objeto;
    }

    @Override
    public T getObjeto() {
        return objeto;
    }
    @Override
    public void addObjeto(T objeto) {
        this.objeto = objeto;
    }
    public void eliminarObjeto(){
        this.objeto = null;
    }
}
