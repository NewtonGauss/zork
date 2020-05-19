package zork;

public class InfoComando implements Comando{
	
	/*
	 * PARA QUE FUNCIONE EN EL PARAMETRO RESTODELCOMANDO SE TIENE QUE RECIBIR 
	 * "nombreItem:nombreNPC"
	 * 
	 * */

	@Override
	public String ejecutar(Jugador jugador, String restoDelComando) {
		
		return jugador.getHabitacionActual().getDescription();
	}

}
