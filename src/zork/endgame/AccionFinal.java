package zork.endgame;

import com.google.gson.*;

import zork.Jugador;
import zork.comandos.Comando;

public class AccionFinal extends FinalJuego {
    private ComandoCondicion accion;

    public AccionFinal(JsonElement json) {
	JsonObject jobj = json.getAsJsonObject();
	descripcion = jobj.get("description").getAsString();
	objetivo = jobj.get("thing").getAsString();
	accion = ComandoCondicion
		.stringToComandoCondicion(jobj.get("action").getAsString());
    }

    @Override
    public boolean esComandoFinal(Comando comando, String restoComando) {
	return accion.equals(comando) && restoComando.contains(objetivo);
    }

    @Override
    public String ejecutar(Jugador jugador, Comando comando, String restoComando) {
	return comando.validar(jugador, restoComando) ? this.descripcion : null;
    }

}
