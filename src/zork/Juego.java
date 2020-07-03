package zork;

import java.io.IOException;
import java.util.*;

import zork.endgame.FinalJuego;

public class Juego {
    private Jugador jugador;
    private Narrador narrador;
    private Set<String> mensajesFinales;
    private InputOutput io;
    private boolean andando = false;
    private static Juego instanciaSingleton = new Juego();

    private Juego() {
	mensajesFinales = new HashSet<String>();
    }
    
    public static Juego getInstancia() {
	return instanciaSingleton;
    }
    
    public void setIo(InputOutput io) {
	this.io = io;
    }

    public void cargarHistoria(String pathHistoria) throws IOException {
	    CargadorHistoria ch = new CargadorHistoria(pathHistoria);
	    jugador = ch.cargarHistoria();
	    narrador = new Narrador(jugador);
	    List<FinalJuego> finales = ch.cargarFinales();
	    narrador.addEndgames(finales);
	    for (FinalJuego finalJuego : finales) {
		mensajesFinales.add(finalJuego.getDescripcion());
	    }
	    andando = true;
	    io.setNombreJugador(jugador.getNombre());
	    io.imprimir(ch.getBienvenida(), !andando);
    }

    public void ejecutarInstruccion(String comando) {
	    String salida = narrador.ejecutar(comando);
	    if (mensajesFinales.contains(salida))
		andando = false;
	    io.imprimir(salida, !andando);
    }
}
