package zork;

import java.util.Hashtable;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import zork.input.TriggerInput;
import zork.input.json.TriggerInputJson;

public class NPC extends Personaje {
    private String charla;
    private String descripcion;
    private boolean enemigo;
    private Hashtable<TipoTrigger, Trigger> triggers = new Hashtable<TipoTrigger, Trigger>();

    public NPC(JsonElement json) {
	JsonObject jobject = json.getAsJsonObject();
	nombre = jobject.get("name").getAsString();
	descripcion = jobject.get("description").getAsString();
	charla = jobject.get("talk").getAsString();
	gender = jobject.get("gender").getAsString().equals("male") ? 'm' : 'f';
	number = jobject.get("number").getAsString().equals("singular") ? 's' : 'p';
	inventario = new Inventario();
	salud = jobject.get("health").getAsFloat();
	enemigo = jobject.get("enemy").getAsBoolean();
	for (JsonElement trigger : jobject.getAsJsonArray("triggers")) {
	    addTrigger(new TriggerInputJson(trigger.toString()));
	}
    }

    private void addTrigger(TriggerInput input) {
	switch (input.getTipo()) {
	case ITEM:
	    triggers.put(TipoTrigger.ITEM,new UsarItemTrigger(input));
	    break;
	case ATAQUE:
	    triggers.put(TipoTrigger.ATAQUE,new UsarItemTrigger(input));
	    break;
	case CHARLA:
	    triggers.put(TipoTrigger.CHARLA,new Trigger(input));
	    break;
	}
    }
    
    public String ejecutarTrigger(TipoTrigger tipoTrigger, String objetoActivador) {
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

    public boolean isEnemigo() {
	return enemigo;
    }

    public void setEnemigo(boolean enemy) {
	this.enemigo = enemy;
    }

    public String getNombre() {
	return this.nombre;
    }

}
