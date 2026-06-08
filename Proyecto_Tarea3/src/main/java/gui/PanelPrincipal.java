package gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PanelPrincipal extends JPanel implements MouseListener {
    private PanelExpendedor exp; //Instancia de segmento del panel correspondiente al expendedor
    private PanelComprador com; //Instancia de segmento del panel correspondiente al comprador


    public PanelPrincipal(){
        this.setBackground(Color.WHITE); //Color de fondo del panel
        this.addMouseListener(this); //Listener del panel
       // exp = new PanelExpendedor(30,30, ); //Sub panel expendedor (Falta implementar el funcionamiento de exp)
       // com = new PanelComprador(); //Sub panel comprador (Falta implementar el funcionamiento de com)
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); //Limpia la pantalla y pinta el fondo blanco
        // exp.paintComponent(g); //Pinta el expendedor (Falta implementar el funcionamiento de exp)
        //com.paintComponent(g); //Pinta el comprador (Falta implementar el funcionamiento de com
    }
}















