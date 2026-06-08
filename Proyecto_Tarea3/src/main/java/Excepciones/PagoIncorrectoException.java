package Excepciones;
/**
 * Una excepcion personalizada la cual tiene como mensaje un String
 * Ocurre cuando el medio de pago no es el adecuado, por ejemplo, cuando se intenta pagar con una moneda null
 */
public class PagoIncorrectoException extends Exception{
    public PagoIncorrectoException(){
        super("La moneda con la que desea pagar es incorrecta. Pague con una moneda valida.");
    }
}
