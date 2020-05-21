package zork;

public class HablarComando implements Comando{

    /*
     * Para el funcionamiento de este comando el contenido de restoDelComando
     * debe ser unicamente el nombre del npc.
     * Ejemplo:
     * HablarComando.ejecutar(jugador,"pirata fantasma");
     */
    @Override
    public String ejecutar(Jugador jugador, String restoDelComando) {
	String retorno = "";
	NPC npc = jugador.getHabitacionActual().getNPC(restoDelComando);
	if(npc != null) {
	    retorno = npc.hablar();
	}
	else {
	    retorno = "El NPC al que desea hablarle no se encuentra en la habitacion actual.";
	}
	return retorno;
    }
    

}
