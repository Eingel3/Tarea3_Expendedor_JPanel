package gui;

import javax.swing.JFrame;

public class Ventana extends JFrame {

    public Ventana() {
        super("Expendedor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        add(new PanelPrincipal());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
