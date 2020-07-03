package zork.comandos;

import zork.Jugador;
import zork.endgame.ComandoCondicion;

public class MovimientosComando implements Comando{

	@Override
	public String ejecutar(Jugador jugador, String restoDelComando) {
		return Integer.toString(jugador.getCantMovimientos());
	}

	@Override
	public boolean validar(Jugador jugador, String restoDelComando) {
	    // TODO Auto-generated method stub
	    return false;
	}

	@Override
	public ComandoCondicion getTipo() {
	    return ComandoCondicion.DEFAULT;
	}

}
