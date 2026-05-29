package Productos;

/**
 * Clase que representa una CocaCola.
 */
public class CocaCola extends Bebida {
	public CocaCola (int serie){
		super(serie);
	}
	/**
	 * Consume la CocaCola
	 *
	 * @return nombre de la bebida
	 */
	@Override
	public String consumir(){
        return "coca-cola";
    }
}