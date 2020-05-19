package zork;

public class IrComando implements Comando{
	
	/*
	 * PARA QUE FUNCIONE EN EL PARAMETRO RESTODELCOMANDO SE TIENE QUE RECIBIR 
	 * "direccion"
	 * 
	 * */
	
	

	@Override
	public String ejecutar(Jugador jugador, String restoDelComando) {
		
		String retorno = "";
		
		if(jugador.mover(restoDelComando) == true) {
			retorno = jugador.getHabitacionActual().getDescription();
		}else if (jugador.habitacionActual.getSalidasTable().get(restoDelComando).isEnemyDefeated() == true) {
			retorno = "Hay un NPC bloqueando la salida";
		}else {
			retorno = "Hacia el " + restoDelComando + "no hay salida";			
		}
		
		return retorno;
	}

}
