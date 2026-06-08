package gui;

import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import logica.Expendedor;

public class PanelExpendedor {

    private int x;
    private int y;

    private int ancho;
    private int alto;

    private Expendedor expendedor;

    private ArrayList<PanelDeposito> depositos;

    public PanelExpendedor(
            int x,
            int y,
            Expendedor expendedor) {

        this.x = x;
        this.y = y;

        this.ancho = 400;
        this.alto = 300;

        this.expendedor = expendedor;

        depositos = new ArrayList<>();

        crearDepositos();
    }

    private void crearDepositos() {

        depositos.add(
                new PanelDeposito(
                        x + 20,
                        y + 20,
                        150,
                        50
                )
        );

        depositos.add(
                new PanelDeposito(
                        x + 20,
                        y + 90,
                        150,
                        50
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

        for(PanelDeposito d : depositos){
            d.paintComponent(g);
        }
    }

    public void setXY(int x,int y){

        this.x = x;
        this.y = y;
    }
}