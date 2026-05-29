package Monedas;

/**
 * Clase abstracta que representa Monedas
 */


public abstract class Moneda implements Comparable<Moneda>{
	public Moneda(){} //Moneda solo necesita usar this como identificador
	public Moneda getSerie(){
	return this; //se usa el hashcode para identificar a la Moneda
	}

	/**
	 *
	 * @return retorna el valor de la moneda, se hace override en las clases Moneda100, Moneda500...
	 */
	public abstract int getValor();

	/**
	 *Sobre-escribimos el metodo CompareTo con el objetivo de comparar esta moneda con la moneda entregada
	 * @param entrada es la moneda que sera comparada
	 * @return retorna la comparacion entre las distintas monedas
	 */
    @Override
	public int compareTo(Moneda entrada){//Aqui se han de comparar los valores entre dos monedas

		return Integer.compare(this.getValor(), entrada.getValor());
	}
}
