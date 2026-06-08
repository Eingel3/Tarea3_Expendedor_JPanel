package gui;

import java.awt.Color;
import java.awt.Graphics;

public class VistaProducto {

    private int x;
    private int y;
    private final int ancho;
    private final int alto;
    private final String etiqueta;

    public VistaProducto(int x, int y, int ancho, int alto) {
        this(x, y, ancho, alto, "P");
    }

    public VistaProducto(int x, int y, int ancho, int alto, String etiqueta) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.etiqueta = etiqueta;
    }

    public void paint(Graphics g) {
        g.setColor(new Color(80, 170, 120));//como un verde claro
        g.fillRect(x, y, ancho, alto);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, ancho, alto);
        g.drawString(etiqueta, x + 5, y + (alto / 2) + 4);
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean contains(int px, int py) {
        return px >= x && px <= (x + ancho) && py >= y && py <= (y + alto);
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }
}
