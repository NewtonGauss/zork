package zork;

public class HablarConComando implements Comando{
 // EJEMPLO: Maxi: habalar;
    @Override
    public String ejecutar(Jugador jugador, String restoDelComando) {
	String retorno = "";
	String[] cadenaPartida = restoDelComando.split(":");
	String objetivo = cadenaPartida[0];
	NPC npc = jugador.getHabitacionActual().getNPC(objetivo);
	if(npc!=null)
		retorno = npc.hablar();
	else
	    retorno = objetivo + " no esta en esta habitacion."; 
	return retorno;
    }
    
    	public HablarConComando()
    	{
    	    
    	}
}
