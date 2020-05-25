package zork;

import zork.comandos.Comando;

public abstract class Endgame {
    
	protected String descripcion;
	protected String objetivo;
	 
	public abstract String ejecutar(Jugador jugador, Comando comando, String restoComando);
	public abstract boolean esFinal(Comando comando, String restoComando);
	
	}
