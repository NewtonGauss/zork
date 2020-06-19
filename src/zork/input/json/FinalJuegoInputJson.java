package zork.input.json;

import com.google.gson.*;

import zork.endgame.ComandoCondicion;
import zork.input.FinalJuegoInput;

public class FinalJuegoInputJson implements FinalJuegoInput {
    private JsonObject jobj;

    public FinalJuegoInputJson(String json) {
	this.jobj = JsonParser.parseString(json).getAsJsonObject();
    }

    @Override
    public String getDescripcion() {
	return jobj.get("description").getAsString();
    }

    @Override
    public String getObjetivo() {
	return jobj.get("thing").getAsString();
    }

    @Override
    public ComandoCondicion getAccion() {
	return ComandoCondicion
		.stringToComandoCondicion(jobj.get("action").getAsString());
    }
}
