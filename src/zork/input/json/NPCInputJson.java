package zork.input.json;

import java.util.*;

import com.google.gson.*;

import zork.input.*;

public class NPCInputJson implements NPCInput {
    private JsonObject jobject;
    private List<TriggerInput> listaTriggers = new ArrayList<TriggerInput>();

    public NPCInputJson(String jsonString) {
	jobject = JsonParser.parseString(jsonString).getAsJsonObject();
	JsonArray triggers = jobject.getAsJsonArray("triggers");
	for (JsonElement trigger : triggers) {
	    listaTriggers.add(new TriggerInputJson(trigger.toString()));
	}
    }

    @Override
    public String getNombre() {
	return jobject.get("name").getAsString();
    }

    @Override
    public String getDescripcion() {
	return jobject.get("description").getAsString();
    }

    @Override
    public String getCharla() {
	return jobject.get("talk").getAsString();
    }

    @Override
    public char getGender() {
	return jobject.get("gender").getAsString().equals("male") ? 'm' : 'f';
    }

    @Override
    public char getNumber() {
	return jobject.get("number").getAsString().equals("singular") ? 's' : 'p';
    }

    @Override
    public float getSalud() {
	return jobject.get("health").getAsFloat();
    }

    @Override
    public boolean isEnemigo() {
	return jobject.get("enemy").getAsBoolean();
    }

    @Override
    public List<TriggerInput> getListaTrigger() {
	return listaTriggers;
    }

    @Override
    public String getSpritePath() {
	JsonElement path = jobject.get("sprite");
	if ( path != null)
	    return path.getAsString();
	else
	    return null;
    }

}
