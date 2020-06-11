package zork.input.json;

import com.google.gson.*;

import zork.input.SitioInput;

public class SitioInputJson implements SitioInput {
    private JsonObject jobject;
    
    public SitioInputJson(String json) {
	jobject = JsonParser.parseString(json).getAsJsonObject();
    }

    @Override
    public String getNombre() {
	return jobject.get("name").getAsString();
    }

    @Override
    public char getGender() {
	return jobject.get("gender").getAsString().equals("male") ? 'm' : 'f';
    }

    @Override
    public char getNumber() {
	return jobject.get("number").getAsString().equals("singular") ? 's' : 'p';
    }

}
