package Monedas;

/**
 * Clase que representa una moneda de 500.
 */
public class Moneda500 extends Moneda{
	/**
	 * Constructor de la moneda de 500.
	 */

	public Moneda500(){
		super();
	}

	/**
	 * Obtiene el valor de la moneda.
	 *
	 * @return valor de la moneda
	 */
	@Override
	public int getValor(){
		return 500;
	}
}
