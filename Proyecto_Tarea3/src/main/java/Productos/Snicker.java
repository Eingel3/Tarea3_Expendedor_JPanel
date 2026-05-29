package Productos;
/**
 * Clase que representa un Snickers.
 */
public class Snicker extends Dulce {
	public Snicker (int serie){
		super(serie);
	}
	/**
	 * Come el Snickers.
	 *
	 * @return nombre del dulce
	 */
	@Override
	public String consumir(){
        return "snicker";
    }	
}