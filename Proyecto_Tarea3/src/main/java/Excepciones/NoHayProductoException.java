package Excepciones;

/**
 * Una excepcion personalizada la cual tiene como mensaje un String
 */
public class NoHayProductoException extends Exception {
    public NoHayProductoException(String mensaje){
        super("El producto que intenta comprar no ha sido encontrado.\n"  + mensaje);
    }
}
