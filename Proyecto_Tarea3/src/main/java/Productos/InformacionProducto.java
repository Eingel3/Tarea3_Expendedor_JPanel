package Productos;

/**
 * Clase enumerada que contiene distinta infromacion respecto al producto: Su nombre, precio, y el numero identificador
 */

public enum InformacionProducto {
	SPRITE(500, 11, "Sprite"),
	COCACOLA(500, 12, "CocaCola"),
	SNICKER(500, 21, "Snicker"),
	SUPER8(500, 22, "Super8"),
	FANTA(500, 13, "Fanta");
	private final int precio;
	private final int tipo;
	private final String nombre;
	private InformacionProducto(int precio, int tipo, String nombre) {
		this.precio = precio;
		this.tipo = tipo;
		this.nombre = nombre;
		}

	public int getPrecio(){
		return precio;
	}

	public int getTipo(){
		return tipo;
	}

	public String getNombre(){
		return nombre;
	}
}