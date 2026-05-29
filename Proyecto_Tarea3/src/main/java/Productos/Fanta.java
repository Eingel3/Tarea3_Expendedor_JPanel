package Productos;

/**
 * Clase que representa una Fanta.
 */
public class Fanta extends Bebida {
	public Fanta (int serie){
		super(serie);
	}
	/**
	 * Consume la Fanta
	 *
	 * @return nombre de la bebida
	 */
	@Override
	public String consumir(){
        return "fanta";
    }
}