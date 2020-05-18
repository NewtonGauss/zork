package zork;

public class MirarComando implements Comando{

	@Override
	public String ejecutar(Jugador jugador, String restoDelComando) {
		return jugador.getHabitacionActual().getDescription() + restoDelComando;
	}
	

}
