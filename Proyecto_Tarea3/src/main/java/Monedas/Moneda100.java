package Monedas;

/**
 * Clase que representa una moneda de 100.
 */

public class Moneda100 extends Moneda{
	/**
	 * Constructor de la moneda de 100.
	 */
	public Moneda100(){
		super();
	}

	/**
	 * Obtiene el valor de la moneda.
	 *
	 * @return valor de la moneda
	 */
	@Override
	public int getValor(){
		return 100;
	}
}
