package zork;

public class Movimiento implements Comando{

	@Override
	public String ejecutar(Jugador jugador) {
		
		return Integer.toString(jugador.getCantMovimientos());
	}

}
