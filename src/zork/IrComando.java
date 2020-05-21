package zork;

public class IrComando implements Comando {

<<<<<<< HEAD
	@Override
	public String ejecutar(Jugador jugador, String restoDelComando) {
		
		String retorno = "";
		
		if(jugador.mover(restoDelComando) == true) {
			retorno = jugador.getHabitacionActual().getDescription();
		}else if (jugador.habitacionActual.getSalida(restoDelComando) != null) {
			retorno = "Hay un NPC bloqueando la salida";
		}else {
			retorno = "Hacia el " + restoDelComando + " no hay salida";			
		}
		
		return retorno;
=======
    /**
     * Mueve al jugador (o no).
     * Devuelve el mensaje que debera aparecer en pantalla
     * 
     * @param direccion puede ser norte, sur, este, oeste, arriba, abajo.
     * @return mensaje de salida por pantalla. Puede ser la descripcion de
     * la habitacion nueva, el mensaje de un obstaculo o un mensaje que 
     * indica que no se puede mover.
     */
    @Override
    public String ejecutar(Jugador jugador, String direccion) {
	String retorno = "";

	if (jugador.mover(direccion)) {
	    retorno = jugador.getHabitacionActual().getDescription();
	} else {
	    Salida salida = jugador.habitacionActual.getSalida(direccion);
	    if (salida != null) {
		retorno = salida.getObstacle().getDescripcion();
	    } else {
		retorno = "Hacia "
			+ ((direccion.equals("arriba") || direccion.equals("abajo")) ? ""
				: "el ")
			+ direccion + " no hay salida";
	    }
>>>>>>> 0c362b4dc500972c0730fd105f10de355fcc2f6b
	}
	return retorno;
    }

}
