package gui;

import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class PanelExpendedor {

    private int x;
    private int y;

    private int ancho;
    private int alto;

    private final Expendedor expendedor;

    //contiene a cocacola, sprite, entre otros.
    private final List<PanelDeposito> depositos;

    //Como dice su nombre, muestra lo que se compro
    private final PanelDeposito depositoProductoComprado;
    private final PanelDeposito depositoVuelto;

    public PanelExpendedor(
            int x,
            int y,
            Object expendedor) {

        this.x = x;
        this.y = y;

        this.ancho = 400;
        this.alto = 400;

        this.expendedor = expendedor;

        depositos = new ArrayList<>();

        crearDepositos();

        depositoProductoComprado = new PanelDeposito(x + 220, y + 20, 160, 80, "Producto comprado");
        depositoVuelto = new PanelDeposito(x + 220, y + 120, 160, 160, "Vuelto");
    }

    //deposito de los productos del expendedor
    private void crearDepositos() {

        depositos.add(
                new PanelDeposito(
                        x + 20,
                        y + 20,
                        150,
                        50,
                        "CocaCola"
                )
        );

        depositos.add(
                new PanelDeposito(
                        x + 20,
                        y + 90,
                        150,
                        50,
                        "Sprite"
                )
        );

        depositos.add(
                new PanelDeposito(
                        x + 20,
                        y + 160,
                        150,
                        50,
                        "Fanta"
                )
        );

        depositos.add(
                new PanelDeposito(
                        x + 20,
                        y + 230,
                        150,
                        50,
                        "Snickers"
                )
        );

        depositos.add(
                new PanelDeposito(
                        x + 20,
                        y + 300,
                        150,
                        50,
                        "Super8"
                )
        );
    }



    public void paintComponent(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(
                x,
                y,
                ancho,
                alto
        );

        g.setColor(Color.BLACK);
        g.drawRect(x, y, ancho, alto);//Borde
        g.drawString("Expendedor", x + 12, y + 18);


        //muestra los depositos
        for(PanelDeposito d : depositos){
            d.paintComponent(g);
        }

        depositoProductoComprado.paintComponent(g);
        depositoVuelto.paintComponent(g);
    }

    public void setXY(int x,int y){
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

    public boolean contains(int px, int py) {
        return px >= x && px <= (x + ancho) && py >= y && py <= (y + alto);
    }

    public void setStockDeposito(int indice, List<VistaProducto> productos) {
        if (indice < 0 || indice >= depositos.size()) {
            return;
        }
        depositos.get(indice).setProductos(productos);
    }

    public void setProductoComprado(VistaProducto producto) {
        List<VistaProducto> productos = new ArrayList<>();
        if (producto != null) {
            productos.add(producto);
        }
        depositoProductoComprado.setProductos(productos);
    }

    public void setVuelto(List<VistaMoneda> monedas) {
        depositoVuelto.setMonedas(monedas);
    }

    public void refreshDesdeModelo() {
        if (expendedor == null) {
            return;
        }
    }
}
