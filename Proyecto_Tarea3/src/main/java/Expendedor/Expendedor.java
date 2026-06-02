package Expendedor;

import Monedas.*;
import Productos.*;

/**
 *  Clase que representa un expendedor de productos.
 */
public class Expendedor {
    /**
     *
     */
	private Deposito<Fanta> fanta;
	private Deposito<CocaCola> coca;
	private Deposito<Snicker> snicker;
	private Deposito<Super8> super8;
	private Deposito<Sprite> sprite;

    private Deposito<Moneda> monedaDeposito;

    private int vuelto;
    private Producto compra;

    /**
     *
     * @param cantidad cantidad de Productos por deposito
     *  Llena los depositos segun cantidad
     *  Crea un deposito de monedas
     */
	public Expendedor(int cantidad){
		coca = new Deposito<CocaCola>();
        sprite = new Deposito<Sprite>();
        fanta = new Deposito<Fanta>();
        snicker= new Deposito<Snicker>();
        super8 = new Deposito<Super8>();

        monedaDeposito = new Deposito<Moneda>();

        for (int i = 0; i<cantidad; i++){
            CocaCola temp = new CocaCola(1100+i);
            coca.addObjeto(temp);
        }
        for (int i = 0; i<cantidad; i++){
            Sprite temp = new Sprite(1200+i);
            sprite.addObjeto(temp);
        }
        for (int i = 0; i<cantidad; i++){
            Fanta temp = new Fanta(1300+i);
            fanta.addObjeto(temp);
        }
        for (int i = 0; i<cantidad; i++){
            Super8 temp = new Super8(2100+i);
            super8.addObjeto(temp);
        }
        for (int i = 0; i<cantidad; i++){
            Snicker temp = new Snicker(2200+i);
            snicker.addObjeto(temp);
        }

	}

    

    /**
     *
     * @param tipo es el tipo de producto
     * @param pago es la moneda con la cual se comprara el producto
     * @return Retorna el producto comprado
     * @throws PagoIncorrectoException Pasa si la moneda con la que se quiere pagar es null
     * @throws PagoInsuficienteException Pasa cuando la moneda tiene un valor menor al del producto
     * @throws NoHayProductoException Pasa cuando en el deposito no queda del producto deseado
     */
    public Producto comprarProducto(int tipo, Moneda pago) throws PagoIncorrectoException, PagoInsuficienteException, NoHayProductoException {
        if (pago == null){
            throw new PagoIncorrectoException();}

    switch (tipo) {
    case 11:
        if (pago.getValor() < InformacionProducto.SPRITE.getPrecio()){
            monedaDeposito.addObjeto(pago); //El vuelto
            throw new PagoInsuficienteException("La moneda tiene un valor de " + pago.getValor() + 
                " lo cual es insuficiente, debe de tener un valor de al menos " + InformacionProducto.SPRITE.getPrecio());
        }
        else {
            vuelto = pago.getValor() - InformacionProducto.SPRITE.getPrecio();
            //Para obtener el objeto, hemos de ver la excepcion NoHayProductoExcepcion
            if (sprite.isEmpty()) {
                monedaDeposito.addObjeto(pago);
                throw new NoHayProductoException("No quedan sprites");
            }
            else { //Quedan productos, entonces compramos un producto
                compra = sprite.getObjeto();
            }
        }
        break; //Del caso sprite

            case 13:
        if (pago.getValor() < InformacionProducto.FANTA.getPrecio()){
            monedaDeposito.addObjeto(pago); //El vuelto
            throw new PagoInsuficienteException("La moneda tiene un valor de " + pago.getValor() + 
                " lo cual es insuficiente, debe de tener un valor de al menos " + InformacionProducto.FANTA.getPrecio());
        }
        else {
            vuelto = pago.getValor() - InformacionProducto.FANTA.getPrecio();
            //Para obtener el objeto, hemos de ver la excepcion NoHayProductoExcepcion
            if (fanta.isEmpty()) {
                monedaDeposito.addObjeto(pago);
                throw new NoHayProductoException("No quedan fantas");
            }
            else { //Quedan productos, entonces compramos un producto
                compra = fanta.getObjeto();
            }
        }
        break;


            case 12:
        if (pago.getValor() < InformacionProducto.COCACOLA.getPrecio()){
            monedaDeposito.addObjeto(pago); //El vuelto
            throw new PagoInsuficienteException("La moneda tiene un valor de " + pago.getValor() + 
                " lo cual es insuficiente, debe de tener un valor de al menos " + InformacionProducto.COCACOLA.getPrecio());
        }
        else {
            vuelto = pago.getValor() - InformacionProducto.COCACOLA.getPrecio();
            //Para obtener el objeto, hemos de ver la excepcion NoHayProductoExcepcion
            if (coca.isEmpty()) {
                monedaDeposito.addObjeto(pago);

                throw new NoHayProductoException("No quedan cocacolas");
            }
            else { //Quedan productos, entonces compramos un producto
                compra = coca.getObjeto();
            }
        }
        break;


            case 21:
        if (pago.getValor() < InformacionProducto.SNICKER.getPrecio()){
            monedaDeposito.addObjeto(pago); //El vuelto
            throw new PagoInsuficienteException("La moneda tiene un valor de " + pago.getValor() + 
                " lo cual es insuficiente, debe de tener un valor de al menos " + InformacionProducto.SNICKER.getPrecio());
        }
        else {
            vuelto = pago.getValor() - InformacionProducto.SNICKER.getPrecio();
            //Para obtener el objeto, hemos de ver la excepcion NoHayProductoExcepcion
            if (snicker.isEmpty()) {
                monedaDeposito.addObjeto(pago);

                throw new NoHayProductoException("No quedan snickers");
            }
            else { //Quedan productos, entonces compramos un producto
                compra = snicker.getObjeto();
            }
        }
        break;



            case 22:
        if (pago.getValor() < InformacionProducto.SUPER8.getPrecio()){
            monedaDeposito.addObjeto(pago); //El vuelto
            throw new PagoInsuficienteException("La moneda tiene un valor de " + pago.getValor() + 
                " lo cual es insuficiente, debe de tener un valor de al menos " + InformacionProducto.SUPER8.getPrecio());
        }
        else {
            vuelto = pago.getValor() - InformacionProducto.SUPER8.getPrecio();
            //Para obtener el objeto, hemos de ver la excepcion NoHayProductoExcepcion
            if (super8.isEmpty()) {
                monedaDeposito.addObjeto(pago);

                throw new NoHayProductoException("No quedan super8");
            }
            else { //Quedan productos, entonces compramos un producto
                compra = super8.getObjeto();
            }
        }
        break;
    }//end switch


    //Lo siguiente es para manejar el vuelto
    for (int i = 0; i < vuelto/100; i++){
        monedaDeposito.addObjeto(new Moneda100());
        }

    return compra; //Finalmente retornamos el Producto comprado

    }

    /**
     *
     * @return Moneda retorna una moneda del deposito de monedas donde se deja el vuelto
     * si el deposito esta vacio retorna null
     */
    public Moneda getVuelto(){
        if (monedaDeposito.isEmpty()){
            return null;
        }
        else{
        return monedaDeposito.getObjeto();
        }
    }
// Abajo estan las excepciones
    class NoHayProductoException extends Exception {
        public NoHayProductoException(String mensajeError0){
            super(mensajeError0);
        }
    }
    class PagoIncorrectoException extends Exception {
        public PagoIncorrectoException(){
            super("La moneda con la que desea pagar es incorrecta. Pague con una moneda valida.");
        }
    }
    class PagoInsuficienteException extends Exception {
        public PagoInsuficienteException(String mensajeError){ 
        super(mensajeError);
        }
    }

}
