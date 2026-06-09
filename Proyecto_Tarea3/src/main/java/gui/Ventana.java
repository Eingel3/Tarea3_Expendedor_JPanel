package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Representa la ventana principal de la aplicación de la máquina expendedora.
 * <p>
 * Esta clase extiende {@link JFrame} y se encarga de configurar la ventana
 * donde se muestra el panel principal de la interfaz gráfica.
 * </p>
 * <p>
 * La ventana contiene una instancia de {@link PanelPrincipal}, la cual organiza
 * y dibuja los componentes principales de la máquina expendedora.
 * </p>
 */
public class Ventana extends JFrame {

    /**
     * Crea e inicializa la ventana principal de la aplicación.
     * <p>
     * Configura el título, tamaño, operación de cierre, comportamiento de
     * redimensionamiento, ubicación inicial y agrega el panel principal al centro
     * de la ventana.
     * </p>
     */
    public Ventana() {
        PanelPrincipal panel = new PanelPrincipal(); //Declaración del panel
        this.setTitle("Máquina Expendedora"); //Título de la ventana
        this.setSize(800, 600); //Dimensiones en píxeles
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Determina que ocurre al presionar el botón de cerrar
        this.setResizable(false); //Indica que la ventana no es redimensionable
        this.setLocationRelativeTo(null); //Centra la ventana en la pantalla
        this.add(panel, BorderLayout.CENTER); //Añade el panel a la ventana
        this.setVisible(true); //Hace visible la ventana
    }
}