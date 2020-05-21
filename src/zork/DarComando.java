package zork;

import utilitarias.Cadena;

public class DarComando implements Comando {

    /**
     * Ejecuta un comando de dar un {@link Item} a un {@link NPC}.
     * 
     * @param restoDelComando es una cadena con la sintaxis nombreItem:nombreNPC
     * @return mensaje de salida por pantalla.
     */
    @Override
    public String ejecutar(Jugador jugador, String restoDelComando) {
	String retorno = "";
	String[] cadenaPartida = restoDelComando.split(":");
	String objeto = cadenaPartida[0], sujeto = cadenaPartida[1];
	NPC npc = jugador.getHabitacionActual().getNPC(sujeto);
	Item item = jugador.getItem(objeto);
	if (npc != null) {
	    if (item != null) {
		npc.addItem(item);
		jugador.removeItem(item.getNombre());
		retorno = "Le diste " + item.toString() + " a " + npc.toString() + '.';
		retorno = Cadena.replaceAEl(retorno);
	    } else {
		retorno = "No tienes " + objeto + " en tu inventario.";
	    }
	} else {
	    retorno = sujeto + " no se encuentra en "
		    + jugador.getHabitacionActual().toString() + '.';
	}
	return retorno;
    }

}
