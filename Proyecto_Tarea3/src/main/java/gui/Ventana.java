package gui;

import javax.swing.*;
import java.awt.*;


public class Ventana extends JFrame{
    public Ventana(){
        PanelPrincipal panel = new PanelPrincipal(); //Declaración del panel
        this.setTitle("Máquina Expendedora"); //Título de la ventana
        this.setSize(800, 600); //Dimensiones en píxeles
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Determina que ocurre al presionar el botón de cerrar
        this.setResizable(false); //Indica que la ventana no es redimensionable
        this.setLocationRelativeTo(null); //Centra la ventana en la pantalla
        this.setVisible(true); //Hace visible la ventana
        this.add(panel, BorderLayout.CENTER); //Añade el panel a la ventana
    }
}


