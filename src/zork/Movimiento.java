package zork;

public class Movimiento implements Comando{

	@Override
	public String ejecutar(Jugador jugador, String restoDelComando) {
		return Integer.toString(jugador.getCantMovimientos());
	}

}
