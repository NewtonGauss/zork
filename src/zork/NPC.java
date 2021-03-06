package zork;

import java.util.Hashtable;

import zork.input.*;

public class NPC extends Personaje {
    private String charla;
    private String descripcion;
    private boolean enemigo;
    private Hashtable<TipoTrigger, Trigger> triggers = new Hashtable<TipoTrigger, Trigger>();
    private String spritePath;

    public NPC(NPCInput input) {
	super(input.getNombre(), input.getGender(), input.getNumber());
	charla = input.getCharla();
	descripcion = input.getDescripcion();
	salud = input.getSalud();
	enemigo = input.isEnemigo();
	inventario = new Inventario();
	spritePath = input.getSpritePath();
	for (TriggerInput trigger: input.getListaTrigger())
	    addTrigger(trigger);
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
    
    public String getSpritePath() {
	return spritePath;
    }

    public boolean isEnemigo() {
	return enemigo;
    }

    public void setEnemigo(boolean enemy) {
	this.enemigo = enemy;
    }

}
