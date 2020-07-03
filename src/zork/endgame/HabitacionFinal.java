package zork.endgame;

import zork.*;
import zork.comandos.*;
import zork.input.FinalJuegoInput;

public class HabitacionFinal extends FinalJuego {

    public HabitacionFinal(FinalJuegoInput input) {
	descripcion = input.getDescripcion();
	objetivo = input.getObjetivo();
    }

    @Override
    public boolean esComandoFinal(Comando comando, String restoComando) {
	return comando.getTipo().equals(ComandoCondicion.IR);
    }

    @Override
    public String ejecutar(Jugador jugador, Comando comando, String dir) {
	Habitacion habitacionActual = jugador.getHabitacionActual();
	Direccion direccion = Direccion.stringToDireccion(dir);
	direccion = direccion != null ? direccion
		: habitacionActual.getSalida(dir);
	Salida salida = null;
	if (direccion != null)
	    salida = jugador.getHabitacionActual().getSalida(direccion);
	String retorno = null;
	if (salida != null && esHabitacionFinal(salida)
		&& comando.validar(jugador, dir))
	    retorno = this.descripcion;
	return retorno;
    }

    private boolean esHabitacionFinal(Salida salida) {
	return salida.getNombre().equals(objetivo);
    }
}
