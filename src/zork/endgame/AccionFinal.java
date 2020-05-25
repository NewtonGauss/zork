package zork.endgame;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import zork.Jugador;
import zork.comandos.Comando;

public class AccionFinal extends FinalJuego {
    private String accion;

    public AccionFinal(JsonElement json) {
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
