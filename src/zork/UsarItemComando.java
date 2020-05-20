package zork;

public class UsarItemComando implements Comando {

    /*
     * Manera de usar:
     * ejecutar(jugador,nombreItem + ":" + nombreNPC)
     * el nombre del npc puede ser opcional. si no se psa por parametro el nombre del npc, el item
     * se va a aplicar al jugador.
     */
    @Override
    public String ejecutar(Jugador jugador, String restoDelComando) {
	
	String[] parseado = restoDelComando.split(":");
	String itemNombre = parseado[0];
	String retorno = "";
	Item item = jugador.getItem(itemNombre);
	
	if (parseado.length == 2 && item != null) {
	    
	    String npcNombre = parseado[1];
	    
	    if (item.esObjetivoValido("npcs")) {
		NPC npc = jugador.habitacionActual.getNPC(npcNombre);
		npc.ejecutarTrigger("item", item.getNombre());
		jugador.removeItem(item.getNombre());
		retorno = "Se utilizo el item: " + item.getNombre() + " en el NPC: " + npc.getName();
	    }
	    retorno = "No se puede utilizar el item sobre un NPC";
	}
	if(parseado.length == 1 && item != null) {
	    if(item.esObjetivoValido("self") && isItemAplicable(item.getTipo())) {
		jugador.aplicarItem(item.getNombre());
		jugador.removeItem(item.getNombre());
		retorno = "Se utilizo el item: " + item.getNombre() + " sobre ti." ;
	    }
	}

	return retorno;
    }

    private boolean isItemAplicable(String tipo) {
	return tipo.equals("potion") || tipo.equals("poison");
    }

}
