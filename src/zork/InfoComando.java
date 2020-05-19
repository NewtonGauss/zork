package zork;

public class InfoComando implements Comando{
	
	/*
	 * PARA QUE FUNCIONE EN EL PARAMETRO RESTODELCOMANDO SE TIENE QUE RECIBIR 
	 * "info del Json"
	 * 
	 * */

	@Override
	public String ejecutar(Jugador jugador, String restoDelComando) {
		
		return restoDelComando;
	}

}
