package zork.comandos;

import zork.*;

public class IrComando implements Comando {

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
	    retorno = jugador.getHabitacionActual().getDescripcion();
	} else {
	    Salida salida = jugador.getHabitacionActual().getSalida(direccion);
	    if (salida != null) {
		retorno = salida.getObstaculo().getDescripcion();
	    } else {
		retorno = "Hacia "
			+ ((direccion.equals("arriba") || direccion.equals("abajo")) ? ""
				: "el ")
			+ direccion + " no hay salida.";
	    }

	}
	return retorno;
    }

    @Override
    public boolean validar(Jugador jugador, String restoDelComando) {
	Salida salida = jugador.getHabitacionActual().getSalida(restoDelComando);
	return  salida != null && salida.isEnemigoDerrotado();
    }

}
