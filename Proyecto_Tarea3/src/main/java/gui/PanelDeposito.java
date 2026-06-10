package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa la vista grafica de un deposito dentro del expendedor.
 *
 * <p>Este panel puede mostrar productos o monedas visibles en la maquina,
 * dibujar su contenedor y reposicionar automaticamente el contenido cuando
 * cambian las coordenadas o la cantidad de elementos.</p>
 */
public class PanelDeposito {

    /** Padding interno para separar contenido del borde. */
    private static final int PADDING = 6;

    /** Espacio horizontal/vertical entre elementos internos. */
    private static final int ESPACIADO = 4;

    private int x;
    private int y;
    private int ancho;
    private int alto;

    private String etiqueta;

    /** Productos disponibles o visibles en la maquina. */
    private final List<VistaProducto> productos;

    /** Monedas disponibles o visibles en la maquina. */
    private final List<VistaMoneda> monedas;

    /**
     * Construye un deposito con etiqueta por defecto.
     *
     * @param x coordenada horizontal inicial
     * @param y coordenada vertical inicial
     * @param ancho ancho del deposito
     * @param alto alto del deposito
     */
    public PanelDeposito(int x, int y, int ancho, int alto) {
        this(x, y, ancho, alto, "Deposito");
    }

    /**
     * Construye un deposito con etiqueta personalizada.
     *
     * @param x coordenada horizontal inicial
     * @param y coordenada vertical inicial
     * @param ancho ancho del deposito
     * @param alto alto del deposito
     * @param etiqueta texto identificador del deposito
     */
    public PanelDeposito(int x, int y, int ancho, int alto, String etiqueta) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.etiqueta = etiqueta;
        this.productos = new ArrayList<>();
        this.monedas = new ArrayList<>();
    }

    /**
     * Dibuja el deposito completo: fondo, borde, etiqueta y contenido.
     *
     * <p>El pintado de productos y monedas se delega a sus respectivas clases
     * de vista.</p>
     *
     * @param g contexto grafico de Swing/AWT
     */
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.WHITE);
        g2.fillRoundRect(x, y, ancho, alto, 8, 8);

        g2.setColor(Color.GRAY);
        g2.drawRoundRect(x, y, ancho, alto, 8, 8);

        g2.setFont(new Font("Arial", Font.BOLD, 11));
        g2.setColor(Color.DARK_GRAY);
        g2.drawString(etiqueta, x + 8, y + 16);

        for (VistaProducto producto : productos) {
            producto.paint(g2);
        }

        for (VistaMoneda moneda : monedas) {
            moneda.paint(g2);
        }
    }

    /**
     * Reposiciona el deposito y reorganiza su contenido interno.
     *
     * @param x nueva coordenada horizontal
     * @param y nueva coordenada vertical
     */
    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
        reflowContenido();
    }

    /**
     * Verifica si un punto cae dentro del area del deposito.
     *
     * @param px coordenada horizontal del punto
     * @param py coordenada vertical del punto
     * @return true si el punto esta dentro del deposito, false si no
     */
    public boolean contains(int px, int py) {
        return px >= x && px <= (x + ancho) && py >= y && py <= (y + alto);
    }

    /**
     * Actualiza la etiqueta mostrada en el deposito.
     *
     * @param etiqueta nuevo texto de la etiqueta
     */
    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    /**
     * Reemplaza los productos visibles del deposito.
     *
     * <p>Primero limpia productos anteriores, elimina monedas visibles y luego
     * reposiciona automaticamente el nuevo contenido.</p>
     *
     * @param nuevosProductos lista de productos a mostrar
     */
    public void setProductos(List<VistaProducto> nuevosProductos) {
        productos.clear();
        if (nuevosProductos != null) {
            productos.addAll(nuevosProductos);
        }
        monedas.clear();
        reflowContenido();
    }

    /**
     * Reemplaza las monedas visibles del deposito.
     *
     * <p>Primero limpia monedas anteriores, elimina productos visibles y luego
     * reposiciona automaticamente el nuevo contenido.</p>
     *
     * @param nuevasMonedas lista de monedas a mostrar
     */
    public void setMonedas(List<VistaMoneda> nuevasMonedas) {
        monedas.clear();
        if (nuevasMonedas != null) {
            monedas.addAll(nuevasMonedas);
        }
        productos.clear();
        reflowContenido();
    }

    /**
     * Reposiciona productos/monedas en filas dentro del deposito.
     *
     * <p>Si un elemento no entra en la linea actual, realiza salto a la
     * siguiente fila para mantener el contenido dentro del panel.</p>
     */
    public void reflowContenido() {
        int inicioX = x + PADDING;
        int inicioY = y + 22;

        if (!productos.isEmpty()) {
            int actualX = inicioX;
            int actualY = inicioY;

            for (VistaProducto producto : productos) {
                if ((actualX + producto.getAncho()) > (x + ancho - PADDING)) {
                    actualX = inicioX;
                    actualY += producto.getAlto() + ESPACIADO;
                }
                producto.setXY(actualX, actualY);
                actualX += producto.getAncho() + ESPACIADO;
            }
        }

        if (!monedas.isEmpty()) {
            int actualX = inicioX;
            int actualY = inicioY;

            for (VistaMoneda moneda : monedas) {
                if ((actualX + moneda.getDiametro()) > (x + ancho - PADDING)) {
                    actualX = inicioX;
                    actualY += moneda.getDiametro() + ESPACIADO;
                }
                moneda.setXY(actualX, actualY);
                actualX += moneda.getDiametro() + ESPACIADO;
            }
        }
    }

    /**
     * Obtiene la cantidad de productos visibles en el deposito.
     *
     * @return cantidad de productos
     */
    public int getCantidadProductos() {
        return productos.size();
    }

    /**
     * Obtiene la cantidad de monedas visibles en el deposito.
     *
     * @return cantidad de monedas
     */
    public int getCantidadMonedas() {
        return monedas.size();
    }

    /**
     * Obtiene la coordenada horizontal del deposito.
     *
     * @return coordenada x
     */
    public int getX() {
        return x;
    }

    /**
     * Obtiene la coordenada vertical del deposito.
     *
     * @return coordenada y
     */
    public int getY() {
        return y;
    }

    /**
     * Obtiene el ancho del deposito.
     *
     * @return ancho en pixeles
     */
    public int getAncho() {
        return ancho;
    }

    /**
     * Obtiene el alto del deposito.
     *
     * @return alto en pixeles
     */
    public int getAlto() {
        return alto;
    }
}
