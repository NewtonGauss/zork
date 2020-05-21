package zork;

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
	Room habitacionActual = jugador.getHabitacionActual();
	NPC npc = habitacionActual.getNPC(objetivo);
	if (npc != null) {
	    retorno = npc.hablar();
	} else {
	    retorno = objetivo + " no se encuentra en " + habitacionActual.toString()
		    + '.';
	}
	return retorno;
    }

}
