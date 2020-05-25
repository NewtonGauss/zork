package zork;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import zork.comandos.Comando;

public class LocationEndGame extends Endgame{

    public LocationEndGame(JsonElement json) {
	JsonObject jobj = json.getAsJsonObject();
	descripcion = jobj.get("description").getAsString();
	objetivo = jobj.get("thing").getAsString();
	
    }

    @Override
    public boolean esFinal(Comando comando, String restoComando) {
	return (comando.getClass().toString().toLowerCase().contains("ir"));
    }

    @Override
    public String ejecutar(Jugador jugador, Comando comando, String restoComando) {
	Salida salida = jugador.getHabitacionActual().getSalida(restoComando);
	String retorno = null;
	if(salida != null && salida.getNombre().equals(objetivo) && comando.validar(jugador, restoComando)) {
	    retorno = this.descripcion;
	}
	return retorno;
    }
}
