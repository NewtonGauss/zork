package zork;

public class DarConComando implements Comando {

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
		retorno = "se lo diste, capo";
	    } else {
		retorno = "no tenes ese item, capo";
	    }
	} else {
	    retorno = sujeto + " no se encuentra en la habitacion actual";
	}
	return retorno;
    }

    public DarConComando() {
    }
}
