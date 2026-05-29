package Productos;

/**
 * Clase que representa un Super8.
 */
public class Super8 extends Dulce {
	public Super8 (int serie){
		super(serie);
	}
	/**
	 * consume el producto
	 *
	 * @return nombre del dulce
	 */
	@Override
	public String consumir(){
        return "super8";
    }
}