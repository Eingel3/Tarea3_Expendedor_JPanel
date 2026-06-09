package gui;

import Expendedor.Expendedor;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PanelPrincipal extends JPanel implements MouseListener {
    private final int ANCHO = 950; //Ancho del panel en pixeles
    private final int ALTO = 600; //Alto del panel en pixeles

    private PanelExpendedor exp; //Instancia de segmento del panel correspondiente al expendedor
    private PanelComprador com; //Instancia de segmento del panel correspondiente al comprador


    public PanelPrincipal(){
        this.setPreferredSize(new Dimension(ANCHO, ALTO)); //Dimensiones del panel
        this.setBackground(Color.WHITE); //Color de fondo del panel
        this.addMouseListener(this); //Listener del panel
        Expendedor expendedor = new Expendedor(5); //Instancia del expendedor
        exp = new PanelExpendedor(30,80,expendedor); //Sub panel expendedor
        com = new PanelComprador(); //Sub panel comprador (Falta implementar el funcionamiento de com)
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); //Limpia la pantalla y pinta el fondo blanco
        exp.paintComponent(g); //Pinta el expendedor (Falta implementar el funcionamiento de exp)
        //com.paintComponent(g); //Pinta el comprador (Falta implementar el funcionamiento de com
    }

    @Override
    public void mouseClicked(MouseEvent e){
        int mouseX = e.getX();
        int mouseY = e.getY();

    }
}















