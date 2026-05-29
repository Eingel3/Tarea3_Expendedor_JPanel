package Productos;
/**
 * Clase abstracta que representa un producto.
 */
public abstract class Producto {
	private int serie;
	public Producto (int serie){
		this.serie = serie;
	}
	public int getSerie(){
		return serie;
	}

	/**
	 * Se edita en las clases Sprite, Fanta, Snicker.....
	 * @return retorna un String que indica que producto fue consumido
	 */
	public abstract String consumir();
}