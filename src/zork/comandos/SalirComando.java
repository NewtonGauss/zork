package zork.comandos;

import zork.Jugador;
import zork.endgame.ComandoCondicion;

public class SalirComando implements Comando {

    @Override
    public String ejecutar(Jugador jugador, String restoDelComando) {
	System.exit(0);
	return null;
    }

    @Override
    public boolean validar(Jugador jugador, String restoDelComando) {
	return false;
    }

    @Override
    public ComandoCondicion getTipo() {
	return ComandoCondicion.DEFAULT;
    }

}
