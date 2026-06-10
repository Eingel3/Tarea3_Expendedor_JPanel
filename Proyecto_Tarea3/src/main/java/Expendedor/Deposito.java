package Expendedor;
import java.util.ArrayList;
/**
 * Clase que representa un depósito genérico.
 *
 * @param <T> tipo de objeto que guarda el depósito
 */
public class Deposito<T>{

	/**
	 * Creamos el array genérico
	 */

	private ArrayList<T> almacen; //almacen es de tipo generico

	/**
	 * Constructor del arreglo
	 */

	public Deposito(){
		almacen = new ArrayList<>();
	}

	/**
	 * Método para añadir una vez un objeto al arreglo
	 *
	 * @param objeto objeto que se quiere añadir al arreglo
	 */

	public void addObjeto(T objeto){ //Obtenemos un objeto generico
		almacen.add(objeto); //Se agrega el objeto generico, ya sea Moneda, Bebida o Dulce		
	}

	/**
	 * Método para retirar el objeto
	 *
	 * @return objeto retirado del depósito o null si está vacío
	 */

	public T getObjeto() { //Retorna una Moneda o Bebida o Dulce
        if (almacen.isEmpty()) { //Si el almacen no tiene objetos, retornamos null
            return null;
        }
        return almacen.remove(0); //Si tiene un objeto, removemos el objeto del almacen y lo retornamos
    }

	/**
	 * Metodo que indica si el almacen esta vacio
	 * @return True si almacen está vacío, False si contiene algun objeto
	 */
	public boolean isEmpty(){ //Para que otros metodos puedan ver si esta vacio
		return almacen.isEmpty();
	}

	/**
	 * Metodo para saber la cantidad de objetos que hay en el deposito
	 * @return retorna una variable int que indica la cantidad de objetos que hay en el deposito
	 */
	public int cantidadObjetos(){
		return almacen.size();
	}
}