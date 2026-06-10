package Expendedor;

import Monedas.*;
import Productos.*;
import Excepciones.*;

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
    private DepositoUnitario<Producto> depositoCompra; //Deposito especial con capacidad de un solo producto

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

        depositoCompra = new DepositoUnitario();

        //LLenamos los depositos segun la cantidad de productos solicitada
        llenarExpendedor(cantidad);

	}

    /**
     * Llena los depósitos con la cantidad de productos indicada.
     * La serie de cada producto depende del tipo del producto contenido en el enum informacionProducto y
     * tambien depende de el numero del producto creado, es decir, el numero de la iteracion del ciclo for
     */
    private void llenarExpendedor(int cantProducto){
        for(int i = 0; i < cantProducto; i++){
            coca.addObjeto(new CocaCola(InformacionProducto.COCACOLA.getTipo()*100 + i));
            fanta.addObjeto(new Fanta(InformacionProducto.FANTA.getTipo()*100 + i));
            sprite.addObjeto(new Sprite(InformacionProducto.SPRITE.getTipo()*100 + i));
            snicker.addObjeto(new Snicker(InformacionProducto.SNICKER.getTipo()*100 + i));
            super8.addObjeto(new Super8(InformacionProducto.SUPER8.getTipo()*100 + i));
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
    public void comprarProducto(int tipo, Moneda pago) throws PagoIncorrectoException, PagoInsuficienteException, NoHayProductoException {
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
            //Para obtener el objeto, hemos de ver la excepcion NoHayProductoExcepcion
            if (sprite.isEmpty()) {
                monedaDeposito.addObjeto(pago);
                throw new NoHayProductoException("No quedan sprites");
            }
            else { //Quedan productos, entonces compramos un producto
                depositoCompra.addObjeto(sprite.getObjeto());
                vuelto = pago.getValor() - InformacionProducto.SPRITE.getPrecio();
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
            //Para obtener el objeto, hemos de ver la excepcion NoHayProductoExcepcion
            if (fanta.isEmpty()) {
                monedaDeposito.addObjeto(pago);
                throw new NoHayProductoException("No quedan fantas");
            }
            else { //Quedan productos, entonces compramos un producto
                depositoCompra.addObjeto(fanta.getObjeto());
                vuelto = pago.getValor() - InformacionProducto.FANTA.getPrecio();
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
            //Para obtener el objeto, hemos de ver la excepcion NoHayProductoExcepcion
            if (coca.isEmpty()) {
                monedaDeposito.addObjeto(pago);

                throw new NoHayProductoException("No quedan cocacolas");
            }
            else { //Quedan productos, entonces compramos un producto
                depositoCompra.addObjeto(coca.getObjeto());
                vuelto = pago.getValor() - InformacionProducto.COCACOLA.getPrecio();

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
            //Para obtener el objeto, hemos de ver la excepcion NoHayProductoExcepcion
            if (snicker.isEmpty()) {
                monedaDeposito.addObjeto(pago);

                throw new NoHayProductoException("No quedan snickers");
            }
            else { //Quedan productos, entonces compramos un producto
                depositoCompra.addObjeto(snicker.getObjeto());
                vuelto = pago.getValor() - InformacionProducto.SNICKER.getPrecio();

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
            //Para obtener el objeto, hemos de ver la excepcion NoHayProductoExcepcion
            if (super8.isEmpty()) {
                monedaDeposito.addObjeto(pago);

                throw new NoHayProductoException("No quedan super8");
            }
            else { //Quedan productos, entonces compramos un producto
                depositoCompra.addObjeto(super8.getObjeto());
                vuelto = pago.getValor() - InformacionProducto.SUPER8.getPrecio();
                
            }
        }
        break;
    }//end switch


    //Lo siguiente es para manejar el vuelto
    manejarVuelto(vuelto);
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

    /**
     * Getter del deposito especial depositoCompra
     * @return retorna el producto que almacena depositoCompra
     */
    public Producto getProducto(){
        return depositoCompra.getObjeto();
    }

    /**
     * El siguiente metodo está diseñado para ser usado dentro del método comprarProducto
     * Este metodo recibe una cantidad tipo int, y luego, deposita el vuelto en el deposito de monedas
     * @param vuelto es la cantidad de vuelto que ha de ser almacenado
     */
    public void manejarVuelto(int vuelto){
        while (vuelto >= 2000){
            monedaDeposito.addObjeto(new Moneda2000());
            vuelto -= 2000;
        }

        while (vuelto >= 1000){
            monedaDeposito.addObjeto(new Moneda1000());
            vuelto -= 1000;
        }

        while (vuelto >= 500){
            monedaDeposito.addObjeto(new Moneda500());
            vuelto -= 500;
        }

        while (vuelto >= 100){
            monedaDeposito.addObjeto(new Moneda100());
            vuelto -= 100;
        }

        while (vuelto >= 1){
            monedaDeposito.addObjeto(new Moneda1());
            vuelto -= 1;
        }
    }

}
