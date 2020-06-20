package zork;

import java.io.IOException;

public class Juego {

    public static void main(String[] args) {
	Jugador jugador = null;
	InputOutput io = new InputOutput("Jugadorazo");
	try {
	    CargadorHistoria ch = new CargadorHistoria("aventuras/mi.zork");
	    jugador = ch.cargarHistoria();
	} catch (IOException e) {
	    e.printStackTrace();
	    System.exit(1);
	}
	Narrador narrador = new Narrador(jugador);
	String comando;
	while (!(comando = io.leerComando()).equals("exit")) {
	    /* Falta cargar los finales del juego y fijarse para salir del while */
	    String salida = narrador.ejecutar(comando);
	    io.imprimir(salida);
	}
    }

}
