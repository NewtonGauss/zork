package zork;

public class UsarItemComando implements Comando {

    private static final String NO_HA_SERVIDO = "Eso no ha servido de nada.";

    /**
     * Ejecuta un comando de usar item. Todo objeto que no tenga
     * la opcion de "usar" no hara nada con este comando.
     * 
     * Si el objetivo del comando es un npc, se ejecutara el comando
     * siempre y cuando sea un objeto aplicable sobre npcs
     * ,y se trate de un trigger del npc o se trate de 
     * un objeto aplicable, a saber, poison o potion.
     * 
     * Si el objetivo del comando es el jugador, se ejecutara siempre y
     * cuando el item pueda ser aplicado sobre "self" y sea de tipo poison o
     * potion.
     * 
     * En el caso de no cumplir, se indicara que no ha hecho efecto.
     * 
     * @param restoDelComando es una cadena con la sintaxis nombreItem:nombreNPC
     * siendo nombreNPC opcional. Siempre que no se incluya, se tratara como self
     * @return mensaje de salida por pantalla.
     */
    @Override
    public String ejecutar(Jugador jugador, String restoDelComando) {
	String[] parseado = restoDelComando.split(":");
	String itemNombre = parseado[0];
	String retorno = "";
	Item item = jugador.getItem(itemNombre);

	if (item != null && !item.esUsoValido("usar"))
	    retorno = NO_HA_SERVIDO;

	else if (parseado.length == 2 && item != null)
	    retorno = usarSobreNPC(jugador, parseado[1], item);

	else if (item != null && item.esObjetivoValido("self")
		&& isItemAplicable(item.getTipo()))
	    retorno = usarSobreJugador(jugador, item);

	else if (item != null)
	    retorno = NO_HA_SERVIDO;
	else
	    retorno = "No cuenta con " + itemNombre + " en el inventario.";

	return retorno;
    }

    private String usarSobreNPC(Jugador jugador, String npcNombre, Item item) {
	String retorno;
	NPC npc = jugador.habitacionActual.getNPC(npcNombre);
	if (npc != null)
	    retorno = aplicarSobreNpc(jugador, item, npc);
	else
	    retorno = npcNombre + " no se encuentra en "
		    + jugador.habitacionActual.toString() + ".";
	return retorno;
    }

    private String aplicarSobreNpc(Jugador jugador, Item item, NPC npc) {
	String retorno;
	if (item.esObjetivoValido("npcs")) {
	    retorno = "Se utilizo " + item.toString() + " sobre " + npc.toString()
		    + ".\n";

	    String trigger = npc.ejecutarTrigger(Trigger.ITEM, item.getNombre());
	    if (trigger != null)
		retorno += trigger;
	    else if (isItemAplicable(item.getTipo())) {
		npc.aplicarItem(item.getTipo());
		jugador.removeItem(item.getNombre());
	    } else
		retorno = NO_HA_SERVIDO;
	} else
	    retorno = NO_HA_SERVIDO;
	return retorno;
    }

    private String usarSobreJugador(Jugador jugador, Item item) {
	String retorno;
	jugador.aplicarItem(item.getTipo());
	jugador.removeItem(item.getNombre());
	retorno = "Se utilizo " + item.toString() + " sobre ti.";
	return retorno;
    }

    private boolean isItemAplicable(String tipo) {
	return tipo.equals("potion") || tipo.equals("poison");
    }

}
