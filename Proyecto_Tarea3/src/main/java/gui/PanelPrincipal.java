package gui;

import Expendedor.Expendedor;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Panel principal de la interfaz gráfica de la máquina expendedora.
 * <p>
 * Esta clase extiende {@link JPanel} e implementa {@link MouseListener} para
 * capturar los clicks realizados por el usuario sobre la interfaz.
 * </p>
 * <p>
 * El panel principal contiene y coordina dos subpaneles:
 * </p>
 * <ul>
 *     <li>{@link PanelExpendedor}: representa visualmente la máquina expendedora.</li>
 *     <li>{@link PanelComprador}: representa al comprador y su interacción de compra.</li>
 * </ul>
 * <p>
 * Ambos subpaneles comparten una misma instancia de {@link Expendedor}, lo que
 * permite mantener sincronizado el estado de la máquina entre la vista del
 * expendedor y la vista del comprador.
 * </p>
 */
public class PanelPrincipal extends JPanel implements MouseListener {
    private final int ANCHO = 950; //Ancho del panel en pixeles
    private final int ALTO = 600; //Alto del panel en pixeles

    private PanelExpendedor exp; //Instancia de segmento del panel correspondiente al expendedor
    private PanelComprador com; //Instancia de segmento del panel correspondiente al comprador

    /**
     * Crea e inicializa el panel principal de la aplicación.
     * <p>
     * Configura las dimensiones del panel, el color de fondo y registra el
     * listener de mouse. Además, crea una instancia compartida del modelo
     * {@link Expendedor} y la entrega a los subpaneles del expendedor y comprador.
     * </p>
     */
    public PanelPrincipal() {
        this.setPreferredSize(new Dimension(ANCHO, ALTO)); //Dimensiones del panel
        this.setBackground(Color.WHITE); //Color de fondo del panel
        this.addMouseListener(this); //Listener del panel
        Expendedor expendedor = new Expendedor(5); //Instancia del expendedor
        exp = new PanelExpendedor(30, 80, expendedor); //Sub panel expendedor
        com = new PanelComprador(370, 80, expendedor); //Sub panel comprador
    }

    /**
     * Dibuja los componentes gráficos del panel principal.
     * <p>
     * Primero limpia el panel mediante la implementación de {@link JPanel} y
     * luego delega el dibujo de cada sección a los subpaneles del expendedor
     * y del comprador.
     * </p>
     *
     * @param g contexto gráfico utilizado para dibujar sobre el panel.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); //Limpia la pantalla y pinta el fondo blanco
        exp.paintComponent(g); //Pinta el expendedor
        com.paintComponent(g); //Pinta el comprador
    }

    /**
     * Maneja el evento de click del mouse sobre el panel principal.
     * <p>
     * Obtiene las coordenadas del click y las delega a los subpaneles para que
     * cada uno determine si debe reaccionar al evento. Finalmente, repinta el
     * panel para reflejar posibles cambios visuales.
     * </p>
     *
     * @param e evento de mouse que contiene la posición del click.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX(); //Obtenemos coordenadas del mouse
        int mouseY = e.getY();

        exp.manejarClick(mouseX, mouseY); //Llamamos al metodo manejarClick del expendedor
        com.manejarClick(mouseX, mouseY);
        repaint();
    }

    /**
     * Método requerido por {@link MouseListener}.
     * <p>
     * No realiza ninguna acción cuando se presiona el botón del mouse.
     * </p>
     *
     * @param e evento de mouse recibido.
     */
    @Override
    public void mousePressed(MouseEvent e) {}

    /**
     * Método requerido por {@link MouseListener}.
     * <p>
     * No realiza ninguna acción cuando se suelta el botón del mouse.
     * </p>
     *
     * @param e evento de mouse recibido.
     */
    @Override
    public void mouseReleased(MouseEvent e) {}

    /**
     * Método requerido por {@link MouseListener}.
     * <p>
     * No realiza ninguna acción cuando el cursor entra al panel.
     * </p>
     *
     * @param e evento de mouse recibido.
     */
    @Override
    public void mouseEntered(MouseEvent e) {}

    /**
     * Método requerido por {@link MouseListener}.
     * <p>
     * No realiza ninguna acción cuando el cursor sale del panel.
     * </p>
     *
     * @param e evento de mouse recibido.
     */
    @Override
    public void mouseExited(MouseEvent e) {}
}