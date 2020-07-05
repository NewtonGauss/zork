package zork.comandos;

import zork.*;
import zork.endgame.ComandoCondicion;

public class UsarItemComando implements Comando {
    /**
     * Ejecuta un comando de usar item. Todo objeto que no tenga la opcion de "usar"
     * no hara nada con este comando.
     * 
     * Si el objetivo del comando es un npc, se ejecutara el comando siempre y
     * cuando sea un objeto aplicable sobre npcs ,y se trate de un trigger del npc o
     * se trate de un objeto aplicable, a saber, poison o potion.
     * 
     * Si el objetivo del comando es el jugador, se ejecutara siempre y cuando el
     * item pueda ser aplicado sobre "self" y sea de tipo poison o potion.
     * 
     * En el caso de no cumplir, se indicara que no ha hecho efecto.
     * 
     * @param restoDelComando es una cadena con la sintaxis nombreItem:nombreNPC
     *                        siendo nombreNPC opcional. Siempre que no se incluya,
     *                        se tratara como self
     * @return mensaje de salida por pantalla.
     */
    @Override
    public String ejecutar(Jugador jugador, String restoDelComando) {
	String retorno = "";
	Habitacion habitacionActual = jugador.getHabitacionActual();
	String[] parseado = restoDelComando.split(":");
	String itemNombre = parseado[0];
	String npcNombre = parseado.length > 1 ? parseado[1] : null;
	Item item = jugador.getItem(itemNombre);
	NPC objetivo = npcNombre != null ? habitacionActual.getNPC(npcNombre) : null;

	if (item == null)
	    retorno = "No cuenta con " + itemNombre + " en el inventario.";
	else if (!item.esUsoValido(AccionItem.USE)
		|| (npcNombre == null && !esAplicableSobre(item, ObjetivoItem.SELF))
		|| (objetivo != null && !esAplicableSobre(item, ObjetivoItem.NPCS)))
	    retorno = "Eso no ha servido de nada.";
	else if (objetivo == null && parseado.length > 1)
	    retorno = npcNombre + " no se encuentra en " + habitacionActual.toString()
		    + '.';
	else if (objetivo == null)
	    retorno = usarSobreJugador(jugador, item);
	else
	    retorno = usarSobreNpc(jugador, item, objetivo);

	return retorno;
    }

    private String usarSobreNpc(Jugador jugador, Item item, NPC npc) {
	String retorno;
	retorno = "Se utilizo " + item.toString() + " sobre " + npc.toString() + ".\n";

	String trigger = npc.ejecutarTrigger(TipoTrigger.ITEM, item.getNombre());
	if (trigger == null) {
	    npc.aplicarItem(item);
	    jugador.sacarItem(item.getNombre());
	} else
	    retorno += trigger;

	return retorno;
    }

    private String usarSobreJugador(Jugador jugador, Item item) {
	jugador.aplicarItem(item);
	jugador.sacarItem(item.getNombre());
	return "Se utilizo " + item.toString() + " sobre ti.";
    }

    private boolean esAplicableSobre(Item item, ObjetivoItem objetivo) {
	TipoItem tipo = item.getTipo();
	return item.esObjetivoValido(objetivo)
		&& (tipo.equals(TipoItem.VENENO) || tipo.equals(TipoItem.POCION));
    }

    @Override
    public boolean validar(Jugador jugador, String restoDelComando) {
	boolean rta = true;
	Habitacion habitacionActual = jugador.getHabitacionActual();
	String[] parseado = restoDelComando.split(":");
	String itemNombre = parseado[0];
	String npcNombre = parseado.length > 1 ? parseado[1] : null;
	Item item = jugador.getItem(itemNombre);
	NPC objetivo = npcNombre != null ? habitacionActual.getNPC(npcNombre) : null;
	
	if (item == null)
	    rta = false;
	if (objetivo == null && npcNombre != null)
	    rta = false;
	if (objetivo == null && npcNombre == null && !esAplicableSobre(item, ObjetivoItem.SELF))
	    rta = false;
	if (objetivo != null && !esAplicableSobre(item, ObjetivoItem.NPCS))
	    rta = false;
	
	return rta;
    }

    @Override
    public ComandoCondicion getTipo() {
	return ComandoCondicion.USAR;
    }

}
