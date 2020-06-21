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
	return comando.getClass().equals(IrComando.class);
    }

    @Override
    public String ejecutar(Jugador jugador, Comando comando, String restoComando) {
	Direccion direccion = Direccion.stringToDireccion(restoComando);
	Salida salida = null;
	if (direccion != null)
	    salida = jugador.getHabitacionActual().getSalida(direccion);
	String retorno = null;
	if (salida != null && esHabitacionFinal(salida)
		&& comando.validar(jugador, restoComando)) {
	    retorno = this.descripcion;
	}
	return retorno;
    }

    private boolean esHabitacionFinal(Salida salida) {
	return salida.getNombre().equals(objetivo);
    }
}
