package zork;

import java.util.Hashtable;
import java.util.Iterator;

public class AtacarConComando implements Comando {

	/*
	 * PARA QUE FUNCIONE EN EL PARAMETRO RESTODELCOMANDO SE TIENE QUE RECIBIR 
	 * "nombreItem:nombreNPC"
	 * 
	 * */
	
	@Override
	public String ejecutar(Jugador jugador, String restoDelComando) {
		String retorno = "";
		String[] aux = restoDelComando.split(":");
		boolean band = false;
		Hashtable<String, NPC> npcs = jugador.getHabitacionActual().getNPC();		
		for (Iterator<Item> i = jugador.getItems().iterator(); i.hasNext();) {
			Item item = i.next();
			if(item.getNombre().equals(aux[0])) {
				retorno = aux[1] + ": " + npcs.get(aux[1]).ejecutarTrigger("attack", aux[0]);
				band = true;
			}
		}
		if(!band) {
			retorno = "Utilice uno de los siguientes items para atacar: ";
			for (Iterator<Item> i = jugador.getItems().iterator(); i.hasNext();) {
				retorno = retorno + "\n" + i.next().getNombre();
			}
		}
		return retorno;
	}
	

}
