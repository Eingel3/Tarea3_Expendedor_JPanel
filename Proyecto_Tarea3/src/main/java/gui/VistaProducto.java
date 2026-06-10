package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

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
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(new Color(225, 252, 255));
        g2.fillRoundRect(x, y, ancho, alto, 8, 8);
        g2.setColor(Color.CYAN.darker());
        g2.drawRoundRect(x, y, ancho, alto, 8, 8);

        g2.setFont(new Font("Arial", Font.BOLD, 10));
        g2.setColor(Color.BLACK);
        g2.drawString(etiqueta, x + 5, y + (alto / 2) + 4);
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
