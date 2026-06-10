package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

import Expendedor.Expendedor;
import Productos.InformacionProducto;

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

        this.depositoProductoComprado = new PanelDeposito(x + 220, y + 50, 160, 80, "Producto comprado");
        this.depositoVuelto = new PanelDeposito(x + 220, y + 150, 160, 160, "Vuelto");
    }

    /**
     * Crea los depositos visuales de productos del expendedor.
     *
     * Inicializa los depositos para CocaCola, Sprite, Fanta, Snickers y
     * Super8 con posiciones fijas dentro del panel.
     */
    private void crearDepositos() {
        depositos.add(new PanelDeposito(x + 20, y + 50, 150, 50, "CocaCola"));
        depositos.add(new PanelDeposito(x + 20, y + 120, 150, 50, "Sprite"));
        depositos.add(new PanelDeposito(x + 20, y + 190, 150, 50, "Fanta"));
        depositos.add(new PanelDeposito(x + 20, y + 260, 150, 50, "Snickers"));
        depositos.add(new PanelDeposito(x + 20, y + 330, 150, 50, "Super8"));
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
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRoundRect(x, y, ancho, alto, 14, 14);

        g2.setColor(Color.GRAY);
        g2.drawRoundRect(x, y, ancho, alto, 14, 14);

        g2.setFont(new Font("Arial", Font.BOLD, 13));
        g2.setColor(Color.BLACK);
        g2.drawString("EXPENDEDOR", x + ancho / 2 - 42, y + 22);

        g2.setColor(Color.GRAY);
        g2.drawLine(x + 10, y + 32, x + ancho - 10, y + 32);

        for (PanelDeposito d : depositos) {
            d.paintComponent(g2);
        }

        depositoProductoComprado.paintComponent(g2);
        depositoVuelto.paintComponent(g2);
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
     * Maneja un click y retorna el deposito visual seleccionado.
     *
     * Este metodo permite delegar el manejo de clicks desde un panel externo
     * sin exponer la lista interna de depositos.
     *
     * @param px coordenada horizontal del click
     * @param py coordenada vertical del click
     * @return deposito clickeado o null si no se hizo click en un deposito
     */
    public PanelDeposito manejarClick(int px, int py) {
        if (!contains(px, py)) {
            return null;
        }

        for (PanelDeposito deposito : depositos) {
            if (deposito.contains(px, py)) {
                return deposito;
            }
        }

        if (depositoProductoComprado.contains(px, py)) {
            return depositoProductoComprado;
        }

        if (depositoVuelto.contains(px, py)) {
            return depositoVuelto;
        }

        return null;
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
     * Muestra el monto total de vuelto como texto en el deposito.
     *
     * @param monto cantidad de dinero devuelta
     */
    public void setVueltoMonto(int monto) {
        depositoVuelto.setEtiqueta("Vuelto: $" + monto);
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

        InformacionProducto[] tipos = {
            InformacionProducto.COCACOLA,
            InformacionProducto.SPRITE,
            InformacionProducto.FANTA,
            InformacionProducto.SNICKER,
            InformacionProducto.SUPER8
        };

        for (int i = 0; i < tipos.length; i++) {
            depositos.get(i).setEtiqueta(
                tipos[i].getNombre() + ": " + expendedor.getCantidadProductos(tipos[i]));
        }
    }
}
