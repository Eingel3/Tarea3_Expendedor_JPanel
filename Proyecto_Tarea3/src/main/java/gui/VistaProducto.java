package gui;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Representa la vista grafica de un producto dentro de la interfaz.
 *
 * Esta clase solo maneja informacion visual (posicion, tamano y etiqueta)
 * y no contiene logica de negocio del expendedor.
 */
public class VistaProducto {

    private int x;
    private int y;
    private final int ancho;
    private final int alto;
    private final String etiqueta;

    /**
     * Crea una vista de producto con etiqueta por defecto.
     *
     * @param x coordenada horizontal inicial
     * @param y coordenada vertical inicial
     * @param ancho ancho de la vista
     * @param alto alto de la vista
     */
    public VistaProducto(int x, int y, int ancho, int alto) {
        this(x, y, ancho, alto, "P");
    }

    /**
     * Crea una vista de producto con etiqueta personalizada.
     *
     * @param x coordenada horizontal inicial
     * @param y coordenada vertical inicial
     * @param ancho ancho de la vista
     * @param alto alto de la vista
     * @param etiqueta texto mostrado en la vista del producto
     */
    public VistaProducto(int x, int y, int ancho, int alto, String etiqueta) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.etiqueta = etiqueta;
    }

    /**
     * Dibuja el producto en el contexto grafico entregado.
     *
     * @param g contexto de dibujo de Swing/AWT
     */
    public void paint(Graphics g) {
        g.setColor(new Color(80, 170, 120));//como un verde claro
        g.fillRect(x, y, ancho, alto);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, ancho, alto);
        g.drawString(etiqueta, x + 5, y + (alto / 2) + 4);
    }

    /**
     * Reposiciona la vista del producto.
     *
     * @param x nueva coordenada horizontal
     * @param y nueva coordenada vertical
     */
    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Indica si un punto esta dentro del area del producto.
     *
     * @param px coordenada horizontal del punto
     * @param py coordenada vertical del punto
     * @return true si el punto cae dentro de la vista, false en caso contrario
     */
    public boolean contains(int px, int py) {
        return px >= x && px <= (x + ancho) && py >= y && py <= (y + alto);
    }

    /**
     * Obtiene el ancho visual del producto.
     *
     * @return ancho de la vista
     */
    public int getAncho() {
        return ancho;
    }

    /**
     * Obtiene el alto visual del producto.
     *
     * @return alto de la vista
     */
    public int getAlto() {
        return alto;
    }
}
