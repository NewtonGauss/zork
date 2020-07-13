package zork;

import java.io.IOException;

import javax.swing.JOptionPane;

public class InputOutput {

    private String nombreJugador;
    private GUI gui;

    public void setGui(GUI gui) {
	this.gui = gui;
    }
    
    public GUI getGUI() {
	return this.gui;
    }
    
    public void setNombreJugador(String nombreJugador) {
	this.nombreJugador = nombreJugador;
    }

    public String getNombreJugador() {
	return nombreJugador;
    }

    public void cargarHistoria(String pathHistoria) {
	try {
	    Juego.getInstancia().cargarHistoria(pathHistoria);
	} catch (IOException e) {
	    JOptionPane.showMessageDialog(null, "No se ha podido cargar la aventura",
		    "Error", JOptionPane.ERROR_MESSAGE, null);
	}
    }
    
    public void leerComando(String comando) {
	Juego.getInstancia().ejecutarInstruccion(comando);
    }

    public void imprimir(String mensaje, Habitacion habitacionActual , boolean esMsjFinal) {
	mensaje = normalizarMensaje(mensaje);
	if (esMsjFinal)
	    gui.finalizar(mensaje);
	else
	    gui.imprimir(mensaje, habitacionActual);
    }

    private String normalizarMensaje(String mensaje) {
	mensaje = mensaje.replaceAll(" \\s*", " ");
	if (!mensaje.endsWith("."))
	    mensaje = mensaje + '.';
	if (Character.isLowerCase(mensaje.charAt(0)))
	    mensaje = Character.toUpperCase(mensaje.charAt(0)) + mensaje.substring(1);
	return mensaje;
    }

}
