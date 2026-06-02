package Excepciones;
/**
 * Una excepcion personalizada la cual tiene como mensaje un String
 * Ocurre cuando el medio de pago no es suficiente, por ejemplo, cuando se intenta pagar con una moneda de 100 un producto de costo 500
 */
public class PagoInsuficienteException extends Exception{
    public PagoInsuficienteException(String mensaje){
        super("El pago es insuficiente para el producto que se desea comprar. Por favor, intentar de nuevo con un pago adecuado. \n" + mensaje);
    }
}
