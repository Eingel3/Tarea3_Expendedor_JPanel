package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import Monedas.Moneda;

/**
 * Representa la vista grafica de una moneda en la interfaz.
 *
 * <p>Esta clase maneja solo informacion visual (posicion y tamano) junto con
 * una referencia a la moneda del modelo para mostrar su valor.</p>
 */
public class VistaMoneda {

    private int x;
    private int y;
    private final int diametro;
    private final Moneda moneda;

    /**
     * Crea una vista de moneda sin referencia a moneda de logica.
     *
     * @param x coordenada horizontal inicial
     * @param y coordenada vertical inicial
     * @param diametro diametro visual de la moneda
     */
    public VistaMoneda(int x, int y, int diametro) {
        this(x, y, diametro, null);
    }

    /**
     * Crea una vista de moneda asociada a una moneda del modelo.
     *
     * @param x coordenada horizontal inicial
     * @param y coordenada vertical inicial
     * @param diametro diametro visual de la moneda
     * @param moneda moneda de logica asociada a la vista
     */
    public VistaMoneda(int x, int y, int diametro, Moneda moneda) {
        this.x = x;
        this.y = y;
        this.diametro = diametro;
        this.moneda = moneda;
    }

    /**
     * Dibuja la moneda en el contexto grafico entregado.
     *
     * <p>Si existe una moneda asociada, se muestra su valor dentro del circulo.</p>
     *
     * @param g contexto grafico de Swing/AWT
     */
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(new Color(255, 230, 130));
        g2.fillOval(x, y, diametro, diametro);
        g2.setColor(Color.GRAY);
        g2.drawOval(x, y, diametro, diametro);

        if (moneda != null) {
            g2.setFont(new Font("Arial", Font.BOLD, 10));
            g2.setColor(Color.BLACK);
            g2.drawString(String.valueOf(moneda.getValor()), x + 4, y + (diametro / 2) + 4);
        }
    }

    /**
     * Reposiciona la vista de la moneda.
     *
     * @param x nueva coordenada horizontal
     * @param y nueva coordenada vertical
     */
    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Indica si un punto se encuentra dentro del area circular de la moneda.
     *
     * @param px coordenada horizontal del punto
     * @param py coordenada vertical del punto
     * @return true si el punto esta dentro del circulo, false en caso contrario
     */
    public boolean contains(int px, int py) {
        int centroX = x + diametro / 2;
        int centroY = y + diametro / 2;
        int dx = px - centroX;
        int dy = py - centroY;
        int radio = diametro / 2;
        return (dx * dx + dy * dy) <= (radio * radio);
    }

    /**
     * Obtiene el diametro visual de la moneda.
     *
     * @return diametro en pixeles
     */
    public int getDiametro() {
        return diametro;
    }

    /**
     * Obtiene la moneda del modelo asociada a esta vista.
     *
     * @return moneda asociada o null si no existe
     */
    public Moneda getMoneda() {
        return moneda;
    }
}
