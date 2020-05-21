package zork;

public class UsarItemComando implements Comando {

    /*
     * Manera de usar: ejecutar(jugador,nombreItem + ":" + nombreNPC) el nombre del
     * npc puede ser opcional. si no se psa por parametro el nombre del npc, el item
     * se va a aplicar al jugador.
     */
    @Override
    public String ejecutar(Jugador jugador, String restoDelComando) {
	
	String[] parseado = restoDelComando.split(":");
	String itemNombre = parseado[0];
	String retorno = "";
	Item item = jugador.getItem(itemNombre);
	
	if (item != null && !item.esUsoValido("usar"))
	    return "Eso no ha servido de nada.";
	
	if (parseado.length == 2 && item != null) {
	    String npcNombre = parseado[1];
	    NPC npc = jugador.habitacionActual.getNPC(npcNombre);
	    if (item.esObjetivoValido("npcs") && npc != null && isItemAplicable(item.getTipo())) {
		retorno = "Se utilizo " + item.toString() + " sobre " + npc.toString() + ".\n";
		String trigger = npc.ejecutarTrigger(Trigger.ITEM, item.getNombre());
		if (trigger != null)
		    retorno += trigger;
		else
		    npc.aplicarItem(item.getTipo());
		jugador.removeItem(item.getNombre());
	    } else if (npc != null)
		retorno = "Eso no ha servido de nada.";
	    else
		retorno = npcNombre + " no se encuentra en " + jugador.habitacionActual.toString() + ".";
	} else if(item != null && item.esObjetivoValido("self") && isItemAplicable(item.getTipo())) {
		jugador.aplicarItem(item.getTipo());
		jugador.removeItem(item.getNombre());
		retorno = "Se utilizo " + item.toString() + " sobre ti." ;
	} else if (item != null)
	    retorno = "Eso no ha servido de nada.";
	else
	    retorno = "No cuenta con " + itemNombre + " en el inventario.";

	return retorno;
    }

    private boolean isItemAplicable(String tipo) {
	return tipo.equals("potion") || tipo.equals("poison");
    }

}
