package Expendedor;
/**
 * Clase que representa un depósito especial de capacidad un solo producto.
 *
 * @param <T> tipo de objeto que guarda el depósito
 */
public class depositoUnitario<T> extends Deposito {
    /**
     * objeto es un deposito especial con capacidad de un unico objeto
     */
    private  T objeto;

    /**
     * Constructor de la clase, uniccamente inicializamos objeto con valor null.
     */
    public depositoUnitario(){
        this.objeto = null;
    }

    /**
     * Getter del objeto que contiene objeto, se guarda el objeto del deposito unitario en una variable auxiliar
     * Y luego se deja vacío el deposito unitario llamado objeto
     * @return objetoAuxiliar quien referencia al objeto que estaba contenido en el deposito unitario
     */
    @Override
    public T getObjeto() {
        T objetoAuxiliar = this.objeto;
        this.objeto = null;
        return objetoAuxiliar;
    }

    /**
     * Setter de la propiedad objeto
     * @param objeto es el objeto que se quiere depositar en el deposito unitario
     */
    @Override
    public void addObjeto(T objeto) {
        this.objeto = objeto;
    }

    /**
     * Metodo que deja el deposito unitario vacio
     */
    public void eliminarObjeto(){
        this.objeto = null;
    }
}
