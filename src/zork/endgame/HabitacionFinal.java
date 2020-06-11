package zork.endgame;

import com.google.gson.*;

import zork.*;
import zork.comandos.*;

public class HabitacionFinal extends FinalJuego {

    public HabitacionFinal(JsonElement json) {
	JsonObject jobj = json.getAsJsonObject();
	descripcion = jobj.get("description").getAsString();
	objetivo = jobj.get("thing").getAsString();
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
