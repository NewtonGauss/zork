package zork.comandos;

import zork.Habitacion;
import zork.Jugador;
import zork.NPC;

public class HablarComando implements Comando {

    /**
     * Ejecuta el metodo hablar de {@link NPC}
     * 
     * @param objetivo el nombre del npc
     * @return mensaje de salida por pantalla
     */
    @Override
    public String ejecutar(Jugador jugador, String objetivo) {
	String retorno = "";
	Habitacion habitacionActual = jugador.getHabitacionActual();
	NPC npc = habitacionActual.getNPC(objetivo);
	if (npc != null) {
	    retorno = npc.hablar();
	} else {
	    retorno = objetivo + " no se encuentra en " + habitacionActual.toString()
		    + '.';
	}
	return retorno;
    }

    @Override
    public boolean validar(Jugador jugador, String restoDelComando) {
	return jugador.getHabitacionActual().getNPC(restoDelComando) != null;
    }

}