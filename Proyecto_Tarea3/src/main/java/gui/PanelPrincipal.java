package gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PanelPrincipal extends JPanel implements MouseListener {
    private PanelExpendedor exp; //Instancia de segmento del panel correspondiente al expendedor
    private PanelComprador com; //Instancia de segmento del panel correspondiente al comprador


    public PanelPrincipal(){
        this.setBackground(Color.WHITE);
        this.addMouseListener(this);
        exp = new PanelExpendedor(30,30, );
        com = new PanelComprador();
    }
}















