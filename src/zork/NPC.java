package zork;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class NPC extends Character {
    private String charla;
    private String descripcion;
    private boolean enemy;
    private char gender;
    private char number;
    // TODO agregar triggers

    public NPC(JsonElement json) {
	JsonObject jobject = json.getAsJsonObject();
	nombre = jobject.get("name").getAsString();
	descripcion = jobject.get("description").getAsString();
	charla = jobject.get("talk").getAsString();
	gender = jobject.get("gender").getAsString().equals("male") ? 'm' : 'f';
	number = jobject.get("number").getAsString().equals("singular") ? 's' : 'p';
	// TODO agregar triggers
	inventario = new Inventario();
	salud = 100; // TODO agregar al formato
    }

    public String hablar() {
	return charla;
    }

    public String getDescripcion() {
	return descripcion;
    }
    
    public void killNPC() {
    	this.salud = 0;
    }
   
    public boolean isEnemy() {
	return enemy;
    }

    public void setEnemy(boolean enemy) {
	this.enemy = enemy;
    }

    public String getName() {
    	return this.nombre;
    }
    
    @Override
    public String toString() {
	String fraseItem = "";
	if (number == 's')
	    fraseItem += gender == 'm' ? "el " : "la ";
	else
	    fraseItem += gender == 'm' ? "los " : "las ";
	return fraseItem + nombre;
    }
}
