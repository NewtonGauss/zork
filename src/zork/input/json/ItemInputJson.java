package zork.input.json;

import java.util.*;

import com.google.gson.*;

import zork.*;
import zork.input.ItemInput;

public class ItemInputJson implements ItemInput {
    private List<AccionItem> accionesValidas = new ArrayList<AccionItem>();
    private List<ObjetivoItem> objetivosValidos = new ArrayList<ObjetivoItem>();
    private JsonObject jobj;

    public ItemInputJson(String jsonString) {
	JsonElement json = JsonParser.parseString(jsonString);
	jobj = json.getAsJsonObject();
	for (JsonElement accion : jobj.getAsJsonArray("actions"))
	    accionesValidas.add(AccionItem.stringToAccionItem(accion.getAsString()));
	for (JsonElement effectOn : jobj.getAsJsonArray("effects_over"))
	    objetivosValidos.add(ObjetivoItem.stringTObjetivoItem(effectOn.getAsString()));
    }

    @Override
    public String getNombre() {
	return jobj.get("name").getAsString();
    }

    @Override
    public Double getPeso() {
	return jobj.get("weight").getAsDouble();
    }

    @Override
    public int getPuntos() {
	return jobj.get("points").getAsInt();
    }

    @Override
    public TipoItem getTipo() {
	return TipoItem.stringToTipo(jobj.get("type").getAsString());
    }

    @Override
    public char getGender() {
	return jobj.get("gender").getAsString().equals("male") ? 'm' : 'f';
    }

    @Override
    public char getNumber() {
	return jobj.get("number").getAsString().equals("singular") ? 's' : 'p';
    }

    @Override
    public List<AccionItem> getAccionesValidas() {
	return accionesValidas;
    }

    @Override
    public List<ObjetivoItem> getObjetivosValidos() {
	return objetivosValidos;
    }

    @Override
    public float getSaludSumar() {
	JsonElement j = jobj.get("health");
	if (j != null)
	    return j.getAsInt();
	return 0;
    }

    @Override
    public String getSpritePath() {
	JsonElement path = jobj.get("sprite");
	if (path != null)
	    return path.getAsString();
	else
	    return null;
    }

}
