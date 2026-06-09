package gui;

import Expendedor.Expendedor;
import Excepciones.*;
import Monedas.*;
import Productos.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PanelComprador {

    private int x, y; //Posición del panel dentro de PanelPrincipal
    private static final int ANCHO = 420; //Ancho del panel del comprador
    private static final int ALTO  = 490; //Alto del panel del comprador

    private final Expendedor expendedor; //Referencia al modelo compartido

    private enum Paso { PRODUCTO, MONEDA, FIN } //Estados del flujo de compra
    private Paso paso; //Paso actual del flujo

    private InformacionProducto productoElegido; //Producto seleccionado por el usuario
    private String mensaje; //Mensaje de feedback que se muestra al usuario
    private String ultimoProducto; //Nombre del último producto comprado con éxito
    private int dinero; //Dinero disponible del comprador
    private int vueltoAcumulado; //Total de vuelto recibido hasta ahora

    private BufferedImage[] imagenes = new BufferedImage[5]; //Imágenes png de los productos

    private static final int TARJETA_W  = 72; //Ancho de cada tarjeta de producto
    private static final int TARJETA_H  = 90; //Alto de cada tarjeta de producto
    private static final int TARJETA_Y  = 80; //Posición vertical de las tarjetas (relativo a y)
    private static final int IMAGEN_TAM = 48; //Tamaño del png dentro de la tarjeta

    private static final int BOTON_W   = 88; //Ancho de cada botón de moneda
    private static final int BOTON_H   = 36; //Alto de cada botón de moneda
    private static final int BOTON_X   = 15; //Posición horizontal de los botones (relativo a x)
    private static final int BOTON_Y   = 210; //Posición vertical de los botones (relativo a y)
    private static final int BOTON_SEP = 8; //Separación entre botones de moneda

    private static final InformacionProducto[] PRODUCTOS = { //Productos disponibles en el expendedor
            InformacionProducto.COCACOLA,
            InformacionProducto.FANTA,
            InformacionProducto.SPRITE,
            InformacionProducto.SNICKER,
            InformacionProducto.SUPER8
    };

    private static final String[] ARCHIVOS_PNG = { //Nombres de los archivos png en resources/
            "cocacola.png", "fanta.png", "sprite.png", "snicker.png", "super8.png"
    };

    private static final int[] MONEDAS = { 100, 500, 1000, 2000 }; //Denominaciones disponibles

    public PanelComprador(int x, int y, Expendedor expendedor) {
        this.x              = x; //Posición horizontal dentro del panel principal
        this.y              = y; //Posición vertical dentro del panel principal
        this.expendedor     = expendedor; //Referencia al expendedor compartido con PanelExpendedor
        this.dinero         = 5000; //El comprador empieza con $5000
        this.vueltoAcumulado = 0; //Todavía no ha recibido vuelto
        this.paso           = Paso.PRODUCTO; //Estado inicial: eligiendo producto
        this.mensaje        = "Elige un producto"; //Mensaje inicial
        cargarImagenes(); //Intentar cargar los pngs desde resources/
    }

    private void cargarImagenes() {
        for (int i = 0; i < ARCHIVOS_PNG.length; i++) { //Recorre los nombres de archivos
            try {
                imagenes[i] = ImageIO.read( //Intenta leer el png desde el classpath
                        getClass().getClassLoader().getResourceAsStream(ARCHIVOS_PNG[i])
                );
            } catch (IOException | IllegalArgumentException e) {
                imagenes[i] = null; //Si no cargó se dibuja un rectángulo de color en su lugar
            }
        }
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g; //Cast para usar funciones de Graphics2D
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //Bordes suavizados

        g2.setColor(Color.LIGHT_GRAY); //Color de fondo del panel
        g2.fillRoundRect(x, y, ANCHO, ALTO, 14, 14); //Fondo del panel con esquinas redondeadas
        g2.setColor(Color.GRAY); //Color del borde
        g2.drawRoundRect(x, y, ANCHO, ALTO, 14, 14); //Borde del panel

        g2.setFont(new Font("Arial", Font.BOLD, 13)); //Fuente del título
        g2.setColor(Color.BLACK); //Color del título
        g2.drawString("COMPRADOR", x + ANCHO/2 - 38, y + 22); //Título centrado

        g2.setFont(new Font("Arial", Font.PLAIN, 11)); //Fuente para información del comprador
        g2.setColor(Color.GREEN.darker()); //Verde para el dinero disponible
        g2.drawString("Dinero: $" + dinero, x + 14, y + 44); //Muestra el dinero actual
        g2.setColor(Color.BLACK); //Color del vuelto acumulado
        g2.drawString("Vuelto acumulado: $" + vueltoAcumulado, x + 14, y + 60); //Muestra el vuelto total recibido

        if (ultimoProducto != null) { //Solo se muestra si hubo una compra exitosa
            g2.setColor(Color.BLACK); //Color del último producto
            g2.drawString("Último: " + ultimoProducto, x + ANCHO - 130, y + 44); //Muestra el último comprado
        }

        g2.setColor(Color.GRAY); //Color de la línea separadora
        g2.drawLine(x + 10, y + 62, x + ANCHO - 10, y + 68); //Línea que separa el encabezado del contenido

        int anchoTotal = PRODUCTOS.length * TARJETA_W + (PRODUCTOS.length - 1) * 8; //Ancho total de las tarjetas
        int inicioX    = x + (ANCHO - anchoTotal) / 2; //Posición x para centrar las tarjetas

        g2.setFont(new Font("Arial", Font.BOLD, 10)); //Fuente para el label de sección
        g2.setColor(Color.DARK_GRAY); //Color del label
        g2.drawString((paso == Paso.PRODUCTO) ? "▶ PRODUCTO" : "  PRODUCTO", x + 14, y + TARJETA_Y - 6); //Indica si es el paso activo

        g2.setFont(new Font("Arial", Font.BOLD, 9)); //Fuente para el texto dentro de las tarjetas
        for (int i = 0; i < PRODUCTOS.length; i++) {
            int tarjX   = inicioX + i * (TARJETA_W + 8); //Posición horizontal de esta tarjeta
            int tarjY   = y + TARJETA_Y; //Posición vertical de esta tarjeta
            boolean elegida = (PRODUCTOS[i] == productoElegido); //Si este producto fue seleccionado
            boolean activa  = (paso == Paso.PRODUCTO); //Si es el turno de elegir producto

            if (!activa)       g2.setColor(Color.LIGHT_GRAY); //Oscurecido si no es el paso activo
            else if (elegida)  g2.setColor(Color.CYAN); //Resaltado si está elegido
            else               g2.setColor(Color.WHITE); //Blanco si está disponible para elegir
            g2.fillRoundRect(tarjX, tarjY, TARJETA_W, TARJETA_H, 8, 8); //Fondo de la tarjeta

            g2.setColor(elegida ? Color.BLUE : Color.GRAY); //Borde azul si elegido, gris si no
            g2.drawRoundRect(tarjX, tarjY, TARJETA_W, TARJETA_H, 8, 8); //Borde de la tarjeta

            int imgX = tarjX + (TARJETA_W - IMAGEN_TAM) / 2; //Centrar la imagen horizontalmente
            int imgY = tarjY + 6; //Pequeño margen arriba de la imagen
            if (imagenes[i] != null) { //Si el png cargó correctamente
                g2.drawImage(imagenes[i], imgX, imgY, IMAGEN_TAM, IMAGEN_TAM, null); //Dibuja el png del producto
            } //Si el png no cargó no se dibuja nada

            g2.setColor(activa ? Color.BLACK : Color.GRAY); //Texto oscuro si activo, gris si no
            g2.drawString(nombreProducto(PRODUCTOS[i]), tarjX + 4, tarjY + TARJETA_H - 22); //Nombre del producto
            g2.setColor(Color.DARK_GRAY); //Color del precio
            g2.drawString("$" + PRODUCTOS[i].getPrecio(), tarjX + 4, tarjY + TARJETA_H - 10); //Precio del producto
        }

        if (paso == Paso.MONEDA) { //Botón cancelar solo visible cuando se está eligiendo moneda
            g2.setColor(Color.RED); //Rojo para el botón cancelar
            g2.fillRoundRect(x + ANCHO - 90, y + TARJETA_Y, 75, 28, 8, 8); //Fondo del botón cancelar
            g2.setColor(Color.RED.darker()); //Borde más oscuro
            g2.drawRoundRect(x + ANCHO - 90, y + TARJETA_Y, 75, 28, 8, 8); //Borde del botón cancelar
            g2.setColor(Color.WHITE); //Texto blanco
            g2.setFont(new Font("Arial", Font.BOLD, 11)); //Fuente del texto cancelar
            g2.drawString("Cancelar", x + ANCHO - 83, y + TARJETA_Y + 18); //Texto del botón
        }

        g2.setFont(new Font("Arial", Font.BOLD, 10)); //Fuente para el label de moneda
        g2.setColor(Color.DARK_GRAY); //Color del label
        g2.drawString((paso == Paso.MONEDA) ? "▶ MONEDA" : "  MONEDA", x + 14, y + BOTON_Y - 6); //Indica si es el paso activo

        g2.setFont(new Font("Arial", Font.BOLD, 12)); //Fuente para el valor de la moneda
        for (int i = 0; i < MONEDAS.length; i++) {
            int botonX = x + BOTON_X + i * (BOTON_W + BOTON_SEP); //Posición horizontal del botón
            int botonY = y + BOTON_Y; //Posición vertical del botón
            boolean activa = (paso == Paso.MONEDA); //Si es el turno de elegir moneda

            g2.setColor(activa ? Color.DARK_GRAY : Color.LIGHT_GRAY); //Gris oscuro si activo, claro si no
            g2.fillRoundRect(botonX, botonY, BOTON_W, BOTON_H, 8, 8); //Fondo del botón
            g2.setColor(activa ? Color.BLACK : Color.GRAY); //Borde del botón
            g2.drawRoundRect(botonX, botonY, BOTON_W, BOTON_H, 8, 8); //Borde del botón

            g2.setColor(activa ? Color.WHITE : Color.GRAY); //Texto blanco si activo, gris si no
            g2.drawString("$" + MONEDAS[i], botonX + BOTON_W/2 - 18, botonY + BOTON_H/2 + 5); //Valor de la moneda centrado
        }

        int mensajeY = y + ALTO - 70; //Posición vertical del área de mensaje
        g2.setColor(Color.LIGHT_GRAY); //Fondo del área de mensaje
        g2.fillRoundRect(x + 10, mensajeY, ANCHO - 20, 50, 8, 8); //Dibuja el área de mensaje

        boolean esError = mensaje.contains("insuficiente") //Detecta si el mensaje es de error
                || mensaje.contains("stock")
                || mensaje.contains("tienes");
        g2.setColor(esError ? Color.RED : Color.GREEN.darker()); //Rojo si error, verde si éxito
        g2.setFont(new Font("Arial", Font.BOLD, 11)); //Fuente del mensaje
        g2.drawString(mensaje, x + 18, mensajeY + 20); //Muestra el mensaje de feedback

        if (paso == Paso.FIN) { //Solo se muestra cuando hay un resultado que ver
            g2.setColor(Color.DARK_GRAY); //Color del texto de instrucción
            g2.setFont(new Font("Arial", Font.PLAIN, 10)); //Fuente más pequeña para la instrucción
            g2.drawString("Click para nueva compra", x + 18, mensajeY + 38); //Indica que se puede reiniciar
        }
    }

    public void manejarClick(int mx, int my) {
        if (mx < x || mx > x + ANCHO || my < y || my > y + ALTO) return; //Ignorar clicks fuera del panel

        if (paso == Paso.FIN) { //En estado FIN cualquier click reinicia el flujo
            reiniciar();
            return;
        }

        int anchoTotal = PRODUCTOS.length * TARJETA_W + (PRODUCTOS.length - 1) * 8; //Igual que en paintComponent
        int inicioX    = x + (ANCHO - anchoTotal) / 2; //Para saber dónde están las tarjetas

        for (int i = 0; i < PRODUCTOS.length; i++) {
            int tarjX = inicioX + i * (TARJETA_W + 8); //Posición horizontal de esta tarjeta
            int tarjY = y + TARJETA_Y; //Posición vertical de esta tarjeta
            if (paso == Paso.PRODUCTO //Solo si es el paso de elegir producto
                    && mx >= tarjX && mx <= tarjX + TARJETA_W
                    && my >= tarjY && my <= tarjY + TARJETA_H) {
                productoElegido = PRODUCTOS[i]; //Guardar el producto elegido
                paso    = Paso.MONEDA; //Avanzar al siguiente paso
                mensaje = "Elegiste " + nombreProducto(productoElegido) + ". Ahora elige una moneda";
                return;
            }
        }

        if (paso == Paso.MONEDA //Click en el botón cancelar
                && mx >= x + ANCHO - 90 && mx <= x + ANCHO - 15
                && my >= y + TARJETA_Y && my <= y + TARJETA_Y + 28) {
            reiniciar(); //Volver al paso de elegir producto
            return;
        }

        for (int i = 0; i < MONEDAS.length; i++) {
            int botonX = x + BOTON_X + i * (BOTON_W + BOTON_SEP); //Posición horizontal del botón
            int botonY = y + BOTON_Y; //Posición vertical del botón
            if (paso == Paso.MONEDA //Solo si es el paso de elegir moneda
                    && mx >= botonX && mx <= botonX + BOTON_W
                    && my >= botonY && my <= botonY + BOTON_H) {
                procesarCompra(MONEDAS[i]); //Intentar la compra con la moneda elegida
                return;
            }
        }
    }

    private void procesarCompra(int valorMoneda) {
        if (dinero < valorMoneda) { //Verificar que el comprador tiene suficiente dinero
            mensaje = "No tienes $" + valorMoneda + " disponibles";
            paso    = Paso.FIN;
            return;
        }

        Moneda moneda = crearMoneda(valorMoneda); //Crear la instancia de moneda correspondiente
        try {
            expendedor.comprarProducto(productoElegido.getTipo(), moneda); //Intentar la compra en el expendedor

            Producto producto = expendedor.getProducto(); //Sacar el producto del depósito especial
            ultimoProducto   = (producto != null) ? producto.consumir() : nombreProducto(productoElegido);
            dinero          -= valorMoneda; //Descontar el valor de la moneda insertada

            Moneda devuelta;
            while ((devuelta = expendedor.getVuelto()) != null) { //Recoger todo el vuelto del expendedor
                dinero          += devuelta.getValor(); //Sumar el vuelto al dinero disponible
                vueltoAcumulado += devuelta.getValor(); //Acumular el total de vuelto recibido
            }
            mensaje = "Compra exitosa: " + ultimoProducto
                    + (producto != null ? " #" + producto.getSerie() : ""); //Mostrar nombre y número de serie

        } catch (PagoInsuficienteException e) {
            Moneda devuelta = expendedor.getVuelto(); //El expendedor devuelve la moneda rechazada
            if (devuelta != null) dinero += devuelta.getValor(); //Recuperar la moneda sin descontar
            mensaje = "Moneda insuficiente para " + nombreProducto(productoElegido);

        } catch (NoHayProductoException e) {
            Moneda devuelta = expendedor.getVuelto(); //El expendedor devuelve la moneda si no hay stock
            if (devuelta != null) dinero += devuelta.getValor(); //Recuperar la moneda sin descontar
            mensaje = "Sin stock de " + nombreProducto(productoElegido);

        } catch (PagoIncorrectoException e) {
            mensaje = "Error de pago"; //No debería ocurrir ya que la moneda nunca es null aquí
        }

        paso = Paso.FIN; //Mostrar el resultado al usuario
    }

    private void reiniciar() {
        productoElegido = null; //Limpiar el producto elegido anteriormente
        paso    = Paso.PRODUCTO; //Volver al primer paso
        mensaje = "Elige un producto"; //Restablecer el mensaje inicial
    }

    private Moneda crearMoneda(int valor) {
        switch (valor) {
            case 100:  return new Moneda100(); //Moneda de cien pesos
            case 500:  return new Moneda500(); //Moneda de quinientos pesos
            case 1000: return new Moneda1000(); //Billete de mil pesos
            default:   return new Moneda2000(); //Billete de dos mil pesos
        }
    }

    private String nombreProducto(InformacionProducto p) { //Retorna el nombre legible del producto
        switch (p) {
            case COCACOLA: return "Coca-Cola";
            case FANTA:    return "Fanta";
            case SPRITE:   return "Sprite";
            case SNICKER:  return "Snicker";
            default:       return "Super8";
        }
    }

}