package zork.comandos;

import zork.Jugador;

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

}
