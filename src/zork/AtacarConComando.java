package zork;

import java.util.Hashtable;
import java.util.Iterator;

public class AtacarConComando implements Comando {

    /**
     * Ejecuta un comando de Atacar {@link NPC} con {@link Item} del tipo "weapon".
     * Devuelve el mensaje que debera aparecer en pantalla
     * 
     * @param restoDelComando es una cadena con la sintaxis nombreNPC:nombreItem
     * @return mensaje de salida por pantalla. Puede ser el resultado de ejecucion
     *         del trigger o un mensaje de ataque fallido
     */
    @Override
    public String ejecutar(Jugador jugador, String restoDelComando) {
	String retorno = "";
	String[] cadenaPartida = restoDelComando.split(":");
	String objetivo = cadenaPartida[0], objeto = cadenaPartida[1];

	NPC npc = jugador.getHabitacionActual().getNPC(objetivo);
	Item item = jugador.getItem(objeto);
	if (item != null && item.getTipo().equals("weapon"))
	    retorno = objetivo + ": " + npc.ejecutarTrigger("attack", objeto);
	else {
	    retorno = "Utilice uno de los siguientes items para atacar: \n";
	    for (Iterator<Item> i = jugador.getItems().iterator(); i.hasNext();) {
		item = i.next();
		if (item.getTipo().equals("weapon"))
		    retorno += item.getNombre() + '\n';
	    }
	    if (retorno.equals("Utilice uno de los siguientes items para atacar: \n"))
		retorno = "No tiene armas para atacar. ¡Busque una!";
	}
	return retorno;
    }

}
