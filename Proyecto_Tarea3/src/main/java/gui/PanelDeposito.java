package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class PanelDeposito {

    private static final int PADDING = 6;
    private static final int ESPACIADO = 4;

    private int x;
    private int y;
    private int ancho;
    private int alto;

    private String etiqueta;

    //productos disponibles o visibles en la maquina
    private final List<VistaProducto> productos;
    private final List<VistaMoneda> monedas;

    public PanelDeposito(int x, int y, int ancho, int alto) {
        this(x, y, ancho, alto, "Deposito");
    }

    public PanelDeposito(int x, int y, int ancho, int alto, String etiqueta) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.etiqueta = etiqueta;
        this.productos = new ArrayList<>();
        this.monedas = new ArrayList<>();
    }


    //este metodo dibuja el deposito completo
    public void paintComponent(Graphics g) {
        g.setColor(new Color(210, 210, 210));//color gris
        g.fillRect(x, y, ancho, alto);//Background o fondo
        g.setColor(Color.DARK_GRAY);//Color
        g.drawRect(x, y, ancho, alto);//Borde

        g.drawString(etiqueta, x + 8, y + 16);//Texto

        //dibuja productos
        for (VistaProducto producto : productos) {
            //aca el deposito delega el pintado del producto a la vistaProducto
            producto.paint(g);
        }

        for (VistaMoneda moneda : monedas) {
            //lo mismo con la moneda
            moneda.paint(g);
        }
    }

    //mueve el deposito
    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
        //se llama a reflow para que tambien se muevan los depositos interiores
        reflowContenido();
    }

    //revisa si el click sucedio
    public boolean contains(int px, int py) {
        return px >= x && px <= (x + ancho) && py >= y && py <= (y + alto);
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    //modifica los productos limpiando o eliminando los anteriores
    public void setProductos(List<VistaProducto> nuevosProductos) {
        productos.clear();
        if (nuevosProductos != null) {
            productos.addAll(nuevosProductos);
        }
        monedas.clear();
        reflowContenido();
    }

    //lo mismo con las monedas
    public void setMonedas(List<VistaMoneda> nuevasMonedas) {
        monedas.clear();
        if (nuevasMonedas != null) {
            monedas.addAll(nuevasMonedas);
        }
        productos.clear();
        reflowContenido();
    }

    //metodo que reposiciona elementos comprobando que entren en una liena si no hace un salto
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


    //GETTERS

    public int getCantidadProductos() {
        return productos.size();
    }

    public int getCantidadMonedas() {
        return monedas.size();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }
}
