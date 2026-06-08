package gui;

import java.awt.Color;
import java.awt.Graphics;

import Monedas.Moneda;

public class VistaMoneda {

    private int x;
    private int y;
    private final int diametro;
    private final Moneda moneda;

    public VistaMoneda(int x, int y, int diametro) {
        this(x, y, diametro, null);
    }

    public VistaMoneda(int x, int y, int diametro, Moneda moneda) {
        this.x = x;
        this.y = y;
        this.diametro = diametro;
        this.moneda = moneda;
    }

    public void paint(Graphics g) {
        g.setColor(new Color(240, 200, 80));
        g.fillOval(x, y, diametro, diametro);
        g.setColor(Color.BLACK);
        g.drawOval(x, y, diametro, diametro);

        if (moneda != null) {
            g.drawString(String.valueOf(moneda.getValor()), x + 4, y + (diametro / 2) + 4);
        }
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean contains(int px, int py) {
        int centroX = x + diametro / 2;
        int centroY = y + diametro / 2;
        int dx = px - centroX;
        int dy = py - centroY;
        int radio = diametro / 2;
        return (dx * dx + dy * dy) <= (radio * radio);
    }

    public int getDiametro() {
        return diametro;
    }

    public Moneda getMoneda() {
        return moneda;
    }



}
