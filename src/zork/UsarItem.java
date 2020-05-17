package zork;

import com.google.gson.JsonElement;

public class UsarItem extends Trigger {

    public UsarItem(JsonElement json) {
	super(json);
    }

    @Override
    public String ejecutar(NPC npc, String activador) {
	if (!activador.equals(objetoActivador))
	    return null;
	switch (afterTrigger) {
	case "kill":
	    npc.killNPC();
	    break;
	case "defeat":
	    npc.setEnemy(false);
	    break;
	case "remove":
	    npc.getHabitacionActual().removeNPC(npc.getName());
	    break;
	}
	return mensaje;
    }

}
