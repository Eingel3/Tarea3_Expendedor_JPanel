package Productos;

/**
 * Clase que representa un <Sprite>.
 */
public class Sprite extends Bebida { //Falta extends
	public Sprite (int serie){
		super(serie);
	}
	/**
	 * consume el producto
	 *
	 * @return nombre del dulce
	 */
	@Override
	public String consumir(){
        return "sprite";
    }
}