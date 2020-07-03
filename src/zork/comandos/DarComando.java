package zork.comandos;

import utilitarias.Cadena;
import zork.*;
import zork.endgame.ComandoCondicion;

public class DarComando implements Comando {

    private NPC npc;
    private Item item;

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
	String objeto = cadenaPartida[0],
		sujeto = cadenaPartida.length > 1 ? cadenaPartida[1] : "";
	npc = jugador.getHabitacionActual().getNPC(sujeto);
	item = jugador.getItem(objeto);
	if (npc != null) {
	    if (item != null && item.esUsoValido(AccionItem.DROP)) {
		retorno = npc.ejecutarTrigger(TipoTrigger.ITEM, item.getNombre());
		if (retorno == null) {
		    npc.ponerItem(item);
		    jugador.sacarItem(item.getNombre());
		    retorno = "Le diste " + item.toString() + " a " + npc.toString()
			    + '.';
		    retorno = Cadena.replaceAEl(retorno);
		}
	    } else if (item != null)
		retorno = "No puedes soltar " + item.toString() + '.';
	    else
		retorno = "No tienes " + objeto + " en tu inventario.";
	} else {
	    retorno = sujeto + " no se encuentra en "
		    + jugador.getHabitacionActual().toString() + '.';
	}
	return retorno;
    }

    @Override
    public boolean validar(Jugador jugador, String restoDelComando) {
	String[] cadenaPartida = restoDelComando.split(":");
	String objeto = cadenaPartida[0],
		sujeto = cadenaPartida.length > 1 ? cadenaPartida[1] : "";
	npc = jugador.getHabitacionActual().getNPC(sujeto);
	item = jugador.getItem(objeto);
	return npc != null && item != null;
    }

    @Override
    public ComandoCondicion getTipo() {
	return ComandoCondicion.DAR;
    }

}
