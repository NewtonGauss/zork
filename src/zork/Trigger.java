package zork;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Trigger {
    protected String objetoActivador;
    protected String mensaje;
    protected String afterTrigger;
    
   public Trigger(JsonElement json) {
       JsonObject jobject = json.getAsJsonObject();
       objetoActivador = jobject.get("thing").getAsString();
       mensaje = jobject.get("on_trigger").getAsString();
       afterTrigger = jobject.get("after_trigger").getAsString();
   }
   
    public String ejecutar(NPC npc, String activador) {
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
