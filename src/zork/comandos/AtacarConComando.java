package zork.comandos;

import java.util.Iterator;

import zork.*;

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

	Habitacion habitacionActual = jugador.getHabitacionActual();
	NPC npc = habitacionActual.getNPC(objetivo);
	Item item = jugador.getItem(objeto);
	if (item != null && npc != null && item.getTipo().equals(TipoItem.ARMA))
	    retorno = objetivo + ": " + npc.ejecutarTrigger(TipoTrigger.ATAQUE, objeto)
		    + '.';
	else if (npc != null) {
	    retorno = "Utilice uno de los siguientes items para atacar: \n";
	    for (Iterator<Item> i = jugador.getItems().iterator(); i.hasNext();) {
		item = i.next();
		if (item.getTipo().equals(TipoItem.ARMA))
		    retorno += item.getNombre() + '\n';
	    }
	    if (retorno.equals("Utilice uno de los siguientes items para atacar: \n"))
		retorno = "No tiene armas para atacar. ¡Busque una!";
	} else
	    retorno = objetivo + " no se encuentra en " + habitacionActual.toString()
		    + '.';
	return retorno;
    }

    @Override
    public boolean validar(Jugador jugador, String restoDelComando) {
	// TODO Auto-generated method stub
	return false;
    }

}
