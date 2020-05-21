package zork;

import java.util.Hashtable;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import utilitarias.Cadena;

public class NPC extends Character {
    private String charla;
    private String descripcion;
    private boolean enemy;
    private char gender;
    private char number;
    private Hashtable<String, Trigger> triggers = new Hashtable<String, Trigger>();

    public NPC(JsonElement json) {
	JsonObject jobject = json.getAsJsonObject();
	nombre = jobject.get("name").getAsString();
	descripcion = jobject.get("description").getAsString();
	charla = jobject.get(Trigger.TALK).getAsString();
	gender = jobject.get("gender").getAsString().equals("male") ? 'm' : 'f';
	number = jobject.get("number").getAsString().equals("singular") ? 's' : 'p';
	inventario = new Inventario();
	salud = jobject.get("health").getAsFloat();
	enemy = jobject.get("enemy").getAsBoolean();
	for (JsonElement trigger : jobject.getAsJsonArray("triggers")) {
	    addTrigger(trigger);
	}
    }

    private void addTrigger(JsonElement trigger) {
	JsonObject triggerObj = trigger.getAsJsonObject();
	switch (triggerObj.get("type").getAsString()) {
	case Trigger.ITEM:
	    triggers.put(Trigger.ITEM,new UsarItemTrigger(trigger));
	    break;
	case Trigger.ATTACK:
	    triggers.put(Trigger.ATTACK,new UsarItemTrigger(trigger));
	    break;
	case Trigger.TALK:
	    triggers.put(Trigger.TALK,new Trigger(trigger));
	    break;
	}
    }
    
    public String ejecutarTrigger(String tipoTrigger, String objetoActivador) {
	Trigger trigger = triggers.get(tipoTrigger);
	if (trigger != null)
	    return trigger.ejecutar(this, objetoActivador);
	else
	    return null;
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
	return Cadena.articuloDefinido(nombre, gender, number);
    }
    
    public String articuloIndefinido() {
	return Cadena.articuloIndefinido(nombre, gender, number);
    }

}
