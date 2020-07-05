package zork.input.json;

import com.google.gson.*;

import zork.input.HabitacionInput;

public class HabitacionInputJson implements HabitacionInput {
    private JsonObject jobj;
    
    public HabitacionInputJson(String json) {
	jobj = JsonParser.parseString(json).getAsJsonObject();
    }
    
    @Override
    public String getNombre() {
	return jobj.get("name").getAsString();
    }

    @Override
    public String getDescripcion() {
	return jobj.get("description").getAsString();
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
    public String getSpritePath() {
	JsonElement path = jobj.get("sprite");
	if ( path != null)
	    return path.getAsString();
	else
	    return null;
    }

}
