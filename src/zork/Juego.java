package zork;

import java.io.IOException;
import java.util.*;

import zork.endgame.FinalJuego;

public class Juego {

    public static void main(String[] args) {
	Jugador jugador = null;
	Narrador narrador = null;
	Set<String> mensajesFinales = new HashSet<String>();
	InputOutput io = new InputOutput("Jugadorazo");
	try {
	    CargadorHistoria ch = new CargadorHistoria("aventuras/mi.zork");
	    jugador = ch.cargarHistoria();
	    narrador = new Narrador(jugador);
	    List<FinalJuego> finales = ch.cargarFinales();
	    narrador.addEndgames(finales);
	    for (FinalJuego finalJuego : finales) {
		mensajesFinales.add(finalJuego.getDescripcion());
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	    System.exit(1);
	}
	String comando;
	while (!(comando = io.leerComando()).equals("exit")) {
	    String salida = narrador.ejecutar(comando);
	    io.imprimir(salida);
	    if (mensajesFinales.contains(salida))
		break;
	}
    }

}
