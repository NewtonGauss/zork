package zork;

import com.google.gson.JsonElement;

public class UsarItemTrigger extends Trigger {

    public UsarItemTrigger(JsonElement json) {
	super(json);
    }

    @Override
    public String ejecutar(NPC npc, String activador) {
	if (!activador.equals(objetoActivador) && !objetoActivador.equals("all"))
	    return null;
	return super.ejecutar(npc, activador);
    }
}
