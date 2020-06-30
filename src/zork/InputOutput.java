package zork;

import java.io.IOException;

public class InputOutput {

    private String nombreJugador;
    private GUI gui;
    
    public void setGui(GUI gui) {
	this.gui = gui;
    }

    public void setNombreJugador(String nombreJugador) {
	this.nombreJugador = nombreJugador;
    }
    
    public void cargarHistoria(String pathHistoria) {
	try {
	    Juego.getInstancia().cargarHistoria(pathHistoria);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    public void leerComando(String comando) {
	Juego.getInstancia().ejecutarInstruccion(comando);
    }

    public void imprimir(String mensaje, boolean esMsjFinal) {
	gui.imprimir(mensaje);
	if (esMsjFinal)
	    gui.finalizar();
    }

}
