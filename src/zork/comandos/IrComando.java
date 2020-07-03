package zork.comandos;

import zork.*;
import zork.endgame.ComandoCondicion;

public class IrComando implements Comando {
    /**
     * Mueve al jugador (o no). Devuelve el mensaje que debera aparecer en pantalla
     * 
     * @param direccion puede ser norte, sur, este, oeste, arriba, abajo. Tambien
     *                  recibe el nombre exacto de la habitacion.
     * @return mensaje de salida por pantalla. Puede ser la descripcion de la
     *         habitacion nueva, el mensaje de un obstaculo o un mensaje que indica
     *         que no se puede mover.
     */
    @Override
    public String ejecutar(Jugador jugador, String dir) {
	String retorno = "";
	Habitacion habitacionActual = jugador.getHabitacionActual();
	Direccion direccion = Direccion.stringToDireccion(dir);
	direccion = direccion != null ? direccion
		: habitacionActual.getSalida(dir);

	if (direccion != null && jugador.mover(direccion)) {
	    retorno = jugador.getHabitacionActual().getDescripcion();
	} else if (direccion != null) {
	    Salida salida = habitacionActual.getSalida(direccion);
	    if (salida != null) {
		retorno = salida.getObstaculo().getDescripcion();
	    } else {
		retorno = "Hacia "
			+ ((direccion.equals(Direccion.ARRIBA)
				|| direccion.equals(Direccion.ABAJO)) ? "" : "el ")
			+ direccion.toString().toLowerCase() + " no hay salida.";
	    }

	} else
	    retorno = "No se reconoce la direccion " + dir + "\n";
	return retorno;
    }

    @Override
    public boolean validar(Jugador jugador, String dir) {
	Habitacion habitacionActual = jugador.getHabitacionActual();
	Direccion direccion = Direccion.stringToDireccion(dir);
	direccion = direccion != null ? direccion
		: habitacionActual.getSalida(dir);
	Salida salida = habitacionActual.getSalida(direccion);
	return salida != null && salida.isEnemigoDerrotado();
    }

    @Override
    public ComandoCondicion getTipo() {
	return ComandoCondicion.IR;
    }
}
