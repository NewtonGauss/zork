package zork;

import zork.input.TriggerInput;

public class UsarItemTrigger extends Trigger {

    public UsarItemTrigger(TriggerInput input) {
	super(input);
    }

    @Override
    public String ejecutar(NPC npc, String activador) {
	if (!activador.equals(objetoActivador) && !objetoActivador.equals("all"))
	    return null;
	return super.ejecutar(npc, activador);
    }
}
