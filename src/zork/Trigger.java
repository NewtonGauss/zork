package zork;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Trigger {
    protected String objetoActivador;
    protected String mensaje;
    protected String afterTrigger;
    public static final String ITEM = "item";
    public static final String ATTACK = "attack";
    public static final String TALK = "talk";
    
   public Trigger(JsonElement json) {
       JsonObject jobject = json.getAsJsonObject();
       objetoActivador = jobject.get("thing").getAsString();
       mensaje = jobject.get("on_trigger").getAsString();
       afterTrigger = jobject.get("after_trigger").getAsString();
   }
   
    public String ejecutar(NPC npc, String activador) {
	switch (afterTrigger) {
	case "kill":
	    npc.matar();
	    break;
	case "defeat":
	    npc.setEnemigo(false);
	    break;
	case "remove":
	    npc.getHabitacionActual().sacarNPC(npc.getNombre());
	    break;}
	return mensaje;
    }
}
