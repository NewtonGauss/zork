package zork;

public class MirarComando implements Comando {

    @Override
    public String ejecutar(Jugador jugador, String objetivo) {
	String mensajeSalida = "";
	Room habitacionActual = jugador.getHabitacionActual();
	NPC objetivoDescribir;
	if (objetivo.equals("") || objetivo.equals("alrededor")
		|| objetivo.equals(habitacionActual.getNombre()))
	    mensajeSalida = habitacionActual.getDescription();
	else if ( (objetivoDescribir = habitacionActual.getNPC(objetivo)) != null )
	    mensajeSalida = objetivoDescribir.getDescripcion();
	else
	    mensajeSalida = objetivo + " no existe";
	return mensajeSalida;
    }

}
