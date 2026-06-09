package Monedas;

/**
 * Clase que representa una moneda de 1.
 */

public class Moneda1 extends Moneda{
    /**
     * Constructor de la moneda de 1.
     */
    public Moneda1(){
        super();
    }

    /**
     * Obtiene el valor de la moneda.
     *
     * @return valor de la moneda
     */
    @Override
    public int getValor(){
        return 1;
    }
}
