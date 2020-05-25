package zork;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import zork.comandos.Comando;

public class ActionEndGame extends Endgame {
    private String accion;

    public ActionEndGame(JsonElement json) {
	JsonObject jobj = json.getAsJsonObject();
	descripcion = jobj.get("description").getAsString();
	objetivo = jobj.get("thing").getAsString();
	accion = jobj.get("action").getAsString();

    }

    @Override
    public boolean esFinal(Comando comando, String restoComando) {
	return (comando.getClass().toString().toLowerCase().contains(accion.toLowerCase())
		&& restoComando.contains(objetivo));
    }
    
    @Override
    public String ejecutar(Jugador jugador, Comando comando, String restoComando) {
	    return comando.validar(jugador, restoComando) ? this.descripcion : null;
	}

}
