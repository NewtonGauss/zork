package zork;

import java.util.Hashtable;

import zork.input.NPCInput;
import zork.input.TriggerInput;

public class NPC extends Personaje {
    private String charla;
    private String descripcion;
    private boolean enemigo;
    private Hashtable<TipoTrigger, Trigger> triggers = new Hashtable<TipoTrigger, Trigger>();

    public NPC(NPCInput input) {
	nombre = input.getNombre();
	charla = input.getCharla();
	descripcion = input.getDescripcion();
	gender = input.getGender();
	number = input.getNumber();
	salud = input.getSalud();
	enemigo = input.isEnemigo();
	inventario = new Inventario();
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
