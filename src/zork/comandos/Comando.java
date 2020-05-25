package zork.comandos;

import zork.Jugador;

public interface Comando {

	public abstract String ejecutar(Jugador jugador, String restoDelComando);
	public  boolean validar(Jugador jugador, String restoDelComando);
}
