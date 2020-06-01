package zork;

import zork.input.TriggerInput;

public class Trigger {
    protected String objetoActivador;
    protected String mensaje;
    protected String afterTrigger;

    public Trigger(TriggerInput input) {
	objetoActivador = input.getObjetoActivador();
	mensaje = input.getMensaje();
	afterTrigger = input.getAfterTrigger();
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
	    break;
	}
	return mensaje;
    }
}
