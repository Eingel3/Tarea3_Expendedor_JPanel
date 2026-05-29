package Monedas;

/**
 * Clase que representa una moneda de 1000.
 */
public class Moneda1000 extends Moneda{
	/**
	 * Constructor de la moneda de 1000.
	 */
	public Moneda1000(){
		super();
	}

	/**
	 * Obtiene el valor de la moneda.
	 *
	 * @return valor de la moneda
	 */
	@Override public int getValor(){
		return 1000;
	}
}
