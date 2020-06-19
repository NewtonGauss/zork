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
	Salida salida = jugador.getHabitacionActual().getSalida(restoComando);
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
