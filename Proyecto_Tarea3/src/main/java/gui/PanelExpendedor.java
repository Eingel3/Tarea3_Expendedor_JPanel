package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import Expendedor.Expendedor;

/**
 * Representa la vista grafica del expendedor completo.
 *
 * Este panel dibuja la estructura del expendedor, los depositos de productos
 * (como CocaCola, Sprite, Fanta, Snickers y Super8), el deposito del producto
 * comprado y el deposito del vuelto
 */
public class PanelExpendedor {

    private int x;
    private int y;

    private int ancho;
    private int alto;

    private final Expendedor expendedor;

    /**
     * Depositos visuales de productos del expendedor.
     */
    private final List<PanelDeposito> depositos;

    /**
     * Deposito visual que muestra el producto comprado.
     */
    private final PanelDeposito depositoProductoComprado;

    /**
     * Deposito visual que muestra monedas de vuelto.
     */
    private final PanelDeposito depositoVuelto;

    /**
     * Construye el panel del expendedor en una posicion dada.
     *
     * @param x coordenada horizontal inicial
     * @param y coordenada vertical inicial
     * @param expendedor referencia al modelo de logica asociado
     */
    public PanelExpendedor(int x, int y, Expendedor expendedor) {
        this.x = x;
        this.y = y;

        this.ancho = 400;
        this.alto = 400;

        this.expendedor = expendedor;

        this.depositos = new ArrayList<>();

        crearDepositos();

        this.depositoProductoComprado = new PanelDeposito(x + 220, y + 20, 160, 80, "Producto comprado");
        this.depositoVuelto = new PanelDeposito(x + 220, y + 120, 160, 160, "Vuelto");
    }

    /**
     * Crea los depositos visuales de productos del expendedor.
     *
     * Inicializa los depositos para CocaCola, Sprite, Fanta, Snickers y
     * Super8 con posiciones fijas dentro del panel.
     */
    private void crearDepositos() {
        depositos.add(new PanelDeposito(x + 20, y + 20, 150, 50, "CocaCola"));
        depositos.add(new PanelDeposito(x + 20, y + 90, 150, 50, "Sprite"));
        depositos.add(new PanelDeposito(x + 20, y + 160, 150, 50, "Fanta"));
        depositos.add(new PanelDeposito(x + 20, y + 230, 150, 50, "Snickers"));
        depositos.add(new PanelDeposito(x + 20, y + 300, 150, 50, "Super8"));
    }

    /**
     * Dibuja el expendedor y todos sus depositos.
     *
     * Incluye fondo, borde, titulo, depositos de productos, deposito de
     * producto comprado y deposito de vuelto.
     *
     * @param g contexto grafico utilizado por Swing/AWT
     */
    public void paintComponent(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(x, y, ancho, alto);

        g.setColor(Color.BLACK);
        g.drawRect(x, y, ancho, alto);
        g.drawString("Expendedor", x + 12, y + 18);

        for (PanelDeposito d : depositos) {
            d.paintComponent(g);
        }

        depositoProductoComprado.paintComponent(g);
        depositoVuelto.paintComponent(g);
    }

    /**
     * Reposiciona el panel y mueve todos sus elementos internos.
     *
     * @param x nueva coordenada horizontal
     * @param y nueva coordenada vertical
     */
    public void setXY(int x, int y) {
        int dx = x - this.x;
        int dy = y - this.y;
        this.x = x;
        this.y = y;

        for (PanelDeposito deposito : depositos) {
            deposito.setXY(deposito.getX() + dx, deposito.getY() + dy);
        }

        depositoProductoComprado.setXY(depositoProductoComprado.getX() + dx, depositoProductoComprado.getY() + dy);
        depositoVuelto.setXY(depositoVuelto.getX() + dx, depositoVuelto.getY() + dy);
    }

    /**
     * Indica si un punto esta dentro del area del expendedor.
     *
     * @param px coordenada horizontal del punto
     * @param py coordenada vertical del punto
     * @return true si el punto cae dentro del panel, false en caso contrario
     */
    public boolean contains(int px, int py) {
        return px >= x && px <= (x + ancho) && py >= y && py <= (y + alto);
    }

    /**
     * Actualiza el contenido visual de un deposito de productos por indice.
     *
     * @param indice posicion del deposito dentro de la lista
     * @param productos vistas de productos a mostrar
     */
    public void setStockDeposito(int indice, List<VistaProducto> productos) {
        if (indice < 0 || indice >= depositos.size()) {
            return;
        }
        depositos.get(indice).setProductos(productos);
    }

    /**
     * Define el producto mostrado como comprado en la vista.
     *
     * @param producto vista del producto comprado; null para vaciar la zona
     */
    public void setProductoComprado(VistaProducto producto) {
        List<VistaProducto> productos = new ArrayList<>();
        if (producto != null) {
            productos.add(producto);
        }
        depositoProductoComprado.setProductos(productos);
    }

    /**
     * Define las monedas de vuelto que se muestran en la vista.
     *
     * @param monedas lista de vistas de monedas de vuelto
     */
    public void setVuelto(List<VistaMoneda> monedas) {
        depositoVuelto.setMonedas(monedas);
    }

    /**
     * Sincroniza la vista con el estado del modelo de logica.
     *
     * Metodo reservado para conectar la vista con {@code Expendedor} sin
     * mezclar logica de negocio dentro de la GUI.
     */
    public void refreshDesdeModelo() {
        if (expendedor == null) {
            return;
        }
    }
}
